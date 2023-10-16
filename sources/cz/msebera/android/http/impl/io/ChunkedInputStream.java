package cz.msebera.android.http.impl.io;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpException;
import cz.msebera.android.http.IOException;
import cz.msebera.android.http.MalformedChunkCodingException;
import cz.msebera.android.http.TruncatedChunkException;
import cz.msebera.android.http.io.BufferInfo;
import cz.msebera.android.http.io.SessionInputBuffer;
import cz.msebera.android.http.message.LineParser;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.CharArrayBuffer;
import cz.msebera.android.http.protocol.MessageConstraints;
import java.io.InputStream;

public class ChunkedInputStream extends InputStream {
    private final CharArrayBuffer buffer;
    private int chunkSize;
    private boolean closed;
    private final MessageConstraints constraints;
    private boolean eof;
    private Header[] footers;
    private final SessionInputBuffer in;
    private int pos;
    private int state;

    public ChunkedInputStream(SessionInputBuffer sessionInputBuffer) {
        this(sessionInputBuffer, (MessageConstraints) null);
    }

    public ChunkedInputStream(SessionInputBuffer $r1, MessageConstraints $r2) {
        this.eof = false;
        this.closed = false;
        this.footers = new Header[0];
        Args.notNull($r1, "Session input buffer");
        this.in = $r1;
        this.pos = 0;
        this.buffer = new CharArrayBuffer(16);
        this.constraints = $r2 == null ? MessageConstraints.DEFAULT : $r2;
        this.state = 1;
    }

    private int getChunkSize() {
        int $i0 = this.state;
        if ($i0 != 1) {
            if ($i0 == 3) {
                this.buffer.clear();
                if (this.in.readLine(this.buffer) == -1) {
                    throw new MalformedChunkCodingException("CRLF expected at end of chunk");
                } else if (this.buffer.isEmpty()) {
                    this.state = 1;
                } else {
                    throw new MalformedChunkCodingException("Unexpected content at the end of chunk");
                }
            } else {
                throw new IllegalStateException("Inconsistent codec state");
            }
        }
        this.buffer.clear();
        if (this.in.readLine(this.buffer) != -1) {
            int $i02 = this.buffer.indexOf(59);
            int $i1 = $i02;
            if ($i02 < 0) {
                $i1 = this.buffer.length();
            }
            try {
                return Integer.parseInt(this.buffer.substringTrimmed(0, $i1), 16);
            } catch (NumberFormatException e) {
                throw new MalformedChunkCodingException("Bad chunk header");
            }
        } else {
            throw new IOException("Premature end of chunk coded message body: closing chunk expected");
        }
    }

    private void nextChunk() {
        if (this.state != Integer.MAX_VALUE) {
            try {
                this.chunkSize = getChunkSize();
                if (this.chunkSize >= 0) {
                    this.state = 2;
                    this.pos = 0;
                    if (this.chunkSize == 0) {
                        this.eof = true;
                        parseTrailerHeaders();
                        return;
                    }
                    return;
                }
                throw new MalformedChunkCodingException("Negative chunk size");
            } catch (MalformedChunkCodingException $r1) {
                this.state = Integer.MAX_VALUE;
                throw $r1;
            }
        } else {
            throw new MalformedChunkCodingException("Corrupt data stream");
        }
    }

    private void parseTrailerHeaders() {
        try {
            this.footers = AbstractMessageParser.parseHeaders(this.in, this.constraints.getMaxLineLength(), this.constraints.getMaxHeaderCount(), (LineParser) null);
        } catch (HttpException $r4) {
            MalformedChunkCodingException $r5 = new MalformedChunkCodingException("Invalid footer: " + $r4.getMessage());
            $r5.initCause($r4);
            throw $r5;
        }
    }

    public int available() {
        SessionInputBuffer $r1 = this.in;
        if ($r1 instanceof BufferInfo) {
            return Math.min(((BufferInfo) $r1).length(), this.chunkSize - this.pos);
        }
        return 0;
    }

    public void close() {
        if (!this.closed) {
            try {
                if (!this.eof) {
                    if (this.state != Integer.MAX_VALUE) {
                        do {
                        } while (read(new byte[2048]) >= 0);
                    }
                }
            } finally {
                this.eof = true;
                this.closed = true;
            }
        }
    }

    public int read() {
        if (this.closed) {
            throw new java.io.IOException("Attempted read from closed stream.");
        } else if (this.eof) {
            return -1;
        } else {
            if (this.state != 2) {
                nextChunk();
                if (this.eof) {
                    return -1;
                }
            }
            int $i0 = this.in.read();
            if ($i0 != -1) {
                this.pos++;
                if (this.pos >= this.chunkSize) {
                    this.state = 3;
                    return $i0;
                }
            }
            return $i0;
        }
    }

    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) {
        if (this.closed) {
            throw new java.io.IOException("Attempted read from closed stream.");
        } else if (this.eof) {
            return -1;
        } else {
            if (this.state != 2) {
                nextChunk();
                if (this.eof) {
                    return -1;
                }
            }
            int $i1 = this.in.read(bArr, i, Math.min(i2, this.chunkSize - this.pos));
            if ($i1 != -1) {
                this.pos += $i1;
                if (this.pos < this.chunkSize) {
                    return $i1;
                }
                this.state = 3;
                return $i1;
            }
            this.eof = true;
            throw new TruncatedChunkException("Truncated chunk ( expected size: " + this.chunkSize + "; actual size: " + this.pos + ")");
        }
    }
}

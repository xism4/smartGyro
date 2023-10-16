package cz.msebera.android.http.impl.io;

import cz.msebera.android.http.io.BufferInfo;
import cz.msebera.android.http.io.SessionInputBuffer;
import cz.msebera.android.http.mime.Args;
import java.io.IOException;
import java.io.InputStream;

public class ContentLengthInputStream extends InputStream {
    private boolean closed = false;
    private final long contentLength;
    private SessionInputBuffer in = null;
    private long pos = 0;

    public ContentLengthInputStream(SessionInputBuffer $r1, long j) {
        Args.notNull($r1, "Session input buffer");
        this.in = $r1;
        Args.notNegative(j, "Content length");
        this.contentLength = j;
    }

    public int available() {
        SessionInputBuffer $r1 = this.in;
        if ($r1 instanceof BufferInfo) {
            return Math.min(((BufferInfo) $r1).length(), (int) (this.contentLength - this.pos));
        }
        return 0;
    }

    public void close() {
        if (!this.closed) {
            try {
                if (this.pos < this.contentLength) {
                    do {
                    } while (read(new byte[2048]) >= 0);
                }
            } finally {
                this.closed = true;
            }
        }
    }

    public int read() {
        if (this.closed) {
            throw new IOException("Attempted read from closed stream.");
        } else if (this.pos >= this.contentLength) {
            return -1;
        } else {
            int $i3 = this.in.read();
            if ($i3 != -1) {
                this.pos++;
                return $i3;
            } else if (this.pos >= this.contentLength) {
                return $i3;
            } else {
                throw new cz.msebera.android.http.IOException("Premature end of Content-Length delimited message body (expected: " + this.contentLength + "; received: " + this.pos);
            }
        }
    }

    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int $i2) {
        if (!this.closed) {
            long $l3 = this.pos;
            long $l4 = this.contentLength;
            if ($l3 >= $l4) {
                return -1;
            }
            if (((long) $i2) + $l3 > $l4) {
                $i2 = (int) ($l4 - $l3);
            }
            int $i1 = this.in.read(bArr, i, $i2);
            if ($i1 == -1 && this.pos < this.contentLength) {
                throw new cz.msebera.android.http.IOException("Premature end of Content-Length delimited message body (expected: " + this.contentLength + "; received: " + this.pos);
            } else if ($i1 <= 0) {
                return $i1;
            } else {
                this.pos += (long) $i1;
                return $i1;
            }
        } else {
            throw new IOException("Attempted read from closed stream.");
        }
    }

    public long skip(long j) {
        int $i0;
        if (j <= 0) {
            return 0;
        }
        byte[] $r1 = new byte[2048];
        long $l1 = Math.min(j, this.contentLength - this.pos);
        long $l3 = 0;
        while ($l1 > 0 && ($i0 = read($r1, 0, (int) Math.min(2048, $l1))) != -1) {
            long $l4 = (long) $i0;
            $l3 += $l4;
            $l1 -= $l4;
        }
        return $l3;
    }
}

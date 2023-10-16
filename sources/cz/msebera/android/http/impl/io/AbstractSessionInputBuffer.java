package cz.msebera.android.http.impl.io;

import cz.msebera.android.http.Consts;
import cz.msebera.android.http.io.BufferInfo;
import cz.msebera.android.http.io.HttpTransportMetrics;
import cz.msebera.android.http.io.SessionInputBuffer;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.ByteArrayBuffer;
import cz.msebera.android.http.mime.CharArrayBuffer;
import cz.msebera.android.http.util.HttpParams;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;

@Deprecated
public abstract class AbstractSessionInputBuffer implements SessionInputBuffer, BufferInfo {
    private boolean ascii;
    private byte[] buffer;
    private int bufferlen;
    private int bufferpos;
    private CharBuffer cbuf;
    private Charset charset;
    private CharsetDecoder decoder;
    private InputStream instream;
    private ByteArrayBuffer linebuffer;
    private int maxLineLen;
    private HttpTransportMetricsImpl metrics;
    private int minChunkLimit;
    private CodingErrorAction onMalformedCharAction;
    private CodingErrorAction onUnmappableCharAction;

    private int appendDecoded(CharArrayBuffer charArrayBuffer, ByteBuffer byteBuffer) {
        int $i0 = 0;
        if (!byteBuffer.hasRemaining()) {
            return 0;
        }
        if (this.decoder == null) {
            this.decoder = this.charset.newDecoder();
            this.decoder.onMalformedInput(this.onMalformedCharAction);
            this.decoder.onUnmappableCharacter(this.onUnmappableCharAction);
        }
        if (this.cbuf == null) {
            this.cbuf = CharBuffer.allocate(1024);
        }
        this.decoder.reset();
        while (byteBuffer.hasRemaining()) {
            $i0 += handleDecodingResult(this.decoder.decode(byteBuffer, this.cbuf, true), charArrayBuffer, byteBuffer);
        }
        int $i02 = $i0 + handleDecodingResult(this.decoder.flush(this.cbuf), charArrayBuffer, byteBuffer);
        this.cbuf.clear();
        return $i02;
    }

    private int handleDecodingResult(CoderResult coderResult, CharArrayBuffer charArrayBuffer, ByteBuffer byteBuffer) {
        if (coderResult.isError()) {
            coderResult.throwException();
        }
        this.cbuf.flip();
        int $i0 = this.cbuf.remaining();
        while (this.cbuf.hasRemaining()) {
            charArrayBuffer.append(this.cbuf.get());
        }
        this.cbuf.compact();
        return $i0;
    }

    private int lineFromLineBuffer(CharArrayBuffer charArrayBuffer) {
        int $i0 = this.linebuffer.length();
        int $i1 = $i0;
        if ($i0 > 0) {
            if (this.linebuffer.byteAt($i0 - 1) == 10) {
                $i1 = $i0 - 1;
            }
            if ($i1 > 0 && this.linebuffer.byteAt($i1 - 1) == 13) {
                $i1--;
            }
        }
        if (this.ascii) {
            charArrayBuffer.append(this.linebuffer, 0, $i1);
        } else {
            $i1 = appendDecoded(charArrayBuffer, ByteBuffer.wrap(this.linebuffer.buffer(), 0, $i1));
        }
        this.linebuffer.clear();
        return $i1;
    }

    private int lineFromReadBuffer(CharArrayBuffer charArrayBuffer, int $i1) {
        int $i0 = this.bufferpos;
        this.bufferpos = $i1 + 1;
        if ($i1 > $i0 && this.buffer[$i1 - 1] == 13) {
            $i1--;
        }
        int $i12 = $i1 - $i0;
        if (!this.ascii) {
            return appendDecoded(charArrayBuffer, ByteBuffer.wrap(this.buffer, $i0, $i12));
        }
        charArrayBuffer.append(this.buffer, $i0, $i12);
        return $i12;
    }

    private int locateLF() {
        for (int $i0 = this.bufferpos; $i0 < this.bufferlen; $i0++) {
            if (this.buffer[$i0] == 10) {
                return $i0;
            }
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public HttpTransportMetricsImpl createTransportMetrics() {
        return new HttpTransportMetricsImpl();
    }

    /* access modifiers changed from: protected */
    public int fillBuffer() {
        int $i1 = this.bufferpos;
        if ($i1 > 0) {
            int $i0 = this.bufferlen - $i1;
            if ($i0 > 0) {
                byte[] $r1 = this.buffer;
                System.arraycopy($r1, $i1, $r1, 0, $i0);
            }
            this.bufferpos = 0;
            this.bufferlen = $i0;
        }
        int $i12 = this.bufferlen;
        byte[] $r12 = this.buffer;
        int $i02 = this.instream.read($r12, $i12, $r12.length - $i12);
        if ($i02 == -1) {
            return -1;
        }
        this.bufferlen = $i12 + $i02;
        this.metrics.incrementBytesTransferred((long) $i02);
        return $i02;
    }

    public HttpTransportMetrics getMetrics() {
        return this.metrics;
    }

    /* access modifiers changed from: protected */
    public boolean hasBufferedData() {
        return this.bufferpos < this.bufferlen;
    }

    /* access modifiers changed from: protected */
    public void init(InputStream inputStream, int i, HttpParams httpParams) {
        Args.notNull(inputStream, "Input stream");
        Args.notNegative(i, "Buffer size");
        Args.notNull(httpParams, "HTTP parameters");
        this.instream = inputStream;
        this.buffer = new byte[i];
        this.bufferpos = 0;
        this.bufferlen = 0;
        this.linebuffer = new ByteArrayBuffer(i);
        String $r6 = (String) httpParams.getParameter("http.protocol.element-charset");
        this.charset = $r6 != null ? Charset.forName($r6) : Consts.ASCII;
        this.ascii = this.charset.equals(Consts.ASCII);
        this.decoder = null;
        this.maxLineLen = httpParams.getIntParameter("http.connection.max-line-length", -1);
        this.minChunkLimit = httpParams.getIntParameter("http.connection.min-chunk-limit", 512);
        this.metrics = createTransportMetrics();
        CodingErrorAction $r10 = (CodingErrorAction) httpParams.getParameter("http.malformed.input.action");
        if ($r10 == null) {
            $r10 = CodingErrorAction.REPORT;
        }
        this.onMalformedCharAction = $r10;
        CodingErrorAction $r102 = (CodingErrorAction) httpParams.getParameter("http.unmappable.input.action");
        if ($r102 == null) {
            $r102 = CodingErrorAction.REPORT;
        }
        this.onUnmappableCharAction = $r102;
    }

    public int length() {
        return this.bufferlen - this.bufferpos;
    }

    public int read() {
        while (!hasBufferedData()) {
            if (fillBuffer() == -1) {
                return -1;
            }
        }
        byte[] $r1 = this.buffer;
        int $i1 = this.bufferpos;
        this.bufferpos = $i1 + 1;
        return $r1[$i1] & 255;
    }

    public int read(byte[] bArr, int $i0, int $i1) {
        int $i12;
        if (bArr == null) {
            return 0;
        }
        if (hasBufferedData()) {
            int $i2 = Math.min($i1, this.bufferlen - this.bufferpos);
            $i12 = $i2;
            System.arraycopy(this.buffer, this.bufferpos, bArr, $i0, $i2);
        } else if ($i1 > this.minChunkLimit) {
            int $i02 = this.instream.read(bArr, $i0, $i1);
            if ($i02 <= 0) {
                return $i02;
            }
            this.metrics.incrementBytesTransferred((long) $i02);
            return $i02;
        } else {
            while (!hasBufferedData()) {
                if (fillBuffer() == -1) {
                    return -1;
                }
            }
            int $i22 = Math.min($i1, this.bufferlen - this.bufferpos);
            $i12 = $i22;
            System.arraycopy(this.buffer, this.bufferpos, bArr, $i0, $i22);
        }
        this.bufferpos += $i12;
        return $i12;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004a, code lost:
        if (r3 == -1) goto L_0x002c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int readLine(cz.msebera.android.http.mime.CharArrayBuffer r12) {
        /*
            r11 = this;
            java.lang.String r0 = "Char array buffer"
            cz.msebera.android.http.mime.Args.notNull(r12, r0)
            r1 = 1
            r2 = 0
        L_0x0007:
            if (r1 == 0) goto L_0x0064
            int r3 = r11.locateLF()
            r4 = -1
            if (r3 == r4) goto L_0x002e
            cz.msebera.android.http.mime.ByteArrayBuffer r5 = r11.linebuffer
            boolean r1 = r5.isEmpty()
            if (r1 == 0) goto L_0x001d
            int r2 = r11.lineFromReadBuffer(r12, r3)
            return r2
        L_0x001d:
            int r3 = r3 + 1
            int r6 = r11.bufferpos
            int r7 = r3 - r6
            cz.msebera.android.http.mime.ByteArrayBuffer r5 = r11.linebuffer
            byte[] r8 = r11.buffer
            r5.append((byte[]) r8, (int) r6, (int) r7)
            r11.bufferpos = r3
        L_0x002c:
            r1 = 0
            goto L_0x004d
        L_0x002e:
            boolean r9 = r11.hasBufferedData()
            if (r9 == 0) goto L_0x0044
            int r3 = r11.bufferlen
            int r2 = r11.bufferpos
            int r3 = r3 - r2
            cz.msebera.android.http.mime.ByteArrayBuffer r5 = r11.linebuffer
            byte[] r8 = r11.buffer
            r5.append((byte[]) r8, (int) r2, (int) r3)
            int r2 = r11.bufferlen
            r11.bufferpos = r2
        L_0x0044:
            int r3 = r11.fillBuffer()
            r2 = r3
            r4 = -1
            if (r3 != r4) goto L_0x004d
            goto L_0x002c
        L_0x004d:
            int r3 = r11.maxLineLen
            if (r3 <= 0) goto L_0x0007
            cz.msebera.android.http.mime.ByteArrayBuffer r5 = r11.linebuffer
            int r3 = r5.length()
            int r6 = r11.maxLineLen
            if (r3 >= r6) goto L_0x005c
            goto L_0x0007
        L_0x005c:
            java.io.IOException r10 = new java.io.IOException
            java.lang.String r0 = "Maximum line length limit exceeded"
            r10.<init>(r0)
            throw r10
        L_0x0064:
            r4 = -1
            if (r2 != r4) goto L_0x0071
            cz.msebera.android.http.mime.ByteArrayBuffer r5 = r11.linebuffer
            boolean r1 = r5.isEmpty()
            if (r1 == 0) goto L_0x0071
            r4 = -1
            return r4
        L_0x0071:
            int r2 = r11.lineFromLineBuffer(r12)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.impl.io.AbstractSessionInputBuffer.readLine(cz.msebera.android.http.mime.CharArrayBuffer):int");
    }
}

package cz.msebera.android.http.impl.io;

import cz.msebera.android.http.Consts;
import cz.msebera.android.http.io.BufferInfo;
import cz.msebera.android.http.io.HttpTransportMetrics;
import cz.msebera.android.http.io.SessionOutputBuffer;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.ByteArrayBuffer;
import cz.msebera.android.http.mime.CharArrayBuffer;
import cz.msebera.android.http.util.HttpParams;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;

@Deprecated
public abstract class AbstractSessionOutputBuffer implements SessionOutputBuffer, BufferInfo {
    private static final byte[] CRLF = {13, 10};
    private boolean ascii;
    private ByteBuffer bbuf;
    private ByteArrayBuffer buffer;
    private Charset charset;
    private CharsetEncoder encoder;
    private HttpTransportMetricsImpl metrics;
    private int minChunkLimit;
    private CodingErrorAction onMalformedCharAction;
    private CodingErrorAction onUnmappableCharAction;
    private OutputStream outstream;

    private void handleEncodingResult(CoderResult coderResult) {
        if (coderResult.isError()) {
            coderResult.throwException();
        }
        this.bbuf.flip();
        while (this.bbuf.hasRemaining()) {
            write((int) this.bbuf.get());
        }
        this.bbuf.compact();
    }

    private void writeEncoded(CharBuffer charBuffer) {
        if (charBuffer.hasRemaining()) {
            if (this.encoder == null) {
                this.encoder = this.charset.newEncoder();
                this.encoder.onMalformedInput(this.onMalformedCharAction);
                this.encoder.onUnmappableCharacter(this.onUnmappableCharAction);
            }
            if (this.bbuf == null) {
                this.bbuf = ByteBuffer.allocate(1024);
            }
            this.encoder.reset();
            while (charBuffer.hasRemaining()) {
                handleEncodingResult(this.encoder.encode(charBuffer, this.bbuf, true));
            }
            handleEncodingResult(this.encoder.flush(this.bbuf));
            this.bbuf.clear();
        }
    }

    /* access modifiers changed from: protected */
    public HttpTransportMetricsImpl createTransportMetrics() {
        return new HttpTransportMetricsImpl();
    }

    public void flush() {
        flushBuffer();
        this.outstream.flush();
    }

    /* access modifiers changed from: protected */
    public void flushBuffer() {
        int $i0 = this.buffer.length();
        if ($i0 > 0) {
            this.outstream.write(this.buffer.buffer(), 0, $i0);
            this.buffer.clear();
            this.metrics.incrementBytesTransferred((long) $i0);
        }
    }

    public HttpTransportMetrics getMetrics() {
        return this.metrics;
    }

    /* access modifiers changed from: protected */
    public void init(OutputStream outputStream, int i, HttpParams httpParams) {
        Args.notNull(outputStream, "Input stream");
        Args.notNegative(i, "Buffer size");
        Args.notNull(httpParams, "HTTP parameters");
        this.outstream = outputStream;
        this.buffer = new ByteArrayBuffer(i);
        String $r5 = (String) httpParams.getParameter("http.protocol.element-charset");
        this.charset = $r5 != null ? Charset.forName($r5) : Consts.ASCII;
        this.ascii = this.charset.equals(Consts.ASCII);
        this.encoder = null;
        this.minChunkLimit = httpParams.getIntParameter("http.connection.min-chunk-limit", 512);
        this.metrics = createTransportMetrics();
        CodingErrorAction $r9 = (CodingErrorAction) httpParams.getParameter("http.malformed.input.action");
        if ($r9 == null) {
            $r9 = CodingErrorAction.REPORT;
        }
        this.onMalformedCharAction = $r9;
        CodingErrorAction $r92 = (CodingErrorAction) httpParams.getParameter("http.unmappable.input.action");
        if ($r92 == null) {
            $r92 = CodingErrorAction.REPORT;
        }
        this.onUnmappableCharAction = $r92;
    }

    public int length() {
        return this.buffer.length();
    }

    public void write(int i) {
        if (this.buffer.isFull()) {
            flushBuffer();
        }
        this.buffer.append(i);
    }

    public void write(byte[] bArr) {
        if (bArr != null) {
            write(bArr, 0, bArr.length);
        }
    }

    public void write(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            if (i2 > this.minChunkLimit || i2 > this.buffer.capacity()) {
                flushBuffer();
                this.outstream.write(bArr, i, i2);
                this.metrics.incrementBytesTransferred((long) i2);
                return;
            }
            if (i2 > this.buffer.capacity() - this.buffer.length()) {
                flushBuffer();
            }
            this.buffer.append(bArr, i, i2);
        }
    }

    public void writeLine(CharArrayBuffer charArrayBuffer) {
        if (charArrayBuffer != null) {
            int $i0 = 0;
            if (this.ascii) {
                int $i1 = charArrayBuffer.length();
                while ($i1 > 0) {
                    int $i2 = Math.min(this.buffer.capacity() - this.buffer.length(), $i1);
                    if ($i2 > 0) {
                        this.buffer.append(charArrayBuffer, $i0, $i2);
                    }
                    if (this.buffer.isFull()) {
                        flushBuffer();
                    }
                    $i0 += $i2;
                    $i1 -= $i2;
                }
            } else {
                writeEncoded(CharBuffer.wrap(charArrayBuffer.buffer(), 0, charArrayBuffer.length()));
            }
            write(CRLF);
        }
    }

    public void writeLine(String str) {
        if (str != null) {
            if (str.length() > 0) {
                if (this.ascii) {
                    for (int $i0 = 0; $i0 < str.length(); $i0++) {
                        write((int) str.charAt($i0));
                    }
                } else {
                    writeEncoded(CharBuffer.wrap(str));
                }
            }
            write(CRLF);
        }
    }
}

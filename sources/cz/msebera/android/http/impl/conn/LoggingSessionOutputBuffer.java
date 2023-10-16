package cz.msebera.android.http.impl.conn;

import cz.msebera.android.http.Consts;
import cz.msebera.android.http.io.HttpTransportMetrics;
import cz.msebera.android.http.io.SessionOutputBuffer;
import cz.msebera.android.http.mime.CharArrayBuffer;

@Deprecated
public class LoggingSessionOutputBuffer implements SessionOutputBuffer {
    private final String charset;
    private final SessionOutputBuffer out;
    private final Wire wire;

    public LoggingSessionOutputBuffer(SessionOutputBuffer sessionOutputBuffer, Wire wire2, String $r2) {
        this.out = sessionOutputBuffer;
        this.wire = wire2;
        this.charset = $r2 == null ? Consts.ASCII.name() : $r2;
    }

    public void flush() {
        this.out.flush();
    }

    public HttpTransportMetrics getMetrics() {
        return this.out.getMetrics();
    }

    public void write(int i) {
        this.out.write(i);
        if (this.wire.enabled()) {
            this.wire.output(i);
        }
    }

    public void write(byte[] bArr, int i, int i2) {
        this.out.write(bArr, i, i2);
        if (this.wire.enabled()) {
            this.wire.output(bArr, i, i2);
        }
    }

    public void writeLine(CharArrayBuffer charArrayBuffer) {
        this.out.writeLine(charArrayBuffer);
        if (this.wire.enabled()) {
            String $r4 = new String(charArrayBuffer.buffer(), 0, charArrayBuffer.length());
            this.wire.output(($r4 + "\r\n").getBytes(this.charset));
        }
    }

    public void writeLine(String str) {
        this.out.writeLine(str);
        if (this.wire.enabled()) {
            this.wire.output((str + "\r\n").getBytes(this.charset));
        }
    }
}

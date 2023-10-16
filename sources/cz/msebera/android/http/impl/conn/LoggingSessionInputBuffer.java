package cz.msebera.android.http.impl.conn;

import cz.msebera.android.http.Consts;
import cz.msebera.android.http.io.EofSensor;
import cz.msebera.android.http.io.HttpTransportMetrics;
import cz.msebera.android.http.io.SessionInputBuffer;
import cz.msebera.android.http.mime.CharArrayBuffer;

@Deprecated
public class LoggingSessionInputBuffer implements SessionInputBuffer, EofSensor {
    private final String charset;
    private final EofSensor eofSensor;
    private final SessionInputBuffer in;
    private final Wire wire;

    public LoggingSessionInputBuffer(SessionInputBuffer sessionInputBuffer, Wire wire2, String $r2) {
        this.in = sessionInputBuffer;
        this.eofSensor = sessionInputBuffer instanceof EofSensor ? (EofSensor) sessionInputBuffer : null;
        this.wire = wire2;
        this.charset = $r2 == null ? Consts.ASCII.name() : $r2;
    }

    public HttpTransportMetrics getMetrics() {
        return this.in.getMetrics();
    }

    public boolean isDataAvailable(int i) {
        return this.in.isDataAvailable(i);
    }

    public boolean isEof() {
        EofSensor $r1 = this.eofSensor;
        if ($r1 != null) {
            return $r1.isEof();
        }
        return false;
    }

    public int read() {
        int $i0 = this.in.read();
        if (this.wire.enabled() && $i0 != -1) {
            this.wire.input($i0);
        }
        return $i0;
    }

    public int read(byte[] bArr, int i, int i2) {
        int $i1 = this.in.read(bArr, i, i2);
        if (this.wire.enabled() && $i1 > 0) {
            this.wire.input(bArr, i, $i1);
        }
        return $i1;
    }

    public int readLine(CharArrayBuffer charArrayBuffer) {
        int $i0 = this.in.readLine(charArrayBuffer);
        if (this.wire.enabled() && $i0 >= 0) {
            String $r4 = new String(charArrayBuffer.buffer(), charArrayBuffer.length() - $i0, $i0);
            this.wire.input(($r4 + "\r\n").getBytes(this.charset));
        }
        return $i0;
    }
}

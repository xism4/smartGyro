package cz.msebera.android.http.impl.conn;

import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.mime.Args;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Wire {
    private final String id;
    public HttpClientAndroidLog log;

    public Wire(HttpClientAndroidLog httpClientAndroidLog) {
        this(httpClientAndroidLog, "");
    }

    public Wire(HttpClientAndroidLog httpClientAndroidLog, String str) {
        this.log = httpClientAndroidLog;
        this.id = str;
    }

    private void wire(String str, InputStream inputStream) {
        String $r1;
        StringBuilder $r4 = new StringBuilder();
        while (true) {
            int $i0 = inputStream.read();
            if ($i0 == -1) {
                break;
            }
            if ($i0 == 13) {
                $r1 = "[\\r]";
            } else if ($i0 == 10) {
                $r4.append("[\\n]\"");
                $r4.insert(0, "\"");
                $r4.insert(0, str);
                HttpClientAndroidLog $r5 = this.log;
                $r5.debug(this.id + " " + $r4.toString());
                $r4.setLength(0);
            } else if ($i0 < 32 || $i0 > 127) {
                $r4.append("[0x");
                $r4.append(Integer.toHexString($i0));
                $r1 = "]";
            } else {
                $r4.append((char) $i0);
            }
            $r4.append($r1);
        }
        if ($r4.length() > 0) {
            $r4.append('\"');
            $r4.insert(0, '\"');
            $r4.insert(0, str);
            HttpClientAndroidLog $r52 = this.log;
            $r52.debug(this.id + " " + $r4.toString());
        }
    }

    public boolean enabled() {
        return this.log.isDebugEnabled();
    }

    public void input(int i) {
        input(new byte[]{(byte) i});
    }

    public void input(byte[] bArr) {
        Args.notNull(bArr, "Input");
        wire("<< ", new ByteArrayInputStream(bArr));
    }

    public void input(byte[] bArr, int i, int i2) {
        Args.notNull(bArr, "Input");
        wire("<< ", new ByteArrayInputStream(bArr, i, i2));
    }

    public void output(int i) {
        output(new byte[]{(byte) i});
    }

    public void output(byte[] bArr) {
        Args.notNull(bArr, "Output");
        wire(">> ", new ByteArrayInputStream(bArr));
    }

    public void output(byte[] bArr, int i, int i2) {
        Args.notNull(bArr, "Output");
        wire(">> ", new ByteArrayInputStream(bArr, i, i2));
    }
}

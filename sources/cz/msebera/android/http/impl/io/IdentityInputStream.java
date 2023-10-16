package cz.msebera.android.http.impl.io;

import cz.msebera.android.http.io.BufferInfo;
import cz.msebera.android.http.io.SessionInputBuffer;
import cz.msebera.android.http.mime.Args;
import java.io.InputStream;

public class IdentityInputStream extends InputStream {
    private boolean closed = false;
    private final SessionInputBuffer in;

    public IdentityInputStream(SessionInputBuffer $r1) {
        Args.notNull($r1, "Session input buffer");
        this.in = $r1;
    }

    public int available() {
        SessionInputBuffer $r1 = this.in;
        if ($r1 instanceof BufferInfo) {
            return ((BufferInfo) $r1).length();
        }
        return 0;
    }

    public void close() {
        this.closed = true;
    }

    public int read() {
        if (this.closed) {
            return -1;
        }
        return this.in.read();
    }

    public int read(byte[] bArr, int i, int i2) {
        if (this.closed) {
            return -1;
        }
        return this.in.read(bArr, i, i2);
    }
}

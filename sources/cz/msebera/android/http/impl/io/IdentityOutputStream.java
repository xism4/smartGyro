package cz.msebera.android.http.impl.io;

import cz.msebera.android.http.io.SessionOutputBuffer;
import cz.msebera.android.http.mime.Args;
import java.io.IOException;
import java.io.OutputStream;

public class IdentityOutputStream extends OutputStream {
    private boolean closed = false;
    private final SessionOutputBuffer out;

    public IdentityOutputStream(SessionOutputBuffer $r1) {
        Args.notNull($r1, "Session output buffer");
        this.out = $r1;
    }

    public void close() {
        if (!this.closed) {
            this.closed = true;
            this.out.flush();
        }
    }

    public void flush() {
        this.out.flush();
    }

    public void write(int i) {
        if (!this.closed) {
            this.out.write(i);
            return;
        }
        throw new IOException("Attempted write to closed stream.");
    }

    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) {
        if (!this.closed) {
            this.out.write(bArr, i, i2);
            return;
        }
        throw new IOException("Attempted write to closed stream.");
    }
}

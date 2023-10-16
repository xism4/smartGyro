package cz.msebera.android.http.impl.auth;

import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;

class HttpEntityDigester extends OutputStream {
    private boolean closed;
    private byte[] digest;
    private final MessageDigest digester;

    HttpEntityDigester(MessageDigest messageDigest) {
        this.digester = messageDigest;
        this.digester.reset();
    }

    public void close() {
        if (!this.closed) {
            this.closed = true;
            this.digest = this.digester.digest();
            super.close();
        }
    }

    public byte[] getDigest() {
        return this.digest;
    }

    public void write(int i) {
        if (!this.closed) {
            this.digester.update((byte) i);
            return;
        }
        throw new IOException("Stream has been already closed");
    }

    public void write(byte[] bArr, int i, int i2) {
        if (!this.closed) {
            this.digester.update(bArr, i, i2);
            return;
        }
        throw new IOException("Stream has been already closed");
    }
}

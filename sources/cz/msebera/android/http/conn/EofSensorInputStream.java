package cz.msebera.android.http.conn;

import cz.msebera.android.http.mime.Args;
import java.io.IOException;
import java.io.InputStream;

public class EofSensorInputStream extends InputStream implements ConnectionReleaseTrigger {
    private final EofSensorWatcher eofWatcher;
    private boolean selfClosed = false;
    protected InputStream wrappedStream;

    public EofSensorInputStream(InputStream inputStream, EofSensorWatcher eofSensorWatcher) {
        Args.notNull(inputStream, "Wrapped stream");
        this.wrappedStream = inputStream;
        this.eofWatcher = eofSensorWatcher;
    }

    public void abortConnection() {
        this.selfClosed = true;
        checkAbort();
    }

    public int available() {
        if (!isReadAllowed()) {
            return 0;
        }
        try {
            return this.wrappedStream.available();
        } catch (IOException $r2) {
            checkAbort();
            throw $r2;
        }
    }

    /* access modifiers changed from: protected */
    public void checkAbort() {
        InputStream $r2 = this.wrappedStream;
        if ($r2 != null) {
            boolean $z0 = true;
            try {
                if (this.eofWatcher != null) {
                    $z0 = this.eofWatcher.streamAbort($r2);
                }
                if ($z0) {
                    this.wrappedStream.close();
                }
            } finally {
                this.wrappedStream = null;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void checkClose() {
        InputStream $r2 = this.wrappedStream;
        if ($r2 != null) {
            boolean $z0 = true;
            try {
                if (this.eofWatcher != null) {
                    $z0 = this.eofWatcher.streamClosed($r2);
                }
                if ($z0) {
                    this.wrappedStream.close();
                }
            } finally {
                this.wrappedStream = null;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void checkEOF(int i) {
        InputStream $r1 = this.wrappedStream;
        if ($r1 != null && i < 0) {
            boolean $z0 = true;
            try {
                if (this.eofWatcher != null) {
                    $z0 = this.eofWatcher.eofDetected($r1);
                }
                if ($z0) {
                    this.wrappedStream.close();
                }
            } finally {
                this.wrappedStream = null;
            }
        }
    }

    public void close() {
        this.selfClosed = true;
        checkClose();
    }

    /* access modifiers changed from: protected */
    public boolean isReadAllowed() {
        if (!this.selfClosed) {
            return this.wrappedStream != null;
        }
        throw new IOException("Attempted read on closed stream.");
    }

    public int read() {
        if (!isReadAllowed()) {
            return -1;
        }
        try {
            int $i0 = this.wrappedStream.read();
            checkEOF($i0);
            return $i0;
        } catch (IOException $r2) {
            checkAbort();
            throw $r2;
        }
    }

    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) {
        if (!isReadAllowed()) {
            return -1;
        }
        try {
            int $i0 = this.wrappedStream.read(bArr, i, i2);
            checkEOF($i0);
            return $i0;
        } catch (IOException $r3) {
            checkAbort();
            throw $r3;
        }
    }
}

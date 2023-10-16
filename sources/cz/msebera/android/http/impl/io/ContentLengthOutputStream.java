package cz.msebera.android.http.impl.io;

import cz.msebera.android.http.io.SessionOutputBuffer;
import cz.msebera.android.http.mime.Args;
import java.io.IOException;
import java.io.OutputStream;

public class ContentLengthOutputStream extends OutputStream {
    private boolean closed = false;
    private final long contentLength;
    private final SessionOutputBuffer out;
    private long total = 0;

    public ContentLengthOutputStream(SessionOutputBuffer $r1, long j) {
        Args.notNull($r1, "Session output buffer");
        this.out = $r1;
        Args.notNegative(j, "Content length");
        this.contentLength = j;
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
        if (this.closed) {
            throw new IOException("Attempted write to closed stream.");
        } else if (this.total < this.contentLength) {
            this.out.write(i);
            this.total++;
        }
    }

    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int $i1) {
        if (!this.closed) {
            long $l2 = this.total;
            long $l3 = this.contentLength;
            if ($l2 < $l3) {
                long $l22 = $l3 - $l2;
                if (((long) $i1) > $l22) {
                    $i1 = (int) $l22;
                }
                this.out.write(bArr, i, $i1);
                this.total += (long) $i1;
                return;
            }
            return;
        }
        throw new IOException("Attempted write to closed stream.");
    }
}

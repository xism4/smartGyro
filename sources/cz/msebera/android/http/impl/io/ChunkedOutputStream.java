package cz.msebera.android.http.impl.io;

import cz.msebera.android.http.io.SessionOutputBuffer;
import java.io.IOException;
import java.io.OutputStream;

public class ChunkedOutputStream extends OutputStream {
    private final byte[] cache;
    private int cachePosition;
    private boolean closed;
    private final SessionOutputBuffer out;
    private boolean wroteLastChunk;

    public ChunkedOutputStream(int i, SessionOutputBuffer sessionOutputBuffer) {
        this.cachePosition = 0;
        this.wroteLastChunk = false;
        this.closed = false;
        this.cache = new byte[i];
        this.out = sessionOutputBuffer;
    }

    public ChunkedOutputStream(SessionOutputBuffer sessionOutputBuffer) {
        this(2048, sessionOutputBuffer);
    }

    public void close() {
        if (!this.closed) {
            this.closed = true;
            finish();
            this.out.flush();
        }
    }

    public void finish() {
        if (!this.wroteLastChunk) {
            flushCache();
            writeClosingChunk();
            this.wroteLastChunk = true;
        }
    }

    public void flush() {
        flushCache();
        this.out.flush();
    }

    /* access modifiers changed from: protected */
    public void flushCache() {
        int $i0 = this.cachePosition;
        if ($i0 > 0) {
            this.out.writeLine(Integer.toHexString($i0));
            this.out.write(this.cache, 0, this.cachePosition);
            this.out.writeLine("");
            this.cachePosition = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void flushCacheWithAppend(byte[] bArr, int i, int i2) {
        this.out.writeLine(Integer.toHexString(this.cachePosition + i2));
        this.out.write(this.cache, 0, this.cachePosition);
        this.out.write(bArr, i, i2);
        this.out.writeLine("");
        this.cachePosition = 0;
    }

    public void write(int i) {
        if (!this.closed) {
            byte[] $r1 = this.cache;
            int $i1 = this.cachePosition;
            $r1[$i1] = (byte) i;
            this.cachePosition = $i1 + 1;
            if (this.cachePosition == $r1.length) {
                flushCache();
                return;
            }
            return;
        }
        throw new IOException("Attempted write to closed stream.");
    }

    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) {
        if (!this.closed) {
            byte[] $r2 = this.cache;
            int $i3 = $r2.length;
            int $i1 = this.cachePosition;
            if (i2 >= $i3 - $i1) {
                flushCacheWithAppend(bArr, i, i2);
                return;
            }
            System.arraycopy(bArr, i, $r2, $i1, i2);
            this.cachePosition += i2;
            return;
        }
        throw new IOException("Attempted write to closed stream.");
    }

    /* access modifiers changed from: protected */
    public void writeClosingChunk() {
        this.out.writeLine("0");
        this.out.writeLine("");
    }
}

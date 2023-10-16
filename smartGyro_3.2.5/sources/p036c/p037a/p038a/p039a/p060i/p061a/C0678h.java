package p036c.p037a.p038a.p039a.p060i.p061a;

import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;

/* renamed from: c.a.a.a.i.a.h */
class C0678h extends OutputStream {

    /* renamed from: a */
    private final MessageDigest f2015a;

    /* renamed from: b */
    private boolean f2016b;

    /* renamed from: c */
    private byte[] f2017c;

    C0678h(MessageDigest messageDigest) {
        this.f2015a = messageDigest;
        this.f2015a.reset();
    }

    /* renamed from: a */
    public byte[] mo2835a() {
        return this.f2017c;
    }

    public void close() {
        if (!this.f2016b) {
            this.f2016b = true;
            this.f2017c = this.f2015a.digest();
            super.close();
        }
    }

    public void write(int i) {
        if (!this.f2016b) {
            this.f2015a.update((byte) i);
            return;
        }
        throw new IOException("Stream has been already closed");
    }

    public void write(byte[] bArr, int i, int i2) {
        if (!this.f2016b) {
            this.f2015a.update(bArr, i, i2);
            return;
        }
        throw new IOException("Stream has been already closed");
    }
}

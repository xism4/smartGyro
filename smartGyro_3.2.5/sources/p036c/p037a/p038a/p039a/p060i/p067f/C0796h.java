package p036c.p037a.p038a.p039a.p060i.p067f;

import java.io.IOException;
import java.io.OutputStream;
import p036c.p037a.p038a.p039a.p068j.C0811g;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.f.h */
public class C0796h extends OutputStream {

    /* renamed from: a */
    private final C0811g f2332a;

    /* renamed from: b */
    private final long f2333b;

    /* renamed from: c */
    private long f2334c = 0;

    /* renamed from: d */
    private boolean f2335d = false;

    public C0796h(C0811g gVar, long j) {
        C0870a.m3042a(gVar, "Session output buffer");
        this.f2332a = gVar;
        C0870a.m3040a(j, "Content length");
        this.f2333b = j;
    }

    public void close() {
        if (!this.f2335d) {
            this.f2335d = true;
            this.f2332a.flush();
        }
    }

    public void flush() {
        this.f2332a.flush();
    }

    public void write(int i) {
        if (this.f2335d) {
            throw new IOException("Attempted write to closed stream.");
        } else if (this.f2334c < this.f2333b) {
            this.f2332a.write(i);
            this.f2334c++;
        }
    }

    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) {
        if (!this.f2335d) {
            long j = this.f2334c;
            long j2 = this.f2333b;
            if (j < j2) {
                long j3 = j2 - j;
                if (((long) i2) > j3) {
                    i2 = (int) j3;
                }
                this.f2332a.write(bArr, i, i2);
                this.f2334c += (long) i2;
                return;
            }
            return;
        }
        throw new IOException("Attempted write to closed stream.");
    }
}

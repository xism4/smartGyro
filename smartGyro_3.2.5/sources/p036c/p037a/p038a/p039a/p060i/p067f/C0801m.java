package p036c.p037a.p038a.p039a.p060i.p067f;

import java.io.IOException;
import java.io.OutputStream;
import p036c.p037a.p038a.p039a.p068j.C0811g;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.f.m */
public class C0801m extends OutputStream {

    /* renamed from: a */
    private final C0811g f2340a;

    /* renamed from: b */
    private boolean f2341b = false;

    public C0801m(C0811g gVar) {
        C0870a.m3042a(gVar, "Session output buffer");
        this.f2340a = gVar;
    }

    public void close() {
        if (!this.f2341b) {
            this.f2341b = true;
            this.f2340a.flush();
        }
    }

    public void flush() {
        this.f2340a.flush();
    }

    public void write(int i) {
        if (!this.f2341b) {
            this.f2340a.write(i);
            return;
        }
        throw new IOException("Attempted write to closed stream.");
    }

    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) {
        if (!this.f2341b) {
            this.f2340a.write(bArr, i, i2);
            return;
        }
        throw new IOException("Attempted write to closed stream.");
    }
}

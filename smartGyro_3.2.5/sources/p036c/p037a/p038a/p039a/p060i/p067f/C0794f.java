package p036c.p037a.p038a.p039a.p060i.p067f;

import java.io.IOException;
import java.io.OutputStream;
import p036c.p037a.p038a.p039a.p068j.C0811g;

/* renamed from: c.a.a.a.i.f.f */
public class C0794f extends OutputStream {

    /* renamed from: a */
    private final C0811g f2323a;

    /* renamed from: b */
    private final byte[] f2324b;

    /* renamed from: c */
    private int f2325c;

    /* renamed from: d */
    private boolean f2326d;

    /* renamed from: e */
    private boolean f2327e;

    public C0794f(int i, C0811g gVar) {
        this.f2325c = 0;
        this.f2326d = false;
        this.f2327e = false;
        this.f2324b = new byte[i];
        this.f2323a = gVar;
    }

    @Deprecated
    public C0794f(C0811g gVar) {
        this(2048, gVar);
    }

    /* renamed from: a */
    public void mo3075a() {
        if (!this.f2326d) {
            mo3077b();
            mo3078c();
            this.f2326d = true;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo3076a(byte[] bArr, int i, int i2) {
        this.f2323a.writeLine(Integer.toHexString(this.f2325c + i2));
        this.f2323a.write(this.f2324b, 0, this.f2325c);
        this.f2323a.write(bArr, i, i2);
        this.f2323a.writeLine("");
        this.f2325c = 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo3077b() {
        int i = this.f2325c;
        if (i > 0) {
            this.f2323a.writeLine(Integer.toHexString(i));
            this.f2323a.write(this.f2324b, 0, this.f2325c);
            this.f2323a.writeLine("");
            this.f2325c = 0;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo3078c() {
        this.f2323a.writeLine("0");
        this.f2323a.writeLine("");
    }

    public void close() {
        if (!this.f2327e) {
            this.f2327e = true;
            mo3075a();
            this.f2323a.flush();
        }
    }

    public void flush() {
        mo3077b();
        this.f2323a.flush();
    }

    public void write(int i) {
        if (!this.f2327e) {
            byte[] bArr = this.f2324b;
            int i2 = this.f2325c;
            bArr[i2] = (byte) i;
            this.f2325c = i2 + 1;
            if (this.f2325c == bArr.length) {
                mo3077b();
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
        if (!this.f2327e) {
            byte[] bArr2 = this.f2324b;
            int length = bArr2.length;
            int i3 = this.f2325c;
            if (i2 >= length - i3) {
                mo3076a(bArr, i, i2);
                return;
            }
            System.arraycopy(bArr, i, bArr2, i3, i2);
            this.f2325c += i2;
            return;
        }
        throw new IOException("Attempted write to closed stream.");
    }
}

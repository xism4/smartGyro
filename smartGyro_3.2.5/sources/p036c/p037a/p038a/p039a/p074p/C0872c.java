package p036c.p037a.p038a.p039a.p074p;

import java.io.Serializable;

/* renamed from: c.a.a.a.p.c */
public final class C0872c implements Serializable {

    /* renamed from: a */
    private byte[] f2435a;

    /* renamed from: b */
    private int f2436b;

    public C0872c(int i) {
        C0870a.m3039a(i, "Buffer capacity");
        this.f2435a = new byte[i];
    }

    /* renamed from: c */
    private void m3051c(int i) {
        byte[] bArr = new byte[Math.max(this.f2435a.length << 1, i)];
        System.arraycopy(this.f2435a, 0, bArr, 0, this.f2436b);
        this.f2435a = bArr;
    }

    /* renamed from: a */
    public void mo3281a(int i) {
        int i2 = this.f2436b + 1;
        if (i2 > this.f2435a.length) {
            m3051c(i2);
        }
        this.f2435a[this.f2436b] = (byte) i;
        this.f2436b = i2;
    }

    /* renamed from: a */
    public void mo3282a(C0873d dVar, int i, int i2) {
        if (dVar != null) {
            mo3284a(dVar.mo3301a(), i, i2);
        }
    }

    /* renamed from: a */
    public void mo3283a(byte[] bArr, int i, int i2) {
        int i3;
        if (bArr != null) {
            if (i < 0 || i > bArr.length || i2 < 0 || (i3 = i + i2) < 0 || i3 > bArr.length) {
                throw new IndexOutOfBoundsException("off: " + i + " len: " + i2 + " b.length: " + bArr.length);
            } else if (i2 != 0) {
                int i4 = this.f2436b + i2;
                if (i4 > this.f2435a.length) {
                    m3051c(i4);
                }
                System.arraycopy(bArr, i, this.f2435a, this.f2436b, i2);
                this.f2436b = i4;
            }
        }
    }

    /* renamed from: a */
    public void mo3284a(char[] cArr, int i, int i2) {
        int i3;
        if (cArr != null) {
            if (i < 0 || i > cArr.length || i2 < 0 || (i3 = i + i2) < 0 || i3 > cArr.length) {
                throw new IndexOutOfBoundsException("off: " + i + " len: " + i2 + " b.length: " + cArr.length);
            } else if (i2 != 0) {
                int i4 = this.f2436b;
                int i5 = i2 + i4;
                if (i5 > this.f2435a.length) {
                    m3051c(i5);
                }
                while (i4 < i5) {
                    this.f2435a[i4] = (byte) cArr[i];
                    i++;
                    i4++;
                }
                this.f2436b = i5;
            }
        }
    }

    /* renamed from: a */
    public byte[] mo3285a() {
        return this.f2435a;
    }

    /* renamed from: b */
    public int mo3286b() {
        return this.f2435a.length;
    }

    /* renamed from: b */
    public int mo3287b(int i) {
        return this.f2435a[i];
    }

    /* renamed from: c */
    public void mo3288c() {
        this.f2436b = 0;
    }

    /* renamed from: d */
    public boolean mo3289d() {
        return this.f2436b == 0;
    }

    /* renamed from: e */
    public boolean mo3290e() {
        return this.f2436b == this.f2435a.length;
    }

    /* renamed from: f */
    public int mo3291f() {
        return this.f2436b;
    }

    /* renamed from: g */
    public byte[] mo3292g() {
        int i = this.f2436b;
        byte[] bArr = new byte[i];
        if (i > 0) {
            System.arraycopy(this.f2435a, 0, bArr, 0, i);
        }
        return bArr;
    }
}

package p000a.p001a.p005c.p013f;

/* renamed from: a.a.c.f.d */
public class C0080d<E> implements Cloneable {

    /* renamed from: a */
    private static final Object f207a = new Object();

    /* renamed from: b */
    private boolean f208b;

    /* renamed from: c */
    private long[] f209c;

    /* renamed from: d */
    private Object[] f210d;

    /* renamed from: e */
    private int f211e;

    public C0080d() {
        this(10);
    }

    public C0080d(int i) {
        this.f208b = false;
        if (i == 0) {
            this.f209c = C0079c.f205b;
            this.f210d = C0079c.f206c;
        } else {
            int c = C0079c.m275c(i);
            this.f209c = new long[c];
            this.f210d = new Object[c];
        }
        this.f211e = 0;
    }

    /* renamed from: b */
    private void m276b() {
        int i = this.f211e;
        long[] jArr = this.f209c;
        Object[] objArr = this.f210d;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f207a) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f208b = false;
        this.f211e = i2;
    }

    /* renamed from: a */
    public int mo282a() {
        if (this.f208b) {
            m276b();
        }
        return this.f211e;
    }

    /* renamed from: a */
    public long mo283a(int i) {
        if (this.f208b) {
            m276b();
        }
        return this.f209c[i];
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000a, code lost:
        r4 = r2.f210d;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo284a(long r3) {
        /*
            r2 = this;
            long[] r0 = r2.f209c
            int r1 = r2.f211e
            int r3 = p000a.p001a.p005c.p013f.C0079c.m272a((long[]) r0, (int) r1, (long) r3)
            if (r3 < 0) goto L_0x0017
            java.lang.Object[] r4 = r2.f210d
            r0 = r4[r3]
            java.lang.Object r1 = f207a
            if (r0 == r1) goto L_0x0017
            r4[r3] = r1
            r3 = 1
            r2.f208b = r3
        L_0x0017:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000a.p001a.p005c.p013f.C0080d.mo284a(long):void");
    }

    /* renamed from: a */
    public void mo285a(long j, E e) {
        int i = this.f211e;
        if (i == 0 || j > this.f209c[i - 1]) {
            if (this.f208b && this.f211e >= this.f209c.length) {
                m276b();
            }
            int i2 = this.f211e;
            if (i2 >= this.f209c.length) {
                int c = C0079c.m275c(i2 + 1);
                long[] jArr = new long[c];
                Object[] objArr = new Object[c];
                long[] jArr2 = this.f209c;
                System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
                Object[] objArr2 = this.f210d;
                System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
                this.f209c = jArr;
                this.f210d = objArr;
            }
            this.f209c[i2] = j;
            this.f210d[i2] = e;
            this.f211e = i2 + 1;
            return;
        }
        mo289c(j, e);
    }

    /* renamed from: b */
    public E mo286b(int i) {
        if (this.f208b) {
            m276b();
        }
        return this.f210d[i];
    }

    /* renamed from: b */
    public E mo287b(long j) {
        return mo288b(j, (Object) null);
    }

    /* renamed from: b */
    public E mo288b(long j, E e) {
        int a = C0079c.m272a(this.f209c, this.f211e, j);
        if (a >= 0) {
            E[] eArr = this.f210d;
            if (eArr[a] != f207a) {
                return eArr[a];
            }
        }
        return e;
    }

    /* renamed from: c */
    public void mo289c(long j, E e) {
        int a = C0079c.m272a(this.f209c, this.f211e, j);
        if (a >= 0) {
            this.f210d[a] = e;
            return;
        }
        int i = a ^ -1;
        if (i < this.f211e) {
            Object[] objArr = this.f210d;
            if (objArr[i] == f207a) {
                this.f209c[i] = j;
                objArr[i] = e;
                return;
            }
        }
        if (this.f208b && this.f211e >= this.f209c.length) {
            m276b();
            i = C0079c.m272a(this.f209c, this.f211e, j) ^ -1;
        }
        int i2 = this.f211e;
        if (i2 >= this.f209c.length) {
            int c = C0079c.m275c(i2 + 1);
            long[] jArr = new long[c];
            Object[] objArr2 = new Object[c];
            long[] jArr2 = this.f209c;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.f210d;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f209c = jArr;
            this.f210d = objArr2;
        }
        int i3 = this.f211e;
        if (i3 - i != 0) {
            long[] jArr3 = this.f209c;
            int i4 = i + 1;
            System.arraycopy(jArr3, i, jArr3, i4, i3 - i);
            Object[] objArr4 = this.f210d;
            System.arraycopy(objArr4, i, objArr4, i4, this.f211e - i);
        }
        this.f209c[i] = j;
        this.f210d[i] = e;
        this.f211e++;
    }

    public C0080d<E> clone() {
        try {
            C0080d<E> dVar = (C0080d) super.clone();
            dVar.f209c = (long[]) this.f209c.clone();
            dVar.f210d = (Object[]) this.f210d.clone();
            return dVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public String toString() {
        if (mo282a() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f211e * 28);
        sb.append('{');
        for (int i = 0; i < this.f211e; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(mo283a(i));
            sb.append('=');
            Object b = mo286b(i);
            if (b != this) {
                sb.append(b);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}

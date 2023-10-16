package p000a.p001a.p005c.p013f;

/* renamed from: a.a.c.f.j */
public class C0091j<E> implements Cloneable {

    /* renamed from: a */
    private static final Object f242a = new Object();

    /* renamed from: b */
    private boolean f243b;

    /* renamed from: c */
    private int[] f244c;

    /* renamed from: d */
    private Object[] f245d;

    /* renamed from: e */
    private int f246e;

    public C0091j() {
        this(10);
    }

    public C0091j(int i) {
        this.f243b = false;
        if (i == 0) {
            this.f244c = C0079c.f204a;
            this.f245d = C0079c.f206c;
        } else {
            int b = C0079c.m274b(i);
            this.f244c = new int[b];
            this.f245d = new Object[b];
        }
        this.f246e = 0;
    }

    /* renamed from: b */
    private void m326b() {
        int i = this.f246e;
        int[] iArr = this.f244c;
        Object[] objArr = this.f245d;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f242a) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f243b = false;
        this.f246e = i2;
    }

    /* renamed from: a */
    public int mo380a() {
        if (this.f243b) {
            m326b();
        }
        return this.f246e;
    }

    /* renamed from: a */
    public E mo381a(int i) {
        return mo384b(i, (Object) null);
    }

    /* renamed from: a */
    public void mo382a(int i, E e) {
        int i2 = this.f246e;
        if (i2 == 0 || i > this.f244c[i2 - 1]) {
            if (this.f243b && this.f246e >= this.f244c.length) {
                m326b();
            }
            int i3 = this.f246e;
            if (i3 >= this.f244c.length) {
                int b = C0079c.m274b(i3 + 1);
                int[] iArr = new int[b];
                Object[] objArr = new Object[b];
                int[] iArr2 = this.f244c;
                System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
                Object[] objArr2 = this.f245d;
                System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
                this.f244c = iArr;
                this.f245d = objArr;
            }
            this.f244c[i3] = i;
            this.f245d[i3] = e;
            this.f246e = i3 + 1;
            return;
        }
        mo386c(i, e);
    }

    /* renamed from: b */
    public int mo383b(int i) {
        if (this.f243b) {
            m326b();
        }
        return this.f244c[i];
    }

    /* renamed from: b */
    public E mo384b(int i, E e) {
        int a = C0079c.m271a(this.f244c, this.f246e, i);
        if (a >= 0) {
            E[] eArr = this.f245d;
            if (eArr[a] != f242a) {
                return eArr[a];
            }
        }
        return e;
    }

    /* renamed from: c */
    public E mo385c(int i) {
        if (this.f243b) {
            m326b();
        }
        return this.f245d[i];
    }

    /* renamed from: c */
    public void mo386c(int i, E e) {
        int a = C0079c.m271a(this.f244c, this.f246e, i);
        if (a >= 0) {
            this.f245d[a] = e;
            return;
        }
        int i2 = a ^ -1;
        if (i2 < this.f246e) {
            Object[] objArr = this.f245d;
            if (objArr[i2] == f242a) {
                this.f244c[i2] = i;
                objArr[i2] = e;
                return;
            }
        }
        if (this.f243b && this.f246e >= this.f244c.length) {
            m326b();
            i2 = C0079c.m271a(this.f244c, this.f246e, i) ^ -1;
        }
        int i3 = this.f246e;
        if (i3 >= this.f244c.length) {
            int b = C0079c.m274b(i3 + 1);
            int[] iArr = new int[b];
            Object[] objArr2 = new Object[b];
            int[] iArr2 = this.f244c;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.f245d;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f244c = iArr;
            this.f245d = objArr2;
        }
        int i4 = this.f246e;
        if (i4 - i2 != 0) {
            int[] iArr3 = this.f244c;
            int i5 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i5, i4 - i2);
            Object[] objArr4 = this.f245d;
            System.arraycopy(objArr4, i2, objArr4, i5, this.f246e - i2);
        }
        this.f244c[i2] = i;
        this.f245d[i2] = e;
        this.f246e++;
    }

    public C0091j<E> clone() {
        try {
            C0091j<E> jVar = (C0091j) super.clone();
            jVar.f244c = (int[]) this.f244c.clone();
            jVar.f245d = (Object[]) this.f245d.clone();
            return jVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public String toString() {
        if (mo380a() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f246e * 28);
        sb.append('{');
        for (int i = 0; i < this.f246e; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(mo383b(i));
            sb.append('=');
            Object c = mo385c(i);
            if (c != this) {
                sb.append(c);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}

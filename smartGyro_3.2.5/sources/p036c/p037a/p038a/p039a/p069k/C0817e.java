package p036c.p037a.p038a.p039a.p069k;

import java.util.NoSuchElementException;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0664h;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.k.e */
public class C0817e implements C0664h {

    /* renamed from: a */
    protected final C0576e[] f2356a;

    /* renamed from: b */
    protected int f2357b = mo3138b(-1);

    /* renamed from: c */
    protected String f2358c;

    public C0817e(C0576e[] eVarArr, String str) {
        C0870a.m3042a(eVarArr, "Header array");
        this.f2356a = eVarArr;
        this.f2358c = str;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo3137a(int i) {
        String str = this.f2358c;
        return str == null || str.equalsIgnoreCase(this.f2356a[i].getName());
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public int mo3138b(int i) {
        if (i < -1) {
            return -1;
        }
        int length = this.f2356a.length - 1;
        boolean z = false;
        while (!z && i < length) {
            i++;
            z = mo3137a(i);
        }
        if (z) {
            return i;
        }
        return -1;
    }

    public boolean hasNext() {
        return this.f2357b >= 0;
    }

    public final Object next() {
        return nextHeader();
    }

    public C0576e nextHeader() {
        int i = this.f2357b;
        if (i >= 0) {
            this.f2357b = mo3138b(i);
            return this.f2356a[i];
        }
        throw new NoSuchElementException("Iteration already finished.");
    }

    public void remove() {
        throw new UnsupportedOperationException("Removing headers is not supported.");
    }
}

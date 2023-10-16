package p036c.p037a.p038a.p039a;

import java.io.Serializable;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.D */
public class C0488D implements Serializable, Cloneable {

    /* renamed from: a */
    protected final String f1741a;

    /* renamed from: b */
    protected final int f1742b;

    /* renamed from: c */
    protected final int f1743c;

    public C0488D(String str, int i, int i2) {
        C0870a.m3042a(str, "Protocol name");
        this.f1741a = str;
        C0870a.m3039a(i, "Protocol minor version");
        this.f1742b = i;
        C0870a.m3039a(i2, "Protocol minor version");
        this.f1743c = i2;
    }

    /* renamed from: a */
    public final int mo2439a() {
        return this.f1742b;
    }

    /* renamed from: a */
    public int mo2440a(C0488D d) {
        C0870a.m3042a(d, "Protocol version");
        C0870a.m3045a(this.f1741a.equals(d.f1741a), "Versions for different protocols cannot be compared: %s %s", this, d);
        int a = mo2439a() - d.mo2439a();
        return a == 0 ? mo2442b() - d.mo2442b() : a;
    }

    /* renamed from: a */
    public C0488D mo2441a(int i, int i2) {
        return (i == this.f1742b && i2 == this.f1743c) ? this : new C0488D(this.f1741a, i, i2);
    }

    /* renamed from: b */
    public final int mo2442b() {
        return this.f1743c;
    }

    /* renamed from: b */
    public boolean mo2443b(C0488D d) {
        return d != null && this.f1741a.equals(d.f1741a);
    }

    /* renamed from: c */
    public final String mo2444c() {
        return this.f1741a;
    }

    /* renamed from: c */
    public final boolean mo2445c(C0488D d) {
        return mo2443b(d) && mo2440a(d) <= 0;
    }

    public Object clone() {
        return super.clone();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0488D)) {
            return false;
        }
        C0488D d = (C0488D) obj;
        return this.f1741a.equals(d.f1741a) && this.f1742b == d.f1742b && this.f1743c == d.f1743c;
    }

    public final int hashCode() {
        return (this.f1741a.hashCode() ^ (this.f1742b * 100000)) ^ this.f1743c;
    }

    public String toString() {
        return this.f1741a + '/' + Integer.toString(this.f1742b) + '.' + Integer.toString(this.f1743c);
    }
}

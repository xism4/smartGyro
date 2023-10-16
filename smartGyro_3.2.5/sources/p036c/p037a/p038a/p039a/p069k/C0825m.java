package p036c.p037a.p038a.p039a.p069k;

import java.io.Serializable;
import p036c.p037a.p038a.p039a.C0889z;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0877h;

/* renamed from: c.a.a.a.k.m */
public class C0825m implements C0889z, Cloneable, Serializable {

    /* renamed from: a */
    private final String f2385a;

    /* renamed from: b */
    private final String f2386b;

    public C0825m(String str, String str2) {
        C0870a.m3042a(str, "Name");
        this.f2385a = str;
        this.f2386b = str2;
    }

    public Object clone() {
        return super.clone();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0889z)) {
            return false;
        }
        C0825m mVar = (C0825m) obj;
        return this.f2385a.equals(mVar.f2385a) && C0877h.m3085a((Object) this.f2386b, (Object) mVar.f2386b);
    }

    public String getName() {
        return this.f2385a;
    }

    public String getValue() {
        return this.f2386b;
    }

    public int hashCode() {
        return C0877h.m3083a(C0877h.m3083a(17, (Object) this.f2385a), (Object) this.f2386b);
    }

    public String toString() {
        if (this.f2386b == null) {
            return this.f2385a;
        }
        StringBuilder sb = new StringBuilder(this.f2385a.length() + 1 + this.f2386b.length());
        sb.append(this.f2385a);
        sb.append("=");
        sb.append(this.f2386b);
        return sb.toString();
    }
}

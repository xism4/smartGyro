package p036c.p037a.p038a.p039a.p040a;

import java.io.Serializable;
import java.security.Principal;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0877h;

/* renamed from: c.a.a.a.a.k */
public final class C0505k implements Principal, Serializable {

    /* renamed from: a */
    private final String f1769a;

    public C0505k(String str) {
        C0870a.m3042a(str, "User name");
        this.f1769a = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof C0505k) && C0877h.m3085a((Object) this.f1769a, (Object) ((C0505k) obj).f1769a);
    }

    public String getName() {
        return this.f1769a;
    }

    public int hashCode() {
        return C0877h.m3083a(17, (Object) this.f1769a);
    }

    public String toString() {
        return "[principal: " + this.f1769a + "]";
    }
}

package p036c.p037a.p038a.p039a.p040a;

import java.io.Serializable;
import java.security.Principal;
import p036c.p037a.p038a.p039a.p074p.C0877h;

/* renamed from: c.a.a.a.a.q */
public class C0511q implements C0508n, Serializable {

    /* renamed from: a */
    private final C0512r f1773a;

    /* renamed from: b */
    private final String f1774b;

    /* renamed from: c */
    private final String f1775c;

    /* renamed from: a */
    public String mo2495a() {
        this.f1773a.mo2500a();
        throw null;
    }

    /* renamed from: b */
    public String mo2496b() {
        this.f1773a.mo2501b();
        throw null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0511q)) {
            return false;
        }
        C0511q qVar = (C0511q) obj;
        return C0877h.m3085a((Object) this.f1773a, (Object) qVar.f1773a) && C0877h.m3085a((Object) this.f1775c, (Object) qVar.f1775c);
    }

    public String getPassword() {
        return this.f1774b;
    }

    public Principal getUserPrincipal() {
        return this.f1773a;
    }

    public int hashCode() {
        return C0877h.m3083a(C0877h.m3083a(17, (Object) this.f1773a), (Object) this.f1775c);
    }

    public String toString() {
        return "[principal: " + this.f1773a + "][workstation: " + this.f1775c + "]";
    }
}

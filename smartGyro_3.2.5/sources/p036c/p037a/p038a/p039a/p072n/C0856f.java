package p036c.p037a.p038a.p039a.p072n;

import p036c.p037a.p038a.p039a.C0804j;
import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.n.f */
public class C0856f implements C0855e {

    /* renamed from: a */
    private final C0855e f2423a;

    public C0856f() {
        this.f2423a = new C0851a();
    }

    public C0856f(C0855e eVar) {
        this.f2423a = eVar;
    }

    /* renamed from: a */
    public static C0856f m3013a(C0855e eVar) {
        C0870a.m3042a(eVar, "HTTP context");
        return eVar instanceof C0856f ? (C0856f) eVar : new C0856f(eVar);
    }

    /* renamed from: a */
    public C0804j mo3259a() {
        return (C0804j) mo3260a("http.connection", C0804j.class);
    }

    /* renamed from: a */
    public <T> T mo3260a(String str, Class<T> cls) {
        C0870a.m3042a(cls, "Attribute class");
        Object attribute = getAttribute(str);
        if (attribute == null) {
            return null;
        }
        return cls.cast(attribute);
    }

    /* renamed from: b */
    public C0881r mo3261b() {
        return (C0881r) mo3260a("http.request", C0881r.class);
    }

    /* renamed from: c */
    public C0867o mo3262c() {
        return (C0867o) mo3260a("http.target_host", C0867o.class);
    }

    /* renamed from: d */
    public boolean mo3263d() {
        Boolean bool = (Boolean) mo3260a("http.request_sent", Boolean.class);
        return bool != null && bool.booleanValue();
    }

    public Object getAttribute(String str) {
        return this.f2423a.getAttribute(str);
    }

    public void setAttribute(String str, Object obj) {
        this.f2423a.setAttribute(str, obj);
    }
}

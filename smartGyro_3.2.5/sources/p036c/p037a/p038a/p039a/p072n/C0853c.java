package p036c.p037a.p038a.p039a.p072n;

import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.n.c */
public final class C0853c implements C0855e {

    /* renamed from: a */
    private final C0855e f2419a;

    /* renamed from: b */
    private final C0855e f2420b;

    public C0853c(C0855e eVar, C0855e eVar2) {
        C0870a.m3042a(eVar, "HTTP context");
        this.f2419a = eVar;
        this.f2420b = eVar2;
    }

    public Object getAttribute(String str) {
        Object attribute = this.f2419a.getAttribute(str);
        return attribute == null ? this.f2420b.getAttribute(str) : attribute;
    }

    public void setAttribute(String str, Object obj) {
        this.f2419a.setAttribute(str, obj);
    }

    public String toString() {
        return "[local: " + this.f2419a + "defaults: " + this.f2420b + "]";
    }
}

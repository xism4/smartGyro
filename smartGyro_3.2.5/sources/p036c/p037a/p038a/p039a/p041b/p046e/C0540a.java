package p036c.p037a.p038a.p039a.p041b.p046e;

import p036c.p037a.p038a.p039a.p040a.C0499e;
import p036c.p037a.p038a.p039a.p040a.C0503i;
import p036c.p037a.p038a.p039a.p041b.C0515a;
import p036c.p037a.p038a.p039a.p041b.C0559h;
import p036c.p037a.p038a.p039a.p041b.C0560i;
import p036c.p037a.p038a.p039a.p041b.p042a.C0516a;
import p036c.p037a.p038a.p039a.p049d.C0573a;
import p036c.p037a.p038a.p039a.p050e.p052b.C0587b;
import p036c.p037a.p038a.p039a.p050e.p052b.C0590e;
import p036c.p037a.p038a.p039a.p057f.C0645f;
import p036c.p037a.p038a.p039a.p057f.C0648i;
import p036c.p037a.p038a.p039a.p057f.C0650k;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p072n.C0856f;

/* renamed from: c.a.a.a.b.e.a */
public class C0540a extends C0856f {
    public C0540a() {
    }

    public C0540a(C0855e eVar) {
        super(eVar);
    }

    /* renamed from: a */
    public static C0540a m2199a(C0855e eVar) {
        return eVar instanceof C0540a ? (C0540a) eVar : new C0540a(eVar);
    }

    /* renamed from: b */
    private <T> C0573a<T> m2200b(String str, Class<T> cls) {
        return (C0573a) mo3260a(str, C0573a.class);
    }

    /* renamed from: a */
    public void mo2564a(C0515a aVar) {
        setAttribute("http.auth.auth-cache", aVar);
    }

    /* renamed from: e */
    public C0515a mo2565e() {
        return (C0515a) mo3260a("http.auth.auth-cache", C0515a.class);
    }

    /* renamed from: f */
    public C0573a<C0499e> mo2566f() {
        return m2200b("http.authscheme-registry", C0499e.class);
    }

    /* renamed from: g */
    public C0645f mo2567g() {
        return (C0645f) mo3260a("http.cookie-origin", C0645f.class);
    }

    /* renamed from: h */
    public C0648i mo2568h() {
        return (C0648i) mo3260a("http.cookie-spec", C0648i.class);
    }

    /* renamed from: i */
    public C0573a<C0650k> mo2569i() {
        return m2200b("http.cookiespec-registry", C0650k.class);
    }

    /* renamed from: j */
    public C0559h mo2570j() {
        return (C0559h) mo3260a("http.cookie-store", C0559h.class);
    }

    /* renamed from: k */
    public C0560i mo2571k() {
        return (C0560i) mo3260a("http.auth.credentials-provider", C0560i.class);
    }

    /* renamed from: l */
    public C0590e mo2572l() {
        return (C0590e) mo3260a("http.route", C0587b.class);
    }

    /* renamed from: m */
    public C0503i mo2573m() {
        return (C0503i) mo3260a("http.auth.proxy-scope", C0503i.class);
    }

    /* renamed from: n */
    public C0516a mo2574n() {
        C0516a aVar = (C0516a) mo3260a("http.request-config", C0516a.class);
        return aVar != null ? aVar : C0516a.f1778a;
    }

    /* renamed from: o */
    public C0503i mo2575o() {
        return (C0503i) mo3260a("http.auth.target-scope", C0503i.class);
    }
}

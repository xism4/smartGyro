package p036c.p037a.p038a.p039a.p050e.p051a;

import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.e.a.c */
public final class C0580c implements C0578a {

    /* renamed from: a */
    private static final C0581d f1875a = new C0579b();

    /* renamed from: a */
    public static C0581d m2290a(C0844g gVar) {
        C0870a.m3042a(gVar, "HTTP parameters");
        C0581d dVar = (C0581d) gVar.getParameter("http.conn-manager.max-per-route");
        return dVar == null ? f1875a : dVar;
    }

    /* renamed from: a */
    public static void m2291a(C0844g gVar, int i) {
        C0870a.m3042a(gVar, "HTTP parameters");
        gVar.setIntParameter("http.conn-manager.max-total", i);
    }

    @Deprecated
    /* renamed from: a */
    public static void m2292a(C0844g gVar, long j) {
        C0870a.m3042a(gVar, "HTTP parameters");
        gVar.setLongParameter("http.conn-manager.timeout", j);
    }

    /* renamed from: a */
    public static void m2293a(C0844g gVar, C0581d dVar) {
        C0870a.m3042a(gVar, "HTTP parameters");
        gVar.setParameter("http.conn-manager.max-per-route", dVar);
    }

    /* renamed from: b */
    public static int m2294b(C0844g gVar) {
        C0870a.m3042a(gVar, "HTTP parameters");
        return gVar.getIntParameter("http.conn-manager.max-total", 20);
    }
}

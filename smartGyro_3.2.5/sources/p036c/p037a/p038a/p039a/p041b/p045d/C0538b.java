package p036c.p037a.p038a.p039a.p041b.p045d;

import p036c.p037a.p038a.p039a.p070l.C0842e;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.b.d.b */
public class C0538b {
    /* renamed from: a */
    public static long m2196a(C0844g gVar) {
        C0870a.m3042a(gVar, "HTTP parameters");
        Long l = (Long) gVar.getParameter("http.conn-manager.timeout");
        return l != null ? l.longValue() : (long) C0842e.m2977a(gVar);
    }

    /* renamed from: b */
    public static boolean m2197b(C0844g gVar) {
        C0870a.m3042a(gVar, "HTTP parameters");
        return gVar.getBooleanParameter("http.protocol.handle-authentication", true);
    }

    /* renamed from: c */
    public static boolean m2198c(C0844g gVar) {
        C0870a.m3042a(gVar, "HTTP parameters");
        return gVar.getBooleanParameter("http.protocol.handle-redirects", true);
    }
}

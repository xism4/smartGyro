package p036c.p037a.p038a.p039a.p050e.p051a;

import java.net.InetAddress;
import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.p050e.p052b.C0587b;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.e.a.g */
public class C0584g implements C0583f {

    /* renamed from: a */
    public static final C0867o f1878a = new C0867o("127.0.0.255", 0, "no-host");

    /* renamed from: b */
    public static final C0587b f1879b = new C0587b(f1878a);

    /* renamed from: a */
    public static C0867o m2298a(C0844g gVar) {
        C0870a.m3042a(gVar, "Parameters");
        C0867o oVar = (C0867o) gVar.getParameter("http.route.default-proxy");
        if (oVar == null || !f1878a.equals(oVar)) {
            return oVar;
        }
        return null;
    }

    /* renamed from: b */
    public static C0587b m2299b(C0844g gVar) {
        C0870a.m3042a(gVar, "Parameters");
        C0587b bVar = (C0587b) gVar.getParameter("http.route.forced-route");
        if (bVar == null || !f1879b.equals(bVar)) {
            return bVar;
        }
        return null;
    }

    /* renamed from: c */
    public static InetAddress m2300c(C0844g gVar) {
        C0870a.m3042a(gVar, "Parameters");
        return (InetAddress) gVar.getParameter("http.route.local-address");
    }
}

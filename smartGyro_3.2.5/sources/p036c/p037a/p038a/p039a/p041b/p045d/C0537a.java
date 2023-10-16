package p036c.p037a.p038a.p039a.p041b.p045d;

import java.net.InetAddress;
import java.util.Collection;
import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.p041b.p042a.C0516a;
import p036c.p037a.p038a.p039a.p070l.C0844g;

@Deprecated
/* renamed from: c.a.a.a.b.d.a */
public final class C0537a {
    /* renamed from: a */
    public static C0516a m2195a(C0844g gVar) {
        C0516a.C0517a a = C0516a.m2148a();
        a.mo2528d(gVar.getIntParameter("http.socket.timeout", 0));
        a.mo2531f(gVar.getBooleanParameter("http.connection.stalecheck", true));
        a.mo2516a(gVar.getIntParameter("http.connection.timeout", 0));
        a.mo2527c(gVar.getBooleanParameter("http.protocol.expect-continue", false));
        a.mo2517a((C0867o) gVar.getParameter("http.route.default-proxy"));
        a.mo2519a((InetAddress) gVar.getParameter("http.route.local-address"));
        a.mo2520a((Collection<String>) (Collection) gVar.getParameter("http.auth.proxy-scheme-pref"));
        a.mo2524b((Collection<String>) (Collection) gVar.getParameter("http.auth.target-scheme-pref"));
        a.mo2521a(gVar.getBooleanParameter("http.protocol.handle-authentication", true));
        a.mo2525b(gVar.getBooleanParameter("http.protocol.allow-circular-redirects", false));
        a.mo2523b((int) gVar.getLongParameter("http.conn-manager.timeout", 0));
        a.mo2518a((String) gVar.getParameter("http.protocol.cookie-policy"));
        a.mo2526c(gVar.getIntParameter("http.protocol.max-redirects", 50));
        a.mo2529d(gVar.getBooleanParameter("http.protocol.handle-redirects", true));
        a.mo2530e(!gVar.getBooleanParameter("http.protocol.reject-relative-redirect", false));
        return a.mo2522a();
    }
}

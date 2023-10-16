package p036c.p037a.p038a.p039a.p070l;

import p036c.p037a.p038a.p039a.C0488D;
import p036c.p037a.p038a.p039a.C0886w;
import p036c.p037a.p038a.p039a.p072n.C0854d;
import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.l.i */
public final class C0846i implements C0841d {
    /* renamed from: a */
    public static String m2988a(C0844g gVar) {
        C0870a.m3042a(gVar, "HTTP parameters");
        String str = (String) gVar.getParameter("http.protocol.element-charset");
        return str == null ? C0854d.f2422b.name() : str;
    }

    /* renamed from: a */
    public static void m2989a(C0844g gVar, C0488D d) {
        C0870a.m3042a(gVar, "HTTP parameters");
        gVar.setParameter("http.protocol.version", d);
    }

    /* renamed from: a */
    public static void m2990a(C0844g gVar, String str) {
        C0870a.m3042a(gVar, "HTTP parameters");
        gVar.setParameter("http.protocol.content-charset", str);
    }

    /* renamed from: b */
    public static C0488D m2991b(C0844g gVar) {
        C0870a.m3042a(gVar, "HTTP parameters");
        Object parameter = gVar.getParameter("http.protocol.version");
        return parameter == null ? C0886w.f2446f : (C0488D) parameter;
    }

    /* renamed from: b */
    public static void m2992b(C0844g gVar, String str) {
        C0870a.m3042a(gVar, "HTTP parameters");
        gVar.setParameter("http.useragent", str);
    }
}

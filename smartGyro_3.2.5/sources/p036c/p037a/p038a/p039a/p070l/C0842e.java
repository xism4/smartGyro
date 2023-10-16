package p036c.p037a.p038a.p039a.p070l;

import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.l.e */
public final class C0842e implements C0840c {
    /* renamed from: a */
    public static int m2977a(C0844g gVar) {
        C0870a.m3042a(gVar, "HTTP parameters");
        return gVar.getIntParameter("http.connection.timeout", 0);
    }

    /* renamed from: a */
    public static void m2978a(C0844g gVar, int i) {
        C0870a.m3042a(gVar, "HTTP parameters");
        gVar.setIntParameter("http.connection.timeout", i);
    }

    /* renamed from: a */
    public static void m2979a(C0844g gVar, boolean z) {
        C0870a.m3042a(gVar, "HTTP parameters");
        gVar.setBooleanParameter("http.tcp.nodelay", z);
    }

    /* renamed from: b */
    public static int m2980b(C0844g gVar) {
        C0870a.m3042a(gVar, "HTTP parameters");
        return gVar.getIntParameter("http.socket.linger", -1);
    }

    /* renamed from: b */
    public static void m2981b(C0844g gVar, int i) {
        C0870a.m3042a(gVar, "HTTP parameters");
        gVar.setIntParameter("http.socket.timeout", i);
    }

    /* renamed from: c */
    public static void m2982c(C0844g gVar, int i) {
        C0870a.m3042a(gVar, "HTTP parameters");
        gVar.setIntParameter("http.socket.buffer-size", i);
    }

    /* renamed from: c */
    public static boolean m2983c(C0844g gVar) {
        C0870a.m3042a(gVar, "HTTP parameters");
        return gVar.getBooleanParameter("http.socket.reuseaddr", false);
    }

    /* renamed from: d */
    public static int m2984d(C0844g gVar) {
        C0870a.m3042a(gVar, "HTTP parameters");
        return gVar.getIntParameter("http.socket.timeout", 0);
    }

    /* renamed from: e */
    public static boolean m2985e(C0844g gVar) {
        C0870a.m3042a(gVar, "HTTP parameters");
        return gVar.getBooleanParameter("http.tcp.nodelay", true);
    }

    /* renamed from: f */
    public static boolean m2986f(C0844g gVar) {
        C0870a.m3042a(gVar, "HTTP parameters");
        return gVar.getBooleanParameter("http.connection.stalecheck", true);
    }
}

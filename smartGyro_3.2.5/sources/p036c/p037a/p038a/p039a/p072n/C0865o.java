package p036c.p037a.p038a.p039a.p072n;

import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.C0882s;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.n.o */
public class C0865o implements C0882s {

    /* renamed from: a */
    private final String f2429a;

    public C0865o() {
        this((String) null);
    }

    public C0865o(String str) {
        this.f2429a = str;
    }

    /* renamed from: a */
    public void mo2352a(C0881r rVar, C0855e eVar) {
        C0870a.m3042a(rVar, "HTTP request");
        if (!rVar.containsHeader("User-Agent")) {
            String str = null;
            C0844g params = rVar.getParams();
            if (params != null) {
                str = (String) params.getParameter("http.useragent");
            }
            if (str == null) {
                str = this.f2429a;
            }
            if (str != null) {
                rVar.addHeader("User-Agent", str);
            }
        }
    }
}

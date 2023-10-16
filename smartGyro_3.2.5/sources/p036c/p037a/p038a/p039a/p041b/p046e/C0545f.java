package p036c.p037a.p038a.p039a.p041b.p046e;

import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.C0882s;
import p036c.p037a.p038a.p039a.p050e.p052b.C0590e;
import p036c.p037a.p038a.p039a.p059h.C0668b;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.b.e.f */
public class C0545f implements C0882s {

    /* renamed from: a */
    public C0668b f1835a = new C0668b(C0545f.class);

    /* renamed from: a */
    public void mo2352a(C0881r rVar, C0855e eVar) {
        C0870a.m3042a(rVar, "HTTP request");
        if (rVar.getRequestLine().getMethod().equalsIgnoreCase("CONNECT")) {
            rVar.setHeader("Proxy-Connection", "Keep-Alive");
            return;
        }
        C0590e l = C0540a.m2199a(eVar).mo2572l();
        if (l == null) {
            this.f1835a.mo2803a("Connection route not set in the context");
            return;
        }
        if ((l.getHopCount() == 1 || l.isTunnelled()) && !rVar.containsHeader("Connection")) {
            rVar.addHeader("Connection", "Keep-Alive");
        }
        if (l.getHopCount() == 2 && !l.isTunnelled() && !rVar.containsHeader("Proxy-Connection")) {
            rVar.addHeader("Proxy-Connection", "Keep-Alive");
        }
    }
}

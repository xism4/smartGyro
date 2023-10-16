package p036c.p037a.p038a.p039a.p041b.p046e;

import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.p040a.C0503i;
import p036c.p037a.p038a.p039a.p059h.C0668b;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.b.e.i */
public class C0548i extends C0544e {
    /* renamed from: a */
    public void mo2352a(C0881r rVar, C0855e eVar) {
        C0870a.m3042a(rVar, "HTTP request");
        C0870a.m3042a(eVar, "HTTP context");
        if (!rVar.getRequestLine().getMethod().equalsIgnoreCase("CONNECT") && !rVar.containsHeader("Authorization")) {
            C0503i iVar = (C0503i) eVar.getAttribute("http.auth.target-scope");
            if (iVar == null) {
                this.f1834a.mo2803a("Target auth state not set in the context");
                return;
            }
            if (this.f1834a.mo2805a()) {
                C0668b bVar = this.f1834a;
                bVar.mo2803a("Target auth state: " + iVar.mo2485d());
            }
            mo2576a(iVar, rVar, eVar);
        }
    }
}

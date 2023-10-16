package p036c.p037a.p038a.p039a.p041b.p046e;

import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.p040a.C0503i;
import p036c.p037a.p038a.p039a.p050e.C0633n;
import p036c.p037a.p038a.p039a.p059h.C0668b;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.b.e.h */
public class C0547h extends C0544e {
    /* renamed from: a */
    public void mo2352a(C0881r rVar, C0855e eVar) {
        C0668b bVar;
        String str;
        C0870a.m3042a(rVar, "HTTP request");
        C0870a.m3042a(eVar, "HTTP context");
        if (!rVar.containsHeader("Proxy-Authorization")) {
            C0633n nVar = (C0633n) eVar.getAttribute("http.connection");
            if (nVar == null) {
                bVar = this.f1834a;
                str = "HTTP connection not set in the context";
            } else if (!nVar.getRoute().isTunnelled()) {
                C0503i iVar = (C0503i) eVar.getAttribute("http.auth.proxy-scope");
                if (iVar == null) {
                    bVar = this.f1834a;
                    str = "Proxy auth state not set in the context";
                } else {
                    if (this.f1834a.mo2805a()) {
                        C0668b bVar2 = this.f1834a;
                        bVar2.mo2803a("Proxy auth state: " + iVar.mo2485d());
                    }
                    mo2576a(iVar, rVar, eVar);
                    return;
                }
            } else {
                return;
            }
            bVar.mo2803a(str);
        }
    }
}

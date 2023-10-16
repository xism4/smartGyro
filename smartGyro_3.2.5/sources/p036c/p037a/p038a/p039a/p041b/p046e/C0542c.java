package p036c.p037a.p038a.p039a.p041b.p046e;

import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.C0882s;
import p036c.p037a.p038a.p039a.p040a.C0496b;
import p036c.p037a.p038a.p039a.p040a.C0497c;
import p036c.p037a.p038a.p039a.p040a.C0502h;
import p036c.p037a.p038a.p039a.p040a.C0503i;
import p036c.p037a.p038a.p039a.p040a.C0508n;
import p036c.p037a.p038a.p039a.p041b.C0515a;
import p036c.p037a.p038a.p039a.p041b.C0560i;
import p036c.p037a.p038a.p039a.p050e.p052b.C0590e;
import p036c.p037a.p038a.p039a.p059h.C0668b;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.b.e.c */
public class C0542c implements C0882s {

    /* renamed from: a */
    public C0668b f1832a = new C0668b(C0542c.class);

    /* renamed from: a */
    private void m2214a(C0867o oVar, C0497c cVar, C0503i iVar, C0560i iVar2) {
        String schemeName = cVar.getSchemeName();
        if (this.f1832a.mo2805a()) {
            C0668b bVar = this.f1832a;
            bVar.mo2803a("Re-using cached '" + schemeName + "' auth scheme for " + oVar);
        }
        C0508n a = iVar2.mo2598a(new C0502h(oVar, C0502h.f1756b, schemeName));
        if (a != null) {
            iVar.mo2478a("BASIC".equalsIgnoreCase(cVar.getSchemeName()) ? C0496b.CHALLENGED : C0496b.SUCCESS);
            iVar.mo2480a(cVar, a);
            return;
        }
        this.f1832a.mo2803a("No credentials for preemptive authentication");
    }

    /* renamed from: a */
    public void mo2352a(C0881r rVar, C0855e eVar) {
        C0497c b;
        C0497c b2;
        C0668b bVar;
        String str;
        C0870a.m3042a(rVar, "HTTP request");
        C0870a.m3042a(eVar, "HTTP context");
        C0540a a = C0540a.m2199a(eVar);
        C0515a e = a.mo2565e();
        if (e == null) {
            bVar = this.f1832a;
            str = "Auth cache not set in the context";
        } else {
            C0560i k = a.mo2571k();
            if (k == null) {
                bVar = this.f1832a;
                str = "Credentials provider not set in the context";
            } else {
                C0590e l = a.mo2572l();
                if (l == null) {
                    bVar = this.f1832a;
                    str = "Route info not set in the context";
                } else {
                    C0867o c = a.mo3262c();
                    if (c == null) {
                        bVar = this.f1832a;
                        str = "Target host not set in the context";
                    } else {
                        if (c.mo3272c() < 0) {
                            c = new C0867o(c.mo3271b(), l.getTargetHost().mo3272c(), c.mo3274d());
                        }
                        C0503i o = a.mo2575o();
                        if (!(o == null || o.mo2485d() != C0496b.UNCHALLENGED || (b2 = e.mo2508b(c)) == null)) {
                            m2214a(c, b2, o, k);
                        }
                        C0867o proxyHost = l.getProxyHost();
                        C0503i m = a.mo2573m();
                        if (proxyHost != null && m != null && m.mo2485d() == C0496b.UNCHALLENGED && (b = e.mo2508b(proxyHost)) != null) {
                            m2214a(proxyHost, b, m, k);
                            return;
                        }
                        return;
                    }
                }
            }
        }
        bVar.mo2803a(str);
    }
}

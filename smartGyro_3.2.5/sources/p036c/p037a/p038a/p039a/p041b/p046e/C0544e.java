package p036c.p037a.p038a.p039a.p041b.p046e;

import java.util.Queue;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.C0882s;
import p036c.p037a.p038a.p039a.p040a.C0495a;
import p036c.p037a.p038a.p039a.p040a.C0497c;
import p036c.p037a.p038a.p039a.p040a.C0503i;
import p036c.p037a.p038a.p039a.p040a.C0504j;
import p036c.p037a.p038a.p039a.p040a.C0507m;
import p036c.p037a.p038a.p039a.p040a.C0508n;
import p036c.p037a.p038a.p039a.p059h.C0668b;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p074p.C0871b;

@Deprecated
/* renamed from: c.a.a.a.b.e.e */
abstract class C0544e implements C0882s {

    /* renamed from: a */
    final C0668b f1834a = new C0668b(getClass());

    /* renamed from: a */
    private C0576e m2216a(C0497c cVar, C0508n nVar, C0881r rVar, C0855e eVar) {
        C0871b.m3049a((Object) cVar, "Auth scheme");
        return cVar instanceof C0507m ? ((C0507m) cVar).mo2492a(nVar, rVar, eVar) : cVar.mo2462a(nVar, rVar);
    }

    /* renamed from: a */
    private void m2217a(C0497c cVar) {
        C0871b.m3049a((Object) cVar, "Auth scheme");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo2576a(C0503i iVar, C0881r rVar, C0855e eVar) {
        C0497c b = iVar.mo2483b();
        C0508n c = iVar.mo2484c();
        int i = C0543d.f1833a[iVar.mo2485d().ordinal()];
        if (i != 1) {
            if (i == 2) {
                m2217a(b);
                if (b.isConnectionBased()) {
                    return;
                }
            } else if (i == 3) {
                Queue<C0495a> a = iVar.mo2477a();
                if (a != null) {
                    while (!a.isEmpty()) {
                        C0495a remove = a.remove();
                        C0497c a2 = remove.mo2459a();
                        C0508n b2 = remove.mo2460b();
                        iVar.mo2480a(a2, b2);
                        if (this.f1834a.mo2805a()) {
                            C0668b bVar = this.f1834a;
                            bVar.mo2803a("Generating response to an authentication challenge using " + a2.getSchemeName() + " scheme");
                        }
                        try {
                            rVar.mo3115a(m2216a(a2, b2, rVar, eVar));
                            return;
                        } catch (C0504j e) {
                            if (this.f1834a.mo2812d()) {
                                C0668b bVar2 = this.f1834a;
                                bVar2.mo2811d(a2 + " authentication error: " + e.getMessage());
                            }
                        }
                    }
                    return;
                }
                m2217a(b);
            }
            if (b != null) {
                try {
                    rVar.mo3115a(m2216a(b, c, rVar, eVar));
                } catch (C0504j e2) {
                    if (this.f1834a.mo2808b()) {
                        C0668b bVar3 = this.f1834a;
                        bVar3.mo2806b(b + " authentication error: " + e2.getMessage());
                    }
                }
            }
        }
    }
}

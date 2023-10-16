package p026b.p033c.p034a.p035a;

import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.C0882s;
import p036c.p037a.p038a.p039a.p040a.C0497c;
import p036c.p037a.p038a.p039a.p040a.C0502h;
import p036c.p037a.p038a.p039a.p040a.C0503i;
import p036c.p037a.p038a.p039a.p040a.C0508n;
import p036c.p037a.p038a.p039a.p041b.C0560i;
import p036c.p037a.p038a.p039a.p060i.p061a.C0672b;
import p036c.p037a.p038a.p039a.p072n.C0855e;

/* renamed from: b.c.a.a.c */
class C0464c implements C0882s {

    /* renamed from: a */
    final /* synthetic */ C0465d f1689a;

    C0464c(C0465d dVar) {
        this.f1689a = dVar;
    }

    /* renamed from: a */
    public void mo2352a(C0881r rVar, C0855e eVar) {
        C0508n a;
        C0503i iVar = (C0503i) eVar.getAttribute("http.auth.target-scope");
        C0560i iVar2 = (C0560i) eVar.getAttribute("http.auth.credentials-provider");
        C0867o oVar = (C0867o) eVar.getAttribute("http.target_host");
        if (iVar.mo2483b() == null && (a = iVar2.mo2598a(new C0502h(oVar.mo3271b(), oVar.mo3272c()))) != null) {
            iVar.mo2479a((C0497c) new C0672b());
            iVar.mo2481a(a);
        }
    }
}

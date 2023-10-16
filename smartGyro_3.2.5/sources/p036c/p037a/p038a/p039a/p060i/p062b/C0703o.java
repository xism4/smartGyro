package p036c.p037a.p038a.p039a.p060i.p062b;

import java.net.URI;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.C0883t;
import p036c.p037a.p038a.p039a.p041b.C0566o;
import p036c.p037a.p038a.p039a.p041b.C0567p;
import p036c.p037a.p038a.p039a.p041b.p044c.C0529i;
import p036c.p037a.p038a.p039a.p041b.p044c.C0530j;
import p036c.p037a.p038a.p039a.p041b.p044c.C0532l;
import p036c.p037a.p038a.p039a.p072n.C0855e;

@Deprecated
/* renamed from: c.a.a.a.i.b.o */
class C0703o implements C0567p {

    /* renamed from: a */
    private final C0566o f2087a;

    public C0703o(C0566o oVar) {
        this.f2087a = oVar;
    }

    /* renamed from: a */
    public C0532l mo2599a(C0881r rVar, C0883t tVar, C0855e eVar) {
        URI a = this.f2087a.mo2423a(tVar, eVar);
        return rVar.getRequestLine().getMethod().equalsIgnoreCase("HEAD") ? new C0530j(a) : new C0529i(a);
    }

    /* renamed from: a */
    public C0566o mo2903a() {
        return this.f2087a;
    }

    /* renamed from: b */
    public boolean mo2600b(C0881r rVar, C0883t tVar, C0855e eVar) {
        return this.f2087a.mo2424b(tVar, eVar);
    }
}

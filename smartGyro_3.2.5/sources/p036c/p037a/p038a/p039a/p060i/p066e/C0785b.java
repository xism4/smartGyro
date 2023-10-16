package p036c.p037a.p038a.p039a.p060i.p066e;

import java.io.OutputStream;
import p036c.p037a.p038a.p039a.C0837l;
import p036c.p037a.p038a.p039a.C0880q;
import p036c.p037a.p038a.p039a.p058g.C0660d;
import p036c.p037a.p038a.p039a.p060i.p067f.C0794f;
import p036c.p037a.p038a.p039a.p060i.p067f.C0796h;
import p036c.p037a.p038a.p039a.p060i.p067f.C0801m;
import p036c.p037a.p038a.p039a.p068j.C0811g;
import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.i.e.b */
public class C0785b {

    /* renamed from: a */
    private final C0660d f2273a;

    public C0785b(C0660d dVar) {
        C0870a.m3042a(dVar, "Content length strategy");
        this.f2273a = dVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public OutputStream mo3053a(C0811g gVar, C0880q qVar) {
        long a = this.f2273a.mo2795a(qVar);
        return a == -2 ? new C0794f(gVar) : a == -1 ? new C0801m(gVar) : new C0796h(gVar, a);
    }

    /* renamed from: a */
    public void mo3054a(C0811g gVar, C0880q qVar, C0837l lVar) {
        C0870a.m3042a(gVar, "Session output buffer");
        C0870a.m3042a(qVar, "HTTP message");
        C0870a.m3042a(lVar, "HTTP entity");
        OutputStream a = mo3053a(gVar, qVar);
        lVar.writeTo(a);
        a.close();
    }
}

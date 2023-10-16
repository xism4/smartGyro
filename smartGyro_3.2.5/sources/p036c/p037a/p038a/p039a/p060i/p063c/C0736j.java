package p036c.p037a.p038a.p039a.p060i.p063c;

import java.net.InetAddress;
import p036c.p037a.p038a.p039a.C0850n;
import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.p050e.p051a.C0584g;
import p036c.p037a.p038a.p039a.p050e.p052b.C0587b;
import p036c.p037a.p038a.p039a.p050e.p052b.C0589d;
import p036c.p037a.p038a.p039a.p050e.p053c.C0603i;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0871b;

@Deprecated
/* renamed from: c.a.a.a.i.c.j */
public class C0736j implements C0589d {

    /* renamed from: a */
    protected final C0603i f2212a;

    public C0736j(C0603i iVar) {
        C0870a.m3042a(iVar, "Scheme registry");
        this.f2212a = iVar;
    }

    /* renamed from: a */
    public C0587b mo2645a(C0867o oVar, C0881r rVar, C0855e eVar) {
        C0870a.m3042a(rVar, "HTTP request");
        C0587b b = C0584g.m2299b(rVar.getParams());
        if (b != null) {
            return b;
        }
        C0871b.m3049a((Object) oVar, "Target host");
        InetAddress c = C0584g.m2300c(rVar.getParams());
        C0867o a = C0584g.m2298a(rVar.getParams());
        try {
            boolean d = this.f2212a.mo2676b(oVar.mo3274d()).mo2668d();
            return a == null ? new C0587b(oVar, c, d) : new C0587b(oVar, c, a, d);
        } catch (IllegalStateException e) {
            throw new C0850n(e.getMessage());
        }
    }
}

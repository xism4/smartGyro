package p036c.p037a.p038a.p039a.p060i.p061a;

import java.nio.charset.Charset;
import p036c.p037a.p038a.p039a.C0570c;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.p040a.C0508n;
import p036c.p037a.p038a.p039a.p059h.C0665a;
import p036c.p037a.p038a.p039a.p069k.C0829q;
import p036c.p037a.p038a.p039a.p072n.C0851a;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;
import p036c.p037a.p038a.p039a.p074p.C0875f;

/* renamed from: c.a.a.a.i.a.b */
public class C0672b extends C0686m {

    /* renamed from: d */
    private boolean f2003d;

    public C0672b() {
        this(C0570c.f1866b);
    }

    public C0672b(Charset charset) {
        super(charset);
        this.f2003d = false;
    }

    @Deprecated
    /* renamed from: a */
    public C0576e mo2462a(C0508n nVar, C0881r rVar) {
        return mo2492a(nVar, rVar, new C0851a());
    }

    /* renamed from: a */
    public C0576e mo2492a(C0508n nVar, C0881r rVar, C0855e eVar) {
        C0870a.m3042a(nVar, "Credentials");
        C0870a.m3042a(rVar, "HTTP request");
        StringBuilder sb = new StringBuilder();
        sb.append(nVar.getUserPrincipal().getName());
        sb.append(":");
        sb.append(nVar.getPassword() == null ? "null" : nVar.getPassword());
        byte[] a = C0665a.m2428a(C0875f.m3079a(sb.toString(), mo2839a(rVar)), 2);
        C0873d dVar = new C0873d(32);
        dVar.mo3298a(mo2831a() ? "Proxy-Authorization" : "Authorization");
        dVar.mo3298a(": Basic ");
        dVar.mo3299a(a, 0, a.length);
        return new C0829q(dVar);
    }

    /* renamed from: a */
    public void mo2463a(C0576e eVar) {
        super.mo2463a(eVar);
        this.f2003d = true;
    }

    public String getSchemeName() {
        return "basic";
    }

    public boolean isComplete() {
        return this.f2003d;
    }

    public boolean isConnectionBased() {
        return false;
    }

    public String toString() {
        return "BASIC [complete=" + this.f2003d + "]";
    }
}

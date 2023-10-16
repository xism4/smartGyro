package p036c.p037a.p038a.p039a.p041b.p044c;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;
import p036c.p037a.p038a.p039a.C0488D;
import p036c.p037a.p038a.p039a.C0570c;
import p036c.p037a.p038a.p039a.C0837l;
import p036c.p037a.p038a.p039a.C0848m;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.C0889z;
import p036c.p037a.p038a.p039a.p041b.p042a.C0516a;
import p036c.p037a.p038a.p039a.p041b.p043b.C0519a;
import p036c.p037a.p038a.p039a.p041b.p047f.C0555d;
import p036c.p037a.p038a.p039a.p041b.p047f.C0557f;
import p036c.p037a.p038a.p039a.p058g.C0661e;
import p036c.p037a.p038a.p039a.p069k.C0830r;
import p036c.p037a.p038a.p039a.p072n.C0854d;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.b.c.m */
public class C0533m {

    /* renamed from: a */
    private String f1821a;

    /* renamed from: b */
    private Charset f1822b;

    /* renamed from: c */
    private C0488D f1823c;

    /* renamed from: d */
    private URI f1824d;

    /* renamed from: e */
    private C0830r f1825e;

    /* renamed from: f */
    private C0837l f1826f;

    /* renamed from: g */
    private List<C0889z> f1827g;

    /* renamed from: h */
    private C0516a f1828h;

    /* renamed from: c.a.a.a.b.c.m$a */
    static class C0534a extends C0527g {

        /* renamed from: i */
        private final String f1829i;

        C0534a(String str) {
            this.f1829i = str;
        }

        public String getMethod() {
            return this.f1829i;
        }
    }

    /* renamed from: c.a.a.a.b.c.m$b */
    static class C0535b extends C0531k {

        /* renamed from: h */
        private final String f1830h;

        C0535b(String str) {
            this.f1830h = str;
        }

        public String getMethod() {
            return this.f1830h;
        }
    }

    C0533m() {
        this((String) null);
    }

    C0533m(String str) {
        this.f1822b = C0570c.f1865a;
        this.f1821a = str;
    }

    /* renamed from: a */
    public static C0533m m2189a(C0881r rVar) {
        C0870a.m3042a(rVar, "HTTP request");
        C0533m mVar = new C0533m();
        mVar.m2190b(rVar);
        return mVar;
    }

    /* renamed from: b */
    private C0533m m2190b(C0881r rVar) {
        if (rVar == null) {
            return this;
        }
        this.f1821a = rVar.getRequestLine().getMethod();
        this.f1823c = rVar.getRequestLine().getProtocolVersion();
        if (this.f1825e == null) {
            this.f1825e = new C0830r();
        }
        this.f1825e.mo3205a();
        this.f1825e.mo3207a(rVar.getAllHeaders());
        this.f1827g = null;
        this.f1826f = null;
        if (rVar instanceof C0848m) {
            C0837l entity = ((C0848m) rVar).getEntity();
            C0661e a = C0661e.m2422a(entity);
            if (a == null || !a.mo2797b().equals(C0661e.f1959b.mo2797b())) {
                this.f1826f = entity;
            } else {
                try {
                    List<C0889z> a2 = C0557f.m2263a(entity);
                    if (!a2.isEmpty()) {
                        this.f1827g = a2;
                    }
                } catch (IOException unused) {
                }
            }
        }
        URI uri = rVar instanceof C0532l ? ((C0532l) rVar).getURI() : URI.create(rVar.getRequestLine().getUri());
        C0555d dVar = new C0555d(uri);
        if (this.f1827g == null) {
            List<C0889z> e = dVar.mo2590e();
            if (!e.isEmpty()) {
                this.f1827g = e;
                dVar.mo2583b();
            } else {
                this.f1827g = null;
            }
        }
        try {
            this.f1824d = dVar.mo2582a();
        } catch (URISyntaxException unused2) {
            this.f1824d = uri;
        }
        if (rVar instanceof C0526f) {
            this.f1828h = ((C0526f) rVar).mo2547a();
        } else {
            this.f1828h = null;
        }
        return this;
    }

    /* renamed from: a */
    public C0532l mo2560a() {
        C0531k kVar;
        URI uri = this.f1824d;
        if (uri == null) {
            uri = URI.create("/");
        }
        C0837l lVar = this.f1826f;
        List<C0889z> list = this.f1827g;
        if (list != null && !list.isEmpty()) {
            if (lVar != null || (!"POST".equalsIgnoreCase(this.f1821a) && !"PUT".equalsIgnoreCase(this.f1821a))) {
                try {
                    C0555d dVar = new C0555d(uri);
                    dVar.mo2580a(this.f1822b);
                    dVar.mo2581a(this.f1827g);
                    uri = dVar.mo2582a();
                } catch (URISyntaxException unused) {
                }
            } else {
                lVar = new C0519a(this.f1827g, C0854d.f2421a);
            }
        }
        if (lVar == null) {
            kVar = new C0535b(this.f1821a);
        } else {
            C0534a aVar = new C0534a(this.f1821a);
            aVar.mo2548a(lVar);
            kVar = aVar;
        }
        kVar.mo2551a(this.f1823c);
        kVar.mo2553a(uri);
        C0830r rVar = this.f1825e;
        if (rVar != null) {
            kVar.mo3117a(rVar.mo3211b());
        }
        kVar.mo2552a(this.f1828h);
        return kVar;
    }

    /* renamed from: a */
    public C0533m mo2561a(URI uri) {
        this.f1824d = uri;
        return this;
    }
}

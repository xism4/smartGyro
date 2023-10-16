package p036c.p037a.p038a.p039a.p060i.p062b;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import p036c.p037a.p038a.p039a.C0487C;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.C0883t;
import p036c.p037a.p038a.p039a.p041b.C0539e;
import p036c.p037a.p038a.p039a.p041b.C0567p;
import p036c.p037a.p038a.p039a.p041b.p042a.C0516a;
import p036c.p037a.p038a.p039a.p041b.p044c.C0529i;
import p036c.p037a.p038a.p039a.p041b.p044c.C0530j;
import p036c.p037a.p038a.p039a.p041b.p044c.C0532l;
import p036c.p037a.p038a.p039a.p041b.p044c.C0533m;
import p036c.p037a.p038a.p039a.p041b.p046e.C0540a;
import p036c.p037a.p038a.p039a.p041b.p047f.C0555d;
import p036c.p037a.p038a.p039a.p041b.p047f.C0556e;
import p036c.p037a.p038a.p039a.p059h.C0668b;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0871b;
import p036c.p037a.p038a.p039a.p074p.C0878i;

/* renamed from: c.a.a.a.i.b.n */
public class C0702n implements C0567p {

    /* renamed from: a */
    public static final C0702n f2084a = new C0702n();

    /* renamed from: b */
    private static final String[] f2085b = {"GET", "HEAD"};

    /* renamed from: c */
    public C0668b f2086c = new C0668b(C0702n.class);

    /* renamed from: a */
    public C0532l mo2599a(C0881r rVar, C0883t tVar, C0855e eVar) {
        URI c = mo2902c(rVar, tVar, eVar);
        String method = rVar.getRequestLine().getMethod();
        if (method.equalsIgnoreCase("HEAD")) {
            return new C0530j(c);
        }
        if (method.equalsIgnoreCase("GET")) {
            return new C0529i(c);
        }
        if (tVar.getStatusLine().getStatusCode() != 307) {
            return new C0529i(c);
        }
        C0533m a = C0533m.m2189a(rVar);
        a.mo2561a(c);
        return a.mo2560a();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public URI mo2900a(String str) {
        try {
            C0555d dVar = new C0555d(new URI(str).normalize());
            String c = dVar.mo2586c();
            if (c != null) {
                dVar.mo2584b(c.toLowerCase(Locale.ROOT));
            }
            if (C0878i.m3089c(dVar.mo2588d())) {
                dVar.mo2585c("/");
            }
            return dVar.mo2582a();
        } catch (URISyntaxException e) {
            throw new C0487C("Invalid redirect URI: " + str, e);
        }
    }

    /* renamed from: b */
    public boolean mo2600b(C0881r rVar, C0883t tVar, C0855e eVar) {
        C0870a.m3042a(rVar, "HTTP request");
        C0870a.m3042a(tVar, "HTTP response");
        int statusCode = tVar.getStatusLine().getStatusCode();
        String method = rVar.getRequestLine().getMethod();
        C0576e firstHeader = tVar.getFirstHeader("location");
        if (statusCode != 307) {
            switch (statusCode) {
                case 301:
                    break;
                case 302:
                    return mo2901b(method) && firstHeader != null;
                case 303:
                    return true;
                default:
                    return false;
            }
        }
        return mo2901b(method);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public boolean mo2901b(String str) {
        for (String equalsIgnoreCase : f2085b) {
            if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: c */
    public URI mo2902c(C0881r rVar, C0883t tVar, C0855e eVar) {
        C0870a.m3042a(rVar, "HTTP request");
        C0870a.m3042a(tVar, "HTTP response");
        C0870a.m3042a(eVar, "HTTP context");
        C0540a a = C0540a.m2199a(eVar);
        C0576e firstHeader = tVar.getFirstHeader("location");
        if (firstHeader != null) {
            String value = firstHeader.getValue();
            if (this.f2086c.mo2805a()) {
                C0668b bVar = this.f2086c;
                bVar.mo2803a("Redirect requested to location '" + value + "'");
            }
            C0516a n = a.mo2574n();
            URI a2 = mo2900a(value);
            try {
                if (!a2.isAbsolute()) {
                    if (n.mo2514f()) {
                        C0867o c = a.mo3262c();
                        C0871b.m3049a((Object) c, "Target host");
                        a2 = C0556e.m2254a(C0556e.m2253a(new URI(rVar.getRequestLine().getUri()), c, false), a2);
                    } else {
                        throw new C0487C("Relative redirect location '" + a2 + "' not allowed");
                    }
                }
                C0710u uVar = (C0710u) a.getAttribute("http.protocol.redirect-locations");
                if (uVar == null) {
                    uVar = new C0710u();
                    eVar.setAttribute("http.protocol.redirect-locations", uVar);
                }
                if (n.mo2513e() || !uVar.mo2917b(a2)) {
                    uVar.mo2915a(a2);
                    return a2;
                }
                throw new C0539e("Circular redirect to '" + a2 + "'");
            } catch (URISyntaxException e) {
                throw new C0487C(e.getMessage(), e);
            }
        } else {
            throw new C0487C("Received redirect response " + tVar.getStatusLine() + " but no location header");
        }
    }
}

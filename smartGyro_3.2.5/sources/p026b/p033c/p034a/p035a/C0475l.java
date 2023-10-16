package p026b.p033c.p034a.p035a;

import java.net.URI;
import java.net.URISyntaxException;
import p036c.p037a.p038a.p039a.C0487C;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.C0883t;
import p036c.p037a.p038a.p039a.p041b.C0539e;
import p036c.p037a.p038a.p039a.p041b.p047f.C0556e;
import p036c.p037a.p038a.p039a.p060i.p062b.C0701m;
import p036c.p037a.p038a.p039a.p060i.p062b.C0710u;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p072n.C0855e;

/* renamed from: b.c.a.a.l */
class C0475l extends C0701m {

    /* renamed from: b */
    private final boolean f1728b;

    public C0475l(boolean z) {
        this.f1728b = z;
    }

    /* renamed from: a */
    public URI mo2423a(C0883t tVar, C0855e eVar) {
        URI uri;
        if (tVar != null) {
            C0576e firstHeader = tVar.getFirstHeader("location");
            if (firstHeader != null) {
                String replaceAll = firstHeader.getValue().replaceAll(" ", "%20");
                try {
                    URI uri2 = new URI(replaceAll);
                    C0844g params = tVar.getParams();
                    if (!uri2.isAbsolute()) {
                        if (!params.isParameterTrue("http.protocol.reject-relative-redirect")) {
                            C0867o oVar = (C0867o) eVar.getAttribute("http.target_host");
                            if (oVar != null) {
                                try {
                                    uri2 = C0556e.m2254a(C0556e.m2253a(new URI(((C0881r) eVar.getAttribute("http.request")).getRequestLine().getUri()), oVar, true), uri2);
                                } catch (URISyntaxException e) {
                                    throw new C0487C(e.getMessage(), e);
                                }
                            } else {
                                throw new IllegalStateException("Target host not available in the HTTP context");
                            }
                        } else {
                            throw new C0487C("Relative redirect location '" + uri2 + "' not allowed");
                        }
                    }
                    if (params.isParameterFalse("http.protocol.allow-circular-redirects")) {
                        C0710u uVar = (C0710u) eVar.getAttribute("http.protocol.redirect-locations");
                        if (uVar == null) {
                            uVar = new C0710u();
                            eVar.setAttribute("http.protocol.redirect-locations", uVar);
                        }
                        if (uri2.getFragment() != null) {
                            try {
                                uri = C0556e.m2253a(uri2, new C0867o(uri2.getHost(), uri2.getPort(), uri2.getScheme()), true);
                            } catch (URISyntaxException e2) {
                                throw new C0487C(e2.getMessage(), e2);
                            }
                        } else {
                            uri = uri2;
                        }
                        if (!uVar.mo2917b(uri)) {
                            uVar.mo2915a(uri);
                        } else {
                            throw new C0539e("Circular redirect to '" + uri + "'");
                        }
                    }
                    return uri2;
                } catch (URISyntaxException e3) {
                    throw new C0487C("Invalid redirect URI: " + replaceAll, e3);
                }
            } else {
                throw new C0487C("Received redirect response " + tVar.getStatusLine() + " but no location header");
            }
        } else {
            throw new IllegalArgumentException("HTTP response may not be null");
        }
    }

    /* renamed from: b */
    public boolean mo2424b(C0883t tVar, C0855e eVar) {
        if (!this.f1728b) {
            return false;
        }
        if (tVar != null) {
            int statusCode = tVar.getStatusLine().getStatusCode();
            if (statusCode == 307) {
                return true;
            }
            switch (statusCode) {
                case 301:
                case 302:
                case 303:
                    return true;
                default:
                    return false;
            }
        } else {
            throw new IllegalArgumentException("HTTP response may not be null");
        }
    }
}

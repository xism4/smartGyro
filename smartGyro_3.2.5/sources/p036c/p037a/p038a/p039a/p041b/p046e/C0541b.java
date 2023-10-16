package p036c.p037a.p038a.p039a.p041b.p046e;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.C0882s;
import p036c.p037a.p038a.p039a.p041b.C0559h;
import p036c.p037a.p038a.p039a.p041b.p044c.C0532l;
import p036c.p037a.p038a.p039a.p049d.C0573a;
import p036c.p037a.p038a.p039a.p050e.p052b.C0590e;
import p036c.p037a.p038a.p039a.p057f.C0642c;
import p036c.p037a.p038a.p039a.p057f.C0645f;
import p036c.p037a.p038a.p039a.p057f.C0648i;
import p036c.p037a.p038a.p039a.p057f.C0650k;
import p036c.p037a.p038a.p039a.p059h.C0668b;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0878i;

/* renamed from: c.a.a.a.b.e.b */
public class C0541b implements C0882s {

    /* renamed from: a */
    public C0668b f1831a = new C0668b(C0541b.class);

    /* renamed from: a */
    public void mo2352a(C0881r rVar, C0855e eVar) {
        URI uri;
        C0576e versionHeader;
        C0870a.m3042a(rVar, "HTTP request");
        C0870a.m3042a(eVar, "HTTP context");
        if (!rVar.getRequestLine().getMethod().equalsIgnoreCase("CONNECT")) {
            C0540a a = C0540a.m2199a(eVar);
            C0559h j = a.mo2570j();
            if (j == null) {
                this.f1831a.mo2803a("Cookie store not specified in HTTP context");
                return;
            }
            C0573a<C0650k> i = a.mo2569i();
            if (i == null) {
                this.f1831a.mo2803a("CookieSpec registry not specified in HTTP context");
                return;
            }
            C0867o c = a.mo3262c();
            if (c == null) {
                this.f1831a.mo2803a("Target host not set in the context");
                return;
            }
            C0590e l = a.mo2572l();
            if (l == null) {
                this.f1831a.mo2803a("Connection route not set in the context");
                return;
            }
            String b = a.mo2574n().mo2509b();
            if (b == null) {
                b = "default";
            }
            if (this.f1831a.mo2805a()) {
                this.f1831a.mo2803a("CookieSpec selected: " + b);
            }
            String str = null;
            if (rVar instanceof C0532l) {
                uri = ((C0532l) rVar).getURI();
            } else {
                try {
                    uri = new URI(rVar.getRequestLine().getUri());
                } catch (URISyntaxException unused) {
                    uri = null;
                }
            }
            if (uri != null) {
                str = uri.getPath();
            }
            String b2 = c.mo3271b();
            int c2 = c.mo3272c();
            if (c2 < 0) {
                c2 = l.getTargetHost().mo3272c();
            }
            boolean z = false;
            if (c2 < 0) {
                c2 = 0;
            }
            if (C0878i.m3089c(str)) {
                str = "/";
            }
            C0645f fVar = new C0645f(b2, c2, str, l.isSecure());
            C0650k lookup = i.lookup(b);
            if (lookup != null) {
                C0648i a2 = lookup.mo2770a(a);
                List<C0642c> cookies = j.getCookies();
                ArrayList arrayList = new ArrayList();
                Date date = new Date();
                for (C0642c next : cookies) {
                    if (next.isExpired(date)) {
                        if (this.f1831a.mo2805a()) {
                            this.f1831a.mo2803a("Cookie " + next + " expired");
                        }
                        z = true;
                    } else if (a2.mo2765b(next, fVar)) {
                        if (this.f1831a.mo2805a()) {
                            this.f1831a.mo2803a("Cookie " + next + " match " + fVar);
                        }
                        arrayList.add(next);
                    }
                }
                if (z) {
                    j.clearExpired(date);
                }
                if (!arrayList.isEmpty()) {
                    for (C0576e a3 : a2.formatCookies(arrayList)) {
                        rVar.mo3115a(a3);
                    }
                }
                if (a2.getVersion() > 0 && (versionHeader = a2.getVersionHeader()) != null) {
                    rVar.mo3115a(versionHeader);
                }
                eVar.setAttribute("http.cookie-spec", a2);
                eVar.setAttribute("http.cookie-origin", fVar);
            } else if (this.f1831a.mo2805a()) {
                this.f1831a.mo2803a("Unsupported cookie policy: " + b);
            }
        }
    }
}

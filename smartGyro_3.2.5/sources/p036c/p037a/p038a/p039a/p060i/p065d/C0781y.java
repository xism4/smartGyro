package p036c.p037a.p038a.p039a.p060i.p065d;

import java.util.Locale;
import p036c.p037a.p038a.p039a.p057f.C0641b;
import p036c.p037a.p038a.p039a.p057f.C0642c;
import p036c.p037a.p038a.p039a.p057f.C0645f;
import p036c.p037a.p038a.p039a.p057f.C0647h;
import p036c.p037a.p038a.p039a.p057f.C0653n;
import p036c.p037a.p038a.p039a.p057f.C0655p;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.d.y */
public class C0781y implements C0641b {
    /* renamed from: a */
    public String mo2741a() {
        return "domain";
    }

    /* renamed from: a */
    public void mo2751a(C0642c cVar, C0645f fVar) {
        C0870a.m3042a(cVar, "Cookie");
        C0870a.m3042a(fVar, "Cookie origin");
        String a = fVar.mo2756a();
        String domain = cVar.getDomain();
        if (domain == null) {
            throw new C0647h("Cookie domain may not be null");
        } else if (domain.equals(a)) {
        } else {
            if (domain.indexOf(46) == -1) {
                throw new C0647h("Domain attribute \"" + domain + "\" does not match the host \"" + a + "\"");
            } else if (domain.startsWith(".")) {
                int indexOf = domain.indexOf(46, 1);
                if (indexOf < 0 || indexOf == domain.length() - 1) {
                    throw new C0647h("Domain attribute \"" + domain + "\" violates RFC 2109: domain must contain an embedded dot");
                }
                String lowerCase = a.toLowerCase(Locale.ROOT);
                if (!lowerCase.endsWith(domain)) {
                    throw new C0647h("Illegal domain attribute \"" + domain + "\". Domain of origin: \"" + lowerCase + "\"");
                } else if (lowerCase.substring(0, lowerCase.length() - domain.length()).indexOf(46) != -1) {
                    throw new C0647h("Domain attribute \"" + domain + "\" violates RFC 2109: host minus domain may not contain any dots");
                }
            } else {
                throw new C0647h("Domain attribute \"" + domain + "\" violates RFC 2109: domain must start with a dot");
            }
        }
    }

    /* renamed from: a */
    public void mo2752a(C0655p pVar, String str) {
        C0870a.m3042a(pVar, "Cookie");
        if (str == null) {
            throw new C0653n("Missing value for domain attribute");
        } else if (!str.trim().isEmpty()) {
            pVar.setDomain(str);
        } else {
            throw new C0653n("Blank value for domain attribute");
        }
    }

    /* renamed from: b */
    public boolean mo2753b(C0642c cVar, C0645f fVar) {
        C0870a.m3042a(cVar, "Cookie");
        C0870a.m3042a(fVar, "Cookie origin");
        String a = fVar.mo2756a();
        String domain = cVar.getDomain();
        if (domain == null) {
            return false;
        }
        return a.equals(domain) || (domain.startsWith(".") && a.endsWith(domain));
    }
}

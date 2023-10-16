package p036c.p037a.p038a.p039a.p060i.p065d;

import java.util.Locale;
import p036c.p037a.p038a.p039a.p057f.C0640a;
import p036c.p037a.p038a.p039a.p057f.C0641b;
import p036c.p037a.p038a.p039a.p057f.C0642c;
import p036c.p037a.p038a.p039a.p057f.C0645f;
import p036c.p037a.p038a.p039a.p057f.C0647h;
import p036c.p037a.p038a.p039a.p057f.C0653n;
import p036c.p037a.p038a.p039a.p057f.C0655p;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.d.E */
public class C0751E implements C0641b {
    /* renamed from: a */
    public String mo2741a() {
        return "domain";
    }

    /* renamed from: a */
    public void mo2751a(C0642c cVar, C0645f fVar) {
        C0870a.m3042a(cVar, "Cookie");
        C0870a.m3042a(fVar, "Cookie origin");
        String lowerCase = fVar.mo2756a().toLowerCase(Locale.ROOT);
        if (cVar.getDomain() != null) {
            String lowerCase2 = cVar.getDomain().toLowerCase(Locale.ROOT);
            if (!(cVar instanceof C0640a) || !((C0640a) cVar).containsAttribute("domain")) {
                if (!cVar.getDomain().equals(lowerCase)) {
                    throw new C0647h("Illegal domain attribute: \"" + cVar.getDomain() + "\"." + "Domain of origin: \"" + lowerCase + "\"");
                }
            } else if (lowerCase2.startsWith(".")) {
                int indexOf = lowerCase2.indexOf(46, 1);
                if ((indexOf < 0 || indexOf == lowerCase2.length() - 1) && !lowerCase2.equals(".local")) {
                    throw new C0647h("Domain attribute \"" + cVar.getDomain() + "\" violates RFC 2965: the value contains no embedded dots " + "and the value is not .local");
                } else if (!mo3035a(lowerCase, lowerCase2)) {
                    throw new C0647h("Domain attribute \"" + cVar.getDomain() + "\" violates RFC 2965: effective host name does not " + "domain-match domain attribute.");
                } else if (lowerCase.substring(0, lowerCase.length() - lowerCase2.length()).indexOf(46) != -1) {
                    throw new C0647h("Domain attribute \"" + cVar.getDomain() + "\" violates RFC 2965: " + "effective host minus domain may not contain any dots");
                }
            } else {
                throw new C0647h("Domain attribute \"" + cVar.getDomain() + "\" violates RFC 2109: domain must start with a dot");
            }
        } else {
            throw new C0647h("Invalid cookie state: domain not specified");
        }
    }

    /* renamed from: a */
    public void mo2752a(C0655p pVar, String str) {
        C0870a.m3042a(pVar, "Cookie");
        if (str == null) {
            throw new C0653n("Missing value for domain attribute");
        } else if (!str.trim().isEmpty()) {
            String lowerCase = str.toLowerCase(Locale.ROOT);
            if (!str.startsWith(".")) {
                lowerCase = '.' + lowerCase;
            }
            pVar.setDomain(lowerCase);
        } else {
            throw new C0653n("Blank value for domain attribute");
        }
    }

    /* renamed from: a */
    public boolean mo3035a(String str, String str2) {
        return str.equals(str2) || (str2.startsWith(".") && str.endsWith(str2));
    }

    /* renamed from: b */
    public boolean mo2753b(C0642c cVar, C0645f fVar) {
        C0870a.m3042a(cVar, "Cookie");
        C0870a.m3042a(fVar, "Cookie origin");
        String lowerCase = fVar.mo2756a().toLowerCase(Locale.ROOT);
        String domain = cVar.getDomain();
        return mo3035a(lowerCase, domain) && lowerCase.substring(0, lowerCase.length() - domain.length()).indexOf(46) == -1;
    }
}

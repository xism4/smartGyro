package p036c.p037a.p038a.p039a.p060i.p065d;

import java.util.Locale;
import java.util.StringTokenizer;
import p036c.p037a.p038a.p039a.p057f.C0642c;
import p036c.p037a.p038a.p039a.p057f.C0645f;
import p036c.p037a.p038a.p039a.p057f.C0647h;
import p036c.p037a.p038a.p039a.p057f.C0653n;
import p036c.p037a.p038a.p039a.p057f.C0655p;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0878i;

/* renamed from: c.a.a.a.i.d.u */
public class C0777u extends C0761f {
    /* renamed from: a */
    private static boolean m2821a(String str) {
        String upperCase = str.toUpperCase(Locale.ROOT);
        return upperCase.endsWith(".COM") || upperCase.endsWith(".EDU") || upperCase.endsWith(".NET") || upperCase.endsWith(".GOV") || upperCase.endsWith(".MIL") || upperCase.endsWith(".ORG") || upperCase.endsWith(".INT");
    }

    /* renamed from: a */
    public String mo2741a() {
        return "domain";
    }

    /* renamed from: a */
    public void mo2751a(C0642c cVar, C0645f fVar) {
        String a = fVar.mo2756a();
        String domain = cVar.getDomain();
        if (!a.equals(domain) && !C0761f.m2784a(domain, a)) {
            throw new C0647h("Illegal domain attribute \"" + domain + "\". Domain of origin: \"" + a + "\"");
        } else if (a.contains(".")) {
            int countTokens = new StringTokenizer(domain, ".").countTokens();
            if (m2821a(domain)) {
                if (countTokens < 2) {
                    throw new C0647h("Domain attribute \"" + domain + "\" violates the Netscape cookie specification for " + "special domains");
                }
            } else if (countTokens < 3) {
                throw new C0647h("Domain attribute \"" + domain + "\" violates the Netscape cookie specification");
            }
        }
    }

    /* renamed from: a */
    public void mo2752a(C0655p pVar, String str) {
        C0870a.m3042a(pVar, "Cookie");
        if (!C0878i.m3088b(str)) {
            pVar.setDomain(str);
            return;
        }
        throw new C0653n("Blank or null value for domain attribute");
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
        return a.endsWith(domain);
    }
}

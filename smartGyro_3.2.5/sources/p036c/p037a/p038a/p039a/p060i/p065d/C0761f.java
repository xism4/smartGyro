package p036c.p037a.p038a.p039a.p060i.p065d;

import java.util.Locale;
import p036c.p037a.p038a.p039a.p050e.p056f.C0625a;
import p036c.p037a.p038a.p039a.p057f.C0640a;
import p036c.p037a.p038a.p039a.p057f.C0641b;
import p036c.p037a.p038a.p039a.p057f.C0642c;
import p036c.p037a.p038a.p039a.p057f.C0645f;
import p036c.p037a.p038a.p039a.p057f.C0647h;
import p036c.p037a.p038a.p039a.p057f.C0653n;
import p036c.p037a.p038a.p039a.p057f.C0655p;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0878i;

/* renamed from: c.a.a.a.i.d.f */
public class C0761f implements C0641b {
    /* renamed from: a */
    static boolean m2784a(String str, String str2) {
        if (!C0625a.m2378a(str2) && !C0625a.m2379b(str2)) {
            if (str.startsWith(".")) {
                str = str.substring(1);
            }
            if (str2.endsWith(str)) {
                int length = str2.length() - str.length();
                if (length == 0) {
                    return true;
                }
                return length > 1 && str2.charAt(length - 1) == '.';
            }
        }
    }

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
            throw new C0647h("Cookie 'domain' may not be null");
        } else if (!a.equals(domain) && !m2784a(domain, a)) {
            throw new C0647h("Illegal 'domain' attribute \"" + domain + "\". Domain of origin: \"" + a + "\"");
        }
    }

    /* renamed from: a */
    public void mo2752a(C0655p pVar, String str) {
        C0870a.m3042a(pVar, "Cookie");
        if (C0878i.m3088b(str)) {
            throw new C0653n("Blank or null value for domain attribute");
        } else if (!str.endsWith(".")) {
            if (str.startsWith(".")) {
                str = str.substring(1);
            }
            pVar.setDomain(str.toLowerCase(Locale.ROOT));
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
        if (domain.startsWith(".")) {
            domain = domain.substring(1);
        }
        String lowerCase = domain.toLowerCase(Locale.ROOT);
        if (a.equals(lowerCase)) {
            return true;
        }
        if (!(cVar instanceof C0640a) || !((C0640a) cVar).containsAttribute("domain")) {
            return false;
        }
        return m2784a(lowerCase, a);
    }
}

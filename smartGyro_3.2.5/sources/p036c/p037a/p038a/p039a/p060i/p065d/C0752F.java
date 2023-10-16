package p036c.p037a.p038a.p039a.p060i.p065d;

import java.util.StringTokenizer;
import p036c.p037a.p038a.p039a.p057f.C0640a;
import p036c.p037a.p038a.p039a.p057f.C0641b;
import p036c.p037a.p038a.p039a.p057f.C0642c;
import p036c.p037a.p038a.p039a.p057f.C0645f;
import p036c.p037a.p038a.p039a.p057f.C0647h;
import p036c.p037a.p038a.p039a.p057f.C0653n;
import p036c.p037a.p038a.p039a.p057f.C0654o;
import p036c.p037a.p038a.p039a.p057f.C0655p;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.d.F */
public class C0752F implements C0641b {
    /* renamed from: a */
    private static boolean m2758a(int i, int[] iArr) {
        for (int i2 : iArr) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private static int[] m2759a(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        int[] iArr = new int[stringTokenizer.countTokens()];
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            try {
                iArr[i] = Integer.parseInt(stringTokenizer.nextToken().trim());
                if (iArr[i] >= 0) {
                    i++;
                } else {
                    throw new C0653n("Invalid Port attribute.");
                }
            } catch (NumberFormatException e) {
                throw new C0653n("Invalid Port attribute: " + e.getMessage());
            }
        }
        return iArr;
    }

    /* renamed from: a */
    public String mo2741a() {
        return "port";
    }

    /* renamed from: a */
    public void mo2751a(C0642c cVar, C0645f fVar) {
        C0870a.m3042a(cVar, "Cookie");
        C0870a.m3042a(fVar, "Cookie origin");
        int c = fVar.mo2758c();
        if ((cVar instanceof C0640a) && ((C0640a) cVar).containsAttribute("port") && !m2758a(c, cVar.getPorts())) {
            throw new C0647h("Port attribute violates RFC 2965: Request port not found in cookie's port list.");
        }
    }

    /* renamed from: a */
    public void mo2752a(C0655p pVar, String str) {
        C0870a.m3042a(pVar, "Cookie");
        if (pVar instanceof C0654o) {
            C0654o oVar = (C0654o) pVar;
            if (str != null && !str.trim().isEmpty()) {
                oVar.setPorts(m2759a(str));
            }
        }
    }

    /* renamed from: b */
    public boolean mo2753b(C0642c cVar, C0645f fVar) {
        C0870a.m3042a(cVar, "Cookie");
        C0870a.m3042a(fVar, "Cookie origin");
        int c = fVar.mo2758c();
        if (!(cVar instanceof C0640a) || !((C0640a) cVar).containsAttribute("port")) {
            return true;
        }
        return cVar.getPorts() != null && m2758a(c, cVar.getPorts());
    }
}

package p036c.p037a.p038a.p039a.p060i.p065d;

import p036c.p037a.p038a.p039a.p057f.C0641b;
import p036c.p037a.p038a.p039a.p057f.C0642c;
import p036c.p037a.p038a.p039a.p057f.C0645f;
import p036c.p037a.p038a.p039a.p057f.C0647h;
import p036c.p037a.p038a.p039a.p057f.C0655p;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0878i;

/* renamed from: c.a.a.a.i.d.i */
public class C0764i implements C0641b {
    /* renamed from: a */
    static boolean m2793a(String str, String str2) {
        if (str2 == null) {
            str2 = "/";
        }
        if (str2.length() > 1 && str2.endsWith("/")) {
            str2 = str2.substring(0, str2.length() - 1);
        }
        return str.startsWith(str2) && (str2.equals("/") || str.length() == str2.length() || str.charAt(str2.length()) == '/');
    }

    /* renamed from: a */
    public String mo2741a() {
        return "path";
    }

    /* renamed from: a */
    public void mo2751a(C0642c cVar, C0645f fVar) {
        if (!mo2753b(cVar, fVar)) {
            throw new C0647h("Illegal 'path' attribute \"" + cVar.getPath() + "\". Path of origin: \"" + fVar.mo2757b() + "\"");
        }
    }

    /* renamed from: a */
    public void mo2752a(C0655p pVar, String str) {
        C0870a.m3042a(pVar, "Cookie");
        if (C0878i.m3088b(str)) {
            str = "/";
        }
        pVar.setPath(str);
    }

    /* renamed from: b */
    public boolean mo2753b(C0642c cVar, C0645f fVar) {
        C0870a.m3042a(cVar, "Cookie");
        C0870a.m3042a(fVar, "Cookie origin");
        return m2793a(fVar.mo2757b(), cVar.getPath());
    }
}

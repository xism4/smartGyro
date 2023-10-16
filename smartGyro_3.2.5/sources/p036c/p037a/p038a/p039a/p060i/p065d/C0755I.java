package p036c.p037a.p038a.p039a.p060i.p065d;

import p036c.p037a.p038a.p039a.p057f.C0640a;
import p036c.p037a.p038a.p039a.p057f.C0641b;
import p036c.p037a.p038a.p039a.p057f.C0642c;
import p036c.p037a.p038a.p039a.p057f.C0645f;
import p036c.p037a.p038a.p039a.p057f.C0647h;
import p036c.p037a.p038a.p039a.p057f.C0653n;
import p036c.p037a.p038a.p039a.p057f.C0654o;
import p036c.p037a.p038a.p039a.p057f.C0655p;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.d.I */
public class C0755I implements C0641b {
    /* renamed from: a */
    public String mo2741a() {
        return "version";
    }

    /* renamed from: a */
    public void mo2751a(C0642c cVar, C0645f fVar) {
        C0870a.m3042a(cVar, "Cookie");
        if ((cVar instanceof C0654o) && (cVar instanceof C0640a) && !((C0640a) cVar).containsAttribute("version")) {
            throw new C0647h("Violates RFC 2965. Version attribute is required.");
        }
    }

    /* renamed from: a */
    public void mo2752a(C0655p pVar, String str) {
        int i;
        C0870a.m3042a(pVar, "Cookie");
        if (str != null) {
            try {
                i = Integer.parseInt(str);
            } catch (NumberFormatException unused) {
                i = -1;
            }
            if (i >= 0) {
                pVar.setVersion(i);
                return;
            }
            throw new C0653n("Invalid cookie version.");
        }
        throw new C0653n("Missing value for version attribute");
    }

    /* renamed from: b */
    public boolean mo2753b(C0642c cVar, C0645f fVar) {
        return true;
    }
}

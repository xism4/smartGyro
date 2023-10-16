package p036c.p037a.p038a.p039a.p060i.p065d;

import p036c.p037a.p038a.p039a.p057f.C0641b;
import p036c.p037a.p038a.p039a.p057f.C0642c;
import p036c.p037a.p038a.p039a.p057f.C0645f;
import p036c.p037a.p038a.p039a.p057f.C0647h;
import p036c.p037a.p038a.p039a.p057f.C0653n;
import p036c.p037a.p038a.p039a.p057f.C0655p;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.d.B */
public class C0748B extends C0756a implements C0641b {
    /* renamed from: a */
    public String mo2741a() {
        return "version";
    }

    /* renamed from: a */
    public void mo2751a(C0642c cVar, C0645f fVar) {
        C0870a.m3042a(cVar, "Cookie");
        if (cVar.getVersion() < 0) {
            throw new C0647h("Cookie version may not be negative");
        }
    }

    /* renamed from: a */
    public void mo2752a(C0655p pVar, String str) {
        C0870a.m3042a(pVar, "Cookie");
        if (str == null) {
            throw new C0653n("Missing value for version attribute");
        } else if (!str.trim().isEmpty()) {
            try {
                pVar.setVersion(Integer.parseInt(str));
            } catch (NumberFormatException e) {
                throw new C0653n("Invalid version: " + e.getMessage());
            }
        } else {
            throw new C0653n("Blank value for version attribute");
        }
    }
}

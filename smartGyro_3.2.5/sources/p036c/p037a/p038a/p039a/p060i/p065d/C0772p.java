package p036c.p037a.p038a.p039a.p060i.p065d;

import p036c.p037a.p038a.p039a.p057f.C0641b;
import p036c.p037a.p038a.p039a.p057f.C0653n;
import p036c.p037a.p038a.p039a.p057f.C0655p;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.d.p */
public class C0772p extends C0756a implements C0641b {
    /* renamed from: a */
    public String mo2741a() {
        return "version";
    }

    /* renamed from: a */
    public void mo2752a(C0655p pVar, String str) {
        C0870a.m3042a(pVar, "Cookie");
        if (str != null) {
            int i = 0;
            try {
                i = Integer.parseInt(str);
            } catch (NumberFormatException unused) {
            }
            pVar.setVersion(i);
            return;
        }
        throw new C0653n("Missing value for version attribute");
    }
}

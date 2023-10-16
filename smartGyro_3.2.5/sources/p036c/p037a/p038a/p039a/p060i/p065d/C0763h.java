package p036c.p037a.p038a.p039a.p060i.p065d;

import java.util.Date;
import p036c.p037a.p038a.p039a.p057f.C0641b;
import p036c.p037a.p038a.p039a.p057f.C0653n;
import p036c.p037a.p038a.p039a.p057f.C0655p;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.d.h */
public class C0763h extends C0756a implements C0641b {
    /* renamed from: a */
    public String mo2741a() {
        return "max-age";
    }

    /* renamed from: a */
    public void mo2752a(C0655p pVar, String str) {
        C0870a.m3042a(pVar, "Cookie");
        if (str != null) {
            try {
                int parseInt = Integer.parseInt(str);
                if (parseInt >= 0) {
                    pVar.setExpiryDate(new Date(System.currentTimeMillis() + (((long) parseInt) * 1000)));
                    return;
                }
                throw new C0653n("Negative 'max-age' attribute: " + str);
            } catch (NumberFormatException unused) {
                throw new C0653n("Invalid 'max-age' attribute: " + str);
            }
        } else {
            throw new C0653n("Missing value for 'max-age' attribute");
        }
    }
}

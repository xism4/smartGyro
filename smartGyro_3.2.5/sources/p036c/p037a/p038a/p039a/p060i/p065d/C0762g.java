package p036c.p037a.p038a.p039a.p060i.p065d;

import java.util.Date;
import p036c.p037a.p038a.p039a.p041b.p047f.C0553c;
import p036c.p037a.p038a.p039a.p057f.C0641b;
import p036c.p037a.p038a.p039a.p057f.C0653n;
import p036c.p037a.p038a.p039a.p057f.C0655p;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.d.g */
public class C0762g extends C0756a implements C0641b {

    /* renamed from: a */
    private final String[] f2250a;

    public C0762g(String[] strArr) {
        C0870a.m3042a(strArr, "Array of date patterns");
        this.f2250a = strArr;
    }

    /* renamed from: a */
    public String mo2741a() {
        return "expires";
    }

    /* renamed from: a */
    public void mo2752a(C0655p pVar, String str) {
        C0870a.m3042a(pVar, "Cookie");
        if (str != null) {
            Date a = C0553c.m2227a(str, this.f2250a);
            if (a != null) {
                pVar.setExpiryDate(a);
                return;
            }
            throw new C0653n("Invalid 'expires' attribute: " + str);
        }
        throw new C0653n("Missing value for 'expires' attribute");
    }
}

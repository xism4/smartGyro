package p036c.p037a.p038a.p039a.p060i.p063c;

import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.p050e.C0637r;
import p036c.p037a.p038a.p039a.p050e.C0638s;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.c.k */
public class C0737k implements C0637r {

    /* renamed from: a */
    public static final C0737k f2213a = new C0737k();

    /* renamed from: a */
    public int mo2732a(C0867o oVar) {
        C0870a.m3042a(oVar, "HTTP host");
        int c = oVar.mo3272c();
        if (c > 0) {
            return c;
        }
        String d = oVar.mo3274d();
        if (d.equalsIgnoreCase("http")) {
            return 80;
        }
        if (d.equalsIgnoreCase("https")) {
            return 443;
        }
        throw new C0638s(d + " protocol is not supported");
    }
}

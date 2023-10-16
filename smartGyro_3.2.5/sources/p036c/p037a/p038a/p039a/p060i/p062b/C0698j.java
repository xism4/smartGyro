package p036c.p037a.p038a.p039a.p060i.p062b;

import p036c.p037a.p038a.p039a.C0639f;
import p036c.p037a.p038a.p039a.C0883t;
import p036c.p037a.p038a.p039a.p050e.C0626g;
import p036c.p037a.p038a.p039a.p069k.C0816d;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.b.j */
public class C0698j implements C0626g {

    /* renamed from: a */
    public static final C0698j f2078a = new C0698j();

    /* renamed from: a */
    public long mo2704a(C0883t tVar, C0855e eVar) {
        C0870a.m3042a(tVar, "HTTP response");
        C0816d dVar = new C0816d(tVar.headerIterator("Keep-Alive"));
        while (dVar.hasNext()) {
            C0639f nextElement = dVar.nextElement();
            String name = nextElement.getName();
            String value = nextElement.getValue();
            if (value != null && name.equalsIgnoreCase("timeout")) {
                try {
                    return Long.parseLong(value) * 1000;
                } catch (NumberFormatException unused) {
                }
            }
        }
        return -1;
    }
}

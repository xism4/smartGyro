package p036c.p037a.p038a.p039a.p060i.p065d;

import p036c.p037a.p038a.p039a.p057f.C0648i;
import p036c.p037a.p038a.p039a.p057f.C0649j;
import p036c.p037a.p038a.p039a.p057f.C0650k;
import p036c.p037a.p038a.p039a.p072n.C0855e;

@Deprecated
/* renamed from: c.a.a.a.i.d.o */
public class C0770o implements C0649j, C0650k {

    /* renamed from: a */
    private final C0771a f2253a;

    /* renamed from: b */
    private final C0648i f2254b;

    /* renamed from: c.a.a.a.i.d.o$a */
    public enum C0771a {
        SECURITYLEVEL_DEFAULT,
        SECURITYLEVEL_IE_MEDIUM
    }

    public C0770o() {
        this((String[]) null, C0771a.SECURITYLEVEL_DEFAULT);
    }

    public C0770o(String[] strArr, C0771a aVar) {
        this.f2253a = aVar;
        this.f2254b = new C0769n(strArr, aVar);
    }

    /* JADX WARNING: type inference failed for: r3v5, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public p036c.p037a.p038a.p039a.p057f.C0648i mo2769a(p036c.p037a.p038a.p039a.p070l.C0844g r3) {
        /*
            r2 = this;
            r0 = 0
            if (r3 == 0) goto L_0x0022
            java.lang.String r1 = "http.protocol.cookie-datepatterns"
            java.lang.Object r3 = r3.getParameter(r1)
            java.util.Collection r3 = (java.util.Collection) r3
            if (r3 == 0) goto L_0x001a
            int r0 = r3.size()
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.Object[] r3 = r3.toArray(r0)
            r0 = r3
            java.lang.String[] r0 = (java.lang.String[]) r0
        L_0x001a:
            c.a.a.a.i.d.n r3 = new c.a.a.a.i.d.n
            c.a.a.a.i.d.o$a r1 = r2.f2253a
            r3.<init>(r0, r1)
            return r3
        L_0x0022:
            c.a.a.a.i.d.n r3 = new c.a.a.a.i.d.n
            c.a.a.a.i.d.o$a r1 = r2.f2253a
            r3.<init>(r0, r1)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p060i.p065d.C0770o.mo2769a(c.a.a.a.l.g):c.a.a.a.f.i");
    }

    /* renamed from: a */
    public C0648i mo2770a(C0855e eVar) {
        return this.f2254b;
    }
}

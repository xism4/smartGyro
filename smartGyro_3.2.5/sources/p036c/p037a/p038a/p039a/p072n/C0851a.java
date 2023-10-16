package p036c.p037a.p038a.p039a.p072n;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.n.a */
public class C0851a implements C0855e {

    /* renamed from: a */
    private final C0855e f2415a;

    /* renamed from: b */
    private final Map<String, Object> f2416b;

    public C0851a() {
        this((C0855e) null);
    }

    public C0851a(C0855e eVar) {
        this.f2416b = new ConcurrentHashMap();
        this.f2415a = eVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000d, code lost:
        r1 = r2.f2415a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getAttribute(java.lang.String r3) {
        /*
            r2 = this;
            java.lang.String r0 = "Id"
            p036c.p037a.p038a.p039a.p074p.C0870a.m3042a(r3, (java.lang.String) r0)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r2.f2416b
            java.lang.Object r0 = r0.get(r3)
            if (r0 != 0) goto L_0x0015
            c.a.a.a.n.e r1 = r2.f2415a
            if (r1 == 0) goto L_0x0015
            java.lang.Object r0 = r1.getAttribute(r3)
        L_0x0015:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p072n.C0851a.getAttribute(java.lang.String):java.lang.Object");
    }

    public void setAttribute(String str, Object obj) {
        C0870a.m3042a(str, "Id");
        if (obj != null) {
            this.f2416b.put(str, obj);
        } else {
            this.f2416b.remove(str);
        }
    }

    public String toString() {
        return this.f2416b.toString();
    }
}

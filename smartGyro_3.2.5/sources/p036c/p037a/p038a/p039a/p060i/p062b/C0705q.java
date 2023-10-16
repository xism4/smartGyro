package p036c.p037a.p038a.p039a.p060i.p062b;

import java.security.Principal;
import p036c.p037a.p038a.p039a.p040a.C0497c;
import p036c.p037a.p038a.p039a.p040a.C0503i;
import p036c.p037a.p038a.p039a.p040a.C0508n;
import p036c.p037a.p038a.p039a.p041b.C0569r;

/* renamed from: c.a.a.a.i.b.q */
public class C0705q implements C0569r {

    /* renamed from: a */
    public static final C0705q f2112a = new C0705q();

    /* renamed from: a */
    private static Principal m2586a(C0503i iVar) {
        C0508n c;
        C0497c b = iVar.mo2483b();
        if (b == null || !b.isComplete() || !b.isConnectionBased() || (c = iVar.mo2484c()) == null) {
            return null;
        }
        return c.getUserPrincipal();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002a, code lost:
        r3 = ((p036c.p037a.p038a.p039a.p050e.C0635p) r3).getSSLSession();
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object mo2602a(p036c.p037a.p038a.p039a.p072n.C0855e r3) {
        /*
            r2 = this;
            c.a.a.a.b.e.a r3 = p036c.p037a.p038a.p039a.p041b.p046e.C0540a.m2199a((p036c.p037a.p038a.p039a.p072n.C0855e) r3)
            c.a.a.a.a.i r0 = r3.mo2575o()
            if (r0 == 0) goto L_0x0019
            java.security.Principal r0 = m2586a((p036c.p037a.p038a.p039a.p040a.C0503i) r0)
            if (r0 != 0) goto L_0x001a
            c.a.a.a.a.i r0 = r3.mo2573m()
            java.security.Principal r0 = m2586a((p036c.p037a.p038a.p039a.p040a.C0503i) r0)
            goto L_0x001a
        L_0x0019:
            r0 = 0
        L_0x001a:
            if (r0 != 0) goto L_0x0036
            c.a.a.a.j r3 = r3.mo3259a()
            boolean r1 = r3.isOpen()
            if (r1 == 0) goto L_0x0036
            boolean r1 = r3 instanceof p036c.p037a.p038a.p039a.p050e.C0635p
            if (r1 == 0) goto L_0x0036
            c.a.a.a.e.p r3 = (p036c.p037a.p038a.p039a.p050e.C0635p) r3
            javax.net.ssl.SSLSession r3 = r3.getSSLSession()
            if (r3 == 0) goto L_0x0036
            java.security.Principal r0 = r3.getLocalPrincipal()
        L_0x0036:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p060i.p062b.C0705q.mo2602a(c.a.a.a.n.e):java.lang.Object");
    }
}

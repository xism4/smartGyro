package p036c.p037a.p038a.p039a.p060i.p061a;

import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.C0883t;
import p036c.p037a.p038a.p039a.p040a.C0496b;
import p036c.p037a.p038a.p039a.p040a.C0503i;
import p036c.p037a.p038a.p039a.p041b.C0520c;
import p036c.p037a.p038a.p039a.p059h.C0668b;
import p036c.p037a.p038a.p039a.p072n.C0855e;

/* renamed from: c.a.a.a.i.a.g */
public class C0677g {

    /* renamed from: a */
    public C0668b f2014a;

    public C0677g(C0668b bVar) {
        this.f2014a = bVar == null ? new C0668b(getClass()) : bVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0072 A[Catch:{ p -> 0x00e5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00bd A[Catch:{ p -> 0x00e5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00e4 A[RETURN] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo2833a(p036c.p037a.p038a.p039a.C0867o r7, p036c.p037a.p038a.p039a.C0883t r8, p036c.p037a.p038a.p039a.p041b.C0520c r9, p036c.p037a.p038a.p039a.p040a.C0503i r10, p036c.p037a.p038a.p039a.p072n.C0855e r11) {
        /*
            r6 = this;
            r0 = 0
            c.a.a.a.h.b r1 = r6.f2014a     // Catch:{ p -> 0x00e5 }
            boolean r1 = r1.mo2805a()     // Catch:{ p -> 0x00e5 }
            if (r1 == 0) goto L_0x0023
            c.a.a.a.h.b r1 = r6.f2014a     // Catch:{ p -> 0x00e5 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ p -> 0x00e5 }
            r2.<init>()     // Catch:{ p -> 0x00e5 }
            java.lang.String r3 = r7.mo3275e()     // Catch:{ p -> 0x00e5 }
            r2.append(r3)     // Catch:{ p -> 0x00e5 }
            java.lang.String r3 = " requested authentication"
            r2.append(r3)     // Catch:{ p -> 0x00e5 }
            java.lang.String r2 = r2.toString()     // Catch:{ p -> 0x00e5 }
            r1.mo2803a(r2)     // Catch:{ p -> 0x00e5 }
        L_0x0023:
            java.util.Map r1 = r9.mo2535a((p036c.p037a.p038a.p039a.C0867o) r7, (p036c.p037a.p038a.p039a.C0883t) r8, (p036c.p037a.p038a.p039a.p072n.C0855e) r11)     // Catch:{ p -> 0x00e5 }
            boolean r2 = r1.isEmpty()     // Catch:{ p -> 0x00e5 }
            if (r2 == 0) goto L_0x0035
            c.a.a.a.h.b r7 = r6.f2014a     // Catch:{ p -> 0x00e5 }
            java.lang.String r8 = "Response contains no authentication challenges"
            r7.mo2803a(r8)     // Catch:{ p -> 0x00e5 }
            return r0
        L_0x0035:
            c.a.a.a.a.c r2 = r10.mo2483b()     // Catch:{ p -> 0x00e5 }
            int[] r3 = p036c.p037a.p038a.p039a.p060i.p061a.C0676f.f2013a     // Catch:{ p -> 0x00e5 }
            c.a.a.a.a.b r4 = r10.mo2485d()     // Catch:{ p -> 0x00e5 }
            int r4 = r4.ordinal()     // Catch:{ p -> 0x00e5 }
            r3 = r3[r4]     // Catch:{ p -> 0x00e5 }
            r4 = 1
            if (r3 == r4) goto L_0x005a
            r5 = 2
            if (r3 == r5) goto L_0x005a
            r5 = 3
            if (r3 == r5) goto L_0x0056
            r5 = 4
            if (r3 == r5) goto L_0x0055
            r5 = 5
            if (r3 == r5) goto L_0x0070
            goto L_0x00b1
        L_0x0055:
            return r0
        L_0x0056:
            r10.mo2486e()     // Catch:{ p -> 0x00e5 }
            goto L_0x00b1
        L_0x005a:
            if (r2 != 0) goto L_0x0070
            c.a.a.a.h.b r8 = r6.f2014a     // Catch:{ p -> 0x00e5 }
            java.lang.String r1 = "Auth scheme is null"
            r8.mo2803a(r1)     // Catch:{ p -> 0x00e5 }
            r8 = 0
            r9.mo2537a((p036c.p037a.p038a.p039a.C0867o) r7, (p036c.p037a.p038a.p039a.p040a.C0497c) r8, (p036c.p037a.p038a.p039a.p072n.C0855e) r11)     // Catch:{ p -> 0x00e5 }
            r10.mo2486e()     // Catch:{ p -> 0x00e5 }
            c.a.a.a.a.b r7 = p036c.p037a.p038a.p039a.p040a.C0496b.FAILURE     // Catch:{ p -> 0x00e5 }
            r10.mo2478a((p036c.p037a.p038a.p039a.p040a.C0496b) r7)     // Catch:{ p -> 0x00e5 }
            return r0
        L_0x0070:
            if (r2 == 0) goto L_0x00b1
            java.lang.String r3 = r2.getSchemeName()     // Catch:{ p -> 0x00e5 }
            java.util.Locale r5 = java.util.Locale.ROOT     // Catch:{ p -> 0x00e5 }
            java.lang.String r3 = r3.toLowerCase(r5)     // Catch:{ p -> 0x00e5 }
            java.lang.Object r3 = r1.get(r3)     // Catch:{ p -> 0x00e5 }
            c.a.a.a.e r3 = (p036c.p037a.p038a.p039a.C0576e) r3     // Catch:{ p -> 0x00e5 }
            if (r3 == 0) goto L_0x0056
            c.a.a.a.h.b r8 = r6.f2014a     // Catch:{ p -> 0x00e5 }
            java.lang.String r1 = "Authorization challenge processed"
            r8.mo2803a(r1)     // Catch:{ p -> 0x00e5 }
            r2.mo2463a(r3)     // Catch:{ p -> 0x00e5 }
            boolean r8 = r2.isComplete()     // Catch:{ p -> 0x00e5 }
            if (r8 == 0) goto L_0x00ab
            c.a.a.a.h.b r8 = r6.f2014a     // Catch:{ p -> 0x00e5 }
            java.lang.String r1 = "Authentication failed"
            r8.mo2803a(r1)     // Catch:{ p -> 0x00e5 }
            c.a.a.a.a.c r8 = r10.mo2483b()     // Catch:{ p -> 0x00e5 }
            r9.mo2537a((p036c.p037a.p038a.p039a.C0867o) r7, (p036c.p037a.p038a.p039a.p040a.C0497c) r8, (p036c.p037a.p038a.p039a.p072n.C0855e) r11)     // Catch:{ p -> 0x00e5 }
            r10.mo2486e()     // Catch:{ p -> 0x00e5 }
            c.a.a.a.a.b r7 = p036c.p037a.p038a.p039a.p040a.C0496b.FAILURE     // Catch:{ p -> 0x00e5 }
            r10.mo2478a((p036c.p037a.p038a.p039a.p040a.C0496b) r7)     // Catch:{ p -> 0x00e5 }
            return r0
        L_0x00ab:
            c.a.a.a.a.b r7 = p036c.p037a.p038a.p039a.p040a.C0496b.HANDSHAKE     // Catch:{ p -> 0x00e5 }
            r10.mo2478a((p036c.p037a.p038a.p039a.p040a.C0496b) r7)     // Catch:{ p -> 0x00e5 }
            return r4
        L_0x00b1:
            java.util.Queue r7 = r9.mo2536a(r1, r7, r8, r11)     // Catch:{ p -> 0x00e5 }
            if (r7 == 0) goto L_0x00e4
            boolean r8 = r7.isEmpty()     // Catch:{ p -> 0x00e5 }
            if (r8 != 0) goto L_0x00e4
            c.a.a.a.h.b r8 = r6.f2014a     // Catch:{ p -> 0x00e5 }
            boolean r8 = r8.mo2805a()     // Catch:{ p -> 0x00e5 }
            if (r8 == 0) goto L_0x00db
            c.a.a.a.h.b r8 = r6.f2014a     // Catch:{ p -> 0x00e5 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ p -> 0x00e5 }
            r9.<init>()     // Catch:{ p -> 0x00e5 }
            java.lang.String r11 = "Selected authentication options: "
            r9.append(r11)     // Catch:{ p -> 0x00e5 }
            r9.append(r7)     // Catch:{ p -> 0x00e5 }
            java.lang.String r9 = r9.toString()     // Catch:{ p -> 0x00e5 }
            r8.mo2803a(r9)     // Catch:{ p -> 0x00e5 }
        L_0x00db:
            c.a.a.a.a.b r8 = p036c.p037a.p038a.p039a.p040a.C0496b.CHALLENGED     // Catch:{ p -> 0x00e5 }
            r10.mo2478a((p036c.p037a.p038a.p039a.p040a.C0496b) r8)     // Catch:{ p -> 0x00e5 }
            r10.mo2482a((java.util.Queue<p036c.p037a.p038a.p039a.p040a.C0495a>) r7)     // Catch:{ p -> 0x00e5 }
            return r4
        L_0x00e4:
            return r0
        L_0x00e5:
            r7 = move-exception
            c.a.a.a.h.b r8 = r6.f2014a
            boolean r8 = r8.mo2812d()
            if (r8 == 0) goto L_0x0108
            c.a.a.a.h.b r8 = r6.f2014a
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r11 = "Malformed challenge: "
            r9.append(r11)
            java.lang.String r7 = r7.getMessage()
            r9.append(r7)
            java.lang.String r7 = r9.toString()
            r8.mo2811d(r7)
        L_0x0108:
            r10.mo2486e()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p060i.p061a.C0677g.mo2833a(c.a.a.a.o, c.a.a.a.t, c.a.a.a.b.c, c.a.a.a.a.i, c.a.a.a.n.e):boolean");
    }

    /* renamed from: b */
    public boolean mo2834b(C0867o oVar, C0883t tVar, C0520c cVar, C0503i iVar, C0855e eVar) {
        if (cVar.mo2539b(oVar, tVar, eVar)) {
            this.f2014a.mo2803a("Authentication required");
            if (iVar.mo2485d() == C0496b.SUCCESS) {
                cVar.mo2537a(oVar, iVar.mo2483b(), eVar);
            }
            return true;
        }
        int i = C0676f.f2013a[iVar.mo2485d().ordinal()];
        if (i == 1 || i == 2) {
            this.f2014a.mo2803a("Authentication succeeded");
            iVar.mo2478a(C0496b.SUCCESS);
            cVar.mo2538b(oVar, iVar.mo2483b(), eVar);
            return false;
        } else if (i == 3) {
            return false;
        } else {
            iVar.mo2478a(C0496b.UNCHALLENGED);
            return false;
        }
    }
}

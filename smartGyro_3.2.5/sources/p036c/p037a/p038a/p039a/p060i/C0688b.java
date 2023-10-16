package p036c.p037a.p038a.p039a.p060i;

import p036c.p037a.p038a.p039a.C0514b;
import p036c.p037a.p038a.p039a.C0883t;

/* renamed from: c.a.a.a.i.b */
public class C0688b implements C0514b {

    /* renamed from: a */
    public static final C0688b f2039a = new C0688b();

    /* renamed from: a */
    private boolean m2487a(C0883t tVar) {
        int statusCode = tVar.getStatusLine().getStatusCode();
        return (statusCode < 200 || statusCode == 204 || statusCode == 304 || statusCode == 205) ? false : true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0042, code lost:
        if (java.lang.Integer.parseInt(r0[0].getValue()) < 0) goto L_0x0044;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo2505a(p036c.p037a.p038a.p039a.C0883t r6, p036c.p037a.p038a.p039a.p072n.C0855e r7) {
        /*
            r5 = this;
            java.lang.String r0 = "HTTP response"
            p036c.p037a.p038a.p039a.p074p.C0870a.m3042a(r6, (java.lang.String) r0)
            java.lang.String r0 = "HTTP context"
            p036c.p037a.p038a.p039a.p074p.C0870a.m3042a(r7, (java.lang.String) r0)
            c.a.a.a.G r7 = r6.getStatusLine()
            c.a.a.a.D r7 = r7.getProtocolVersion()
            java.lang.String r0 = "Transfer-Encoding"
            c.a.a.a.e r0 = r6.getFirstHeader(r0)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0029
            java.lang.String r0 = r0.getValue()
            java.lang.String r3 = "chunked"
            boolean r0 = r3.equalsIgnoreCase(r0)
            if (r0 != 0) goto L_0x0045
            return r2
        L_0x0029:
            boolean r0 = r5.m2487a(r6)
            if (r0 == 0) goto L_0x0045
            java.lang.String r0 = "Content-Length"
            c.a.a.a.e[] r0 = r6.getHeaders(r0)
            int r3 = r0.length
            if (r3 != r1) goto L_0x0044
            r0 = r0[r2]
            java.lang.String r0 = r0.getValue()     // Catch:{ NumberFormatException -> 0x0044 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x0044 }
            if (r0 >= 0) goto L_0x0045
        L_0x0044:
            return r2
        L_0x0045:
            java.lang.String r0 = "Connection"
            c.a.a.a.e[] r0 = r6.getHeaders(r0)
            int r3 = r0.length
            if (r3 != 0) goto L_0x0054
            java.lang.String r0 = "Proxy-Connection"
            c.a.a.a.e[] r0 = r6.getHeaders(r0)
        L_0x0054:
            int r6 = r0.length
            if (r6 == 0) goto L_0x0084
            c.a.a.a.k.p r6 = new c.a.a.a.k.p     // Catch:{ B -> 0x0083 }
            c.a.a.a.k.e r3 = new c.a.a.a.k.e     // Catch:{ B -> 0x0083 }
            r4 = 0
            r3.<init>(r0, r4)     // Catch:{ B -> 0x0083 }
            r6.<init>(r3)     // Catch:{ B -> 0x0083 }
            r0 = 0
        L_0x0063:
            boolean r3 = r6.hasNext()     // Catch:{ B -> 0x0083 }
            if (r3 == 0) goto L_0x0080
            java.lang.String r3 = r6.nextToken()     // Catch:{ B -> 0x0083 }
            java.lang.String r4 = "Close"
            boolean r4 = r4.equalsIgnoreCase(r3)     // Catch:{ B -> 0x0083 }
            if (r4 == 0) goto L_0x0076
            return r2
        L_0x0076:
            java.lang.String r4 = "Keep-Alive"
            boolean r3 = r4.equalsIgnoreCase(r3)     // Catch:{ B -> 0x0083 }
            if (r3 == 0) goto L_0x0063
            r0 = 1
            goto L_0x0063
        L_0x0080:
            if (r0 == 0) goto L_0x0084
            return r1
        L_0x0083:
            return r2
        L_0x0084:
            c.a.a.a.w r6 = p036c.p037a.p038a.p039a.C0886w.f2445e
            boolean r6 = r7.mo2445c(r6)
            r6 = r6 ^ r1
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p060i.C0688b.mo2505a(c.a.a.a.t, c.a.a.a.n.e):boolean");
    }
}

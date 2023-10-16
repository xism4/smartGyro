package p036c.p037a.p038a.p039a.p072n;

import java.io.IOException;
import p036c.p037a.p038a.p039a.C0487C;
import p036c.p037a.p038a.p039a.C0488D;
import p036c.p037a.p038a.p039a.C0669i;
import p036c.p037a.p038a.p039a.C0848m;
import p036c.p037a.p038a.p039a.C0850n;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.C0883t;
import p036c.p037a.p038a.p039a.C0886w;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.n.h */
public class C0858h {

    /* renamed from: a */
    private final int f2424a;

    public C0858h() {
        this(3000);
    }

    public C0858h(int i) {
        C0870a.m3046b(i, "Wait for continue time");
        this.f2424a = i;
    }

    /* renamed from: a */
    private static void m3019a(C0669i iVar) {
        try {
            iVar.close();
        } catch (IOException unused) {
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0883t mo3264a(C0881r rVar, C0669i iVar, C0855e eVar) {
        C0870a.m3042a(rVar, "HTTP request");
        C0870a.m3042a(iVar, "Client connection");
        C0870a.m3042a(eVar, "HTTP context");
        C0883t tVar = null;
        int i = 0;
        while (true) {
            if (tVar != null && i >= 200) {
                return tVar;
            }
            tVar = iVar.receiveResponseHeader();
            if (mo3267a(rVar, tVar)) {
                iVar.mo2815a(tVar);
            }
            i = tVar.getStatusLine().getStatusCode();
        }
    }

    /* renamed from: a */
    public void mo3265a(C0881r rVar, C0857g gVar, C0855e eVar) {
        C0870a.m3042a(rVar, "HTTP request");
        C0870a.m3042a(gVar, "HTTP processor");
        C0870a.m3042a(eVar, "HTTP context");
        eVar.setAttribute("http.request", rVar);
        gVar.mo2352a(rVar, eVar);
    }

    /* renamed from: a */
    public void mo3266a(C0883t tVar, C0857g gVar, C0855e eVar) {
        C0870a.m3042a(tVar, "HTTP response");
        C0870a.m3042a(gVar, "HTTP processor");
        C0870a.m3042a(eVar, "HTTP context");
        eVar.setAttribute("http.response", tVar);
        gVar.mo2353a(tVar, eVar);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0012, code lost:
        r2 = r3.getStatusLine().getStatusCode();
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo3267a(p036c.p037a.p038a.p039a.C0881r r2, p036c.p037a.p038a.p039a.C0883t r3) {
        /*
            r1 = this;
            c.a.a.a.F r2 = r2.getRequestLine()
            java.lang.String r2 = r2.getMethod()
            java.lang.String r0 = "HEAD"
            boolean r2 = r0.equalsIgnoreCase(r2)
            r0 = 0
            if (r2 == 0) goto L_0x0012
            return r0
        L_0x0012:
            c.a.a.a.G r2 = r3.getStatusLine()
            int r2 = r2.getStatusCode()
            r3 = 200(0xc8, float:2.8E-43)
            if (r2 < r3) goto L_0x002b
            r3 = 204(0xcc, float:2.86E-43)
            if (r2 == r3) goto L_0x002b
            r3 = 304(0x130, float:4.26E-43)
            if (r2 == r3) goto L_0x002b
            r3 = 205(0xcd, float:2.87E-43)
            if (r2 == r3) goto L_0x002b
            r0 = 1
        L_0x002b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p072n.C0858h.mo3267a(c.a.a.a.r, c.a.a.a.t):boolean");
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public C0883t mo3268b(C0881r rVar, C0669i iVar, C0855e eVar) {
        C0870a.m3042a(rVar, "HTTP request");
        C0870a.m3042a(iVar, "Client connection");
        C0870a.m3042a(eVar, "HTTP context");
        eVar.setAttribute("http.connection", iVar);
        eVar.setAttribute("http.request_sent", Boolean.FALSE);
        iVar.mo2814a(rVar);
        C0883t tVar = null;
        if (rVar instanceof C0848m) {
            boolean z = true;
            C0488D protocolVersion = rVar.getRequestLine().getProtocolVersion();
            C0848m mVar = (C0848m) rVar;
            if (mVar.expectContinue() && !protocolVersion.mo2445c(C0886w.f2445e)) {
                iVar.flush();
                if (iVar.isResponseAvailable(this.f2424a)) {
                    C0883t receiveResponseHeader = iVar.receiveResponseHeader();
                    if (mo3267a(rVar, receiveResponseHeader)) {
                        iVar.mo2815a(receiveResponseHeader);
                    }
                    int statusCode = receiveResponseHeader.getStatusLine().getStatusCode();
                    if (statusCode >= 200) {
                        z = false;
                        tVar = receiveResponseHeader;
                    } else if (statusCode != 100) {
                        throw new C0487C("Unexpected response: " + receiveResponseHeader.getStatusLine());
                    }
                }
            }
            if (z) {
                iVar.mo2813a(mVar);
            }
        }
        iVar.flush();
        eVar.setAttribute("http.request_sent", Boolean.TRUE);
        return tVar;
    }

    /* renamed from: c */
    public C0883t mo3269c(C0881r rVar, C0669i iVar, C0855e eVar) {
        C0870a.m3042a(rVar, "HTTP request");
        C0870a.m3042a(iVar, "Client connection");
        C0870a.m3042a(eVar, "HTTP context");
        try {
            C0883t b = mo3268b(rVar, iVar, eVar);
            return b == null ? mo3264a(rVar, iVar, eVar) : b;
        } catch (IOException e) {
            m3019a(iVar);
            throw e;
        } catch (C0850n e2) {
            m3019a(iVar);
            throw e2;
        } catch (RuntimeException e3) {
            m3019a(iVar);
            throw e3;
        }
    }
}

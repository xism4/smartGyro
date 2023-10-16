package p036c.p037a.p038a.p039a.p060i.p063c;

import java.net.InetAddress;
import java.net.Socket;
import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.p050e.C0607d;
import p036c.p037a.p038a.p039a.p050e.C0629j;
import p036c.p037a.p038a.p039a.p050e.C0636q;
import p036c.p037a.p038a.p039a.p050e.p053c.C0599e;
import p036c.p037a.p038a.p039a.p050e.p053c.C0600f;
import p036c.p037a.p038a.p039a.p050e.p053c.C0603i;
import p036c.p037a.p038a.p039a.p059h.C0668b;
import p036c.p037a.p038a.p039a.p070l.C0842e;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0871b;

@Deprecated
/* renamed from: c.a.a.a.i.c.h */
public class C0734h implements C0607d {

    /* renamed from: a */
    public C0668b f2206a = new C0668b(C0734h.class);

    /* renamed from: b */
    protected final C0603i f2207b;

    /* renamed from: c */
    protected final C0629j f2208c;

    public C0734h(C0603i iVar) {
        C0870a.m3042a(iVar, "Scheme registry");
        this.f2207b = iVar;
        this.f2208c = new C0744r();
    }

    /* renamed from: a */
    private C0603i m2698a(C0855e eVar) {
        C0603i iVar = (C0603i) eVar.getAttribute("http.scheme-registry");
        return iVar == null ? this.f2207b : iVar;
    }

    /* renamed from: a */
    public void mo2679a(C0636q qVar, C0867o oVar, C0855e eVar, C0844g gVar) {
        C0870a.m3042a(qVar, "Connection");
        C0870a.m3042a(oVar, "Target host");
        C0870a.m3042a(gVar, "Parameters");
        C0871b.m3050a(qVar.isOpen(), "Connection must be open");
        C0599e b = m2698a(eVar).mo2676b(oVar.mo3274d());
        C0871b.m3050a(b.mo2667c() instanceof C0600f, "Socket factory must implement SchemeLayeredSocketFactory");
        C0600f fVar = (C0600f) b.mo2667c();
        Socket a = fVar.mo2672a(qVar.getSocket(), oVar.mo3271b(), b.mo2665a(oVar.mo3272c()), gVar);
        mo3002a(a, eVar, gVar);
        qVar.mo2728a(a, oVar, fVar.isSecure(a), gVar);
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00cf A[SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo2680a(p036c.p037a.p038a.p039a.p050e.C0636q r17, p036c.p037a.p038a.p039a.C0867o r18, java.net.InetAddress r19, p036c.p037a.p038a.p039a.p072n.C0855e r20, p036c.p037a.p038a.p039a.p070l.C0844g r21) {
        /*
            r16 = this;
            r1 = r16
            r2 = r17
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r21
            java.lang.String r0 = "Connection"
            p036c.p037a.p038a.p039a.p074p.C0870a.m3042a(r2, (java.lang.String) r0)
            java.lang.String r0 = "Target host"
            p036c.p037a.p038a.p039a.p074p.C0870a.m3042a(r3, (java.lang.String) r0)
            java.lang.String r0 = "HTTP parameters"
            p036c.p037a.p038a.p039a.p074p.C0870a.m3042a(r6, (java.lang.String) r0)
            boolean r0 = r17.isOpen()
            r7 = 1
            r8 = 0
            if (r0 != 0) goto L_0x0025
            r0 = 1
            goto L_0x0026
        L_0x0025:
            r0 = 0
        L_0x0026:
            java.lang.String r9 = "Connection must not be open"
            p036c.p037a.p038a.p039a.p074p.C0871b.m3050a((boolean) r0, (java.lang.String) r9)
            c.a.a.a.e.c.i r0 = r1.m2698a((p036c.p037a.p038a.p039a.p072n.C0855e) r5)
            java.lang.String r9 = r18.mo3274d()
            c.a.a.a.e.c.e r0 = r0.mo2676b(r9)
            c.a.a.a.e.c.j r9 = r0.mo2667c()
            java.lang.String r10 = r18.mo3271b()
            java.net.InetAddress[] r10 = r1.mo3003a((java.lang.String) r10)
            int r11 = r18.mo3272c()
            int r11 = r0.mo2665a(r11)
            r12 = 0
        L_0x004c:
            int r0 = r10.length
            if (r12 >= r0) goto L_0x00d8
            r0 = r10[r12]
            int r13 = r10.length
            int r13 = r13 - r7
            if (r12 != r13) goto L_0x0057
            r13 = 1
            goto L_0x0058
        L_0x0057:
            r13 = 0
        L_0x0058:
            java.net.Socket r14 = r9.mo2660a(r6)
            r2.mo2727a(r14, r3)
            c.a.a.a.e.m r15 = new c.a.a.a.e.m
            r15.<init>(r3, r0, r11)
            r0 = 0
            if (r4 == 0) goto L_0x006c
            java.net.InetSocketAddress r0 = new java.net.InetSocketAddress
            r0.<init>(r4, r8)
        L_0x006c:
            c.a.a.a.h.b r7 = r1.f2206a
            boolean r7 = r7.mo2805a()
            if (r7 == 0) goto L_0x008a
            c.a.a.a.h.b r7 = r1.f2206a
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r4 = "Connecting to "
            r8.append(r4)
            r8.append(r15)
            java.lang.String r4 = r8.toString()
            r7.mo2803a(r4)
        L_0x008a:
            java.net.Socket r0 = r9.mo2662a(r14, r15, r0, r6)     // Catch:{ ConnectException -> 0x00a4, f -> 0x009f }
            if (r14 == r0) goto L_0x0094
            r2.mo2727a(r0, r3)     // Catch:{ ConnectException -> 0x00a4, f -> 0x009f }
            r14 = r0
        L_0x0094:
            r1.mo3002a(r14, r5, r6)     // Catch:{ ConnectException -> 0x00a4, f -> 0x009f }
            boolean r0 = r9.isSecure(r14)     // Catch:{ ConnectException -> 0x00a4, f -> 0x009f }
            r2.mo2729b(r0, r6)     // Catch:{ ConnectException -> 0x00a4, f -> 0x009f }
            return
        L_0x009f:
            r0 = move-exception
            if (r13 != 0) goto L_0x00a3
            goto L_0x00a7
        L_0x00a3:
            throw r0
        L_0x00a4:
            r0 = move-exception
            if (r13 != 0) goto L_0x00d7
        L_0x00a7:
            c.a.a.a.h.b r0 = r1.f2206a
            boolean r0 = r0.mo2805a()
            if (r0 == 0) goto L_0x00cf
            c.a.a.a.h.b r0 = r1.f2206a
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r7 = "Connect to "
            r4.append(r7)
            r4.append(r15)
            java.lang.String r7 = " timed out. "
            r4.append(r7)
            java.lang.String r7 = "Connection will be retried using another IP address"
            r4.append(r7)
            java.lang.String r4 = r4.toString()
            r0.mo2803a(r4)
        L_0x00cf:
            int r12 = r12 + 1
            r4 = r19
            r7 = 1
            r8 = 0
            goto L_0x004c
        L_0x00d7:
            throw r0
        L_0x00d8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p060i.p063c.C0734h.mo2680a(c.a.a.a.e.q, c.a.a.a.o, java.net.InetAddress, c.a.a.a.n.e, c.a.a.a.l.g):void");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo3002a(Socket socket, C0855e eVar, C0844g gVar) {
        socket.setTcpNoDelay(C0842e.m2985e(gVar));
        socket.setSoTimeout(C0842e.m2984d(gVar));
        int b = C0842e.m2980b(gVar);
        if (b >= 0) {
            socket.setSoLinger(b > 0, b);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public InetAddress[] mo3003a(String str) {
        return this.f2208c.resolve(str);
    }

    public C0636q createConnection() {
        return new C0733g();
    }
}

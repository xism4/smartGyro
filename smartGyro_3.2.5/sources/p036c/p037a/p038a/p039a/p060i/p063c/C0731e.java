package p036c.p037a.p038a.p039a.p060i.p063c;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import p036c.p037a.p038a.p039a.C0669i;
import p036c.p037a.p038a.p039a.p050e.C0585b;
import p036c.p037a.p038a.p039a.p050e.C0607d;
import p036c.p037a.p038a.p039a.p050e.C0610e;
import p036c.p037a.p038a.p039a.p050e.C0634o;
import p036c.p037a.p038a.p039a.p050e.p052b.C0587b;
import p036c.p037a.p038a.p039a.p050e.p053c.C0603i;
import p036c.p037a.p038a.p039a.p059h.C0668b;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0871b;

@Deprecated
/* renamed from: c.a.a.a.i.c.e */
public class C0731e implements C0585b {

    /* renamed from: a */
    private static final AtomicLong f2191a = new AtomicLong();

    /* renamed from: b */
    public C0668b f2192b = new C0668b(C0731e.class);

    /* renamed from: c */
    private final C0603i f2193c;

    /* renamed from: d */
    private final C0607d f2194d;

    /* renamed from: e */
    private C0738l f2195e;

    /* renamed from: f */
    private C0742p f2196f;

    /* renamed from: g */
    private volatile boolean f2197g;

    public C0731e(C0603i iVar) {
        C0870a.m3042a(iVar, "Scheme registry");
        this.f2193c = iVar;
        this.f2194d = mo2997a(iVar);
    }

    /* renamed from: a */
    private void m2685a() {
        C0871b.m3050a(!this.f2197g, "Connection manager has been shut down");
    }

    /* renamed from: a */
    private void m2686a(C0669i iVar) {
        try {
            iVar.shutdown();
        } catch (IOException e) {
            if (this.f2192b.mo2805a()) {
                this.f2192b.mo2804a("I/O exception shutting down connection", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0607d mo2997a(C0603i iVar) {
        return new C0734h(iVar);
    }

    /* renamed from: a */
    public final C0610e mo2625a(C0587b bVar, Object obj) {
        return new C0730d(this, bVar, obj);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00bc, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo2626a(p036c.p037a.p038a.p039a.p050e.C0634o r5, long r6, java.util.concurrent.TimeUnit r8) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof p036c.p037a.p038a.p039a.p060i.p063c.C0742p
            java.lang.String r1 = "Connection class mismatch, connection not obtained from this manager"
            p036c.p037a.p038a.p039a.p074p.C0870a.m3044a((boolean) r0, (java.lang.String) r1)
            r0 = r5
            c.a.a.a.i.c.p r0 = (p036c.p037a.p038a.p039a.p060i.p063c.C0742p) r0
            monitor-enter(r0)
            c.a.a.a.h.b r1 = r4.f2192b     // Catch:{ all -> 0x00d1 }
            boolean r1 = r1.mo2805a()     // Catch:{ all -> 0x00d1 }
            if (r1 == 0) goto L_0x0029
            c.a.a.a.h.b r1 = r4.f2192b     // Catch:{ all -> 0x00d1 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d1 }
            r2.<init>()     // Catch:{ all -> 0x00d1 }
            java.lang.String r3 = "Releasing connection "
            r2.append(r3)     // Catch:{ all -> 0x00d1 }
            r2.append(r5)     // Catch:{ all -> 0x00d1 }
            java.lang.String r5 = r2.toString()     // Catch:{ all -> 0x00d1 }
            r1.mo2803a(r5)     // Catch:{ all -> 0x00d1 }
        L_0x0029:
            c.a.a.a.i.c.l r5 = r0.mo3026c()     // Catch:{ all -> 0x00d1 }
            if (r5 != 0) goto L_0x0031
            monitor-exit(r0)     // Catch:{ all -> 0x00d1 }
            return
        L_0x0031:
            c.a.a.a.e.b r5 = r0.mo3025b()     // Catch:{ all -> 0x00d1 }
            if (r5 != r4) goto L_0x0039
            r5 = 1
            goto L_0x003a
        L_0x0039:
            r5 = 0
        L_0x003a:
            java.lang.String r1 = "Connection not obtained from this manager"
            p036c.p037a.p038a.p039a.p074p.C0871b.m3050a((boolean) r5, (java.lang.String) r1)     // Catch:{ all -> 0x00d1 }
            monitor-enter(r4)     // Catch:{ all -> 0x00d1 }
            boolean r5 = r4.f2197g     // Catch:{ all -> 0x00ce }
            if (r5 == 0) goto L_0x004a
            r4.m2686a((p036c.p037a.p038a.p039a.C0669i) r0)     // Catch:{ all -> 0x00ce }
            monitor-exit(r4)     // Catch:{ all -> 0x00ce }
            monitor-exit(r0)     // Catch:{ all -> 0x00d1 }
            return
        L_0x004a:
            r5 = 0
            boolean r1 = r0.isOpen()     // Catch:{ all -> 0x00bd }
            if (r1 == 0) goto L_0x005a
            boolean r1 = r0.mo3027d()     // Catch:{ all -> 0x00bd }
            if (r1 != 0) goto L_0x005a
            r4.m2686a((p036c.p037a.p038a.p039a.C0669i) r0)     // Catch:{ all -> 0x00bd }
        L_0x005a:
            boolean r1 = r0.mo3027d()     // Catch:{ all -> 0x00bd }
            if (r1 == 0) goto L_0x00ab
            c.a.a.a.i.c.l r1 = r4.f2195e     // Catch:{ all -> 0x00bd }
            if (r8 == 0) goto L_0x0066
            r2 = r8
            goto L_0x0068
        L_0x0066:
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x00bd }
        L_0x0068:
            r1.mo3240a(r6, r2)     // Catch:{ all -> 0x00bd }
            c.a.a.a.h.b r1 = r4.f2192b     // Catch:{ all -> 0x00bd }
            boolean r1 = r1.mo2805a()     // Catch:{ all -> 0x00bd }
            if (r1 == 0) goto L_0x00ab
            r1 = 0
            int r3 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r3 <= 0) goto L_0x0093
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bd }
            r1.<init>()     // Catch:{ all -> 0x00bd }
            java.lang.String r2 = "for "
            r1.append(r2)     // Catch:{ all -> 0x00bd }
            r1.append(r6)     // Catch:{ all -> 0x00bd }
            java.lang.String r6 = " "
            r1.append(r6)     // Catch:{ all -> 0x00bd }
            r1.append(r8)     // Catch:{ all -> 0x00bd }
            java.lang.String r6 = r1.toString()     // Catch:{ all -> 0x00bd }
            goto L_0x0095
        L_0x0093:
            java.lang.String r6 = "indefinitely"
        L_0x0095:
            c.a.a.a.h.b r7 = r4.f2192b     // Catch:{ all -> 0x00bd }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bd }
            r8.<init>()     // Catch:{ all -> 0x00bd }
            java.lang.String r1 = "Connection can be kept alive "
            r8.append(r1)     // Catch:{ all -> 0x00bd }
            r8.append(r6)     // Catch:{ all -> 0x00bd }
            java.lang.String r6 = r8.toString()     // Catch:{ all -> 0x00bd }
            r7.mo2803a(r6)     // Catch:{ all -> 0x00bd }
        L_0x00ab:
            r0.mo3024a()     // Catch:{ all -> 0x00ce }
            r4.f2196f = r5     // Catch:{ all -> 0x00ce }
            c.a.a.a.i.c.l r6 = r4.f2195e     // Catch:{ all -> 0x00ce }
            boolean r6 = r6.mo3011h()     // Catch:{ all -> 0x00ce }
            if (r6 == 0) goto L_0x00ba
            r4.f2195e = r5     // Catch:{ all -> 0x00ce }
        L_0x00ba:
            monitor-exit(r4)     // Catch:{ all -> 0x00ce }
            monitor-exit(r0)     // Catch:{ all -> 0x00d1 }
            return
        L_0x00bd:
            r6 = move-exception
            r0.mo3024a()     // Catch:{ all -> 0x00ce }
            r4.f2196f = r5     // Catch:{ all -> 0x00ce }
            c.a.a.a.i.c.l r7 = r4.f2195e     // Catch:{ all -> 0x00ce }
            boolean r7 = r7.mo3011h()     // Catch:{ all -> 0x00ce }
            if (r7 == 0) goto L_0x00cd
            r4.f2195e = r5     // Catch:{ all -> 0x00ce }
        L_0x00cd:
            throw r6     // Catch:{ all -> 0x00ce }
        L_0x00ce:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x00ce }
            throw r5     // Catch:{ all -> 0x00d1 }
        L_0x00d1:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00d1 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p060i.p063c.C0731e.mo2626a(c.a.a.a.e.o, long, java.util.concurrent.TimeUnit):void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public C0634o mo2998b(C0587b bVar, Object obj) {
        C0742p pVar;
        C0870a.m3042a(bVar, "Route");
        synchronized (this) {
            m2685a();
            if (this.f2192b.mo2805a()) {
                C0668b bVar2 = this.f2192b;
                bVar2.mo2803a("Get connection for route " + bVar);
            }
            C0871b.m3050a(this.f2196f == null, "Invalid use of BasicClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.");
            if (this.f2195e != null && !this.f2195e.mo3009f().equals(bVar)) {
                this.f2195e.mo3007d();
                this.f2195e = null;
            }
            if (this.f2195e == null) {
                this.f2195e = new C0738l(this.f2192b, Long.toString(f2191a.getAndIncrement()), bVar, this.f2194d.createConnection(), 0, TimeUnit.MILLISECONDS);
            }
            if (this.f2195e.mo3006a(System.currentTimeMillis())) {
                this.f2195e.mo3007d();
                this.f2195e.mo3010g().mo2649b();
            }
            this.f2196f = new C0742p(this, this.f2194d, this.f2195e);
            pVar = this.f2196f;
        }
        return pVar;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        try {
            shutdown();
        } finally {
            super.finalize();
        }
    }

    public C0603i getSchemeRegistry() {
        return this.f2193c;
    }

    public void shutdown() {
        synchronized (this) {
            this.f2197g = true;
            try {
                if (this.f2195e != null) {
                    this.f2195e.mo3007d();
                }
            } finally {
                this.f2195e = null;
                this.f2196f = null;
            }
        }
    }
}

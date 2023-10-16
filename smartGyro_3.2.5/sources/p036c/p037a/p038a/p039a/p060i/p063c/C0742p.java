package p036c.p037a.p038a.p039a.p060i.p063c;

import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import p036c.p037a.p038a.p039a.C0848m;
import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.C0883t;
import p036c.p037a.p038a.p039a.p050e.C0585b;
import p036c.p037a.p038a.p039a.p050e.C0607d;
import p036c.p037a.p038a.p039a.p050e.C0634o;
import p036c.p037a.p038a.p039a.p050e.C0636q;
import p036c.p037a.p038a.p039a.p050e.p052b.C0587b;
import p036c.p037a.p038a.p039a.p050e.p052b.C0593f;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0871b;

@Deprecated
/* renamed from: c.a.a.a.i.c.p */
class C0742p implements C0634o {

    /* renamed from: a */
    private final C0585b f2225a;

    /* renamed from: b */
    private final C0607d f2226b;

    /* renamed from: c */
    private volatile C0738l f2227c;

    /* renamed from: d */
    private volatile boolean f2228d = false;

    /* renamed from: e */
    private volatile long f2229e = Long.MAX_VALUE;

    C0742p(C0585b bVar, C0607d dVar, C0738l lVar) {
        C0870a.m3042a(bVar, "Connection manager");
        C0870a.m3042a(dVar, "Connection operator");
        C0870a.m3042a(lVar, "HTTP pool entry");
        this.f2225a = bVar;
        this.f2226b = dVar;
        this.f2227c = lVar;
    }

    /* renamed from: e */
    private C0636q m2717e() {
        C0738l lVar = this.f2227c;
        if (lVar != null) {
            return (C0636q) lVar.mo3239a();
        }
        throw new C0732f();
    }

    /* renamed from: f */
    private C0738l m2718f() {
        C0738l lVar = this.f2227c;
        if (lVar != null) {
            return lVar;
        }
        throw new C0732f();
    }

    /* renamed from: g */
    private C0636q m2719g() {
        C0738l lVar = this.f2227c;
        if (lVar == null) {
            return null;
        }
        return (C0636q) lVar.mo3239a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C0738l mo3024a() {
        C0738l lVar = this.f2227c;
        this.f2227c = null;
        return lVar;
    }

    /* renamed from: a */
    public void mo2719a(C0587b bVar, C0855e eVar, C0844g gVar) {
        C0636q qVar;
        C0870a.m3042a(bVar, "Route");
        C0870a.m3042a(gVar, "HTTP parameters");
        synchronized (this) {
            if (this.f2227c != null) {
                C0593f g = this.f2227c.mo3010g();
                C0871b.m3049a((Object) g, "Route tracker");
                C0871b.m3050a(!g.mo2648a(), "Connection already open");
                qVar = (C0636q) this.f2227c.mo3239a();
            } else {
                throw new C0732f();
            }
        }
        C0867o proxyHost = bVar.getProxyHost();
        this.f2226b.mo2680a(qVar, proxyHost != null ? proxyHost : bVar.getTargetHost(), bVar.getLocalAddress(), eVar, gVar);
        synchronized (this) {
            if (this.f2227c != null) {
                C0593f g2 = this.f2227c.mo3010g();
                if (proxyHost == null) {
                    g2.mo2647a(qVar.isSecure());
                } else {
                    g2.mo2646a(proxyHost, qVar.isSecure());
                }
            } else {
                throw new InterruptedIOException();
            }
        }
    }

    /* renamed from: a */
    public void mo2813a(C0848m mVar) {
        m2717e().mo2813a(mVar);
    }

    /* renamed from: a */
    public void mo2720a(C0855e eVar, C0844g gVar) {
        C0867o targetHost;
        C0636q qVar;
        C0870a.m3042a(gVar, "HTTP parameters");
        synchronized (this) {
            if (this.f2227c != null) {
                C0593f g = this.f2227c.mo3010g();
                C0871b.m3049a((Object) g, "Route tracker");
                C0871b.m3050a(g.mo2648a(), "Connection not open");
                C0871b.m3050a(g.isTunnelled(), "Protocol layering without a tunnel not supported");
                C0871b.m3050a(!g.isLayered(), "Multiple protocol layering not supported");
                targetHost = g.getTargetHost();
                qVar = (C0636q) this.f2227c.mo3239a();
            } else {
                throw new C0732f();
            }
        }
        this.f2226b.mo2679a(qVar, targetHost, eVar, gVar);
        synchronized (this) {
            if (this.f2227c != null) {
                this.f2227c.mo3010g().mo2650b(qVar.isSecure());
            } else {
                throw new InterruptedIOException();
            }
        }
    }

    /* renamed from: a */
    public void mo2814a(C0881r rVar) {
        m2717e().mo2814a(rVar);
    }

    /* renamed from: a */
    public void mo2815a(C0883t tVar) {
        m2717e().mo2815a(tVar);
    }

    /* renamed from: a */
    public void mo2721a(boolean z, C0844g gVar) {
        C0867o targetHost;
        C0636q qVar;
        C0870a.m3042a(gVar, "HTTP parameters");
        synchronized (this) {
            if (this.f2227c != null) {
                C0593f g = this.f2227c.mo3010g();
                C0871b.m3049a((Object) g, "Route tracker");
                C0871b.m3050a(g.mo2648a(), "Connection not open");
                C0871b.m3050a(!g.isTunnelled(), "Connection is already tunnelled");
                targetHost = g.getTargetHost();
                qVar = (C0636q) this.f2227c.mo3239a();
            } else {
                throw new C0732f();
            }
        }
        qVar.mo2728a((Socket) null, targetHost, z, gVar);
        synchronized (this) {
            if (this.f2227c != null) {
                this.f2227c.mo3010g().mo2652c(z);
            } else {
                throw new InterruptedIOException();
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:6|7|8|9|10|11|12) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0015 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void abortConnection() {
        /*
            r4 = this;
            monitor-enter(r4)
            c.a.a.a.i.c.l r0 = r4.f2227c     // Catch:{ all -> 0x0023 }
            if (r0 != 0) goto L_0x0007
            monitor-exit(r4)     // Catch:{ all -> 0x0023 }
            return
        L_0x0007:
            r0 = 0
            r4.f2228d = r0     // Catch:{ all -> 0x0023 }
            c.a.a.a.i.c.l r0 = r4.f2227c     // Catch:{ all -> 0x0023 }
            java.lang.Object r0 = r0.mo3239a()     // Catch:{ all -> 0x0023 }
            c.a.a.a.e.q r0 = (p036c.p037a.p038a.p039a.p050e.C0636q) r0     // Catch:{ all -> 0x0023 }
            r0.shutdown()     // Catch:{ IOException -> 0x0015 }
        L_0x0015:
            c.a.a.a.e.b r0 = r4.f2225a     // Catch:{ all -> 0x0023 }
            long r1 = r4.f2229e     // Catch:{ all -> 0x0023 }
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x0023 }
            r0.mo2626a(r4, r1, r3)     // Catch:{ all -> 0x0023 }
            r0 = 0
            r4.f2227c = r0     // Catch:{ all -> 0x0023 }
            monitor-exit(r4)     // Catch:{ all -> 0x0023 }
            return
        L_0x0023:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0023 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p060i.p063c.C0742p.abortConnection():void");
    }

    /* renamed from: b */
    public C0585b mo3025b() {
        return this.f2225a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public C0738l mo3026c() {
        return this.f2227c;
    }

    public void close() {
        C0738l lVar = this.f2227c;
        if (lVar != null) {
            lVar.mo3010g().mo2649b();
            ((C0636q) lVar.mo3239a()).close();
        }
    }

    /* renamed from: d */
    public boolean mo3027d() {
        return this.f2228d;
    }

    public void flush() {
        m2717e().flush();
    }

    public InetAddress getRemoteAddress() {
        return m2717e().getRemoteAddress();
    }

    public int getRemotePort() {
        return m2717e().getRemotePort();
    }

    public C0587b getRoute() {
        return m2718f().mo3008e();
    }

    public SSLSession getSSLSession() {
        Socket socket = m2717e().getSocket();
        if (socket instanceof SSLSocket) {
            return ((SSLSocket) socket).getSession();
        }
        return null;
    }

    public boolean isOpen() {
        C0636q g = m2719g();
        if (g != null) {
            return g.isOpen();
        }
        return false;
    }

    public boolean isResponseAvailable(int i) {
        return m2717e().isResponseAvailable(i);
    }

    public boolean isStale() {
        C0636q g = m2719g();
        if (g != null) {
            return g.isStale();
        }
        return true;
    }

    public void markReusable() {
        this.f2228d = true;
    }

    public C0883t receiveResponseHeader() {
        return m2717e().receiveResponseHeader();
    }

    public void releaseConnection() {
        synchronized (this) {
            if (this.f2227c != null) {
                this.f2225a.mo2626a(this, this.f2229e, TimeUnit.MILLISECONDS);
                this.f2227c = null;
            }
        }
    }

    public void setIdleDuration(long j, TimeUnit timeUnit) {
        this.f2229e = j > 0 ? timeUnit.toMillis(j) : -1;
    }

    public void setSocketTimeout(int i) {
        m2717e().setSocketTimeout(i);
    }

    public void setState(Object obj) {
        m2718f().mo3241a(obj);
    }

    public void shutdown() {
        C0738l lVar = this.f2227c;
        if (lVar != null) {
            lVar.mo3010g().mo2649b();
            ((C0636q) lVar.mo3239a()).shutdown();
        }
    }

    public void unmarkReusable() {
        this.f2228d = false;
    }
}

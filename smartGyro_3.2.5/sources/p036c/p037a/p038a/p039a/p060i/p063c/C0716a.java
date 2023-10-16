package p036c.p037a.p038a.p039a.p060i.p063c;

import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import p036c.p037a.p038a.p039a.C0848m;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.C0883t;
import p036c.p037a.p038a.p039a.p050e.C0585b;
import p036c.p037a.p038a.p039a.p050e.C0634o;
import p036c.p037a.p038a.p039a.p050e.C0636q;
import p036c.p037a.p038a.p039a.p072n.C0855e;

@Deprecated
/* renamed from: c.a.a.a.i.c.a */
public abstract class C0716a implements C0634o, C0855e {

    /* renamed from: a */
    private final C0585b f2130a;

    /* renamed from: b */
    private volatile C0636q f2131b;

    /* renamed from: c */
    private volatile boolean f2132c = false;

    /* renamed from: d */
    private volatile boolean f2133d = false;

    /* renamed from: e */
    private volatile long f2134e = Long.MAX_VALUE;

    protected C0716a(C0585b bVar, C0636q qVar) {
        this.f2130a = bVar;
        this.f2131b = qVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public synchronized void mo2933a() {
        this.f2131b = null;
        this.f2134e = Long.MAX_VALUE;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo2934a(C0636q qVar) {
        if (mo2938e() || qVar == null) {
            throw new C0732f();
        }
    }

    /* renamed from: a */
    public void mo2813a(C0848m mVar) {
        C0636q c = mo2936c();
        mo2934a(c);
        unmarkReusable();
        c.mo2813a(mVar);
    }

    /* renamed from: a */
    public void mo2814a(C0881r rVar) {
        C0636q c = mo2936c();
        mo2934a(c);
        unmarkReusable();
        c.mo2814a(rVar);
    }

    /* renamed from: a */
    public void mo2815a(C0883t tVar) {
        C0636q c = mo2936c();
        mo2934a(c);
        unmarkReusable();
        c.mo2815a(tVar);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(9:6|7|8|9|10|11|12|13|14) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0010 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void abortConnection() {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = r4.f2133d     // Catch:{ all -> 0x001b }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r4)
            return
        L_0x0007:
            r0 = 1
            r4.f2133d = r0     // Catch:{ all -> 0x001b }
            r4.unmarkReusable()     // Catch:{ all -> 0x001b }
            r4.shutdown()     // Catch:{ IOException -> 0x0010 }
        L_0x0010:
            c.a.a.a.e.b r0 = r4.f2130a     // Catch:{ all -> 0x001b }
            long r1 = r4.f2134e     // Catch:{ all -> 0x001b }
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x001b }
            r0.mo2626a(r4, r1, r3)     // Catch:{ all -> 0x001b }
            monitor-exit(r4)
            return
        L_0x001b:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p060i.p063c.C0716a.abortConnection():void");
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public C0585b mo2935b() {
        return this.f2130a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public C0636q mo2936c() {
        return this.f2131b;
    }

    /* renamed from: d */
    public boolean mo2937d() {
        return this.f2132c;
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public boolean mo2938e() {
        return this.f2133d;
    }

    public void flush() {
        C0636q c = mo2936c();
        mo2934a(c);
        c.flush();
    }

    public Object getAttribute(String str) {
        C0636q c = mo2936c();
        mo2934a(c);
        if (c instanceof C0855e) {
            return ((C0855e) c).getAttribute(str);
        }
        return null;
    }

    public InetAddress getRemoteAddress() {
        C0636q c = mo2936c();
        mo2934a(c);
        return c.getRemoteAddress();
    }

    public int getRemotePort() {
        C0636q c = mo2936c();
        mo2934a(c);
        return c.getRemotePort();
    }

    public SSLSession getSSLSession() {
        C0636q c = mo2936c();
        mo2934a(c);
        if (!isOpen()) {
            return null;
        }
        Socket socket = c.getSocket();
        if (socket instanceof SSLSocket) {
            return ((SSLSocket) socket).getSession();
        }
        return null;
    }

    public boolean isOpen() {
        C0636q c = mo2936c();
        if (c == null) {
            return false;
        }
        return c.isOpen();
    }

    public boolean isResponseAvailable(int i) {
        C0636q c = mo2936c();
        mo2934a(c);
        return c.isResponseAvailable(i);
    }

    public boolean isStale() {
        C0636q c;
        if (!mo2938e() && (c = mo2936c()) != null) {
            return c.isStale();
        }
        return true;
    }

    public void markReusable() {
        this.f2132c = true;
    }

    public C0883t receiveResponseHeader() {
        C0636q c = mo2936c();
        mo2934a(c);
        unmarkReusable();
        return c.receiveResponseHeader();
    }

    public synchronized void releaseConnection() {
        if (!this.f2133d) {
            this.f2133d = true;
            this.f2130a.mo2626a(this, this.f2134e, TimeUnit.MILLISECONDS);
        }
    }

    public void setAttribute(String str, Object obj) {
        C0636q c = mo2936c();
        mo2934a(c);
        if (c instanceof C0855e) {
            ((C0855e) c).setAttribute(str, obj);
        }
    }

    public void setIdleDuration(long j, TimeUnit timeUnit) {
        this.f2134e = j > 0 ? timeUnit.toMillis(j) : -1;
    }

    public void setSocketTimeout(int i) {
        C0636q c = mo2936c();
        mo2934a(c);
        c.setSocketTimeout(i);
    }

    public void unmarkReusable() {
        this.f2132c = false;
    }
}

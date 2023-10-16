package p026b.p033c.p034a.p035a;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicBoolean;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0883t;
import p036c.p037a.p038a.p039a.p041b.C0562k;
import p036c.p037a.p038a.p039a.p041b.p044c.C0525e;
import p036c.p037a.p038a.p039a.p041b.p044c.C0532l;
import p036c.p037a.p038a.p039a.p060i.p062b.C0689a;
import p036c.p037a.p038a.p039a.p072n.C0855e;

/* renamed from: b.c.a.a.e */
public class C0467e implements Runnable {

    /* renamed from: a */
    private final C0689a f1703a;

    /* renamed from: b */
    private final C0855e f1704b;

    /* renamed from: c */
    private final C0532l f1705c;

    /* renamed from: d */
    private final C0482s f1706d;

    /* renamed from: e */
    private final AtomicBoolean f1707e = new AtomicBoolean();

    /* renamed from: f */
    private int f1708f;

    /* renamed from: g */
    private boolean f1709g;

    /* renamed from: h */
    private volatile boolean f1710h;

    /* renamed from: i */
    private boolean f1711i;

    public C0467e(C0689a aVar, C0855e eVar, C0532l lVar, C0482s sVar) {
        C0484u.m2110a(aVar, "client");
        this.f1703a = aVar;
        C0484u.m2110a(eVar, "context");
        this.f1704b = eVar;
        C0484u.m2110a(lVar, "request");
        this.f1705c = lVar;
        C0484u.m2110a(sVar, "responseHandler");
        this.f1706d = sVar;
    }

    /* renamed from: c */
    private void m2019c() {
        if (!mo2371a()) {
            if (this.f1705c.getURI().getScheme() != null) {
                C0482s sVar = this.f1706d;
                if (sVar instanceof C0478o) {
                    ((C0478o) sVar).mo2430a(this.f1705c);
                }
                C0525e a = this.f1703a.mo2895a(this.f1705c, this.f1704b);
                if (!mo2371a()) {
                    C0482s sVar2 = this.f1706d;
                    sVar2.mo2395b(sVar2, a);
                    if (!mo2371a()) {
                        this.f1706d.mo2383a((C0883t) a);
                        if (!mo2371a()) {
                            C0482s sVar3 = this.f1706d;
                            sVar3.mo2382a(sVar3, a);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            throw new MalformedURLException("No valid URI scheme was provided");
        }
    }

    /* renamed from: d */
    private void m2020d() {
        C0562k A = this.f1703a.mo2843A();
        IOException iOException = null;
        boolean z = true;
        while (z) {
            try {
                m2019c();
                return;
            } catch (UnknownHostException e) {
                iOException = new IOException("UnknownHostException exception: " + e.getMessage());
                if (this.f1708f > 0) {
                    int i = this.f1708f + 1;
                    this.f1708f = i;
                    if (A.mo2437a(e, i, this.f1704b)) {
                        z = true;
                    }
                }
                z = false;
            } catch (NullPointerException e2) {
                iOException = new IOException("NPE in HttpClient: " + e2.getMessage());
                int i2 = this.f1708f + 1;
                this.f1708f = i2;
                z = A.mo2437a(iOException, i2, this.f1704b);
            } catch (IOException e3) {
                try {
                    if (!mo2371a()) {
                        int i3 = this.f1708f + 1;
                        this.f1708f = i3;
                        boolean a = A.mo2437a(e3, i3, this.f1704b);
                        iOException = e3;
                        z = a;
                    } else {
                        return;
                    }
                } catch (Exception e4) {
                    C0465d.f1690a.mo2420b("AsyncHttpRequest", "Unhandled exception origin cause", e4);
                    iOException = new IOException("Unhandled exception: " + e4.getMessage());
                }
            }
        }
        throw iOException;
        if (z) {
            this.f1706d.mo2377a(this.f1708f);
        }
    }

    /* renamed from: e */
    private synchronized void m2021e() {
        if (!this.f1710h && this.f1707e.get() && !this.f1709g) {
            this.f1709g = true;
            this.f1706d.mo2400e();
        }
    }

    /* renamed from: a */
    public void mo2370a(C0467e eVar) {
    }

    /* renamed from: a */
    public boolean mo2371a() {
        boolean z = this.f1707e.get();
        if (z) {
            m2021e();
        }
        return z;
    }

    /* renamed from: a */
    public boolean mo2372a(boolean z) {
        this.f1707e.set(true);
        this.f1705c.abort();
        return mo2371a();
    }

    /* renamed from: b */
    public void mo2373b(C0467e eVar) {
    }

    /* renamed from: b */
    public boolean mo2374b() {
        return mo2371a() || this.f1710h;
    }

    public void run() {
        if (!mo2371a()) {
            if (!this.f1711i) {
                this.f1711i = true;
                mo2373b(this);
            }
            if (!mo2371a()) {
                this.f1706d.mo2398c();
                if (!mo2371a()) {
                    try {
                        m2020d();
                    } catch (IOException e) {
                        if (!mo2371a()) {
                            this.f1706d.mo2379a(0, (C0576e[]) null, (byte[]) null, e);
                        } else {
                            C0465d.f1690a.mo2420b("AsyncHttpRequest", "makeRequestWithRetries returned error", e);
                        }
                    }
                    if (!mo2371a()) {
                        this.f1706d.mo2399d();
                        if (!mo2371a()) {
                            mo2370a(this);
                            this.f1710h = true;
                        }
                    }
                }
            }
        }
    }
}

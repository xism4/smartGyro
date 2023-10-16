package p036c.p037a.p038a.p039a.p060i.p062b;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;
import p036c.p037a.p038a.p039a.C0485A;
import p036c.p037a.p038a.p039a.C0487C;
import p036c.p037a.p038a.p039a.C0514b;
import p036c.p037a.p038a.p039a.C0837l;
import p036c.p037a.p038a.p039a.C0848m;
import p036c.p037a.p038a.p039a.C0850n;
import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.C0883t;
import p036c.p037a.p038a.p039a.p040a.C0496b;
import p036c.p037a.p038a.p039a.p040a.C0497c;
import p036c.p037a.p038a.p039a.p040a.C0503i;
import p036c.p037a.p038a.p039a.p040a.C0513s;
import p036c.p037a.p038a.p039a.p041b.C0518b;
import p036c.p037a.p038a.p039a.p041b.C0520c;
import p036c.p037a.p038a.p039a.p041b.C0562k;
import p036c.p037a.p038a.p039a.p041b.C0564m;
import p036c.p037a.p038a.p039a.p041b.C0565n;
import p036c.p037a.p038a.p039a.p041b.C0566o;
import p036c.p037a.p038a.p039a.p041b.C0567p;
import p036c.p037a.p038a.p039a.p041b.C0568q;
import p036c.p037a.p038a.p039a.p041b.C0569r;
import p036c.p037a.p038a.p039a.p041b.p044c.C0521a;
import p036c.p037a.p038a.p039a.p041b.p044c.C0532l;
import p036c.p037a.p038a.p039a.p041b.p045d.C0538b;
import p036c.p037a.p038a.p039a.p041b.p047f.C0556e;
import p036c.p037a.p038a.p039a.p050e.C0577a;
import p036c.p037a.p038a.p039a.p050e.C0585b;
import p036c.p037a.p038a.p039a.p050e.C0610e;
import p036c.p037a.p038a.p039a.p050e.C0626g;
import p036c.p037a.p038a.p039a.p050e.C0628i;
import p036c.p037a.p038a.p039a.p050e.C0634o;
import p036c.p037a.p038a.p039a.p050e.p052b.C0586a;
import p036c.p037a.p038a.p039a.p050e.p052b.C0587b;
import p036c.p037a.p038a.p039a.p050e.p052b.C0589d;
import p036c.p037a.p038a.p039a.p058g.C0659c;
import p036c.p037a.p038a.p039a.p059h.C0668b;
import p036c.p037a.p038a.p039a.p060i.p061a.C0672b;
import p036c.p037a.p038a.p039a.p060i.p063c.C0732f;
import p036c.p037a.p038a.p039a.p069k.C0820h;
import p036c.p037a.p038a.p039a.p070l.C0842e;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p070l.C0846i;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p072n.C0857g;
import p036c.p037a.p038a.p039a.p072n.C0858h;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0876g;

@Deprecated
/* renamed from: c.a.a.a.i.b.p */
public class C0704p implements C0568q {

    /* renamed from: a */
    public C0668b f2088a;

    /* renamed from: b */
    protected final C0585b f2089b;

    /* renamed from: c */
    protected final C0589d f2090c;

    /* renamed from: d */
    protected final C0514b f2091d;

    /* renamed from: e */
    protected final C0626g f2092e;

    /* renamed from: f */
    protected final C0858h f2093f;

    /* renamed from: g */
    protected final C0857g f2094g;

    /* renamed from: h */
    protected final C0562k f2095h;
    @Deprecated

    /* renamed from: i */
    protected final C0566o f2096i;

    /* renamed from: j */
    protected final C0567p f2097j;
    @Deprecated

    /* renamed from: k */
    protected final C0518b f2098k;

    /* renamed from: l */
    protected final C0520c f2099l;
    @Deprecated

    /* renamed from: m */
    protected final C0518b f2100m;

    /* renamed from: n */
    protected final C0520c f2101n;

    /* renamed from: o */
    protected final C0569r f2102o;

    /* renamed from: p */
    protected final C0844g f2103p;

    /* renamed from: q */
    protected C0634o f2104q;

    /* renamed from: r */
    protected final C0503i f2105r;

    /* renamed from: s */
    protected final C0503i f2106s;

    /* renamed from: t */
    private final C0708s f2107t;

    /* renamed from: u */
    private int f2108u;

    /* renamed from: v */
    private int f2109v;

    /* renamed from: w */
    private final int f2110w;

    /* renamed from: x */
    private C0867o f2111x;

    public C0704p(C0668b bVar, C0858h hVar, C0585b bVar2, C0514b bVar3, C0626g gVar, C0589d dVar, C0857g gVar2, C0562k kVar, C0567p pVar, C0520c cVar, C0520c cVar2, C0569r rVar, C0844g gVar3) {
        C0870a.m3042a(bVar, "Log");
        C0870a.m3042a(hVar, "Request executor");
        C0870a.m3042a(bVar2, "Client connection manager");
        C0870a.m3042a(bVar3, "Connection reuse strategy");
        C0870a.m3042a(gVar, "Connection keep alive strategy");
        C0870a.m3042a(dVar, "Route planner");
        C0870a.m3042a(gVar2, "HTTP protocol processor");
        C0870a.m3042a(kVar, "HTTP request retry handler");
        C0870a.m3042a(pVar, "Redirect strategy");
        C0870a.m3042a(cVar, "Target authentication strategy");
        C0870a.m3042a(cVar2, "Proxy authentication strategy");
        C0870a.m3042a(rVar, "User token handler");
        C0870a.m3042a(gVar3, "HTTP parameters");
        this.f2088a = bVar;
        this.f2107t = new C0708s(bVar);
        this.f2093f = hVar;
        this.f2089b = bVar2;
        this.f2091d = bVar3;
        this.f2092e = gVar;
        this.f2090c = dVar;
        this.f2094g = gVar2;
        this.f2095h = kVar;
        this.f2097j = pVar;
        this.f2099l = cVar;
        this.f2101n = cVar2;
        this.f2102o = rVar;
        this.f2103p = gVar3;
        if (pVar instanceof C0703o) {
            this.f2096i = ((C0703o) pVar).mo2903a();
        } else {
            this.f2096i = null;
        }
        if (cVar instanceof C0690b) {
            this.f2098k = ((C0690b) cVar).mo2886a();
        } else {
            this.f2098k = null;
        }
        if (cVar2 instanceof C0690b) {
            this.f2100m = ((C0690b) cVar2).mo2886a();
        } else {
            this.f2100m = null;
        }
        this.f2104q = null;
        this.f2108u = 0;
        this.f2109v = 0;
        this.f2105r = new C0503i();
        this.f2106s = new C0503i();
        this.f2110w = this.f2103p.getIntParameter("http.protocol.max-redirects", 100);
    }

    /* renamed from: a */
    private C0711v m2573a(C0881r rVar) {
        return rVar instanceof C0848m ? new C0706r((C0848m) rVar) : new C0711v(rVar);
    }

    /* renamed from: a */
    private void m2574a(C0712w wVar, C0855e eVar) {
        C0587b b = wVar.mo2929b();
        C0711v a = wVar.mo2928a();
        int i = 0;
        while (true) {
            eVar.setAttribute("http.request", a);
            i++;
            try {
                if (!this.f2104q.isOpen()) {
                    this.f2104q.mo2719a(b, eVar, this.f2103p);
                } else {
                    this.f2104q.setSocketTimeout(C0842e.m2984d(this.f2103p));
                }
                mo2911c(b, eVar);
                return;
            } catch (IOException e) {
                try {
                    this.f2104q.close();
                } catch (IOException unused) {
                }
                if (!this.f2095h.mo2437a(e, i, eVar)) {
                    throw e;
                } else if (this.f2088a.mo2810c()) {
                    C0668b bVar = this.f2088a;
                    bVar.mo2809c("I/O exception (" + e.getClass().getName() + ") caught when connecting to " + b + ": " + e.getMessage());
                    if (this.f2088a.mo2805a()) {
                        this.f2088a.mo2804a(e.getMessage(), e);
                    }
                    C0668b bVar2 = this.f2088a;
                    bVar2.mo2809c("Retrying connect to " + b);
                }
            }
        }
    }

    /* renamed from: b */
    private C0883t m2575b(C0712w wVar, C0855e eVar) {
        C0711v a = wVar.mo2928a();
        C0587b b = wVar.mo2929b();
        IOException e = null;
        while (true) {
            this.f2108u++;
            a.mo2926d();
            if (!a.mo2913e()) {
                this.f2088a.mo2803a("Cannot retry non-repeatable request");
                if (e != null) {
                    throw new C0564m("Cannot retry request with a non-repeatable request entity.  The cause lists the reason the original request failed.", e);
                }
                throw new C0564m("Cannot retry request with a non-repeatable request entity.");
            }
            try {
                if (!this.f2104q.isOpen()) {
                    if (!b.isTunnelled()) {
                        this.f2088a.mo2803a("Reopening the direct connection.");
                        this.f2104q.mo2719a(b, eVar, this.f2103p);
                    } else {
                        this.f2088a.mo2803a("Proxied connection. Need to start over.");
                        return null;
                    }
                }
                if (this.f2088a.mo2805a()) {
                    this.f2088a.mo2803a("Attempt " + this.f2108u + " to execute request");
                }
                return this.f2093f.mo3269c(a, this.f2104q, eVar);
            } catch (IOException e2) {
                e = e2;
                this.f2088a.mo2803a("Closing the connection.");
                try {
                    this.f2104q.close();
                } catch (IOException unused) {
                }
                if (this.f2095h.mo2437a(e, a.mo2924b(), eVar)) {
                    if (this.f2088a.mo2810c()) {
                        this.f2088a.mo2809c("I/O exception (" + e.getClass().getName() + ") caught when processing request to " + b + ": " + e.getMessage());
                    }
                    if (this.f2088a.mo2805a()) {
                        this.f2088a.mo2804a(e.getMessage(), e);
                    }
                    if (this.f2088a.mo2810c()) {
                        this.f2088a.mo2809c("Retrying request to " + b);
                    }
                } else if (e instanceof C0485A) {
                    C0485A a2 = new C0485A(b.getTargetHost().mo3275e() + " failed to respond");
                    a2.setStackTrace(e.getStackTrace());
                    throw a2;
                } else {
                    throw e;
                }
            }
        }
    }

    /* renamed from: b */
    private void m2576b() {
        C0634o oVar = this.f2104q;
        if (oVar != null) {
            this.f2104q = null;
            try {
                oVar.abortConnection();
            } catch (IOException e) {
                if (this.f2088a.mo2805a()) {
                    this.f2088a.mo2804a(e.getMessage(), e);
                }
            }
            try {
                oVar.releaseConnection();
            } catch (IOException e2) {
                this.f2088a.mo2804a("Error releasing connection", e2);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0712w mo2904a(C0712w wVar, C0883t tVar, C0855e eVar) {
        C0867o oVar;
        C0883t tVar2 = tVar;
        C0855e eVar2 = eVar;
        C0587b b = wVar.mo2929b();
        C0711v a = wVar.mo2928a();
        C0844g params = a.getParams();
        if (C0538b.m2197b(params)) {
            C0867o oVar2 = (C0867o) eVar2.getAttribute("http.target_host");
            if (oVar2 == null) {
                oVar2 = b.getTargetHost();
            }
            if (oVar2.mo3272c() < 0) {
                oVar = new C0867o(oVar2.mo3271b(), this.f2089b.getSchemeRegistry().mo2674a(oVar2).mo2664a(), oVar2.mo3274d());
            } else {
                oVar = oVar2;
            }
            boolean b2 = this.f2107t.mo2834b(oVar, tVar, this.f2099l, this.f2105r, eVar);
            C0867o proxyHost = b.getProxyHost();
            if (proxyHost == null) {
                proxyHost = b.getTargetHost();
            }
            C0867o oVar3 = proxyHost;
            boolean b3 = this.f2107t.mo2834b(oVar3, tVar, this.f2101n, this.f2106s, eVar);
            if (b2) {
                if (this.f2107t.mo2914c(oVar, tVar, this.f2099l, this.f2105r, eVar)) {
                    return wVar;
                }
            }
            if (b3) {
                if (this.f2107t.mo2914c(oVar3, tVar, this.f2101n, this.f2106s, eVar)) {
                    return wVar;
                }
            }
        }
        if (!C0538b.m2198c(params) || !this.f2097j.mo2600b(a, tVar2, eVar2)) {
            return null;
        }
        int i = this.f2109v;
        if (i < this.f2110w) {
            this.f2109v = i + 1;
            this.f2111x = null;
            C0532l a2 = this.f2097j.mo2599a(a, tVar2, eVar2);
            a2.mo3117a(a.mo2925c().getAllHeaders());
            URI uri = a2.getURI();
            C0867o a3 = C0556e.m2252a(uri);
            if (a3 != null) {
                if (!b.getTargetHost().equals(a3)) {
                    this.f2088a.mo2803a("Resetting target auth state");
                    this.f2105r.mo2486e();
                    C0497c b4 = this.f2106s.mo2483b();
                    if (b4 != null && b4.isConnectionBased()) {
                        this.f2088a.mo2803a("Resetting proxy auth state");
                        this.f2106s.mo2486e();
                    }
                }
                C0711v a4 = m2573a(a2);
                a4.mo3116a(params);
                C0587b b5 = mo2909b(a3, a4, eVar2);
                C0712w wVar2 = new C0712w(a4, b5);
                if (this.f2088a.mo2805a()) {
                    C0668b bVar = this.f2088a;
                    bVar.mo2803a("Redirecting to '" + uri + "' via " + b5);
                }
                return wVar2;
            }
            throw new C0487C("Redirect URI does not specify a valid host name: " + uri);
        }
        throw new C0565n("Maximum redirects (" + this.f2110w + ") exceeded");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0881r mo2905a(C0587b bVar, C0855e eVar) {
        C0867o targetHost = bVar.getTargetHost();
        String b = targetHost.mo3271b();
        int c = targetHost.mo3272c();
        if (c < 0) {
            c = this.f2089b.getSchemeRegistry().mo2676b(targetHost.mo3274d()).mo2664a();
        }
        StringBuilder sb = new StringBuilder(b.length() + 6);
        sb.append(b);
        sb.append(':');
        sb.append(Integer.toString(c));
        return new C0820h("CONNECT", sb.toString(), C0846i.m2991b(this.f2103p));
    }

    /* renamed from: a */
    public C0883t mo2601a(C0867o oVar, C0881r rVar, C0855e eVar) {
        String str;
        eVar.setAttribute("http.auth.target-scope", this.f2105r);
        eVar.setAttribute("http.auth.proxy-scope", this.f2106s);
        C0711v a = m2573a(rVar);
        a.mo3116a(this.f2103p);
        C0587b b = mo2909b(oVar, a, eVar);
        this.f2111x = (C0867o) a.getParams().getParameter("http.virtual-host");
        C0867o oVar2 = this.f2111x;
        if (oVar2 != null && oVar2.mo3272c() == -1) {
            int c = (oVar != null ? oVar : b.getTargetHost()).mo3272c();
            if (c != -1) {
                this.f2111x = new C0867o(this.f2111x.mo3271b(), c, this.f2111x.mo3274d());
            }
        }
        C0712w wVar = new C0712w(a, b);
        C0883t tVar = null;
        boolean z = false;
        C0867o oVar3 = oVar;
        boolean z2 = false;
        while (!z) {
            try {
                C0711v a2 = wVar.mo2928a();
                C0587b b2 = wVar.mo2929b();
                Object attribute = eVar.getAttribute("http.user-token");
                if (this.f2104q == null) {
                    C0610e a3 = this.f2089b.mo2625a(b2, attribute);
                    if (rVar instanceof C0521a) {
                        ((C0521a) rVar).mo2540a(a3);
                    }
                    this.f2104q = a3.getConnection(C0538b.m2196a(this.f2103p), TimeUnit.MILLISECONDS);
                    if (C0842e.m2986f(this.f2103p) && this.f2104q.isOpen()) {
                        this.f2088a.mo2803a("Stale connection check");
                        if (this.f2104q.isStale()) {
                            this.f2088a.mo2803a("Stale connection detected");
                            this.f2104q.close();
                        }
                    }
                }
                if (rVar instanceof C0521a) {
                    ((C0521a) rVar).mo2541a((C0628i) this.f2104q);
                }
                try {
                    m2574a(wVar, eVar);
                    String userInfo = a2.getURI().getUserInfo();
                    if (userInfo != null) {
                        this.f2105r.mo2480a(new C0672b(), new C0513s(userInfo));
                    }
                    if (this.f2111x != null) {
                        oVar3 = this.f2111x;
                    } else {
                        URI uri = a2.getURI();
                        if (uri.isAbsolute()) {
                            oVar3 = C0556e.m2252a(uri);
                        }
                    }
                    if (oVar3 == null) {
                        oVar3 = b2.getTargetHost();
                    }
                    a2.mo2927f();
                    mo2907a(a2, b2);
                    eVar.setAttribute("http.target_host", oVar3);
                    eVar.setAttribute("http.route", b2);
                    eVar.setAttribute("http.connection", this.f2104q);
                    this.f2093f.mo3265a((C0881r) a2, this.f2094g, eVar);
                    tVar = m2575b(wVar, eVar);
                    if (tVar != null) {
                        tVar.mo3116a(this.f2103p);
                        this.f2093f.mo3266a(tVar, this.f2094g, eVar);
                        z2 = this.f2091d.mo2505a(tVar, eVar);
                        if (z2) {
                            long a4 = this.f2092e.mo2704a(tVar, eVar);
                            if (this.f2088a.mo2805a()) {
                                if (a4 > 0) {
                                    str = "for " + a4 + " " + TimeUnit.MILLISECONDS;
                                } else {
                                    str = "indefinitely";
                                }
                                this.f2088a.mo2803a("Connection can be kept alive " + str);
                            }
                            this.f2104q.setIdleDuration(a4, TimeUnit.MILLISECONDS);
                        }
                        C0712w a5 = mo2904a(wVar, tVar, eVar);
                        if (a5 == null) {
                            z = true;
                        } else {
                            if (z2) {
                                C0876g.m3080a(tVar.getEntity());
                                this.f2104q.markReusable();
                            } else {
                                this.f2104q.close();
                                if (this.f2106s.mo2485d().compareTo(C0496b.CHALLENGED) > 0 && this.f2106s.mo2483b() != null && this.f2106s.mo2483b().isConnectionBased()) {
                                    this.f2088a.mo2803a("Resetting proxy auth state");
                                    this.f2106s.mo2486e();
                                }
                                if (this.f2105r.mo2485d().compareTo(C0496b.CHALLENGED) > 0 && this.f2105r.mo2483b() != null && this.f2105r.mo2483b().isConnectionBased()) {
                                    this.f2088a.mo2803a("Resetting target auth state");
                                    this.f2105r.mo2486e();
                                }
                            }
                            if (!a5.mo2929b().equals(wVar.mo2929b())) {
                                mo2906a();
                            }
                            wVar = a5;
                        }
                        if (this.f2104q != null) {
                            if (attribute == null) {
                                attribute = this.f2102o.mo2602a(eVar);
                                eVar.setAttribute("http.user-token", attribute);
                            }
                            if (attribute != null) {
                                this.f2104q.setState(attribute);
                            }
                        }
                    }
                } catch (C0714y e) {
                    if (this.f2088a.mo2805a()) {
                        this.f2088a.mo2803a(e.getMessage());
                    }
                    tVar = e.mo2930a();
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                throw new InterruptedIOException();
            } catch (C0732f e2) {
                InterruptedIOException interruptedIOException = new InterruptedIOException("Connection has been shut down");
                interruptedIOException.initCause(e2);
                throw interruptedIOException;
            } catch (C0850n e3) {
                m2576b();
                throw e3;
            } catch (IOException e4) {
                m2576b();
                throw e4;
            } catch (RuntimeException e5) {
                m2576b();
                throw e5;
            }
        }
        if (!(tVar == null || tVar.getEntity() == null)) {
            if (tVar.getEntity().isStreaming()) {
                tVar.mo3158a(new C0577a(tVar.getEntity(), this.f2104q, z2));
                return tVar;
            }
        }
        if (z2) {
            this.f2104q.markReusable();
        }
        mo2906a();
        return tVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo2906a() {
        try {
            this.f2104q.releaseConnection();
        } catch (IOException e) {
            this.f2088a.mo2804a("IOException releasing connection", e);
        }
        this.f2104q = null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo2907a(C0711v vVar, C0587b bVar) {
        URI uri;
        try {
            URI uri2 = vVar.getURI();
            if (bVar.getProxyHost() == null || bVar.isTunnelled()) {
                if (uri2.isAbsolute()) {
                    uri = C0556e.m2253a(uri2, (C0867o) null, true);
                    vVar.mo2923a(uri);
                }
            } else if (!uri2.isAbsolute()) {
                uri = C0556e.m2253a(uri2, bVar.getTargetHost(), true);
                vVar.mo2923a(uri);
            }
            uri = C0556e.m2255b(uri2);
            vVar.mo2923a(uri);
        } catch (URISyntaxException e) {
            throw new C0487C("Invalid URI: " + vVar.getRequestLine().getUri(), e);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo2908a(C0587b bVar, int i, C0855e eVar) {
        throw new C0850n("Proxy chains are not supported.");
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public C0587b mo2909b(C0867o oVar, C0881r rVar, C0855e eVar) {
        C0589d dVar = this.f2090c;
        if (oVar == null) {
            oVar = (C0867o) rVar.getParams().getParameter("http.default-host");
        }
        return dVar.mo2645a(oVar, rVar, eVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public boolean mo2910b(C0587b bVar, C0855e eVar) {
        C0883t c;
        C0867o proxyHost = bVar.getProxyHost();
        C0867o targetHost = bVar.getTargetHost();
        while (true) {
            if (!this.f2104q.isOpen()) {
                this.f2104q.mo2719a(bVar, eVar, this.f2103p);
            }
            C0881r a = mo2905a(bVar, eVar);
            a.mo3116a(this.f2103p);
            eVar.setAttribute("http.target_host", targetHost);
            eVar.setAttribute("http.route", bVar);
            eVar.setAttribute("http.proxy_host", proxyHost);
            eVar.setAttribute("http.connection", this.f2104q);
            eVar.setAttribute("http.request", a);
            this.f2093f.mo3265a(a, this.f2094g, eVar);
            c = this.f2093f.mo3269c(a, this.f2104q, eVar);
            c.mo3116a(this.f2103p);
            this.f2093f.mo3266a(c, this.f2094g, eVar);
            if (c.getStatusLine().getStatusCode() < 200) {
                throw new C0850n("Unexpected response to CONNECT request: " + c.getStatusLine());
            } else if (C0538b.m2197b(this.f2103p)) {
                if (!this.f2107t.mo2834b(proxyHost, c, this.f2101n, this.f2106s, eVar)) {
                    break;
                }
                if (!this.f2107t.mo2914c(proxyHost, c, this.f2101n, this.f2106s, eVar)) {
                    break;
                } else if (this.f2091d.mo2505a(c, eVar)) {
                    this.f2088a.mo2803a("Connection kept alive");
                    C0876g.m3080a(c.getEntity());
                } else {
                    this.f2104q.close();
                }
            }
        }
        if (c.getStatusLine().getStatusCode() > 299) {
            C0837l entity = c.getEntity();
            if (entity != null) {
                c.mo3158a(new C0659c(entity));
            }
            this.f2104q.close();
            throw new C0714y("CONNECT refused by proxy: " + c.getStatusLine(), c);
        }
        this.f2104q.markReusable();
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo2911c(C0587b bVar, C0855e eVar) {
        int a;
        C0586a aVar = new C0586a();
        do {
            C0587b route = this.f2104q.getRoute();
            a = aVar.mo2630a(bVar, route);
            switch (a) {
                case -1:
                    throw new C0850n("Unable to establish route: planned = " + bVar + "; current = " + route);
                case 0:
                    break;
                case 1:
                case 2:
                    this.f2104q.mo2719a(bVar, eVar, this.f2103p);
                    continue;
                case 3:
                    boolean b = mo2910b(bVar, eVar);
                    this.f2088a.mo2803a("Tunnel to target created.");
                    this.f2104q.mo2721a(b, this.f2103p);
                    continue;
                case 4:
                    mo2908a(bVar, route.getHopCount() - 1, eVar);
                    throw null;
                case 5:
                    this.f2104q.mo2720a(eVar, this.f2103p);
                    continue;
                default:
                    throw new IllegalStateException("Unknown step indicator " + a + " from RouteDirector.");
            }
        } while (a > 0);
    }
}

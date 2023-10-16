package p036c.p037a.p038a.p039a.p060i.p063c;

import java.io.InterruptedIOException;
import java.net.Socket;
import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.p050e.C0607d;
import p036c.p037a.p038a.p039a.p050e.C0636q;
import p036c.p037a.p038a.p039a.p050e.p052b.C0587b;
import p036c.p037a.p038a.p039a.p050e.p052b.C0593f;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0871b;

@Deprecated
/* renamed from: c.a.a.a.i.c.b */
public abstract class C0728b {

    /* renamed from: a */
    protected final C0607d f2182a;

    /* renamed from: b */
    protected final C0636q f2183b;

    /* renamed from: c */
    protected volatile C0587b f2184c;

    /* renamed from: d */
    protected volatile Object f2185d;

    /* renamed from: e */
    protected volatile C0593f f2186e = null;

    protected C0728b(C0607d dVar, C0587b bVar) {
        C0870a.m3042a(dVar, "Connection operator");
        this.f2182a = dVar;
        this.f2183b = dVar.createConnection();
        this.f2184c = bVar;
    }

    /* renamed from: a */
    public Object mo2989a() {
        return this.f2185d;
    }

    /* renamed from: a */
    public void mo2990a(C0587b bVar, C0855e eVar, C0844g gVar) {
        C0870a.m3042a(bVar, "Route");
        C0870a.m3042a(gVar, "HTTP parameters");
        if (this.f2186e != null) {
            C0871b.m3050a(!this.f2186e.mo2648a(), "Connection already open");
        }
        this.f2186e = new C0593f(bVar);
        C0867o proxyHost = bVar.getProxyHost();
        this.f2182a.mo2680a(this.f2183b, proxyHost != null ? proxyHost : bVar.getTargetHost(), bVar.getLocalAddress(), eVar, gVar);
        C0593f fVar = this.f2186e;
        if (fVar == null) {
            throw new InterruptedIOException("Request aborted");
        } else if (proxyHost == null) {
            fVar.mo2647a(this.f2183b.isSecure());
        } else {
            fVar.mo2646a(proxyHost, this.f2183b.isSecure());
        }
    }

    /* renamed from: a */
    public void mo2991a(C0855e eVar, C0844g gVar) {
        C0870a.m3042a(gVar, "HTTP parameters");
        C0871b.m3049a((Object) this.f2186e, "Route tracker");
        C0871b.m3050a(this.f2186e.mo2648a(), "Connection not open");
        C0871b.m3050a(this.f2186e.isTunnelled(), "Protocol layering without a tunnel not supported");
        C0871b.m3050a(!this.f2186e.isLayered(), "Multiple protocol layering not supported");
        this.f2182a.mo2679a(this.f2183b, this.f2186e.getTargetHost(), eVar, gVar);
        this.f2186e.mo2650b(this.f2183b.isSecure());
    }

    /* renamed from: a */
    public void mo2992a(Object obj) {
        this.f2185d = obj;
    }

    /* renamed from: a */
    public void mo2993a(boolean z, C0844g gVar) {
        C0870a.m3042a(gVar, "HTTP parameters");
        C0871b.m3049a((Object) this.f2186e, "Route tracker");
        C0871b.m3050a(this.f2186e.mo2648a(), "Connection not open");
        C0871b.m3050a(!this.f2186e.isTunnelled(), "Connection is already tunnelled");
        this.f2183b.mo2728a((Socket) null, this.f2186e.getTargetHost(), z, gVar);
        this.f2186e.mo2652c(z);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo2947b() {
        this.f2186e = null;
        this.f2185d = null;
    }
}

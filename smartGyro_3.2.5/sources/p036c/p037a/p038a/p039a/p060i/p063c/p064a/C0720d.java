package p036c.p037a.p038a.p039a.p060i.p063c.p064a;

import java.util.concurrent.TimeUnit;
import p036c.p037a.p038a.p039a.p050e.p052b.C0587b;

/* renamed from: c.a.a.a.i.c.a.d */
class C0720d implements C0722f {

    /* renamed from: a */
    final /* synthetic */ C0727k f2143a;

    /* renamed from: b */
    final /* synthetic */ C0587b f2144b;

    /* renamed from: c */
    final /* synthetic */ Object f2145c;

    /* renamed from: d */
    final /* synthetic */ C0721e f2146d;

    C0720d(C0721e eVar, C0727k kVar, C0587b bVar, Object obj) {
        this.f2146d = eVar;
        this.f2143a = kVar;
        this.f2144b = bVar;
        this.f2145c = obj;
    }

    public void abortRequest() {
        this.f2146d.f2148f.lock();
        try {
            this.f2143a.mo2987a();
        } finally {
            this.f2146d.f2148f.unlock();
        }
    }

    public C0718b getPoolEntry(long j, TimeUnit timeUnit) {
        return this.f2146d.mo2953a(this.f2144b, this.f2145c, j, timeUnit, this.f2143a);
    }
}

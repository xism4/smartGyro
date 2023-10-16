package p036c.p037a.p038a.p039a.p060i.p063c;

import java.util.concurrent.TimeUnit;
import p036c.p037a.p038a.p039a.p050e.C0610e;
import p036c.p037a.p038a.p039a.p050e.C0634o;
import p036c.p037a.p038a.p039a.p050e.p052b.C0587b;

/* renamed from: c.a.a.a.i.c.d */
class C0730d implements C0610e {

    /* renamed from: a */
    final /* synthetic */ C0587b f2188a;

    /* renamed from: b */
    final /* synthetic */ Object f2189b;

    /* renamed from: c */
    final /* synthetic */ C0731e f2190c;

    C0730d(C0731e eVar, C0587b bVar, Object obj) {
        this.f2190c = eVar;
        this.f2188a = bVar;
        this.f2189b = obj;
    }

    public void abortRequest() {
    }

    public C0634o getConnection(long j, TimeUnit timeUnit) {
        return this.f2190c.mo2998b(this.f2188a, this.f2189b);
    }
}

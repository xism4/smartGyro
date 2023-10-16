package p036c.p037a.p038a.p039a.p060i.p063c.p064a;

import java.util.concurrent.TimeUnit;
import p036c.p037a.p038a.p039a.p050e.C0610e;
import p036c.p037a.p038a.p039a.p050e.C0634o;
import p036c.p037a.p038a.p039a.p050e.p052b.C0587b;
import p036c.p037a.p038a.p039a.p059h.C0668b;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.c.a.h */
class C0724h implements C0610e {

    /* renamed from: a */
    final /* synthetic */ C0722f f2167a;

    /* renamed from: b */
    final /* synthetic */ C0587b f2168b;

    /* renamed from: c */
    final /* synthetic */ C0725i f2169c;

    C0724h(C0725i iVar, C0722f fVar, C0587b bVar) {
        this.f2169c = iVar;
        this.f2167a = fVar;
        this.f2168b = bVar;
    }

    public void abortRequest() {
        this.f2167a.abortRequest();
    }

    public C0634o getConnection(long j, TimeUnit timeUnit) {
        C0870a.m3042a(this.f2168b, "Route");
        if (this.f2169c.f2170a.mo2805a()) {
            C0668b bVar = this.f2169c.f2170a;
            bVar.mo2803a("Get connection: " + this.f2168b + ", timeout = " + j);
        }
        return new C0719c(this.f2169c, this.f2167a.getPoolEntry(j, timeUnit));
    }
}

package p036c.p037a.p038a.p039a.p060i.p063c.p064a;

import java.util.concurrent.TimeUnit;
import p036c.p037a.p038a.p039a.p050e.C0607d;
import p036c.p037a.p038a.p039a.p050e.C0636q;
import p036c.p037a.p038a.p039a.p050e.p052b.C0587b;
import p036c.p037a.p038a.p039a.p060i.p063c.C0728b;
import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.i.c.a.b */
public class C0718b extends C0728b {

    /* renamed from: f */
    private final long f2139f = System.currentTimeMillis();

    /* renamed from: g */
    private long f2140g;

    /* renamed from: h */
    private final long f2141h;

    /* renamed from: i */
    private long f2142i;

    public C0718b(C0607d dVar, C0587b bVar, long j, TimeUnit timeUnit) {
        super(dVar, bVar);
        C0870a.m3042a(bVar, "HTTP route");
        this.f2141h = j > 0 ? this.f2139f + timeUnit.toMillis(j) : Long.MAX_VALUE;
        this.f2142i = this.f2141h;
    }

    /* renamed from: a */
    public void mo2945a(long j, TimeUnit timeUnit) {
        this.f2140g = System.currentTimeMillis();
        this.f2142i = Math.min(this.f2141h, j > 0 ? this.f2140g + timeUnit.toMillis(j) : Long.MAX_VALUE);
    }

    /* renamed from: a */
    public boolean mo2946a(long j) {
        return j >= this.f2142i;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo2947b() {
        super.mo2947b();
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final C0636q mo2948c() {
        return this.f2183b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public final C0587b mo2949d() {
        return this.f2184c;
    }
}

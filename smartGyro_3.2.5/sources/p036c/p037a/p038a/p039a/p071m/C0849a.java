package p036c.p037a.p038a.p039a.p071m;

import java.util.concurrent.TimeUnit;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.m.a */
public abstract class C0849a<T, C> {

    /* renamed from: a */
    private final String f2407a;

    /* renamed from: b */
    private final T f2408b;

    /* renamed from: c */
    private final C f2409c;

    /* renamed from: d */
    private final long f2410d = System.currentTimeMillis();

    /* renamed from: e */
    private final long f2411e;

    /* renamed from: f */
    private long f2412f;

    /* renamed from: g */
    private long f2413g;

    /* renamed from: h */
    private volatile Object f2414h;

    public C0849a(String str, T t, C c, long j, TimeUnit timeUnit) {
        C0870a.m3042a(t, "Route");
        C0870a.m3042a(c, "Connection");
        C0870a.m3042a(timeUnit, "Time unit");
        this.f2407a = str;
        this.f2408b = t;
        this.f2409c = c;
        this.f2411e = j > 0 ? this.f2410d + timeUnit.toMillis(j) : Long.MAX_VALUE;
        this.f2413g = this.f2411e;
    }

    /* renamed from: a */
    public C mo3239a() {
        return this.f2409c;
    }

    /* renamed from: a */
    public synchronized void mo3240a(long j, TimeUnit timeUnit) {
        C0870a.m3042a(timeUnit, "Time unit");
        this.f2412f = System.currentTimeMillis();
        this.f2413g = Math.min(j > 0 ? this.f2412f + timeUnit.toMillis(j) : Long.MAX_VALUE, this.f2411e);
    }

    /* renamed from: a */
    public void mo3241a(Object obj) {
        this.f2414h = obj;
    }

    /* renamed from: a */
    public synchronized boolean mo3006a(long j) {
        return j >= this.f2413g;
    }

    /* renamed from: b */
    public synchronized long mo3242b() {
        return this.f2413g;
    }

    /* renamed from: c */
    public T mo3243c() {
        return this.f2408b;
    }

    public String toString() {
        return "[id:" + this.f2407a + "][route:" + this.f2408b + "][state:" + this.f2414h + "]";
    }
}

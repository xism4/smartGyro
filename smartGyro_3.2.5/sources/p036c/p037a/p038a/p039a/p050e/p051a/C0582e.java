package p036c.p037a.p038a.p039a.p050e.p051a;

import java.util.concurrent.ConcurrentHashMap;
import p036c.p037a.p038a.p039a.p050e.p052b.C0587b;
import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.e.a.e */
public final class C0582e implements C0581d {

    /* renamed from: a */
    private final ConcurrentHashMap<C0587b, Integer> f1876a;

    /* renamed from: b */
    private volatile int f1877b;

    public C0582e() {
        this(2);
    }

    public C0582e(int i) {
        this.f1876a = new ConcurrentHashMap<>();
        mo2623a(i);
    }

    /* renamed from: a */
    public int mo2622a(C0587b bVar) {
        C0870a.m3042a(bVar, "HTTP route");
        Integer num = this.f1876a.get(bVar);
        return num != null ? num.intValue() : this.f1877b;
    }

    /* renamed from: a */
    public void mo2623a(int i) {
        C0870a.m3046b(i, "Default max per route");
        this.f1877b = i;
    }

    public String toString() {
        return this.f1876a.toString();
    }
}

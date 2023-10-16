package p036c.p037a.p038a.p039a.p050e.p053c;

import java.util.concurrent.ConcurrentHashMap;
import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.e.c.i */
public final class C0603i {

    /* renamed from: a */
    private final ConcurrentHashMap<String, C0599e> f1907a = new ConcurrentHashMap<>();

    /* renamed from: a */
    public final C0599e mo2673a(C0599e eVar) {
        C0870a.m3042a(eVar, "Scheme");
        return this.f1907a.put(eVar.mo2666b(), eVar);
    }

    /* renamed from: a */
    public final C0599e mo2674a(C0867o oVar) {
        C0870a.m3042a(oVar, "Host");
        return mo2676b(oVar.mo3274d());
    }

    /* renamed from: a */
    public final C0599e mo2675a(String str) {
        C0870a.m3042a(str, "Scheme name");
        return this.f1907a.get(str);
    }

    /* renamed from: b */
    public final C0599e mo2676b(String str) {
        C0599e a = mo2675a(str);
        if (a != null) {
            return a;
        }
        throw new IllegalStateException("Scheme '" + str + "' not registered.");
    }
}

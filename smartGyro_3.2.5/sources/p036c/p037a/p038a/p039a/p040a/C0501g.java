package p036c.p037a.p038a.p039a.p040a;

import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import p036c.p037a.p038a.p039a.p049d.C0573a;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.a.g */
public final class C0501g implements C0573a<C0499e> {

    /* renamed from: a */
    private final ConcurrentHashMap<String, C0498d> f1754a = new ConcurrentHashMap<>();

    /* renamed from: a */
    public C0497c mo2470a(String str, C0844g gVar) {
        C0870a.m3042a(str, "Name");
        C0498d dVar = this.f1754a.get(str.toLowerCase(Locale.ENGLISH));
        if (dVar != null) {
            return dVar.mo2468a(gVar);
        }
        throw new IllegalStateException("Unsupported authentication scheme: " + str);
    }

    /* renamed from: a */
    public void mo2471a(String str, C0498d dVar) {
        C0870a.m3042a(str, "Name");
        C0870a.m3042a(dVar, "Authentication scheme factory");
        this.f1754a.put(str.toLowerCase(Locale.ENGLISH), dVar);
    }

    public C0499e lookup(String str) {
        return new C0500f(this, str);
    }
}

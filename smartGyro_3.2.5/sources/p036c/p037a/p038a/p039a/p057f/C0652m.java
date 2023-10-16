package p036c.p037a.p038a.p039a.p057f;

import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import p036c.p037a.p038a.p039a.p049d.C0573a;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.f.m */
public final class C0652m implements C0573a<C0650k> {

    /* renamed from: a */
    private final ConcurrentHashMap<String, C0649j> f1951a = new ConcurrentHashMap<>();

    /* renamed from: a */
    public C0648i mo2771a(String str, C0844g gVar) {
        C0870a.m3042a(str, "Name");
        C0649j jVar = this.f1951a.get(str.toLowerCase(Locale.ENGLISH));
        if (jVar != null) {
            return jVar.mo2769a(gVar);
        }
        throw new IllegalStateException("Unsupported cookie spec: " + str);
    }

    /* renamed from: a */
    public void mo2772a(String str, C0649j jVar) {
        C0870a.m3042a(str, "Name");
        C0870a.m3042a(jVar, "Cookie spec factory");
        this.f1951a.put(str.toLowerCase(Locale.ENGLISH), jVar);
    }

    public C0650k lookup(String str) {
        return new C0651l(this, str);
    }
}

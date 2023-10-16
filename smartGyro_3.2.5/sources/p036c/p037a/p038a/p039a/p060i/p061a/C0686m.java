package p036c.p037a.p038a.p039a.p060i.p061a;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import p036c.p037a.p038a.p039a.C0570c;
import p036c.p037a.p038a.p039a.C0639f;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.p069k.C0819g;
import p036c.p037a.p038a.p039a.p069k.C0835w;
import p036c.p037a.p038a.p039a.p074p.C0873d;

/* renamed from: c.a.a.a.i.a.m */
public abstract class C0686m extends C0671a implements Serializable {

    /* renamed from: b */
    private final Map<String, String> f2037b = new HashMap();

    /* renamed from: c */
    private transient Charset f2038c;

    public C0686m(Charset charset) {
        this.f2038c = charset == null ? C0570c.f1866b : charset;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public String mo2839a(C0881r rVar) {
        String str = (String) rVar.getParams().getParameter("http.auth.credential-charset");
        return str == null ? mo2841b().name() : str;
    }

    /* renamed from: a */
    public String mo2840a(String str) {
        if (str == null) {
            return null;
        }
        return this.f2037b.get(str.toLowerCase(Locale.ROOT));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo2830a(C0873d dVar, int i, int i2) {
        C0639f[] a = C0819g.f2362b.mo3152a(dVar, new C0835w(i, dVar.length()));
        this.f2037b.clear();
        for (C0639f fVar : a) {
            this.f2037b.put(fVar.getName().toLowerCase(Locale.ROOT), fVar.getValue());
        }
    }

    /* renamed from: b */
    public Charset mo2841b() {
        Charset charset = this.f2038c;
        return charset != null ? charset : C0570c.f1866b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public Map<String, String> mo2842c() {
        return this.f2037b;
    }

    public String getRealm() {
        return mo2840a("realm");
    }
}

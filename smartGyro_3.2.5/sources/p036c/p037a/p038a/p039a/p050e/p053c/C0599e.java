package p036c.p037a.p038a.p039a.p050e.p053c;

import java.util.Locale;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0877h;

@Deprecated
/* renamed from: c.a.a.a.e.c.e */
public final class C0599e {

    /* renamed from: a */
    private final String f1900a;

    /* renamed from: b */
    private final C0604j f1901b;

    /* renamed from: c */
    private final int f1902c;

    /* renamed from: d */
    private final boolean f1903d;

    /* renamed from: e */
    private String f1904e;

    public C0599e(String str, int i, C0604j jVar) {
        C0870a.m3042a(str, "Scheme name");
        C0870a.m3044a(i > 0 && i <= 65535, "Port is invalid");
        C0870a.m3042a(jVar, "Socket factory");
        this.f1900a = str.toLowerCase(Locale.ENGLISH);
        this.f1902c = i;
        if (jVar instanceof C0600f) {
            this.f1903d = true;
        } else if (jVar instanceof C0596b) {
            this.f1903d = true;
            this.f1901b = new C0601g((C0596b) jVar);
            return;
        } else {
            this.f1903d = false;
        }
        this.f1901b = jVar;
    }

    @Deprecated
    public C0599e(String str, C0606l lVar, int i) {
        C0870a.m3042a(str, "Scheme name");
        C0870a.m3042a(lVar, "Socket factory");
        C0870a.m3044a(i > 0 && i <= 65535, "Port is invalid");
        this.f1900a = str.toLowerCase(Locale.ENGLISH);
        if (lVar instanceof C0597c) {
            this.f1901b = new C0602h((C0597c) lVar);
            this.f1903d = true;
        } else {
            this.f1901b = new C0605k(lVar);
            this.f1903d = false;
        }
        this.f1902c = i;
    }

    /* renamed from: a */
    public final int mo2664a() {
        return this.f1902c;
    }

    /* renamed from: a */
    public final int mo2665a(int i) {
        return i <= 0 ? this.f1902c : i;
    }

    /* renamed from: b */
    public final String mo2666b() {
        return this.f1900a;
    }

    /* renamed from: c */
    public final C0604j mo2667c() {
        return this.f1901b;
    }

    /* renamed from: d */
    public final boolean mo2668d() {
        return this.f1903d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0599e)) {
            return false;
        }
        C0599e eVar = (C0599e) obj;
        return this.f1900a.equals(eVar.f1900a) && this.f1902c == eVar.f1902c && this.f1903d == eVar.f1903d;
    }

    public int hashCode() {
        return C0877h.m3084a(C0877h.m3083a(C0877h.m3082a(17, this.f1902c), (Object) this.f1900a), this.f1903d);
    }

    public final String toString() {
        if (this.f1904e == null) {
            this.f1904e = this.f1900a + ':' + Integer.toString(this.f1902c);
        }
        return this.f1904e;
    }
}

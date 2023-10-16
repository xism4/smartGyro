package p036c.p037a.p038a.p039a.p069k;

import p036c.p037a.p038a.p039a.C0488D;
import p036c.p037a.p038a.p039a.C0490F;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.C0886w;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.k.h */
public class C0820h extends C0813a implements C0881r {

    /* renamed from: c */
    private final String f2366c;

    /* renamed from: d */
    private final String f2367d;

    /* renamed from: e */
    private C0490F f2368e;

    public C0820h(C0490F f) {
        C0870a.m3042a(f, "Request line");
        this.f2368e = f;
        this.f2366c = f.getMethod();
        this.f2367d = f.getUri();
    }

    public C0820h(String str, String str2, C0488D d) {
        this(new C0826n(str, str2, d));
    }

    public C0488D getProtocolVersion() {
        return getRequestLine().getProtocolVersion();
    }

    public C0490F getRequestLine() {
        if (this.f2368e == null) {
            this.f2368e = new C0826n(this.f2366c, this.f2367d, C0886w.f2446f);
        }
        return this.f2368e;
    }

    public String toString() {
        return this.f2366c + ' ' + this.f2367d + ' ' + this.f2344a;
    }
}

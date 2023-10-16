package p036c.p037a.p038a.p039a.p069k;

import java.io.Serializable;
import p036c.p037a.p038a.p039a.C0488D;
import p036c.p037a.p038a.p039a.C0490F;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;

/* renamed from: c.a.a.a.k.n */
public class C0826n implements C0490F, Cloneable, Serializable {

    /* renamed from: a */
    private final C0488D f2387a;

    /* renamed from: b */
    private final String f2388b;

    /* renamed from: c */
    private final String f2389c;

    public C0826n(String str, String str2, C0488D d) {
        C0870a.m3042a(str, "Method");
        this.f2388b = str;
        C0870a.m3042a(str2, "URI");
        this.f2389c = str2;
        C0870a.m3042a(d, "Version");
        this.f2387a = d;
    }

    public Object clone() {
        return super.clone();
    }

    public String getMethod() {
        return this.f2388b;
    }

    public C0488D getProtocolVersion() {
        return this.f2387a;
    }

    public String getUri() {
        return this.f2389c;
    }

    public String toString() {
        return C0822j.f2377b.mo3165a((C0873d) null, (C0490F) this).toString();
    }
}

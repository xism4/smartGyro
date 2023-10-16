package p036c.p037a.p038a.p039a.p069k;

import java.io.Serializable;
import p036c.p037a.p038a.p039a.C0488D;
import p036c.p037a.p038a.p039a.C0491G;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;

/* renamed from: c.a.a.a.k.o */
public class C0827o implements C0491G, Cloneable, Serializable {

    /* renamed from: a */
    private final C0488D f2390a;

    /* renamed from: b */
    private final int f2391b;

    /* renamed from: c */
    private final String f2392c;

    public C0827o(C0488D d, int i, String str) {
        C0870a.m3042a(d, "Version");
        this.f2390a = d;
        C0870a.m3039a(i, "Status code");
        this.f2391b = i;
        this.f2392c = str;
    }

    public Object clone() {
        return super.clone();
    }

    public C0488D getProtocolVersion() {
        return this.f2390a;
    }

    public String getReasonPhrase() {
        return this.f2392c;
    }

    public int getStatusCode() {
        return this.f2391b;
    }

    public String toString() {
        return C0822j.f2377b.mo3168b((C0873d) null, (C0491G) this).toString();
    }
}

package p036c.p037a.p038a.p039a.p069k;

import java.io.Serializable;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0639f;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0873d;

/* renamed from: c.a.a.a.k.b */
public class C0814b implements C0576e, Cloneable, Serializable {

    /* renamed from: a */
    private final String f2346a;

    /* renamed from: b */
    private final String f2347b;

    public C0814b(String str, String str2) {
        C0870a.m3042a(str, "Name");
        this.f2346a = str;
        this.f2347b = str2;
    }

    public Object clone() {
        return super.clone();
    }

    public C0639f[] getElements() {
        String str = this.f2347b;
        return str != null ? C0819g.m2910a(str, (C0832t) null) : new C0639f[0];
    }

    public String getName() {
        return this.f2346a;
    }

    public String getValue() {
        return this.f2347b;
    }

    public String toString() {
        return C0822j.f2377b.mo3166a((C0873d) null, (C0576e) this).toString();
    }
}

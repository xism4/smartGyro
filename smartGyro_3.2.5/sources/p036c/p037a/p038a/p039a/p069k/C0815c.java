package p036c.p037a.p038a.p039a.p069k;

import p036c.p037a.p038a.p039a.C0639f;
import p036c.p037a.p038a.p039a.C0889z;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0877h;

/* renamed from: c.a.a.a.k.c */
public class C0815c implements C0639f, Cloneable {

    /* renamed from: a */
    private final String f2348a;

    /* renamed from: b */
    private final String f2349b;

    /* renamed from: c */
    private final C0889z[] f2350c;

    public C0815c(String str, String str2) {
        this(str, str2, (C0889z[]) null);
    }

    public C0815c(String str, String str2, C0889z[] zVarArr) {
        C0870a.m3042a(str, "Name");
        this.f2348a = str;
        this.f2349b = str2;
        if (zVarArr != null) {
            this.f2350c = zVarArr;
        } else {
            this.f2350c = new C0889z[0];
        }
    }

    public Object clone() {
        return super.clone();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0639f)) {
            return false;
        }
        C0815c cVar = (C0815c) obj;
        return this.f2348a.equals(cVar.f2348a) && C0877h.m3085a((Object) this.f2349b, (Object) cVar.f2349b) && C0877h.m3086a((Object[]) this.f2350c, (Object[]) cVar.f2350c);
    }

    public String getName() {
        return this.f2348a;
    }

    public C0889z getParameter(int i) {
        return this.f2350c[i];
    }

    public C0889z getParameterByName(String str) {
        C0870a.m3042a(str, "Name");
        for (C0889z zVar : this.f2350c) {
            if (zVar.getName().equalsIgnoreCase(str)) {
                return zVar;
            }
        }
        return null;
    }

    public int getParameterCount() {
        return this.f2350c.length;
    }

    public C0889z[] getParameters() {
        return (C0889z[]) this.f2350c.clone();
    }

    public String getValue() {
        return this.f2349b;
    }

    public int hashCode() {
        int a = C0877h.m3083a(C0877h.m3083a(17, (Object) this.f2348a), (Object) this.f2349b);
        for (C0889z a2 : this.f2350c) {
            a = C0877h.m3083a(a, (Object) a2);
        }
        return a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f2348a);
        if (this.f2349b != null) {
            sb.append("=");
            sb.append(this.f2349b);
        }
        for (C0889z append : this.f2350c) {
            sb.append("; ");
            sb.append(append);
        }
        return sb.toString();
    }
}

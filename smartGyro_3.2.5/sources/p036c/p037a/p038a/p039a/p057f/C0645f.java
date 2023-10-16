package p036c.p037a.p038a.p039a.p057f;

import java.util.Locale;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0878i;

/* renamed from: c.a.a.a.f.f */
public final class C0645f {

    /* renamed from: a */
    private final String f1944a;

    /* renamed from: b */
    private final int f1945b;

    /* renamed from: c */
    private final String f1946c;

    /* renamed from: d */
    private final boolean f1947d;

    public C0645f(String str, int i, String str2, boolean z) {
        C0870a.m3047b(str, "Host");
        C0870a.m3039a(i, "Port");
        C0870a.m3042a(str2, "Path");
        this.f1944a = str.toLowerCase(Locale.ROOT);
        this.f1945b = i;
        if (!C0878i.m3088b(str2)) {
            this.f1946c = str2;
        } else {
            this.f1946c = "/";
        }
        this.f1947d = z;
    }

    /* renamed from: a */
    public String mo2756a() {
        return this.f1944a;
    }

    /* renamed from: b */
    public String mo2757b() {
        return this.f1946c;
    }

    /* renamed from: c */
    public int mo2758c() {
        return this.f1945b;
    }

    /* renamed from: d */
    public boolean mo2759d() {
        return this.f1947d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        if (this.f1947d) {
            sb.append("(secure)");
        }
        sb.append(this.f1944a);
        sb.append(':');
        sb.append(Integer.toString(this.f1945b));
        sb.append(this.f1946c);
        sb.append(']');
        return sb.toString();
    }
}

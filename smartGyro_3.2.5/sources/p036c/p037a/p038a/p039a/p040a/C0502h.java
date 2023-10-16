package p036c.p037a.p038a.p039a.p040a;

import java.util.Locale;
import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0877h;

/* renamed from: c.a.a.a.a.h */
public class C0502h {

    /* renamed from: a */
    public static final String f1755a = null;

    /* renamed from: b */
    public static final String f1756b = null;

    /* renamed from: c */
    public static final String f1757c = null;

    /* renamed from: d */
    public static final C0502h f1758d = new C0502h(f1755a, -1, f1756b, f1757c);

    /* renamed from: e */
    private final String f1759e;

    /* renamed from: f */
    private final String f1760f;

    /* renamed from: g */
    private final String f1761g;

    /* renamed from: h */
    private final int f1762h;

    /* renamed from: i */
    private final C0867o f1763i;

    public C0502h(C0867o oVar, String str, String str2) {
        C0870a.m3042a(oVar, "Host");
        this.f1761g = oVar.mo3271b().toLowerCase(Locale.ROOT);
        this.f1762h = oVar.mo3272c() < 0 ? -1 : oVar.mo3272c();
        this.f1760f = str == null ? f1756b : str;
        this.f1759e = str2 == null ? f1757c : str2.toUpperCase(Locale.ROOT);
        this.f1763i = oVar;
    }

    public C0502h(String str, int i) {
        this(str, i, f1756b, f1757c);
    }

    public C0502h(String str, int i, String str2, String str3) {
        this.f1761g = str == null ? f1755a : str.toLowerCase(Locale.ROOT);
        this.f1762h = i < 0 ? -1 : i;
        this.f1760f = str2 == null ? f1756b : str2;
        this.f1759e = str3 == null ? f1757c : str3.toUpperCase(Locale.ROOT);
        this.f1763i = null;
    }

    /* renamed from: a */
    public int mo2473a(C0502h hVar) {
        int i;
        if (C0877h.m3085a((Object) this.f1759e, (Object) hVar.f1759e)) {
            i = 1;
        } else {
            String str = this.f1759e;
            String str2 = f1757c;
            if (str != str2 && hVar.f1759e != str2) {
                return -1;
            }
            i = 0;
        }
        if (C0877h.m3085a((Object) this.f1760f, (Object) hVar.f1760f)) {
            i += 2;
        } else {
            String str3 = this.f1760f;
            String str4 = f1756b;
            if (!(str3 == str4 || hVar.f1760f == str4)) {
                return -1;
            }
        }
        int i2 = this.f1762h;
        int i3 = hVar.f1762h;
        if (i2 == i3) {
            i += 4;
        } else if (!(i2 == -1 || i3 == -1)) {
            return -1;
        }
        if (C0877h.m3085a((Object) this.f1761g, (Object) hVar.f1761g)) {
            return i + 8;
        }
        String str5 = this.f1761g;
        String str6 = f1755a;
        if (str5 == str6 || hVar.f1761g == str6) {
            return i;
        }
        return -1;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0502h)) {
            return super.equals(obj);
        }
        C0502h hVar = (C0502h) obj;
        return C0877h.m3085a((Object) this.f1761g, (Object) hVar.f1761g) && this.f1762h == hVar.f1762h && C0877h.m3085a((Object) this.f1760f, (Object) hVar.f1760f) && C0877h.m3085a((Object) this.f1759e, (Object) hVar.f1759e);
    }

    public int hashCode() {
        return C0877h.m3083a(C0877h.m3083a(C0877h.m3082a(C0877h.m3083a(17, (Object) this.f1761g), this.f1762h), (Object) this.f1760f), (Object) this.f1759e);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = this.f1759e;
        if (str != null) {
            sb.append(str.toUpperCase(Locale.ROOT));
            sb.append(' ');
        }
        if (this.f1760f != null) {
            sb.append('\'');
            sb.append(this.f1760f);
            sb.append('\'');
        } else {
            sb.append("<any realm>");
        }
        if (this.f1761g != null) {
            sb.append('@');
            sb.append(this.f1761g);
            if (this.f1762h >= 0) {
                sb.append(':');
                sb.append(this.f1762h);
            }
        }
        return sb.toString();
    }
}

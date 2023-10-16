package p036c.p037a.p038a.p039a;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Locale;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0877h;

/* renamed from: c.a.a.a.o */
public final class C0867o implements Cloneable, Serializable {

    /* renamed from: a */
    protected final String f2430a;

    /* renamed from: b */
    protected final String f2431b;

    /* renamed from: c */
    protected final int f2432c;

    /* renamed from: d */
    protected final String f2433d;

    /* renamed from: e */
    protected final InetAddress f2434e;

    public C0867o(String str, int i) {
        this(str, i, (String) null);
    }

    public C0867o(String str, int i, String str2) {
        C0870a.m3041a(str, "Host name");
        this.f2430a = str;
        this.f2431b = str.toLowerCase(Locale.ROOT);
        this.f2433d = str2 != null ? str2.toLowerCase(Locale.ROOT) : "http";
        this.f2432c = i;
        this.f2434e = null;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public C0867o(InetAddress inetAddress, int i, String str) {
        this(inetAddress, inetAddress.getHostName(), i, str);
        C0870a.m3042a(inetAddress, "Inet address");
    }

    public C0867o(InetAddress inetAddress, String str, int i, String str2) {
        C0870a.m3042a(inetAddress, "Inet address");
        this.f2434e = inetAddress;
        C0870a.m3042a(str, "Hostname");
        this.f2430a = str;
        this.f2431b = this.f2430a.toLowerCase(Locale.ROOT);
        this.f2433d = str2 != null ? str2.toLowerCase(Locale.ROOT) : "http";
        this.f2432c = i;
    }

    /* renamed from: a */
    public InetAddress mo3270a() {
        return this.f2434e;
    }

    /* renamed from: b */
    public String mo3271b() {
        return this.f2430a;
    }

    /* renamed from: c */
    public int mo3272c() {
        return this.f2432c;
    }

    public Object clone() {
        return super.clone();
    }

    /* renamed from: d */
    public String mo3274d() {
        return this.f2433d;
    }

    /* renamed from: e */
    public String mo3275e() {
        if (this.f2432c == -1) {
            return this.f2430a;
        }
        StringBuilder sb = new StringBuilder(this.f2430a.length() + 6);
        sb.append(this.f2430a);
        sb.append(":");
        sb.append(Integer.toString(this.f2432c));
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0867o)) {
            return false;
        }
        C0867o oVar = (C0867o) obj;
        if (this.f2431b.equals(oVar.f2431b) && this.f2432c == oVar.f2432c && this.f2433d.equals(oVar.f2433d)) {
            InetAddress inetAddress = this.f2434e;
            if (inetAddress == null) {
                if (oVar.f2434e == null) {
                    return true;
                }
            } else if (inetAddress.equals(oVar.f2434e)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: f */
    public String mo3277f() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f2433d);
        sb.append("://");
        sb.append(this.f2430a);
        if (this.f2432c != -1) {
            sb.append(':');
            sb.append(Integer.toString(this.f2432c));
        }
        return sb.toString();
    }

    public int hashCode() {
        int a = C0877h.m3083a(C0877h.m3082a(C0877h.m3083a(17, (Object) this.f2431b), this.f2432c), (Object) this.f2433d);
        InetAddress inetAddress = this.f2434e;
        return inetAddress != null ? C0877h.m3083a(a, (Object) inetAddress) : a;
    }

    public String toString() {
        return mo3277f();
    }
}

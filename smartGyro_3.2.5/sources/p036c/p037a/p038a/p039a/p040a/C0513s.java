package p036c.p037a.p038a.p039a.p040a;

import java.io.Serializable;
import java.security.Principal;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0877h;

/* renamed from: c.a.a.a.a.s */
public class C0513s implements C0508n, Serializable {

    /* renamed from: a */
    private final C0505k f1776a;

    /* renamed from: b */
    private final String f1777b;

    public C0513s(String str) {
        String str2;
        C0870a.m3042a(str, "Username:password string");
        int indexOf = str.indexOf(58);
        if (indexOf >= 0) {
            this.f1776a = new C0505k(str.substring(0, indexOf));
            str2 = str.substring(indexOf + 1);
        } else {
            this.f1776a = new C0505k(str);
            str2 = null;
        }
        this.f1777b = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof C0513s) && C0877h.m3085a((Object) this.f1776a, (Object) ((C0513s) obj).f1776a);
    }

    public String getPassword() {
        return this.f1777b;
    }

    public Principal getUserPrincipal() {
        return this.f1776a;
    }

    public int hashCode() {
        return this.f1776a.hashCode();
    }

    public String toString() {
        return this.f1776a.toString();
    }
}

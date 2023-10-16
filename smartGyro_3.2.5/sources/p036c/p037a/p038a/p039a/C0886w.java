package p036c.p037a.p038a.p039a;

import java.io.Serializable;

/* renamed from: c.a.a.a.w */
public final class C0886w extends C0488D implements Serializable {

    /* renamed from: d */
    public static final C0886w f2444d = new C0886w(0, 9);

    /* renamed from: e */
    public static final C0886w f2445e = new C0886w(1, 0);

    /* renamed from: f */
    public static final C0886w f2446f = new C0886w(1, 1);

    public C0886w(int i, int i2) {
        super("HTTP", i, i2);
    }

    /* renamed from: a */
    public C0488D mo2441a(int i, int i2) {
        if (i == this.f1742b && i2 == this.f1743c) {
            return this;
        }
        if (i == 1) {
            if (i2 == 0) {
                return f2445e;
            }
            if (i2 == 1) {
                return f2446f;
            }
        }
        return (i == 0 && i2 == 9) ? f2444d : new C0886w(i, i2);
    }
}

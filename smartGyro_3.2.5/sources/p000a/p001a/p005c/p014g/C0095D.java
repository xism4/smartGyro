package p000a.p001a.p005c.p014g;

import android.os.Build;
import android.view.WindowInsets;

/* renamed from: a.a.c.g.D */
public class C0095D {

    /* renamed from: a */
    private final Object f247a;

    private C0095D(Object obj) {
        this.f247a = obj;
    }

    /* renamed from: a */
    static C0095D m340a(Object obj) {
        if (obj == null) {
            return null;
        }
        return new C0095D(obj);
    }

    /* renamed from: a */
    static Object m341a(C0095D d) {
        if (d == null) {
            return null;
        }
        return d.f247a;
    }

    /* renamed from: a */
    public int mo393a() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.f247a).getSystemWindowInsetBottom();
        }
        return 0;
    }

    /* renamed from: a */
    public C0095D mo394a(int i, int i2, int i3, int i4) {
        if (Build.VERSION.SDK_INT >= 20) {
            return new C0095D(((WindowInsets) this.f247a).replaceSystemWindowInsets(i, i2, i3, i4));
        }
        return null;
    }

    /* renamed from: b */
    public int mo395b() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.f247a).getSystemWindowInsetLeft();
        }
        return 0;
    }

    /* renamed from: c */
    public int mo396c() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.f247a).getSystemWindowInsetRight();
        }
        return 0;
    }

    /* renamed from: d */
    public int mo397d() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.f247a).getSystemWindowInsetTop();
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C0095D.class != obj.getClass()) {
            return false;
        }
        C0095D d = (C0095D) obj;
        Object obj2 = this.f247a;
        return obj2 == null ? d.f247a == null : obj2.equals(d.f247a);
    }

    public int hashCode() {
        Object obj = this.f247a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }
}

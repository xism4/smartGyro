package p000a.p001a.p005c.p014g;

import android.view.View;
import android.view.ViewParent;

/* renamed from: a.a.c.g.m */
public class C0119m {

    /* renamed from: a */
    private ViewParent f270a;

    /* renamed from: b */
    private ViewParent f271b;

    /* renamed from: c */
    private final View f272c;

    /* renamed from: d */
    private boolean f273d;

    /* renamed from: e */
    private int[] f274e;

    public C0119m(View view) {
        this.f272c = view;
    }

    /* renamed from: a */
    private void m414a(int i, ViewParent viewParent) {
        if (i == 0) {
            this.f270a = viewParent;
        } else if (i == 1) {
            this.f271b = viewParent;
        }
    }

    /* renamed from: c */
    private ViewParent m415c(int i) {
        if (i == 0) {
            return this.f270a;
        }
        if (i != 1) {
            return null;
        }
        return this.f271b;
    }

    /* renamed from: a */
    public void mo465a(boolean z) {
        if (this.f273d) {
            C0127u.m458l(this.f272c);
        }
        this.f273d = z;
    }

    /* renamed from: a */
    public boolean mo466a() {
        return this.f273d;
    }

    /* renamed from: a */
    public boolean mo467a(float f, float f2) {
        ViewParent c;
        if (!mo466a() || (c = m415c(0)) == null) {
            return false;
        }
        return C0131w.m472a(c, this.f272c, f, f2);
    }

    /* renamed from: a */
    public boolean mo468a(float f, float f2, boolean z) {
        ViewParent c;
        if (!mo466a() || (c = m415c(0)) == null) {
            return false;
        }
        return C0131w.m473a(c, this.f272c, f, f2, z);
    }

    /* renamed from: a */
    public boolean mo469a(int i) {
        return m415c(i) != null;
    }

    /* renamed from: a */
    public boolean mo470a(int i, int i2) {
        if (mo469a(i2)) {
            return true;
        }
        if (!mo466a()) {
            return false;
        }
        View view = this.f272c;
        for (ViewParent parent = this.f272c.getParent(); parent != null; parent = parent.getParent()) {
            if (C0131w.m474b(parent, view, this.f272c, i, i2)) {
                m414a(i2, parent);
                C0131w.m471a(parent, view, this.f272c, i, i2);
                return true;
            }
            if (parent instanceof View) {
                view = (View) parent;
            }
        }
        return false;
    }

    /* renamed from: a */
    public boolean mo471a(int i, int i2, int i3, int i4, int[] iArr, int i5) {
        ViewParent c;
        int i6;
        int i7;
        int[] iArr2 = iArr;
        if (!mo466a() || (c = m415c(i5)) == null) {
            return false;
        }
        if (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            if (iArr2 != null) {
                iArr2[0] = 0;
                iArr2[1] = 0;
            }
            return false;
        }
        if (iArr2 != null) {
            this.f272c.getLocationInWindow(iArr2);
            i7 = iArr2[0];
            i6 = iArr2[1];
        } else {
            i7 = 0;
            i6 = 0;
        }
        C0131w.m469a(c, this.f272c, i, i2, i3, i4, i5);
        if (iArr2 != null) {
            this.f272c.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i7;
            iArr2[1] = iArr2[1] - i6;
        }
        return true;
    }

    /* renamed from: a */
    public boolean mo472a(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        ViewParent c;
        int i4;
        int i5;
        if (!mo466a() || (c = m415c(i3)) == null) {
            return false;
        }
        if (i == 0 && i2 == 0) {
            if (iArr2 != null) {
                iArr2[0] = 0;
                iArr2[1] = 0;
            }
            return false;
        }
        if (iArr2 != null) {
            this.f272c.getLocationInWindow(iArr2);
            i5 = iArr2[0];
            i4 = iArr2[1];
        } else {
            i5 = 0;
            i4 = 0;
        }
        if (iArr == null) {
            if (this.f274e == null) {
                this.f274e = new int[2];
            }
            iArr = this.f274e;
        }
        iArr[0] = 0;
        iArr[1] = 0;
        C0131w.m470a(c, this.f272c, i, i2, iArr, i3);
        if (iArr2 != null) {
            this.f272c.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i5;
            iArr2[1] = iArr2[1] - i4;
        }
        return (iArr[0] == 0 && iArr[1] == 0) ? false : true;
    }

    /* renamed from: b */
    public void mo473b(int i) {
        ViewParent c = m415c(i);
        if (c != null) {
            C0131w.m468a(c, this.f272c, i);
            m414a(i, (ViewParent) null);
        }
    }
}

package android.support.p025v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import p000a.p001a.p005c.p014g.C0127u;
import p000a.p001a.p017d.p018a.C0145j;

/* renamed from: android.support.v7.widget.j */
class C0412j {

    /* renamed from: a */
    private final View f1557a;

    /* renamed from: b */
    private final C0423o f1558b;

    /* renamed from: c */
    private int f1559c = -1;

    /* renamed from: d */
    private C0435ra f1560d;

    /* renamed from: e */
    private C0435ra f1561e;

    /* renamed from: f */
    private C0435ra f1562f;

    C0412j(View view) {
        this.f1557a = view;
        this.f1558b = C0423o.m1851a();
    }

    /* renamed from: b */
    private boolean m1804b(Drawable drawable) {
        if (this.f1562f == null) {
            this.f1562f = new C0435ra();
        }
        C0435ra raVar = this.f1562f;
        raVar.mo2253a();
        ColorStateList b = C0127u.m447b(this.f1557a);
        if (b != null) {
            raVar.f1633d = true;
            raVar.f1630a = b;
        }
        PorterDuff.Mode c = C0127u.m449c(this.f1557a);
        if (c != null) {
            raVar.f1632c = true;
            raVar.f1631b = c;
        }
        if (!raVar.f1633d && !raVar.f1632c) {
            return false;
        }
        C0423o.m1855a(drawable, raVar, this.f1557a.getDrawableState());
        return true;
    }

    /* renamed from: d */
    private boolean m1805d() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.f1560d != null : i == 21;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo2165a() {
        Drawable background = this.f1557a.getBackground();
        if (background == null) {
            return;
        }
        if (!m1805d() || !m1804b(background)) {
            C0435ra raVar = this.f1561e;
            if (raVar != null) {
                C0423o.m1855a(background, raVar, this.f1557a.getDrawableState());
                return;
            }
            C0435ra raVar2 = this.f1560d;
            if (raVar2 != null) {
                C0423o.m1855a(background, raVar2, this.f1557a.getDrawableState());
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo2166a(int i) {
        this.f1559c = i;
        C0423o oVar = this.f1558b;
        mo2167a(oVar != null ? oVar.mo2228b(this.f1557a.getContext(), i) : null);
        mo2165a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo2167a(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f1560d == null) {
                this.f1560d = new C0435ra();
            }
            C0435ra raVar = this.f1560d;
            raVar.f1630a = colorStateList;
            raVar.f1633d = true;
        } else {
            this.f1560d = null;
        }
        mo2165a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo2168a(PorterDuff.Mode mode) {
        if (this.f1561e == null) {
            this.f1561e = new C0435ra();
        }
        C0435ra raVar = this.f1561e;
        raVar.f1631b = mode;
        raVar.f1632c = true;
        mo2165a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo2169a(Drawable drawable) {
        this.f1559c = -1;
        mo2167a((ColorStateList) null);
        mo2165a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo2170a(AttributeSet attributeSet, int i) {
        C0439ta a = C0439ta.m1902a(this.f1557a.getContext(), attributeSet, C0145j.ViewBackgroundHelper, i, 0);
        try {
            if (a.mo2287g(C0145j.ViewBackgroundHelper_android_background)) {
                this.f1559c = a.mo2286g(C0145j.ViewBackgroundHelper_android_background, -1);
                ColorStateList b = this.f1558b.mo2228b(this.f1557a.getContext(), this.f1559c);
                if (b != null) {
                    mo2167a(b);
                }
            }
            if (a.mo2287g(C0145j.ViewBackgroundHelper_backgroundTint)) {
                C0127u.m441a(this.f1557a, a.mo2272a(C0145j.ViewBackgroundHelper_backgroundTint));
            }
            if (a.mo2287g(C0145j.ViewBackgroundHelper_backgroundTintMode)) {
                C0127u.m442a(this.f1557a, C0348M.m1559a(a.mo2280d(C0145j.ViewBackgroundHelper_backgroundTintMode, -1), (PorterDuff.Mode) null));
            }
        } finally {
            a.mo2274a();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public ColorStateList mo2171b() {
        C0435ra raVar = this.f1561e;
        if (raVar != null) {
            return raVar.f1630a;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo2172b(ColorStateList colorStateList) {
        if (this.f1561e == null) {
            this.f1561e = new C0435ra();
        }
        C0435ra raVar = this.f1561e;
        raVar.f1630a = colorStateList;
        raVar.f1633d = true;
        mo2165a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public PorterDuff.Mode mo2173c() {
        C0435ra raVar = this.f1561e;
        if (raVar != null) {
            return raVar.f1631b;
        }
        return null;
    }
}

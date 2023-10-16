package android.support.p025v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.p024v4.widget.C0212g;
import android.util.AttributeSet;
import android.widget.ImageView;
import p000a.p001a.p017d.p018a.C0145j;
import p000a.p001a.p017d.p019b.p020a.C0146a;

/* renamed from: android.support.v7.widget.s */
public class C0436s {

    /* renamed from: a */
    private final ImageView f1634a;

    /* renamed from: b */
    private C0435ra f1635b;

    /* renamed from: c */
    private C0435ra f1636c;

    /* renamed from: d */
    private C0435ra f1637d;

    public C0436s(ImageView imageView) {
        this.f1634a = imageView;
    }

    /* renamed from: a */
    private boolean m1890a(Drawable drawable) {
        if (this.f1637d == null) {
            this.f1637d = new C0435ra();
        }
        C0435ra raVar = this.f1637d;
        raVar.mo2253a();
        ColorStateList a = C0212g.m792a(this.f1634a);
        if (a != null) {
            raVar.f1633d = true;
            raVar.f1630a = a;
        }
        PorterDuff.Mode b = C0212g.m795b(this.f1634a);
        if (b != null) {
            raVar.f1632c = true;
            raVar.f1631b = b;
        }
        if (!raVar.f1633d && !raVar.f1632c) {
            return false;
        }
        C0423o.m1855a(drawable, raVar, this.f1634a.getDrawableState());
        return true;
    }

    /* renamed from: e */
    private boolean m1891e() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.f1635b != null : i == 21;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo2254a() {
        Drawable drawable = this.f1634a.getDrawable();
        if (drawable != null) {
            C0348M.m1561b(drawable);
        }
        if (drawable == null) {
            return;
        }
        if (!m1891e() || !m1890a(drawable)) {
            C0435ra raVar = this.f1636c;
            if (raVar != null) {
                C0423o.m1855a(drawable, raVar, this.f1634a.getDrawableState());
                return;
            }
            C0435ra raVar2 = this.f1635b;
            if (raVar2 != null) {
                C0423o.m1855a(drawable, raVar2, this.f1634a.getDrawableState());
            }
        }
    }

    /* renamed from: a */
    public void mo2255a(int i) {
        if (i != 0) {
            Drawable b = C0146a.m492b(this.f1634a.getContext(), i);
            if (b != null) {
                C0348M.m1561b(b);
            }
            this.f1634a.setImageDrawable(b);
        } else {
            this.f1634a.setImageDrawable((Drawable) null);
        }
        mo2254a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo2256a(ColorStateList colorStateList) {
        if (this.f1636c == null) {
            this.f1636c = new C0435ra();
        }
        C0435ra raVar = this.f1636c;
        raVar.f1630a = colorStateList;
        raVar.f1633d = true;
        mo2254a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo2257a(PorterDuff.Mode mode) {
        if (this.f1636c == null) {
            this.f1636c = new C0435ra();
        }
        C0435ra raVar = this.f1636c;
        raVar.f1631b = mode;
        raVar.f1632c = true;
        mo2254a();
    }

    /* renamed from: a */
    public void mo2258a(AttributeSet attributeSet, int i) {
        int g;
        C0439ta a = C0439ta.m1902a(this.f1634a.getContext(), attributeSet, C0145j.AppCompatImageView, i, 0);
        try {
            Drawable drawable = this.f1634a.getDrawable();
            if (!(drawable != null || (g = a.mo2286g(C0145j.AppCompatImageView_srcCompat, -1)) == -1 || (drawable = C0146a.m492b(this.f1634a.getContext(), g)) == null)) {
                this.f1634a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                C0348M.m1561b(drawable);
            }
            if (a.mo2287g(C0145j.AppCompatImageView_tint)) {
                C0212g.m793a(this.f1634a, a.mo2272a(C0145j.AppCompatImageView_tint));
            }
            if (a.mo2287g(C0145j.AppCompatImageView_tintMode)) {
                C0212g.m794a(this.f1634a, C0348M.m1559a(a.mo2280d(C0145j.AppCompatImageView_tintMode, -1), (PorterDuff.Mode) null));
            }
        } finally {
            a.mo2274a();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public ColorStateList mo2259b() {
        C0435ra raVar = this.f1636c;
        if (raVar != null) {
            return raVar.f1630a;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public PorterDuff.Mode mo2260c() {
        C0435ra raVar = this.f1636c;
        if (raVar != null) {
            return raVar.f1631b;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public boolean mo2261d() {
        return Build.VERSION.SDK_INT < 21 || !(this.f1634a.getBackground() instanceof RippleDrawable);
    }
}

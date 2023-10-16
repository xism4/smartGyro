package android.support.p025v7.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.p024v4.widget.C0204b;
import android.support.p024v4.widget.C0218m;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import p000a.p001a.p005c.p006a.p007a.C0038h;
import p000a.p001a.p017d.p018a.C0145j;

/* renamed from: android.support.v7.widget.H */
class C0341H {

    /* renamed from: a */
    private final TextView f1230a;

    /* renamed from: b */
    private C0435ra f1231b;

    /* renamed from: c */
    private C0435ra f1232c;

    /* renamed from: d */
    private C0435ra f1233d;

    /* renamed from: e */
    private C0435ra f1234e;

    /* renamed from: f */
    private C0435ra f1235f;

    /* renamed from: g */
    private C0435ra f1236g;

    /* renamed from: h */
    private final C0345J f1237h;

    /* renamed from: i */
    private int f1238i = 0;

    /* renamed from: j */
    private Typeface f1239j;

    /* renamed from: k */
    private boolean f1240k;

    C0341H(TextView textView) {
        this.f1230a = textView;
        this.f1237h = new C0345J(this.f1230a);
    }

    /* renamed from: a */
    private static C0435ra m1476a(Context context, C0423o oVar, int i) {
        ColorStateList b = oVar.mo2228b(context, i);
        if (b == null) {
            return null;
        }
        C0435ra raVar = new C0435ra();
        raVar.f1633d = true;
        raVar.f1630a = b;
        return raVar;
    }

    /* renamed from: a */
    private void m1477a(Context context, C0439ta taVar) {
        String d;
        Typeface typeface;
        this.f1238i = taVar.mo2280d(C0145j.TextAppearance_android_textStyle, this.f1238i);
        boolean z = false;
        if (taVar.mo2287g(C0145j.TextAppearance_android_fontFamily) || taVar.mo2287g(C0145j.TextAppearance_fontFamily)) {
            this.f1239j = null;
            int i = taVar.mo2287g(C0145j.TextAppearance_fontFamily) ? C0145j.TextAppearance_fontFamily : C0145j.TextAppearance_android_fontFamily;
            if (!context.isRestricted()) {
                try {
                    this.f1239j = taVar.mo2273a(i, this.f1238i, (C0038h.C0039a) new C0339G(this, new WeakReference(this.f1230a)));
                    if (this.f1239j == null) {
                        z = true;
                    }
                    this.f1240k = z;
                } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
                }
            }
            if (this.f1239j == null && (d = taVar.mo2281d(i)) != null) {
                this.f1239j = Typeface.create(d, this.f1238i);
            }
        } else if (taVar.mo2287g(C0145j.TextAppearance_android_typeface)) {
            this.f1240k = false;
            int d2 = taVar.mo2280d(C0145j.TextAppearance_android_typeface, 1);
            if (d2 == 1) {
                typeface = Typeface.SANS_SERIF;
            } else if (d2 == 2) {
                typeface = Typeface.SERIF;
            } else if (d2 == 3) {
                typeface = Typeface.MONOSPACE;
            } else {
                return;
            }
            this.f1239j = typeface;
        }
    }

    /* renamed from: a */
    private void m1478a(Drawable drawable, C0435ra raVar) {
        if (drawable != null && raVar != null) {
            C0423o.m1855a(drawable, raVar, this.f1230a.getDrawableState());
        }
    }

    /* renamed from: b */
    private void m1479b(int i, float f) {
        this.f1237h.mo1767a(i, f);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1720a() {
        if (!(this.f1231b == null && this.f1232c == null && this.f1233d == null && this.f1234e == null)) {
            Drawable[] compoundDrawables = this.f1230a.getCompoundDrawables();
            m1478a(compoundDrawables[0], this.f1231b);
            m1478a(compoundDrawables[1], this.f1232c);
            m1478a(compoundDrawables[2], this.f1233d);
            m1478a(compoundDrawables[3], this.f1234e);
        }
        if (Build.VERSION.SDK_INT < 17) {
            return;
        }
        if (this.f1235f != null || this.f1236g != null) {
            Drawable[] compoundDrawablesRelative = this.f1230a.getCompoundDrawablesRelative();
            m1478a(compoundDrawablesRelative[0], this.f1235f);
            m1478a(compoundDrawablesRelative[2], this.f1236g);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1721a(int i) {
        this.f1237h.mo1766a(i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1722a(int i, float f) {
        if (!C0204b.f538a && !mo1736h()) {
            m1479b(i, f);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1723a(int i, int i2, int i3, int i4) {
        this.f1237h.mo1768a(i, i2, i3, i4);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1724a(Context context, int i) {
        ColorStateList a;
        C0439ta a2 = C0439ta.m1900a(context, i, C0145j.TextAppearance);
        if (a2.mo2287g(C0145j.TextAppearance_textAllCaps)) {
            mo1727a(a2.mo2275a(C0145j.TextAppearance_textAllCaps, false));
        }
        if (Build.VERSION.SDK_INT < 23 && a2.mo2287g(C0145j.TextAppearance_android_textColor) && (a = a2.mo2272a(C0145j.TextAppearance_android_textColor)) != null) {
            this.f1230a.setTextColor(a);
        }
        if (a2.mo2287g(C0145j.TextAppearance_android_textSize) && a2.mo2278c(C0145j.TextAppearance_android_textSize, -1) == 0) {
            this.f1230a.setTextSize(0, 0.0f);
        }
        m1477a(context, a2);
        a2.mo2274a();
        Typeface typeface = this.f1239j;
        if (typeface != null) {
            this.f1230a.setTypeface(typeface, this.f1238i);
        }
    }

    /* access modifiers changed from: package-private */
    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public void mo1725a(AttributeSet attributeSet, int i) {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        boolean z;
        boolean z2;
        AttributeSet attributeSet2 = attributeSet;
        int i2 = i;
        Context context = this.f1230a.getContext();
        C0423o a = C0423o.m1851a();
        C0439ta a2 = C0439ta.m1902a(context, attributeSet2, C0145j.AppCompatTextHelper, i2, 0);
        int g = a2.mo2286g(C0145j.AppCompatTextHelper_android_textAppearance, -1);
        if (a2.mo2287g(C0145j.AppCompatTextHelper_android_drawableLeft)) {
            this.f1231b = m1476a(context, a, a2.mo2286g(C0145j.AppCompatTextHelper_android_drawableLeft, 0));
        }
        if (a2.mo2287g(C0145j.AppCompatTextHelper_android_drawableTop)) {
            this.f1232c = m1476a(context, a, a2.mo2286g(C0145j.AppCompatTextHelper_android_drawableTop, 0));
        }
        if (a2.mo2287g(C0145j.AppCompatTextHelper_android_drawableRight)) {
            this.f1233d = m1476a(context, a, a2.mo2286g(C0145j.AppCompatTextHelper_android_drawableRight, 0));
        }
        if (a2.mo2287g(C0145j.AppCompatTextHelper_android_drawableBottom)) {
            this.f1234e = m1476a(context, a, a2.mo2286g(C0145j.AppCompatTextHelper_android_drawableBottom, 0));
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (a2.mo2287g(C0145j.AppCompatTextHelper_android_drawableStart)) {
                this.f1235f = m1476a(context, a, a2.mo2286g(C0145j.AppCompatTextHelper_android_drawableStart, 0));
            }
            if (a2.mo2287g(C0145j.AppCompatTextHelper_android_drawableEnd)) {
                this.f1236g = m1476a(context, a, a2.mo2286g(C0145j.AppCompatTextHelper_android_drawableEnd, 0));
            }
        }
        a2.mo2274a();
        boolean z3 = this.f1230a.getTransformationMethod() instanceof PasswordTransformationMethod;
        boolean z4 = true;
        ColorStateList colorStateList3 = null;
        if (g != -1) {
            C0439ta a3 = C0439ta.m1900a(context, g, C0145j.TextAppearance);
            if (z3 || !a3.mo2287g(C0145j.TextAppearance_textAllCaps)) {
                z2 = false;
                z = false;
            } else {
                z = a3.mo2275a(C0145j.TextAppearance_textAllCaps, false);
                z2 = true;
            }
            m1477a(context, a3);
            if (Build.VERSION.SDK_INT < 23) {
                ColorStateList a4 = a3.mo2287g(C0145j.TextAppearance_android_textColor) ? a3.mo2272a(C0145j.TextAppearance_android_textColor) : null;
                colorStateList = a3.mo2287g(C0145j.TextAppearance_android_textColorHint) ? a3.mo2272a(C0145j.TextAppearance_android_textColorHint) : null;
                if (a3.mo2287g(C0145j.TextAppearance_android_textColorLink)) {
                    colorStateList3 = a3.mo2272a(C0145j.TextAppearance_android_textColorLink);
                }
                ColorStateList colorStateList4 = a4;
                colorStateList2 = colorStateList3;
                colorStateList3 = colorStateList4;
            } else {
                colorStateList2 = null;
                colorStateList = null;
            }
            a3.mo2274a();
        } else {
            colorStateList2 = null;
            colorStateList = null;
            z2 = false;
            z = false;
        }
        C0439ta a5 = C0439ta.m1902a(context, attributeSet2, C0145j.TextAppearance, i2, 0);
        if (z3 || !a5.mo2287g(C0145j.TextAppearance_textAllCaps)) {
            z4 = z2;
        } else {
            z = a5.mo2275a(C0145j.TextAppearance_textAllCaps, false);
        }
        if (Build.VERSION.SDK_INT < 23) {
            if (a5.mo2287g(C0145j.TextAppearance_android_textColor)) {
                colorStateList3 = a5.mo2272a(C0145j.TextAppearance_android_textColor);
            }
            if (a5.mo2287g(C0145j.TextAppearance_android_textColorHint)) {
                colorStateList = a5.mo2272a(C0145j.TextAppearance_android_textColorHint);
            }
            if (a5.mo2287g(C0145j.TextAppearance_android_textColorLink)) {
                colorStateList2 = a5.mo2272a(C0145j.TextAppearance_android_textColorLink);
            }
        }
        if (Build.VERSION.SDK_INT >= 28 && a5.mo2287g(C0145j.TextAppearance_android_textSize) && a5.mo2278c(C0145j.TextAppearance_android_textSize, -1) == 0) {
            this.f1230a.setTextSize(0, 0.0f);
        }
        m1477a(context, a5);
        a5.mo2274a();
        if (colorStateList3 != null) {
            this.f1230a.setTextColor(colorStateList3);
        }
        if (colorStateList != null) {
            this.f1230a.setHintTextColor(colorStateList);
        }
        if (colorStateList2 != null) {
            this.f1230a.setLinkTextColor(colorStateList2);
        }
        if (!z3 && z4) {
            mo1727a(z);
        }
        Typeface typeface = this.f1239j;
        if (typeface != null) {
            this.f1230a.setTypeface(typeface, this.f1238i);
        }
        this.f1237h.mo1769a(attributeSet2, i2);
        if (C0204b.f538a && this.f1237h.mo1775f() != 0) {
            int[] e = this.f1237h.mo1774e();
            if (e.length > 0) {
                if (((float) this.f1230a.getAutoSizeStepGranularity()) != -1.0f) {
                    this.f1230a.setAutoSizeTextTypeUniformWithConfiguration(this.f1237h.mo1772c(), this.f1237h.mo1771b(), this.f1237h.mo1773d(), 0);
                } else {
                    this.f1230a.setAutoSizeTextTypeUniformWithPresetSizes(e, 0);
                }
            }
        }
        C0439ta a6 = C0439ta.m1901a(context, attributeSet2, C0145j.AppCompatTextView);
        int c = a6.mo2278c(C0145j.AppCompatTextView_firstBaselineToTopHeight, -1);
        int c2 = a6.mo2278c(C0145j.AppCompatTextView_lastBaselineToBottomHeight, -1);
        int c3 = a6.mo2278c(C0145j.AppCompatTextView_lineHeight, -1);
        a6.mo2274a();
        if (c != -1) {
            C0218m.m808a(this.f1230a, c);
        }
        if (c2 != -1) {
            C0218m.m812b(this.f1230a, c2);
        }
        if (c3 != -1) {
            C0218m.m814c(this.f1230a, c3);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1726a(WeakReference<TextView> weakReference, Typeface typeface) {
        if (this.f1240k) {
            this.f1239j = typeface;
            TextView textView = (TextView) weakReference.get();
            if (textView != null) {
                textView.setTypeface(typeface, this.f1238i);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1727a(boolean z) {
        this.f1230a.setAllCaps(z);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1728a(boolean z, int i, int i2, int i3, int i4) {
        if (!C0204b.f538a) {
            mo1730b();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1729a(int[] iArr, int i) {
        this.f1237h.mo1770a(iArr, i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo1730b() {
        this.f1237h.mo1765a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public int mo1731c() {
        return this.f1237h.mo1771b();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public int mo1732d() {
        return this.f1237h.mo1772c();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public int mo1733e() {
        return this.f1237h.mo1773d();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public int[] mo1734f() {
        return this.f1237h.mo1774e();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public int mo1735g() {
        return this.f1237h.mo1775f();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: h */
    public boolean mo1736h() {
        return this.f1237h.mo1776g();
    }
}

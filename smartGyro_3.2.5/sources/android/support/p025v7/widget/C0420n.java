package android.support.p025v7.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.p024v4.graphics.drawable.C0190a;
import android.support.p024v4.widget.C0205c;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import p000a.p001a.p017d.p018a.C0145j;
import p000a.p001a.p017d.p019b.p020a.C0146a;

/* renamed from: android.support.v7.widget.n */
class C0420n {

    /* renamed from: a */
    private final CompoundButton f1571a;

    /* renamed from: b */
    private ColorStateList f1572b = null;

    /* renamed from: c */
    private PorterDuff.Mode f1573c = null;

    /* renamed from: d */
    private boolean f1574d = false;

    /* renamed from: e */
    private boolean f1575e = false;

    /* renamed from: f */
    private boolean f1576f;

    C0420n(CompoundButton compoundButton) {
        this.f1571a = compoundButton;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = android.support.p024v4.widget.C0205c.m777a(r2.f1571a);
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int mo2210a(int r3) {
        /*
            r2 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 17
            if (r0 >= r1) goto L_0x0013
            android.widget.CompoundButton r0 = r2.f1571a
            android.graphics.drawable.Drawable r0 = android.support.p024v4.widget.C0205c.m777a(r0)
            if (r0 == 0) goto L_0x0013
            int r0 = r0.getIntrinsicWidth()
            int r3 = r3 + r0
        L_0x0013:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.widget.C0420n.mo2210a(int):int");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo2211a() {
        Drawable a = C0205c.m777a(this.f1571a);
        if (a == null) {
            return;
        }
        if (this.f1574d || this.f1575e) {
            Drawable mutate = C0190a.m687g(a).mutate();
            if (this.f1574d) {
                C0190a.m674a(mutate, this.f1572b);
            }
            if (this.f1575e) {
                C0190a.m677a(mutate, this.f1573c);
            }
            if (mutate.isStateful()) {
                mutate.setState(this.f1571a.getDrawableState());
            }
            this.f1571a.setButtonDrawable(mutate);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo2212a(ColorStateList colorStateList) {
        this.f1572b = colorStateList;
        this.f1574d = true;
        mo2211a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo2213a(PorterDuff.Mode mode) {
        this.f1573c = mode;
        this.f1575e = true;
        mo2211a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo2214a(AttributeSet attributeSet, int i) {
        int resourceId;
        TypedArray obtainStyledAttributes = this.f1571a.getContext().obtainStyledAttributes(attributeSet, C0145j.CompoundButton, i, 0);
        try {
            if (obtainStyledAttributes.hasValue(C0145j.CompoundButton_android_button) && (resourceId = obtainStyledAttributes.getResourceId(C0145j.CompoundButton_android_button, 0)) != 0) {
                this.f1571a.setButtonDrawable(C0146a.m492b(this.f1571a.getContext(), resourceId));
            }
            if (obtainStyledAttributes.hasValue(C0145j.CompoundButton_buttonTint)) {
                C0205c.m778a(this.f1571a, obtainStyledAttributes.getColorStateList(C0145j.CompoundButton_buttonTint));
            }
            if (obtainStyledAttributes.hasValue(C0145j.CompoundButton_buttonTintMode)) {
                C0205c.m779a(this.f1571a, C0348M.m1559a(obtainStyledAttributes.getInt(C0145j.CompoundButton_buttonTintMode, -1), (PorterDuff.Mode) null));
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public ColorStateList mo2215b() {
        return this.f1572b;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public PorterDuff.Mode mo2216c() {
        return this.f1573c;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo2217d() {
        if (this.f1576f) {
            this.f1576f = false;
            return;
        }
        this.f1576f = true;
        mo2211a();
    }
}

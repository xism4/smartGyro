package android.support.p025v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.p024v4.widget.C0220n;
import android.util.AttributeSet;
import android.widget.RadioButton;
import p000a.p001a.p017d.p018a.C0136a;
import p000a.p001a.p017d.p019b.p020a.C0146a;

/* renamed from: android.support.v7.widget.x */
public class C0446x extends RadioButton implements C0220n {

    /* renamed from: a */
    private final C0420n f1655a;

    /* renamed from: b */
    private final C0341H f1656b;

    public C0446x(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0136a.radioButtonStyle);
    }

    public C0446x(Context context, AttributeSet attributeSet, int i) {
        super(C0433qa.m1887a(context), attributeSet, i);
        this.f1655a = new C0420n(this);
        this.f1655a.mo2214a(attributeSet, i);
        this.f1656b = new C0341H(this);
        this.f1656b.mo1725a(attributeSet, i);
    }

    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        C0420n nVar = this.f1655a;
        return nVar != null ? nVar.mo2210a(compoundPaddingLeft) : compoundPaddingLeft;
    }

    public ColorStateList getSupportButtonTintList() {
        C0420n nVar = this.f1655a;
        if (nVar != null) {
            return nVar.mo2215b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        C0420n nVar = this.f1655a;
        if (nVar != null) {
            return nVar.mo2216c();
        }
        return null;
    }

    public void setButtonDrawable(int i) {
        setButtonDrawable(C0146a.m492b(getContext(), i));
    }

    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        C0420n nVar = this.f1655a;
        if (nVar != null) {
            nVar.mo2217d();
        }
    }

    public void setSupportButtonTintList(ColorStateList colorStateList) {
        C0420n nVar = this.f1655a;
        if (nVar != null) {
            nVar.mo2212a(colorStateList);
        }
    }

    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        C0420n nVar = this.f1655a;
        if (nVar != null) {
            nVar.mo2213a(mode);
        }
    }
}

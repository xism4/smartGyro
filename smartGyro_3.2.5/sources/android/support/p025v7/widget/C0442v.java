package android.support.p025v7.widget;

import android.content.Context;
import android.os.Build;
import android.support.p024v4.widget.C0216k;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;
import p000a.p001a.p017d.p018a.C0145j;

/* renamed from: android.support.v7.widget.v */
class C0442v extends PopupWindow {

    /* renamed from: a */
    private static final boolean f1648a = (Build.VERSION.SDK_INT < 21);

    /* renamed from: b */
    private boolean f1649b;

    public C0442v(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        m1921a(context, attributeSet, i, i2);
    }

    /* renamed from: a */
    private void m1921a(Context context, AttributeSet attributeSet, int i, int i2) {
        C0439ta a = C0439ta.m1902a(context, attributeSet, C0145j.PopupWindow, i, i2);
        if (a.mo2287g(C0145j.PopupWindow_overlapAnchor)) {
            m1922a(a.mo2275a(C0145j.PopupWindow_overlapAnchor, false));
        }
        setBackgroundDrawable(a.mo2277b(C0145j.PopupWindow_android_popupBackground));
        a.mo2274a();
    }

    /* renamed from: a */
    private void m1922a(boolean z) {
        if (f1648a) {
            this.f1649b = z;
        } else {
            C0216k.m802a((PopupWindow) this, z);
        }
    }

    public void showAsDropDown(View view, int i, int i2) {
        if (f1648a && this.f1649b) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2);
    }

    public void showAsDropDown(View view, int i, int i2, int i3) {
        if (f1648a && this.f1649b) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2, i3);
    }

    public void update(View view, int i, int i2, int i3, int i4) {
        if (f1648a && this.f1649b) {
            i2 -= view.getHeight();
        }
        super.update(view, i, i2, i3, i4);
    }
}

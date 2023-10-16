package android.support.p025v7.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.p024v4.graphics.drawable.C0190a;
import android.util.AttributeSet;
import android.widget.SeekBar;
import p000a.p001a.p005c.p014g.C0127u;
import p000a.p001a.p017d.p018a.C0145j;

/* renamed from: android.support.v7.widget.A */
class C0317A extends C0444w {

    /* renamed from: d */
    private final SeekBar f1079d;

    /* renamed from: e */
    private Drawable f1080e;

    /* renamed from: f */
    private ColorStateList f1081f = null;

    /* renamed from: g */
    private PorterDuff.Mode f1082g = null;

    /* renamed from: h */
    private boolean f1083h = false;

    /* renamed from: i */
    private boolean f1084i = false;

    C0317A(SeekBar seekBar) {
        super(seekBar);
        this.f1079d = seekBar;
    }

    /* renamed from: d */
    private void m1343d() {
        if (this.f1080e == null) {
            return;
        }
        if (this.f1083h || this.f1084i) {
            this.f1080e = C0190a.m687g(this.f1080e.mutate());
            if (this.f1083h) {
                C0190a.m674a(this.f1080e, this.f1081f);
            }
            if (this.f1084i) {
                C0190a.m677a(this.f1080e, this.f1082g);
            }
            if (this.f1080e.isStateful()) {
                this.f1080e.setState(this.f1079d.getDrawableState());
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1490a(Canvas canvas) {
        if (this.f1080e != null) {
            int max = this.f1079d.getMax();
            int i = 1;
            if (max > 1) {
                int intrinsicWidth = this.f1080e.getIntrinsicWidth();
                int intrinsicHeight = this.f1080e.getIntrinsicHeight();
                int i2 = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                if (intrinsicHeight >= 0) {
                    i = intrinsicHeight / 2;
                }
                this.f1080e.setBounds(-i2, -i, i2, i);
                float width = ((float) ((this.f1079d.getWidth() - this.f1079d.getPaddingLeft()) - this.f1079d.getPaddingRight())) / ((float) max);
                int save = canvas.save();
                canvas.translate((float) this.f1079d.getPaddingLeft(), (float) (this.f1079d.getHeight() / 2));
                for (int i3 = 0; i3 <= max; i3++) {
                    this.f1080e.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(save);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1491a(Drawable drawable) {
        Drawable drawable2 = this.f1080e;
        if (drawable2 != null) {
            drawable2.setCallback((Drawable.Callback) null);
        }
        this.f1080e = drawable;
        if (drawable != null) {
            drawable.setCallback(this.f1079d);
            C0190a.m680a(drawable, C0127u.m450d(this.f1079d));
            if (drawable.isStateful()) {
                drawable.setState(this.f1079d.getDrawableState());
            }
            m1343d();
        }
        this.f1079d.invalidate();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1492a(AttributeSet attributeSet, int i) {
        super.mo1492a(attributeSet, i);
        C0439ta a = C0439ta.m1902a(this.f1079d.getContext(), attributeSet, C0145j.AppCompatSeekBar, i, 0);
        Drawable c = a.mo2279c(C0145j.AppCompatSeekBar_android_thumb);
        if (c != null) {
            this.f1079d.setThumb(c);
        }
        mo1491a(a.mo2277b(C0145j.AppCompatSeekBar_tickMark));
        if (a.mo2287g(C0145j.AppCompatSeekBar_tickMarkTintMode)) {
            this.f1082g = C0348M.m1559a(a.mo2280d(C0145j.AppCompatSeekBar_tickMarkTintMode, -1), this.f1082g);
            this.f1084i = true;
        }
        if (a.mo2287g(C0145j.AppCompatSeekBar_tickMarkTint)) {
            this.f1081f = a.mo2272a(C0145j.AppCompatSeekBar_tickMarkTint);
            this.f1083h = true;
        }
        a.mo2274a();
        m1343d();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo1493b() {
        Drawable drawable = this.f1080e;
        if (drawable != null && drawable.isStateful() && drawable.setState(this.f1079d.getDrawableState())) {
            this.f1079d.invalidateDrawable(drawable);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo1494c() {
        Drawable drawable = this.f1080e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }
}

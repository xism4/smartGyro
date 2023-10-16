package p000a.p001a.p017d.p021c.p022a;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.support.p024v4.graphics.drawable.C0190a;

/* renamed from: a.a.d.c.a.e */
public class C0160e extends Drawable implements Drawable.Callback {

    /* renamed from: a */
    private Drawable f370a;

    public C0160e(Drawable drawable) {
        mo603a(drawable);
    }

    /* renamed from: a */
    public Drawable mo602a() {
        return this.f370a;
    }

    /* renamed from: a */
    public void mo603a(Drawable drawable) {
        Drawable drawable2 = this.f370a;
        if (drawable2 != null) {
            drawable2.setCallback((Drawable.Callback) null);
        }
        this.f370a = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
    }

    public void draw(Canvas canvas) {
        this.f370a.draw(canvas);
    }

    public int getChangingConfigurations() {
        return this.f370a.getChangingConfigurations();
    }

    public Drawable getCurrent() {
        return this.f370a.getCurrent();
    }

    public int getIntrinsicHeight() {
        return this.f370a.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        return this.f370a.getIntrinsicWidth();
    }

    public int getMinimumHeight() {
        return this.f370a.getMinimumHeight();
    }

    public int getMinimumWidth() {
        return this.f370a.getMinimumWidth();
    }

    public int getOpacity() {
        return this.f370a.getOpacity();
    }

    public boolean getPadding(Rect rect) {
        return this.f370a.getPadding(rect);
    }

    public int[] getState() {
        return this.f370a.getState();
    }

    public Region getTransparentRegion() {
        return this.f370a.getTransparentRegion();
    }

    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    public boolean isAutoMirrored() {
        return C0190a.m685e(this.f370a);
    }

    public boolean isStateful() {
        return this.f370a.isStateful();
    }

    public void jumpToCurrentState() {
        C0190a.m686f(this.f370a);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        this.f370a.setBounds(rect);
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        return this.f370a.setLevel(i);
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    public void setAlpha(int i) {
        this.f370a.setAlpha(i);
    }

    public void setAutoMirrored(boolean z) {
        C0190a.m678a(this.f370a, z);
    }

    public void setChangingConfigurations(int i) {
        this.f370a.setChangingConfigurations(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f370a.setColorFilter(colorFilter);
    }

    public void setDither(boolean z) {
        this.f370a.setDither(z);
    }

    public void setFilterBitmap(boolean z) {
        this.f370a.setFilterBitmap(z);
    }

    public void setHotspot(float f, float f2) {
        C0190a.m672a(this.f370a, f, f2);
    }

    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        C0190a.m673a(this.f370a, i, i2, i3, i4);
    }

    public boolean setState(int[] iArr) {
        return this.f370a.setState(iArr);
    }

    public void setTint(int i) {
        C0190a.m682b(this.f370a, i);
    }

    public void setTintList(ColorStateList colorStateList) {
        C0190a.m674a(this.f370a, colorStateList);
    }

    public void setTintMode(PorterDuff.Mode mode) {
        C0190a.m677a(this.f370a, mode);
    }

    public boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2) || this.f370a.setVisible(z, z2);
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }
}

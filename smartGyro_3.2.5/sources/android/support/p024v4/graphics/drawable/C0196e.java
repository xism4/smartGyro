package android.support.p024v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.p024v4.graphics.drawable.C0193d;
import android.util.Log;
import java.lang.reflect.Method;

/* renamed from: android.support.v4.graphics.drawable.e */
class C0196e extends C0193d {

    /* renamed from: h */
    private static Method f478h;

    /* renamed from: android.support.v4.graphics.drawable.e$a */
    private static class C0197a extends C0193d.C0194a {
        C0197a(C0193d.C0194a aVar, Resources resources) {
            super(aVar, resources);
        }

        public Drawable newDrawable(Resources resources) {
            return new C0196e(this, resources);
        }
    }

    C0196e(Drawable drawable) {
        super(drawable);
        m697d();
    }

    C0196e(C0193d.C0194a aVar, Resources resources) {
        super(aVar, resources);
        m697d();
    }

    /* renamed from: d */
    private void m697d() {
        if (f478h == null) {
            try {
                f478h = Drawable.class.getDeclaredMethod("isProjected", new Class[0]);
            } catch (Exception e) {
                Log.w("WrappedDrawableApi21", "Failed to retrieve Drawable#isProjected() method", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public boolean mo756b() {
        if (Build.VERSION.SDK_INT != 21) {
            return false;
        }
        Drawable drawable = this.f473g;
        return (drawable instanceof GradientDrawable) || (drawable instanceof DrawableContainer) || (drawable instanceof InsetDrawable) || (drawable instanceof RippleDrawable);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public C0193d.C0194a mo757c() {
        return new C0197a(this.f471e, (Resources) null);
    }

    public Rect getDirtyBounds() {
        return this.f473g.getDirtyBounds();
    }

    public void getOutline(Outline outline) {
        this.f473g.getOutline(outline);
    }

    public boolean isProjected() {
        Method method;
        Drawable drawable = this.f473g;
        if (!(drawable == null || (method = f478h) == null)) {
            try {
                return ((Boolean) method.invoke(drawable, new Object[0])).booleanValue();
            } catch (Exception e) {
                Log.w("WrappedDrawableApi21", "Error calling Drawable#isProjected() method", e);
            }
        }
        return false;
    }

    public void setHotspot(float f, float f2) {
        this.f473g.setHotspot(f, f2);
    }

    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        this.f473g.setHotspotBounds(i, i2, i3, i4);
    }

    public boolean setState(int[] iArr) {
        if (!super.setState(iArr)) {
            return false;
        }
        invalidateSelf();
        return true;
    }

    public void setTint(int i) {
        if (mo756b()) {
            super.setTint(i);
        } else {
            this.f473g.setTint(i);
        }
    }

    public void setTintList(ColorStateList colorStateList) {
        if (mo756b()) {
            super.setTintList(colorStateList);
        } else {
            this.f473g.setTintList(colorStateList);
        }
    }

    public void setTintMode(PorterDuff.Mode mode) {
        if (mo756b()) {
            super.setTintMode(mode);
        } else {
            this.f473g.setTintMode(mode);
        }
    }
}

package android.support.v4.graphics.drawable;

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
import android.support.v4.graphics.drawable.DrawableWrapperDonut;
import android.util.Log;
import java.lang.reflect.Method;

class DrawableWrapperLollipop extends DrawableWrapperDonut {
    private static Method mSetExclusiveCheckableMethod;

    class DrawableWrapperStateLollipop extends DrawableWrapperDonut.DrawableWrapperState {
        DrawableWrapperStateLollipop(DrawableWrapperDonut.DrawableWrapperState drawableWrapperState, Resources resources) {
            super(drawableWrapperState, resources);
        }

        public Drawable newDrawable(Resources resources) {
            return new DrawableWrapperLollipop(this, resources);
        }
    }

    DrawableWrapperLollipop(Drawable drawable) {
        super(drawable);
        setItem();
    }

    DrawableWrapperLollipop(DrawableWrapperDonut.DrawableWrapperState drawableWrapperState, Resources resources) {
        super(drawableWrapperState, resources);
        setItem();
    }

    private void setItem() {
        if (mSetExclusiveCheckableMethod == null) {
            try {
                mSetExclusiveCheckableMethod = Drawable.class.getDeclaredMethod("isProjected", new Class[0]);
            } catch (Exception $r4) {
                Log.w("WrappedDrawableApi21", "Failed to retrieve Drawable#isProjected() method", $r4);
            }
        }
    }

    public Rect getDirtyBounds() {
        return this.mDrawable.getDirtyBounds();
    }

    public void getOutline(Outline outline) {
        this.mDrawable.getOutline(outline);
    }

    /* access modifiers changed from: protected */
    public boolean isCompatTintEnabled() {
        if (Build.VERSION.SDK_INT != 21) {
            return false;
        }
        Drawable $r1 = this.mDrawable;
        return ($r1 instanceof GradientDrawable) || ($r1 instanceof DrawableContainer) || ($r1 instanceof InsetDrawable) || ($r1 instanceof RippleDrawable);
    }

    public boolean isProjected() {
        Method $r2;
        Drawable $r1 = this.mDrawable;
        if ($r1 == null || ($r2 = mSetExclusiveCheckableMethod) == null) {
            return false;
        }
        try {
            return ((Boolean) $r2.invoke($r1, new Object[0])).booleanValue();
        } catch (Exception $r6) {
            Log.w("WrappedDrawableApi21", "Error calling Drawable#isProjected() method", $r6);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public DrawableWrapperDonut.DrawableWrapperState mutateConstantState() {
        return new DrawableWrapperStateLollipop(this.mState, (Resources) null);
    }

    public void setHotspot(float f, float f2) {
        this.mDrawable.setHotspot(f, f2);
    }

    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        this.mDrawable.setHotspotBounds(i, i2, i3, i4);
    }

    public boolean setState(int[] iArr) {
        if (!super.setState(iArr)) {
            return false;
        }
        invalidateSelf();
        return true;
    }

    public void setTint(int i) {
        if (isCompatTintEnabled()) {
            super.setTint(i);
        } else {
            this.mDrawable.setTint(i);
        }
    }

    public void setTintList(ColorStateList colorStateList) {
        if (isCompatTintEnabled()) {
            super.setTintList(colorStateList);
        } else {
            this.mDrawable.setTintList(colorStateList);
        }
    }

    public void setTintMode(PorterDuff.Mode mode) {
        if (isCompatTintEnabled()) {
            super.setTintMode(mode);
        } else {
            this.mDrawable.setTintMode(mode);
        }
    }
}

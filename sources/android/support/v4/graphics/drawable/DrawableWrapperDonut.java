package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;

class DrawableWrapperDonut extends Drawable implements Drawable.Callback, Drawable, DrawableWrapper {
    static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
    private boolean mColorFilterSet;
    private int mCurrentColor;
    private PorterDuff.Mode mCurrentMode;
    Drawable mDrawable;
    private boolean mMutated;
    DrawableWrapperState mState;

    public abstract class DrawableWrapperState extends Drawable.ConstantState {
        int mChangingConfigurations;
        Drawable.ConstantState mDrawableState;
        ColorStateList mTint = null;
        PorterDuff.Mode mTintMode = DrawableWrapperDonut.DEFAULT_TINT_MODE;

        DrawableWrapperState(DrawableWrapperState drawableWrapperState, Resources resources) {
            if (drawableWrapperState != null) {
                this.mChangingConfigurations = drawableWrapperState.mChangingConfigurations;
                this.mDrawableState = drawableWrapperState.mDrawableState;
                this.mTint = drawableWrapperState.mTint;
                this.mTintMode = drawableWrapperState.mTintMode;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean canConstantState() {
            return this.mDrawableState != null;
        }

        public int getChangingConfigurations() {
            int $i1 = this.mChangingConfigurations;
            Drawable.ConstantState $r1 = this.mDrawableState;
            return $i1 | ($r1 != null ? $r1.getChangingConfigurations() : 0);
        }

        public Drawable newDrawable() {
            return newDrawable((Resources) null);
        }

        public abstract Drawable newDrawable(Resources resources);
    }

    DrawableWrapperDonut(Drawable drawable) {
        this.mState = mutateConstantState();
        setWrappedDrawable(drawable);
    }

    DrawableWrapperDonut(DrawableWrapperState drawableWrapperState, Resources resources) {
        this.mState = drawableWrapperState;
        updateLocalState(resources);
    }

    private void updateLocalState(Resources resources) {
        Drawable.ConstantState $r4;
        DrawableWrapperState $r3 = this.mState;
        if ($r3 != null && ($r4 = $r3.mDrawableState) != null) {
            setWrappedDrawable($r4.newDrawable(resources));
        }
    }

    private boolean updateTint(int[] iArr) {
        if (!isCompatTintEnabled()) {
            return false;
        }
        DrawableWrapperState $r2 = this.mState;
        ColorStateList $r3 = $r2.mTint;
        PorterDuff.Mode $r4 = $r2.mTintMode;
        if ($r3 == null || $r4 == null) {
            this.mColorFilterSet = false;
            clearColorFilter();
            return false;
        }
        int $i0 = $r3.getColorForState(iArr, $r3.getDefaultColor());
        if (this.mColorFilterSet && $i0 == this.mCurrentColor && $r4 == this.mCurrentMode) {
            return false;
        }
        setColorFilter($i0, $r4);
        this.mCurrentColor = $i0;
        this.mCurrentMode = $r4;
        this.mColorFilterSet = true;
        return true;
    }

    public void draw(Canvas canvas) {
        this.mDrawable.draw(canvas);
    }

    public int getChangingConfigurations() {
        int $i0 = super.getChangingConfigurations();
        DrawableWrapperState $r1 = this.mState;
        return $i0 | ($r1 != null ? $r1.getChangingConfigurations() : 0) | this.mDrawable.getChangingConfigurations();
    }

    public Drawable.ConstantState getConstantState() {
        DrawableWrapperState $r1 = this.mState;
        if ($r1 == null || !$r1.canConstantState()) {
            return null;
        }
        this.mState.mChangingConfigurations = getChangingConfigurations();
        return this.mState;
    }

    public Drawable getCurrent() {
        return this.mDrawable.getCurrent();
    }

    public int getIntrinsicHeight() {
        return this.mDrawable.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        return this.mDrawable.getIntrinsicWidth();
    }

    public int getMinimumHeight() {
        return this.mDrawable.getMinimumHeight();
    }

    public int getMinimumWidth() {
        return this.mDrawable.getMinimumWidth();
    }

    public int getOpacity() {
        return this.mDrawable.getOpacity();
    }

    public boolean getPadding(Rect rect) {
        return this.mDrawable.getPadding(rect);
    }

    public int[] getState() {
        return this.mDrawable.getState();
    }

    public Region getTransparentRegion() {
        return this.mDrawable.getTransparentRegion();
    }

    public final Drawable getWrappedDrawable() {
        return this.mDrawable;
    }

    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    public boolean isAutoMirrored() {
        return this.mDrawable.isAutoMirrored();
    }

    /* access modifiers changed from: protected */
    public boolean isCompatTintEnabled() {
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r1 = r5.mState;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isStateful() {
        /*
            r5 = this;
            boolean r0 = r5.isCompatTintEnabled()
            if (r0 == 0) goto L_0x000d
            android.support.v4.graphics.drawable.DrawableWrapperDonut$DrawableWrapperState r1 = r5.mState
            if (r1 == 0) goto L_0x000d
            android.content.res.ColorStateList r2 = r1.mTint
            goto L_0x000e
        L_0x000d:
            r2 = 0
        L_0x000e:
            if (r2 == 0) goto L_0x0016
            boolean r0 = r2.isStateful()
            if (r0 != 0) goto L_0x001e
        L_0x0016:
            android.graphics.drawable.Drawable r3 = r5.mDrawable
            boolean r0 = r3.isStateful()
            if (r0 == 0) goto L_0x0020
        L_0x001e:
            r4 = 1
            return r4
        L_0x0020:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.drawable.DrawableWrapperDonut.isStateful():boolean");
    }

    public void jumpToCurrentState() {
        this.mDrawable.jumpToCurrentState();
    }

    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            this.mState = mutateConstantState();
            Drawable $r1 = this.mDrawable;
            if ($r1 != null) {
                $r1.mutate();
            }
            DrawableWrapperState $r2 = this.mState;
            if ($r2 != null) {
                Drawable $r12 = this.mDrawable;
                $r2.mDrawableState = $r12 != null ? $r12.getConstantState() : null;
            }
            this.mMutated = true;
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public DrawableWrapperState mutateConstantState() {
        return new DrawableWrapperEclair$DrawableWrapperStateEclair(this.mState, (Resources) null);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Drawable $r2 = this.mDrawable;
        if ($r2 != null) {
            $r2.setBounds(rect);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        return this.mDrawable.setLevel(i);
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    public void setAlpha(int i) {
        this.mDrawable.setAlpha(i);
    }

    public void setAutoMirrored(boolean z) {
        this.mDrawable.setAutoMirrored(z);
    }

    public void setChangingConfigurations(int i) {
        this.mDrawable.setChangingConfigurations(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mDrawable.setColorFilter(colorFilter);
    }

    public void setDither(boolean z) {
        this.mDrawable.setDither(z);
    }

    public void setFilterBitmap(boolean z) {
        this.mDrawable.setFilterBitmap(z);
    }

    public boolean setState(int[] iArr) {
        return updateTint(iArr) || this.mDrawable.setState(iArr);
    }

    public void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    public void setTintList(ColorStateList colorStateList) {
        this.mState.mTint = colorStateList;
        updateTint(getState());
    }

    public void setTintMode(PorterDuff.Mode mode) {
        this.mState.mTintMode = mode;
        updateTint(getState());
    }

    public boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2) || this.mDrawable.setVisible(z, z2);
    }

    public final void setWrappedDrawable(Drawable drawable) {
        Drawable $r2 = this.mDrawable;
        if ($r2 != null) {
            $r2.setCallback((Drawable.Callback) null);
        }
        this.mDrawable = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            setVisible(drawable.isVisible(), true);
            setState(drawable.getState());
            setLevel(drawable.getLevel());
            setBounds(drawable.getBounds());
            DrawableWrapperState $r5 = this.mState;
            if ($r5 != null) {
                $r5.mDrawableState = drawable.getConstantState();
            }
        }
        invalidateSelf();
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }
}

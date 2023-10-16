package com.org.shortcuts.drawable;

import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.graphics.drawable.DrawableWrapper;

abstract class VectorDrawableCommon extends Drawable implements DrawableWrapper {
    Drawable mDelegateDrawable;

    VectorDrawableCommon() {
    }

    public void applyTheme(Resources.Theme theme) {
        Drawable $r2 = this.mDelegateDrawable;
        if ($r2 != null) {
            DrawableCompat.applyTheme($r2, theme);
        }
    }

    public void clearColorFilter() {
        Drawable $r1 = this.mDelegateDrawable;
        if ($r1 != null) {
            $r1.clearColorFilter();
        } else {
            super.clearColorFilter();
        }
    }

    public ColorFilter getColorFilter() {
        Drawable $r2 = this.mDelegateDrawable;
        if ($r2 != null) {
            return DrawableCompat.getColorFilter($r2);
        }
        return null;
    }

    public Drawable getCurrent() {
        Drawable $r1 = this.mDelegateDrawable;
        return $r1 != null ? $r1.getCurrent() : super.getCurrent();
    }

    public int getMinimumHeight() {
        Drawable $r1 = this.mDelegateDrawable;
        return $r1 != null ? $r1.getMinimumHeight() : super.getMinimumHeight();
    }

    public int getMinimumWidth() {
        Drawable $r1 = this.mDelegateDrawable;
        return $r1 != null ? $r1.getMinimumWidth() : super.getMinimumWidth();
    }

    public boolean getPadding(Rect rect) {
        Drawable $r1 = this.mDelegateDrawable;
        return $r1 != null ? $r1.getPadding(rect) : super.getPadding(rect);
    }

    public int[] getState() {
        Drawable $r1 = this.mDelegateDrawable;
        return $r1 != null ? $r1.getState() : super.getState();
    }

    public Region getTransparentRegion() {
        Drawable $r1 = this.mDelegateDrawable;
        return $r1 != null ? $r1.getTransparentRegion() : super.getTransparentRegion();
    }

    public void jumpToCurrentState() {
        Drawable $r1 = this.mDelegateDrawable;
        if ($r1 != null) {
            DrawableCompat.jumpToCurrentState($r1);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        Drawable $r1 = this.mDelegateDrawable;
        return $r1 != null ? $r1.setLevel(i) : super.onLevelChange(i);
    }

    public void setChangingConfigurations(int i) {
        Drawable $r1 = this.mDelegateDrawable;
        if ($r1 != null) {
            $r1.setChangingConfigurations(i);
        } else {
            super.setChangingConfigurations(i);
        }
    }

    public void setColorFilter(int i, PorterDuff.Mode mode) {
        Drawable $r2 = this.mDelegateDrawable;
        if ($r2 != null) {
            $r2.setColorFilter(i, mode);
        } else {
            super.setColorFilter(i, mode);
        }
    }

    public void setFilterBitmap(boolean z) {
        Drawable $r1 = this.mDelegateDrawable;
        if ($r1 != null) {
            $r1.setFilterBitmap(z);
        }
    }

    public void setHotspot(float f, float f2) {
        Drawable $r1 = this.mDelegateDrawable;
        if ($r1 != null) {
            DrawableCompat.setHotspot($r1, f, f2);
        }
    }

    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        Drawable $r1 = this.mDelegateDrawable;
        if ($r1 != null) {
            DrawableCompat.setHotspotBounds($r1, i, i2, i3, i4);
        }
    }

    public boolean setState(int[] iArr) {
        Drawable $r1 = this.mDelegateDrawable;
        return $r1 != null ? $r1.setState(iArr) : super.setState(iArr);
    }
}

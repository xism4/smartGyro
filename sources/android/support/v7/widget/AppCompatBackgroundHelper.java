package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import com.org.android.view.ViewCompat;
import com.org.v4.util.R$styleable;

class AppCompatBackgroundHelper {
    private TintInfo mBackgroundTint;
    private final AppCompatDrawableManager mDrawableManager;
    private TintInfo mInternalBackgroundTint;
    private int mOldLayerType = -1;
    private TintInfo mTmpInfo;
    private final View mView;

    AppCompatBackgroundHelper(View view) {
        this.mView = view;
        this.mDrawableManager = AppCompatDrawableManager.get();
    }

    private boolean compatTintDrawableUsingFrameworkTint(Drawable drawable) {
        if (this.mTmpInfo == null) {
            this.mTmpInfo = new TintInfo();
        }
        TintInfo $r2 = this.mTmpInfo;
        $r2.clear();
        ColorStateList $r4 = ViewCompat.getBackgroundTintList(this.mView);
        if ($r4 != null) {
            $r2.mHasTintList = true;
            $r2.mTintList = $r4;
        }
        PorterDuff.Mode $r5 = ViewCompat.getBackgroundTintMode(this.mView);
        if ($r5 != null) {
            $r2.mHasTintMode = true;
            $r2.mTintMode = $r5;
        }
        if (!$r2.mHasTintList && !$r2.mHasTintMode) {
            return false;
        }
        AppCompatDrawableManager.tintDrawable(drawable, $r2, this.mView.getDrawableState());
        return true;
    }

    private boolean shouldCompatTintUsingFrameworkTint() {
        int $i0 = Build.VERSION.SDK_INT;
        return $i0 > 21 ? this.mInternalBackgroundTint != null : $i0 == 21;
    }

    /* access modifiers changed from: package-private */
    public void applySupportBackgroundTint() {
        Drawable $r2 = this.mView.getBackground();
        if ($r2 == null) {
            return;
        }
        if (!shouldCompatTintUsingFrameworkTint() || !compatTintDrawableUsingFrameworkTint($r2)) {
            TintInfo $r3 = this.mBackgroundTint;
            if ($r3 != null) {
                AppCompatDrawableManager.tintDrawable($r2, $r3, this.mView.getDrawableState());
                return;
            }
            TintInfo $r32 = this.mInternalBackgroundTint;
            if ($r32 != null) {
                AppCompatDrawableManager.tintDrawable($r2, $r32, this.mView.getDrawableState());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ColorStateList getSupportBackgroundTintList() {
        TintInfo $r1 = this.mBackgroundTint;
        if ($r1 != null) {
            return $r1.mTintList;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        TintInfo $r1 = this.mBackgroundTint;
        if ($r1 != null) {
            return $r1.mTintMode;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void loadFromAttributes(AttributeSet attributeSet, int i) {
        TintTypedArray $r5 = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), attributeSet, R$styleable.ViewBackgroundHelper, i, 0);
        try {
            if ($r5.hasValue(R$styleable.ViewBackgroundHelper_android_background)) {
                this.mOldLayerType = $r5.getResourceId(R$styleable.ViewBackgroundHelper_android_background, -1);
                ColorStateList $r7 = this.mDrawableManager.getTintList(this.mView.getContext(), this.mOldLayerType);
                if ($r7 != null) {
                    setInternalBackgroundTint($r7);
                }
            }
            if ($r5.hasValue(R$styleable.ViewBackgroundHelper_backgroundTint)) {
                ViewCompat.setBackgroundTintList(this.mView, $r5.getColorStateList(R$styleable.ViewBackgroundHelper_backgroundTint));
            }
            if ($r5.hasValue(R$styleable.ViewBackgroundHelper_backgroundTintMode)) {
                ViewCompat.setBackgroundTintMode(this.mView, DrawableUtils.parseTintMode($r5.getInt(R$styleable.ViewBackgroundHelper_backgroundTintMode, -1), (PorterDuff.Mode) null));
            }
        } finally {
            $r5.recycle();
        }
    }

    /* access modifiers changed from: package-private */
    public void onSetBackgroundDrawable(Drawable drawable) {
        this.mOldLayerType = -1;
        setInternalBackgroundTint((ColorStateList) null);
        applySupportBackgroundTint();
    }

    /* access modifiers changed from: package-private */
    public void onSetBackgroundResource(int i) {
        this.mOldLayerType = i;
        AppCompatDrawableManager $r1 = this.mDrawableManager;
        setInternalBackgroundTint($r1 != null ? $r1.getTintList(this.mView.getContext(), i) : null);
        applySupportBackgroundTint();
    }

    /* access modifiers changed from: package-private */
    public void setInternalBackgroundTint(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.mInternalBackgroundTint == null) {
                this.mInternalBackgroundTint = new TintInfo();
            }
            TintInfo $r2 = this.mInternalBackgroundTint;
            $r2.mTintList = colorStateList;
            $r2.mHasTintList = true;
        } else {
            this.mInternalBackgroundTint = null;
        }
        applySupportBackgroundTint();
    }

    /* access modifiers changed from: package-private */
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (this.mBackgroundTint == null) {
            this.mBackgroundTint = new TintInfo();
        }
        TintInfo $r2 = this.mBackgroundTint;
        $r2.mTintList = colorStateList;
        $r2.mHasTintList = true;
        applySupportBackgroundTint();
    }

    /* access modifiers changed from: package-private */
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if (this.mBackgroundTint == null) {
            this.mBackgroundTint = new TintInfo();
        }
        TintInfo $r2 = this.mBackgroundTint;
        $r2.mTintMode = mode;
        $r2.mHasTintMode = true;
        applySupportBackgroundTint();
    }
}

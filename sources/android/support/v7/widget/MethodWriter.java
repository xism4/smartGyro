package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.v4.widget.AppCompatBackgroundHelper;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.org.v4.text.view.Resources;
import com.org.v4.util.R$styleable;

public class MethodWriter {
    private TintInfo mBackgroundTint;
    private TintInfo mInternalBackgroundTint;
    private TintInfo mTmpInfo;
    private final ImageView mView;

    public MethodWriter(ImageView imageView) {
        this.mView = imageView;
    }

    private boolean b() {
        int $i0 = Build.VERSION.SDK_INT;
        return $i0 > 21 ? this.mInternalBackgroundTint != null : $i0 == 21;
    }

    private boolean compatTintDrawableUsingFrameworkTint(Drawable drawable) {
        if (this.mTmpInfo == null) {
            this.mTmpInfo = new TintInfo();
        }
        TintInfo $r2 = this.mTmpInfo;
        $r2.clear();
        ColorStateList $r4 = AppCompatBackgroundHelper.b(this.mView);
        if ($r4 != null) {
            $r2.mHasTintList = true;
            $r2.mTintList = $r4;
        }
        PorterDuff.Mode $r5 = AppCompatBackgroundHelper.createView(this.mView);
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

    /* access modifiers changed from: package-private */
    public void a() {
        Drawable $r2 = this.mView.getDrawable();
        if ($r2 != null) {
            DrawableUtils.fixDrawable($r2);
        }
        if ($r2 == null) {
            return;
        }
        if (!b() || !compatTintDrawableUsingFrameworkTint($r2)) {
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

    public void a(int i) {
        if (i != 0) {
            Drawable $r3 = Resources.getDrawable(this.mView.getContext(), i);
            if ($r3 != null) {
                DrawableUtils.fixDrawable($r3);
            }
            this.mView.setImageDrawable($r3);
        } else {
            this.mView.setImageDrawable((Drawable) null);
        }
        a();
    }

    /* access modifiers changed from: package-private */
    public void a(ColorStateList colorStateList) {
        if (this.mBackgroundTint == null) {
            this.mBackgroundTint = new TintInfo();
        }
        TintInfo $r2 = this.mBackgroundTint;
        $r2.mTintList = colorStateList;
        $r2.mHasTintList = true;
        a();
    }

    /* access modifiers changed from: package-private */
    public void a(PorterDuff.Mode mode) {
        if (this.mBackgroundTint == null) {
            this.mBackgroundTint = new TintInfo();
        }
        TintInfo $r2 = this.mBackgroundTint;
        $r2.mTintMode = mode;
        $r2.mHasTintMode = true;
        a();
    }

    /* access modifiers changed from: package-private */
    public boolean getSize() {
        return Build.VERSION.SDK_INT < 21 || !(this.mView.getBackground() instanceof RippleDrawable);
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

    public void loadFromAttributes(AttributeSet attributeSet, int i) {
        TintTypedArray $r5 = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), attributeSet, R$styleable.AppCompatImageView, i, 0);
        try {
            Drawable $r6 = this.mView.getDrawable();
            Drawable $r7 = $r6;
            if ($r6 == null) {
                int $i0 = $r5.getResourceId(R$styleable.AppCompatImageView_srcCompat, -1);
                if ($i0 != -1) {
                    Drawable $r62 = Resources.getDrawable(this.mView.getContext(), $i0);
                    $r7 = $r62;
                    if ($r62 != null) {
                        this.mView.setImageDrawable($r62);
                    }
                }
            }
            if ($r7 != null) {
                DrawableUtils.fixDrawable($r7);
            }
            if ($r5.hasValue(R$styleable.AppCompatImageView_tint)) {
                AppCompatBackgroundHelper.loadFromAttributes(this.mView, $r5.getColorStateList(R$styleable.AppCompatImageView_tint));
            }
            if ($r5.hasValue(R$styleable.AppCompatImageView_tintMode)) {
                AppCompatBackgroundHelper.loadFromAttributes(this.mView, DrawableUtils.parseTintMode($r5.getInt(R$styleable.AppCompatImageView_tintMode, -1), (PorterDuff.Mode) null));
            }
        } finally {
            $r5.recycle();
        }
    }
}

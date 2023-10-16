package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.widget.SeekBar;
import com.org.android.view.ViewCompat;
import com.org.v4.util.R$styleable;

class AppCompatTextHelper extends AppCompatProgressBarHelper {
    private ColorStateList mButtonTintList = null;
    private PorterDuff.Mode mButtonTintMode = null;
    private Drawable mDrawable;
    private boolean mHasButtonTint = false;
    private boolean mHasButtonTintMode = false;
    private final SeekBar mView;

    AppCompatTextHelper(SeekBar seekBar) {
        super(seekBar);
        this.mView = seekBar;
    }

    private void applyButtonTint() {
        if (this.mDrawable == null) {
            return;
        }
        if (this.mHasButtonTint || this.mHasButtonTintMode) {
            this.mDrawable = DrawableCompat.wrap(this.mDrawable.mutate());
            if (this.mHasButtonTint) {
                DrawableCompat.setTintList(this.mDrawable, this.mButtonTintList);
            }
            if (this.mHasButtonTintMode) {
                DrawableCompat.setTintMode(this.mDrawable, this.mButtonTintMode);
            }
            if (this.mDrawable.isStateful()) {
                this.mDrawable.setState(this.mView.getDrawableState());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void draw(Canvas canvas) {
        if (this.mDrawable != null) {
            int $i1 = this.mView.getMax();
            int $i2 = 1;
            if ($i1 > 1) {
                int $i3 = this.mDrawable.getIntrinsicWidth();
                int $i0 = this.mDrawable.getIntrinsicHeight();
                int $i32 = $i3 >= 0 ? $i3 / 2 : 1;
                if ($i0 >= 0) {
                    $i2 = $i0 / 2;
                }
                this.mDrawable.setBounds(-$i32, -$i2, $i32, $i2);
                float $f0 = ((float) ((this.mView.getWidth() - this.mView.getPaddingLeft()) - this.mView.getPaddingRight())) / ((float) $i1);
                int $i22 = canvas.save();
                canvas.translate((float) this.mView.getPaddingLeft(), (float) (this.mView.getHeight() / 2));
                for (int $i33 = 0; $i33 <= $i1; $i33++) {
                    this.mDrawable.draw(canvas);
                    canvas.translate($f0, 0.0f);
                }
                canvas.restoreToCount($i22);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void jumpToCurrentState() {
        Drawable $r1 = this.mDrawable;
        if ($r1 != null) {
            $r1.jumpToCurrentState();
        }
    }

    /* access modifiers changed from: package-private */
    public void loadFromAttributes(AttributeSet attributeSet, int i) {
        super.loadFromAttributes(attributeSet, i);
        TintTypedArray $r5 = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), attributeSet, R$styleable.AppCompatSeekBar, i, 0);
        Drawable $r6 = $r5.getDrawableIfKnown(R$styleable.AppCompatSeekBar_android_thumb);
        if ($r6 != null) {
            this.mView.setThumb($r6);
        }
        setStatusBarBackground($r5.getDrawable(R$styleable.AppCompatSeekBar_tickMark));
        if ($r5.hasValue(R$styleable.AppCompatSeekBar_tickMarkTintMode)) {
            this.mButtonTintMode = DrawableUtils.parseTintMode($r5.getInt(R$styleable.AppCompatSeekBar_tickMarkTintMode, -1), this.mButtonTintMode);
            this.mHasButtonTintMode = true;
        }
        if ($r5.hasValue(R$styleable.AppCompatSeekBar_tickMarkTint)) {
            this.mButtonTintList = $r5.getColorStateList(R$styleable.AppCompatSeekBar_tickMarkTint);
            this.mHasButtonTint = true;
        }
        $r5.recycle();
        applyButtonTint();
    }

    /* access modifiers changed from: package-private */
    public void setState() {
        Drawable $r1 = this.mDrawable;
        if ($r1 != null && $r1.isStateful() && $r1.setState(this.mView.getDrawableState())) {
            this.mView.invalidateDrawable($r1);
        }
    }

    /* access modifiers changed from: package-private */
    public void setStatusBarBackground(Drawable drawable) {
        Drawable $r2 = this.mDrawable;
        if ($r2 != null) {
            $r2.setCallback((Drawable.Callback) null);
        }
        this.mDrawable = drawable;
        if (drawable != null) {
            drawable.setCallback(this.mView);
            DrawableCompat.a(drawable, ViewCompat.getLayoutDirection(this.mView));
            if (drawable.isStateful()) {
                drawable.setState(this.mView.getDrawableState());
            }
            applyButtonTint();
        }
        this.mView.invalidate();
    }
}

package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.CompoundButtonCompat;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import com.org.v4.text.view.Resources;
import com.org.v4.util.R$styleable;

class AppCompatCompoundButtonHelper {
    private ColorStateList mButtonTintList = null;
    private PorterDuff.Mode mButtonTintMode = null;
    private boolean mHasButtonTint = false;
    private boolean mHasButtonTintMode = false;
    private boolean mSkipNextApply;
    private final CompoundButton mView;

    AppCompatCompoundButtonHelper(CompoundButton compoundButton) {
        this.mView = compoundButton;
    }

    /* access modifiers changed from: package-private */
    public void applyButtonTint() {
        Drawable $r2 = CompoundButtonCompat.getButtonDrawable(this.mView);
        if ($r2 == null) {
            return;
        }
        if (this.mHasButtonTint || this.mHasButtonTintMode) {
            Drawable $r22 = DrawableCompat.wrap($r2).mutate();
            if (this.mHasButtonTint) {
                DrawableCompat.setTintList($r22, this.mButtonTintList);
            }
            if (this.mHasButtonTintMode) {
                DrawableCompat.setTintMode($r22, this.mButtonTintMode);
            }
            if ($r22.isStateful()) {
                $r22.setState(this.mView.getDrawableState());
            }
            this.mView.setButtonDrawable($r22);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r3 = android.support.v4.widget.CompoundButtonCompat.getButtonDrawable(r4.mView);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getCompoundPaddingLeft(int r5) {
        /*
            r4 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 17
            if (r0 >= r1) goto L_0x0013
            android.widget.CompoundButton r2 = r4.mView
            android.graphics.drawable.Drawable r3 = android.support.v4.widget.CompoundButtonCompat.getButtonDrawable(r2)
            if (r3 == 0) goto L_0x0013
            int r0 = r3.getIntrinsicWidth()
            int r5 = r5 + r0
        L_0x0013:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.AppCompatCompoundButtonHelper.getCompoundPaddingLeft(int):int");
    }

    /* access modifiers changed from: package-private */
    public ColorStateList getSupportButtonTintList() {
        return this.mButtonTintList;
    }

    /* access modifiers changed from: package-private */
    public PorterDuff.Mode getSupportButtonTintMode() {
        return this.mButtonTintMode;
    }

    /* access modifiers changed from: package-private */
    public void loadFromAttributes(AttributeSet attributeSet, int i) {
        TypedArray $r5 = this.mView.getContext().obtainStyledAttributes(attributeSet, R$styleable.CompoundButton, i, 0);
        try {
            if ($r5.hasValue(R$styleable.CompoundButton_android_button)) {
                int $i0 = $r5.getResourceId(R$styleable.CompoundButton_android_button, 0);
                if ($i0 != 0) {
                    this.mView.setButtonDrawable(Resources.getDrawable(this.mView.getContext(), $i0));
                }
            }
            if ($r5.hasValue(R$styleable.CompoundButton_buttonTint)) {
                CompoundButtonCompat.setButtonTintList(this.mView, $r5.getColorStateList(R$styleable.CompoundButton_buttonTint));
            }
            if ($r5.hasValue(R$styleable.CompoundButton_buttonTintMode)) {
                CompoundButtonCompat.setButtonTintMode(this.mView, DrawableUtils.parseTintMode($r5.getInt(R$styleable.CompoundButton_buttonTintMode, -1), (PorterDuff.Mode) null));
            }
        } finally {
            $r5.recycle();
        }
    }

    /* access modifiers changed from: package-private */
    public void onSetButtonDrawable() {
        if (this.mSkipNextApply) {
            this.mSkipNextApply = false;
            return;
        }
        this.mSkipNextApply = true;
        applyButtonTint();
    }

    /* access modifiers changed from: package-private */
    public void setSupportButtonTintList(ColorStateList colorStateList) {
        this.mButtonTintList = colorStateList;
        this.mHasButtonTint = true;
        applyButtonTint();
    }

    /* access modifiers changed from: package-private */
    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        this.mButtonTintMode = mode;
        this.mHasButtonTintMode = true;
        applyButtonTint();
    }
}

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.widget.LayoutManager;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import com.org.v4.util.R$styleable;
import java.lang.ref.WeakReference;

class TimePicker {
    private int mContext = 0;
    private TintInfo mDrawableBottomTint;
    private TintInfo mDrawableEndTint;
    private TintInfo mDrawableLeftTint;
    private TintInfo mDrawableRightTint;
    private TintInfo mDrawableStartTint;
    private TintInfo mDrawableTopTint;
    private final f mMenu;
    private boolean mMode;
    private Typeface mTypeface;
    private final TextView mView;

    TimePicker(TextView textView) {
        this.mView = textView;
        this.mMenu = new f(this.mView);
    }

    private void applyCompoundDrawableTint(Drawable drawable, TintInfo tintInfo) {
        if (drawable != null && tintInfo != null) {
            AppCompatDrawableManager.tintDrawable(drawable, tintInfo, this.mView.getDrawableState());
        }
    }

    private void applyStyle(Context context, TintTypedArray tintTypedArray) {
        String $r9;
        Typeface $r5;
        this.mContext = tintTypedArray.getInt(R$styleable.TextAppearance_android_textStyle, this.mContext);
        boolean $z1 = false;
        if (tintTypedArray.hasValue(R$styleable.TextAppearance_android_fontFamily) || tintTypedArray.hasValue(R$styleable.TextAppearance_fontFamily)) {
            this.mTypeface = null;
            int $i0 = tintTypedArray.hasValue(R$styleable.TextAppearance_fontFamily) ? R$styleable.TextAppearance_fontFamily : R$styleable.TextAppearance_android_fontFamily;
            if (!context.isRestricted()) {
                try {
                    this.mTypeface = tintTypedArray.getDrawable($i0, this.mContext, new e(this, new WeakReference(this.mView)));
                    if (this.mTypeface == null) {
                        $z1 = true;
                    }
                    this.mMode = $z1;
                } catch (UnsupportedOperationException e) {
                } catch (Resources.NotFoundException e2) {
                }
            }
            if (this.mTypeface == null && ($r9 = tintTypedArray.getString($i0)) != null) {
                this.mTypeface = Typeface.create($r9, this.mContext);
            }
        } else if (tintTypedArray.hasValue(R$styleable.TextAppearance_android_typeface)) {
            this.mMode = false;
            int $i02 = tintTypedArray.getInt(R$styleable.TextAppearance_android_typeface, 1);
            if ($i02 == 1) {
                $r5 = Typeface.SANS_SERIF;
            } else if ($i02 == 2) {
                $r5 = Typeface.SERIF;
            } else if ($i02 == 3) {
                $r5 = Typeface.MONOSPACE;
            } else {
                return;
            }
            this.mTypeface = $r5;
        }
    }

    private static TintInfo createTintInfo(Context context, AppCompatDrawableManager appCompatDrawableManager, int i) {
        ColorStateList $r0 = appCompatDrawableManager.getTintList(context, i);
        if ($r0 == null) {
            return null;
        }
        TintInfo $r3 = new TintInfo();
        $r3.mHasTintList = true;
        $r3.mTintList = $r0;
        return $r3;
    }

    private void setEnabled(int i, float f) {
        this.mMenu.b(i, f);
    }

    /* access modifiers changed from: package-private */
    public void applyCompoundDrawablesTints() {
        if (!(this.mDrawableLeftTint == null && this.mDrawableBottomTint == null && this.mDrawableTopTint == null && this.mDrawableRightTint == null)) {
            Drawable[] $r3 = this.mView.getCompoundDrawables();
            applyCompoundDrawableTint($r3[0], this.mDrawableLeftTint);
            applyCompoundDrawableTint($r3[1], this.mDrawableBottomTint);
            applyCompoundDrawableTint($r3[2], this.mDrawableTopTint);
            applyCompoundDrawableTint($r3[3], this.mDrawableRightTint);
        }
        if (Build.VERSION.SDK_INT < 17) {
            return;
        }
        if (this.mDrawableStartTint != null || this.mDrawableEndTint != null) {
            Drawable[] $r32 = this.mView.getCompoundDrawablesRelative();
            applyCompoundDrawableTint($r32[0], this.mDrawableStartTint);
            applyCompoundDrawableTint($r32[2], this.mDrawableEndTint);
        }
    }

    /* access modifiers changed from: package-private */
    public void applyStyle(Context context, int i) {
        ColorStateList $r4;
        TintTypedArray $r3 = TintTypedArray.obtainStyledAttributes(context, i, R$styleable.TextAppearance);
        if ($r3.hasValue(R$styleable.TextAppearance_textAllCaps)) {
            setAllCaps($r3.getBoolean(R$styleable.TextAppearance_textAllCaps, false));
        }
        if (Build.VERSION.SDK_INT < 23 && $r3.hasValue(R$styleable.TextAppearance_android_textColor) && ($r4 = $r3.getColorStateList(R$styleable.TextAppearance_android_textColor)) != null) {
            this.mView.setTextColor($r4);
        }
        if ($r3.hasValue(R$styleable.TextAppearance_android_textSize) && $r3.getDimensionPixelSize(R$styleable.TextAppearance_android_textSize, -1) == 0) {
            this.mView.setTextSize(0, 0.0f);
        }
        applyStyle(context, $r3);
        $r3.recycle();
        Typeface $r6 = this.mTypeface;
        if ($r6 != null) {
            this.mView.setTypeface($r6, this.mContext);
        }
    }

    /* access modifiers changed from: package-private */
    public void applyStyle(AttributeSet attributeSet, int i) {
        ColorStateList $r12;
        ColorStateList $r10;
        boolean z;
        boolean $z2;
        Context $r3 = this.mView.getContext();
        AppCompatDrawableManager $r4 = AppCompatDrawableManager.get();
        TintTypedArray $r6 = TintTypedArray.obtainStyledAttributes($r3, attributeSet, R$styleable.AppCompatTextHelper, i, 0);
        int $i1 = $r6.getResourceId(R$styleable.AppCompatTextHelper_android_textAppearance, -1);
        if ($r6.hasValue(R$styleable.AppCompatTextHelper_android_drawableLeft)) {
            this.mDrawableLeftTint = createTintInfo($r3, $r4, $r6.getResourceId(R$styleable.AppCompatTextHelper_android_drawableLeft, 0));
        }
        if ($r6.hasValue(R$styleable.AppCompatTextHelper_android_drawableTop)) {
            this.mDrawableBottomTint = createTintInfo($r3, $r4, $r6.getResourceId(R$styleable.AppCompatTextHelper_android_drawableTop, 0));
        }
        if ($r6.hasValue(R$styleable.AppCompatTextHelper_android_drawableRight)) {
            this.mDrawableTopTint = createTintInfo($r3, $r4, $r6.getResourceId(R$styleable.AppCompatTextHelper_android_drawableRight, 0));
        }
        if ($r6.hasValue(R$styleable.AppCompatTextHelper_android_drawableBottom)) {
            this.mDrawableRightTint = createTintInfo($r3, $r4, $r6.getResourceId(R$styleable.AppCompatTextHelper_android_drawableBottom, 0));
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if ($r6.hasValue(R$styleable.AppCompatTextHelper_android_drawableStart)) {
                this.mDrawableStartTint = createTintInfo($r3, $r4, $r6.getResourceId(R$styleable.AppCompatTextHelper_android_drawableStart, 0));
            }
            if ($r6.hasValue(R$styleable.AppCompatTextHelper_android_drawableEnd)) {
                this.mDrawableEndTint = createTintInfo($r3, $r4, $r6.getResourceId(R$styleable.AppCompatTextHelper_android_drawableEnd, 0));
            }
        }
        $r6.recycle();
        boolean $z0 = this.mView.getTransformationMethod() instanceof PasswordTransformationMethod;
        boolean $z1 = true;
        ColorStateList $r9 = null;
        if ($i1 != -1) {
            TintTypedArray $r62 = TintTypedArray.obtainStyledAttributes($r3, $i1, R$styleable.TextAppearance);
            if ($z0 || !$r62.hasValue(R$styleable.TextAppearance_textAllCaps)) {
                z = false;
                $z2 = false;
            } else {
                $z2 = $r62.getBoolean(R$styleable.TextAppearance_textAllCaps, false);
                z = true;
            }
            applyStyle($r3, $r62);
            if (Build.VERSION.SDK_INT < 23) {
                ColorStateList $r11 = $r62.hasValue(R$styleable.TextAppearance_android_textColor) ? $r62.getColorStateList(R$styleable.TextAppearance_android_textColor) : null;
                $r10 = $r62.hasValue(R$styleable.TextAppearance_android_textColorHint) ? $r62.getColorStateList(R$styleable.TextAppearance_android_textColorHint) : null;
                if ($r62.hasValue(R$styleable.TextAppearance_android_textColorLink)) {
                    $r9 = $r62.getColorStateList(R$styleable.TextAppearance_android_textColorLink);
                }
                $r12 = $r9;
                $r9 = $r11;
            } else {
                $r12 = null;
                $r10 = null;
            }
            $r62.recycle();
        } else {
            $r12 = null;
            $r10 = null;
            z = false;
            $z2 = false;
        }
        TintTypedArray $r63 = TintTypedArray.obtainStyledAttributes($r3, attributeSet, R$styleable.TextAppearance, i, 0);
        if ($z0 || !$r63.hasValue(R$styleable.TextAppearance_textAllCaps)) {
            $z1 = z;
        } else {
            $z2 = $r63.getBoolean(R$styleable.TextAppearance_textAllCaps, false);
        }
        if (Build.VERSION.SDK_INT < 23) {
            if ($r63.hasValue(R$styleable.TextAppearance_android_textColor)) {
                $r9 = $r63.getColorStateList(R$styleable.TextAppearance_android_textColor);
            }
            if ($r63.hasValue(R$styleable.TextAppearance_android_textColorHint)) {
                $r10 = $r63.getColorStateList(R$styleable.TextAppearance_android_textColorHint);
            }
            if ($r63.hasValue(R$styleable.TextAppearance_android_textColorLink)) {
                $r12 = $r63.getColorStateList(R$styleable.TextAppearance_android_textColorLink);
            }
        }
        if (Build.VERSION.SDK_INT >= 28 && $r63.hasValue(R$styleable.TextAppearance_android_textSize) && $r63.getDimensionPixelSize(R$styleable.TextAppearance_android_textSize, -1) == 0) {
            this.mView.setTextSize(0, 0.0f);
        }
        applyStyle($r3, $r63);
        $r63.recycle();
        if ($r9 != null) {
            this.mView.setTextColor($r9);
        }
        if ($r10 != null) {
            this.mView.setHintTextColor($r10);
        }
        if ($r12 != null) {
            this.mView.setLinkTextColor($r12);
        }
        if (!$z0 && $z1) {
            setAllCaps($z2);
        }
        Typeface $r13 = this.mTypeface;
        if ($r13 != null) {
            this.mView.setTypeface($r13, this.mContext);
        }
        f $r14 = this.mMenu;
        f fVar = $r14;
        $r14.a(attributeSet, i);
        if (android.support.v4.widget.TimePicker.mIs24HourMode) {
            f $r142 = this.mMenu;
            f fVar2 = $r142;
            if ($r142.k() != 0) {
                f $r143 = this.mMenu;
                f fVar3 = $r143;
                int[] $r5 = $r143.close();
                if ($r5.length > 0) {
                    if (((float) this.mView.getAutoSizeStepGranularity()) != -1.0f) {
                        TextView $r2 = this.mView;
                        f $r144 = this.mMenu;
                        f fVar4 = $r144;
                        int $i0 = $r144.add();
                        f $r145 = this.mMenu;
                        f fVar5 = $r145;
                        int $i12 = $r145.clear();
                        f $r146 = this.mMenu;
                        f fVar6 = $r146;
                        $r2.setAutoSizeTextTypeUniformWithConfiguration($i0, $i12, $r146.getHeight(), 0);
                    } else {
                        this.mView.setAutoSizeTextTypeUniformWithPresetSizes($r5, 0);
                    }
                }
            }
        }
        TintTypedArray $r64 = TintTypedArray.obtainStyledAttributes($r3, attributeSet, R$styleable.AppCompatTextView);
        int $i02 = $r64.getDimensionPixelSize(R$styleable.AppCompatTextView_firstBaselineToTopHeight, -1);
        int $i13 = $r64.getDimensionPixelSize(R$styleable.AppCompatTextView_lastBaselineToBottomHeight, -1);
        int $i2 = $r64.getDimensionPixelSize(R$styleable.AppCompatTextView_lineHeight, -1);
        $r64.recycle();
        if ($i02 != -1) {
            LayoutManager.a(this.mView, $i02);
        }
        if ($i13 != -1) {
            LayoutManager.initialize(this.mView, $i13);
        }
        if ($i2 != -1) {
            LayoutManager.init(this.mView, $i2);
        }
    }

    /* access modifiers changed from: package-private */
    public void applyStyle(WeakReference weakReference, Typeface typeface) {
        if (this.mMode) {
            this.mTypeface = typeface;
            TextView $r4 = (TextView) weakReference.get();
            if ($r4 != null) {
                $r4.setTypeface(typeface, this.mContext);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int[] getHour() {
        return this.mMenu.close();
    }

    /* access modifiers changed from: package-private */
    public int getMinute() {
        return this.mMenu.clear();
    }

    /* access modifiers changed from: package-private */
    public int getTextSize() {
        return this.mMenu.k();
    }

    /* access modifiers changed from: package-private */
    public int getTypeface() {
        return this.mMenu.add();
    }

    /* access modifiers changed from: package-private */
    public void onSaveInstanceState() {
        this.mMenu.a();
    }

    /* access modifiers changed from: package-private */
    public void onSaveInstanceState(int i, int i2, int i3, int i4) {
        this.mMenu.a(i, i2, i3, i4);
    }

    /* access modifiers changed from: package-private */
    public void setAllCaps(boolean z) {
        this.mView.setAllCaps(z);
    }

    /* access modifiers changed from: package-private */
    public void setEnabled(int i) {
        this.mMenu.b(i);
    }

    /* access modifiers changed from: package-private */
    public void setEnabled(int[] iArr, int i) {
        this.mMenu.a(iArr, i);
    }

    /* access modifiers changed from: package-private */
    public boolean setEnabled() {
        return this.mMenu.i();
    }

    /* access modifiers changed from: package-private */
    public int setTime() {
        return this.mMenu.getHeight();
    }

    /* access modifiers changed from: package-private */
    public void setTime(int i, float f) {
        if (!android.support.v4.widget.TimePicker.mIs24HourMode && !setEnabled()) {
            setEnabled(i, f);
        }
    }

    /* access modifiers changed from: package-private */
    public void setTime(boolean z, int i, int i2, int i3, int i4) {
        if (!android.support.v4.widget.TimePicker.mIs24HourMode) {
            onSaveInstanceState();
        }
    }
}

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.LayoutManager;
import android.support.v4.widget.TimePicker;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.TextView;
import com.org.android.view.TintableBackgroundView;
import com.org.v4.util.R$attr;

public class AppCompatButton extends Button implements TintableBackgroundView, TimePicker {
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final TimePicker mTimePicker;

    public AppCompatButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.buttonStyle);
    }

    public AppCompatButton(Context context, AttributeSet attributeSet, int i) {
        super(TintContextWrapper.wrap(context), attributeSet, i);
        this.mBackgroundTintHelper = new AppCompatBackgroundHelper(this);
        this.mBackgroundTintHelper.loadFromAttributes(attributeSet, i);
        this.mTimePicker = new TimePicker(this);
        this.mTimePicker.applyStyle(attributeSet, i);
        this.mTimePicker.applyCompoundDrawablesTints();
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatBackgroundHelper $r1 = this.mBackgroundTintHelper;
        if ($r1 != null) {
            $r1.applySupportBackgroundTint();
        }
        TimePicker $r2 = this.mTimePicker;
        if ($r2 != null) {
            $r2.applyCompoundDrawablesTints();
        }
    }

    public int getAutoSizeMaxTextSize() {
        if (TimePicker.mIs24HourMode) {
            return super.getAutoSizeMaxTextSize();
        }
        TimePicker $r1 = this.mTimePicker;
        if ($r1 != null) {
            return $r1.getMinute();
        }
        return -1;
    }

    public int getAutoSizeMinTextSize() {
        if (TimePicker.mIs24HourMode) {
            return super.getAutoSizeMinTextSize();
        }
        TimePicker $r1 = this.mTimePicker;
        if ($r1 != null) {
            return $r1.getTypeface();
        }
        return -1;
    }

    public int getAutoSizeStepGranularity() {
        if (TimePicker.mIs24HourMode) {
            return super.getAutoSizeStepGranularity();
        }
        TimePicker $r1 = this.mTimePicker;
        if ($r1 != null) {
            return $r1.setTime();
        }
        return -1;
    }

    public int[] getAutoSizeTextAvailableSizes() {
        if (TimePicker.mIs24HourMode) {
            return super.getAutoSizeTextAvailableSizes();
        }
        TimePicker $r2 = this.mTimePicker;
        return $r2 != null ? $r2.getHour() : new int[0];
    }

    public int getAutoSizeTextType() {
        if (TimePicker.mIs24HourMode) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        TimePicker $r1 = this.mTimePicker;
        if ($r1 != null) {
            return $r1.getTextSize();
        }
        return 0;
    }

    public ColorStateList getSupportBackgroundTintList() {
        AppCompatBackgroundHelper $r2 = this.mBackgroundTintHelper;
        if ($r2 != null) {
            return $r2.getSupportBackgroundTintList();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        AppCompatBackgroundHelper $r2 = this.mBackgroundTintHelper;
        if ($r2 != null) {
            return $r2.getSupportBackgroundTintMode();
        }
        return null;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        TimePicker $r1 = this.mTimePicker;
        if ($r1 != null) {
            $r1.setTime(z, i, i2, i3, i4);
        }
    }

    /* access modifiers changed from: protected */
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        TimePicker $r2 = this.mTimePicker;
        if ($r2 != null && !TimePicker.mIs24HourMode && $r2.setEnabled()) {
            this.mTimePicker.onSaveInstanceState();
        }
    }

    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        if (TimePicker.mIs24HourMode) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        TimePicker $r1 = this.mTimePicker;
        if ($r1 != null) {
            $r1.onSaveInstanceState(i, i2, i3, i4);
        }
    }

    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) {
        if (TimePicker.mIs24HourMode) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
            return;
        }
        TimePicker $r2 = this.mTimePicker;
        if ($r2 != null) {
            $r2.setEnabled(iArr, i);
        }
    }

    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (TimePicker.mIs24HourMode) {
            super.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
        TimePicker $r1 = this.mTimePicker;
        if ($r1 != null) {
            $r1.setEnabled(i);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        AppCompatBackgroundHelper $r2 = this.mBackgroundTintHelper;
        if ($r2 != null) {
            $r2.onSetBackgroundDrawable(drawable);
        }
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        AppCompatBackgroundHelper $r1 = this.mBackgroundTintHelper;
        if ($r1 != null) {
            $r1.onSetBackgroundResource(i);
        }
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(LayoutManager.a((TextView) this, callback));
    }

    public void setSupportAllCaps(boolean z) {
        TimePicker $r1 = this.mTimePicker;
        if ($r1 != null) {
            $r1.setAllCaps(z);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        AppCompatBackgroundHelper $r2 = this.mBackgroundTintHelper;
        if ($r2 != null) {
            $r2.setSupportBackgroundTintList(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        AppCompatBackgroundHelper $r2 = this.mBackgroundTintHelper;
        if ($r2 != null) {
            $r2.setSupportBackgroundTintMode(mode);
        }
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        TimePicker $r2 = this.mTimePicker;
        if ($r2 != null) {
            $r2.applyStyle(context, i);
        }
    }

    public void setTextSize(int i, float f) {
        if (TimePicker.mIs24HourMode) {
            super.setTextSize(i, f);
            return;
        }
        TimePicker $r1 = this.mTimePicker;
        if ($r1 != null) {
            $r1.setTime(i, f);
        }
    }
}

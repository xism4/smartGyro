package android.support.v7.widget;

import a.a.c.e.a;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.widget.LayoutManager;
import android.support.v4.widget.TimePicker;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import com.org.android.core.Label;
import com.org.android.core.Segment;
import com.org.android.view.TintableBackgroundView;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AppCompatTextView extends TextView implements TintableBackgroundView, TimePicker {
    private Future<a> f;
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final TimePicker mTimePicker;

    public AppCompatTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet, int i) {
        super(TintContextWrapper.wrap(context), attributeSet, i);
        this.mBackgroundTintHelper = new AppCompatBackgroundHelper(this);
        this.mBackgroundTintHelper.loadFromAttributes(attributeSet, i);
        this.mTimePicker = new TimePicker(this);
        this.mTimePicker.applyStyle(attributeSet, i);
        this.mTimePicker.applyCompoundDrawablesTints();
    }

    private void a() {
        Future $r4 = this.f;
        if ($r4 != null) {
            this.f = null;
            try {
                LayoutManager.a((TextView) this, $r4.get());
            } catch (InterruptedException e) {
            } catch (ExecutionException e2) {
            }
        }
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

    public int getFirstBaselineToTopHeight() {
        return LayoutManager.updatePadding(this);
    }

    public int getLastBaselineToBottomHeight() {
        return LayoutManager.setText(this);
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

    public CharSequence getText() {
        a();
        return super.getText();
    }

    public Label getTextMetricsParamsCompat() {
        return LayoutManager.a(this);
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection $r2 = super.onCreateInputConnection(editorInfo);
        Resources.validate($r2, editorInfo, this);
        return $r2;
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
    public void onMeasure(int i, int i2) {
        a();
        super.onMeasure(i, i2);
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

    public void setFirstBaselineToTopHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setFirstBaselineToTopHeight(i);
        } else {
            LayoutManager.a((TextView) this, i);
        }
    }

    public void setLastBaselineToBottomHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setLastBaselineToBottomHeight(i);
        } else {
            LayoutManager.initialize(this, i);
        }
    }

    public void setLineHeight(int i) {
        LayoutManager.init(this, i);
    }

    public void setPrecomputedText(Segment segment) {
        LayoutManager.a((TextView) this, segment);
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

    public void setTextFuture(Future future) {
        this.f = future;
        requestLayout();
    }

    public void setTextMetricsParamsCompat(Label label) {
        LayoutManager.a((TextView) this, label);
    }

    public void setTextSize(int i, float f2) {
        if (TimePicker.mIs24HourMode) {
            super.setTextSize(i, f2);
            return;
        }
        TimePicker $r1 = this.mTimePicker;
        if ($r1 != null) {
            $r1.setTime(i, f2);
        }
    }
}

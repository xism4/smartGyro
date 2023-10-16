package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.LayoutManager;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import com.org.android.view.TintableBackgroundView;
import com.org.v4.text.view.Resources;
import com.org.v4.util.R$attr;

public class AppCompatAutoCompleteTextView extends AutoCompleteTextView implements TintableBackgroundView {
    private static final int[] TINT_ATTRS = {16843126};
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final TimePicker mTextHelper;

    public AppCompatAutoCompleteTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public AppCompatAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.autoCompleteTextViewStyle);
    }

    public AppCompatAutoCompleteTextView(Context context, AttributeSet attributeSet, int i) {
        super(TintContextWrapper.wrap(context), attributeSet, i);
        TintTypedArray $r4 = TintTypedArray.obtainStyledAttributes(getContext(), attributeSet, TINT_ATTRS, i, 0);
        if ($r4.hasValue(0)) {
            setDropDownBackgroundDrawable($r4.getDrawable(0));
        }
        $r4.recycle();
        this.mBackgroundTintHelper = new AppCompatBackgroundHelper(this);
        this.mBackgroundTintHelper.loadFromAttributes(attributeSet, i);
        this.mTextHelper = new TimePicker(this);
        this.mTextHelper.applyStyle(attributeSet, i);
        this.mTextHelper.applyCompoundDrawablesTints();
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatBackgroundHelper $r1 = this.mBackgroundTintHelper;
        if ($r1 != null) {
            $r1.applySupportBackgroundTint();
        }
        TimePicker $r2 = this.mTextHelper;
        if ($r2 != null) {
            $r2.applyCompoundDrawablesTints();
        }
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

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection $r2 = super.onCreateInputConnection(editorInfo);
        Resources.validate($r2, editorInfo, this);
        return $r2;
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

    public void setDropDownBackgroundResource(int i) {
        setDropDownBackgroundDrawable(Resources.getDrawable(getContext(), i));
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
        TimePicker $r2 = this.mTextHelper;
        if ($r2 != null) {
            $r2.applyStyle(context, i);
        }
    }
}

package android.support.v7.widget;

import android.content.Context;
import android.support.v4.widget.LayoutManager;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;
import android.widget.TextView;
import com.org.v4.text.view.Resources;

public class AppCompatCheckedTextView extends CheckedTextView {
    private static final int[] TINT_ATTRS = {16843016};
    private final TimePicker mTextHelper;

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16843720);
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet, int i) {
        super(TintContextWrapper.wrap(context), attributeSet, i);
        this.mTextHelper = new TimePicker(this);
        this.mTextHelper.applyStyle(attributeSet, i);
        this.mTextHelper.applyCompoundDrawablesTints();
        TintTypedArray $r5 = TintTypedArray.obtainStyledAttributes(getContext(), attributeSet, TINT_ATTRS, i, 0);
        setCheckMarkDrawable($r5.getDrawable(0));
        $r5.recycle();
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        TimePicker $r1 = this.mTextHelper;
        if ($r1 != null) {
            $r1.applyCompoundDrawablesTints();
        }
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection $r2 = super.onCreateInputConnection(editorInfo);
        Resources.validate($r2, editorInfo, this);
        return $r2;
    }

    public void setCheckMarkDrawable(int i) {
        setCheckMarkDrawable(Resources.getDrawable(getContext(), i));
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(LayoutManager.a((TextView) this, callback));
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        TimePicker $r2 = this.mTextHelper;
        if ($r2 != null) {
            $r2.applyStyle(context, i);
        }
    }
}

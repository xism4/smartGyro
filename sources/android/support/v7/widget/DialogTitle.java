package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.LayoutManager;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.widget.TextView;
import com.org.v4.util.R$styleable;

public class DialogTitle extends TextView {
    public DialogTitle(Context context) {
        super(context);
    }

    public DialogTitle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DialogTitle(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int $i2;
        super.onMeasure(i, i2);
        Layout $r2 = getLayout();
        if ($r2 != null && ($i2 = $r2.getLineCount()) > 0 && $r2.getEllipsisCount($i2 - 1) > 0) {
            setSingleLine(false);
            setMaxLines(2);
            TypedArray $r4 = getContext().obtainStyledAttributes((AttributeSet) null, R$styleable.TextAppearance, 16842817, 16973892);
            int $i22 = $r4.getDimensionPixelSize(R$styleable.TextAppearance_android_textSize, 0);
            if ($i22 != 0) {
                setTextSize(0, (float) $i22);
            }
            $r4.recycle();
            super.onMeasure(i, i2);
        }
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(LayoutManager.a((TextView) this, callback));
    }
}

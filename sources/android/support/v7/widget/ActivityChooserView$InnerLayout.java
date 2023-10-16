package android.support.v7.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class ActivityChooserView$InnerLayout extends LinearLayout {
    private static final int[] TINT_ATTRS = {16842964};

    public ActivityChooserView$InnerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TintTypedArray $r4 = TintTypedArray.obtainStyledAttributes(context, attributeSet, TINT_ATTRS);
        setBackgroundDrawable($r4.getDrawable(0));
        $r4.recycle();
    }
}

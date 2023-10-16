package android.support.v7.widget;

import android.content.Context;
import android.os.Build;
import android.support.v4.widget.PopupWindowCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;
import com.org.v4.util.R$styleable;

class AppCompatPopupWindow extends PopupWindow {
    private static final boolean COMPAT_OVERLAP_ANCHOR = (Build.VERSION.SDK_INT < 21);
    private boolean mOverlapAnchor;

    public AppCompatPopupWindow(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, i2);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        TintTypedArray $r4 = TintTypedArray.obtainStyledAttributes(context, attributeSet, R$styleable.PopupWindow, i, i2);
        if ($r4.hasValue(R$styleable.PopupWindow_overlapAnchor)) {
            setSupportOverlapAnchor($r4.getBoolean(R$styleable.PopupWindow_overlapAnchor, false));
        }
        setBackgroundDrawable($r4.getDrawable(R$styleable.PopupWindow_android_popupBackground));
        $r4.recycle();
    }

    private void setSupportOverlapAnchor(boolean z) {
        if (COMPAT_OVERLAP_ANCHOR) {
            this.mOverlapAnchor = z;
        } else {
            PopupWindowCompat.init(this, z);
        }
    }

    public void showAsDropDown(View view, int i, int $i2) {
        if (COMPAT_OVERLAP_ANCHOR && this.mOverlapAnchor) {
            $i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, $i2);
    }

    public void showAsDropDown(View view, int i, int $i3, int i2) {
        if (COMPAT_OVERLAP_ANCHOR && this.mOverlapAnchor) {
            $i3 -= view.getHeight();
        }
        super.showAsDropDown(view, i, $i3, i2);
    }

    public void update(View view, int i, int $i4, int i2, int i3) {
        if (COMPAT_OVERLAP_ANCHOR && this.mOverlapAnchor) {
            $i4 -= view.getHeight();
        }
        super.update(view, i, $i4, i2, i3);
    }
}

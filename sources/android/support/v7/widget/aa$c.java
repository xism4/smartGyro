package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.a;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.org.v4.util.R$attr;

class aa$c extends LinearLayout {
    private final int[] BG_ATTRS = {16842964};
    private View mCustomView;
    private ImageView mIconView;
    private ActionBar.Tab mTab;
    private TextView mTextView;
    final /* synthetic */ ScrollingTabContainerView this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public aa$c(ScrollingTabContainerView scrollingTabContainerView, Context context, ActionBar.Tab tab, boolean z) {
        super(context, (AttributeSet) null, R$attr.actionBarTabStyle);
        this.this$0 = scrollingTabContainerView;
        this.mTab = tab;
        TintTypedArray $r5 = TintTypedArray.obtainStyledAttributes(context, (AttributeSet) null, this.BG_ATTRS, R$attr.actionBarTabStyle, 0);
        if ($r5.hasValue(0)) {
            setBackgroundDrawable($r5.getDrawable(0));
        }
        $r5.recycle();
        if (z) {
            setGravity(8388627);
        }
        update();
    }

    public void bindTab(ActionBar.Tab tab) {
        this.mTab = tab;
        update();
    }

    public ActionBar.Tab getTab() {
        return this.mTab;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(a.c.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(a.c.class.getName());
    }

    public void onMeasure(int i, int i2) {
        int $i2;
        super.onMeasure(i, i2);
        if (this.this$0.mMaxTabWidth > 0 && getMeasuredWidth() > ($i2 = this.this$0.mMaxTabWidth)) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec($i2, 1073741824), i2);
        }
    }

    public void setSelected(boolean z) {
        boolean $z0 = isSelected() != z;
        super.setSelected(z);
        if ($z0 && z) {
            sendAccessibilityEvent(4);
        }
    }

    public void update() {
        ActionBar.Tab $r1 = this.mTab;
        View $r2 = $r1.getCustomView();
        CharSequence $r3 = null;
        if ($r2 != null) {
            ViewParent $r4 = $r2.getParent();
            if ($r4 != this) {
                if ($r4 != null) {
                    ((ViewGroup) $r4).removeView($r2);
                }
                addView($r2);
            }
            this.mCustomView = $r2;
            TextView $r6 = this.mTextView;
            if ($r6 != null) {
                $r6.setVisibility(8);
            }
            ImageView $r7 = this.mIconView;
            if ($r7 != null) {
                $r7.setVisibility(8);
                this.mIconView.setImageDrawable((Drawable) null);
                return;
            }
            return;
        }
        View $r22 = this.mCustomView;
        if ($r22 != null) {
            removeView($r22);
            this.mCustomView = null;
        }
        Drawable $r8 = $r1.getIcon();
        CharSequence $r9 = $r1.getText();
        if ($r8 != null) {
            if (this.mIconView == null) {
                AppCompatImageView $r10 = new AppCompatImageView(getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                layoutParams.gravity = 16;
                $r10.setLayoutParams(layoutParams);
                addView($r10, 0);
                this.mIconView = $r10;
            }
            this.mIconView.setImageDrawable($r8);
            this.mIconView.setVisibility(0);
        } else {
            ImageView $r72 = this.mIconView;
            if ($r72 != null) {
                $r72.setVisibility(8);
                this.mIconView.setImageDrawable((Drawable) null);
            }
        }
        boolean z = !TextUtils.isEmpty($r9);
        if (z) {
            if (this.mTextView == null) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(getContext(), (AttributeSet) null, R$attr.actionBarTabTextStyle);
                appCompatTextView.setEllipsize(TextUtils.TruncateAt.END);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                layoutParams2.gravity = 16;
                appCompatTextView.setLayoutParams(layoutParams2);
                addView(appCompatTextView);
                this.mTextView = appCompatTextView;
            }
            this.mTextView.setText($r9);
            this.mTextView.setVisibility(0);
        } else {
            TextView $r62 = this.mTextView;
            if ($r62 != null) {
                $r62.setVisibility(8);
                this.mTextView.setText((CharSequence) null);
            }
        }
        ImageView $r73 = this.mIconView;
        if ($r73 != null) {
            $r73.setContentDescription($r1.getContentDescription());
        }
        if (!z) {
            $r3 = $r1.getContentDescription();
        }
        MenuItemImpl.add(this, $r3);
    }
}

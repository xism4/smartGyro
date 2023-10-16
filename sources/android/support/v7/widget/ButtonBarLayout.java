package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.org.android.view.ViewCompat;
import com.org.v4.util.R$id;
import com.org.v4.util.R$styleable;

public class ButtonBarLayout extends LinearLayout {
    private boolean mAllowStacking;
    private int mHeight = 0;
    private int mLastWidthSize = -1;

    public ButtonBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray $r4 = context.obtainStyledAttributes(attributeSet, R$styleable.ButtonBarLayout);
        this.mAllowStacking = $r4.getBoolean(R$styleable.ButtonBarLayout_allowStacking, true);
        $r4.recycle();
    }

    private boolean isStacked() {
        return getOrientation() == 1;
    }

    private int open(int i) {
        int $i1 = getChildCount();
        while (i < $i1) {
            if (getChildAt(i).getVisibility() == 0) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private void setStacked(boolean $i0) {
        setOrientation((int) $i0);
        setGravity($i0 ? 5 : 80);
        View $r1 = findViewById(R$id.spacer);
        if ($r1 != null) {
            $r1.setVisibility($i0 ? 8 : 4);
        }
        for (int $i02 = getChildCount() - 2; $i02 >= 0; $i02--) {
            bringChildToFront(getChildAt($i02));
        }
    }

    public int getMinimumHeight() {
        return Math.max(this.mHeight, super.getMinimumHeight());
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        boolean $z0;
        int $i2;
        int $i22 = View.MeasureSpec.getSize(i);
        int $i3 = 0;
        if (this.mAllowStacking) {
            if ($i22 > this.mLastWidthSize && isStacked()) {
                setStacked(false);
            }
            this.mLastWidthSize = $i22;
        }
        if (isStacked() || View.MeasureSpec.getMode(i) != 1073741824) {
            $i2 = i;
            $z0 = false;
        } else {
            $i2 = View.MeasureSpec.makeMeasureSpec($i22, Integer.MIN_VALUE);
            $z0 = true;
        }
        super.onMeasure($i2, i2);
        if (this.mAllowStacking && !isStacked()) {
            if ((getMeasuredWidthAndState() & -16777216) == 16777216) {
                setStacked(true);
                $z0 = true;
            }
        }
        if ($z0) {
            super.onMeasure(i, i2);
        }
        int $i0 = open(0);
        if ($i0 >= 0) {
            View $r1 = getChildAt($i0);
            LinearLayout.LayoutParams $r3 = (LinearLayout.LayoutParams) $r1.getLayoutParams();
            int $i1 = getPaddingTop() + $r1.getMeasuredHeight() + $r3.topMargin + $r3.bottomMargin + 0;
            if (isStacked()) {
                int $i02 = open($i0 + 1);
                if ($i02 >= 0) {
                    $i1 += getChildAt($i02).getPaddingTop() + ((int) (getResources().getDisplayMetrics().density * 16.0f));
                }
                $i3 = $i1;
            } else {
                $i3 = $i1 + getPaddingBottom();
            }
        }
        if (ViewCompat.getMinimumHeight(this) != $i3) {
            setMinimumHeight($i3);
        }
    }

    public void setAllowStacking(boolean z) {
        if (this.mAllowStacking != z) {
            this.mAllowStacking = z;
            if (!this.mAllowStacking && getOrientation() == 1) {
                setStacked(false);
            }
            requestLayout();
        }
    }
}

package android.support.v7.widget;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.Spinner;
import com.org.v4.util.R$attr;
import com.org.v4.view.ActionBarPolicy;

public class ScrollingTabContainerView extends HorizontalScrollView implements AdapterView.OnItemSelectedListener {
    private static final Interpolator sAlphaInterpolator = new DecelerateInterpolator();
    private boolean mAllowCollapse;
    private int mContentHeight;
    int mMaxTabWidth;
    private int mSelectedTabIndex;
    int mStackedTabMaxWidth;
    private aa$b mTabClickListener;
    LinearLayoutCompat mTabLayout;
    Runnable mTabSelector;
    private Spinner mTabSpinner;

    private Spinner createSpinner() {
        AppCompatSpinner $r1 = new AppCompatSpinner(getContext(), (AttributeSet) null, R$attr.actionDropDownStyle);
        $r1.setLayoutParams(new LinearLayoutCompat.LayoutParams(-2, -1));
        $r1.setOnItemSelectedListener(this);
        return $r1;
    }

    private boolean isCollapsed() {
        Spinner $r2 = this.mTabSpinner;
        return $r2 != null && $r2.getParent() == this;
    }

    private void performCollapse() {
        if (!isCollapsed()) {
            if (this.mTabSpinner == null) {
                this.mTabSpinner = createSpinner();
            }
            removeView(this.mTabLayout);
            addView(this.mTabSpinner, new ViewGroup.LayoutParams(-2, -1));
            if (this.mTabSpinner.getAdapter() == null) {
                this.mTabSpinner.setAdapter(new aa$a(this));
            }
            Runnable $r6 = this.mTabSelector;
            if ($r6 != null) {
                removeCallbacks($r6);
                this.mTabSelector = null;
            }
            this.mTabSpinner.setSelection(this.mSelectedTabIndex);
        }
    }

    private boolean performExpand() {
        if (!isCollapsed()) {
            return false;
        }
        removeView(this.mTabSpinner);
        addView(this.mTabLayout, new ViewGroup.LayoutParams(-2, -1));
        setTabSelected(this.mTabSpinner.getSelectedItemPosition());
        return false;
    }

    public void animateToTab(int i) {
        final View $r1 = this.mTabLayout.getChildAt(i);
        Runnable $r3 = this.mTabSelector;
        if ($r3 != null) {
            removeCallbacks($r3);
        }
        this.mTabSelector = new Runnable() {
            public void run() {
                ScrollingTabContainerView.this.smoothScrollTo($r1.getLeft() - ((ScrollingTabContainerView.this.getWidth() - $r1.getWidth()) / 2), 0);
                ScrollingTabContainerView.this.mTabSelector = null;
            }
        };
        post(this.mTabSelector);
    }

    /* access modifiers changed from: package-private */
    public aa$c createTabView(ActionBar.Tab tab, boolean z) {
        aa$c $r1 = new aa$c(this, getContext(), tab, z);
        if (z) {
            $r1.setBackgroundDrawable((Drawable) null);
            $r1.setLayoutParams(new AbsListView.LayoutParams(-1, this.mContentHeight));
            return $r1;
        }
        $r1.setFocusable(true);
        if (this.mTabClickListener == null) {
            this.mTabClickListener = new aa$b(this);
        }
        $r1.setOnClickListener(this.mTabClickListener);
        return $r1;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Runnable $r1 = this.mTabSelector;
        if ($r1 != null) {
            post($r1);
        }
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ActionBarPolicy $r3 = ActionBarPolicy.get(getContext());
        setContentHeight($r3.getTabContainerHeight());
        this.mStackedTabMaxWidth = $r3.getStackedTabMaxWidth();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Runnable $r1 = this.mTabSelector;
        if ($r1 != null) {
            removeCallbacks($r1);
        }
    }

    public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
        ((aa$c) view).getTab().select();
    }

    public void onMeasure(int i, int i2) {
        int $i1;
        int $i12 = View.MeasureSpec.getMode(i);
        boolean $z0 = true;
        boolean $z1 = $i12 == 1073741824;
        setFillViewport($z1);
        int $i2 = this.mTabLayout.getChildCount();
        if ($i2 <= 1 || !($i12 == 1073741824 || $i12 == Integer.MIN_VALUE)) {
            $i1 = -1;
        } else {
            if ($i2 > 2) {
                this.mMaxTabWidth = (int) (((float) View.MeasureSpec.getSize(i)) * 0.4f);
            } else {
                this.mMaxTabWidth = View.MeasureSpec.getSize(i) / 2;
            }
            $i1 = Math.min(this.mMaxTabWidth, this.mStackedTabMaxWidth);
        }
        this.mMaxTabWidth = $i1;
        int $i13 = View.MeasureSpec.makeMeasureSpec(this.mContentHeight, 1073741824);
        if ($z1 || !this.mAllowCollapse) {
            $z0 = false;
        }
        if ($z0) {
            this.mTabLayout.measure(0, $i13);
            if (this.mTabLayout.getMeasuredWidth() > View.MeasureSpec.getSize(i)) {
                performCollapse();
                int $i22 = getMeasuredWidth();
                super.onMeasure(i, $i13);
                int $i0 = getMeasuredWidth();
                if ($z1 && $i22 != $i0) {
                    setTabSelected(this.mSelectedTabIndex);
                    return;
                }
            }
        }
        performExpand();
        int $i222 = getMeasuredWidth();
        super.onMeasure(i, $i13);
        int $i02 = getMeasuredWidth();
        if ($z1) {
        }
    }

    public void onNothingSelected(AdapterView adapterView) {
    }

    public void setAllowCollapse(boolean z) {
        this.mAllowCollapse = z;
    }

    public void setContentHeight(int i) {
        this.mContentHeight = i;
        requestLayout();
    }

    public void setTabSelected(int i) {
        this.mSelectedTabIndex = i;
        int $i1 = this.mTabLayout.getChildCount();
        int $i2 = 0;
        while ($i2 < $i1) {
            View $r2 = this.mTabLayout.getChildAt($i2);
            boolean $z0 = $i2 == i;
            $r2.setSelected($z0);
            if ($z0) {
                animateToTab(i);
            }
            $i2++;
        }
        Spinner $r3 = this.mTabSpinner;
        if ($r3 != null && i >= 0) {
            $r3.setSelection(i);
        }
    }
}

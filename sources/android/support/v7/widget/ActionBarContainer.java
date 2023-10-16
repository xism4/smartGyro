package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.org.android.view.ViewCompat;
import com.org.v4.util.R$id;
import com.org.v4.util.R$styleable;

public class ActionBarContainer extends FrameLayout {
    private View mActionBarView;
    Drawable mBackground;
    private View mContextView;
    private int mHeight;
    boolean mIsSplit;
    boolean mIsStacked;
    private boolean mIsTransitioning;
    Drawable mSplitBackground;
    Drawable mStackedBackground;
    private View mTabContainer;

    public ActionBarContainer(Context context) {
        this(context, (AttributeSet) null);
    }

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ViewCompat.setBackground(this, new CircularBorderDrawable(this));
        TypedArray $r5 = context.obtainStyledAttributes(attributeSet, R$styleable.ActionBar);
        this.mBackground = $r5.getDrawable(R$styleable.ActionBar_background);
        this.mStackedBackground = $r5.getDrawable(R$styleable.ActionBar_backgroundStacked);
        this.mHeight = $r5.getDimensionPixelSize(R$styleable.ActionBar_height, -1);
        if (getId() == R$id.split_action_bar) {
            this.mIsSplit = true;
            this.mSplitBackground = $r5.getDrawable(R$styleable.ActionBar_backgroundSplit);
        }
        $r5.recycle();
        boolean $z1 = false;
        if (!this.mIsSplit ? this.mBackground == null && this.mStackedBackground == null : this.mSplitBackground == null) {
            $z1 = true;
        }
        setWillNotDraw($z1);
    }

    private int getMeasuredHeightWithMargins(View view) {
        FrameLayout.LayoutParams $r3 = (FrameLayout.LayoutParams) view.getLayoutParams();
        return view.getMeasuredHeight() + $r3.topMargin + $r3.bottomMargin;
    }

    private boolean isCollapsed(View view) {
        return view == null || view.getVisibility() == 8 || view.getMeasuredHeight() == 0;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable $r1 = this.mBackground;
        if ($r1 != null && $r1.isStateful()) {
            this.mBackground.setState(getDrawableState());
        }
        Drawable $r12 = this.mStackedBackground;
        if ($r12 != null && $r12.isStateful()) {
            this.mStackedBackground.setState(getDrawableState());
        }
        Drawable $r13 = this.mSplitBackground;
        if ($r13 != null && $r13.isStateful()) {
            this.mSplitBackground.setState(getDrawableState());
        }
    }

    public View getTabContainer() {
        return this.mTabContainer;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable $r1 = this.mBackground;
        if ($r1 != null) {
            $r1.jumpToCurrentState();
        }
        Drawable $r12 = this.mStackedBackground;
        if ($r12 != null) {
            $r12.jumpToCurrentState();
        }
        Drawable $r13 = this.mSplitBackground;
        if ($r13 != null) {
            $r13.jumpToCurrentState();
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.mActionBarView = findViewById(R$id.action_bar);
        this.mContextView = findViewById(R$id.action_context_bar);
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.mIsTransitioning || super.onInterceptTouchEvent(motionEvent);
    }

    public void onLayout(boolean z, int $i2, int i, int i2, int i3) {
        Drawable $r4;
        Drawable $r42;
        int $i1;
        int $i22;
        int $i3;
        View $r5;
        super.onLayout(z, $i2, i, i2, i3);
        View $r1 = this.mTabContainer;
        boolean z2 = true;
        boolean $z1 = false;
        boolean $z2 = ($r1 == null || $r1.getVisibility() == 8) ? false : true;
        if (!($r1 == null || $r1.getVisibility() == 8)) {
            int $i23 = getMeasuredHeight();
            FrameLayout.LayoutParams $r3 = (FrameLayout.LayoutParams) $r1.getLayoutParams();
            $r1.layout($i2, ($i23 - $r1.getMeasuredHeight()) - $r3.bottomMargin, i2, $i23 - $r3.bottomMargin);
        }
        if (this.mIsSplit) {
            Drawable $r43 = this.mSplitBackground;
            if ($r43 != null) {
                $r43.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            } else {
                z2 = false;
            }
        } else {
            if (this.mBackground != null) {
                if (this.mActionBarView.getVisibility() == 0) {
                    $r42 = this.mBackground;
                    $i1 = this.mActionBarView.getLeft();
                    $i22 = this.mActionBarView.getTop();
                    $i3 = this.mActionBarView.getRight();
                    $r5 = this.mActionBarView;
                } else {
                    View $r52 = this.mContextView;
                    if ($r52 == null || $r52.getVisibility() != 0) {
                        this.mBackground.setBounds(0, 0, 0, 0);
                        $z1 = true;
                    } else {
                        $r42 = this.mBackground;
                        $i1 = this.mContextView.getLeft();
                        $i22 = this.mContextView.getTop();
                        $i3 = this.mContextView.getRight();
                        $r5 = this.mContextView;
                    }
                }
                $r42.setBounds($i1, $i22, $i3, $r5.getBottom());
                $z1 = true;
            }
            this.mIsStacked = $z2;
            if (!$z2 || ($r4 = this.mStackedBackground) == null) {
                z2 = $z1;
            } else {
                $r4.setBounds($r1.getLeft(), $r1.getTop(), $r1.getRight(), $r1.getBottom());
            }
        }
        if (z2) {
            invalidate();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0062  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r6, int r7) {
        /*
            r5 = this;
            android.view.View r0 = r5.mActionBarView
            if (r0 != 0) goto L_0x0020
            int r1 = android.view.View.MeasureSpec.getMode(r7)
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r1 != r2) goto L_0x0020
            int r1 = r5.mHeight
            if (r1 < 0) goto L_0x0020
            int r7 = android.view.View.MeasureSpec.getSize(r7)
            int r7 = java.lang.Math.min(r1, r7)
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r2)
        L_0x0020:
            super.onMeasure(r6, r7)
            android.view.View r0 = r5.mActionBarView
            if (r0 != 0) goto L_0x0028
            return
        L_0x0028:
            int r1 = android.view.View.MeasureSpec.getMode(r7)
            android.view.View r0 = r5.mTabContainer
            if (r0 == 0) goto L_0x0077
            int r6 = r0.getVisibility()
            r2 = 8
            if (r6 == r2) goto L_0x0077
            r2 = 1073741824(0x40000000, float:2.0)
            if (r1 == r2) goto L_0x0077
            android.view.View r0 = r5.mActionBarView
            boolean r3 = r5.isCollapsed(r0)
            if (r3 != 0) goto L_0x004c
            android.view.View r0 = r5.mActionBarView
        L_0x0047:
            int r6 = r5.getMeasuredHeightWithMargins(r0)
            goto L_0x0058
        L_0x004c:
            android.view.View r0 = r5.mContextView
            boolean r3 = r5.isCollapsed(r0)
            if (r3 != 0) goto L_0x0057
            android.view.View r0 = r5.mContextView
            goto L_0x0047
        L_0x0057:
            r6 = 0
        L_0x0058:
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r1 != r2) goto L_0x0062
            int r1 = android.view.View.MeasureSpec.getSize(r7)
            goto L_0x0065
        L_0x0062:
            r1 = 2147483647(0x7fffffff, float:NaN)
        L_0x0065:
            int r7 = r5.getMeasuredWidth()
            android.view.View r0 = r5.mTabContainer
            int r4 = r5.getMeasuredHeightWithMargins(r0)
            int r6 = r6 + r4
            int r6 = java.lang.Math.min(r6, r1)
            r5.setMeasuredDimension(r7, r6)
        L_0x0077:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActionBarContainer.onMeasure(int, int):void");
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable $r2 = this.mBackground;
        if ($r2 != null) {
            $r2.setCallback((Drawable.Callback) null);
            unscheduleDrawable(this.mBackground);
        }
        this.mBackground = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            View $r3 = this.mActionBarView;
            if ($r3 != null) {
                this.mBackground.setBounds($r3.getLeft(), this.mActionBarView.getTop(), this.mActionBarView.getRight(), this.mActionBarView.getBottom());
            }
        }
        boolean $z1 = true;
        if (!this.mIsSplit ? !(this.mBackground == null && this.mStackedBackground == null) : this.mSplitBackground != null) {
            $z1 = false;
        }
        setWillNotDraw($z1);
        invalidate();
    }

    public void setSplitBackground(Drawable drawable) {
        Drawable $r1;
        Drawable $r2 = this.mSplitBackground;
        if ($r2 != null) {
            $r2.setCallback((Drawable.Callback) null);
            unscheduleDrawable(this.mSplitBackground);
        }
        this.mSplitBackground = drawable;
        boolean $z0 = false;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.mIsSplit && ($r1 = this.mSplitBackground) != null) {
                $r1.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        if (!this.mIsSplit ? this.mBackground == null && this.mStackedBackground == null : this.mSplitBackground == null) {
            $z0 = true;
        }
        setWillNotDraw($z0);
        invalidate();
    }

    public void setStackedBackground(Drawable drawable) {
        Drawable $r1;
        Drawable $r2 = this.mStackedBackground;
        if ($r2 != null) {
            $r2.setCallback((Drawable.Callback) null);
            unscheduleDrawable(this.mStackedBackground);
        }
        this.mStackedBackground = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.mIsStacked && ($r1 = this.mStackedBackground) != null) {
                $r1.setBounds(this.mTabContainer.getLeft(), this.mTabContainer.getTop(), this.mTabContainer.getRight(), this.mTabContainer.getBottom());
            }
        }
        boolean $z1 = true;
        if (!this.mIsSplit ? !(this.mBackground == null && this.mStackedBackground == null) : this.mSplitBackground != null) {
            $z1 = false;
        }
        setWillNotDraw($z1);
        invalidate();
    }

    public void setTabContainer(ScrollingTabContainerView scrollingTabContainerView) {
        View $r3 = this.mTabContainer;
        if ($r3 != null) {
            removeView($r3);
        }
        this.mTabContainer = scrollingTabContainerView;
        if (scrollingTabContainerView != null) {
            addView(scrollingTabContainerView);
            ViewGroup.LayoutParams $r2 = scrollingTabContainerView.getLayoutParams();
            $r2.width = -1;
            $r2.height = -2;
            scrollingTabContainerView.setAllowCollapse(false);
        }
    }

    public void setTransitioning(boolean z) {
        this.mIsTransitioning = z;
        setDescendantFocusability(z ? 393216 : 262144);
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean $z0 = i == 0;
        Drawable $r1 = this.mBackground;
        if ($r1 != null) {
            $r1.setVisible($z0, false);
        }
        Drawable $r12 = this.mStackedBackground;
        if ($r12 != null) {
            $r12.setVisible($z0, false);
        }
        Drawable $r13 = this.mSplitBackground;
        if ($r13 != null) {
            $r13.setVisible($z0, false);
        }
    }

    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i) {
        if (i != 0) {
            return super.startActionModeForChild(view, callback, i);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        if (drawable == this.mBackground && !this.mIsSplit) {
            return true;
        }
        if (drawable != this.mStackedBackground || !this.mIsStacked) {
            return (drawable == this.mSplitBackground && this.mIsSplit) || super.verifyDrawable(drawable);
        }
        return true;
    }
}

package android.support.v7.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.org.android.view.ViewCompat;
import com.org.android.view.ViewPropertyAnimatorCompat;
import com.org.v4.util.R$attr;
import com.org.v4.util.R$id;
import com.org.v4.util.R$layout;
import com.org.v4.util.R$styleable;

public class ActionBarContextView extends AbsActionBarView {
    private View mClose;
    private int mCloseItemLayout;
    private View mCustomView;
    private CharSequence mSubtitle;
    private int mSubtitleStyleRes;
    private TextView mSubtitleView;
    private CharSequence mTitle;
    private LinearLayout mTitleLayout;
    private boolean mTitleOptional;
    private int mTitleStyleRes;
    private TextView mTitleView;

    public ActionBarContextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.actionModeStyle);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TintTypedArray $r4 = TintTypedArray.obtainStyledAttributes(context, attributeSet, R$styleable.ActionMode, i, 0);
        ViewCompat.setBackground(this, $r4.getDrawable(R$styleable.ActionMode_background));
        this.mTitleStyleRes = $r4.getResourceId(R$styleable.ActionMode_titleTextStyle, 0);
        this.mSubtitleStyleRes = $r4.getResourceId(R$styleable.ActionMode_subtitleTextStyle, 0);
        this.mContentHeight = $r4.getLayoutDimension(R$styleable.ActionMode_height, 0);
        this.mCloseItemLayout = $r4.getResourceId(R$styleable.ActionMode_closeItemLayout, R$layout.abc_action_mode_close_item_material);
        $r4.recycle();
    }

    private void initTitle() {
        if (this.mTitleLayout == null) {
            LayoutInflater.from(getContext()).inflate(R$layout.abc_action_bar_title_item, this);
            this.mTitleLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.mTitleView = (TextView) this.mTitleLayout.findViewById(R$id.action_bar_title);
            this.mSubtitleView = (TextView) this.mTitleLayout.findViewById(R$id.action_bar_subtitle);
            if (this.mTitleStyleRes != 0) {
                this.mTitleView.setTextAppearance(getContext(), this.mTitleStyleRes);
            }
            if (this.mSubtitleStyleRes != 0) {
                this.mSubtitleView.setTextAppearance(getContext(), this.mSubtitleStyleRes);
            }
        }
        this.mTitleView.setText(this.mTitle);
        this.mSubtitleView.setText(this.mSubtitle);
        boolean $z0 = !TextUtils.isEmpty(this.mTitle);
        boolean $z1 = !TextUtils.isEmpty(this.mSubtitle);
        int $i0 = 0;
        this.mSubtitleView.setVisibility($z1 ? 0 : 8);
        LinearLayout $r1 = this.mTitleLayout;
        if (!$z0 && !$z1) {
            $i0 = 8;
        }
        $r1.setVisibility($i0);
        if (this.mTitleLayout.getParent() == null) {
            addView(this.mTitleLayout);
        }
    }

    public void closeMode() {
        if (this.mClose == null) {
            killMode();
        }
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public CharSequence getSubtitle() {
        return this.mSubtitle;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x002d, code lost:
        if (r2.getParent() == null) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initForMode(com.org.v4.view.ActionMode r20) {
        /*
            r19 = this;
            r0 = r19
            android.view.View r2 = r0.mClose
            if (r2 != 0) goto L_0x0029
            r0 = r19
            android.content.Context r3 = r0.getContext()
            android.view.LayoutInflater r4 = android.view.LayoutInflater.from(r3)
            r0 = r19
            int r5 = r0.mCloseItemLayout
            r6 = 0
            r0 = r19
            android.view.View r2 = r4.inflate(r5, r0, r6)
            r0 = r19
            r0.mClose = r2
        L_0x001f:
            r0 = r19
            android.view.View r2 = r0.mClose
            r0 = r19
            r0.addView(r2)
            goto L_0x0030
        L_0x0029:
            android.view.ViewParent r7 = r2.getParent()
            if (r7 != 0) goto L_0x0030
            goto L_0x001f
        L_0x0030:
            r0 = r19
            android.view.View r2 = r0.mClose
            int r5 = com.org.v4.util.R$id.action_mode_close_button
            android.view.View r2 = r2.findViewById(r5)
            android.support.v7.widget.ActionBarContextView$1 r8 = new android.support.v7.widget.ActionBarContextView$1
            r0 = r19
            r1 = r20
            r8.<init>(r1)
            r2.setOnClickListener(r8)
            r0 = r20
            android.view.Menu r9 = r0.getMenu()
            r11 = r9
            android.support.v7.view.menu.MenuBuilder r11 = (android.support.v7.view.menu.MenuBuilder) r11
            r10 = r11
            r0 = r19
            android.support.v7.widget.ActionMenuPresenter r12 = r0.mActionMenuPresenter
            if (r12 == 0) goto L_0x0059
            r12.dismissPopupMenus()
        L_0x0059:
            android.support.v7.widget.ActionMenuPresenter r12 = new android.support.v7.widget.ActionMenuPresenter
            r0 = r19
            android.content.Context r3 = r0.getContext()
            r12.<init>(r3)
            r0 = r19
            r0.mActionMenuPresenter = r12
            r0 = r19
            android.support.v7.widget.ActionMenuPresenter r12 = r0.mActionMenuPresenter
            r6 = 1
            r12.setReserveOverflow(r6)
            android.view.ViewGroup$LayoutParams r13 = new android.view.ViewGroup$LayoutParams
            r6 = -2
            r14 = -1
            r13.<init>(r6, r14)
            r0 = r19
            android.support.v7.widget.ActionMenuPresenter r12 = r0.mActionMenuPresenter
            r0 = r19
            android.content.Context r3 = r0.mPopupContext
            r10.addMenuPresenter(r12, r3)
            r0 = r19
            android.support.v7.widget.ActionMenuPresenter r12 = r0.mActionMenuPresenter
            r0 = r19
            android.support.v7.view.menu.MenuView r15 = r12.getMenuView((android.view.ViewGroup) r0)
            r17 = r15
            android.support.v7.widget.ActionMenuView r17 = (android.support.v7.widget.ActionMenuView) r17
            r16 = r17
            r0 = r16
            r1 = r19
            r1.mMenuView = r0
            r0 = r19
            android.support.v7.widget.ActionMenuView r0 = r0.mMenuView
            r16 = r0
            r18 = 0
            r0 = r16
            r1 = r18
            com.org.android.view.ViewCompat.setBackground(r0, r1)
            r0 = r19
            android.support.v7.widget.ActionMenuView r0 = r0.mMenuView
            r16 = r0
            r0 = r19
            r1 = r16
            r0.addView(r1, r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActionBarContextView.initForMode(com.org.v4.view.ActionMode):void");
    }

    public boolean isTitleOptional() {
        return this.mTitleOptional;
    }

    public void killMode() {
        removeAllViews();
        this.mCustomView = null;
        this.mMenuView = null;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActionMenuPresenter $r1 = this.mActionMenuPresenter;
        if ($r1 != null) {
            $r1.hideOverflowMenu();
            this.mActionMenuPresenter.hideSubMenus();
        }
    }

    public /* bridge */ /* synthetic */ boolean onHoverEvent(MotionEvent motionEvent) {
        return super.onHoverEvent(motionEvent);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 32) {
            accessibilityEvent.setSource(this);
            accessibilityEvent.setClassName(ActionBarContextView.class.getName());
            accessibilityEvent.setPackageName(getContext().getPackageName());
            accessibilityEvent.setContentDescription(this.mTitle);
            return;
        }
        super.onInitializeAccessibilityEvent(accessibilityEvent);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int $i0, int i, int i2, int i3) {
        int $i3;
        boolean $z0 = ViewUtils.isLayoutRtl(this);
        int $i5 = $z0 ? (i2 - $i0) - getPaddingRight() : getPaddingLeft();
        int $i4 = getPaddingTop();
        int paddingTop = ((i3 - i) - getPaddingTop()) - getPaddingBottom();
        View $r1 = this.mClose;
        if ($r1 == null || $r1.getVisibility() == 8) {
            $i3 = $i5;
        } else {
            ViewGroup.MarginLayoutParams $r3 = (ViewGroup.MarginLayoutParams) this.mClose.getLayoutParams();
            int $i6 = $z0 ? $r3.rightMargin : $r3.leftMargin;
            int $i32 = $z0 ? $r3.leftMargin : $r3.rightMargin;
            int $i52 = AbsActionBarView.next($i5, $i6, $z0);
            $i3 = AbsActionBarView.next($i52 + positionChild(this.mClose, $i52, $i4, paddingTop, $z0), $i32, $z0);
        }
        LinearLayout $r4 = this.mTitleLayout;
        if (!($r4 == null || this.mCustomView != null || $r4.getVisibility() == 8)) {
            $i3 += positionChild(this.mTitleLayout, $i3, $i4, paddingTop, $z0);
        }
        View $r12 = this.mCustomView;
        if ($r12 != null) {
            positionChild($r12, $i3, $i4, paddingTop, $z0);
        }
        int paddingLeft = $z0 ? getPaddingLeft() : (i2 - $i0) - getPaddingRight();
        ActionMenuView $r5 = this.mMenuView;
        if ($r5 != null) {
            positionChild($r5, paddingLeft, $i4, paddingTop, !$z0);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int $i3 = 1073741824;
        if (View.MeasureSpec.getMode(i) != 1073741824) {
            throw new IllegalStateException(ActionBarContextView.class.getSimpleName() + " can only be used " + "with android:layout_width=\"match_parent\" (or fill_parent)");
        } else if (View.MeasureSpec.getMode(i2) != 0) {
            int $i0 = View.MeasureSpec.getSize(i);
            int $i2 = this.mContentHeight;
            if ($i2 <= 0) {
                $i2 = View.MeasureSpec.getSize(i2);
            }
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int $i6 = ($i0 - getPaddingLeft()) - getPaddingRight();
            int $i5 = $i2 - paddingTop;
            int $i7 = View.MeasureSpec.makeMeasureSpec($i5, Integer.MIN_VALUE);
            View $r1 = this.mClose;
            if ($r1 != null) {
                int $i62 = measureChildView($r1, $i6, $i7, 0);
                ViewGroup.MarginLayoutParams $r3 = (ViewGroup.MarginLayoutParams) this.mClose.getLayoutParams();
                $i6 = $i62 - ($r3.leftMargin + $r3.rightMargin);
            }
            ActionMenuView $r4 = this.mMenuView;
            if ($r4 != null && $r4.getParent() == this) {
                $i6 = measureChildView(this.mMenuView, $i6, $i7, 0);
            }
            LinearLayout $r6 = this.mTitleLayout;
            if ($r6 != null && this.mCustomView == null) {
                if (this.mTitleOptional) {
                    int $i8 = View.MeasureSpec.makeMeasureSpec(0, 0);
                    LinearLayout $r62 = this.mTitleLayout;
                    LinearLayout linearLayout = $r62;
                    $r62.measure($i8, $i7);
                    LinearLayout $r63 = this.mTitleLayout;
                    LinearLayout linearLayout2 = $r63;
                    int $i72 = $r63.getMeasuredWidth();
                    boolean $z0 = $i72 <= $i6;
                    if ($z0) {
                        $i6 -= $i72;
                    }
                    this.mTitleLayout.setVisibility($z0 ? (byte) 0 : 8);
                } else {
                    $i6 = measureChildView($r6, $i6, $i7, 0);
                }
            }
            View $r12 = this.mCustomView;
            if ($r12 != null) {
                ViewGroup.LayoutParams $r2 = $r12.getLayoutParams();
                int $i73 = $r2.width != -2 ? 1073741824 : Integer.MIN_VALUE;
                int $i82 = $r2.width;
                if ($i82 >= 0) {
                    $i6 = Math.min($i82, $i6);
                }
                if ($r2.height == -2) {
                    $i3 = Integer.MIN_VALUE;
                }
                int $i83 = $r2.height;
                if ($i83 >= 0) {
                    $i5 = Math.min($i83, $i5);
                }
                this.mCustomView.measure(View.MeasureSpec.makeMeasureSpec($i6, $i73), View.MeasureSpec.makeMeasureSpec($i5, $i3));
            }
            if (this.mContentHeight <= 0) {
                int $i32 = getChildCount();
                int $i22 = 0;
                for (int $i4 = 0; $i4 < $i32; $i4++) {
                    int $i52 = getChildAt($i4).getMeasuredHeight() + paddingTop;
                    if ($i52 > $i22) {
                        $i22 = $i52;
                    }
                }
                setMeasuredDimension($i0, $i22);
                return;
            }
            setMeasuredDimension($i0, $i2);
        } else {
            throw new IllegalStateException(ActionBarContextView.class.getSimpleName() + " can only be used " + "with android:layout_height=\"wrap_content\"");
        }
    }

    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    public void setContentHeight(int i) {
        this.mContentHeight = i;
    }

    public void setCustomView(View view) {
        LinearLayout $r3;
        View $r2 = this.mCustomView;
        if ($r2 != null) {
            removeView($r2);
        }
        this.mCustomView = view;
        if (!(view == null || ($r3 = this.mTitleLayout) == null)) {
            removeView($r3);
            this.mTitleLayout = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.mSubtitle = charSequence;
        initTitle();
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        initTitle();
    }

    public void setTitleOptional(boolean z) {
        if (z != this.mTitleOptional) {
            requestLayout();
        }
        this.mTitleOptional = z;
    }

    public /* bridge */ /* synthetic */ void setVisibility(int i) {
        super.setVisibility(i);
    }

    public /* bridge */ /* synthetic */ ViewPropertyAnimatorCompat setupAnimatorToVisibility(int i, long j) {
        return super.setupAnimatorToVisibility(i, j);
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public boolean showOverflowMenu() {
        ActionMenuPresenter $r1 = this.mActionMenuPresenter;
        if ($r1 != null) {
            return $r1.showOverflowMenu();
        }
        return false;
    }
}

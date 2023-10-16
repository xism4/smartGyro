package android.support.v7.widget;

import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.view.menu.l$a;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.widget.OverScroller;
import com.org.android.view.NestedScrollingParent;
import com.org.android.view.NestedScrollingParentHelper;
import com.org.android.view.ViewCompat;
import com.org.v4.util.R$attr;
import com.org.v4.util.R$id;

public class ActionBarOverlayLayout extends ViewGroup implements DecorContentParent, NestedScrollingParent {
    static final int[] ATTRS = {R$attr.actionBarSize, 16842841};
    final AnimatorListenerAdapter listener;
    private int mActionBarHeight;
    ActionBarContainer mActionBarTop;
    private a mActionBarVisibilityCallback;
    private final Runnable mAddActionBarHideOffset;
    boolean mAnimatingForFling;
    private final Rect mBaseContentInsets;
    private final Rect mBaseInnerInsets;
    private ContentFrameLayout mContent;
    private final Rect mContentInsets;
    ViewPropertyAnimator mCurrentActionBarTopAnimator;
    private DecorToolbar mDecorToolbar;
    private OverScroller mFlingEstimator;
    private boolean mHasNonEmbeddedTabs;
    private boolean mHideOnContentScroll;
    private int mHideOnContentScrollReference;
    private boolean mIgnoreWindowContentOverlay;
    private final Rect mInnerInsets;
    private final Rect mLastBaseContentInsets;
    private final Rect mLastInnerInsets;
    private int mLastSystemUiVisibility;
    private boolean mOverlayMode;
    private final NestedScrollingParentHelper mParentHelper;
    private final Runnable mRemoveActionBarHideOffset;
    private final Rect mTempRect1;
    private Drawable mWindowContentOverlay;
    private int mWindowVisibility;

    public interface a {
        void enableContentAnimations(boolean z);

        void hideForSystem();

        void onContentScrollStarted();

        void onContentScrollStopped();

        void onWindowVisibilityChanged(int i);

        void showForSystem();
    }

    public static class b extends ViewGroup.MarginLayoutParams {
        public b(int i, int i2) {
            super(i, i2);
        }

        public b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public b(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public ActionBarOverlayLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mWindowVisibility = 0;
        this.mBaseContentInsets = new Rect();
        this.mLastBaseContentInsets = new Rect();
        this.mContentInsets = new Rect();
        this.mBaseInnerInsets = new Rect();
        this.mTempRect1 = new Rect();
        this.mInnerInsets = new Rect();
        this.mLastInnerInsets = new Rect();
        this.listener = new ValueAnimatorCompatImplHoneycombMr1$2(this);
        this.mRemoveActionBarHideOffset = new AllInOneActivity$3(this);
        this.mAddActionBarHideOffset = new AgendaByDayAdapter$1(this);
        init(context);
        this.mParentHelper = new NestedScrollingParentHelper(this);
    }

    private void addActionBarHideOffset() {
        haltActionBarHideOffsetAnimations();
        this.mAddActionBarHideOffset.run();
    }

    private boolean applyInsets(View view, Rect rect, boolean z, boolean z2, boolean z3, boolean z4) {
        boolean $z1;
        int $i1;
        int $i12;
        int $i13;
        int $i14;
        b $r4 = (b) view.getLayoutParams();
        if (!z || $r4.leftMargin == ($i14 = rect.left)) {
            $z1 = false;
        } else {
            $r4.leftMargin = $i14;
            $z1 = true;
        }
        if (z2 && $r4.topMargin != ($i13 = rect.top)) {
            $r4.topMargin = $i13;
            $z1 = true;
        }
        if (z4 && $r4.rightMargin != ($i12 = rect.right)) {
            $r4.rightMargin = $i12;
            $z1 = true;
        }
        if (!z3 || $r4.bottomMargin == ($i1 = rect.bottom)) {
            return $z1;
        }
        $r4.bottomMargin = $i1;
        return true;
    }

    private DecorToolbar getDecorToolbar(View view) {
        if (view instanceof DecorToolbar) {
            return (DecorToolbar) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        throw new IllegalStateException("Can't make a decor toolbar out of " + view.getClass().getSimpleName());
    }

    private void init(Context context) {
        TypedArray $r5 = getContext().getTheme().obtainStyledAttributes(ATTRS);
        boolean $z0 = false;
        this.mActionBarHeight = $r5.getDimensionPixelSize(0, 0);
        this.mWindowContentOverlay = $r5.getDrawable(1);
        setWillNotDraw(this.mWindowContentOverlay == null);
        $r5.recycle();
        if (context.getApplicationInfo().targetSdkVersion < 19) {
            $z0 = true;
        }
        this.mIgnoreWindowContentOverlay = $z0;
        this.mFlingEstimator = new OverScroller(context);
    }

    private void postAddActionBarHideOffset() {
        haltActionBarHideOffsetAnimations();
        postDelayed(this.mAddActionBarHideOffset, 600);
    }

    private void postRemoveActionBarHideOffset() {
        haltActionBarHideOffsetAnimations();
        postDelayed(this.mRemoveActionBarHideOffset, 600);
    }

    private void removeActionBarHideOffset() {
        haltActionBarHideOffsetAnimations();
        this.mRemoveActionBarHideOffset.run();
    }

    private boolean shouldHideActionBarOnFling(float f, float f2) {
        this.mFlingEstimator.fling(0, 0, 0, (int) f2, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        int $i0 = this.mFlingEstimator.getFinalY();
        ActionBarContainer $r2 = this.mActionBarTop;
        ActionBarContainer actionBarContainer = $r2;
        return $i0 > $r2.getHeight();
    }

    public boolean canShowOverflowMenu() {
        pullChildren();
        return this.mDecorToolbar.canShowOverflowMenu();
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof b;
    }

    public void dismissPopups() {
        pullChildren();
        this.mDecorToolbar.dismissPopupMenus();
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.mWindowContentOverlay != null && !this.mIgnoreWindowContentOverlay) {
            int $i0 = this.mActionBarTop.getVisibility() == 0 ? (int) (((float) this.mActionBarTop.getBottom()) + this.mActionBarTop.getTranslationY() + 0.5f) : 0;
            this.mWindowContentOverlay.setBounds(0, $i0, getWidth(), this.mWindowContentOverlay.getIntrinsicHeight() + $i0);
            this.mWindowContentOverlay.draw(canvas);
        }
    }

    /* access modifiers changed from: protected */
    public boolean fitSystemWindows(Rect rect) {
        pullChildren();
        ViewCompat.a(this);
        boolean $z0 = applyInsets(this.mActionBarTop, rect, true, true, false, true);
        this.mBaseInnerInsets.set(rect);
        ViewUtils.computeFitSystemWindows(this, this.mBaseInnerInsets, this.mBaseContentInsets);
        if (!this.mTempRect1.equals(this.mBaseInnerInsets)) {
            this.mTempRect1.set(this.mBaseInnerInsets);
            $z0 = true;
        }
        if (!this.mLastBaseContentInsets.equals(this.mBaseContentInsets)) {
            this.mLastBaseContentInsets.set(this.mBaseContentInsets);
            $z0 = true;
        }
        if (!$z0) {
            return true;
        }
        requestLayout();
        return true;
    }

    /* access modifiers changed from: protected */
    public b generateDefaultLayoutParams() {
        return new b(-1, -1);
    }

    public b generateLayoutParams(AttributeSet attributeSet) {
        return new b(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new b(layoutParams);
    }

    public int getActionBarHideOffset() {
        ActionBarContainer $r1 = this.mActionBarTop;
        if ($r1 != null) {
            return -((int) $r1.getTranslationY());
        }
        return 0;
    }

    public int getNestedScrollAxes() {
        return this.mParentHelper.getNestedScrollAxes();
    }

    public CharSequence getTitle() {
        pullChildren();
        return this.mDecorToolbar.getTitle();
    }

    /* access modifiers changed from: package-private */
    public void haltActionBarHideOffsetAnimations() {
        removeCallbacks(this.mRemoveActionBarHideOffset);
        removeCallbacks(this.mAddActionBarHideOffset);
        ViewPropertyAnimator $r2 = this.mCurrentActionBarTopAnimator;
        if ($r2 != null) {
            $r2.cancel();
        }
    }

    public boolean hideOverflowMenu() {
        pullChildren();
        return this.mDecorToolbar.hideOverflowMenu();
    }

    public void initFeature(int i) {
        pullChildren();
        if (i == 2) {
            this.mDecorToolbar.initIndeterminateProgress();
        } else if (i == 5) {
            this.mDecorToolbar.initProgress();
        } else if (i == 109) {
            setOverlayMode(true);
        }
    }

    public boolean isInOverlayMode() {
        return this.mOverlayMode;
    }

    public boolean isOverflowMenuShowPending() {
        pullChildren();
        return this.mDecorToolbar.isOverflowMenuShowPending();
    }

    public boolean isOverflowMenuShowing() {
        pullChildren();
        return this.mDecorToolbar.isOverflowMenuShowing();
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        init(getContext());
        ViewCompat.requestApplyInsets(this);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        haltActionBarHideOffsetAnimations();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int $i0 = getChildCount();
        int $i1 = getPaddingLeft();
        getPaddingRight();
        int $i2 = getPaddingTop();
        getPaddingBottom();
        for (int $i3 = 0; $i3 < $i0; $i3++) {
            View $r1 = getChildAt($i3);
            if ($r1.getVisibility() != 8) {
                b $r3 = (b) $r1.getLayoutParams();
                int $i5 = $r1.getMeasuredWidth();
                int $i4 = $r1.getMeasuredHeight();
                int $i6 = $r3.leftMargin + $i1;
                int $i7 = $r3.topMargin + $i2;
                $r1.layout($i6, $i7, $i5 + $i6, $i4 + $i7);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int $i5;
        pullChildren();
        measureChildWithMargins(this.mActionBarTop, i, 0, i2, 0);
        b $r3 = (b) this.mActionBarTop.getLayoutParams();
        int $i4 = Math.max(0, this.mActionBarTop.getMeasuredWidth() + $r3.leftMargin + $r3.rightMargin);
        int $i3 = Math.max(0, this.mActionBarTop.getMeasuredHeight() + $r3.topMargin + $r3.bottomMargin);
        int $i2 = View.combineMeasuredStates(0, this.mActionBarTop.getMeasuredState());
        boolean z = (ViewCompat.a(this) & 256) != 0;
        if (z) {
            $i5 = this.mActionBarHeight;
            if (this.mHasNonEmbeddedTabs && this.mActionBarTop.getTabContainer() != null) {
                $i5 += this.mActionBarHeight;
            }
        } else {
            $i5 = this.mActionBarTop.getVisibility() != 8 ? this.mActionBarTop.getMeasuredHeight() : 0;
        }
        this.mContentInsets.set(this.mBaseContentInsets);
        this.mInnerInsets.set(this.mBaseInnerInsets);
        Rect $r5 = (this.mOverlayMode || z) ? this.mInnerInsets : this.mContentInsets;
        $r5.top += $i5;
        $r5.bottom += 0;
        applyInsets(this.mContent, this.mContentInsets, true, true, true, true);
        if (!this.mLastInnerInsets.equals(this.mInnerInsets)) {
            this.mLastInnerInsets.set(this.mInnerInsets);
            this.mContent.dispatchFitSystemWindows(this.mInnerInsets);
        }
        measureChildWithMargins(this.mContent, i, 0, i2, 0);
        ContentFrameLayout $r7 = this.mContent;
        ContentFrameLayout contentFrameLayout = $r7;
        b $r32 = (b) $r7.getLayoutParams();
        ContentFrameLayout $r72 = this.mContent;
        ContentFrameLayout contentFrameLayout2 = $r72;
        int measuredWidth = $r72.getMeasuredWidth() + $r32.leftMargin + $r32.rightMargin;
        int i3 = measuredWidth;
        int $i42 = Math.max($i4, measuredWidth);
        ContentFrameLayout $r73 = this.mContent;
        ContentFrameLayout contentFrameLayout3 = $r73;
        int measuredHeight = $r73.getMeasuredHeight() + $r32.topMargin + $r32.bottomMargin;
        int i4 = measuredHeight;
        int $i32 = Math.max($i3, measuredHeight);
        ContentFrameLayout $r74 = this.mContent;
        ContentFrameLayout contentFrameLayout4 = $r74;
        int $i22 = View.combineMeasuredStates($i2, $r74.getMeasuredState());
        int $i52 = getPaddingLeft() + getPaddingRight();
        int i5 = $i52;
        int $i43 = $i42 + $i52;
        int $i53 = getPaddingTop() + getPaddingBottom();
        int i6 = $i53;
        setMeasuredDimension(View.resolveSizeAndState(Math.max($i43, getSuggestedMinimumWidth()), i, $i22), View.resolveSizeAndState(Math.max($i32 + $i53, getSuggestedMinimumHeight()), i2, $i22 << 16));
    }

    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (!this.mHideOnContentScroll || !z) {
            return false;
        }
        if (shouldHideActionBarOnFling(f, f2)) {
            addActionBarHideOffset();
        } else {
            removeActionBarHideOffset();
        }
        this.mAnimatingForFling = true;
        return true;
    }

    public boolean onNestedPreFling(View view, float f, float f2) {
        return false;
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        this.mHideOnContentScrollReference += i2;
        setActionBarHideOffset(this.mHideOnContentScrollReference);
    }

    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.mParentHelper.onNestedScrollAccepted(view, view2, i);
        this.mHideOnContentScrollReference = getActionBarHideOffset();
        haltActionBarHideOffsetAnimations();
        a $r4 = this.mActionBarVisibilityCallback;
        if ($r4 != null) {
            $r4.onContentScrollStarted();
        }
    }

    public boolean onStartNestedScroll(View view, View view2, int i) {
        if ((i & 2) == 0 || this.mActionBarTop.getVisibility() != 0) {
            return false;
        }
        return this.mHideOnContentScroll;
    }

    public void onStopNestedScroll(View view) {
        if (this.mHideOnContentScroll && !this.mAnimatingForFling) {
            if (this.mHideOnContentScrollReference <= this.mActionBarTop.getHeight()) {
                postRemoveActionBarHideOffset();
            } else {
                postAddActionBarHideOffset();
            }
        }
        a $r3 = this.mActionBarVisibilityCallback;
        if ($r3 != null) {
            $r3.onContentScrollStopped();
        }
    }

    public void onWindowSystemUiVisibilityChanged(int i) {
        if (Build.VERSION.SDK_INT >= 16) {
            super.onWindowSystemUiVisibilityChanged(i);
        }
        pullChildren();
        int $i1 = this.mLastSystemUiVisibility ^ i;
        this.mLastSystemUiVisibility = i;
        boolean $z0 = false;
        boolean $z1 = (i & 4) == 0;
        if ((i & 256) != 0) {
            $z0 = true;
        }
        a $r1 = this.mActionBarVisibilityCallback;
        if ($r1 != null) {
            $r1.enableContentAnimations(!$z0);
            if ($z1 || !$z0) {
                this.mActionBarVisibilityCallback.showForSystem();
            } else {
                this.mActionBarVisibilityCallback.hideForSystem();
            }
        }
        if (($i1 & 256) != 0 && this.mActionBarVisibilityCallback != null) {
            ViewCompat.requestApplyInsets(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.mWindowVisibility = i;
        a $r1 = this.mActionBarVisibilityCallback;
        if ($r1 != null) {
            $r1.onWindowVisibilityChanged(i);
        }
    }

    /* access modifiers changed from: package-private */
    public void pullChildren() {
        if (this.mContent == null) {
            this.mContent = (ContentFrameLayout) findViewById(R$id.action_bar_activity_content);
            this.mActionBarTop = (ActionBarContainer) findViewById(R$id.action_bar_container);
            this.mDecorToolbar = getDecorToolbar(findViewById(R$id.action_bar));
        }
    }

    public void setActionBarHideOffset(int i) {
        haltActionBarHideOffsetAnimations();
        this.mActionBarTop.setTranslationY((float) (-Math.max(0, Math.min(i, this.mActionBarTop.getHeight()))));
    }

    public void setActionBarVisibilityCallback(a aVar) {
        this.mActionBarVisibilityCallback = aVar;
        if (getWindowToken() != null) {
            this.mActionBarVisibilityCallback.onWindowVisibilityChanged(this.mWindowVisibility);
            int $i0 = this.mLastSystemUiVisibility;
            if ($i0 != 0) {
                onWindowSystemUiVisibilityChanged($i0);
                ViewCompat.requestApplyInsets(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z) {
        this.mHasNonEmbeddedTabs = z;
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        if (z != this.mHideOnContentScroll) {
            this.mHideOnContentScroll = z;
            if (!z) {
                haltActionBarHideOffsetAnimations();
                setActionBarHideOffset(0);
            }
        }
    }

    public void setIcon(int i) {
        pullChildren();
        this.mDecorToolbar.setIcon(i);
    }

    public void setIcon(Drawable drawable) {
        pullChildren();
        this.mDecorToolbar.setIcon(drawable);
    }

    public void setLogo(int i) {
        pullChildren();
        this.mDecorToolbar.setLogo(i);
    }

    public void setMenu(Menu menu, l$a l_a) {
        pullChildren();
        this.mDecorToolbar.setMenu(menu, l_a);
    }

    public void setMenuPrepared() {
        pullChildren();
        this.mDecorToolbar.setMenuPrepared();
    }

    public void setOverlayMode(boolean z) {
        this.mOverlayMode = z;
        this.mIgnoreWindowContentOverlay = z && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void setShowingForActionMode(boolean z) {
    }

    public void setUiOptions(int i) {
    }

    public void setWindowCallback(Window.Callback callback) {
        pullChildren();
        this.mDecorToolbar.setWindowCallback(callback);
    }

    public void setWindowTitle(CharSequence charSequence) {
        pullChildren();
        this.mDecorToolbar.setWindowTitle(charSequence);
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public boolean showOverflowMenu() {
        pullChildren();
        return this.mDecorToolbar.showOverflowMenu();
    }
}

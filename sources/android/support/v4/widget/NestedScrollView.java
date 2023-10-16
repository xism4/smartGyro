package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;
import com.org.android.view.AccessibilityDelegateCompat;
import com.org.android.view.NestedScrollingChild;
import com.org.android.view.NestedScrollingChildHelper;
import com.org.android.view.NestedScrollingParentHelper;
import com.org.android.view.Vector;
import com.org.android.view.ViewCompat;
import com.org.android.view.ViewParentCompat;
import com.org.android.view.accessibility.AccessibilityNodeInfoCompat;
import com.org.android.view.accessibility.AccessibilityRecordCompat;
import java.util.ArrayList;

public class NestedScrollView extends FrameLayout implements ViewParentCompat.ViewParentCompatImpl, Vector, NestedScrollingChild {
    private static final a ACCESSIBILITY_DELEGATE = new a();
    private static final int[] SCROLLVIEW_STYLEABLE = {16843130};
    private int mActivePointerId;
    private final NestedScrollingChildHelper mChildHelper;
    private View mChildToScrollTo;
    private EdgeEffect mEdgeGlowBottom;
    private EdgeEffect mEdgeGlowTop;
    private boolean mFillViewport;
    private boolean mIsBeingDragged;
    private boolean mIsLaidOut;
    private boolean mIsLayoutDirty;
    private int mLastMotionY;
    private long mLastScroll;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private int mNestedYOffset;
    private b mOnScrollChangeListener;
    private final NestedScrollingParentHelper mParentHelper;
    private int mPreviousScrollerY;
    private c mSavedState;
    private final int[] mScrollConsumed;
    private final int[] mScrollOffset;
    private OverScroller mScroller;
    private boolean mSmoothScrollingEnabled;
    private final Rect mTempRect;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private float mVerticalScrollFactor;

    static class a extends AccessibilityDelegateCompat {
        a() {
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            NestedScrollView $r3 = (NestedScrollView) view;
            accessibilityEvent.setClassName(ScrollView.class.getName());
            accessibilityEvent.setScrollable($r3.getScrollRange() > 0);
            accessibilityEvent.setScrollX($r3.getScrollX());
            accessibilityEvent.setScrollY($r3.getScrollY());
            AccessibilityRecordCompat.setMaxScrollX(accessibilityEvent, $r3.getScrollX());
            AccessibilityRecordCompat.obtain(accessibilityEvent, $r3.getScrollRange());
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            int $i0;
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            NestedScrollView $r3 = (NestedScrollView) view;
            accessibilityNodeInfoCompat.setClassName(ScrollView.class.getName());
            if ($r3.isEnabled() && ($i0 = $r3.getScrollRange()) > 0) {
                accessibilityNodeInfoCompat.setScrollable(true);
                if ($r3.getScrollY() > 0) {
                    accessibilityNodeInfoCompat.addAction(8192);
                }
                if ($r3.getScrollY() < $i0) {
                    accessibilityNodeInfoCompat.addAction(4096);
                }
            }
        }

        public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            if (super.performAccessibilityAction(view, i, bundle)) {
                return true;
            }
            NestedScrollView $r3 = (NestedScrollView) view;
            if (!$r3.isEnabled()) {
                return false;
            }
            if (i == 4096) {
                int $i0 = Math.min($r3.getScrollY() + (($r3.getHeight() - $r3.getPaddingBottom()) - $r3.getPaddingTop()), $r3.getScrollRange());
                if ($i0 == $r3.getScrollY()) {
                    return false;
                }
                $r3.smoothScrollTo(0, $i0);
                return true;
            } else if (i != 8192) {
                return false;
            } else {
                int $i02 = Math.max($r3.getScrollY() - (($r3.getHeight() - $r3.getPaddingBottom()) - $r3.getPaddingTop()), 0);
                if ($i02 == $r3.getScrollY()) {
                    return false;
                }
                $r3.smoothScrollTo(0, $i02);
                return true;
            }
        }
    }

    public interface b {
        void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4);
    }

    static class c extends View.BaseSavedState {
        public static final Parcelable.Creator<c> CREATOR = new VerticalProgressBar$SavedState$1();
        public int scrollPosition;

        c(Parcel parcel) {
            super(parcel);
            this.scrollPosition = parcel.readInt();
        }

        c(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.scrollPosition + "}";
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.scrollPosition);
        }
    }

    public NestedScrollView(Context context) {
        this(context, (AttributeSet) null);
    }

    public NestedScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NestedScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTempRect = new Rect();
        this.mIsLayoutDirty = true;
        this.mIsLaidOut = false;
        this.mChildToScrollTo = null;
        this.mIsBeingDragged = false;
        this.mSmoothScrollingEnabled = true;
        this.mActivePointerId = -1;
        this.mScrollOffset = new int[2];
        this.mScrollConsumed = new int[2];
        initScrollView();
        TypedArray $r5 = context.obtainStyledAttributes(attributeSet, SCROLLVIEW_STYLEABLE, i, 0);
        setFillViewport($r5.getBoolean(0, false));
        $r5.recycle();
        this.mParentHelper = new NestedScrollingParentHelper(this);
        this.mChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
        ViewCompat.setAccessibilityDelegate(this, ACCESSIBILITY_DELEGATE);
    }

    private static int clamp(int $i2, int i, int i2) {
        if (i >= i2 || $i2 < 0) {
            return 0;
        }
        return i + $i2 > i2 ? i2 - i : $i2;
    }

    private void doScrollY(int i) {
        if (i == 0) {
            return;
        }
        if (this.mSmoothScrollingEnabled) {
            smoothScrollBy(0, i);
        } else {
            scrollBy(0, i);
        }
    }

    private void endDrag() {
        this.mIsBeingDragged = false;
        recycleVelocityTracker();
        stopNestedScroll(0);
        EdgeEffect $r1 = this.mEdgeGlowTop;
        if ($r1 != null) {
            $r1.onRelease();
            this.mEdgeGlowBottom.onRelease();
        }
    }

    private void ensureGlows() {
        if (getOverScrollMode() == 2) {
            this.mEdgeGlowTop = null;
            this.mEdgeGlowBottom = null;
        } else if (this.mEdgeGlowTop == null) {
            Context $r2 = getContext();
            this.mEdgeGlowTop = new EdgeEffect($r2);
            this.mEdgeGlowBottom = new EdgeEffect($r2);
        }
    }

    private View findFocusableViewInBounds(boolean z, int i, int i2) {
        ArrayList $r1 = getFocusables(2);
        int $i2 = $r1.size();
        View $r2 = null;
        boolean $z1 = false;
        for (int $i3 = 0; $i3 < $i2; $i3++) {
            View $r4 = $r1.get($i3);
            int $i4 = $r4.getTop();
            int $i5 = $r4.getBottom();
            if (i < $i5 && $i4 < i2) {
                boolean $z2 = i < $i4 && $i5 < i2;
                if ($r2 == null) {
                    $r2 = $r4;
                    $z1 = $z2;
                } else {
                    boolean $z3 = (z && $i4 < $r2.getTop()) || (!z && $i5 > $r2.getBottom());
                    if ($z1) {
                        if ($z2) {
                            if (!$z3) {
                            }
                        }
                    } else if ($z2) {
                        $r2 = $r4;
                        $z1 = true;
                    } else if (!$z3) {
                    }
                    $r2 = $r4;
                }
            }
        }
        return $r2;
    }

    private void flingWithNestedDispatch(int i) {
        int $i1 = getScrollY();
        boolean $z0 = ($i1 > 0 || i > 0) && ($i1 < getScrollRange() || i < 0);
        float $f0 = (float) i;
        if (!dispatchNestedPreFling(0.0f, $f0)) {
            dispatchNestedFling(0.0f, $f0, $z0);
            fling(i);
        }
    }

    private float getVerticalScrollFactorCompat() {
        if (this.mVerticalScrollFactor == 0.0f) {
            TypedValue $r1 = new TypedValue();
            Context $r2 = getContext();
            if ($r2.getTheme().resolveAttribute(16842829, $r1, true)) {
                this.mVerticalScrollFactor = $r1.getDimension($r2.getResources().getDisplayMetrics());
            } else {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
        }
        return this.mVerticalScrollFactor;
    }

    private boolean inChild(int i, int i2) {
        if (getChildCount() <= 0) {
            return false;
        }
        int $i2 = getScrollY();
        View $r1 = getChildAt(0);
        return i2 >= $r1.getTop() - $i2 && i2 < $r1.getBottom() - $i2 && i >= $r1.getLeft() && i < $r1.getRight();
    }

    private void initOrResetVelocityTracker() {
        VelocityTracker $r1 = this.mVelocityTracker;
        if ($r1 == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        } else {
            $r1.clear();
        }
    }

    private void initScrollView() {
        this.mScroller = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration $r3 = ViewConfiguration.get(getContext());
        this.mTouchSlop = $r3.getScaledTouchSlop();
        this.mMinimumVelocity = $r3.getScaledMinimumFlingVelocity();
        this.mMaximumVelocity = $r3.getScaledMaximumFlingVelocity();
    }

    private void initVelocityTrackerIfNotExists() {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
    }

    private boolean isOffScreen(View view) {
        return !isWithinDeltaOfScreen(view, 0, getHeight());
    }

    private static boolean isViewDescendantOf(View view, View view2) {
        if (view == view2) {
            return true;
        }
        ViewParent $r2 = view.getParent();
        return ($r2 instanceof ViewGroup) && isViewDescendantOf((View) $r2, view2);
    }

    private boolean isWithinDeltaOfScreen(View view, int i, int i2) {
        view.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(view, this.mTempRect);
        return this.mTempRect.bottom + i >= getScrollY() && this.mTempRect.top - i <= getScrollY() + i2;
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int $i1 = motionEvent.getActionIndex();
        if (motionEvent.getPointerId($i1) == this.mActivePointerId) {
            byte $b3 = $i1 == 0 ? (byte) 1 : 0;
            this.mLastMotionY = (int) motionEvent.getY($b3);
            this.mActivePointerId = motionEvent.getPointerId($b3);
            VelocityTracker $r2 = this.mVelocityTracker;
            if ($r2 != null) {
                $r2.clear();
            }
        }
    }

    private void recycleVelocityTracker() {
        VelocityTracker $r1 = this.mVelocityTracker;
        if ($r1 != null) {
            $r1.recycle();
            this.mVelocityTracker = null;
        }
    }

    private boolean scrollAndFocus(int i, int i2, int i3) {
        int $i3 = getHeight();
        int $i4 = getScrollY();
        int $i32 = $i3 + $i4;
        boolean $z0 = false;
        boolean $z1 = i == 33;
        View $r1 = findFocusableViewInBounds($z1, i2, i3);
        View $r2 = $r1;
        if ($r1 == null) {
            $r2 = this;
        }
        if (i2 < $i4 || i3 > $i32) {
            doScrollY($z1 ? i2 - $i4 : i3 - $i32);
            $z0 = true;
        }
        if ($r2 != findFocus()) {
            $r2.requestFocus(i);
        }
        return $z0;
    }

    private void scrollToChild(View view) {
        view.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(view, this.mTempRect);
        int $i0 = computeScrollDeltaToGetChildRectOnScreen(this.mTempRect);
        if ($i0 != 0) {
            scrollBy(0, $i0);
        }
    }

    private boolean scrollToChildRect(Rect rect, boolean z) {
        int $i0 = computeScrollDeltaToGetChildRectOnScreen(rect);
        boolean $z1 = $i0 != 0;
        if ($z1) {
            if (z) {
                scrollBy(0, $i0);
                return $z1;
            }
            smoothScrollBy(0, $i0);
        }
        return $z1;
    }

    private boolean show() {
        if (getChildCount() <= 0) {
            return false;
        }
        View $r1 = getChildAt(0);
        FrameLayout.LayoutParams $r3 = (FrameLayout.LayoutParams) $r1.getLayoutParams();
        return ($r1.getHeight() + $r3.topMargin) + $r3.bottomMargin > (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    public void addView(View view) {
        if (getChildCount() <= 0) {
            super.addView(view);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public void addView(View view, int i) {
        if (getChildCount() <= 0) {
            super.addView(view, i);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, i, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public boolean arrowScroll(int i) {
        View $r1 = findFocus();
        View $r2 = $r1;
        if ($r1 == this) {
            $r2 = null;
        }
        View $r12 = FocusFinder.getInstance().findNextFocus(this, $r2, i);
        int $i1 = getMaxScrollAmount();
        int $i2 = $i1;
        if ($r12 == null || !isWithinDeltaOfScreen($r12, $i1, getHeight())) {
            if (i == 33 && getScrollY() < $i1) {
                $i2 = getScrollY();
            } else if (i == 130 && getChildCount() > 0) {
                View $r13 = getChildAt(0);
                $i2 = Math.min(($r13.getBottom() + ((FrameLayout.LayoutParams) $r13.getLayoutParams()).bottomMargin) - ((getScrollY() + getHeight()) - getPaddingBottom()), $i1);
            }
            if ($i2 == 0) {
                return false;
            }
            if (i != 130) {
                $i2 = -$i2;
            }
            doScrollY($i2);
        } else {
            $r12.getDrawingRect(this.mTempRect);
            offsetDescendantRectToMyCoords($r12, this.mTempRect);
            doScrollY(computeScrollDeltaToGetChildRectOnScreen(this.mTempRect));
            $r12.requestFocus(i);
        }
        if ($r2 == null || !$r2.isFocused() || !isOffScreen($r2)) {
            return true;
        }
        int $i0 = getDescendantFocusability();
        setDescendantFocusability(131072);
        requestFocus();
        setDescendantFocusability($i0);
        return true;
    }

    public int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    public int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    public int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    public void computeScroll() {
        EdgeEffect $r3;
        if (this.mScroller.computeScrollOffset()) {
            this.mScroller.getCurrX();
            int $i0 = this.mScroller.getCurrY();
            int $i2 = $i0 - this.mPreviousScrollerY;
            if (dispatchNestedPreScroll(0, $i2, this.mScrollConsumed, (int[]) null, 1)) {
                $i2 -= this.mScrollConsumed[1];
            }
            if ($i2 != 0) {
                int $i1 = getScrollRange();
                int $i3 = getScrollY();
                overScrollByCompat(0, $i2, getScrollX(), $i3, 0, $i1, 0, 0, false);
                int $i4 = getScrollY() - $i3;
                if (!dispatchNestedScroll(0, $i4, 0, $i2 - $i4, (int[]) null, 1)) {
                    int $i22 = getOverScrollMode();
                    if ($i22 == 0 || ($i22 == 1 && $i1 > 0)) {
                        ensureGlows();
                        if ($i0 <= 0 && $i3 > 0) {
                            $r3 = this.mEdgeGlowTop;
                        } else if ($i0 >= $i1 && $i3 < $i1) {
                            $r3 = this.mEdgeGlowBottom;
                        }
                        $r3.onAbsorb((int) this.mScroller.getCurrVelocity());
                    }
                }
            }
            this.mPreviousScrollerY = $i0;
            ViewCompat.postInvalidateOnAnimation(this);
            return;
        }
        if (hasNestedScrollingParent(1)) {
            stopNestedScroll(1);
        }
        this.mPreviousScrollerY = 0;
    }

    /* access modifiers changed from: protected */
    public int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        if (getChildCount() == 0) {
            return 0;
        }
        int $i1 = getHeight();
        int $i2 = getScrollY();
        int $i3 = $i2;
        int $i0 = $i2 + $i1;
        int $i4 = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            $i3 = $i2 + $i4;
        }
        View $r2 = getChildAt(0);
        FrameLayout.LayoutParams $r4 = (FrameLayout.LayoutParams) $r2.getLayoutParams();
        int $i42 = rect.bottom < ($r2.getHeight() + $r4.topMargin) + $r4.bottomMargin ? $i0 - $i4 : $i0;
        if (rect.bottom > $i42 && rect.top > $i3) {
            return Math.min((rect.height() > $i1 ? rect.top - $i3 : rect.bottom - $i42) + 0, ($r2.getBottom() + $r4.bottomMargin) - $i0);
        } else if (rect.top >= $i3 || rect.bottom >= $i42) {
            return 0;
        } else {
            return Math.max(rect.height() > $i1 ? 0 - ($i42 - rect.bottom) : 0 - ($i3 - rect.top), -getScrollY());
        }
    }

    public int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    public int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    public int computeVerticalScrollRange() {
        int $i0 = getChildCount();
        int $i1 = (getHeight() - getPaddingBottom()) - getPaddingTop();
        if ($i0 == 0) {
            return $i1;
        }
        View $r1 = getChildAt(0);
        int $i02 = $r1.getBottom() + ((FrameLayout.LayoutParams) $r1.getLayoutParams()).bottomMargin;
        int $i2 = getScrollY();
        int $i12 = Math.max(0, $i02 - $i1);
        return $i2 < 0 ? $i02 - $i2 : $i2 > $i12 ? $i02 + ($i2 - $i12) : $i02;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || executeKeyEvent(keyEvent);
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.mChildHelper.dispatchNestedFling(f, f2, z);
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.mChildHelper.dispatchNestedPreFling(f, f2);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return dispatchNestedPreScroll(i, i2, iArr, iArr2, 0);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        return this.mChildHelper.dispatchNestedPreScroll(i, i2, iArr, iArr2, i3);
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return dispatchNestedScroll(i, i2, i3, i4, iArr, 0);
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr, int i5) {
        return this.mChildHelper.dispatchNestedScroll(i, i2, i3, i4, iArr, i5);
    }

    public void draw(Canvas canvas) {
        int $i3;
        super.draw(canvas);
        if (this.mEdgeGlowTop != null) {
            int $i0 = getScrollY();
            int $i1 = 0;
            if (!this.mEdgeGlowTop.isFinished()) {
                int $i2 = canvas.save();
                int $i32 = getWidth();
                int $i4 = $i32;
                int $i5 = getHeight();
                int $i6 = $i5;
                int $i7 = Math.min(0, $i0);
                int $i8 = $i7;
                if (Build.VERSION.SDK_INT < 21 || getClipToPadding()) {
                    $i4 = $i32 - (getPaddingLeft() + getPaddingRight());
                    $i3 = getPaddingLeft() + 0;
                } else {
                    $i3 = 0;
                }
                if (Build.VERSION.SDK_INT >= 21 && getClipToPadding()) {
                    $i6 = $i5 - (getPaddingTop() + getPaddingBottom());
                    $i8 = $i7 + getPaddingTop();
                }
                canvas.translate((float) $i3, (float) $i8);
                this.mEdgeGlowTop.setSize($i4, $i6);
                if (this.mEdgeGlowTop.draw(canvas)) {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
                canvas.restoreToCount($i2);
            }
            if (!this.mEdgeGlowBottom.isFinished()) {
                int $i22 = canvas.save();
                int $i62 = getWidth();
                int $i52 = $i62;
                int $i72 = getHeight();
                int $i42 = $i72;
                int $i02 = Math.max(getScrollRange(), $i0) + $i72;
                if (Build.VERSION.SDK_INT < 21 || getClipToPadding()) {
                    $i52 = $i62 - (getPaddingLeft() + getPaddingRight());
                    $i1 = 0 + getPaddingLeft();
                }
                if (Build.VERSION.SDK_INT >= 21 && getClipToPadding()) {
                    $i42 = $i72 - (getPaddingTop() + getPaddingBottom());
                    $i02 -= getPaddingBottom();
                }
                canvas.translate((float) ($i1 - $i52), (float) $i02);
                canvas.rotate(180.0f, (float) $i52, 0.0f);
                this.mEdgeGlowBottom.setSize($i52, $i42);
                if (this.mEdgeGlowBottom.draw(canvas)) {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
                canvas.restoreToCount($i22);
            }
        }
    }

    public boolean executeKeyEvent(KeyEvent keyEvent) {
        this.mTempRect.setEmpty();
        short $s0 = 130;
        if (!show()) {
            if (!isFocused() || keyEvent.getKeyCode() == 4) {
                return false;
            }
            View $r3 = findFocus();
            View $r4 = $r3;
            if ($r3 == this) {
                $r4 = null;
            }
            View $r32 = FocusFinder.getInstance().findNextFocus(this, $r4, 130);
            return ($r32 == null || $r32 == this || !$r32.requestFocus(130)) ? false : true;
        } else if (keyEvent.getAction() != 0) {
            return false;
        } else {
            int $i1 = keyEvent.getKeyCode();
            if ($i1 == 19) {
                return !keyEvent.isAltPressed() ? arrowScroll(33) : fullScroll(33);
            }
            if ($i1 == 20) {
                return !keyEvent.isAltPressed() ? arrowScroll(130) : fullScroll(130);
            }
            if ($i1 != 62) {
                return false;
            }
            if (keyEvent.isShiftPressed()) {
                $s0 = 33;
            }
            pageScroll($s0);
            return false;
        }
    }

    public void fling(int i) {
        if (getChildCount() > 0) {
            startNestedScroll(2, 1);
            this.mScroller.fling(getScrollX(), getScrollY(), 0, i, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
            this.mPreviousScrollerY = getScrollY();
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public boolean fullScroll(int i) {
        int $i2;
        boolean $z0 = i == 130;
        int $i1 = getHeight();
        Rect $r1 = this.mTempRect;
        $r1.top = 0;
        $r1.bottom = $i1;
        if ($z0 && ($i2 = getChildCount()) > 0) {
            View $r2 = getChildAt($i2 - 1);
            this.mTempRect.bottom = $r2.getBottom() + ((FrameLayout.LayoutParams) $r2.getLayoutParams()).bottomMargin + getPaddingBottom();
            Rect $r12 = this.mTempRect;
            $r12.top = $r12.bottom - $i1;
        }
        Rect $r13 = this.mTempRect;
        return scrollAndFocus(i, $r13.top, $r13.bottom);
    }

    /* access modifiers changed from: protected */
    public float getBottomFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        View $r1 = getChildAt(0);
        int $i0 = getVerticalFadingEdgeLength();
        int $i1 = (($r1.getBottom() + ((FrameLayout.LayoutParams) $r1.getLayoutParams()).bottomMargin) - getScrollY()) - (getHeight() - getPaddingBottom());
        if ($i1 < $i0) {
            return ((float) $i1) / ((float) $i0);
        }
        return 1.0f;
    }

    public int getMaxScrollAmount() {
        return (int) (((float) getHeight()) * 0.5f);
    }

    public int getNestedScrollAxes() {
        return this.mParentHelper.getNestedScrollAxes();
    }

    /* access modifiers changed from: package-private */
    public int getScrollRange() {
        if (getChildCount() <= 0) {
            return 0;
        }
        View $r1 = getChildAt(0);
        FrameLayout.LayoutParams $r3 = (FrameLayout.LayoutParams) $r1.getLayoutParams();
        return Math.max(0, (($r1.getHeight() + $r3.topMargin) + $r3.bottomMargin) - ((getHeight() - getPaddingTop()) - getPaddingBottom()));
    }

    /* access modifiers changed from: protected */
    public float getTopFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int $i0 = getVerticalFadingEdgeLength();
        int $i1 = getScrollY();
        if ($i1 < $i0) {
            return ((float) $i1) / ((float) $i0);
        }
        return 1.0f;
    }

    public boolean hasNestedScrollingParent() {
        return hasNestedScrollingParent(0);
    }

    public boolean hasNestedScrollingParent(int i) {
        return this.mChildHelper.hasNestedScrollingParent(i);
    }

    public boolean isNestedScrollingEnabled() {
        return this.mChildHelper.isNestedScrollingEnabled();
    }

    /* access modifiers changed from: protected */
    public void measureChild(View view, int i, int i2) {
        view.measure(ViewGroup.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight(), view.getLayoutParams().width), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    /* access modifiers changed from: protected */
    public void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams $r3 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + $r3.leftMargin + $r3.rightMargin + i2, $r3.width), View.MeasureSpec.makeMeasureSpec($r3.topMargin + $r3.bottomMargin, 0));
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mIsLaidOut = false;
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) == 0 || motionEvent.getAction() != 8 || this.mIsBeingDragged) {
            return false;
        }
        float $f0 = motionEvent.getAxisValue(9);
        if ($f0 == 0.0f) {
            return false;
        }
        int $i0 = getScrollRange();
        int $i3 = getScrollY();
        int $i2 = $i3 - ((int) ($f0 * getVerticalScrollFactorCompat()));
        if ($i2 < 0) {
            $i2 = 0;
        } else if ($i2 > $i0) {
            $i2 = $i0;
        }
        if ($i2 == $i3) {
            return false;
        }
        super.scrollTo(getScrollX(), $i2);
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int $i0 = motionEvent.getAction();
        if ($i0 == 2 && this.mIsBeingDragged) {
            return true;
        }
        int $i02 = $i0 & 255;
        if ($i02 != 0) {
            if ($i02 != 1) {
                if ($i02 == 2) {
                    int $i03 = this.mActivePointerId;
                    if ($i03 != -1) {
                        int $i1 = motionEvent.findPointerIndex($i03);
                        if ($i1 == -1) {
                            Log.e("NestedScrollView", "Invalid pointerId=" + $i03 + " in onInterceptTouchEvent");
                        } else {
                            int $i04 = (int) motionEvent.getY($i1);
                            if (Math.abs($i04 - this.mLastMotionY) > this.mTouchSlop && (2 & getNestedScrollAxes()) == 0) {
                                this.mIsBeingDragged = true;
                                this.mLastMotionY = $i04;
                                initVelocityTrackerIfNotExists();
                                VelocityTracker $r4 = this.mVelocityTracker;
                                VelocityTracker velocityTracker = $r4;
                                $r4.addMovement(motionEvent);
                                this.mNestedYOffset = 0;
                                ViewParent $r5 = getParent();
                                if ($r5 != null) {
                                    $r5.requestDisallowInterceptTouchEvent(true);
                                }
                            }
                        }
                    }
                } else if ($i02 != 3) {
                    if ($i02 == 6) {
                        onSecondaryPointerUp(motionEvent);
                    }
                }
            }
            this.mIsBeingDragged = false;
            this.mActivePointerId = -1;
            recycleVelocityTracker();
            if (this.mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                ViewCompat.postInvalidateOnAnimation(this);
            }
            stopNestedScroll(0);
        } else {
            int $i05 = (int) motionEvent.getY();
            if (!inChild((int) motionEvent.getX(), $i05)) {
                this.mIsBeingDragged = false;
                recycleVelocityTracker();
            } else {
                this.mLastMotionY = $i05;
                this.mActivePointerId = motionEvent.getPointerId(0);
                initOrResetVelocityTracker();
                VelocityTracker $r42 = this.mVelocityTracker;
                VelocityTracker velocityTracker2 = $r42;
                $r42.addMovement(motionEvent);
                OverScroller $r6 = this.mScroller;
                OverScroller overScroller = $r6;
                $r6.computeScrollOffset();
                OverScroller $r62 = this.mScroller;
                OverScroller overScroller2 = $r62;
                this.mIsBeingDragged = !$r62.isFinished();
                startNestedScroll(2, 0);
            }
        }
        return this.mIsBeingDragged;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int $i1 = 0;
        this.mIsLayoutDirty = false;
        View $r1 = this.mChildToScrollTo;
        if ($r1 != null && isViewDescendantOf($r1, this)) {
            scrollToChild(this.mChildToScrollTo);
        }
        this.mChildToScrollTo = null;
        if (!this.mIsLaidOut) {
            if (this.mSavedState != null) {
                scrollTo(getScrollX(), this.mSavedState.scrollPosition);
                this.mSavedState = null;
            }
            if (getChildCount() > 0) {
                View $r12 = getChildAt(0);
                FrameLayout.LayoutParams $r4 = (FrameLayout.LayoutParams) $r12.getLayoutParams();
                $i1 = $r12.getMeasuredHeight() + $r4.topMargin + $r4.bottomMargin;
            }
            int $i2 = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
            int $i0 = getScrollY();
            int $i12 = clamp($i0, $i2, $i1);
            if ($i12 != $i0) {
                scrollTo(getScrollX(), $i12);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.mIsLaidOut = true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mFillViewport && View.MeasureSpec.getMode(i2) != 0 && getChildCount() > 0) {
            View $r1 = getChildAt(0);
            FrameLayout.LayoutParams $r3 = (FrameLayout.LayoutParams) $r1.getLayoutParams();
            int $i2 = $r1.getMeasuredHeight();
            int $i1 = (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - $r3.topMargin) - $r3.bottomMargin;
            if ($i2 < $i1) {
                $r1.measure(ViewGroup.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + $r3.leftMargin + $r3.rightMargin, $r3.width), View.MeasureSpec.makeMeasureSpec($i1, 1073741824));
            }
        }
    }

    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (z) {
            return false;
        }
        flingWithNestedDispatch((int) f2);
        return true;
    }

    public boolean onNestedPreFling(View view, float f, float f2) {
        return dispatchNestedPreFling(f, f2);
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        onNestedPreScroll(view, i, i2, iArr, 0);
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr, int i3) {
        dispatchNestedPreScroll(i, i2, iArr, (int[]) null, i3);
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        scroll(view, i, i2, i3, i4, 0);
    }

    public void onNestedScrollAccepted(View view, View view2, int i) {
        onNestedScrollAccepted(view, view2, i, 0);
    }

    public void onNestedScrollAccepted(View view, View view2, int i, int i2) {
        this.mParentHelper.onNestedScrollAccepted(view, view2, i, i2);
        startNestedScroll(2, i2);
    }

    /* access modifiers changed from: protected */
    public void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        super.scrollTo(i, i2);
    }

    /* access modifiers changed from: protected */
    public boolean onRequestFocusInDescendants(int $i0, Rect rect) {
        if ($i0 == 2) {
            $i0 = 130;
        } else if ($i0 == 1) {
            $i0 = 33;
        }
        View $r3 = rect == null ? FocusFinder.getInstance().findNextFocus(this, (View) null, $i0) : FocusFinder.getInstance().findNextFocusFromRect(this, rect, $i0);
        if ($r3 != null && !isOffScreen($r3)) {
            return $r3.requestFocus($i0, rect);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof c)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        c $r2 = (c) parcelable;
        super.onRestoreInstanceState($r2.getSuperState());
        this.mSavedState = $r2;
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        c $r1 = new c(super.onSaveInstanceState());
        $r1.scrollPosition = getScrollY();
        return $r1;
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        b $r1 = this.mOnScrollChangeListener;
        if ($r1 != null) {
            $r1.onScrollChange(this, i, i2, i3, i4);
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        View $r1 = findFocus();
        if ($r1 != null && this != $r1 && isWithinDeltaOfScreen($r1, 0, i4)) {
            $r1.getDrawingRect(this.mTempRect);
            offsetDescendantRectToMyCoords($r1, this.mTempRect);
            doScrollY(computeScrollDeltaToGetChildRectOnScreen(this.mTempRect));
        }
    }

    public boolean onStartNestedScroll(View view, View view2, int i) {
        return onStartNestedScroll(view, view2, i, 0);
    }

    public boolean onStartNestedScroll(View view, View view2, int i, int i2) {
        return (i & 2) != 0;
    }

    public void onStopNestedScroll(View view) {
        onStopNestedScroll(view, 0);
    }

    public void onStopNestedScroll(View view, int i) {
        this.mParentHelper.onStopNestedScroll(view, i);
        stopNestedScroll(i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00ae, code lost:
        if (r38.mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange()) != false) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0345, code lost:
        if (r0.isFinished() == false) goto L_0x0347;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x03ad, code lost:
        if (r38.mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange()) != false) goto L_0x00b0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0333  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r39) {
        /*
            r38 = this;
            r0 = r38
            r0.initVelocityTrackerIfNotExists()
            r0 = r39
            android.view.MotionEvent r10 = android.view.MotionEvent.obtain(r0)
            r0 = r39
            int r11 = r0.getActionMasked()
            if (r11 != 0) goto L_0x0018
            r12 = 0
            r0 = r38
            r0.mNestedYOffset = r12
        L_0x0018:
            r0 = r38
            int r13 = r0.mNestedYOffset
            float r14 = (float) r13
            r15 = 0
            r10.offsetLocation(r15, r14)
            if (r11 == 0) goto L_0x03b2
            r12 = 1
            if (r11 == r12) goto L_0x034f
            r12 = 2
            if (r11 == r12) goto L_0x00c2
            r12 = 3
            if (r11 == r12) goto L_0x0072
            r12 = 5
            if (r11 == r12) goto L_0x0054
            r12 = 6
            if (r11 == r12) goto L_0x0035
            goto L_0x0415
        L_0x0035:
            r0 = r38
            r1 = r39
            r0.onSecondaryPointerUp(r1)
            r0 = r38
            int r11 = r0.mActivePointerId
            r0 = r39
            int r11 = r0.findPointerIndex(r11)
            r0 = r39
            float r14 = r0.getY(r11)
            int r11 = (int) r14
            r0 = r38
            r0.mLastMotionY = r11
            goto L_0x0415
        L_0x0054:
            r0 = r39
            int r11 = r0.getActionIndex()
            r0 = r39
            float r14 = r0.getY(r11)
            int r13 = (int) r14
            r0 = r38
            r0.mLastMotionY = r13
            r0 = r39
            int r11 = r0.getPointerId(r11)
            r0 = r38
            r0.mActivePointerId = r11
            goto L_0x0415
        L_0x0072:
            r0 = r38
            boolean r0 = r0.mIsBeingDragged
            r16 = r0
            if (r16 == 0) goto L_0x00b5
            r0 = r38
            int r11 = r0.getChildCount()
            if (r11 <= 0) goto L_0x00b5
            r0 = r38
            android.widget.OverScroller r0 = r0.mScroller
            r17 = r0
            r0 = r38
            int r11 = r0.getScrollX()
            r0 = r38
            int r13 = r0.getScrollY()
            r0 = r38
            int r18 = r0.getScrollRange()
            r12 = 0
            r19 = 0
            r20 = 0
            r0 = r17
            r1 = r11
            r2 = r13
            r3 = r12
            r4 = r19
            r5 = r20
            r6 = r18
            boolean r16 = r0.springBack(r1, r2, r3, r4, r5, r6)
            if (r16 == 0) goto L_0x00b5
        L_0x00b0:
            r0 = r38
            com.org.android.view.ViewCompat.postInvalidateOnAnimation(r0)
        L_0x00b5:
            r12 = -1
            r0 = r38
            r0.mActivePointerId = r12
            r0 = r38
            r0.endDrag()
            goto L_0x0415
        L_0x00c2:
            r0 = r38
            int r11 = r0.mActivePointerId
            r0 = r39
            int r11 = r0.findPointerIndex(r11)
            r12 = -1
            if (r11 != r12) goto L_0x0103
            java.lang.StringBuilder r21 = new java.lang.StringBuilder
            r0 = r21
            r0.<init>()
            java.lang.String r22 = "Invalid pointerId="
            r0 = r21
            r1 = r22
            r0.append(r1)
            r0 = r38
            int r11 = r0.mActivePointerId
            r0 = r21
            r0.append(r11)
            java.lang.String r22 = " in onTouchEvent"
            r0 = r21
            r1 = r22
            r0.append(r1)
            r0 = r21
            java.lang.String r23 = r0.toString()
            java.lang.String r22 = "NestedScrollView"
            r0 = r22
            r1 = r23
            android.util.Log.e(r0, r1)
            goto L_0x0415
        L_0x0103:
            r0 = r39
            float r14 = r0.getY(r11)
            int r13 = (int) r14
            r0 = r38
            int r0 = r0.mLastMotionY
            r18 = r0
            int r0 = r0 - r13
            r18 = r0
            r0 = r38
            int[] r0 = r0.mScrollConsumed
            r24 = r0
            r0 = r38
            int[] r0 = r0.mScrollOffset
            r25 = r0
            r12 = 0
            r19 = 0
            r0 = r38
            r1 = r12
            r2 = r18
            r3 = r24
            r4 = r25
            r5 = r19
            boolean r16 = r0.dispatchNestedPreScroll(r1, r2, r3, r4, r5)
            if (r16 == 0) goto L_0x016d
            r0 = r38
            int[] r0 = r0.mScrollConsumed
            r24 = r0
            r12 = 1
            r26 = r24[r12]
            r0 = r18
            r1 = r26
            int r0 = r0 - r1
            r18 = r0
            r0 = r38
            int[] r0 = r0.mScrollOffset
            r24 = r0
            r12 = 1
            r26 = r24[r12]
            r0 = r26
            float r14 = (float) r0
            r15 = 0
            r10.offsetLocation(r15, r14)
            r0 = r38
            int r0 = r0.mNestedYOffset
            r26 = r0
            r0 = r38
            int[] r0 = r0.mScrollOffset
            r24 = r0
            r12 = 1
            r27 = r24[r12]
            r0 = r26
            r1 = r27
            int r0 = r0 + r1
            r26 = r0
            r1 = r38
            r1.mNestedYOffset = r0
        L_0x016d:
            r0 = r38
            boolean r0 = r0.mIsBeingDragged
            r16 = r0
            if (r16 != 0) goto L_0x01b7
            r0 = r18
            int r26 = java.lang.Math.abs(r0)
            r0 = r38
            int r0 = r0.mTouchSlop
            r27 = r0
            r0 = r26
            r1 = r27
            if (r0 <= r1) goto L_0x01b7
            r0 = r38
            android.view.ViewParent r28 = r0.getParent()
            if (r28 == 0) goto L_0x0195
            r12 = 1
            r0 = r28
            r0.requestDisallowInterceptTouchEvent(r12)
        L_0x0195:
            r12 = 1
            r0 = r38
            r0.mIsBeingDragged = r12
            if (r18 <= 0) goto L_0x01aa
            r0 = r38
            int r0 = r0.mTouchSlop
            r26 = r0
            r0 = r18
            r1 = r26
            int r0 = r0 - r1
            r18 = r0
            goto L_0x01b7
        L_0x01aa:
            r0 = r38
            int r0 = r0.mTouchSlop
            r26 = r0
            r0 = r18
            r1 = r26
            int r0 = r0 + r1
            r18 = r0
        L_0x01b7:
            r0 = r38
            boolean r0 = r0.mIsBeingDragged
            r16 = r0
            if (r16 == 0) goto L_0x0415
            r0 = r38
            int[] r0 = r0.mScrollOffset
            r24 = r0
            r12 = 1
            r26 = r24[r12]
            r0 = r26
            int r13 = r13 - r0
            r0 = r38
            r0.mLastMotionY = r13
            r0 = r38
            int r26 = r0.getScrollY()
            r0 = r38
            int r13 = r0.getScrollRange()
            r0 = r38
            int r27 = r0.getOverScrollMode()
            if (r27 == 0) goto L_0x01ee
            r12 = 1
            r0 = r27
            if (r0 != r12) goto L_0x01eb
            if (r13 <= 0) goto L_0x01eb
            goto L_0x01ee
        L_0x01eb:
            r16 = 0
            goto L_0x01f0
        L_0x01ee:
            r16 = 1
        L_0x01f0:
            r0 = r38
            int r27 = r0.getScrollY()
            r12 = 0
            r19 = 0
            r20 = 0
            r30 = 0
            r31 = 0
            r32 = 1
            r0 = r38
            r1 = r12
            r2 = r18
            r3 = r19
            r4 = r27
            r5 = r20
            r6 = r13
            r7 = r30
            r8 = r31
            r9 = r32
            boolean r29 = r0.overScrollByCompat(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            if (r29 == 0) goto L_0x022b
            r12 = 0
            r0 = r38
            boolean r29 = r0.hasNestedScrollingParent(r12)
            if (r29 != 0) goto L_0x022b
            r0 = r38
            android.view.VelocityTracker r0 = r0.mVelocityTracker
            r33 = r0
            r0.clear()
        L_0x022b:
            r0 = r38
            int r27 = r0.getScrollY()
            r0 = r27
            r1 = r26
            int r0 = r0 - r1
            r27 = r0
            int r34 = r18 - r27
            r0 = r38
            int[] r0 = r0.mScrollOffset
            r24 = r0
            r12 = 0
            r19 = 0
            r20 = 0
            r0 = r38
            r1 = r12
            r2 = r27
            r3 = r19
            r4 = r34
            r5 = r24
            r6 = r20
            boolean r29 = r0.dispatchNestedScroll(r1, r2, r3, r4, r5, r6)
            if (r29 == 0) goto L_0x0287
            r0 = r38
            int r11 = r0.mLastMotionY
            r0 = r38
            int[] r0 = r0.mScrollOffset
            r24 = r0
            r12 = 1
            r13 = r24[r12]
            int r11 = r11 - r13
            r0 = r38
            r0.mLastMotionY = r11
            r12 = 1
            r11 = r24[r12]
            float r14 = (float) r11
            r15 = 0
            r10.offsetLocation(r15, r14)
            r0 = r38
            int r11 = r0.mNestedYOffset
            r0 = r38
            int[] r0 = r0.mScrollOffset
            r24 = r0
            r12 = 1
            r13 = r24[r12]
            int r11 = r11 + r13
            r0 = r38
            r0.mNestedYOffset = r11
            goto L_0x0415
        L_0x0287:
            if (r16 == 0) goto L_0x0415
            r0 = r38
            r0.ensureGlows()
            r0 = r26
            r1 = r18
            int r0 = r0 + r1
            r26 = r0
            if (r26 >= 0) goto L_0x02df
            r0 = r38
            android.widget.EdgeEffect r0 = r0.mEdgeGlowTop
            r35 = r0
            r0 = r18
            float r14 = (float) r0
            r0 = r38
            int r13 = r0.getHeight()
            float r0 = (float) r13
            r36 = r0
            float r14 = r14 / r0
            r0 = r39
            float r36 = r0.getX(r11)
            r0 = r38
            int r11 = r0.getWidth()
            float r0 = (float) r11
            r37 = r0
            r0 = r36
            r1 = r37
            float r0 = r0 / r1
            r36 = r0
            r0 = r35
            r1 = r36
            android.support.v4.widget.RangeSeekBar$OnRangeSeekBarChangeListener.draw(r0, r14, r1)
            r0 = r38
            android.widget.EdgeEffect r0 = r0.mEdgeGlowBottom
            r35 = r0
            boolean r16 = r0.isFinished()
            if (r16 != 0) goto L_0x032b
            r0 = r38
            android.widget.EdgeEffect r0 = r0.mEdgeGlowBottom
            r35 = r0
        L_0x02d9:
            r0 = r35
            r0.onRelease()
            goto L_0x032b
        L_0x02df:
            r0 = r26
            if (r0 <= r13) goto L_0x032b
            r0 = r38
            android.widget.EdgeEffect r0 = r0.mEdgeGlowBottom
            r35 = r0
            r0 = r18
            float r14 = (float) r0
            r0 = r38
            int r13 = r0.getHeight()
            float r0 = (float) r13
            r36 = r0
            float r14 = r14 / r0
            r0 = r39
            float r36 = r0.getX(r11)
            r0 = r38
            int r11 = r0.getWidth()
            float r0 = (float) r11
            r37 = r0
            r0 = r36
            r1 = r37
            float r0 = r0 / r1
            r36 = r0
            r15 = 1065353216(0x3f800000, float:1.0)
            float r36 = r15 - r36
            r0 = r35
            r1 = r36
            android.support.v4.widget.RangeSeekBar$OnRangeSeekBarChangeListener.draw(r0, r14, r1)
            r0 = r38
            android.widget.EdgeEffect r0 = r0.mEdgeGlowTop
            r35 = r0
            boolean r16 = r0.isFinished()
            if (r16 != 0) goto L_0x032b
            r0 = r38
            android.widget.EdgeEffect r0 = r0.mEdgeGlowTop
            r35 = r0
            goto L_0x02d9
        L_0x032b:
            r0 = r38
            android.widget.EdgeEffect r0 = r0.mEdgeGlowTop
            r35 = r0
            if (r35 == 0) goto L_0x0415
            r0 = r35
            boolean r16 = r0.isFinished()
            if (r16 == 0) goto L_0x0347
            r0 = r38
            android.widget.EdgeEffect r0 = r0.mEdgeGlowBottom
            r35 = r0
            boolean r16 = r0.isFinished()
            if (r16 != 0) goto L_0x0415
        L_0x0347:
            r0 = r38
            com.org.android.view.ViewCompat.postInvalidateOnAnimation(r0)
            goto L_0x0415
        L_0x034f:
            r0 = r38
            android.view.VelocityTracker r0 = r0.mVelocityTracker
            r33 = r0
            r0 = r38
            int r11 = r0.mMaximumVelocity
            float r14 = (float) r11
            r12 = 1000(0x3e8, float:1.401E-42)
            r0 = r33
            r0.computeCurrentVelocity(r12, r14)
            r0 = r38
            int r11 = r0.mActivePointerId
            r0 = r33
            float r14 = r0.getYVelocity(r11)
            int r11 = (int) r14
            int r13 = java.lang.Math.abs(r11)
            r0 = r38
            int r0 = r0.mMinimumVelocity
            r18 = r0
            if (r13 <= r0) goto L_0x0381
            int r11 = -r11
            r0 = r38
            r0.flingWithNestedDispatch(r11)
            goto L_0x00b5
        L_0x0381:
            r0 = r38
            android.widget.OverScroller r0 = r0.mScroller
            r17 = r0
            r0 = r38
            int r11 = r0.getScrollX()
            r0 = r38
            int r13 = r0.getScrollY()
            r0 = r38
            int r18 = r0.getScrollRange()
            r12 = 0
            r19 = 0
            r20 = 0
            r0 = r17
            r1 = r11
            r2 = r13
            r3 = r12
            r4 = r19
            r5 = r20
            r6 = r18
            boolean r16 = r0.springBack(r1, r2, r3, r4, r5, r6)
            if (r16 == 0) goto L_0x00b5
            goto L_0x00b0
        L_0x03b2:
            r0 = r38
            int r11 = r0.getChildCount()
            if (r11 != 0) goto L_0x03bc
            r12 = 0
            return r12
        L_0x03bc:
            r0 = r38
            android.widget.OverScroller r0 = r0.mScroller
            r17 = r0
            boolean r16 = r0.isFinished()
            r12 = 1
            r0 = r16
            r0 = r0 ^ r12
            r16 = r0
            r1 = r38
            r1.mIsBeingDragged = r0
            if (r16 == 0) goto L_0x03e0
            r0 = r38
            android.view.ViewParent r28 = r0.getParent()
            if (r28 == 0) goto L_0x03e0
            r12 = 1
            r0 = r28
            r0.requestDisallowInterceptTouchEvent(r12)
        L_0x03e0:
            r0 = r38
            android.widget.OverScroller r0 = r0.mScroller
            r17 = r0
            boolean r16 = r0.isFinished()
            if (r16 != 0) goto L_0x03f5
            r0 = r38
            android.widget.OverScroller r0 = r0.mScroller
            r17 = r0
            r0.abortAnimation()
        L_0x03f5:
            r0 = r39
            float r14 = r0.getY()
            int r11 = (int) r14
            r0 = r38
            r0.mLastMotionY = r11
            r12 = 0
            r0 = r39
            int r11 = r0.getPointerId(r12)
            r0 = r38
            r0.mActivePointerId = r11
            r12 = 2
            r19 = 0
            r0 = r38
            r1 = r19
            r0.startNestedScroll(r12, r1)
        L_0x0415:
            r0 = r38
            android.view.VelocityTracker r0 = r0.mVelocityTracker
            r33 = r0
            if (r33 == 0) goto L_0x0422
            r0 = r33
            r0.addMovement(r10)
        L_0x0422:
            r10.recycle()
            r12 = 1
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.NestedScrollView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00c1 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:50:? A[ADDED_TO_REGION, ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean overScrollByCompat(int r17, int r18, int r19, int r20, int r21, int r22, int r23, int r24, boolean r25) {
        /*
            r16 = this;
            r0 = r16
            int r7 = r0.getOverScrollMode()
            r0 = r16
            int r8 = r0.computeHorizontalScrollRange()
            r0 = r16
            int r9 = r0.computeHorizontalScrollExtent()
            if (r8 <= r9) goto L_0x0016
            r10 = 1
            goto L_0x0017
        L_0x0016:
            r10 = 0
        L_0x0017:
            r0 = r16
            int r8 = r0.computeVerticalScrollRange()
            r0 = r16
            int r9 = r0.computeVerticalScrollExtent()
            if (r8 <= r9) goto L_0x0028
            r25 = 1
            goto L_0x002a
        L_0x0028:
            r25 = 0
        L_0x002a:
            if (r7 == 0) goto L_0x0034
            r11 = 1
            if (r7 != r11) goto L_0x0032
            if (r10 == 0) goto L_0x0032
            goto L_0x0034
        L_0x0032:
            r10 = 0
            goto L_0x0035
        L_0x0034:
            r10 = 1
        L_0x0035:
            if (r7 == 0) goto L_0x0040
            r11 = 1
            if (r7 != r11) goto L_0x003d
            if (r25 == 0) goto L_0x003d
            goto L_0x0040
        L_0x003d:
            r25 = 0
            goto L_0x0042
        L_0x0040:
            r25 = 1
        L_0x0042:
            int r17 = r19 + r17
            if (r10 != 0) goto L_0x0049
            r23 = 0
            goto L_0x0049
        L_0x0049:
            int r18 = r20 + r18
            if (r25 != 0) goto L_0x0050
            r20 = 0
            goto L_0x0052
        L_0x0050:
            r20 = r24
        L_0x0052:
            r0 = r23
            int r0 = -r0
            r19 = r0
            int r21 = r23 + r21
            r0 = r20
            int r0 = -r0
            r23 = r0
            r0 = r20
            r1 = r22
            int r0 = r0 + r1
            r20 = r0
            r0 = r17
            r1 = r21
            if (r0 <= r1) goto L_0x0070
            r19 = r21
        L_0x006d:
            r25 = 1
            goto L_0x007b
        L_0x0070:
            r0 = r17
            r1 = r19
            if (r0 >= r1) goto L_0x0077
            goto L_0x006d
        L_0x0077:
            r19 = r17
            r25 = 0
        L_0x007b:
            r0 = r18
            r1 = r20
            if (r0 <= r1) goto L_0x0085
            r18 = r20
        L_0x0083:
            r10 = 1
            goto L_0x008f
        L_0x0085:
            r0 = r18
            r1 = r23
            if (r0 >= r1) goto L_0x008e
            r18 = r23
            goto L_0x0083
        L_0x008e:
            r10 = 0
        L_0x008f:
            if (r10 == 0) goto L_0x00b4
            r11 = 1
            r0 = r16
            boolean r12 = r0.hasNestedScrollingParent(r11)
            if (r12 != 0) goto L_0x00b4
            r0 = r16
            android.widget.OverScroller r13 = r0.mScroller
            r0 = r16
            int r17 = r0.getScrollRange()
            r11 = 0
            r14 = 0
            r15 = 0
            r0 = r13
            r1 = r19
            r2 = r18
            r3 = r11
            r4 = r14
            r5 = r15
            r6 = r17
            r0.springBack(r1, r2, r3, r4, r5, r6)
        L_0x00b4:
            r0 = r16
            r1 = r19
            r2 = r18
            r3 = r25
            r0.onOverScrolled(r1, r2, r3, r10)
            if (r25 != 0) goto L_0x00c3
            if (r10 == 0) goto L_0x00c5
        L_0x00c3:
            r11 = 1
            return r11
        L_0x00c5:
            r11 = 0
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.NestedScrollView.overScrollByCompat(int, int, int, int, int, int, int, int, boolean):boolean");
    }

    public boolean pageScroll(int i) {
        boolean $z0 = i == 130;
        int $i1 = getHeight();
        if ($z0) {
            this.mTempRect.top = getScrollY() + $i1;
            int $i2 = getChildCount();
            if ($i2 > 0) {
                View $r2 = getChildAt($i2 - 1);
                int $i22 = $r2.getBottom() + ((FrameLayout.LayoutParams) $r2.getLayoutParams()).bottomMargin + getPaddingBottom();
                Rect $r1 = this.mTempRect;
                if ($r1.top + $i1 > $i22) {
                    $r1.top = $i22 - $i1;
                }
            }
        } else {
            this.mTempRect.top = getScrollY() - $i1;
            Rect $r12 = this.mTempRect;
            if ($r12.top < 0) {
                $r12.top = 0;
            }
        }
        Rect $r13 = this.mTempRect;
        int $i23 = $r13.top;
        $r13.bottom = $i1 + $i23;
        return scrollAndFocus(i, $i23, $r13.bottom);
    }

    public void requestChildFocus(View view, View view2) {
        if (!this.mIsLayoutDirty) {
            scrollToChild(view2);
        } else {
            this.mChildToScrollTo = view2;
        }
        super.requestChildFocus(view, view2);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        return scrollToChildRect(rect, z);
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (z) {
            recycleVelocityTracker();
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public void requestLayout() {
        this.mIsLayoutDirty = true;
        super.requestLayout();
    }

    public void scroll(View view, int i, int i2, int i3, int i4, int i5) {
        int $i4 = getScrollY();
        scrollBy(0, i4);
        int $i42 = getScrollY() - $i4;
        dispatchNestedScroll(0, $i42, 0, i4 - $i42, (int[]) null, i5);
    }

    public void scrollTo(int i, int i2) {
        if (getChildCount() > 0) {
            View $r1 = getChildAt(0);
            FrameLayout.LayoutParams $r3 = (FrameLayout.LayoutParams) $r1.getLayoutParams();
            int $i0 = clamp(i, (getWidth() - getPaddingLeft()) - getPaddingRight(), $r1.getWidth() + $r3.leftMargin + $r3.rightMargin);
            int $i1 = clamp(i2, (getHeight() - getPaddingTop()) - getPaddingBottom(), $r1.getHeight() + $r3.topMargin + $r3.bottomMargin);
            if ($i0 != getScrollX() || $i1 != getScrollY()) {
                super.scrollTo($i0, $i1);
            }
        }
    }

    public void setFillViewport(boolean z) {
        if (z != this.mFillViewport) {
            this.mFillViewport = z;
            requestLayout();
        }
    }

    public void setNestedScrollingEnabled(boolean z) {
        this.mChildHelper.setNestedScrollingEnabled(z);
    }

    public void setOnScrollChangeListener(b bVar) {
        this.mOnScrollChangeListener = bVar;
    }

    public void setSmoothScrollingEnabled(boolean z) {
        this.mSmoothScrollingEnabled = z;
    }

    public boolean shouldDelayChildPressedState() {
        return true;
    }

    public final void smoothScrollBy(int $i1, int $i2) {
        if (getChildCount() != 0) {
            if (AnimationUtils.currentAnimationTimeMillis() - this.mLastScroll > 250) {
                View $r1 = getChildAt(0);
                FrameLayout.LayoutParams $r3 = (FrameLayout.LayoutParams) $r1.getLayoutParams();
                int $i3 = $r1.getHeight() + $r3.topMargin + $r3.bottomMargin;
                int $i6 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int $i12 = getScrollY();
                int $i22 = $i2 + $i12;
                int i = $i22;
                int max = Math.max(0, Math.min($i22, Math.max(0, $i3 - $i6))) - $i12;
                this.mPreviousScrollerY = getScrollY();
                this.mScroller.startScroll(getScrollX(), $i12, 0, max);
                ViewCompat.postInvalidateOnAnimation(this);
            } else {
                OverScroller $r4 = this.mScroller;
                OverScroller overScroller = $r4;
                if (!$r4.isFinished()) {
                    OverScroller $r42 = this.mScroller;
                    OverScroller overScroller2 = $r42;
                    $r42.abortAnimation();
                }
                scrollBy($i1, $i2);
            }
            this.mLastScroll = AnimationUtils.currentAnimationTimeMillis();
        }
    }

    public final void smoothScrollTo(int i, int i2) {
        smoothScrollBy(i - getScrollX(), i2 - getScrollY());
    }

    public boolean startNestedScroll(int i) {
        return startNestedScroll(i, 0);
    }

    public boolean startNestedScroll(int i, int i2) {
        return this.mChildHelper.startNestedScroll(i, i2);
    }

    public void stopNestedScroll() {
        stopNestedScroll(0);
    }

    public void stopNestedScroll(int i) {
        this.mChildHelper.stopNestedScroll(i);
    }
}

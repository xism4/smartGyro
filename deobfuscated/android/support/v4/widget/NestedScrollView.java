package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.AbsSavedState;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityRecord;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.OverScroller;
import android.widget.ScrollView;
import com.org.android.view.AccessibilityDelegateCompat;
import com.org.android.view.NestedScrollingChild;
import com.org.android.view.NestedScrollingChildHelper;
import com.org.android.view.NestedScrollingParentHelper;
import com.org.android.view.Vector;
import com.org.android.view.ViewCompat;
import com.org.android.view.ViewParentCompat.ViewParentCompatImpl;
import com.org.android.view.accessibility.AccessibilityNodeInfoCompat;
import com.org.android.view.accessibility.AccessibilityRecordCompat;
import java.util.ArrayList;
import java.util.List;

public class NestedScrollView
  extends FrameLayout
  implements ViewParentCompat.ViewParentCompatImpl, Vector, NestedScrollingChild
{
  private static final a ACCESSIBILITY_DELEGATE = new a();
  private static final int[] SCROLLVIEW_STYLEABLE = { 16843130 };
  private int mActivePointerId = -1;
  private final NestedScrollingChildHelper mChildHelper;
  private View mChildToScrollTo = null;
  private EdgeEffect mEdgeGlowBottom;
  private EdgeEffect mEdgeGlowTop;
  private boolean mFillViewport;
  private boolean mIsBeingDragged = false;
  private boolean mIsLaidOut = false;
  private boolean mIsLayoutDirty = true;
  private int mLastMotionY;
  private long mLastScroll;
  private int mMaximumVelocity;
  private int mMinimumVelocity;
  private int mNestedYOffset;
  private b mOnScrollChangeListener;
  private final NestedScrollingParentHelper mParentHelper;
  private int mPreviousScrollerY;
  private c mSavedState;
  private final int[] mScrollConsumed = new int[2];
  private final int[] mScrollOffset = new int[2];
  private OverScroller mScroller;
  private boolean mSmoothScrollingEnabled = true;
  private final Rect mTempRect = new Rect();
  private int mTouchSlop;
  private VelocityTracker mVelocityTracker;
  private float mVerticalScrollFactor;
  
  public NestedScrollView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public NestedScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public NestedScrollView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initScrollView();
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, SCROLLVIEW_STYLEABLE, paramInt, 0);
    setFillViewport(paramContext.getBoolean(0, false));
    paramContext.recycle();
    mParentHelper = new NestedScrollingParentHelper(this);
    mChildHelper = new NestedScrollingChildHelper(this);
    setNestedScrollingEnabled(true);
    ViewCompat.setAccessibilityDelegate(this, ACCESSIBILITY_DELEGATE);
  }
  
  private static int clamp(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt2 < paramInt3) && (paramInt1 >= 0))
    {
      int i = paramInt1;
      if (paramInt2 + paramInt1 > paramInt3) {
        i = paramInt3 - paramInt2;
      }
      return i;
    }
    return 0;
  }
  
  private void doScrollY(int paramInt)
  {
    if (paramInt != 0)
    {
      if (mSmoothScrollingEnabled)
      {
        smoothScrollBy(0, paramInt);
        return;
      }
      scrollBy(0, paramInt);
    }
  }
  
  private void endDrag()
  {
    mIsBeingDragged = false;
    recycleVelocityTracker();
    stopNestedScroll(0);
    EdgeEffect localEdgeEffect = mEdgeGlowTop;
    if (localEdgeEffect != null)
    {
      localEdgeEffect.onRelease();
      mEdgeGlowBottom.onRelease();
    }
  }
  
  private void ensureGlows()
  {
    if (getOverScrollMode() != 2)
    {
      if (mEdgeGlowTop == null)
      {
        Context localContext = getContext();
        mEdgeGlowTop = new EdgeEffect(localContext);
        mEdgeGlowBottom = new EdgeEffect(localContext);
      }
    }
    else
    {
      mEdgeGlowTop = null;
      mEdgeGlowBottom = null;
    }
  }
  
  private View findFocusableViewInBounds(boolean paramBoolean, int paramInt1, int paramInt2)
  {
    ArrayList localArrayList = getFocusables(2);
    int i1 = localArrayList.size();
    Object localObject2 = null;
    int k = 0;
    int j;
    for (int m = 0; k < i1; m = j)
    {
      View localView = (View)localArrayList.get(k);
      int n = localView.getTop();
      int i2 = localView.getBottom();
      Object localObject1 = localObject2;
      j = m;
      if (paramInt1 < i2)
      {
        localObject1 = localObject2;
        j = m;
        if (n < paramInt2)
        {
          int i;
          if ((paramInt1 < n) && (i2 < paramInt2)) {
            i = 1;
          } else {
            i = 0;
          }
          if (localObject2 == null)
          {
            localObject1 = localView;
            j = i;
          }
          else
          {
            if (((paramBoolean) && (n < localObject2.getTop())) || ((!paramBoolean) && (i2 > localObject2.getBottom()))) {
              n = 1;
            } else {
              n = 0;
            }
            if (m != 0)
            {
              localObject1 = localObject2;
              j = m;
              if (i == 0) {
                break label232;
              }
              localObject1 = localObject2;
              j = m;
              if (n == 0) {
                break label232;
              }
            }
            else
            {
              if (i != 0)
              {
                localObject1 = localView;
                j = 1;
                break label232;
              }
              localObject1 = localObject2;
              j = m;
              if (n == 0) {
                break label232;
              }
            }
            localObject1 = localView;
            j = m;
          }
        }
      }
      label232:
      k += 1;
      localObject2 = localObject1;
    }
    return localObject2;
  }
  
  private void flingWithNestedDispatch(int paramInt)
  {
    int i = getScrollY();
    boolean bool;
    if (((i <= 0) && (paramInt <= 0)) || ((i >= getScrollRange()) && (paramInt >= 0))) {
      bool = false;
    } else {
      bool = true;
    }
    float f = paramInt;
    if (!dispatchNestedPreFling(0.0F, f))
    {
      dispatchNestedFling(0.0F, f, bool);
      fling(paramInt);
    }
  }
  
  private float getVerticalScrollFactorCompat()
  {
    if (mVerticalScrollFactor == 0.0F)
    {
      TypedValue localTypedValue = new TypedValue();
      Context localContext = getContext();
      if (localContext.getTheme().resolveAttribute(16842829, localTypedValue, true)) {
        mVerticalScrollFactor = localTypedValue.getDimension(localContext.getResources().getDisplayMetrics());
      } else {
        throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
      }
    }
    return mVerticalScrollFactor;
  }
  
  private boolean inChild(int paramInt1, int paramInt2)
  {
    if (getChildCount() > 0)
    {
      int i = getScrollY();
      View localView = getChildAt(0);
      if ((paramInt2 >= localView.getTop() - i) && (paramInt2 < localView.getBottom() - i) && (paramInt1 >= localView.getLeft()) && (paramInt1 < localView.getRight())) {
        return true;
      }
    }
    return false;
  }
  
  private void initOrResetVelocityTracker()
  {
    VelocityTracker localVelocityTracker = mVelocityTracker;
    if (localVelocityTracker == null)
    {
      mVelocityTracker = VelocityTracker.obtain();
      return;
    }
    localVelocityTracker.clear();
  }
  
  private void initScrollView()
  {
    mScroller = new OverScroller(getContext());
    setFocusable(true);
    setDescendantFocusability(262144);
    setWillNotDraw(false);
    ViewConfiguration localViewConfiguration = ViewConfiguration.get(getContext());
    mTouchSlop = localViewConfiguration.getScaledTouchSlop();
    mMinimumVelocity = localViewConfiguration.getScaledMinimumFlingVelocity();
    mMaximumVelocity = localViewConfiguration.getScaledMaximumFlingVelocity();
  }
  
  private void initVelocityTrackerIfNotExists()
  {
    if (mVelocityTracker == null) {
      mVelocityTracker = VelocityTracker.obtain();
    }
  }
  
  private boolean isOffScreen(View paramView)
  {
    return isWithinDeltaOfScreen(paramView, 0, getHeight()) ^ true;
  }
  
  private static boolean isViewDescendantOf(View paramView1, View paramView2)
  {
    if (paramView1 == paramView2) {
      return true;
    }
    paramView1 = paramView1.getParent();
    return ((paramView1 instanceof ViewGroup)) && (isViewDescendantOf((View)paramView1, paramView2));
  }
  
  private boolean isWithinDeltaOfScreen(View paramView, int paramInt1, int paramInt2)
  {
    paramView.getDrawingRect(mTempRect);
    offsetDescendantRectToMyCoords(paramView, mTempRect);
    return (mTempRect.bottom + paramInt1 >= getScrollY()) && (mTempRect.top - paramInt1 <= getScrollY() + paramInt2);
  }
  
  private void onSecondaryPointerUp(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getActionIndex();
    if (paramMotionEvent.getPointerId(i) == mActivePointerId)
    {
      if (i == 0) {
        i = 1;
      } else {
        i = 0;
      }
      mLastMotionY = ((int)paramMotionEvent.getY(i));
      mActivePointerId = paramMotionEvent.getPointerId(i);
      paramMotionEvent = mVelocityTracker;
      if (paramMotionEvent != null) {
        paramMotionEvent.clear();
      }
    }
  }
  
  private void recycleVelocityTracker()
  {
    VelocityTracker localVelocityTracker = mVelocityTracker;
    if (localVelocityTracker != null)
    {
      localVelocityTracker.recycle();
      mVelocityTracker = null;
    }
  }
  
  private boolean scrollAndFocus(int paramInt1, int paramInt2, int paramInt3)
  {
    int j = getHeight();
    int i = getScrollY();
    j += i;
    boolean bool2 = false;
    boolean bool1;
    if (paramInt1 == 33) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    View localView = findFocusableViewInBounds(bool1, paramInt2, paramInt3);
    Object localObject = localView;
    if (localView == null) {
      localObject = this;
    }
    if ((paramInt2 >= i) && (paramInt3 <= j))
    {
      bool1 = bool2;
    }
    else
    {
      if (bool1) {
        paramInt2 -= i;
      } else {
        paramInt2 = paramInt3 - j;
      }
      doScrollY(paramInt2);
      bool1 = true;
    }
    if (localObject != findFocus()) {
      ((View)localObject).requestFocus(paramInt1);
    }
    return bool1;
  }
  
  private void scrollToChild(View paramView)
  {
    paramView.getDrawingRect(mTempRect);
    offsetDescendantRectToMyCoords(paramView, mTempRect);
    int i = computeScrollDeltaToGetChildRectOnScreen(mTempRect);
    if (i != 0) {
      scrollBy(0, i);
    }
  }
  
  private boolean scrollToChildRect(Rect paramRect, boolean paramBoolean)
  {
    int i = computeScrollDeltaToGetChildRectOnScreen(paramRect);
    boolean bool;
    if (i != 0) {
      bool = true;
    } else {
      bool = false;
    }
    if (bool)
    {
      if (paramBoolean)
      {
        scrollBy(0, i);
        return bool;
      }
      smoothScrollBy(0, i);
    }
    return bool;
  }
  
  private boolean show()
  {
    if (getChildCount() > 0)
    {
      View localView = getChildAt(0);
      FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView.getLayoutParams();
      if (localView.getHeight() + topMargin + bottomMargin > getHeight() - getPaddingTop() - getPaddingBottom()) {
        return true;
      }
    }
    return false;
  }
  
  public void addView(View paramView)
  {
    if (getChildCount() <= 0)
    {
      super.addView(paramView);
      return;
    }
    throw new IllegalStateException("ScrollView can host only one direct child");
  }
  
  public void addView(View paramView, int paramInt)
  {
    if (getChildCount() <= 0)
    {
      super.addView(paramView, paramInt);
      return;
    }
    throw new IllegalStateException("ScrollView can host only one direct child");
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    if (getChildCount() <= 0)
    {
      super.addView(paramView, paramInt, paramLayoutParams);
      return;
    }
    throw new IllegalStateException("ScrollView can host only one direct child");
  }
  
  public void addView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    if (getChildCount() <= 0)
    {
      super.addView(paramView, paramLayoutParams);
      return;
    }
    throw new IllegalStateException("ScrollView can host only one direct child");
  }
  
  public boolean arrowScroll(int paramInt)
  {
    View localView2 = findFocus();
    View localView1 = localView2;
    if (localView2 == this) {
      localView1 = null;
    }
    localView2 = FocusFinder.getInstance().findNextFocus(this, localView1, paramInt);
    int k = getMaxScrollAmount();
    int j = k;
    if ((localView2 != null) && (isWithinDeltaOfScreen(localView2, k, getHeight())))
    {
      localView2.getDrawingRect(mTempRect);
      offsetDescendantRectToMyCoords(localView2, mTempRect);
      doScrollY(computeScrollDeltaToGetChildRectOnScreen(mTempRect));
      localView2.requestFocus(paramInt);
    }
    else
    {
      int i;
      if ((paramInt == 33) && (getScrollY() < k))
      {
        i = getScrollY();
      }
      else
      {
        i = j;
        if (paramInt == 130)
        {
          i = j;
          if (getChildCount() > 0)
          {
            localView2 = getChildAt(0);
            FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView2.getLayoutParams();
            i = Math.min(localView2.getBottom() + bottomMargin - (getScrollY() + getHeight() - getPaddingBottom()), k);
          }
        }
      }
      if (i == 0) {
        return false;
      }
      if (paramInt != 130) {
        i = -i;
      }
      doScrollY(i);
    }
    if ((localView1 != null) && (localView1.isFocused()) && (isOffScreen(localView1)))
    {
      paramInt = getDescendantFocusability();
      setDescendantFocusability(131072);
      requestFocus();
      setDescendantFocusability(paramInt);
    }
    return true;
  }
  
  public int computeHorizontalScrollExtent()
  {
    return super.computeHorizontalScrollExtent();
  }
  
  public int computeHorizontalScrollOffset()
  {
    return super.computeHorizontalScrollOffset();
  }
  
  public int computeHorizontalScrollRange()
  {
    return super.computeHorizontalScrollRange();
  }
  
  public void computeScroll()
  {
    if (mScroller.computeScrollOffset())
    {
      mScroller.getCurrX();
      int k = mScroller.getCurrY();
      int j = k - mPreviousScrollerY;
      int i = j;
      if (dispatchNestedPreScroll(0, j, mScrollConsumed, null, 1)) {
        i = j - mScrollConsumed[1];
      }
      if (i != 0)
      {
        j = getScrollRange();
        int m = getScrollY();
        overScrollByCompat(0, i, getScrollX(), m, 0, j, 0, 0, false);
        int n = getScrollY() - m;
        if (!dispatchNestedScroll(0, n, 0, i - n, null, 1))
        {
          i = getOverScrollMode();
          if ((i != 0) && ((i != 1) || (j <= 0))) {
            i = 0;
          } else {
            i = 1;
          }
          if (i != 0)
          {
            ensureGlows();
            if ((k <= 0) && (m > 0)) {}
            for (EdgeEffect localEdgeEffect = mEdgeGlowTop;; localEdgeEffect = mEdgeGlowBottom)
            {
              localEdgeEffect.onAbsorb((int)mScroller.getCurrVelocity());
              break;
              if ((k < j) || (m >= j)) {
                break;
              }
            }
          }
        }
      }
      mPreviousScrollerY = k;
      ViewCompat.postInvalidateOnAnimation(this);
      return;
    }
    if (hasNestedScrollingParent(1)) {
      stopNestedScroll(1);
    }
    mPreviousScrollerY = 0;
  }
  
  protected int computeScrollDeltaToGetChildRectOnScreen(Rect paramRect)
  {
    if (getChildCount() == 0) {
      return 0;
    }
    int m = getHeight();
    int j = getScrollY();
    int i = j;
    int k = j + m;
    int n = getVerticalFadingEdgeLength();
    if (top > 0) {
      i = j + n;
    }
    View localView = getChildAt(0);
    FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView.getLayoutParams();
    if (bottom < localView.getHeight() + topMargin + bottomMargin) {
      j = k - n;
    } else {
      j = k;
    }
    if ((bottom > j) && (top > i))
    {
      if (paramRect.height() > m) {
        i = top - i;
      } else {
        i = bottom - j;
      }
      return Math.min(i + 0, localView.getBottom() + bottomMargin - k);
    }
    if ((top < i) && (bottom < j))
    {
      if (paramRect.height() > m) {
        i = 0 - (j - bottom);
      } else {
        i = 0 - (i - top);
      }
      return Math.max(i, -getScrollY());
    }
    return 0;
  }
  
  public int computeVerticalScrollExtent()
  {
    return super.computeVerticalScrollExtent();
  }
  
  public int computeVerticalScrollOffset()
  {
    return Math.max(0, super.computeVerticalScrollOffset());
  }
  
  public int computeVerticalScrollRange()
  {
    int j = getChildCount();
    int i = getHeight() - getPaddingBottom() - getPaddingTop();
    if (j == 0) {
      return i;
    }
    View localView = getChildAt(0);
    FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView.getLayoutParams();
    j = localView.getBottom() + bottomMargin;
    int k = getScrollY();
    int m = Math.max(0, j - i);
    if (k < 0) {
      return j - k;
    }
    i = j;
    if (k > m) {
      i = j + (k - m);
    }
    return i;
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    return (super.dispatchKeyEvent(paramKeyEvent)) || (executeKeyEvent(paramKeyEvent));
  }
  
  public boolean dispatchNestedFling(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    return mChildHelper.dispatchNestedFling(paramFloat1, paramFloat2, paramBoolean);
  }
  
  public boolean dispatchNestedPreFling(float paramFloat1, float paramFloat2)
  {
    return mChildHelper.dispatchNestedPreFling(paramFloat1, paramFloat2);
  }
  
  public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    return dispatchNestedPreScroll(paramInt1, paramInt2, paramArrayOfInt1, paramArrayOfInt2, 0);
  }
  
  public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt3)
  {
    return mChildHelper.dispatchNestedPreScroll(paramInt1, paramInt2, paramArrayOfInt1, paramArrayOfInt2, paramInt3);
  }
  
  public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt)
  {
    return dispatchNestedScroll(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfInt, 0);
  }
  
  public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt, int paramInt5)
  {
    return mChildHelper.dispatchNestedScroll(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfInt, paramInt5);
  }
  
  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    if (mEdgeGlowTop != null)
    {
      int i5 = getScrollY();
      boolean bool = mEdgeGlowTop.isFinished();
      int i2 = 0;
      int k;
      int m;
      int i4;
      int i;
      int i3;
      int j;
      int i1;
      int n;
      if (!bool)
      {
        int i6 = paramCanvas.save();
        k = getWidth();
        m = k;
        i4 = getHeight();
        i = i4;
        i3 = Math.min(0, i5);
        j = i3;
        if ((Build.VERSION.SDK_INT >= 21) && (!getClipToPadding()))
        {
          k = 0;
        }
        else
        {
          m = k - (getPaddingLeft() + getPaddingRight());
          k = getPaddingLeft() + 0;
        }
        i1 = i;
        n = j;
        if (Build.VERSION.SDK_INT >= 21)
        {
          i1 = i;
          n = j;
          if (getClipToPadding())
          {
            i1 = i4 - (getPaddingTop() + getPaddingBottom());
            n = i3 + getPaddingTop();
          }
        }
        paramCanvas.translate(k, n);
        mEdgeGlowTop.setSize(m, i1);
        if (mEdgeGlowTop.draw(paramCanvas)) {
          ViewCompat.postInvalidateOnAnimation(this);
        }
        paramCanvas.restoreToCount(i6);
      }
      if (!mEdgeGlowBottom.isFinished())
      {
        i4 = paramCanvas.save();
        m = getWidth();
        k = m;
        i1 = getHeight();
        i = i1;
        i3 = Math.max(getScrollRange(), i5) + i1;
        if (Build.VERSION.SDK_INT >= 21)
        {
          j = i2;
          if (!getClipToPadding()) {}
        }
        else
        {
          k = m - (getPaddingLeft() + getPaddingRight());
          j = 0 + getPaddingLeft();
        }
        n = i3;
        m = i;
        if (Build.VERSION.SDK_INT >= 21)
        {
          n = i3;
          m = i;
          if (getClipToPadding())
          {
            m = i1 - (getPaddingTop() + getPaddingBottom());
            n = i3 - getPaddingBottom();
          }
        }
        paramCanvas.translate(j - k, n);
        paramCanvas.rotate(180.0F, k, 0.0F);
        mEdgeGlowBottom.setSize(k, m);
        if (mEdgeGlowBottom.draw(paramCanvas)) {
          ViewCompat.postInvalidateOnAnimation(this);
        }
        paramCanvas.restoreToCount(i4);
      }
    }
  }
  
  public boolean executeKeyEvent(KeyEvent paramKeyEvent)
  {
    mTempRect.setEmpty();
    boolean bool = show();
    int i = 130;
    if (!bool)
    {
      if ((isFocused()) && (paramKeyEvent.getKeyCode() != 4))
      {
        View localView = findFocus();
        paramKeyEvent = localView;
        if (localView == this) {
          paramKeyEvent = null;
        }
        paramKeyEvent = FocusFinder.getInstance().findNextFocus(this, paramKeyEvent, 130);
        if ((paramKeyEvent != null) && (paramKeyEvent != this) && (paramKeyEvent.requestFocus(130))) {
          return true;
        }
      }
    }
    else if (paramKeyEvent.getAction() == 0)
    {
      int j = paramKeyEvent.getKeyCode();
      if (j != 19)
      {
        if (j != 20)
        {
          if (j != 62) {
            return false;
          }
          if (paramKeyEvent.isShiftPressed()) {
            i = 33;
          }
          pageScroll(i);
          return false;
        }
        if (!paramKeyEvent.isAltPressed()) {
          return arrowScroll(130);
        }
        return fullScroll(130);
      }
      if (!paramKeyEvent.isAltPressed()) {
        return arrowScroll(33);
      }
      return fullScroll(33);
    }
    return false;
  }
  
  public void fling(int paramInt)
  {
    if (getChildCount() > 0)
    {
      startNestedScroll(2, 1);
      mScroller.fling(getScrollX(), getScrollY(), 0, paramInt, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
      mPreviousScrollerY = getScrollY();
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }
  
  public boolean fullScroll(int paramInt)
  {
    int i;
    if (paramInt == 130) {
      i = 1;
    } else {
      i = 0;
    }
    int j = getHeight();
    Object localObject = mTempRect;
    top = 0;
    bottom = j;
    if (i != 0)
    {
      i = getChildCount();
      if (i > 0)
      {
        localObject = getChildAt(i - 1);
        FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)((View)localObject).getLayoutParams();
        mTempRect.bottom = (((View)localObject).getBottom() + bottomMargin + getPaddingBottom());
        localObject = mTempRect;
        top = (bottom - j);
      }
    }
    localObject = mTempRect;
    return scrollAndFocus(paramInt, top, bottom);
  }
  
  protected float getBottomFadingEdgeStrength()
  {
    if (getChildCount() == 0) {
      return 0.0F;
    }
    View localView = getChildAt(0);
    FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView.getLayoutParams();
    int i = getVerticalFadingEdgeLength();
    int j = getHeight();
    int k = getPaddingBottom();
    j = localView.getBottom() + bottomMargin - getScrollY() - (j - k);
    if (j < i) {
      return j / i;
    }
    return 1.0F;
  }
  
  public int getMaxScrollAmount()
  {
    return (int)(getHeight() * 0.5F);
  }
  
  public int getNestedScrollAxes()
  {
    return mParentHelper.getNestedScrollAxes();
  }
  
  int getScrollRange()
  {
    if (getChildCount() > 0)
    {
      View localView = getChildAt(0);
      FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView.getLayoutParams();
      return Math.max(0, localView.getHeight() + topMargin + bottomMargin - (getHeight() - getPaddingTop() - getPaddingBottom()));
    }
    return 0;
  }
  
  protected float getTopFadingEdgeStrength()
  {
    if (getChildCount() == 0) {
      return 0.0F;
    }
    int i = getVerticalFadingEdgeLength();
    int j = getScrollY();
    if (j < i) {
      return j / i;
    }
    return 1.0F;
  }
  
  public boolean hasNestedScrollingParent()
  {
    return hasNestedScrollingParent(0);
  }
  
  public boolean hasNestedScrollingParent(int paramInt)
  {
    return mChildHelper.hasNestedScrollingParent(paramInt);
  }
  
  public boolean isNestedScrollingEnabled()
  {
    return mChildHelper.isNestedScrollingEnabled();
  }
  
  protected void measureChild(View paramView, int paramInt1, int paramInt2)
  {
    ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
    paramView.measure(ViewGroup.getChildMeasureSpec(paramInt1, getPaddingLeft() + getPaddingRight(), width), View.MeasureSpec.makeMeasureSpec(0, 0));
  }
  
  protected void measureChildWithMargins(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
    paramView.measure(ViewGroup.getChildMeasureSpec(paramInt1, getPaddingLeft() + getPaddingRight() + leftMargin + rightMargin + paramInt2, width), View.MeasureSpec.makeMeasureSpec(topMargin + bottomMargin, 0));
  }
  
  public void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    mIsLaidOut = false;
  }
  
  public boolean onGenericMotionEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getSource() & 0x2) != 0)
    {
      if (paramMotionEvent.getAction() != 8) {
        return false;
      }
      if (!mIsBeingDragged)
      {
        float f = paramMotionEvent.getAxisValue(9);
        if (f != 0.0F)
        {
          int i = (int)(f * getVerticalScrollFactorCompat());
          int j = getScrollRange();
          int m = getScrollY();
          int k = m - i;
          if (k < 0)
          {
            i = 0;
          }
          else
          {
            i = k;
            if (k > j) {
              i = j;
            }
          }
          if (i != m)
          {
            super.scrollTo(getScrollX(), i);
            return true;
          }
        }
      }
    }
    return false;
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction();
    if ((i == 2) && (mIsBeingDragged)) {
      return true;
    }
    i &= 0xFF;
    if (i != 0)
    {
      if (i != 1) {
        if (i != 2)
        {
          if (i != 3)
          {
            if (i != 6) {
              break label343;
            }
            onSecondaryPointerUp(paramMotionEvent);
            break label343;
          }
        }
        else
        {
          i = mActivePointerId;
          if (i == -1) {
            break label343;
          }
          int j = paramMotionEvent.findPointerIndex(i);
          if (j == -1)
          {
            paramMotionEvent = new StringBuilder();
            paramMotionEvent.append("Invalid pointerId=");
            paramMotionEvent.append(i);
            paramMotionEvent.append(" in onInterceptTouchEvent");
            Log.e("NestedScrollView", paramMotionEvent.toString());
            break label343;
          }
          i = (int)paramMotionEvent.getY(j);
          if ((Math.abs(i - mLastMotionY) <= mTouchSlop) || ((0x2 & getNestedScrollAxes()) != 0)) {
            break label343;
          }
          mIsBeingDragged = true;
          mLastMotionY = i;
          initVelocityTrackerIfNotExists();
          mVelocityTracker.addMovement(paramMotionEvent);
          mNestedYOffset = 0;
          paramMotionEvent = getParent();
          if (paramMotionEvent == null) {
            break label343;
          }
          paramMotionEvent.requestDisallowInterceptTouchEvent(true);
          break label343;
        }
      }
      mIsBeingDragged = false;
      mActivePointerId = -1;
      recycleVelocityTracker();
      if (mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
        ViewCompat.postInvalidateOnAnimation(this);
      }
      stopNestedScroll(0);
    }
    else
    {
      i = (int)paramMotionEvent.getY();
      if (!inChild((int)paramMotionEvent.getX(), i))
      {
        mIsBeingDragged = false;
        recycleVelocityTracker();
      }
      else
      {
        mLastMotionY = i;
        mActivePointerId = paramMotionEvent.getPointerId(0);
        initOrResetVelocityTracker();
        mVelocityTracker.addMovement(paramMotionEvent);
        mScroller.computeScrollOffset();
        mIsBeingDragged = (mScroller.isFinished() ^ true);
        startNestedScroll(2, 0);
      }
    }
    label343:
    return mIsBeingDragged;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    paramInt1 = 0;
    mIsLayoutDirty = false;
    View localView = mChildToScrollTo;
    if ((localView != null) && (isViewDescendantOf(localView, this))) {
      scrollToChild(mChildToScrollTo);
    }
    mChildToScrollTo = null;
    if (!mIsLaidOut)
    {
      if (mSavedState != null)
      {
        scrollTo(getScrollX(), mSavedState.scrollPosition);
        mSavedState = null;
      }
      if (getChildCount() > 0)
      {
        localView = getChildAt(0);
        FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView.getLayoutParams();
        paramInt1 = localView.getMeasuredHeight() + topMargin + bottomMargin;
      }
      int i = getPaddingTop();
      int j = getPaddingBottom();
      paramInt3 = getScrollY();
      paramInt1 = clamp(paramInt3, paramInt4 - paramInt2 - i - j, paramInt1);
      if (paramInt1 != paramInt3) {
        scrollTo(getScrollX(), paramInt1);
      }
    }
    scrollTo(getScrollX(), getScrollY());
    mIsLaidOut = true;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (!mFillViewport) {
      return;
    }
    if (View.MeasureSpec.getMode(paramInt2) == 0) {
      return;
    }
    if (getChildCount() > 0)
    {
      View localView = getChildAt(0);
      FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView.getLayoutParams();
      paramInt2 = localView.getMeasuredHeight();
      int i = getMeasuredHeight() - getPaddingTop() - getPaddingBottom() - topMargin - bottomMargin;
      if (paramInt2 < i) {
        localView.measure(ViewGroup.getChildMeasureSpec(paramInt1, getPaddingLeft() + getPaddingRight() + leftMargin + rightMargin, width), View.MeasureSpec.makeMeasureSpec(i, 1073741824));
      }
    }
  }
  
  public boolean onNestedFling(View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    if (!paramBoolean)
    {
      flingWithNestedDispatch((int)paramFloat2);
      return true;
    }
    return false;
  }
  
  public boolean onNestedPreFling(View paramView, float paramFloat1, float paramFloat2)
  {
    return dispatchNestedPreFling(paramFloat1, paramFloat2);
  }
  
  public void onNestedPreScroll(View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt)
  {
    onNestedPreScroll(paramView, paramInt1, paramInt2, paramArrayOfInt, 0);
  }
  
  public void onNestedPreScroll(View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt, int paramInt3)
  {
    dispatchNestedPreScroll(paramInt1, paramInt2, paramArrayOfInt, null, paramInt3);
  }
  
  public void onNestedScroll(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    scroll(paramView, paramInt1, paramInt2, paramInt3, paramInt4, 0);
  }
  
  public void onNestedScrollAccepted(View paramView1, View paramView2, int paramInt)
  {
    onNestedScrollAccepted(paramView1, paramView2, paramInt, 0);
  }
  
  public void onNestedScrollAccepted(View paramView1, View paramView2, int paramInt1, int paramInt2)
  {
    mParentHelper.onNestedScrollAccepted(paramView1, paramView2, paramInt1, paramInt2);
    startNestedScroll(2, paramInt2);
  }
  
  protected void onOverScrolled(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    super.scrollTo(paramInt1, paramInt2);
  }
  
  protected boolean onRequestFocusInDescendants(int paramInt, Rect paramRect)
  {
    int i;
    if (paramInt == 2)
    {
      i = 130;
    }
    else
    {
      i = paramInt;
      if (paramInt == 1) {
        i = 33;
      }
    }
    View localView;
    if (paramRect == null) {
      localView = FocusFinder.getInstance().findNextFocus(this, null, i);
    } else {
      localView = FocusFinder.getInstance().findNextFocusFromRect(this, paramRect, i);
    }
    if (localView == null) {
      return false;
    }
    if (isOffScreen(localView)) {
      return false;
    }
    return localView.requestFocus(i, paramRect);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof c))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    paramParcelable = (c)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    mSavedState = paramParcelable;
    requestLayout();
  }
  
  protected Parcelable onSaveInstanceState()
  {
    c localC = new c(super.onSaveInstanceState());
    scrollPosition = getScrollY();
    return localC;
  }
  
  protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    b localB = mOnScrollChangeListener;
    if (localB != null) {
      localB.onScrollChange(this, paramInt1, paramInt2, paramInt3, paramInt4);
    }
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    View localView = findFocus();
    if (localView != null)
    {
      if (this == localView) {
        return;
      }
      if (isWithinDeltaOfScreen(localView, 0, paramInt4))
      {
        localView.getDrawingRect(mTempRect);
        offsetDescendantRectToMyCoords(localView, mTempRect);
        doScrollY(computeScrollDeltaToGetChildRectOnScreen(mTempRect));
      }
    }
  }
  
  public boolean onStartNestedScroll(View paramView1, View paramView2, int paramInt)
  {
    return onStartNestedScroll(paramView1, paramView2, paramInt, 0);
  }
  
  public boolean onStartNestedScroll(View paramView1, View paramView2, int paramInt1, int paramInt2)
  {
    return (paramInt1 & 0x2) != 0;
  }
  
  public void onStopNestedScroll(View paramView)
  {
    onStopNestedScroll(paramView, 0);
  }
  
  public void onStopNestedScroll(View paramView, int paramInt)
  {
    mParentHelper.onStopNestedScroll(paramView, paramInt);
    stopNestedScroll(paramInt);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    initVelocityTrackerIfNotExists();
    MotionEvent localMotionEvent = MotionEvent.obtain(paramMotionEvent);
    int i = paramMotionEvent.getActionMasked();
    if (i == 0) {
      mNestedYOffset = 0;
    }
    localMotionEvent.offsetLocation(0.0F, mNestedYOffset);
    ViewParent localViewParent;
    if (i != 0)
    {
      if (i != 1) {
        if (i != 2)
        {
          if (i != 3)
          {
            if (i != 5)
            {
              if (i != 6) {
                break label870;
              }
              onSecondaryPointerUp(paramMotionEvent);
              mLastMotionY = ((int)paramMotionEvent.getY(paramMotionEvent.findPointerIndex(mActivePointerId)));
              break label870;
            }
            i = paramMotionEvent.getActionIndex();
            mLastMotionY = ((int)paramMotionEvent.getY(i));
            mActivePointerId = paramMotionEvent.getPointerId(i);
            break label870;
          }
          if ((!mIsBeingDragged) || (getChildCount() <= 0) || (!mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange()))) {}
        }
      }
      for (;;)
      {
        ViewCompat.postInvalidateOnAnimation(this);
        do
        {
          for (;;)
          {
            mActivePointerId = -1;
            endDrag();
            break label870;
            int k = paramMotionEvent.findPointerIndex(mActivePointerId);
            if (k == -1)
            {
              paramMotionEvent = new StringBuilder();
              paramMotionEvent.append("Invalid pointerId=");
              paramMotionEvent.append(mActivePointerId);
              paramMotionEvent.append(" in onTouchEvent");
              Log.e("NestedScrollView", paramMotionEvent.toString());
              break label870;
            }
            int m = (int)paramMotionEvent.getY(k);
            i = mLastMotionY - m;
            int j = i;
            if (dispatchNestedPreScroll(0, i, mScrollConsumed, mScrollOffset, 0))
            {
              j = i - mScrollConsumed[1];
              localMotionEvent.offsetLocation(0.0F, mScrollOffset[1]);
              mNestedYOffset += mScrollOffset[1];
            }
            i = j;
            if (!mIsBeingDragged)
            {
              i = j;
              if (Math.abs(j) > mTouchSlop)
              {
                localViewParent = getParent();
                if (localViewParent != null) {
                  localViewParent.requestDisallowInterceptTouchEvent(true);
                }
                mIsBeingDragged = true;
                if (j > 0) {
                  i = j - mTouchSlop;
                } else {
                  i = j + mTouchSlop;
                }
              }
            }
            if (!mIsBeingDragged) {
              break label870;
            }
            mLastMotionY = (m - mScrollOffset[1]);
            int n = getScrollY();
            m = getScrollRange();
            j = getOverScrollMode();
            if ((j != 0) && ((j != 1) || (m <= 0))) {
              j = 0;
            } else {
              j = 1;
            }
            if ((overScrollByCompat(0, i, 0, getScrollY(), 0, m, 0, 0, true)) && (!hasNestedScrollingParent(0))) {
              mVelocityTracker.clear();
            }
            int i1 = getScrollY() - n;
            if (dispatchNestedScroll(0, i1, 0, i - i1, mScrollOffset, 0))
            {
              i = mLastMotionY;
              paramMotionEvent = mScrollOffset;
              mLastMotionY = (i - paramMotionEvent[1]);
              localMotionEvent.offsetLocation(0.0F, paramMotionEvent[1]);
              mNestedYOffset += mScrollOffset[1];
              break label870;
            }
            if (j == 0) {
              break label870;
            }
            ensureGlows();
            j = n + i;
            if (j < 0)
            {
              RangeSeekBar.OnRangeSeekBarChangeListener.draw(mEdgeGlowTop, i / getHeight(), paramMotionEvent.getX(k) / getWidth());
              if (mEdgeGlowBottom.isFinished()) {}
            }
            else
            {
              for (paramMotionEvent = mEdgeGlowBottom;; paramMotionEvent = mEdgeGlowTop)
              {
                paramMotionEvent.onRelease();
                break;
                if (j <= m) {
                  break;
                }
                RangeSeekBar.OnRangeSeekBarChangeListener.draw(mEdgeGlowBottom, i / getHeight(), 1.0F - paramMotionEvent.getX(k) / getWidth());
                if (mEdgeGlowTop.isFinished()) {
                  break;
                }
              }
            }
            paramMotionEvent = mEdgeGlowTop;
            if ((paramMotionEvent == null) || ((paramMotionEvent.isFinished()) && (mEdgeGlowBottom.isFinished()))) {
              break label870;
            }
            ViewCompat.postInvalidateOnAnimation(this);
            break label870;
            paramMotionEvent = mVelocityTracker;
            paramMotionEvent.computeCurrentVelocity(1000, mMaximumVelocity);
            i = (int)paramMotionEvent.getYVelocity(mActivePointerId);
            if (Math.abs(i) <= mMinimumVelocity) {
              break;
            }
            flingWithNestedDispatch(-i);
          }
        } while (!mScroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange()));
      }
    }
    else
    {
      if (getChildCount() == 0) {
        return false;
      }
      boolean bool = mScroller.isFinished() ^ true;
      mIsBeingDragged = bool;
      if (bool)
      {
        localViewParent = getParent();
        if (localViewParent != null) {
          localViewParent.requestDisallowInterceptTouchEvent(true);
        }
      }
      if (!mScroller.isFinished()) {
        mScroller.abortAnimation();
      }
      mLastMotionY = ((int)paramMotionEvent.getY());
      mActivePointerId = paramMotionEvent.getPointerId(0);
      startNestedScroll(2, 0);
    }
    label870:
    paramMotionEvent = mVelocityTracker;
    if (paramMotionEvent != null) {
      paramMotionEvent.addMovement(localMotionEvent);
    }
    localMotionEvent.recycle();
    return true;
  }
  
  boolean overScrollByCompat(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, boolean paramBoolean)
  {
    int k = getOverScrollMode();
    int i;
    if (computeHorizontalScrollRange() > computeHorizontalScrollExtent()) {
      i = 1;
    } else {
      i = 0;
    }
    int j;
    if (computeVerticalScrollRange() > computeVerticalScrollExtent()) {
      j = 1;
    } else {
      j = 0;
    }
    if ((k != 0) && ((k != 1) || (i == 0))) {
      i = 0;
    } else {
      i = 1;
    }
    if ((k != 0) && ((k != 1) || (j == 0))) {
      j = 0;
    } else {
      j = 1;
    }
    paramInt3 += paramInt1;
    if (i == 0) {
      paramInt7 = 0;
    }
    paramInt4 += paramInt2;
    if (j == 0) {
      paramInt1 = 0;
    } else {
      paramInt1 = paramInt8;
    }
    paramInt2 = -paramInt7;
    paramInt7 += paramInt5;
    paramInt5 = -paramInt1;
    paramInt6 = paramInt1 + paramInt6;
    if (paramInt3 > paramInt7) {}
    for (paramInt1 = paramInt7;; paramInt1 = paramInt2)
    {
      paramBoolean = true;
      paramInt2 = paramInt1;
      break label189;
      if (paramInt3 >= paramInt2) {
        break;
      }
    }
    paramInt2 = paramInt3;
    paramBoolean = false;
    label189:
    if (paramInt4 > paramInt6) {}
    for (paramInt1 = paramInt6;; paramInt1 = paramInt5)
    {
      bool = true;
      break label224;
      if (paramInt4 >= paramInt5) {
        break;
      }
    }
    boolean bool = false;
    paramInt1 = paramInt4;
    label224:
    if ((bool) && (!hasNestedScrollingParent(1))) {
      mScroller.springBack(paramInt2, paramInt1, 0, 0, 0, getScrollRange());
    }
    onOverScrolled(paramInt2, paramInt1, paramBoolean, bool);
    return (paramBoolean) || (bool);
  }
  
  public boolean pageScroll(int paramInt)
  {
    if (paramInt == 130) {
      i = 1;
    } else {
      i = 0;
    }
    int j = getHeight();
    if (i != 0)
    {
      mTempRect.top = (getScrollY() + j);
      i = getChildCount();
      if (i > 0)
      {
        localObject = getChildAt(i - 1);
        FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)((View)localObject).getLayoutParams();
        i = ((View)localObject).getBottom() + bottomMargin + getPaddingBottom();
        localObject = mTempRect;
        if (top + j > i) {
          top = (i - j);
        }
      }
    }
    else
    {
      mTempRect.top = (getScrollY() - j);
      localObject = mTempRect;
      if (top < 0) {
        top = 0;
      }
    }
    Object localObject = mTempRect;
    int i = top;
    bottom = (j + i);
    return scrollAndFocus(paramInt, i, bottom);
  }
  
  public void requestChildFocus(View paramView1, View paramView2)
  {
    if (!mIsLayoutDirty) {
      scrollToChild(paramView2);
    } else {
      mChildToScrollTo = paramView2;
    }
    super.requestChildFocus(paramView1, paramView2);
  }
  
  public boolean requestChildRectangleOnScreen(View paramView, Rect paramRect, boolean paramBoolean)
  {
    paramRect.offset(paramView.getLeft() - paramView.getScrollX(), paramView.getTop() - paramView.getScrollY());
    return scrollToChildRect(paramRect, paramBoolean);
  }
  
  public void requestDisallowInterceptTouchEvent(boolean paramBoolean)
  {
    if (paramBoolean) {
      recycleVelocityTracker();
    }
    super.requestDisallowInterceptTouchEvent(paramBoolean);
  }
  
  public void requestLayout()
  {
    mIsLayoutDirty = true;
    super.requestLayout();
  }
  
  public void scroll(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    paramInt1 = getScrollY();
    scrollBy(0, paramInt4);
    paramInt1 = getScrollY() - paramInt1;
    dispatchNestedScroll(0, paramInt1, 0, paramInt4 - paramInt1, null, paramInt5);
  }
  
  public void scrollTo(int paramInt1, int paramInt2)
  {
    if (getChildCount() > 0)
    {
      View localView = getChildAt(0);
      FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView.getLayoutParams();
      int i2 = getWidth();
      int i3 = getPaddingLeft();
      int i4 = getPaddingRight();
      int i5 = localView.getWidth();
      int i6 = leftMargin;
      int i7 = rightMargin;
      int i = getHeight();
      int j = getPaddingTop();
      int k = getPaddingBottom();
      int m = localView.getHeight();
      int n = topMargin;
      int i1 = bottomMargin;
      paramInt1 = clamp(paramInt1, i2 - i3 - i4, i5 + i6 + i7);
      paramInt2 = clamp(paramInt2, i - j - k, m + n + i1);
      if ((paramInt1 != getScrollX()) || (paramInt2 != getScrollY())) {
        super.scrollTo(paramInt1, paramInt2);
      }
    }
  }
  
  public void setFillViewport(boolean paramBoolean)
  {
    if (paramBoolean != mFillViewport)
    {
      mFillViewport = paramBoolean;
      requestLayout();
    }
  }
  
  public void setNestedScrollingEnabled(boolean paramBoolean)
  {
    mChildHelper.setNestedScrollingEnabled(paramBoolean);
  }
  
  public void setOnScrollChangeListener(b paramB)
  {
    mOnScrollChangeListener = paramB;
  }
  
  public void setSmoothScrollingEnabled(boolean paramBoolean)
  {
    mSmoothScrollingEnabled = paramBoolean;
  }
  
  public boolean shouldDelayChildPressedState()
  {
    return true;
  }
  
  public final void smoothScrollBy(int paramInt1, int paramInt2)
  {
    if (getChildCount() == 0) {
      return;
    }
    if (AnimationUtils.currentAnimationTimeMillis() - mLastScroll > 250L)
    {
      View localView = getChildAt(0);
      FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView.getLayoutParams();
      int i = localView.getHeight();
      int j = topMargin;
      int k = bottomMargin;
      int m = getHeight();
      int n = getPaddingTop();
      int i1 = getPaddingBottom();
      paramInt1 = getScrollY();
      paramInt2 = Math.max(0, Math.min(paramInt2 + paramInt1, Math.max(0, i + j + k - (m - n - i1))));
      mPreviousScrollerY = getScrollY();
      mScroller.startScroll(getScrollX(), paramInt1, 0, paramInt2 - paramInt1);
      ViewCompat.postInvalidateOnAnimation(this);
    }
    else
    {
      if (!mScroller.isFinished()) {
        mScroller.abortAnimation();
      }
      scrollBy(paramInt1, paramInt2);
    }
    mLastScroll = AnimationUtils.currentAnimationTimeMillis();
  }
  
  public final void smoothScrollTo(int paramInt1, int paramInt2)
  {
    smoothScrollBy(paramInt1 - getScrollX(), paramInt2 - getScrollY());
  }
  
  public boolean startNestedScroll(int paramInt)
  {
    return startNestedScroll(paramInt, 0);
  }
  
  public boolean startNestedScroll(int paramInt1, int paramInt2)
  {
    return mChildHelper.startNestedScroll(paramInt1, paramInt2);
  }
  
  public void stopNestedScroll()
  {
    stopNestedScroll(0);
  }
  
  public void stopNestedScroll(int paramInt)
  {
    mChildHelper.stopNestedScroll(paramInt);
  }
  
  static class a
    extends AccessibilityDelegateCompat
  {
    a() {}
    
    public void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
    {
      super.onInitializeAccessibilityEvent(paramView, paramAccessibilityEvent);
      paramView = (NestedScrollView)paramView;
      paramAccessibilityEvent.setClassName(ScrollView.class.getName());
      boolean bool;
      if (paramView.getScrollRange() > 0) {
        bool = true;
      } else {
        bool = false;
      }
      paramAccessibilityEvent.setScrollable(bool);
      paramAccessibilityEvent.setScrollX(paramView.getScrollX());
      paramAccessibilityEvent.setScrollY(paramView.getScrollY());
      AccessibilityRecordCompat.setMaxScrollX(paramAccessibilityEvent, paramView.getScrollX());
      AccessibilityRecordCompat.obtain(paramAccessibilityEvent, paramView.getScrollRange());
    }
    
    public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      super.onInitializeAccessibilityNodeInfo(paramView, paramAccessibilityNodeInfoCompat);
      paramView = (NestedScrollView)paramView;
      paramAccessibilityNodeInfoCompat.setClassName(ScrollView.class.getName());
      if (paramView.isEnabled())
      {
        int i = paramView.getScrollRange();
        if (i > 0)
        {
          paramAccessibilityNodeInfoCompat.setScrollable(true);
          if (paramView.getScrollY() > 0) {
            paramAccessibilityNodeInfoCompat.addAction(8192);
          }
          if (paramView.getScrollY() < i) {
            paramAccessibilityNodeInfoCompat.addAction(4096);
          }
        }
      }
    }
    
    public boolean performAccessibilityAction(View paramView, int paramInt, Bundle paramBundle)
    {
      if (super.performAccessibilityAction(paramView, paramInt, paramBundle)) {
        return true;
      }
      paramView = (NestedScrollView)paramView;
      if (!paramView.isEnabled()) {
        return false;
      }
      if (paramInt != 4096)
      {
        if (paramInt != 8192) {
          return false;
        }
        paramInt = paramView.getHeight();
        i = paramView.getPaddingBottom();
        j = paramView.getPaddingTop();
        paramInt = Math.max(paramView.getScrollY() - (paramInt - i - j), 0);
        if (paramInt != paramView.getScrollY())
        {
          paramView.smoothScrollTo(0, paramInt);
          return true;
        }
        return false;
      }
      paramInt = paramView.getHeight();
      int i = paramView.getPaddingBottom();
      int j = paramView.getPaddingTop();
      paramInt = Math.min(paramView.getScrollY() + (paramInt - i - j), paramView.getScrollRange());
      if (paramInt != paramView.getScrollY())
      {
        paramView.smoothScrollTo(0, paramInt);
        return true;
      }
      return false;
    }
  }
  
  public static abstract interface b
  {
    public abstract void onScrollChange(NestedScrollView paramNestedScrollView, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  }
  
  static class c
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<c> CREATOR = new VerticalProgressBar.SavedState.1();
    public int scrollPosition;
    
    c(Parcel paramParcel)
    {
      super();
      scrollPosition = paramParcel.readInt();
    }
    
    c(Parcelable paramParcelable)
    {
      super();
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("HorizontalScrollView.SavedState{");
      localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      localStringBuilder.append(" scrollPosition=");
      localStringBuilder.append(scrollPosition);
      localStringBuilder.append("}");
      return localStringBuilder.toString();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(scrollPosition);
    }
  }
}

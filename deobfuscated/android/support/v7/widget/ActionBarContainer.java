package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.org.android.view.ViewCompat;
import com.org.v4.util.R.id;
import com.org.v4.util.R.styleable;

public class ActionBarContainer
  extends FrameLayout
{
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
  
  public ActionBarContainer(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ActionBarContainer(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    ViewCompat.setBackground(this, new CircularBorderDrawable(this));
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ActionBar);
    mBackground = paramContext.getDrawable(R.styleable.ActionBar_background);
    mStackedBackground = paramContext.getDrawable(R.styleable.ActionBar_backgroundStacked);
    mHeight = paramContext.getDimensionPixelSize(R.styleable.ActionBar_height, -1);
    if (getId() == R.id.split_action_bar)
    {
      mIsSplit = true;
      mSplitBackground = paramContext.getDrawable(R.styleable.ActionBar_backgroundSplit);
    }
    paramContext.recycle();
    boolean bool1 = mIsSplit;
    boolean bool2 = false;
    if (bool1)
    {
      bool1 = bool2;
      if (mSplitBackground != null) {}
    }
    else
    {
      do
      {
        bool1 = true;
        break;
        bool1 = bool2;
        if (mBackground != null) {
          break;
        }
        bool1 = bool2;
      } while (mStackedBackground == null);
    }
    setWillNotDraw(bool1);
  }
  
  private int getMeasuredHeightWithMargins(View paramView)
  {
    FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)paramView.getLayoutParams();
    return paramView.getMeasuredHeight() + topMargin + bottomMargin;
  }
  
  private boolean isCollapsed(View paramView)
  {
    return (paramView == null) || (paramView.getVisibility() == 8) || (paramView.getMeasuredHeight() == 0);
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    Drawable localDrawable = mBackground;
    if ((localDrawable != null) && (localDrawable.isStateful())) {
      mBackground.setState(getDrawableState());
    }
    localDrawable = mStackedBackground;
    if ((localDrawable != null) && (localDrawable.isStateful())) {
      mStackedBackground.setState(getDrawableState());
    }
    localDrawable = mSplitBackground;
    if ((localDrawable != null) && (localDrawable.isStateful())) {
      mSplitBackground.setState(getDrawableState());
    }
  }
  
  public View getTabContainer()
  {
    return mTabContainer;
  }
  
  public void jumpDrawablesToCurrentState()
  {
    super.jumpDrawablesToCurrentState();
    Drawable localDrawable = mBackground;
    if (localDrawable != null) {
      localDrawable.jumpToCurrentState();
    }
    localDrawable = mStackedBackground;
    if (localDrawable != null) {
      localDrawable.jumpToCurrentState();
    }
    localDrawable = mSplitBackground;
    if (localDrawable != null) {
      localDrawable.jumpToCurrentState();
    }
  }
  
  public void onFinishInflate()
  {
    super.onFinishInflate();
    mActionBarView = findViewById(R.id.action_bar);
    mContextView = findViewById(R.id.action_context_bar);
  }
  
  public boolean onHoverEvent(MotionEvent paramMotionEvent)
  {
    super.onHoverEvent(paramMotionEvent);
    return true;
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    return (mIsTransitioning) || (super.onInterceptTouchEvent(paramMotionEvent));
  }
  
  public void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    View localView2 = mTabContainer;
    paramInt4 = 1;
    paramInt2 = 0;
    if ((localView2 != null) && (localView2.getVisibility() != 8)) {
      paramBoolean = true;
    } else {
      paramBoolean = false;
    }
    Object localObject;
    if ((localView2 != null) && (localView2.getVisibility() != 8))
    {
      int i = getMeasuredHeight();
      localObject = (FrameLayout.LayoutParams)localView2.getLayoutParams();
      localView2.layout(paramInt1, i - localView2.getMeasuredHeight() - bottomMargin, paramInt3, i - bottomMargin);
    }
    if (mIsSplit)
    {
      localObject = mSplitBackground;
      if (localObject != null)
      {
        ((Drawable)localObject).setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        paramInt1 = paramInt4;
      }
      else
      {
        paramInt1 = 0;
      }
    }
    else
    {
      paramInt1 = paramInt2;
      if (mBackground != null)
      {
        if (mActionBarView.getVisibility() == 0)
        {
          localObject = mBackground;
          paramInt1 = mActionBarView.getLeft();
          paramInt2 = mActionBarView.getTop();
          paramInt3 = mActionBarView.getRight();
        }
        for (View localView1 = mActionBarView;; localView1 = mContextView)
        {
          ((Drawable)localObject).setBounds(paramInt1, paramInt2, paramInt3, localView1.getBottom());
          break label292;
          localObject = mContextView;
          if ((localObject == null) || (((View)localObject).getVisibility() != 0)) {
            break;
          }
          localObject = mBackground;
          paramInt1 = mContextView.getLeft();
          paramInt2 = mContextView.getTop();
          paramInt3 = mContextView.getRight();
        }
        mBackground.setBounds(0, 0, 0, 0);
        label292:
        paramInt1 = 1;
      }
      mIsStacked = paramBoolean;
      if (paramBoolean)
      {
        localObject = mStackedBackground;
        if (localObject != null)
        {
          ((Drawable)localObject).setBounds(localView2.getLeft(), localView2.getTop(), localView2.getRight(), localView2.getBottom());
          paramInt1 = paramInt4;
        }
      }
    }
    if (paramInt1 != 0) {
      invalidate();
    }
  }
  
  public void onMeasure(int paramInt1, int paramInt2)
  {
    int i = paramInt2;
    if (mActionBarView == null)
    {
      i = paramInt2;
      if (View.MeasureSpec.getMode(paramInt2) == Integer.MIN_VALUE)
      {
        int j = mHeight;
        i = paramInt2;
        if (j >= 0) {
          i = View.MeasureSpec.makeMeasureSpec(Math.min(j, View.MeasureSpec.getSize(paramInt2)), Integer.MIN_VALUE);
        }
      }
    }
    super.onMeasure(paramInt1, i);
    if (mActionBarView == null) {
      return;
    }
    paramInt2 = View.MeasureSpec.getMode(i);
    View localView = mTabContainer;
    if ((localView != null) && (localView.getVisibility() != 8) && (paramInt2 != 1073741824))
    {
      if (!isCollapsed(mActionBarView)) {}
      for (localView = mActionBarView;; localView = mContextView)
      {
        paramInt1 = getMeasuredHeightWithMargins(localView);
        break label143;
        if (isCollapsed(mContextView)) {
          break;
        }
      }
      paramInt1 = 0;
      label143:
      if (paramInt2 == Integer.MIN_VALUE) {
        paramInt2 = View.MeasureSpec.getSize(i);
      } else {
        paramInt2 = Integer.MAX_VALUE;
      }
      setMeasuredDimension(getMeasuredWidth(), Math.min(paramInt1 + getMeasuredHeightWithMargins(mTabContainer), paramInt2));
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    super.onTouchEvent(paramMotionEvent);
    return true;
  }
  
  public void setPrimaryBackground(Drawable paramDrawable)
  {
    Drawable localDrawable = mBackground;
    if (localDrawable != null)
    {
      localDrawable.setCallback(null);
      unscheduleDrawable(mBackground);
    }
    mBackground = paramDrawable;
    if (paramDrawable != null)
    {
      paramDrawable.setCallback(this);
      paramDrawable = mActionBarView;
      if (paramDrawable != null) {
        mBackground.setBounds(paramDrawable.getLeft(), mActionBarView.getTop(), mActionBarView.getRight(), mActionBarView.getBottom());
      }
    }
    boolean bool2 = mIsSplit;
    boolean bool1 = true;
    if (bool2 ? mSplitBackground == null : (mBackground != null) || (mStackedBackground != null)) {
      bool1 = false;
    }
    setWillNotDraw(bool1);
    invalidate();
  }
  
  public void setSplitBackground(Drawable paramDrawable)
  {
    Drawable localDrawable = mSplitBackground;
    if (localDrawable != null)
    {
      localDrawable.setCallback(null);
      unscheduleDrawable(mSplitBackground);
    }
    mSplitBackground = paramDrawable;
    boolean bool2 = false;
    if (paramDrawable != null)
    {
      paramDrawable.setCallback(this);
      if (mIsSplit)
      {
        paramDrawable = mSplitBackground;
        if (paramDrawable != null) {
          paramDrawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        }
      }
    }
    boolean bool1;
    if (mIsSplit)
    {
      bool1 = bool2;
      if (mSplitBackground != null) {}
    }
    else
    {
      do
      {
        bool1 = true;
        break;
        bool1 = bool2;
        if (mBackground != null) {
          break;
        }
        bool1 = bool2;
      } while (mStackedBackground == null);
    }
    setWillNotDraw(bool1);
    invalidate();
  }
  
  public void setStackedBackground(Drawable paramDrawable)
  {
    Drawable localDrawable = mStackedBackground;
    if (localDrawable != null)
    {
      localDrawable.setCallback(null);
      unscheduleDrawable(mStackedBackground);
    }
    mStackedBackground = paramDrawable;
    if (paramDrawable != null)
    {
      paramDrawable.setCallback(this);
      if (mIsStacked)
      {
        paramDrawable = mStackedBackground;
        if (paramDrawable != null) {
          paramDrawable.setBounds(mTabContainer.getLeft(), mTabContainer.getTop(), mTabContainer.getRight(), mTabContainer.getBottom());
        }
      }
    }
    boolean bool2 = mIsSplit;
    boolean bool1 = true;
    if (bool2 ? mSplitBackground == null : (mBackground != null) || (mStackedBackground != null)) {
      bool1 = false;
    }
    setWillNotDraw(bool1);
    invalidate();
  }
  
  public void setTabContainer(ScrollingTabContainerView paramScrollingTabContainerView)
  {
    Object localObject = mTabContainer;
    if (localObject != null) {
      removeView((View)localObject);
    }
    mTabContainer = paramScrollingTabContainerView;
    if (paramScrollingTabContainerView != null)
    {
      addView(paramScrollingTabContainerView);
      localObject = paramScrollingTabContainerView.getLayoutParams();
      width = -1;
      height = -2;
      paramScrollingTabContainerView.setAllowCollapse(false);
    }
  }
  
  public void setTransitioning(boolean paramBoolean)
  {
    mIsTransitioning = paramBoolean;
    int i;
    if (paramBoolean) {
      i = 393216;
    } else {
      i = 262144;
    }
    setDescendantFocusability(i);
  }
  
  public void setVisibility(int paramInt)
  {
    super.setVisibility(paramInt);
    boolean bool;
    if (paramInt == 0) {
      bool = true;
    } else {
      bool = false;
    }
    Drawable localDrawable = mBackground;
    if (localDrawable != null) {
      localDrawable.setVisible(bool, false);
    }
    localDrawable = mStackedBackground;
    if (localDrawable != null) {
      localDrawable.setVisible(bool, false);
    }
    localDrawable = mSplitBackground;
    if (localDrawable != null) {
      localDrawable.setVisible(bool, false);
    }
  }
  
  public ActionMode startActionModeForChild(View paramView, ActionMode.Callback paramCallback)
  {
    return null;
  }
  
  public ActionMode startActionModeForChild(View paramView, ActionMode.Callback paramCallback, int paramInt)
  {
    if (paramInt != 0) {
      return super.startActionModeForChild(paramView, paramCallback, paramInt);
    }
    return null;
  }
  
  protected boolean verifyDrawable(Drawable paramDrawable)
  {
    return ((paramDrawable == mBackground) && (!mIsSplit)) || ((paramDrawable == mStackedBackground) && (mIsStacked)) || ((paramDrawable == mSplitBackground) && (mIsSplit)) || (super.verifyDrawable(paramDrawable));
  }
}

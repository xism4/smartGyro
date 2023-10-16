package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import com.org.android.view.ViewCompat;
import com.org.android.view.ViewPropertyAnimatorCompat;
import com.org.android.view.ViewPropertyAnimatorListener;
import com.org.v4.util.R.attr;
import com.org.v4.util.R.styleable;

abstract class AbsActionBarView
  extends ViewGroup
{
  protected ActionMenuPresenter mActionMenuPresenter;
  protected int mContentHeight;
  private boolean mEatingHover;
  private boolean mEatingTouch;
  protected ActionMenuView mMenuView;
  protected final Context mPopupContext;
  protected final VisibilityAnimListener mVisAnimListener = new VisibilityAnimListener();
  protected ViewPropertyAnimatorCompat mVisibilityAnim;
  
  AbsActionBarView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  AbsActionBarView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  AbsActionBarView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramAttributeSet = new TypedValue();
    if (paramContext.getTheme().resolveAttribute(R.attr.actionBarPopupTheme, paramAttributeSet, true))
    {
      paramInt = resourceId;
      if (paramInt != 0)
      {
        mPopupContext = new ContextThemeWrapper(paramContext, paramInt);
        return;
      }
    }
    mPopupContext = paramContext;
  }
  
  protected static int next(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramBoolean) {
      return paramInt1 - paramInt2;
    }
    return paramInt1 + paramInt2;
  }
  
  public int getAnimatedVisibility()
  {
    if (mVisibilityAnim != null) {
      return mVisAnimListener.mFinalVisibility;
    }
    return getVisibility();
  }
  
  public int getContentHeight()
  {
    return mContentHeight;
  }
  
  protected int measureChildView(View paramView, int paramInt1, int paramInt2, int paramInt3)
  {
    paramView.measure(View.MeasureSpec.makeMeasureSpec(paramInt1, Integer.MIN_VALUE), paramInt2);
    return Math.max(0, paramInt1 - paramView.getMeasuredWidth() - paramInt3);
  }
  
  protected void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    Object localObject = getContext().obtainStyledAttributes(null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
    setContentHeight(((TypedArray)localObject).getLayoutDimension(R.styleable.ActionBar_height, 0));
    ((TypedArray)localObject).recycle();
    localObject = mActionMenuPresenter;
    if (localObject != null) {
      ((ActionMenuPresenter)localObject).onConfigurationChanged(paramConfiguration);
    }
  }
  
  public boolean onHoverEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getActionMasked();
    if (i == 9) {
      mEatingHover = false;
    }
    if (!mEatingHover)
    {
      boolean bool = super.onHoverEvent(paramMotionEvent);
      if ((i == 9) && (!bool)) {
        mEatingHover = true;
      }
    }
    if ((i == 10) || (i == 3)) {
      mEatingHover = false;
    }
    return true;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getActionMasked();
    if (i == 0) {
      mEatingTouch = false;
    }
    if (!mEatingTouch)
    {
      boolean bool = super.onTouchEvent(paramMotionEvent);
      if ((i == 0) && (!bool)) {
        mEatingTouch = true;
      }
    }
    if ((i == 1) || (i == 3)) {
      mEatingTouch = false;
    }
    return true;
  }
  
  protected int positionChild(View paramView, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    int i = paramView.getMeasuredWidth();
    int j = paramView.getMeasuredHeight();
    paramInt2 += (paramInt3 - j) / 2;
    if (paramBoolean) {
      paramView.layout(paramInt1 - i, paramInt2, paramInt1, j + paramInt2);
    } else {
      paramView.layout(paramInt1, paramInt2, paramInt1 + i, j + paramInt2);
    }
    if (paramBoolean) {
      return -i;
    }
    return i;
  }
  
  public abstract void setContentHeight(int paramInt);
  
  public void setVisibility(int paramInt)
  {
    if (paramInt != getVisibility())
    {
      ViewPropertyAnimatorCompat localViewPropertyAnimatorCompat = mVisibilityAnim;
      if (localViewPropertyAnimatorCompat != null) {
        localViewPropertyAnimatorCompat.cancel();
      }
      super.setVisibility(paramInt);
    }
  }
  
  public ViewPropertyAnimatorCompat setupAnimatorToVisibility(int paramInt, long paramLong)
  {
    ViewPropertyAnimatorCompat localViewPropertyAnimatorCompat = mVisibilityAnim;
    if (localViewPropertyAnimatorCompat != null) {
      localViewPropertyAnimatorCompat.cancel();
    }
    if (paramInt == 0)
    {
      if (getVisibility() != 0) {
        setAlpha(0.0F);
      }
      localViewPropertyAnimatorCompat = ViewCompat.animate(this);
      localViewPropertyAnimatorCompat.alpha(1.0F);
      localViewPropertyAnimatorCompat.setDuration(paramLong);
      localVisibilityAnimListener = mVisAnimListener;
      localVisibilityAnimListener.withFinalVisibility(localViewPropertyAnimatorCompat, paramInt);
      localViewPropertyAnimatorCompat.setListener(localVisibilityAnimListener);
      return localViewPropertyAnimatorCompat;
    }
    localViewPropertyAnimatorCompat = ViewCompat.animate(this);
    localViewPropertyAnimatorCompat.alpha(0.0F);
    localViewPropertyAnimatorCompat.setDuration(paramLong);
    VisibilityAnimListener localVisibilityAnimListener = mVisAnimListener;
    localVisibilityAnimListener.withFinalVisibility(localViewPropertyAnimatorCompat, paramInt);
    localViewPropertyAnimatorCompat.setListener(localVisibilityAnimListener);
    return localViewPropertyAnimatorCompat;
  }
  
  public class VisibilityAnimListener
    implements ViewPropertyAnimatorListener
  {
    private boolean mCanceled = false;
    int mFinalVisibility;
    
    protected VisibilityAnimListener() {}
    
    public void onAnimationCancel(View paramView)
    {
      mCanceled = true;
    }
    
    public void onAnimationEnd(View paramView)
    {
      if (mCanceled) {
        return;
      }
      paramView = AbsActionBarView.this;
      mVisibilityAnim = null;
      AbsActionBarView.setVisibility(paramView, mFinalVisibility);
    }
    
    public void onAnimationStart(View paramView)
    {
      AbsActionBarView.access$getSetVisibility(AbsActionBarView.this, 0);
      mCanceled = false;
    }
    
    public VisibilityAnimListener withFinalVisibility(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, int paramInt)
    {
      mVisibilityAnim = paramViewPropertyAnimatorCompat;
      mFinalVisibility = paramInt;
      return this;
    }
  }
}

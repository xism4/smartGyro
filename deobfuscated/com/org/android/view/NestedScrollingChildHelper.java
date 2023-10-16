package com.org.android.view;

import android.view.View;
import android.view.ViewParent;

public class NestedScrollingChildHelper
{
  private ViewParent mCallback;
  private boolean mIsNestedScrollingEnabled;
  private ViewParent mMenu;
  private int[] mTempNestedScrollConsumed;
  private final View mView;
  
  public NestedScrollingChildHelper(View paramView)
  {
    mView = paramView;
  }
  
  private ViewParent getChildAt(int paramInt)
  {
    if (paramInt != 0)
    {
      if (paramInt != 1) {
        return null;
      }
      return mCallback;
    }
    return mMenu;
  }
  
  private void initialize(int paramInt, ViewParent paramViewParent)
  {
    if (paramInt != 0)
    {
      if (paramInt != 1) {
        return;
      }
      mCallback = paramViewParent;
      return;
    }
    mMenu = paramViewParent;
  }
  
  public boolean dispatchNestedFling(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    if (isNestedScrollingEnabled())
    {
      ViewParent localViewParent = getChildAt(0);
      if (localViewParent != null) {
        return ViewParentCompat.onNestedFling(localViewParent, mView, paramFloat1, paramFloat2, paramBoolean);
      }
    }
    return false;
  }
  
  public boolean dispatchNestedPreFling(float paramFloat1, float paramFloat2)
  {
    if (isNestedScrollingEnabled())
    {
      ViewParent localViewParent = getChildAt(0);
      if (localViewParent != null) {
        return ViewParentCompat.onNestedPreFling(localViewParent, mView, paramFloat1, paramFloat2);
      }
    }
    return false;
  }
  
  public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt3)
  {
    if (isNestedScrollingEnabled())
    {
      ViewParent localViewParent = getChildAt(paramInt3);
      if (localViewParent == null) {
        return false;
      }
      if ((paramInt1 == 0) && (paramInt2 == 0))
      {
        if (paramArrayOfInt2 != null)
        {
          paramArrayOfInt2[0] = 0;
          paramArrayOfInt2[1] = 0;
          return false;
        }
      }
      else
      {
        int i;
        int j;
        if (paramArrayOfInt2 != null)
        {
          mView.getLocationInWindow(paramArrayOfInt2);
          i = paramArrayOfInt2[0];
          j = paramArrayOfInt2[1];
        }
        else
        {
          i = 0;
          j = 0;
        }
        int[] arrayOfInt = paramArrayOfInt1;
        if (paramArrayOfInt1 == null)
        {
          if (mTempNestedScrollConsumed == null) {
            mTempNestedScrollConsumed = new int[2];
          }
          arrayOfInt = mTempNestedScrollConsumed;
        }
        arrayOfInt[0] = 0;
        arrayOfInt[1] = 0;
        ViewParentCompat.onNestedPreScroll(localViewParent, mView, paramInt1, paramInt2, arrayOfInt, paramInt3);
        if (paramArrayOfInt2 != null)
        {
          mView.getLocationInWindow(paramArrayOfInt2);
          paramArrayOfInt2[0] -= i;
          paramArrayOfInt2[1] -= j;
        }
        if (arrayOfInt[0] != 0) {
          break label193;
        }
        return arrayOfInt[1] != 0;
      }
    }
    return false;
    label193:
    return true;
  }
  
  public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt, int paramInt5)
  {
    if (isNestedScrollingEnabled())
    {
      ViewParent localViewParent = getChildAt(paramInt5);
      if (localViewParent == null) {
        return false;
      }
      if ((paramInt1 == 0) && (paramInt2 == 0) && (paramInt3 == 0) && (paramInt4 == 0))
      {
        if (paramArrayOfInt != null)
        {
          paramArrayOfInt[0] = 0;
          paramArrayOfInt[1] = 0;
          return false;
        }
      }
      else
      {
        int i;
        int j;
        if (paramArrayOfInt != null)
        {
          mView.getLocationInWindow(paramArrayOfInt);
          i = paramArrayOfInt[0];
          j = paramArrayOfInt[1];
        }
        else
        {
          i = 0;
          j = 0;
        }
        ViewParentCompat.onNestedScroll(localViewParent, mView, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
        if (paramArrayOfInt == null) {
          break label150;
        }
        mView.getLocationInWindow(paramArrayOfInt);
        paramArrayOfInt[0] -= i;
        paramArrayOfInt[1] -= j;
        return true;
      }
    }
    return false;
    label150:
    return true;
  }
  
  public boolean hasNestedScrollingParent(int paramInt)
  {
    return getChildAt(paramInt) != null;
  }
  
  public boolean isNestedScrollingEnabled()
  {
    return mIsNestedScrollingEnabled;
  }
  
  public void setNestedScrollingEnabled(boolean paramBoolean)
  {
    if (mIsNestedScrollingEnabled) {
      ViewCompat.draw(mView);
    }
    mIsNestedScrollingEnabled = paramBoolean;
  }
  
  public boolean startNestedScroll(int paramInt1, int paramInt2)
  {
    if (hasNestedScrollingParent(paramInt2)) {
      return true;
    }
    if (isNestedScrollingEnabled())
    {
      ViewParent localViewParent = mView.getParent();
      View localView = mView;
      while (localViewParent != null)
      {
        if (ViewParentCompat.onStartNestedScroll(localViewParent, localView, mView, paramInt1, paramInt2))
        {
          initialize(paramInt2, localViewParent);
          ViewParentCompat.onNestedScrollAccepted(localViewParent, localView, mView, paramInt1, paramInt2);
          return true;
        }
        if ((localViewParent instanceof View)) {
          localView = (View)localViewParent;
        }
        localViewParent = localViewParent.getParent();
      }
    }
    return false;
  }
  
  public void stopNestedScroll(int paramInt)
  {
    ViewParent localViewParent = getChildAt(paramInt);
    if (localViewParent != null)
    {
      ViewParentCompat.onStopNestedScroll(localViewParent, mView, paramInt);
      initialize(paramInt, null);
    }
  }
}

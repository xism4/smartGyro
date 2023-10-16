package com.org.android.view;

import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;

public final class ViewParentCompat
{
  public static boolean onNestedFling(ViewParent paramViewParent, View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      try
      {
        paramBoolean = paramViewParent.onNestedFling(paramView, paramFloat1, paramFloat2, paramBoolean);
        return paramBoolean;
      }
      catch (AbstractMethodError paramView)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("ViewParent ");
        localStringBuilder.append(paramViewParent);
        localStringBuilder.append(" does not implement interface ");
        localStringBuilder.append("method onNestedFling");
        Log.e("ViewParentCompat", localStringBuilder.toString(), paramView);
      }
    } else if ((paramViewParent instanceof NestedScrollingParent)) {
      return ((NestedScrollingParent)paramViewParent).onNestedFling(paramView, paramFloat1, paramFloat2, paramBoolean);
    }
    return false;
  }
  
  public static boolean onNestedPreFling(ViewParent paramViewParent, View paramView, float paramFloat1, float paramFloat2)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      try
      {
        boolean bool = paramViewParent.onNestedPreFling(paramView, paramFloat1, paramFloat2);
        return bool;
      }
      catch (AbstractMethodError paramView)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("ViewParent ");
        localStringBuilder.append(paramViewParent);
        localStringBuilder.append(" does not implement interface ");
        localStringBuilder.append("method onNestedPreFling");
        Log.e("ViewParentCompat", localStringBuilder.toString(), paramView);
      }
    } else if ((paramViewParent instanceof NestedScrollingParent)) {
      return ((NestedScrollingParent)paramViewParent).onNestedPreFling(paramView, paramFloat1, paramFloat2);
    }
    return false;
  }
  
  public static void onNestedPreScroll(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt, int paramInt3)
  {
    if ((paramViewParent instanceof ViewParentCompat.ViewParentCompatImpl))
    {
      ((ViewParentCompat.ViewParentCompatImpl)paramViewParent).onNestedPreScroll(paramView, paramInt1, paramInt2, paramArrayOfInt, paramInt3);
      return;
    }
    if (paramInt3 == 0)
    {
      if (Build.VERSION.SDK_INT >= 21) {
        try
        {
          paramViewParent.onNestedPreScroll(paramView, paramInt1, paramInt2, paramArrayOfInt);
          return;
        }
        catch (AbstractMethodError paramView)
        {
          paramArrayOfInt = new StringBuilder();
          paramArrayOfInt.append("ViewParent ");
          paramArrayOfInt.append(paramViewParent);
          paramArrayOfInt.append(" does not implement interface ");
          paramArrayOfInt.append("method onNestedPreScroll");
          Log.e("ViewParentCompat", paramArrayOfInt.toString(), paramView);
          return;
        }
      }
      if ((paramViewParent instanceof NestedScrollingParent)) {
        ((NestedScrollingParent)paramViewParent).onNestedPreScroll(paramView, paramInt1, paramInt2, paramArrayOfInt);
      }
    }
  }
  
  public static void onNestedScroll(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    if ((paramViewParent instanceof ViewParentCompat.ViewParentCompatImpl))
    {
      ((ViewParentCompat.ViewParentCompatImpl)paramViewParent).scroll(paramView, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
      return;
    }
    if (paramInt5 == 0)
    {
      if (Build.VERSION.SDK_INT >= 21) {
        try
        {
          paramViewParent.onNestedScroll(paramView, paramInt1, paramInt2, paramInt3, paramInt4);
          return;
        }
        catch (AbstractMethodError paramView)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("ViewParent ");
          localStringBuilder.append(paramViewParent);
          localStringBuilder.append(" does not implement interface ");
          localStringBuilder.append("method onNestedScroll");
          Log.e("ViewParentCompat", localStringBuilder.toString(), paramView);
          return;
        }
      }
      if ((paramViewParent instanceof NestedScrollingParent)) {
        ((NestedScrollingParent)paramViewParent).onNestedScroll(paramView, paramInt1, paramInt2, paramInt3, paramInt4);
      }
    }
  }
  
  public static void onNestedScrollAccepted(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt1, int paramInt2)
  {
    if ((paramViewParent instanceof ViewParentCompat.ViewParentCompatImpl))
    {
      ((ViewParentCompat.ViewParentCompatImpl)paramViewParent).onNestedScrollAccepted(paramView1, paramView2, paramInt1, paramInt2);
      return;
    }
    if (paramInt2 == 0)
    {
      if (Build.VERSION.SDK_INT >= 21) {
        try
        {
          paramViewParent.onNestedScrollAccepted(paramView1, paramView2, paramInt1);
          return;
        }
        catch (AbstractMethodError paramView1)
        {
          paramView2 = new StringBuilder();
          paramView2.append("ViewParent ");
          paramView2.append(paramViewParent);
          paramView2.append(" does not implement interface ");
          paramView2.append("method onNestedScrollAccepted");
          Log.e("ViewParentCompat", paramView2.toString(), paramView1);
          return;
        }
      }
      if ((paramViewParent instanceof NestedScrollingParent)) {
        ((NestedScrollingParent)paramViewParent).onNestedScrollAccepted(paramView1, paramView2, paramInt1);
      }
    }
  }
  
  public static boolean onStartNestedScroll(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt1, int paramInt2)
  {
    if ((paramViewParent instanceof ViewParentCompat.ViewParentCompatImpl)) {
      return ((ViewParentCompat.ViewParentCompatImpl)paramViewParent).onStartNestedScroll(paramView1, paramView2, paramInt1, paramInt2);
    }
    if (paramInt2 == 0) {
      if (Build.VERSION.SDK_INT >= 21) {
        try
        {
          boolean bool = paramViewParent.onStartNestedScroll(paramView1, paramView2, paramInt1);
          return bool;
        }
        catch (AbstractMethodError paramView1)
        {
          paramView2 = new StringBuilder();
          paramView2.append("ViewParent ");
          paramView2.append(paramViewParent);
          paramView2.append(" does not implement interface ");
          paramView2.append("method onStartNestedScroll");
          Log.e("ViewParentCompat", paramView2.toString(), paramView1);
        }
      } else if ((paramViewParent instanceof NestedScrollingParent)) {
        return ((NestedScrollingParent)paramViewParent).onStartNestedScroll(paramView1, paramView2, paramInt1);
      }
    }
    return false;
  }
  
  public static void onStopNestedScroll(ViewParent paramViewParent, View paramView, int paramInt)
  {
    if ((paramViewParent instanceof ViewParentCompat.ViewParentCompatImpl))
    {
      ((ViewParentCompat.ViewParentCompatImpl)paramViewParent).onStopNestedScroll(paramView, paramInt);
      return;
    }
    if (paramInt == 0)
    {
      if (Build.VERSION.SDK_INT >= 21) {
        try
        {
          paramViewParent.onStopNestedScroll(paramView);
          return;
        }
        catch (AbstractMethodError paramView)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("ViewParent ");
          localStringBuilder.append(paramViewParent);
          localStringBuilder.append(" does not implement interface ");
          localStringBuilder.append("method onStopNestedScroll");
          Log.e("ViewParentCompat", localStringBuilder.toString(), paramView);
          return;
        }
      }
      if ((paramViewParent instanceof NestedScrollingParent)) {
        ((NestedScrollingParent)paramViewParent).onStopNestedScroll(paramView);
      }
    }
  }
}

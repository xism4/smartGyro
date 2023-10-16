package com.org.android.view;

import a.a.c.g.D;
import android.os.Build.VERSION;
import android.view.WindowInsets;

public class Label
{
  private final Object b;
  
  private Label(Object paramObject)
  {
    b = paramObject;
  }
  
  static Label a(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    return new Label(paramObject);
  }
  
  static Object b(Label paramLabel)
  {
    if (paramLabel == null) {
      return null;
    }
    return b;
  }
  
  public int a()
  {
    if (Build.VERSION.SDK_INT >= 20) {
      return ((WindowInsets)b).getSystemWindowInsetBottom();
    }
    return 0;
  }
  
  public Label a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (Build.VERSION.SDK_INT >= 20) {
      return new Label(((WindowInsets)b).replaceSystemWindowInsets(paramInt1, paramInt2, paramInt3, paramInt4));
    }
    return null;
  }
  
  public int b()
  {
    if (Build.VERSION.SDK_INT >= 20) {
      return ((WindowInsets)b).getSystemWindowInsetLeft();
    }
    return 0;
  }
  
  public int c()
  {
    if (Build.VERSION.SDK_INT >= 20) {
      return ((WindowInsets)b).getSystemWindowInsetRight();
    }
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (paramObject != null)
    {
      if (D.class != paramObject.getClass()) {
        return false;
      }
      paramObject = (Label)paramObject;
      Object localObject = b;
      if (localObject == null) {
        return b == null;
      }
      return localObject.equals(b);
    }
    return false;
  }
  
  public int getColor()
  {
    if (Build.VERSION.SDK_INT >= 20) {
      return ((WindowInsets)b).getSystemWindowInsetTop();
    }
    return 0;
  }
  
  public int hashCode()
  {
    Object localObject = b;
    if (localObject == null) {
      return 0;
    }
    return localObject.hashCode();
  }
}

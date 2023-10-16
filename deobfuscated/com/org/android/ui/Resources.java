package com.org.android.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Process;
import android.util.TypedValue;

public class Resources
{
  private static final Object cache = new Object();
  private static TypedValue mTypedValue;
  
  public static int checkSelfPermission(Context paramContext, String paramString)
  {
    if (paramString != null) {
      return paramContext.checkPermission(paramString, Process.myPid(), Process.myUid());
    }
    throw new IllegalArgumentException("permission is null");
  }
  
  public static ColorStateList getColorStateList(Context paramContext, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 23) {
      return paramContext.getColorStateList(paramInt);
    }
    return paramContext.getResources().getColorStateList(paramInt);
  }
  
  public static Drawable getDrawable(Context paramContext, int paramInt)
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 21) {
      return paramContext.getDrawable(paramInt);
    }
    if (i >= 16) {}
    for (;;)
    {
      return paramContext.getResources().getDrawable(paramInt);
      Object localObject = cache;
      try
      {
        if (mTypedValue == null) {
          mTypedValue = new TypedValue();
        }
        paramContext.getResources().getValue(paramInt, mTypedValue, true);
        paramInt = mTypedValueresourceId;
      }
      catch (Throwable paramContext)
      {
        throw paramContext;
      }
    }
  }
}

package com.org.android.view;

import android.os.Build.VERSION;
import android.view.Gravity;

public final class View
{
  public static int getAbsoluteGravity(int paramInt1, int paramInt2)
  {
    if (Build.VERSION.SDK_INT >= 17) {
      return Gravity.getAbsoluteGravity(paramInt1, paramInt2);
    }
    return paramInt1 & 0xFF7FFFFF;
  }
}

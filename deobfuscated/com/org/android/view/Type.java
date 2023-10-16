package com.org.android.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.ViewConfiguration;
import java.lang.reflect.Method;

public final class Type
{
  private static Method b;
  
  static
  {
    if (Build.VERSION.SDK_INT == 25)
    {
      try
      {
        Method localMethod = ViewConfiguration.class.getDeclaredMethod("getScaledScrollFactor", new Class[0]);
        b = localMethod;
        return;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
      Log.i("ViewConfigCompat", "Could not find method getScaledScrollFactor() on ViewConfiguration");
      return;
    }
  }
  
  public static boolean a(ViewConfiguration paramViewConfiguration, Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 28) {
      return paramViewConfiguration.shouldShowMenuShortcutsWhenKeyboardPresent();
    }
    paramViewConfiguration = paramContext.getResources();
    int i = paramViewConfiguration.getIdentifier("config_showMenuShortcutsWhenKeyboardPresent", "bool", "android");
    return (i != 0) && (paramViewConfiguration.getBoolean(i));
  }
  
  public static int getSize(ViewConfiguration paramViewConfiguration)
  {
    if (Build.VERSION.SDK_INT >= 28) {
      return paramViewConfiguration.getScaledHoverSlop();
    }
    return paramViewConfiguration.getScaledTouchSlop() / 2;
  }
}

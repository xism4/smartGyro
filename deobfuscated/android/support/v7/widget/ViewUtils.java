package android.support.v7.widget;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import com.org.android.view.ViewCompat;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ViewUtils
{
  private static Method sComputeFitSystemWindowsMethod;
  
  static
  {
    if (Build.VERSION.SDK_INT >= 18)
    {
      try
      {
        Method localMethod = View.class.getDeclaredMethod("computeFitSystemWindows", new Class[] { Rect.class, Rect.class });
        sComputeFitSystemWindowsMethod = localMethod;
        localMethod = sComputeFitSystemWindowsMethod;
        boolean bool = localMethod.isAccessible();
        if (bool) {
          return;
        }
        localMethod = sComputeFitSystemWindowsMethod;
        localMethod.setAccessible(true);
        return;
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        for (;;) {}
      }
      Log.d("ViewUtils", "Could not find method computeFitSystemWindows. Oh well.");
      return;
    }
  }
  
  public static void computeFitSystemWindows(View paramView, Rect paramRect1, Rect paramRect2)
  {
    Method localMethod = sComputeFitSystemWindowsMethod;
    if (localMethod != null) {
      try
      {
        localMethod.invoke(paramView, new Object[] { paramRect1, paramRect2 });
        return;
      }
      catch (Exception paramView)
      {
        Log.d("ViewUtils", "Could not invoke computeFitSystemWindows", paramView);
      }
    }
  }
  
  public static boolean isLayoutRtl(View paramView)
  {
    return ViewCompat.getLayoutDirection(paramView) == 1;
  }
  
  public static void makeOptionalFitsSystemWindows(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      try
      {
        try
        {
          Object localObject = paramView.getClass();
          localObject = ((Class)localObject).getMethod("makeOptionalFitsSystemWindows", new Class[0]);
          boolean bool = ((Method)localObject).isAccessible();
          if (!bool) {
            ((Method)localObject).setAccessible(true);
          }
          ((Method)localObject).invoke(paramView, new Object[0]);
          return;
        }
        catch (IllegalAccessException paramView) {}catch (InvocationTargetException paramView) {}
        Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", paramView);
        return;
      }
      catch (NoSuchMethodException paramView)
      {
        for (;;) {}
      }
      Log.d("ViewUtils", "Could not find method makeOptionalFitsSystemWindows. Oh well...");
      return;
    }
  }
}

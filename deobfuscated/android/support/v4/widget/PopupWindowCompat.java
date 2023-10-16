package android.support.v4.widget;

import android.os.Build.VERSION;
import android.util.Log;
import android.widget.PopupWindow;
import com.org.android.view.ViewCompat;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class PopupWindowCompat
{
  private static boolean sCheckedField;
  private static Field sLayoutInflaterFactory2Field;
  private static Method sSetWindowLayoutTypeMethod;
  private static boolean sSetWindowLayoutTypeMethodAttempted;
  
  public static void init(PopupWindow paramPopupWindow, boolean paramBoolean)
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 23)
    {
      paramPopupWindow.setOverlapAnchor(paramBoolean);
      return;
    }
    if (i >= 21)
    {
      if (!sCheckedField)
      {
        try
        {
          Field localField1 = PopupWindow.class.getDeclaredField("mOverlapAnchor");
          sLayoutInflaterFactory2Field = localField1;
          localField1 = sLayoutInflaterFactory2Field;
          localField1.setAccessible(true);
        }
        catch (NoSuchFieldException localNoSuchFieldException)
        {
          Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", localNoSuchFieldException);
        }
        sCheckedField = true;
      }
      Field localField2 = sLayoutInflaterFactory2Field;
      if (localField2 != null) {
        try
        {
          localField2.set(paramPopupWindow, Boolean.valueOf(paramBoolean));
          return;
        }
        catch (IllegalAccessException paramPopupWindow)
        {
          Log.i("PopupWindowCompatApi21", "Could not set overlap anchor field in PopupWindow", paramPopupWindow);
        }
      }
    }
  }
  
  public static void setWindowLayoutType(PopupWindow paramPopupWindow, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 23)
    {
      paramPopupWindow.setWindowLayoutType(paramInt);
      return;
    }
    if (!sSetWindowLayoutTypeMethodAttempted) {
      localObject = Integer.TYPE;
    }
    try
    {
      localObject = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", new Class[] { localObject });
      sSetWindowLayoutTypeMethod = (Method)localObject;
      localObject = sSetWindowLayoutTypeMethod;
      ((Method)localObject).setAccessible(true);
    }
    catch (Exception localException)
    {
      for (;;)
      {
        try
        {
          ((Method)localObject).invoke(paramPopupWindow, new Object[] { Integer.valueOf(paramInt) });
          return;
        }
        catch (Exception paramPopupWindow) {}
        localException = localException;
      }
    }
    sSetWindowLayoutTypeMethodAttempted = true;
    Object localObject = sSetWindowLayoutTypeMethod;
    if (localObject != null) {}
  }
  
  public static void update(PopupWindow paramPopupWindow, android.view.View paramView, int paramInt1, int paramInt2, int paramInt3)
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      paramPopupWindow.showAsDropDown(paramView, paramInt1, paramInt2, paramInt3);
      return;
    }
    int i = paramInt1;
    if ((com.org.android.view.View.getAbsoluteGravity(paramInt3, ViewCompat.getLayoutDirection(paramView)) & 0x7) == 5) {
      i = paramInt1 - (paramPopupWindow.getWidth() - paramView.getWidth());
    }
    paramPopupWindow.showAsDropDown(paramView, i, paramInt2);
  }
}

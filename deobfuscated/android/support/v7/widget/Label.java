package android.support.v7.widget;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.v7.view.menu.MenuBuilder;
import android.transition.Transition;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

public class Label
  extends ListPopupWindow
  implements Object
{
  private static Method b;
  private Object d;
  
  static
  {
    java.lang.Object localObject = Boolean.TYPE;
    try
    {
      localObject = PopupWindow.class.getDeclaredMethod("setTouchModal", new Class[] { localObject });
      b = (Method)localObject;
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;) {}
    }
    Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
  }
  
  public Label(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
  }
  
  public void a(MenuBuilder paramMenuBuilder, MenuItem paramMenuItem)
  {
    Object localObject = d;
    if (localObject != null) {
      localObject.a(paramMenuBuilder, paramMenuItem);
    }
  }
  
  public void a(Object paramObject)
  {
    d = paramObject;
  }
  
  public void a(boolean paramBoolean)
  {
    Method localMethod = b;
    if (localMethod != null)
    {
      PopupWindow localPopupWindow = mPopup;
      try
      {
        localMethod.invoke(localPopupWindow, new java.lang.Object[] { Boolean.valueOf(paramBoolean) });
        return;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
      Log.i("MenuPopupWindow", "Could not invoke setTouchModal() on PopupWindow. Oh well.");
      return;
    }
  }
  
  public void b(MenuBuilder paramMenuBuilder, MenuItem paramMenuItem)
  {
    Object localObject = d;
    if (localObject != null) {
      localObject.b(paramMenuBuilder, paramMenuItem);
    }
  }
  
  public void init(java.lang.Object paramObject)
  {
    if (Build.VERSION.SDK_INT >= 23) {
      mPopup.setEnterTransition((Transition)paramObject);
    }
  }
  
  ListViewCompat show(Context paramContext, boolean paramBoolean)
  {
    paramContext = new ListPopupWindow.DropDownListView(paramContext, paramBoolean);
    paramContext.setHoverListener(this);
    return paramContext;
  }
  
  public void show(java.lang.Object paramObject)
  {
    if (Build.VERSION.SDK_INT >= 23) {
      mPopup.setExitTransition((Transition)paramObject);
    }
  }
}

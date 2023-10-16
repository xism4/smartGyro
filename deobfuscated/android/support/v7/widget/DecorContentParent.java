package android.support.v7.widget;

import android.support.v7.view.menu.l.a;
import android.view.Menu;
import android.view.Window.Callback;

public abstract interface DecorContentParent
{
  public abstract boolean canShowOverflowMenu();
  
  public abstract void dismissPopups();
  
  public abstract boolean hideOverflowMenu();
  
  public abstract void initFeature(int paramInt);
  
  public abstract boolean isOverflowMenuShowPending();
  
  public abstract boolean isOverflowMenuShowing();
  
  public abstract void setMenu(Menu paramMenu, l.a paramA);
  
  public abstract void setMenuPrepared();
  
  public abstract void setWindowCallback(Window.Callback paramCallback);
  
  public abstract void setWindowTitle(CharSequence paramCharSequence);
  
  public abstract boolean showOverflowMenu();
}

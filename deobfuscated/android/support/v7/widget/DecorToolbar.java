package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.l.a;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window.Callback;
import com.org.android.view.ViewPropertyAnimatorCompat;

public abstract interface DecorToolbar
{
  public abstract boolean canShowOverflowMenu();
  
  public abstract void collapseActionView();
  
  public abstract void dismissPopupMenus();
  
  public abstract Context getContext();
  
  public abstract int getDisplayOptions();
  
  public abstract int getNavigationMode();
  
  public abstract CharSequence getTitle();
  
  public abstract ViewGroup getViewGroup();
  
  public abstract boolean hasExpandedActionView();
  
  public abstract boolean hideOverflowMenu();
  
  public abstract void initIndeterminateProgress();
  
  public abstract void initProgress();
  
  public abstract boolean isOverflowMenuShowPending();
  
  public abstract boolean isOverflowMenuShowing();
  
  public abstract void setCollapsible(boolean paramBoolean);
  
  public abstract void setDisplayOptions(int paramInt);
  
  public abstract void setEmbeddedTabView(ScrollingTabContainerView paramScrollingTabContainerView);
  
  public abstract void setHomeButtonEnabled(boolean paramBoolean);
  
  public abstract void setIcon(int paramInt);
  
  public abstract void setIcon(Drawable paramDrawable);
  
  public abstract void setLogo(int paramInt);
  
  public abstract void setMenu(Menu paramMenu, l.a paramA);
  
  public abstract void setMenuPrepared();
  
  public abstract void setVisibility(int paramInt);
  
  public abstract void setWindowCallback(Window.Callback paramCallback);
  
  public abstract void setWindowTitle(CharSequence paramCharSequence);
  
  public abstract ViewPropertyAnimatorCompat setupAnimatorToVisibility(int paramInt, long paramLong);
  
  public abstract boolean showOverflowMenu();
}

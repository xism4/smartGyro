package android.support.v7.view.menu;

import android.content.Context;

public abstract interface MenuPresenter
{
  public abstract void a(MenuBuilder paramMenuBuilder, boolean paramBoolean);
  
  public abstract void a(l.a paramA);
  
  public abstract boolean a(SubMenuBuilder paramSubMenuBuilder);
  
  public abstract boolean collapseItemActionView(MenuBuilder paramMenuBuilder, MenuItemImpl paramMenuItemImpl);
  
  public abstract boolean expandItemActionView(MenuBuilder paramMenuBuilder, MenuItemImpl paramMenuItemImpl);
  
  public abstract boolean flagActionItems();
  
  public abstract void initForMenu(Context paramContext, MenuBuilder paramMenuBuilder);
  
  public abstract void updateMenuView(boolean paramBoolean);
}

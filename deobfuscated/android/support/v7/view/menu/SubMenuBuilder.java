package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public class SubMenuBuilder
  extends MenuBuilder
  implements SubMenu
{
  private MenuItemImpl mItem;
  private MenuBuilder mParentMenu;
  
  public SubMenuBuilder(Context paramContext, MenuBuilder paramMenuBuilder, MenuItemImpl paramMenuItemImpl)
  {
    super(paramContext);
    mParentMenu = paramMenuBuilder;
    mItem = paramMenuItemImpl;
  }
  
  public boolean collapseItemActionView(MenuItemImpl paramMenuItemImpl)
  {
    return mParentMenu.collapseItemActionView(paramMenuItemImpl);
  }
  
  boolean dispatchMenuItemSelected(MenuBuilder paramMenuBuilder, MenuItem paramMenuItem)
  {
    return (super.dispatchMenuItemSelected(paramMenuBuilder, paramMenuItem)) || (mParentMenu.dispatchMenuItemSelected(paramMenuBuilder, paramMenuItem));
  }
  
  public boolean expandItemActionView()
  {
    return mParentMenu.expandItemActionView();
  }
  
  public boolean expandItemActionView(MenuItemImpl paramMenuItemImpl)
  {
    return mParentMenu.expandItemActionView(paramMenuItemImpl);
  }
  
  public String getActionViewStatesKey()
  {
    Object localObject = mItem;
    int i;
    if (localObject != null) {
      i = ((MenuItemImpl)localObject).getItemId();
    } else {
      i = 0;
    }
    if (i == 0) {
      return null;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(super.getActionViewStatesKey());
    ((StringBuilder)localObject).append(":");
    ((StringBuilder)localObject).append(i);
    return ((StringBuilder)localObject).toString();
  }
  
  public MenuItem getItem()
  {
    return mItem;
  }
  
  public Menu getParentMenu()
  {
    return mParentMenu;
  }
  
  public MenuBuilder getRootMenu()
  {
    return mParentMenu.getRootMenu();
  }
  
  public boolean isQwertyMode()
  {
    return mParentMenu.isQwertyMode();
  }
  
  public boolean isShortcutsVisible()
  {
    return mParentMenu.isShortcutsVisible();
  }
  
  public void setCallback(MenuBuilder.Callback paramCallback)
  {
    mParentMenu.setCallback(paramCallback);
  }
  
  public void setGroupDividerEnabled(boolean paramBoolean)
  {
    mParentMenu.setGroupDividerEnabled(paramBoolean);
  }
  
  public SubMenu setHeaderIcon(int paramInt)
  {
    super.setHeaderIconInt(paramInt);
    return (SubMenu)this;
  }
  
  public SubMenu setHeaderIcon(Drawable paramDrawable)
  {
    super.setHeaderIconInt(paramDrawable);
    return (SubMenu)this;
  }
  
  public SubMenu setHeaderTitle(int paramInt)
  {
    super.setHeaderTitleInt(paramInt);
    return (SubMenu)this;
  }
  
  public SubMenu setHeaderTitle(CharSequence paramCharSequence)
  {
    super.setHeaderTitleInt(paramCharSequence);
    return (SubMenu)this;
  }
  
  public SubMenu setHeaderView(View paramView)
  {
    super.setHeaderViewInt(paramView);
    return (SubMenu)this;
  }
  
  public SubMenu setIcon(int paramInt)
  {
    mItem.setIcon(paramInt);
    return this;
  }
  
  public SubMenu setIcon(Drawable paramDrawable)
  {
    mItem.setIcon(paramDrawable);
    return this;
  }
  
  public void setQwertyMode(boolean paramBoolean)
  {
    mParentMenu.setQwertyMode(paramBoolean);
  }
}

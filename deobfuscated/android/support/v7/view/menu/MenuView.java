package android.support.v7.view.menu;

public abstract interface MenuView
{
  public abstract void initialize(MenuBuilder paramMenuBuilder);
  
  public abstract interface ItemView
  {
    public abstract MenuItemImpl getItemData();
    
    public abstract void initialize(MenuItemImpl paramMenuItemImpl, int paramInt);
    
    public abstract boolean prefersCondensedTitle();
  }
}

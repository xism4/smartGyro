package android.support.v7.view.menu;

import a.a.c.c.a.b;
import a.a.c.c.a.c;
import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import com.org.android.impl.cookie.SupportMenuItem;
import com.org.android.impl.cookie.SupportSubMenu;
import com.org.android.util.ArrayMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

abstract class BaseMenuWrapper<T>
  extends d<T>
{
  final Context mContext;
  private Map<b, MenuItem> mMenuItems;
  private Map<c, SubMenu> mSubMenus;
  
  BaseMenuWrapper(Context paramContext, Object paramObject)
  {
    super(paramObject);
    mContext = paramContext;
  }
  
  final MenuItem getMenuItemWrapper(MenuItem paramMenuItem)
  {
    MenuItem localMenuItem = paramMenuItem;
    if ((paramMenuItem instanceof SupportMenuItem))
    {
      SupportMenuItem localSupportMenuItem = (SupportMenuItem)paramMenuItem;
      if (mMenuItems == null) {
        mMenuItems = new ArrayMap();
      }
      paramMenuItem = (MenuItem)mMenuItems.get(paramMenuItem);
      localMenuItem = paramMenuItem;
      if (paramMenuItem == null)
      {
        localMenuItem = MenuWrapperFactory.wrapSupportMenuItem(mContext, localSupportMenuItem);
        mMenuItems.put(localSupportMenuItem, localMenuItem);
      }
    }
    return localMenuItem;
  }
  
  final SubMenu getSubMenuWrapper(SubMenu paramSubMenu)
  {
    SubMenu localSubMenu = paramSubMenu;
    if ((paramSubMenu instanceof SupportSubMenu))
    {
      SupportSubMenu localSupportSubMenu = (SupportSubMenu)paramSubMenu;
      if (mSubMenus == null) {
        mSubMenus = new ArrayMap();
      }
      paramSubMenu = (SubMenu)mSubMenus.get(localSupportSubMenu);
      localSubMenu = paramSubMenu;
      if (paramSubMenu == null)
      {
        localSubMenu = MenuWrapperFactory.wrapSupportSubMenu(mContext, localSupportSubMenu);
        mSubMenus.put(localSupportSubMenu, localSubMenu);
      }
    }
    return localSubMenu;
  }
  
  final void internalClear()
  {
    Map localMap = mMenuItems;
    if (localMap != null) {
      localMap.clear();
    }
    localMap = mSubMenus;
    if (localMap != null) {
      localMap.clear();
    }
  }
  
  final void internalRemoveGroup(int paramInt)
  {
    Object localObject = mMenuItems;
    if (localObject == null) {
      return;
    }
    localObject = ((Map)localObject).keySet().iterator();
    while (((Iterator)localObject).hasNext()) {
      if (paramInt == ((MenuItem)((Iterator)localObject).next()).getGroupId()) {
        ((Iterator)localObject).remove();
      }
    }
  }
  
  final void internalRemoveItem(int paramInt)
  {
    Object localObject = mMenuItems;
    if (localObject == null) {
      return;
    }
    localObject = ((Map)localObject).keySet().iterator();
    while (((Iterator)localObject).hasNext()) {
      if (paramInt == ((MenuItem)((Iterator)localObject).next()).getItemId()) {
        ((Iterator)localObject).remove();
      }
    }
  }
}

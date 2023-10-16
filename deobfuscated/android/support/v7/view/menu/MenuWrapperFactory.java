package android.support.v7.view.menu;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import com.org.android.impl.cookie.SupportMenu;
import com.org.android.impl.cookie.SupportMenuItem;
import com.org.android.impl.cookie.SupportSubMenu;

public final class MenuWrapperFactory
{
  public static Menu wrapSupportMenu(Context paramContext, SupportMenu paramSupportMenu)
  {
    return new MenuWrapperICS(paramContext, paramSupportMenu);
  }
  
  public static MenuItem wrapSupportMenuItem(Context paramContext, SupportMenuItem paramSupportMenuItem)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return new MenuItemWrapperJB(paramContext, paramSupportMenuItem);
    }
    return new MenuItemWrapperICS(paramContext, paramSupportMenuItem);
  }
  
  public static SubMenu wrapSupportSubMenu(Context paramContext, SupportSubMenu paramSupportSubMenu)
  {
    return new SubMenuWrapperICS(paramContext, paramSupportSubMenu);
  }
}

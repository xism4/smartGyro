package android.support.v7.view.menu;

import android.content.Context;
import android.os.Build;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import com.org.android.impl.cookie.SupportMenu;
import com.org.android.impl.cookie.SupportMenuItem;
import com.org.android.impl.cookie.SupportSubMenu;

public final class MenuWrapperFactory {
    public static Menu wrapSupportMenu(Context context, SupportMenu supportMenu) {
        return new MenuWrapperICS(context, supportMenu);
    }

    public static MenuItem wrapSupportMenuItem(Context context, SupportMenuItem supportMenuItem) {
        return Build.VERSION.SDK_INT >= 16 ? new MenuItemWrapperJB(context, supportMenuItem) : new MenuItemWrapperICS(context, supportMenuItem);
    }

    public static SubMenu wrapSupportSubMenu(Context context, SupportSubMenu supportSubMenu) {
        return new SubMenuWrapperICS(context, supportSubMenu);
    }
}

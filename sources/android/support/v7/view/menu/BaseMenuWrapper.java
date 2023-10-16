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

abstract class BaseMenuWrapper<T> extends d<T> {
    final Context mContext;
    private Map<b, MenuItem> mMenuItems;
    private Map<c, SubMenu> mSubMenus;

    BaseMenuWrapper(Context context, Object obj) {
        super(obj);
        this.mContext = context;
    }

    /* access modifiers changed from: package-private */
    public final MenuItem getMenuItemWrapper(MenuItem $r1) {
        if (!($r1 instanceof SupportMenuItem)) {
            return $r1;
        }
        SupportMenuItem $r2 = (SupportMenuItem) $r1;
        if (this.mMenuItems == null) {
            this.mMenuItems = new ArrayMap();
        }
        MenuItem $r12 = this.mMenuItems.get($r1);
        if ($r12 != null) {
            return $r12;
        }
        MenuItem $r13 = MenuWrapperFactory.wrapSupportMenuItem(this.mContext, $r2);
        this.mMenuItems.put($r2, $r13);
        return $r13;
    }

    /* access modifiers changed from: package-private */
    public final SubMenu getSubMenuWrapper(SubMenu $r2) {
        if (!($r2 instanceof SupportSubMenu)) {
            return $r2;
        }
        SupportSubMenu $r3 = (SupportSubMenu) $r2;
        if (this.mSubMenus == null) {
            this.mSubMenus = new ArrayMap();
        }
        SubMenu $r22 = this.mSubMenus.get($r3);
        if ($r22 != null) {
            return $r22;
        }
        SubMenu $r23 = MenuWrapperFactory.wrapSupportSubMenu(this.mContext, $r3);
        this.mSubMenus.put($r3, $r23);
        return $r23;
    }

    /* access modifiers changed from: package-private */
    public final void internalClear() {
        Map $r1 = this.mMenuItems;
        if ($r1 != null) {
            $r1.clear();
        }
        Map $r12 = this.mSubMenus;
        if ($r12 != null) {
            $r12.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public final void internalRemoveGroup(int i) {
        Map $r1 = this.mMenuItems;
        if ($r1 != null) {
            Iterator $r3 = $r1.keySet().iterator();
            while ($r3.hasNext()) {
                if (i == $r3.next().getGroupId()) {
                    $r3.remove();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void internalRemoveItem(int i) {
        Map $r1 = this.mMenuItems;
        if ($r1 != null) {
            Iterator $r3 = $r1.keySet().iterator();
            while ($r3.hasNext()) {
                if (i == $r3.next().getItemId()) {
                    $r3.remove();
                    return;
                }
            }
        }
    }
}

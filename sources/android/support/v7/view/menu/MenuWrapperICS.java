package android.support.v7.view.menu;

import a.a.c.c.a.a;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import com.org.android.impl.cookie.SupportMenu;

class MenuWrapperICS extends c<a> implements Menu {
    MenuWrapperICS(Context context, SupportMenu supportMenu) {
        super(context, supportMenu);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuWrapperICS, android.support.v7.view.menu.BaseMenuWrapper, android.support.v7.view.menu.BaseWrapper] */
    public MenuItem add(int i) {
        return getMenuItemWrapper(((SupportMenu) this.mWrappedObject).add(i));
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuWrapperICS, android.support.v7.view.menu.BaseMenuWrapper, android.support.v7.view.menu.BaseWrapper] */
    public MenuItem add(int i, int i2, int i3, int i4) {
        return getMenuItemWrapper(((SupportMenu) this.mWrappedObject).add(i, i2, i3, i4));
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuWrapperICS, android.support.v7.view.menu.BaseMenuWrapper, android.support.v7.view.menu.BaseWrapper] */
    public MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return getMenuItemWrapper(((SupportMenu) this.mWrappedObject).add(i, i2, i3, charSequence));
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuWrapperICS, android.support.v7.view.menu.BaseMenuWrapper, android.support.v7.view.menu.BaseWrapper] */
    public MenuItem add(CharSequence charSequence) {
        return getMenuItemWrapper(((SupportMenu) this.mWrappedObject).add(charSequence));
    }

    /* JADX WARNING: type inference failed for: r15v0, types: [android.support.v7.view.menu.MenuWrapperICS, android.support.v7.view.menu.BaseMenuWrapper, android.support.v7.view.menu.BaseWrapper] */
    public int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        MenuItem[] $r5 = menuItemArr != null ? new MenuItem[menuItemArr.length] : null;
        int $i0 = ((SupportMenu) this.mWrappedObject).addIntentOptions(i, i2, i3, componentName, intentArr, intent, i4, $r5);
        if ($r5 != null) {
            int $i2 = $r5.length;
            for (int $i22 = 0; $i22 < $i2; $i22++) {
                menuItemArr[$i22] = getMenuItemWrapper($r5[$i22]);
            }
        }
        return $i0;
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuWrapperICS, android.support.v7.view.menu.BaseMenuWrapper, android.support.v7.view.menu.BaseWrapper] */
    public SubMenu addSubMenu(int i) {
        return getSubMenuWrapper(((SupportMenu) this.mWrappedObject).addSubMenu(i));
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuWrapperICS, android.support.v7.view.menu.BaseMenuWrapper, android.support.v7.view.menu.BaseWrapper] */
    public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return getSubMenuWrapper(((SupportMenu) this.mWrappedObject).addSubMenu(i, i2, i3, i4));
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuWrapperICS, android.support.v7.view.menu.BaseMenuWrapper, android.support.v7.view.menu.BaseWrapper] */
    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        return getSubMenuWrapper(((SupportMenu) this.mWrappedObject).addSubMenu(i, i2, i3, charSequence));
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuWrapperICS, android.support.v7.view.menu.BaseMenuWrapper, android.support.v7.view.menu.BaseWrapper] */
    public SubMenu addSubMenu(CharSequence charSequence) {
        return getSubMenuWrapper(((SupportMenu) this.mWrappedObject).addSubMenu(charSequence));
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuWrapperICS, android.support.v7.view.menu.BaseMenuWrapper, android.support.v7.view.menu.BaseWrapper] */
    public void clear() {
        internalClear();
        ((SupportMenu) this.mWrappedObject).clear();
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public void close() {
        ((SupportMenu) this.mWrappedObject).close();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuWrapperICS, android.support.v7.view.menu.BaseMenuWrapper, android.support.v7.view.menu.BaseWrapper] */
    public MenuItem findItem(int i) {
        return getMenuItemWrapper(((SupportMenu) this.mWrappedObject).findItem(i));
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuWrapperICS, android.support.v7.view.menu.BaseMenuWrapper, android.support.v7.view.menu.BaseWrapper] */
    public MenuItem getItem(int i) {
        return getMenuItemWrapper(((SupportMenu) this.mWrappedObject).getItem(i));
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public boolean hasVisibleItems() {
        return ((SupportMenu) this.mWrappedObject).hasVisibleItems();
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return ((SupportMenu) this.mWrappedObject).isShortcutKey(i, keyEvent);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public boolean performIdentifierAction(int i, int i2) {
        return ((SupportMenu) this.mWrappedObject).performIdentifierAction(i, i2);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        return ((SupportMenu) this.mWrappedObject).performShortcut(i, keyEvent, i2);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuWrapperICS, android.support.v7.view.menu.BaseMenuWrapper, android.support.v7.view.menu.BaseWrapper] */
    public void removeGroup(int i) {
        internalRemoveGroup(i);
        ((SupportMenu) this.mWrappedObject).removeGroup(i);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuWrapperICS, android.support.v7.view.menu.BaseMenuWrapper, android.support.v7.view.menu.BaseWrapper] */
    public void removeItem(int i) {
        internalRemoveItem(i);
        ((SupportMenu) this.mWrappedObject).removeItem(i);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public void setGroupCheckable(int i, boolean z, boolean z2) {
        ((SupportMenu) this.mWrappedObject).setGroupCheckable(i, z, z2);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public void setGroupEnabled(int i, boolean z) {
        ((SupportMenu) this.mWrappedObject).setGroupEnabled(i, z);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public void setGroupVisible(int i, boolean z) {
        ((SupportMenu) this.mWrappedObject).setGroupVisible(i, z);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.MenuWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public void setQwertyMode(boolean z) {
        ((SupportMenu) this.mWrappedObject).setQwertyMode(z);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.support.v7.view.menu.MenuWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public int size() {
        return ((SupportMenu) this.mWrappedObject).size();
    }
}

package android.support.p025v7.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import p000a.p001a.p005c.p009c.p010a.C0056a;

/* renamed from: android.support.v7.view.menu.y */
class C0315y extends C0282c<C0056a> implements Menu {
    C0315y(Context context, C0056a aVar) {
        super(context, aVar);
    }

    public MenuItem add(int i) {
        return mo1234a(((C0056a) this.f939a).add(i));
    }

    public MenuItem add(int i, int i2, int i3, int i4) {
        return mo1234a(((C0056a) this.f939a).add(i, i2, i3, i4));
    }

    public MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return mo1234a(((C0056a) this.f939a).add(i, i2, i3, charSequence));
    }

    public MenuItem add(CharSequence charSequence) {
        return mo1234a(((C0056a) this.f939a).add(charSequence));
    }

    public int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        MenuItem[] menuItemArr2 = menuItemArr;
        MenuItem[] menuItemArr3 = menuItemArr2 != null ? new MenuItem[menuItemArr2.length] : null;
        int addIntentOptions = ((C0056a) this.f939a).addIntentOptions(i, i2, i3, componentName, intentArr, intent, i4, menuItemArr3);
        if (menuItemArr3 != null) {
            int length = menuItemArr3.length;
            for (int i5 = 0; i5 < length; i5++) {
                menuItemArr2[i5] = mo1234a(menuItemArr3[i5]);
            }
        }
        return addIntentOptions;
    }

    public SubMenu addSubMenu(int i) {
        return mo1235a(((C0056a) this.f939a).addSubMenu(i));
    }

    public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return mo1235a(((C0056a) this.f939a).addSubMenu(i, i2, i3, i4));
    }

    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        return mo1235a(((C0056a) this.f939a).addSubMenu(i, i2, i3, charSequence));
    }

    public SubMenu addSubMenu(CharSequence charSequence) {
        return mo1235a(((C0056a) this.f939a).addSubMenu(charSequence));
    }

    public void clear() {
        mo1237b();
        ((C0056a) this.f939a).clear();
    }

    public void close() {
        ((C0056a) this.f939a).close();
    }

    public MenuItem findItem(int i) {
        return mo1234a(((C0056a) this.f939a).findItem(i));
    }

    public MenuItem getItem(int i) {
        return mo1234a(((C0056a) this.f939a).getItem(i));
    }

    public boolean hasVisibleItems() {
        return ((C0056a) this.f939a).hasVisibleItems();
    }

    public boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return ((C0056a) this.f939a).isShortcutKey(i, keyEvent);
    }

    public boolean performIdentifierAction(int i, int i2) {
        return ((C0056a) this.f939a).performIdentifierAction(i, i2);
    }

    public boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        return ((C0056a) this.f939a).performShortcut(i, keyEvent, i2);
    }

    public void removeGroup(int i) {
        mo1236a(i);
        ((C0056a) this.f939a).removeGroup(i);
    }

    public void removeItem(int i) {
        mo1238b(i);
        ((C0056a) this.f939a).removeItem(i);
    }

    public void setGroupCheckable(int i, boolean z, boolean z2) {
        ((C0056a) this.f939a).setGroupCheckable(i, z, z2);
    }

    public void setGroupEnabled(int i, boolean z) {
        ((C0056a) this.f939a).setGroupEnabled(i, z);
    }

    public void setGroupVisible(int i, boolean z) {
        ((C0056a) this.f939a).setGroupVisible(i, z);
    }

    public void setQwertyMode(boolean z) {
        ((C0056a) this.f939a).setQwertyMode(z);
    }

    public int size() {
        return ((C0056a) this.f939a).size();
    }
}

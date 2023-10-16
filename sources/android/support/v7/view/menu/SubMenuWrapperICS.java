package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import com.org.android.impl.cookie.SupportSubMenu;

class SubMenuWrapperICS extends MenuWrapperICS implements SubMenu {
    SubMenuWrapperICS(Context context, SupportSubMenu supportSubMenu) {
        super(context, supportSubMenu);
    }

    public void clearHeader() {
        getWrappedObject().clearHeader();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.support.v7.view.menu.SubMenuWrapperICS, android.support.v7.view.menu.BaseMenuWrapper] */
    public MenuItem getItem() {
        return getMenuItemWrapper(getWrappedObject().getItem());
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.support.v7.view.menu.SubMenuWrapperICS, android.support.v7.view.menu.BaseWrapper] */
    public SupportSubMenu getWrappedObject() {
        return (SupportSubMenu) this.mWrappedObject;
    }

    public SubMenu setHeaderIcon(int i) {
        getWrappedObject().setHeaderIcon(i);
        return this;
    }

    public SubMenu setHeaderIcon(Drawable drawable) {
        getWrappedObject().setHeaderIcon(drawable);
        return this;
    }

    public SubMenu setHeaderTitle(int i) {
        getWrappedObject().setHeaderTitle(i);
        return this;
    }

    public SubMenu setHeaderTitle(CharSequence charSequence) {
        getWrappedObject().setHeaderTitle(charSequence);
        return this;
    }

    public SubMenu setHeaderView(View view) {
        getWrappedObject().setHeaderView(view);
        return this;
    }

    public SubMenu setIcon(int i) {
        getWrappedObject().setIcon(i);
        return this;
    }

    public SubMenu setIcon(Drawable drawable) {
        getWrappedObject().setIcon(drawable);
        return this;
    }
}

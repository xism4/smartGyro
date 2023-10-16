package android.support.p025v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import p000a.p001a.p005c.p009c.p010a.C0058c;

/* renamed from: android.support.v7.view.menu.E */
class C0279E extends C0315y implements SubMenu {
    C0279E(Context context, C0058c cVar) {
        super(context, cVar);
    }

    /* renamed from: c */
    public C0058c mo1164c() {
        return (C0058c) this.f939a;
    }

    public void clearHeader() {
        mo1164c().clearHeader();
    }

    public MenuItem getItem() {
        return mo1234a(mo1164c().getItem());
    }

    public SubMenu setHeaderIcon(int i) {
        mo1164c().setHeaderIcon(i);
        return this;
    }

    public SubMenu setHeaderIcon(Drawable drawable) {
        mo1164c().setHeaderIcon(drawable);
        return this;
    }

    public SubMenu setHeaderTitle(int i) {
        mo1164c().setHeaderTitle(i);
        return this;
    }

    public SubMenu setHeaderTitle(CharSequence charSequence) {
        mo1164c().setHeaderTitle(charSequence);
        return this;
    }

    public SubMenu setHeaderView(View view) {
        mo1164c().setHeaderView(view);
        return this;
    }

    public SubMenu setIcon(int i) {
        mo1164c().setIcon(i);
        return this;
    }

    public SubMenu setIcon(Drawable drawable) {
        mo1164c().setIcon(drawable);
        return this;
    }
}

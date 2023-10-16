package android.support.v7.view.menu;

import android.content.Context;

public interface MenuPresenter {
    void a(MenuBuilder menuBuilder, boolean z);

    void a(l$a l_a);

    boolean a(SubMenuBuilder subMenuBuilder);

    boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl);

    boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl);

    boolean flagActionItems();

    void initForMenu(Context context, MenuBuilder menuBuilder);

    void updateMenuView(boolean z);
}

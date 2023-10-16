package android.support.v7.view.menu;

import android.content.Context;
import android.support.v7.view.menu.MenuView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public abstract class b implements MenuPresenter {
    private l$a d;
    protected Context mContext;
    private int mId;
    protected LayoutInflater mInflater;
    private int mItemLayoutRes;
    protected MenuBuilder mMenu;
    private int mMenuLayoutRes;
    protected MenuView mMenuView;
    protected Context mSystemContext;
    protected LayoutInflater mSystemInflater;

    public b(Context context, int i, int i2) {
        this.mSystemContext = context;
        this.mSystemInflater = LayoutInflater.from(context);
        this.mMenuLayoutRes = i;
        this.mItemLayoutRes = i2;
    }

    public void a(MenuBuilder menuBuilder, boolean z) {
        l$a $r2 = this.d;
        if ($r2 != null) {
            $r2.onCloseMenu(menuBuilder, z);
        }
    }

    public void a(l$a l_a) {
        this.d = l_a;
    }

    public boolean a(SubMenuBuilder subMenuBuilder) {
        l$a $r1 = this.d;
        if ($r1 != null) {
            return $r1.onOpenSubMenu(subMenuBuilder);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void addItemView(View view, int i) {
        ViewGroup $r3 = (ViewGroup) view.getParent();
        if ($r3 != null) {
            $r3.removeView(view);
        }
        ((ViewGroup) this.mMenuView).addView(view, i);
    }

    public abstract void bindItemView(MenuItemImpl menuItemImpl, MenuView.ItemView itemView);

    public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public MenuView.ItemView createItemView(ViewGroup viewGroup) {
        return (MenuView.ItemView) this.mSystemInflater.inflate(this.mItemLayoutRes, viewGroup, false);
    }

    public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean filterLeftoverView(ViewGroup viewGroup, int i) {
        viewGroup.removeViewAt(i);
        return true;
    }

    public l$a getCallback() {
        return this.d;
    }

    public View getItemView(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        MenuView.ItemView $r3 = view instanceof MenuView.ItemView ? (MenuView.ItemView) view : createItemView(viewGroup);
        bindItemView(menuItemImpl, $r3);
        return (View) $r3;
    }

    public MenuView getMenuView(ViewGroup viewGroup) {
        if (this.mMenuView == null) {
            this.mMenuView = (MenuView) this.mSystemInflater.inflate(this.mMenuLayoutRes, viewGroup, false);
            this.mMenuView.initialize(this.mMenu);
            updateMenuView(true);
        }
        return this.mMenuView;
    }

    public void initForMenu(Context context, MenuBuilder menuBuilder) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(this.mContext);
        this.mMenu = menuBuilder;
    }

    public void setId(int i) {
        this.mId = i;
    }

    public abstract boolean shouldIncludeItem(int i, MenuItemImpl menuItemImpl);

    public void updateMenuView(boolean z) {
        ViewGroup $r2 = (ViewGroup) this.mMenuView;
        if ($r2 != null) {
            MenuBuilder $r3 = this.mMenu;
            int $i0 = 0;
            if ($r3 != null) {
                $r3.flagActionItems();
                ArrayList $r4 = this.mMenu.getVisibleItems();
                int $i02 = $r4.size();
                int $i2 = 0;
                for (int $i1 = 0; $i1 < $i02; $i1++) {
                    MenuItemImpl $r6 = (MenuItemImpl) $r4.get($i1);
                    if (shouldIncludeItem($i2, $r6)) {
                        View $r7 = $r2.getChildAt($i2);
                        MenuItemImpl $r9 = $r7 instanceof MenuView.ItemView ? ((MenuView.ItemView) $r7).getItemData() : null;
                        View $r10 = getItemView($r6, $r7, $r2);
                        if ($r6 != $r9) {
                            $r10.setPressed(false);
                            $r10.jumpDrawablesToCurrentState();
                        }
                        if ($r10 != $r7) {
                            addItemView($r10, $i2);
                        }
                        $i2++;
                    }
                }
                $i0 = $i2;
            }
            while ($i0 < $r2.getChildCount()) {
                if (!filterLeftoverView($r2, $i0)) {
                    $i0++;
                }
            }
        }
    }
}

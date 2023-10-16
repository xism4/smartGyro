package android.support.v7.view.menu;

import android.content.Context;
import android.os.IBinder;
import android.support.v7.view.menu.MenuView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import com.org.v4.util.R$layout;
import java.util.ArrayList;

public class ListMenuPresenter implements MenuPresenter, AdapterView.OnItemClickListener {
    private l$a d;
    MenuAdapter mAdapter;
    Context mContext;
    int mExpandedIndex;
    LayoutInflater mInflater;
    int mItemLayoutRes;
    MenuBuilder mMenu;
    ExpandedMenuView mMenuView;
    int mThemeRes;

    class MenuAdapter extends BaseAdapter {
        private int mExpandedIndex = -1;

        public MenuAdapter() {
            findExpandedIndex();
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: android.support.v7.view.menu.MenuItemImpl} */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void findExpandedIndex() {
            /*
                r10 = this;
                android.support.v7.view.menu.ListMenuPresenter r0 = android.support.v7.view.menu.ListMenuPresenter.this
                android.support.v7.view.menu.MenuBuilder r1 = r0.mMenu
                android.support.v7.view.menu.MenuItemImpl r2 = r1.getExpandedItem()
                if (r2 == 0) goto L_0x0029
                android.support.v7.view.menu.ListMenuPresenter r0 = android.support.v7.view.menu.ListMenuPresenter.this
                android.support.v7.view.menu.MenuBuilder r1 = r0.mMenu
                java.util.ArrayList r3 = r1.getNonActionItems()
                int r4 = r3.size()
                r5 = 0
            L_0x0017:
                if (r5 >= r4) goto L_0x0029
                java.lang.Object r6 = r3.get(r5)
                r8 = r6
                android.support.v7.view.menu.MenuItemImpl r8 = (android.support.v7.view.menu.MenuItemImpl) r8
                r7 = r8
                if (r7 != r2) goto L_0x0026
                r10.mExpandedIndex = r5
                return
            L_0x0026:
                int r5 = r5 + 1
                goto L_0x0017
            L_0x0029:
                r9 = -1
                r10.mExpandedIndex = r9
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.view.menu.ListMenuPresenter.MenuAdapter.findExpandedIndex():void");
        }

        public int getCount() {
            int $i0 = ListMenuPresenter.this.mMenu.getNonActionItems().size() - ListMenuPresenter.this.mExpandedIndex;
            return this.mExpandedIndex < 0 ? $i0 : $i0 - 1;
        }

        public MenuItemImpl getItem(int i) {
            ArrayList $r3 = ListMenuPresenter.this.mMenu.getNonActionItems();
            int $i0 = i + ListMenuPresenter.this.mExpandedIndex;
            int $i1 = this.mExpandedIndex;
            if ($i1 >= 0 && $i0 >= $i1) {
                $i0++;
            }
            return (MenuItemImpl) $r3.get($i0);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View $r2, ViewGroup viewGroup) {
            if ($r2 == null) {
                ListMenuPresenter $r4 = ListMenuPresenter.this;
                $r2 = $r4.mInflater.inflate($r4.mItemLayoutRes, viewGroup, false);
            }
            ((MenuView.ItemView) $r2).initialize(getItem(i), 0);
            return $r2;
        }

        public void notifyDataSetChanged() {
            findExpandedIndex();
            super.notifyDataSetChanged();
        }
    }

    public ListMenuPresenter(int i, int i2) {
        this.mItemLayoutRes = i;
        this.mThemeRes = i2;
    }

    public ListMenuPresenter(Context context, int i) {
        this(i, 0);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(this.mContext);
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
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        new g(subMenuBuilder).a((IBinder) null);
        l$a $r3 = this.d;
        if ($r3 == null) {
            return true;
        }
        $r3.onOpenSubMenu(subMenuBuilder);
        return true;
    }

    public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean flagActionItems() {
        return false;
    }

    public ListAdapter getAdapter() {
        if (this.mAdapter == null) {
            this.mAdapter = new MenuAdapter();
        }
        return this.mAdapter;
    }

    public MenuView getMenuView(ViewGroup viewGroup) {
        if (this.mMenuView == null) {
            this.mMenuView = (ExpandedMenuView) this.mInflater.inflate(R$layout.abc_expanded_menu_layout, viewGroup, false);
            if (this.mAdapter == null) {
                this.mAdapter = new MenuAdapter();
            }
            this.mMenuView.setAdapter(this.mAdapter);
            this.mMenuView.setOnItemClickListener(this);
        }
        return this.mMenuView;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001c, code lost:
        if (r5.mInflater == null) goto L_0x000b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initForMenu(android.content.Context r6, android.support.v7.view.menu.MenuBuilder r7) {
        /*
            r5 = this;
            int r0 = r5.mThemeRes
            if (r0 == 0) goto L_0x0014
            android.view.ContextThemeWrapper r1 = new android.view.ContextThemeWrapper
            r1.<init>(r6, r0)
            r5.mContext = r1
        L_0x000b:
            android.content.Context r6 = r5.mContext
            android.view.LayoutInflater r2 = android.view.LayoutInflater.from(r6)
            r5.mInflater = r2
            goto L_0x001f
        L_0x0014:
            android.content.Context r3 = r5.mContext
            if (r3 == 0) goto L_0x001f
            r5.mContext = r6
            android.view.LayoutInflater r2 = r5.mInflater
            if (r2 != 0) goto L_0x001f
            goto L_0x000b
        L_0x001f:
            r5.mMenu = r7
            android.support.v7.view.menu.ListMenuPresenter$MenuAdapter r4 = r5.mAdapter
            if (r4 == 0) goto L_0x0028
            r4.notifyDataSetChanged()
        L_0x0028:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.view.menu.ListMenuPresenter.initForMenu(android.content.Context, android.support.v7.view.menu.MenuBuilder):void");
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.mMenu.performItemAction(this.mAdapter.getItem(i), this, 0);
    }

    public void updateMenuView(boolean z) {
        MenuAdapter $r1 = this.mAdapter;
        if ($r1 != null) {
            $r1.notifyDataSetChanged();
        }
    }
}

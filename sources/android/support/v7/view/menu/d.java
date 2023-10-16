package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.view.menu.e;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

abstract class d implements ListPopupWindow, MenuPresenter, AdapterView.OnItemClickListener {
    private Rect a;

    d() {
    }

    protected static e.a a(ListAdapter $r0) {
        return $r0 instanceof HeaderViewListAdapter ? (e.a) ((HeaderViewListAdapter) $r0).getWrappedAdapter() : (e.a) $r0;
    }

    protected static int measureContentWidth(ListAdapter listAdapter, ViewGroup $r2, Context context, int i) {
        int $i2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int $i3 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int $i4 = listAdapter.getCount();
        View $r3 = null;
        int $i5 = 0;
        int $i6 = 0;
        for (int $i1 = 0; $i1 < $i4; $i1++) {
            int $i7 = listAdapter.getItemViewType($i1);
            if ($i7 != $i6) {
                $r3 = null;
                $i6 = $i7;
            }
            if ($r2 == null) {
                $r2 = r5;
                FrameLayout r5 = new FrameLayout(context);
            }
            View $r4 = listAdapter.getView($i1, $r3, $r2);
            $r3 = $r4;
            $r4.measure($i2, $i3);
            int $i72 = $r4.getMeasuredWidth();
            if ($i72 >= i) {
                return i;
            }
            if ($i72 > $i5) {
                $i5 = $i72;
            }
        }
        return $i5;
    }

    protected static boolean onSubMenuSelected(MenuBuilder menuBuilder) {
        int $i0 = menuBuilder.size();
        for (int $i1 = 0; $i1 < $i0; $i1++) {
            MenuItem $r1 = menuBuilder.getItem($i1);
            if ($r1.isVisible() && $r1.getIcon() != null) {
                return true;
            }
        }
        return false;
    }

    public abstract void a(int i);

    public abstract void a(View view);

    public abstract void a(PopupWindow.OnDismissListener onDismissListener);

    public abstract void a(boolean z);

    public abstract void add(MenuBuilder menuBuilder);

    public abstract void b(int i);

    public void b(Rect rect) {
        this.a = rect;
    }

    public abstract void b(boolean z);

    public Rect c() {
        return this.a;
    }

    public abstract void c(int i);

    public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean g() {
        return true;
    }

    public void initForMenu(Context context, MenuBuilder menuBuilder) {
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        ListAdapter $r4 = (ListAdapter) adapterView.getAdapter();
        a($r4).c.performItemAction((MenuItem) $r4.getItem(i), this, g() ? (byte) 0 : 4);
    }
}

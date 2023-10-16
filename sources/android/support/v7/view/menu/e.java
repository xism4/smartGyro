package android.support.v7.view.menu;

import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.Label;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import java.util.ArrayList;

class e {
    public final MenuBuilder c;
    public final int d;
    public final Label this$0;

    public class a extends BaseAdapter {
        private boolean a;
        private final LayoutInflater b;
        MenuBuilder c;
        private final int d;
        private final boolean l;
        private int n = -1;

        public a(MenuBuilder menuBuilder, LayoutInflater layoutInflater, boolean z, int i) {
            this.l = z;
            this.b = layoutInflater;
            this.c = menuBuilder;
            this.d = i;
            a();
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: android.support.v7.view.menu.MenuItemImpl} */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a() {
            /*
                r9 = this;
                android.support.v7.view.menu.MenuBuilder r0 = r9.c
                android.support.v7.view.menu.MenuItemImpl r1 = r0.getExpandedItem()
                if (r1 == 0) goto L_0x0025
                android.support.v7.view.menu.MenuBuilder r0 = r9.c
                java.util.ArrayList r2 = r0.getNonActionItems()
                int r3 = r2.size()
                r4 = 0
            L_0x0013:
                if (r4 >= r3) goto L_0x0025
                java.lang.Object r5 = r2.get(r4)
                r7 = r5
                android.support.v7.view.menu.MenuItemImpl r7 = (android.support.v7.view.menu.MenuItemImpl) r7
                r6 = r7
                if (r6 != r1) goto L_0x0022
                r9.n = r4
                return
            L_0x0022:
                int r4 = r4 + 1
                goto L_0x0013
            L_0x0025:
                r8 = -1
                r9.n = r8
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.view.menu.e.a.a():void");
        }

        public void a(boolean z) {
            this.a = z;
        }

        public MenuBuilder b() {
            return this.c;
        }

        public int getCount() {
            ArrayList $r2 = this.l ? this.c.getNonActionItems() : this.c.getVisibleItems();
            return this.n < 0 ? $r2.size() : $r2.size() - 1;
        }

        public MenuItemImpl getItem(int $i1) {
            ArrayList $r2 = this.l ? this.c.getNonActionItems() : this.c.getVisibleItems();
            int $i0 = this.n;
            if ($i0 >= 0 && $i1 >= $i0) {
                $i1++;
            }
            return (MenuItemImpl) $r2.get($i1);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View $r1, ViewGroup viewGroup) {
            if ($r1 == null) {
                $r1 = this.b.inflate(this.d, viewGroup, false);
            }
            int $i1 = getItem(i).getGroupId();
            int $i2 = i - 1;
            ListMenuItemView $r5 = (ListMenuItemView) $r1;
            $r5.setGroupDividerEnabled(this.c.expandItemActionView() && $i1 != ($i2 >= 0 ? getItem($i2).getGroupId() : $i1));
            MenuView.ItemView $r7 = (MenuView.ItemView) $r1;
            if (this.a) {
                $r5.setForceShowIcon(true);
            }
            $r7.initialize(getItem(i), 0);
            return $r1;
        }

        public void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }
    }

    public e(Label label, MenuBuilder menuBuilder, int i) {
        this.this$0 = label;
        this.c = menuBuilder;
        this.d = i;
    }

    public ListView get() {
        return this.this$0.add();
    }
}

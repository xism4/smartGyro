package android.support.v7.view.menu;

import android.view.MenuItem;

class Plot$a implements Runnable {
    final /* synthetic */ c a;
    final /* synthetic */ e k;
    final /* synthetic */ MenuBuilder mMenu;
    final /* synthetic */ MenuItem this$0;

    Plot$a(c cVar, e eVar, MenuItem menuItem, MenuBuilder menuBuilder) {
        this.a = cVar;
        this.k = eVar;
        this.this$0 = menuItem;
        this.mMenu = menuBuilder;
    }

    public void run() {
        e $r1 = this.k;
        if ($r1 != null) {
            this.a.c.i = true;
            $r1.c.close(false);
            this.a.c.i = false;
        }
        if (this.this$0.isEnabled() && this.this$0.hasSubMenu()) {
            this.mMenu.performItemAction(this.this$0, 4);
        }
    }
}

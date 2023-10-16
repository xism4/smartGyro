package android.support.p025v7.widget;

import android.support.p025v7.widget.ActionMenuView;
import android.support.p025v7.widget.Toolbar;
import android.view.MenuItem;

/* renamed from: android.support.v7.widget.ua */
class C0441ua implements ActionMenuView.C0325e {

    /* renamed from: a */
    final /* synthetic */ Toolbar f1647a;

    C0441ua(Toolbar toolbar) {
        this.f1647a = toolbar;
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        Toolbar.C0369c cVar = this.f1647a.f1376G;
        if (cVar != null) {
            return cVar.onMenuItemClick(menuItem);
        }
        return false;
    }
}

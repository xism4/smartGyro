package android.support.v7.widget;

import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

class ShareActionProvider$ShareMenuItemOnMenuItemClickListener implements ActionMenuView.e {
    final /* synthetic */ Toolbar this$0;

    ShareActionProvider$ShareMenuItemOnMenuItemClickListener(Toolbar toolbar) {
        this.this$0 = toolbar;
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        Toolbar.c $r3 = this.this$0.mMenuItemClickListener;
        if ($r3 != null) {
            return $r3.onMenuItemClick(menuItem);
        }
        return false;
    }
}

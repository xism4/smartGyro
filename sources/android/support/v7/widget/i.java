package android.support.v7.widget;

import android.content.Context;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.l$a;
import android.view.View;
import com.org.v4.util.R$attr;

class i extends h {
    final /* synthetic */ ActionMenuPresenter b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i(ActionMenuPresenter actionMenuPresenter, Context context, MenuBuilder menuBuilder, View view, boolean z) {
        super(context, menuBuilder, view, z, R$attr.actionOverflowMenuStyle);
        this.b = actionMenuPresenter;
        b(8388613);
        a((l$a) actionMenuPresenter.mPopupPresenterCallback);
    }

    /* access modifiers changed from: protected */
    public void b() {
        if (this.b.mMenu != null) {
            this.b.mMenu.close();
        }
        this.b.mOverflowPopup = null;
        super.b();
    }
}

package android.support.v7.widget;

import android.content.Context;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.l$a;
import android.view.View;
import com.org.v4.util.R$attr;

class XYPlot extends h {
    final /* synthetic */ ActionMenuPresenter K;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public XYPlot(ActionMenuPresenter actionMenuPresenter, Context context, SubMenuBuilder subMenuBuilder, View view) {
        super(context, subMenuBuilder, view, false, R$attr.actionOverflowMenuStyle);
        this.K = actionMenuPresenter;
        if (!((MenuItemImpl) subMenuBuilder.getItem()).isActionButton()) {
            View $r2 = actionMenuPresenter.mOverflowButton;
            a($r2 == null ? (View) actionMenuPresenter.mMenuView : $r2);
        }
        a((l$a) actionMenuPresenter.mPopupPresenterCallback);
    }

    /* access modifiers changed from: protected */
    public void b() {
        ActionMenuPresenter $r1 = this.K;
        $r1.a = null;
        $r1.e = 0;
        super.b();
    }
}

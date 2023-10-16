package android.support.v7.view.menu;

import android.view.View;
import android.view.ViewTreeObserver;

class MenuPopupHelper implements View.OnAttachStateChangeListener {
    final /* synthetic */ f mMenu;

    MenuPopupHelper(f fVar) {
        this.mMenu = fVar;
    }

    public void onViewAttachedToWindow(View view) {
    }

    public void onViewDetachedFromWindow(View view) {
        ViewTreeObserver $r3 = this.mMenu.x;
        if ($r3 != null) {
            if (!$r3.isAlive()) {
                this.mMenu.x = view.getViewTreeObserver();
            }
            f $r2 = this.mMenu;
            $r2.x.removeGlobalOnLayoutListener($r2.t);
        }
        view.removeOnAttachStateChangeListener(this);
    }
}

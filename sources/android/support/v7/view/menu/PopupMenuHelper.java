package android.support.v7.view.menu;

import android.view.View;
import android.view.ViewTreeObserver;

class PopupMenuHelper implements View.OnAttachStateChangeListener {
    final /* synthetic */ i mMenu;

    PopupMenuHelper(i iVar) {
        this.mMenu = iVar;
    }

    public void onViewAttachedToWindow(View view) {
    }

    public void onViewDetachedFromWindow(View view) {
        ViewTreeObserver $r3 = this.mMenu.mTreeObserver;
        if ($r3 != null) {
            if (!$r3.isAlive()) {
                this.mMenu.mTreeObserver = view.getViewTreeObserver();
            }
            i $r2 = this.mMenu;
            $r2.mTreeObserver.removeGlobalOnLayoutListener($r2.w);
        }
        view.removeOnAttachStateChangeListener(this);
    }
}

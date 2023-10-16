package android.support.v7.view.menu;

import android.view.View;
import android.view.ViewTreeObserver;

class a implements ViewTreeObserver.OnGlobalLayoutListener {
    final /* synthetic */ i a;

    a(i iVar) {
        this.a = iVar;
    }

    public void onGlobalLayout() {
        if (this.a.isShowing() && !this.a.a.isModal()) {
            View $r3 = this.a.v;
            if ($r3 == null || !$r3.isShown()) {
                this.a.dismiss();
            } else {
                this.a.a.show();
            }
        }
    }
}

package android.support.p025v7.view.menu;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: android.support.v7.view.menu.B */
class C0276B implements View.OnAttachStateChangeListener {

    /* renamed from: a */
    final /* synthetic */ C0277C f859a;

    C0276B(C0277C c) {
        this.f859a = c;
    }

    public void onViewAttachedToWindow(View view) {
    }

    public void onViewDetachedFromWindow(View view) {
        ViewTreeObserver viewTreeObserver = this.f859a.f875q;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.f859a.f875q = view.getViewTreeObserver();
            }
            C0277C c = this.f859a;
            c.f875q.removeGlobalOnLayoutListener(c.f869k);
        }
        view.removeOnAttachStateChangeListener(this);
    }
}

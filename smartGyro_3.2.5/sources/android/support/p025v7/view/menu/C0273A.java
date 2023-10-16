package android.support.p025v7.view.menu;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: android.support.v7.view.menu.A */
class C0273A implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a */
    final /* synthetic */ C0277C f846a;

    C0273A(C0277C c) {
        this.f846a = c;
    }

    public void onGlobalLayout() {
        if (this.f846a.mo1136b() && !this.f846a.f868j.mo2018k()) {
            View view = this.f846a.f873o;
            if (view == null || !view.isShown()) {
                this.f846a.dismiss();
            } else {
                this.f846a.f868j.mo1137c();
            }
        }
    }
}

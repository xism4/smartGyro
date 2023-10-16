package android.support.p025v7.view.menu;

import android.support.p025v7.view.menu.C0288i;
import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: android.support.v7.view.menu.e */
class C0284e implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a */
    final /* synthetic */ C0288i f940a;

    C0284e(C0288i iVar) {
        this.f940a = iVar;
    }

    public void onGlobalLayout() {
        if (this.f940a.mo1136b() && this.f940a.f957j.size() > 0 && !this.f940a.f957j.get(0).f974a.mo2018k()) {
            View view = this.f940a.f964q;
            if (view == null || !view.isShown()) {
                this.f940a.dismiss();
                return;
            }
            for (C0288i.C0289a aVar : this.f940a.f957j) {
                aVar.f974a.mo1137c();
            }
        }
    }
}

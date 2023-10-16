package android.support.p025v7.widget;

import android.support.p025v7.widget.C0328C;
import android.view.ViewTreeObserver;

/* renamed from: android.support.v7.widget.E */
class C0335E implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a */
    final /* synthetic */ C0328C.C0330b f1203a;

    C0335E(C0328C.C0330b bVar) {
        this.f1203a = bVar;
    }

    public void onGlobalLayout() {
        C0328C.C0330b bVar = this.f1203a;
        if (!bVar.mo1684b(C0328C.this)) {
            this.f1203a.dismiss();
            return;
        }
        this.f1203a.mo1685l();
        C0335E.super.mo1137c();
    }
}

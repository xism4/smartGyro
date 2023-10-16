package android.support.p025v7.app;

import android.view.View;

/* renamed from: android.support.v7.app.f */
class C0244f implements Runnable {

    /* renamed from: a */
    final /* synthetic */ View f739a;

    /* renamed from: b */
    final /* synthetic */ View f740b;

    /* renamed from: c */
    final /* synthetic */ AlertController f741c;

    C0244f(AlertController alertController, View view, View view2) {
        this.f741c = alertController;
        this.f739a = view;
        this.f740b = view2;
    }

    public void run() {
        AlertController.m828a(this.f741c.f599g, this.f739a, this.f740b);
    }
}

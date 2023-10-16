package android.support.p025v7.app;

import android.view.View;

/* renamed from: android.support.v7.app.d */
class C0242d implements Runnable {

    /* renamed from: a */
    final /* synthetic */ View f733a;

    /* renamed from: b */
    final /* synthetic */ View f734b;

    /* renamed from: c */
    final /* synthetic */ AlertController f735c;

    C0242d(AlertController alertController, View view, View view2) {
        this.f735c = alertController;
        this.f733a = view;
        this.f734b = view2;
    }

    public void run() {
        AlertController.m828a(this.f735c.f574A, this.f733a, this.f734b);
    }
}

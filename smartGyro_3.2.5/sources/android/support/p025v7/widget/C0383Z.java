package android.support.p025v7.widget;

import android.view.View;

/* renamed from: android.support.v7.widget.Z */
class C0383Z implements Runnable {

    /* renamed from: a */
    final /* synthetic */ View f1478a;

    /* renamed from: b */
    final /* synthetic */ C0386aa f1479b;

    C0383Z(C0386aa aaVar, View view) {
        this.f1479b = aaVar;
        this.f1478a = view;
    }

    public void run() {
        this.f1479b.smoothScrollTo(this.f1478a.getLeft() - ((this.f1479b.getWidth() - this.f1478a.getWidth()) / 2), 0);
        this.f1479b.f1492b = null;
    }
}

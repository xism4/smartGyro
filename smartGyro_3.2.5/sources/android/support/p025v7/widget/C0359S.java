package android.support.p025v7.widget;

import android.view.View;

/* renamed from: android.support.v7.widget.S */
class C0359S implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C0371U f1303a;

    C0359S(C0371U u) {
        this.f1303a = u;
    }

    public void run() {
        View e = this.f1303a.mo2007e();
        if (e != null && e.getWindowToken() != null) {
            this.f1303a.mo1137c();
        }
    }
}

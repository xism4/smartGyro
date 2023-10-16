package android.support.p025v7.widget;

/* renamed from: android.support.v7.widget.e */
class C0396e implements Runnable {

    /* renamed from: a */
    final /* synthetic */ ActionBarOverlayLayout f1516a;

    C0396e(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.f1516a = actionBarOverlayLayout;
    }

    public void run() {
        this.f1516a.mo1592h();
        ActionBarOverlayLayout actionBarOverlayLayout = this.f1516a;
        actionBarOverlayLayout.f1148x = actionBarOverlayLayout.f1129e.animate().translationY(0.0f).setListener(this.f1516a.f1149y);
    }
}

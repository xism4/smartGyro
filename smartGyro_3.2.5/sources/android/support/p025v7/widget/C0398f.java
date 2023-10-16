package android.support.p025v7.widget;

/* renamed from: android.support.v7.widget.f */
class C0398f implements Runnable {

    /* renamed from: a */
    final /* synthetic */ ActionBarOverlayLayout f1518a;

    C0398f(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.f1518a = actionBarOverlayLayout;
    }

    public void run() {
        this.f1518a.mo1592h();
        ActionBarOverlayLayout actionBarOverlayLayout = this.f1518a;
        actionBarOverlayLayout.f1148x = actionBarOverlayLayout.f1129e.animate().translationY((float) (-this.f1518a.f1129e.getHeight())).setListener(this.f1518a.f1149y);
    }
}

package android.support.p025v7.app;

import android.support.p025v7.widget.ActionBarOverlayLayout;
import android.view.View;
import p000a.p001a.p005c.p014g.C0093B;
import p000a.p001a.p005c.p014g.C0127u;

/* renamed from: android.support.v7.app.D */
class C0231D extends C0093B {

    /* renamed from: a */
    final /* synthetic */ C0234G f687a;

    C0231D(C0234G g) {
        this.f687a = g;
    }

    /* renamed from: b */
    public void mo390b(View view) {
        View view2;
        C0234G g = this.f687a;
        if (g.f719w && (view2 = g.f707k) != null) {
            view2.setTranslationY(0.0f);
            this.f687a.f704h.setTranslationY(0.0f);
        }
        this.f687a.f704h.setVisibility(8);
        this.f687a.f704h.setTransitioning(false);
        C0234G g2 = this.f687a;
        g2.f693B = null;
        g2.mo980h();
        ActionBarOverlayLayout actionBarOverlayLayout = this.f687a.f703g;
        if (actionBarOverlayLayout != null) {
            C0127u.m457k(actionBarOverlayLayout);
        }
    }
}

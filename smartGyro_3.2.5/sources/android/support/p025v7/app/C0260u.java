package android.support.p025v7.app;

import android.view.View;
import p000a.p001a.p005c.p014g.C0092A;
import p000a.p001a.p005c.p014g.C0093B;
import p000a.p001a.p005c.p014g.C0127u;

/* renamed from: android.support.v7.app.u */
class C0260u extends C0093B {

    /* renamed from: a */
    final /* synthetic */ C0261v f765a;

    C0260u(C0261v vVar) {
        this.f765a = vVar;
    }

    /* renamed from: b */
    public void mo390b(View view) {
        this.f765a.f800p.setAlpha(1.0f);
        this.f765a.f803s.mo505a((C0092A) null);
        this.f765a.f803s = null;
    }

    /* renamed from: c */
    public void mo391c(View view) {
        this.f765a.f800p.setVisibility(0);
        this.f765a.f800p.sendAccessibilityEvent(32);
        if (this.f765a.f800p.getParent() instanceof View) {
            C0127u.m457k((View) this.f765a.f800p.getParent());
        }
    }
}

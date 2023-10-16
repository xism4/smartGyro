package android.support.p025v7.app;

import android.support.p025v7.app.C0261v;
import android.view.View;
import android.widget.PopupWindow;
import p000a.p001a.p005c.p014g.C0092A;
import p000a.p001a.p005c.p014g.C0093B;
import p000a.p001a.p005c.p014g.C0127u;

/* renamed from: android.support.v7.app.w */
class C0269w extends C0093B {

    /* renamed from: a */
    final /* synthetic */ C0261v.C0263b f841a;

    C0269w(C0261v.C0263b bVar) {
        this.f841a = bVar;
    }

    /* renamed from: b */
    public void mo390b(View view) {
        C0261v.this.f800p.setVisibility(8);
        C0261v vVar = C0261v.this;
        PopupWindow popupWindow = vVar.f801q;
        if (popupWindow != null) {
            popupWindow.dismiss();
        } else if (vVar.f800p.getParent() instanceof View) {
            C0127u.m457k((View) C0261v.this.f800p.getParent());
        }
        C0261v.this.f800p.removeAllViews();
        C0261v.this.f803s.mo505a((C0092A) null);
        C0261v.this.f803s = null;
    }
}

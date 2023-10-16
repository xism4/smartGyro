package android.support.p025v7.widget;

import android.view.View;
import p000a.p001a.p005c.p014g.C0093B;

/* renamed from: android.support.v7.widget.za */
class C0451za extends C0093B {

    /* renamed from: a */
    private boolean f1661a = false;

    /* renamed from: b */
    final /* synthetic */ int f1662b;

    /* renamed from: c */
    final /* synthetic */ C0318Aa f1663c;

    C0451za(C0318Aa aa, int i) {
        this.f1663c = aa;
        this.f1662b = i;
    }

    /* renamed from: a */
    public void mo389a(View view) {
        this.f1661a = true;
    }

    /* renamed from: b */
    public void mo390b(View view) {
        if (!this.f1661a) {
            this.f1663c.f1085a.setVisibility(this.f1662b);
        }
    }

    /* renamed from: c */
    public void mo391c(View view) {
        this.f1663c.f1085a.setVisibility(0);
    }
}

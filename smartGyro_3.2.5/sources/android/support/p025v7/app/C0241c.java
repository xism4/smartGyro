package android.support.p025v7.app;

import android.support.p024v4.widget.NestedScrollView;
import android.view.View;

/* renamed from: android.support.v7.app.c */
class C0241c implements NestedScrollView.C0199b {

    /* renamed from: a */
    final /* synthetic */ View f730a;

    /* renamed from: b */
    final /* synthetic */ View f731b;

    /* renamed from: c */
    final /* synthetic */ AlertController f732c;

    C0241c(AlertController alertController, View view, View view2) {
        this.f732c = alertController;
        this.f730a = view;
        this.f731b = view2;
    }

    /* renamed from: a */
    public void mo859a(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
        AlertController.m828a(nestedScrollView, this.f730a, this.f731b);
    }
}

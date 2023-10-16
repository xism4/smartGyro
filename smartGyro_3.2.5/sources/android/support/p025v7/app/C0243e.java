package android.support.p025v7.app;

import android.view.View;
import android.widget.AbsListView;

/* renamed from: android.support.v7.app.e */
class C0243e implements AbsListView.OnScrollListener {

    /* renamed from: a */
    final /* synthetic */ View f736a;

    /* renamed from: b */
    final /* synthetic */ View f737b;

    /* renamed from: c */
    final /* synthetic */ AlertController f738c;

    C0243e(AlertController alertController, View view, View view2) {
        this.f738c = alertController;
        this.f736a = view;
        this.f737b = view2;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        AlertController.m828a(absListView, this.f736a, this.f737b);
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }
}

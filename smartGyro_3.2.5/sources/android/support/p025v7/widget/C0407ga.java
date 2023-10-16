package android.support.p025v7.widget;

import android.view.View;

/* renamed from: android.support.v7.widget.ga */
class C0407ga implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ SearchView f1549a;

    C0407ga(SearchView searchView) {
        this.f1549a = searchView;
    }

    public void onClick(View view) {
        SearchView searchView = this.f1549a;
        if (view == searchView.f1349u) {
            searchView.mo1854e();
        } else if (view == searchView.f1351w) {
            searchView.mo1852d();
        } else if (view == searchView.f1350v) {
            searchView.mo1855f();
        } else if (view == searchView.f1352x) {
            searchView.mo1865h();
        } else if (view == searchView.f1344q) {
            searchView.mo1848b();
        }
    }
}

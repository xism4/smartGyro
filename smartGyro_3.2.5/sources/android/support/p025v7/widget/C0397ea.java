package android.support.p025v7.widget;

import android.view.View;

/* renamed from: android.support.v7.widget.ea */
class C0397ea implements View.OnFocusChangeListener {

    /* renamed from: a */
    final /* synthetic */ SearchView f1517a;

    C0397ea(SearchView searchView) {
        this.f1517a = searchView;
    }

    public void onFocusChange(View view, boolean z) {
        SearchView searchView = this.f1517a;
        View.OnFocusChangeListener onFocusChangeListener = searchView.f1318N;
        if (onFocusChangeListener != null) {
            onFocusChangeListener.onFocusChange(searchView, z);
        }
    }
}

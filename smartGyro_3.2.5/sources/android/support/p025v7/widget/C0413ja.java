package android.support.p025v7.widget;

import android.view.View;
import android.widget.AdapterView;

/* renamed from: android.support.v7.widget.ja */
class C0413ja implements AdapterView.OnItemClickListener {

    /* renamed from: a */
    final /* synthetic */ SearchView f1563a;

    C0413ja(SearchView searchView) {
        this.f1563a = searchView;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f1563a.mo1846a(i, 0, (String) null);
    }
}

package android.support.p025v7.widget;

import android.view.View;
import android.widget.AdapterView;

/* renamed from: android.support.v7.widget.ka */
class C0415ka implements AdapterView.OnItemSelectedListener {

    /* renamed from: a */
    final /* synthetic */ SearchView f1566a;

    C0415ka(SearchView searchView) {
        this.f1566a = searchView;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        this.f1566a.mo1853d(i);
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}

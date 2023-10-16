package android.support.p025v7.widget;

import android.text.Editable;
import android.text.TextWatcher;

/* renamed from: android.support.v7.widget.ba */
class C0391ba implements TextWatcher {

    /* renamed from: a */
    final /* synthetic */ SearchView f1510a;

    C0391ba(SearchView searchView) {
        this.f1510a = searchView;
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.f1510a.mo1849b(charSequence);
    }
}

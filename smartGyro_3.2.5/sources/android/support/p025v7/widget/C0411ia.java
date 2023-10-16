package android.support.p025v7.widget;

import android.view.KeyEvent;
import android.widget.TextView;

/* renamed from: android.support.v7.widget.ia */
class C0411ia implements TextView.OnEditorActionListener {

    /* renamed from: a */
    final /* synthetic */ SearchView f1556a;

    C0411ia(SearchView searchView) {
        this.f1556a = searchView;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        this.f1556a.mo1855f();
        return true;
    }
}

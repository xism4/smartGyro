package android.support.p025v7.widget;

import android.view.KeyEvent;
import android.view.View;

/* renamed from: android.support.v7.widget.ha */
class C0409ha implements View.OnKeyListener {

    /* renamed from: a */
    final /* synthetic */ SearchView f1552a;

    C0409ha(SearchView searchView) {
        this.f1552a = searchView;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        SearchView searchView = this.f1552a;
        if (searchView.f1334ga == null) {
            return false;
        }
        if (searchView.f1344q.isPopupShowing() && this.f1552a.f1344q.getListSelection() != -1) {
            return this.f1552a.mo1847a(view, i, keyEvent);
        }
        if (this.f1552a.f1344q.mo1888a() || !keyEvent.hasNoModifiers() || keyEvent.getAction() != 1 || i != 66) {
            return false;
        }
        view.cancelLongPress();
        SearchView searchView2 = this.f1552a;
        searchView2.mo1843a(0, (String) null, searchView2.f1344q.getText().toString());
        return true;
    }
}

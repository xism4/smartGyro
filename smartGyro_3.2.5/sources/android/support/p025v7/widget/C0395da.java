package android.support.p025v7.widget;

import android.database.Cursor;
import android.support.p024v4.widget.C0206d;

/* renamed from: android.support.v7.widget.da */
class C0395da implements Runnable {

    /* renamed from: a */
    final /* synthetic */ SearchView f1515a;

    C0395da(SearchView searchView) {
        this.f1515a = searchView;
    }

    public void run() {
        C0206d dVar = this.f1515a.f1323S;
        if (dVar != null && (dVar instanceof C0421na)) {
            dVar.mo893a((Cursor) null);
        }
    }
}

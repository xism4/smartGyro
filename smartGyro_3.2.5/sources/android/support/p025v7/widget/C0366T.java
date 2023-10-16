package android.support.p025v7.widget;

import android.view.View;
import android.widget.AdapterView;

/* renamed from: android.support.v7.widget.T */
class C0366T implements AdapterView.OnItemSelectedListener {

    /* renamed from: a */
    final /* synthetic */ C0371U f1369a;

    C0366T(C0371U u) {
        this.f1369a = u;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        C0349N n;
        if (i != -1 && (n = this.f1369a.f1432f) != null) {
            n.setListSelectionHidden(false);
        }
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}

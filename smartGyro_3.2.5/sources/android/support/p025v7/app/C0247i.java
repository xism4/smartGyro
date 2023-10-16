package android.support.p025v7.app;

import android.support.p025v7.app.AlertController;
import android.view.View;
import android.widget.AdapterView;

/* renamed from: android.support.v7.app.i */
class C0247i implements AdapterView.OnItemClickListener {

    /* renamed from: a */
    final /* synthetic */ AlertController f749a;

    /* renamed from: b */
    final /* synthetic */ AlertController.C0223a f750b;

    C0247i(AlertController.C0223a aVar, AlertController alertController) {
        this.f750b = aVar;
        this.f749a = alertController;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f750b.f660x.onClick(this.f749a.f594b, i);
        if (!this.f750b.f628H) {
            this.f749a.f594b.dismiss();
        }
    }
}

package android.support.p025v7.app;

import android.support.p025v7.app.AlertController;
import android.view.View;
import android.widget.AdapterView;

/* renamed from: android.support.v7.app.j */
class C0248j implements AdapterView.OnItemClickListener {

    /* renamed from: a */
    final /* synthetic */ AlertController.RecycleListView f751a;

    /* renamed from: b */
    final /* synthetic */ AlertController f752b;

    /* renamed from: c */
    final /* synthetic */ AlertController.C0223a f753c;

    C0248j(AlertController.C0223a aVar, AlertController.RecycleListView recycleListView, AlertController alertController) {
        this.f753c = aVar;
        this.f751a = recycleListView;
        this.f752b = alertController;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        boolean[] zArr = this.f753c.f626F;
        if (zArr != null) {
            zArr[i] = this.f751a.isItemChecked(i);
        }
        this.f753c.f630J.onClick(this.f752b.f594b, i, this.f751a.isItemChecked(i));
    }
}

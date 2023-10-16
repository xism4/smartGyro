package android.support.p025v7.app;

import android.content.Context;
import android.support.p025v7.app.AlertController;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/* renamed from: android.support.v7.app.g */
class C0245g extends ArrayAdapter<CharSequence> {

    /* renamed from: a */
    final /* synthetic */ AlertController.RecycleListView f742a;

    /* renamed from: b */
    final /* synthetic */ AlertController.C0223a f743b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0245g(AlertController.C0223a aVar, Context context, int i, int i2, CharSequence[] charSequenceArr, AlertController.RecycleListView recycleListView) {
        super(context, i, i2, charSequenceArr);
        this.f743b = aVar;
        this.f742a = recycleListView;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = super.getView(i, view, viewGroup);
        boolean[] zArr = this.f743b.f626F;
        if (zArr != null && zArr[i]) {
            this.f742a.setItemChecked(i, true);
        }
        return view2;
    }
}

package android.support.v7.app;

import android.content.Context;
import android.support.v7.app.AlertController;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

class AlertController$AlertParams$1 extends ArrayAdapter<CharSequence> {
    final /* synthetic */ AlertController.a this$0;
    final /* synthetic */ AlertController.RecycleListView val$listView;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AlertController$AlertParams$1(AlertController.a aVar, Context context, int i, int i2, CharSequence[] charSequenceArr, AlertController.RecycleListView recycleListView) {
        super(context, i, i2, charSequenceArr);
        this.this$0 = aVar;
        this.val$listView = recycleListView;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View $r1 = super.getView(i, view, viewGroup);
        boolean[] $r4 = this.this$0.mCheckedItems;
        if ($r4 != null && $r4[i]) {
            this.val$listView.setItemChecked(i, true);
        }
        return $r1;
    }
}

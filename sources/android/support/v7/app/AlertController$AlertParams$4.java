package android.support.v7.app;

import android.support.v7.app.AlertController;
import android.view.View;
import android.widget.AdapterView;

class AlertController$AlertParams$4 implements AdapterView.OnItemClickListener {
    final /* synthetic */ AlertController.a this$0;
    final /* synthetic */ AlertController val$dialog;
    final /* synthetic */ AlertController.RecycleListView val$listView;

    AlertController$AlertParams$4(AlertController.a aVar, AlertController.RecycleListView recycleListView, AlertController alertController) {
        this.this$0 = aVar;
        this.val$listView = recycleListView;
        this.val$dialog = alertController;
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        boolean[] $r4 = this.this$0.mCheckedItems;
        if ($r4 != null) {
            $r4[i] = this.val$listView.isItemChecked(i);
        }
        this.this$0.mOnCheckboxClickListener.onClick(this.val$dialog.mDialog, i, this.val$listView.isItemChecked(i));
    }
}

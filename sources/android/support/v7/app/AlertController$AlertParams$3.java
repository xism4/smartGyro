package android.support.v7.app;

import android.support.v7.app.AlertController;
import android.view.View;
import android.widget.AdapterView;

class AlertController$AlertParams$3 implements AdapterView.OnItemClickListener {
    final /* synthetic */ AlertController.a this$0;
    final /* synthetic */ AlertController val$dialog;

    AlertController$AlertParams$3(AlertController.a aVar, AlertController alertController) {
        this.this$0 = aVar;
        this.val$dialog = alertController;
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.this$0.mOnClickListener.onClick(this.val$dialog.mDialog, i);
        if (!this.this$0.mIsSingleChoice) {
            this.val$dialog.mDialog.dismiss();
        }
    }
}

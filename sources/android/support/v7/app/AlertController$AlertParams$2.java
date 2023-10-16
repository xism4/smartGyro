package android.support.v7.app;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AlertController;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;

class AlertController$AlertParams$2 extends CursorAdapter {
    private final int mIsCheckedIndex;
    private final int mLabelIndex;
    final /* synthetic */ AlertController.a this$0;
    final /* synthetic */ AlertController val$dialog;
    final /* synthetic */ AlertController.RecycleListView val$listView;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AlertController$AlertParams$2(AlertController.a aVar, Context context, Cursor cursor, boolean z, AlertController.RecycleListView recycleListView, AlertController alertController) {
        super(context, cursor, z);
        this.this$0 = aVar;
        this.val$listView = recycleListView;
        this.val$dialog = alertController;
        Cursor $r1 = getCursor();
        this.mLabelIndex = $r1.getColumnIndexOrThrow(this.this$0.mLabelColumn);
        this.mIsCheckedIndex = $r1.getColumnIndexOrThrow(this.this$0.mIsCheckedColumn);
    }

    public void bindView(View view, Context context, Cursor cursor) {
        ((CheckedTextView) view.findViewById(16908308)).setText(cursor.getString(this.mLabelIndex));
        AlertController.RecycleListView $r6 = this.val$listView;
        int $i0 = cursor.getPosition();
        boolean $z0 = true;
        if (cursor.getInt(this.mIsCheckedIndex) != 1) {
            $z0 = false;
        }
        $r6.setItemChecked($i0, $z0);
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.this$0.mInflater.inflate(this.val$dialog.mMultiChoiceItemLayout, viewGroup, false);
    }
}

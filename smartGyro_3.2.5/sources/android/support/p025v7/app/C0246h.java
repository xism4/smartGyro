package android.support.p025v7.app;

import android.content.Context;
import android.database.Cursor;
import android.support.p025v7.app.AlertController;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;

/* renamed from: android.support.v7.app.h */
class C0246h extends CursorAdapter {

    /* renamed from: a */
    private final int f744a;

    /* renamed from: b */
    private final int f745b;

    /* renamed from: c */
    final /* synthetic */ AlertController.RecycleListView f746c;

    /* renamed from: d */
    final /* synthetic */ AlertController f747d;

    /* renamed from: e */
    final /* synthetic */ AlertController.C0223a f748e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0246h(AlertController.C0223a aVar, Context context, Cursor cursor, boolean z, AlertController.RecycleListView recycleListView, AlertController alertController) {
        super(context, cursor, z);
        this.f748e = aVar;
        this.f746c = recycleListView;
        this.f747d = alertController;
        Cursor cursor2 = getCursor();
        this.f744a = cursor2.getColumnIndexOrThrow(this.f748e.f632L);
        this.f745b = cursor2.getColumnIndexOrThrow(this.f748e.f633M);
    }

    public void bindView(View view, Context context, Cursor cursor) {
        ((CheckedTextView) view.findViewById(16908308)).setText(cursor.getString(this.f744a));
        AlertController.RecycleListView recycleListView = this.f746c;
        int position = cursor.getPosition();
        boolean z = true;
        if (cursor.getInt(this.f745b) != 1) {
            z = false;
        }
        recycleListView.setItemChecked(position, z);
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f748e.f638b.inflate(this.f747d.f586M, viewGroup, false);
    }
}

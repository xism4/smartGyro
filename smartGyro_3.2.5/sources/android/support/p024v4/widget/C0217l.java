package android.support.p024v4.widget;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: android.support.v4.widget.l */
public abstract class C0217l extends C0206d {

    /* renamed from: i */
    private int f557i;

    /* renamed from: j */
    private int f558j;

    /* renamed from: k */
    private LayoutInflater f559k;

    @Deprecated
    public C0217l(Context context, int i, Cursor cursor, boolean z) {
        super(context, cursor, z);
        this.f558j = i;
        this.f557i = i;
        this.f559k = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    /* renamed from: a */
    public View mo891a(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f559k.inflate(this.f558j, viewGroup, false);
    }

    /* renamed from: b */
    public View mo896b(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f559k.inflate(this.f557i, viewGroup, false);
    }
}

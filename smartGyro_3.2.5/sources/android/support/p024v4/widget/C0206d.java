package android.support.p024v4.widget;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.support.p024v4.widget.C0209e;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

/* renamed from: android.support.v4.widget.d */
public abstract class C0206d extends BaseAdapter implements Filterable, C0209e.C0210a {

    /* renamed from: a */
    protected boolean f541a;

    /* renamed from: b */
    protected boolean f542b;

    /* renamed from: c */
    protected Cursor f543c;

    /* renamed from: d */
    protected Context f544d;

    /* renamed from: e */
    protected int f545e;

    /* renamed from: f */
    protected C0207a f546f;

    /* renamed from: g */
    protected DataSetObserver f547g;

    /* renamed from: h */
    protected C0209e f548h;

    /* renamed from: android.support.v4.widget.d$a */
    private class C0207a extends ContentObserver {
        C0207a() {
            super(new Handler());
        }

        public boolean deliverSelfNotifications() {
            return true;
        }

        public void onChange(boolean z) {
            C0206d.this.mo897b();
        }
    }

    /* renamed from: android.support.v4.widget.d$b */
    private class C0208b extends DataSetObserver {
        C0208b() {
        }

        public void onChanged() {
            C0206d dVar = C0206d.this;
            dVar.f541a = true;
            dVar.notifyDataSetChanged();
        }

        public void onInvalidated() {
            C0206d dVar = C0206d.this;
            dVar.f541a = false;
            dVar.notifyDataSetInvalidated();
        }
    }

    public C0206d(Context context, Cursor cursor, boolean z) {
        mo892a(context, cursor, z ? 1 : 2);
    }

    /* renamed from: a */
    public Cursor mo890a() {
        return this.f543c;
    }

    /* renamed from: a */
    public abstract View mo891a(Context context, Cursor cursor, ViewGroup viewGroup);

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo892a(Context context, Cursor cursor, int i) {
        C0208b bVar;
        boolean z = false;
        if ((i & 1) == 1) {
            i |= 2;
            this.f542b = true;
        } else {
            this.f542b = false;
        }
        if (cursor != null) {
            z = true;
        }
        this.f543c = cursor;
        this.f541a = z;
        this.f544d = context;
        this.f545e = z ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i & 2) == 2) {
            this.f546f = new C0207a();
            bVar = new C0208b();
        } else {
            bVar = null;
            this.f546f = null;
        }
        this.f547g = bVar;
        if (z) {
            C0207a aVar = this.f546f;
            if (aVar != null) {
                cursor.registerContentObserver(aVar);
            }
            DataSetObserver dataSetObserver = this.f547g;
            if (dataSetObserver != null) {
                cursor.registerDataSetObserver(dataSetObserver);
            }
        }
    }

    /* renamed from: a */
    public void mo893a(Cursor cursor) {
        Cursor b = mo895b(cursor);
        if (b != null) {
            b.close();
        }
    }

    /* renamed from: a */
    public abstract void mo894a(View view, Context context, Cursor cursor);

    /* renamed from: b */
    public Cursor mo895b(Cursor cursor) {
        Cursor cursor2 = this.f543c;
        if (cursor == cursor2) {
            return null;
        }
        if (cursor2 != null) {
            C0207a aVar = this.f546f;
            if (aVar != null) {
                cursor2.unregisterContentObserver(aVar);
            }
            DataSetObserver dataSetObserver = this.f547g;
            if (dataSetObserver != null) {
                cursor2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f543c = cursor;
        if (cursor != null) {
            C0207a aVar2 = this.f546f;
            if (aVar2 != null) {
                cursor.registerContentObserver(aVar2);
            }
            DataSetObserver dataSetObserver2 = this.f547g;
            if (dataSetObserver2 != null) {
                cursor.registerDataSetObserver(dataSetObserver2);
            }
            this.f545e = cursor.getColumnIndexOrThrow("_id");
            this.f541a = true;
            notifyDataSetChanged();
        } else {
            this.f545e = -1;
            this.f541a = false;
            notifyDataSetInvalidated();
        }
        return cursor2;
    }

    /* renamed from: b */
    public abstract View mo896b(Context context, Cursor cursor, ViewGroup viewGroup);

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo897b() {
        Cursor cursor;
        if (this.f542b && (cursor = this.f543c) != null && !cursor.isClosed()) {
            this.f541a = this.f543c.requery();
        }
    }

    public abstract CharSequence convertToString(Cursor cursor);

    public int getCount() {
        Cursor cursor;
        if (!this.f541a || (cursor = this.f543c) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (!this.f541a) {
            return null;
        }
        this.f543c.moveToPosition(i);
        if (view == null) {
            view = mo891a(this.f544d, this.f543c, viewGroup);
        }
        mo894a(view, this.f544d, this.f543c);
        return view;
    }

    public Filter getFilter() {
        if (this.f548h == null) {
            this.f548h = new C0209e(this);
        }
        return this.f548h;
    }

    public Object getItem(int i) {
        Cursor cursor;
        if (!this.f541a || (cursor = this.f543c) == null) {
            return null;
        }
        cursor.moveToPosition(i);
        return this.f543c;
    }

    public long getItemId(int i) {
        Cursor cursor;
        if (!this.f541a || (cursor = this.f543c) == null || !cursor.moveToPosition(i)) {
            return 0;
        }
        return this.f543c.getLong(this.f545e);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!this.f541a) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        } else if (this.f543c.moveToPosition(i)) {
            if (view == null) {
                view = mo896b(this.f544d, this.f543c, viewGroup);
            }
            mo894a(view, this.f544d, this.f543c);
            return view;
        } else {
            throw new IllegalStateException("couldn't move cursor to position " + i);
        }
    }
}

package android.support.p024v4.widget;

import android.database.Cursor;
import android.widget.Filter;

/* renamed from: android.support.v4.widget.e */
class C0209e extends Filter {

    /* renamed from: a */
    C0210a f551a;

    /* renamed from: android.support.v4.widget.e$a */
    interface C0210a {
        /* renamed from: a */
        Cursor mo890a();

        /* renamed from: a */
        Cursor mo912a(CharSequence charSequence);

        /* renamed from: a */
        void mo893a(Cursor cursor);

        CharSequence convertToString(Cursor cursor);
    }

    C0209e(C0210a aVar) {
        this.f551a = aVar;
    }

    public CharSequence convertResultToString(Object obj) {
        return this.f551a.convertToString((Cursor) obj);
    }

    /* access modifiers changed from: protected */
    public Filter.FilterResults performFiltering(CharSequence charSequence) {
        Cursor a = this.f551a.mo912a(charSequence);
        Filter.FilterResults filterResults = new Filter.FilterResults();
        if (a != null) {
            filterResults.count = a.getCount();
        } else {
            filterResults.count = 0;
            a = null;
        }
        filterResults.values = a;
        return filterResults;
    }

    /* access modifiers changed from: protected */
    public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        Cursor a = this.f551a.mo890a();
        Object obj = filterResults.values;
        if (obj != null && obj != a) {
            this.f551a.mo893a((Cursor) obj);
        }
    }
}

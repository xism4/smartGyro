package android.support.p025v7.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.p025v7.widget.SearchView;

/* renamed from: android.support.v7.widget.la */
class C0417la implements Parcelable.ClassLoaderCreator<SearchView.C0364e> {
    C0417la() {
    }

    public SearchView.C0364e createFromParcel(Parcel parcel) {
        return new SearchView.C0364e(parcel, (ClassLoader) null);
    }

    public SearchView.C0364e createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new SearchView.C0364e(parcel, classLoader);
    }

    public SearchView.C0364e[] newArray(int i) {
        return new SearchView.C0364e[i];
    }
}

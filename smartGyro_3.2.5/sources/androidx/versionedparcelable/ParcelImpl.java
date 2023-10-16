package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelImpl> CREATOR = new C0452a();

    /* renamed from: a */
    private final C0455d f1664a;

    protected ParcelImpl(Parcel parcel) {
        this.f1664a = new C0454c(parcel).mo2340h();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        new C0454c(parcel).mo2322a(this.f1664a);
    }
}

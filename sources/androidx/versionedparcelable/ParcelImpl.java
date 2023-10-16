package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelImpl> CREATOR = new Server$1();
    private final Context a;

    protected ParcelImpl(Parcel parcel) {
        this.a = new i(parcel).f();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        new i(parcel).d(this.a);
    }
}

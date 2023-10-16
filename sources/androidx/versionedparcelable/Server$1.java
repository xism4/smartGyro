package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;

final class Server$1 implements Parcelable.Creator<ParcelImpl> {
    Server$1() {
    }

    public ParcelImpl createFromParcel(Parcel parcel) {
        return new ParcelImpl(parcel);
    }

    public ParcelImpl[] newArray(int i) {
        return new ParcelImpl[i];
    }
}

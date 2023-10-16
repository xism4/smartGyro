package p000a.p001a.p005c.p014g;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: a.a.c.g.b */
class C0100b implements Parcelable.ClassLoaderCreator<C0105c> {
    C0100b() {
    }

    public C0105c createFromParcel(Parcel parcel) {
        return createFromParcel(parcel, (ClassLoader) null);
    }

    public C0105c createFromParcel(Parcel parcel, ClassLoader classLoader) {
        if (parcel.readParcelable(classLoader) == null) {
            return C0105c.f256a;
        }
        throw new IllegalStateException("superState must be null");
    }

    public C0105c[] newArray(int i) {
        return new C0105c[i];
    }
}

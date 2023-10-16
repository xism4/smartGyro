package p000a.p001a.p005c.p014g;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: a.a.c.g.c */
public abstract class C0105c implements Parcelable {
    public static final Parcelable.Creator<C0105c> CREATOR = new C0100b();

    /* renamed from: a */
    public static final C0105c f256a = new C0096a();

    /* renamed from: b */
    private final Parcelable f257b;

    private C0105c() {
        this.f257b = null;
    }

    /* synthetic */ C0105c(C0096a aVar) {
        this();
    }

    protected C0105c(Parcel parcel, ClassLoader classLoader) {
        Parcelable readParcelable = parcel.readParcelable(classLoader);
        this.f257b = readParcelable == null ? f256a : readParcelable;
    }

    protected C0105c(Parcelable parcelable) {
        if (parcelable != null) {
            this.f257b = parcelable == f256a ? null : parcelable;
            return;
        }
        throw new IllegalArgumentException("superState must not be null");
    }

    /* renamed from: a */
    public final Parcelable mo430a() {
        return this.f257b;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f257b, i);
    }
}

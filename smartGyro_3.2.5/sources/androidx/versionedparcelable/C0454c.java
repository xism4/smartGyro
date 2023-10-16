package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseIntArray;

/* renamed from: androidx.versionedparcelable.c */
class C0454c extends C0453b {

    /* renamed from: a */
    private final SparseIntArray f1665a;

    /* renamed from: b */
    private final Parcel f1666b;

    /* renamed from: c */
    private final int f1667c;

    /* renamed from: d */
    private final int f1668d;

    /* renamed from: e */
    private final String f1669e;

    /* renamed from: f */
    private int f1670f;

    /* renamed from: g */
    private int f1671g;

    C0454c(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "");
    }

    C0454c(Parcel parcel, int i, int i2, String str) {
        this.f1665a = new SparseIntArray();
        this.f1670f = -1;
        this.f1671g = 0;
        this.f1666b = parcel;
        this.f1667c = i;
        this.f1668d = i2;
        this.f1671g = this.f1667c;
        this.f1669e = str;
    }

    /* renamed from: d */
    private int m1960d(int i) {
        int readInt;
        do {
            int i2 = this.f1671g;
            if (i2 >= this.f1668d) {
                return -1;
            }
            this.f1666b.setDataPosition(i2);
            int readInt2 = this.f1666b.readInt();
            readInt = this.f1666b.readInt();
            this.f1671g += readInt2;
        } while (readInt != i);
        return this.f1666b.dataPosition();
    }

    /* renamed from: a */
    public void mo2320a() {
        int i = this.f1670f;
        if (i >= 0) {
            int i2 = this.f1665a.get(i);
            int dataPosition = this.f1666b.dataPosition();
            this.f1666b.setDataPosition(i2);
            this.f1666b.writeInt(dataPosition - i2);
            this.f1666b.setDataPosition(dataPosition);
        }
    }

    /* renamed from: a */
    public void mo2321a(Parcelable parcelable) {
        this.f1666b.writeParcelable(parcelable, 0);
    }

    /* renamed from: a */
    public void mo2323a(String str) {
        this.f1666b.writeString(str);
    }

    /* renamed from: a */
    public void mo2325a(byte[] bArr) {
        if (bArr != null) {
            this.f1666b.writeInt(bArr.length);
            this.f1666b.writeByteArray(bArr);
            return;
        }
        this.f1666b.writeInt(-1);
    }

    /* renamed from: a */
    public boolean mo2326a(int i) {
        int d = m1960d(i);
        if (d == -1) {
            return false;
        }
        this.f1666b.setDataPosition(d);
        return true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public C0453b mo2328b() {
        Parcel parcel = this.f1666b;
        int dataPosition = parcel.dataPosition();
        int i = this.f1671g;
        if (i == this.f1667c) {
            i = this.f1668d;
        }
        return new C0454c(parcel, dataPosition, i, this.f1669e + "  ");
    }

    /* renamed from: b */
    public void mo2329b(int i) {
        mo2320a();
        this.f1670f = i;
        this.f1665a.put(i, this.f1666b.dataPosition());
        mo2334c(0);
        mo2334c(i);
    }

    /* renamed from: c */
    public void mo2334c(int i) {
        this.f1666b.writeInt(i);
    }

    /* renamed from: d */
    public byte[] mo2336d() {
        int readInt = this.f1666b.readInt();
        if (readInt < 0) {
            return null;
        }
        byte[] bArr = new byte[readInt];
        this.f1666b.readByteArray(bArr);
        return bArr;
    }

    /* renamed from: e */
    public int mo2337e() {
        return this.f1666b.readInt();
    }

    /* renamed from: f */
    public <T extends Parcelable> T mo2338f() {
        return this.f1666b.readParcelable(C0454c.class.getClassLoader());
    }

    /* renamed from: g */
    public String mo2339g() {
        return this.f1666b.readString();
    }
}

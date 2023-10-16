package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseIntArray;

class i extends f {
    private final SparseIntArray a;
    private final Parcel b;
    private final String h;
    private int i;
    private final int k;
    private int m;
    private final int n;

    i(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "");
    }

    i(Parcel parcel, int i2, int i3, String str) {
        this.a = new SparseIntArray();
        this.i = -1;
        this.m = 0;
        this.b = parcel;
        this.k = i2;
        this.n = i3;
        this.m = this.k;
        this.h = str;
    }

    private int a(int i2) {
        int $i1;
        do {
            int $i12 = this.m;
            if ($i12 >= this.n) {
                return -1;
            }
            this.b.setDataPosition($i12);
            int $i2 = this.b.readInt();
            $i1 = this.b.readInt();
            this.m += $i2;
        } while ($i1 != i2);
        return this.b.dataPosition();
    }

    public void a() {
        int $i0 = this.i;
        if ($i0 >= 0) {
            int $i1 = this.a.get($i0);
            int $i2 = this.b.dataPosition();
            this.b.setDataPosition($i1);
            this.b.writeInt($i2 - $i1);
            this.b.setDataPosition($i2);
        }
    }

    public void a(String str) {
        this.b.writeString(str);
    }

    public void a(byte[] bArr) {
        if (bArr != null) {
            this.b.writeInt(bArr.length);
            this.b.writeByteArray(bArr);
            return;
        }
        this.b.writeInt(-1);
    }

    public Parcelable add() {
        return this.b.readParcelable(c.class.getClassLoader());
    }

    public boolean add(int i2) {
        int $i0 = a(i2);
        if ($i0 == -1) {
            return false;
        }
        this.b.setDataPosition($i0);
        return true;
    }

    public void b(int i2) {
        this.b.writeInt(i2);
    }

    public byte[] b() {
        int $i0 = this.b.readInt();
        if ($i0 < 0) {
            return null;
        }
        byte[] $r2 = new byte[$i0];
        this.b.readByteArray($r2);
        return $r2;
    }

    /* access modifiers changed from: protected */
    public f c() {
        Parcel $r2 = this.b;
        int $i0 = $r2.dataPosition();
        int $i1 = this.m;
        if ($i1 == this.k) {
            $i1 = this.n;
        }
        return new i($r2, $i0, $i1, this.h + "  ");
    }

    public void clear(Parcelable parcelable) {
        this.b.writeParcelable(parcelable, 0);
    }

    public void e(int i2) {
        a();
        this.i = i2;
        this.a.put(i2, this.b.dataPosition());
        b(0);
        b(i2);
    }

    public String getValue() {
        return this.b.readString();
    }

    public int size() {
        return this.b.readInt();
    }
}

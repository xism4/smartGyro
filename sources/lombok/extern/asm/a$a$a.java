package lombok.extern.asm;

import android.os.IBinder;
import android.os.Parcel;

class a$a$a implements ByteVector {
    public static ByteVector d;
    private IBinder mRemote;

    a$a$a(IBinder iBinder) {
        this.mRemote = iBinder;
    }

    public int a() {
        Parcel $r1 = Parcel.obtain();
        Parcel $r2 = Parcel.obtain();
        try {
            $r1.writeInterfaceToken("com.enhance.gameservice.IGameTuningService");
            if (!this.mRemote.transact(4, $r1, $r2, 0)) {
                if (b.b() != null) {
                    return b.b().a();
                }
            }
            $r2.readException();
            int $i0 = $r2.readInt();
            $r2.recycle();
            $r1.recycle();
            return $i0;
        } finally {
            $r2.recycle();
            $r1.recycle();
        }
    }

    public int a(int i) {
        Parcel $r1 = Parcel.obtain();
        Parcel $r2 = Parcel.obtain();
        try {
            $r1.writeInterfaceToken("com.enhance.gameservice.IGameTuningService");
            $r1.writeInt(i);
            if (!this.mRemote.transact(3, $r1, $r2, 0)) {
                if (b.b() != null) {
                    return b.b().a(i);
                }
            }
            $r2.readException();
            int $i0 = $r2.readInt();
            $r2.recycle();
            $r1.recycle();
            return $i0;
        } finally {
            $r2.recycle();
            $r1.recycle();
        }
    }

    public int a(boolean z) {
        Parcel $r1 = Parcel.obtain();
        Parcel $r2 = Parcel.obtain();
        $r1.writeInterfaceToken("com.enhance.gameservice.IGameTuningService");
        try {
            $r1.writeInt(z ? (byte) 1 : 0);
            if (!this.mRemote.transact(5, $r1, $r2, 0)) {
                if (b.b() != null) {
                    return b.b().a(z);
                }
            }
            $r2.readException();
            int $i1 = $r2.readInt();
            $r2.recycle();
            $r1.recycle();
            return $i1;
        } finally {
            $r2.recycle();
            $r1.recycle();
        }
    }

    public IBinder asBinder() {
        return this.mRemote;
    }

    public int generateKey(int i) {
        Parcel $r1 = Parcel.obtain();
        Parcel $r2 = Parcel.obtain();
        try {
            $r1.writeInterfaceToken("com.enhance.gameservice.IGameTuningService");
            $r1.writeInt(i);
            if (!this.mRemote.transact(1, $r1, $r2, 0)) {
                if (b.b() != null) {
                    return b.b().generateKey(i);
                }
            }
            $r2.readException();
            int $i0 = $r2.readInt();
            $r2.recycle();
            $r1.recycle();
            return $i0;
        } finally {
            $r2.recycle();
            $r1.recycle();
        }
    }

    public int get(int i) {
        Parcel $r1 = Parcel.obtain();
        Parcel $r2 = Parcel.obtain();
        try {
            $r1.writeInterfaceToken("com.enhance.gameservice.IGameTuningService");
            $r1.writeInt(i);
            if (!this.mRemote.transact(2, $r1, $r2, 0)) {
                if (b.b() != null) {
                    return b.b().get(i);
                }
            }
            $r2.readException();
            int $i0 = $r2.readInt();
            $r2.recycle();
            $r1.recycle();
            return $i0;
        } finally {
            $r2.recycle();
            $r1.recycle();
        }
    }
}

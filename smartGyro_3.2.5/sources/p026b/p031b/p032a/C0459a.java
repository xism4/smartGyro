package p026b.p031b.p032a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* renamed from: b.b.a.a */
public interface C0459a extends IInterface {

    /* renamed from: b.b.a.a$a */
    public static abstract class C0460a extends Binder implements C0459a {

        /* renamed from: b.b.a.a$a$a */
        private static class C0461a implements C0459a {

            /* renamed from: a */
            public static C0459a f1685a;

            /* renamed from: b */
            private IBinder f1686b;

            C0461a(IBinder iBinder) {
                this.f1686b = iBinder;
            }

            /* renamed from: a */
            public int mo2346a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.enhance.gameservice.IGameTuningService");
                    if (!this.f1686b.transact(4, obtain, obtain2, 0) && C0460a.m1989b() != null) {
                        return C0460a.m1989b().mo2346a();
                    }
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: a */
            public int mo2347a(int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.enhance.gameservice.IGameTuningService");
                    obtain.writeInt(i);
                    if (!this.f1686b.transact(1, obtain, obtain2, 0) && C0460a.m1989b() != null) {
                        return C0460a.m1989b().mo2347a(i);
                    }
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: a */
            public int mo2348a(boolean z) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.enhance.gameservice.IGameTuningService");
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.f1686b.transact(5, obtain, obtain2, 0) && C0460a.m1989b() != null) {
                        return C0460a.m1989b().mo2348a(z);
                    }
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f1686b;
            }

            /* renamed from: b */
            public int mo2349b(int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.enhance.gameservice.IGameTuningService");
                    obtain.writeInt(i);
                    if (!this.f1686b.transact(2, obtain, obtain2, 0) && C0460a.m1989b() != null) {
                        return C0460a.m1989b().mo2349b(i);
                    }
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: c */
            public int mo2350c(int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.enhance.gameservice.IGameTuningService");
                    obtain.writeInt(i);
                    if (!this.f1686b.transact(3, obtain, obtain2, 0) && C0460a.m1989b() != null) {
                        return C0460a.m1989b().mo2350c(i);
                    }
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        /* renamed from: a */
        public static C0459a m1988a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.enhance.gameservice.IGameTuningService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof C0459a)) ? new C0461a(iBinder) : (C0459a) queryLocalInterface;
        }

        /* renamed from: b */
        public static C0459a m1989b() {
            return C0461a.f1685a;
        }
    }

    /* renamed from: a */
    int mo2346a();

    /* renamed from: a */
    int mo2347a(int i);

    /* renamed from: a */
    int mo2348a(boolean z);

    /* renamed from: b */
    int mo2349b(int i);

    /* renamed from: c */
    int mo2350c(int i);
}

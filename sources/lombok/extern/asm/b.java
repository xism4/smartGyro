package lombok.extern.asm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

public abstract class b extends Binder implements ByteVector {
    public static ByteVector a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface $r0 = iBinder.queryLocalInterface("com.enhance.gameservice.IGameTuningService");
        return ($r0 == null || !($r0 instanceof ByteVector)) ? new a$a$a(iBinder) : (ByteVector) $r0;
    }

    public static ByteVector b() {
        return a$a$a.d;
    }
}

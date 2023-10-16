package p000a.p001a.p005c.p014g;

import android.os.Build;
import android.view.Gravity;

/* renamed from: a.a.c.g.f */
public final class C0111f {
    /* renamed from: a */
    public static int m395a(int i, int i2) {
        return Build.VERSION.SDK_INT >= 17 ? Gravity.getAbsoluteGravity(i, i2) : i & -8388609;
    }
}

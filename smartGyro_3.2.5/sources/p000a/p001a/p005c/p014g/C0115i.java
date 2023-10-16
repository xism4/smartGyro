package p000a.p001a.p005c.p014g;

import android.os.Build;
import android.view.ViewGroup;

/* renamed from: a.a.c.g.i */
public final class C0115i {
    /* renamed from: a */
    public static int m405a(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return Build.VERSION.SDK_INT >= 17 ? marginLayoutParams.getMarginEnd() : marginLayoutParams.rightMargin;
    }

    /* renamed from: b */
    public static int m406b(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return Build.VERSION.SDK_INT >= 17 ? marginLayoutParams.getMarginStart() : marginLayoutParams.leftMargin;
    }
}

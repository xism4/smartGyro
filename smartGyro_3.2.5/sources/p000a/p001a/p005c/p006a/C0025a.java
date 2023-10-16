package p000a.p001a.p005c.p006a;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Process;
import android.util.TypedValue;

/* renamed from: a.a.c.a.a */
public class C0025a {

    /* renamed from: a */
    private static final Object f99a = new Object();

    /* renamed from: b */
    private static TypedValue f100b;

    /* renamed from: a */
    public static int m75a(Context context, String str) {
        if (str != null) {
            return context.checkPermission(str, Process.myPid(), Process.myUid());
        }
        throw new IllegalArgumentException("permission is null");
    }

    /* renamed from: a */
    public static ColorStateList m76a(Context context, int i) {
        return Build.VERSION.SDK_INT >= 23 ? context.getColorStateList(i) : context.getResources().getColorStateList(i);
    }

    /* renamed from: b */
    public static Drawable m77b(Context context, int i) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            return context.getDrawable(i);
        }
        if (i2 < 16) {
            synchronized (f99a) {
                if (f100b == null) {
                    f100b = new TypedValue();
                }
                context.getResources().getValue(i, f100b, true);
                i = f100b.resourceId;
            }
        }
        return context.getResources().getDrawable(i);
    }
}

package com.org.android.asm;

import a.a.c.f.e;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import com.org.android.manager.Label;
import com.org.android.manager.i;
import com.org.android.ui.asm.b;
import com.org.android.ui.asm.k;
import com.org.android.ui.asm.l;
import com.org.android.util.LruCache;

public class c {
    private static final f c;
    private static final e<String, Typeface> d = new LruCache(16);

    static {
        f $r0;
        int $i0 = Build.VERSION.SDK_INT;
        if ($i0 >= 28) {
            $r0 = r2;
            f r2 = new NonLazyDocumentFactory();
        } else if ($i0 >= 26) {
            $r0 = r3;
            f r3 = new ClassWriter();
        } else if ($i0 >= 24 && Frame.set()) {
            $r0 = r4;
            f r4 = new Frame();
        } else if (Build.VERSION.SDK_INT >= 21) {
            $r0 = r5;
            f r5 = new a();
        } else {
            $r0 = r6;
            f r6 = new f();
        }
        c = $r0;
    }

    public static Typeface a(Context context, Resources resources, int i, String str, int i2) {
        Typeface $r4 = c.a(context, resources, i, str, i2);
        if ($r4 != null) {
            d.put(valueOf(resources, i, i2), $r4);
        }
        return $r4;
    }

    public static Typeface a(Context context, CancellationSignal cancellationSignal, Label[] labelArr, int i) {
        return c.a(context, cancellationSignal, labelArr, i);
    }

    public static Typeface a(Context context, l lVar, Resources resources, int i, int i2, k kVar, Handler handler, boolean z) {
        Typeface $r7;
        if (lVar instanceof b) {
            b $r5 = (b) lVar;
            boolean $z1 = false;
            if (!z ? kVar == null : $r5.d() == 0) {
                $z1 = true;
            }
            $r7 = i.a(context, $r5.a(), kVar, handler, $z1, z ? $r5.e() : -1, i2);
        } else {
            Typeface $r10 = c.a(context, (com.org.android.ui.asm.e) lVar, resources, i2);
            $r7 = $r10;
            if (kVar != null) {
                if ($r10 != null) {
                    kVar.a($r10, handler);
                } else {
                    kVar.a(-3, handler);
                }
            }
        }
        if ($r7 != null) {
            d.put(valueOf(resources, i, i2), $r7);
        }
        return $r7;
    }

    public static Typeface a(Resources resources, int i, int i2) {
        return (Typeface) d.get(valueOf(resources, i, i2));
    }

    private static String valueOf(Resources resources, int i, int i2) {
        return resources.getResourcePackageName(i) + "-" + i + "-" + i2;
    }
}

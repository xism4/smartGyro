package com.org.android.asm;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.util.Log;
import com.org.android.manager.Label;
import com.org.android.ui.asm.e;
import com.org.android.ui.asm.f;
import com.org.android.util.SimpleArrayMap;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;

class Frame extends f {
    private static final Method a;
    private static final Constructor c;
    private static final Method d;
    private static final Class g;

    static {
        Method $r8;
        Method $r6;
        Class $r2;
        Constructor $r0 = null;
        try {
            Class $r1 = Class.forName("android.graphics.FontFamily");
            $r2 = $r1;
            Constructor $r4 = $r1.getConstructor(new Class[0]);
            $r6 = $r1.getMethod("addFontWeightStyle", new Class[]{ByteBuffer.class, Integer.TYPE, List.class, Integer.TYPE, Boolean.TYPE});
            Object $r7 = Array.newInstance($r1, 1);
            Class $r12 = Typeface.class;
            Class[] $r3 = new Class[1];
            $r3[0] = $r7.getClass();
            $r8 = $r12.getMethod("createFromFamiliesWithDefault", $r3);
            $r0 = $r4;
        } catch (ClassNotFoundException | NoSuchMethodException $r9) {
            Log.e("TypefaceCompatApi24Impl", $r9.getClass().getName(), $r9);
            $r2 = null;
            $r8 = null;
            $r6 = null;
        }
        c = $r0;
        g = $r2;
        d = $r6;
        a = $r8;
    }

    Frame() {
    }

    private static boolean a(Object obj, ByteBuffer byteBuffer, int i, int i2, boolean z) {
        Method $r0 = d;
        Object[] $r3 = new Object[5];
        $r3[0] = byteBuffer;
        try {
            $r3[1] = Integer.valueOf(i);
            $r3[2] = null;
            $r3[3] = Integer.valueOf(i2);
            $r3[4] = Boolean.valueOf(z);
            return ((Boolean) $r0.invoke(obj, $r3)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException $r6) {
            throw new RuntimeException($r6);
        }
    }

    private static Typeface get(Object obj) {
        try {
            Object $r2 = Array.newInstance(g, 1);
            Array.set($r2, 0, obj);
            return (Typeface) a.invoke((Object) null, new Object[]{$r2});
        } catch (IllegalAccessException | InvocationTargetException $r6) {
            throw new RuntimeException($r6);
        }
    }

    private static Object get() {
        try {
            return c.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException $r3) {
            throw new RuntimeException($r3);
        }
    }

    public static boolean set() {
        if (d == null) {
            Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
        }
        return d != null;
    }

    public Typeface a(Context context, CancellationSignal cancellationSignal, Label[] labelArr, int i) {
        Object $r5 = get();
        SimpleArrayMap $r3 = new SimpleArrayMap();
        for (Label $r6 : labelArr) {
            Uri $r7 = $r6.getValue();
            ByteBuffer $r9 = (ByteBuffer) $r3.get($r7);
            if ($r9 == null) {
                ByteBuffer $r10 = ByteVector.read(context, cancellationSignal, $r7);
                $r9 = $r10;
                $r3.put($r7, $r10);
            }
            if (!a($r5, $r9, $r6.d(), $r6.b(), $r6.a())) {
                return null;
            }
        }
        return Typeface.create(get($r5), i);
    }

    public Typeface a(Context context, e eVar, Resources resources, int i) {
        Object $r4 = get();
        for (f $r6 : eVar.a()) {
            ByteBuffer $r7 = ByteVector.a(context, resources, $r6.b());
            if ($r7 == null || !a($r4, $r7, $r6.r(), $r6.f(), $r6.c())) {
                return null;
            }
        }
        return get($r4);
    }
}

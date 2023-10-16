package com.org.android.asm;

import android.graphics.Typeface;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NonLazyDocumentFactory extends ClassWriter {
    /* access modifiers changed from: protected */
    public Typeface get(Object obj) {
        try {
            Object $r3 = Array.newInstance(this.version, 1);
            Array.set($r3, 0, obj);
            Method $r4 = this.c;
            Object[] $r5 = new Object[4];
            $r5[0] = $r3;
            $r5[1] = "sans-serif";
            $r5[2] = -1;
            $r5[3] = -1;
            return (Typeface) $r4.invoke((Object) null, $r5);
        } catch (IllegalAccessException | InvocationTargetException $r8) {
            throw new RuntimeException($r8);
        }
    }

    /* access modifiers changed from: protected */
    public Method getInstance(Class cls) {
        Object $r2 = Array.newInstance(cls, 1);
        Class $r4 = Integer.TYPE;
        Method $r5 = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", new Class[]{$r2.getClass(), String.class, $r4, $r4});
        $r5.setAccessible(true);
        return $r5;
    }
}

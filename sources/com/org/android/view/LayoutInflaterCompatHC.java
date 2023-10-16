package com.org.android.view;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import java.lang.reflect.Field;

public final class LayoutInflaterCompatHC {
    private static boolean sCheckedField;
    private static Field sLayoutInflaterFactory2Field;

    private static void forceSetFactory2(LayoutInflater layoutInflater, LayoutInflater.Factory2 factory2) {
        if (!sCheckedField) {
            try {
                sLayoutInflaterFactory2Field = LayoutInflater.class.getDeclaredField("mFactory2");
                sLayoutInflaterFactory2Field.setAccessible(true);
            } catch (NoSuchFieldException $r4) {
                Log.e("LayoutInflaterCompatHC", "forceSetFactory2 Could not find field 'mFactory2' on class " + LayoutInflater.class.getName() + "; inflation may have unexpected results.", $r4);
            }
            sCheckedField = true;
        }
        Field $r3 = sLayoutInflaterFactory2Field;
        if ($r3 != null) {
            try {
                $r3.set(layoutInflater, factory2);
            } catch (IllegalAccessException $r7) {
                Log.e("LayoutInflaterCompatHC", "forceSetFactory2 could not set the Factory2 on LayoutInflater " + layoutInflater + "; inflation may have unexpected results.", $r7);
            }
        }
    }

    public static void setFactory(LayoutInflater layoutInflater, LayoutInflater.Factory2 $r1) {
        layoutInflater.setFactory2($r1);
        if (Build.VERSION.SDK_INT < 21) {
            LayoutInflater.Factory $r2 = layoutInflater.getFactory();
            if ($r2 instanceof LayoutInflater.Factory2) {
                forceSetFactory2(layoutInflater, (LayoutInflater.Factory2) $r2);
            } else {
                forceSetFactory2(layoutInflater, $r1);
            }
        }
    }
}

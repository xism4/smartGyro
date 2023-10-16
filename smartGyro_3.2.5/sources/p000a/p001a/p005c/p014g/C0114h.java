package p000a.p001a.p005c.p014g;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import java.lang.reflect.Field;

/* renamed from: a.a.c.g.h */
public final class C0114h {

    /* renamed from: a */
    private static Field f268a;

    /* renamed from: b */
    private static boolean f269b;

    /* renamed from: a */
    public static void m403a(LayoutInflater layoutInflater, LayoutInflater.Factory2 factory2) {
        layoutInflater.setFactory2(factory2);
        if (Build.VERSION.SDK_INT < 21) {
            LayoutInflater.Factory factory = layoutInflater.getFactory();
            if (factory instanceof LayoutInflater.Factory2) {
                m404b(layoutInflater, (LayoutInflater.Factory2) factory);
            } else {
                m404b(layoutInflater, factory2);
            }
        }
    }

    /* renamed from: b */
    private static void m404b(LayoutInflater layoutInflater, LayoutInflater.Factory2 factory2) {
        if (!f269b) {
            try {
                f268a = LayoutInflater.class.getDeclaredField("mFactory2");
                f268a.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Log.e("LayoutInflaterCompatHC", "forceSetFactory2 Could not find field 'mFactory2' on class " + LayoutInflater.class.getName() + "; inflation may have unexpected results.", e);
            }
            f269b = true;
        }
        Field field = f268a;
        if (field != null) {
            try {
                field.set(layoutInflater, factory2);
            } catch (IllegalAccessException e2) {
                Log.e("LayoutInflaterCompatHC", "forceSetFactory2 could not set the Factory2 on LayoutInflater " + layoutInflater + "; inflation may have unexpected results.", e2);
            }
        }
    }
}

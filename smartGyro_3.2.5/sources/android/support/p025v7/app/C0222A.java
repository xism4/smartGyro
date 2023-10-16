package android.support.p025v7.app;

import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import android.util.LongSparseArray;
import java.lang.reflect.Field;
import java.util.Map;

/* renamed from: android.support.v7.app.A */
class C0222A {

    /* renamed from: a */
    private static Field f566a;

    /* renamed from: b */
    private static boolean f567b;

    /* renamed from: c */
    private static Class f568c;

    /* renamed from: d */
    private static boolean f569d;

    /* renamed from: e */
    private static Field f570e;

    /* renamed from: f */
    private static boolean f571f;

    /* renamed from: g */
    private static Field f572g;

    /* renamed from: h */
    private static boolean f573h;

    /* renamed from: a */
    static void m822a(Resources resources) {
        int i = Build.VERSION.SDK_INT;
        if (i < 28) {
            if (i >= 24) {
                m826d(resources);
            } else if (i >= 23) {
                m825c(resources);
            } else if (i >= 21) {
                m824b(resources);
            }
        }
    }

    /* renamed from: a */
    private static void m823a(Object obj) {
        LongSparseArray longSparseArray;
        if (!f569d) {
            try {
                f568c = Class.forName("android.content.res.ThemedResourceCache");
            } catch (ClassNotFoundException e) {
                Log.e("ResourcesFlusher", "Could not find ThemedResourceCache class", e);
            }
            f569d = true;
        }
        Class cls = f568c;
        if (cls != null) {
            if (!f571f) {
                try {
                    f570e = cls.getDeclaredField("mUnthemedEntries");
                    f570e.setAccessible(true);
                } catch (NoSuchFieldException e2) {
                    Log.e("ResourcesFlusher", "Could not retrieve ThemedResourceCache#mUnthemedEntries field", e2);
                }
                f571f = true;
            }
            Field field = f570e;
            if (field != null) {
                try {
                    longSparseArray = (LongSparseArray) field.get(obj);
                } catch (IllegalAccessException e3) {
                    Log.e("ResourcesFlusher", "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", e3);
                    longSparseArray = null;
                }
                if (longSparseArray != null) {
                    longSparseArray.clear();
                }
            }
        }
    }

    /* renamed from: b */
    private static void m824b(Resources resources) {
        Map map;
        if (!f567b) {
            try {
                f566a = Resources.class.getDeclaredField("mDrawableCache");
                f566a.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", e);
            }
            f567b = true;
        }
        Field field = f566a;
        if (field != null) {
            try {
                map = (Map) field.get(resources);
            } catch (IllegalAccessException e2) {
                Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", e2);
                map = null;
            }
            if (map != null) {
                map.clear();
            }
        }
    }

    /* renamed from: c */
    private static void m825c(Resources resources) {
        if (!f567b) {
            try {
                f566a = Resources.class.getDeclaredField("mDrawableCache");
                f566a.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", e);
            }
            f567b = true;
        }
        Object obj = null;
        Field field = f566a;
        if (field != null) {
            try {
                obj = field.get(resources);
            } catch (IllegalAccessException e2) {
                Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", e2);
            }
        }
        if (obj != null) {
            m823a(obj);
        }
    }

    /* renamed from: d */
    private static void m826d(Resources resources) {
        Object obj;
        if (!f573h) {
            try {
                f572g = Resources.class.getDeclaredField("mResourcesImpl");
                f572g.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mResourcesImpl field", e);
            }
            f573h = true;
        }
        Field field = f572g;
        if (field != null) {
            Object obj2 = null;
            try {
                obj = field.get(resources);
            } catch (IllegalAccessException e2) {
                Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mResourcesImpl", e2);
                obj = null;
            }
            if (obj != null) {
                if (!f567b) {
                    try {
                        f566a = obj.getClass().getDeclaredField("mDrawableCache");
                        f566a.setAccessible(true);
                    } catch (NoSuchFieldException e3) {
                        Log.e("ResourcesFlusher", "Could not retrieve ResourcesImpl#mDrawableCache field", e3);
                    }
                    f567b = true;
                }
                Field field2 = f566a;
                if (field2 != null) {
                    try {
                        obj2 = field2.get(obj);
                    } catch (IllegalAccessException e4) {
                        Log.e("ResourcesFlusher", "Could not retrieve value from ResourcesImpl#mDrawableCache", e4);
                    }
                }
                if (obj2 != null) {
                    m823a(obj2);
                }
            }
        }
    }
}

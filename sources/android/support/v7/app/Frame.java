package android.support.v7.app;

import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.Field;

class Frame {
    private static Field a;
    private static Field b;
    private static Class c;
    private static boolean d;
    private static boolean e;
    private static boolean f;
    private static Field g;
    private static boolean i;

    private static void a(Resources resources) {
        Object $r5;
        if (!f) {
            try {
                a = Resources.class.getDeclaredField("mResourcesImpl");
                a.setAccessible(true);
            } catch (NoSuchFieldException $r3) {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mResourcesImpl field", $r3);
            }
            f = true;
        }
        Field $r2 = a;
        if ($r2 != null) {
            Object $r4 = null;
            try {
                $r5 = $r2.get(resources);
            } catch (IllegalAccessException $r6) {
                Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mResourcesImpl", $r6);
                $r5 = null;
            }
            if ($r5 != null) {
                if (!d) {
                    try {
                        g = $r5.getClass().getDeclaredField("mDrawableCache");
                        g.setAccessible(true);
                    } catch (NoSuchFieldException $r7) {
                        Log.e("ResourcesFlusher", "Could not retrieve ResourcesImpl#mDrawableCache field", $r7);
                    }
                    d = true;
                }
                Field $r22 = g;
                if ($r22 != null) {
                    try {
                        $r4 = $r22.get($r5);
                    } catch (IllegalAccessException $r8) {
                        Log.e("ResourcesFlusher", "Could not retrieve value from ResourcesImpl#mDrawableCache", $r8);
                    }
                }
                if ($r4 != null) {
                    init($r4);
                }
            }
        }
    }

    private static void init(Resources resources) {
        if (!d) {
            try {
                g = Resources.class.getDeclaredField("mDrawableCache");
                g.setAccessible(true);
            } catch (NoSuchFieldException $r3) {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", $r3);
            }
            d = true;
        }
        Object $r4 = null;
        Field $r2 = g;
        if ($r2 != null) {
            try {
                $r4 = $r2.get(resources);
            } catch (IllegalAccessException $r5) {
                Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", $r5);
            }
        }
        if ($r4 != null) {
            init($r4);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v0, resolved type: android.util.LongSparseArray} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void init(java.lang.Object r11) {
        /*
            boolean r0 = i
            if (r0 != 0) goto L_0x0018
            java.lang.String r2 = "android.content.res.ThemedResourceCache"
            java.lang.Class r1 = java.lang.Class.forName(r2)     // Catch:{ ClassNotFoundException -> 0x000d }
            c = r1
            goto L_0x0015
        L_0x000d:
            r3 = move-exception
            java.lang.String r2 = "ResourcesFlusher"
            java.lang.String r4 = "Could not find ThemedResourceCache class"
            android.util.Log.e(r2, r4, r3)
        L_0x0015:
            r5 = 1
            i = r5
        L_0x0018:
            java.lang.Class r1 = c
            if (r1 != 0) goto L_0x001d
            return
        L_0x001d:
            boolean r0 = e
            if (r0 != 0) goto L_0x003b
            java.lang.String r2 = "mUnthemedEntries"
            java.lang.reflect.Field r6 = r1.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x0030 }
            b = r6
            java.lang.reflect.Field r6 = b
            r5 = 1
            r6.setAccessible(r5)     // Catch:{ NoSuchFieldException -> 0x0030 }
            goto L_0x0038
        L_0x0030:
            r7 = move-exception
            java.lang.String r2 = "ResourcesFlusher"
            java.lang.String r4 = "Could not retrieve ThemedResourceCache#mUnthemedEntries field"
            android.util.Log.e(r2, r4, r7)
        L_0x0038:
            r5 = 1
            e = r5
        L_0x003b:
            java.lang.reflect.Field r6 = b
            if (r6 != 0) goto L_0x0040
            return
        L_0x0040:
            java.lang.Object r11 = r6.get(r11)     // Catch:{ IllegalAccessException -> 0x0049 }
            r9 = r11
            android.util.LongSparseArray r9 = (android.util.LongSparseArray) r9
            r8 = r9
            goto L_0x0052
        L_0x0049:
            r10 = move-exception
            java.lang.String r2 = "ResourcesFlusher"
            java.lang.String r4 = "Could not retrieve value from ThemedResourceCache#mUnthemedEntries"
            android.util.Log.e(r2, r4, r10)
            r8 = 0
        L_0x0052:
            if (r8 == 0) goto L_0x0057
            r8.clear()
        L_0x0057:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.Frame.init(java.lang.Object):void");
    }

    static void initialize(Resources resources) {
        int $i0 = Build.VERSION.SDK_INT;
        if ($i0 < 28) {
            if ($i0 >= 24) {
                a(resources);
            } else if ($i0 >= 23) {
                init(resources);
            } else if ($i0 >= 21) {
                set(resources);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v0, resolved type: java.util.Map} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void set(android.content.res.Resources r11) {
        /*
            boolean r0 = d
            if (r0 != 0) goto L_0x0020
            java.lang.Class<android.content.res.Resources> r1 = android.content.res.Resources.class
            java.lang.String r3 = "mDrawableCache"
            java.lang.reflect.Field r2 = r1.getDeclaredField(r3)     // Catch:{ NoSuchFieldException -> 0x0015 }
            g = r2
            java.lang.reflect.Field r2 = g
            r4 = 1
            r2.setAccessible(r4)     // Catch:{ NoSuchFieldException -> 0x0015 }
            goto L_0x001d
        L_0x0015:
            r5 = move-exception
            java.lang.String r3 = "ResourcesFlusher"
            java.lang.String r6 = "Could not retrieve Resources#mDrawableCache field"
            android.util.Log.e(r3, r6, r5)
        L_0x001d:
            r4 = 1
            d = r4
        L_0x0020:
            java.lang.reflect.Field r2 = g
            if (r2 == 0) goto L_0x003b
            java.lang.Object r7 = r2.get(r11)     // Catch:{ IllegalAccessException -> 0x002d }
            r9 = r7
            java.util.Map r9 = (java.util.Map) r9
            r8 = r9
            goto L_0x0036
        L_0x002d:
            r10 = move-exception
            java.lang.String r3 = "ResourcesFlusher"
            java.lang.String r6 = "Could not retrieve value from Resources#mDrawableCache"
            android.util.Log.e(r3, r6, r10)
            r8 = 0
        L_0x0036:
            if (r8 == 0) goto L_0x003b
            r8.clear()
        L_0x003b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.Frame.set(android.content.res.Resources):void");
    }
}

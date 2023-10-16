package com.org.v4.text.view;

import a.a.d.b.a.a;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import com.org.android.ui.asm.Handler;
import java.util.WeakHashMap;

public final class Resources {
    private static final ThreadLocal<TypedValue> TL_TYPED_VALUE = new ThreadLocal();
    private static final WeakHashMap<Context, SparseArray<a.a>> c = new WeakHashMap(0);
    private static final Object mutex = new Object();

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003f, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.content.res.ColorStateList add(android.content.Context r16, int r17) {
        /*
            java.lang.Object r1 = mutex
            monitor-enter(r1)
            java.util.WeakHashMap<android.content.Context, android.util.SparseArray<a.a.d.b.a.a$a>> r2 = c     // Catch:{ Throwable -> 0x0041 }
            r0 = r16
            java.lang.Object r3 = r2.get(r0)     // Catch:{ Throwable -> 0x0041 }
            r5 = r3
            android.util.SparseArray r5 = (android.util.SparseArray) r5     // Catch:{ Throwable -> 0x0041 }
            r4 = r5
            if (r4 == 0) goto L_0x003e
            int r6 = r4.size()     // Catch:{ Throwable -> 0x0041 }
            if (r6 <= 0) goto L_0x003e
            r0 = r17
            java.lang.Object r3 = r4.get(r0)     // Catch:{ Throwable -> 0x0041 }
            r8 = r3
            com.org.v4.text.view.Item r8 = (com.org.v4.text.view.Item) r8     // Catch:{ Throwable -> 0x0041 }
            r7 = r8
            if (r7 == 0) goto L_0x003e
            android.content.res.Configuration r9 = r7.d     // Catch:{ Throwable -> 0x0041 }
            r0 = r16
            android.content.res.Resources r10 = r0.getResources()     // Catch:{ Throwable -> 0x0041 }
            android.content.res.Configuration r11 = r10.getConfiguration()     // Catch:{ Throwable -> 0x0041 }
            boolean r12 = r9.equals(r11)     // Catch:{ Throwable -> 0x0041 }
            if (r12 == 0) goto L_0x0039
            android.content.res.ColorStateList r13 = r7.c     // Catch:{ Throwable -> 0x0041 }
            monitor-exit(r1)     // Catch:{ Throwable -> 0x0041 }
            return r13
        L_0x0039:
            r0 = r17
            r4.remove(r0)     // Catch:{ Throwable -> 0x0041 }
        L_0x003e:
            monitor-exit(r1)     // Catch:{ Throwable -> 0x0041 }
            r14 = 0
            return r14
        L_0x0041:
            r15 = move-exception
            monitor-exit(r1)     // Catch:{ Throwable -> 0x0041 }
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.v4.text.view.Resources.add(android.content.Context, int):android.content.res.ColorStateList");
    }

    private static void add(Context context, int i, ColorStateList colorStateList) {
        synchronized (mutex) {
            SparseArray $r5 = c.get(context);
            if ($r5 == null) {
                $r5 = new SparseArray();
                c.put(context, $r5);
            }
            $r5.append(i, new Item(colorStateList, context.getResources().getConfiguration()));
        }
    }

    private static ColorStateList create(Context context, int i) {
        if (validate(context, i)) {
            return null;
        }
        android.content.res.Resources $r1 = context.getResources();
        try {
            return Handler.create($r1, $r1.getXml(i), context.getTheme());
        } catch (Exception $r5) {
            Log.e("AppCompatResources", "Failed to inflate ColorStateList, leaving it to the framework", $r5);
            return null;
        }
    }

    public static Drawable getDrawable(Context context, int i) {
        return AppCompatDrawableManager.get().getDrawable(context, i);
    }

    private static TypedValue getTypedValue() {
        TypedValue $r2 = TL_TYPED_VALUE.get();
        if ($r2 != null) {
            return $r2;
        }
        TypedValue $r22 = new TypedValue();
        TL_TYPED_VALUE.set($r22);
        return $r22;
    }

    public static ColorStateList show(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColorStateList(i);
        }
        ColorStateList $r1 = add(context, i);
        if ($r1 != null) {
            return $r1;
        }
        ColorStateList $r12 = create(context, i);
        if ($r12 == null) {
            return com.org.android.ui.Resources.getColorStateList(context, i);
        }
        add(context, i, $r12);
        return $r12;
    }

    private static boolean validate(Context context, int i) {
        android.content.res.Resources $r1 = context.getResources();
        TypedValue $r2 = getTypedValue();
        $r1.getValue(i, $r2, true);
        int $i0 = $r2.type;
        return $i0 >= 28 && $i0 <= 31;
    }
}

package p000a.p001a.p017d.p019b.p020a;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.p025v7.widget.C0423o;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import java.util.WeakHashMap;
import p000a.p001a.p005c.p006a.C0025a;
import p000a.p001a.p005c.p006a.p007a.C0026a;

/* renamed from: a.a.d.b.a.a */
public final class C0146a {

    /* renamed from: a */
    private static final ThreadLocal<TypedValue> f301a = new ThreadLocal<>();

    /* renamed from: b */
    private static final WeakHashMap<Context, SparseArray<C0147a>> f302b = new WeakHashMap<>(0);

    /* renamed from: c */
    private static final Object f303c = new Object();

    /* renamed from: a.a.d.b.a.a$a */
    private static class C0147a {

        /* renamed from: a */
        final ColorStateList f304a;

        /* renamed from: b */
        final Configuration f305b;

        C0147a(ColorStateList colorStateList, Configuration configuration) {
            this.f304a = colorStateList;
            this.f305b = configuration;
        }
    }

    /* renamed from: a */
    public static ColorStateList m489a(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColorStateList(i);
        }
        ColorStateList c = m493c(context, i);
        if (c != null) {
            return c;
        }
        ColorStateList d = m494d(context, i);
        if (d == null) {
            return C0025a.m76a(context, i);
        }
        m491a(context, i, d);
        return d;
    }

    /* renamed from: a */
    private static TypedValue m490a() {
        TypedValue typedValue = f301a.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        f301a.set(typedValue2);
        return typedValue2;
    }

    /* renamed from: a */
    private static void m491a(Context context, int i, ColorStateList colorStateList) {
        synchronized (f303c) {
            SparseArray sparseArray = f302b.get(context);
            if (sparseArray == null) {
                sparseArray = new SparseArray();
                f302b.put(context, sparseArray);
            }
            sparseArray.append(i, new C0147a(colorStateList, context.getResources().getConfiguration()));
        }
    }

    /* renamed from: b */
    public static Drawable m492b(Context context, int i) {
        return C0423o.m1851a().mo2225a(context, i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0033, code lost:
        return null;
     */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.content.res.ColorStateList m493c(android.content.Context r4, int r5) {
        /*
            java.lang.Object r0 = f303c
            monitor-enter(r0)
            java.util.WeakHashMap<android.content.Context, android.util.SparseArray<a.a.d.b.a.a$a>> r1 = f302b     // Catch:{ all -> 0x0035 }
            java.lang.Object r1 = r1.get(r4)     // Catch:{ all -> 0x0035 }
            android.util.SparseArray r1 = (android.util.SparseArray) r1     // Catch:{ all -> 0x0035 }
            if (r1 == 0) goto L_0x0032
            int r2 = r1.size()     // Catch:{ all -> 0x0035 }
            if (r2 <= 0) goto L_0x0032
            java.lang.Object r2 = r1.get(r5)     // Catch:{ all -> 0x0035 }
            a.a.d.b.a.a$a r2 = (p000a.p001a.p017d.p019b.p020a.C0146a.C0147a) r2     // Catch:{ all -> 0x0035 }
            if (r2 == 0) goto L_0x0032
            android.content.res.Configuration r3 = r2.f305b     // Catch:{ all -> 0x0035 }
            android.content.res.Resources r4 = r4.getResources()     // Catch:{ all -> 0x0035 }
            android.content.res.Configuration r4 = r4.getConfiguration()     // Catch:{ all -> 0x0035 }
            boolean r4 = r3.equals(r4)     // Catch:{ all -> 0x0035 }
            if (r4 == 0) goto L_0x002f
            android.content.res.ColorStateList r4 = r2.f304a     // Catch:{ all -> 0x0035 }
            monitor-exit(r0)     // Catch:{ all -> 0x0035 }
            return r4
        L_0x002f:
            r1.remove(r5)     // Catch:{ all -> 0x0035 }
        L_0x0032:
            monitor-exit(r0)     // Catch:{ all -> 0x0035 }
            r4 = 0
            return r4
        L_0x0035:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0035 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p000a.p001a.p017d.p019b.p020a.C0146a.m493c(android.content.Context, int):android.content.res.ColorStateList");
    }

    /* renamed from: d */
    private static ColorStateList m494d(Context context, int i) {
        if (m495e(context, i)) {
            return null;
        }
        Resources resources = context.getResources();
        try {
            return C0026a.m79a(resources, resources.getXml(i), context.getTheme());
        } catch (Exception e) {
            Log.e("AppCompatResources", "Failed to inflate ColorStateList, leaving it to the framework", e);
            return null;
        }
    }

    /* renamed from: e */
    private static boolean m495e(Context context, int i) {
        Resources resources = context.getResources();
        TypedValue a = m490a();
        resources.getValue(i, a, true);
        int i2 = a.type;
        return i2 >= 28 && i2 <= 31;
    }
}

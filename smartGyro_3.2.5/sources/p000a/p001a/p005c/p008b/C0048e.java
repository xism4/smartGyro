package p000a.p001a.p005c.p008b;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;
import p000a.p001a.p005c.p006a.p007a.C0028c;
import p000a.p001a.p005c.p011d.C0064f;
import p000a.p001a.p005c.p013f.C0090i;

/* renamed from: a.a.c.b.e */
class C0048e extends C0053j {

    /* renamed from: a */
    private static final Class f127a;

    /* renamed from: b */
    private static final Constructor f128b;

    /* renamed from: c */
    private static final Method f129c;

    /* renamed from: d */
    private static final Method f130d;

    static {
        Method method;
        Method method2;
        Class<?> cls;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            Constructor<?> constructor2 = cls.getConstructor(new Class[0]);
            method = cls.getMethod("addFontWeightStyle", new Class[]{ByteBuffer.class, Integer.TYPE, List.class, Integer.TYPE, Boolean.TYPE});
            method2 = Typeface.class.getMethod("createFromFamiliesWithDefault", new Class[]{Array.newInstance(cls, 1).getClass()});
            constructor = constructor2;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e("TypefaceCompatApi24Impl", e.getClass().getName(), e);
            cls = null;
            method2 = null;
            method = null;
        }
        f128b = constructor;
        f127a = cls;
        f129c = method;
        f130d = method2;
    }

    C0048e() {
    }

    /* renamed from: a */
    private static Typeface m166a(Object obj) {
        try {
            Object newInstance = Array.newInstance(f127a, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) f130d.invoke((Object) null, new Object[]{newInstance});
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public static boolean m167a() {
        if (f129c == null) {
            Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
        }
        return f129c != null;
    }

    /* renamed from: a */
    private static boolean m168a(Object obj, ByteBuffer byteBuffer, int i, int i2, boolean z) {
        try {
            return ((Boolean) f129c.invoke(obj, new Object[]{byteBuffer, Integer.valueOf(i), null, Integer.valueOf(i2), Boolean.valueOf(z)})).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: b */
    private static Object m169b() {
        try {
            return f128b.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public Typeface mo176a(Context context, C0028c.C0030b bVar, Resources resources, int i) {
        Object b = m169b();
        for (C0028c.C0031c cVar : bVar.mo158a()) {
            ByteBuffer a = C0055k.m209a(context, resources, cVar.mo160b());
            if (a == null || !m168a(b, a, cVar.mo161c(), cVar.mo163e(), cVar.mo164f())) {
                return null;
            }
        }
        return m166a(b);
    }

    /* renamed from: a */
    public Typeface mo175a(Context context, CancellationSignal cancellationSignal, C0064f.C0066b[] bVarArr, int i) {
        Object b = m169b();
        C0090i iVar = new C0090i();
        for (C0064f.C0066b bVar : bVarArr) {
            Uri c = bVar.mo234c();
            ByteBuffer byteBuffer = (ByteBuffer) iVar.get(c);
            if (byteBuffer == null) {
                byteBuffer = C0055k.m210a(context, cancellationSignal, c);
                iVar.put(c, byteBuffer);
            }
            if (!m168a(b, byteBuffer, bVar.mo233b(), bVar.mo235d(), bVar.mo236e())) {
                return null;
            }
        }
        return Typeface.create(m166a(b), i);
    }
}

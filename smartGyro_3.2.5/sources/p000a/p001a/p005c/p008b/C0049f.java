package p000a.p001a.p005c.p008b;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import p000a.p001a.p005c.p006a.p007a.C0028c;

/* renamed from: a.a.c.b.f */
public class C0049f extends C0047d {

    /* renamed from: a */
    protected final Class f131a;

    /* renamed from: b */
    protected final Constructor f132b;

    /* renamed from: c */
    protected final Method f133c;

    /* renamed from: d */
    protected final Method f134d;

    /* renamed from: e */
    protected final Method f135e;

    /* renamed from: f */
    protected final Method f136f;

    /* renamed from: g */
    protected final Method f137g;

    public C0049f() {
        Method method;
        Method method2;
        Method method3;
        Method method4;
        Constructor constructor;
        Method method5;
        Class cls = null;
        try {
            Class a = mo179a();
            constructor = mo184e(a);
            method4 = mo181b(a);
            method3 = mo182c(a);
            method2 = mo185f(a);
            method = mo180a(a);
            Class cls2 = a;
            method5 = mo183d(a);
            cls = cls2;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class " + e.getClass().getName(), e);
            method5 = null;
            constructor = null;
            method4 = null;
            method3 = null;
            method2 = null;
            method = null;
        }
        this.f131a = cls;
        this.f132b = constructor;
        this.f133c = method4;
        this.f134d = method3;
        this.f135e = method2;
        this.f136f = method;
        this.f137g = method5;
    }

    /* renamed from: a */
    private boolean m172a(Context context, Object obj, String str, int i, int i2, int i3, FontVariationAxis[] fontVariationAxisArr) {
        try {
            return ((Boolean) this.f133c.invoke(obj, new Object[]{context.getAssets(), str, 0, false, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), fontVariationAxisArr})).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    private boolean m173a(Object obj, ByteBuffer byteBuffer, int i, int i2, int i3) {
        try {
            return ((Boolean) this.f134d.invoke(obj, new Object[]{byteBuffer, Integer.valueOf(i), null, Integer.valueOf(i2), Integer.valueOf(i3)})).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: b */
    private void m174b(Object obj) {
        try {
            this.f136f.invoke(obj, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: b */
    private boolean m175b() {
        if (this.f133c == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        return this.f133c != null;
    }

    /* renamed from: c */
    private Object m176c() {
        try {
            return this.f132b.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: c */
    private boolean m177c(Object obj) {
        try {
            return ((Boolean) this.f135e.invoke(obj, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public Typeface mo176a(Context context, C0028c.C0030b bVar, Resources resources, int i) {
        if (!m175b()) {
            return super.mo176a(context, bVar, resources, i);
        }
        Object c = m176c();
        for (C0028c.C0031c cVar : bVar.mo158a()) {
            if (!m172a(context, c, cVar.mo159a(), cVar.mo161c(), cVar.mo163e(), cVar.mo164f() ? 1 : 0, FontVariationAxis.fromFontVariationSettings(cVar.mo162d()))) {
                m174b(c);
                return null;
            }
        }
        if (!m177c(c)) {
            return null;
        }
        return mo178a(c);
    }

    /* renamed from: a */
    public Typeface mo177a(Context context, Resources resources, int i, String str, int i2) {
        if (!m175b()) {
            return super.mo177a(context, resources, i, str, i2);
        }
        Object c = m176c();
        if (!m172a(context, c, str, 0, -1, -1, (FontVariationAxis[]) null)) {
            m174b(c);
            return null;
        } else if (!m177c(c)) {
            return null;
        } else {
            return mo178a(c);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0049, code lost:
        r13 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004a, code lost:
        r14 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004e, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004f, code lost:
        r10 = r14;
        r14 = r13;
        r13 = r10;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface mo175a(android.content.Context r12, android.os.CancellationSignal r13, p000a.p001a.p005c.p011d.C0064f.C0066b[] r14, int r15) {
        /*
            r11 = this;
            int r0 = r14.length
            r1 = 1
            r2 = 0
            if (r0 >= r1) goto L_0x0006
            return r2
        L_0x0006:
            boolean r0 = r11.m175b()
            if (r0 != 0) goto L_0x0064
            a.a.c.d.f$b r14 = r11.mo192a((p000a.p001a.p005c.p011d.C0064f.C0066b[]) r14, (int) r15)
            android.content.ContentResolver r12 = r12.getContentResolver()
            android.net.Uri r15 = r14.mo234c()     // Catch:{ IOException -> 0x0063 }
            java.lang.String r0 = "r"
            android.os.ParcelFileDescriptor r12 = r12.openFileDescriptor(r15, r0, r13)     // Catch:{ IOException -> 0x0063 }
            if (r12 != 0) goto L_0x0026
            if (r12 == 0) goto L_0x0025
            r12.close()     // Catch:{ IOException -> 0x0063 }
        L_0x0025:
            return r2
        L_0x0026:
            android.graphics.Typeface$Builder r13 = new android.graphics.Typeface$Builder     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            java.io.FileDescriptor r15 = r12.getFileDescriptor()     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            r13.<init>(r15)     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            int r15 = r14.mo235d()     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            android.graphics.Typeface$Builder r13 = r13.setWeight(r15)     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            boolean r14 = r14.mo236e()     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            android.graphics.Typeface$Builder r13 = r13.setItalic(r14)     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            android.graphics.Typeface r13 = r13.build()     // Catch:{ Throwable -> 0x004c, all -> 0x0049 }
            if (r12 == 0) goto L_0x0048
            r12.close()     // Catch:{ IOException -> 0x0063 }
        L_0x0048:
            return r13
        L_0x0049:
            r13 = move-exception
            r14 = r2
            goto L_0x0052
        L_0x004c:
            r13 = move-exception
            throw r13     // Catch:{ all -> 0x004e }
        L_0x004e:
            r14 = move-exception
            r10 = r14
            r14 = r13
            r13 = r10
        L_0x0052:
            if (r12 == 0) goto L_0x0062
            if (r14 == 0) goto L_0x005f
            r12.close()     // Catch:{ Throwable -> 0x005a }
            goto L_0x0062
        L_0x005a:
            r12 = move-exception
            r14.addSuppressed(r12)     // Catch:{ IOException -> 0x0063 }
            goto L_0x0062
        L_0x005f:
            r12.close()     // Catch:{ IOException -> 0x0063 }
        L_0x0062:
            throw r13     // Catch:{ IOException -> 0x0063 }
        L_0x0063:
            return r2
        L_0x0064:
            java.util.Map r12 = p000a.p001a.p005c.p011d.C0064f.m234a((android.content.Context) r12, (p000a.p001a.p005c.p011d.C0064f.C0066b[]) r14, (android.os.CancellationSignal) r13)
            java.lang.Object r13 = r11.m176c()
            int r0 = r14.length
            r3 = 0
            r9 = 0
        L_0x006f:
            if (r9 >= r0) goto L_0x009c
            r4 = r14[r9]
            android.net.Uri r5 = r4.mo234c()
            java.lang.Object r5 = r12.get(r5)
            java.nio.ByteBuffer r5 = (java.nio.ByteBuffer) r5
            if (r5 != 0) goto L_0x0080
            goto L_0x0099
        L_0x0080:
            int r6 = r4.mo233b()
            int r7 = r4.mo235d()
            boolean r8 = r4.mo236e()
            r3 = r11
            r4 = r13
            boolean r3 = r3.m173a((java.lang.Object) r4, (java.nio.ByteBuffer) r5, (int) r6, (int) r7, (int) r8)
            if (r3 != 0) goto L_0x0098
            r11.m174b((java.lang.Object) r13)
            return r2
        L_0x0098:
            r3 = 1
        L_0x0099:
            int r9 = r9 + 1
            goto L_0x006f
        L_0x009c:
            if (r3 != 0) goto L_0x00a2
            r11.m174b((java.lang.Object) r13)
            return r2
        L_0x00a2:
            boolean r12 = r11.m177c((java.lang.Object) r13)
            if (r12 != 0) goto L_0x00a9
            return r2
        L_0x00a9:
            android.graphics.Typeface r12 = r11.mo178a((java.lang.Object) r13)
            android.graphics.Typeface r12 = android.graphics.Typeface.create(r12, r15)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: p000a.p001a.p005c.p008b.C0049f.mo175a(android.content.Context, android.os.CancellationSignal, a.a.c.d.f$b[], int):android.graphics.Typeface");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Typeface mo178a(Object obj) {
        try {
            Object newInstance = Array.newInstance(this.f131a, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) this.f137g.invoke((Object) null, new Object[]{newInstance, -1, -1});
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Class mo179a() {
        return Class.forName("android.graphics.FontFamily");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Method mo180a(Class cls) {
        return cls.getMethod("abortCreation", new Class[0]);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public Method mo181b(Class cls) {
        Class cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromAssetManager", new Class[]{AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, cls2, cls2, cls2, FontVariationAxis[].class});
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public Method mo182c(Class cls) {
        Class cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromBuffer", new Class[]{ByteBuffer.class, cls2, FontVariationAxis[].class, cls2, cls2});
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public Method mo183d(Class cls) {
        Class cls2 = Integer.TYPE;
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", new Class[]{Array.newInstance(cls, 1).getClass(), cls2, cls2});
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public Constructor mo184e(Class cls) {
        return cls.getConstructor(new Class[0]);
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public Method mo185f(Class cls) {
        return cls.getMethod("freeze", new Class[0]);
    }
}

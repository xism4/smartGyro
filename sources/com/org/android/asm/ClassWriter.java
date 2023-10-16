package com.org.android.asm;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.util.Log;
import com.org.android.ui.asm.e;
import com.org.android.ui.asm.f;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

public class ClassWriter extends a {
    protected final Method a;
    protected final Method b;
    protected final Method c;
    protected final Method d;
    protected final Method e;
    protected final Constructor f;
    protected final Class version;

    public ClassWriter() {
        Method $r8;
        Method $r7;
        Method $r6;
        Method $r5;
        Method $r4;
        Constructor $r3;
        Class $r1 = null;
        try {
            Class $r2 = get();
            $r3 = invoke($r2);
            $r4 = get($r2);
            $r5 = find($r2);
            $r6 = findGetListenerMethod($r2);
            $r7 = getMethod($r2);
            $r8 = getInstance($r2);
            $r1 = $r2;
        } catch (ClassNotFoundException | NoSuchMethodException $r9) {
            Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class " + $r9.getClass().getName(), $r9);
            $r8 = null;
            $r3 = null;
            $r4 = null;
            $r5 = null;
            $r6 = null;
            $r7 = null;
        }
        this.version = $r1;
        this.f = $r3;
        this.e = $r4;
        this.a = $r5;
        this.b = $r6;
        this.d = $r7;
        this.c = $r8;
    }

    private Object a() {
        try {
            return this.f.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException $r4) {
            throw new RuntimeException($r4);
        }
    }

    private boolean a(Context context, Object obj, String str, int i, int i2, int i3, FontVariationAxis[] fontVariationAxisArr) {
        Method $r2 = this.e;
        Object[] $r6 = new Object[8];
        try {
            $r6[0] = context.getAssets();
            $r6[1] = str;
            $r6[2] = 0;
            $r6[3] = false;
            $r6[4] = Integer.valueOf(i);
            $r6[5] = Integer.valueOf(i2);
            $r6[6] = Integer.valueOf(i3);
            $r6[7] = fontVariationAxisArr;
            return ((Boolean) $r2.invoke(obj, $r6)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException $r10) {
            throw new RuntimeException($r10);
        }
    }

    private boolean a(Object obj) {
        try {
            return ((Boolean) this.b.invoke(obj, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException $r5) {
            throw new RuntimeException($r5);
        }
    }

    private boolean a(Object obj, ByteBuffer byteBuffer, int i, int i2, int i3) {
        Method $r1 = this.a;
        Object[] $r4 = new Object[5];
        $r4[0] = byteBuffer;
        try {
            $r4[1] = Integer.valueOf(i);
            $r4[2] = null;
            $r4[3] = Integer.valueOf(i2);
            $r4[4] = Integer.valueOf(i3);
            return ((Boolean) $r1.invoke(obj, $r4)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException $r7) {
            throw new RuntimeException($r7);
        }
    }

    private void b(Object obj) {
        try {
            this.d.invoke(obj, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException $r4) {
            throw new RuntimeException($r4);
        }
    }

    private boolean b() {
        if (this.e == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        return this.e != null;
    }

    public Typeface a(Context context, Resources resources, int i, String str, int i2) {
        if (!b()) {
            return super.a(context, resources, i, str, i2);
        }
        Object $r5 = a();
        if (!a(context, $r5, str, 0, -1, -1, (FontVariationAxis[]) null)) {
            b($r5);
            return null;
        } else if (!a($r5)) {
            return null;
        } else {
            return get($r5);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005e, code lost:
        r19 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005f, code lost:
        if (r13 != null) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0061, code lost:
        if (r18 != null) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r13.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0067, code lost:
        r20 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r18.addSuppressed(r20);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r13.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0073, code lost:
        throw r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface a(android.content.Context r34, android.os.CancellationSignal r35, com.org.android.manager.Label[] r36, int r37) {
        /*
            r33 = this;
            r0 = r36
            int r6 = r0.length
            r7 = 1
            if (r6 >= r7) goto L_0x0008
            r8 = 0
            return r8
        L_0x0008:
            r0 = r33
            boolean r9 = r0.b()
            if (r9 != 0) goto L_0x0074
            r0 = r33
            r1 = r36
            r2 = r37
            com.org.android.manager.Label r10 = r0.a((com.org.android.manager.Label[]) r1, (int) r2)
            r0 = r34
            android.content.ContentResolver r11 = r0.getContentResolver()
            android.net.Uri r12 = r10.getValue()     // Catch:{ IOException -> 0x00f8 }
            java.lang.String r14 = "r"
            r0 = r35
            android.os.ParcelFileDescriptor r13 = r11.openFileDescriptor(r12, r14, r0)     // Catch:{ IOException -> 0x00f8 }
            if (r13 != 0) goto L_0x0035
            if (r13 == 0) goto L_0x00ff
            r13.close()     // Catch:{ IOException -> 0x00f8 }
            r8 = 0
            return r8
        L_0x0035:
            android.graphics.Typeface$Builder r15 = new android.graphics.Typeface$Builder     // Catch:{ Throwable -> 0x005c }
            java.io.FileDescriptor r16 = r13.getFileDescriptor()     // Catch:{ Throwable -> 0x005c }
            r0 = r16
            r15.<init>(r0)     // Catch:{ Throwable -> 0x005c }
            int r37 = r10.b()     // Catch:{ Throwable -> 0x005c }
            r0 = r37
            android.graphics.Typeface$Builder r15 = r15.setWeight(r0)     // Catch:{ Throwable -> 0x005c }
            boolean r9 = r10.a()     // Catch:{ Throwable -> 0x005c }
            android.graphics.Typeface$Builder r15 = r15.setItalic(r9)     // Catch:{ Throwable -> 0x005c }
            android.graphics.Typeface r17 = r15.build()     // Catch:{ Throwable -> 0x005c }
            if (r13 == 0) goto L_0x0101
            r13.close()     // Catch:{ IOException -> 0x00fb }
            return r17
        L_0x005c:
            r18 = move-exception
            throw r18     // Catch:{ Throwable -> 0x005e }
        L_0x005e:
            r19 = move-exception
            if (r13 == 0) goto L_0x0073
            if (r18 == 0) goto L_0x0070
            r13.close()     // Catch:{ Throwable -> 0x0067 }
            goto L_0x0073
        L_0x0067:
            r20 = move-exception
            r0 = r18
            r1 = r20
            r0.addSuppressed(r1)     // Catch:{ IOException -> 0x00fe }
            goto L_0x0073
        L_0x0070:
            r13.close()     // Catch:{ IOException -> 0x00fe }
        L_0x0073:
            throw r19     // Catch:{ IOException -> 0x00fe }
        L_0x0074:
            r0 = r34
            r1 = r36
            r2 = r35
            java.util.Map r21 = com.org.android.manager.i.a((android.content.Context) r0, (com.org.android.manager.Label[]) r1, (android.os.CancellationSignal) r2)
            r0 = r33
            java.lang.Object r22 = r0.a()
            r0 = r36
            int r6 = r0.length
            r9 = 0
            r23 = 0
        L_0x008a:
            r0 = r23
            if (r0 >= r6) goto L_0x00d0
            r10 = r36[r23]
            android.net.Uri r12 = r10.getValue()
            r0 = r21
            java.lang.Object r24 = r0.get(r12)
            r26 = r24
            java.nio.ByteBuffer r26 = (java.nio.ByteBuffer) r26
            r25 = r26
            if (r25 != 0) goto L_0x00a3
            goto L_0x00cd
        L_0x00a3:
            int r27 = r10.d()
            int r28 = r10.b()
            boolean r9 = r10.a()
            r29 = r9
            r0 = r33
            r1 = r22
            r2 = r25
            r3 = r27
            r4 = r28
            r5 = r29
            boolean r9 = r0.a((java.lang.Object) r1, (java.nio.ByteBuffer) r2, (int) r3, (int) r4, (int) r5)
            if (r9 != 0) goto L_0x00cc
            r0 = r33
            r1 = r22
            r0.b(r1)
            r8 = 0
            return r8
        L_0x00cc:
            r9 = 1
        L_0x00cd:
            int r23 = r23 + 1
            goto L_0x008a
        L_0x00d0:
            if (r9 != 0) goto L_0x00db
            r0 = r33
            r1 = r22
            r0.b(r1)
            r8 = 0
            return r8
        L_0x00db:
            r0 = r33
            r1 = r22
            boolean r9 = r0.a(r1)
            if (r9 != 0) goto L_0x00e7
            r8 = 0
            return r8
        L_0x00e7:
            r0 = r33
            r1 = r22
            android.graphics.Typeface r17 = r0.get((java.lang.Object) r1)
            r0 = r17
            r1 = r37
            android.graphics.Typeface r17 = android.graphics.Typeface.create(r0, r1)
            return r17
        L_0x00f8:
            r30 = move-exception
            r8 = 0
            return r8
        L_0x00fb:
            r31 = move-exception
            r8 = 0
            return r8
        L_0x00fe:
            r32 = move-exception
        L_0x00ff:
            r8 = 0
            return r8
        L_0x0101:
            return r17
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.android.asm.ClassWriter.a(android.content.Context, android.os.CancellationSignal, com.org.android.manager.Label[], int):android.graphics.Typeface");
    }

    public Typeface a(Context context, e eVar, Resources resources, int i) {
        if (!b()) {
            return super.a(context, eVar, resources, i);
        }
        Object $r5 = a();
        for (f $r7 : eVar.a()) {
            if (!a(context, $r5, $r7.a(), $r7.r(), $r7.f(), (int) $r7.c(), FontVariationAxis.fromFontVariationSettings($r7.i()))) {
                b($r5);
                return null;
            }
        }
        if (!a($r5)) {
            return null;
        }
        return get($r5);
    }

    /* access modifiers changed from: protected */
    public Method find(Class cls) {
        Class $r4 = Integer.TYPE;
        return cls.getMethod("addFontFromBuffer", new Class[]{ByteBuffer.class, $r4, FontVariationAxis[].class, $r4, $r4});
    }

    /* access modifiers changed from: protected */
    public Method findGetListenerMethod(Class cls) {
        return cls.getMethod("freeze", new Class[0]);
    }

    /* access modifiers changed from: protected */
    public Typeface get(Object obj) {
        try {
            Object $r3 = Array.newInstance(this.version, 1);
            Array.set($r3, 0, obj);
            Method $r4 = this.c;
            Object[] $r5 = new Object[3];
            $r5[0] = $r3;
            $r5[1] = -1;
            $r5[2] = -1;
            return (Typeface) $r4.invoke((Object) null, $r5);
        } catch (IllegalAccessException | InvocationTargetException $r8) {
            throw new RuntimeException($r8);
        }
    }

    /* access modifiers changed from: protected */
    public Class get() {
        return Class.forName("android.graphics.FontFamily");
    }

    /* access modifiers changed from: protected */
    public Method get(Class cls) {
        Class $r4 = Integer.TYPE;
        return cls.getMethod("addFontFromAssetManager", new Class[]{AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, $r4, $r4, $r4, FontVariationAxis[].class});
    }

    /* access modifiers changed from: protected */
    public Method getInstance(Class cls) {
        Object $r2 = Array.newInstance(cls, 1);
        Class $r4 = Integer.TYPE;
        Method $r5 = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", new Class[]{$r2.getClass(), $r4, $r4});
        $r5.setAccessible(true);
        return $r5;
    }

    /* access modifiers changed from: protected */
    public Method getMethod(Class cls) {
        return cls.getMethod("abortCreation", new Class[0]);
    }

    /* access modifiers changed from: protected */
    public Constructor invoke(Class cls) {
        return cls.getConstructor(new Class[0]);
    }
}

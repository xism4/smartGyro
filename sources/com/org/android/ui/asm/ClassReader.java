package com.org.android.ui.asm;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.TypedValue;

public final class ClassReader {
    public static Typeface a(Context context, int i, TypedValue typedValue, int i2, k kVar) {
        if (context.isRestricted()) {
            return null;
        }
        return a(context, i, typedValue, i2, kVar, (Handler) null, true);
    }

    private static Typeface a(Context context, int i, TypedValue typedValue, int i2, k kVar, Handler handler, boolean z) {
        Resources $r4 = context.getResources();
        $r4.getValue(i, typedValue, true);
        Typeface $r5 = a(context, $r4, typedValue, i, i2, kVar, handler, z);
        if ($r5 != null || kVar != null) {
            return $r5;
        }
        throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(i) + " could not be retrieved.");
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0124 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Typeface a(android.content.Context r22, android.content.res.Resources r23, android.util.TypedValue r24, int r25, int r26, com.org.android.ui.asm.k r27, android.os.Handler r28, boolean r29) {
        /*
            r0 = r24
            java.lang.CharSequence r8 = r0.string
            if (r8 == 0) goto L_0x00d5
            java.lang.String r9 = r8.toString()
            java.lang.String r11 = "res/"
            boolean r10 = r9.startsWith(r11)
            if (r10 != 0) goto L_0x001e
            if (r27 == 0) goto L_0x011e
            r12 = -3
            r0 = r27
            r1 = r28
            r0.a((int) r12, (android.os.Handler) r1)
            r13 = 0
            return r13
        L_0x001e:
            r0 = r23
            r1 = r25
            r2 = r26
            android.graphics.Typeface r14 = com.org.android.asm.c.a(r0, r1, r2)
            if (r14 == 0) goto L_0x0034
            if (r27 == 0) goto L_0x0120
            r0 = r27
            r1 = r28
            r0.a((android.graphics.Typeface) r14, (android.os.Handler) r1)
            return r14
        L_0x0034:
            java.lang.String r15 = r9.toLowerCase()     // Catch:{ XmlPullParserException -> 0x00a8, IOException -> 0x009d }
            java.lang.String r11 = ".xml"
            boolean r10 = r15.endsWith(r11)     // Catch:{ XmlPullParserException -> 0x00a8, IOException -> 0x009d }
            if (r10 == 0) goto L_0x007c
            r0 = r23
            r1 = r25
            android.content.res.XmlResourceParser r16 = r0.getXml(r1)     // Catch:{ XmlPullParserException -> 0x00a8, IOException -> 0x009d }
            r0 = r16
            r1 = r23
            com.org.android.ui.asm.l r17 = com.org.android.ui.asm.Type.createFromXml(r0, r1)     // Catch:{ XmlPullParserException -> 0x00a8, IOException -> 0x009d }
            if (r17 != 0) goto L_0x0067
            java.lang.String r11 = "ResourcesCompat"
            java.lang.String r18 = "Failed to find font-family tag"
            r0 = r18
            android.util.Log.e(r11, r0)     // Catch:{ XmlPullParserException -> 0x00a8, IOException -> 0x009d }
            if (r27 == 0) goto L_0x0121
            r12 = -3
            r0 = r27
            r1 = r28
            r0.a((int) r12, (android.os.Handler) r1)     // Catch:{ XmlPullParserException -> 0x00a8, IOException -> 0x009d }
            r13 = 0
            return r13
        L_0x0067:
            r0 = r22
            r1 = r17
            r2 = r23
            r3 = r25
            r4 = r26
            r5 = r27
            r6 = r28
            r7 = r29
            android.graphics.Typeface r14 = com.org.android.asm.c.a(r0, r1, r2, r3, r4, r5, r6, r7)     // Catch:{ XmlPullParserException -> 0x00a8, IOException -> 0x009d }
            return r14
        L_0x007c:
            r0 = r22
            r1 = r23
            r2 = r25
            r3 = r26
            android.graphics.Typeface r14 = com.org.android.asm.c.a(r0, r1, r2, r9, r3)     // Catch:{ XmlPullParserException -> 0x00a8, IOException -> 0x009d }
            if (r27 == 0) goto L_0x0123
            if (r14 == 0) goto L_0x0094
            r0 = r27
            r1 = r28
            r0.a((android.graphics.Typeface) r14, (android.os.Handler) r1)     // Catch:{ XmlPullParserException -> 0x00a8, IOException -> 0x009d }
            return r14
        L_0x0094:
            r12 = -3
            r0 = r27
            r1 = r28
            r0.a((int) r12, (android.os.Handler) r1)     // Catch:{ XmlPullParserException -> 0x00a8, IOException -> 0x009d }
            return r14
        L_0x009d:
            r19 = move-exception
            java.lang.StringBuilder r20 = new java.lang.StringBuilder
            r0 = r20
            r0.<init>()
            java.lang.String r15 = "Failed to read xml resource "
            goto L_0x00b2
        L_0x00a8:
            r19 = move-exception
            java.lang.StringBuilder r20 = new java.lang.StringBuilder
            r0 = r20
            r0.<init>()
            java.lang.String r15 = "Failed to parse xml resource "
        L_0x00b2:
            r0 = r20
            r0.append(r15)
            r0 = r20
            r0.append(r9)
            r0 = r20
            java.lang.String r9 = r0.toString()
            java.lang.String r11 = "ResourcesCompat"
            r0 = r19
            android.util.Log.e(r11, r9, r0)
            if (r27 == 0) goto L_0x0124
            r12 = -3
            r0 = r27
            r1 = r28
            r0.a((int) r12, (android.os.Handler) r1)
            r13 = 0
            return r13
        L_0x00d5:
            android.content.res.Resources$NotFoundException r21 = new android.content.res.Resources$NotFoundException
            java.lang.StringBuilder r20 = new java.lang.StringBuilder
            r0 = r20
            r0.<init>()
            java.lang.String r11 = "Resource \""
            r0 = r20
            r0.append(r11)
            r0 = r23
            r1 = r25
            java.lang.String r9 = r0.getResourceName(r1)
            r0 = r20
            r0.append(r9)
            java.lang.String r11 = "\" ("
            r0 = r20
            r0.append(r11)
            r0 = r25
            java.lang.String r9 = java.lang.Integer.toHexString(r0)
            r0 = r20
            r0.append(r9)
            java.lang.String r11 = ") is not a Font: "
            r0 = r20
            r0.append(r11)
            r0 = r20
            r1 = r24
            r0.append(r1)
            r0 = r20
            java.lang.String r9 = r0.toString()
            r0 = r21
            r0.<init>(r9)
            throw r21
        L_0x011e:
            r13 = 0
            return r13
        L_0x0120:
            return r14
        L_0x0121:
            r13 = 0
            return r13
        L_0x0123:
            return r14
        L_0x0124:
            r13 = 0
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.android.ui.asm.ClassReader.a(android.content.Context, android.content.res.Resources, android.util.TypedValue, int, int, com.org.android.ui.asm.k, android.os.Handler, boolean):android.graphics.Typeface");
    }

    public static Drawable getDrawable(Resources resources, int i, Resources.Theme theme) {
        return Build.VERSION.SDK_INT >= 21 ? resources.getDrawable(i, theme) : resources.getDrawable(i);
    }
}

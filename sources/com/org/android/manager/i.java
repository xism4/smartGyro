package com.org.android.manager;

import a.a.c.d.f;
import a.a.c.d.k;
import a.a.c.f.e;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.CancellationSignal;
import com.org.android.asm.ByteVector;
import com.org.android.asm.c;
import com.org.android.ui.asm.Type;
import com.org.android.util.LruCache;
import com.org.android.util.SimpleArrayMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class i {
    private static final Comparator<byte[]> a = new Version$BuildAwareOrder();
    static final a.a.c.f.i<String, ArrayList<k.a<f.c>>> b = new SimpleArrayMap();
    static final Object c = new Object();
    private static final Connection s = new Connection("fonts", 10, 10000);
    static final e<String, Typeface> t = new LruCache(16);

    public static ProviderInfo a(PackageManager packageManager, h hVar, Resources resources) {
        String $r3 = hVar.c();
        ProviderInfo $r4 = packageManager.resolveContentProvider($r3, 0);
        if ($r4 == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + $r3);
        } else if ($r4.packageName.equals(hVar.getGroupId())) {
            List $r9 = toArray(packageManager.getPackageInfo($r4.packageName, 64).signatures);
            Collections.sort($r9, a);
            List $r11 = a(hVar, resources);
            for (int $i0 = 0; $i0 < $r11.size(); $i0++) {
                ArrayList $r12 = new ArrayList((Collection) $r11.get($i0));
                Collections.sort($r12, a);
                if (equals($r9, $r12)) {
                    return $r4;
                }
            }
            return null;
        } else {
            throw new PackageManager.NameNotFoundException("Found content provider " + $r3 + ", but package was not " + hVar.getGroupId());
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: com.org.android.manager.e} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: com.org.android.manager.c} */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ad, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00c8, code lost:
        s.close(r12, new com.org.android.manager.e(r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00d8, code lost:
        return null;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Typeface a(android.content.Context r24, com.org.android.manager.h r25, com.org.android.ui.asm.k r26, android.os.Handler r27, boolean r28, int r29, int r30) {
        /*
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r0 = r25
            java.lang.String r4 = r0.a()
            r3.append(r4)
            java.lang.String r5 = "-"
            r3.append(r5)
            r0 = r30
            r3.append(r0)
            java.lang.String r4 = r3.toString()
            a.a.c.f.e<java.lang.String, android.graphics.Typeface> r6 = t
            java.lang.Object r7 = r6.get(r4)
            r9 = r7
            android.graphics.Typeface r9 = (android.graphics.Typeface) r9
            r8 = r9
            if (r8 == 0) goto L_0x0030
            if (r26 == 0) goto L_0x00e0
            r0 = r26
            r0.a(r8)
            return r8
        L_0x0030:
            if (r28 == 0) goto L_0x005f
            r10 = -1
            r0 = r29
            if (r0 != r10) goto L_0x005f
            r0 = r24
            r1 = r25
            r2 = r30
            com.org.android.manager.f r11 = a((android.content.Context) r0, (com.org.android.manager.h) r1, (int) r2)
            if (r26 == 0) goto L_0x005c
            int r0 = r11.f
            r29 = r0
            if (r29 != 0) goto L_0x0053
            android.graphics.Typeface r8 = r11.t
            r0 = r26
            r1 = r27
            r0.a((android.graphics.Typeface) r8, (android.os.Handler) r1)
            goto L_0x005c
        L_0x0053:
            r0 = r26
            r1 = r29
            r2 = r27
            r0.a((int) r1, (android.os.Handler) r2)
        L_0x005c:
            android.graphics.Typeface r8 = r11.t
            return r8
        L_0x005f:
            com.org.android.manager.a r12 = new com.org.android.manager.a
            r0 = r24
            r1 = r25
            r2 = r30
            r12.<init>(r0, r1, r2, r4)
            if (r28 == 0) goto L_0x007d
            com.org.android.manager.Connection r13 = s
            r0 = r29
            java.lang.Object r7 = r13.get(r12, r0)     // Catch:{ InterruptedException -> 0x00dc }
            r14 = r7
            com.org.android.manager.f r14 = (com.org.android.manager.f) r14
            r11 = r14
            android.graphics.Typeface r8 = r11.t
            return r8
        L_0x007d:
            if (r26 != 0) goto L_0x0081
            r15 = 0
            goto L_0x008a
        L_0x0081:
            com.org.android.manager.d r15 = new com.org.android.manager.d
            r0 = r26
            r1 = r27
            r15.<init>(r0, r1)
        L_0x008a:
            java.lang.Object r7 = c
            monitor-enter(r7)
            a.a.c.f.i<java.lang.String, java.util.ArrayList<a.a.c.d.k$a<a.a.c.d.f$c>>> r16 = b     // Catch:{ Throwable -> 0x00d9 }
            r0 = r16
            boolean r28 = r0.containsKey(r4)     // Catch:{ Throwable -> 0x00d9 }
            if (r28 == 0) goto L_0x00b0
            if (r15 == 0) goto L_0x00ac
            a.a.c.f.i<java.lang.String, java.util.ArrayList<a.a.c.d.k$a<a.a.c.d.f$c>>> r16 = b     // Catch:{ Throwable -> 0x00d9 }
            r0 = r16
            java.lang.Object r17 = r0.get(r4)     // Catch:{ Throwable -> 0x00d9 }
            r19 = r17
            java.util.ArrayList r19 = (java.util.ArrayList) r19     // Catch:{ Throwable -> 0x00d9 }
            r18 = r19
            r0 = r18
            r0.add(r15)     // Catch:{ Throwable -> 0x00d9 }
        L_0x00ac:
            monitor-exit(r7)     // Catch:{ Throwable -> 0x00d9 }
            r20 = 0
            return r20
        L_0x00b0:
            if (r15 == 0) goto L_0x00c7
            java.util.ArrayList r18 = new java.util.ArrayList     // Catch:{ Throwable -> 0x00d9 }
            r0 = r18
            r0.<init>()     // Catch:{ Throwable -> 0x00d9 }
            r0 = r18
            r0.add(r15)     // Catch:{ Throwable -> 0x00d9 }
            a.a.c.f.i<java.lang.String, java.util.ArrayList<a.a.c.d.k$a<a.a.c.d.f$c>>> r16 = b     // Catch:{ Throwable -> 0x00d9 }
            r0 = r16
            r1 = r18
            r0.put(r4, r1)     // Catch:{ Throwable -> 0x00d9 }
        L_0x00c7:
            monitor-exit(r7)     // Catch:{ Throwable -> 0x00d9 }
            com.org.android.manager.Connection r13 = s
            com.org.android.manager.e r21 = new com.org.android.manager.e
            r0 = r21
            r0.<init>(r4)
            r0 = r21
            r13.close(r12, r0)
            r20 = 0
            return r20
        L_0x00d9:
            r22 = move-exception
            monitor-exit(r7)     // Catch:{ Throwable -> 0x00d9 }
            throw r22
        L_0x00dc:
            r23 = move-exception
            r20 = 0
            return r20
        L_0x00e0:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.android.manager.i.a(android.content.Context, com.org.android.manager.h, com.org.android.ui.asm.k, android.os.Handler, boolean, int, int):android.graphics.Typeface");
    }

    static f a(Context context, h hVar, int i) {
        try {
            g $r3 = a(context, (CancellationSignal) null, hVar);
            byte $b2 = -3;
            if ($r3.a() == 0) {
                Typeface $r5 = c.a(context, (CancellationSignal) null, $r3.b(), i);
                if ($r5 != null) {
                    $b2 = 0;
                }
                return new f($r5, $b2);
            }
            if ($r3.a() == 1) {
                $b2 = -2;
            }
            return new f((Typeface) null, $b2);
        } catch (PackageManager.NameNotFoundException e) {
            return new f((Typeface) null, -1);
        }
    }

    public static g a(Context context, CancellationSignal cancellationSignal, h hVar) {
        ProviderInfo $r5 = a(context.getPackageManager(), hVar, context.getResources());
        return $r5 == null ? new g(1, (Label[]) null) : new g(0, load(context, hVar, $r5.authority, cancellationSignal));
    }

    private static List a(h hVar, Resources resources) {
        return hVar.getTitle() != null ? hVar.getTitle() : Type.getValue(resources, hVar.b());
    }

    public static Map a(Context context, Label[] labelArr, CancellationSignal cancellationSignal) {
        HashMap $r2 = new HashMap();
        for (Label $r4 : labelArr) {
            if ($r4.c() == 0) {
                Uri $r5 = $r4.getValue();
                if (!$r2.containsKey($r5)) {
                    $r2.put($r5, ByteVector.read(context, cancellationSignal, $r5));
                }
            }
        }
        return Collections.unmodifiableMap($r2);
    }

    private static boolean equals(List list, List list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int $i0 = 0; $i0 < list.size(); $i0++) {
            if (!Arrays.equals((byte[]) list.get($i0), (byte[]) list2.get($i0))) {
                return false;
            }
        }
        return true;
    }

    static Label[] load(Context context, h hVar, String str, CancellationSignal cancellationSignal) {
        boolean $z0;
        ArrayList $r4 = new ArrayList();
        Uri $r6 = new Uri.Builder().scheme("content").authority(str).build();
        Uri $r7 = new Uri.Builder().scheme("content").authority(str).appendPath("file").build();
        Cursor $r8 = null;
        try {
            $r8 = Build.VERSION.SDK_INT > 16 ? context.getContentResolver().query($r6, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{hVar.e()}, (String) null, cancellationSignal) : context.getContentResolver().query($r6, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{hVar.e()}, (String) null);
            if ($r8 != null) {
                if ($r8.getCount() > 0) {
                    int $i0 = $r8.getColumnIndex("result_code");
                    $r4 = new ArrayList();
                    int $i1 = $r8.getColumnIndex("_id");
                    int $i2 = $r8.getColumnIndex("file_id");
                    int $i3 = $r8.getColumnIndex("font_ttc_index");
                    int $i4 = $r8.getColumnIndex("font_weight");
                    int $i5 = $r8.getColumnIndex("font_italic");
                    while ($r8.moveToNext()) {
                        int $i6 = $i0 != -1 ? $r8.getInt($i0) : 0;
                        int $i7 = $i3 != -1 ? $r8.getInt($i3) : 0;
                        Uri $r12 = $i2 == -1 ? ContentUris.withAppendedId($r6, $r8.getLong($i1)) : ContentUris.withAppendedId($r7, $r8.getLong($i2));
                        int $i9 = $i4 != -1 ? $r8.getInt($i4) : 400;
                        if ($i5 != -1) {
                            if ($r8.getInt($i5) == 1) {
                                $z0 = true;
                                $r4.add(new Label($r12, $i7, $i9, $z0, $i6));
                            }
                        }
                        $z0 = false;
                        $r4.add(new Label($r12, $i7, $i9, $z0, $i6));
                    }
                }
            }
            Label[] $r14 = new Label[0];
            Label[] labelArr = $r14;
            return (Label[]) $r4.toArray($r14);
        } finally {
            if ($r8 != null) {
                $r8.close();
            }
        }
    }

    private static List toArray(Signature[] signatureArr) {
        ArrayList $r1 = new ArrayList();
        for (Signature $r3 : signatureArr) {
            $r1.add($r3.toByteArray());
        }
        return $r1;
    }
}

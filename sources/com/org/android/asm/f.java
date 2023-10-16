package com.org.android.asm;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import com.org.android.manager.Label;
import com.org.android.ui.asm.e;
import java.io.IOException;
import java.io.InputStream;

class f {
    f() {
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.org.android.asm.d, com.org.android.asm.h] */
    private com.org.android.ui.asm.f a(e eVar, int i) {
        return (com.org.android.ui.asm.f) a(eVar.a(), i, new h(this));
    }

    private static Object a(Object[] objArr, int i, d dVar) {
        short $s2 = (i & 1) == 0 ? (short) 400 : 700;
        boolean $z0 = (i & 2) != 0;
        Object $r3 = null;
        int $i3 = Integer.MAX_VALUE;
        for (Object $r2 : objArr) {
            int $i4 = (Math.abs(dVar.f($r2) - $s2) * 2) + (dVar.a($r2) == $z0 ? (byte) 0 : 1);
            if ($r3 == null || $i3 > $i4) {
                $r3 = $r2;
                $i3 = $i4;
            }
        }
        return $r3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        r0.delete();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
        throw r4;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x001f A[ExcHandler: Throwable (r4v0 '$r8' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:3:0x0008] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface a(android.content.Context r8, android.content.res.Resources r9, int r10, java.lang.String r11, int r12) {
        /*
            r7 = this;
            java.io.File r0 = com.org.android.asm.ByteVector.a(r8)
            if (r0 != 0) goto L_0x0008
            r1 = 0
            return r1
        L_0x0008:
            boolean r2 = com.org.android.asm.ByteVector.b(r0, r9, r10)     // Catch:{ RuntimeException -> 0x0029, Throwable -> 0x001f }
            if (r2 != 0) goto L_0x0013
            r0.delete()
            r1 = 0
            return r1
        L_0x0013:
            java.lang.String r11 = r0.getPath()     // Catch:{ RuntimeException -> 0x002b, Throwable -> 0x001f }
            android.graphics.Typeface r3 = android.graphics.Typeface.createFromFile(r11)     // Catch:{ RuntimeException -> 0x002b, Throwable -> 0x001f }
            r0.delete()
            return r3
        L_0x001f:
            r4 = move-exception
            r0.delete()
            throw r4
        L_0x0024:
            r0.delete()
            r1 = 0
            return r1
        L_0x0029:
            r5 = move-exception
            goto L_0x0024
        L_0x002b:
            r6 = move-exception
            goto L_0x0024
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.android.asm.f.a(android.content.Context, android.content.res.Resources, int, java.lang.String, int):android.graphics.Typeface");
    }

    public Typeface a(Context context, CancellationSignal cancellationSignal, Label[] labelArr, int i) {
        InputStream $r6;
        InputStream $r62 = null;
        if (labelArr.length < 1) {
            return null;
        }
        try {
            InputStream $r10 = context.getContentResolver().openInputStream(a(labelArr, i).getValue());
            $r6 = $r10;
            try {
                Typeface $r11 = add(context, $r10);
                ByteVector.close($r10);
                return $r11;
            } catch (IOException e) {
                ByteVector.close($r6);
                return null;
            } catch (Throwable th) {
                $r12 = th;
                $r62 = $r10;
                ByteVector.close($r62);
                throw $r12;
            }
        } catch (IOException e2) {
            $r6 = null;
            ByteVector.close($r6);
            return null;
        } catch (Throwable th2) {
            $r12 = th2;
            ByteVector.close($r62);
            throw $r12;
        }
    }

    public Typeface a(Context context, e eVar, Resources resources, int i) {
        com.org.android.ui.asm.f $r4 = a(eVar, i);
        if ($r4 == null) {
            return null;
        }
        return c.a(context, resources, $r4.b(), $r4.a(), i);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.org.android.asm.d, com.org.android.asm.MethodWriter] */
    /* access modifiers changed from: protected */
    public Label a(Label[] labelArr, int i) {
        return (Label) a(labelArr, i, new MethodWriter(this));
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        r0.delete();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
        throw r5;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x001f A[ExcHandler: Throwable (r5v0 '$r8' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:3:0x0008] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface add(android.content.Context r9, java.io.InputStream r10) {
        /*
            r8 = this;
            java.io.File r0 = com.org.android.asm.ByteVector.a(r9)
            if (r0 != 0) goto L_0x0008
            r1 = 0
            return r1
        L_0x0008:
            boolean r2 = com.org.android.asm.ByteVector.a(r0, r10)     // Catch:{ RuntimeException -> 0x0029, Throwable -> 0x001f }
            if (r2 != 0) goto L_0x0013
            r0.delete()
            r1 = 0
            return r1
        L_0x0013:
            java.lang.String r3 = r0.getPath()     // Catch:{ RuntimeException -> 0x002b, Throwable -> 0x001f }
            android.graphics.Typeface r4 = android.graphics.Typeface.createFromFile(r3)     // Catch:{ RuntimeException -> 0x002b, Throwable -> 0x001f }
            r0.delete()
            return r4
        L_0x001f:
            r5 = move-exception
            r0.delete()
            throw r5
        L_0x0024:
            r0.delete()
            r1 = 0
            return r1
        L_0x0029:
            r6 = move-exception
            goto L_0x0024
        L_0x002b:
            r7 = move-exception
            goto L_0x0024
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.android.asm.f.add(android.content.Context, java.io.InputStream):android.graphics.Typeface");
    }
}

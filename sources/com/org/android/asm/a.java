package com.org.android.asm;

import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.File;

class a extends f {
    a() {
    }

    private File close(ParcelFileDescriptor parcelFileDescriptor) {
        try {
            String $r4 = Os.readlink("/proc/self/fd/" + parcelFileDescriptor.getFd());
            if (OsConstants.S_ISREG(Os.stat($r4).st_mode)) {
                return new File($r4);
            }
            return null;
        } catch (ErrnoException e) {
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0059, code lost:
        r17 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x005a, code lost:
        if (r16 != null) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        r14.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0060, code lost:
        r18 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r16.addSuppressed(r18);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r14.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x006c, code lost:
        throw r17;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface a(android.content.Context r27, android.os.CancellationSignal r28, com.org.android.manager.Label[] r29, int r30) {
        /*
            r26 = this;
            r0 = r29
            int r3 = r0.length
            r4 = 1
            if (r3 >= r4) goto L_0x0008
            r5 = 0
            return r5
        L_0x0008:
            r0 = r26
            r1 = r29
            r2 = r30
            com.org.android.manager.Label r6 = r0.a((com.org.android.manager.Label[]) r1, (int) r2)
            r0 = r27
            android.content.ContentResolver r7 = r0.getContentResolver()
            android.net.Uri r8 = r6.getValue()     // Catch:{ IOException -> 0x0085 }
            java.lang.String r10 = "r"
            r0 = r28
            android.os.ParcelFileDescriptor r9 = r7.openFileDescriptor(r8, r10, r0)     // Catch:{ IOException -> 0x0085 }
            r0 = r26
            java.io.File r11 = r0.close(r9)     // Catch:{ Throwable -> 0x006d }
            if (r11 == 0) goto L_0x003d
            boolean r12 = r11.canRead()     // Catch:{ Throwable -> 0x006d }
            if (r12 != 0) goto L_0x0033
            goto L_0x003d
        L_0x0033:
            android.graphics.Typeface r13 = android.graphics.Typeface.createFromFile(r11)     // Catch:{ Throwable -> 0x006d }
            if (r9 == 0) goto L_0x0091
            r9.close()     // Catch:{ IOException -> 0x0088 }
            return r13
        L_0x003d:
            java.io.FileInputStream r14 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x006d }
            java.io.FileDescriptor r15 = r9.getFileDescriptor()     // Catch:{ Throwable -> 0x006d }
            r14.<init>(r15)     // Catch:{ Throwable -> 0x006d }
            r0 = r26
            r1 = r27
            android.graphics.Typeface r13 = super.add(r1, r14)     // Catch:{ Throwable -> 0x0057 }
            r14.close()     // Catch:{ Throwable -> 0x006d }
            if (r9 == 0) goto L_0x0091
            r9.close()     // Catch:{ IOException -> 0x008b }
            return r13
        L_0x0057:
            r16 = move-exception
            throw r16     // Catch:{ Throwable -> 0x0059 }
        L_0x0059:
            r17 = move-exception
            if (r16 == 0) goto L_0x0069
            r14.close()     // Catch:{ Throwable -> 0x0060 }
            goto L_0x006c
        L_0x0060:
            r18 = move-exception
            r0 = r16
            r1 = r18
            r0.addSuppressed(r1)     // Catch:{ Throwable -> 0x006d }
            goto L_0x006c
        L_0x0069:
            r14.close()     // Catch:{ Throwable -> 0x006d }
        L_0x006c:
            throw r17     // Catch:{ Throwable -> 0x006d }
        L_0x006d:
            r19 = move-exception
            throw r19     // Catch:{ Throwable -> 0x006f }
        L_0x006f:
            r20 = move-exception
            if (r9 == 0) goto L_0x0084
            if (r19 == 0) goto L_0x0081
            r9.close()     // Catch:{ Throwable -> 0x0078 }
            goto L_0x0084
        L_0x0078:
            r21 = move-exception
            r0 = r19
            r1 = r21
            r0.addSuppressed(r1)     // Catch:{ IOException -> 0x008e }
            goto L_0x0084
        L_0x0081:
            r9.close()     // Catch:{ IOException -> 0x008e }
        L_0x0084:
            throw r20     // Catch:{ IOException -> 0x008e }
        L_0x0085:
            r22 = move-exception
            r5 = 0
            return r5
        L_0x0088:
            r23 = move-exception
            r5 = 0
            return r5
        L_0x008b:
            r24 = move-exception
            r5 = 0
            return r5
        L_0x008e:
            r25 = move-exception
            r5 = 0
            return r5
        L_0x0091:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.android.asm.a.a(android.content.Context, android.os.CancellationSignal, com.org.android.manager.Label[], int):android.graphics.Typeface");
    }
}

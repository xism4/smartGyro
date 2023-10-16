package com.org.android.asm;

import android.content.Context;
import android.content.res.Resources;
import android.os.Process;
import android.os.StrictMode;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ByteVector {
    public static File a(Context context) {
        String $r3 = ".font" + Process.myPid() + "-" + Process.myTid() + "-";
        int $i0 = 0;
        while ($i0 < 100) {
            File $r4 = new File(context.getCacheDir(), $r3 + $i0);
            try {
                if ($r4.createNewFile()) {
                    return $r4;
                }
                $i0++;
            } catch (IOException e) {
            }
        }
        return null;
    }

    /* JADX INFO: finally extract failed */
    public static ByteBuffer a(Context context, Resources resources, int i) {
        File $r2 = a(context);
        if ($r2 == null) {
            return null;
        }
        try {
            if (!b($r2, resources, i)) {
                $r2.delete();
                return null;
            }
            ByteBuffer $r3 = read($r2);
            $r2.delete();
            return $r3;
        } catch (Throwable $r4) {
            $r2.delete();
            throw $r4;
        }
    }

    public static boolean a(File file, InputStream inputStream) {
        StrictMode.ThreadPolicy $r2 = StrictMode.allowThreadDiskWrites();
        FileOutputStream $r3 = null;
        try {
            FileOutputStream $r4 = new FileOutputStream(file, false);
            byte[] $r5 = new byte[1024];
            while (true) {
                try {
                    int $i0 = inputStream.read($r5);
                    if ($i0 != -1) {
                        $r4.write($r5, 0, $i0);
                    } else {
                        close($r4);
                        StrictMode.setThreadPolicy($r2);
                        return true;
                    }
                } catch (IOException e) {
                    $r7 = e;
                    $r3 = $r4;
                } catch (Throwable th) {
                    $r6 = th;
                    $r3 = $r4;
                    close($r3);
                    StrictMode.setThreadPolicy($r2);
                    throw $r6;
                }
            }
        } catch (IOException e2) {
            $r7 = e2;
            try {
                Log.e("TypefaceCompatUtil", "Error copying resource contents to temp file: " + $r7.getMessage());
                close($r3);
                StrictMode.setThreadPolicy($r2);
                return false;
            } catch (Throwable th2) {
                $r6 = th2;
                close($r3);
                StrictMode.setThreadPolicy($r2);
                throw $r6;
            }
        }
    }

    public static boolean b(File file, Resources resources, int i) {
        InputStream $r3;
        try {
            InputStream $r2 = resources.openRawResource(i);
            $r3 = $r2;
            try {
                boolean $z0 = a(file, $r2);
                close($r2);
                return $z0;
            } catch (Throwable th) {
                $r4 = th;
                close($r3);
                throw $r4;
            }
        } catch (Throwable th2) {
            $r4 = th2;
            $r3 = null;
            close($r3);
            throw $r4;
        }
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0042, code lost:
        r20 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0043, code lost:
        if (r19 != null) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0049, code lost:
        r21 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r19.addSuppressed(r21);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0055, code lost:
        throw r20;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.nio.ByteBuffer read(android.content.Context r28, android.os.CancellationSignal r29, android.net.Uri r30) {
        /*
            r0 = r28
            android.content.ContentResolver r6 = r0.getContentResolver()
            java.lang.String r8 = "r"
            r0 = r30
            r1 = r29
            android.os.ParcelFileDescriptor r7 = r6.openFileDescriptor(r0, r8, r1)     // Catch:{ IOException -> 0x006e }
            if (r7 != 0) goto L_0x0019
            if (r7 == 0) goto L_0x0075
            r7.close()     // Catch:{ IOException -> 0x006e }
            r9 = 0
            return r9
        L_0x0019:
            java.io.FileInputStream r10 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0056 }
            java.io.FileDescriptor r11 = r7.getFileDescriptor()     // Catch:{ Throwable -> 0x0056 }
            r10.<init>(r11)     // Catch:{ Throwable -> 0x0056 }
            java.nio.channels.FileChannel r12 = r10.getChannel()     // Catch:{ Throwable -> 0x0040 }
            long r13 = r12.size()     // Catch:{ Throwable -> 0x0040 }
            java.nio.channels.FileChannel$MapMode r15 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch:{ Throwable -> 0x0040 }
            r17 = 0
            r0 = r12
            r1 = r15
            r2 = r17
            r4 = r13
            java.nio.MappedByteBuffer r16 = r0.map(r1, r2, r4)     // Catch:{ Throwable -> 0x0040 }
            r10.close()     // Catch:{ Throwable -> 0x0056 }
            if (r7 == 0) goto L_0x0077
            r7.close()     // Catch:{ IOException -> 0x0071 }
            return r16
        L_0x0040:
            r19 = move-exception
            throw r19     // Catch:{ Throwable -> 0x0042 }
        L_0x0042:
            r20 = move-exception
            if (r19 == 0) goto L_0x0052
            r10.close()     // Catch:{ Throwable -> 0x0049 }
            goto L_0x0055
        L_0x0049:
            r21 = move-exception
            r0 = r19
            r1 = r21
            r0.addSuppressed(r1)     // Catch:{ Throwable -> 0x0056 }
            goto L_0x0055
        L_0x0052:
            r10.close()     // Catch:{ Throwable -> 0x0056 }
        L_0x0055:
            throw r20     // Catch:{ Throwable -> 0x0056 }
        L_0x0056:
            r22 = move-exception
            throw r22     // Catch:{ Throwable -> 0x0058 }
        L_0x0058:
            r23 = move-exception
            if (r7 == 0) goto L_0x006d
            if (r22 == 0) goto L_0x006a
            r7.close()     // Catch:{ Throwable -> 0x0061 }
            goto L_0x006d
        L_0x0061:
            r24 = move-exception
            r0 = r22
            r1 = r24
            r0.addSuppressed(r1)     // Catch:{ IOException -> 0x0074 }
            goto L_0x006d
        L_0x006a:
            r7.close()     // Catch:{ IOException -> 0x0074 }
        L_0x006d:
            throw r23     // Catch:{ IOException -> 0x0074 }
        L_0x006e:
            r25 = move-exception
            r9 = 0
            return r9
        L_0x0071:
            r26 = move-exception
            r9 = 0
            return r9
        L_0x0074:
            r27 = move-exception
        L_0x0075:
            r9 = 0
            return r9
        L_0x0077:
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.android.asm.ByteVector.read(android.content.Context, android.os.CancellationSignal, android.net.Uri):java.nio.ByteBuffer");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0021, code lost:
        r15 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
        if (r14 != null) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0028, code lost:
        r16 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r14.addSuppressed(r16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0032, code lost:
        throw r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x003c, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.nio.ByteBuffer read(java.io.File r21) {
        /*
            java.io.FileInputStream r6 = new java.io.FileInputStream
            r0 = r21
            r6.<init>(r0)     // Catch:{ IOException -> 0x0033 }
            java.nio.channels.FileChannel r7 = r6.getChannel()     // Catch:{ Throwable -> 0x001f }
            long r8 = r7.size()     // Catch:{ Throwable -> 0x001f }
            java.nio.channels.FileChannel$MapMode r10 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch:{ Throwable -> 0x001f }
            r12 = 0
            r0 = r7
            r1 = r10
            r2 = r12
            r4 = r8
            java.nio.MappedByteBuffer r11 = r0.map(r1, r2, r4)     // Catch:{ Throwable -> 0x001f }
            r6.close()     // Catch:{ IOException -> 0x0037 }
            return r11
        L_0x001f:
            r14 = move-exception
            throw r14     // Catch:{ Throwable -> 0x0021 }
        L_0x0021:
            r15 = move-exception
            if (r14 == 0) goto L_0x002f
            r6.close()     // Catch:{ Throwable -> 0x0028 }
            goto L_0x0032
        L_0x0028:
            r16 = move-exception
            r0 = r16
            r14.addSuppressed(r0)     // Catch:{ IOException -> 0x003b }
            goto L_0x0032
        L_0x002f:
            r6.close()     // Catch:{ IOException -> 0x003b }
        L_0x0032:
            throw r15     // Catch:{ IOException -> 0x003b }
        L_0x0033:
            r17 = move-exception
            r18 = 0
            return r18
        L_0x0037:
            r19 = move-exception
            r18 = 0
            return r18
        L_0x003b:
            r20 = move-exception
            r18 = 0
            return r18
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.android.asm.ByteVector.read(java.io.File):java.nio.ByteBuffer");
    }
}

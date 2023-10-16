package com.org.android.util;

import java.util.LinkedHashMap;
import java.util.Locale;

public class LruCache<K, V> {
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final LinkedHashMap<K, V> map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    public LruCache(int i) {
        if (i > 0) {
            this.maxSize = i;
            this.map = new LinkedHashMap(0, 0.75f, true);
            return;
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    private int safeSizeOf(Object obj, Object obj2) {
        int $i0 = sizeOf(obj, obj2);
        if ($i0 >= 0) {
            return $i0;
        }
        throw new IllegalStateException("Negative size: " + obj + "=" + obj2);
    }

    /* access modifiers changed from: protected */
    public Object create(Object obj) {
        return null;
    }

    /* access modifiers changed from: protected */
    public void entryRemoved(boolean z, Object obj, Object obj2, Object obj3) {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001a, code lost:
        r1 = create(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        if (r1 != null) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0022, code lost:
        monitor-enter(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r11.createCount++;
        r4 = r11.map.put(r12, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002f, code lost:
        if (r4 == null) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0031, code lost:
        r11.map.put(r12, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0037, code lost:
        r11.size += safeSizeOf(r12, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0040, code lost:
        monitor-exit(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0041, code lost:
        if (r4 == null) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0043, code lost:
        entryRemoved(false, r12, r1, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0047, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0048, code lost:
        trimToSize(r11.maxSize);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004d, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object get(java.lang.Object r12) {
        /*
            r11 = this;
            if (r12 == 0) goto L_0x0054
            monitor-enter(r11)
            java.util.LinkedHashMap<K, V> r0 = r11.map     // Catch:{ Throwable -> 0x0051 }
            java.lang.Object r1 = r0.get(r12)     // Catch:{ Throwable -> 0x0051 }
            if (r1 == 0) goto L_0x0013
            int r2 = r11.hitCount     // Catch:{ Throwable -> 0x0051 }
            int r2 = r2 + 1
            r11.hitCount = r2     // Catch:{ Throwable -> 0x0051 }
            monitor-exit(r11)     // Catch:{ Throwable -> 0x0051 }
            return r1
        L_0x0013:
            int r2 = r11.missCount     // Catch:{ Throwable -> 0x0051 }
            int r2 = r2 + 1
            r11.missCount = r2     // Catch:{ Throwable -> 0x0051 }
            monitor-exit(r11)     // Catch:{ Throwable -> 0x0051 }
            java.lang.Object r1 = r11.create(r12)
            if (r1 != 0) goto L_0x0022
            r3 = 0
            return r3
        L_0x0022:
            monitor-enter(r11)
            int r2 = r11.createCount     // Catch:{ Throwable -> 0x004e }
            int r2 = r2 + 1
            r11.createCount = r2     // Catch:{ Throwable -> 0x004e }
            java.util.LinkedHashMap<K, V> r0 = r11.map     // Catch:{ Throwable -> 0x004e }
            java.lang.Object r4 = r0.put(r12, r1)     // Catch:{ Throwable -> 0x004e }
            if (r4 == 0) goto L_0x0037
            java.util.LinkedHashMap<K, V> r0 = r11.map     // Catch:{ Throwable -> 0x004e }
            r0.put(r12, r4)     // Catch:{ Throwable -> 0x004e }
            goto L_0x0040
        L_0x0037:
            int r2 = r11.size     // Catch:{ Throwable -> 0x004e }
            int r5 = r11.safeSizeOf(r12, r1)     // Catch:{ Throwable -> 0x004e }
            int r2 = r2 + r5
            r11.size = r2     // Catch:{ Throwable -> 0x004e }
        L_0x0040:
            monitor-exit(r11)     // Catch:{ Throwable -> 0x004e }
            if (r4 == 0) goto L_0x0048
            r6 = 0
            r11.entryRemoved(r6, r12, r1, r4)
            return r4
        L_0x0048:
            int r2 = r11.maxSize
            r11.trimToSize(r2)
            return r1
        L_0x004e:
            r7 = move-exception
            monitor-exit(r11)     // Catch:{ Throwable -> 0x004e }
            throw r7
        L_0x0051:
            r8 = move-exception
            monitor-exit(r11)     // Catch:{ Throwable -> 0x0051 }
            throw r8
        L_0x0054:
            java.lang.NullPointerException r9 = new java.lang.NullPointerException
            java.lang.String r10 = "key == null"
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.android.util.LruCache.get(java.lang.Object):java.lang.Object");
    }

    public final Object put(Object obj, Object obj2) {
        Object $r4;
        if (obj == null || obj2 == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.putCount++;
            this.size += safeSizeOf(obj, obj2);
            $r4 = this.map.put(obj, obj2);
            if ($r4 != null) {
                this.size -= safeSizeOf(obj, $r4);
            }
        }
        if ($r4 != null) {
            entryRemoved(false, obj, $r4, obj2);
        }
        trimToSize(this.maxSize);
        return $r4;
    }

    /* access modifiers changed from: protected */
    public int sizeOf(Object obj, Object obj2) {
        return 1;
    }

    public final synchronized String toString() {
        int $i0;
        $i0 = this.hitCount + this.missCount;
        return String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[]{Integer.valueOf(this.maxSize), Integer.valueOf(this.hitCount), Integer.valueOf(this.missCount), Integer.valueOf($i0 != 0 ? (this.hitCount * 100) / $i0 : 0)});
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x009f, code lost:
        throw new java.lang.IllegalStateException(r19.getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0019, code lost:
        if (r0.size == 0) goto L_0x001b;
     */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:17:0x0077=Splitter:B:17:0x0077, B:20:0x0079=Splitter:B:20:0x0079} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void trimToSize(int r20) {
        /*
            r19 = this;
        L_0x0000:
            monitor-enter(r19)
            r0 = r19
            int r1 = r0.size     // Catch:{ Throwable -> 0x00a0 }
            r19 = r0
            if (r1 < 0) goto L_0x0079
            r0 = r19
            java.util.LinkedHashMap<K, V> r2 = r0.map     // Catch:{ Throwable -> 0x00a0 }
            boolean r3 = r2.isEmpty()     // Catch:{ Throwable -> 0x00a0 }
            if (r3 == 0) goto L_0x001b
            r0 = r19
            int r1 = r0.size     // Catch:{ Throwable -> 0x00a0 }
            r19 = r0
            if (r1 != 0) goto L_0x0079
        L_0x001b:
            r0 = r19
            int r1 = r0.size     // Catch:{ Throwable -> 0x00a0 }
            r19 = r0
            r0 = r20
            if (r1 <= r0) goto L_0x0077
            r0 = r19
            java.util.LinkedHashMap<K, V> r2 = r0.map     // Catch:{ Throwable -> 0x00a0 }
            boolean r3 = r2.isEmpty()     // Catch:{ Throwable -> 0x00a0 }
            if (r3 == 0) goto L_0x0030
            goto L_0x0077
        L_0x0030:
            r0 = r19
            java.util.LinkedHashMap<K, V> r2 = r0.map     // Catch:{ Throwable -> 0x00a0 }
            java.util.Set r4 = r2.entrySet()     // Catch:{ Throwable -> 0x00a0 }
            java.util.Iterator r5 = r4.iterator()     // Catch:{ Throwable -> 0x00a0 }
            java.lang.Object r6 = r5.next()     // Catch:{ Throwable -> 0x00a0 }
            r8 = r6
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8     // Catch:{ Throwable -> 0x00a0 }
            r7 = r8
            java.lang.Object r6 = r7.getKey()     // Catch:{ Throwable -> 0x00a0 }
            java.lang.Object r9 = r7.getValue()     // Catch:{ Throwable -> 0x00a0 }
            r0 = r19
            java.util.LinkedHashMap<K, V> r2 = r0.map     // Catch:{ Throwable -> 0x00a0 }
            r2.remove(r6)     // Catch:{ Throwable -> 0x00a0 }
            r0 = r19
            int r1 = r0.size     // Catch:{ Throwable -> 0x00a0 }
            r19 = r0
            int r10 = r0.safeSizeOf(r6, r9)     // Catch:{ Throwable -> 0x00a0 }
            int r1 = r1 - r10
            r0 = r19
            r0.size = r1     // Catch:{ Throwable -> 0x00a0 }
            r0 = r19
            int r1 = r0.evictionCount     // Catch:{ Throwable -> 0x00a0 }
            r19 = r0
            int r1 = r1 + 1
            r0 = r19
            r0.evictionCount = r1     // Catch:{ Throwable -> 0x00a0 }
            monitor-exit(r19)     // Catch:{ Throwable -> 0x00a0 }
            r11 = 1
            r12 = 0
            r0 = r19
            r0.entryRemoved(r11, r6, r9, r12)
            goto L_0x0000
        L_0x0077:
            monitor-exit(r19)     // Catch:{ Throwable -> 0x00a0 }
            return
        L_0x0079:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException     // Catch:{ Throwable -> 0x00a0 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00a0 }
            r14.<init>()     // Catch:{ Throwable -> 0x00a0 }
            r0 = r19
            java.lang.Class r15 = r0.getClass()     // Catch:{ Throwable -> 0x00a0 }
            java.lang.String r16 = r15.getName()     // Catch:{ Throwable -> 0x00a0 }
            r0 = r16
            r14.append(r0)     // Catch:{ Throwable -> 0x00a0 }
            java.lang.String r17 = ".sizeOf() is reporting inconsistent results!"
            r0 = r17
            r14.append(r0)     // Catch:{ Throwable -> 0x00a0 }
            java.lang.String r16 = r14.toString()     // Catch:{ Throwable -> 0x00a0 }
            r0 = r16
            r13.<init>(r0)     // Catch:{ Throwable -> 0x00a0 }
            throw r13     // Catch:{ Throwable -> 0x00a0 }
        L_0x00a0:
            r18 = move-exception
            monitor-exit(r19)     // Catch:{ Throwable -> 0x00a0 }
            goto L_0x00a3
        L_0x00a3:
            throw r18
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.android.util.LruCache.trimToSize(int):void");
    }
}

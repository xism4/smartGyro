package com.org.android.manager;

import a.a.c.d.f;
import a.a.c.d.k;

final class e implements k.a<f.c> {
    final /* synthetic */ String a;

    e(String str) {
        this.a = str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001b, code lost:
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
        if (r6 >= r4.size()) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0022, code lost:
        ((com.org.android.manager.c) r4.get(r6)).a(r12);
        r6 = r6 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0030, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.org.android.manager.f r12) {
        /*
            r11 = this;
            java.lang.Object r0 = com.org.android.manager.i.c
            monitor-enter(r0)
            a.a.c.f.i<java.lang.String, java.util.ArrayList<a.a.c.d.k$a<a.a.c.d.f$c>>> r1 = com.org.android.manager.i.b     // Catch:{ Throwable -> 0x0031 }
            java.lang.String r2 = r11.a     // Catch:{ Throwable -> 0x0031 }
            java.lang.Object r3 = r1.get(r2)     // Catch:{ Throwable -> 0x0031 }
            r5 = r3
            java.util.ArrayList r5 = (java.util.ArrayList) r5     // Catch:{ Throwable -> 0x0031 }
            r4 = r5
            if (r4 != 0) goto L_0x0013
            monitor-exit(r0)     // Catch:{ Throwable -> 0x0031 }
            return
        L_0x0013:
            a.a.c.f.i<java.lang.String, java.util.ArrayList<a.a.c.d.k$a<a.a.c.d.f$c>>> r1 = com.org.android.manager.i.b     // Catch:{ Throwable -> 0x0031 }
            java.lang.String r2 = r11.a     // Catch:{ Throwable -> 0x0031 }
            r1.remove(r2)     // Catch:{ Throwable -> 0x0031 }
            monitor-exit(r0)     // Catch:{ Throwable -> 0x0031 }
            r6 = 0
        L_0x001c:
            int r7 = r4.size()
            if (r6 >= r7) goto L_0x0030
            java.lang.Object r0 = r4.get(r6)
            r9 = r0
            com.org.android.manager.c r9 = (com.org.android.manager.c) r9
            r8 = r9
            r8.a(r12)
            int r6 = r6 + 1
            goto L_0x001c
        L_0x0030:
            return
        L_0x0031:
            r10 = move-exception
            monitor-exit(r0)     // Catch:{ Throwable -> 0x0031 }
            goto L_0x0034
        L_0x0034:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.android.manager.e.a(com.org.android.manager.f):void");
    }
}

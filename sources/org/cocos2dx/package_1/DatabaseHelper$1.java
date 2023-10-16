package org.cocos2dx.package_1;

import java.util.concurrent.Callable;

final class DatabaseHelper$1 implements Callable<Float> {
    final /* synthetic */ int val$position;

    DatabaseHelper$1(int i) {
        this.val$position = i;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.lang.Float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.lang.Float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: java.lang.Float} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Float call() {
        /*
            r14 = this;
            android.util.SparseArray r0 = org.cocos2dx.package_1.Cocos2dxWebViewHelper.webViews
            int r1 = r14.val$position
            java.lang.Object r2 = r0.get(r1)
            r4 = r2
            org.cocos2dx.package_1.Cocos2dxWebView r4 = (org.cocos2dx.package_1.Cocos2dxWebView) r4
            r3 = r4
            if (r3 == 0) goto L_0x0029
            java.lang.Class r5 = r3.getClass()     // Catch:{ Exception -> 0x0025 }
            r7 = 0
            java.lang.Class[] r6 = new java.lang.Class[r7]
            java.lang.String r9 = "getAlpha"
            java.lang.reflect.Method r8 = r5.getMethod(r9, r6)     // Catch:{ Exception -> 0x0025 }
            r7 = 0
            java.lang.Object[] r10 = new java.lang.Object[r7]
            java.lang.Object r2 = r8.invoke(r3, r10)     // Catch:{ Exception -> 0x0025 }
            goto L_0x002a
        L_0x0025:
            r11 = move-exception
            r11.printStackTrace()
        L_0x0029:
            r2 = 0
        L_0x002a:
            r13 = r2
            java.lang.Float r13 = (java.lang.Float) r13
            r12 = r13
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cocos2dx.package_1.DatabaseHelper$1.call():java.lang.Float");
    }
}

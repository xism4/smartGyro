package com.org.android.view;

import android.view.View;

class ViewPropertyAnimatorCompat$ICSViewPropertyAnimatorCompatImpl$MyVpaListener implements ViewPropertyAnimatorListener {
    boolean mAnimEndCalled;
    ViewPropertyAnimatorCompat mVpa;

    ViewPropertyAnimatorCompat$ICSViewPropertyAnimatorCompatImpl$MyVpaListener(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
        this.mVpa = viewPropertyAnimatorCompat;
    }

    public void onAnimationCancel(View view) {
        Object $r2 = view.getTag(2113929216);
        ViewPropertyAnimatorListener $r3 = $r2 instanceof ViewPropertyAnimatorListener ? (ViewPropertyAnimatorListener) $r2 : null;
        if ($r3 != null) {
            $r3.onAnimationCancel(view);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: com.org.android.view.ViewPropertyAnimatorListener} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onAnimationEnd(android.view.View r10) {
        /*
            r9 = this;
            com.org.android.view.ViewPropertyAnimatorCompat r0 = r9.mVpa
            int r1 = r0.mOldLayerType
            r2 = 0
            r3 = -1
            if (r1 <= r3) goto L_0x0011
            r4 = 0
            r10.setLayerType(r1, r4)
            com.org.android.view.ViewPropertyAnimatorCompat r0 = r9.mVpa
            r3 = -1
            r0.mOldLayerType = r3
        L_0x0011:
            int r1 = android.os.Build.VERSION.SDK_INT
            r3 = 16
            if (r1 >= r3) goto L_0x001b
            boolean r5 = r9.mAnimEndCalled
            if (r5 != 0) goto L_0x003e
        L_0x001b:
            com.org.android.view.ViewPropertyAnimatorCompat r0 = r9.mVpa
            java.lang.Runnable r6 = r0.mEndAction
            if (r6 == 0) goto L_0x0027
            r4 = 0
            r0.mEndAction = r4
            r6.run()
        L_0x0027:
            r3 = 2113929216(0x7e000000, float:4.2535296E37)
            java.lang.Object r7 = r10.getTag(r3)
            boolean r5 = r7 instanceof com.org.android.view.ViewPropertyAnimatorListener
            if (r5 == 0) goto L_0x0036
            r8 = r7
            com.org.android.view.ViewPropertyAnimatorListener r8 = (com.org.android.view.ViewPropertyAnimatorListener) r8
            r2 = r8
        L_0x0036:
            if (r2 == 0) goto L_0x003b
            r2.onAnimationEnd(r10)
        L_0x003b:
            r3 = 1
            r9.mAnimEndCalled = r3
        L_0x003e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.android.view.ViewPropertyAnimatorCompat$ICSViewPropertyAnimatorCompatImpl$MyVpaListener.onAnimationEnd(android.view.View):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: com.org.android.view.ViewPropertyAnimatorListener} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onAnimationStart(android.view.View r10) {
        /*
            r9 = this;
            r0 = 0
            r9.mAnimEndCalled = r0
            com.org.android.view.ViewPropertyAnimatorCompat r1 = r9.mVpa
            int r2 = r1.mOldLayerType
            r3 = 0
            r0 = -1
            if (r2 <= r0) goto L_0x0010
            r0 = 2
            r4 = 0
            r10.setLayerType(r0, r4)
        L_0x0010:
            com.org.android.view.ViewPropertyAnimatorCompat r1 = r9.mVpa
            java.lang.Runnable r5 = r1.mStartAction
            if (r5 == 0) goto L_0x001c
            r4 = 0
            r1.mStartAction = r4
            r5.run()
        L_0x001c:
            r0 = 2113929216(0x7e000000, float:4.2535296E37)
            java.lang.Object r6 = r10.getTag(r0)
            boolean r7 = r6 instanceof com.org.android.view.ViewPropertyAnimatorListener
            if (r7 == 0) goto L_0x002b
            r8 = r6
            com.org.android.view.ViewPropertyAnimatorListener r8 = (com.org.android.view.ViewPropertyAnimatorListener) r8
            r3 = r8
        L_0x002b:
            if (r3 == 0) goto L_0x0030
            r3.onAnimationStart(r10)
        L_0x0030:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.org.android.view.ViewPropertyAnimatorCompat$ICSViewPropertyAnimatorCompatImpl$MyVpaListener.onAnimationStart(android.view.View):void");
    }
}

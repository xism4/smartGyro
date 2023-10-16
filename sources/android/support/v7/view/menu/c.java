package android.support.v7.view.menu;

import android.support.v7.widget.Object;
import android.view.MenuItem;

class c implements Object {
    final /* synthetic */ f c;

    c(f fVar) {
        this.c = fVar;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v0, resolved type: android.support.v7.view.menu.e} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: java.util.List<android.support.v7.view.menu.i$a>} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.support.v7.view.menu.MenuBuilder r24, android.view.MenuItem r25) {
        /*
            r23 = this;
            r0 = r23
            android.support.v7.view.menu.f r5 = r0.c
            android.os.Handler r6 = r5.B
            r7 = 0
            r8 = 0
            r6.removeCallbacksAndMessages(r8)
            r0 = r23
            android.support.v7.view.menu.f r5 = r0.c
            java.util.List<android.support.v7.view.menu.i$a> r9 = r5.c
            int r10 = r9.size()
            r11 = 0
        L_0x0016:
            if (r11 >= r10) goto L_0x0030
            r0 = r23
            android.support.v7.view.menu.f r5 = r0.c
            java.util.List<android.support.v7.view.menu.i$a> r9 = r5.c
            java.lang.Object r12 = r9.get(r11)
            r14 = r12
            android.support.v7.view.menu.e r14 = (android.support.v7.view.menu.e) r14
            r13 = r14
            android.support.v7.view.menu.MenuBuilder r15 = r13.c
            r0 = r24
            if (r0 != r15) goto L_0x002d
            goto L_0x0031
        L_0x002d:
            int r11 = r11 + 1
            goto L_0x0016
        L_0x0030:
            r11 = -1
        L_0x0031:
            r16 = -1
            r0 = r16
            if (r11 != r0) goto L_0x0038
            return
        L_0x0038:
            int r10 = r11 + 1
            r0 = r23
            android.support.v7.view.menu.f r5 = r0.c
            java.util.List<android.support.v7.view.menu.i$a> r9 = r5.c
            int r11 = r9.size()
            if (r10 >= r11) goto L_0x0056
            r0 = r23
            android.support.v7.view.menu.f r5 = r0.c
            java.util.List<android.support.v7.view.menu.i$a> r9 = r5.c
            java.lang.Object r12 = r9.get(r10)
            r17 = r12
            android.support.v7.view.menu.e r17 = (android.support.v7.view.menu.e) r17
            r7 = r17
        L_0x0056:
            android.support.v7.view.menu.Plot$a r18 = new android.support.v7.view.menu.Plot$a
            r0 = r18
            r1 = r23
            r2 = r25
            r3 = r24
            r0.<init>(r1, r7, r2, r3)
            long r19 = android.os.SystemClock.uptimeMillis()
            r21 = 200(0xc8, double:9.9E-322)
            r0 = r19
            r2 = r21
            long r0 = r0 + r2
            r19 = r0
            r0 = r23
            android.support.v7.view.menu.f r5 = r0.c
            android.os.Handler r6 = r5.B
            r0 = r18
            r1 = r24
            r2 = r19
            r6.postAtTime(r0, r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.view.menu.c.a(android.support.v7.view.menu.MenuBuilder, android.view.MenuItem):void");
    }

    public void b(MenuBuilder menuBuilder, MenuItem menuItem) {
        this.c.B.removeCallbacksAndMessages(menuBuilder);
    }
}

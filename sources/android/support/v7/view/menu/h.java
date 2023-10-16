package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.PopupWindow;
import com.org.android.view.ViewCompat;

public class h implements MenuItem {
    private View a;
    private d b;
    private final Context c;
    private final MenuBuilder d;
    private final int e;
    private final boolean f;
    private final int g;
    private boolean h;
    private l$a k;
    private int l;
    private PopupWindow.OnDismissListener v;
    private final PopupWindow.OnDismissListener x;

    public h(Context context, MenuBuilder menuBuilder, View view, boolean z, int i) {
        this(context, menuBuilder, view, z, i, 0);
    }

    public h(Context context, MenuBuilder menuBuilder, View view, boolean z, int i, int i2) {
        this.l = 8388611;
        this.x = new w(this);
        this.c = context;
        this.d = menuBuilder;
        this.a = view;
        this.f = z;
        this.e = i;
        this.g = i2;
    }

    private void a(int $i2, int i, boolean z, boolean z2) {
        d $r1 = a();
        $r1.a(z2);
        if (z) {
            if ((com.org.android.view.View.getAbsoluteGravity(this.l, ViewCompat.getLayoutDirection(this.a)) & 7) == 5) {
                $i2 -= this.a.getWidth();
            }
            $r1.c($i2);
            $r1.b(i);
            int $i4 = (int) ((this.c.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            $r1.b(new Rect($i2 - $i4, i - $i4, $i2 + $i4, i + $i4));
        }
        $r1.show();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v0, resolved type: android.support.v7.view.menu.i} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v1, resolved type: android.support.v7.view.menu.i} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v2, resolved type: android.support.v7.view.menu.i} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v42, resolved type: android.support.v7.view.menu.f} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.support.v7.view.menu.d onCreateView() {
        /*
            r27 = this;
            r0 = r27
            android.content.Context r7 = r0.c
            java.lang.String r9 = "window"
            java.lang.Object r8 = r7.getSystemService(r9)
            r11 = r8
            android.view.WindowManager r11 = (android.view.WindowManager) r11
            r10 = r11
            android.view.Display r12 = r10.getDefaultDisplay()
            android.graphics.Point r13 = new android.graphics.Point
            r14 = r13
            r13.<init>()
            int r15 = android.os.Build.VERSION.SDK_INT
            r16 = 17
            r0 = r16
            if (r15 < r0) goto L_0x0024
            r12.getRealSize(r14)
            goto L_0x0027
        L_0x0024:
            r12.getSize(r14)
        L_0x0027:
            int r15 = r14.x
            int r0 = r14.y
            r17 = r0
            int r15 = java.lang.Math.min(r15, r0)
            r0 = r27
            android.content.Context r7 = r0.c
            android.content.res.Resources r18 = r7.getResources()
            int r17 = com.org.v4.util.R$dimen.abc_cascading_menus_min_smallest_width
            r0 = r18
            r1 = r17
            int r17 = r0.getDimensionPixelSize(r1)
            r0 = r17
            if (r15 < r0) goto L_0x004a
            r19 = 1
            goto L_0x004c
        L_0x004a:
            r19 = 0
        L_0x004c:
            if (r19 == 0) goto L_0x007a
            android.support.v7.view.menu.f r20 = new android.support.v7.view.menu.f
            r21 = r20
            r0 = r27
            android.content.Context r7 = r0.c
            r0 = r27
            android.view.View r0 = r0.a
            r22 = r0
            r0 = r27
            int r15 = r0.e
            r0 = r27
            int r0 = r0.g
            r17 = r0
            r0 = r27
            boolean r0 = r0.f
            r19 = r0
            r0 = r20
            r1 = r7
            r2 = r22
            r3 = r15
            r4 = r17
            r5 = r19
            r0.<init>(r1, r2, r3, r4, r5)
            goto L_0x00ad
        L_0x007a:
            android.support.v7.view.menu.i r23 = new android.support.v7.view.menu.i
            r21 = r23
            r0 = r27
            android.content.Context r7 = r0.c
            r0 = r27
            android.support.v7.view.menu.MenuBuilder r0 = r0.d
            r24 = r0
            r0 = r27
            android.view.View r0 = r0.a
            r22 = r0
            r0 = r27
            int r15 = r0.e
            r0 = r27
            int r0 = r0.g
            r17 = r0
            r0 = r27
            boolean r0 = r0.f
            r19 = r0
            r0 = r23
            r1 = r7
            r2 = r24
            r3 = r22
            r4 = r15
            r5 = r17
            r6 = r19
            r0.<init>(r1, r2, r3, r4, r5, r6)
        L_0x00ad:
            r0 = r27
            android.support.v7.view.menu.MenuBuilder r0 = r0.d
            r24 = r0
            r0 = r21
            r1 = r24
            r0.add(r1)
            r0 = r27
            android.widget.PopupWindow$OnDismissListener r0 = r0.x
            r25 = r0
            r0 = r21
            r1 = r25
            r0.a((android.widget.PopupWindow.OnDismissListener) r1)
            r0 = r27
            android.view.View r0 = r0.a
            r22 = r0
            r0 = r21
            r1 = r22
            r0.a((android.view.View) r1)
            r0 = r27
            android.support.v7.view.menu.l$a r0 = r0.k
            r26 = r0
            r0 = r21
            r1 = r26
            r0.a((android.support.v7.view.menu.l$a) r1)
            r0 = r27
            boolean r0 = r0.h
            r19 = r0
            r0 = r21
            r1 = r19
            r0.b((boolean) r1)
            r0 = r27
            int r15 = r0.l
            r0 = r21
            r0.a((int) r15)
            return r21
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.view.menu.h.onCreateView():android.support.v7.view.menu.d");
    }

    public d a() {
        if (this.b == null) {
            this.b = onCreateView();
        }
        return this.b;
    }

    public void a(l$a l_a) {
        this.k = l_a;
        d $r2 = this.b;
        if ($r2 != null) {
            $r2.a(l_a);
        }
    }

    public void a(View view) {
        this.a = view;
    }

    public void a(boolean z) {
        this.h = z;
        d $r1 = this.b;
        if ($r1 != null) {
            $r1.b(z);
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        this.b = null;
        PopupWindow.OnDismissListener $r1 = this.v;
        if ($r1 != null) {
            $r1.onDismiss();
        }
    }

    public void b(int i) {
        this.l = i;
    }

    public void b(PopupWindow.OnDismissListener onDismissListener) {
        this.v = onDismissListener;
    }

    public boolean b(int i, int i2) {
        if (isShowing()) {
            return true;
        }
        if (this.a == null) {
            return false;
        }
        a(i, i2, true, true);
        return true;
    }

    public boolean c() {
        if (isShowing()) {
            return true;
        }
        if (this.a == null) {
            return false;
        }
        a(0, 0, false, false);
        return true;
    }

    public void dismiss() {
        if (isShowing()) {
            this.b.dismiss();
        }
    }

    public boolean isShowing() {
        d $r1 = this.b;
        return $r1 != null && $r1.isShowing();
    }

    public void setTitle() {
        if (!c()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }
}

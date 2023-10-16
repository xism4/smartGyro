package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v7.view.menu.i;
import android.support.v7.widget.Label;
import android.support.v7.widget.Object;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.org.android.view.ViewCompat;
import com.org.v4.util.R$dimen;
import com.org.v4.util.R$layout;
import java.util.ArrayList;
import java.util.List;

final class f extends d implements MenuPresenter, View.OnKeyListener, PopupWindow.OnDismissListener {
    private static final int p = R$layout.abc_cascading_menu_item_layout;
    private final int A;
    final Handler B;
    private PopupWindow.OnDismissListener D;
    private final Context a;
    private boolean b;
    final List<i.a> c = new ArrayList();
    private l$a d;
    private boolean e;
    private final List<l> f = new ArrayList();
    private int g;
    private View h;
    boolean i;
    private int j;
    private int k = 0;
    private boolean l;
    private final int m;
    private final int n;
    private final Object o = new c(this);
    private int r;
    private final boolean s;
    final ViewTreeObserver.OnGlobalLayoutListener t = new AmbilWarnaDialog$6(this);
    View this$0;
    private final View.OnAttachStateChangeListener v = new MenuPopupHelper(this);
    ViewTreeObserver x;
    private int y = 0;
    private boolean z;

    public f(Context context, View view, int i2, int i3, boolean z2) {
        this.a = context;
        this.h = view;
        this.m = i2;
        this.n = i3;
        this.s = z2;
        this.b = false;
        this.r = b();
        Resources $r7 = context.getResources();
        this.A = Math.max($r7.getDisplayMetrics().widthPixels / 2, $r7.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
        this.B = new Handler();
    }

    private Label a() {
        Label $r1 = new Label(this.a, (AttributeSet) null, this.m, this.n);
        $r1.a(this.o);
        $r1.setOnItemClickListener(this);
        $r1.setOnDismissListener(this);
        $r1.a(this.h);
        $r1.a(this.y);
        $r1.dismiss(true);
        $r1.show(2);
        return $r1;
    }

    private MenuItem a(MenuBuilder menuBuilder, MenuBuilder menuBuilder2) {
        int $i0 = menuBuilder.size();
        for (int $i1 = 0; $i1 < $i0; $i1++) {
            MenuItem $r3 = menuBuilder.getItem($i1);
            if ($r3.hasSubMenu() && menuBuilder2 == $r3.getSubMenu()) {
                return $r3;
            }
        }
        return null;
    }

    /* JADX WARNING: type inference failed for: r6v1, types: [android.widget.ListAdapter] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.view.View a(android.support.v7.view.menu.e r20, android.support.v7.view.menu.MenuBuilder r21) {
        /*
            r19 = this;
            r0 = r20
            android.support.v7.view.menu.MenuBuilder r2 = r0.c
            r0 = r19
            r1 = r21
            android.view.MenuItem r3 = r0.a((android.support.v7.view.menu.MenuBuilder) r2, (android.support.v7.view.menu.MenuBuilder) r1)
            if (r3 != 0) goto L_0x0010
            r4 = 0
            return r4
        L_0x0010:
            r0 = r20
            android.widget.ListView r5 = r0.get()
            android.widget.ListAdapter r6 = r5.getAdapter()
            boolean r7 = r6 instanceof android.widget.HeaderViewListAdapter
            r8 = 0
            if (r7 == 0) goto L_0x0030
            r10 = r6
            android.widget.HeaderViewListAdapter r10 = (android.widget.HeaderViewListAdapter) r10
            r9 = r10
            int r11 = r9.getHeadersCount()
            android.widget.ListAdapter r6 = r9.getWrappedAdapter()
            r13 = r6
            android.support.v7.view.menu.e$a r13 = (android.support.v7.view.menu.e.a) r13
            r12 = r13
            goto L_0x0035
        L_0x0030:
            r14 = r6
            android.support.v7.view.menu.e$a r14 = (android.support.v7.view.menu.e.a) r14
            r12 = r14
            r11 = 0
        L_0x0035:
            int r15 = r12.getCount()
        L_0x0039:
            if (r8 >= r15) goto L_0x0047
            android.support.v7.view.menu.MenuItemImpl r16 = r12.getItem((int) r8)
            r0 = r16
            if (r3 != r0) goto L_0x0044
            goto L_0x0048
        L_0x0044:
            int r8 = r8 + 1
            goto L_0x0039
        L_0x0047:
            r8 = -1
        L_0x0048:
            r17 = -1
            r0 = r17
            if (r8 != r0) goto L_0x0050
            r4 = 0
            return r4
        L_0x0050:
            int r8 = r8 + r11
            int r11 = r5.getFirstVisiblePosition()
            int r8 = r8 - r11
            if (r8 < 0) goto L_0x0065
            int r11 = r5.getChildCount()
            if (r8 < r11) goto L_0x0060
            r4 = 0
            return r4
        L_0x0060:
            android.view.View r18 = r5.getChildAt(r8)
            return r18
        L_0x0065:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.view.menu.f.a(android.support.v7.view.menu.e, android.support.v7.view.menu.MenuBuilder):android.view.View");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v0, resolved type: android.support.v7.view.menu.e} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.support.v7.view.menu.MenuBuilder r30) {
        /*
            r29 = this;
            r0 = r29
            android.content.Context r2 = r0.a
            android.view.LayoutInflater r3 = android.view.LayoutInflater.from(r2)
            android.support.v7.view.menu.e$a r4 = new android.support.v7.view.menu.e$a
            r0 = r29
            boolean r5 = r0.s
            int r6 = p
            r0 = r30
            r4.<init>(r0, r3, r5, r6)
            r0 = r29
            boolean r5 = r0.isShowing()
            if (r5 != 0) goto L_0x0028
            r0 = r29
            boolean r5 = r0.b
            if (r5 == 0) goto L_0x0028
            r7 = 1
            r4.a(r7)
            goto L_0x0039
        L_0x0028:
            r0 = r29
            boolean r5 = r0.isShowing()
            if (r5 == 0) goto L_0x0039
            r0 = r30
            boolean r5 = android.support.v7.view.menu.d.onSubMenuSelected(r0)
            r4.a(r5)
        L_0x0039:
            r0 = r29
            android.content.Context r2 = r0.a
            r0 = r29
            int r6 = r0.A
            r9 = 0
            int r8 = android.support.v7.view.menu.d.measureContentWidth(r4, r9, r2, r6)
            r6 = r8
            r0 = r29
            android.support.v7.widget.Label r10 = r0.a()
            r10.setAdapter((android.widget.ListAdapter) r4)
            r10.setContentWidth(r8)
            r0 = r29
            int r11 = r0.y
            r10.a((int) r11)
            r0 = r29
            java.util.List<android.support.v7.view.menu.i$a> r12 = r0.c
            int r11 = r12.size()
            if (r11 <= 0) goto L_0x007f
            r0 = r29
            java.util.List<android.support.v7.view.menu.i$a> r12 = r0.c
            int r11 = r12.size()
            r7 = 1
            int r11 = r11 - r7
            java.lang.Object r13 = r12.get(r11)
            r15 = r13
            android.support.v7.view.menu.e r15 = (android.support.v7.view.menu.e) r15
            r14 = r15
            r0 = r29
            r1 = r30
            android.view.View r16 = r0.a((android.support.v7.view.menu.e) r14, (android.support.v7.view.menu.MenuBuilder) r1)
            goto L_0x0082
        L_0x007f:
            r14 = 0
            r16 = 0
        L_0x0082:
            if (r16 == 0) goto L_0x012c
            r7 = 0
            r10.a((boolean) r7)
            r9 = 0
            r10.init(r9)
            r0 = r29
            int r8 = r0.add((int) r8)
            r7 = 1
            if (r8 != r7) goto L_0x0097
            r5 = 1
            goto L_0x0098
        L_0x0097:
            r5 = 0
        L_0x0098:
            r0 = r29
            r0.r = r8
            int r8 = android.os.Build.VERSION.SDK_INT
            r7 = 26
            if (r8 < r7) goto L_0x00aa
            r0 = r16
            r10.a((android.view.View) r0)
            r11 = 0
            r8 = 0
            goto L_0x00fd
        L_0x00aa:
            r7 = 2
            int[] r0 = new int[r7]
            r17 = r0
            r0 = r29
            android.view.View r0 = r0.h
            r18 = r0
            r1 = r17
            r0.getLocationOnScreen(r1)
            r7 = 2
            int[] r0 = new int[r7]
            r19 = r0
            r0 = r16
            r1 = r19
            r0.getLocationOnScreen(r1)
            r0 = r29
            int r8 = r0.y
            r8 = r8 & 7
            r7 = 5
            if (r8 != r7) goto L_0x00ed
            r7 = 0
            r8 = r17[r7]
            r0 = r29
            android.view.View r0 = r0.h
            r18 = r0
            int r11 = r0.getWidth()
            int r8 = r8 + r11
            r7 = 0
            r17[r7] = r8
            r7 = 0
            r8 = r19[r7]
            r0 = r16
            int r11 = r0.getWidth()
            int r8 = r8 + r11
            r7 = 0
            r19[r7] = r8
        L_0x00ed:
            r7 = 0
            r8 = r19[r7]
            r7 = 0
            r11 = r17[r7]
            int r8 = r8 - r11
            r7 = 1
            r11 = r19[r7]
            r7 = 1
            r20 = r17[r7]
            r0 = r20
            int r11 = r11 - r0
        L_0x00fd:
            r0 = r29
            int r0 = r0.y
            r20 = r0
            r20 = r20 & 5
            r7 = 5
            r0 = r20
            if (r0 != r7) goto L_0x0114
            if (r5 == 0) goto L_0x010d
            goto L_0x011c
        L_0x010d:
            r0 = r16
            int r6 = r0.getWidth()
            goto L_0x011f
        L_0x0114:
            if (r5 == 0) goto L_0x011f
            r0 = r16
            int r6 = r0.getWidth()
        L_0x011c:
            int r6 = r8 + r6
            goto L_0x0121
        L_0x011f:
            int r6 = r8 - r6
        L_0x0121:
            r10.setAdapter((int) r6)
            r7 = 1
            r10.show((boolean) r7)
            r10.setVerticalOffset(r11)
            goto L_0x0151
        L_0x012c:
            r0 = r29
            boolean r5 = r0.e
            if (r5 == 0) goto L_0x0139
            r0 = r29
            int r6 = r0.g
            r10.setAdapter((int) r6)
        L_0x0139:
            r0 = r29
            boolean r5 = r0.l
            if (r5 == 0) goto L_0x0146
            r0 = r29
            int r6 = r0.j
            r10.setVerticalOffset(r6)
        L_0x0146:
            r0 = r29
            android.graphics.Rect r21 = r0.c()
            r0 = r21
            r10.setAdapter((android.graphics.Rect) r0)
        L_0x0151:
            android.support.v7.view.menu.e r22 = new android.support.v7.view.menu.e
            r0 = r29
            int r6 = r0.r
            r0 = r22
            r1 = r30
            r0.<init>(r10, r1, r6)
            r0 = r29
            java.util.List<android.support.v7.view.menu.i$a> r12 = r0.c
            r0 = r22
            r12.add(r0)
            r10.show()
            android.widget.ListView r23 = r10.add()
            r0 = r23
            r1 = r29
            r0.setOnKeyListener(r1)
            if (r14 != 0) goto L_0x01c2
            r0 = r29
            boolean r5 = r0.z
            if (r5 == 0) goto L_0x01c2
            r0 = r30
            java.lang.CharSequence r24 = r0.getValue()
            if (r24 == 0) goto L_0x01c2
            int r6 = com.org.v4.util.R$layout.abc_popup_menu_header_item_layout
            r7 = 0
            r0 = r23
            android.view.View r16 = r3.inflate(r6, r0, r7)
            r26 = r16
            android.widget.FrameLayout r26 = (android.widget.FrameLayout) r26
            r25 = r26
            r7 = 16908310(0x1020016, float:2.387729E-38)
            r0 = r25
            android.view.View r16 = r0.findViewById(r7)
            r28 = r16
            android.widget.TextView r28 = (android.widget.TextView) r28
            r27 = r28
            r7 = 0
            r0 = r25
            r0.setEnabled(r7)
            r0 = r30
            java.lang.CharSequence r24 = r0.getValue()
            r0 = r27
            r1 = r24
            r0.setText(r1)
            r9 = 0
            r7 = 0
            r0 = r23
            r1 = r25
            r0.addHeaderView(r1, r9, r7)
            r10.show()
        L_0x01c2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.view.menu.f.a(android.support.v7.view.menu.MenuBuilder):void");
    }

    private int add(int $i0) {
        List $r2 = this.c;
        ListView $r5 = $r2.get($r2.size() - 1).get();
        int[] $r6 = new int[2];
        $r5.getLocationOnScreen($r6);
        Rect $r1 = new Rect();
        this.this$0.getWindowVisibleDisplayFrame($r1);
        return this.r == 1 ? ($r6[0] + $r5.getWidth()) + $i0 > $r1.right ? 0 : 1 : $r6[0] - $i0 < 0 ? 1 : 0;
    }

    private int b() {
        return ViewCompat.getLayoutDirection(this.h) == 1 ? 0 : 1;
    }

    private int b(MenuBuilder menuBuilder) {
        int $i0 = this.c.size();
        for (int $i1 = 0; $i1 < $i0; $i1++) {
            if (menuBuilder == this.c.get($i1).c) {
                return $i1;
            }
        }
        return -1;
    }

    public void a(int i2) {
        if (this.k != i2) {
            this.k = i2;
            this.y = com.org.android.view.View.getAbsoluteGravity(i2, ViewCompat.getLayoutDirection(this.h));
        }
    }

    public void a(MenuBuilder menuBuilder, boolean $z0) {
        int $i1 = b(menuBuilder);
        if ($i1 >= 0) {
            int $i0 = $i1 + 1;
            if ($i0 < this.c.size()) {
                this.c.get($i0).c.close(false);
            }
            e $r4 = this.c.remove($i1);
            $r4.c.removeMenuPresenter(this);
            if (this.i) {
                $r4.this$0.show((Object) null);
                $r4.this$0.dismiss(0);
            }
            $r4.this$0.dismiss();
            int $i12 = this.c.size();
            this.r = $i12 > 0 ? this.c.get($i12 - 1).d : b();
            if ($i12 == 0) {
                dismiss();
                l$a $r7 = this.d;
                if ($r7 != null) {
                    $r7.onCloseMenu(menuBuilder, true);
                }
                ViewTreeObserver $r8 = this.x;
                if ($r8 != null) {
                    if ($r8.isAlive()) {
                        this.x.removeGlobalOnLayoutListener(this.t);
                    }
                    this.x = null;
                }
                this.this$0.removeOnAttachStateChangeListener(this.v);
                PopupWindow.OnDismissListener $r12 = this.D;
                PopupWindow.OnDismissListener onDismissListener = $r12;
                $r12.onDismiss();
            } else if ($z0) {
                this.c.get(0).c.close(false);
            }
        }
    }

    public void a(l$a l_a) {
        this.d = l_a;
    }

    public void a(View view) {
        if (this.h != view) {
            this.h = view;
            this.y = com.org.android.view.View.getAbsoluteGravity(this.k, ViewCompat.getLayoutDirection(this.h));
        }
    }

    public void a(PopupWindow.OnDismissListener onDismissListener) {
        this.D = onDismissListener;
    }

    public void a(boolean z2) {
        this.z = z2;
    }

    public boolean a(SubMenuBuilder subMenuBuilder) {
        for (e $r6 : this.c) {
            if (subMenuBuilder == $r6.c) {
                $r6.get().requestFocus();
                return true;
            }
        }
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        add((MenuBuilder) subMenuBuilder);
        l$a $r8 = this.d;
        if ($r8 == null) {
            return true;
        }
        $r8.onOpenSubMenu(subMenuBuilder);
        return true;
    }

    public ListView add() {
        if (this.c.isEmpty()) {
            return null;
        }
        List $r1 = this.c;
        return $r1.get($r1.size() - 1).get();
    }

    public void add(MenuBuilder menuBuilder) {
        menuBuilder.addMenuPresenter(this, this.a);
        if (isShowing()) {
            a(menuBuilder);
        } else {
            this.f.add(menuBuilder);
        }
    }

    public void b(int i2) {
        this.l = true;
        this.j = i2;
    }

    public void b(boolean z2) {
        this.b = z2;
    }

    public void c(int i2) {
        this.e = true;
        this.g = i2;
    }

    public void dismiss() {
        int $i0 = this.c.size();
        if ($i0 > 0) {
            e[] $r2 = (e[]) this.c.toArray(new e[$i0]);
            for (int $i02 = $i0 - 1; $i02 >= 0; $i02--) {
                e $r4 = $r2[$i02];
                if ($r4.this$0.isShowing()) {
                    $r4.this$0.dismiss();
                }
            }
        }
    }

    public boolean flagActionItems() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean g() {
        return false;
    }

    public boolean isShowing() {
        return this.c.size() > 0 && this.c.get(0).this$0.isShowing();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: android.support.v7.view.menu.e} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDismiss() {
        /*
            r10 = this;
            java.util.List<android.support.v7.view.menu.i$a> r0 = r10.c
            int r1 = r0.size()
            r2 = 0
        L_0x0007:
            if (r2 >= r1) goto L_0x001f
            java.util.List<android.support.v7.view.menu.i$a> r0 = r10.c
            java.lang.Object r3 = r0.get(r2)
            r5 = r3
            android.support.v7.view.menu.e r5 = (android.support.v7.view.menu.e) r5
            r4 = r5
            android.support.v7.widget.Label r6 = r4.this$0
            boolean r7 = r6.isShowing()
            if (r7 != 0) goto L_0x001c
            goto L_0x0020
        L_0x001c:
            int r2 = r2 + 1
            goto L_0x0007
        L_0x001f:
            r4 = 0
        L_0x0020:
            if (r4 == 0) goto L_0x0028
            android.support.v7.view.menu.MenuBuilder r8 = r4.c
            r9 = 0
            r8.close(r9)
        L_0x0028:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.view.menu.f.onDismiss():void");
    }

    public boolean onKey(View view, int i2, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i2 != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    public void show() {
        if (!isShowing()) {
            for (MenuBuilder $r4 : this.f) {
                a($r4);
            }
            this.f.clear();
            this.this$0 = this.h;
            if (this.this$0 != null) {
                boolean $z0 = this.x == null;
                this.x = this.this$0.getViewTreeObserver();
                if ($z0) {
                    this.x.addOnGlobalLayoutListener(this.t);
                }
                this.this$0.addOnAttachStateChangeListener(this.v);
            }
        }
    }

    public void updateMenuView(boolean z2) {
        for (e $r4 : this.c) {
            d.a($r4.get().getAdapter()).notifyDataSetChanged();
        }
    }
}

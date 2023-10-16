package android.support.p025v7.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.support.p025v7.view.menu.C0310v;
import android.view.View;
import android.widget.PopupWindow;
import p000a.p001a.p005c.p014g.C0111f;
import p000a.p001a.p005c.p014g.C0127u;

/* renamed from: android.support.v7.view.menu.u */
public class C0309u implements C0297n {

    /* renamed from: a */
    private final Context f1067a;

    /* renamed from: b */
    private final C0293l f1068b;

    /* renamed from: c */
    private final boolean f1069c;

    /* renamed from: d */
    private final int f1070d;

    /* renamed from: e */
    private final int f1071e;

    /* renamed from: f */
    private View f1072f;

    /* renamed from: g */
    private int f1073g;

    /* renamed from: h */
    private boolean f1074h;

    /* renamed from: i */
    private C0310v.C0311a f1075i;

    /* renamed from: j */
    private C0307s f1076j;

    /* renamed from: k */
    private PopupWindow.OnDismissListener f1077k;

    /* renamed from: l */
    private final PopupWindow.OnDismissListener f1078l;

    public C0309u(Context context, C0293l lVar, View view, boolean z, int i) {
        this(context, lVar, view, z, i, 0);
    }

    public C0309u(Context context, C0293l lVar, View view, boolean z, int i, int i2) {
        this.f1073g = 8388611;
        this.f1078l = new C0308t(this);
        this.f1067a = context;
        this.f1068b = lVar;
        this.f1072f = view;
        this.f1069c = z;
        this.f1070d = i;
        this.f1071e = i2;
    }

    /* renamed from: a */
    private void m1310a(int i, int i2, boolean z, boolean z2) {
        C0307s b = mo1461b();
        b.mo1139c(z2);
        if (z) {
            if ((C0111f.m395a(this.f1073g, C0127u.m450d(this.f1072f)) & 7) == 5) {
                i -= this.f1072f.getWidth();
            }
            b.mo1134b(i);
            b.mo1138c(i2);
            int i3 = (int) ((this.f1067a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            b.mo1450a(new Rect(i - i3, i2 - i3, i + i3, i2 + i3));
        }
        b.mo1137c();
    }

    /* JADX WARNING: type inference failed for: r0v7, types: [android.support.v7.view.menu.v, android.support.v7.view.menu.s] */
    /* JADX WARNING: type inference failed for: r7v1, types: [android.support.v7.view.menu.C] */
    /* JADX WARNING: type inference failed for: r1v13, types: [android.support.v7.view.menu.i] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: g */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.support.p025v7.view.menu.C0307s m1311g() {
        /*
            r14 = this;
            android.content.Context r0 = r14.f1067a
            java.lang.String r1 = "window"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.view.WindowManager r0 = (android.view.WindowManager) r0
            android.view.Display r0 = r0.getDefaultDisplay()
            android.graphics.Point r1 = new android.graphics.Point
            r1.<init>()
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 17
            if (r2 < r3) goto L_0x001d
            r0.getRealSize(r1)
            goto L_0x0020
        L_0x001d:
            r0.getSize(r1)
        L_0x0020:
            int r0 = r1.x
            int r1 = r1.y
            int r0 = java.lang.Math.min(r0, r1)
            android.content.Context r1 = r14.f1067a
            android.content.res.Resources r1 = r1.getResources()
            int r2 = p000a.p001a.p017d.p018a.C0139d.abc_cascading_menus_min_smallest_width
            int r1 = r1.getDimensionPixelSize(r2)
            if (r0 < r1) goto L_0x0038
            r0 = 1
            goto L_0x0039
        L_0x0038:
            r0 = 0
        L_0x0039:
            if (r0 == 0) goto L_0x004c
            android.support.v7.view.menu.i r0 = new android.support.v7.view.menu.i
            android.content.Context r2 = r14.f1067a
            android.view.View r3 = r14.f1072f
            int r4 = r14.f1070d
            int r5 = r14.f1071e
            boolean r6 = r14.f1069c
            r1 = r0
            r1.<init>(r2, r3, r4, r5, r6)
            goto L_0x005e
        L_0x004c:
            android.support.v7.view.menu.C r0 = new android.support.v7.view.menu.C
            android.content.Context r8 = r14.f1067a
            android.support.v7.view.menu.l r9 = r14.f1068b
            android.view.View r10 = r14.f1072f
            int r11 = r14.f1070d
            int r12 = r14.f1071e
            boolean r13 = r14.f1069c
            r7 = r0
            r7.<init>(r8, r9, r10, r11, r12, r13)
        L_0x005e:
            android.support.v7.view.menu.l r1 = r14.f1068b
            r0.mo1126a((android.support.p025v7.view.menu.C0293l) r1)
            android.widget.PopupWindow$OnDismissListener r1 = r14.f1078l
            r0.mo1130a((android.widget.PopupWindow.OnDismissListener) r1)
            android.view.View r1 = r14.f1072f
            r0.mo1129a((android.view.View) r1)
            android.support.v7.view.menu.v$a r1 = r14.f1075i
            r0.mo1128a((android.support.p025v7.view.menu.C0310v.C0311a) r1)
            boolean r1 = r14.f1074h
            r0.mo1135b((boolean) r1)
            int r1 = r14.f1073g
            r0.mo1125a((int) r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.view.menu.C0309u.m1311g():android.support.v7.view.menu.s");
    }

    /* renamed from: a */
    public void mo1454a() {
        if (mo1462c()) {
            this.f1076j.dismiss();
        }
    }

    /* renamed from: a */
    public void mo1455a(int i) {
        this.f1073g = i;
    }

    /* renamed from: a */
    public void mo1456a(C0310v.C0311a aVar) {
        this.f1075i = aVar;
        C0307s sVar = this.f1076j;
        if (sVar != null) {
            sVar.mo1128a(aVar);
        }
    }

    /* renamed from: a */
    public void mo1457a(View view) {
        this.f1072f = view;
    }

    /* renamed from: a */
    public void mo1458a(PopupWindow.OnDismissListener onDismissListener) {
        this.f1077k = onDismissListener;
    }

    /* renamed from: a */
    public void mo1459a(boolean z) {
        this.f1074h = z;
        C0307s sVar = this.f1076j;
        if (sVar != null) {
            sVar.mo1135b(z);
        }
    }

    /* renamed from: a */
    public boolean mo1460a(int i, int i2) {
        if (mo1462c()) {
            return true;
        }
        if (this.f1072f == null) {
            return false;
        }
        m1310a(i, i2, true, true);
        return true;
    }

    /* renamed from: b */
    public C0307s mo1461b() {
        if (this.f1076j == null) {
            this.f1076j = m1311g();
        }
        return this.f1076j;
    }

    /* renamed from: c */
    public boolean mo1462c() {
        C0307s sVar = this.f1076j;
        return sVar != null && sVar.mo1136b();
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo1463d() {
        this.f1076j = null;
        PopupWindow.OnDismissListener onDismissListener = this.f1077k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    /* renamed from: e */
    public void mo1464e() {
        if (!mo1465f()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    /* renamed from: f */
    public boolean mo1465f() {
        if (mo1462c()) {
            return true;
        }
        if (this.f1072f == null) {
            return false;
        }
        m1310a(0, 0, false, false);
        return true;
    }
}

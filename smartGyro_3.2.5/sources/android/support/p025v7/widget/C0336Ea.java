package android.support.p025v7.widget;

import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;
import p000a.p001a.p005c.p014g.C0127u;
import p000a.p001a.p005c.p014g.C0130v;

/* renamed from: android.support.v7.widget.Ea */
class C0336Ea implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {

    /* renamed from: a */
    private static C0336Ea f1204a;

    /* renamed from: b */
    private static C0336Ea f1205b;

    /* renamed from: c */
    private final View f1206c;

    /* renamed from: d */
    private final CharSequence f1207d;

    /* renamed from: e */
    private final int f1208e;

    /* renamed from: f */
    private final Runnable f1209f = new C0331Ca(this);

    /* renamed from: g */
    private final Runnable f1210g = new C0334Da(this);

    /* renamed from: h */
    private int f1211h;

    /* renamed from: i */
    private int f1212i;

    /* renamed from: j */
    private C0338Fa f1213j;

    /* renamed from: k */
    private boolean f1214k;

    private C0336Ea(View view, CharSequence charSequence) {
        this.f1206c = view;
        this.f1207d = charSequence;
        this.f1208e = C0130v.m466a(ViewConfiguration.get(this.f1206c.getContext()));
        m1462c();
        this.f1206c.setOnLongClickListener(this);
        this.f1206c.setOnHoverListener(this);
    }

    /* renamed from: a */
    private static void m1458a(C0336Ea ea) {
        C0336Ea ea2 = f1204a;
        if (ea2 != null) {
            ea2.m1461b();
        }
        f1204a = ea;
        C0336Ea ea3 = f1204a;
        if (ea3 != null) {
            ea3.m1463d();
        }
    }

    /* renamed from: a */
    public static void m1459a(View view, CharSequence charSequence) {
        C0336Ea ea = f1204a;
        if (ea != null && ea.f1206c == view) {
            m1458a((C0336Ea) null);
        }
        if (TextUtils.isEmpty(charSequence)) {
            C0336Ea ea2 = f1205b;
            if (ea2 != null && ea2.f1206c == view) {
                ea2.mo1705a();
            }
            view.setOnLongClickListener((View.OnLongClickListener) null);
            view.setLongClickable(false);
            view.setOnHoverListener((View.OnHoverListener) null);
            return;
        }
        new C0336Ea(view, charSequence);
    }

    /* renamed from: a */
    private boolean m1460a(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (Math.abs(x - this.f1211h) <= this.f1208e && Math.abs(y - this.f1212i) <= this.f1208e) {
            return false;
        }
        this.f1211h = x;
        this.f1212i = y;
        return true;
    }

    /* renamed from: b */
    private void m1461b() {
        this.f1206c.removeCallbacks(this.f1209f);
    }

    /* renamed from: c */
    private void m1462c() {
        this.f1211h = Integer.MAX_VALUE;
        this.f1212i = Integer.MAX_VALUE;
    }

    /* renamed from: d */
    private void m1463d() {
        this.f1206c.postDelayed(this.f1209f, (long) ViewConfiguration.getLongPressTimeout());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1705a() {
        if (f1205b == this) {
            f1205b = null;
            C0338Fa fa = this.f1213j;
            if (fa != null) {
                fa.mo1712a();
                this.f1213j = null;
                m1462c();
                this.f1206c.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (f1204a == this) {
            m1458a((C0336Ea) null);
        }
        this.f1206c.removeCallbacks(this.f1210g);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1706a(boolean z) {
        long j;
        if (C0127u.m454h(this.f1206c)) {
            m1458a((C0336Ea) null);
            C0336Ea ea = f1205b;
            if (ea != null) {
                ea.mo1705a();
            }
            f1205b = this;
            this.f1214k = z;
            this.f1213j = new C0338Fa(this.f1206c.getContext());
            this.f1213j.mo1713a(this.f1206c, this.f1211h, this.f1212i, this.f1214k, this.f1207d);
            this.f1206c.addOnAttachStateChangeListener(this);
            if (this.f1214k) {
                j = 2500;
            } else {
                j = ((C0127u.m452f(this.f1206c) & 1) == 1 ? 3000 : 15000) - ((long) ViewConfiguration.getLongPressTimeout());
            }
            this.f1206c.removeCallbacks(this.f1210g);
            this.f1206c.postDelayed(this.f1210g, j);
        }
    }

    public boolean onHover(View view, MotionEvent motionEvent) {
        if (this.f1213j != null && this.f1214k) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.f1206c.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                m1462c();
                mo1705a();
            }
        } else if (this.f1206c.isEnabled() && this.f1213j == null && m1460a(motionEvent)) {
            m1458a(this);
        }
        return false;
    }

    public boolean onLongClick(View view) {
        this.f1211h = view.getWidth() / 2;
        this.f1212i = view.getHeight() / 2;
        mo1706a(true);
        return true;
    }

    public void onViewAttachedToWindow(View view) {
    }

    public void onViewDetachedFromWindow(View view) {
        mo1705a();
    }
}

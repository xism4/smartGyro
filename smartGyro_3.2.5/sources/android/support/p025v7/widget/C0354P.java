package android.support.p025v7.widget;

import android.os.SystemClock;
import android.support.p025v7.view.menu.C0316z;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;

/* renamed from: android.support.v7.widget.P */
public abstract class C0354P implements View.OnTouchListener, View.OnAttachStateChangeListener {

    /* renamed from: a */
    private final float f1275a;

    /* renamed from: b */
    private final int f1276b;

    /* renamed from: c */
    private final int f1277c;

    /* renamed from: d */
    final View f1278d;

    /* renamed from: e */
    private Runnable f1279e;

    /* renamed from: f */
    private Runnable f1280f;

    /* renamed from: g */
    private boolean f1281g;

    /* renamed from: h */
    private int f1282h;

    /* renamed from: i */
    private final int[] f1283i = new int[2];

    /* renamed from: android.support.v7.widget.P$a */
    private class C0355a implements Runnable {
        C0355a() {
        }

        public void run() {
            ViewParent parent = C0354P.this.f1278d.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    /* renamed from: android.support.v7.widget.P$b */
    private class C0356b implements Runnable {
        C0356b() {
        }

        public void run() {
            C0354P.this.mo1795d();
        }
    }

    public C0354P(View view) {
        this.f1278d = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.f1275a = (float) ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        this.f1276b = ViewConfiguration.getTapTimeout();
        this.f1277c = (this.f1276b + ViewConfiguration.getLongPressTimeout()) / 2;
    }

    /* renamed from: a */
    private boolean m1578a(MotionEvent motionEvent) {
        C0349N n;
        View view = this.f1278d;
        C0316z a = mo1120a();
        if (a == null || !a.mo1136b() || (n = (C0349N) a.mo1140d()) == null || !n.isShown()) {
            return false;
        }
        MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
        m1580a(view, obtainNoHistory);
        m1582b(n, obtainNoHistory);
        boolean a2 = n.mo1778a(obtainNoHistory, this.f1282h);
        obtainNoHistory.recycle();
        int actionMasked = motionEvent.getActionMasked();
        return a2 && (actionMasked != 1 && actionMasked != 3);
    }

    /* renamed from: a */
    private static boolean m1579a(View view, float f, float f2, float f3) {
        float f4 = -f3;
        return f >= f4 && f2 >= f4 && f < ((float) (view.getRight() - view.getLeft())) + f3 && f2 < ((float) (view.getBottom() - view.getTop())) + f3;
    }

    /* renamed from: a */
    private boolean m1580a(View view, MotionEvent motionEvent) {
        int[] iArr = this.f1283i;
        view.getLocationOnScreen(iArr);
        motionEvent.offsetLocation((float) iArr[0], (float) iArr[1]);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0017, code lost:
        if (r1 != 3) goto L_0x006d;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m1581b(android.view.MotionEvent r6) {
        /*
            r5 = this;
            android.view.View r0 = r5.f1278d
            boolean r1 = r0.isEnabled()
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            int r1 = r6.getActionMasked()
            if (r1 == 0) goto L_0x0041
            r3 = 1
            if (r1 == r3) goto L_0x003d
            r4 = 2
            if (r1 == r4) goto L_0x001a
            r6 = 3
            if (r1 == r6) goto L_0x003d
            goto L_0x006d
        L_0x001a:
            int r1 = r5.f1282h
            int r1 = r6.findPointerIndex(r1)
            if (r1 < 0) goto L_0x006d
            float r4 = r6.getX(r1)
            float r6 = r6.getY(r1)
            float r1 = r5.f1275a
            boolean r6 = m1579a(r0, r4, r6, r1)
            if (r6 != 0) goto L_0x006d
            r5.m1583e()
            android.view.ViewParent r6 = r0.getParent()
            r6.requestDisallowInterceptTouchEvent(r3)
            return r3
        L_0x003d:
            r5.m1583e()
            goto L_0x006d
        L_0x0041:
            int r6 = r6.getPointerId(r2)
            r5.f1282h = r6
            java.lang.Runnable r6 = r5.f1279e
            if (r6 != 0) goto L_0x0052
            android.support.v7.widget.P$a r6 = new android.support.v7.widget.P$a
            r6.<init>()
            r5.f1279e = r6
        L_0x0052:
            java.lang.Runnable r6 = r5.f1279e
            int r1 = r5.f1276b
            long r3 = (long) r1
            r0.postDelayed(r6, r3)
            java.lang.Runnable r6 = r5.f1280f
            if (r6 != 0) goto L_0x0065
            android.support.v7.widget.P$b r6 = new android.support.v7.widget.P$b
            r6.<init>()
            r5.f1280f = r6
        L_0x0065:
            java.lang.Runnable r6 = r5.f1280f
            int r1 = r5.f1277c
            long r3 = (long) r1
            r0.postDelayed(r6, r3)
        L_0x006d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.widget.C0354P.m1581b(android.view.MotionEvent):boolean");
    }

    /* renamed from: b */
    private boolean m1582b(View view, MotionEvent motionEvent) {
        int[] iArr = this.f1283i;
        view.getLocationOnScreen(iArr);
        motionEvent.offsetLocation((float) (-iArr[0]), (float) (-iArr[1]));
        return true;
    }

    /* renamed from: e */
    private void m1583e() {
        Runnable runnable = this.f1280f;
        if (runnable != null) {
            this.f1278d.removeCallbacks(runnable);
        }
        Runnable runnable2 = this.f1279e;
        if (runnable2 != null) {
            this.f1278d.removeCallbacks(runnable2);
        }
    }

    /* renamed from: a */
    public abstract C0316z mo1120a();

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract boolean mo1121b();

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public boolean mo1794c() {
        C0316z a = mo1120a();
        if (a == null || !a.mo1136b()) {
            return true;
        }
        a.dismiss();
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo1795d() {
        m1583e();
        View view = this.f1278d;
        if (view.isEnabled() && !view.isLongClickable() && mo1121b()) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            view.onTouchEvent(obtain);
            obtain.recycle();
            this.f1281g = true;
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z;
        boolean z2 = this.f1281g;
        if (z2) {
            z = m1578a(motionEvent) || !mo1794c();
        } else {
            z = m1581b(motionEvent) && mo1121b();
            if (z) {
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                this.f1278d.onTouchEvent(obtain);
                obtain.recycle();
            }
        }
        this.f1281g = z;
        return z || z2;
    }

    public void onViewAttachedToWindow(View view) {
    }

    public void onViewDetachedFromWindow(View view) {
        this.f1281g = false;
        this.f1282h = -1;
        Runnable runnable = this.f1279e;
        if (runnable != null) {
            this.f1278d.removeCallbacks(runnable);
        }
    }
}

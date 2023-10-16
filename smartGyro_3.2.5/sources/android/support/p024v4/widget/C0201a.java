package android.support.p024v4.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import p000a.p001a.p005c.p014g.C0127u;

/* renamed from: android.support.v4.widget.a */
public abstract class C0201a implements View.OnTouchListener {

    /* renamed from: a */
    private static final int f508a = ViewConfiguration.getTapTimeout();

    /* renamed from: b */
    final C0202a f509b = new C0202a();

    /* renamed from: c */
    private final Interpolator f510c = new AccelerateInterpolator();

    /* renamed from: d */
    final View f511d;

    /* renamed from: e */
    private Runnable f512e;

    /* renamed from: f */
    private float[] f513f = {0.0f, 0.0f};

    /* renamed from: g */
    private float[] f514g = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* renamed from: h */
    private int f515h;

    /* renamed from: i */
    private int f516i;

    /* renamed from: j */
    private float[] f517j = {0.0f, 0.0f};

    /* renamed from: k */
    private float[] f518k = {0.0f, 0.0f};

    /* renamed from: l */
    private float[] f519l = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* renamed from: m */
    private boolean f520m;

    /* renamed from: n */
    boolean f521n;

    /* renamed from: o */
    boolean f522o;

    /* renamed from: p */
    boolean f523p;

    /* renamed from: q */
    private boolean f524q;

    /* renamed from: r */
    private boolean f525r;

    /* renamed from: android.support.v4.widget.a$a */
    private static class C0202a {

        /* renamed from: a */
        private int f526a;

        /* renamed from: b */
        private int f527b;

        /* renamed from: c */
        private float f528c;

        /* renamed from: d */
        private float f529d;

        /* renamed from: e */
        private long f530e = Long.MIN_VALUE;

        /* renamed from: f */
        private long f531f = 0;

        /* renamed from: g */
        private int f532g = 0;

        /* renamed from: h */
        private int f533h = 0;

        /* renamed from: i */
        private long f534i = -1;

        /* renamed from: j */
        private float f535j;

        /* renamed from: k */
        private int f536k;

        C0202a() {
        }

        /* renamed from: a */
        private float m764a(float f) {
            return (-4.0f * f * f) + (f * 4.0f);
        }

        /* renamed from: a */
        private float m765a(long j) {
            if (j < this.f530e) {
                return 0.0f;
            }
            long j2 = this.f534i;
            if (j2 < 0 || j < j2) {
                return C0201a.m742a(((float) (j - this.f530e)) / ((float) this.f526a), 0.0f, 1.0f) * 0.5f;
            }
            long j3 = j - j2;
            float f = this.f535j;
            return (1.0f - f) + (f * C0201a.m742a(((float) j3) / ((float) this.f536k), 0.0f, 1.0f));
        }

        /* renamed from: a */
        public void mo878a() {
            if (this.f531f != 0) {
                long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                float a = m764a(m765a(currentAnimationTimeMillis));
                this.f531f = currentAnimationTimeMillis;
                float f = ((float) (currentAnimationTimeMillis - this.f531f)) * a;
                this.f532g = (int) (this.f528c * f);
                this.f533h = (int) (f * this.f529d);
                return;
            }
            throw new RuntimeException("Cannot compute scroll delta before calling start()");
        }

        /* renamed from: a */
        public void mo879a(float f, float f2) {
            this.f528c = f;
            this.f529d = f2;
        }

        /* renamed from: a */
        public void mo880a(int i) {
            this.f527b = i;
        }

        /* renamed from: b */
        public int mo881b() {
            return this.f532g;
        }

        /* renamed from: b */
        public void mo882b(int i) {
            this.f526a = i;
        }

        /* renamed from: c */
        public int mo883c() {
            return this.f533h;
        }

        /* renamed from: d */
        public int mo884d() {
            float f = this.f528c;
            return (int) (f / Math.abs(f));
        }

        /* renamed from: e */
        public int mo885e() {
            float f = this.f529d;
            return (int) (f / Math.abs(f));
        }

        /* renamed from: f */
        public boolean mo886f() {
            return this.f534i > 0 && AnimationUtils.currentAnimationTimeMillis() > this.f534i + ((long) this.f536k);
        }

        /* renamed from: g */
        public void mo887g() {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.f536k = C0201a.m745a((int) (currentAnimationTimeMillis - this.f530e), 0, this.f527b);
            this.f535j = m765a(currentAnimationTimeMillis);
            this.f534i = currentAnimationTimeMillis;
        }

        /* renamed from: h */
        public void mo888h() {
            this.f530e = AnimationUtils.currentAnimationTimeMillis();
            this.f534i = -1;
            this.f531f = this.f530e;
            this.f535j = 0.5f;
            this.f532g = 0;
            this.f533h = 0;
        }
    }

    /* renamed from: android.support.v4.widget.a$b */
    private class C0203b implements Runnable {
        C0203b() {
        }

        public void run() {
            C0201a aVar = C0201a.this;
            if (aVar.f523p) {
                if (aVar.f521n) {
                    aVar.f521n = false;
                    aVar.f509b.mo888h();
                }
                C0202a aVar2 = C0201a.this.f509b;
                if (aVar2.mo886f() || !C0201a.this.mo868b()) {
                    C0201a.this.f523p = false;
                    return;
                }
                C0201a aVar3 = C0201a.this;
                if (aVar3.f522o) {
                    aVar3.f522o = false;
                    aVar3.mo864a();
                }
                aVar2.mo878a();
                C0201a.this.mo865a(aVar2.mo881b(), aVar2.mo883c());
                C0127u.m444a(C0201a.this.f511d, (Runnable) this);
            }
        }
    }

    public C0201a(View view) {
        this.f511d = view;
        float f = Resources.getSystem().getDisplayMetrics().density;
        float f2 = (float) ((int) ((1575.0f * f) + 0.5f));
        mo867b(f2, f2);
        float f3 = (float) ((int) ((f * 315.0f) + 0.5f));
        mo870c(f3, f3);
        mo873d(1);
        mo862a(Float.MAX_VALUE, Float.MAX_VALUE);
        mo872d(0.2f, 0.2f);
        mo874e(1.0f, 1.0f);
        mo871c(f508a);
        mo876f(500);
        mo875e(500);
    }

    /* renamed from: a */
    static float m742a(float f, float f2, float f3) {
        return f > f3 ? f3 : f < f2 ? f2 : f;
    }

    /* renamed from: a */
    private float m743a(float f, float f2, float f3, float f4) {
        float f5;
        float a = m742a(f * f2, 0.0f, f3);
        float f6 = m748f(f2 - f4, a) - m748f(f4, a);
        if (f6 < 0.0f) {
            f5 = -this.f510c.getInterpolation(-f6);
        } else if (f6 <= 0.0f) {
            return 0.0f;
        } else {
            f5 = this.f510c.getInterpolation(f6);
        }
        return m742a(f5, -1.0f, 1.0f);
    }

    /* renamed from: a */
    private float m744a(int i, float f, float f2, float f3) {
        float a = m743a(this.f513f[i], f2, this.f514g[i], f);
        if (a == 0.0f) {
            return 0.0f;
        }
        float f4 = this.f517j[i];
        float f5 = this.f518k[i];
        float f6 = this.f519l[i];
        float f7 = f4 * f3;
        return a > 0.0f ? m742a(a * f7, f5, f6) : -m742a((-a) * f7, f5, f6);
    }

    /* renamed from: a */
    static int m745a(int i, int i2, int i3) {
        return i > i3 ? i3 : i < i2 ? i2 : i;
    }

    /* renamed from: c */
    private void m746c() {
        if (this.f521n) {
            this.f523p = false;
        } else {
            this.f509b.mo887g();
        }
    }

    /* renamed from: d */
    private void m747d() {
        int i;
        if (this.f512e == null) {
            this.f512e = new C0203b();
        }
        this.f523p = true;
        this.f521n = true;
        if (this.f520m || (i = this.f516i) <= 0) {
            this.f512e.run();
        } else {
            C0127u.m445a(this.f511d, this.f512e, (long) i);
        }
        this.f520m = true;
    }

    /* renamed from: f */
    private float m748f(float f, float f2) {
        if (f2 == 0.0f) {
            return 0.0f;
        }
        int i = this.f515h;
        if (i == 0 || i == 1) {
            if (f < f2) {
                return f >= 0.0f ? 1.0f - (f / f2) : (!this.f523p || this.f515h != 1) ? 0.0f : 1.0f;
            }
        } else if (i == 2 && f < 0.0f) {
            return f / (-f2);
        }
    }

    /* renamed from: a */
    public C0201a mo862a(float f, float f2) {
        float[] fArr = this.f514g;
        fArr[0] = f;
        fArr[1] = f2;
        return this;
    }

    /* renamed from: a */
    public C0201a mo863a(boolean z) {
        if (this.f524q && !z) {
            m746c();
        }
        this.f524q = z;
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo864a() {
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
        this.f511d.onTouchEvent(obtain);
        obtain.recycle();
    }

    /* renamed from: a */
    public abstract void mo865a(int i, int i2);

    /* renamed from: a */
    public abstract boolean mo866a(int i);

    /* renamed from: b */
    public C0201a mo867b(float f, float f2) {
        float[] fArr = this.f519l;
        fArr[0] = f / 1000.0f;
        fArr[1] = f2 / 1000.0f;
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean mo868b() {
        C0202a aVar = this.f509b;
        int e = aVar.mo885e();
        int d = aVar.mo884d();
        return (e != 0 && mo869b(e)) || (d != 0 && mo866a(d));
    }

    /* renamed from: b */
    public abstract boolean mo869b(int i);

    /* renamed from: c */
    public C0201a mo870c(float f, float f2) {
        float[] fArr = this.f518k;
        fArr[0] = f / 1000.0f;
        fArr[1] = f2 / 1000.0f;
        return this;
    }

    /* renamed from: c */
    public C0201a mo871c(int i) {
        this.f516i = i;
        return this;
    }

    /* renamed from: d */
    public C0201a mo872d(float f, float f2) {
        float[] fArr = this.f513f;
        fArr[0] = f;
        fArr[1] = f2;
        return this;
    }

    /* renamed from: d */
    public C0201a mo873d(int i) {
        this.f515h = i;
        return this;
    }

    /* renamed from: e */
    public C0201a mo874e(float f, float f2) {
        float[] fArr = this.f517j;
        fArr[0] = f / 1000.0f;
        fArr[1] = f2 / 1000.0f;
        return this;
    }

    /* renamed from: e */
    public C0201a mo875e(int i) {
        this.f509b.mo880a(i);
        return this;
    }

    /* renamed from: f */
    public C0201a mo876f(int i) {
        this.f509b.mo882b(i);
        return this;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0013, code lost:
        if (r0 != 3) goto L_0x0058;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0060 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouch(android.view.View r6, android.view.MotionEvent r7) {
        /*
            r5 = this;
            boolean r0 = r5.f524q
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            int r0 = r7.getActionMasked()
            r2 = 1
            if (r0 == 0) goto L_0x001a
            if (r0 == r2) goto L_0x0016
            r3 = 2
            if (r0 == r3) goto L_0x001e
            r6 = 3
            if (r0 == r6) goto L_0x0016
            goto L_0x0058
        L_0x0016:
            r5.m746c()
            goto L_0x0058
        L_0x001a:
            r5.f522o = r2
            r5.f520m = r1
        L_0x001e:
            float r0 = r7.getX()
            int r3 = r6.getWidth()
            float r3 = (float) r3
            android.view.View r4 = r5.f511d
            int r4 = r4.getWidth()
            float r4 = (float) r4
            float r0 = r5.m744a((int) r1, (float) r0, (float) r3, (float) r4)
            float r7 = r7.getY()
            int r6 = r6.getHeight()
            float r6 = (float) r6
            android.view.View r3 = r5.f511d
            int r3 = r3.getHeight()
            float r3 = (float) r3
            float r6 = r5.m744a((int) r2, (float) r7, (float) r6, (float) r3)
            android.support.v4.widget.a$a r7 = r5.f509b
            r7.mo879a(r0, r6)
            boolean r6 = r5.f523p
            if (r6 != 0) goto L_0x0058
            boolean r6 = r5.mo868b()
            if (r6 == 0) goto L_0x0058
            r5.m747d()
        L_0x0058:
            boolean r6 = r5.f525r
            if (r6 == 0) goto L_0x0061
            boolean r6 = r5.f523p
            if (r6 == 0) goto L_0x0061
            r1 = 1
        L_0x0061:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p024v4.widget.C0201a.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }
}

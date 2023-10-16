package p000a.p001a.p017d.p021c.p022a;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.p024v4.graphics.drawable.C0190a;
import android.util.SparseArray;

/* renamed from: a.a.d.c.a.d */
class C0157d extends Drawable implements Drawable.Callback {

    /* renamed from: a */
    private C0159b f321a;

    /* renamed from: b */
    private Rect f322b;

    /* renamed from: c */
    private Drawable f323c;

    /* renamed from: d */
    private Drawable f324d;

    /* renamed from: e */
    private int f325e = 255;

    /* renamed from: f */
    private boolean f326f;

    /* renamed from: g */
    private int f327g = -1;

    /* renamed from: h */
    private int f328h = -1;

    /* renamed from: i */
    private boolean f329i;

    /* renamed from: j */
    private Runnable f330j;

    /* renamed from: k */
    private long f331k;

    /* renamed from: l */
    private long f332l;

    /* renamed from: m */
    private C0158a f333m;

    /* renamed from: a.a.d.c.a.d$a */
    static class C0158a implements Drawable.Callback {

        /* renamed from: a */
        private Drawable.Callback f334a;

        C0158a() {
        }

        /* renamed from: a */
        public C0158a mo573a(Drawable.Callback callback) {
            this.f334a = callback;
            return this;
        }

        /* renamed from: a */
        public Drawable.Callback mo574a() {
            Drawable.Callback callback = this.f334a;
            this.f334a = null;
            return callback;
        }

        public void invalidateDrawable(Drawable drawable) {
        }

        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
            Drawable.Callback callback = this.f334a;
            if (callback != null) {
                callback.scheduleDrawable(drawable, runnable, j);
            }
        }

        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            Drawable.Callback callback = this.f334a;
            if (callback != null) {
                callback.unscheduleDrawable(drawable, runnable);
            }
        }
    }

    /* renamed from: a.a.d.c.a.d$b */
    static abstract class C0159b extends Drawable.ConstantState {

        /* renamed from: A */
        int f335A;

        /* renamed from: B */
        int f336B;

        /* renamed from: C */
        boolean f337C;

        /* renamed from: D */
        ColorFilter f338D;

        /* renamed from: E */
        boolean f339E;

        /* renamed from: F */
        ColorStateList f340F;

        /* renamed from: G */
        PorterDuff.Mode f341G;

        /* renamed from: H */
        boolean f342H;

        /* renamed from: I */
        boolean f343I;

        /* renamed from: a */
        final C0157d f344a;

        /* renamed from: b */
        Resources f345b;

        /* renamed from: c */
        int f346c = 160;

        /* renamed from: d */
        int f347d;

        /* renamed from: e */
        int f348e;

        /* renamed from: f */
        SparseArray<Drawable.ConstantState> f349f;

        /* renamed from: g */
        Drawable[] f350g;

        /* renamed from: h */
        int f351h;

        /* renamed from: i */
        boolean f352i;

        /* renamed from: j */
        boolean f353j;

        /* renamed from: k */
        Rect f354k;

        /* renamed from: l */
        boolean f355l;

        /* renamed from: m */
        boolean f356m;

        /* renamed from: n */
        int f357n;

        /* renamed from: o */
        int f358o;

        /* renamed from: p */
        int f359p;

        /* renamed from: q */
        int f360q;

        /* renamed from: r */
        boolean f361r;

        /* renamed from: s */
        int f362s;

        /* renamed from: t */
        boolean f363t;

        /* renamed from: u */
        boolean f364u;

        /* renamed from: v */
        boolean f365v;

        /* renamed from: w */
        boolean f366w;

        /* renamed from: x */
        boolean f367x;

        /* renamed from: y */
        boolean f368y;

        /* renamed from: z */
        int f369z;

        C0159b(C0159b bVar, C0157d dVar, Resources resources) {
            this.f352i = false;
            this.f355l = false;
            this.f367x = true;
            this.f335A = 0;
            this.f336B = 0;
            this.f344a = dVar;
            this.f345b = resources != null ? resources : bVar != null ? bVar.f345b : null;
            this.f346c = C0157d.m531a(resources, bVar != null ? bVar.f346c : 0);
            if (bVar != null) {
                this.f347d = bVar.f347d;
                this.f348e = bVar.f348e;
                this.f365v = true;
                this.f366w = true;
                this.f352i = bVar.f352i;
                this.f355l = bVar.f355l;
                this.f367x = bVar.f367x;
                this.f368y = bVar.f368y;
                this.f369z = bVar.f369z;
                this.f335A = bVar.f335A;
                this.f336B = bVar.f336B;
                this.f337C = bVar.f337C;
                this.f338D = bVar.f338D;
                this.f339E = bVar.f339E;
                this.f340F = bVar.f340F;
                this.f341G = bVar.f341G;
                this.f342H = bVar.f342H;
                this.f343I = bVar.f343I;
                if (bVar.f346c == this.f346c) {
                    if (bVar.f353j) {
                        this.f354k = new Rect(bVar.f354k);
                        this.f353j = true;
                    }
                    if (bVar.f356m) {
                        this.f357n = bVar.f357n;
                        this.f358o = bVar.f358o;
                        this.f359p = bVar.f359p;
                        this.f360q = bVar.f360q;
                        this.f356m = true;
                    }
                }
                if (bVar.f361r) {
                    this.f362s = bVar.f362s;
                    this.f361r = true;
                }
                if (bVar.f363t) {
                    this.f364u = bVar.f364u;
                    this.f363t = true;
                }
                Drawable[] drawableArr = bVar.f350g;
                this.f350g = new Drawable[drawableArr.length];
                this.f351h = bVar.f351h;
                SparseArray<Drawable.ConstantState> sparseArray = bVar.f349f;
                this.f349f = sparseArray != null ? sparseArray.clone() : new SparseArray<>(this.f351h);
                int i = this.f351h;
                for (int i2 = 0; i2 < i; i2++) {
                    if (drawableArr[i2] != null) {
                        Drawable.ConstantState constantState = drawableArr[i2].getConstantState();
                        if (constantState != null) {
                            this.f349f.put(i2, constantState);
                        } else {
                            this.f350g[i2] = drawableArr[i2];
                        }
                    }
                }
                return;
            }
            this.f350g = new Drawable[10];
            this.f351h = 0;
        }

        /* renamed from: b */
        private Drawable m542b(Drawable drawable) {
            if (Build.VERSION.SDK_INT >= 23) {
                drawable.setLayoutDirection(this.f369z);
            }
            Drawable mutate = drawable.mutate();
            mutate.setCallback(this.f344a);
            return mutate;
        }

        /* renamed from: n */
        private void m543n() {
            SparseArray<Drawable.ConstantState> sparseArray = this.f349f;
            if (sparseArray != null) {
                int size = sparseArray.size();
                for (int i = 0; i < size; i++) {
                    this.f350g[this.f349f.keyAt(i)] = m542b(this.f349f.valueAt(i).newDrawable(this.f345b));
                }
                this.f349f = null;
            }
        }

        /* renamed from: a */
        public final int mo578a(Drawable drawable) {
            int i = this.f351h;
            if (i >= this.f350g.length) {
                mo580a(i, i + 10);
            }
            drawable.mutate();
            drawable.setVisible(false, true);
            drawable.setCallback(this.f344a);
            this.f350g[i] = drawable;
            this.f351h++;
            this.f348e = drawable.getChangingConfigurations() | this.f348e;
            mo600k();
            this.f354k = null;
            this.f353j = false;
            this.f356m = false;
            this.f365v = false;
            return i;
        }

        /* renamed from: a */
        public final Drawable mo579a(int i) {
            int indexOfKey;
            Drawable drawable = this.f350g[i];
            if (drawable != null) {
                return drawable;
            }
            SparseArray<Drawable.ConstantState> sparseArray = this.f349f;
            if (sparseArray == null || (indexOfKey = sparseArray.indexOfKey(i)) < 0) {
                return null;
            }
            Drawable b = m542b(this.f349f.valueAt(indexOfKey).newDrawable(this.f345b));
            this.f350g[i] = b;
            this.f349f.removeAt(indexOfKey);
            if (this.f349f.size() == 0) {
                this.f349f = null;
            }
            return b;
        }

        /* renamed from: a */
        public void mo580a(int i, int i2) {
            Drawable[] drawableArr = new Drawable[i2];
            System.arraycopy(this.f350g, 0, drawableArr, 0, i);
            this.f350g = drawableArr;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public final void mo581a(Resources.Theme theme) {
            if (theme != null) {
                m543n();
                int i = this.f351h;
                Drawable[] drawableArr = this.f350g;
                for (int i2 = 0; i2 < i; i2++) {
                    if (drawableArr[i2] != null && drawableArr[i2].canApplyTheme()) {
                        drawableArr[i2].applyTheme(theme);
                        this.f348e |= drawableArr[i2].getChangingConfigurations();
                    }
                }
                mo582a(theme.getResources());
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public final void mo582a(Resources resources) {
            if (resources != null) {
                this.f345b = resources;
                int a = C0157d.m531a(resources, this.f346c);
                int i = this.f346c;
                this.f346c = a;
                if (i != a) {
                    this.f356m = false;
                    this.f353j = false;
                }
            }
        }

        /* renamed from: a */
        public final void mo583a(boolean z) {
            this.f355l = z;
        }

        /* renamed from: a */
        public synchronized boolean mo584a() {
            if (this.f365v) {
                return this.f366w;
            }
            m543n();
            this.f365v = true;
            int i = this.f351h;
            Drawable[] drawableArr = this.f350g;
            for (int i2 = 0; i2 < i; i2++) {
                if (drawableArr[i2].getConstantState() == null) {
                    this.f366w = false;
                    return false;
                }
            }
            this.f366w = true;
            return true;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void mo585b() {
            this.f356m = true;
            m543n();
            int i = this.f351h;
            Drawable[] drawableArr = this.f350g;
            this.f358o = -1;
            this.f357n = -1;
            this.f360q = 0;
            this.f359p = 0;
            for (int i2 = 0; i2 < i; i2++) {
                Drawable drawable = drawableArr[i2];
                int intrinsicWidth = drawable.getIntrinsicWidth();
                if (intrinsicWidth > this.f357n) {
                    this.f357n = intrinsicWidth;
                }
                int intrinsicHeight = drawable.getIntrinsicHeight();
                if (intrinsicHeight > this.f358o) {
                    this.f358o = intrinsicHeight;
                }
                int minimumWidth = drawable.getMinimumWidth();
                if (minimumWidth > this.f359p) {
                    this.f359p = minimumWidth;
                }
                int minimumHeight = drawable.getMinimumHeight();
                if (minimumHeight > this.f360q) {
                    this.f360q = minimumHeight;
                }
            }
        }

        /* renamed from: b */
        public final void mo586b(int i) {
            this.f335A = i;
        }

        /* renamed from: b */
        public final void mo587b(boolean z) {
            this.f352i = z;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public final boolean mo588b(int i, int i2) {
            int i3 = this.f351h;
            Drawable[] drawableArr = this.f350g;
            boolean z = false;
            for (int i4 = 0; i4 < i3; i4++) {
                if (drawableArr[i4] != null) {
                    boolean layoutDirection = Build.VERSION.SDK_INT >= 23 ? drawableArr[i4].setLayoutDirection(i) : false;
                    if (i4 == i2) {
                        z = layoutDirection;
                    }
                }
            }
            this.f369z = i;
            return z;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: c */
        public final int mo589c() {
            return this.f350g.length;
        }

        /* renamed from: c */
        public final void mo590c(int i) {
            this.f336B = i;
        }

        public boolean canApplyTheme() {
            int i = this.f351h;
            Drawable[] drawableArr = this.f350g;
            for (int i2 = 0; i2 < i; i2++) {
                Drawable drawable = drawableArr[i2];
                if (drawable == null) {
                    Drawable.ConstantState constantState = this.f349f.get(i2);
                    if (constantState != null && constantState.canApplyTheme()) {
                        return true;
                    }
                } else if (drawable.canApplyTheme()) {
                    return true;
                }
            }
            return false;
        }

        /* renamed from: d */
        public final int mo592d() {
            return this.f351h;
        }

        /* renamed from: e */
        public final int mo593e() {
            if (!this.f356m) {
                mo585b();
            }
            return this.f358o;
        }

        /* renamed from: f */
        public final int mo594f() {
            if (!this.f356m) {
                mo585b();
            }
            return this.f360q;
        }

        /* renamed from: g */
        public final int mo595g() {
            if (!this.f356m) {
                mo585b();
            }
            return this.f359p;
        }

        public int getChangingConfigurations() {
            return this.f347d | this.f348e;
        }

        /* renamed from: h */
        public final Rect mo597h() {
            if (this.f352i) {
                return null;
            }
            if (this.f354k != null || this.f353j) {
                return this.f354k;
            }
            m543n();
            Rect rect = new Rect();
            int i = this.f351h;
            Drawable[] drawableArr = this.f350g;
            Rect rect2 = null;
            for (int i2 = 0; i2 < i; i2++) {
                if (drawableArr[i2].getPadding(rect)) {
                    if (rect2 == null) {
                        rect2 = new Rect(0, 0, 0, 0);
                    }
                    int i3 = rect.left;
                    if (i3 > rect2.left) {
                        rect2.left = i3;
                    }
                    int i4 = rect.top;
                    if (i4 > rect2.top) {
                        rect2.top = i4;
                    }
                    int i5 = rect.right;
                    if (i5 > rect2.right) {
                        rect2.right = i5;
                    }
                    int i6 = rect.bottom;
                    if (i6 > rect2.bottom) {
                        rect2.bottom = i6;
                    }
                }
            }
            this.f353j = true;
            this.f354k = rect2;
            return rect2;
        }

        /* renamed from: i */
        public final int mo598i() {
            if (!this.f356m) {
                mo585b();
            }
            return this.f357n;
        }

        /* renamed from: j */
        public final int mo599j() {
            if (this.f361r) {
                return this.f362s;
            }
            m543n();
            int i = this.f351h;
            Drawable[] drawableArr = this.f350g;
            int opacity = i > 0 ? drawableArr[0].getOpacity() : -2;
            for (int i2 = 1; i2 < i; i2++) {
                opacity = Drawable.resolveOpacity(opacity, drawableArr[i2].getOpacity());
            }
            this.f362s = opacity;
            this.f361r = true;
            return opacity;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: k */
        public void mo600k() {
            this.f361r = false;
            this.f363t = false;
        }

        /* renamed from: l */
        public final boolean mo601l() {
            return this.f355l;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: m */
        public abstract void mo557m();
    }

    C0157d() {
    }

    /* renamed from: a */
    static int m531a(Resources resources, int i) {
        if (resources != null) {
            i = resources.getDisplayMetrics().densityDpi;
        }
        if (i == 0) {
            return 160;
        }
        return i;
    }

    /* renamed from: a */
    private void m532a(Drawable drawable) {
        if (this.f333m == null) {
            this.f333m = new C0158a();
        }
        C0158a aVar = this.f333m;
        aVar.mo573a(drawable.getCallback());
        drawable.setCallback(aVar);
        try {
            if (this.f321a.f335A <= 0 && this.f326f) {
                drawable.setAlpha(this.f325e);
            }
            if (this.f321a.f339E) {
                drawable.setColorFilter(this.f321a.f338D);
            } else {
                if (this.f321a.f342H) {
                    C0190a.m674a(drawable, this.f321a.f340F);
                }
                if (this.f321a.f343I) {
                    C0190a.m677a(drawable, this.f321a.f341G);
                }
            }
            drawable.setVisible(isVisible(), true);
            drawable.setDither(this.f321a.f367x);
            drawable.setState(getState());
            drawable.setLevel(getLevel());
            drawable.setBounds(getBounds());
            if (Build.VERSION.SDK_INT >= 23) {
                drawable.setLayoutDirection(getLayoutDirection());
            }
            if (Build.VERSION.SDK_INT >= 19) {
                drawable.setAutoMirrored(this.f321a.f337C);
            }
            Rect rect = this.f322b;
            if (Build.VERSION.SDK_INT >= 21 && rect != null) {
                drawable.setHotspotBounds(rect.left, rect.top, rect.right, rect.bottom);
            }
        } finally {
            drawable.setCallback(this.f333m.mo574a());
        }
    }

    @SuppressLint({"WrongConstant"})
    @TargetApi(23)
    /* renamed from: c */
    private boolean m533c() {
        return isAutoMirrored() && getLayoutDirection() == 1;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C0159b mo513a() {
        throw null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo514a(C0159b bVar) {
        this.f321a = bVar;
        int i = this.f327g;
        if (i >= 0) {
            this.f323c = bVar.mo579a(i);
            Drawable drawable = this.f323c;
            if (drawable != null) {
                m532a(drawable);
            }
        }
        this.f328h = -1;
        this.f324d = null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo566a(Resources resources) {
        this.f321a.mo582a(resources);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0069 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo567a(boolean r14) {
        /*
            r13 = this;
            r0 = 1
            r13.f326f = r0
            long r1 = android.os.SystemClock.uptimeMillis()
            android.graphics.drawable.Drawable r3 = r13.f323c
            r4 = 255(0xff, double:1.26E-321)
            r6 = 0
            r7 = 0
            if (r3 == 0) goto L_0x0036
            long r9 = r13.f331k
            int r11 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r11 == 0) goto L_0x0038
            int r11 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r11 > 0) goto L_0x0020
            int r9 = r13.f325e
            r3.setAlpha(r9)
            goto L_0x0036
        L_0x0020:
            long r9 = r9 - r1
            long r9 = r9 * r4
            int r10 = (int) r9
            a.a.d.c.a.d$b r9 = r13.f321a
            int r9 = r9.f335A
            int r10 = r10 / r9
            int r9 = 255 - r10
            int r10 = r13.f325e
            int r9 = r9 * r10
            int r9 = r9 / 255
            r3.setAlpha(r9)
            r3 = 1
            goto L_0x0039
        L_0x0036:
            r13.f331k = r7
        L_0x0038:
            r3 = 0
        L_0x0039:
            android.graphics.drawable.Drawable r9 = r13.f324d
            if (r9 == 0) goto L_0x0064
            long r10 = r13.f332l
            int r12 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r12 == 0) goto L_0x0066
            int r12 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r12 > 0) goto L_0x0051
            r9.setVisible(r6, r6)
            r0 = 0
            r13.f324d = r0
            r0 = -1
            r13.f328h = r0
            goto L_0x0064
        L_0x0051:
            long r10 = r10 - r1
            long r10 = r10 * r4
            int r3 = (int) r10
            a.a.d.c.a.d$b r4 = r13.f321a
            int r4 = r4.f336B
            int r3 = r3 / r4
            int r4 = r13.f325e
            int r3 = r3 * r4
            int r3 = r3 / 255
            r9.setAlpha(r3)
            goto L_0x0067
        L_0x0064:
            r13.f332l = r7
        L_0x0066:
            r0 = r3
        L_0x0067:
            if (r14 == 0) goto L_0x0073
            if (r0 == 0) goto L_0x0073
            java.lang.Runnable r14 = r13.f330j
            r3 = 16
            long r1 = r1 + r3
            r13.scheduleSelf(r14, r1)
        L_0x0073:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000a.p001a.p017d.p021c.p022a.C0157d.mo567a(boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0079  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo568a(int r9) {
        /*
            r8 = this;
            int r0 = r8.f327g
            r1 = 0
            if (r9 != r0) goto L_0x0006
            return r1
        L_0x0006:
            long r2 = android.os.SystemClock.uptimeMillis()
            a.a.d.c.a.d$b r0 = r8.f321a
            int r0 = r0.f336B
            r4 = -1
            r5 = 0
            r6 = 0
            if (r0 <= 0) goto L_0x0035
            android.graphics.drawable.Drawable r0 = r8.f324d
            if (r0 == 0) goto L_0x001b
            r0.setVisible(r1, r1)
        L_0x001b:
            android.graphics.drawable.Drawable r0 = r8.f323c
            if (r0 == 0) goto L_0x002e
            r8.f324d = r0
            int r0 = r8.f327g
            r8.f328h = r0
            a.a.d.c.a.d$b r0 = r8.f321a
            int r0 = r0.f336B
            long r0 = (long) r0
            long r0 = r0 + r2
            r8.f332l = r0
            goto L_0x003c
        L_0x002e:
            r8.f324d = r5
            r8.f328h = r4
            r8.f332l = r6
            goto L_0x003c
        L_0x0035:
            android.graphics.drawable.Drawable r0 = r8.f323c
            if (r0 == 0) goto L_0x003c
            r0.setVisible(r1, r1)
        L_0x003c:
            if (r9 < 0) goto L_0x005c
            a.a.d.c.a.d$b r0 = r8.f321a
            int r1 = r0.f351h
            if (r9 >= r1) goto L_0x005c
            android.graphics.drawable.Drawable r0 = r0.mo579a((int) r9)
            r8.f323c = r0
            r8.f327g = r9
            if (r0 == 0) goto L_0x0060
            a.a.d.c.a.d$b r9 = r8.f321a
            int r9 = r9.f335A
            if (r9 <= 0) goto L_0x0058
            long r4 = (long) r9
            long r2 = r2 + r4
            r8.f331k = r2
        L_0x0058:
            r8.m532a((android.graphics.drawable.Drawable) r0)
            goto L_0x0060
        L_0x005c:
            r8.f323c = r5
            r8.f327g = r4
        L_0x0060:
            long r0 = r8.f331k
            r9 = 1
            int r2 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r2 != 0) goto L_0x006d
            long r0 = r8.f332l
            int r2 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x007f
        L_0x006d:
            java.lang.Runnable r0 = r8.f330j
            if (r0 != 0) goto L_0x0079
            a.a.d.c.a.c r0 = new a.a.d.c.a.c
            r0.<init>(r8)
            r8.f330j = r0
            goto L_0x007c
        L_0x0079:
            r8.unscheduleSelf(r0)
        L_0x007c:
            r8.mo567a((boolean) r9)
        L_0x007f:
            r8.invalidateSelf()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: p000a.p001a.p017d.p021c.p022a.C0157d.mo568a(int):boolean");
    }

    public void applyTheme(Resources.Theme theme) {
        this.f321a.mo581a(theme);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public int mo569b() {
        return this.f327g;
    }

    public boolean canApplyTheme() {
        return this.f321a.canApplyTheme();
    }

    public void draw(Canvas canvas) {
        Drawable drawable = this.f323c;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        Drawable drawable2 = this.f324d;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
    }

    public int getAlpha() {
        return this.f325e;
    }

    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.f321a.getChangingConfigurations();
    }

    public final Drawable.ConstantState getConstantState() {
        if (!this.f321a.mo584a()) {
            return null;
        }
        this.f321a.f347d = getChangingConfigurations();
        return this.f321a;
    }

    public Drawable getCurrent() {
        return this.f323c;
    }

    public void getHotspotBounds(Rect rect) {
        Rect rect2 = this.f322b;
        if (rect2 != null) {
            rect.set(rect2);
        } else {
            super.getHotspotBounds(rect);
        }
    }

    public int getIntrinsicHeight() {
        if (this.f321a.mo601l()) {
            return this.f321a.mo593e();
        }
        Drawable drawable = this.f323c;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return -1;
    }

    public int getIntrinsicWidth() {
        if (this.f321a.mo601l()) {
            return this.f321a.mo598i();
        }
        Drawable drawable = this.f323c;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return -1;
    }

    public int getMinimumHeight() {
        if (this.f321a.mo601l()) {
            return this.f321a.mo594f();
        }
        Drawable drawable = this.f323c;
        if (drawable != null) {
            return drawable.getMinimumHeight();
        }
        return 0;
    }

    public int getMinimumWidth() {
        if (this.f321a.mo601l()) {
            return this.f321a.mo595g();
        }
        Drawable drawable = this.f323c;
        if (drawable != null) {
            return drawable.getMinimumWidth();
        }
        return 0;
    }

    public int getOpacity() {
        Drawable drawable = this.f323c;
        if (drawable == null || !drawable.isVisible()) {
            return -2;
        }
        return this.f321a.mo599j();
    }

    public void getOutline(Outline outline) {
        Drawable drawable = this.f323c;
        if (drawable != null) {
            drawable.getOutline(outline);
        }
    }

    public boolean getPadding(Rect rect) {
        boolean z;
        Rect h = this.f321a.mo597h();
        if (h != null) {
            rect.set(h);
            z = (h.right | ((h.left | h.top) | h.bottom)) != 0;
        } else {
            Drawable drawable = this.f323c;
            z = drawable != null ? drawable.getPadding(rect) : super.getPadding(rect);
        }
        if (m533c()) {
            int i = rect.left;
            rect.left = rect.right;
            rect.right = i;
        }
        return z;
    }

    public void invalidateDrawable(Drawable drawable) {
        C0159b bVar = this.f321a;
        if (bVar != null) {
            bVar.mo600k();
        }
        if (drawable == this.f323c && getCallback() != null) {
            getCallback().invalidateDrawable(this);
        }
    }

    public boolean isAutoMirrored() {
        return this.f321a.f337C;
    }

    public void jumpToCurrentState() {
        boolean z;
        Drawable drawable = this.f324d;
        if (drawable != null) {
            drawable.jumpToCurrentState();
            this.f324d = null;
            this.f328h = -1;
            z = true;
        } else {
            z = false;
        }
        Drawable drawable2 = this.f323c;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
            if (this.f326f) {
                this.f323c.setAlpha(this.f325e);
            }
        }
        if (this.f332l != 0) {
            this.f332l = 0;
            z = true;
        }
        if (this.f331k != 0) {
            this.f331k = 0;
            z = true;
        }
        if (z) {
            invalidateSelf();
        }
    }

    public Drawable mutate() {
        if (!this.f329i && super.mutate() == this) {
            C0159b a = mo513a();
            a.mo557m();
            mo514a(a);
            this.f329i = true;
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f324d;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
        Drawable drawable2 = this.f323c;
        if (drawable2 != null) {
            drawable2.setBounds(rect);
        }
    }

    public boolean onLayoutDirectionChanged(int i) {
        return this.f321a.mo588b(i, mo569b());
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        Drawable drawable = this.f324d;
        if (drawable != null) {
            return drawable.setLevel(i);
        }
        Drawable drawable2 = this.f323c;
        if (drawable2 != null) {
            return drawable2.setLevel(i);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f324d;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        Drawable drawable2 = this.f323c;
        if (drawable2 != null) {
            return drawable2.setState(iArr);
        }
        return false;
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        if (drawable == this.f323c && getCallback() != null) {
            getCallback().scheduleDrawable(this, runnable, j);
        }
    }

    public void setAlpha(int i) {
        if (!this.f326f || this.f325e != i) {
            this.f326f = true;
            this.f325e = i;
            Drawable drawable = this.f323c;
            if (drawable == null) {
                return;
            }
            if (this.f331k == 0) {
                drawable.setAlpha(i);
            } else {
                mo567a(false);
            }
        }
    }

    public void setAutoMirrored(boolean z) {
        C0159b bVar = this.f321a;
        if (bVar.f337C != z) {
            bVar.f337C = z;
            Drawable drawable = this.f323c;
            if (drawable != null) {
                C0190a.m678a(drawable, bVar.f337C);
            }
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        C0159b bVar = this.f321a;
        bVar.f339E = true;
        if (bVar.f338D != colorFilter) {
            bVar.f338D = colorFilter;
            Drawable drawable = this.f323c;
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }
    }

    public void setDither(boolean z) {
        C0159b bVar = this.f321a;
        if (bVar.f367x != z) {
            bVar.f367x = z;
            Drawable drawable = this.f323c;
            if (drawable != null) {
                drawable.setDither(bVar.f367x);
            }
        }
    }

    public void setHotspot(float f, float f2) {
        Drawable drawable = this.f323c;
        if (drawable != null) {
            C0190a.m672a(drawable, f, f2);
        }
    }

    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        Rect rect = this.f322b;
        if (rect == null) {
            this.f322b = new Rect(i, i2, i3, i4);
        } else {
            rect.set(i, i2, i3, i4);
        }
        Drawable drawable = this.f323c;
        if (drawable != null) {
            C0190a.m673a(drawable, i, i2, i3, i4);
        }
    }

    public void setTintList(ColorStateList colorStateList) {
        C0159b bVar = this.f321a;
        bVar.f342H = true;
        if (bVar.f340F != colorStateList) {
            bVar.f340F = colorStateList;
            C0190a.m674a(this.f323c, colorStateList);
        }
    }

    public void setTintMode(PorterDuff.Mode mode) {
        C0159b bVar = this.f321a;
        bVar.f343I = true;
        if (bVar.f341G != mode) {
            bVar.f341G = mode;
            C0190a.m677a(this.f323c, mode);
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        Drawable drawable = this.f324d;
        if (drawable != null) {
            drawable.setVisible(z, z2);
        }
        Drawable drawable2 = this.f323c;
        if (drawable2 != null) {
            drawable2.setVisible(z, z2);
        }
        return visible;
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        if (drawable == this.f323c && getCallback() != null) {
            getCallback().unscheduleDrawable(this, runnable);
        }
    }
}

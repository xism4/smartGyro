package android.support.p025v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.p024v4.graphics.drawable.C0190a;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import p000a.p001a.p003b.p004a.C0006d;
import p000a.p001a.p003b.p004a.C0016k;
import p000a.p001a.p005c.p006a.C0025a;
import p000a.p001a.p005c.p008b.C0042a;
import p000a.p001a.p005c.p013f.C0078b;
import p000a.p001a.p005c.p013f.C0080d;
import p000a.p001a.p005c.p013f.C0081e;
import p000a.p001a.p005c.p013f.C0091j;
import p000a.p001a.p017d.p018a.C0136a;
import p000a.p001a.p017d.p018a.C0140e;
import p000a.p001a.p017d.p021c.p022a.C0149b;

/* renamed from: android.support.v7.widget.o */
public final class C0423o {

    /* renamed from: a */
    private static final PorterDuff.Mode f1597a = PorterDuff.Mode.SRC_IN;

    /* renamed from: b */
    private static C0423o f1598b;

    /* renamed from: c */
    private static final C0426c f1599c = new C0426c(6);

    /* renamed from: d */
    private static final int[] f1600d = {C0140e.abc_textfield_search_default_mtrl_alpha, C0140e.abc_textfield_default_mtrl_alpha, C0140e.abc_ab_share_pack_mtrl_alpha};

    /* renamed from: e */
    private static final int[] f1601e = {C0140e.abc_ic_commit_search_api_mtrl_alpha, C0140e.abc_seekbar_tick_mark_material, C0140e.abc_ic_menu_share_mtrl_alpha, C0140e.abc_ic_menu_copy_mtrl_am_alpha, C0140e.abc_ic_menu_cut_mtrl_alpha, C0140e.abc_ic_menu_selectall_mtrl_alpha, C0140e.abc_ic_menu_paste_mtrl_am_alpha};

    /* renamed from: f */
    private static final int[] f1602f = {C0140e.abc_textfield_activated_mtrl_alpha, C0140e.abc_textfield_search_activated_mtrl_alpha, C0140e.abc_cab_background_top_mtrl_alpha, C0140e.abc_text_cursor_material, C0140e.abc_text_select_handle_left_mtrl_dark, C0140e.abc_text_select_handle_middle_mtrl_dark, C0140e.abc_text_select_handle_right_mtrl_dark, C0140e.abc_text_select_handle_left_mtrl_light, C0140e.abc_text_select_handle_middle_mtrl_light, C0140e.abc_text_select_handle_right_mtrl_light};

    /* renamed from: g */
    private static final int[] f1603g = {C0140e.abc_popup_background_mtrl_mult, C0140e.abc_cab_background_internal_bg, C0140e.abc_menu_hardkey_panel_mtrl_mult};

    /* renamed from: h */
    private static final int[] f1604h = {C0140e.abc_tab_indicator_material, C0140e.abc_textfield_search_material};

    /* renamed from: i */
    private static final int[] f1605i = {C0140e.abc_btn_check_material, C0140e.abc_btn_radio_material};

    /* renamed from: j */
    private WeakHashMap<Context, C0091j<ColorStateList>> f1606j;

    /* renamed from: k */
    private C0078b<String, C0427d> f1607k;

    /* renamed from: l */
    private C0091j<String> f1608l;

    /* renamed from: m */
    private final WeakHashMap<Context, C0080d<WeakReference<Drawable.ConstantState>>> f1609m = new WeakHashMap<>(0);

    /* renamed from: n */
    private TypedValue f1610n;

    /* renamed from: o */
    private boolean f1611o;

    /* renamed from: android.support.v7.widget.o$a */
    static class C0424a implements C0427d {
        C0424a() {
        }

        /* renamed from: a */
        public Drawable mo2229a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return C0149b.m496a(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", e);
                return null;
            }
        }
    }

    /* renamed from: android.support.v7.widget.o$b */
    private static class C0425b implements C0427d {
        C0425b() {
        }

        /* renamed from: a */
        public Drawable mo2229a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return C0006d.m0a(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", e);
                return null;
            }
        }
    }

    /* renamed from: android.support.v7.widget.o$c */
    private static class C0426c extends C0081e<Integer, PorterDuffColorFilter> {
        public C0426c(int i) {
            super(i);
        }

        /* renamed from: b */
        private static int m1876b(int i, PorterDuff.Mode mode) {
            return ((i + 31) * 31) + mode.hashCode();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public PorterDuffColorFilter mo2230a(int i, PorterDuff.Mode mode) {
            return (PorterDuffColorFilter) mo297b(Integer.valueOf(m1876b(i, mode)));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public PorterDuffColorFilter mo2231a(int i, PorterDuff.Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return (PorterDuffColorFilter) mo293a(Integer.valueOf(m1876b(i, mode)), porterDuffColorFilter);
        }
    }

    /* renamed from: android.support.v7.widget.o$d */
    private interface C0427d {
        /* renamed from: a */
        Drawable mo2229a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme);
    }

    /* renamed from: android.support.v7.widget.o$e */
    private static class C0428e implements C0427d {
        C0428e() {
        }

        /* renamed from: a */
        public Drawable mo2229a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return C0016k.createFromXmlInner(context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", e);
                return null;
            }
        }
    }

    /* renamed from: a */
    private static long m1845a(TypedValue typedValue) {
        return (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
    }

    /* renamed from: a */
    static PorterDuff.Mode m1846a(int i) {
        if (i == C0140e.abc_switch_thumb_material) {
            return PorterDuff.Mode.MULTIPLY;
        }
        return null;
    }

    /* renamed from: a */
    public static synchronized PorterDuffColorFilter m1847a(int i, PorterDuff.Mode mode) {
        PorterDuffColorFilter a;
        synchronized (C0423o.class) {
            a = f1599c.mo2230a(i, mode);
            if (a == null) {
                a = new PorterDuffColorFilter(i, mode);
                f1599c.mo2231a(i, mode, a);
            }
        }
        return a;
    }

    /* renamed from: a */
    private static PorterDuffColorFilter m1848a(ColorStateList colorStateList, PorterDuff.Mode mode, int[] iArr) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return m1847a(colorStateList.getColorForState(iArr, 0), mode);
    }

    /* renamed from: a */
    private Drawable m1849a(Context context, int i, boolean z, Drawable drawable) {
        LayerDrawable layerDrawable;
        Drawable findDrawableByLayerId;
        int i2;
        ColorStateList b = mo2228b(context, i);
        if (b != null) {
            if (C0348M.m1560a(drawable)) {
                drawable = drawable.mutate();
            }
            Drawable g = C0190a.m687g(drawable);
            C0190a.m674a(g, b);
            PorterDuff.Mode a = m1846a(i);
            if (a == null) {
                return g;
            }
            C0190a.m677a(g, a);
            return g;
        }
        if (i == C0140e.abc_seekbar_track_material) {
            layerDrawable = (LayerDrawable) drawable;
            m1854a(layerDrawable.findDrawableByLayerId(16908288), C0429oa.m1884b(context, C0136a.colorControlNormal), f1597a);
            findDrawableByLayerId = layerDrawable.findDrawableByLayerId(16908303);
            i2 = C0136a.colorControlNormal;
        } else if (i == C0140e.abc_ratingbar_material || i == C0140e.abc_ratingbar_indicator_material || i == C0140e.abc_ratingbar_small_material) {
            layerDrawable = (LayerDrawable) drawable;
            m1854a(layerDrawable.findDrawableByLayerId(16908288), C0429oa.m1881a(context, C0136a.colorControlNormal), f1597a);
            findDrawableByLayerId = layerDrawable.findDrawableByLayerId(16908303);
            i2 = C0136a.colorControlActivated;
        } else if (m1858a(context, i, drawable) || !z) {
            return drawable;
        } else {
            return null;
        }
        m1854a(findDrawableByLayerId, C0429oa.m1884b(context, i2), f1597a);
        m1854a(layerDrawable.findDrawableByLayerId(16908301), C0429oa.m1884b(context, C0136a.colorControlActivated), f1597a);
        return drawable;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002c, code lost:
        return null;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized android.graphics.drawable.Drawable m1850a(android.content.Context r4, long r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.util.WeakHashMap<android.content.Context, a.a.c.f.d<java.lang.ref.WeakReference<android.graphics.drawable.Drawable$ConstantState>>> r0 = r3.f1609m     // Catch:{ all -> 0x002d }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x002d }
            a.a.c.f.d r0 = (p000a.p001a.p005c.p013f.C0080d) r0     // Catch:{ all -> 0x002d }
            r1 = 0
            if (r0 != 0) goto L_0x000e
            monitor-exit(r3)
            return r1
        L_0x000e:
            java.lang.Object r2 = r0.mo287b((long) r5)     // Catch:{ all -> 0x002d }
            java.lang.ref.WeakReference r2 = (java.lang.ref.WeakReference) r2     // Catch:{ all -> 0x002d }
            if (r2 == 0) goto L_0x002b
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x002d }
            android.graphics.drawable.Drawable$ConstantState r2 = (android.graphics.drawable.Drawable.ConstantState) r2     // Catch:{ all -> 0x002d }
            if (r2 == 0) goto L_0x0028
            android.content.res.Resources r4 = r4.getResources()     // Catch:{ all -> 0x002d }
            android.graphics.drawable.Drawable r4 = r2.newDrawable(r4)     // Catch:{ all -> 0x002d }
            monitor-exit(r3)
            return r4
        L_0x0028:
            r0.mo284a((long) r5)     // Catch:{ all -> 0x002d }
        L_0x002b:
            monitor-exit(r3)
            return r1
        L_0x002d:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.widget.C0423o.m1850a(android.content.Context, long):android.graphics.drawable.Drawable");
    }

    /* renamed from: a */
    public static synchronized C0423o m1851a() {
        C0423o oVar;
        synchronized (C0423o.class) {
            if (f1598b == null) {
                f1598b = new C0423o();
                m1856a(f1598b);
            }
            oVar = f1598b;
        }
        return oVar;
    }

    /* renamed from: a */
    private void m1852a(Context context) {
        if (!this.f1611o) {
            this.f1611o = true;
            Drawable a = mo2225a(context, C0140e.abc_vector_test);
            if (a == null || !m1860a(a)) {
                this.f1611o = false;
                throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
            }
        }
    }

    /* renamed from: a */
    private void m1853a(Context context, int i, ColorStateList colorStateList) {
        if (this.f1606j == null) {
            this.f1606j = new WeakHashMap<>();
        }
        C0091j jVar = this.f1606j.get(context);
        if (jVar == null) {
            jVar = new C0091j();
            this.f1606j.put(context, jVar);
        }
        jVar.mo382a(i, colorStateList);
    }

    /* renamed from: a */
    private static void m1854a(Drawable drawable, int i, PorterDuff.Mode mode) {
        if (C0348M.m1560a(drawable)) {
            drawable = drawable.mutate();
        }
        if (mode == null) {
            mode = f1597a;
        }
        drawable.setColorFilter(m1847a(i, mode));
    }

    /* renamed from: a */
    static void m1855a(Drawable drawable, C0435ra raVar, int[] iArr) {
        if (!C0348M.m1560a(drawable) || drawable.mutate() == drawable) {
            if (raVar.f1633d || raVar.f1632c) {
                drawable.setColorFilter(m1848a(raVar.f1633d ? raVar.f1630a : null, raVar.f1632c ? raVar.f1631b : f1597a, iArr));
            } else {
                drawable.clearColorFilter();
            }
            if (Build.VERSION.SDK_INT <= 23) {
                drawable.invalidateSelf();
                return;
            }
            return;
        }
        Log.d("AppCompatDrawableManag", "Mutated drawable is not the same instance as the input.");
    }

    /* renamed from: a */
    private static void m1856a(C0423o oVar) {
        if (Build.VERSION.SDK_INT < 24) {
            oVar.m1857a("vector", (C0427d) new C0428e());
            oVar.m1857a("animated-vector", (C0427d) new C0425b());
            oVar.m1857a("animated-selector", (C0427d) new C0424a());
        }
    }

    /* renamed from: a */
    private void m1857a(String str, C0427d dVar) {
        if (this.f1607k == null) {
            this.f1607k = new C0078b<>();
        }
        this.f1607k.put(str, dVar);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0061 A[RETURN] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean m1858a(android.content.Context r6, int r7, android.graphics.drawable.Drawable r8) {
        /*
            android.graphics.PorterDuff$Mode r0 = f1597a
            int[] r1 = f1600d
            boolean r1 = m1861a((int[]) r1, (int) r7)
            r2 = 16842801(0x1010031, float:2.3693695E-38)
            r3 = -1
            r4 = 0
            r5 = 1
            if (r1 == 0) goto L_0x0015
            int r2 = p000a.p001a.p017d.p018a.C0136a.colorControlNormal
        L_0x0012:
            r7 = 1
            r1 = -1
            goto L_0x0044
        L_0x0015:
            int[] r1 = f1602f
            boolean r1 = m1861a((int[]) r1, (int) r7)
            if (r1 == 0) goto L_0x0020
            int r2 = p000a.p001a.p017d.p018a.C0136a.colorControlActivated
            goto L_0x0012
        L_0x0020:
            int[] r1 = f1603g
            boolean r1 = m1861a((int[]) r1, (int) r7)
            if (r1 == 0) goto L_0x002b
            android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.MULTIPLY
            goto L_0x0012
        L_0x002b:
            int r1 = p000a.p001a.p017d.p018a.C0140e.abc_list_divider_mtrl_alpha
            if (r7 != r1) goto L_0x003c
            r2 = 16842800(0x1010030, float:2.3693693E-38)
            r7 = 1109603123(0x42233333, float:40.8)
            int r7 = java.lang.Math.round(r7)
            r1 = r7
            r7 = 1
            goto L_0x0044
        L_0x003c:
            int r1 = p000a.p001a.p017d.p018a.C0140e.abc_dialog_material_background
            if (r7 != r1) goto L_0x0041
            goto L_0x0012
        L_0x0041:
            r7 = 0
            r1 = -1
            r2 = 0
        L_0x0044:
            if (r7 == 0) goto L_0x0061
            boolean r7 = android.support.p025v7.widget.C0348M.m1560a(r8)
            if (r7 == 0) goto L_0x0050
            android.graphics.drawable.Drawable r8 = r8.mutate()
        L_0x0050:
            int r6 = android.support.p025v7.widget.C0429oa.m1884b(r6, r2)
            android.graphics.PorterDuffColorFilter r6 = m1847a((int) r6, (android.graphics.PorterDuff.Mode) r0)
            r8.setColorFilter(r6)
            if (r1 == r3) goto L_0x0060
            r8.setAlpha(r1)
        L_0x0060:
            return r5
        L_0x0061:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.widget.C0423o.m1858a(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
    }

    /* renamed from: a */
    private synchronized boolean m1859a(Context context, long j, Drawable drawable) {
        boolean z;
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState != null) {
            C0080d dVar = this.f1609m.get(context);
            if (dVar == null) {
                dVar = new C0080d();
                this.f1609m.put(context, dVar);
            }
            dVar.mo289c(j, new WeakReference(constantState));
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    /* renamed from: a */
    private static boolean m1860a(Drawable drawable) {
        return (drawable instanceof C0016k) || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }

    /* renamed from: a */
    private static boolean m1861a(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    private ColorStateList m1862b(Context context) {
        return m1864c(context, 0);
    }

    /* renamed from: c */
    private ColorStateList m1863c(Context context) {
        return m1864c(context, C0429oa.m1884b(context, C0136a.colorAccent));
    }

    /* renamed from: c */
    private ColorStateList m1864c(Context context, int i) {
        int b = C0429oa.m1884b(context, C0136a.colorControlHighlight);
        int a = C0429oa.m1881a(context, C0136a.colorButtonNormal);
        return new ColorStateList(new int[][]{C0429oa.f1613b, C0429oa.f1616e, C0429oa.f1614c, C0429oa.f1620i}, new int[]{a, C0042a.m140a(b, i), C0042a.m140a(b, i), i});
    }

    /* renamed from: d */
    private ColorStateList m1865d(Context context) {
        return m1864c(context, C0429oa.m1884b(context, C0136a.colorButtonNormal));
    }

    /* renamed from: d */
    private Drawable m1866d(Context context, int i) {
        if (this.f1610n == null) {
            this.f1610n = new TypedValue();
        }
        TypedValue typedValue = this.f1610n;
        context.getResources().getValue(i, typedValue, true);
        long a = m1845a(typedValue);
        Drawable a2 = m1850a(context, a);
        if (a2 != null) {
            return a2;
        }
        if (i == C0140e.abc_cab_background_top_material) {
            a2 = new LayerDrawable(new Drawable[]{mo2225a(context, C0140e.abc_cab_background_internal_bg), mo2225a(context, C0140e.abc_cab_background_top_mtrl_alpha)});
        }
        if (a2 != null) {
            a2.setChangingConfigurations(typedValue.changingConfigurations);
            m1859a(context, a, a2);
        }
        return a2;
    }

    /* renamed from: e */
    private ColorStateList m1867e(Context context) {
        int[][] iArr = new int[3][];
        int[] iArr2 = new int[3];
        ColorStateList c = C0429oa.m1885c(context, C0136a.colorSwitchThumbNormal);
        if (c == null || !c.isStateful()) {
            iArr[0] = C0429oa.f1613b;
            iArr2[0] = C0429oa.m1881a(context, C0136a.colorSwitchThumbNormal);
            iArr[1] = C0429oa.f1617f;
            iArr2[1] = C0429oa.m1884b(context, C0136a.colorControlActivated);
            iArr[2] = C0429oa.f1620i;
            iArr2[2] = C0429oa.m1884b(context, C0136a.colorSwitchThumbNormal);
        } else {
            iArr[0] = C0429oa.f1613b;
            iArr2[0] = c.getColorForState(iArr[0], 0);
            iArr[1] = C0429oa.f1617f;
            iArr2[1] = C0429oa.m1884b(context, C0136a.colorControlActivated);
            iArr[2] = C0429oa.f1620i;
            iArr2[2] = c.getDefaultColor();
        }
        return new ColorStateList(iArr, iArr2);
    }

    /* renamed from: e */
    private ColorStateList m1868e(Context context, int i) {
        C0091j jVar;
        WeakHashMap<Context, C0091j<ColorStateList>> weakHashMap = this.f1606j;
        if (weakHashMap == null || (jVar = weakHashMap.get(context)) == null) {
            return null;
        }
        return (ColorStateList) jVar.mo381a(i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0073 A[Catch:{ Exception -> 0x00a2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x009a A[Catch:{ Exception -> 0x00a2 }] */
    /* renamed from: f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.drawable.Drawable m1869f(android.content.Context r11, int r12) {
        /*
            r10 = this;
            a.a.c.f.b<java.lang.String, android.support.v7.widget.o$d> r0 = r10.f1607k
            r1 = 0
            if (r0 == 0) goto L_0x00b2
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x00b2
            a.a.c.f.j<java.lang.String> r0 = r10.f1608l
            java.lang.String r2 = "appcompat_skip_skip"
            if (r0 == 0) goto L_0x0028
            java.lang.Object r0 = r0.mo381a(r12)
            java.lang.String r0 = (java.lang.String) r0
            boolean r3 = r2.equals(r0)
            if (r3 != 0) goto L_0x0027
            if (r0 == 0) goto L_0x002f
            a.a.c.f.b<java.lang.String, android.support.v7.widget.o$d> r3 = r10.f1607k
            java.lang.Object r0 = r3.get(r0)
            if (r0 != 0) goto L_0x002f
        L_0x0027:
            return r1
        L_0x0028:
            a.a.c.f.j r0 = new a.a.c.f.j
            r0.<init>()
            r10.f1608l = r0
        L_0x002f:
            android.util.TypedValue r0 = r10.f1610n
            if (r0 != 0) goto L_0x003a
            android.util.TypedValue r0 = new android.util.TypedValue
            r0.<init>()
            r10.f1610n = r0
        L_0x003a:
            android.util.TypedValue r0 = r10.f1610n
            android.content.res.Resources r1 = r11.getResources()
            r3 = 1
            r1.getValue(r12, r0, r3)
            long r4 = m1845a((android.util.TypedValue) r0)
            android.graphics.drawable.Drawable r6 = r10.m1850a((android.content.Context) r11, (long) r4)
            if (r6 == 0) goto L_0x004f
            return r6
        L_0x004f:
            java.lang.CharSequence r7 = r0.string
            if (r7 == 0) goto L_0x00aa
            java.lang.String r7 = r7.toString()
            java.lang.String r8 = ".xml"
            boolean r7 = r7.endsWith(r8)
            if (r7 == 0) goto L_0x00aa
            android.content.res.XmlResourceParser r1 = r1.getXml(r12)     // Catch:{ Exception -> 0x00a2 }
            android.util.AttributeSet r7 = android.util.Xml.asAttributeSet(r1)     // Catch:{ Exception -> 0x00a2 }
        L_0x0067:
            int r8 = r1.next()     // Catch:{ Exception -> 0x00a2 }
            r9 = 2
            if (r8 == r9) goto L_0x0071
            if (r8 == r3) goto L_0x0071
            goto L_0x0067
        L_0x0071:
            if (r8 != r9) goto L_0x009a
            java.lang.String r3 = r1.getName()     // Catch:{ Exception -> 0x00a2 }
            a.a.c.f.j<java.lang.String> r8 = r10.f1608l     // Catch:{ Exception -> 0x00a2 }
            r8.mo382a(r12, r3)     // Catch:{ Exception -> 0x00a2 }
            a.a.c.f.b<java.lang.String, android.support.v7.widget.o$d> r8 = r10.f1607k     // Catch:{ Exception -> 0x00a2 }
            java.lang.Object r3 = r8.get(r3)     // Catch:{ Exception -> 0x00a2 }
            android.support.v7.widget.o$d r3 = (android.support.p025v7.widget.C0423o.C0427d) r3     // Catch:{ Exception -> 0x00a2 }
            if (r3 == 0) goto L_0x008f
            android.content.res.Resources$Theme r8 = r11.getTheme()     // Catch:{ Exception -> 0x00a2 }
            android.graphics.drawable.Drawable r1 = r3.mo2229a(r11, r1, r7, r8)     // Catch:{ Exception -> 0x00a2 }
            r6 = r1
        L_0x008f:
            if (r6 == 0) goto L_0x00aa
            int r0 = r0.changingConfigurations     // Catch:{ Exception -> 0x00a2 }
            r6.setChangingConfigurations(r0)     // Catch:{ Exception -> 0x00a2 }
            r10.m1859a((android.content.Context) r11, (long) r4, (android.graphics.drawable.Drawable) r6)     // Catch:{ Exception -> 0x00a2 }
            goto L_0x00aa
        L_0x009a:
            org.xmlpull.v1.XmlPullParserException r11 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ Exception -> 0x00a2 }
            java.lang.String r0 = "No start tag found"
            r11.<init>(r0)     // Catch:{ Exception -> 0x00a2 }
            throw r11     // Catch:{ Exception -> 0x00a2 }
        L_0x00a2:
            r11 = move-exception
            java.lang.String r0 = "AppCompatDrawableManag"
            java.lang.String r1 = "Exception while inflating drawable"
            android.util.Log.e(r0, r1, r11)
        L_0x00aa:
            if (r6 != 0) goto L_0x00b1
            a.a.c.f.j<java.lang.String> r11 = r10.f1608l
            r11.mo382a(r12, r2)
        L_0x00b1:
            return r6
        L_0x00b2:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.widget.C0423o.m1869f(android.content.Context, int):android.graphics.drawable.Drawable");
    }

    /* renamed from: a */
    public synchronized Drawable mo2225a(Context context, int i) {
        return mo2226a(context, i, false);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized Drawable mo2226a(Context context, int i, boolean z) {
        Drawable f;
        m1852a(context);
        f = m1869f(context, i);
        if (f == null) {
            f = m1866d(context, i);
        }
        if (f == null) {
            f = C0025a.m77b(context, i);
        }
        if (f != null) {
            f = m1849a(context, i, z, f);
        }
        if (f != null) {
            C0348M.m1561b(f);
        }
        return f;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized Drawable mo2227a(Context context, C0340Ga ga, int i) {
        Drawable f = m1869f(context, i);
        if (f == null) {
            f = ga.mo1718a(i);
        }
        if (f == null) {
            return null;
        }
        return m1849a(context, i, false, f);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0078  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.content.res.ColorStateList mo2228b(android.content.Context r3, int r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            android.content.res.ColorStateList r0 = r2.m1868e(r3, r4)     // Catch:{ all -> 0x007d }
            if (r0 != 0) goto L_0x007b
            int r1 = p000a.p001a.p017d.p018a.C0140e.abc_edit_text_material     // Catch:{ all -> 0x007d }
            if (r4 != r1) goto L_0x0013
            int r0 = p000a.p001a.p017d.p018a.C0138c.abc_tint_edittext     // Catch:{ all -> 0x007d }
        L_0x000d:
            android.content.res.ColorStateList r0 = p000a.p001a.p017d.p019b.p020a.C0146a.m489a(r3, r0)     // Catch:{ all -> 0x007d }
            goto L_0x0076
        L_0x0013:
            int r1 = p000a.p001a.p017d.p018a.C0140e.abc_switch_track_mtrl_alpha     // Catch:{ all -> 0x007d }
            if (r4 != r1) goto L_0x001a
            int r0 = p000a.p001a.p017d.p018a.C0138c.abc_tint_switch_track     // Catch:{ all -> 0x007d }
            goto L_0x000d
        L_0x001a:
            int r1 = p000a.p001a.p017d.p018a.C0140e.abc_switch_thumb_material     // Catch:{ all -> 0x007d }
            if (r4 != r1) goto L_0x0023
            android.content.res.ColorStateList r0 = r2.m1867e(r3)     // Catch:{ all -> 0x007d }
            goto L_0x0076
        L_0x0023:
            int r1 = p000a.p001a.p017d.p018a.C0140e.abc_btn_default_mtrl_shape     // Catch:{ all -> 0x007d }
            if (r4 != r1) goto L_0x002c
            android.content.res.ColorStateList r0 = r2.m1865d(r3)     // Catch:{ all -> 0x007d }
            goto L_0x0076
        L_0x002c:
            int r1 = p000a.p001a.p017d.p018a.C0140e.abc_btn_borderless_material     // Catch:{ all -> 0x007d }
            if (r4 != r1) goto L_0x0035
            android.content.res.ColorStateList r0 = r2.m1862b(r3)     // Catch:{ all -> 0x007d }
            goto L_0x0076
        L_0x0035:
            int r1 = p000a.p001a.p017d.p018a.C0140e.abc_btn_colored_material     // Catch:{ all -> 0x007d }
            if (r4 != r1) goto L_0x003e
            android.content.res.ColorStateList r0 = r2.m1863c(r3)     // Catch:{ all -> 0x007d }
            goto L_0x0076
        L_0x003e:
            int r1 = p000a.p001a.p017d.p018a.C0140e.abc_spinner_mtrl_am_alpha     // Catch:{ all -> 0x007d }
            if (r4 == r1) goto L_0x0073
            int r1 = p000a.p001a.p017d.p018a.C0140e.abc_spinner_textfield_background_material     // Catch:{ all -> 0x007d }
            if (r4 != r1) goto L_0x0047
            goto L_0x0073
        L_0x0047:
            int[] r1 = f1601e     // Catch:{ all -> 0x007d }
            boolean r1 = m1861a((int[]) r1, (int) r4)     // Catch:{ all -> 0x007d }
            if (r1 == 0) goto L_0x0056
            int r0 = p000a.p001a.p017d.p018a.C0136a.colorControlNormal     // Catch:{ all -> 0x007d }
            android.content.res.ColorStateList r0 = android.support.p025v7.widget.C0429oa.m1885c(r3, r0)     // Catch:{ all -> 0x007d }
            goto L_0x0076
        L_0x0056:
            int[] r1 = f1604h     // Catch:{ all -> 0x007d }
            boolean r1 = m1861a((int[]) r1, (int) r4)     // Catch:{ all -> 0x007d }
            if (r1 == 0) goto L_0x0061
            int r0 = p000a.p001a.p017d.p018a.C0138c.abc_tint_default     // Catch:{ all -> 0x007d }
            goto L_0x000d
        L_0x0061:
            int[] r1 = f1605i     // Catch:{ all -> 0x007d }
            boolean r1 = m1861a((int[]) r1, (int) r4)     // Catch:{ all -> 0x007d }
            if (r1 == 0) goto L_0x006c
            int r0 = p000a.p001a.p017d.p018a.C0138c.abc_tint_btn_checkable     // Catch:{ all -> 0x007d }
            goto L_0x000d
        L_0x006c:
            int r1 = p000a.p001a.p017d.p018a.C0140e.abc_seekbar_thumb_material     // Catch:{ all -> 0x007d }
            if (r4 != r1) goto L_0x0076
            int r0 = p000a.p001a.p017d.p018a.C0138c.abc_tint_seek_thumb     // Catch:{ all -> 0x007d }
            goto L_0x000d
        L_0x0073:
            int r0 = p000a.p001a.p017d.p018a.C0138c.abc_tint_spinner     // Catch:{ all -> 0x007d }
            goto L_0x000d
        L_0x0076:
            if (r0 == 0) goto L_0x007b
            r2.m1853a((android.content.Context) r3, (int) r4, (android.content.res.ColorStateList) r0)     // Catch:{ all -> 0x007d }
        L_0x007b:
            monitor-exit(r2)
            return r0
        L_0x007d:
            r3 = move-exception
            monitor-exit(r2)
            goto L_0x0081
        L_0x0080:
            throw r3
        L_0x0081:
            goto L_0x0080
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.widget.C0423o.mo2228b(android.content.Context, int):android.content.res.ColorStateList");
    }
}

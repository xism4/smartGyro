package android.support.v7.widget;

import a.a.c.f.b;
import a.a.c.f.d;
import a.a.c.f.j;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.TintManager;
import android.support.v7.widget.o;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import com.org.android.asm.ColorUtils;
import com.org.android.util.ArrayMap;
import com.org.android.util.ByteVector;
import com.org.android.util.LongSparseArray;
import com.org.shortcuts.drawable.AnimatedVectorDrawableCompat;
import com.org.v4.graphics.drawable.VectorDrawableCompat;
import com.org.v4.util.R$attr;
import com.org.v4.util.R$drawable;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class AppCompatDrawableManager {
    private static final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY = {R$drawable.abc_popup_background_mtrl_mult, R$drawable.abc_cab_background_internal_bg, R$drawable.abc_menu_hardkey_panel_mtrl_mult};
    private static final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED = {R$drawable.abc_textfield_activated_mtrl_alpha, R$drawable.abc_textfield_search_activated_mtrl_alpha, R$drawable.abc_cab_background_top_mtrl_alpha, R$drawable.abc_text_cursor_material, R$drawable.abc_text_select_handle_left_mtrl_dark, R$drawable.abc_text_select_handle_middle_mtrl_dark, R$drawable.abc_text_select_handle_right_mtrl_dark, R$drawable.abc_text_select_handle_left_mtrl_light, R$drawable.abc_text_select_handle_middle_mtrl_light, R$drawable.abc_text_select_handle_right_mtrl_light};
    private static final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL = {R$drawable.abc_textfield_search_default_mtrl_alpha, R$drawable.abc_textfield_default_mtrl_alpha, R$drawable.abc_ab_share_pack_mtrl_alpha};
    private static final TintManager.ColorFilterLruCache COLOR_FILTER_CACHE = new TintManager.ColorFilterLruCache(6);
    private static final PorterDuff.Mode DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
    private static AppCompatDrawableManager INSTANCE;
    private static final int[] TINT_CHECKABLE_BUTTON_LIST = {R$drawable.abc_btn_check_material, R$drawable.abc_btn_radio_material};
    private static final int[] TINT_COLOR_CONTROL_NORMAL = {R$drawable.abc_ic_commit_search_api_mtrl_alpha, R$drawable.abc_seekbar_tick_mark_material, R$drawable.abc_ic_menu_share_mtrl_alpha, R$drawable.abc_ic_menu_copy_mtrl_am_alpha, R$drawable.abc_ic_menu_cut_mtrl_alpha, R$drawable.abc_ic_menu_selectall_mtrl_alpha, R$drawable.abc_ic_menu_paste_mtrl_am_alpha};
    private static final int[] TINT_COLOR_CONTROL_STATE_LIST = {R$drawable.abc_tab_indicator_material, R$drawable.abc_textfield_search_material};
    private boolean goRight;
    private b<String, o.d> mDelegates;
    private final WeakHashMap<Context, d<WeakReference<Drawable.ConstantState>>> mDrawableCaches = new WeakHashMap(0);
    private j<String> mKnownDrawableIdTags;
    private WeakHashMap<Context, j<ColorStateList>> mTintLists;
    private TypedValue mTypedValue;

    class AvdcInflateDelegate implements InflateDelegate {
        AvdcInflateDelegate() {
        }

        public Drawable createFromXmlInner(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return AnimatedVectorDrawableCompat.createFromXmlInner(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception $r7) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", $r7);
                return null;
            }
        }
    }

    interface InflateDelegate {
        Drawable createFromXmlInner(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme);
    }

    class VdcInflateDelegate implements InflateDelegate {
        VdcInflateDelegate() {
        }

        public Drawable createFromXmlInner(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return VectorDrawableCompat.createFromXmlInner(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception $r7) {
                Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", $r7);
                return null;
            }
        }
    }

    private void addDelegate(String str, InflateDelegate inflateDelegate) {
        if (this.mDelegates == null) {
            this.mDelegates = new ArrayMap();
        }
        this.mDelegates.put(str, inflateDelegate);
    }

    private synchronized boolean addDrawableToCache(Context context, long j, Drawable drawable) {
        boolean $z0;
        Drawable.ConstantState $r4 = drawable.getConstantState();
        if ($r4 != null) {
            LongSparseArray $r6 = this.mDrawableCaches.get(context);
            if ($r6 == null) {
                $r6 = new LongSparseArray();
                this.mDrawableCaches.put(context, $r6);
            }
            $r6.put(j, new WeakReference($r4));
            $z0 = true;
        } else {
            $z0 = false;
        }
        return $z0;
    }

    private void addTintListToCache(Context context, int i, ColorStateList colorStateList) {
        if (this.mTintLists == null) {
            this.mTintLists = new WeakHashMap();
        }
        ByteVector $r5 = this.mTintLists.get(context);
        if ($r5 == null) {
            $r5 = new ByteVector();
            this.mTintLists.put(context, $r5);
        }
        $r5.d(i, colorStateList);
    }

    private static boolean arrayContains(int[] iArr, int i) {
        for (int $i2 : iArr) {
            if ($i2 == i) {
                return true;
            }
        }
        return false;
    }

    private ColorStateList createBorderlessButtonColorStateList(Context context) {
        return createButtonColorStateList(context, 0);
    }

    private ColorStateList createButtonColorStateList(Context context, int i) {
        int $i1 = ThemeUtils.getThemeAttrColor(context, R$attr.colorControlHighlight);
        int $i2 = ThemeUtils.getDisabledThemeAttrColor(context, R$attr.colorButtonNormal);
        return new ColorStateList(new int[][]{ThemeUtils.DISABLED_STATE_SET, ThemeUtils.PRESSED_STATE_SET, ThemeUtils.FOCUSED_STATE_SET, ThemeUtils.EMPTY_STATE_SET}, new int[]{$i2, ColorUtils.compositeColors($i1, i), ColorUtils.compositeColors($i1, i), i});
    }

    private static long createCacheKey(TypedValue typedValue) {
        return (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
    }

    private ColorStateList createColoredButtonColorStateList(Context context) {
        return createButtonColorStateList(context, ThemeUtils.getThemeAttrColor(context, R$attr.colorAccent));
    }

    private ColorStateList createDefaultButtonColorStateList(Context context) {
        return createButtonColorStateList(context, ThemeUtils.getThemeAttrColor(context, R$attr.colorButtonNormal));
    }

    private Drawable createDrawableIfNeeded(Context context, int i) {
        if (this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }
        TypedValue $r2 = this.mTypedValue;
        context.getResources().getValue(i, $r2, true);
        long $l1 = createCacheKey($r2);
        Drawable $r4 = getCachedDrawable(context, $l1);
        Drawable $r5 = $r4;
        if ($r4 != null) {
            return $r4;
        }
        if (i == R$drawable.abc_cab_background_top_material) {
            $r5 = r8;
            Drawable r8 = new LayerDrawable(new Drawable[]{getDrawable(context, R$drawable.abc_cab_background_internal_bg), getDrawable(context, R$drawable.abc_cab_background_top_mtrl_alpha)});
        }
        if ($r5 != null) {
            $r5.setChangingConfigurations($r2.changingConfigurations);
            addDrawableToCache(context, $l1, $r5);
        }
        return $r5;
    }

    private ColorStateList createSwitchThumbColorStateList(Context context) {
        int[][] $r1 = new int[3][];
        int[] $r3 = new int[3];
        ColorStateList $r4 = ThemeUtils.getThemeAttrColorStateList(context, R$attr.colorSwitchThumbNormal);
        if ($r4 == null || !$r4.isStateful()) {
            $r1[0] = ThemeUtils.DISABLED_STATE_SET;
            $r3[0] = ThemeUtils.getDisabledThemeAttrColor(context, R$attr.colorSwitchThumbNormal);
            $r1[1] = ThemeUtils.CHECKED_STATE_SET;
            $r3[1] = ThemeUtils.getThemeAttrColor(context, R$attr.colorControlActivated);
            $r1[2] = ThemeUtils.EMPTY_STATE_SET;
            $r3[2] = ThemeUtils.getThemeAttrColor(context, R$attr.colorSwitchThumbNormal);
        } else {
            $r1[0] = ThemeUtils.DISABLED_STATE_SET;
            $r3[0] = $r4.getColorForState($r1[0], 0);
            $r1[1] = ThemeUtils.CHECKED_STATE_SET;
            $r3[1] = ThemeUtils.getThemeAttrColor(context, R$attr.colorControlActivated);
            $r1[2] = ThemeUtils.EMPTY_STATE_SET;
            $r3[2] = $r4.getDefaultColor();
        }
        return new ColorStateList($r1, $r3);
    }

    private static PorterDuffColorFilter createTintFilter(ColorStateList colorStateList, PorterDuff.Mode mode, int[] iArr) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return getPorterDuffColorFilter(colorStateList.getColorForState(iArr, 0), mode);
    }

    public static synchronized AppCompatDrawableManager get() {
        AppCompatDrawableManager $r0;
        synchronized (o.class) {
            if (INSTANCE == null) {
                INSTANCE = new AppCompatDrawableManager();
                installDefaultInflateDelegates(INSTANCE);
            }
            $r0 = INSTANCE;
        }
        return $r0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0032, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized android.graphics.drawable.Drawable getCachedDrawable(android.content.Context r13, long r14) {
        /*
            r12 = this;
            monitor-enter(r12)
            java.util.WeakHashMap<android.content.Context, a.a.c.f.d<java.lang.ref.WeakReference<android.graphics.drawable.Drawable$ConstantState>>> r0 = r12.mDrawableCaches     // Catch:{ Throwable -> 0x0034 }
            java.lang.Object r1 = r0.get(r13)     // Catch:{ Throwable -> 0x0034 }
            r3 = r1
            com.org.android.util.LongSparseArray r3 = (com.org.android.util.LongSparseArray) r3     // Catch:{ Throwable -> 0x0034 }
            r2 = r3
            if (r2 != 0) goto L_0x0010
            monitor-exit(r12)
            r4 = 0
            return r4
        L_0x0010:
            java.lang.Object r1 = r2.get(r14)     // Catch:{ Throwable -> 0x0034 }
            r6 = r1
            java.lang.ref.WeakReference r6 = (java.lang.ref.WeakReference) r6     // Catch:{ Throwable -> 0x0034 }
            r5 = r6
            if (r5 == 0) goto L_0x0031
            java.lang.Object r1 = r5.get()     // Catch:{ Throwable -> 0x0034 }
            r8 = r1
            android.graphics.drawable.Drawable$ConstantState r8 = (android.graphics.drawable.Drawable.ConstantState) r8     // Catch:{ Throwable -> 0x0034 }
            r7 = r8
            if (r7 == 0) goto L_0x002e
            android.content.res.Resources r9 = r13.getResources()     // Catch:{ Throwable -> 0x0034 }
            android.graphics.drawable.Drawable r10 = r7.newDrawable(r9)     // Catch:{ Throwable -> 0x0034 }
            monitor-exit(r12)
            return r10
        L_0x002e:
            r2.delete(r14)     // Catch:{ Throwable -> 0x0034 }
        L_0x0031:
            monitor-exit(r12)
            r4 = 0
            return r4
        L_0x0034:
            r11 = move-exception
            monitor-exit(r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.AppCompatDrawableManager.getCachedDrawable(android.content.Context, long):android.graphics.drawable.Drawable");
    }

    public static synchronized PorterDuffColorFilter getPorterDuffColorFilter(int i, PorterDuff.Mode mode) {
        PorterDuffColorFilter $r3;
        synchronized (o.class) {
            PorterDuffColorFilter $r2 = COLOR_FILTER_CACHE.get(i, mode);
            $r3 = $r2;
            if ($r2 == null) {
                $r3 = new PorterDuffColorFilter(i, mode);
                COLOR_FILTER_CACHE.put(i, mode, $r3);
            }
        }
        return $r3;
    }

    private ColorStateList getTintListFromCache(Context context, int i) {
        ByteVector $r4;
        WeakHashMap $r1 = this.mTintLists;
        if ($r1 == null || ($r4 = $r1.get(context)) == null) {
            return null;
        }
        return (ColorStateList) $r4.get(i);
    }

    static PorterDuff.Mode getTintMode(int i) {
        if (i == R$drawable.abc_switch_thumb_material) {
            return PorterDuff.Mode.MULTIPLY;
        }
        return null;
    }

    private static void installDefaultInflateDelegates(AppCompatDrawableManager appCompatDrawableManager) {
        if (Build.VERSION.SDK_INT < 24) {
            appCompatDrawableManager.addDelegate("vector", new ServiceController());
            appCompatDrawableManager.addDelegate("animated-vector", new AvdcInflateDelegate());
            appCompatDrawableManager.addDelegate("animated-selector", new VdcInflateDelegate());
        }
    }

    private static boolean isVectorDrawable(Drawable drawable) {
        return (drawable instanceof com.org.shortcuts.drawable.VectorDrawableCompat) || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }

    private Drawable loadDrawableFromDelegates(Context context, int i) {
        int $i2;
        b<String, o.d> bVar = this.mDelegates;
        if (bVar == null || bVar.isEmpty()) {
            return null;
        }
        ByteVector $r4 = this.mKnownDrawableIdTags;
        if ($r4 != null) {
            String $r6 = (String) $r4.get(i);
            if ("appcompat_skip_skip".equals($r6)) {
                return null;
            }
            if ($r6 != null && this.mDelegates.get($r6) == null) {
                return null;
            }
        } else {
            this.mKnownDrawableIdTags = new ByteVector();
        }
        if (this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }
        TypedValue $r7 = this.mTypedValue;
        Resources $r8 = context.getResources();
        $r8.getValue(i, $r7, true);
        long $l1 = createCacheKey($r7);
        Drawable $r9 = getCachedDrawable(context, $l1);
        Drawable $r10 = $r9;
        if ($r9 != null) {
            return $r9;
        }
        CharSequence $r11 = $r7.string;
        if ($r11 != null && $r11.toString().endsWith(".xml")) {
            try {
                XmlResourceParser $r12 = $r8.getXml(i);
                AttributeSet $r13 = Xml.asAttributeSet($r12);
                do {
                    $i2 = $r12.next();
                    if ($i2 == 2 || $i2 == 1) {
                    }
                    $i2 = $r12.next();
                    break;
                } while ($i2 == 1);
                if ($i2 == 2) {
                    String $r1 = $r12.getName();
                    this.mKnownDrawableIdTags.d(i, $r1);
                    InflateDelegate $r14 = (InflateDelegate) this.mDelegates.get($r1);
                    if ($r14 != null) {
                        $r10 = $r14.createFromXmlInner(context, $r12, $r13, context.getTheme());
                    }
                    if ($r10 != null) {
                        $r10.setChangingConfigurations($r7.changingConfigurations);
                        addDrawableToCache(context, $l1, $r10);
                    }
                } else {
                    XmlPullParserException xmlPullParserException = new XmlPullParserException("No start tag found");
                    throw xmlPullParserException;
                }
            } catch (Exception $r17) {
                Log.e("AppCompatDrawableManag", "Exception while inflating drawable", $r17);
            }
        }
        if ($r10 != null) {
            return $r10;
        }
        this.mKnownDrawableIdTags.d(i, "appcompat_skip_skip");
        return $r10;
    }

    private static void setPorterDuffColorFilter(Drawable $r0, int i, PorterDuff.Mode $r1) {
        if (DrawableUtils.canSafelyMutateDrawable($r0)) {
            $r0 = $r0.mutate();
        }
        if ($r1 == null) {
            $r1 = DEFAULT_MODE;
        }
        $r0.setColorFilter(getPorterDuffColorFilter(i, $r1));
    }

    private Drawable tintDrawable(Context context, int $i0, boolean $z0, Drawable $r2) {
        LayerDrawable $r5;
        Drawable $r6;
        int $i02;
        ColorStateList $r3 = getTintList(context, $i0);
        if ($r3 != null) {
            if (DrawableUtils.canSafelyMutateDrawable($r2)) {
                $r2 = $r2.mutate();
            }
            $r2 = DrawableCompat.wrap($r2);
            DrawableCompat.setTintList($r2, $r3);
            PorterDuff.Mode $r4 = getTintMode($i0);
            if ($r4 != null) {
                DrawableCompat.setTintMode($r2, $r4);
                return $r2;
            }
        } else {
            if ($i0 == R$drawable.abc_seekbar_track_material) {
                $r5 = (LayerDrawable) $r2;
                setPorterDuffColorFilter($r5.findDrawableByLayerId(16908288), ThemeUtils.getThemeAttrColor(context, R$attr.colorControlNormal), DEFAULT_MODE);
                $r6 = $r5.findDrawableByLayerId(16908303);
                $i02 = R$attr.colorControlNormal;
            } else if ($i0 == R$drawable.abc_ratingbar_material || $i0 == R$drawable.abc_ratingbar_indicator_material || $i0 == R$drawable.abc_ratingbar_small_material) {
                $r5 = (LayerDrawable) $r2;
                setPorterDuffColorFilter($r5.findDrawableByLayerId(16908288), ThemeUtils.getDisabledThemeAttrColor(context, R$attr.colorControlNormal), DEFAULT_MODE);
                $r6 = $r5.findDrawableByLayerId(16908303);
                $i02 = R$attr.colorControlActivated;
            } else if (tintDrawableUsingColorFilter(context, $i0, $r2) || !$z0) {
                return $r2;
            } else {
                return null;
            }
            setPorterDuffColorFilter($r6, ThemeUtils.getThemeAttrColor(context, $i02), DEFAULT_MODE);
            setPorterDuffColorFilter($r5.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(context, R$attr.colorControlActivated), DEFAULT_MODE);
            return $r2;
        }
        return $r2;
    }

    private void tintDrawable(Context context) {
        if (!this.goRight) {
            this.goRight = true;
            Drawable $r2 = getDrawable(context, R$drawable.abc_vector_test);
            if ($r2 == null || !isVectorDrawable($r2)) {
                this.goRight = false;
                throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
            }
        }
    }

    static void tintDrawable(Drawable drawable, TintInfo tintInfo, int[] iArr) {
        if (!DrawableUtils.canSafelyMutateDrawable(drawable) || drawable.mutate() == drawable) {
            if (tintInfo.mHasTintList || tintInfo.mHasTintMode) {
                drawable.setColorFilter(createTintFilter(tintInfo.mHasTintList ? tintInfo.mTintList : null, tintInfo.mHasTintMode ? tintInfo.mTintMode : DEFAULT_MODE, iArr));
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

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x005f A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean tintDrawableUsingColorFilter(android.content.Context r8, int r9, android.graphics.drawable.Drawable r10) {
        /*
            android.graphics.PorterDuff$Mode r0 = DEFAULT_MODE
            int[] r1 = COLORFILTER_TINT_COLOR_CONTROL_NORMAL
            boolean r2 = arrayContains(r1, r9)
            r3 = 16842801(0x1010031, float:2.3693695E-38)
            if (r2 == 0) goto L_0x0012
            int r3 = com.org.v4.util.R$attr.colorControlNormal
        L_0x000f:
            r2 = 1
            r9 = -1
            goto L_0x0040
        L_0x0012:
            int[] r1 = COLORFILTER_COLOR_CONTROL_ACTIVATED
            boolean r2 = arrayContains(r1, r9)
            if (r2 == 0) goto L_0x001d
            int r3 = com.org.v4.util.R$attr.colorControlActivated
            goto L_0x000f
        L_0x001d:
            int[] r1 = COLORFILTER_COLOR_BACKGROUND_MULTIPLY
            boolean r2 = arrayContains(r1, r9)
            if (r2 == 0) goto L_0x0028
            android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.MULTIPLY
            goto L_0x000f
        L_0x0028:
            int r4 = com.org.v4.util.R$drawable.abc_list_divider_mtrl_alpha
            if (r9 != r4) goto L_0x0038
            r3 = 16842800(0x1010030, float:2.3693693E-38)
            r5 = 1109603123(0x42233333, float:40.8)
            int r9 = java.lang.Math.round(r5)
            r2 = 1
            goto L_0x0040
        L_0x0038:
            int r4 = com.org.v4.util.R$drawable.abc_dialog_material_background
            if (r9 != r4) goto L_0x003d
            goto L_0x000f
        L_0x003d:
            r2 = 0
            r9 = -1
            r3 = 0
        L_0x0040:
            if (r2 == 0) goto L_0x005f
            boolean r2 = android.support.v7.widget.DrawableUtils.canSafelyMutateDrawable(r10)
            if (r2 == 0) goto L_0x004c
            android.graphics.drawable.Drawable r10 = r10.mutate()
        L_0x004c:
            int r3 = android.support.v7.widget.ThemeUtils.getThemeAttrColor(r8, r3)
            android.graphics.PorterDuffColorFilter r6 = getPorterDuffColorFilter(r3, r0)
            r10.setColorFilter(r6)
            r7 = -1
            if (r9 == r7) goto L_0x0061
            r10.setAlpha(r9)
            r7 = 1
            return r7
        L_0x005f:
            r7 = 0
            return r7
        L_0x0061:
            r7 = 1
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.AppCompatDrawableManager.tintDrawableUsingColorFilter(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
    }

    public synchronized Drawable getDrawable(Context context, int i) {
        return getDrawable(context, i, false);
    }

    /* access modifiers changed from: package-private */
    public synchronized Drawable getDrawable(Context context, int i, boolean z) {
        Drawable $r3;
        tintDrawable(context);
        Drawable $r2 = loadDrawableFromDelegates(context, i);
        $r3 = $r2;
        if ($r2 == null) {
            $r3 = createDrawableIfNeeded(context, i);
        }
        if ($r3 == null) {
            $r3 = com.org.android.ui.Resources.getDrawable(context, i);
        }
        if ($r3 != null) {
            $r3 = tintDrawable(context, i, z, $r3);
        }
        if ($r3 != null) {
            DrawableUtils.fixDrawable($r3);
        }
        return $r3;
    }

    /* access modifiers changed from: package-private */
    public synchronized Drawable getDrawable(Context context, TintManager tintManager, int i) {
        Drawable $r3 = loadDrawableFromDelegates(context, i);
        Drawable $r4 = $r3;
        if ($r3 == null) {
            $r4 = tintManager.superGetDrawable(i);
        }
        if ($r4 == null) {
            return null;
        }
        return tintDrawable(context, i, false, $r4);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0078  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.content.res.ColorStateList getTintList(android.content.Context r7, int r8) {
        /*
            r6 = this;
            monitor-enter(r6)
            android.content.res.ColorStateList r0 = r6.getTintListFromCache(r7, r8)     // Catch:{ Throwable -> 0x007d }
            r1 = r0
            if (r0 != 0) goto L_0x007b
            int r2 = com.org.v4.util.R$drawable.abc_edit_text_material     // Catch:{ Throwable -> 0x007d }
            if (r8 != r2) goto L_0x0013
            int r2 = com.org.v4.util.R$color.abc_tint_edittext     // Catch:{ Throwable -> 0x007d }
        L_0x000e:
            android.content.res.ColorStateList r1 = com.org.v4.text.view.Resources.show(r7, r2)     // Catch:{ Throwable -> 0x007d }
            goto L_0x0076
        L_0x0013:
            int r2 = com.org.v4.util.R$drawable.abc_switch_track_mtrl_alpha     // Catch:{ Throwable -> 0x007d }
            if (r8 != r2) goto L_0x001a
            int r2 = com.org.v4.util.R$color.abc_tint_switch_track     // Catch:{ Throwable -> 0x007d }
            goto L_0x000e
        L_0x001a:
            int r2 = com.org.v4.util.R$drawable.abc_switch_thumb_material     // Catch:{ Throwable -> 0x007d }
            if (r8 != r2) goto L_0x0023
            android.content.res.ColorStateList r1 = r6.createSwitchThumbColorStateList(r7)     // Catch:{ Throwable -> 0x007d }
            goto L_0x0076
        L_0x0023:
            int r2 = com.org.v4.util.R$drawable.abc_btn_default_mtrl_shape     // Catch:{ Throwable -> 0x007d }
            if (r8 != r2) goto L_0x002c
            android.content.res.ColorStateList r1 = r6.createDefaultButtonColorStateList(r7)     // Catch:{ Throwable -> 0x007d }
            goto L_0x0076
        L_0x002c:
            int r2 = com.org.v4.util.R$drawable.abc_btn_borderless_material     // Catch:{ Throwable -> 0x007d }
            if (r8 != r2) goto L_0x0035
            android.content.res.ColorStateList r1 = r6.createBorderlessButtonColorStateList(r7)     // Catch:{ Throwable -> 0x007d }
            goto L_0x0076
        L_0x0035:
            int r2 = com.org.v4.util.R$drawable.abc_btn_colored_material     // Catch:{ Throwable -> 0x007d }
            if (r8 != r2) goto L_0x003e
            android.content.res.ColorStateList r1 = r6.createColoredButtonColorStateList(r7)     // Catch:{ Throwable -> 0x007d }
            goto L_0x0076
        L_0x003e:
            int r2 = com.org.v4.util.R$drawable.abc_spinner_mtrl_am_alpha     // Catch:{ Throwable -> 0x007d }
            if (r8 == r2) goto L_0x0073
            int r2 = com.org.v4.util.R$drawable.abc_spinner_textfield_background_material     // Catch:{ Throwable -> 0x007d }
            if (r8 != r2) goto L_0x0047
            goto L_0x0073
        L_0x0047:
            int[] r3 = TINT_COLOR_CONTROL_NORMAL     // Catch:{ Throwable -> 0x007d }
            boolean r4 = arrayContains(r3, r8)     // Catch:{ Throwable -> 0x007d }
            if (r4 == 0) goto L_0x0056
            int r2 = com.org.v4.util.R$attr.colorControlNormal     // Catch:{ Throwable -> 0x007d }
            android.content.res.ColorStateList r1 = android.support.v7.widget.ThemeUtils.getThemeAttrColorStateList(r7, r2)     // Catch:{ Throwable -> 0x007d }
            goto L_0x0076
        L_0x0056:
            int[] r3 = TINT_COLOR_CONTROL_STATE_LIST     // Catch:{ Throwable -> 0x007d }
            boolean r4 = arrayContains(r3, r8)     // Catch:{ Throwable -> 0x007d }
            if (r4 == 0) goto L_0x0061
            int r2 = com.org.v4.util.R$color.abc_tint_default     // Catch:{ Throwable -> 0x007d }
            goto L_0x000e
        L_0x0061:
            int[] r3 = TINT_CHECKABLE_BUTTON_LIST     // Catch:{ Throwable -> 0x007d }
            boolean r4 = arrayContains(r3, r8)     // Catch:{ Throwable -> 0x007d }
            if (r4 == 0) goto L_0x006c
            int r2 = com.org.v4.util.R$color.abc_tint_btn_checkable     // Catch:{ Throwable -> 0x007d }
            goto L_0x000e
        L_0x006c:
            int r2 = com.org.v4.util.R$drawable.abc_seekbar_thumb_material     // Catch:{ Throwable -> 0x007d }
            if (r8 != r2) goto L_0x0076
            int r2 = com.org.v4.util.R$color.abc_tint_seek_thumb     // Catch:{ Throwable -> 0x007d }
            goto L_0x000e
        L_0x0073:
            int r2 = com.org.v4.util.R$color.abc_tint_spinner     // Catch:{ Throwable -> 0x007d }
            goto L_0x000e
        L_0x0076:
            if (r1 == 0) goto L_0x007b
            r6.addTintListToCache(r7, r8, r1)     // Catch:{ Throwable -> 0x007d }
        L_0x007b:
            monitor-exit(r6)
            return r1
        L_0x007d:
            r5 = move-exception
            monitor-exit(r6)
            goto L_0x0080
        L_0x0080:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.AppCompatDrawableManager.getTintList(android.content.Context, int):android.content.res.ColorStateList");
    }
}

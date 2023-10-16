package android.support.p025v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import p000a.p001a.p005c.p008b.C0042a;

/* renamed from: android.support.v7.widget.oa */
class C0429oa {

    /* renamed from: a */
    private static final ThreadLocal<TypedValue> f1612a = new ThreadLocal<>();

    /* renamed from: b */
    static final int[] f1613b = {-16842910};

    /* renamed from: c */
    static final int[] f1614c = {16842908};

    /* renamed from: d */
    static final int[] f1615d = {16843518};

    /* renamed from: e */
    static final int[] f1616e = {16842919};

    /* renamed from: f */
    static final int[] f1617f = {16842912};

    /* renamed from: g */
    static final int[] f1618g = {16842913};

    /* renamed from: h */
    static final int[] f1619h = {-16842919, -16842908};

    /* renamed from: i */
    static final int[] f1620i = new int[0];

    /* renamed from: j */
    private static final int[] f1621j = new int[1];

    /* renamed from: a */
    public static int m1881a(Context context, int i) {
        ColorStateList c = m1885c(context, i);
        if (c != null && c.isStateful()) {
            return c.getColorForState(f1613b, c.getDefaultColor());
        }
        TypedValue a = m1883a();
        context.getTheme().resolveAttribute(16842803, a, true);
        return m1882a(context, i, a.getFloat());
    }

    /* renamed from: a */
    static int m1882a(Context context, int i, float f) {
        int b = m1884b(context, i);
        return C0042a.m142b(b, Math.round(((float) Color.alpha(b)) * f));
    }

    /* renamed from: a */
    private static TypedValue m1883a() {
        TypedValue typedValue = f1612a.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        f1612a.set(typedValue2);
        return typedValue2;
    }

    /* renamed from: b */
    public static int m1884b(Context context, int i) {
        int[] iArr = f1621j;
        iArr[0] = i;
        C0439ta a = C0439ta.m1901a(context, (AttributeSet) null, iArr);
        try {
            return a.mo2271a(0, 0);
        } finally {
            a.mo2274a();
        }
    }

    /* renamed from: c */
    public static ColorStateList m1885c(Context context, int i) {
        int[] iArr = f1621j;
        iArr[0] = i;
        C0439ta a = C0439ta.m1901a(context, (AttributeSet) null, iArr);
        try {
            return a.mo2272a(0);
        } finally {
            a.mo2274a();
        }
    }
}

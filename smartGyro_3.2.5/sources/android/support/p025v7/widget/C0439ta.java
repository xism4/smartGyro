package android.support.p025v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import p000a.p001a.p005c.p006a.p007a.C0038h;

/* renamed from: android.support.v7.widget.ta */
public class C0439ta {

    /* renamed from: a */
    private final Context f1641a;

    /* renamed from: b */
    private final TypedArray f1642b;

    /* renamed from: c */
    private TypedValue f1643c;

    private C0439ta(Context context, TypedArray typedArray) {
        this.f1641a = context;
        this.f1642b = typedArray;
    }

    /* renamed from: a */
    public static C0439ta m1900a(Context context, int i, int[] iArr) {
        return new C0439ta(context, context.obtainStyledAttributes(i, iArr));
    }

    /* renamed from: a */
    public static C0439ta m1901a(Context context, AttributeSet attributeSet, int[] iArr) {
        return new C0439ta(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    /* renamed from: a */
    public static C0439ta m1902a(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2) {
        return new C0439ta(context, context.obtainStyledAttributes(attributeSet, iArr, i, i2));
    }

    /* renamed from: a */
    public float mo2270a(int i, float f) {
        return this.f1642b.getFloat(i, f);
    }

    /* renamed from: a */
    public int mo2271a(int i, int i2) {
        return this.f1642b.getColor(i, i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0011, code lost:
        r0 = p000a.p001a.p017d.p019b.p020a.C0146a.m489a(r2.f1641a, (r0 = r2.f1642b.getResourceId(r3, 0)));
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.content.res.ColorStateList mo2272a(int r3) {
        /*
            r2 = this;
            android.content.res.TypedArray r0 = r2.f1642b
            boolean r0 = r0.hasValue(r3)
            if (r0 == 0) goto L_0x001a
            android.content.res.TypedArray r0 = r2.f1642b
            r1 = 0
            int r0 = r0.getResourceId(r3, r1)
            if (r0 == 0) goto L_0x001a
            android.content.Context r1 = r2.f1641a
            android.content.res.ColorStateList r0 = p000a.p001a.p017d.p019b.p020a.C0146a.m489a(r1, r0)
            if (r0 == 0) goto L_0x001a
            return r0
        L_0x001a:
            android.content.res.TypedArray r0 = r2.f1642b
            android.content.res.ColorStateList r3 = r0.getColorStateList(r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.widget.C0439ta.mo2272a(int):android.content.res.ColorStateList");
    }

    /* renamed from: a */
    public Typeface mo2273a(int i, int i2, C0038h.C0039a aVar) {
        int resourceId = this.f1642b.getResourceId(i, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.f1643c == null) {
            this.f1643c = new TypedValue();
        }
        return C0038h.m120a(this.f1641a, resourceId, this.f1643c, i2, aVar);
    }

    /* renamed from: a */
    public void mo2274a() {
        this.f1642b.recycle();
    }

    /* renamed from: a */
    public boolean mo2275a(int i, boolean z) {
        return this.f1642b.getBoolean(i, z);
    }

    /* renamed from: b */
    public int mo2276b(int i, int i2) {
        return this.f1642b.getDimensionPixelOffset(i, i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r0 = r2.f1642b.getResourceId(r3, 0);
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.drawable.Drawable mo2277b(int r3) {
        /*
            r2 = this;
            android.content.res.TypedArray r0 = r2.f1642b
            boolean r0 = r0.hasValue(r3)
            if (r0 == 0) goto L_0x0018
            android.content.res.TypedArray r0 = r2.f1642b
            r1 = 0
            int r0 = r0.getResourceId(r3, r1)
            if (r0 == 0) goto L_0x0018
            android.content.Context r3 = r2.f1641a
            android.graphics.drawable.Drawable r3 = p000a.p001a.p017d.p019b.p020a.C0146a.m492b(r3, r0)
            return r3
        L_0x0018:
            android.content.res.TypedArray r0 = r2.f1642b
            android.graphics.drawable.Drawable r3 = r0.getDrawable(r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.widget.C0439ta.mo2277b(int):android.graphics.drawable.Drawable");
    }

    /* renamed from: c */
    public int mo2278c(int i, int i2) {
        return this.f1642b.getDimensionPixelSize(i, i2);
    }

    /* renamed from: c */
    public Drawable mo2279c(int i) {
        int resourceId;
        if (!this.f1642b.hasValue(i) || (resourceId = this.f1642b.getResourceId(i, 0)) == 0) {
            return null;
        }
        return C0423o.m1851a().mo2226a(this.f1641a, resourceId, true);
    }

    /* renamed from: d */
    public int mo2280d(int i, int i2) {
        return this.f1642b.getInt(i, i2);
    }

    /* renamed from: d */
    public String mo2281d(int i) {
        return this.f1642b.getString(i);
    }

    /* renamed from: e */
    public int mo2282e(int i, int i2) {
        return this.f1642b.getInteger(i, i2);
    }

    /* renamed from: e */
    public CharSequence mo2283e(int i) {
        return this.f1642b.getText(i);
    }

    /* renamed from: f */
    public int mo2284f(int i, int i2) {
        return this.f1642b.getLayoutDimension(i, i2);
    }

    /* renamed from: f */
    public CharSequence[] mo2285f(int i) {
        return this.f1642b.getTextArray(i);
    }

    /* renamed from: g */
    public int mo2286g(int i, int i2) {
        return this.f1642b.getResourceId(i, i2);
    }

    /* renamed from: g */
    public boolean mo2287g(int i) {
        return this.f1642b.hasValue(i);
    }
}

package android.support.p025v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import p000a.p001a.p005c.p014g.C0111f;
import p000a.p001a.p005c.p014g.C0127u;
import p000a.p001a.p017d.p018a.C0145j;

/* renamed from: android.support.v7.widget.Q */
public class C0357Q extends ViewGroup {

    /* renamed from: a */
    private boolean f1286a;

    /* renamed from: b */
    private int f1287b;

    /* renamed from: c */
    private int f1288c;

    /* renamed from: d */
    private int f1289d;

    /* renamed from: e */
    private int f1290e;

    /* renamed from: f */
    private int f1291f;

    /* renamed from: g */
    private float f1292g;

    /* renamed from: h */
    private boolean f1293h;

    /* renamed from: i */
    private int[] f1294i;

    /* renamed from: j */
    private int[] f1295j;

    /* renamed from: k */
    private Drawable f1296k;

    /* renamed from: l */
    private int f1297l;

    /* renamed from: m */
    private int f1298m;

    /* renamed from: n */
    private int f1299n;

    /* renamed from: o */
    private int f1300o;

    /* renamed from: android.support.v7.widget.Q$a */
    public static class C0358a extends ViewGroup.MarginLayoutParams {

        /* renamed from: a */
        public float f1301a;

        /* renamed from: b */
        public int f1302b;

        public C0358a(int i, int i2) {
            super(i, i2);
            this.f1302b = -1;
            this.f1301a = 0.0f;
        }

        public C0358a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1302b = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0145j.LinearLayoutCompat_Layout);
            this.f1301a = obtainStyledAttributes.getFloat(C0145j.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.f1302b = obtainStyledAttributes.getInt(C0145j.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            obtainStyledAttributes.recycle();
        }

        public C0358a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1302b = -1;
        }
    }

    public C0357Q(Context context) {
        this(context, (AttributeSet) null);
    }

    public C0357Q(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public C0357Q(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1286a = true;
        this.f1287b = -1;
        this.f1288c = 0;
        this.f1290e = 8388659;
        C0439ta a = C0439ta.m1902a(context, attributeSet, C0145j.LinearLayoutCompat, i, 0);
        int d = a.mo2280d(C0145j.LinearLayoutCompat_android_orientation, -1);
        if (d >= 0) {
            setOrientation(d);
        }
        int d2 = a.mo2280d(C0145j.LinearLayoutCompat_android_gravity, -1);
        if (d2 >= 0) {
            setGravity(d2);
        }
        boolean a2 = a.mo2275a(C0145j.LinearLayoutCompat_android_baselineAligned, true);
        if (!a2) {
            setBaselineAligned(a2);
        }
        this.f1292g = a.mo2270a(C0145j.LinearLayoutCompat_android_weightSum, -1.0f);
        this.f1287b = a.mo2280d(C0145j.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.f1293h = a.mo2275a(C0145j.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(a.mo2277b(C0145j.LinearLayoutCompat_divider));
        this.f1299n = a.mo2280d(C0145j.LinearLayoutCompat_showDividers, 0);
        this.f1300o = a.mo2278c(C0145j.LinearLayoutCompat_dividerPadding, 0);
        a.mo2274a();
    }

    /* renamed from: a */
    private void m1588a(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }

    /* renamed from: c */
    private void m1589c(int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View a = mo1803a(i3);
            if (a.getVisibility() != 8) {
                C0358a aVar = (C0358a) a.getLayoutParams();
                if (aVar.height == -1) {
                    int i4 = aVar.width;
                    aVar.width = a.getMeasuredWidth();
                    measureChildWithMargins(a, i2, 0, makeMeasureSpec, 0);
                    aVar.width = i4;
                }
            }
        }
    }

    /* renamed from: d */
    private void m1590d(int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View a = mo1803a(i3);
            if (a.getVisibility() != 8) {
                C0358a aVar = (C0358a) a.getLayoutParams();
                if (aVar.width == -1) {
                    int i4 = aVar.height;
                    aVar.height = a.getMeasuredHeight();
                    measureChildWithMargins(a, makeMeasureSpec, 0, i2, 0);
                    aVar.height = i4;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public int mo1801a(View view) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public int mo1802a(View view, int i) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public View mo1803a(int i) {
        return getChildAt(i);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x03aa, code lost:
        if (r8 > 0) goto L_0x03b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x03b5, code lost:
        if (r8 < 0) goto L_0x03b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x03b7, code lost:
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x03b8, code lost:
        r14.measure(android.view.View.MeasureSpec.makeMeasureSpec(r8, r4), r2);
        r9 = android.view.View.combineMeasuredStates(r9, r14.getMeasuredState() & -16777216);
        r2 = r23;
        r4 = r24;
     */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x0441  */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x0463  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x016c  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0170  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0192  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x01bd  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x01bf  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01c6  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01d1  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo1804a(int r39, int r40) {
        /*
            r38 = this;
            r7 = r38
            r8 = r39
            r9 = r40
            r10 = 0
            r7.f1291f = r10
            int r11 = r38.getVirtualChildCount()
            int r12 = android.view.View.MeasureSpec.getMode(r39)
            int r13 = android.view.View.MeasureSpec.getMode(r40)
            int[] r0 = r7.f1294i
            r14 = 4
            if (r0 == 0) goto L_0x001e
            int[] r0 = r7.f1295j
            if (r0 != 0) goto L_0x0026
        L_0x001e:
            int[] r0 = new int[r14]
            r7.f1294i = r0
            int[] r0 = new int[r14]
            r7.f1295j = r0
        L_0x0026:
            int[] r15 = r7.f1294i
            int[] r6 = r7.f1295j
            r16 = 3
            r5 = -1
            r15[r16] = r5
            r17 = 2
            r15[r17] = r5
            r18 = 1
            r15[r18] = r5
            r15[r10] = r5
            r6[r16] = r5
            r6[r17] = r5
            r6[r18] = r5
            r6[r10] = r5
            boolean r4 = r7.f1286a
            boolean r3 = r7.f1293h
            r2 = 1073741824(0x40000000, float:2.0)
            if (r12 != r2) goto L_0x004c
            r19 = 1
            goto L_0x004e
        L_0x004c:
            r19 = 0
        L_0x004e:
            r20 = 0
            r0 = 0
            r1 = 0
            r14 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r26 = 0
            r27 = 1
            r28 = 0
        L_0x0061:
            r29 = r6
            r5 = 8
            if (r1 >= r11) goto L_0x01fe
            android.view.View r6 = r7.mo1803a((int) r1)
            if (r6 != 0) goto L_0x007c
            int r5 = r7.f1291f
            int r6 = r7.mo1815c(r1)
            int r5 = r5 + r6
            r7.f1291f = r5
        L_0x0076:
            r33 = r3
            r37 = r4
            goto L_0x01ee
        L_0x007c:
            int r10 = r6.getVisibility()
            if (r10 != r5) goto L_0x0088
            int r5 = r7.mo1802a((android.view.View) r6, (int) r1)
            int r1 = r1 + r5
            goto L_0x0076
        L_0x0088:
            boolean r5 = r7.mo1814b((int) r1)
            if (r5 == 0) goto L_0x0095
            int r5 = r7.f1291f
            int r10 = r7.f1297l
            int r5 = r5 + r10
            r7.f1291f = r5
        L_0x0095:
            android.view.ViewGroup$LayoutParams r5 = r6.getLayoutParams()
            r10 = r5
            android.support.v7.widget.Q$a r10 = (android.support.p025v7.widget.C0357Q.C0358a) r10
            float r5 = r10.f1301a
            float r32 = r0 + r5
            if (r12 != r2) goto L_0x00e9
            int r0 = r10.width
            if (r0 != 0) goto L_0x00e9
            float r0 = r10.f1301a
            int r0 = (r0 > r20 ? 1 : (r0 == r20 ? 0 : -1))
            if (r0 <= 0) goto L_0x00e9
            if (r19 == 0) goto L_0x00b7
            int r0 = r7.f1291f
            int r5 = r10.leftMargin
            int r2 = r10.rightMargin
            int r5 = r5 + r2
            int r0 = r0 + r5
            goto L_0x00c3
        L_0x00b7:
            int r0 = r7.f1291f
            int r2 = r10.leftMargin
            int r2 = r2 + r0
            int r5 = r10.rightMargin
            int r2 = r2 + r5
            int r0 = java.lang.Math.max(r0, r2)
        L_0x00c3:
            r7.f1291f = r0
            if (r4 == 0) goto L_0x00da
            r0 = 0
            int r2 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r0)
            r6.measure(r2, r2)
            r35 = r1
            r33 = r3
            r37 = r4
            r3 = r6
            r31 = -2
            goto L_0x0163
        L_0x00da:
            r35 = r1
            r33 = r3
            r37 = r4
            r3 = r6
            r1 = 1073741824(0x40000000, float:2.0)
            r24 = 1
            r31 = -2
            goto L_0x0165
        L_0x00e9:
            int r0 = r10.width
            if (r0 != 0) goto L_0x00f8
            float r0 = r10.f1301a
            int r0 = (r0 > r20 ? 1 : (r0 == r20 ? 0 : -1))
            if (r0 <= 0) goto L_0x00f8
            r5 = -2
            r10.width = r5
            r2 = 0
            goto L_0x00fb
        L_0x00f8:
            r5 = -2
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
        L_0x00fb:
            int r0 = (r32 > r20 ? 1 : (r32 == r20 ? 0 : -1))
            if (r0 != 0) goto L_0x0104
            int r0 = r7.f1291f
            r30 = r0
            goto L_0x0106
        L_0x0104:
            r30 = 0
        L_0x0106:
            r34 = 0
            r0 = r38
            r35 = r1
            r1 = r6
            r36 = r2
            r2 = r35
            r33 = r3
            r3 = r39
            r37 = r4
            r4 = r30
            r9 = -1
            r30 = -2
            r5 = r40
            r30 = r6
            r9 = -2147483648(0xffffffff80000000, float:-0.0)
            r31 = -2
            r6 = r34
            r0.mo1808a(r1, r2, r3, r4, r5, r6)
            r0 = r36
            if (r0 == r9) goto L_0x012f
            r10.width = r0
        L_0x012f:
            int r0 = r30.getMeasuredWidth()
            if (r19 == 0) goto L_0x0146
            int r1 = r7.f1291f
            int r2 = r10.leftMargin
            int r2 = r2 + r0
            int r3 = r10.rightMargin
            int r2 = r2 + r3
            r3 = r30
            int r4 = r7.mo1809b((android.view.View) r3)
            int r2 = r2 + r4
            int r1 = r1 + r2
            goto L_0x015b
        L_0x0146:
            r3 = r30
            int r1 = r7.f1291f
            int r2 = r1 + r0
            int r4 = r10.leftMargin
            int r2 = r2 + r4
            int r4 = r10.rightMargin
            int r2 = r2 + r4
            int r4 = r7.mo1809b((android.view.View) r3)
            int r2 = r2 + r4
            int r1 = java.lang.Math.max(r1, r2)
        L_0x015b:
            r7.f1291f = r1
            if (r33 == 0) goto L_0x0163
            int r14 = java.lang.Math.max(r0, r14)
        L_0x0163:
            r1 = 1073741824(0x40000000, float:2.0)
        L_0x0165:
            if (r13 == r1) goto L_0x0170
            int r0 = r10.height
            r2 = -1
            if (r0 != r2) goto L_0x0170
            r0 = 1
            r28 = 1
            goto L_0x0171
        L_0x0170:
            r0 = 0
        L_0x0171:
            int r2 = r10.topMargin
            int r4 = r10.bottomMargin
            int r2 = r2 + r4
            int r4 = r3.getMeasuredHeight()
            int r4 = r4 + r2
            int r5 = r3.getMeasuredState()
            r6 = r26
            int r5 = android.view.View.combineMeasuredStates(r6, r5)
            if (r37 == 0) goto L_0x01b0
            int r6 = r3.getBaseline()
            r9 = -1
            if (r6 == r9) goto L_0x01b0
            int r9 = r10.f1302b
            if (r9 >= 0) goto L_0x0194
            int r9 = r7.f1290e
        L_0x0194:
            r9 = r9 & 112(0x70, float:1.57E-43)
            r25 = 4
            int r9 = r9 >> 4
            r9 = r9 & -2
            int r9 = r9 >> 1
            r1 = r15[r9]
            int r1 = java.lang.Math.max(r1, r6)
            r15[r9] = r1
            r1 = r29[r9]
            int r6 = r4 - r6
            int r1 = java.lang.Math.max(r1, r6)
            r29[r9] = r1
        L_0x01b0:
            r1 = r21
            int r1 = java.lang.Math.max(r1, r4)
            if (r27 == 0) goto L_0x01bf
            int r6 = r10.height
            r9 = -1
            if (r6 != r9) goto L_0x01bf
            r6 = 1
            goto L_0x01c0
        L_0x01bf:
            r6 = 0
        L_0x01c0:
            float r9 = r10.f1301a
            int r9 = (r9 > r20 ? 1 : (r9 == r20 ? 0 : -1))
            if (r9 <= 0) goto L_0x01d1
            if (r0 == 0) goto L_0x01c9
            goto L_0x01ca
        L_0x01c9:
            r2 = r4
        L_0x01ca:
            r10 = r23
            int r23 = java.lang.Math.max(r10, r2)
            goto L_0x01de
        L_0x01d1:
            r10 = r23
            if (r0 == 0) goto L_0x01d6
            r4 = r2
        L_0x01d6:
            r2 = r22
            int r22 = java.lang.Math.max(r2, r4)
            r23 = r10
        L_0x01de:
            r10 = r35
            int r0 = r7.mo1802a((android.view.View) r3, (int) r10)
            int r0 = r0 + r10
            r21 = r1
            r26 = r5
            r27 = r6
            r1 = r0
            r0 = r32
        L_0x01ee:
            int r1 = r1 + 1
            r9 = r40
            r6 = r29
            r3 = r33
            r4 = r37
            r2 = 1073741824(0x40000000, float:2.0)
            r5 = -1
            r10 = 0
            goto L_0x0061
        L_0x01fe:
            r33 = r3
            r37 = r4
            r1 = r21
            r2 = r22
            r10 = r23
            r6 = r26
            r9 = -2147483648(0xffffffff80000000, float:-0.0)
            r31 = -2
            int r3 = r7.f1291f
            if (r3 <= 0) goto L_0x021f
            boolean r3 = r7.mo1814b((int) r11)
            if (r3 == 0) goto L_0x021f
            int r3 = r7.f1291f
            int r4 = r7.f1297l
            int r3 = r3 + r4
            r7.f1291f = r3
        L_0x021f:
            r3 = r15[r18]
            r4 = -1
            if (r3 != r4) goto L_0x0235
            r3 = 0
            r5 = r15[r3]
            if (r5 != r4) goto L_0x0235
            r3 = r15[r17]
            if (r3 != r4) goto L_0x0235
            r3 = r15[r16]
            if (r3 == r4) goto L_0x0232
            goto L_0x0235
        L_0x0232:
            r23 = r6
            goto L_0x0266
        L_0x0235:
            r3 = r15[r16]
            r4 = 0
            r5 = r15[r4]
            r9 = r15[r18]
            r4 = r15[r17]
            int r4 = java.lang.Math.max(r9, r4)
            int r4 = java.lang.Math.max(r5, r4)
            int r3 = java.lang.Math.max(r3, r4)
            r4 = r29[r16]
            r5 = 0
            r9 = r29[r5]
            r5 = r29[r18]
            r23 = r6
            r6 = r29[r17]
            int r5 = java.lang.Math.max(r5, r6)
            int r5 = java.lang.Math.max(r9, r5)
            int r4 = java.lang.Math.max(r4, r5)
            int r3 = r3 + r4
            int r1 = java.lang.Math.max(r1, r3)
        L_0x0266:
            if (r33 == 0) goto L_0x02c9
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r12 == r3) goto L_0x026e
            if (r12 != 0) goto L_0x02c9
        L_0x026e:
            r3 = 0
            r7.f1291f = r3
            r3 = 0
        L_0x0272:
            if (r3 >= r11) goto L_0x02c9
            android.view.View r4 = r7.mo1803a((int) r3)
            if (r4 != 0) goto L_0x0284
            int r4 = r7.f1291f
            int r5 = r7.mo1815c(r3)
            int r4 = r4 + r5
            r7.f1291f = r4
            goto L_0x0291
        L_0x0284:
            int r5 = r4.getVisibility()
            r6 = 8
            if (r5 != r6) goto L_0x0294
            int r4 = r7.mo1802a((android.view.View) r4, (int) r3)
            int r3 = r3 + r4
        L_0x0291:
            r22 = r1
            goto L_0x02c4
        L_0x0294:
            android.view.ViewGroup$LayoutParams r5 = r4.getLayoutParams()
            android.support.v7.widget.Q$a r5 = (android.support.p025v7.widget.C0357Q.C0358a) r5
            if (r19 == 0) goto L_0x02ad
            int r6 = r7.f1291f
            int r9 = r5.leftMargin
            int r9 = r9 + r14
            int r5 = r5.rightMargin
            int r9 = r9 + r5
            int r4 = r7.mo1809b((android.view.View) r4)
            int r9 = r9 + r4
            int r6 = r6 + r9
            r7.f1291f = r6
            goto L_0x0291
        L_0x02ad:
            int r6 = r7.f1291f
            int r9 = r6 + r14
            r22 = r1
            int r1 = r5.leftMargin
            int r9 = r9 + r1
            int r1 = r5.rightMargin
            int r9 = r9 + r1
            int r1 = r7.mo1809b((android.view.View) r4)
            int r9 = r9 + r1
            int r1 = java.lang.Math.max(r6, r9)
            r7.f1291f = r1
        L_0x02c4:
            int r3 = r3 + 1
            r1 = r22
            goto L_0x0272
        L_0x02c9:
            r22 = r1
            int r1 = r7.f1291f
            int r3 = r38.getPaddingLeft()
            int r4 = r38.getPaddingRight()
            int r3 = r3 + r4
            int r1 = r1 + r3
            r7.f1291f = r1
            int r1 = r7.f1291f
            int r3 = r38.getSuggestedMinimumWidth()
            int r1 = java.lang.Math.max(r1, r3)
            r3 = 0
            int r1 = android.view.View.resolveSizeAndState(r1, r8, r3)
            r3 = 16777215(0xffffff, float:2.3509886E-38)
            r3 = r3 & r1
            int r4 = r7.f1291f
            int r3 = r3 - r4
            if (r24 != 0) goto L_0x033c
            if (r3 == 0) goto L_0x02f8
            int r5 = (r0 > r20 ? 1 : (r0 == r20 ? 0 : -1))
            if (r5 <= 0) goto L_0x02f8
            goto L_0x033c
        L_0x02f8:
            int r0 = java.lang.Math.max(r2, r10)
            if (r33 == 0) goto L_0x0334
            r2 = 1073741824(0x40000000, float:2.0)
            if (r12 == r2) goto L_0x0334
            r2 = 0
        L_0x0303:
            if (r2 >= r11) goto L_0x0334
            android.view.View r3 = r7.mo1803a((int) r2)
            if (r3 == 0) goto L_0x0331
            int r5 = r3.getVisibility()
            r6 = 8
            if (r5 != r6) goto L_0x0314
            goto L_0x0331
        L_0x0314:
            android.view.ViewGroup$LayoutParams r5 = r3.getLayoutParams()
            android.support.v7.widget.Q$a r5 = (android.support.p025v7.widget.C0357Q.C0358a) r5
            float r5 = r5.f1301a
            int r5 = (r5 > r20 ? 1 : (r5 == r20 ? 0 : -1))
            if (r5 <= 0) goto L_0x0331
            r5 = 1073741824(0x40000000, float:2.0)
            int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r14, r5)
            int r9 = r3.getMeasuredHeight()
            int r9 = android.view.View.MeasureSpec.makeMeasureSpec(r9, r5)
            r3.measure(r6, r9)
        L_0x0331:
            int r2 = r2 + 1
            goto L_0x0303
        L_0x0334:
            r3 = r40
            r26 = r11
            r2 = r22
            goto L_0x04d5
        L_0x033c:
            float r5 = r7.f1292g
            int r6 = (r5 > r20 ? 1 : (r5 == r20 ? 0 : -1))
            if (r6 <= 0) goto L_0x0343
            r0 = r5
        L_0x0343:
            r5 = -1
            r15[r16] = r5
            r15[r17] = r5
            r15[r18] = r5
            r6 = 0
            r15[r6] = r5
            r29[r16] = r5
            r29[r17] = r5
            r29[r18] = r5
            r29[r6] = r5
            r7.f1291f = r6
            r10 = r2
            r9 = r23
            r6 = -1
            r2 = r0
            r0 = 0
        L_0x035d:
            if (r0 >= r11) goto L_0x047c
            android.view.View r14 = r7.mo1803a((int) r0)
            if (r14 == 0) goto L_0x046b
            int r5 = r14.getVisibility()
            r4 = 8
            if (r5 != r4) goto L_0x036f
            goto L_0x046b
        L_0x036f:
            android.view.ViewGroup$LayoutParams r5 = r14.getLayoutParams()
            android.support.v7.widget.Q$a r5 = (android.support.p025v7.widget.C0357Q.C0358a) r5
            float r4 = r5.f1301a
            int r23 = (r4 > r20 ? 1 : (r4 == r20 ? 0 : -1))
            if (r23 <= 0) goto L_0x03cf
            float r8 = (float) r3
            float r8 = r8 * r4
            float r8 = r8 / r2
            int r8 = (int) r8
            float r2 = r2 - r4
            int r3 = r3 - r8
            int r4 = r38.getPaddingTop()
            int r23 = r38.getPaddingBottom()
            int r4 = r4 + r23
            r23 = r2
            int r2 = r5.topMargin
            int r4 = r4 + r2
            int r2 = r5.bottomMargin
            int r4 = r4 + r2
            int r2 = r5.height
            r24 = r3
            r26 = r11
            r11 = -1
            r3 = r40
            int r2 = android.view.ViewGroup.getChildMeasureSpec(r3, r4, r2)
            int r4 = r5.width
            if (r4 != 0) goto L_0x03ad
            r4 = 1073741824(0x40000000, float:2.0)
            if (r12 == r4) goto L_0x03aa
            goto L_0x03af
        L_0x03aa:
            if (r8 <= 0) goto L_0x03b7
            goto L_0x03b8
        L_0x03ad:
            r4 = 1073741824(0x40000000, float:2.0)
        L_0x03af:
            int r30 = r14.getMeasuredWidth()
            int r8 = r30 + r8
            if (r8 >= 0) goto L_0x03b8
        L_0x03b7:
            r8 = 0
        L_0x03b8:
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r4)
            r14.measure(r8, r2)
            int r2 = r14.getMeasuredState()
            r4 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r2 = r2 & r4
            int r9 = android.view.View.combineMeasuredStates(r9, r2)
            r2 = r23
            r4 = r24
            goto L_0x03d5
        L_0x03cf:
            r4 = r3
            r26 = r11
            r11 = -1
            r3 = r40
        L_0x03d5:
            if (r19 == 0) goto L_0x03f2
            int r8 = r7.f1291f
            int r23 = r14.getMeasuredWidth()
            int r11 = r5.leftMargin
            int r23 = r23 + r11
            int r11 = r5.rightMargin
            int r23 = r23 + r11
            int r11 = r7.mo1809b((android.view.View) r14)
            int r23 = r23 + r11
            int r8 = r8 + r23
            r7.f1291f = r8
            r23 = r2
            goto L_0x040c
        L_0x03f2:
            int r8 = r7.f1291f
            int r11 = r14.getMeasuredWidth()
            int r11 = r11 + r8
            r23 = r2
            int r2 = r5.leftMargin
            int r11 = r11 + r2
            int r2 = r5.rightMargin
            int r11 = r11 + r2
            int r2 = r7.mo1809b((android.view.View) r14)
            int r11 = r11 + r2
            int r2 = java.lang.Math.max(r8, r11)
            r7.f1291f = r2
        L_0x040c:
            r2 = 1073741824(0x40000000, float:2.0)
            if (r13 == r2) goto L_0x0417
            int r2 = r5.height
            r8 = -1
            if (r2 != r8) goto L_0x0417
            r2 = 1
            goto L_0x0418
        L_0x0417:
            r2 = 0
        L_0x0418:
            int r8 = r5.topMargin
            int r11 = r5.bottomMargin
            int r8 = r8 + r11
            int r11 = r14.getMeasuredHeight()
            int r11 = r11 + r8
            int r6 = java.lang.Math.max(r6, r11)
            if (r2 == 0) goto L_0x0429
            goto L_0x042a
        L_0x0429:
            r8 = r11
        L_0x042a:
            int r2 = java.lang.Math.max(r10, r8)
            if (r27 == 0) goto L_0x0437
            int r8 = r5.height
            r10 = -1
            if (r8 != r10) goto L_0x0438
            r8 = 1
            goto L_0x0439
        L_0x0437:
            r10 = -1
        L_0x0438:
            r8 = 0
        L_0x0439:
            if (r37 == 0) goto L_0x0463
            int r14 = r14.getBaseline()
            if (r14 == r10) goto L_0x0463
            int r5 = r5.f1302b
            if (r5 >= 0) goto L_0x0447
            int r5 = r7.f1290e
        L_0x0447:
            r5 = r5 & 112(0x70, float:1.57E-43)
            r24 = 4
            int r5 = r5 >> 4
            r5 = r5 & -2
            int r5 = r5 >> 1
            r10 = r15[r5]
            int r10 = java.lang.Math.max(r10, r14)
            r15[r5] = r10
            r10 = r29[r5]
            int r11 = r11 - r14
            int r10 = java.lang.Math.max(r10, r11)
            r29[r5] = r10
            goto L_0x0465
        L_0x0463:
            r24 = 4
        L_0x0465:
            r10 = r2
            r27 = r8
            r2 = r23
            goto L_0x0472
        L_0x046b:
            r4 = r3
            r26 = r11
            r24 = 4
            r3 = r40
        L_0x0472:
            int r0 = r0 + 1
            r8 = r39
            r3 = r4
            r11 = r26
            r5 = -1
            goto L_0x035d
        L_0x047c:
            r3 = r40
            r26 = r11
            int r0 = r7.f1291f
            int r2 = r38.getPaddingLeft()
            int r4 = r38.getPaddingRight()
            int r2 = r2 + r4
            int r0 = r0 + r2
            r7.f1291f = r0
            r0 = r15[r18]
            r2 = -1
            if (r0 != r2) goto L_0x04a3
            r0 = 0
            r4 = r15[r0]
            if (r4 != r2) goto L_0x04a3
            r0 = r15[r17]
            if (r0 != r2) goto L_0x04a3
            r0 = r15[r16]
            if (r0 == r2) goto L_0x04a1
            goto L_0x04a3
        L_0x04a1:
            r0 = r6
            goto L_0x04d1
        L_0x04a3:
            r0 = r15[r16]
            r2 = 0
            r4 = r15[r2]
            r5 = r15[r18]
            r8 = r15[r17]
            int r5 = java.lang.Math.max(r5, r8)
            int r4 = java.lang.Math.max(r4, r5)
            int r0 = java.lang.Math.max(r0, r4)
            r4 = r29[r16]
            r2 = r29[r2]
            r5 = r29[r18]
            r8 = r29[r17]
            int r5 = java.lang.Math.max(r5, r8)
            int r2 = java.lang.Math.max(r2, r5)
            int r2 = java.lang.Math.max(r4, r2)
            int r0 = r0 + r2
            int r0 = java.lang.Math.max(r6, r0)
        L_0x04d1:
            r2 = r0
            r23 = r9
            r0 = r10
        L_0x04d5:
            if (r27 != 0) goto L_0x04dc
            r4 = 1073741824(0x40000000, float:2.0)
            if (r13 == r4) goto L_0x04dc
            goto L_0x04dd
        L_0x04dc:
            r0 = r2
        L_0x04dd:
            int r2 = r38.getPaddingTop()
            int r4 = r38.getPaddingBottom()
            int r2 = r2 + r4
            int r0 = r0 + r2
            int r2 = r38.getSuggestedMinimumHeight()
            int r0 = java.lang.Math.max(r0, r2)
            r2 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r2 = r23 & r2
            r1 = r1 | r2
            int r2 = r23 << 16
            int r0 = android.view.View.resolveSizeAndState(r0, r3, r2)
            r7.setMeasuredDimension(r1, r0)
            if (r28 == 0) goto L_0x0506
            r0 = r39
            r1 = r26
            r7.m1589c(r1, r0)
        L_0x0506:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.widget.C0357Q.mo1804a(int, int):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00fd  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo1805a(int r25, int r26, int r27, int r28) {
        /*
            r24 = this;
            r6 = r24
            boolean r0 = android.support.p025v7.widget.C0342Ha.m1498a(r24)
            int r7 = r24.getPaddingTop()
            int r1 = r28 - r26
            int r2 = r24.getPaddingBottom()
            int r8 = r1 - r2
            int r1 = r1 - r7
            int r2 = r24.getPaddingBottom()
            int r9 = r1 - r2
            int r10 = r24.getVirtualChildCount()
            int r1 = r6.f1290e
            r2 = 8388615(0x800007, float:1.1754953E-38)
            r2 = r2 & r1
            r11 = r1 & 112(0x70, float:1.57E-43)
            boolean r12 = r6.f1286a
            int[] r13 = r6.f1294i
            int[] r14 = r6.f1295j
            int r1 = p000a.p001a.p005c.p014g.C0127u.m450d(r24)
            int r1 = p000a.p001a.p005c.p014g.C0111f.m395a(r2, r1)
            r15 = 2
            r5 = 1
            if (r1 == r5) goto L_0x004b
            r2 = 5
            if (r1 == r2) goto L_0x003f
            int r1 = r24.getPaddingLeft()
            goto L_0x0056
        L_0x003f:
            int r1 = r24.getPaddingLeft()
            int r1 = r1 + r27
            int r1 = r1 - r25
            int r2 = r6.f1291f
            int r1 = r1 - r2
            goto L_0x0056
        L_0x004b:
            int r1 = r24.getPaddingLeft()
            int r2 = r27 - r25
            int r3 = r6.f1291f
            int r2 = r2 - r3
            int r2 = r2 / r15
            int r1 = r1 + r2
        L_0x0056:
            r2 = 0
            if (r0 == 0) goto L_0x0060
            int r0 = r10 + -1
            r16 = r0
            r17 = -1
            goto L_0x0064
        L_0x0060:
            r16 = 0
            r17 = 1
        L_0x0064:
            r3 = 0
        L_0x0065:
            if (r3 >= r10) goto L_0x0145
            int r0 = r17 * r3
            int r2 = r16 + r0
            android.view.View r0 = r6.mo1803a((int) r2)
            if (r0 != 0) goto L_0x0078
            int r0 = r6.mo1815c(r2)
            int r1 = r1 + r0
            goto L_0x012f
        L_0x0078:
            int r5 = r0.getVisibility()
            r15 = 8
            if (r5 == r15) goto L_0x012d
            int r15 = r0.getMeasuredWidth()
            int r5 = r0.getMeasuredHeight()
            android.view.ViewGroup$LayoutParams r18 = r0.getLayoutParams()
            r4 = r18
            android.support.v7.widget.Q$a r4 = (android.support.p025v7.widget.C0357Q.C0358a) r4
            if (r12 == 0) goto L_0x00a0
            r18 = r3
            int r3 = r4.height
            r19 = r10
            r10 = -1
            if (r3 == r10) goto L_0x00a4
            int r3 = r0.getBaseline()
            goto L_0x00a5
        L_0x00a0:
            r18 = r3
            r19 = r10
        L_0x00a4:
            r3 = -1
        L_0x00a5:
            int r10 = r4.f1302b
            if (r10 >= 0) goto L_0x00aa
            r10 = r11
        L_0x00aa:
            r10 = r10 & 112(0x70, float:1.57E-43)
            r20 = r11
            r11 = 16
            if (r10 == r11) goto L_0x00e9
            r11 = 48
            if (r10 == r11) goto L_0x00d6
            r11 = 80
            if (r10 == r11) goto L_0x00bf
            r3 = r7
            r11 = -1
        L_0x00bc:
            r21 = 1
            goto L_0x00f7
        L_0x00bf:
            int r10 = r8 - r5
            int r11 = r4.bottomMargin
            int r10 = r10 - r11
            r11 = -1
            if (r3 == r11) goto L_0x00d4
            int r21 = r0.getMeasuredHeight()
            int r21 = r21 - r3
            r3 = 2
            r22 = r14[r3]
            int r22 = r22 - r21
            int r10 = r10 - r22
        L_0x00d4:
            r3 = r10
            goto L_0x00bc
        L_0x00d6:
            r11 = -1
            int r10 = r4.topMargin
            int r10 = r10 + r7
            if (r3 == r11) goto L_0x00e5
            r21 = 1
            r22 = r13[r21]
            int r22 = r22 - r3
            int r10 = r10 + r22
            goto L_0x00e7
        L_0x00e5:
            r21 = 1
        L_0x00e7:
            r3 = r10
            goto L_0x00f7
        L_0x00e9:
            r11 = -1
            r21 = 1
            int r3 = r9 - r5
            r10 = 2
            int r3 = r3 / r10
            int r3 = r3 + r7
            int r10 = r4.topMargin
            int r3 = r3 + r10
            int r10 = r4.bottomMargin
            int r3 = r3 - r10
        L_0x00f7:
            boolean r10 = r6.mo1814b((int) r2)
            if (r10 == 0) goto L_0x0100
            int r10 = r6.f1297l
            int r1 = r1 + r10
        L_0x0100:
            int r10 = r4.leftMargin
            int r10 = r10 + r1
            int r1 = r6.mo1801a((android.view.View) r0)
            int r22 = r10 + r1
            r1 = r0
            r0 = r24
            r25 = r1
            r11 = r2
            r2 = r22
            r22 = r7
            r23 = -1
            r7 = r4
            r4 = r15
            r0.m1588a(r1, r2, r3, r4, r5)
            int r0 = r7.rightMargin
            int r15 = r15 + r0
            r0 = r25
            int r1 = r6.mo1809b((android.view.View) r0)
            int r15 = r15 + r1
            int r10 = r10 + r15
            int r0 = r6.mo1802a((android.view.View) r0, (int) r11)
            int r3 = r18 + r0
            r1 = r10
            goto L_0x0139
        L_0x012d:
            r18 = r3
        L_0x012f:
            r22 = r7
            r19 = r10
            r20 = r11
            r21 = 1
            r23 = -1
        L_0x0139:
            int r3 = r3 + 1
            r10 = r19
            r11 = r20
            r7 = r22
            r5 = 1
            r15 = 2
            goto L_0x0065
        L_0x0145:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.widget.C0357Q.mo1805a(int, int, int, int):void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1806a(Canvas canvas) {
        int i;
        int i2;
        int i3;
        int virtualChildCount = getVirtualChildCount();
        boolean a = C0342Ha.m1498a(this);
        for (int i4 = 0; i4 < virtualChildCount; i4++) {
            View a2 = mo1803a(i4);
            if (!(a2 == null || a2.getVisibility() == 8 || !mo1814b(i4))) {
                C0358a aVar = (C0358a) a2.getLayoutParams();
                mo1813b(canvas, a ? a2.getRight() + aVar.rightMargin : (a2.getLeft() - aVar.leftMargin) - this.f1297l);
            }
        }
        if (mo1814b(virtualChildCount)) {
            View a3 = mo1803a(virtualChildCount - 1);
            if (a3 != null) {
                C0358a aVar2 = (C0358a) a3.getLayoutParams();
                if (a) {
                    i3 = a3.getLeft();
                    i2 = aVar2.leftMargin;
                } else {
                    i = a3.getRight() + aVar2.rightMargin;
                    mo1813b(canvas, i);
                }
            } else if (a) {
                i = getPaddingLeft();
                mo1813b(canvas, i);
            } else {
                i3 = getWidth();
                i2 = getPaddingRight();
            }
            i = (i3 - i2) - this.f1297l;
            mo1813b(canvas, i);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1807a(Canvas canvas, int i) {
        this.f1296k.setBounds(getPaddingLeft() + this.f1300o, i, (getWidth() - getPaddingRight()) - this.f1300o, this.f1298m + i);
        this.f1296k.draw(canvas);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1808a(View view, int i, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public int mo1809b(View view) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x02dd, code lost:
        if (r15 > 0) goto L_0x02eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x02e8, code lost:
        if (r15 < 0) goto L_0x02ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x02ea, code lost:
        r15 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x02eb, code lost:
        r13.measure(r9, android.view.View.MeasureSpec.makeMeasureSpec(r15, r10));
        r1 = android.view.View.combineMeasuredStates(r1, r13.getMeasuredState() & -256);
     */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x0326  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0331  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x0333  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo1810b(int r34, int r35) {
        /*
            r33 = this;
            r7 = r33
            r8 = r34
            r9 = r35
            r10 = 0
            r7.f1291f = r10
            int r11 = r33.getVirtualChildCount()
            int r12 = android.view.View.MeasureSpec.getMode(r34)
            int r13 = android.view.View.MeasureSpec.getMode(r35)
            int r14 = r7.f1287b
            boolean r15 = r7.f1293h
            r16 = 0
            r17 = 1
            r0 = 0
            r1 = 0
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r18 = 0
            r19 = 1
            r20 = 0
        L_0x002a:
            r10 = 8
            r22 = r4
            if (r6 >= r11) goto L_0x019d
            android.view.View r4 = r7.mo1803a((int) r6)
            if (r4 != 0) goto L_0x0047
            int r4 = r7.f1291f
            int r10 = r7.mo1815c(r6)
            int r4 = r4 + r10
            r7.f1291f = r4
            r23 = r11
            r4 = r22
        L_0x0043:
            r22 = r13
            goto L_0x0191
        L_0x0047:
            r24 = r1
            int r1 = r4.getVisibility()
            if (r1 != r10) goto L_0x005b
            int r1 = r7.mo1802a((android.view.View) r4, (int) r6)
            int r6 = r6 + r1
            r23 = r11
            r4 = r22
            r1 = r24
            goto L_0x0043
        L_0x005b:
            boolean r1 = r7.mo1814b((int) r6)
            if (r1 == 0) goto L_0x0068
            int r1 = r7.f1291f
            int r10 = r7.f1298m
            int r1 = r1 + r10
            r7.f1291f = r1
        L_0x0068:
            android.view.ViewGroup$LayoutParams r1 = r4.getLayoutParams()
            r10 = r1
            android.support.v7.widget.Q$a r10 = (android.support.p025v7.widget.C0357Q.C0358a) r10
            float r1 = r10.f1301a
            float r25 = r0 + r1
            r1 = 1073741824(0x40000000, float:2.0)
            if (r13 != r1) goto L_0x00a6
            int r0 = r10.height
            if (r0 != 0) goto L_0x00a6
            float r0 = r10.f1301a
            int r0 = (r0 > r16 ? 1 : (r0 == r16 ? 0 : -1))
            if (r0 <= 0) goto L_0x00a6
            int r0 = r7.f1291f
            int r1 = r10.topMargin
            int r1 = r1 + r0
            r26 = r2
            int r2 = r10.bottomMargin
            int r1 = r1 + r2
            int r0 = java.lang.Math.max(r0, r1)
            r7.f1291f = r0
            r0 = r3
            r3 = r4
            r31 = r5
            r23 = r11
            r8 = r24
            r30 = r26
            r18 = 1
            r11 = r6
            r32 = r22
            r22 = r13
            r13 = r32
            goto L_0x0118
        L_0x00a6:
            r26 = r2
            int r0 = r10.height
            if (r0 != 0) goto L_0x00b7
            float r0 = r10.f1301a
            int r0 = (r0 > r16 ? 1 : (r0 == r16 ? 0 : -1))
            if (r0 <= 0) goto L_0x00b7
            r0 = -2
            r10.height = r0
            r2 = 0
            goto L_0x00b9
        L_0x00b7:
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
        L_0x00b9:
            r27 = 0
            int r0 = (r25 > r16 ? 1 : (r25 == r16 ? 0 : -1))
            if (r0 != 0) goto L_0x00c4
            int r0 = r7.f1291f
            r28 = r0
            goto L_0x00c6
        L_0x00c4:
            r28 = 0
        L_0x00c6:
            r0 = r33
            r8 = r24
            r23 = 1073741824(0x40000000, float:2.0)
            r1 = r4
            r29 = r2
            r30 = r26
            r2 = r6
            r9 = r3
            r3 = r34
            r24 = r4
            r23 = r11
            r11 = 1073741824(0x40000000, float:2.0)
            r32 = r22
            r22 = r13
            r13 = r32
            r4 = r27
            r31 = r5
            r5 = r35
            r11 = r6
            r6 = r28
            r0.mo1808a(r1, r2, r3, r4, r5, r6)
            r0 = r29
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r0 == r1) goto L_0x00f5
            r10.height = r0
        L_0x00f5:
            int r0 = r24.getMeasuredHeight()
            int r1 = r7.f1291f
            int r2 = r1 + r0
            int r3 = r10.topMargin
            int r2 = r2 + r3
            int r3 = r10.bottomMargin
            int r2 = r2 + r3
            r3 = r24
            int r4 = r7.mo1809b((android.view.View) r3)
            int r2 = r2 + r4
            int r1 = java.lang.Math.max(r1, r2)
            r7.f1291f = r1
            if (r15 == 0) goto L_0x0117
            int r0 = java.lang.Math.max(r0, r9)
            goto L_0x0118
        L_0x0117:
            r0 = r9
        L_0x0118:
            if (r14 < 0) goto L_0x0122
            int r6 = r11 + 1
            if (r14 != r6) goto L_0x0122
            int r1 = r7.f1291f
            r7.f1288c = r1
        L_0x0122:
            if (r11 >= r14) goto L_0x0133
            float r1 = r10.f1301a
            int r1 = (r1 > r16 ? 1 : (r1 == r16 ? 0 : -1))
            if (r1 > 0) goto L_0x012b
            goto L_0x0133
        L_0x012b:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex."
            r0.<init>(r1)
            throw r0
        L_0x0133:
            r1 = 1073741824(0x40000000, float:2.0)
            if (r12 == r1) goto L_0x0140
            int r1 = r10.width
            r2 = -1
            if (r1 != r2) goto L_0x0140
            r1 = 1
            r20 = 1
            goto L_0x0141
        L_0x0140:
            r1 = 0
        L_0x0141:
            int r2 = r10.leftMargin
            int r4 = r10.rightMargin
            int r2 = r2 + r4
            int r4 = r3.getMeasuredWidth()
            int r4 = r4 + r2
            r5 = r30
            int r5 = java.lang.Math.max(r5, r4)
            int r6 = r3.getMeasuredState()
            int r6 = android.view.View.combineMeasuredStates(r8, r6)
            if (r19 == 0) goto L_0x0162
            int r8 = r10.width
            r9 = -1
            if (r8 != r9) goto L_0x0162
            r8 = 1
            goto L_0x0163
        L_0x0162:
            r8 = 0
        L_0x0163:
            float r9 = r10.f1301a
            int r9 = (r9 > r16 ? 1 : (r9 == r16 ? 0 : -1))
            if (r9 <= 0) goto L_0x0175
            if (r1 == 0) goto L_0x016c
            goto L_0x016d
        L_0x016c:
            r2 = r4
        L_0x016d:
            int r4 = java.lang.Math.max(r13, r2)
            r13 = r4
            r1 = r31
            goto L_0x017f
        L_0x0175:
            if (r1 == 0) goto L_0x0178
            goto L_0x0179
        L_0x0178:
            r2 = r4
        L_0x0179:
            r1 = r31
            int r1 = java.lang.Math.max(r1, r2)
        L_0x017f:
            int r2 = r7.mo1802a((android.view.View) r3, (int) r11)
            int r2 = r2 + r11
            r3 = r0
            r19 = r8
            r4 = r13
            r0 = r25
            r32 = r5
            r5 = r1
            r1 = r6
            r6 = r2
            r2 = r32
        L_0x0191:
            int r6 = r6 + 1
            r8 = r34
            r9 = r35
            r13 = r22
            r11 = r23
            goto L_0x002a
        L_0x019d:
            r8 = r1
            r9 = r3
            r1 = r5
            r23 = r11
            r5 = r2
            r32 = r22
            r22 = r13
            r13 = r32
            int r2 = r7.f1291f
            if (r2 <= 0) goto L_0x01bd
            r2 = r23
            boolean r3 = r7.mo1814b((int) r2)
            if (r3 == 0) goto L_0x01bf
            int r3 = r7.f1291f
            int r4 = r7.f1298m
            int r3 = r3 + r4
            r7.f1291f = r3
            goto L_0x01bf
        L_0x01bd:
            r2 = r23
        L_0x01bf:
            if (r15 == 0) goto L_0x020d
            r3 = r22
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r3 == r4) goto L_0x01c9
            if (r3 != 0) goto L_0x020f
        L_0x01c9:
            r4 = 0
            r7.f1291f = r4
            r4 = 0
        L_0x01cd:
            if (r4 >= r2) goto L_0x020f
            android.view.View r6 = r7.mo1803a((int) r4)
            if (r6 != 0) goto L_0x01df
            int r6 = r7.f1291f
            int r11 = r7.mo1815c(r4)
            int r6 = r6 + r11
        L_0x01dc:
            r7.f1291f = r6
            goto L_0x0208
        L_0x01df:
            int r11 = r6.getVisibility()
            if (r11 != r10) goto L_0x01eb
            int r6 = r7.mo1802a((android.view.View) r6, (int) r4)
            int r4 = r4 + r6
            goto L_0x0208
        L_0x01eb:
            android.view.ViewGroup$LayoutParams r11 = r6.getLayoutParams()
            android.support.v7.widget.Q$a r11 = (android.support.p025v7.widget.C0357Q.C0358a) r11
            int r14 = r7.f1291f
            int r21 = r14 + r9
            int r10 = r11.topMargin
            int r21 = r21 + r10
            int r10 = r11.bottomMargin
            int r21 = r21 + r10
            int r6 = r7.mo1809b((android.view.View) r6)
            int r6 = r21 + r6
            int r6 = java.lang.Math.max(r14, r6)
            goto L_0x01dc
        L_0x0208:
            int r4 = r4 + 1
            r10 = 8
            goto L_0x01cd
        L_0x020d:
            r3 = r22
        L_0x020f:
            int r4 = r7.f1291f
            int r6 = r33.getPaddingTop()
            int r10 = r33.getPaddingBottom()
            int r6 = r6 + r10
            int r4 = r4 + r6
            r7.f1291f = r4
            int r4 = r7.f1291f
            int r6 = r33.getSuggestedMinimumHeight()
            int r4 = java.lang.Math.max(r4, r6)
            r6 = r35
            r10 = r9
            r9 = 0
            int r4 = android.view.View.resolveSizeAndState(r4, r6, r9)
            r9 = 16777215(0xffffff, float:2.3509886E-38)
            r9 = r9 & r4
            int r11 = r7.f1291f
            int r9 = r9 - r11
            if (r18 != 0) goto L_0x0280
            if (r9 == 0) goto L_0x023f
            int r11 = (r0 > r16 ? 1 : (r0 == r16 ? 0 : -1))
            if (r11 <= 0) goto L_0x023f
            goto L_0x0280
        L_0x023f:
            int r0 = java.lang.Math.max(r1, r13)
            if (r15 == 0) goto L_0x027b
            r1 = 1073741824(0x40000000, float:2.0)
            if (r3 == r1) goto L_0x027b
            r1 = 0
        L_0x024a:
            if (r1 >= r2) goto L_0x027b
            android.view.View r3 = r7.mo1803a((int) r1)
            if (r3 == 0) goto L_0x0278
            int r9 = r3.getVisibility()
            r11 = 8
            if (r9 != r11) goto L_0x025b
            goto L_0x0278
        L_0x025b:
            android.view.ViewGroup$LayoutParams r9 = r3.getLayoutParams()
            android.support.v7.widget.Q$a r9 = (android.support.p025v7.widget.C0357Q.C0358a) r9
            float r9 = r9.f1301a
            int r9 = (r9 > r16 ? 1 : (r9 == r16 ? 0 : -1))
            if (r9 <= 0) goto L_0x0278
            int r9 = r3.getMeasuredWidth()
            r11 = 1073741824(0x40000000, float:2.0)
            int r9 = android.view.View.MeasureSpec.makeMeasureSpec(r9, r11)
            int r13 = android.view.View.MeasureSpec.makeMeasureSpec(r10, r11)
            r3.measure(r9, r13)
        L_0x0278:
            int r1 = r1 + 1
            goto L_0x024a
        L_0x027b:
            r11 = r34
            r1 = r8
            goto L_0x036a
        L_0x0280:
            float r10 = r7.f1292g
            int r11 = (r10 > r16 ? 1 : (r10 == r16 ? 0 : -1))
            if (r11 <= 0) goto L_0x0287
            r0 = r10
        L_0x0287:
            r10 = 0
            r7.f1291f = r10
            r11 = r0
            r0 = 0
            r32 = r8
            r8 = r1
            r1 = r32
        L_0x0291:
            if (r0 >= r2) goto L_0x0359
            android.view.View r13 = r7.mo1803a((int) r0)
            int r14 = r13.getVisibility()
            r15 = 8
            if (r14 != r15) goto L_0x02a5
            r21 = r11
            r11 = r34
            goto L_0x0352
        L_0x02a5:
            android.view.ViewGroup$LayoutParams r14 = r13.getLayoutParams()
            android.support.v7.widget.Q$a r14 = (android.support.p025v7.widget.C0357Q.C0358a) r14
            float r10 = r14.f1301a
            int r18 = (r10 > r16 ? 1 : (r10 == r16 ? 0 : -1))
            if (r18 <= 0) goto L_0x02fd
            float r15 = (float) r9
            float r15 = r15 * r10
            float r15 = r15 / r11
            int r15 = (int) r15
            float r11 = r11 - r10
            int r9 = r9 - r15
            int r10 = r33.getPaddingLeft()
            int r18 = r33.getPaddingRight()
            int r10 = r10 + r18
            r18 = r9
            int r9 = r14.leftMargin
            int r10 = r10 + r9
            int r9 = r14.rightMargin
            int r10 = r10 + r9
            int r9 = r14.width
            r21 = r11
            r11 = r34
            int r9 = android.view.ViewGroup.getChildMeasureSpec(r11, r10, r9)
            int r10 = r14.height
            if (r10 != 0) goto L_0x02e0
            r10 = 1073741824(0x40000000, float:2.0)
            if (r3 == r10) goto L_0x02dd
            goto L_0x02e2
        L_0x02dd:
            if (r15 <= 0) goto L_0x02ea
            goto L_0x02eb
        L_0x02e0:
            r10 = 1073741824(0x40000000, float:2.0)
        L_0x02e2:
            int r23 = r13.getMeasuredHeight()
            int r15 = r23 + r15
            if (r15 >= 0) goto L_0x02eb
        L_0x02ea:
            r15 = 0
        L_0x02eb:
            int r15 = android.view.View.MeasureSpec.makeMeasureSpec(r15, r10)
            r13.measure(r9, r15)
            int r9 = r13.getMeasuredState()
            r9 = r9 & -256(0xffffffffffffff00, float:NaN)
            int r1 = android.view.View.combineMeasuredStates(r1, r9)
            goto L_0x0304
        L_0x02fd:
            r10 = r11
            r11 = r34
            r18 = r9
            r21 = r10
        L_0x0304:
            int r9 = r14.leftMargin
            int r10 = r14.rightMargin
            int r9 = r9 + r10
            int r10 = r13.getMeasuredWidth()
            int r10 = r10 + r9
            int r5 = java.lang.Math.max(r5, r10)
            r15 = 1073741824(0x40000000, float:2.0)
            if (r12 == r15) goto L_0x031f
            int r15 = r14.width
            r23 = r1
            r1 = -1
            if (r15 != r1) goto L_0x0322
            r15 = 1
            goto L_0x0323
        L_0x031f:
            r23 = r1
            r1 = -1
        L_0x0322:
            r15 = 0
        L_0x0323:
            if (r15 == 0) goto L_0x0326
            goto L_0x0327
        L_0x0326:
            r9 = r10
        L_0x0327:
            int r8 = java.lang.Math.max(r8, r9)
            if (r19 == 0) goto L_0x0333
            int r9 = r14.width
            if (r9 != r1) goto L_0x0333
            r9 = 1
            goto L_0x0334
        L_0x0333:
            r9 = 0
        L_0x0334:
            int r10 = r7.f1291f
            int r15 = r13.getMeasuredHeight()
            int r15 = r15 + r10
            int r1 = r14.topMargin
            int r15 = r15 + r1
            int r1 = r14.bottomMargin
            int r15 = r15 + r1
            int r1 = r7.mo1809b((android.view.View) r13)
            int r15 = r15 + r1
            int r1 = java.lang.Math.max(r10, r15)
            r7.f1291f = r1
            r19 = r9
            r9 = r18
            r1 = r23
        L_0x0352:
            int r0 = r0 + 1
            r11 = r21
            r10 = 0
            goto L_0x0291
        L_0x0359:
            r11 = r34
            int r0 = r7.f1291f
            int r3 = r33.getPaddingTop()
            int r9 = r33.getPaddingBottom()
            int r3 = r3 + r9
            int r0 = r0 + r3
            r7.f1291f = r0
            r0 = r8
        L_0x036a:
            if (r19 != 0) goto L_0x0371
            r3 = 1073741824(0x40000000, float:2.0)
            if (r12 == r3) goto L_0x0371
            goto L_0x0372
        L_0x0371:
            r0 = r5
        L_0x0372:
            int r3 = r33.getPaddingLeft()
            int r5 = r33.getPaddingRight()
            int r3 = r3 + r5
            int r0 = r0 + r3
            int r3 = r33.getSuggestedMinimumWidth()
            int r0 = java.lang.Math.max(r0, r3)
            int r0 = android.view.View.resolveSizeAndState(r0, r11, r1)
            r7.setMeasuredDimension(r0, r4)
            if (r20 == 0) goto L_0x0390
            r7.m1590d(r2, r6)
        L_0x0390:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.widget.C0357Q.mo1810b(int, int):void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo1811b(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int paddingLeft = getPaddingLeft();
        int i7 = i3 - i;
        int paddingRight = i7 - getPaddingRight();
        int paddingRight2 = (i7 - paddingLeft) - getPaddingRight();
        int virtualChildCount = getVirtualChildCount();
        int i8 = this.f1290e;
        int i9 = i8 & 112;
        int i10 = i8 & 8388615;
        int paddingTop = i9 != 16 ? i9 != 80 ? getPaddingTop() : ((getPaddingTop() + i4) - i2) - this.f1291f : getPaddingTop() + (((i4 - i2) - this.f1291f) / 2);
        int i11 = 0;
        while (i11 < virtualChildCount) {
            View a = mo1803a(i11);
            if (a == null) {
                paddingTop += mo1815c(i11);
            } else if (a.getVisibility() != 8) {
                int measuredWidth = a.getMeasuredWidth();
                int measuredHeight = a.getMeasuredHeight();
                C0358a aVar = (C0358a) a.getLayoutParams();
                int i12 = aVar.f1302b;
                if (i12 < 0) {
                    i12 = i10;
                }
                int a2 = C0111f.m395a(i12, C0127u.m450d(this)) & 7;
                if (a2 == 1) {
                    i5 = ((paddingRight2 - measuredWidth) / 2) + paddingLeft + aVar.leftMargin;
                    i6 = i5 - aVar.rightMargin;
                } else if (a2 != 5) {
                    i6 = aVar.leftMargin + paddingLeft;
                } else {
                    i5 = paddingRight - measuredWidth;
                    i6 = i5 - aVar.rightMargin;
                }
                int i13 = i6;
                if (mo1814b(i11)) {
                    paddingTop += this.f1298m;
                }
                int i14 = paddingTop + aVar.topMargin;
                m1588a(a, i13, i14 + mo1801a(a), measuredWidth, measuredHeight);
                i11 += mo1802a(a, i11);
                paddingTop = i14 + measuredHeight + aVar.bottomMargin + mo1809b(a);
            }
            i11++;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo1812b(Canvas canvas) {
        int virtualChildCount = getVirtualChildCount();
        for (int i = 0; i < virtualChildCount; i++) {
            View a = mo1803a(i);
            if (!(a == null || a.getVisibility() == 8 || !mo1814b(i))) {
                mo1807a(canvas, (a.getTop() - ((C0358a) a.getLayoutParams()).topMargin) - this.f1298m);
            }
        }
        if (mo1814b(virtualChildCount)) {
            View a2 = mo1803a(virtualChildCount - 1);
            mo1807a(canvas, a2 == null ? (getHeight() - getPaddingBottom()) - this.f1298m : a2.getBottom() + ((C0358a) a2.getLayoutParams()).bottomMargin);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo1813b(Canvas canvas, int i) {
        this.f1296k.setBounds(i, getPaddingTop() + this.f1300o, this.f1297l + i, (getHeight() - getPaddingBottom()) - this.f1300o);
        this.f1296k.draw(canvas);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public boolean mo1814b(int i) {
        if (i == 0) {
            return (this.f1299n & 1) != 0;
        }
        if (i == getChildCount()) {
            return (this.f1299n & 4) != 0;
        }
        if ((this.f1299n & 2) == 0) {
            return false;
        }
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (getChildAt(i2).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public int mo1815c(int i) {
        return 0;
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C0358a;
    }

    /* access modifiers changed from: protected */
    public C0358a generateDefaultLayoutParams() {
        int i = this.f1289d;
        if (i == 0) {
            return new C0358a(-2, -2);
        }
        if (i == 1) {
            return new C0358a(-1, -2);
        }
        return null;
    }

    public C0358a generateLayoutParams(AttributeSet attributeSet) {
        return new C0358a(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public C0358a generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new C0358a(layoutParams);
    }

    public int getBaseline() {
        int i;
        if (this.f1287b < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i2 = this.f1287b;
        if (childCount > i2) {
            View childAt = getChildAt(i2);
            int baseline = childAt.getBaseline();
            if (baseline != -1) {
                int i3 = this.f1288c;
                if (this.f1289d == 1 && (i = this.f1290e & 112) != 48) {
                    if (i == 16) {
                        i3 += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.f1291f) / 2;
                    } else if (i == 80) {
                        i3 = ((getBottom() - getTop()) - getPaddingBottom()) - this.f1291f;
                    }
                }
                return i3 + ((C0358a) childAt.getLayoutParams()).topMargin + baseline;
            } else if (this.f1287b == 0) {
                return -1;
            } else {
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
        } else {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
    }

    public int getBaselineAlignedChildIndex() {
        return this.f1287b;
    }

    public Drawable getDividerDrawable() {
        return this.f1296k;
    }

    public int getDividerPadding() {
        return this.f1300o;
    }

    public int getDividerWidth() {
        return this.f1297l;
    }

    public int getGravity() {
        return this.f1290e;
    }

    public int getOrientation() {
        return this.f1289d;
    }

    public int getShowDividers() {
        return this.f1299n;
    }

    /* access modifiers changed from: package-private */
    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.f1292g;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.f1296k != null) {
            if (this.f1289d == 1) {
                mo1812b(canvas);
            } else {
                mo1806a(canvas);
            }
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(C0357Q.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(C0357Q.class.getName());
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.f1289d == 1) {
            mo1811b(i, i2, i3, i4);
        } else {
            mo1805a(i, i2, i3, i4);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.f1289d == 1) {
            mo1810b(i, i2);
        } else {
            mo1804a(i, i2);
        }
    }

    public void setBaselineAligned(boolean z) {
        this.f1286a = z;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i < 0 || i >= getChildCount()) {
            throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
        }
        this.f1287b = i;
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable != this.f1296k) {
            this.f1296k = drawable;
            boolean z = false;
            if (drawable != null) {
                this.f1297l = drawable.getIntrinsicWidth();
                this.f1298m = drawable.getIntrinsicHeight();
            } else {
                this.f1297l = 0;
                this.f1298m = 0;
            }
            if (drawable == null) {
                z = true;
            }
            setWillNotDraw(z);
            requestLayout();
        }
    }

    public void setDividerPadding(int i) {
        this.f1300o = i;
    }

    public void setGravity(int i) {
        if (this.f1290e != i) {
            if ((8388615 & i) == 0) {
                i |= 8388611;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.f1290e = i;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i) {
        int i2 = i & 8388615;
        int i3 = this.f1290e;
        if ((8388615 & i3) != i2) {
            this.f1290e = i2 | (-8388616 & i3);
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.f1293h = z;
    }

    public void setOrientation(int i) {
        if (this.f1289d != i) {
            this.f1289d = i;
            requestLayout();
        }
    }

    public void setShowDividers(int i) {
        if (i != this.f1299n) {
            requestLayout();
        }
        this.f1299n = i;
    }

    public void setVerticalGravity(int i) {
        int i2 = i & 112;
        int i3 = this.f1290e;
        if ((i3 & 112) != i2) {
            this.f1290e = i2 | (i3 & -113);
            requestLayout();
        }
    }

    public void setWeightSum(float f) {
        this.f1292g = Math.max(0.0f, f);
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }
}

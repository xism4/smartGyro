package android.support.p025v7.widget;

import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.p025v7.view.menu.C0310v;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.widget.OverScroller;
import p000a.p001a.p005c.p014g.C0121o;
import p000a.p001a.p005c.p014g.C0122p;
import p000a.p001a.p005c.p014g.C0127u;
import p000a.p001a.p017d.p018a.C0136a;
import p000a.p001a.p017d.p018a.C0141f;

/* renamed from: android.support.v7.widget.ActionBarOverlayLayout */
public class ActionBarOverlayLayout extends ViewGroup implements C0346K, C0121o {

    /* renamed from: a */
    static final int[] f1123a = {C0136a.actionBarSize, 16842841};

    /* renamed from: A */
    private final Runnable f1124A;

    /* renamed from: B */
    private final C0122p f1125B;

    /* renamed from: b */
    private int f1126b;

    /* renamed from: c */
    private int f1127c;

    /* renamed from: d */
    private ContentFrameLayout f1128d;

    /* renamed from: e */
    ActionBarContainer f1129e;

    /* renamed from: f */
    private C0347L f1130f;

    /* renamed from: g */
    private Drawable f1131g;

    /* renamed from: h */
    private boolean f1132h;

    /* renamed from: i */
    private boolean f1133i;

    /* renamed from: j */
    private boolean f1134j;

    /* renamed from: k */
    private boolean f1135k;

    /* renamed from: l */
    boolean f1136l;

    /* renamed from: m */
    private int f1137m;

    /* renamed from: n */
    private int f1138n;

    /* renamed from: o */
    private final Rect f1139o;

    /* renamed from: p */
    private final Rect f1140p;

    /* renamed from: q */
    private final Rect f1141q;

    /* renamed from: r */
    private final Rect f1142r;

    /* renamed from: s */
    private final Rect f1143s;

    /* renamed from: t */
    private final Rect f1144t;

    /* renamed from: u */
    private final Rect f1145u;

    /* renamed from: v */
    private C0319a f1146v;

    /* renamed from: w */
    private OverScroller f1147w;

    /* renamed from: x */
    ViewPropertyAnimator f1148x;

    /* renamed from: y */
    final AnimatorListenerAdapter f1149y;

    /* renamed from: z */
    private final Runnable f1150z;

    /* renamed from: android.support.v7.widget.ActionBarOverlayLayout$a */
    public interface C0319a {
        /* renamed from: a */
        void mo963a();

        /* renamed from: a */
        void mo967a(boolean z);

        /* renamed from: b */
        void mo969b();

        /* renamed from: c */
        void mo971c();

        /* renamed from: d */
        void mo973d();

        void onWindowVisibilityChanged(int i);
    }

    /* renamed from: android.support.v7.widget.ActionBarOverlayLayout$b */
    public static class C0320b extends ViewGroup.MarginLayoutParams {
        public C0320b(int i, int i2) {
            super(i, i2);
        }

        public C0320b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public C0320b(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public ActionBarOverlayLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1127c = 0;
        this.f1139o = new Rect();
        this.f1140p = new Rect();
        this.f1141q = new Rect();
        this.f1142r = new Rect();
        this.f1143s = new Rect();
        this.f1144t = new Rect();
        this.f1145u = new Rect();
        this.f1149y = new C0394d(this);
        this.f1150z = new C0396e(this);
        this.f1124A = new C0398f(this);
        m1394a(context);
        this.f1125B = new C0122p(this);
    }

    /* renamed from: a */
    private C0347L m1393a(View view) {
        if (view instanceof C0347L) {
            return (C0347L) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        throw new IllegalStateException("Can't make a decor toolbar out of " + view.getClass().getSimpleName());
    }

    /* renamed from: a */
    private void m1394a(Context context) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(f1123a);
        boolean z = false;
        this.f1126b = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.f1131g = obtainStyledAttributes.getDrawable(1);
        setWillNotDraw(this.f1131g == null);
        obtainStyledAttributes.recycle();
        if (context.getApplicationInfo().targetSdkVersion < 19) {
            z = true;
        }
        this.f1132h = z;
        this.f1147w = new OverScroller(context);
    }

    /* renamed from: a */
    private boolean m1395a(float f, float f2) {
        this.f1147w.fling(0, 0, 0, (int) f2, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return this.f1147w.getFinalY() > this.f1129e.getHeight();
    }

    /* renamed from: a */
    private boolean m1396a(View view, Rect rect, boolean z, boolean z2, boolean z3, boolean z4) {
        boolean z5;
        int i;
        int i2;
        int i3;
        int i4;
        C0320b bVar = (C0320b) view.getLayoutParams();
        if (!z || bVar.leftMargin == (i4 = rect.left)) {
            z5 = false;
        } else {
            bVar.leftMargin = i4;
            z5 = true;
        }
        if (z2 && bVar.topMargin != (i3 = rect.top)) {
            bVar.topMargin = i3;
            z5 = true;
        }
        if (z4 && bVar.rightMargin != (i2 = rect.right)) {
            bVar.rightMargin = i2;
            z5 = true;
        }
        if (!z3 || bVar.bottomMargin == (i = rect.bottom)) {
            return z5;
        }
        bVar.bottomMargin = i;
        return true;
    }

    /* renamed from: k */
    private void m1397k() {
        mo1592h();
        this.f1124A.run();
    }

    /* renamed from: l */
    private void m1398l() {
        mo1592h();
        postDelayed(this.f1124A, 600);
    }

    /* renamed from: m */
    private void m1399m() {
        mo1592h();
        postDelayed(this.f1150z, 600);
    }

    /* renamed from: n */
    private void m1400n() {
        mo1592h();
        this.f1150z.run();
    }

    /* renamed from: a */
    public void mo1574a(int i) {
        mo1594j();
        if (i == 2) {
            this.f1130f.mo1525m();
        } else if (i == 5) {
            this.f1130f.mo1526n();
        } else if (i == 109) {
            setOverlayMode(true);
        }
    }

    /* renamed from: a */
    public void mo1575a(Menu menu, C0310v.C0311a aVar) {
        mo1594j();
        this.f1130f.mo1499a(menu, aVar);
    }

    /* renamed from: a */
    public boolean mo1576a() {
        mo1594j();
        return this.f1130f.mo1503a();
    }

    /* renamed from: b */
    public void mo1577b() {
        mo1594j();
        this.f1130f.mo1504b();
    }

    /* renamed from: c */
    public boolean mo1578c() {
        mo1594j();
        return this.f1130f.mo1511c();
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C0320b;
    }

    /* renamed from: d */
    public boolean mo1580d() {
        mo1594j();
        return this.f1130f.mo1514d();
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.f1131g != null && !this.f1132h) {
            int bottom = this.f1129e.getVisibility() == 0 ? (int) (((float) this.f1129e.getBottom()) + this.f1129e.getTranslationY() + 0.5f) : 0;
            this.f1131g.setBounds(0, bottom, getWidth(), this.f1131g.getIntrinsicHeight() + bottom);
            this.f1131g.draw(canvas);
        }
    }

    /* renamed from: e */
    public boolean mo1582e() {
        mo1594j();
        return this.f1130f.mo1516e();
    }

    /* renamed from: f */
    public boolean mo1583f() {
        mo1594j();
        return this.f1130f.mo1517f();
    }

    /* access modifiers changed from: protected */
    public boolean fitSystemWindows(Rect rect) {
        mo1594j();
        int f = C0127u.m452f(this) & 256;
        boolean a = m1396a(this.f1129e, rect, true, true, false, true);
        this.f1142r.set(rect);
        C0342Ha.m1497a(this, this.f1142r, this.f1139o);
        if (!this.f1143s.equals(this.f1142r)) {
            this.f1143s.set(this.f1142r);
            a = true;
        }
        if (!this.f1140p.equals(this.f1139o)) {
            this.f1140p.set(this.f1139o);
            a = true;
        }
        if (a) {
            requestLayout();
        }
        return true;
    }

    /* renamed from: g */
    public void mo1585g() {
        mo1594j();
        this.f1130f.mo1518g();
    }

    /* access modifiers changed from: protected */
    public C0320b generateDefaultLayoutParams() {
        return new C0320b(-1, -1);
    }

    public C0320b generateLayoutParams(AttributeSet attributeSet) {
        return new C0320b(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new C0320b(layoutParams);
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.f1129e;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    public int getNestedScrollAxes() {
        return this.f1125B.mo486a();
    }

    public CharSequence getTitle() {
        mo1594j();
        return this.f1130f.getTitle();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: h */
    public void mo1592h() {
        removeCallbacks(this.f1150z);
        removeCallbacks(this.f1124A);
        ViewPropertyAnimator viewPropertyAnimator = this.f1148x;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    /* renamed from: i */
    public boolean mo1593i() {
        return this.f1133i;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: j */
    public void mo1594j() {
        if (this.f1128d == null) {
            this.f1128d = (ContentFrameLayout) findViewById(C0141f.action_bar_activity_content);
            this.f1129e = (ActionBarContainer) findViewById(C0141f.action_bar_container);
            this.f1130f = m1393a(findViewById(C0141f.action_bar));
        }
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m1394a(getContext());
        C0127u.m457k(this);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mo1592h();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        getPaddingRight();
        int paddingTop = getPaddingTop();
        getPaddingBottom();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                C0320b bVar = (C0320b) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i6 = bVar.leftMargin + paddingLeft;
                int i7 = bVar.topMargin + paddingTop;
                childAt.layout(i6, i7, measuredWidth + i6, measuredHeight + i7);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        mo1594j();
        measureChildWithMargins(this.f1129e, i, 0, i2, 0);
        C0320b bVar = (C0320b) this.f1129e.getLayoutParams();
        int max = Math.max(0, this.f1129e.getMeasuredWidth() + bVar.leftMargin + bVar.rightMargin);
        int max2 = Math.max(0, this.f1129e.getMeasuredHeight() + bVar.topMargin + bVar.bottomMargin);
        int combineMeasuredStates = View.combineMeasuredStates(0, this.f1129e.getMeasuredState());
        boolean z = (C0127u.m452f(this) & 256) != 0;
        if (z) {
            i3 = this.f1126b;
            if (this.f1134j && this.f1129e.getTabContainer() != null) {
                i3 += this.f1126b;
            }
        } else {
            i3 = this.f1129e.getVisibility() != 8 ? this.f1129e.getMeasuredHeight() : 0;
        }
        this.f1141q.set(this.f1139o);
        this.f1144t.set(this.f1142r);
        Rect rect = (this.f1133i || z) ? this.f1144t : this.f1141q;
        rect.top += i3;
        rect.bottom += 0;
        m1396a(this.f1128d, this.f1141q, true, true, true, true);
        if (!this.f1145u.equals(this.f1144t)) {
            this.f1145u.set(this.f1144t);
            this.f1128d.mo1689a(this.f1144t);
        }
        measureChildWithMargins(this.f1128d, i, 0, i2, 0);
        C0320b bVar2 = (C0320b) this.f1128d.getLayoutParams();
        int max3 = Math.max(max, this.f1128d.getMeasuredWidth() + bVar2.leftMargin + bVar2.rightMargin);
        int max4 = Math.max(max2, this.f1128d.getMeasuredHeight() + bVar2.topMargin + bVar2.bottomMargin);
        int combineMeasuredStates2 = View.combineMeasuredStates(combineMeasuredStates, this.f1128d.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(max3 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i, combineMeasuredStates2), View.resolveSizeAndState(Math.max(max4 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i2, combineMeasuredStates2 << 16));
    }

    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (!this.f1135k || !z) {
            return false;
        }
        if (m1395a(f, f2)) {
            m1397k();
        } else {
            m1400n();
        }
        this.f1136l = true;
        return true;
    }

    public boolean onNestedPreFling(View view, float f, float f2) {
        return false;
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        this.f1137m += i2;
        setActionBarHideOffset(this.f1137m);
    }

    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.f1125B.mo488a(view, view2, i);
        this.f1137m = getActionBarHideOffset();
        mo1592h();
        C0319a aVar = this.f1146v;
        if (aVar != null) {
            aVar.mo973d();
        }
    }

    public boolean onStartNestedScroll(View view, View view2, int i) {
        if ((i & 2) == 0 || this.f1129e.getVisibility() != 0) {
            return false;
        }
        return this.f1135k;
    }

    public void onStopNestedScroll(View view) {
        if (this.f1135k && !this.f1136l) {
            if (this.f1137m <= this.f1129e.getHeight()) {
                m1399m();
            } else {
                m1398l();
            }
        }
        C0319a aVar = this.f1146v;
        if (aVar != null) {
            aVar.mo969b();
        }
    }

    public void onWindowSystemUiVisibilityChanged(int i) {
        if (Build.VERSION.SDK_INT >= 16) {
            super.onWindowSystemUiVisibilityChanged(i);
        }
        mo1594j();
        int i2 = this.f1138n ^ i;
        this.f1138n = i;
        boolean z = false;
        boolean z2 = (i & 4) == 0;
        if ((i & 256) != 0) {
            z = true;
        }
        C0319a aVar = this.f1146v;
        if (aVar != null) {
            aVar.mo967a(!z);
            if (z2 || !z) {
                this.f1146v.mo963a();
            } else {
                this.f1146v.mo971c();
            }
        }
        if ((i2 & 256) != 0 && this.f1146v != null) {
            C0127u.m457k(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.f1127c = i;
        C0319a aVar = this.f1146v;
        if (aVar != null) {
            aVar.onWindowVisibilityChanged(i);
        }
    }

    public void setActionBarHideOffset(int i) {
        mo1592h();
        this.f1129e.setTranslationY((float) (-Math.max(0, Math.min(i, this.f1129e.getHeight()))));
    }

    public void setActionBarVisibilityCallback(C0319a aVar) {
        this.f1146v = aVar;
        if (getWindowToken() != null) {
            this.f1146v.onWindowVisibilityChanged(this.f1127c);
            int i = this.f1138n;
            if (i != 0) {
                onWindowSystemUiVisibilityChanged(i);
                C0127u.m457k(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z) {
        this.f1134j = z;
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        if (z != this.f1135k) {
            this.f1135k = z;
            if (!z) {
                mo1592h();
                setActionBarHideOffset(0);
            }
        }
    }

    public void setIcon(int i) {
        mo1594j();
        this.f1130f.setIcon(i);
    }

    public void setIcon(Drawable drawable) {
        mo1594j();
        this.f1130f.setIcon(drawable);
    }

    public void setLogo(int i) {
        mo1594j();
        this.f1130f.mo1505b(i);
    }

    public void setOverlayMode(boolean z) {
        this.f1133i = z;
        this.f1132h = z && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void setShowingForActionMode(boolean z) {
    }

    public void setUiOptions(int i) {
    }

    public void setWindowCallback(Window.Callback callback) {
        mo1594j();
        this.f1130f.setWindowCallback(callback);
    }

    public void setWindowTitle(CharSequence charSequence) {
        mo1594j();
        this.f1130f.setWindowTitle(charSequence);
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }
}

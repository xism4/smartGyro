package android.support.p024v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;
import java.util.ArrayList;
import p000a.p001a.p005c.p014g.C0106d;
import p000a.p001a.p005c.p014g.C0117k;
import p000a.p001a.p005c.p014g.C0119m;
import p000a.p001a.p005c.p014g.C0120n;
import p000a.p001a.p005c.p014g.C0122p;
import p000a.p001a.p005c.p014g.C0124r;
import p000a.p001a.p005c.p014g.C0127u;
import p000a.p001a.p005c.p014g.p015a.C0097a;
import p000a.p001a.p005c.p014g.p015a.C0099c;

/* renamed from: android.support.v4.widget.NestedScrollView */
public class NestedScrollView extends FrameLayout implements C0120n, C0117k, C0124r {

    /* renamed from: a */
    private static final C0198a f479a = new C0198a();

    /* renamed from: b */
    private static final int[] f480b = {16843130};

    /* renamed from: A */
    private float f481A;

    /* renamed from: B */
    private C0199b f482B;

    /* renamed from: c */
    private long f483c;

    /* renamed from: d */
    private final Rect f484d;

    /* renamed from: e */
    private OverScroller f485e;

    /* renamed from: f */
    private EdgeEffect f486f;

    /* renamed from: g */
    private EdgeEffect f487g;

    /* renamed from: h */
    private int f488h;

    /* renamed from: i */
    private boolean f489i;

    /* renamed from: j */
    private boolean f490j;

    /* renamed from: k */
    private View f491k;

    /* renamed from: l */
    private boolean f492l;

    /* renamed from: m */
    private VelocityTracker f493m;

    /* renamed from: n */
    private boolean f494n;

    /* renamed from: o */
    private boolean f495o;

    /* renamed from: p */
    private int f496p;

    /* renamed from: q */
    private int f497q;

    /* renamed from: r */
    private int f498r;

    /* renamed from: s */
    private int f499s;

    /* renamed from: t */
    private final int[] f500t;

    /* renamed from: u */
    private final int[] f501u;

    /* renamed from: v */
    private int f502v;

    /* renamed from: w */
    private int f503w;

    /* renamed from: x */
    private C0200c f504x;

    /* renamed from: y */
    private final C0122p f505y;

    /* renamed from: z */
    private final C0119m f506z;

    /* renamed from: android.support.v4.widget.NestedScrollView$a */
    static class C0198a extends C0106d {
        C0198a() {
        }

        /* renamed from: a */
        public void mo436a(View view, C0097a aVar) {
            int scrollRange;
            super.mo436a(view, aVar);
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            aVar.mo403a((CharSequence) ScrollView.class.getName());
            if (nestedScrollView.isEnabled() && (scrollRange = nestedScrollView.getScrollRange()) > 0) {
                aVar.mo404a(true);
                if (nestedScrollView.getScrollY() > 0) {
                    aVar.mo401a(8192);
                }
                if (nestedScrollView.getScrollY() < scrollRange) {
                    aVar.mo401a(4096);
                }
            }
        }

        /* renamed from: a */
        public boolean mo437a(View view, int i, Bundle bundle) {
            if (super.mo437a(view, i, bundle)) {
                return true;
            }
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            if (!nestedScrollView.isEnabled()) {
                return false;
            }
            if (i == 4096) {
                int min = Math.min(nestedScrollView.getScrollY() + ((nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), nestedScrollView.getScrollRange());
                if (min == nestedScrollView.getScrollY()) {
                    return false;
                }
                nestedScrollView.mo808b(0, min);
                return true;
            } else if (i != 8192) {
                return false;
            } else {
                int max = Math.max(nestedScrollView.getScrollY() - ((nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), 0);
                if (max == nestedScrollView.getScrollY()) {
                    return false;
                }
                nestedScrollView.mo808b(0, max);
                return true;
            }
        }

        /* renamed from: b */
        public void mo440b(View view, AccessibilityEvent accessibilityEvent) {
            super.mo440b(view, accessibilityEvent);
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            accessibilityEvent.setClassName(ScrollView.class.getName());
            accessibilityEvent.setScrollable(nestedScrollView.getScrollRange() > 0);
            accessibilityEvent.setScrollX(nestedScrollView.getScrollX());
            accessibilityEvent.setScrollY(nestedScrollView.getScrollY());
            C0099c.m372a(accessibilityEvent, nestedScrollView.getScrollX());
            C0099c.m373b(accessibilityEvent, nestedScrollView.getScrollRange());
        }
    }

    /* renamed from: android.support.v4.widget.NestedScrollView$b */
    public interface C0199b {
        /* renamed from: a */
        void mo859a(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4);
    }

    /* renamed from: android.support.v4.widget.NestedScrollView$c */
    static class C0200c extends View.BaseSavedState {
        public static final Parcelable.Creator<C0200c> CREATOR = new C0215j();

        /* renamed from: a */
        public int f507a;

        C0200c(Parcel parcel) {
            super(parcel);
            this.f507a = parcel.readInt();
        }

        C0200c(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.f507a + "}";
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f507a);
        }
    }

    public NestedScrollView(Context context) {
        this(context, (AttributeSet) null);
    }

    public NestedScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NestedScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f484d = new Rect();
        this.f489i = true;
        this.f490j = false;
        this.f491k = null;
        this.f492l = false;
        this.f495o = true;
        this.f499s = -1;
        this.f500t = new int[2];
        this.f501u = new int[2];
        m714e();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f480b, i, 0);
        setFillViewport(obtainStyledAttributes.getBoolean(0, false));
        obtainStyledAttributes.recycle();
        this.f505y = new C0122p(this);
        this.f506z = new C0119m(this);
        setNestedScrollingEnabled(true);
        C0127u.m439a((View) this, (C0106d) f479a);
    }

    /* renamed from: a */
    private static int m700a(int i, int i2, int i3) {
        if (i2 >= i3 || i < 0) {
            return 0;
        }
        return i2 + i > i3 ? i3 - i2 : i;
    }

    /* renamed from: a */
    private View m701a(boolean z, int i, int i2) {
        ArrayList focusables = getFocusables(2);
        int size = focusables.size();
        View view = null;
        boolean z2 = false;
        for (int i3 = 0; i3 < size; i3++) {
            View view2 = (View) focusables.get(i3);
            int top = view2.getTop();
            int bottom = view2.getBottom();
            if (i < bottom && top < i2) {
                boolean z3 = i < top && bottom < i2;
                if (view == null) {
                    view = view2;
                    z2 = z3;
                } else {
                    boolean z4 = (z && top < view.getTop()) || (!z && bottom > view.getBottom());
                    if (z2) {
                        if (z3) {
                            if (!z4) {
                            }
                        }
                    } else if (z3) {
                        view = view2;
                        z2 = true;
                    } else if (!z4) {
                    }
                    view = view2;
                }
            }
        }
        return view;
    }

    /* renamed from: a */
    private void m702a(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.f499s) {
            int i = actionIndex == 0 ? 1 : 0;
            this.f488h = (int) motionEvent.getY(i);
            this.f499s = motionEvent.getPointerId(i);
            VelocityTracker velocityTracker = this.f493m;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    /* renamed from: a */
    private boolean m703a() {
        if (getChildCount() <= 0) {
            return false;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        return (childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin > (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    /* renamed from: a */
    private boolean m704a(Rect rect, boolean z) {
        int a = mo796a(rect);
        boolean z2 = a != 0;
        if (z2) {
            if (z) {
                scrollBy(0, a);
            } else {
                mo797a(0, a);
            }
        }
        return z2;
    }

    /* renamed from: a */
    private boolean m705a(View view) {
        return !m706a(view, 0, getHeight());
    }

    /* renamed from: a */
    private boolean m706a(View view, int i, int i2) {
        view.getDrawingRect(this.f484d);
        offsetDescendantRectToMyCoords(view, this.f484d);
        return this.f484d.bottom + i >= getScrollY() && this.f484d.top - i <= getScrollY() + i2;
    }

    /* renamed from: a */
    private static boolean m707a(View view, View view2) {
        if (view == view2) {
            return true;
        }
        ViewParent parent = view.getParent();
        return (parent instanceof ViewGroup) && m707a((View) parent, view2);
    }

    /* renamed from: b */
    private void m708b() {
        this.f492l = false;
        m716g();
        mo826f(0);
        EdgeEffect edgeEffect = this.f486f;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            this.f487g.onRelease();
        }
    }

    /* renamed from: b */
    private void m709b(View view) {
        view.getDrawingRect(this.f484d);
        offsetDescendantRectToMyCoords(view, this.f484d);
        int a = mo796a(this.f484d);
        if (a != 0) {
            scrollBy(0, a);
        }
    }

    /* renamed from: b */
    private boolean m710b(int i, int i2, int i3) {
        int height = getHeight();
        int scrollY = getScrollY();
        int i4 = height + scrollY;
        boolean z = false;
        boolean z2 = i == 33;
        View a = m701a(z2, i2, i3);
        if (a == null) {
            a = this;
        }
        if (i2 < scrollY || i3 > i4) {
            m717g(z2 ? i2 - scrollY : i3 - i4);
            z = true;
        }
        if (a != findFocus()) {
            a.requestFocus(i);
        }
        return z;
    }

    /* renamed from: c */
    private void m711c() {
        if (getOverScrollMode() == 2) {
            this.f486f = null;
            this.f487g = null;
        } else if (this.f486f == null) {
            Context context = getContext();
            this.f486f = new EdgeEffect(context);
            this.f487g = new EdgeEffect(context);
        }
    }

    /* renamed from: d */
    private void m712d() {
        VelocityTracker velocityTracker = this.f493m;
        if (velocityTracker == null) {
            this.f493m = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
    }

    /* renamed from: d */
    private boolean m713d(int i, int i2) {
        if (getChildCount() <= 0) {
            return false;
        }
        int scrollY = getScrollY();
        View childAt = getChildAt(0);
        return i2 >= childAt.getTop() - scrollY && i2 < childAt.getBottom() - scrollY && i >= childAt.getLeft() && i < childAt.getRight();
    }

    /* renamed from: e */
    private void m714e() {
        this.f485e = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.f496p = viewConfiguration.getScaledTouchSlop();
        this.f497q = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f498r = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    /* renamed from: f */
    private void m715f() {
        if (this.f493m == null) {
            this.f493m = VelocityTracker.obtain();
        }
    }

    /* renamed from: g */
    private void m716g() {
        VelocityTracker velocityTracker = this.f493m;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f493m = null;
        }
    }

    /* renamed from: g */
    private void m717g(int i) {
        if (i == 0) {
            return;
        }
        if (this.f495o) {
            mo797a(0, i);
        } else {
            scrollBy(0, i);
        }
    }

    private float getVerticalScrollFactorCompat() {
        if (this.f481A == 0.0f) {
            TypedValue typedValue = new TypedValue();
            Context context = getContext();
            if (context.getTheme().resolveAttribute(16842829, typedValue, true)) {
                this.f481A = typedValue.getDimension(context.getResources().getDisplayMetrics());
            } else {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
        }
        return this.f481A;
    }

    /* renamed from: h */
    private void m718h(int i) {
        int scrollY = getScrollY();
        boolean z = (scrollY > 0 || i > 0) && (scrollY < getScrollRange() || i < 0);
        float f = (float) i;
        if (!dispatchNestedPreFling(0.0f, f)) {
            dispatchNestedFling(0.0f, f, z);
            mo807b(i);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public int mo796a(Rect rect) {
        if (getChildCount() == 0) {
            return 0;
        }
        int height = getHeight();
        int scrollY = getScrollY();
        int i = scrollY + height;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            scrollY += verticalFadingEdgeLength;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        int i2 = rect.bottom < (childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin ? i - verticalFadingEdgeLength : i;
        if (rect.bottom > i2 && rect.top > scrollY) {
            return Math.min((rect.height() > height ? rect.top - scrollY : rect.bottom - i2) + 0, (childAt.getBottom() + layoutParams.bottomMargin) - i);
        } else if (rect.top >= scrollY || rect.bottom >= i2) {
            return 0;
        } else {
            return Math.max(rect.height() > height ? 0 - (i2 - rect.bottom) : 0 - (scrollY - rect.top), -getScrollY());
        }
    }

    /* renamed from: a */
    public final void mo797a(int i, int i2) {
        if (getChildCount() != 0) {
            if (AnimationUtils.currentAnimationTimeMillis() - this.f483c > 250) {
                View childAt = getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                int height = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int scrollY = getScrollY();
                this.f503w = getScrollY();
                OverScroller overScroller = this.f485e;
                int scrollX = getScrollX();
                overScroller.startScroll(scrollX, scrollY, 0, Math.max(0, Math.min(i2 + scrollY, Math.max(0, height - height2))) - scrollY);
                C0127u.m456j(this);
            } else {
                if (!this.f485e.isFinished()) {
                    this.f485e.abortAnimation();
                }
                scrollBy(i, i2);
            }
            this.f483c = AnimationUtils.currentAnimationTimeMillis();
        }
    }

    /* renamed from: a */
    public void mo474a(View view, int i) {
        this.f505y.mo487a(view, i);
        mo826f(i);
    }

    /* renamed from: a */
    public void mo475a(View view, int i, int i2, int i3, int i4, int i5) {
        int scrollY = getScrollY();
        scrollBy(0, i4);
        int scrollY2 = getScrollY() - scrollY;
        mo800a(0, scrollY2, 0, i4 - scrollY2, (int[]) null, i5);
    }

    /* renamed from: a */
    public void mo476a(View view, int i, int i2, int[] iArr, int i3) {
        mo801a(i, i2, iArr, (int[]) null, i3);
    }

    /* renamed from: a */
    public boolean mo798a(int i) {
        View findFocus = findFocus();
        if (findFocus == this) {
            findFocus = null;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, i);
        int maxScrollAmount = getMaxScrollAmount();
        if (findNextFocus == null || !m706a(findNextFocus, maxScrollAmount, getHeight())) {
            if (i == 33 && getScrollY() < maxScrollAmount) {
                maxScrollAmount = getScrollY();
            } else if (i == 130 && getChildCount() > 0) {
                View childAt = getChildAt(0);
                maxScrollAmount = Math.min((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - ((getScrollY() + getHeight()) - getPaddingBottom()), maxScrollAmount);
            }
            if (maxScrollAmount == 0) {
                return false;
            }
            if (i != 130) {
                maxScrollAmount = -maxScrollAmount;
            }
            m717g(maxScrollAmount);
        } else {
            findNextFocus.getDrawingRect(this.f484d);
            offsetDescendantRectToMyCoords(findNextFocus, this.f484d);
            m717g(mo796a(this.f484d));
            findNextFocus.requestFocus(i);
        }
        if (findFocus == null || !findFocus.isFocused() || !m705a(findFocus)) {
            return true;
        }
        int descendantFocusability = getDescendantFocusability();
        setDescendantFocusability(131072);
        requestFocus();
        setDescendantFocusability(descendantFocusability);
        return true;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0083 A[ADDED_TO_REGION] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo799a(int r13, int r14, int r15, int r16, int r17, int r18, int r19, int r20, boolean r21) {
        /*
            r12 = this;
            r0 = r12
            int r1 = r12.getOverScrollMode()
            int r2 = r12.computeHorizontalScrollRange()
            int r3 = r12.computeHorizontalScrollExtent()
            r4 = 0
            r5 = 1
            if (r2 <= r3) goto L_0x0013
            r2 = 1
            goto L_0x0014
        L_0x0013:
            r2 = 0
        L_0x0014:
            int r3 = r12.computeVerticalScrollRange()
            int r6 = r12.computeVerticalScrollExtent()
            if (r3 <= r6) goto L_0x0020
            r3 = 1
            goto L_0x0021
        L_0x0020:
            r3 = 0
        L_0x0021:
            if (r1 == 0) goto L_0x002a
            if (r1 != r5) goto L_0x0028
            if (r2 == 0) goto L_0x0028
            goto L_0x002a
        L_0x0028:
            r2 = 0
            goto L_0x002b
        L_0x002a:
            r2 = 1
        L_0x002b:
            if (r1 == 0) goto L_0x0034
            if (r1 != r5) goto L_0x0032
            if (r3 == 0) goto L_0x0032
            goto L_0x0034
        L_0x0032:
            r1 = 0
            goto L_0x0035
        L_0x0034:
            r1 = 1
        L_0x0035:
            int r3 = r15 + r13
            if (r2 != 0) goto L_0x003b
            r2 = 0
            goto L_0x003d
        L_0x003b:
            r2 = r19
        L_0x003d:
            int r6 = r16 + r14
            if (r1 != 0) goto L_0x0043
            r1 = 0
            goto L_0x0045
        L_0x0043:
            r1 = r20
        L_0x0045:
            int r7 = -r2
            int r2 = r2 + r17
            int r8 = -r1
            int r1 = r1 + r18
            if (r3 <= r2) goto L_0x0050
            r7 = r2
        L_0x004e:
            r2 = 1
            goto L_0x0055
        L_0x0050:
            if (r3 >= r7) goto L_0x0053
            goto L_0x004e
        L_0x0053:
            r7 = r3
            r2 = 0
        L_0x0055:
            if (r6 <= r1) goto L_0x005a
            r6 = r1
        L_0x0058:
            r1 = 1
            goto L_0x005f
        L_0x005a:
            if (r6 >= r8) goto L_0x005e
            r6 = r8
            goto L_0x0058
        L_0x005e:
            r1 = 0
        L_0x005f:
            if (r1 == 0) goto L_0x007e
            boolean r3 = r12.mo818d(r5)
            if (r3 != 0) goto L_0x007e
            android.widget.OverScroller r3 = r0.f485e
            r8 = 0
            r9 = 0
            r10 = 0
            int r11 = r12.getScrollRange()
            r13 = r3
            r14 = r7
            r15 = r6
            r16 = r8
            r17 = r9
            r18 = r10
            r19 = r11
            r13.springBack(r14, r15, r16, r17, r18, r19)
        L_0x007e:
            r12.onOverScrolled(r7, r6, r2, r1)
            if (r2 != 0) goto L_0x0085
            if (r1 == 0) goto L_0x0086
        L_0x0085:
            r4 = 1
        L_0x0086:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p024v4.widget.NestedScrollView.mo799a(int, int, int, int, int, int, int, int, boolean):boolean");
    }

    /* renamed from: a */
    public boolean mo800a(int i, int i2, int i3, int i4, int[] iArr, int i5) {
        return this.f506z.mo471a(i, i2, i3, i4, iArr, i5);
    }

    /* renamed from: a */
    public boolean mo801a(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        return this.f506z.mo472a(i, i2, iArr, iArr2, i3);
    }

    /* renamed from: a */
    public boolean mo802a(KeyEvent keyEvent) {
        this.f484d.setEmpty();
        int i = 130;
        if (!m703a()) {
            if (!isFocused() || keyEvent.getKeyCode() == 4) {
                return false;
            }
            View findFocus = findFocus();
            if (findFocus == this) {
                findFocus = null;
            }
            View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus, 130);
            return (findNextFocus == null || findNextFocus == this || !findNextFocus.requestFocus(130)) ? false : true;
        } else if (keyEvent.getAction() != 0) {
            return false;
        } else {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode == 19) {
                return !keyEvent.isAltPressed() ? mo798a(33) : mo809c(33);
            }
            if (keyCode == 20) {
                return !keyEvent.isAltPressed() ? mo798a(130) : mo809c(130);
            }
            if (keyCode != 62) {
                return false;
            }
            if (keyEvent.isShiftPressed()) {
                i = 33;
            }
            mo825e(i);
            return false;
        }
    }

    /* renamed from: a */
    public boolean mo477a(View view, View view2, int i, int i2) {
        return (i & 2) != 0;
    }

    public void addView(View view) {
        if (getChildCount() <= 0) {
            super.addView(view);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public void addView(View view, int i) {
        if (getChildCount() <= 0) {
            super.addView(view, i);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, i, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    /* renamed from: b */
    public void mo807b(int i) {
        if (getChildCount() > 0) {
            mo810c(2, 1);
            this.f485e.fling(getScrollX(), getScrollY(), 0, i, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
            this.f503w = getScrollY();
            C0127u.m456j(this);
        }
    }

    /* renamed from: b */
    public final void mo808b(int i, int i2) {
        mo797a(i - getScrollX(), i2 - getScrollY());
    }

    /* renamed from: b */
    public void mo478b(View view, View view2, int i, int i2) {
        this.f505y.mo489a(view, view2, i, i2);
        mo810c(2, i2);
    }

    /* renamed from: c */
    public boolean mo809c(int i) {
        int childCount;
        boolean z = i == 130;
        int height = getHeight();
        Rect rect = this.f484d;
        rect.top = 0;
        rect.bottom = height;
        if (z && (childCount = getChildCount()) > 0) {
            View childAt = getChildAt(childCount - 1);
            this.f484d.bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin + getPaddingBottom();
            Rect rect2 = this.f484d;
            rect2.top = rect2.bottom - height;
        }
        Rect rect3 = this.f484d;
        return m710b(i, rect3.top, rect3.bottom);
    }

    /* renamed from: c */
    public boolean mo810c(int i, int i2) {
        return this.f506z.mo470a(i, i2);
    }

    public int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    public int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    public int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    public void computeScroll() {
        EdgeEffect edgeEffect;
        if (this.f485e.computeScrollOffset()) {
            this.f485e.getCurrX();
            int currY = this.f485e.getCurrY();
            int i = currY - this.f503w;
            if (mo801a(0, i, this.f501u, (int[]) null, 1)) {
                i -= this.f501u[1];
            }
            int i2 = i;
            if (i2 != 0) {
                int scrollRange = getScrollRange();
                int scrollY = getScrollY();
                int i3 = scrollY;
                mo799a(0, i2, getScrollX(), scrollY, 0, scrollRange, 0, 0, false);
                int scrollY2 = getScrollY() - i3;
                if (!mo800a(0, scrollY2, 0, i2 - scrollY2, (int[]) null, 1)) {
                    int overScrollMode = getOverScrollMode();
                    if (overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0)) {
                        m711c();
                        if (currY <= 0 && i3 > 0) {
                            edgeEffect = this.f486f;
                        } else if (currY >= scrollRange && i3 < scrollRange) {
                            edgeEffect = this.f487g;
                        }
                        edgeEffect.onAbsorb((int) this.f485e.getCurrVelocity());
                    }
                }
            }
            this.f503w = currY;
            C0127u.m456j(this);
            return;
        }
        if (mo818d(1)) {
            mo826f(1);
        }
        this.f503w = 0;
    }

    public int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    public int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    public int computeVerticalScrollRange() {
        int childCount = getChildCount();
        int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
        if (childCount == 0) {
            return height;
        }
        View childAt = getChildAt(0);
        int bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
        int scrollY = getScrollY();
        int max = Math.max(0, bottom - height);
        return scrollY < 0 ? bottom - scrollY : scrollY > max ? bottom + (scrollY - max) : bottom;
    }

    /* renamed from: d */
    public boolean mo818d(int i) {
        return this.f506z.mo469a(i);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || mo802a(keyEvent);
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.f506z.mo468a(f, f2, z);
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.f506z.mo467a(f, f2);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return mo801a(i, i2, iArr, iArr2, 0);
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return mo800a(i, i2, i3, i4, iArr, 0);
    }

    public void draw(Canvas canvas) {
        int i;
        super.draw(canvas);
        if (this.f486f != null) {
            int scrollY = getScrollY();
            int i2 = 0;
            if (!this.f486f.isFinished()) {
                int save = canvas.save();
                int width = getWidth();
                int height = getHeight();
                int min = Math.min(0, scrollY);
                if (Build.VERSION.SDK_INT < 21 || getClipToPadding()) {
                    width -= getPaddingLeft() + getPaddingRight();
                    i = getPaddingLeft() + 0;
                } else {
                    i = 0;
                }
                if (Build.VERSION.SDK_INT >= 21 && getClipToPadding()) {
                    height -= getPaddingTop() + getPaddingBottom();
                    min += getPaddingTop();
                }
                canvas.translate((float) i, (float) min);
                this.f486f.setSize(width, height);
                if (this.f486f.draw(canvas)) {
                    C0127u.m456j(this);
                }
                canvas.restoreToCount(save);
            }
            if (!this.f487g.isFinished()) {
                int save2 = canvas.save();
                int width2 = getWidth();
                int height2 = getHeight();
                int max = Math.max(getScrollRange(), scrollY) + height2;
                if (Build.VERSION.SDK_INT < 21 || getClipToPadding()) {
                    width2 -= getPaddingLeft() + getPaddingRight();
                    i2 = 0 + getPaddingLeft();
                }
                if (Build.VERSION.SDK_INT >= 21 && getClipToPadding()) {
                    height2 -= getPaddingTop() + getPaddingBottom();
                    max -= getPaddingBottom();
                }
                canvas.translate((float) (i2 - width2), (float) max);
                canvas.rotate(180.0f, (float) width2, 0.0f);
                this.f487g.setSize(width2, height2);
                if (this.f487g.draw(canvas)) {
                    C0127u.m456j(this);
                }
                canvas.restoreToCount(save2);
            }
        }
    }

    /* renamed from: e */
    public boolean mo825e(int i) {
        boolean z = i == 130;
        int height = getHeight();
        if (z) {
            this.f484d.top = getScrollY() + height;
            int childCount = getChildCount();
            if (childCount > 0) {
                View childAt = getChildAt(childCount - 1);
                int bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin + getPaddingBottom();
                Rect rect = this.f484d;
                if (rect.top + height > bottom) {
                    rect.top = bottom - height;
                }
            }
        } else {
            this.f484d.top = getScrollY() - height;
            Rect rect2 = this.f484d;
            if (rect2.top < 0) {
                rect2.top = 0;
            }
        }
        Rect rect3 = this.f484d;
        int i2 = rect3.top;
        rect3.bottom = height + i2;
        return m710b(i, i2, rect3.bottom);
    }

    /* renamed from: f */
    public void mo826f(int i) {
        this.f506z.mo473b(i);
    }

    /* access modifiers changed from: protected */
    public float getBottomFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        View childAt = getChildAt(0);
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int bottom = ((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - getScrollY()) - (getHeight() - getPaddingBottom());
        if (bottom < verticalFadingEdgeLength) {
            return ((float) bottom) / ((float) verticalFadingEdgeLength);
        }
        return 1.0f;
    }

    public int getMaxScrollAmount() {
        return (int) (((float) getHeight()) * 0.5f);
    }

    public int getNestedScrollAxes() {
        return this.f505y.mo486a();
    }

    /* access modifiers changed from: package-private */
    public int getScrollRange() {
        if (getChildCount() <= 0) {
            return 0;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        return Math.max(0, ((childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin) - ((getHeight() - getPaddingTop()) - getPaddingBottom()));
    }

    /* access modifiers changed from: protected */
    public float getTopFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int scrollY = getScrollY();
        if (scrollY < verticalFadingEdgeLength) {
            return ((float) scrollY) / ((float) verticalFadingEdgeLength);
        }
        return 1.0f;
    }

    public boolean hasNestedScrollingParent() {
        return mo818d(0);
    }

    public boolean isNestedScrollingEnabled() {
        return this.f506z.mo466a();
    }

    /* access modifiers changed from: protected */
    public void measureChild(View view, int i, int i2) {
        view.measure(FrameLayout.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight(), view.getLayoutParams().width), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    /* access modifiers changed from: protected */
    public void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(FrameLayout.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, 0));
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f490j = false;
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getAction() == 8 && !this.f492l) {
            float axisValue = motionEvent.getAxisValue(9);
            if (axisValue != 0.0f) {
                int scrollRange = getScrollRange();
                int scrollY = getScrollY();
                int verticalScrollFactorCompat = scrollY - ((int) (axisValue * getVerticalScrollFactorCompat()));
                if (verticalScrollFactorCompat < 0) {
                    verticalScrollFactorCompat = 0;
                } else if (verticalScrollFactorCompat > scrollRange) {
                    verticalScrollFactorCompat = scrollRange;
                }
                if (verticalScrollFactorCompat != scrollY) {
                    super.scrollTo(getScrollX(), verticalScrollFactorCompat);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 2 && this.f492l) {
            return true;
        }
        int i = action & 255;
        if (i != 0) {
            if (i != 1) {
                if (i == 2) {
                    int i2 = this.f499s;
                    if (i2 != -1) {
                        int findPointerIndex = motionEvent.findPointerIndex(i2);
                        if (findPointerIndex == -1) {
                            Log.e("NestedScrollView", "Invalid pointerId=" + i2 + " in onInterceptTouchEvent");
                        } else {
                            int y = (int) motionEvent.getY(findPointerIndex);
                            if (Math.abs(y - this.f488h) > this.f496p && (2 & getNestedScrollAxes()) == 0) {
                                this.f492l = true;
                                this.f488h = y;
                                m715f();
                                this.f493m.addMovement(motionEvent);
                                this.f502v = 0;
                                ViewParent parent = getParent();
                                if (parent != null) {
                                    parent.requestDisallowInterceptTouchEvent(true);
                                }
                            }
                        }
                    }
                } else if (i != 3) {
                    if (i == 6) {
                        m702a(motionEvent);
                    }
                }
            }
            this.f492l = false;
            this.f499s = -1;
            m716g();
            if (this.f485e.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                C0127u.m456j(this);
            }
            mo826f(0);
        } else {
            int y2 = (int) motionEvent.getY();
            if (!m713d((int) motionEvent.getX(), y2)) {
                this.f492l = false;
                m716g();
            } else {
                this.f488h = y2;
                this.f499s = motionEvent.getPointerId(0);
                m712d();
                this.f493m.addMovement(motionEvent);
                this.f485e.computeScrollOffset();
                this.f492l = !this.f485e.isFinished();
                mo810c(2, 0);
            }
        }
        return this.f492l;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int i5 = 0;
        this.f489i = false;
        View view = this.f491k;
        if (view != null && m707a(view, (View) this)) {
            m709b(this.f491k);
        }
        this.f491k = null;
        if (!this.f490j) {
            if (this.f504x != null) {
                scrollTo(getScrollX(), this.f504x.f507a);
                this.f504x = null;
            }
            if (getChildCount() > 0) {
                View childAt = getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                i5 = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            }
            int paddingTop = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            int a = m700a(scrollY, paddingTop, i5);
            if (a != scrollY) {
                scrollTo(getScrollX(), a);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.f490j = true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.f494n && View.MeasureSpec.getMode(i2) != 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredHeight2 = (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - layoutParams.topMargin) - layoutParams.bottomMargin;
            if (measuredHeight < measuredHeight2) {
                childAt.measure(FrameLayout.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width), View.MeasureSpec.makeMeasureSpec(measuredHeight2, 1073741824));
            }
        }
    }

    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (z) {
            return false;
        }
        m718h((int) f2);
        return true;
    }

    public boolean onNestedPreFling(View view, float f, float f2) {
        return dispatchNestedPreFling(f, f2);
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        mo476a(view, i, i2, iArr, 0);
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        mo475a(view, i, i2, i3, i4, 0);
    }

    public void onNestedScrollAccepted(View view, View view2, int i) {
        mo478b(view, view2, i, 0);
    }

    /* access modifiers changed from: protected */
    public void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        super.scrollTo(i, i2);
    }

    /* access modifiers changed from: protected */
    public boolean onRequestFocusInDescendants(int i, Rect rect) {
        if (i == 2) {
            i = 130;
        } else if (i == 1) {
            i = 33;
        }
        View findNextFocus = rect == null ? FocusFinder.getInstance().findNextFocus(this, (View) null, i) : FocusFinder.getInstance().findNextFocusFromRect(this, rect, i);
        if (findNextFocus != null && !m705a(findNextFocus)) {
            return findNextFocus.requestFocus(i, rect);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof C0200c)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        C0200c cVar = (C0200c) parcelable;
        super.onRestoreInstanceState(cVar.getSuperState());
        this.f504x = cVar;
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        C0200c cVar = new C0200c(super.onSaveInstanceState());
        cVar.f507a = getScrollY();
        return cVar;
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        C0199b bVar = this.f482B;
        if (bVar != null) {
            bVar.mo859a(this, i, i2, i3, i4);
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        View findFocus = findFocus();
        if (findFocus != null && this != findFocus && m706a(findFocus, 0, i4)) {
            findFocus.getDrawingRect(this.f484d);
            offsetDescendantRectToMyCoords(findFocus, this.f484d);
            m717g(mo796a(this.f484d));
        }
    }

    public boolean onStartNestedScroll(View view, View view2, int i) {
        return mo477a(view, view2, i, 0);
    }

    public void onStopNestedScroll(View view) {
        mo474a(view, 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0073, code lost:
        if (r10.f485e.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange()) != false) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0218, code lost:
        if (r10.f485e.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange()) != false) goto L_0x0075;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r24) {
        /*
            r23 = this;
            r10 = r23
            r11 = r24
            r23.m715f()
            android.view.MotionEvent r12 = android.view.MotionEvent.obtain(r24)
            int r0 = r24.getActionMasked()
            r13 = 0
            if (r0 != 0) goto L_0x0014
            r10.f502v = r13
        L_0x0014:
            int r1 = r10.f502v
            float r1 = (float) r1
            r14 = 0
            r12.offsetLocation(r14, r1)
            r1 = 2
            r15 = 1
            if (r0 == 0) goto L_0x021c
            r2 = -1
            if (r0 == r15) goto L_0x01e4
            if (r0 == r1) goto L_0x007f
            r1 = 3
            if (r0 == r1) goto L_0x0054
            r1 = 5
            if (r0 == r1) goto L_0x0041
            r1 = 6
            if (r0 == r1) goto L_0x002f
            goto L_0x0254
        L_0x002f:
            r23.m702a((android.view.MotionEvent) r24)
            int r0 = r10.f499s
            int r0 = r11.findPointerIndex(r0)
            float r0 = r11.getY(r0)
            int r0 = (int) r0
            r10.f488h = r0
            goto L_0x0254
        L_0x0041:
            int r0 = r24.getActionIndex()
            float r1 = r11.getY(r0)
            int r1 = (int) r1
            r10.f488h = r1
            int r0 = r11.getPointerId(r0)
            r10.f499s = r0
            goto L_0x0254
        L_0x0054:
            boolean r0 = r10.f492l
            if (r0 == 0) goto L_0x0078
            int r0 = r23.getChildCount()
            if (r0 <= 0) goto L_0x0078
            android.widget.OverScroller r3 = r10.f485e
            int r4 = r23.getScrollX()
            int r5 = r23.getScrollY()
            r6 = 0
            r7 = 0
            r8 = 0
            int r9 = r23.getScrollRange()
            boolean r0 = r3.springBack(r4, r5, r6, r7, r8, r9)
            if (r0 == 0) goto L_0x0078
        L_0x0075:
            p000a.p001a.p005c.p014g.C0127u.m456j(r23)
        L_0x0078:
            r10.f499s = r2
            r23.m708b()
            goto L_0x0254
        L_0x007f:
            int r0 = r10.f499s
            int r9 = r11.findPointerIndex(r0)
            if (r9 != r2) goto L_0x00a6
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Invalid pointerId="
            r0.append(r1)
            int r1 = r10.f499s
            r0.append(r1)
            java.lang.String r1 = " in onTouchEvent"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "NestedScrollView"
            android.util.Log.e(r1, r0)
            goto L_0x0254
        L_0x00a6:
            float r0 = r11.getY(r9)
            int r6 = (int) r0
            int r0 = r10.f488h
            int r7 = r0 - r6
            r1 = 0
            int[] r3 = r10.f501u
            int[] r4 = r10.f500t
            r5 = 0
            r0 = r23
            r2 = r7
            boolean r0 = r0.mo801a((int) r1, (int) r2, (int[]) r3, (int[]) r4, (int) r5)
            if (r0 == 0) goto L_0x00d4
            int[] r0 = r10.f501u
            r0 = r0[r15]
            int r7 = r7 - r0
            int[] r0 = r10.f500t
            r0 = r0[r15]
            float r0 = (float) r0
            r12.offsetLocation(r14, r0)
            int r0 = r10.f502v
            int[] r1 = r10.f500t
            r1 = r1[r15]
            int r0 = r0 + r1
            r10.f502v = r0
        L_0x00d4:
            boolean r0 = r10.f492l
            if (r0 != 0) goto L_0x00f4
            int r0 = java.lang.Math.abs(r7)
            int r1 = r10.f496p
            if (r0 <= r1) goto L_0x00f4
            android.view.ViewParent r0 = r23.getParent()
            if (r0 == 0) goto L_0x00e9
            r0.requestDisallowInterceptTouchEvent(r15)
        L_0x00e9:
            r10.f492l = r15
            if (r7 <= 0) goto L_0x00f1
            int r0 = r10.f496p
            int r7 = r7 - r0
            goto L_0x00f4
        L_0x00f1:
            int r0 = r10.f496p
            int r7 = r7 + r0
        L_0x00f4:
            r8 = r7
            boolean r0 = r10.f492l
            if (r0 == 0) goto L_0x0254
            int[] r0 = r10.f500t
            r0 = r0[r15]
            int r6 = r6 - r0
            r10.f488h = r6
            int r16 = r23.getScrollY()
            int r7 = r23.getScrollRange()
            int r0 = r23.getOverScrollMode()
            if (r0 == 0) goto L_0x0116
            if (r0 != r15) goto L_0x0113
            if (r7 <= 0) goto L_0x0113
            goto L_0x0116
        L_0x0113:
            r17 = 0
            goto L_0x0118
        L_0x0116:
            r17 = 1
        L_0x0118:
            r1 = 0
            r3 = 0
            int r4 = r23.getScrollY()
            r5 = 0
            r18 = 0
            r19 = 0
            r20 = 1
            r0 = r23
            r2 = r8
            r6 = r7
            r21 = r7
            r7 = r18
            r14 = r8
            r8 = r19
            r22 = r9
            r9 = r20
            boolean r0 = r0.mo799a(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            if (r0 == 0) goto L_0x0145
            boolean r0 = r10.mo818d(r13)
            if (r0 != 0) goto L_0x0145
            android.view.VelocityTracker r0 = r10.f493m
            r0.clear()
        L_0x0145:
            int r0 = r23.getScrollY()
            int r2 = r0 - r16
            int r4 = r14 - r2
            r1 = 0
            r3 = 0
            int[] r5 = r10.f500t
            r6 = 0
            r0 = r23
            boolean r0 = r0.mo800a((int) r1, (int) r2, (int) r3, (int) r4, (int[]) r5, (int) r6)
            if (r0 == 0) goto L_0x0175
            int r0 = r10.f488h
            int[] r1 = r10.f500t
            r2 = r1[r15]
            int r0 = r0 - r2
            r10.f488h = r0
            r0 = r1[r15]
            float r0 = (float) r0
            r1 = 0
            r12.offsetLocation(r1, r0)
            int r0 = r10.f502v
            int[] r1 = r10.f500t
            r1 = r1[r15]
            int r0 = r0 + r1
            r10.f502v = r0
            goto L_0x0254
        L_0x0175:
            if (r17 == 0) goto L_0x0254
            r23.m711c()
            int r0 = r16 + r14
            if (r0 >= 0) goto L_0x01a4
            android.widget.EdgeEffect r0 = r10.f486f
            float r1 = (float) r14
            int r2 = r23.getHeight()
            float r2 = (float) r2
            float r1 = r1 / r2
            r2 = r22
            float r2 = r11.getX(r2)
            int r3 = r23.getWidth()
            float r3 = (float) r3
            float r2 = r2 / r3
            android.support.p024v4.widget.C0211f.m791a(r0, r1, r2)
            android.widget.EdgeEffect r0 = r10.f487g
            boolean r0 = r0.isFinished()
            if (r0 != 0) goto L_0x01ce
            android.widget.EdgeEffect r0 = r10.f487g
        L_0x01a0:
            r0.onRelease()
            goto L_0x01ce
        L_0x01a4:
            r1 = r21
            r2 = r22
            if (r0 <= r1) goto L_0x01ce
            android.widget.EdgeEffect r0 = r10.f487g
            float r1 = (float) r14
            int r3 = r23.getHeight()
            float r3 = (float) r3
            float r1 = r1 / r3
            r3 = 1065353216(0x3f800000, float:1.0)
            float r2 = r11.getX(r2)
            int r4 = r23.getWidth()
            float r4 = (float) r4
            float r2 = r2 / r4
            float r3 = r3 - r2
            android.support.p024v4.widget.C0211f.m791a(r0, r1, r3)
            android.widget.EdgeEffect r0 = r10.f486f
            boolean r0 = r0.isFinished()
            if (r0 != 0) goto L_0x01ce
            android.widget.EdgeEffect r0 = r10.f486f
            goto L_0x01a0
        L_0x01ce:
            android.widget.EdgeEffect r0 = r10.f486f
            if (r0 == 0) goto L_0x0254
            boolean r0 = r0.isFinished()
            if (r0 == 0) goto L_0x01e0
            android.widget.EdgeEffect r0 = r10.f487g
            boolean r0 = r0.isFinished()
            if (r0 != 0) goto L_0x0254
        L_0x01e0:
            p000a.p001a.p005c.p014g.C0127u.m456j(r23)
            goto L_0x0254
        L_0x01e4:
            android.view.VelocityTracker r0 = r10.f493m
            r1 = 1000(0x3e8, float:1.401E-42)
            int r3 = r10.f498r
            float r3 = (float) r3
            r0.computeCurrentVelocity(r1, r3)
            int r1 = r10.f499s
            float r0 = r0.getYVelocity(r1)
            int r0 = (int) r0
            int r1 = java.lang.Math.abs(r0)
            int r3 = r10.f497q
            if (r1 <= r3) goto L_0x0203
            int r0 = -r0
            r10.m718h(r0)
            goto L_0x0078
        L_0x0203:
            android.widget.OverScroller r3 = r10.f485e
            int r4 = r23.getScrollX()
            int r5 = r23.getScrollY()
            r6 = 0
            r7 = 0
            r8 = 0
            int r9 = r23.getScrollRange()
            boolean r0 = r3.springBack(r4, r5, r6, r7, r8, r9)
            if (r0 == 0) goto L_0x0078
            goto L_0x0075
        L_0x021c:
            int r0 = r23.getChildCount()
            if (r0 != 0) goto L_0x0223
            return r13
        L_0x0223:
            android.widget.OverScroller r0 = r10.f485e
            boolean r0 = r0.isFinished()
            r0 = r0 ^ r15
            r10.f492l = r0
            if (r0 == 0) goto L_0x0237
            android.view.ViewParent r0 = r23.getParent()
            if (r0 == 0) goto L_0x0237
            r0.requestDisallowInterceptTouchEvent(r15)
        L_0x0237:
            android.widget.OverScroller r0 = r10.f485e
            boolean r0 = r0.isFinished()
            if (r0 != 0) goto L_0x0244
            android.widget.OverScroller r0 = r10.f485e
            r0.abortAnimation()
        L_0x0244:
            float r0 = r24.getY()
            int r0 = (int) r0
            r10.f488h = r0
            int r0 = r11.getPointerId(r13)
            r10.f499s = r0
            r10.mo810c(r1, r13)
        L_0x0254:
            android.view.VelocityTracker r0 = r10.f493m
            if (r0 == 0) goto L_0x025b
            r0.addMovement(r12)
        L_0x025b:
            r12.recycle()
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p024v4.widget.NestedScrollView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void requestChildFocus(View view, View view2) {
        if (!this.f489i) {
            m709b(view2);
        } else {
            this.f491k = view2;
        }
        super.requestChildFocus(view, view2);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        return m704a(rect, z);
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (z) {
            m716g();
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public void requestLayout() {
        this.f489i = true;
        super.requestLayout();
    }

    public void scrollTo(int i, int i2) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int a = m700a(i, (getWidth() - getPaddingLeft()) - getPaddingRight(), childAt.getWidth() + layoutParams.leftMargin + layoutParams.rightMargin);
            int a2 = m700a(i2, (getHeight() - getPaddingTop()) - getPaddingBottom(), childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin);
            if (a != getScrollX() || a2 != getScrollY()) {
                super.scrollTo(a, a2);
            }
        }
    }

    public void setFillViewport(boolean z) {
        if (z != this.f494n) {
            this.f494n = z;
            requestLayout();
        }
    }

    public void setNestedScrollingEnabled(boolean z) {
        this.f506z.mo465a(z);
    }

    public void setOnScrollChangeListener(C0199b bVar) {
        this.f482B = bVar;
    }

    public void setSmoothScrollingEnabled(boolean z) {
        this.f495o = z;
    }

    public boolean shouldDelayChildPressedState() {
        return true;
    }

    public boolean startNestedScroll(int i) {
        return mo810c(i, 0);
    }

    public void stopNestedScroll() {
        mo826f(0);
    }
}

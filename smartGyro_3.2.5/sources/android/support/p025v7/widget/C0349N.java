package android.support.p025v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.p024v4.graphics.drawable.C0190a;
import android.support.p024v4.widget.C0213h;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.lang.reflect.Field;
import p000a.p001a.p005c.p014g.C0134z;
import p000a.p001a.p017d.p018a.C0136a;
import p000a.p001a.p017d.p021c.p022a.C0160e;

/* renamed from: android.support.v7.widget.N */
class C0349N extends ListView {

    /* renamed from: a */
    private final Rect f1259a = new Rect();

    /* renamed from: b */
    private int f1260b = 0;

    /* renamed from: c */
    private int f1261c = 0;

    /* renamed from: d */
    private int f1262d = 0;

    /* renamed from: e */
    private int f1263e = 0;

    /* renamed from: f */
    private int f1264f;

    /* renamed from: g */
    private Field f1265g;

    /* renamed from: h */
    private C0350a f1266h;

    /* renamed from: i */
    private boolean f1267i;

    /* renamed from: j */
    private boolean f1268j;

    /* renamed from: k */
    private boolean f1269k;

    /* renamed from: l */
    private C0134z f1270l;

    /* renamed from: m */
    private C0213h f1271m;

    /* renamed from: n */
    C0351b f1272n;

    /* renamed from: android.support.v7.widget.N$a */
    private static class C0350a extends C0160e {

        /* renamed from: b */
        private boolean f1273b = true;

        C0350a(Drawable drawable) {
            super(drawable);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo1790a(boolean z) {
            this.f1273b = z;
        }

        public void draw(Canvas canvas) {
            if (this.f1273b) {
                super.draw(canvas);
            }
        }

        public void setHotspot(float f, float f2) {
            if (this.f1273b) {
                super.setHotspot(f, f2);
            }
        }

        public void setHotspotBounds(int i, int i2, int i3, int i4) {
            if (this.f1273b) {
                super.setHotspotBounds(i, i2, i3, i4);
            }
        }

        public boolean setState(int[] iArr) {
            if (this.f1273b) {
                return super.setState(iArr);
            }
            return false;
        }

        public boolean setVisible(boolean z, boolean z2) {
            if (this.f1273b) {
                return super.setVisible(z, z2);
            }
            return false;
        }
    }

    /* renamed from: android.support.v7.widget.N$b */
    private class C0351b implements Runnable {
        C0351b() {
        }

        /* renamed from: a */
        public void mo1791a() {
            C0349N n = C0349N.this;
            n.f1272n = null;
            n.removeCallbacks(this);
        }

        /* renamed from: b */
        public void mo1792b() {
            C0349N.this.post(this);
        }

        public void run() {
            C0349N n = C0349N.this;
            n.f1272n = null;
            n.drawableStateChanged();
        }
    }

    C0349N(Context context, boolean z) {
        super(context, (AttributeSet) null, C0136a.dropDownListViewStyle);
        this.f1268j = z;
        setCacheColorHint(0);
        try {
            this.f1265g = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.f1265g.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m1563a() {
        this.f1269k = false;
        setPressed(false);
        drawableStateChanged();
        View childAt = getChildAt(this.f1264f - getFirstVisiblePosition());
        if (childAt != null) {
            childAt.setPressed(false);
        }
        C0134z zVar = this.f1270l;
        if (zVar != null) {
            zVar.mo508a();
            this.f1270l = null;
        }
    }

    /* renamed from: a */
    private void m1564a(int i, View view) {
        Rect rect = this.f1259a;
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        rect.left -= this.f1260b;
        rect.top -= this.f1261c;
        rect.right += this.f1262d;
        rect.bottom += this.f1263e;
        try {
            boolean z = this.f1265g.getBoolean(this);
            if (view.isEnabled() != z) {
                this.f1265g.set(this, Boolean.valueOf(!z));
                if (i != -1) {
                    refreshDrawableState();
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m1565a(int i, View view, float f, float f2) {
        m1569b(i, view);
        Drawable selector = getSelector();
        if (selector != null && i != -1) {
            C0190a.m672a(selector, f, f2);
        }
    }

    /* renamed from: a */
    private void m1566a(Canvas canvas) {
        Drawable selector;
        if (!this.f1259a.isEmpty() && (selector = getSelector()) != null) {
            selector.setBounds(this.f1259a);
            selector.draw(canvas);
        }
    }

    /* renamed from: a */
    private void m1567a(View view, int i) {
        performItemClick(view, i, getItemIdAtPosition(i));
    }

    /* renamed from: a */
    private void m1568a(View view, int i, float f, float f2) {
        View childAt;
        this.f1269k = true;
        if (Build.VERSION.SDK_INT >= 21) {
            drawableHotspotChanged(f, f2);
        }
        if (!isPressed()) {
            setPressed(true);
        }
        layoutChildren();
        int i2 = this.f1264f;
        if (!(i2 == -1 || (childAt = getChildAt(i2 - getFirstVisiblePosition())) == null || childAt == view || !childAt.isPressed())) {
            childAt.setPressed(false);
        }
        this.f1264f = i;
        float left = f - ((float) view.getLeft());
        float top = f2 - ((float) view.getTop());
        if (Build.VERSION.SDK_INT >= 21) {
            view.drawableHotspotChanged(left, top);
        }
        if (!view.isPressed()) {
            view.setPressed(true);
        }
        m1565a(i, view, f, f2);
        setSelectorEnabled(false);
        refreshDrawableState();
    }

    /* renamed from: b */
    private void m1569b(int i, View view) {
        Drawable selector = getSelector();
        boolean z = true;
        boolean z2 = (selector == null || i == -1) ? false : true;
        if (z2) {
            selector.setVisible(false, false);
        }
        m1564a(i, view);
        if (z2) {
            Rect rect = this.f1259a;
            float exactCenterX = rect.exactCenterX();
            float exactCenterY = rect.exactCenterY();
            if (getVisibility() != 0) {
                z = false;
            }
            selector.setVisible(z, false);
            C0190a.m672a(selector, exactCenterX, exactCenterY);
        }
    }

    /* renamed from: b */
    private boolean m1570b() {
        return this.f1269k;
    }

    /* renamed from: c */
    private void m1571c() {
        Drawable selector = getSelector();
        if (selector != null && m1570b() && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    private void setSelectorEnabled(boolean z) {
        C0350a aVar = this.f1266h;
        if (aVar != null) {
            aVar.mo1790a(z);
        }
    }

    /* renamed from: a */
    public int mo1777a(int i, int i2, int i3, int i4, int i5) {
        int listPaddingTop = getListPaddingTop();
        int listPaddingBottom = getListPaddingBottom();
        getListPaddingLeft();
        getListPaddingRight();
        int dividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        if (adapter == null) {
            return listPaddingTop + listPaddingBottom;
        }
        int i6 = listPaddingTop + listPaddingBottom;
        if (dividerHeight <= 0 || divider == null) {
            dividerHeight = 0;
        }
        int count = adapter.getCount();
        int i7 = i6;
        View view = null;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        while (i8 < count) {
            int itemViewType = adapter.getItemViewType(i8);
            if (itemViewType != i9) {
                view = null;
                i9 = itemViewType;
            }
            view = adapter.getView(i8, view, this);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            int i11 = layoutParams.height;
            view.measure(i, i11 > 0 ? View.MeasureSpec.makeMeasureSpec(i11, 1073741824) : View.MeasureSpec.makeMeasureSpec(0, 0));
            view.forceLayout();
            if (i8 > 0) {
                i7 += dividerHeight;
            }
            i7 += view.getMeasuredHeight();
            if (i7 >= i4) {
                return (i5 < 0 || i8 <= i5 || i10 <= 0 || i7 == i4) ? i4 : i10;
            }
            if (i5 >= 0 && i8 >= i5) {
                i10 = i7;
            }
            i8++;
        }
        return i7;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000c, code lost:
        if (r0 != 3) goto L_0x000e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x001e  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0065  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo1778a(android.view.MotionEvent r8, int r9) {
        /*
            r7 = this;
            int r0 = r8.getActionMasked()
            r1 = 0
            r2 = 1
            if (r0 == r2) goto L_0x0016
            r3 = 2
            if (r0 == r3) goto L_0x0014
            r9 = 3
            if (r0 == r9) goto L_0x0011
        L_0x000e:
            r9 = 0
            r3 = 1
            goto L_0x0046
        L_0x0011:
            r9 = 0
            r3 = 0
            goto L_0x0046
        L_0x0014:
            r3 = 1
            goto L_0x0017
        L_0x0016:
            r3 = 0
        L_0x0017:
            int r9 = r8.findPointerIndex(r9)
            if (r9 >= 0) goto L_0x001e
            goto L_0x0011
        L_0x001e:
            float r4 = r8.getX(r9)
            int r4 = (int) r4
            float r9 = r8.getY(r9)
            int r9 = (int) r9
            int r5 = r7.pointToPosition(r4, r9)
            r6 = -1
            if (r5 != r6) goto L_0x0031
            r9 = 1
            goto L_0x0046
        L_0x0031:
            int r3 = r7.getFirstVisiblePosition()
            int r3 = r5 - r3
            android.view.View r3 = r7.getChildAt(r3)
            float r4 = (float) r4
            float r9 = (float) r9
            r7.m1568a((android.view.View) r3, (int) r5, (float) r4, (float) r9)
            if (r0 != r2) goto L_0x000e
            r7.m1567a((android.view.View) r3, (int) r5)
            goto L_0x000e
        L_0x0046:
            if (r3 == 0) goto L_0x004a
            if (r9 == 0) goto L_0x004d
        L_0x004a:
            r7.m1563a()
        L_0x004d:
            if (r3 == 0) goto L_0x0065
            android.support.v4.widget.h r9 = r7.f1271m
            if (r9 != 0) goto L_0x005a
            android.support.v4.widget.h r9 = new android.support.v4.widget.h
            r9.<init>(r7)
            r7.f1271m = r9
        L_0x005a:
            android.support.v4.widget.h r9 = r7.f1271m
            r9.mo863a((boolean) r2)
            android.support.v4.widget.h r9 = r7.f1271m
            r9.onTouch(r7, r8)
            goto L_0x006c
        L_0x0065:
            android.support.v4.widget.h r8 = r7.f1271m
            if (r8 == 0) goto L_0x006c
            r8.mo863a((boolean) r1)
        L_0x006c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.widget.C0349N.mo1778a(android.view.MotionEvent, int):boolean");
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        m1566a(canvas);
        super.dispatchDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        if (this.f1272n == null) {
            super.drawableStateChanged();
            setSelectorEnabled(true);
            m1571c();
        }
    }

    public boolean hasFocus() {
        return this.f1268j || super.hasFocus();
    }

    public boolean hasWindowFocus() {
        return this.f1268j || super.hasWindowFocus();
    }

    public boolean isFocused() {
        return this.f1268j || super.isFocused();
    }

    public boolean isInTouchMode() {
        return (this.f1268j && this.f1267i) || super.isInTouchMode();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.f1272n = null;
        super.onDetachedFromWindow();
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (Build.VERSION.SDK_INT < 26) {
            return super.onHoverEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10 && this.f1272n == null) {
            this.f1272n = new C0351b();
            this.f1272n.mo1792b();
        }
        boolean onHoverEvent = super.onHoverEvent(motionEvent);
        if (actionMasked == 9 || actionMasked == 7) {
            int pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
            if (!(pointToPosition == -1 || pointToPosition == getSelectedItemPosition())) {
                View childAt = getChildAt(pointToPosition - getFirstVisiblePosition());
                if (childAt.isEnabled()) {
                    setSelectionFromTop(pointToPosition, childAt.getTop() - getTop());
                }
                m1571c();
            }
        } else {
            setSelection(-1);
        }
        return onHoverEvent;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.f1264f = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        C0351b bVar = this.f1272n;
        if (bVar != null) {
            bVar.mo1791a();
        }
        return super.onTouchEvent(motionEvent);
    }

    /* access modifiers changed from: package-private */
    public void setListSelectionHidden(boolean z) {
        this.f1267i = z;
    }

    public void setSelector(Drawable drawable) {
        this.f1266h = drawable != null ? new C0350a(drawable) : null;
        super.setSelector(this.f1266h);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.f1260b = rect.left;
        this.f1261c = rect.top;
        this.f1262d = rect.right;
        this.f1263e = rect.bottom;
    }
}

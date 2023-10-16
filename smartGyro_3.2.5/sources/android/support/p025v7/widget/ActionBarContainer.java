package android.support.p025v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import p000a.p001a.p005c.p014g.C0127u;
import p000a.p001a.p017d.p018a.C0141f;
import p000a.p001a.p017d.p018a.C0145j;

/* renamed from: android.support.v7.widget.ActionBarContainer */
public class ActionBarContainer extends FrameLayout {

    /* renamed from: a */
    private boolean f1102a;

    /* renamed from: b */
    private View f1103b;

    /* renamed from: c */
    private View f1104c;

    /* renamed from: d */
    private View f1105d;

    /* renamed from: e */
    Drawable f1106e;

    /* renamed from: f */
    Drawable f1107f;

    /* renamed from: g */
    Drawable f1108g;

    /* renamed from: h */
    boolean f1109h;

    /* renamed from: i */
    boolean f1110i;

    /* renamed from: j */
    private int f1111j;

    public ActionBarContainer(Context context) {
        this(context, (AttributeSet) null);
    }

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        C0127u.m443a((View) this, (Drawable) new C0390b(this));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0145j.ActionBar);
        this.f1106e = obtainStyledAttributes.getDrawable(C0145j.ActionBar_background);
        this.f1107f = obtainStyledAttributes.getDrawable(C0145j.ActionBar_backgroundStacked);
        this.f1111j = obtainStyledAttributes.getDimensionPixelSize(C0145j.ActionBar_height, -1);
        if (getId() == C0141f.split_action_bar) {
            this.f1109h = true;
            this.f1108g = obtainStyledAttributes.getDrawable(C0145j.ActionBar_backgroundSplit);
        }
        obtainStyledAttributes.recycle();
        boolean z = false;
        if (!this.f1109h ? this.f1106e == null && this.f1107f == null : this.f1108g == null) {
            z = true;
        }
        setWillNotDraw(z);
    }

    /* renamed from: a */
    private int m1384a(View view) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        return view.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    /* renamed from: b */
    private boolean m1385b(View view) {
        return view == null || view.getVisibility() == 8 || view.getMeasuredHeight() == 0;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f1106e;
        if (drawable != null && drawable.isStateful()) {
            this.f1106e.setState(getDrawableState());
        }
        Drawable drawable2 = this.f1107f;
        if (drawable2 != null && drawable2.isStateful()) {
            this.f1107f.setState(getDrawableState());
        }
        Drawable drawable3 = this.f1108g;
        if (drawable3 != null && drawable3.isStateful()) {
            this.f1108g.setState(getDrawableState());
        }
    }

    public View getTabContainer() {
        return this.f1103b;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f1106e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.f1107f;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        Drawable drawable3 = this.f1108g;
        if (drawable3 != null) {
            drawable3.jumpToCurrentState();
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.f1104c = findViewById(C0141f.action_bar);
        this.f1105d = findViewById(C0141f.action_context_bar);
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f1102a || super.onInterceptTouchEvent(motionEvent);
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Drawable drawable;
        Drawable drawable2;
        int left;
        int top;
        int right;
        View view;
        super.onLayout(z, i, i2, i3, i4);
        View view2 = this.f1103b;
        boolean z2 = true;
        boolean z3 = false;
        boolean z4 = (view2 == null || view2.getVisibility() == 8) ? false : true;
        if (!(view2 == null || view2.getVisibility() == 8)) {
            int measuredHeight = getMeasuredHeight();
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view2.getLayoutParams();
            view2.layout(i, (measuredHeight - view2.getMeasuredHeight()) - layoutParams.bottomMargin, i3, measuredHeight - layoutParams.bottomMargin);
        }
        if (this.f1109h) {
            Drawable drawable3 = this.f1108g;
            if (drawable3 != null) {
                drawable3.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            } else {
                z2 = false;
            }
        } else {
            if (this.f1106e != null) {
                if (this.f1104c.getVisibility() == 0) {
                    drawable2 = this.f1106e;
                    left = this.f1104c.getLeft();
                    top = this.f1104c.getTop();
                    right = this.f1104c.getRight();
                    view = this.f1104c;
                } else {
                    View view3 = this.f1105d;
                    if (view3 == null || view3.getVisibility() != 0) {
                        this.f1106e.setBounds(0, 0, 0, 0);
                        z3 = true;
                    } else {
                        drawable2 = this.f1106e;
                        left = this.f1105d.getLeft();
                        top = this.f1105d.getTop();
                        right = this.f1105d.getRight();
                        view = this.f1105d;
                    }
                }
                drawable2.setBounds(left, top, right, view.getBottom());
                z3 = true;
            }
            this.f1110i = z4;
            if (!z4 || (drawable = this.f1107f) == null) {
                z2 = z3;
            } else {
                drawable.setBounds(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            }
        }
        if (z2) {
            invalidate();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r4, int r5) {
        /*
            r3 = this;
            android.view.View r0 = r3.f1104c
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r0 != 0) goto L_0x001c
            int r0 = android.view.View.MeasureSpec.getMode(r5)
            if (r0 != r1) goto L_0x001c
            int r0 = r3.f1111j
            if (r0 < 0) goto L_0x001c
            int r5 = android.view.View.MeasureSpec.getSize(r5)
            int r5 = java.lang.Math.min(r0, r5)
            int r5 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r1)
        L_0x001c:
            super.onMeasure(r4, r5)
            android.view.View r4 = r3.f1104c
            if (r4 != 0) goto L_0x0024
            return
        L_0x0024:
            int r4 = android.view.View.MeasureSpec.getMode(r5)
            android.view.View r0 = r3.f1103b
            if (r0 == 0) goto L_0x006f
            int r0 = r0.getVisibility()
            r2 = 8
            if (r0 == r2) goto L_0x006f
            r0 = 1073741824(0x40000000, float:2.0)
            if (r4 == r0) goto L_0x006f
            android.view.View r0 = r3.f1104c
            boolean r0 = r3.m1385b(r0)
            if (r0 != 0) goto L_0x0047
            android.view.View r0 = r3.f1104c
        L_0x0042:
            int r0 = r3.m1384a(r0)
            goto L_0x0053
        L_0x0047:
            android.view.View r0 = r3.f1105d
            boolean r0 = r3.m1385b(r0)
            if (r0 != 0) goto L_0x0052
            android.view.View r0 = r3.f1105d
            goto L_0x0042
        L_0x0052:
            r0 = 0
        L_0x0053:
            if (r4 != r1) goto L_0x005a
            int r4 = android.view.View.MeasureSpec.getSize(r5)
            goto L_0x005d
        L_0x005a:
            r4 = 2147483647(0x7fffffff, float:NaN)
        L_0x005d:
            int r5 = r3.getMeasuredWidth()
            android.view.View r1 = r3.f1103b
            int r1 = r3.m1384a(r1)
            int r0 = r0 + r1
            int r4 = java.lang.Math.min(r0, r4)
            r3.setMeasuredDimension(r5, r4)
        L_0x006f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.widget.ActionBarContainer.onMeasure(int, int):void");
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable drawable2 = this.f1106e;
        if (drawable2 != null) {
            drawable2.setCallback((Drawable.Callback) null);
            unscheduleDrawable(this.f1106e);
        }
        this.f1106e = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            View view = this.f1104c;
            if (view != null) {
                this.f1106e.setBounds(view.getLeft(), this.f1104c.getTop(), this.f1104c.getRight(), this.f1104c.getBottom());
            }
        }
        boolean z = true;
        if (!this.f1109h ? !(this.f1106e == null && this.f1107f == null) : this.f1108g != null) {
            z = false;
        }
        setWillNotDraw(z);
        invalidate();
    }

    public void setSplitBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f1108g;
        if (drawable3 != null) {
            drawable3.setCallback((Drawable.Callback) null);
            unscheduleDrawable(this.f1108g);
        }
        this.f1108g = drawable;
        boolean z = false;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f1109h && (drawable2 = this.f1108g) != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        if (!this.f1109h ? this.f1106e == null && this.f1107f == null : this.f1108g == null) {
            z = true;
        }
        setWillNotDraw(z);
        invalidate();
    }

    public void setStackedBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f1107f;
        if (drawable3 != null) {
            drawable3.setCallback((Drawable.Callback) null);
            unscheduleDrawable(this.f1107f);
        }
        this.f1107f = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f1110i && (drawable2 = this.f1107f) != null) {
                drawable2.setBounds(this.f1103b.getLeft(), this.f1103b.getTop(), this.f1103b.getRight(), this.f1103b.getBottom());
            }
        }
        boolean z = true;
        if (!this.f1109h ? !(this.f1106e == null && this.f1107f == null) : this.f1108g != null) {
            z = false;
        }
        setWillNotDraw(z);
        invalidate();
    }

    public void setTabContainer(C0386aa aaVar) {
        View view = this.f1103b;
        if (view != null) {
            removeView(view);
        }
        this.f1103b = aaVar;
        if (aaVar != null) {
            addView(aaVar);
            ViewGroup.LayoutParams layoutParams = aaVar.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            aaVar.setAllowCollapse(false);
        }
    }

    public void setTransitioning(boolean z) {
        this.f1102a = z;
        setDescendantFocusability(z ? 393216 : 262144);
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.f1106e;
        if (drawable != null) {
            drawable.setVisible(z, false);
        }
        Drawable drawable2 = this.f1107f;
        if (drawable2 != null) {
            drawable2.setVisible(z, false);
        }
        Drawable drawable3 = this.f1108g;
        if (drawable3 != null) {
            drawable3.setVisible(z, false);
        }
    }

    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i) {
        if (i != 0) {
            return super.startActionModeForChild(view, callback, i);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return (drawable == this.f1106e && !this.f1109h) || (drawable == this.f1107f && this.f1110i) || ((drawable == this.f1108g && this.f1109h) || super.verifyDrawable(drawable));
    }
}

package android.support.p025v7.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import p000a.p001a.p005c.p014g.C0127u;
import p000a.p001a.p005c.p014g.C0134z;
import p000a.p001a.p017d.p018a.C0136a;
import p000a.p001a.p017d.p018a.C0141f;
import p000a.p001a.p017d.p018a.C0142g;
import p000a.p001a.p017d.p018a.C0145j;

/* renamed from: android.support.v7.widget.ActionBarContextView */
public class ActionBarContextView extends C0384a {

    /* renamed from: i */
    private CharSequence f1112i;

    /* renamed from: j */
    private CharSequence f1113j;

    /* renamed from: k */
    private View f1114k;

    /* renamed from: l */
    private View f1115l;

    /* renamed from: m */
    private LinearLayout f1116m;

    /* renamed from: n */
    private TextView f1117n;

    /* renamed from: o */
    private TextView f1118o;

    /* renamed from: p */
    private int f1119p;

    /* renamed from: q */
    private int f1120q;

    /* renamed from: r */
    private boolean f1121r;

    /* renamed from: s */
    private int f1122s;

    public ActionBarContextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0136a.actionModeStyle);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        C0439ta a = C0439ta.m1902a(context, attributeSet, C0145j.ActionMode, i, 0);
        C0127u.m443a((View) this, a.mo2277b(C0145j.ActionMode_background));
        this.f1119p = a.mo2286g(C0145j.ActionMode_titleTextStyle, 0);
        this.f1120q = a.mo2286g(C0145j.ActionMode_subtitleTextStyle, 0);
        this.f1484e = a.mo2284f(C0145j.ActionMode_height, 0);
        this.f1122s = a.mo2286g(C0145j.ActionMode_closeItemLayout, C0142g.abc_action_mode_close_item_material);
        a.mo2274a();
    }

    /* renamed from: e */
    private void m1386e() {
        if (this.f1116m == null) {
            LayoutInflater.from(getContext()).inflate(C0142g.abc_action_bar_title_item, this);
            this.f1116m = (LinearLayout) getChildAt(getChildCount() - 1);
            this.f1117n = (TextView) this.f1116m.findViewById(C0141f.action_bar_title);
            this.f1118o = (TextView) this.f1116m.findViewById(C0141f.action_bar_subtitle);
            if (this.f1119p != 0) {
                this.f1117n.setTextAppearance(getContext(), this.f1119p);
            }
            if (this.f1120q != 0) {
                this.f1118o.setTextAppearance(getContext(), this.f1120q);
            }
        }
        this.f1117n.setText(this.f1112i);
        this.f1118o.setText(this.f1113j);
        boolean z = !TextUtils.isEmpty(this.f1112i);
        boolean z2 = !TextUtils.isEmpty(this.f1113j);
        int i = 0;
        this.f1118o.setVisibility(z2 ? 0 : 8);
        LinearLayout linearLayout = this.f1116m;
        if (!z && !z2) {
            i = 8;
        }
        linearLayout.setVisibility(i);
        if (this.f1116m.getParent() == null) {
            addView(this.f1116m);
        }
    }

    /* renamed from: a */
    public /* bridge */ /* synthetic */ C0134z mo1549a(int i, long j) {
        return super.mo1549a(i, j);
    }

    /* renamed from: a */
    public void mo1550a() {
        if (this.f1114k == null) {
            mo1553c();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001f, code lost:
        if (r0.getParent() == null) goto L_0x0015;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo1551a(p000a.p001a.p017d.p023d.C0164b r4) {
        /*
            r3 = this;
            android.view.View r0 = r3.f1114k
            if (r0 != 0) goto L_0x001b
            android.content.Context r0 = r3.getContext()
            android.view.LayoutInflater r0 = android.view.LayoutInflater.from(r0)
            int r1 = r3.f1122s
            r2 = 0
            android.view.View r0 = r0.inflate(r1, r3, r2)
            r3.f1114k = r0
        L_0x0015:
            android.view.View r0 = r3.f1114k
            r3.addView(r0)
            goto L_0x0022
        L_0x001b:
            android.view.ViewParent r0 = r0.getParent()
            if (r0 != 0) goto L_0x0022
            goto L_0x0015
        L_0x0022:
            android.view.View r0 = r3.f1114k
            int r1 = p000a.p001a.p017d.p018a.C0141f.action_mode_close_button
            android.view.View r0 = r0.findViewById(r1)
            android.support.v7.widget.c r1 = new android.support.v7.widget.c
            r1.<init>(r3, r4)
            r0.setOnClickListener(r1)
            android.view.Menu r4 = r4.mo655c()
            android.support.v7.view.menu.l r4 = (android.support.p025v7.view.menu.C0293l) r4
            android.support.v7.widget.g r0 = r3.f1483d
            if (r0 == 0) goto L_0x003f
            r0.mo2146c()
        L_0x003f:
            android.support.v7.widget.g r0 = new android.support.v7.widget.g
            android.content.Context r1 = r3.getContext()
            r0.<init>(r1)
            r3.f1483d = r0
            android.support.v7.widget.g r0 = r3.f1483d
            r1 = 1
            r0.mo2145c((boolean) r1)
            android.view.ViewGroup$LayoutParams r0 = new android.view.ViewGroup$LayoutParams
            r1 = -2
            r2 = -1
            r0.<init>(r1, r2)
            android.support.v7.widget.g r1 = r3.f1483d
            android.content.Context r2 = r3.f1481b
            r4.mo1276a((android.support.p025v7.view.menu.C0310v) r1, (android.content.Context) r2)
            android.support.v7.widget.g r4 = r3.f1483d
            android.support.v7.view.menu.w r4 = r4.mo1232b((android.view.ViewGroup) r3)
            android.support.v7.widget.ActionMenuView r4 = (android.support.p025v7.widget.ActionMenuView) r4
            r3.f1482c = r4
            android.support.v7.widget.ActionMenuView r4 = r3.f1482c
            r1 = 0
            p000a.p001a.p005c.p014g.C0127u.m443a((android.view.View) r4, (android.graphics.drawable.Drawable) r1)
            android.support.v7.widget.ActionMenuView r4 = r3.f1482c
            r3.addView(r4, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.widget.ActionBarContextView.mo1551a(a.a.d.d.b):void");
    }

    /* renamed from: b */
    public boolean mo1552b() {
        return this.f1121r;
    }

    /* renamed from: c */
    public void mo1553c() {
        removeAllViews();
        this.f1115l = null;
        this.f1482c = null;
    }

    /* renamed from: d */
    public boolean mo1554d() {
        C0400g gVar = this.f1483d;
        if (gVar != null) {
            return gVar.mo2152i();
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public CharSequence getSubtitle() {
        return this.f1113j;
    }

    public CharSequence getTitle() {
        return this.f1112i;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        C0400g gVar = this.f1483d;
        if (gVar != null) {
            gVar.mo2148e();
            this.f1483d.mo2149f();
        }
    }

    public /* bridge */ /* synthetic */ boolean onHoverEvent(MotionEvent motionEvent) {
        return super.onHoverEvent(motionEvent);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 32) {
            accessibilityEvent.setSource(this);
            accessibilityEvent.setClassName(ActionBarContextView.class.getName());
            accessibilityEvent.setPackageName(getContext().getPackageName());
            accessibilityEvent.setContentDescription(this.f1112i);
            return;
        }
        super.onInitializeAccessibilityEvent(accessibilityEvent);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        boolean a = C0342Ha.m1498a(this);
        int paddingRight = a ? (i3 - i) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
        View view = this.f1114k;
        if (view == null || view.getVisibility() == 8) {
            i5 = paddingRight;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f1114k.getLayoutParams();
            int i6 = a ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i7 = a ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int a2 = C0384a.m1746a(paddingRight, i6, a);
            i5 = C0384a.m1746a(a2 + mo2098a(this.f1114k, a2, paddingTop, paddingTop2, a), i7, a);
        }
        LinearLayout linearLayout = this.f1116m;
        if (!(linearLayout == null || this.f1115l != null || linearLayout.getVisibility() == 8)) {
            i5 += mo2098a(this.f1116m, i5, paddingTop, paddingTop2, a);
        }
        int i8 = i5;
        View view2 = this.f1115l;
        if (view2 != null) {
            mo2098a(view2, i8, paddingTop, paddingTop2, a);
        }
        int paddingLeft = a ? getPaddingLeft() : (i3 - i) - getPaddingRight();
        ActionMenuView actionMenuView = this.f1482c;
        if (actionMenuView != null) {
            mo2098a(actionMenuView, paddingLeft, paddingTop, paddingTop2, !a);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3 = 1073741824;
        if (View.MeasureSpec.getMode(i) != 1073741824) {
            throw new IllegalStateException(ActionBarContextView.class.getSimpleName() + " can only be used " + "with android:layout_width=\"match_parent\" (or fill_parent)");
        } else if (View.MeasureSpec.getMode(i2) != 0) {
            int size = View.MeasureSpec.getSize(i);
            int i4 = this.f1484e;
            if (i4 <= 0) {
                i4 = View.MeasureSpec.getSize(i2);
            }
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
            int i5 = i4 - paddingTop;
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE);
            View view = this.f1114k;
            if (view != null) {
                int a = mo2097a(view, paddingLeft, makeMeasureSpec, 0);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f1114k.getLayoutParams();
                paddingLeft = a - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
            }
            ActionMenuView actionMenuView = this.f1482c;
            if (actionMenuView != null && actionMenuView.getParent() == this) {
                paddingLeft = mo2097a(this.f1482c, paddingLeft, makeMeasureSpec, 0);
            }
            LinearLayout linearLayout = this.f1116m;
            if (linearLayout != null && this.f1115l == null) {
                if (this.f1121r) {
                    this.f1116m.measure(View.MeasureSpec.makeMeasureSpec(0, 0), makeMeasureSpec);
                    int measuredWidth = this.f1116m.getMeasuredWidth();
                    boolean z = measuredWidth <= paddingLeft;
                    if (z) {
                        paddingLeft -= measuredWidth;
                    }
                    this.f1116m.setVisibility(z ? 0 : 8);
                } else {
                    paddingLeft = mo2097a(linearLayout, paddingLeft, makeMeasureSpec, 0);
                }
            }
            View view2 = this.f1115l;
            if (view2 != null) {
                ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                int i6 = layoutParams.width != -2 ? 1073741824 : Integer.MIN_VALUE;
                int i7 = layoutParams.width;
                if (i7 >= 0) {
                    paddingLeft = Math.min(i7, paddingLeft);
                }
                if (layoutParams.height == -2) {
                    i3 = Integer.MIN_VALUE;
                }
                int i8 = layoutParams.height;
                if (i8 >= 0) {
                    i5 = Math.min(i8, i5);
                }
                this.f1115l.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i6), View.MeasureSpec.makeMeasureSpec(i5, i3));
            }
            if (this.f1484e <= 0) {
                int childCount = getChildCount();
                int i9 = 0;
                for (int i10 = 0; i10 < childCount; i10++) {
                    int measuredHeight = getChildAt(i10).getMeasuredHeight() + paddingTop;
                    if (measuredHeight > i9) {
                        i9 = measuredHeight;
                    }
                }
                setMeasuredDimension(size, i9);
                return;
            }
            setMeasuredDimension(size, i4);
        } else {
            throw new IllegalStateException(ActionBarContextView.class.getSimpleName() + " can only be used " + "with android:layout_height=\"wrap_content\"");
        }
    }

    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    public void setContentHeight(int i) {
        this.f1484e = i;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.f1115l;
        if (view2 != null) {
            removeView(view2);
        }
        this.f1115l = view;
        if (!(view == null || (linearLayout = this.f1116m) == null)) {
            removeView(linearLayout);
            this.f1116m = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.f1113j = charSequence;
        m1386e();
    }

    public void setTitle(CharSequence charSequence) {
        this.f1112i = charSequence;
        m1386e();
    }

    public void setTitleOptional(boolean z) {
        if (z != this.f1121r) {
            requestLayout();
        }
        this.f1121r = z;
    }

    public /* bridge */ /* synthetic */ void setVisibility(int i) {
        super.setVisibility(i);
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }
}

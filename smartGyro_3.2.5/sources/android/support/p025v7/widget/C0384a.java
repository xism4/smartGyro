package android.support.p025v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import p000a.p001a.p005c.p014g.C0092A;
import p000a.p001a.p005c.p014g.C0127u;
import p000a.p001a.p005c.p014g.C0134z;
import p000a.p001a.p017d.p018a.C0136a;
import p000a.p001a.p017d.p018a.C0145j;

/* renamed from: android.support.v7.widget.a */
abstract class C0384a extends ViewGroup {

    /* renamed from: a */
    protected final C0385a f1480a;

    /* renamed from: b */
    protected final Context f1481b;

    /* renamed from: c */
    protected ActionMenuView f1482c;

    /* renamed from: d */
    protected C0400g f1483d;

    /* renamed from: e */
    protected int f1484e;

    /* renamed from: f */
    protected C0134z f1485f;

    /* renamed from: g */
    private boolean f1486g;

    /* renamed from: h */
    private boolean f1487h;

    /* renamed from: android.support.v7.widget.a$a */
    protected class C0385a implements C0092A {

        /* renamed from: a */
        private boolean f1488a = false;

        /* renamed from: b */
        int f1489b;

        protected C0385a() {
        }

        /* renamed from: a */
        public C0385a mo2100a(C0134z zVar, int i) {
            C0384a.this.f1485f = zVar;
            this.f1489b = i;
            return this;
        }

        /* renamed from: a */
        public void mo389a(View view) {
            this.f1488a = true;
        }

        /* renamed from: b */
        public void mo390b(View view) {
            if (!this.f1488a) {
                C0384a aVar = C0384a.this;
                aVar.f1485f = null;
                C0384a.super.setVisibility(this.f1489b);
            }
        }

        /* renamed from: c */
        public void mo391c(View view) {
            C0384a.super.setVisibility(0);
            this.f1488a = false;
        }
    }

    C0384a(Context context) {
        this(context, (AttributeSet) null);
    }

    C0384a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    C0384a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        int i2;
        this.f1480a = new C0385a();
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(C0136a.actionBarPopupTheme, typedValue, true) || (i2 = typedValue.resourceId) == 0) {
            this.f1481b = context;
        } else {
            this.f1481b = new ContextThemeWrapper(context, i2);
        }
    }

    /* renamed from: a */
    protected static int m1746a(int i, int i2, boolean z) {
        return z ? i - i2 : i + i2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public int mo2097a(View view, int i, int i2, int i3) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE), i2);
        return Math.max(0, (i - view.getMeasuredWidth()) - i3);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public int mo2098a(View view, int i, int i2, int i3, boolean z) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i4 = i2 + ((i3 - measuredHeight) / 2);
        if (z) {
            view.layout(i - measuredWidth, i4, i, measuredHeight + i4);
        } else {
            view.layout(i, i4, i + measuredWidth, measuredHeight + i4);
        }
        return z ? -measuredWidth : measuredWidth;
    }

    /* renamed from: a */
    public C0134z mo1549a(int i, long j) {
        C0134z zVar = this.f1485f;
        if (zVar != null) {
            zVar.mo508a();
        }
        if (i == 0) {
            if (getVisibility() != 0) {
                setAlpha(0.0f);
            }
            C0134z a = C0127u.m436a(this);
            a.mo503a(1.0f);
            a.mo504a(j);
            C0385a aVar = this.f1480a;
            aVar.mo2100a(a, i);
            a.mo505a((C0092A) aVar);
            return a;
        }
        C0134z a2 = C0127u.m436a(this);
        a2.mo503a(0.0f);
        a2.mo504a(j);
        C0385a aVar2 = this.f1480a;
        aVar2.mo2100a(a2, i);
        a2.mo505a((C0092A) aVar2);
        return a2;
    }

    public int getAnimatedVisibility() {
        return this.f1485f != null ? this.f1480a.f1489b : getVisibility();
    }

    public int getContentHeight() {
        return this.f1484e;
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes((AttributeSet) null, C0145j.ActionBar, C0136a.actionBarStyle, 0);
        setContentHeight(obtainStyledAttributes.getLayoutDimension(C0145j.ActionBar_height, 0));
        obtainStyledAttributes.recycle();
        C0400g gVar = this.f1483d;
        if (gVar != null) {
            gVar.mo2141a(configuration);
        }
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.f1487h = false;
        }
        if (!this.f1487h) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.f1487h = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.f1487h = false;
        }
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f1486g = false;
        }
        if (!this.f1486g) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.f1486g = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.f1486g = false;
        }
        return true;
    }

    public abstract void setContentHeight(int i);

    public void setVisibility(int i) {
        if (i != getVisibility()) {
            C0134z zVar = this.f1485f;
            if (zVar != null) {
                zVar.mo508a();
            }
            super.setVisibility(i);
        }
    }
}

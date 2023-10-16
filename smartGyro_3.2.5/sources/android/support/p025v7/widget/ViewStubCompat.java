package android.support.p025v7.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.ref.WeakReference;
import p000a.p001a.p017d.p018a.C0145j;

/* renamed from: android.support.v7.widget.ViewStubCompat */
public final class ViewStubCompat extends View {

    /* renamed from: a */
    private int f1458a;

    /* renamed from: b */
    private int f1459b;

    /* renamed from: c */
    private WeakReference<View> f1460c;

    /* renamed from: d */
    private LayoutInflater f1461d;

    /* renamed from: e */
    private C0378a f1462e;

    /* renamed from: android.support.v7.widget.ViewStubCompat$a */
    public interface C0378a {
        /* renamed from: a */
        void mo2038a(ViewStubCompat viewStubCompat, View view);
    }

    public ViewStubCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ViewStubCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1458a = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0145j.ViewStubCompat, i, 0);
        this.f1459b = obtainStyledAttributes.getResourceId(C0145j.ViewStubCompat_android_inflatedId, -1);
        this.f1458a = obtainStyledAttributes.getResourceId(C0145j.ViewStubCompat_android_layout, 0);
        setId(obtainStyledAttributes.getResourceId(C0145j.ViewStubCompat_android_id, -1));
        obtainStyledAttributes.recycle();
        setVisibility(8);
        setWillNotDraw(true);
    }

    /* renamed from: a */
    public View mo2026a() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ViewGroup)) {
            throw new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent");
        } else if (this.f1458a != 0) {
            ViewGroup viewGroup = (ViewGroup) parent;
            LayoutInflater layoutInflater = this.f1461d;
            if (layoutInflater == null) {
                layoutInflater = LayoutInflater.from(getContext());
            }
            View inflate = layoutInflater.inflate(this.f1458a, viewGroup, false);
            int i = this.f1459b;
            if (i != -1) {
                inflate.setId(i);
            }
            int indexOfChild = viewGroup.indexOfChild(this);
            viewGroup.removeViewInLayout(this);
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            if (layoutParams != null) {
                viewGroup.addView(inflate, indexOfChild, layoutParams);
            } else {
                viewGroup.addView(inflate, indexOfChild);
            }
            this.f1460c = new WeakReference<>(inflate);
            C0378a aVar = this.f1462e;
            if (aVar != null) {
                aVar.mo2038a(this, inflate);
            }
            return inflate;
        } else {
            throw new IllegalArgumentException("ViewStub must have a valid layoutResource");
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
    }

    @SuppressLint({"MissingSuperCall"})
    public void draw(Canvas canvas) {
    }

    public int getInflatedId() {
        return this.f1459b;
    }

    public LayoutInflater getLayoutInflater() {
        return this.f1461d;
    }

    public int getLayoutResource() {
        return this.f1458a;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(0, 0);
    }

    public void setInflatedId(int i) {
        this.f1459b = i;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.f1461d = layoutInflater;
    }

    public void setLayoutResource(int i) {
        this.f1458a = i;
    }

    public void setOnInflateListener(C0378a aVar) {
        this.f1462e = aVar;
    }

    public void setVisibility(int i) {
        WeakReference<View> weakReference = this.f1460c;
        if (weakReference != null) {
            View view = (View) weakReference.get();
            if (view != null) {
                view.setVisibility(i);
                return;
            }
            throw new IllegalStateException("setVisibility called on un-referenced view");
        }
        super.setVisibility(i);
        if (i == 0 || i == 4) {
            mo2026a();
        }
    }
}

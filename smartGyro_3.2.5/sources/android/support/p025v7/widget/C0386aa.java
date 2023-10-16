package android.support.p025v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.p025v7.app.C0236a;
import android.support.p025v7.widget.C0357Q;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import p000a.p001a.p017d.p018a.C0136a;
import p000a.p001a.p017d.p023d.C0163a;

/* renamed from: android.support.v7.widget.aa */
public class C0386aa extends HorizontalScrollView implements AdapterView.OnItemSelectedListener {

    /* renamed from: a */
    private static final Interpolator f1491a = new DecelerateInterpolator();

    /* renamed from: b */
    Runnable f1492b;

    /* renamed from: c */
    private C0388b f1493c;

    /* renamed from: d */
    C0357Q f1494d;

    /* renamed from: e */
    private Spinner f1495e;

    /* renamed from: f */
    private boolean f1496f;

    /* renamed from: g */
    int f1497g;

    /* renamed from: h */
    int f1498h;

    /* renamed from: i */
    private int f1499i;

    /* renamed from: j */
    private int f1500j;

    /* renamed from: android.support.v7.widget.aa$a */
    private class C0387a extends BaseAdapter {
        C0387a() {
        }

        public int getCount() {
            return C0386aa.this.f1494d.getChildCount();
        }

        public Object getItem(int i) {
            return ((C0389c) C0386aa.this.f1494d.getChildAt(i)).mo2117a();
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                return C0386aa.this.mo2101a((C0236a.C0239c) getItem(i), true);
            }
            ((C0389c) view).mo2118a((C0236a.C0239c) getItem(i));
            return view;
        }
    }

    /* renamed from: android.support.v7.widget.aa$b */
    private class C0388b implements View.OnClickListener {
        C0388b() {
        }

        public void onClick(View view) {
            ((C0389c) view).mo2117a().mo993e();
            int childCount = C0386aa.this.f1494d.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = C0386aa.this.f1494d.getChildAt(i);
                childAt.setSelected(childAt == view);
            }
        }
    }

    /* renamed from: android.support.v7.widget.aa$c */
    private class C0389c extends LinearLayout {

        /* renamed from: a */
        private final int[] f1503a = {16842964};

        /* renamed from: b */
        private C0236a.C0239c f1504b;

        /* renamed from: c */
        private TextView f1505c;

        /* renamed from: d */
        private ImageView f1506d;

        /* renamed from: e */
        private View f1507e;

        public C0389c(Context context, C0236a.C0239c cVar, boolean z) {
            super(context, (AttributeSet) null, C0136a.actionBarTabStyle);
            this.f1504b = cVar;
            C0439ta a = C0439ta.m1902a(context, (AttributeSet) null, this.f1503a, C0136a.actionBarTabStyle, 0);
            if (a.mo2287g(0)) {
                setBackgroundDrawable(a.mo2277b(0));
            }
            a.mo2274a();
            if (z) {
                setGravity(8388627);
            }
            mo2119b();
        }

        /* renamed from: a */
        public C0236a.C0239c mo2117a() {
            return this.f1504b;
        }

        /* renamed from: a */
        public void mo2118a(C0236a.C0239c cVar) {
            this.f1504b = cVar;
            mo2119b();
        }

        /* renamed from: b */
        public void mo2119b() {
            C0236a.C0239c cVar = this.f1504b;
            View b = cVar.mo990b();
            CharSequence charSequence = null;
            if (b != null) {
                ViewParent parent = b.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(b);
                    }
                    addView(b);
                }
                this.f1507e = b;
                TextView textView = this.f1505c;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.f1506d;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.f1506d.setImageDrawable((Drawable) null);
                    return;
                }
                return;
            }
            View view = this.f1507e;
            if (view != null) {
                removeView(view);
                this.f1507e = null;
            }
            Drawable c = cVar.mo991c();
            CharSequence d = cVar.mo992d();
            if (c != null) {
                if (this.f1506d == null) {
                    C0438t tVar = new C0438t(getContext());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.gravity = 16;
                    tVar.setLayoutParams(layoutParams);
                    addView(tVar, 0);
                    this.f1506d = tVar;
                }
                this.f1506d.setImageDrawable(c);
                this.f1506d.setVisibility(0);
            } else {
                ImageView imageView2 = this.f1506d;
                if (imageView2 != null) {
                    imageView2.setVisibility(8);
                    this.f1506d.setImageDrawable((Drawable) null);
                }
            }
            boolean z = !TextUtils.isEmpty(d);
            if (z) {
                if (this.f1505c == null) {
                    C0343I i = new C0343I(getContext(), (AttributeSet) null, C0136a.actionBarTabTextStyle);
                    i.setEllipsize(TextUtils.TruncateAt.END);
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams2.gravity = 16;
                    i.setLayoutParams(layoutParams2);
                    addView(i);
                    this.f1505c = i;
                }
                this.f1505c.setText(d);
                this.f1505c.setVisibility(0);
            } else {
                TextView textView2 = this.f1505c;
                if (textView2 != null) {
                    textView2.setVisibility(8);
                    this.f1505c.setText((CharSequence) null);
                }
            }
            ImageView imageView3 = this.f1506d;
            if (imageView3 != null) {
                imageView3.setContentDescription(cVar.mo989a());
            }
            if (!z) {
                charSequence = cVar.mo989a();
            }
            C0327Ba.m1444a(this, charSequence);
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName(C0236a.C0239c.class.getName());
        }

        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName(C0236a.C0239c.class.getName());
        }

        public void onMeasure(int i, int i2) {
            int i3;
            super.onMeasure(i, i2);
            if (C0386aa.this.f1497g > 0 && getMeasuredWidth() > (i3 = C0386aa.this.f1497g)) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), i2);
            }
        }

        public void setSelected(boolean z) {
            boolean z2 = isSelected() != z;
            super.setSelected(z);
            if (z2 && z) {
                sendAccessibilityEvent(4);
            }
        }
    }

    /* renamed from: a */
    private Spinner m1756a() {
        C0328C c = new C0328C(getContext(), (AttributeSet) null, C0136a.actionDropDownStyle);
        c.setLayoutParams(new C0357Q.C0358a(-2, -1));
        c.setOnItemSelectedListener(this);
        return c;
    }

    /* renamed from: b */
    private boolean m1757b() {
        Spinner spinner = this.f1495e;
        return spinner != null && spinner.getParent() == this;
    }

    /* renamed from: c */
    private void m1758c() {
        if (!m1757b()) {
            if (this.f1495e == null) {
                this.f1495e = m1756a();
            }
            removeView(this.f1494d);
            addView(this.f1495e, new ViewGroup.LayoutParams(-2, -1));
            if (this.f1495e.getAdapter() == null) {
                this.f1495e.setAdapter(new C0387a());
            }
            Runnable runnable = this.f1492b;
            if (runnable != null) {
                removeCallbacks(runnable);
                this.f1492b = null;
            }
            this.f1495e.setSelection(this.f1500j);
        }
    }

    /* renamed from: d */
    private boolean m1759d() {
        if (!m1757b()) {
            return false;
        }
        removeView(this.f1495e);
        addView(this.f1494d, new ViewGroup.LayoutParams(-2, -1));
        setTabSelected(this.f1495e.getSelectedItemPosition());
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C0389c mo2101a(C0236a.C0239c cVar, boolean z) {
        C0389c cVar2 = new C0389c(getContext(), cVar, z);
        if (z) {
            cVar2.setBackgroundDrawable((Drawable) null);
            cVar2.setLayoutParams(new AbsListView.LayoutParams(-1, this.f1499i));
        } else {
            cVar2.setFocusable(true);
            if (this.f1493c == null) {
                this.f1493c = new C0388b();
            }
            cVar2.setOnClickListener(this.f1493c);
        }
        return cVar2;
    }

    /* renamed from: a */
    public void mo2102a(int i) {
        View childAt = this.f1494d.getChildAt(i);
        Runnable runnable = this.f1492b;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        this.f1492b = new C0383Z(this, childAt);
        post(this.f1492b);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Runnable runnable = this.f1492b;
        if (runnable != null) {
            post(runnable);
        }
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        C0163a a = C0163a.m577a(getContext());
        setContentHeight(a.mo643e());
        this.f1498h = a.mo642d();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Runnable runnable = this.f1492b;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        ((C0389c) view).mo2117a().mo993e();
    }

    public void onMeasure(int i, int i2) {
        int i3;
        int mode = View.MeasureSpec.getMode(i);
        boolean z = true;
        boolean z2 = mode == 1073741824;
        setFillViewport(z2);
        int childCount = this.f1494d.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            i3 = -1;
        } else {
            if (childCount > 2) {
                this.f1497g = (int) (((float) View.MeasureSpec.getSize(i)) * 0.4f);
            } else {
                this.f1497g = View.MeasureSpec.getSize(i) / 2;
            }
            i3 = Math.min(this.f1497g, this.f1498h);
        }
        this.f1497g = i3;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.f1499i, 1073741824);
        if (z2 || !this.f1496f) {
            z = false;
        }
        if (z) {
            this.f1494d.measure(0, makeMeasureSpec);
            if (this.f1494d.getMeasuredWidth() > View.MeasureSpec.getSize(i)) {
                m1758c();
                int measuredWidth = getMeasuredWidth();
                super.onMeasure(i, makeMeasureSpec);
                int measuredWidth2 = getMeasuredWidth();
                if (z2 && measuredWidth != measuredWidth2) {
                    setTabSelected(this.f1500j);
                    return;
                }
            }
        }
        m1759d();
        int measuredWidth3 = getMeasuredWidth();
        super.onMeasure(i, makeMeasureSpec);
        int measuredWidth22 = getMeasuredWidth();
        if (z2) {
        }
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void setAllowCollapse(boolean z) {
        this.f1496f = z;
    }

    public void setContentHeight(int i) {
        this.f1499i = i;
        requestLayout();
    }

    public void setTabSelected(int i) {
        this.f1500j = i;
        int childCount = this.f1494d.getChildCount();
        int i2 = 0;
        while (i2 < childCount) {
            View childAt = this.f1494d.getChildAt(i2);
            boolean z = i2 == i;
            childAt.setSelected(z);
            if (z) {
                mo2102a(i);
            }
            i2++;
        }
        Spinner spinner = this.f1495e;
        if (spinner != null && i >= 0) {
            spinner.setSelection(i);
        }
    }
}

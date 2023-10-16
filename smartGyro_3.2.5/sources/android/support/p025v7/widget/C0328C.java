package android.support.p025v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;
import p000a.p001a.p005c.p014g.C0125s;
import p000a.p001a.p005c.p014g.C0127u;
import p000a.p001a.p017d.p018a.C0136a;
import p000a.p001a.p017d.p019b.p020a.C0146a;

/* renamed from: android.support.v7.widget.C */
public class C0328C extends Spinner implements C0125s {

    /* renamed from: a */
    private static final int[] f1176a = {16843505};

    /* renamed from: b */
    private final C0412j f1177b;

    /* renamed from: c */
    private final Context f1178c;

    /* renamed from: d */
    private C0354P f1179d;

    /* renamed from: e */
    private SpinnerAdapter f1180e;

    /* renamed from: f */
    private final boolean f1181f;

    /* renamed from: g */
    C0330b f1182g;

    /* renamed from: h */
    int f1183h;

    /* renamed from: i */
    final Rect f1184i;

    /* renamed from: android.support.v7.widget.C$a */
    private static class C0329a implements ListAdapter, SpinnerAdapter {

        /* renamed from: a */
        private SpinnerAdapter f1185a;

        /* renamed from: b */
        private ListAdapter f1186b;

        public C0329a(SpinnerAdapter spinnerAdapter, Resources.Theme theme) {
            this.f1185a = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.f1186b = (ListAdapter) spinnerAdapter;
            }
            if (theme == null) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 23 && (spinnerAdapter instanceof ThemedSpinnerAdapter)) {
                ThemedSpinnerAdapter themedSpinnerAdapter = (ThemedSpinnerAdapter) spinnerAdapter;
                if (themedSpinnerAdapter.getDropDownViewTheme() != theme) {
                    themedSpinnerAdapter.setDropDownViewTheme(theme);
                }
            } else if (spinnerAdapter instanceof C0431pa) {
                C0431pa paVar = (C0431pa) spinnerAdapter;
                if (paVar.getDropDownViewTheme() == null) {
                    paVar.setDropDownViewTheme(theme);
                }
            }
        }

        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.f1186b;
            if (listAdapter != null) {
                return listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        public int getCount() {
            SpinnerAdapter spinnerAdapter = this.f1185a;
            if (spinnerAdapter == null) {
                return 0;
            }
            return spinnerAdapter.getCount();
        }

        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            SpinnerAdapter spinnerAdapter = this.f1185a;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getDropDownView(i, view, viewGroup);
        }

        public Object getItem(int i) {
            SpinnerAdapter spinnerAdapter = this.f1185a;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getItem(i);
        }

        public long getItemId(int i) {
            SpinnerAdapter spinnerAdapter = this.f1185a;
            if (spinnerAdapter == null) {
                return -1;
            }
            return spinnerAdapter.getItemId(i);
        }

        public int getItemViewType(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            return getDropDownView(i, view, viewGroup);
        }

        public int getViewTypeCount() {
            return 1;
        }

        public boolean hasStableIds() {
            SpinnerAdapter spinnerAdapter = this.f1185a;
            return spinnerAdapter != null && spinnerAdapter.hasStableIds();
        }

        public boolean isEmpty() {
            return getCount() == 0;
        }

        public boolean isEnabled(int i) {
            ListAdapter listAdapter = this.f1186b;
            if (listAdapter != null) {
                return listAdapter.isEnabled(i);
            }
            return true;
        }

        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.f1185a;
            if (spinnerAdapter != null) {
                spinnerAdapter.registerDataSetObserver(dataSetObserver);
            }
        }

        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.f1185a;
            if (spinnerAdapter != null) {
                spinnerAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }

    /* renamed from: android.support.v7.widget.C$b */
    private class C0330b extends C0371U {

        /* renamed from: K */
        private CharSequence f1187K;

        /* renamed from: L */
        ListAdapter f1188L;

        /* renamed from: M */
        private final Rect f1189M = new Rect();

        public C0330b(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            mo1999a((View) C0328C.this);
            mo2002a(true);
            mo2010f(0);
            mo2000a((AdapterView.OnItemClickListener) new C0333D(this, C0328C.this));
        }

        /* renamed from: a */
        public void mo1682a(ListAdapter listAdapter) {
            super.mo1682a(listAdapter);
            this.f1188L = listAdapter;
        }

        /* renamed from: a */
        public void mo1683a(CharSequence charSequence) {
            this.f1187K = charSequence;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public boolean mo1684b(View view) {
            return C0127u.m454h(view) && view.getGlobalVisibleRect(this.f1189M);
        }

        /* renamed from: c */
        public void mo1137c() {
            ViewTreeObserver viewTreeObserver;
            boolean b = mo1136b();
            mo1685l();
            mo2008e(2);
            super.mo1137c();
            mo1140d().setChoiceMode(1);
            mo2012g(C0328C.this.getSelectedItemPosition());
            if (!b && (viewTreeObserver = C0328C.this.getViewTreeObserver()) != null) {
                C0335E e = new C0335E(this);
                viewTreeObserver.addOnGlobalLayoutListener(e);
                mo2001a((PopupWindow.OnDismissListener) new C0337F(this, e));
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x008d  */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x0095  */
        /* renamed from: l */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void mo1685l() {
            /*
                r8 = this;
                android.graphics.drawable.Drawable r0 = r8.mo2009f()
                r1 = 0
                if (r0 == 0) goto L_0x0026
                android.support.v7.widget.C r1 = android.support.p025v7.widget.C0328C.this
                android.graphics.Rect r1 = r1.f1184i
                r0.getPadding(r1)
                android.support.v7.widget.C r0 = android.support.p025v7.widget.C0328C.this
                boolean r0 = android.support.p025v7.widget.C0342Ha.m1498a(r0)
                if (r0 == 0) goto L_0x001d
                android.support.v7.widget.C r0 = android.support.p025v7.widget.C0328C.this
                android.graphics.Rect r0 = r0.f1184i
                int r0 = r0.right
                goto L_0x0024
            L_0x001d:
                android.support.v7.widget.C r0 = android.support.p025v7.widget.C0328C.this
                android.graphics.Rect r0 = r0.f1184i
                int r0 = r0.left
                int r0 = -r0
            L_0x0024:
                r1 = r0
                goto L_0x002e
            L_0x0026:
                android.support.v7.widget.C r0 = android.support.p025v7.widget.C0328C.this
                android.graphics.Rect r0 = r0.f1184i
                r0.right = r1
                r0.left = r1
            L_0x002e:
                android.support.v7.widget.C r0 = android.support.p025v7.widget.C0328C.this
                int r0 = r0.getPaddingLeft()
                android.support.v7.widget.C r2 = android.support.p025v7.widget.C0328C.this
                int r2 = r2.getPaddingRight()
                android.support.v7.widget.C r3 = android.support.p025v7.widget.C0328C.this
                int r3 = r3.getWidth()
                android.support.v7.widget.C r4 = android.support.p025v7.widget.C0328C.this
                int r5 = r4.f1183h
                r6 = -2
                if (r5 != r6) goto L_0x0078
                android.widget.ListAdapter r5 = r8.f1188L
                android.widget.SpinnerAdapter r5 = (android.widget.SpinnerAdapter) r5
                android.graphics.drawable.Drawable r6 = r8.mo2009f()
                int r4 = r4.mo1647a(r5, r6)
                android.support.v7.widget.C r5 = android.support.p025v7.widget.C0328C.this
                android.content.Context r5 = r5.getContext()
                android.content.res.Resources r5 = r5.getResources()
                android.util.DisplayMetrics r5 = r5.getDisplayMetrics()
                int r5 = r5.widthPixels
                android.support.v7.widget.C r6 = android.support.p025v7.widget.C0328C.this
                android.graphics.Rect r6 = r6.f1184i
                int r7 = r6.left
                int r5 = r5 - r7
                int r6 = r6.right
                int r5 = r5 - r6
                if (r4 <= r5) goto L_0x0070
                r4 = r5
            L_0x0070:
                int r5 = r3 - r0
                int r5 = r5 - r2
                int r4 = java.lang.Math.max(r4, r5)
                goto L_0x007e
            L_0x0078:
                r4 = -1
                if (r5 != r4) goto L_0x0082
                int r4 = r3 - r0
                int r4 = r4 - r2
            L_0x007e:
                r8.mo2003b((int) r4)
                goto L_0x0085
            L_0x0082:
                r8.mo2003b((int) r5)
            L_0x0085:
                android.support.v7.widget.C r4 = android.support.p025v7.widget.C0328C.this
                boolean r4 = android.support.p025v7.widget.C0342Ha.m1498a(r4)
                if (r4 == 0) goto L_0x0095
                int r3 = r3 - r2
                int r0 = r8.mo2015i()
                int r3 = r3 - r0
                int r1 = r1 + r3
                goto L_0x0096
            L_0x0095:
                int r1 = r1 + r0
            L_0x0096:
                r8.mo2006d(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.widget.C0328C.C0330b.mo1685l():void");
        }

        /* renamed from: m */
        public CharSequence mo1686m() {
            return this.f1187K;
        }
    }

    public C0328C(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0136a.spinnerStyle);
    }

    public C0328C(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, -1);
    }

    public C0328C(Context context, AttributeSet attributeSet, int i, int i2) {
        this(context, attributeSet, i, i2, (Resources.Theme) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0054, code lost:
        if (r12 != null) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0056, code lost:
        r12.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0068, code lost:
        if (r12 != null) goto L_0x0056;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00cb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C0328C(android.content.Context r8, android.util.AttributeSet r9, int r10, int r11, android.content.res.Resources.Theme r12) {
        /*
            r7 = this;
            r7.<init>(r8, r9, r10)
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
            r7.f1184i = r0
            int[] r0 = p000a.p001a.p017d.p018a.C0145j.Spinner
            r1 = 0
            android.support.v7.widget.ta r0 = android.support.p025v7.widget.C0439ta.m1902a(r8, r9, r0, r10, r1)
            android.support.v7.widget.j r2 = new android.support.v7.widget.j
            r2.<init>(r7)
            r7.f1177b = r2
            r2 = 0
            if (r12 == 0) goto L_0x0023
            a.a.d.d.d r3 = new a.a.d.d.d
            r3.<init>((android.content.Context) r8, (android.content.res.Resources.Theme) r12)
        L_0x0020:
            r7.f1178c = r3
            goto L_0x003c
        L_0x0023:
            int r12 = p000a.p001a.p017d.p018a.C0145j.Spinner_popupTheme
            int r12 = r0.mo2286g(r12, r1)
            if (r12 == 0) goto L_0x0031
            a.a.d.d.d r3 = new a.a.d.d.d
            r3.<init>((android.content.Context) r8, (int) r12)
            goto L_0x0020
        L_0x0031:
            int r12 = android.os.Build.VERSION.SDK_INT
            r3 = 23
            if (r12 >= r3) goto L_0x0039
            r12 = r8
            goto L_0x003a
        L_0x0039:
            r12 = r2
        L_0x003a:
            r7.f1178c = r12
        L_0x003c:
            android.content.Context r12 = r7.f1178c
            r3 = 1
            if (r12 == 0) goto L_0x00aa
            r12 = -1
            if (r11 != r12) goto L_0x0072
            int[] r12 = f1176a     // Catch:{ Exception -> 0x005f, all -> 0x005c }
            android.content.res.TypedArray r12 = r8.obtainStyledAttributes(r9, r12, r10, r1)     // Catch:{ Exception -> 0x005f, all -> 0x005c }
            boolean r4 = r12.hasValue(r1)     // Catch:{ Exception -> 0x005a }
            if (r4 == 0) goto L_0x0054
            int r11 = r12.getInt(r1, r1)     // Catch:{ Exception -> 0x005a }
        L_0x0054:
            if (r12 == 0) goto L_0x0072
        L_0x0056:
            r12.recycle()
            goto L_0x0072
        L_0x005a:
            r4 = move-exception
            goto L_0x0061
        L_0x005c:
            r8 = move-exception
            r12 = r2
            goto L_0x006c
        L_0x005f:
            r4 = move-exception
            r12 = r2
        L_0x0061:
            java.lang.String r5 = "AppCompatSpinner"
            java.lang.String r6 = "Could not read android:spinnerMode"
            android.util.Log.i(r5, r6, r4)     // Catch:{ all -> 0x006b }
            if (r12 == 0) goto L_0x0072
            goto L_0x0056
        L_0x006b:
            r8 = move-exception
        L_0x006c:
            if (r12 == 0) goto L_0x0071
            r12.recycle()
        L_0x0071:
            throw r8
        L_0x0072:
            if (r11 != r3) goto L_0x00aa
            android.support.v7.widget.C$b r11 = new android.support.v7.widget.C$b
            android.content.Context r12 = r7.f1178c
            r11.<init>(r12, r9, r10)
            android.content.Context r12 = r7.f1178c
            int[] r4 = p000a.p001a.p017d.p018a.C0145j.Spinner
            android.support.v7.widget.ta r12 = android.support.p025v7.widget.C0439ta.m1902a(r12, r9, r4, r10, r1)
            int r1 = p000a.p001a.p017d.p018a.C0145j.Spinner_android_dropDownWidth
            r4 = -2
            int r1 = r12.mo2284f(r1, r4)
            r7.f1183h = r1
            int r1 = p000a.p001a.p017d.p018a.C0145j.Spinner_android_popupBackground
            android.graphics.drawable.Drawable r1 = r12.mo2277b(r1)
            r11.mo1998a((android.graphics.drawable.Drawable) r1)
            int r1 = p000a.p001a.p017d.p018a.C0145j.Spinner_android_prompt
            java.lang.String r1 = r0.mo2281d(r1)
            r11.mo1683a((java.lang.CharSequence) r1)
            r12.mo2274a()
            r7.f1182g = r11
            android.support.v7.widget.B r12 = new android.support.v7.widget.B
            r12.<init>(r7, r7, r11)
            r7.f1179d = r12
        L_0x00aa:
            int r11 = p000a.p001a.p017d.p018a.C0145j.Spinner_android_entries
            java.lang.CharSequence[] r11 = r0.mo2285f(r11)
            if (r11 == 0) goto L_0x00c2
            android.widget.ArrayAdapter r12 = new android.widget.ArrayAdapter
            r1 = 17367048(0x1090008, float:2.5162948E-38)
            r12.<init>(r8, r1, r11)
            int r8 = p000a.p001a.p017d.p018a.C0142g.support_simple_spinner_dropdown_item
            r12.setDropDownViewResource(r8)
            r7.setAdapter((android.widget.SpinnerAdapter) r12)
        L_0x00c2:
            r0.mo2274a()
            r7.f1181f = r3
            android.widget.SpinnerAdapter r8 = r7.f1180e
            if (r8 == 0) goto L_0x00d0
            r7.setAdapter((android.widget.SpinnerAdapter) r8)
            r7.f1180e = r2
        L_0x00d0:
            android.support.v7.widget.j r8 = r7.f1177b
            r8.mo2170a(r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.widget.C0328C.<init>(android.content.Context, android.util.AttributeSet, int, int, android.content.res.Resources$Theme):void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public int mo1647a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int max = Math.max(0, getSelectedItemPosition());
        int min = Math.min(spinnerAdapter.getCount(), max + 15);
        View view = null;
        int i2 = 0;
        for (int max2 = Math.max(0, max - (15 - (min - max))); max2 < min; max2++) {
            int itemViewType = spinnerAdapter.getItemViewType(max2);
            if (itemViewType != i) {
                view = null;
                i = itemViewType;
            }
            view = spinnerAdapter.getView(max2, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            i2 = Math.max(i2, view.getMeasuredWidth());
        }
        if (drawable == null) {
            return i2;
        }
        drawable.getPadding(this.f1184i);
        Rect rect = this.f1184i;
        return i2 + rect.left + rect.right;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0412j jVar = this.f1177b;
        if (jVar != null) {
            jVar.mo2165a();
        }
    }

    public int getDropDownHorizontalOffset() {
        C0330b bVar = this.f1182g;
        if (bVar != null) {
            return bVar.mo2011g();
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getDropDownHorizontalOffset();
        }
        return 0;
    }

    public int getDropDownVerticalOffset() {
        C0330b bVar = this.f1182g;
        if (bVar != null) {
            return bVar.mo2013h();
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getDropDownVerticalOffset();
        }
        return 0;
    }

    public int getDropDownWidth() {
        if (this.f1182g != null) {
            return this.f1183h;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getDropDownWidth();
        }
        return 0;
    }

    public Drawable getPopupBackground() {
        C0330b bVar = this.f1182g;
        if (bVar != null) {
            return bVar.mo2009f();
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getPopupBackground();
        }
        return null;
    }

    public Context getPopupContext() {
        if (this.f1182g != null) {
            return this.f1178c;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            return super.getPopupContext();
        }
        return null;
    }

    public CharSequence getPrompt() {
        C0330b bVar = this.f1182g;
        return bVar != null ? bVar.mo1686m() : super.getPrompt();
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0412j jVar = this.f1177b;
        if (jVar != null) {
            return jVar.mo2171b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0412j jVar = this.f1177b;
        if (jVar != null) {
            return jVar.mo2173c();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        C0330b bVar = this.f1182g;
        if (bVar != null && bVar.mo1136b()) {
            this.f1182g.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.f1182g != null && View.MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), mo1647a(getAdapter(), getBackground())), View.MeasureSpec.getSize(i)), getMeasuredHeight());
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        C0354P p = this.f1179d;
        if (p == null || !p.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public boolean performClick() {
        C0330b bVar = this.f1182g;
        if (bVar == null) {
            return super.performClick();
        }
        if (bVar.mo1136b()) {
            return true;
        }
        this.f1182g.mo1137c();
        return true;
    }

    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.f1181f) {
            this.f1180e = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        if (this.f1182g != null) {
            Context context = this.f1178c;
            if (context == null) {
                context = getContext();
            }
            this.f1182g.mo1682a((ListAdapter) new C0329a(spinnerAdapter, context.getTheme()));
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0412j jVar = this.f1177b;
        if (jVar != null) {
            jVar.mo2169a(drawable);
        }
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        C0412j jVar = this.f1177b;
        if (jVar != null) {
            jVar.mo2166a(i);
        }
    }

    public void setDropDownHorizontalOffset(int i) {
        C0330b bVar = this.f1182g;
        if (bVar != null) {
            bVar.mo2006d(i);
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setDropDownHorizontalOffset(i);
        }
    }

    public void setDropDownVerticalOffset(int i) {
        C0330b bVar = this.f1182g;
        if (bVar != null) {
            bVar.mo2014h(i);
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setDropDownVerticalOffset(i);
        }
    }

    public void setDropDownWidth(int i) {
        if (this.f1182g != null) {
            this.f1183h = i;
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setDropDownWidth(i);
        }
    }

    public void setPopupBackgroundDrawable(Drawable drawable) {
        C0330b bVar = this.f1182g;
        if (bVar != null) {
            bVar.mo1998a(drawable);
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    public void setPopupBackgroundResource(int i) {
        setPopupBackgroundDrawable(C0146a.m492b(getPopupContext(), i));
    }

    public void setPrompt(CharSequence charSequence) {
        C0330b bVar = this.f1182g;
        if (bVar != null) {
            bVar.mo1683a(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0412j jVar = this.f1177b;
        if (jVar != null) {
            jVar.mo2172b(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0412j jVar = this.f1177b;
        if (jVar != null) {
            jVar.mo2168a(mode);
        }
    }
}

package android.support.p025v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p025v7.app.C0236a;
import android.support.p025v7.view.menu.C0278D;
import android.support.p025v7.view.menu.C0293l;
import android.support.p025v7.view.menu.C0299p;
import android.support.p025v7.view.menu.C0310v;
import android.support.p025v7.widget.ActionMenuView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import p000a.p001a.p005c.p014g.C0105c;
import p000a.p001a.p005c.p014g.C0111f;
import p000a.p001a.p005c.p014g.C0115i;
import p000a.p001a.p005c.p014g.C0127u;
import p000a.p001a.p017d.p018a.C0136a;
import p000a.p001a.p017d.p018a.C0145j;
import p000a.p001a.p017d.p019b.p020a.C0146a;
import p000a.p001a.p017d.p023d.C0166c;
import p000a.p001a.p017d.p023d.C0171g;

/* renamed from: android.support.v7.widget.Toolbar */
public class Toolbar extends ViewGroup {

    /* renamed from: A */
    private int f1370A;

    /* renamed from: B */
    private boolean f1371B;

    /* renamed from: C */
    private boolean f1372C;

    /* renamed from: D */
    private final ArrayList<View> f1373D;

    /* renamed from: E */
    private final ArrayList<View> f1374E;

    /* renamed from: F */
    private final int[] f1375F;

    /* renamed from: G */
    C0369c f1376G;

    /* renamed from: H */
    private final ActionMenuView.C0325e f1377H;

    /* renamed from: I */
    private C0318Aa f1378I;

    /* renamed from: J */
    private C0400g f1379J;

    /* renamed from: K */
    private C0367a f1380K;

    /* renamed from: L */
    private C0310v.C0311a f1381L;

    /* renamed from: M */
    private C0293l.C0294a f1382M;

    /* renamed from: N */
    private boolean f1383N;

    /* renamed from: O */
    private final Runnable f1384O;

    /* renamed from: a */
    private ActionMenuView f1385a;

    /* renamed from: b */
    private TextView f1386b;

    /* renamed from: c */
    private TextView f1387c;

    /* renamed from: d */
    private ImageButton f1388d;

    /* renamed from: e */
    private ImageView f1389e;

    /* renamed from: f */
    private Drawable f1390f;

    /* renamed from: g */
    private CharSequence f1391g;

    /* renamed from: h */
    ImageButton f1392h;

    /* renamed from: i */
    View f1393i;

    /* renamed from: j */
    private Context f1394j;

    /* renamed from: k */
    private int f1395k;

    /* renamed from: l */
    private int f1396l;

    /* renamed from: m */
    private int f1397m;

    /* renamed from: n */
    int f1398n;

    /* renamed from: o */
    private int f1399o;

    /* renamed from: p */
    private int f1400p;

    /* renamed from: q */
    private int f1401q;

    /* renamed from: r */
    private int f1402r;

    /* renamed from: s */
    private int f1403s;

    /* renamed from: t */
    private C0382Y f1404t;

    /* renamed from: u */
    private int f1405u;

    /* renamed from: v */
    private int f1406v;

    /* renamed from: w */
    private int f1407w;

    /* renamed from: x */
    private CharSequence f1408x;

    /* renamed from: y */
    private CharSequence f1409y;

    /* renamed from: z */
    private int f1410z;

    /* renamed from: android.support.v7.widget.Toolbar$a */
    private class C0367a implements C0310v {

        /* renamed from: a */
        C0293l f1411a;

        /* renamed from: b */
        C0299p f1412b;

        C0367a() {
        }

        /* renamed from: a */
        public void mo1225a(Context context, C0293l lVar) {
            C0299p pVar;
            C0293l lVar2 = this.f1411a;
            if (!(lVar2 == null || (pVar = this.f1412b) == null)) {
                lVar2.mo1146a(pVar);
            }
            this.f1411a = lVar;
        }

        /* renamed from: a */
        public void mo1127a(C0293l lVar, boolean z) {
        }

        /* renamed from: a */
        public void mo1131a(boolean z) {
            if (this.f1412b != null) {
                C0293l lVar = this.f1411a;
                boolean z2 = false;
                if (lVar != null) {
                    int size = lVar.size();
                    int i = 0;
                    while (true) {
                        if (i >= size) {
                            break;
                        } else if (this.f1411a.getItem(i) == this.f1412b) {
                            z2 = true;
                            break;
                        } else {
                            i++;
                        }
                    }
                }
                if (!z2) {
                    mo1233b(this.f1411a, this.f1412b);
                }
            }
        }

        /* renamed from: a */
        public boolean mo1132a() {
            return false;
        }

        /* renamed from: a */
        public boolean mo1133a(C0278D d) {
            return false;
        }

        /* renamed from: a */
        public boolean mo1229a(C0293l lVar, C0299p pVar) {
            Toolbar.this.mo1923e();
            ViewParent parent = Toolbar.this.f1392h.getParent();
            Toolbar toolbar = Toolbar.this;
            if (parent != toolbar) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(toolbar.f1392h);
                }
                Toolbar toolbar2 = Toolbar.this;
                toolbar2.addView(toolbar2.f1392h);
            }
            Toolbar.this.f1393i = pVar.getActionView();
            this.f1412b = pVar;
            ViewParent parent2 = Toolbar.this.f1393i.getParent();
            Toolbar toolbar3 = Toolbar.this;
            if (parent2 != toolbar3) {
                if (parent2 instanceof ViewGroup) {
                    ((ViewGroup) parent2).removeView(toolbar3.f1393i);
                }
                C0368b generateDefaultLayoutParams = Toolbar.this.generateDefaultLayoutParams();
                Toolbar toolbar4 = Toolbar.this;
                generateDefaultLayoutParams.f728a = 8388611 | (toolbar4.f1398n & 112);
                generateDefaultLayoutParams.f1414b = 2;
                toolbar4.f1393i.setLayoutParams(generateDefaultLayoutParams);
                Toolbar toolbar5 = Toolbar.this;
                toolbar5.addView(toolbar5.f1393i);
            }
            Toolbar.this.mo1957j();
            Toolbar.this.requestLayout();
            pVar.mo1337a(true);
            View view = Toolbar.this.f1393i;
            if (view instanceof C0166c) {
                ((C0166c) view).onActionViewExpanded();
            }
            return true;
        }

        /* renamed from: b */
        public boolean mo1233b(C0293l lVar, C0299p pVar) {
            View view = Toolbar.this.f1393i;
            if (view instanceof C0166c) {
                ((C0166c) view).onActionViewCollapsed();
            }
            Toolbar toolbar = Toolbar.this;
            toolbar.removeView(toolbar.f1393i);
            Toolbar toolbar2 = Toolbar.this;
            toolbar2.removeView(toolbar2.f1392h);
            Toolbar toolbar3 = Toolbar.this;
            toolbar3.f1393i = null;
            toolbar3.mo1914a();
            this.f1412b = null;
            Toolbar.this.requestLayout();
            pVar.mo1337a(false);
            return true;
        }
    }

    /* renamed from: android.support.v7.widget.Toolbar$b */
    public static class C0368b extends C0236a.C0237a {

        /* renamed from: b */
        int f1414b = 0;

        public C0368b(int i, int i2) {
            super(i, i2);
            this.f728a = 8388627;
        }

        public C0368b(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public C0368b(C0236a.C0237a aVar) {
            super(aVar);
        }

        public C0368b(C0368b bVar) {
            super((C0236a.C0237a) bVar);
            this.f1414b = bVar.f1414b;
        }

        public C0368b(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public C0368b(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super((ViewGroup.LayoutParams) marginLayoutParams);
            mo1992a(marginLayoutParams);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo1992a(ViewGroup.MarginLayoutParams marginLayoutParams) {
            this.leftMargin = marginLayoutParams.leftMargin;
            this.topMargin = marginLayoutParams.topMargin;
            this.rightMargin = marginLayoutParams.rightMargin;
            this.bottomMargin = marginLayoutParams.bottomMargin;
        }
    }

    /* renamed from: android.support.v7.widget.Toolbar$c */
    public interface C0369c {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    /* renamed from: android.support.v7.widget.Toolbar$d */
    public static class C0370d extends C0105c {
        public static final Parcelable.Creator<C0370d> CREATOR = new C0447xa();

        /* renamed from: c */
        int f1415c;

        /* renamed from: d */
        boolean f1416d;

        public C0370d(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1415c = parcel.readInt();
            this.f1416d = parcel.readInt() != 0;
        }

        public C0370d(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f1415c);
            parcel.writeInt(this.f1416d ? 1 : 0);
        }
    }

    public Toolbar(Context context) {
        this(context, (AttributeSet) null);
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0136a.toolbarStyle);
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1407w = 8388627;
        this.f1373D = new ArrayList<>();
        this.f1374E = new ArrayList<>();
        this.f1375F = new int[2];
        this.f1377H = new C0441ua(this);
        this.f1384O = new C0443va(this);
        C0439ta a = C0439ta.m1902a(getContext(), attributeSet, C0145j.Toolbar, i, 0);
        this.f1396l = a.mo2286g(C0145j.Toolbar_titleTextAppearance, 0);
        this.f1397m = a.mo2286g(C0145j.Toolbar_subtitleTextAppearance, 0);
        this.f1407w = a.mo2282e(C0145j.Toolbar_android_gravity, this.f1407w);
        this.f1398n = a.mo2282e(C0145j.Toolbar_buttonGravity, 48);
        int b = a.mo2276b(C0145j.Toolbar_titleMargin, 0);
        b = a.mo2287g(C0145j.Toolbar_titleMargins) ? a.mo2276b(C0145j.Toolbar_titleMargins, b) : b;
        this.f1403s = b;
        this.f1402r = b;
        this.f1401q = b;
        this.f1400p = b;
        int b2 = a.mo2276b(C0145j.Toolbar_titleMarginStart, -1);
        if (b2 >= 0) {
            this.f1400p = b2;
        }
        int b3 = a.mo2276b(C0145j.Toolbar_titleMarginEnd, -1);
        if (b3 >= 0) {
            this.f1401q = b3;
        }
        int b4 = a.mo2276b(C0145j.Toolbar_titleMarginTop, -1);
        if (b4 >= 0) {
            this.f1402r = b4;
        }
        int b5 = a.mo2276b(C0145j.Toolbar_titleMarginBottom, -1);
        if (b5 >= 0) {
            this.f1403s = b5;
        }
        this.f1399o = a.mo2278c(C0145j.Toolbar_maxButtonHeight, -1);
        int b6 = a.mo2276b(C0145j.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int b7 = a.mo2276b(C0145j.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int c = a.mo2278c(C0145j.Toolbar_contentInsetLeft, 0);
        int c2 = a.mo2278c(C0145j.Toolbar_contentInsetRight, 0);
        m1663l();
        this.f1404t.mo2090a(c, c2);
        if (!(b6 == Integer.MIN_VALUE && b7 == Integer.MIN_VALUE)) {
            this.f1404t.mo2093b(b6, b7);
        }
        this.f1405u = a.mo2276b(C0145j.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.f1406v = a.mo2276b(C0145j.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.f1390f = a.mo2277b(C0145j.Toolbar_collapseIcon);
        this.f1391g = a.mo2283e(C0145j.Toolbar_collapseContentDescription);
        CharSequence e = a.mo2283e(C0145j.Toolbar_title);
        if (!TextUtils.isEmpty(e)) {
            setTitle(e);
        }
        CharSequence e2 = a.mo2283e(C0145j.Toolbar_subtitle);
        if (!TextUtils.isEmpty(e2)) {
            setSubtitle(e2);
        }
        this.f1394j = getContext();
        setPopupTheme(a.mo2286g(C0145j.Toolbar_popupTheme, 0));
        Drawable b8 = a.mo2277b(C0145j.Toolbar_navigationIcon);
        if (b8 != null) {
            setNavigationIcon(b8);
        }
        CharSequence e3 = a.mo2283e(C0145j.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(e3)) {
            setNavigationContentDescription(e3);
        }
        Drawable b9 = a.mo2277b(C0145j.Toolbar_logo);
        if (b9 != null) {
            setLogo(b9);
        }
        CharSequence e4 = a.mo2283e(C0145j.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(e4)) {
            setLogoDescription(e4);
        }
        if (a.mo2287g(C0145j.Toolbar_titleTextColor)) {
            setTitleTextColor(a.mo2271a(C0145j.Toolbar_titleTextColor, -1));
        }
        if (a.mo2287g(C0145j.Toolbar_subtitleTextColor)) {
            setSubtitleTextColor(a.mo2271a(C0145j.Toolbar_subtitleTextColor, -1));
        }
        a.mo2274a();
    }

    /* renamed from: a */
    private int m1649a(int i) {
        int d = C0127u.m450d(this);
        int a = C0111f.m395a(i, d) & 7;
        return (a == 1 || a == 3 || a == 5) ? a : d == 1 ? 5 : 3;
    }

    /* renamed from: a */
    private int m1650a(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return C0115i.m406b(marginLayoutParams) + C0115i.m405a(marginLayoutParams);
    }

    /* renamed from: a */
    private int m1651a(View view, int i) {
        C0368b bVar = (C0368b) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i2 = i > 0 ? (measuredHeight - i) / 2 : 0;
        int b = m1658b(bVar.f728a);
        if (b == 48) {
            return getPaddingTop() - i2;
        }
        if (b == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - bVar.bottomMargin) - i2;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int i3 = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        int i4 = bVar.topMargin;
        if (i3 < i4) {
            i3 = i4;
        } else {
            int i5 = (((height - paddingBottom) - measuredHeight) - i3) - paddingTop;
            int i6 = bVar.bottomMargin;
            if (i5 < i6) {
                i3 = Math.max(0, i3 - (i6 - i5));
            }
        }
        return paddingTop + i3;
    }

    /* renamed from: a */
    private int m1652a(View view, int i, int i2, int i3, int i4, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i5 = marginLayoutParams.leftMargin - iArr[0];
        int i6 = marginLayoutParams.rightMargin - iArr[1];
        int max = Math.max(0, i5) + Math.max(0, i6);
        iArr[0] = Math.max(0, -i5);
        iArr[1] = Math.max(0, -i6);
        view.measure(ViewGroup.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + max + i2, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height));
        return view.getMeasuredWidth() + max;
    }

    /* renamed from: a */
    private int m1653a(View view, int i, int[] iArr, int i2) {
        C0368b bVar = (C0368b) view.getLayoutParams();
        int i3 = bVar.leftMargin - iArr[0];
        int max = i + Math.max(0, i3);
        iArr[0] = Math.max(0, -i3);
        int a = m1651a(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max, a, max + measuredWidth, view.getMeasuredHeight() + a);
        return max + measuredWidth + bVar.rightMargin;
    }

    /* renamed from: a */
    private int m1654a(List<View> list, int[] iArr) {
        int i = iArr[0];
        int i2 = iArr[1];
        int size = list.size();
        int i3 = i2;
        int i4 = i;
        int i5 = 0;
        int i6 = 0;
        while (i5 < size) {
            View view = list.get(i5);
            C0368b bVar = (C0368b) view.getLayoutParams();
            int i7 = bVar.leftMargin - i4;
            int i8 = bVar.rightMargin - i3;
            int max = Math.max(0, i7);
            int max2 = Math.max(0, i8);
            int max3 = Math.max(0, -i7);
            int max4 = Math.max(0, -i8);
            i6 += max + view.getMeasuredWidth() + max2;
            i5++;
            i3 = max4;
            i4 = max3;
        }
        return i6;
    }

    /* renamed from: a */
    private void m1655a(View view, int i, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i5 >= 0) {
            if (mode != 0) {
                i5 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i5);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    /* renamed from: a */
    private void m1656a(View view, boolean z) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        C0368b generateDefaultLayoutParams = layoutParams == null ? generateDefaultLayoutParams() : !checkLayoutParams(layoutParams) ? generateLayoutParams(layoutParams) : (C0368b) layoutParams;
        generateDefaultLayoutParams.f1414b = 1;
        if (!z || this.f1393i == null) {
            addView(view, generateDefaultLayoutParams);
            return;
        }
        view.setLayoutParams(generateDefaultLayoutParams);
        this.f1374E.add(view);
    }

    /* renamed from: a */
    private void m1657a(List<View> list, int i) {
        boolean z = C0127u.m450d(this) == 1;
        int childCount = getChildCount();
        int a = C0111f.m395a(i, C0127u.m450d(this));
        list.clear();
        if (z) {
            for (int i2 = childCount - 1; i2 >= 0; i2--) {
                View childAt = getChildAt(i2);
                C0368b bVar = (C0368b) childAt.getLayoutParams();
                if (bVar.f1414b == 0 && m1662d(childAt) && m1649a(bVar.f728a) == a) {
                    list.add(childAt);
                }
            }
            return;
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt2 = getChildAt(i3);
            C0368b bVar2 = (C0368b) childAt2.getLayoutParams();
            if (bVar2.f1414b == 0 && m1662d(childAt2) && m1649a(bVar2.f728a) == a) {
                list.add(childAt2);
            }
        }
    }

    /* renamed from: b */
    private int m1658b(int i) {
        int i2 = i & 112;
        return (i2 == 16 || i2 == 48 || i2 == 80) ? i2 : this.f1407w & 112;
    }

    /* renamed from: b */
    private int m1659b(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    /* renamed from: b */
    private int m1660b(View view, int i, int[] iArr, int i2) {
        C0368b bVar = (C0368b) view.getLayoutParams();
        int i3 = bVar.rightMargin - iArr[1];
        int max = i - Math.max(0, i3);
        iArr[1] = Math.max(0, -i3);
        int a = m1651a(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max - measuredWidth, a, max, view.getMeasuredHeight() + a);
        return max - (measuredWidth + bVar.leftMargin);
    }

    /* renamed from: c */
    private boolean m1661c(View view) {
        return view.getParent() == this || this.f1374E.contains(view);
    }

    /* renamed from: d */
    private boolean m1662d(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    private MenuInflater getMenuInflater() {
        return new C0171g(getContext());
    }

    /* renamed from: l */
    private void m1663l() {
        if (this.f1404t == null) {
            this.f1404t = new C0382Y();
        }
    }

    /* renamed from: m */
    private void m1664m() {
        if (this.f1389e == null) {
            this.f1389e = new C0438t(getContext());
        }
    }

    /* renamed from: n */
    private void m1665n() {
        m1666o();
        if (this.f1385a.mo1624g() == null) {
            C0293l lVar = (C0293l) this.f1385a.getMenu();
            if (this.f1380K == null) {
                this.f1380K = new C0367a();
            }
            this.f1385a.setExpandedActionViewsExclusive(true);
            lVar.mo1276a((C0310v) this.f1380K, this.f1394j);
        }
    }

    /* renamed from: o */
    private void m1666o() {
        if (this.f1385a == null) {
            this.f1385a = new ActionMenuView(getContext());
            this.f1385a.setPopupTheme(this.f1395k);
            this.f1385a.setOnMenuItemClickListener(this.f1377H);
            this.f1385a.mo1615a(this.f1381L, this.f1382M);
            C0368b generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.f728a = 8388613 | (this.f1398n & 112);
            this.f1385a.setLayoutParams(generateDefaultLayoutParams);
            m1656a((View) this.f1385a, false);
        }
    }

    /* renamed from: p */
    private void m1667p() {
        if (this.f1388d == null) {
            this.f1388d = new C0434r(getContext(), (AttributeSet) null, C0136a.toolbarNavigationButtonStyle);
            C0368b generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.f728a = 8388611 | (this.f1398n & 112);
            this.f1388d.setLayoutParams(generateDefaultLayoutParams);
        }
    }

    /* renamed from: q */
    private void m1668q() {
        removeCallbacks(this.f1384O);
        post(this.f1384O);
    }

    /* renamed from: r */
    private boolean m1669r() {
        if (!this.f1383N) {
            return false;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (m1662d(childAt) && childAt.getMeasuredWidth() > 0 && childAt.getMeasuredHeight() > 0) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1914a() {
        for (int size = this.f1374E.size() - 1; size >= 0; size--) {
            addView(this.f1374E.get(size));
        }
        this.f1374E.clear();
    }

    /* renamed from: a */
    public void mo1915a(int i, int i2) {
        m1663l();
        this.f1404t.mo2093b(i, i2);
    }

    /* renamed from: a */
    public void mo1916a(Context context, int i) {
        this.f1397m = i;
        TextView textView = this.f1387c;
        if (textView != null) {
            textView.setTextAppearance(context, i);
        }
    }

    /* renamed from: a */
    public void mo1917a(C0293l lVar, C0400g gVar) {
        if (lVar != null || this.f1385a != null) {
            m1666o();
            C0293l g = this.f1385a.mo1624g();
            if (g != lVar) {
                if (g != null) {
                    g.mo1294b((C0310v) this.f1379J);
                    g.mo1294b((C0310v) this.f1380K);
                }
                if (this.f1380K == null) {
                    this.f1380K = new C0367a();
                }
                gVar.mo2144b(true);
                if (lVar != null) {
                    lVar.mo1276a((C0310v) gVar, this.f1394j);
                    lVar.mo1276a((C0310v) this.f1380K, this.f1394j);
                } else {
                    gVar.mo1225a(this.f1394j, (C0293l) null);
                    this.f1380K.mo1225a(this.f1394j, (C0293l) null);
                    gVar.mo1131a(true);
                    this.f1380K.mo1131a(true);
                }
                this.f1385a.setPopupTheme(this.f1395k);
                this.f1385a.setPresenter(gVar);
                this.f1379J = gVar;
            }
        }
    }

    /* renamed from: b */
    public void mo1918b(Context context, int i) {
        this.f1396l = i;
        TextView textView = this.f1386b;
        if (textView != null) {
            textView.setTextAppearance(context, i);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r1.f1385a;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo1919b() {
        /*
            r1 = this;
            int r0 = r1.getVisibility()
            if (r0 != 0) goto L_0x0012
            android.support.v7.widget.ActionMenuView r0 = r1.f1385a
            if (r0 == 0) goto L_0x0012
            boolean r0 = r0.mo1623f()
            if (r0 == 0) goto L_0x0012
            r0 = 1
            goto L_0x0013
        L_0x0012:
            r0 = 0
        L_0x0013:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.widget.Toolbar.mo1919b():boolean");
    }

    /* renamed from: c */
    public void mo1920c() {
        C0367a aVar = this.f1380K;
        C0299p pVar = aVar == null ? null : aVar.f1412b;
        if (pVar != null) {
            pVar.collapseActionView();
        }
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof C0368b);
    }

    /* renamed from: d */
    public void mo1922d() {
        ActionMenuView actionMenuView = this.f1385a;
        if (actionMenuView != null) {
            actionMenuView.mo1614a();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public void mo1923e() {
        if (this.f1392h == null) {
            this.f1392h = new C0434r(getContext(), (AttributeSet) null, C0136a.toolbarNavigationButtonStyle);
            this.f1392h.setImageDrawable(this.f1390f);
            this.f1392h.setContentDescription(this.f1391g);
            C0368b generateDefaultLayoutParams = generateDefaultLayoutParams();
            generateDefaultLayoutParams.f728a = 8388611 | (this.f1398n & 112);
            generateDefaultLayoutParams.f1414b = 2;
            this.f1392h.setLayoutParams(generateDefaultLayoutParams);
            this.f1392h.setOnClickListener(new C0445wa(this));
        }
    }

    /* renamed from: f */
    public boolean mo1924f() {
        C0367a aVar = this.f1380K;
        return (aVar == null || aVar.f1412b == null) ? false : true;
    }

    /* renamed from: g */
    public boolean mo1925g() {
        ActionMenuView actionMenuView = this.f1385a;
        return actionMenuView != null && actionMenuView.mo1617c();
    }

    /* access modifiers changed from: protected */
    public C0368b generateDefaultLayoutParams() {
        return new C0368b(-2, -2);
    }

    public C0368b generateLayoutParams(AttributeSet attributeSet) {
        return new C0368b(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public C0368b generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C0368b ? new C0368b((C0368b) layoutParams) : layoutParams instanceof C0236a.C0237a ? new C0368b((C0236a.C0237a) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new C0368b((ViewGroup.MarginLayoutParams) layoutParams) : new C0368b(layoutParams);
    }

    public int getContentInsetEnd() {
        C0382Y y = this.f1404t;
        if (y != null) {
            return y.mo2089a();
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i = this.f1406v;
        return i != Integer.MIN_VALUE ? i : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        C0382Y y = this.f1404t;
        if (y != null) {
            return y.mo2092b();
        }
        return 0;
    }

    public int getContentInsetRight() {
        C0382Y y = this.f1404t;
        if (y != null) {
            return y.mo2094c();
        }
        return 0;
    }

    public int getContentInsetStart() {
        C0382Y y = this.f1404t;
        if (y != null) {
            return y.mo2095d();
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i = this.f1405u;
        return i != Integer.MIN_VALUE ? i : getContentInsetStart();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0005, code lost:
        r0 = r0.mo1624g();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getCurrentContentInsetEnd() {
        /*
            r3 = this;
            android.support.v7.widget.ActionMenuView r0 = r3.f1385a
            r1 = 0
            if (r0 == 0) goto L_0x0013
            android.support.v7.view.menu.l r0 = r0.mo1624g()
            if (r0 == 0) goto L_0x0013
            boolean r0 = r0.hasVisibleItems()
            if (r0 == 0) goto L_0x0013
            r0 = 1
            goto L_0x0014
        L_0x0013:
            r0 = 0
        L_0x0014:
            if (r0 == 0) goto L_0x0025
            int r0 = r3.getContentInsetEnd()
            int r2 = r3.f1406v
            int r1 = java.lang.Math.max(r2, r1)
            int r0 = java.lang.Math.max(r0, r1)
            goto L_0x0029
        L_0x0025:
            int r0 = r3.getContentInsetEnd()
        L_0x0029:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.widget.Toolbar.getCurrentContentInsetEnd():int");
    }

    public int getCurrentContentInsetLeft() {
        return C0127u.m450d(this) == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        return C0127u.m450d(this) == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.f1405u, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        ImageView imageView = this.f1389e;
        if (imageView != null) {
            return imageView.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        ImageView imageView = this.f1389e;
        if (imageView != null) {
            return imageView.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        m1665n();
        return this.f1385a.getMenu();
    }

    public CharSequence getNavigationContentDescription() {
        ImageButton imageButton = this.f1388d;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        ImageButton imageButton = this.f1388d;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public C0400g getOuterActionMenuPresenter() {
        return this.f1379J;
    }

    public Drawable getOverflowIcon() {
        m1665n();
        return this.f1385a.getOverflowIcon();
    }

    /* access modifiers changed from: package-private */
    public Context getPopupContext() {
        return this.f1394j;
    }

    public int getPopupTheme() {
        return this.f1395k;
    }

    public CharSequence getSubtitle() {
        return this.f1409y;
    }

    public CharSequence getTitle() {
        return this.f1408x;
    }

    public int getTitleMarginBottom() {
        return this.f1403s;
    }

    public int getTitleMarginEnd() {
        return this.f1401q;
    }

    public int getTitleMarginStart() {
        return this.f1400p;
    }

    public int getTitleMarginTop() {
        return this.f1402r;
    }

    public C0347L getWrapper() {
        if (this.f1378I == null) {
            this.f1378I = new C0318Aa(this, true);
        }
        return this.f1378I;
    }

    /* renamed from: h */
    public boolean mo1955h() {
        ActionMenuView actionMenuView = this.f1385a;
        return actionMenuView != null && actionMenuView.mo1619d();
    }

    /* renamed from: i */
    public boolean mo1956i() {
        ActionMenuView actionMenuView = this.f1385a;
        return actionMenuView != null && actionMenuView.mo1622e();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: j */
    public void mo1957j() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (!(((C0368b) childAt.getLayoutParams()).f1414b == 2 || childAt == this.f1385a)) {
                removeViewAt(childCount);
                this.f1374E.add(childAt);
            }
        }
    }

    /* renamed from: k */
    public boolean mo1958k() {
        ActionMenuView actionMenuView = this.f1385a;
        return actionMenuView != null && actionMenuView.mo1632h();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.f1384O);
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.f1372C = false;
        }
        if (!this.f1372C) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.f1372C = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.f1372C = false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x02a6 A[LOOP:0: B:101:0x02a4->B:102:0x02a6, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x02c8 A[LOOP:1: B:104:0x02c6->B:105:0x02c8, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x02f3  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x0302 A[LOOP:2: B:112:0x0300->B:113:0x0302, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x011d  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x012d  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0134  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0137  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x016a  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x01a8  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01b9  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x022c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayout(boolean r20, int r21, int r22, int r23, int r24) {
        /*
            r19 = this;
            r0 = r19
            int r1 = p000a.p001a.p005c.p014g.C0127u.m450d(r19)
            r2 = 1
            r3 = 0
            if (r1 != r2) goto L_0x000c
            r1 = 1
            goto L_0x000d
        L_0x000c:
            r1 = 0
        L_0x000d:
            int r4 = r19.getWidth()
            int r5 = r19.getHeight()
            int r6 = r19.getPaddingLeft()
            int r7 = r19.getPaddingRight()
            int r8 = r19.getPaddingTop()
            int r9 = r19.getPaddingBottom()
            int r10 = r4 - r7
            int[] r11 = r0.f1375F
            r11[r2] = r3
            r11[r3] = r3
            int r12 = p000a.p001a.p005c.p014g.C0127u.m451e(r19)
            if (r12 < 0) goto L_0x003a
            int r13 = r24 - r22
            int r12 = java.lang.Math.min(r12, r13)
            goto L_0x003b
        L_0x003a:
            r12 = 0
        L_0x003b:
            android.widget.ImageButton r13 = r0.f1388d
            boolean r13 = r0.m1662d(r13)
            if (r13 == 0) goto L_0x0055
            if (r1 == 0) goto L_0x004e
            android.widget.ImageButton r13 = r0.f1388d
            int r13 = r0.m1660b(r13, r10, r11, r12)
            r14 = r13
            r13 = r6
            goto L_0x0057
        L_0x004e:
            android.widget.ImageButton r13 = r0.f1388d
            int r13 = r0.m1653a(r13, r6, r11, r12)
            goto L_0x0056
        L_0x0055:
            r13 = r6
        L_0x0056:
            r14 = r10
        L_0x0057:
            android.widget.ImageButton r15 = r0.f1392h
            boolean r15 = r0.m1662d(r15)
            if (r15 == 0) goto L_0x006e
            if (r1 == 0) goto L_0x0068
            android.widget.ImageButton r15 = r0.f1392h
            int r14 = r0.m1660b(r15, r14, r11, r12)
            goto L_0x006e
        L_0x0068:
            android.widget.ImageButton r15 = r0.f1392h
            int r13 = r0.m1653a(r15, r13, r11, r12)
        L_0x006e:
            android.support.v7.widget.ActionMenuView r15 = r0.f1385a
            boolean r15 = r0.m1662d(r15)
            if (r15 == 0) goto L_0x0085
            if (r1 == 0) goto L_0x007f
            android.support.v7.widget.ActionMenuView r15 = r0.f1385a
            int r13 = r0.m1653a(r15, r13, r11, r12)
            goto L_0x0085
        L_0x007f:
            android.support.v7.widget.ActionMenuView r15 = r0.f1385a
            int r14 = r0.m1660b(r15, r14, r11, r12)
        L_0x0085:
            int r15 = r19.getCurrentContentInsetLeft()
            int r16 = r19.getCurrentContentInsetRight()
            int r2 = r15 - r13
            int r2 = java.lang.Math.max(r3, r2)
            r11[r3] = r2
            int r2 = r10 - r14
            int r2 = r16 - r2
            int r2 = java.lang.Math.max(r3, r2)
            r17 = 1
            r11[r17] = r2
            int r2 = java.lang.Math.max(r13, r15)
            int r10 = r10 - r16
            int r10 = java.lang.Math.min(r14, r10)
            android.view.View r13 = r0.f1393i
            boolean r13 = r0.m1662d(r13)
            if (r13 == 0) goto L_0x00c2
            if (r1 == 0) goto L_0x00bc
            android.view.View r13 = r0.f1393i
            int r10 = r0.m1660b(r13, r10, r11, r12)
            goto L_0x00c2
        L_0x00bc:
            android.view.View r13 = r0.f1393i
            int r2 = r0.m1653a(r13, r2, r11, r12)
        L_0x00c2:
            android.widget.ImageView r13 = r0.f1389e
            boolean r13 = r0.m1662d(r13)
            if (r13 == 0) goto L_0x00d9
            if (r1 == 0) goto L_0x00d3
            android.widget.ImageView r13 = r0.f1389e
            int r10 = r0.m1660b(r13, r10, r11, r12)
            goto L_0x00d9
        L_0x00d3:
            android.widget.ImageView r13 = r0.f1389e
            int r2 = r0.m1653a(r13, r2, r11, r12)
        L_0x00d9:
            android.widget.TextView r13 = r0.f1386b
            boolean r13 = r0.m1662d(r13)
            android.widget.TextView r14 = r0.f1387c
            boolean r14 = r0.m1662d(r14)
            if (r13 == 0) goto L_0x0100
            android.widget.TextView r15 = r0.f1386b
            android.view.ViewGroup$LayoutParams r15 = r15.getLayoutParams()
            android.support.v7.widget.Toolbar$b r15 = (android.support.p025v7.widget.Toolbar.C0368b) r15
            int r3 = r15.topMargin
            r23 = r7
            android.widget.TextView r7 = r0.f1386b
            int r7 = r7.getMeasuredHeight()
            int r3 = r3 + r7
            int r7 = r15.bottomMargin
            int r3 = r3 + r7
            r7 = 0
            int r3 = r3 + r7
            goto L_0x0103
        L_0x0100:
            r23 = r7
            r3 = 0
        L_0x0103:
            if (r14 == 0) goto L_0x011d
            android.widget.TextView r7 = r0.f1387c
            android.view.ViewGroup$LayoutParams r7 = r7.getLayoutParams()
            android.support.v7.widget.Toolbar$b r7 = (android.support.p025v7.widget.Toolbar.C0368b) r7
            int r15 = r7.topMargin
            r16 = r4
            android.widget.TextView r4 = r0.f1387c
            int r4 = r4.getMeasuredHeight()
            int r15 = r15 + r4
            int r4 = r7.bottomMargin
            int r15 = r15 + r4
            int r3 = r3 + r15
            goto L_0x011f
        L_0x011d:
            r16 = r4
        L_0x011f:
            if (r13 != 0) goto L_0x012b
            if (r14 == 0) goto L_0x0124
            goto L_0x012b
        L_0x0124:
            r17 = r6
            r22 = r12
        L_0x0128:
            r7 = 0
            goto L_0x0296
        L_0x012b:
            if (r13 == 0) goto L_0x0130
            android.widget.TextView r4 = r0.f1386b
            goto L_0x0132
        L_0x0130:
            android.widget.TextView r4 = r0.f1387c
        L_0x0132:
            if (r14 == 0) goto L_0x0137
            android.widget.TextView r7 = r0.f1387c
            goto L_0x0139
        L_0x0137:
            android.widget.TextView r7 = r0.f1386b
        L_0x0139:
            android.view.ViewGroup$LayoutParams r4 = r4.getLayoutParams()
            android.support.v7.widget.Toolbar$b r4 = (android.support.p025v7.widget.Toolbar.C0368b) r4
            android.view.ViewGroup$LayoutParams r7 = r7.getLayoutParams()
            android.support.v7.widget.Toolbar$b r7 = (android.support.p025v7.widget.Toolbar.C0368b) r7
            if (r13 == 0) goto L_0x014f
            android.widget.TextView r15 = r0.f1386b
            int r15 = r15.getMeasuredWidth()
            if (r15 > 0) goto L_0x0159
        L_0x014f:
            if (r14 == 0) goto L_0x015d
            android.widget.TextView r15 = r0.f1387c
            int r15 = r15.getMeasuredWidth()
            if (r15 <= 0) goto L_0x015d
        L_0x0159:
            r17 = r6
            r15 = 1
            goto L_0x0160
        L_0x015d:
            r17 = r6
            r15 = 0
        L_0x0160:
            int r6 = r0.f1407w
            r6 = r6 & 112(0x70, float:1.57E-43)
            r22 = r12
            r12 = 48
            if (r6 == r12) goto L_0x01a8
            r12 = 80
            if (r6 == r12) goto L_0x019a
            int r6 = r5 - r8
            int r6 = r6 - r9
            int r6 = r6 - r3
            int r6 = r6 / 2
            int r12 = r4.topMargin
            r24 = r2
            int r2 = r0.f1402r
            r18 = r14
            int r14 = r12 + r2
            if (r6 >= r14) goto L_0x0183
            int r6 = r12 + r2
            goto L_0x0198
        L_0x0183:
            int r5 = r5 - r9
            int r5 = r5 - r3
            int r5 = r5 - r6
            int r5 = r5 - r8
            int r2 = r4.bottomMargin
            int r3 = r0.f1403s
            int r2 = r2 + r3
            if (r5 >= r2) goto L_0x0198
            int r2 = r7.bottomMargin
            int r2 = r2 + r3
            int r2 = r2 - r5
            int r6 = r6 - r2
            r2 = 0
            int r6 = java.lang.Math.max(r2, r6)
        L_0x0198:
            int r8 = r8 + r6
            goto L_0x01b7
        L_0x019a:
            r24 = r2
            r18 = r14
            int r5 = r5 - r9
            int r2 = r7.bottomMargin
            int r5 = r5 - r2
            int r2 = r0.f1403s
            int r5 = r5 - r2
            int r8 = r5 - r3
            goto L_0x01b7
        L_0x01a8:
            r24 = r2
            r18 = r14
            int r2 = r19.getPaddingTop()
            int r3 = r4.topMargin
            int r2 = r2 + r3
            int r3 = r0.f1402r
            int r8 = r2 + r3
        L_0x01b7:
            if (r1 == 0) goto L_0x022c
            if (r15 == 0) goto L_0x01bf
            int r3 = r0.f1400p
            r1 = 1
            goto L_0x01c1
        L_0x01bf:
            r1 = 1
            r3 = 0
        L_0x01c1:
            r2 = r11[r1]
            int r3 = r3 - r2
            r2 = 0
            int r4 = java.lang.Math.max(r2, r3)
            int r10 = r10 - r4
            int r3 = -r3
            int r3 = java.lang.Math.max(r2, r3)
            r11[r1] = r3
            if (r13 == 0) goto L_0x01f7
            android.widget.TextView r1 = r0.f1386b
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            android.support.v7.widget.Toolbar$b r1 = (android.support.p025v7.widget.Toolbar.C0368b) r1
            android.widget.TextView r2 = r0.f1386b
            int r2 = r2.getMeasuredWidth()
            int r2 = r10 - r2
            android.widget.TextView r3 = r0.f1386b
            int r3 = r3.getMeasuredHeight()
            int r3 = r3 + r8
            android.widget.TextView r4 = r0.f1386b
            r4.layout(r2, r8, r10, r3)
            int r4 = r0.f1401q
            int r2 = r2 - r4
            int r1 = r1.bottomMargin
            int r8 = r3 + r1
            goto L_0x01f8
        L_0x01f7:
            r2 = r10
        L_0x01f8:
            if (r18 == 0) goto L_0x0220
            android.widget.TextView r1 = r0.f1387c
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            android.support.v7.widget.Toolbar$b r1 = (android.support.p025v7.widget.Toolbar.C0368b) r1
            int r3 = r1.topMargin
            int r8 = r8 + r3
            android.widget.TextView r3 = r0.f1387c
            int r3 = r3.getMeasuredWidth()
            int r3 = r10 - r3
            android.widget.TextView r4 = r0.f1387c
            int r4 = r4.getMeasuredHeight()
            int r4 = r4 + r8
            android.widget.TextView r5 = r0.f1387c
            r5.layout(r3, r8, r10, r4)
            int r3 = r0.f1401q
            int r3 = r10 - r3
            int r1 = r1.bottomMargin
            goto L_0x0221
        L_0x0220:
            r3 = r10
        L_0x0221:
            if (r15 == 0) goto L_0x0228
            int r1 = java.lang.Math.min(r2, r3)
            r10 = r1
        L_0x0228:
            r2 = r24
            goto L_0x0128
        L_0x022c:
            if (r15 == 0) goto L_0x0231
            int r3 = r0.f1400p
            goto L_0x0232
        L_0x0231:
            r3 = 0
        L_0x0232:
            r7 = 0
            r1 = r11[r7]
            int r3 = r3 - r1
            int r1 = java.lang.Math.max(r7, r3)
            int r2 = r24 + r1
            int r1 = -r3
            int r1 = java.lang.Math.max(r7, r1)
            r11[r7] = r1
            if (r13 == 0) goto L_0x0268
            android.widget.TextView r1 = r0.f1386b
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            android.support.v7.widget.Toolbar$b r1 = (android.support.p025v7.widget.Toolbar.C0368b) r1
            android.widget.TextView r3 = r0.f1386b
            int r3 = r3.getMeasuredWidth()
            int r3 = r3 + r2
            android.widget.TextView r4 = r0.f1386b
            int r4 = r4.getMeasuredHeight()
            int r4 = r4 + r8
            android.widget.TextView r5 = r0.f1386b
            r5.layout(r2, r8, r3, r4)
            int r5 = r0.f1401q
            int r3 = r3 + r5
            int r1 = r1.bottomMargin
            int r8 = r4 + r1
            goto L_0x0269
        L_0x0268:
            r3 = r2
        L_0x0269:
            if (r18 == 0) goto L_0x028f
            android.widget.TextView r1 = r0.f1387c
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            android.support.v7.widget.Toolbar$b r1 = (android.support.p025v7.widget.Toolbar.C0368b) r1
            int r4 = r1.topMargin
            int r8 = r8 + r4
            android.widget.TextView r4 = r0.f1387c
            int r4 = r4.getMeasuredWidth()
            int r4 = r4 + r2
            android.widget.TextView r5 = r0.f1387c
            int r5 = r5.getMeasuredHeight()
            int r5 = r5 + r8
            android.widget.TextView r6 = r0.f1387c
            r6.layout(r2, r8, r4, r5)
            int r5 = r0.f1401q
            int r4 = r4 + r5
            int r1 = r1.bottomMargin
            goto L_0x0290
        L_0x028f:
            r4 = r2
        L_0x0290:
            if (r15 == 0) goto L_0x0296
            int r2 = java.lang.Math.max(r3, r4)
        L_0x0296:
            java.util.ArrayList<android.view.View> r1 = r0.f1373D
            r3 = 3
            r0.m1657a((java.util.List<android.view.View>) r1, (int) r3)
            java.util.ArrayList<android.view.View> r1 = r0.f1373D
            int r1 = r1.size()
            r3 = r2
            r2 = 0
        L_0x02a4:
            if (r2 >= r1) goto L_0x02b7
            java.util.ArrayList<android.view.View> r4 = r0.f1373D
            java.lang.Object r4 = r4.get(r2)
            android.view.View r4 = (android.view.View) r4
            r12 = r22
            int r3 = r0.m1653a(r4, r3, r11, r12)
            int r2 = r2 + 1
            goto L_0x02a4
        L_0x02b7:
            r12 = r22
            java.util.ArrayList<android.view.View> r1 = r0.f1373D
            r2 = 5
            r0.m1657a((java.util.List<android.view.View>) r1, (int) r2)
            java.util.ArrayList<android.view.View> r1 = r0.f1373D
            int r1 = r1.size()
            r2 = 0
        L_0x02c6:
            if (r2 >= r1) goto L_0x02d7
            java.util.ArrayList<android.view.View> r4 = r0.f1373D
            java.lang.Object r4 = r4.get(r2)
            android.view.View r4 = (android.view.View) r4
            int r10 = r0.m1660b(r4, r10, r11, r12)
            int r2 = r2 + 1
            goto L_0x02c6
        L_0x02d7:
            java.util.ArrayList<android.view.View> r1 = r0.f1373D
            r2 = 1
            r0.m1657a((java.util.List<android.view.View>) r1, (int) r2)
            java.util.ArrayList<android.view.View> r1 = r0.f1373D
            int r1 = r0.m1654a((java.util.List<android.view.View>) r1, (int[]) r11)
            int r4 = r16 - r17
            int r4 = r4 - r23
            int r4 = r4 / 2
            int r6 = r17 + r4
            int r2 = r1 / 2
            int r2 = r6 - r2
            int r1 = r1 + r2
            if (r2 >= r3) goto L_0x02f3
            goto L_0x02fa
        L_0x02f3:
            if (r1 <= r10) goto L_0x02f9
            int r1 = r1 - r10
            int r3 = r2 - r1
            goto L_0x02fa
        L_0x02f9:
            r3 = r2
        L_0x02fa:
            java.util.ArrayList<android.view.View> r1 = r0.f1373D
            int r1 = r1.size()
        L_0x0300:
            if (r7 >= r1) goto L_0x0311
            java.util.ArrayList<android.view.View> r2 = r0.f1373D
            java.lang.Object r2 = r2.get(r7)
            android.view.View r2 = (android.view.View) r2
            int r3 = r0.m1653a(r2, r3, r11, r12)
            int r7 = r7 + 1
            goto L_0x0300
        L_0x0311:
            java.util.ArrayList<android.view.View> r1 = r0.f1373D
            r1.clear()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        char c;
        char c2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int[] iArr = this.f1375F;
        if (C0342Ha.m1498a(this)) {
            c2 = 1;
            c = 0;
        } else {
            c2 = 0;
            c = 1;
        }
        if (m1662d(this.f1388d)) {
            m1655a((View) this.f1388d, i, 0, i2, 0, this.f1399o);
            i5 = this.f1388d.getMeasuredWidth() + m1650a((View) this.f1388d);
            i4 = Math.max(0, this.f1388d.getMeasuredHeight() + m1659b((View) this.f1388d));
            i3 = View.combineMeasuredStates(0, this.f1388d.getMeasuredState());
        } else {
            i5 = 0;
            i4 = 0;
            i3 = 0;
        }
        if (m1662d(this.f1392h)) {
            m1655a((View) this.f1392h, i, 0, i2, 0, this.f1399o);
            i5 = this.f1392h.getMeasuredWidth() + m1650a((View) this.f1392h);
            i4 = Math.max(i4, this.f1392h.getMeasuredHeight() + m1659b((View) this.f1392h));
            i3 = View.combineMeasuredStates(i3, this.f1392h.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int max = 0 + Math.max(currentContentInsetStart, i5);
        iArr[c2] = Math.max(0, currentContentInsetStart - i5);
        if (m1662d(this.f1385a)) {
            m1655a((View) this.f1385a, i, max, i2, 0, this.f1399o);
            i6 = this.f1385a.getMeasuredWidth() + m1650a((View) this.f1385a);
            i4 = Math.max(i4, this.f1385a.getMeasuredHeight() + m1659b((View) this.f1385a));
            i3 = View.combineMeasuredStates(i3, this.f1385a.getMeasuredState());
        } else {
            i6 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int max2 = max + Math.max(currentContentInsetEnd, i6);
        iArr[c] = Math.max(0, currentContentInsetEnd - i6);
        if (m1662d(this.f1393i)) {
            max2 += m1652a(this.f1393i, i, max2, i2, 0, iArr);
            i4 = Math.max(i4, this.f1393i.getMeasuredHeight() + m1659b(this.f1393i));
            i3 = View.combineMeasuredStates(i3, this.f1393i.getMeasuredState());
        }
        if (m1662d(this.f1389e)) {
            max2 += m1652a((View) this.f1389e, i, max2, i2, 0, iArr);
            i4 = Math.max(i4, this.f1389e.getMeasuredHeight() + m1659b((View) this.f1389e));
            i3 = View.combineMeasuredStates(i3, this.f1389e.getMeasuredState());
        }
        int childCount = getChildCount();
        int i10 = i4;
        int i11 = max2;
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            if (((C0368b) childAt.getLayoutParams()).f1414b == 0 && m1662d(childAt)) {
                i11 += m1652a(childAt, i, i11, i2, 0, iArr);
                i10 = Math.max(i10, childAt.getMeasuredHeight() + m1659b(childAt));
                i3 = View.combineMeasuredStates(i3, childAt.getMeasuredState());
            }
        }
        int i13 = this.f1402r + this.f1403s;
        int i14 = this.f1400p + this.f1401q;
        if (m1662d(this.f1386b)) {
            m1652a((View) this.f1386b, i, i11 + i14, i2, i13, iArr);
            int measuredWidth = this.f1386b.getMeasuredWidth() + m1650a((View) this.f1386b);
            i7 = this.f1386b.getMeasuredHeight() + m1659b((View) this.f1386b);
            i9 = View.combineMeasuredStates(i3, this.f1386b.getMeasuredState());
            i8 = measuredWidth;
        } else {
            i9 = i3;
            i8 = 0;
            i7 = 0;
        }
        if (m1662d(this.f1387c)) {
            int i15 = i7 + i13;
            i8 = Math.max(i8, m1652a((View) this.f1387c, i, i11 + i14, i2, i15, iArr));
            i7 += this.f1387c.getMeasuredHeight() + m1659b((View) this.f1387c);
            i9 = View.combineMeasuredStates(i9, this.f1387c.getMeasuredState());
        } else {
            int i16 = i9;
        }
        int max3 = Math.max(i10, i7);
        int paddingLeft = i11 + i8 + getPaddingLeft() + getPaddingRight();
        int paddingTop = max3 + getPaddingTop() + getPaddingBottom();
        int resolveSizeAndState = View.resolveSizeAndState(Math.max(paddingLeft, getSuggestedMinimumWidth()), i, -16777216 & i9);
        int resolveSizeAndState2 = View.resolveSizeAndState(Math.max(paddingTop, getSuggestedMinimumHeight()), i2, i9 << 16);
        if (m1669r()) {
            resolveSizeAndState2 = 0;
        }
        setMeasuredDimension(resolveSizeAndState, resolveSizeAndState2);
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem findItem;
        if (!(parcelable instanceof C0370d)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        C0370d dVar = (C0370d) parcelable;
        super.onRestoreInstanceState(dVar.mo430a());
        ActionMenuView actionMenuView = this.f1385a;
        C0293l g = actionMenuView != null ? actionMenuView.mo1624g() : null;
        int i = dVar.f1415c;
        if (!(i == 0 || this.f1380K == null || g == null || (findItem = g.findItem(i)) == null)) {
            findItem.expandActionView();
        }
        if (dVar.f1416d) {
            m1668q();
        }
    }

    public void onRtlPropertiesChanged(int i) {
        if (Build.VERSION.SDK_INT >= 17) {
            super.onRtlPropertiesChanged(i);
        }
        m1663l();
        C0382Y y = this.f1404t;
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        y.mo2091a(z);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        C0299p pVar;
        C0370d dVar = new C0370d(super.onSaveInstanceState());
        C0367a aVar = this.f1380K;
        if (!(aVar == null || (pVar = aVar.f1412b) == null)) {
            dVar.f1415c = pVar.getItemId();
        }
        dVar.f1416d = mo1956i();
        return dVar;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f1371B = false;
        }
        if (!this.f1371B) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.f1371B = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.f1371B = false;
        }
        return true;
    }

    public void setCollapsible(boolean z) {
        this.f1383N = z;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.f1406v) {
            this.f1406v = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i) {
        if (i < 0) {
            i = Integer.MIN_VALUE;
        }
        if (i != this.f1405u) {
            this.f1405u = i;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setLogo(int i) {
        setLogo(C0146a.m492b(getContext(), i));
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            m1664m();
            if (!m1661c(this.f1389e)) {
                m1656a((View) this.f1389e, true);
            }
        } else {
            ImageView imageView = this.f1389e;
            if (imageView != null && m1661c(imageView)) {
                removeView(this.f1389e);
                this.f1374E.remove(this.f1389e);
            }
        }
        ImageView imageView2 = this.f1389e;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(int i) {
        setLogoDescription(getContext().getText(i));
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            m1664m();
        }
        ImageView imageView = this.f1389e;
        if (imageView != null) {
            imageView.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(int i) {
        setNavigationContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            m1667p();
        }
        ImageButton imageButton = this.f1388d;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(int i) {
        setNavigationIcon(C0146a.m492b(getContext(), i));
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            m1667p();
            if (!m1661c(this.f1388d)) {
                m1656a((View) this.f1388d, true);
            }
        } else {
            ImageButton imageButton = this.f1388d;
            if (imageButton != null && m1661c(imageButton)) {
                removeView(this.f1388d);
                this.f1374E.remove(this.f1388d);
            }
        }
        ImageButton imageButton2 = this.f1388d;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
        }
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        m1667p();
        this.f1388d.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(C0369c cVar) {
        this.f1376G = cVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        m1665n();
        this.f1385a.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i) {
        if (this.f1395k != i) {
            this.f1395k = i;
            if (i == 0) {
                this.f1394j = getContext();
            } else {
                this.f1394j = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public void setSubtitle(int i) {
        setSubtitle(getContext().getText(i));
    }

    public void setSubtitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.f1387c == null) {
                Context context = getContext();
                this.f1387c = new C0343I(context);
                this.f1387c.setSingleLine();
                this.f1387c.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.f1397m;
                if (i != 0) {
                    this.f1387c.setTextAppearance(context, i);
                }
                int i2 = this.f1370A;
                if (i2 != 0) {
                    this.f1387c.setTextColor(i2);
                }
            }
            if (!m1661c(this.f1387c)) {
                m1656a((View) this.f1387c, true);
            }
        } else {
            TextView textView = this.f1387c;
            if (textView != null && m1661c(textView)) {
                removeView(this.f1387c);
                this.f1374E.remove(this.f1387c);
            }
        }
        TextView textView2 = this.f1387c;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.f1409y = charSequence;
    }

    public void setSubtitleTextColor(int i) {
        this.f1370A = i;
        TextView textView = this.f1387c;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }

    public void setTitle(int i) {
        setTitle(getContext().getText(i));
    }

    public void setTitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.f1386b == null) {
                Context context = getContext();
                this.f1386b = new C0343I(context);
                this.f1386b.setSingleLine();
                this.f1386b.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.f1396l;
                if (i != 0) {
                    this.f1386b.setTextAppearance(context, i);
                }
                int i2 = this.f1410z;
                if (i2 != 0) {
                    this.f1386b.setTextColor(i2);
                }
            }
            if (!m1661c(this.f1386b)) {
                m1656a((View) this.f1386b, true);
            }
        } else {
            TextView textView = this.f1386b;
            if (textView != null && m1661c(textView)) {
                removeView(this.f1386b);
                this.f1374E.remove(this.f1386b);
            }
        }
        TextView textView2 = this.f1386b;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.f1408x = charSequence;
    }

    public void setTitleMarginBottom(int i) {
        this.f1403s = i;
        requestLayout();
    }

    public void setTitleMarginEnd(int i) {
        this.f1401q = i;
        requestLayout();
    }

    public void setTitleMarginStart(int i) {
        this.f1400p = i;
        requestLayout();
    }

    public void setTitleMarginTop(int i) {
        this.f1402r = i;
        requestLayout();
    }

    public void setTitleTextColor(int i) {
        this.f1410z = i;
        TextView textView = this.f1386b;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }
}

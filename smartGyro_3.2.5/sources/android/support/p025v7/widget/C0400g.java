package android.support.p025v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.p024v4.graphics.drawable.C0190a;
import android.support.p025v7.view.menu.ActionMenuItemView;
import android.support.p025v7.view.menu.C0278D;
import android.support.p025v7.view.menu.C0281b;
import android.support.p025v7.view.menu.C0293l;
import android.support.p025v7.view.menu.C0299p;
import android.support.p025v7.view.menu.C0309u;
import android.support.p025v7.view.menu.C0310v;
import android.support.p025v7.view.menu.C0312w;
import android.support.p025v7.view.menu.C0316z;
import android.support.p025v7.widget.ActionMenuView;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import p000a.p001a.p005c.p014g.C0108e;
import p000a.p001a.p017d.p018a.C0136a;
import p000a.p001a.p017d.p018a.C0142g;
import p000a.p001a.p017d.p023d.C0163a;

/* renamed from: android.support.v7.widget.g */
class C0400g extends C0281b implements C0108e.C0109a {

    /* renamed from: A */
    C0401a f1520A;

    /* renamed from: B */
    C0403c f1521B;

    /* renamed from: C */
    private C0402b f1522C;

    /* renamed from: D */
    final C0406f f1523D = new C0406f();

    /* renamed from: E */
    int f1524E;

    /* renamed from: k */
    C0404d f1525k;

    /* renamed from: l */
    private Drawable f1526l;

    /* renamed from: m */
    private boolean f1527m;

    /* renamed from: n */
    private boolean f1528n;

    /* renamed from: o */
    private boolean f1529o;

    /* renamed from: p */
    private int f1530p;

    /* renamed from: q */
    private int f1531q;

    /* renamed from: r */
    private int f1532r;

    /* renamed from: s */
    private boolean f1533s;

    /* renamed from: t */
    private boolean f1534t;

    /* renamed from: u */
    private boolean f1535u;

    /* renamed from: v */
    private boolean f1536v;

    /* renamed from: w */
    private int f1537w;

    /* renamed from: x */
    private final SparseBooleanArray f1538x = new SparseBooleanArray();

    /* renamed from: y */
    private View f1539y;

    /* renamed from: z */
    C0405e f1540z;

    /* renamed from: android.support.v7.widget.g$a */
    private class C0401a extends C0309u {
        public C0401a(Context context, C0278D d, View view) {
            super(context, d, view, false, C0136a.actionOverflowMenuStyle);
            if (!((C0299p) d.getItem()).mo1360h()) {
                View view2 = C0400g.this.f1525k;
                mo1457a(view2 == null ? (View) C0400g.this.f934i : view2);
            }
            mo1456a((C0310v.C0311a) C0400g.this.f1523D);
        }

        /* access modifiers changed from: protected */
        /* renamed from: d */
        public void mo1463d() {
            C0400g gVar = C0400g.this;
            gVar.f1520A = null;
            gVar.f1524E = 0;
            super.mo1463d();
        }
    }

    /* renamed from: android.support.v7.widget.g$b */
    private class C0402b extends ActionMenuItemView.C0275b {
        C0402b() {
        }

        /* renamed from: a */
        public C0316z mo1122a() {
            C0401a aVar = C0400g.this.f1520A;
            if (aVar != null) {
                return aVar.mo1461b();
            }
            return null;
        }
    }

    /* renamed from: android.support.v7.widget.g$c */
    private class C0403c implements Runnable {

        /* renamed from: a */
        private C0405e f1543a;

        public C0403c(C0405e eVar) {
            this.f1543a = eVar;
        }

        public void run() {
            if (C0400g.this.f928c != null) {
                C0400g.this.f928c.mo1273a();
            }
            View view = (View) C0400g.this.f934i;
            if (!(view == null || view.getWindowToken() == null || !this.f1543a.mo1465f())) {
                C0400g.this.f1540z = this.f1543a;
            }
            C0400g.this.f1521B = null;
        }
    }

    /* renamed from: android.support.v7.widget.g$d */
    private class C0404d extends C0438t implements ActionMenuView.C0321a {

        /* renamed from: c */
        private final float[] f1545c = new float[2];

        public C0404d(Context context) {
            super(context, (AttributeSet) null, C0136a.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            C0327Ba.m1444a(this, getContentDescription());
            setOnTouchListener(new C0408h(this, this, C0400g.this));
        }

        /* renamed from: a */
        public boolean mo1102a() {
            return false;
        }

        /* renamed from: b */
        public boolean mo1103b() {
            return false;
        }

        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            C0400g.this.mo2152i();
            return true;
        }

        /* access modifiers changed from: protected */
        public boolean setFrame(int i, int i2, int i3, int i4) {
            boolean frame = super.setFrame(i, i2, i3, i4);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (!(drawable == null || background == null)) {
                int width = getWidth();
                int height = getHeight();
                int max = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                C0190a.m673a(background, paddingLeft - max, paddingTop - max, paddingLeft + max, paddingTop + max);
            }
            return frame;
        }
    }

    /* renamed from: android.support.v7.widget.g$e */
    private class C0405e extends C0309u {
        public C0405e(Context context, C0293l lVar, View view, boolean z) {
            super(context, lVar, view, z, C0136a.actionOverflowMenuStyle);
            mo1455a(8388613);
            mo1456a((C0310v.C0311a) C0400g.this.f1523D);
        }

        /* access modifiers changed from: protected */
        /* renamed from: d */
        public void mo1463d() {
            if (C0400g.this.f928c != null) {
                C0400g.this.f928c.close();
            }
            C0400g.this.f1540z = null;
            super.mo1463d();
        }
    }

    /* renamed from: android.support.v7.widget.g$f */
    private class C0406f implements C0310v.C0311a {
        C0406f() {
        }

        /* renamed from: a */
        public void mo1072a(C0293l lVar, boolean z) {
            if (lVar instanceof C0278D) {
                lVar.mo1150m().mo1279a(false);
            }
            C0310v.C0311a b = C0400g.this.mo1231b();
            if (b != null) {
                b.mo1072a(lVar, z);
            }
        }

        /* renamed from: a */
        public boolean mo1073a(C0293l lVar) {
            if (lVar == null) {
                return false;
            }
            C0400g.this.f1524E = ((C0278D) lVar).getItem().getItemId();
            C0310v.C0311a b = C0400g.this.mo1231b();
            if (b != null) {
                return b.mo1073a(lVar);
            }
            return false;
        }
    }

    public C0400g(Context context) {
        super(context, C0142g.abc_action_menu_layout, C0142g.abc_action_menu_item_layout);
    }

    /* renamed from: a */
    private View m1766a(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.f934i;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if ((childAt instanceof C0312w.C0313a) && ((C0312w.C0313a) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    /* renamed from: a */
    public View mo1223a(C0299p pVar, View view, ViewGroup viewGroup) {
        View actionView = pVar.getActionView();
        if (actionView == null || pVar.mo1346f()) {
            actionView = super.mo1223a(pVar, view, viewGroup);
        }
        actionView.setVisibility(pVar.isActionViewExpanded() ? 8 : 0);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    /* renamed from: a */
    public void mo1225a(Context context, C0293l lVar) {
        super.mo1225a(context, lVar);
        Resources resources = context.getResources();
        C0163a a = C0163a.m577a(context);
        if (!this.f1529o) {
            this.f1528n = a.mo645g();
        }
        if (!this.f1535u) {
            this.f1530p = a.mo640b();
        }
        if (!this.f1533s) {
            this.f1532r = a.mo641c();
        }
        int i = this.f1530p;
        if (this.f1528n) {
            if (this.f1525k == null) {
                this.f1525k = new C0404d(this.f926a);
                if (this.f1527m) {
                    this.f1525k.setImageDrawable(this.f1526l);
                    this.f1526l = null;
                    this.f1527m = false;
                }
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.f1525k.measure(makeMeasureSpec, makeMeasureSpec);
            }
            i -= this.f1525k.getMeasuredWidth();
        } else {
            this.f1525k = null;
        }
        this.f1531q = i;
        this.f1537w = (int) (resources.getDisplayMetrics().density * 56.0f);
        this.f1539y = null;
    }

    /* renamed from: a */
    public void mo2141a(Configuration configuration) {
        if (!this.f1533s) {
            this.f1532r = C0163a.m577a(this.f927b).mo641c();
        }
        C0293l lVar = this.f928c;
        if (lVar != null) {
            lVar.mo1295b(true);
        }
    }

    /* renamed from: a */
    public void mo2142a(Drawable drawable) {
        C0404d dVar = this.f1525k;
        if (dVar != null) {
            dVar.setImageDrawable(drawable);
            return;
        }
        this.f1527m = true;
        this.f1526l = drawable;
    }

    /* renamed from: a */
    public void mo1127a(C0293l lVar, boolean z) {
        mo2146c();
        super.mo1127a(lVar, z);
    }

    /* renamed from: a */
    public void mo1226a(C0299p pVar, C0312w.C0313a aVar) {
        aVar.mo1101a(pVar, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) aVar;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.f934i);
        if (this.f1522C == null) {
            this.f1522C = new C0402b();
        }
        actionMenuItemView.setPopupCallback(this.f1522C);
    }

    /* renamed from: a */
    public void mo2143a(ActionMenuView actionMenuView) {
        this.f934i = actionMenuView;
        actionMenuView.mo1174a(this.f928c);
    }

    /* renamed from: a */
    public void mo1131a(boolean z) {
        C0312w wVar;
        super.mo1131a(z);
        ((View) this.f934i).requestLayout();
        C0293l lVar = this.f928c;
        boolean z2 = false;
        if (lVar != null) {
            ArrayList<C0299p> c = lVar.mo1297c();
            int size = c.size();
            for (int i = 0; i < size; i++) {
                C0108e a = c.get(i).mo195a();
                if (a != null) {
                    a.mo453a((C0108e.C0109a) this);
                }
            }
        }
        C0293l lVar2 = this.f928c;
        ArrayList<C0299p> j = lVar2 != null ? lVar2.mo1315j() : null;
        if (this.f1528n && j != null) {
            int size2 = j.size();
            if (size2 == 1) {
                z2 = !j.get(0).isActionViewExpanded();
            } else if (size2 > 0) {
                z2 = true;
            }
        }
        if (z2) {
            if (this.f1525k == null) {
                this.f1525k = new C0404d(this.f926a);
            }
            ViewGroup viewGroup = (ViewGroup) this.f1525k.getParent();
            if (viewGroup != this.f934i) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.f1525k);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.f934i;
                actionMenuView.addView(this.f1525k, actionMenuView.mo1616b());
            }
        } else {
            C0404d dVar = this.f1525k;
            if (dVar != null && dVar.getParent() == (wVar = this.f934i)) {
                ((ViewGroup) wVar).removeView(this.f1525k);
            }
        }
        ((ActionMenuView) this.f934i).setOverflowReserved(this.f1528n);
    }

    /* renamed from: a */
    public boolean mo1132a() {
        int i;
        ArrayList<C0299p> arrayList;
        int i2;
        int i3;
        int i4;
        boolean z;
        C0400g gVar = this;
        C0293l lVar = gVar.f928c;
        int i5 = 0;
        if (lVar != null) {
            arrayList = lVar.mo1318n();
            i = arrayList.size();
        } else {
            arrayList = null;
            i = 0;
        }
        int i6 = gVar.f1532r;
        int i7 = gVar.f1531q;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) gVar.f934i;
        int i8 = i6;
        boolean z2 = false;
        int i9 = 0;
        int i10 = 0;
        for (int i11 = 0; i11 < i; i11++) {
            C0299p pVar = arrayList.get(i11);
            if (pVar.mo1368k()) {
                i9++;
            } else if (pVar.mo1367j()) {
                i10++;
            } else {
                z2 = true;
            }
            if (gVar.f1536v && pVar.isActionViewExpanded()) {
                i8 = 0;
            }
        }
        if (gVar.f1528n && (z2 || i10 + i9 > i8)) {
            i8--;
        }
        int i12 = i8 - i9;
        SparseBooleanArray sparseBooleanArray = gVar.f1538x;
        sparseBooleanArray.clear();
        if (gVar.f1534t) {
            int i13 = gVar.f1537w;
            i2 = i7 / i13;
            i3 = i13 + ((i7 % i13) / i2);
        } else {
            i3 = 0;
            i2 = 0;
        }
        int i14 = i7;
        int i15 = 0;
        int i16 = 0;
        while (i15 < i) {
            C0299p pVar2 = arrayList.get(i15);
            if (pVar2.mo1368k()) {
                View a = gVar.mo1223a(pVar2, gVar.f1539y, viewGroup);
                if (gVar.f1539y == null) {
                    gVar.f1539y = a;
                }
                if (gVar.f1534t) {
                    i2 -= ActionMenuView.m1418a(a, i3, i2, makeMeasureSpec, i5);
                } else {
                    a.measure(makeMeasureSpec, makeMeasureSpec);
                }
                int measuredWidth = a.getMeasuredWidth();
                i14 -= measuredWidth;
                if (i16 != 0) {
                    measuredWidth = i16;
                }
                int groupId = pVar2.getGroupId();
                if (groupId != 0) {
                    z = true;
                    sparseBooleanArray.put(groupId, true);
                } else {
                    z = true;
                }
                pVar2.mo1343d(z);
                i4 = i;
                i16 = measuredWidth;
            } else if (pVar2.mo1367j()) {
                int groupId2 = pVar2.getGroupId();
                boolean z3 = sparseBooleanArray.get(groupId2);
                boolean z4 = (i12 > 0 || z3) && i14 > 0 && (!gVar.f1534t || i2 > 0);
                if (z4) {
                    boolean z5 = z4;
                    View a2 = gVar.mo1223a(pVar2, gVar.f1539y, viewGroup);
                    i4 = i;
                    if (gVar.f1539y == null) {
                        gVar.f1539y = a2;
                    }
                    if (gVar.f1534t) {
                        int a3 = ActionMenuView.m1418a(a2, i3, i2, makeMeasureSpec, 0);
                        i2 -= a3;
                        if (a3 == 0) {
                            z5 = false;
                        }
                    } else {
                        a2.measure(makeMeasureSpec, makeMeasureSpec);
                    }
                    int measuredWidth2 = a2.getMeasuredWidth();
                    i14 -= measuredWidth2;
                    if (i16 == 0) {
                        i16 = measuredWidth2;
                    }
                    z4 = z5 & (!gVar.f1534t ? i14 + i16 > 0 : i14 >= 0);
                } else {
                    boolean z6 = z4;
                    i4 = i;
                }
                if (z4 && groupId2 != 0) {
                    sparseBooleanArray.put(groupId2, true);
                } else if (z3) {
                    sparseBooleanArray.put(groupId2, false);
                    int i17 = 0;
                    while (i17 < i15) {
                        C0299p pVar3 = arrayList.get(i17);
                        if (pVar3.getGroupId() == groupId2) {
                            if (pVar3.mo1360h()) {
                                i12++;
                            }
                            pVar3.mo1343d(false);
                        }
                        i17++;
                    }
                }
                if (z4) {
                    i12--;
                }
                pVar2.mo1343d(z4);
            } else {
                i4 = i;
                pVar2.mo1343d(false);
                i15++;
                i5 = 0;
                gVar = this;
                i = i4;
            }
            i15++;
            i5 = 0;
            gVar = this;
            i = i4;
        }
        return true;
    }

    /* renamed from: a */
    public boolean mo1228a(int i, C0299p pVar) {
        return pVar.mo1360h();
    }

    /* renamed from: a */
    public boolean mo1133a(C0278D d) {
        boolean z = false;
        if (!d.hasVisibleItems()) {
            return false;
        }
        C0278D d2 = d;
        while (d2.mo1163t() != this.f928c) {
            d2 = (C0278D) d2.mo1163t();
        }
        View a = m1766a(d2.getItem());
        if (a == null) {
            return false;
        }
        this.f1524E = d.getItem().getItemId();
        int size = d.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            MenuItem item = d.getItem(i);
            if (item.isVisible() && item.getIcon() != null) {
                z = true;
                break;
            }
            i++;
        }
        this.f1520A = new C0401a(this.f927b, d, a);
        this.f1520A.mo1459a(z);
        this.f1520A.mo1464e();
        super.mo1133a(d);
        return true;
    }

    /* renamed from: a */
    public boolean mo1230a(ViewGroup viewGroup, int i) {
        if (viewGroup.getChildAt(i) == this.f1525k) {
            return false;
        }
        return super.mo1230a(viewGroup, i);
    }

    /* renamed from: b */
    public C0312w mo1232b(ViewGroup viewGroup) {
        C0312w wVar = this.f934i;
        C0312w b = super.mo1232b(viewGroup);
        if (wVar != b) {
            ((ActionMenuView) b).setPresenter(this);
        }
        return b;
    }

    /* renamed from: b */
    public void mo2144b(boolean z) {
        this.f1536v = z;
    }

    /* renamed from: c */
    public void mo2145c(boolean z) {
        this.f1528n = z;
        this.f1529o = true;
    }

    /* renamed from: c */
    public boolean mo2146c() {
        return mo2148e() | mo2149f();
    }

    /* renamed from: d */
    public Drawable mo2147d() {
        C0404d dVar = this.f1525k;
        if (dVar != null) {
            return dVar.getDrawable();
        }
        if (this.f1527m) {
            return this.f1526l;
        }
        return null;
    }

    /* renamed from: e */
    public boolean mo2148e() {
        C0312w wVar;
        C0403c cVar = this.f1521B;
        if (cVar == null || (wVar = this.f934i) == null) {
            C0405e eVar = this.f1540z;
            if (eVar == null) {
                return false;
            }
            eVar.mo1454a();
            return true;
        }
        ((View) wVar).removeCallbacks(cVar);
        this.f1521B = null;
        return true;
    }

    /* renamed from: f */
    public boolean mo2149f() {
        C0401a aVar = this.f1520A;
        if (aVar == null) {
            return false;
        }
        aVar.mo1454a();
        return true;
    }

    /* renamed from: g */
    public boolean mo2150g() {
        return this.f1521B != null || mo2151h();
    }

    /* renamed from: h */
    public boolean mo2151h() {
        C0405e eVar = this.f1540z;
        return eVar != null && eVar.mo1462c();
    }

    /* renamed from: i */
    public boolean mo2152i() {
        C0293l lVar;
        if (!this.f1528n || mo2151h() || (lVar = this.f928c) == null || this.f934i == null || this.f1521B != null || lVar.mo1315j().isEmpty()) {
            return false;
        }
        this.f1521B = new C0403c(new C0405e(this.f927b, this.f928c, this.f1525k, true));
        ((View) this.f934i).post(this.f1521B);
        super.mo1133a((C0278D) null);
        return true;
    }
}

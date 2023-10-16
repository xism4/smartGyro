package android.support.p025v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p025v7.view.menu.C0293l;
import android.support.p025v7.view.menu.C0310v;
import android.support.p025v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import p000a.p001a.p005c.p014g.C0092A;
import p000a.p001a.p005c.p014g.C0127u;
import p000a.p001a.p005c.p014g.C0134z;
import p000a.p001a.p017d.p018a.C0136a;
import p000a.p001a.p017d.p018a.C0140e;
import p000a.p001a.p017d.p018a.C0141f;
import p000a.p001a.p017d.p018a.C0143h;
import p000a.p001a.p017d.p018a.C0145j;
import p000a.p001a.p017d.p019b.p020a.C0146a;

/* renamed from: android.support.v7.widget.Aa */
public class C0318Aa implements C0347L {

    /* renamed from: a */
    Toolbar f1085a;

    /* renamed from: b */
    private int f1086b;

    /* renamed from: c */
    private View f1087c;

    /* renamed from: d */
    private View f1088d;

    /* renamed from: e */
    private Drawable f1089e;

    /* renamed from: f */
    private Drawable f1090f;

    /* renamed from: g */
    private Drawable f1091g;

    /* renamed from: h */
    private boolean f1092h;

    /* renamed from: i */
    CharSequence f1093i;

    /* renamed from: j */
    private CharSequence f1094j;

    /* renamed from: k */
    private CharSequence f1095k;

    /* renamed from: l */
    Window.Callback f1096l;

    /* renamed from: m */
    boolean f1097m;

    /* renamed from: n */
    private C0400g f1098n;

    /* renamed from: o */
    private int f1099o;

    /* renamed from: p */
    private int f1100p;

    /* renamed from: q */
    private Drawable f1101q;

    public C0318Aa(Toolbar toolbar, boolean z) {
        this(toolbar, z, C0143h.abc_action_bar_up_description, C0140e.abc_ic_ab_back_material);
    }

    public C0318Aa(Toolbar toolbar, boolean z, int i, int i2) {
        Drawable drawable;
        this.f1099o = 0;
        this.f1100p = 0;
        this.f1085a = toolbar;
        this.f1093i = toolbar.getTitle();
        this.f1094j = toolbar.getSubtitle();
        this.f1092h = this.f1093i != null;
        this.f1091g = toolbar.getNavigationIcon();
        C0439ta a = C0439ta.m1902a(toolbar.getContext(), (AttributeSet) null, C0145j.ActionBar, C0136a.actionBarStyle, 0);
        this.f1101q = a.mo2277b(C0145j.ActionBar_homeAsUpIndicator);
        if (z) {
            CharSequence e = a.mo2283e(C0145j.ActionBar_title);
            if (!TextUtils.isEmpty(e)) {
                mo1510c(e);
            }
            CharSequence e2 = a.mo2283e(C0145j.ActionBar_subtitle);
            if (!TextUtils.isEmpty(e2)) {
                mo1507b(e2);
            }
            Drawable b = a.mo2277b(C0145j.ActionBar_logo);
            if (b != null) {
                mo1497a(b);
            }
            Drawable b2 = a.mo2277b(C0145j.ActionBar_icon);
            if (b2 != null) {
                setIcon(b2);
            }
            if (this.f1091g == null && (drawable = this.f1101q) != null) {
                mo1506b(drawable);
            }
            mo1496a(a.mo2280d(C0145j.ActionBar_displayOptions, 0));
            int g = a.mo2286g(C0145j.ActionBar_customNavigationLayout, 0);
            if (g != 0) {
                mo1500a(LayoutInflater.from(this.f1085a.getContext()).inflate(g, this.f1085a, false));
                mo1496a(this.f1086b | 16);
            }
            int f = a.mo2284f(C0145j.ActionBar_height, 0);
            if (f > 0) {
                ViewGroup.LayoutParams layoutParams = this.f1085a.getLayoutParams();
                layoutParams.height = f;
                this.f1085a.setLayoutParams(layoutParams);
            }
            int b3 = a.mo2276b(C0145j.ActionBar_contentInsetStart, -1);
            int b4 = a.mo2276b(C0145j.ActionBar_contentInsetEnd, -1);
            if (b3 >= 0 || b4 >= 0) {
                this.f1085a.mo1915a(Math.max(b3, 0), Math.max(b4, 0));
            }
            int g2 = a.mo2286g(C0145j.ActionBar_titleTextStyle, 0);
            if (g2 != 0) {
                Toolbar toolbar2 = this.f1085a;
                toolbar2.mo1918b(toolbar2.getContext(), g2);
            }
            int g3 = a.mo2286g(C0145j.ActionBar_subtitleTextStyle, 0);
            if (g3 != 0) {
                Toolbar toolbar3 = this.f1085a;
                toolbar3.mo1916a(toolbar3.getContext(), g3);
            }
            int g4 = a.mo2286g(C0145j.ActionBar_popupTheme, 0);
            if (g4 != 0) {
                this.f1085a.setPopupTheme(g4);
            }
        } else {
            this.f1086b = m1350o();
        }
        a.mo2274a();
        mo1513d(i);
        this.f1095k = this.f1085a.getNavigationContentDescription();
        this.f1085a.setNavigationOnClickListener(new C0449ya(this));
    }

    /* renamed from: d */
    private void m1349d(CharSequence charSequence) {
        this.f1093i = charSequence;
        if ((this.f1086b & 8) != 0) {
            this.f1085a.setTitle(charSequence);
        }
    }

    /* renamed from: o */
    private int m1350o() {
        if (this.f1085a.getNavigationIcon() == null) {
            return 11;
        }
        this.f1101q = this.f1085a.getNavigationIcon();
        return 15;
    }

    /* renamed from: p */
    private void m1351p() {
        if ((this.f1086b & 4) == 0) {
            return;
        }
        if (TextUtils.isEmpty(this.f1095k)) {
            this.f1085a.setNavigationContentDescription(this.f1100p);
        } else {
            this.f1085a.setNavigationContentDescription(this.f1095k);
        }
    }

    /* renamed from: q */
    private void m1352q() {
        Drawable drawable;
        Toolbar toolbar;
        if ((this.f1086b & 4) != 0) {
            toolbar = this.f1085a;
            drawable = this.f1091g;
            if (drawable == null) {
                drawable = this.f1101q;
            }
        } else {
            toolbar = this.f1085a;
            drawable = null;
        }
        toolbar.setNavigationIcon(drawable);
    }

    /* renamed from: r */
    private void m1353r() {
        Drawable drawable;
        int i = this.f1086b;
        if ((i & 2) == 0) {
            drawable = null;
        } else if ((i & 1) == 0 || (drawable = this.f1090f) == null) {
            drawable = this.f1089e;
        }
        this.f1085a.setLogo(drawable);
    }

    /* renamed from: a */
    public C0134z mo1495a(int i, long j) {
        C0134z a = C0127u.m436a(this.f1085a);
        a.mo503a(i == 0 ? 1.0f : 0.0f);
        a.mo504a(j);
        a.mo505a((C0092A) new C0451za(this, i));
        return a;
    }

    /* renamed from: a */
    public void mo1496a(int i) {
        View view;
        CharSequence charSequence;
        Toolbar toolbar;
        int i2 = this.f1086b ^ i;
        this.f1086b = i;
        if (i2 != 0) {
            if ((i2 & 4) != 0) {
                if ((i & 4) != 0) {
                    m1351p();
                }
                m1352q();
            }
            if ((i2 & 3) != 0) {
                m1353r();
            }
            if ((i2 & 8) != 0) {
                if ((i & 8) != 0) {
                    this.f1085a.setTitle(this.f1093i);
                    toolbar = this.f1085a;
                    charSequence = this.f1094j;
                } else {
                    charSequence = null;
                    this.f1085a.setTitle((CharSequence) null);
                    toolbar = this.f1085a;
                }
                toolbar.setSubtitle(charSequence);
            }
            if ((i2 & 16) != 0 && (view = this.f1088d) != null) {
                if ((i & 16) != 0) {
                    this.f1085a.addView(view);
                } else {
                    this.f1085a.removeView(view);
                }
            }
        }
    }

    /* renamed from: a */
    public void mo1497a(Drawable drawable) {
        this.f1090f = drawable;
        m1353r();
    }

    /* renamed from: a */
    public void mo1498a(C0386aa aaVar) {
        Toolbar toolbar;
        View view = this.f1087c;
        if (view != null && view.getParent() == (toolbar = this.f1085a)) {
            toolbar.removeView(this.f1087c);
        }
        this.f1087c = aaVar;
        if (aaVar != null && this.f1099o == 2) {
            this.f1085a.addView(this.f1087c, 0);
            Toolbar.C0368b bVar = (Toolbar.C0368b) this.f1087c.getLayoutParams();
            bVar.width = -2;
            bVar.height = -2;
            bVar.f728a = 8388691;
            aaVar.setAllowCollapse(true);
        }
    }

    /* renamed from: a */
    public void mo1499a(Menu menu, C0310v.C0311a aVar) {
        if (this.f1098n == null) {
            this.f1098n = new C0400g(this.f1085a.getContext());
            this.f1098n.mo1224a(C0141f.action_menu_presenter);
        }
        this.f1098n.mo1128a(aVar);
        this.f1085a.mo1917a((C0293l) menu, this.f1098n);
    }

    /* renamed from: a */
    public void mo1500a(View view) {
        View view2 = this.f1088d;
        if (!(view2 == null || (this.f1086b & 16) == 0)) {
            this.f1085a.removeView(view2);
        }
        this.f1088d = view;
        if (view != null && (this.f1086b & 16) != 0) {
            this.f1085a.addView(this.f1088d);
        }
    }

    /* renamed from: a */
    public void mo1501a(CharSequence charSequence) {
        this.f1095k = charSequence;
        m1351p();
    }

    /* renamed from: a */
    public void mo1502a(boolean z) {
    }

    /* renamed from: a */
    public boolean mo1503a() {
        return this.f1085a.mo1956i();
    }

    /* renamed from: b */
    public void mo1504b() {
        this.f1097m = true;
    }

    /* renamed from: b */
    public void mo1505b(int i) {
        mo1497a(i != 0 ? C0146a.m492b(mo1523k(), i) : null);
    }

    /* renamed from: b */
    public void mo1506b(Drawable drawable) {
        this.f1091g = drawable;
        m1352q();
    }

    /* renamed from: b */
    public void mo1507b(CharSequence charSequence) {
        this.f1094j = charSequence;
        if ((this.f1086b & 8) != 0) {
            this.f1085a.setSubtitle(charSequence);
        }
    }

    /* renamed from: b */
    public void mo1508b(boolean z) {
        this.f1085a.setCollapsible(z);
    }

    /* renamed from: c */
    public void mo1509c(int i) {
        this.f1085a.setVisibility(i);
    }

    /* renamed from: c */
    public void mo1510c(CharSequence charSequence) {
        this.f1092h = true;
        m1349d(charSequence);
    }

    /* renamed from: c */
    public boolean mo1511c() {
        return this.f1085a.mo1919b();
    }

    public void collapseActionView() {
        this.f1085a.mo1920c();
    }

    /* renamed from: d */
    public void mo1513d(int i) {
        if (i != this.f1100p) {
            this.f1100p = i;
            if (TextUtils.isEmpty(this.f1085a.getNavigationContentDescription())) {
                mo1515e(this.f1100p);
            }
        }
    }

    /* renamed from: d */
    public boolean mo1514d() {
        return this.f1085a.mo1955h();
    }

    /* renamed from: e */
    public void mo1515e(int i) {
        mo1501a((CharSequence) i == 0 ? null : mo1523k().getString(i));
    }

    /* renamed from: e */
    public boolean mo1516e() {
        return this.f1085a.mo1925g();
    }

    /* renamed from: f */
    public boolean mo1517f() {
        return this.f1085a.mo1958k();
    }

    /* renamed from: g */
    public void mo1518g() {
        this.f1085a.mo1922d();
    }

    public CharSequence getTitle() {
        return this.f1085a.getTitle();
    }

    /* renamed from: h */
    public boolean mo1520h() {
        return this.f1085a.mo1924f();
    }

    /* renamed from: i */
    public int mo1521i() {
        return this.f1099o;
    }

    /* renamed from: j */
    public ViewGroup mo1522j() {
        return this.f1085a;
    }

    /* renamed from: k */
    public Context mo1523k() {
        return this.f1085a.getContext();
    }

    /* renamed from: l */
    public int mo1524l() {
        return this.f1086b;
    }

    /* renamed from: m */
    public void mo1525m() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    /* renamed from: n */
    public void mo1526n() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    public void setIcon(int i) {
        setIcon(i != 0 ? C0146a.m492b(mo1523k(), i) : null);
    }

    public void setIcon(Drawable drawable) {
        this.f1089e = drawable;
        m1353r();
    }

    public void setWindowCallback(Window.Callback callback) {
        this.f1096l = callback;
    }

    public void setWindowTitle(CharSequence charSequence) {
        if (!this.f1092h) {
            m1349d(charSequence);
        }
    }
}

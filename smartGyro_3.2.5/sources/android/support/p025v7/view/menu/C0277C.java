package android.support.p025v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.support.p025v7.view.menu.C0310v;
import android.support.p025v7.widget.C0379W;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import p000a.p001a.p005c.p014g.C0127u;
import p000a.p001a.p017d.p018a.C0139d;
import p000a.p001a.p017d.p018a.C0142g;

/* renamed from: android.support.v7.view.menu.C */
final class C0277C extends C0307s implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, C0310v, View.OnKeyListener {

    /* renamed from: b */
    private static final int f860b = C0142g.abc_popup_menu_item_layout;

    /* renamed from: c */
    private final Context f861c;

    /* renamed from: d */
    private final C0293l f862d;

    /* renamed from: e */
    private final C0292k f863e;

    /* renamed from: f */
    private final boolean f864f;

    /* renamed from: g */
    private final int f865g;

    /* renamed from: h */
    private final int f866h;

    /* renamed from: i */
    private final int f867i;

    /* renamed from: j */
    final C0379W f868j;

    /* renamed from: k */
    final ViewTreeObserver.OnGlobalLayoutListener f869k = new C0273A(this);

    /* renamed from: l */
    private final View.OnAttachStateChangeListener f870l = new C0276B(this);

    /* renamed from: m */
    private PopupWindow.OnDismissListener f871m;

    /* renamed from: n */
    private View f872n;

    /* renamed from: o */
    View f873o;

    /* renamed from: p */
    private C0310v.C0311a f874p;

    /* renamed from: q */
    ViewTreeObserver f875q;

    /* renamed from: r */
    private boolean f876r;

    /* renamed from: s */
    private boolean f877s;

    /* renamed from: t */
    private int f878t;

    /* renamed from: u */
    private int f879u = 0;

    /* renamed from: v */
    private boolean f880v;

    public C0277C(Context context, C0293l lVar, View view, int i, int i2, boolean z) {
        this.f861c = context;
        this.f862d = lVar;
        this.f864f = z;
        this.f863e = new C0292k(lVar, LayoutInflater.from(context), this.f864f, f860b);
        this.f866h = i;
        this.f867i = i2;
        Resources resources = context.getResources();
        this.f865g = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(C0139d.abc_config_prefDialogWidth));
        this.f872n = view;
        this.f868j = new C0379W(this.f861c, (AttributeSet) null, this.f866h, this.f867i);
        lVar.mo1276a((C0310v) this, context);
    }

    /* renamed from: g */
    private boolean m1090g() {
        View view;
        if (mo1136b()) {
            return true;
        }
        if (this.f876r || (view = this.f872n) == null) {
            return false;
        }
        this.f873o = view;
        this.f868j.mo2001a((PopupWindow.OnDismissListener) this);
        this.f868j.mo2000a((AdapterView.OnItemClickListener) this);
        this.f868j.mo2002a(true);
        View view2 = this.f873o;
        boolean z = this.f875q == null;
        this.f875q = view2.getViewTreeObserver();
        if (z) {
            this.f875q.addOnGlobalLayoutListener(this.f869k);
        }
        view2.addOnAttachStateChangeListener(this.f870l);
        this.f868j.mo1999a(view2);
        this.f868j.mo2005c(this.f879u);
        if (!this.f877s) {
            this.f878t = C0307s.m1293a(this.f863e, (ViewGroup) null, this.f861c, this.f865g);
            this.f877s = true;
        }
        this.f868j.mo2003b(this.f878t);
        this.f868j.mo2008e(2);
        this.f868j.mo1997a(mo1451f());
        this.f868j.mo1137c();
        ListView d = this.f868j.mo1140d();
        d.setOnKeyListener(this);
        if (this.f880v && this.f862d.mo1311h() != null) {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.f861c).inflate(C0142g.abc_popup_menu_header_item_layout, d, false);
            TextView textView = (TextView) frameLayout.findViewById(16908310);
            if (textView != null) {
                textView.setText(this.f862d.mo1311h());
            }
            frameLayout.setEnabled(false);
            d.addHeaderView(frameLayout, (Object) null, false);
        }
        this.f868j.mo1682a((ListAdapter) this.f863e);
        this.f868j.mo1137c();
        return true;
    }

    /* renamed from: a */
    public void mo1125a(int i) {
        this.f879u = i;
    }

    /* renamed from: a */
    public void mo1126a(C0293l lVar) {
    }

    /* renamed from: a */
    public void mo1127a(C0293l lVar, boolean z) {
        if (lVar == this.f862d) {
            dismiss();
            C0310v.C0311a aVar = this.f874p;
            if (aVar != null) {
                aVar.mo1072a(lVar, z);
            }
        }
    }

    /* renamed from: a */
    public void mo1128a(C0310v.C0311a aVar) {
        this.f874p = aVar;
    }

    /* renamed from: a */
    public void mo1129a(View view) {
        this.f872n = view;
    }

    /* renamed from: a */
    public void mo1130a(PopupWindow.OnDismissListener onDismissListener) {
        this.f871m = onDismissListener;
    }

    /* renamed from: a */
    public void mo1131a(boolean z) {
        this.f877s = false;
        C0292k kVar = this.f863e;
        if (kVar != null) {
            kVar.notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public boolean mo1132a() {
        return false;
    }

    /* renamed from: a */
    public boolean mo1133a(C0278D d) {
        if (d.hasVisibleItems()) {
            C0309u uVar = new C0309u(this.f861c, d, this.f873o, this.f864f, this.f866h, this.f867i);
            uVar.mo1456a(this.f874p);
            uVar.mo1459a(C0307s.m1295b((C0293l) d));
            uVar.mo1458a(this.f871m);
            this.f871m = null;
            this.f862d.mo1279a(false);
            int g = this.f868j.mo2011g();
            int h = this.f868j.mo2013h();
            if ((Gravity.getAbsoluteGravity(this.f879u, C0127u.m450d(this.f872n)) & 7) == 5) {
                g += this.f872n.getWidth();
            }
            if (uVar.mo1460a(g, h)) {
                C0310v.C0311a aVar = this.f874p;
                if (aVar == null) {
                    return true;
                }
                aVar.mo1073a(d);
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    public void mo1134b(int i) {
        this.f868j.mo2006d(i);
    }

    /* renamed from: b */
    public void mo1135b(boolean z) {
        this.f863e.mo1259a(z);
    }

    /* renamed from: b */
    public boolean mo1136b() {
        return !this.f876r && this.f868j.mo1136b();
    }

    /* renamed from: c */
    public void mo1137c() {
        if (!m1090g()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    /* renamed from: c */
    public void mo1138c(int i) {
        this.f868j.mo2014h(i);
    }

    /* renamed from: c */
    public void mo1139c(boolean z) {
        this.f880v = z;
    }

    /* renamed from: d */
    public ListView mo1140d() {
        return this.f868j.mo1140d();
    }

    public void dismiss() {
        if (mo1136b()) {
            this.f868j.dismiss();
        }
    }

    public void onDismiss() {
        this.f876r = true;
        this.f862d.close();
        ViewTreeObserver viewTreeObserver = this.f875q;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.f875q = this.f873o.getViewTreeObserver();
            }
            this.f875q.removeGlobalOnLayoutListener(this.f869k);
            this.f875q = null;
        }
        this.f873o.removeOnAttachStateChangeListener(this.f870l);
        PopupWindow.OnDismissListener onDismissListener = this.f871m;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        dismiss();
        return true;
    }
}

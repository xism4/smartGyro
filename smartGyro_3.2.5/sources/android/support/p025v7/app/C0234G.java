package android.support.p025v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.p025v7.app.C0236a;
import android.support.p025v7.view.menu.C0293l;
import android.support.p025v7.widget.ActionBarContainer;
import android.support.p025v7.widget.ActionBarContextView;
import android.support.p025v7.widget.ActionBarOverlayLayout;
import android.support.p025v7.widget.C0347L;
import android.support.p025v7.widget.C0386aa;
import android.support.p025v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import p000a.p001a.p005c.p014g.C0092A;
import p000a.p001a.p005c.p014g.C0094C;
import p000a.p001a.p005c.p014g.C0127u;
import p000a.p001a.p005c.p014g.C0134z;
import p000a.p001a.p017d.p018a.C0136a;
import p000a.p001a.p017d.p018a.C0141f;
import p000a.p001a.p017d.p018a.C0145j;
import p000a.p001a.p017d.p023d.C0163a;
import p000a.p001a.p017d.p023d.C0164b;
import p000a.p001a.p017d.p023d.C0171g;
import p000a.p001a.p017d.p023d.C0175i;

/* renamed from: android.support.v7.app.G */
public class C0234G extends C0236a implements ActionBarOverlayLayout.C0319a {

    /* renamed from: a */
    private static final Interpolator f690a = new AccelerateInterpolator();

    /* renamed from: b */
    private static final Interpolator f691b = new DecelerateInterpolator();

    /* renamed from: A */
    private boolean f692A = true;

    /* renamed from: B */
    C0175i f693B;

    /* renamed from: C */
    private boolean f694C;

    /* renamed from: D */
    boolean f695D;

    /* renamed from: E */
    final C0092A f696E = new C0231D(this);

    /* renamed from: F */
    final C0092A f697F = new C0232E(this);

    /* renamed from: G */
    final C0094C f698G = new C0233F(this);

    /* renamed from: c */
    Context f699c;

    /* renamed from: d */
    private Context f700d;

    /* renamed from: e */
    private Activity f701e;

    /* renamed from: f */
    private Dialog f702f;

    /* renamed from: g */
    ActionBarOverlayLayout f703g;

    /* renamed from: h */
    ActionBarContainer f704h;

    /* renamed from: i */
    C0347L f705i;

    /* renamed from: j */
    ActionBarContextView f706j;

    /* renamed from: k */
    View f707k;

    /* renamed from: l */
    C0386aa f708l;

    /* renamed from: m */
    private ArrayList<Object> f709m = new ArrayList<>();

    /* renamed from: n */
    private int f710n = -1;

    /* renamed from: o */
    private boolean f711o;

    /* renamed from: p */
    C0235a f712p;

    /* renamed from: q */
    C0164b f713q;

    /* renamed from: r */
    C0164b.C0165a f714r;

    /* renamed from: s */
    private boolean f715s;

    /* renamed from: t */
    private ArrayList<C0236a.C0238b> f716t = new ArrayList<>();

    /* renamed from: u */
    private boolean f717u;

    /* renamed from: v */
    private int f718v = 0;

    /* renamed from: w */
    boolean f719w = true;

    /* renamed from: x */
    boolean f720x;

    /* renamed from: y */
    boolean f721y;

    /* renamed from: z */
    private boolean f722z;

    /* renamed from: android.support.v7.app.G$a */
    public class C0235a extends C0164b implements C0293l.C0294a {

        /* renamed from: c */
        private final Context f723c;

        /* renamed from: d */
        private final C0293l f724d;

        /* renamed from: e */
        private C0164b.C0165a f725e;

        /* renamed from: f */
        private WeakReference<View> f726f;

        public C0235a(Context context, C0164b.C0165a aVar) {
            this.f723c = context;
            this.f725e = aVar;
            C0293l lVar = new C0293l(context);
            lVar.mo1296c(1);
            this.f724d = lVar;
            this.f724d.mo1144a((C0293l.C0294a) this);
        }

        /* renamed from: a */
        public void mo646a() {
            C0234G g = C0234G.this;
            if (g.f712p == this) {
                if (!C0234G.m889a(g.f720x, g.f721y, false)) {
                    C0234G g2 = C0234G.this;
                    g2.f713q = this;
                    g2.f714r = this.f725e;
                } else {
                    this.f725e.mo663a(this);
                }
                this.f725e = null;
                C0234G.this.mo975e(false);
                C0234G.this.f706j.mo1550a();
                C0234G.this.f705i.mo1522j().sendAccessibilityEvent(32);
                C0234G g3 = C0234G.this;
                g3.f703g.setHideOnContentScrollEnabled(g3.f695D);
                C0234G.this.f712p = null;
            }
        }

        /* renamed from: a */
        public void mo647a(int i) {
            mo649a((CharSequence) C0234G.this.f699c.getResources().getString(i));
        }

        /* renamed from: a */
        public void mo677a(C0293l lVar) {
            if (this.f725e != null) {
                mo661i();
                C0234G.this.f706j.mo1554d();
            }
        }

        /* renamed from: a */
        public void mo648a(View view) {
            C0234G.this.f706j.setCustomView(view);
            this.f726f = new WeakReference<>(view);
        }

        /* renamed from: a */
        public void mo649a(CharSequence charSequence) {
            C0234G.this.f706j.setSubtitle(charSequence);
        }

        /* renamed from: a */
        public void mo651a(boolean z) {
            super.mo651a(z);
            C0234G.this.f706j.setTitleOptional(z);
        }

        /* renamed from: a */
        public boolean mo678a(C0293l lVar, MenuItem menuItem) {
            C0164b.C0165a aVar = this.f725e;
            if (aVar != null) {
                return aVar.mo665a((C0164b) this, menuItem);
            }
            return false;
        }

        /* renamed from: b */
        public View mo652b() {
            WeakReference<View> weakReference = this.f726f;
            if (weakReference != null) {
                return (View) weakReference.get();
            }
            return null;
        }

        /* renamed from: b */
        public void mo653b(int i) {
            mo654b((CharSequence) C0234G.this.f699c.getResources().getString(i));
        }

        /* renamed from: b */
        public void mo654b(CharSequence charSequence) {
            C0234G.this.f706j.setTitle(charSequence);
        }

        /* renamed from: c */
        public Menu mo655c() {
            return this.f724d;
        }

        /* renamed from: d */
        public MenuInflater mo656d() {
            return new C0171g(this.f723c);
        }

        /* renamed from: e */
        public CharSequence mo657e() {
            return C0234G.this.f706j.getSubtitle();
        }

        /* renamed from: g */
        public CharSequence mo659g() {
            return C0234G.this.f706j.getTitle();
        }

        /* renamed from: i */
        public void mo661i() {
            if (C0234G.this.f712p == this) {
                this.f724d.mo1324s();
                try {
                    this.f725e.mo666b(this, this.f724d);
                } finally {
                    this.f724d.mo1321r();
                }
            }
        }

        /* renamed from: j */
        public boolean mo662j() {
            return C0234G.this.f706j.mo1552b();
        }

        /* renamed from: k */
        public boolean mo986k() {
            this.f724d.mo1324s();
            try {
                return this.f725e.mo664a((C0164b) this, (Menu) this.f724d);
            } finally {
                this.f724d.mo1321r();
            }
        }
    }

    public C0234G(Activity activity, boolean z) {
        this.f701e = activity;
        View decorView = activity.getWindow().getDecorView();
        m890b(decorView);
        if (!z) {
            this.f707k = decorView.findViewById(16908290);
        }
    }

    public C0234G(Dialog dialog) {
        this.f702f = dialog;
        m890b(dialog.getWindow().getDecorView());
    }

    /* renamed from: a */
    private C0347L m888a(View view) {
        if (view instanceof C0347L) {
            return (C0347L) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't make a decor toolbar out of ");
        sb.append(view != null ? view.getClass().getSimpleName() : "null");
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: a */
    static boolean m889a(boolean z, boolean z2, boolean z3) {
        if (z3) {
            return true;
        }
        return !z && !z2;
    }

    /* renamed from: b */
    private void m890b(View view) {
        this.f703g = (ActionBarOverlayLayout) view.findViewById(C0141f.decor_content_parent);
        ActionBarOverlayLayout actionBarOverlayLayout = this.f703g;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.f705i = m888a(view.findViewById(C0141f.action_bar));
        this.f706j = (ActionBarContextView) view.findViewById(C0141f.action_context_bar);
        this.f704h = (ActionBarContainer) view.findViewById(C0141f.action_bar_container);
        C0347L l = this.f705i;
        if (l == null || this.f706j == null || this.f704h == null) {
            throw new IllegalStateException(C0234G.class.getSimpleName() + " can only be used " + "with a compatible window decor layout");
        }
        this.f699c = l.mo1523k();
        boolean z = (this.f705i.mo1524l() & 4) != 0;
        if (z) {
            this.f711o = true;
        }
        C0163a a = C0163a.m577a(this.f699c);
        mo984j(a.mo639a() || z);
        m892k(a.mo644f());
        TypedArray obtainStyledAttributes = this.f699c.obtainStyledAttributes((AttributeSet) null, C0145j.ActionBar, C0136a.actionBarStyle, 0);
        if (obtainStyledAttributes.getBoolean(C0145j.ActionBar_hideOnContentScroll, false)) {
            mo983i(true);
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(C0145j.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            mo964a((float) dimensionPixelSize);
        }
        obtainStyledAttributes.recycle();
    }

    /* renamed from: j */
    private void m891j() {
        if (this.f722z) {
            this.f722z = false;
            ActionBarOverlayLayout actionBarOverlayLayout = this.f703g;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(false);
            }
            m895l(false);
        }
    }

    /* renamed from: k */
    private void m892k(boolean z) {
        this.f717u = z;
        if (!this.f717u) {
            this.f705i.mo1498a((C0386aa) null);
            this.f704h.setTabContainer(this.f708l);
        } else {
            this.f704h.setTabContainer((C0386aa) null);
            this.f705i.mo1498a(this.f708l);
        }
        boolean z2 = true;
        boolean z3 = mo982i() == 2;
        C0386aa aaVar = this.f708l;
        if (aaVar != null) {
            if (z3) {
                aaVar.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout = this.f703g;
                if (actionBarOverlayLayout != null) {
                    C0127u.m457k(actionBarOverlayLayout);
                }
            } else {
                aaVar.setVisibility(8);
            }
        }
        this.f705i.mo1508b(!this.f717u && z3);
        ActionBarOverlayLayout actionBarOverlayLayout2 = this.f703g;
        if (this.f717u || !z3) {
            z2 = false;
        }
        actionBarOverlayLayout2.setHasNonEmbeddedTabs(z2);
    }

    /* renamed from: k */
    private boolean m893k() {
        return C0127u.m455i(this.f704h);
    }

    /* renamed from: l */
    private void m894l() {
        if (!this.f722z) {
            this.f722z = true;
            ActionBarOverlayLayout actionBarOverlayLayout = this.f703g;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(true);
            }
            m895l(false);
        }
    }

    /* renamed from: l */
    private void m895l(boolean z) {
        if (m889a(this.f720x, this.f721y, this.f722z)) {
            if (!this.f692A) {
                this.f692A = true;
                mo979g(z);
            }
        } else if (this.f692A) {
            this.f692A = false;
            mo978f(z);
        }
    }

    /* renamed from: a */
    public C0164b mo962a(C0164b.C0165a aVar) {
        C0235a aVar2 = this.f712p;
        if (aVar2 != null) {
            aVar2.mo646a();
        }
        this.f703g.setHideOnContentScrollEnabled(false);
        this.f706j.mo1553c();
        C0235a aVar3 = new C0235a(this.f706j.getContext(), aVar);
        if (!aVar3.mo986k()) {
            return null;
        }
        this.f712p = aVar3;
        aVar3.mo661i();
        this.f706j.mo1551a(aVar3);
        mo975e(true);
        this.f706j.sendAccessibilityEvent(32);
        return aVar3;
    }

    /* renamed from: a */
    public void mo963a() {
        if (this.f721y) {
            this.f721y = false;
            m895l(true);
        }
    }

    /* renamed from: a */
    public void mo964a(float f) {
        C0127u.m437a((View) this.f704h, f);
    }

    /* renamed from: a */
    public void mo965a(int i, int i2) {
        int l = this.f705i.mo1524l();
        if ((i2 & 4) != 0) {
            this.f711o = true;
        }
        this.f705i.mo1496a((i & i2) | ((i2 ^ -1) & l));
    }

    /* renamed from: a */
    public void mo966a(CharSequence charSequence) {
        this.f705i.setWindowTitle(charSequence);
    }

    /* renamed from: a */
    public void mo967a(boolean z) {
        this.f719w = z;
    }

    /* renamed from: a */
    public boolean mo968a(int i, KeyEvent keyEvent) {
        Menu c;
        C0235a aVar = this.f712p;
        if (aVar == null || (c = aVar.mo655c()) == null) {
            return false;
        }
        boolean z = true;
        if (KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() == 1) {
            z = false;
        }
        c.setQwertyMode(z);
        return c.performShortcut(i, keyEvent, 0);
    }

    /* renamed from: b */
    public void mo969b() {
    }

    /* renamed from: b */
    public void mo970b(boolean z) {
        if (z != this.f715s) {
            this.f715s = z;
            int size = this.f716t.size();
            for (int i = 0; i < size; i++) {
                this.f716t.get(i).onMenuVisibilityChanged(z);
            }
        }
    }

    /* renamed from: c */
    public void mo971c() {
        if (!this.f721y) {
            this.f721y = true;
            m895l(true);
        }
    }

    /* renamed from: c */
    public void mo972c(boolean z) {
        if (!this.f711o) {
            mo981h(z);
        }
    }

    /* renamed from: d */
    public void mo973d() {
        C0175i iVar = this.f693B;
        if (iVar != null) {
            iVar.mo712a();
            this.f693B = null;
        }
    }

    /* renamed from: d */
    public void mo974d(boolean z) {
        C0175i iVar;
        this.f694C = z;
        if (!z && (iVar = this.f693B) != null) {
            iVar.mo712a();
        }
    }

    /* renamed from: e */
    public void mo975e(boolean z) {
        C0134z zVar;
        C0134z zVar2;
        if (z) {
            m894l();
        } else {
            m891j();
        }
        if (m893k()) {
            if (z) {
                zVar = this.f705i.mo1495a(4, 100);
                zVar2 = this.f706j.mo1549a(0, 200);
            } else {
                zVar2 = this.f705i.mo1495a(0, 200);
                zVar = this.f706j.mo1549a(8, 100);
            }
            C0175i iVar = new C0175i();
            iVar.mo710a(zVar, zVar2);
            iVar.mo714c();
        } else if (z) {
            this.f705i.mo1509c(4);
            this.f706j.setVisibility(0);
        } else {
            this.f705i.mo1509c(0);
            this.f706j.setVisibility(8);
        }
    }

    /* renamed from: e */
    public boolean mo976e() {
        C0347L l = this.f705i;
        if (l == null || !l.mo1520h()) {
            return false;
        }
        this.f705i.collapseActionView();
        return true;
    }

    /* renamed from: f */
    public Context mo977f() {
        if (this.f700d == null) {
            TypedValue typedValue = new TypedValue();
            this.f699c.getTheme().resolveAttribute(C0136a.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.f700d = new ContextThemeWrapper(this.f699c, i);
            } else {
                this.f700d = this.f699c;
            }
        }
        return this.f700d;
    }

    /* renamed from: f */
    public void mo978f(boolean z) {
        View view;
        C0175i iVar = this.f693B;
        if (iVar != null) {
            iVar.mo712a();
        }
        if (this.f718v != 0 || (!this.f694C && !z)) {
            this.f696E.mo390b((View) null);
            return;
        }
        this.f704h.setAlpha(1.0f);
        this.f704h.setTransitioning(true);
        C0175i iVar2 = new C0175i();
        float f = (float) (-this.f704h.getHeight());
        if (z) {
            int[] iArr = {0, 0};
            this.f704h.getLocationInWindow(iArr);
            f -= (float) iArr[1];
        }
        C0134z a = C0127u.m436a(this.f704h);
        a.mo510b(f);
        a.mo506a(this.f698G);
        iVar2.mo709a(a);
        if (this.f719w && (view = this.f707k) != null) {
            C0134z a2 = C0127u.m436a(view);
            a2.mo510b(f);
            iVar2.mo709a(a2);
        }
        iVar2.mo711a(f690a);
        iVar2.mo707a(250);
        iVar2.mo708a(this.f696E);
        this.f693B = iVar2;
        iVar2.mo714c();
    }

    /* renamed from: g */
    public void mo979g(boolean z) {
        View view;
        View view2;
        C0175i iVar = this.f693B;
        if (iVar != null) {
            iVar.mo712a();
        }
        this.f704h.setVisibility(0);
        if (this.f718v != 0 || (!this.f694C && !z)) {
            this.f704h.setAlpha(1.0f);
            this.f704h.setTranslationY(0.0f);
            if (this.f719w && (view = this.f707k) != null) {
                view.setTranslationY(0.0f);
            }
            this.f697F.mo390b((View) null);
        } else {
            this.f704h.setTranslationY(0.0f);
            float f = (float) (-this.f704h.getHeight());
            if (z) {
                int[] iArr = {0, 0};
                this.f704h.getLocationInWindow(iArr);
                f -= (float) iArr[1];
            }
            this.f704h.setTranslationY(f);
            C0175i iVar2 = new C0175i();
            C0134z a = C0127u.m436a(this.f704h);
            a.mo510b(0.0f);
            a.mo506a(this.f698G);
            iVar2.mo709a(a);
            if (this.f719w && (view2 = this.f707k) != null) {
                view2.setTranslationY(f);
                C0134z a2 = C0127u.m436a(this.f707k);
                a2.mo510b(0.0f);
                iVar2.mo709a(a2);
            }
            iVar2.mo711a(f691b);
            iVar2.mo707a(250);
            iVar2.mo708a(this.f697F);
            this.f693B = iVar2;
            iVar2.mo714c();
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f703g;
        if (actionBarOverlayLayout != null) {
            C0127u.m457k(actionBarOverlayLayout);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: h */
    public void mo980h() {
        C0164b.C0165a aVar = this.f714r;
        if (aVar != null) {
            aVar.mo663a(this.f713q);
            this.f713q = null;
            this.f714r = null;
        }
    }

    /* renamed from: h */
    public void mo981h(boolean z) {
        mo965a(z ? 4 : 0, 4);
    }

    /* renamed from: i */
    public int mo982i() {
        return this.f705i.mo1521i();
    }

    /* renamed from: i */
    public void mo983i(boolean z) {
        if (!z || this.f703g.mo1593i()) {
            this.f695D = z;
            this.f703g.setHideOnContentScrollEnabled(z);
            return;
        }
        throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
    }

    /* renamed from: j */
    public void mo984j(boolean z) {
        this.f705i.mo1502a(z);
    }

    public void onWindowVisibilityChanged(int i) {
        this.f718v = i;
    }
}

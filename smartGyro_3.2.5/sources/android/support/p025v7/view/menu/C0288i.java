package android.support.p025v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.support.p025v7.view.menu.C0310v;
import android.support.p025v7.widget.C0377V;
import android.support.p025v7.widget.C0379W;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import p000a.p001a.p005c.p014g.C0111f;
import p000a.p001a.p005c.p014g.C0127u;
import p000a.p001a.p017d.p018a.C0139d;
import p000a.p001a.p017d.p018a.C0142g;

/* renamed from: android.support.v7.view.menu.i */
final class C0288i extends C0307s implements C0310v, View.OnKeyListener, PopupWindow.OnDismissListener {

    /* renamed from: b */
    private static final int f947b = C0142g.abc_cascading_menu_item_layout;

    /* renamed from: A */
    private PopupWindow.OnDismissListener f948A;

    /* renamed from: B */
    boolean f949B;

    /* renamed from: c */
    private final Context f950c;

    /* renamed from: d */
    private final int f951d;

    /* renamed from: e */
    private final int f952e;

    /* renamed from: f */
    private final int f953f;

    /* renamed from: g */
    private final boolean f954g;

    /* renamed from: h */
    final Handler f955h;

    /* renamed from: i */
    private final List<C0293l> f956i = new ArrayList();

    /* renamed from: j */
    final List<C0289a> f957j = new ArrayList();

    /* renamed from: k */
    final ViewTreeObserver.OnGlobalLayoutListener f958k = new C0284e(this);

    /* renamed from: l */
    private final View.OnAttachStateChangeListener f959l = new C0285f(this);

    /* renamed from: m */
    private final C0377V f960m = new C0287h(this);

    /* renamed from: n */
    private int f961n = 0;

    /* renamed from: o */
    private int f962o = 0;

    /* renamed from: p */
    private View f963p;

    /* renamed from: q */
    View f964q;

    /* renamed from: r */
    private int f965r;

    /* renamed from: s */
    private boolean f966s;

    /* renamed from: t */
    private boolean f967t;

    /* renamed from: u */
    private int f968u;

    /* renamed from: v */
    private int f969v;

    /* renamed from: w */
    private boolean f970w;

    /* renamed from: x */
    private boolean f971x;

    /* renamed from: y */
    private C0310v.C0311a f972y;

    /* renamed from: z */
    ViewTreeObserver f973z;

    /* renamed from: android.support.v7.view.menu.i$a */
    private static class C0289a {

        /* renamed from: a */
        public final C0379W f974a;

        /* renamed from: b */
        public final C0293l f975b;

        /* renamed from: c */
        public final int f976c;

        public C0289a(C0379W w, C0293l lVar, int i) {
            this.f974a = w;
            this.f975b = lVar;
            this.f976c = i;
        }

        /* renamed from: a */
        public ListView mo1248a() {
            return this.f974a.mo1140d();
        }
    }

    public C0288i(Context context, View view, int i, int i2, boolean z) {
        this.f950c = context;
        this.f963p = view;
        this.f952e = i;
        this.f953f = i2;
        this.f954g = z;
        this.f970w = false;
        this.f965r = m1160h();
        Resources resources = context.getResources();
        this.f951d = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(C0139d.abc_config_prefDialogWidth));
        this.f955h = new Handler();
    }

    /* renamed from: a */
    private MenuItem m1154a(C0293l lVar, C0293l lVar2) {
        int size = lVar.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = lVar.getItem(i);
            if (item.hasSubMenu() && lVar2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    /* renamed from: a */
    private View m1155a(C0289a aVar, C0293l lVar) {
        int i;
        C0292k kVar;
        int firstVisiblePosition;
        MenuItem a = m1154a(aVar.f975b, lVar);
        if (a == null) {
            return null;
        }
        ListView a2 = aVar.mo1248a();
        ListAdapter adapter = a2.getAdapter();
        int i2 = 0;
        if (adapter instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
            i = headerViewListAdapter.getHeadersCount();
            kVar = (C0292k) headerViewListAdapter.getWrappedAdapter();
        } else {
            kVar = (C0292k) adapter;
            i = 0;
        }
        int count = kVar.getCount();
        while (true) {
            if (i2 >= count) {
                i2 = -1;
                break;
            } else if (a == kVar.getItem(i2)) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 != -1 && (firstVisiblePosition = (i2 + i) - a2.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < a2.getChildCount()) {
            return a2.getChildAt(firstVisiblePosition);
        }
        return null;
    }

    /* renamed from: c */
    private int m1156c(C0293l lVar) {
        int size = this.f957j.size();
        for (int i = 0; i < size; i++) {
            if (lVar == this.f957j.get(i).f975b) {
                return i;
            }
        }
        return -1;
    }

    /* renamed from: d */
    private int m1157d(int i) {
        List<C0289a> list = this.f957j;
        ListView a = list.get(list.size() - 1).mo1248a();
        int[] iArr = new int[2];
        a.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.f964q.getWindowVisibleDisplayFrame(rect);
        return this.f965r == 1 ? (iArr[0] + a.getWidth()) + i > rect.right ? 0 : 1 : iArr[0] - i < 0 ? 1 : 0;
    }

    /* renamed from: d */
    private void m1158d(C0293l lVar) {
        View view;
        C0289a aVar;
        int i;
        int i2;
        int i3;
        LayoutInflater from = LayoutInflater.from(this.f950c);
        C0292k kVar = new C0292k(lVar, from, this.f954g, f947b);
        if (!mo1136b() && this.f970w) {
            kVar.mo1259a(true);
        } else if (mo1136b()) {
            kVar.mo1259a(C0307s.m1295b(lVar));
        }
        int a = C0307s.m1293a(kVar, (ViewGroup) null, this.f950c, this.f951d);
        C0379W g = m1159g();
        g.mo1682a((ListAdapter) kVar);
        g.mo2003b(a);
        g.mo2005c(this.f962o);
        if (this.f957j.size() > 0) {
            List<C0289a> list = this.f957j;
            aVar = list.get(list.size() - 1);
            view = m1155a(aVar, lVar);
        } else {
            aVar = null;
            view = null;
        }
        if (view != null) {
            g.mo2042c(false);
            g.mo2040a((Object) null);
            int d = m1157d(a);
            boolean z = d == 1;
            this.f965r = d;
            if (Build.VERSION.SDK_INT >= 26) {
                g.mo1999a(view);
                i2 = 0;
                i = 0;
            } else {
                int[] iArr = new int[2];
                this.f963p.getLocationOnScreen(iArr);
                int[] iArr2 = new int[2];
                view.getLocationOnScreen(iArr2);
                if ((this.f962o & 7) == 5) {
                    iArr[0] = iArr[0] + this.f963p.getWidth();
                    iArr2[0] = iArr2[0] + view.getWidth();
                }
                i = iArr2[0] - iArr[0];
                i2 = iArr2[1] - iArr[1];
            }
            if ((this.f962o & 5) != 5) {
                if (z) {
                    a = view.getWidth();
                }
                i3 = i - a;
                g.mo2006d(i3);
                g.mo2004b(true);
                g.mo2014h(i2);
            } else if (!z) {
                a = view.getWidth();
                i3 = i - a;
                g.mo2006d(i3);
                g.mo2004b(true);
                g.mo2014h(i2);
            }
            i3 = i + a;
            g.mo2006d(i3);
            g.mo2004b(true);
            g.mo2014h(i2);
        } else {
            if (this.f966s) {
                g.mo2006d(this.f968u);
            }
            if (this.f967t) {
                g.mo2014h(this.f969v);
            }
            g.mo1997a(mo1451f());
        }
        this.f957j.add(new C0289a(g, lVar, this.f965r));
        g.mo1137c();
        ListView d2 = g.mo1140d();
        d2.setOnKeyListener(this);
        if (aVar == null && this.f971x && lVar.mo1311h() != null) {
            FrameLayout frameLayout = (FrameLayout) from.inflate(C0142g.abc_popup_menu_header_item_layout, d2, false);
            frameLayout.setEnabled(false);
            ((TextView) frameLayout.findViewById(16908310)).setText(lVar.mo1311h());
            d2.addHeaderView(frameLayout, (Object) null, false);
            g.mo1137c();
        }
    }

    /* renamed from: g */
    private C0379W m1159g() {
        C0379W w = new C0379W(this.f950c, (AttributeSet) null, this.f952e, this.f953f);
        w.mo2039a(this.f960m);
        w.mo2000a((AdapterView.OnItemClickListener) this);
        w.mo2001a((PopupWindow.OnDismissListener) this);
        w.mo1999a(this.f963p);
        w.mo2005c(this.f962o);
        w.mo2002a(true);
        w.mo2008e(2);
        return w;
    }

    /* renamed from: h */
    private int m1160h() {
        return C0127u.m450d(this.f963p) == 1 ? 0 : 1;
    }

    /* renamed from: a */
    public void mo1125a(int i) {
        if (this.f961n != i) {
            this.f961n = i;
            this.f962o = C0111f.m395a(i, C0127u.m450d(this.f963p));
        }
    }

    /* renamed from: a */
    public void mo1126a(C0293l lVar) {
        lVar.mo1276a((C0310v) this, this.f950c);
        if (mo1136b()) {
            m1158d(lVar);
        } else {
            this.f956i.add(lVar);
        }
    }

    /* renamed from: a */
    public void mo1127a(C0293l lVar, boolean z) {
        int c = m1156c(lVar);
        if (c >= 0) {
            int i = c + 1;
            if (i < this.f957j.size()) {
                this.f957j.get(i).f975b.mo1279a(false);
            }
            C0289a remove = this.f957j.remove(c);
            remove.f975b.mo1294b((C0310v) this);
            if (this.f949B) {
                remove.f974a.mo2041b((Object) null);
                remove.f974a.mo1996a(0);
            }
            remove.f974a.dismiss();
            int size = this.f957j.size();
            this.f965r = size > 0 ? this.f957j.get(size - 1).f976c : m1160h();
            if (size == 0) {
                dismiss();
                C0310v.C0311a aVar = this.f972y;
                if (aVar != null) {
                    aVar.mo1072a(lVar, true);
                }
                ViewTreeObserver viewTreeObserver = this.f973z;
                if (viewTreeObserver != null) {
                    if (viewTreeObserver.isAlive()) {
                        this.f973z.removeGlobalOnLayoutListener(this.f958k);
                    }
                    this.f973z = null;
                }
                this.f964q.removeOnAttachStateChangeListener(this.f959l);
                this.f948A.onDismiss();
            } else if (z) {
                this.f957j.get(0).f975b.mo1279a(false);
            }
        }
    }

    /* renamed from: a */
    public void mo1128a(C0310v.C0311a aVar) {
        this.f972y = aVar;
    }

    /* renamed from: a */
    public void mo1129a(View view) {
        if (this.f963p != view) {
            this.f963p = view;
            this.f962o = C0111f.m395a(this.f961n, C0127u.m450d(this.f963p));
        }
    }

    /* renamed from: a */
    public void mo1130a(PopupWindow.OnDismissListener onDismissListener) {
        this.f948A = onDismissListener;
    }

    /* renamed from: a */
    public void mo1131a(boolean z) {
        for (C0289a a : this.f957j) {
            C0307s.m1294a(a.mo1248a().getAdapter()).notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public boolean mo1132a() {
        return false;
    }

    /* renamed from: a */
    public boolean mo1133a(C0278D d) {
        for (C0289a next : this.f957j) {
            if (d == next.f975b) {
                next.mo1248a().requestFocus();
                return true;
            }
        }
        if (!d.hasVisibleItems()) {
            return false;
        }
        mo1126a((C0293l) d);
        C0310v.C0311a aVar = this.f972y;
        if (aVar != null) {
            aVar.mo1073a(d);
        }
        return true;
    }

    /* renamed from: b */
    public void mo1134b(int i) {
        this.f966s = true;
        this.f968u = i;
    }

    /* renamed from: b */
    public void mo1135b(boolean z) {
        this.f970w = z;
    }

    /* renamed from: b */
    public boolean mo1136b() {
        return this.f957j.size() > 0 && this.f957j.get(0).f974a.mo1136b();
    }

    /* renamed from: c */
    public void mo1137c() {
        if (!mo1136b()) {
            for (C0293l d : this.f956i) {
                m1158d(d);
            }
            this.f956i.clear();
            this.f964q = this.f963p;
            if (this.f964q != null) {
                boolean z = this.f973z == null;
                this.f973z = this.f964q.getViewTreeObserver();
                if (z) {
                    this.f973z.addOnGlobalLayoutListener(this.f958k);
                }
                this.f964q.addOnAttachStateChangeListener(this.f959l);
            }
        }
    }

    /* renamed from: c */
    public void mo1138c(int i) {
        this.f967t = true;
        this.f969v = i;
    }

    /* renamed from: c */
    public void mo1139c(boolean z) {
        this.f971x = z;
    }

    /* renamed from: d */
    public ListView mo1140d() {
        if (this.f957j.isEmpty()) {
            return null;
        }
        List<C0289a> list = this.f957j;
        return list.get(list.size() - 1).mo1248a();
    }

    public void dismiss() {
        int size = this.f957j.size();
        if (size > 0) {
            C0289a[] aVarArr = (C0289a[]) this.f957j.toArray(new C0289a[size]);
            for (int i = size - 1; i >= 0; i--) {
                C0289a aVar = aVarArr[i];
                if (aVar.f974a.mo1136b()) {
                    aVar.f974a.dismiss();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public boolean mo1245e() {
        return false;
    }

    public void onDismiss() {
        C0289a aVar;
        int size = this.f957j.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                aVar = null;
                break;
            }
            aVar = this.f957j.get(i);
            if (!aVar.f974a.mo1136b()) {
                break;
            }
            i++;
        }
        if (aVar != null) {
            aVar.f975b.mo1279a(false);
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

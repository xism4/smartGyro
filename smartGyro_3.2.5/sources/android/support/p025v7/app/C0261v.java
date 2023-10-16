package android.support.p025v7.app;

import android.app.Activity;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.p024v4.app.C0189e;
import android.support.p025v7.view.menu.C0290j;
import android.support.p025v7.view.menu.C0293l;
import android.support.p025v7.view.menu.C0310v;
import android.support.p025v7.view.menu.C0312w;
import android.support.p025v7.widget.ActionBarContextView;
import android.support.p025v7.widget.C0340Ga;
import android.support.p025v7.widget.C0342Ha;
import android.support.p025v7.widget.C0346K;
import android.support.p025v7.widget.C0352O;
import android.support.p025v7.widget.C0439ta;
import android.support.p025v7.widget.ContentFrameLayout;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.util.List;
import org.cocos2dx.lib.GameControllerDelegate;
import org.xmlpull.v1.XmlPullParser;
import p000a.p001a.p005c.p014g.C0092A;
import p000a.p001a.p005c.p014g.C0112g;
import p000a.p001a.p005c.p014g.C0114h;
import p000a.p001a.p005c.p014g.C0123q;
import p000a.p001a.p005c.p014g.C0127u;
import p000a.p001a.p005c.p014g.C0134z;
import p000a.p001a.p017d.p018a.C0136a;
import p000a.p001a.p017d.p018a.C0138c;
import p000a.p001a.p017d.p018a.C0141f;
import p000a.p001a.p017d.p018a.C0142g;
import p000a.p001a.p017d.p018a.C0144i;
import p000a.p001a.p017d.p018a.C0145j;
import p000a.p001a.p017d.p019b.p020a.C0146a;
import p000a.p001a.p017d.p023d.C0164b;
import p000a.p001a.p017d.p023d.C0167d;
import p000a.p001a.p017d.p023d.C0169f;
import p000a.p001a.p017d.p023d.C0176j;

/* renamed from: android.support.v7.app.v */
class C0261v extends C0252m implements C0293l.C0294a, LayoutInflater.Factory2 {

    /* renamed from: b */
    private static final boolean f766b = (Build.VERSION.SDK_INT < 21);

    /* renamed from: c */
    private static final int[] f767c = {16842836};

    /* renamed from: d */
    private static boolean f768d = true;

    /* renamed from: A */
    boolean f769A;

    /* renamed from: B */
    boolean f770B;

    /* renamed from: C */
    boolean f771C;

    /* renamed from: D */
    boolean f772D;

    /* renamed from: E */
    boolean f773E;

    /* renamed from: F */
    private boolean f774F;

    /* renamed from: G */
    private C0267f[] f775G;

    /* renamed from: H */
    private C0267f f776H;

    /* renamed from: I */
    private boolean f777I;

    /* renamed from: J */
    boolean f778J;

    /* renamed from: K */
    private int f779K = -100;

    /* renamed from: L */
    private boolean f780L;

    /* renamed from: M */
    private C0265d f781M;

    /* renamed from: N */
    boolean f782N;

    /* renamed from: O */
    int f783O;

    /* renamed from: P */
    private final Runnable f784P = new C0254o(this);

    /* renamed from: Q */
    private boolean f785Q;

    /* renamed from: R */
    private Rect f786R;

    /* renamed from: S */
    private Rect f787S;

    /* renamed from: T */
    private AppCompatViewInflater f788T;

    /* renamed from: e */
    final Context f789e;

    /* renamed from: f */
    final Window f790f;

    /* renamed from: g */
    final Window.Callback f791g;

    /* renamed from: h */
    final Window.Callback f792h;

    /* renamed from: i */
    final C0251l f793i;

    /* renamed from: j */
    C0236a f794j;

    /* renamed from: k */
    private CharSequence f795k;

    /* renamed from: l */
    private C0346K f796l;

    /* renamed from: m */
    private C0262a f797m;

    /* renamed from: n */
    private C0268g f798n;

    /* renamed from: o */
    C0164b f799o;

    /* renamed from: p */
    ActionBarContextView f800p;

    /* renamed from: q */
    PopupWindow f801q;

    /* renamed from: r */
    Runnable f802r;

    /* renamed from: s */
    C0134z f803s = null;

    /* renamed from: t */
    private boolean f804t = true;

    /* renamed from: u */
    private boolean f805u;

    /* renamed from: v */
    private ViewGroup f806v;

    /* renamed from: w */
    private TextView f807w;

    /* renamed from: x */
    private View f808x;

    /* renamed from: y */
    private boolean f809y;

    /* renamed from: z */
    private boolean f810z;

    /* renamed from: android.support.v7.app.v$a */
    private final class C0262a implements C0310v.C0311a {
        C0262a() {
        }

        /* renamed from: a */
        public void mo1072a(C0293l lVar, boolean z) {
            C0261v.this.mo1051b(lVar);
        }

        /* renamed from: a */
        public boolean mo1073a(C0293l lVar) {
            Window.Callback k = C0261v.this.mo1065k();
            if (k == null) {
                return true;
            }
            k.onMenuOpened(108, lVar);
            return true;
        }
    }

    /* renamed from: android.support.v7.app.v$b */
    class C0263b implements C0164b.C0165a {

        /* renamed from: a */
        private C0164b.C0165a f812a;

        public C0263b(C0164b.C0165a aVar) {
            this.f812a = aVar;
        }

        /* renamed from: a */
        public void mo663a(C0164b bVar) {
            this.f812a.mo663a(bVar);
            C0261v vVar = C0261v.this;
            if (vVar.f801q != null) {
                vVar.f790f.getDecorView().removeCallbacks(C0261v.this.f802r);
            }
            C0261v vVar2 = C0261v.this;
            if (vVar2.f800p != null) {
                vVar2.mo1058g();
                C0261v vVar3 = C0261v.this;
                C0134z a = C0127u.m436a(vVar3.f800p);
                a.mo503a(0.0f);
                vVar3.f803s = a;
                C0261v.this.f803s.mo505a((C0092A) new C0269w(this));
            }
            C0261v vVar4 = C0261v.this;
            C0251l lVar = vVar4.f793i;
            if (lVar != null) {
                lVar.mo1021a(vVar4.f799o);
            }
            C0261v.this.f799o = null;
        }

        /* renamed from: a */
        public boolean mo664a(C0164b bVar, Menu menu) {
            return this.f812a.mo664a(bVar, menu);
        }

        /* renamed from: a */
        public boolean mo665a(C0164b bVar, MenuItem menuItem) {
            return this.f812a.mo665a(bVar, menuItem);
        }

        /* renamed from: b */
        public boolean mo666b(C0164b bVar, Menu menu) {
            return this.f812a.mo666b(bVar, menu);
        }
    }

    /* renamed from: android.support.v7.app.v$c */
    class C0264c extends C0176j {
        C0264c(Window.Callback callback) {
            super(callback);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public final ActionMode mo1074a(ActionMode.Callback callback) {
            C0169f.C0170a aVar = new C0169f.C0170a(C0261v.this.f789e, callback);
            C0164b a = C0261v.this.mo1041a((C0164b.C0165a) aVar);
            if (a != null) {
                return aVar.mo696b(a);
            }
            return null;
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return C0261v.this.mo1049a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || C0261v.this.mo1052b(keyEvent.getKeyCode(), keyEvent);
        }

        public void onContentChanged() {
        }

        public boolean onCreatePanelMenu(int i, Menu menu) {
            if (i != 0 || (menu instanceof C0293l)) {
                return super.onCreatePanelMenu(i, menu);
            }
            return false;
        }

        public boolean onMenuOpened(int i, Menu menu) {
            super.onMenuOpened(i, menu);
            C0261v.this.mo1059g(i);
            return true;
        }

        public void onPanelClosed(int i, Menu menu) {
            super.onPanelClosed(i, menu);
            C0261v.this.mo1061h(i);
        }

        public boolean onPreparePanel(int i, View view, Menu menu) {
            C0293l lVar = menu instanceof C0293l ? (C0293l) menu : null;
            if (i == 0 && lVar == null) {
                return false;
            }
            if (lVar != null) {
                lVar.mo1299c(true);
            }
            boolean onPreparePanel = super.onPreparePanel(i, view, menu);
            if (lVar != null) {
                lVar.mo1299c(false);
            }
            return onPreparePanel;
        }

        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
            C0293l lVar;
            C0267f a = C0261v.this.mo1042a(0, true);
            if (a == null || (lVar = a.f830j) == null) {
                super.onProvideKeyboardShortcuts(list, menu, i);
            } else {
                super.onProvideKeyboardShortcuts(list, lVar, i);
            }
        }

        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (Build.VERSION.SDK_INT >= 23) {
                return null;
            }
            return C0261v.this.mo1066l() ? mo1074a(callback) : super.onWindowStartingActionMode(callback);
        }

        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            return (!C0261v.this.mo1066l() || i != 0) ? super.onWindowStartingActionMode(callback, i) : mo1074a(callback);
        }
    }

    /* renamed from: android.support.v7.app.v$d */
    final class C0265d {

        /* renamed from: a */
        private C0229C f815a;

        /* renamed from: b */
        private boolean f816b;

        /* renamed from: c */
        private BroadcastReceiver f817c;

        /* renamed from: d */
        private IntentFilter f818d;

        C0265d(C0229C c) {
            this.f815a = c;
            this.f816b = c.mo961a();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo1076a() {
            BroadcastReceiver broadcastReceiver = this.f817c;
            if (broadcastReceiver != null) {
                C0261v.this.f789e.unregisterReceiver(broadcastReceiver);
                this.f817c = null;
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public void mo1077b() {
            boolean a = this.f815a.mo961a();
            if (a != this.f816b) {
                this.f816b = a;
                C0261v.this.mo1028a();
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: c */
        public int mo1078c() {
            this.f816b = this.f815a.mo961a();
            return this.f816b ? 2 : 1;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: d */
        public void mo1079d() {
            mo1076a();
            if (this.f817c == null) {
                this.f817c = new C0270x(this);
            }
            if (this.f818d == null) {
                this.f818d = new IntentFilter();
                this.f818d.addAction("android.intent.action.TIME_SET");
                this.f818d.addAction("android.intent.action.TIMEZONE_CHANGED");
                this.f818d.addAction("android.intent.action.TIME_TICK");
            }
            C0261v.this.f789e.registerReceiver(this.f817c, this.f818d);
        }
    }

    /* renamed from: android.support.v7.app.v$e */
    private class C0266e extends ContentFrameLayout {
        public C0266e(Context context) {
            super(context);
        }

        /* renamed from: a */
        private boolean m1064a(int i, int i2) {
            return i < -5 || i2 < -5 || i > getWidth() + 5 || i2 > getHeight() + 5;
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return C0261v.this.mo1049a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0 || !m1064a((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            C0261v.this.mo1054d(0);
            return true;
        }

        public void setBackgroundResource(int i) {
            setBackgroundDrawable(C0146a.m492b(getContext(), i));
        }
    }

    /* renamed from: android.support.v7.app.v$f */
    protected static final class C0267f {

        /* renamed from: a */
        int f821a;

        /* renamed from: b */
        int f822b;

        /* renamed from: c */
        int f823c;

        /* renamed from: d */
        int f824d;

        /* renamed from: e */
        int f825e;

        /* renamed from: f */
        int f826f;

        /* renamed from: g */
        ViewGroup f827g;

        /* renamed from: h */
        View f828h;

        /* renamed from: i */
        View f829i;

        /* renamed from: j */
        C0293l f830j;

        /* renamed from: k */
        C0290j f831k;

        /* renamed from: l */
        Context f832l;

        /* renamed from: m */
        boolean f833m;

        /* renamed from: n */
        boolean f834n;

        /* renamed from: o */
        boolean f835o;

        /* renamed from: p */
        public boolean f836p;

        /* renamed from: q */
        boolean f837q = false;

        /* renamed from: r */
        boolean f838r;

        /* renamed from: s */
        Bundle f839s;

        C0267f(int i) {
            this.f821a = i;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C0312w mo1083a(C0310v.C0311a aVar) {
            if (this.f830j == null) {
                return null;
            }
            if (this.f831k == null) {
                this.f831k = new C0290j(this.f832l, C0142g.abc_list_menu_item_layout);
                this.f831k.mo1128a(aVar);
                this.f830j.mo1275a((C0310v) this.f831k);
            }
            return this.f831k.mo1249a(this.f827g);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo1084a(Context context) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme newTheme = context.getResources().newTheme();
            newTheme.setTo(context.getTheme());
            newTheme.resolveAttribute(C0136a.actionBarPopupTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                newTheme.applyStyle(i, true);
            }
            newTheme.resolveAttribute(C0136a.panelMenuListTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 == 0) {
                i2 = C0144i.Theme_AppCompat_CompactMenu;
            }
            newTheme.applyStyle(i2, true);
            C0167d dVar = new C0167d(context, 0);
            dVar.getTheme().setTo(newTheme);
            this.f832l = dVar;
            TypedArray obtainStyledAttributes = dVar.obtainStyledAttributes(C0145j.AppCompatTheme);
            this.f822b = obtainStyledAttributes.getResourceId(C0145j.AppCompatTheme_panelBackground, 0);
            this.f826f = obtainStyledAttributes.getResourceId(C0145j.AppCompatTheme_android_windowAnimationStyle, 0);
            obtainStyledAttributes.recycle();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo1085a(C0293l lVar) {
            C0290j jVar;
            C0293l lVar2 = this.f830j;
            if (lVar != lVar2) {
                if (lVar2 != null) {
                    lVar2.mo1294b((C0310v) this.f831k);
                }
                this.f830j = lVar;
                if (lVar != null && (jVar = this.f831k) != null) {
                    lVar.mo1275a((C0310v) jVar);
                }
            }
        }

        /* renamed from: a */
        public boolean mo1086a() {
            if (this.f828h == null) {
                return false;
            }
            return this.f829i != null || this.f831k.mo1250b().getCount() > 0;
        }
    }

    /* renamed from: android.support.v7.app.v$g */
    private final class C0268g implements C0310v.C0311a {
        C0268g() {
        }

        /* renamed from: a */
        public void mo1072a(C0293l lVar, boolean z) {
            C0293l m = lVar.mo1150m();
            boolean z2 = m != lVar;
            C0261v vVar = C0261v.this;
            if (z2) {
                lVar = m;
            }
            C0267f a = vVar.mo1043a((Menu) lVar);
            if (a == null) {
                return;
            }
            if (z2) {
                C0261v.this.mo1045a(a.f821a, a, m);
                C0261v.this.mo1046a(a, true);
                return;
            }
            C0261v.this.mo1046a(a, z);
        }

        /* renamed from: a */
        public boolean mo1073a(C0293l lVar) {
            Window.Callback k;
            if (lVar != null) {
                return true;
            }
            C0261v vVar = C0261v.this;
            if (!vVar.f769A || (k = vVar.mo1065k()) == null || C0261v.this.f778J) {
                return true;
            }
            k.onMenuOpened(108, lVar);
            return true;
        }
    }

    static {
        if (f766b && !f768d) {
            Thread.setDefaultUncaughtExceptionHandler(new C0253n(Thread.getDefaultUncaughtExceptionHandler()));
        }
    }

    C0261v(Context context, Window window, C0251l lVar) {
        this.f789e = context;
        this.f790f = window;
        this.f793i = lVar;
        this.f791g = this.f790f.getCallback();
        Window.Callback callback = this.f791g;
        if (!(callback instanceof C0264c)) {
            this.f792h = new C0264c(callback);
            this.f790f.setCallback(this.f792h);
            C0439ta a = C0439ta.m1901a(context, (AttributeSet) null, f767c);
            Drawable c = a.mo2279c(0);
            if (c != null) {
                this.f790f.setBackgroundDrawable(c);
            }
            a.mo2274a();
            return;
        }
        throw new IllegalStateException("AppCompat has already installed itself into the Window");
    }

    /* renamed from: a */
    private void m989a(C0267f fVar, KeyEvent keyEvent) {
        int i;
        ViewGroup.LayoutParams layoutParams;
        if (!fVar.f835o && !this.f778J) {
            if (fVar.f821a == 0) {
                if ((this.f789e.getResources().getConfiguration().screenLayout & 15) == 4) {
                    return;
                }
            }
            Window.Callback k = mo1065k();
            if (k == null || k.onMenuOpened(fVar.f821a, fVar.f830j)) {
                WindowManager windowManager = (WindowManager) this.f789e.getSystemService("window");
                if (windowManager != null && m995b(fVar, keyEvent)) {
                    if (fVar.f827g == null || fVar.f837q) {
                        ViewGroup viewGroup = fVar.f827g;
                        if (viewGroup == null) {
                            if (!m994b(fVar) || fVar.f827g == null) {
                                return;
                            }
                        } else if (fVar.f837q && viewGroup.getChildCount() > 0) {
                            fVar.f827g.removeAllViews();
                        }
                        if (m991a(fVar) && fVar.mo1086a()) {
                            ViewGroup.LayoutParams layoutParams2 = fVar.f828h.getLayoutParams();
                            if (layoutParams2 == null) {
                                layoutParams2 = new ViewGroup.LayoutParams(-2, -2);
                            }
                            fVar.f827g.setBackgroundResource(fVar.f822b);
                            ViewParent parent = fVar.f828h.getParent();
                            if (parent != null && (parent instanceof ViewGroup)) {
                                ((ViewGroup) parent).removeView(fVar.f828h);
                            }
                            fVar.f827g.addView(fVar.f828h, layoutParams2);
                            if (!fVar.f828h.hasFocus()) {
                                fVar.f828h.requestFocus();
                            }
                        } else {
                            return;
                        }
                    } else {
                        View view = fVar.f829i;
                        if (!(view == null || (layoutParams = view.getLayoutParams()) == null || layoutParams.width != -1)) {
                            i = -1;
                            fVar.f834n = false;
                            WindowManager.LayoutParams layoutParams3 = new WindowManager.LayoutParams(i, -2, fVar.f824d, fVar.f825e, GameControllerDelegate.THUMBSTICK_RIGHT_X, 8519680, -3);
                            layoutParams3.gravity = fVar.f823c;
                            layoutParams3.windowAnimations = fVar.f826f;
                            windowManager.addView(fVar.f827g, layoutParams3);
                            fVar.f835o = true;
                            return;
                        }
                    }
                    i = -2;
                    fVar.f834n = false;
                    WindowManager.LayoutParams layoutParams32 = new WindowManager.LayoutParams(i, -2, fVar.f824d, fVar.f825e, GameControllerDelegate.THUMBSTICK_RIGHT_X, 8519680, -3);
                    layoutParams32.gravity = fVar.f823c;
                    layoutParams32.windowAnimations = fVar.f826f;
                    windowManager.addView(fVar.f827g, layoutParams32);
                    fVar.f835o = true;
                    return;
                }
                return;
            }
            mo1046a(fVar, true);
        }
    }

    /* renamed from: a */
    private void m990a(C0293l lVar, boolean z) {
        C0346K k = this.f796l;
        if (k == null || !k.mo1578c() || (ViewConfiguration.get(this.f789e).hasPermanentMenuKey() && !this.f796l.mo1580d())) {
            C0267f a = mo1042a(0, true);
            a.f837q = true;
            mo1046a(a, false);
            m989a(a, (KeyEvent) null);
            return;
        }
        Window.Callback k2 = mo1065k();
        if (this.f796l.mo1576a() && z) {
            this.f796l.mo1582e();
            if (!this.f778J) {
                k2.onPanelClosed(108, mo1042a(0, true).f830j);
            }
        } else if (k2 != null && !this.f778J) {
            if (this.f782N && (this.f783O & 1) != 0) {
                this.f790f.getDecorView().removeCallbacks(this.f784P);
                this.f784P.run();
            }
            C0267f a2 = mo1042a(0, true);
            C0293l lVar2 = a2.f830j;
            if (lVar2 != null && !a2.f838r && k2.onPreparePanel(0, a2.f829i, lVar2)) {
                k2.onMenuOpened(108, a2.f830j);
                this.f796l.mo1583f();
            }
        }
    }

    /* renamed from: a */
    private boolean m991a(C0267f fVar) {
        View view = fVar.f829i;
        if (view != null) {
            fVar.f828h = view;
            return true;
        } else if (fVar.f830j == null) {
            return false;
        } else {
            if (this.f798n == null) {
                this.f798n = new C0268g();
            }
            fVar.f828h = (View) fVar.mo1083a((C0310v.C0311a) this.f798n);
            return fVar.f828h != null;
        }
    }

    /* renamed from: a */
    private boolean m992a(C0267f fVar, int i, KeyEvent keyEvent, int i2) {
        C0293l lVar;
        boolean z = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((fVar.f833m || m995b(fVar, keyEvent)) && (lVar = fVar.f830j) != null) {
            z = lVar.performShortcut(i, keyEvent, i2);
        }
        if (z && (i2 & 1) == 0 && this.f796l == null) {
            mo1046a(fVar, true);
        }
        return z;
    }

    /* renamed from: a */
    private boolean m993a(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.f790f.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || C0127u.m454h((View) viewParent)) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    /* renamed from: b */
    private boolean m994b(C0267f fVar) {
        fVar.mo1084a(mo1060h());
        fVar.f827g = new C0266e(fVar.f832l);
        fVar.f823c = 81;
        return true;
    }

    /* renamed from: b */
    private boolean m995b(C0267f fVar, KeyEvent keyEvent) {
        C0346K k;
        C0346K k2;
        C0346K k3;
        if (this.f778J) {
            return false;
        }
        if (fVar.f833m) {
            return true;
        }
        C0267f fVar2 = this.f776H;
        if (!(fVar2 == null || fVar2 == fVar)) {
            mo1046a(fVar2, false);
        }
        Window.Callback k4 = mo1065k();
        if (k4 != null) {
            fVar.f829i = k4.onCreatePanelView(fVar.f821a);
        }
        int i = fVar.f821a;
        boolean z = i == 0 || i == 108;
        if (z && (k3 = this.f796l) != null) {
            k3.mo1577b();
        }
        if (fVar.f829i == null) {
            if (z) {
                mo1068n();
            }
            if (fVar.f830j == null || fVar.f838r) {
                if (fVar.f830j == null && (!m996c(fVar) || fVar.f830j == null)) {
                    return false;
                }
                if (z && this.f796l != null) {
                    if (this.f797m == null) {
                        this.f797m = new C0262a();
                    }
                    this.f796l.mo1575a(fVar.f830j, this.f797m);
                }
                fVar.f830j.mo1324s();
                if (!k4.onCreatePanelMenu(fVar.f821a, fVar.f830j)) {
                    fVar.mo1085a((C0293l) null);
                    if (z && (k2 = this.f796l) != null) {
                        k2.mo1575a((Menu) null, this.f797m);
                    }
                    return false;
                }
                fVar.f838r = false;
            }
            fVar.f830j.mo1324s();
            Bundle bundle = fVar.f839s;
            if (bundle != null) {
                fVar.f830j.mo1274a(bundle);
                fVar.f839s = null;
            }
            if (!k4.onPreparePanel(0, fVar.f829i, fVar.f830j)) {
                if (z && (k = this.f796l) != null) {
                    k.mo1575a((Menu) null, this.f797m);
                }
                fVar.f830j.mo1321r();
                return false;
            }
            fVar.f836p = KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1;
            fVar.f830j.setQwertyMode(fVar.f836p);
            fVar.f830j.mo1321r();
        }
        fVar.f833m = true;
        fVar.f834n = false;
        this.f776H = fVar;
        return true;
    }

    /* renamed from: c */
    private boolean m996c(C0267f fVar) {
        Context context = this.f789e;
        int i = fVar.f821a;
        if ((i == 0 || i == 108) && this.f796l != null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = context.getTheme();
            theme.resolveAttribute(C0136a.actionBarTheme, typedValue, true);
            Resources.Theme theme2 = null;
            if (typedValue.resourceId != 0) {
                theme2 = context.getResources().newTheme();
                theme2.setTo(theme);
                theme2.applyStyle(typedValue.resourceId, true);
                theme2.resolveAttribute(C0136a.actionBarWidgetTheme, typedValue, true);
            } else {
                theme.resolveAttribute(C0136a.actionBarWidgetTheme, typedValue, true);
            }
            if (typedValue.resourceId != 0) {
                if (theme2 == null) {
                    theme2 = context.getResources().newTheme();
                    theme2.setTo(theme);
                }
                theme2.applyStyle(typedValue.resourceId, true);
            }
            if (theme2 != null) {
                C0167d dVar = new C0167d(context, 0);
                dVar.getTheme().setTo(theme2);
                context = dVar;
            }
        }
        C0293l lVar = new C0293l(context);
        lVar.mo1144a((C0293l.C0294a) this);
        fVar.mo1085a(lVar);
        return true;
    }

    /* renamed from: d */
    private boolean m997d(int i, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() != 0) {
            return false;
        }
        C0267f a = mo1042a(i, true);
        if (!a.f835o) {
            return m995b(a, keyEvent);
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x006c  */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m998e(int r4, android.view.KeyEvent r5) {
        /*
            r3 = this;
            a.a.d.d.b r0 = r3.f799o
            r1 = 0
            if (r0 == 0) goto L_0x0006
            return r1
        L_0x0006:
            r0 = 1
            android.support.v7.app.v$f r2 = r3.mo1042a((int) r4, (boolean) r0)
            if (r4 != 0) goto L_0x0043
            android.support.v7.widget.K r4 = r3.f796l
            if (r4 == 0) goto L_0x0043
            boolean r4 = r4.mo1578c()
            if (r4 == 0) goto L_0x0043
            android.content.Context r4 = r3.f789e
            android.view.ViewConfiguration r4 = android.view.ViewConfiguration.get(r4)
            boolean r4 = r4.hasPermanentMenuKey()
            if (r4 != 0) goto L_0x0043
            android.support.v7.widget.K r4 = r3.f796l
            boolean r4 = r4.mo1576a()
            if (r4 != 0) goto L_0x003c
            boolean r4 = r3.f778J
            if (r4 != 0) goto L_0x0063
            boolean r4 = r3.m995b((android.support.p025v7.app.C0261v.C0267f) r2, (android.view.KeyEvent) r5)
            if (r4 == 0) goto L_0x0063
            android.support.v7.widget.K r4 = r3.f796l
            boolean r4 = r4.mo1583f()
            goto L_0x006a
        L_0x003c:
            android.support.v7.widget.K r4 = r3.f796l
            boolean r4 = r4.mo1582e()
            goto L_0x006a
        L_0x0043:
            boolean r4 = r2.f835o
            if (r4 != 0) goto L_0x0065
            boolean r4 = r2.f834n
            if (r4 == 0) goto L_0x004c
            goto L_0x0065
        L_0x004c:
            boolean r4 = r2.f833m
            if (r4 == 0) goto L_0x0063
            boolean r4 = r2.f838r
            if (r4 == 0) goto L_0x005b
            r2.f833m = r1
            boolean r4 = r3.m995b((android.support.p025v7.app.C0261v.C0267f) r2, (android.view.KeyEvent) r5)
            goto L_0x005c
        L_0x005b:
            r4 = 1
        L_0x005c:
            if (r4 == 0) goto L_0x0063
            r3.m989a((android.support.p025v7.app.C0261v.C0267f) r2, (android.view.KeyEvent) r5)
            r4 = 1
            goto L_0x006a
        L_0x0063:
            r4 = 0
            goto L_0x006a
        L_0x0065:
            boolean r4 = r2.f835o
            r3.mo1046a((android.support.p025v7.app.C0261v.C0267f) r2, (boolean) r0)
        L_0x006a:
            if (r4 == 0) goto L_0x0083
            android.content.Context r5 = r3.f789e
            java.lang.String r0 = "audio"
            java.lang.Object r5 = r5.getSystemService(r0)
            android.media.AudioManager r5 = (android.media.AudioManager) r5
            if (r5 == 0) goto L_0x007c
            r5.playSoundEffect(r1)
            goto L_0x0083
        L_0x007c:
            java.lang.String r5 = "AppCompatDelegate"
            java.lang.String r0 = "Couldn't get audio manager"
            android.util.Log.w(r5, r0)
        L_0x0083:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.app.C0261v.m998e(int, android.view.KeyEvent):boolean");
    }

    /* renamed from: j */
    private void m999j(int i) {
        this.f783O = (1 << i) | this.f783O;
        if (!this.f782N) {
            C0127u.m444a(this.f790f.getDecorView(), this.f784P);
            this.f782N = true;
        }
    }

    /* renamed from: k */
    private int m1000k(int i) {
        if (i == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return 108;
        } else if (i != 9) {
            return i;
        } else {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            return 109;
        }
    }

    /* renamed from: l */
    private boolean m1001l(int i) {
        Resources resources = this.f789e.getResources();
        Configuration configuration = resources.getConfiguration();
        int i2 = configuration.uiMode & 48;
        int i3 = i == 2 ? 32 : 16;
        if (i2 == i3) {
            return false;
        }
        if (m1008v()) {
            ((Activity) this.f789e).recreate();
            return true;
        }
        Configuration configuration2 = new Configuration(configuration);
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        configuration2.uiMode = i3 | (configuration2.uiMode & -49);
        resources.updateConfiguration(configuration2, displayMetrics);
        if (Build.VERSION.SDK_INT >= 26) {
            return true;
        }
        C0222A.m822a(resources);
        return true;
    }

    /* renamed from: p */
    private void m1002p() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.f806v.findViewById(16908290);
        View decorView = this.f790f.getDecorView();
        contentFrameLayout.mo1688a(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray obtainStyledAttributes = this.f789e.obtainStyledAttributes(C0145j.AppCompatTheme);
        obtainStyledAttributes.getValue(C0145j.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        obtainStyledAttributes.getValue(C0145j.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        if (obtainStyledAttributes.hasValue(C0145j.AppCompatTheme_windowFixedWidthMajor)) {
            obtainStyledAttributes.getValue(C0145j.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout.getFixedWidthMajor());
        }
        if (obtainStyledAttributes.hasValue(C0145j.AppCompatTheme_windowFixedWidthMinor)) {
            obtainStyledAttributes.getValue(C0145j.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout.getFixedWidthMinor());
        }
        if (obtainStyledAttributes.hasValue(C0145j.AppCompatTheme_windowFixedHeightMajor)) {
            obtainStyledAttributes.getValue(C0145j.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout.getFixedHeightMajor());
        }
        if (obtainStyledAttributes.hasValue(C0145j.AppCompatTheme_windowFixedHeightMinor)) {
            obtainStyledAttributes.getValue(C0145j.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout.getFixedHeightMinor());
        }
        obtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    /* renamed from: q */
    private ViewGroup m1003q() {
        ViewGroup viewGroup;
        TypedArray obtainStyledAttributes = this.f789e.obtainStyledAttributes(C0145j.AppCompatTheme);
        if (obtainStyledAttributes.hasValue(C0145j.AppCompatTheme_windowActionBar)) {
            if (obtainStyledAttributes.getBoolean(C0145j.AppCompatTheme_windowNoTitle, false)) {
                mo1030b(1);
            } else if (obtainStyledAttributes.getBoolean(C0145j.AppCompatTheme_windowActionBar, false)) {
                mo1030b(108);
            }
            if (obtainStyledAttributes.getBoolean(C0145j.AppCompatTheme_windowActionBarOverlay, false)) {
                mo1030b(109);
            }
            if (obtainStyledAttributes.getBoolean(C0145j.AppCompatTheme_windowActionModeOverlay, false)) {
                mo1030b(10);
            }
            this.f772D = obtainStyledAttributes.getBoolean(C0145j.AppCompatTheme_android_windowIsFloating, false);
            obtainStyledAttributes.recycle();
            this.f790f.getDecorView();
            LayoutInflater from = LayoutInflater.from(this.f789e);
            if (this.f773E) {
                viewGroup = (ViewGroup) from.inflate(this.f771C ? C0142g.abc_screen_simple_overlay_action_mode : C0142g.abc_screen_simple, (ViewGroup) null);
                if (Build.VERSION.SDK_INT >= 21) {
                    C0127u.m440a((View) viewGroup, (C0123q) new C0255p(this));
                } else {
                    ((C0352O) viewGroup).setOnFitSystemWindowsListener(new C0256q(this));
                }
            } else if (this.f772D) {
                viewGroup = (ViewGroup) from.inflate(C0142g.abc_dialog_title_material, (ViewGroup) null);
                this.f770B = false;
                this.f769A = false;
            } else if (this.f769A) {
                TypedValue typedValue = new TypedValue();
                this.f789e.getTheme().resolveAttribute(C0136a.actionBarTheme, typedValue, true);
                int i = typedValue.resourceId;
                viewGroup = (ViewGroup) LayoutInflater.from(i != 0 ? new C0167d(this.f789e, i) : this.f789e).inflate(C0142g.abc_screen_toolbar, (ViewGroup) null);
                this.f796l = (C0346K) viewGroup.findViewById(C0141f.decor_content_parent);
                this.f796l.setWindowCallback(mo1065k());
                if (this.f770B) {
                    this.f796l.mo1574a(109);
                }
                if (this.f809y) {
                    this.f796l.mo1574a(2);
                }
                if (this.f810z) {
                    this.f796l.mo1574a(5);
                }
            } else {
                viewGroup = null;
            }
            if (viewGroup != null) {
                if (this.f796l == null) {
                    this.f807w = (TextView) viewGroup.findViewById(C0141f.title);
                }
                C0342Ha.m1499b(viewGroup);
                ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(C0141f.action_bar_activity_content);
                ViewGroup viewGroup2 = (ViewGroup) this.f790f.findViewById(16908290);
                if (viewGroup2 != null) {
                    while (viewGroup2.getChildCount() > 0) {
                        View childAt = viewGroup2.getChildAt(0);
                        viewGroup2.removeViewAt(0);
                        contentFrameLayout.addView(childAt);
                    }
                    viewGroup2.setId(-1);
                    contentFrameLayout.setId(16908290);
                    if (viewGroup2 instanceof FrameLayout) {
                        ((FrameLayout) viewGroup2).setForeground((Drawable) null);
                    }
                }
                this.f790f.setContentView(viewGroup);
                contentFrameLayout.setAttachListener(new C0257r(this));
                return viewGroup;
            }
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.f769A + ", windowActionBarOverlay: " + this.f770B + ", android:windowIsFloating: " + this.f772D + ", windowActionModeOverlay: " + this.f771C + ", windowNoTitle: " + this.f773E + " }");
        }
        obtainStyledAttributes.recycle();
        throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
    }

    /* renamed from: r */
    private void m1004r() {
        if (this.f781M == null) {
            this.f781M = new C0265d(C0229C.m880a(this.f789e));
        }
    }

    /* renamed from: s */
    private void m1005s() {
        if (!this.f805u) {
            this.f806v = m1003q();
            CharSequence j = mo1064j();
            if (!TextUtils.isEmpty(j)) {
                C0346K k = this.f796l;
                if (k != null) {
                    k.setWindowTitle(j);
                } else if (mo1068n() != null) {
                    mo1068n().mo966a(j);
                } else {
                    TextView textView = this.f807w;
                    if (textView != null) {
                        textView.setText(j);
                    }
                }
            }
            m1002p();
            mo1047a(this.f806v);
            this.f805u = true;
            C0267f a = mo1042a(0, false);
            if (this.f778J) {
                return;
            }
            if (a == null || a.f830j == null) {
                m999j(108);
            }
        }
    }

    /* renamed from: t */
    private int m1006t() {
        int i = this.f779K;
        return i != -100 ? i : C0252m.m968b();
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* renamed from: u */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1007u() {
        /*
            r3 = this;
            r3.m1005s()
            boolean r0 = r3.f769A
            if (r0 == 0) goto L_0x0033
            android.support.v7.app.a r0 = r3.f794j
            if (r0 == 0) goto L_0x000c
            goto L_0x0033
        L_0x000c:
            android.view.Window$Callback r0 = r3.f791g
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L_0x001e
            android.support.v7.app.G r1 = new android.support.v7.app.G
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r2 = r3.f770B
            r1.<init>(r0, r2)
        L_0x001b:
            r3.f794j = r1
            goto L_0x002a
        L_0x001e:
            boolean r1 = r0 instanceof android.app.Dialog
            if (r1 == 0) goto L_0x002a
            android.support.v7.app.G r1 = new android.support.v7.app.G
            android.app.Dialog r0 = (android.app.Dialog) r0
            r1.<init>(r0)
            goto L_0x001b
        L_0x002a:
            android.support.v7.app.a r0 = r3.f794j
            if (r0 == 0) goto L_0x0033
            boolean r1 = r3.f785Q
            r0.mo972c(r1)
        L_0x0033:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.app.C0261v.m1007u():void");
    }

    /* renamed from: v */
    private boolean m1008v() {
        if (this.f780L) {
            Context context = this.f789e;
            if (context instanceof Activity) {
                try {
                    return (context.getPackageManager().getActivityInfo(new ComponentName(this.f789e, this.f789e.getClass()), 0).configChanges & 512) == 0;
                } catch (PackageManager.NameNotFoundException e) {
                    Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e);
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: w */
    private void m1009w() {
        if (this.f805u) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    /* renamed from: a */
    public C0164b mo1041a(C0164b.C0165a aVar) {
        C0251l lVar;
        if (aVar != null) {
            C0164b bVar = this.f799o;
            if (bVar != null) {
                bVar.mo646a();
            }
            C0263b bVar2 = new C0263b(aVar);
            C0236a i = mo1063i();
            if (i != null) {
                this.f799o = i.mo962a((C0164b.C0165a) bVar2);
                C0164b bVar3 = this.f799o;
                if (!(bVar3 == null || (lVar = this.f793i) == null)) {
                    lVar.mo1022b(bVar3);
                }
            }
            if (this.f799o == null) {
                this.f799o = mo1050b((C0164b.C0165a) bVar2);
            }
            return this.f799o;
        }
        throw new IllegalArgumentException("ActionMode callback can not be null.");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0267f mo1042a(int i, boolean z) {
        C0267f[] fVarArr = this.f775G;
        if (fVarArr == null || fVarArr.length <= i) {
            C0267f[] fVarArr2 = new C0267f[(i + 1)];
            if (fVarArr != null) {
                System.arraycopy(fVarArr, 0, fVarArr2, 0, fVarArr.length);
            }
            this.f775G = fVarArr2;
            fVarArr = fVarArr2;
        }
        C0267f fVar = fVarArr[i];
        if (fVar != null) {
            return fVar;
        }
        C0267f fVar2 = new C0267f(i);
        fVarArr[i] = fVar2;
        return fVar2;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C0267f mo1043a(Menu menu) {
        C0267f[] fVarArr = this.f775G;
        int length = fVarArr != null ? fVarArr.length : 0;
        for (int i = 0; i < length; i++) {
            C0267f fVar = fVarArr[i];
            if (fVar != null && fVar.f830j == menu) {
                return fVar;
            }
        }
        return null;
    }

    /* renamed from: a */
    public <T extends View> T mo1023a(int i) {
        m1005s();
        return this.f790f.findViewById(i);
    }

    /* renamed from: a */
    public View mo1044a(View view, String str, Context context, AttributeSet attributeSet) {
        boolean z;
        AppCompatViewInflater appCompatViewInflater;
        boolean z2 = false;
        if (this.f788T == null) {
            String string = this.f789e.obtainStyledAttributes(C0145j.AppCompatTheme).getString(C0145j.AppCompatTheme_viewInflaterClass);
            if (string == null || AppCompatViewInflater.class.getName().equals(string)) {
                appCompatViewInflater = new AppCompatViewInflater();
            } else {
                try {
                    this.f788T = (AppCompatViewInflater) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    appCompatViewInflater = new AppCompatViewInflater();
                }
            }
            this.f788T = appCompatViewInflater;
        }
        if (f766b) {
            if (!(attributeSet instanceof XmlPullParser)) {
                z2 = m993a((ViewParent) view);
            } else if (((XmlPullParser) attributeSet).getDepth() > 1) {
                z2 = true;
            }
            z = z2;
        } else {
            z = false;
        }
        return this.f788T.mo946a(view, str, context, attributeSet, z, f766b, true, C0340Ga.m1474b());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1045a(int i, C0267f fVar, Menu menu) {
        if (menu == null) {
            if (fVar == null && i >= 0) {
                C0267f[] fVarArr = this.f775G;
                if (i < fVarArr.length) {
                    fVar = fVarArr[i];
                }
            }
            if (fVar != null) {
                menu = fVar.f830j;
            }
        }
        if ((fVar == null || fVar.f835o) && !this.f778J) {
            this.f791g.onPanelClosed(i, menu);
        }
    }

    /* renamed from: a */
    public void mo1024a(Bundle bundle) {
        Window.Callback callback = this.f791g;
        if (callback instanceof Activity) {
            String str = null;
            try {
                str = C0189e.m663a((Activity) callback);
            } catch (IllegalArgumentException unused) {
            }
            if (str != null) {
                C0236a n = mo1068n();
                if (n == null) {
                    this.f785Q = true;
                } else {
                    n.mo972c(true);
                }
            }
        }
        if (bundle != null && this.f779K == -100) {
            this.f779K = bundle.getInt("appcompat:local_night_mode", -100);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1046a(C0267f fVar, boolean z) {
        ViewGroup viewGroup;
        C0346K k;
        if (!z || fVar.f821a != 0 || (k = this.f796l) == null || !k.mo1576a()) {
            WindowManager windowManager = (WindowManager) this.f789e.getSystemService("window");
            if (!(windowManager == null || !fVar.f835o || (viewGroup = fVar.f827g) == null)) {
                windowManager.removeView(viewGroup);
                if (z) {
                    mo1045a(fVar.f821a, fVar, (Menu) null);
                }
            }
            fVar.f833m = false;
            fVar.f834n = false;
            fVar.f835o = false;
            fVar.f828h = null;
            fVar.f837q = true;
            if (this.f776H == fVar) {
                this.f776H = null;
                return;
            }
            return;
        }
        mo1051b(fVar.f830j);
    }

    /* renamed from: a */
    public void mo677a(C0293l lVar) {
        m990a(lVar, true);
    }

    /* renamed from: a */
    public void mo1025a(View view) {
        m1005s();
        ViewGroup viewGroup = (ViewGroup) this.f806v.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.f791g.onContentChanged();
    }

    /* renamed from: a */
    public void mo1026a(View view, ViewGroup.LayoutParams layoutParams) {
        m1005s();
        ((ViewGroup) this.f806v.findViewById(16908290)).addView(view, layoutParams);
        this.f791g.onContentChanged();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1047a(ViewGroup viewGroup) {
    }

    /* renamed from: a */
    public final void mo1027a(CharSequence charSequence) {
        this.f795k = charSequence;
        C0346K k = this.f796l;
        if (k != null) {
            k.setWindowTitle(charSequence);
        } else if (mo1068n() != null) {
            mo1068n().mo966a(charSequence);
        } else {
            TextView textView = this.f807w;
            if (textView != null) {
                textView.setText(charSequence);
            }
        }
    }

    /* renamed from: a */
    public boolean mo1028a() {
        int t = m1006t();
        int f = mo1056f(t);
        boolean l = f != -1 ? m1001l(f) : false;
        if (t == 0) {
            m1004r();
            this.f781M.mo1079d();
        }
        this.f780L = true;
        return l;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo1048a(int i, KeyEvent keyEvent) {
        boolean z = true;
        if (i == 4) {
            if ((keyEvent.getFlags() & 128) == 0) {
                z = false;
            }
            this.f777I = z;
        } else if (i == 82) {
            m997d(0, keyEvent);
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public boolean mo678a(C0293l lVar, MenuItem menuItem) {
        C0267f a;
        Window.Callback k = mo1065k();
        if (k == null || this.f778J || (a = mo1043a((Menu) lVar.mo1150m())) == null) {
            return false;
        }
        return k.onMenuItemSelected(a.f821a, menuItem);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo1049a(KeyEvent keyEvent) {
        View decorView;
        Window.Callback callback = this.f791g;
        boolean z = true;
        if (((callback instanceof C0112g.C0113a) || (callback instanceof C0272z)) && (decorView = this.f790f.getDecorView()) != null && C0112g.m401a(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.f791g.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getAction() != 0) {
            z = false;
        }
        return z ? mo1048a(keyCode, keyEvent) : mo1053c(keyCode, keyEvent);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0029  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public p000a.p001a.p017d.p023d.C0164b mo1050b(p000a.p001a.p017d.p023d.C0164b.C0165a r8) {
        /*
            r7 = this;
            r7.mo1058g()
            a.a.d.d.b r0 = r7.f799o
            if (r0 == 0) goto L_0x000a
            r0.mo646a()
        L_0x000a:
            boolean r0 = r8 instanceof android.support.p025v7.app.C0261v.C0263b
            if (r0 != 0) goto L_0x0014
            android.support.v7.app.v$b r0 = new android.support.v7.app.v$b
            r0.<init>(r8)
            r8 = r0
        L_0x0014:
            android.support.v7.app.l r0 = r7.f793i
            r1 = 0
            if (r0 == 0) goto L_0x0022
            boolean r2 = r7.f778J
            if (r2 != 0) goto L_0x0022
            a.a.d.d.b r0 = r0.mo1020a((p000a.p001a.p017d.p023d.C0164b.C0165a) r8)     // Catch:{ AbstractMethodError -> 0x0022 }
            goto L_0x0023
        L_0x0022:
            r0 = r1
        L_0x0023:
            if (r0 == 0) goto L_0x0029
            r7.f799o = r0
            goto L_0x0165
        L_0x0029:
            android.support.v7.widget.ActionBarContextView r0 = r7.f800p
            r2 = 0
            r3 = 1
            if (r0 != 0) goto L_0x00d6
            boolean r0 = r7.f772D
            if (r0 == 0) goto L_0x00b7
            android.util.TypedValue r0 = new android.util.TypedValue
            r0.<init>()
            android.content.Context r4 = r7.f789e
            android.content.res.Resources$Theme r4 = r4.getTheme()
            int r5 = p000a.p001a.p017d.p018a.C0136a.actionBarTheme
            r4.resolveAttribute(r5, r0, r3)
            int r5 = r0.resourceId
            if (r5 == 0) goto L_0x0068
            android.content.Context r5 = r7.f789e
            android.content.res.Resources r5 = r5.getResources()
            android.content.res.Resources$Theme r5 = r5.newTheme()
            r5.setTo(r4)
            int r4 = r0.resourceId
            r5.applyStyle(r4, r3)
            a.a.d.d.d r4 = new a.a.d.d.d
            android.content.Context r6 = r7.f789e
            r4.<init>((android.content.Context) r6, (int) r2)
            android.content.res.Resources$Theme r6 = r4.getTheme()
            r6.setTo(r5)
            goto L_0x006a
        L_0x0068:
            android.content.Context r4 = r7.f789e
        L_0x006a:
            android.support.v7.widget.ActionBarContextView r5 = new android.support.v7.widget.ActionBarContextView
            r5.<init>(r4)
            r7.f800p = r5
            android.widget.PopupWindow r5 = new android.widget.PopupWindow
            int r6 = p000a.p001a.p017d.p018a.C0136a.actionModePopupWindowStyle
            r5.<init>(r4, r1, r6)
            r7.f801q = r5
            android.widget.PopupWindow r5 = r7.f801q
            r6 = 2
            android.support.p024v4.widget.C0216k.m800a((android.widget.PopupWindow) r5, (int) r6)
            android.widget.PopupWindow r5 = r7.f801q
            android.support.v7.widget.ActionBarContextView r6 = r7.f800p
            r5.setContentView(r6)
            android.widget.PopupWindow r5 = r7.f801q
            r6 = -1
            r5.setWidth(r6)
            android.content.res.Resources$Theme r5 = r4.getTheme()
            int r6 = p000a.p001a.p017d.p018a.C0136a.actionBarSize
            r5.resolveAttribute(r6, r0, r3)
            int r0 = r0.data
            android.content.res.Resources r4 = r4.getResources()
            android.util.DisplayMetrics r4 = r4.getDisplayMetrics()
            int r0 = android.util.TypedValue.complexToDimensionPixelSize(r0, r4)
            android.support.v7.widget.ActionBarContextView r4 = r7.f800p
            r4.setContentHeight(r0)
            android.widget.PopupWindow r0 = r7.f801q
            r4 = -2
            r0.setHeight(r4)
            android.support.v7.app.t r0 = new android.support.v7.app.t
            r0.<init>(r7)
            r7.f802r = r0
            goto L_0x00d6
        L_0x00b7:
            android.view.ViewGroup r0 = r7.f806v
            int r4 = p000a.p001a.p017d.p018a.C0141f.action_mode_bar_stub
            android.view.View r0 = r0.findViewById(r4)
            android.support.v7.widget.ViewStubCompat r0 = (android.support.p025v7.widget.ViewStubCompat) r0
            if (r0 == 0) goto L_0x00d6
            android.content.Context r4 = r7.mo1060h()
            android.view.LayoutInflater r4 = android.view.LayoutInflater.from(r4)
            r0.setLayoutInflater(r4)
            android.view.View r0 = r0.mo2026a()
            android.support.v7.widget.ActionBarContextView r0 = (android.support.p025v7.widget.ActionBarContextView) r0
            r7.f800p = r0
        L_0x00d6:
            android.support.v7.widget.ActionBarContextView r0 = r7.f800p
            if (r0 == 0) goto L_0x0165
            r7.mo1058g()
            android.support.v7.widget.ActionBarContextView r0 = r7.f800p
            r0.mo1553c()
            a.a.d.d.e r0 = new a.a.d.d.e
            android.support.v7.widget.ActionBarContextView r4 = r7.f800p
            android.content.Context r4 = r4.getContext()
            android.support.v7.widget.ActionBarContextView r5 = r7.f800p
            android.widget.PopupWindow r6 = r7.f801q
            if (r6 != 0) goto L_0x00f1
            goto L_0x00f2
        L_0x00f1:
            r3 = 0
        L_0x00f2:
            r0.<init>(r4, r5, r8, r3)
            android.view.Menu r3 = r0.mo655c()
            boolean r8 = r8.mo664a((p000a.p001a.p017d.p023d.C0164b) r0, (android.view.Menu) r3)
            if (r8 == 0) goto L_0x0163
            r0.mo661i()
            android.support.v7.widget.ActionBarContextView r8 = r7.f800p
            r8.mo1551a(r0)
            r7.f799o = r0
            boolean r8 = r7.mo1069o()
            r0 = 1065353216(0x3f800000, float:1.0)
            if (r8 == 0) goto L_0x012d
            android.support.v7.widget.ActionBarContextView r8 = r7.f800p
            r1 = 0
            r8.setAlpha(r1)
            android.support.v7.widget.ActionBarContextView r8 = r7.f800p
            a.a.c.g.z r8 = p000a.p001a.p005c.p014g.C0127u.m436a(r8)
            r8.mo503a((float) r0)
            r7.f803s = r8
            a.a.c.g.z r8 = r7.f803s
            android.support.v7.app.u r0 = new android.support.v7.app.u
            r0.<init>(r7)
            r8.mo505a((p000a.p001a.p005c.p014g.C0092A) r0)
            goto L_0x0153
        L_0x012d:
            android.support.v7.widget.ActionBarContextView r8 = r7.f800p
            r8.setAlpha(r0)
            android.support.v7.widget.ActionBarContextView r8 = r7.f800p
            r8.setVisibility(r2)
            android.support.v7.widget.ActionBarContextView r8 = r7.f800p
            r0 = 32
            r8.sendAccessibilityEvent(r0)
            android.support.v7.widget.ActionBarContextView r8 = r7.f800p
            android.view.ViewParent r8 = r8.getParent()
            boolean r8 = r8 instanceof android.view.View
            if (r8 == 0) goto L_0x0153
            android.support.v7.widget.ActionBarContextView r8 = r7.f800p
            android.view.ViewParent r8 = r8.getParent()
            android.view.View r8 = (android.view.View) r8
            p000a.p001a.p005c.p014g.C0127u.m457k(r8)
        L_0x0153:
            android.widget.PopupWindow r8 = r7.f801q
            if (r8 == 0) goto L_0x0165
            android.view.Window r8 = r7.f790f
            android.view.View r8 = r8.getDecorView()
            java.lang.Runnable r0 = r7.f802r
            r8.post(r0)
            goto L_0x0165
        L_0x0163:
            r7.f799o = r1
        L_0x0165:
            a.a.d.d.b r8 = r7.f799o
            if (r8 == 0) goto L_0x0170
            android.support.v7.app.l r0 = r7.f793i
            if (r0 == 0) goto L_0x0170
            r0.mo1022b(r8)
        L_0x0170:
            a.a.d.d.b r8 = r7.f799o
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.app.C0261v.mo1050b(a.a.d.d.b$a):a.a.d.d.b");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo1051b(C0293l lVar) {
        if (!this.f774F) {
            this.f774F = true;
            this.f796l.mo1585g();
            Window.Callback k = mo1065k();
            if (k != null && !this.f778J) {
                k.onPanelClosed(108, lVar);
            }
            this.f774F = false;
        }
    }

    /* renamed from: b */
    public void mo1029b(View view, ViewGroup.LayoutParams layoutParams) {
        m1005s();
        ViewGroup viewGroup = (ViewGroup) this.f806v.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.f791g.onContentChanged();
    }

    /* renamed from: b */
    public boolean mo1030b(int i) {
        int k = m1000k(i);
        if (this.f773E && k == 108) {
            return false;
        }
        if (this.f769A && k == 1) {
            this.f769A = false;
        }
        if (k == 1) {
            m1009w();
            this.f773E = true;
            return true;
        } else if (k == 2) {
            m1009w();
            this.f809y = true;
            return true;
        } else if (k == 5) {
            m1009w();
            this.f810z = true;
            return true;
        } else if (k == 10) {
            m1009w();
            this.f771C = true;
            return true;
        } else if (k == 108) {
            m1009w();
            this.f769A = true;
            return true;
        } else if (k != 109) {
            return this.f790f.requestFeature(k);
        } else {
            m1009w();
            this.f770B = true;
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean mo1052b(int i, KeyEvent keyEvent) {
        C0236a i2 = mo1063i();
        if (i2 != null && i2.mo968a(i, keyEvent)) {
            return true;
        }
        C0267f fVar = this.f776H;
        if (fVar == null || !m992a(fVar, keyEvent.getKeyCode(), keyEvent, 1)) {
            if (this.f776H == null) {
                C0267f a = mo1042a(0, true);
                m995b(a, keyEvent);
                boolean a2 = m992a(a, keyEvent.getKeyCode(), keyEvent, 1);
                a.f833m = false;
                if (a2) {
                    return true;
                }
            }
            return false;
        }
        C0267f fVar2 = this.f776H;
        if (fVar2 != null) {
            fVar2.f834n = true;
        }
        return true;
    }

    /* renamed from: c */
    public void mo1031c() {
        LayoutInflater from = LayoutInflater.from(this.f789e);
        if (from.getFactory() == null) {
            C0114h.m403a(from, this);
        } else if (!(from.getFactory2() instanceof C0261v)) {
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    /* renamed from: c */
    public void mo1032c(int i) {
        m1005s();
        ViewGroup viewGroup = (ViewGroup) this.f806v.findViewById(16908290);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.f789e).inflate(i, viewGroup);
        this.f791g.onContentChanged();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public boolean mo1053c(int i, KeyEvent keyEvent) {
        if (i == 4) {
            boolean z = this.f777I;
            this.f777I = false;
            C0267f a = mo1042a(0, false);
            if (a != null && a.f835o) {
                if (!z) {
                    mo1046a(a, true);
                }
                return true;
            } else if (mo1067m()) {
                return true;
            }
        } else if (i == 82) {
            m998e(0, keyEvent);
            return true;
        }
        return false;
    }

    /* renamed from: d */
    public void mo1033d() {
        C0236a i = mo1063i();
        if (i == null || !i.mo987g()) {
            m999j(0);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo1054d(int i) {
        mo1046a(mo1042a(i, true), true);
    }

    /* renamed from: e */
    public void mo1034e() {
        C0236a i = mo1063i();
        if (i != null) {
            i.mo974d(false);
        }
        C0265d dVar = this.f781M;
        if (dVar != null) {
            dVar.mo1076a();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public void mo1055e(int i) {
        C0267f a;
        C0267f a2 = mo1042a(i, true);
        if (a2.f830j != null) {
            Bundle bundle = new Bundle();
            a2.f830j.mo1293b(bundle);
            if (bundle.size() > 0) {
                a2.f839s = bundle;
            }
            a2.f830j.mo1324s();
            a2.f830j.clear();
        }
        a2.f838r = true;
        a2.f837q = true;
        if ((i == 108 || i == 0) && this.f796l != null && (a = mo1042a(0, false)) != null) {
            a.f833m = false;
            m995b(a, (KeyEvent) null);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public int mo1056f(int i) {
        if (i == -100) {
            return -1;
        }
        if (i != 0) {
            return i;
        }
        if (Build.VERSION.SDK_INT >= 23 && ((UiModeManager) this.f789e.getSystemService(UiModeManager.class)).getNightMode() == 0) {
            return -1;
        }
        m1004r();
        return this.f781M.mo1078c();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public void mo1057f() {
        C0293l lVar;
        C0346K k = this.f796l;
        if (k != null) {
            k.mo1585g();
        }
        if (this.f801q != null) {
            this.f790f.getDecorView().removeCallbacks(this.f802r);
            if (this.f801q.isShowing()) {
                try {
                    this.f801q.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            this.f801q = null;
        }
        mo1058g();
        C0267f a = mo1042a(0, false);
        if (a != null && (lVar = a.f830j) != null) {
            lVar.close();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public void mo1058g() {
        C0134z zVar = this.f803s;
        if (zVar != null) {
            zVar.mo508a();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public void mo1059g(int i) {
        C0236a i2;
        if (i == 108 && (i2 = mo1063i()) != null) {
            i2.mo970b(true);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: h */
    public final Context mo1060h() {
        C0236a i = mo1063i();
        Context f = i != null ? i.mo977f() : null;
        return f == null ? this.f789e : f;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: h */
    public void mo1061h(int i) {
        if (i == 108) {
            C0236a i2 = mo1063i();
            if (i2 != null) {
                i2.mo970b(false);
            }
        } else if (i == 0) {
            C0267f a = mo1042a(i, true);
            if (a.f835o) {
                mo1046a(a, false);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: i */
    public int mo1062i(int i) {
        boolean z;
        boolean z2;
        boolean z3;
        ActionBarContextView actionBarContextView = this.f800p;
        int i2 = 0;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f800p.getLayoutParams();
            z = true;
            if (this.f800p.isShown()) {
                if (this.f786R == null) {
                    this.f786R = new Rect();
                    this.f787S = new Rect();
                }
                Rect rect = this.f786R;
                Rect rect2 = this.f787S;
                rect.set(0, i, 0, 0);
                C0342Ha.m1497a(this.f806v, rect, rect2);
                if (marginLayoutParams.topMargin != (rect2.top == 0 ? i : 0)) {
                    marginLayoutParams.topMargin = i;
                    View view = this.f808x;
                    if (view == null) {
                        this.f808x = new View(this.f789e);
                        this.f808x.setBackgroundColor(this.f789e.getResources().getColor(C0138c.abc_input_method_navigation_guard));
                        this.f806v.addView(this.f808x, -1, new ViewGroup.LayoutParams(-1, i));
                    } else {
                        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                        if (layoutParams.height != i) {
                            layoutParams.height = i;
                            this.f808x.setLayoutParams(layoutParams);
                        }
                    }
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (this.f808x == null) {
                    z = false;
                }
                if (!this.f771C && z) {
                    i = 0;
                }
            } else {
                if (marginLayoutParams.topMargin != 0) {
                    marginLayoutParams.topMargin = 0;
                    z3 = true;
                } else {
                    z3 = false;
                }
                z = false;
            }
            if (z2) {
                this.f800p.setLayoutParams(marginLayoutParams);
            }
        }
        View view2 = this.f808x;
        if (view2 != null) {
            if (!z) {
                i2 = 8;
            }
            view2.setVisibility(i2);
        }
        return i;
    }

    /* renamed from: i */
    public C0236a mo1063i() {
        m1007u();
        return this.f794j;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: j */
    public final CharSequence mo1064j() {
        Window.Callback callback = this.f791g;
        return callback instanceof Activity ? ((Activity) callback).getTitle() : this.f795k;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: k */
    public final Window.Callback mo1065k() {
        return this.f790f.getCallback();
    }

    /* renamed from: l */
    public boolean mo1066l() {
        return this.f804t;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: m */
    public boolean mo1067m() {
        C0164b bVar = this.f799o;
        if (bVar != null) {
            bVar.mo646a();
            return true;
        }
        C0236a i = mo1063i();
        return i != null && i.mo976e();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: n */
    public final C0236a mo1068n() {
        return this.f794j;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r1.f806v;
     */
    /* renamed from: o */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo1069o() {
        /*
            r1 = this;
            boolean r0 = r1.f805u
            if (r0 == 0) goto L_0x0010
            android.view.ViewGroup r0 = r1.f806v
            if (r0 == 0) goto L_0x0010
            boolean r0 = p000a.p001a.p005c.p014g.C0127u.m455i(r0)
            if (r0 == 0) goto L_0x0010
            r0 = 1
            goto L_0x0011
        L_0x0010:
            r0 = 0
        L_0x0011:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.app.C0261v.mo1069o():boolean");
    }

    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return mo1044a(view, str, context, attributeSet);
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView((View) null, str, context, attributeSet);
    }
}

package android.support.p025v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.p024v4.widget.C0216k;
import android.support.p025v7.view.menu.C0316z;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import java.lang.reflect.Method;
import org.cocos2dx.lib.GameControllerDelegate;
import p000a.p001a.p005c.p014g.C0127u;
import p000a.p001a.p017d.p018a.C0145j;

/* renamed from: android.support.v7.widget.U */
public class C0371U implements C0316z {

    /* renamed from: a */
    private static Method f1417a;

    /* renamed from: b */
    private static Method f1418b;

    /* renamed from: c */
    private static Method f1419c;

    /* renamed from: A */
    final C0376e f1420A;

    /* renamed from: B */
    private final C0375d f1421B;

    /* renamed from: C */
    private final C0374c f1422C;

    /* renamed from: D */
    private final C0372a f1423D;

    /* renamed from: E */
    private Runnable f1424E;

    /* renamed from: F */
    final Handler f1425F;

    /* renamed from: G */
    private final Rect f1426G;

    /* renamed from: H */
    private Rect f1427H;

    /* renamed from: I */
    private boolean f1428I;

    /* renamed from: J */
    PopupWindow f1429J;

    /* renamed from: d */
    private Context f1430d;

    /* renamed from: e */
    private ListAdapter f1431e;

    /* renamed from: f */
    C0349N f1432f;

    /* renamed from: g */
    private int f1433g;

    /* renamed from: h */
    private int f1434h;

    /* renamed from: i */
    private int f1435i;

    /* renamed from: j */
    private int f1436j;

    /* renamed from: k */
    private int f1437k;

    /* renamed from: l */
    private boolean f1438l;

    /* renamed from: m */
    private boolean f1439m;

    /* renamed from: n */
    private boolean f1440n;

    /* renamed from: o */
    private boolean f1441o;

    /* renamed from: p */
    private int f1442p;

    /* renamed from: q */
    private boolean f1443q;

    /* renamed from: r */
    private boolean f1444r;

    /* renamed from: s */
    int f1445s;

    /* renamed from: t */
    private View f1446t;

    /* renamed from: u */
    private int f1447u;

    /* renamed from: v */
    private DataSetObserver f1448v;

    /* renamed from: w */
    private View f1449w;

    /* renamed from: x */
    private Drawable f1450x;

    /* renamed from: y */
    private AdapterView.OnItemClickListener f1451y;

    /* renamed from: z */
    private AdapterView.OnItemSelectedListener f1452z;

    /* renamed from: android.support.v7.widget.U$a */
    private class C0372a implements Runnable {
        C0372a() {
        }

        public void run() {
            C0371U.this.mo1995a();
        }
    }

    /* renamed from: android.support.v7.widget.U$b */
    private class C0373b extends DataSetObserver {
        C0373b() {
        }

        public void onChanged() {
            if (C0371U.this.mo1136b()) {
                C0371U.this.mo1137c();
            }
        }

        public void onInvalidated() {
            C0371U.this.dismiss();
        }
    }

    /* renamed from: android.support.v7.widget.U$c */
    private class C0374c implements AbsListView.OnScrollListener {
        C0374c() {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i == 1 && !C0371U.this.mo2017j() && C0371U.this.f1429J.getContentView() != null) {
                C0371U u = C0371U.this;
                u.f1425F.removeCallbacks(u.f1420A);
                C0371U.this.f1420A.run();
            }
        }
    }

    /* renamed from: android.support.v7.widget.U$d */
    private class C0375d implements View.OnTouchListener {
        C0375d() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            PopupWindow popupWindow;
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && (popupWindow = C0371U.this.f1429J) != null && popupWindow.isShowing() && x >= 0 && x < C0371U.this.f1429J.getWidth() && y >= 0 && y < C0371U.this.f1429J.getHeight()) {
                C0371U u = C0371U.this;
                u.f1425F.postDelayed(u.f1420A, 250);
                return false;
            } else if (action != 1) {
                return false;
            } else {
                C0371U u2 = C0371U.this;
                u2.f1425F.removeCallbacks(u2.f1420A);
                return false;
            }
        }
    }

    /* renamed from: android.support.v7.widget.U$e */
    private class C0376e implements Runnable {
        C0376e() {
        }

        public void run() {
            C0349N n = C0371U.this.f1432f;
            if (n != null && C0127u.m454h(n) && C0371U.this.f1432f.getCount() > C0371U.this.f1432f.getChildCount()) {
                int childCount = C0371U.this.f1432f.getChildCount();
                C0371U u = C0371U.this;
                if (childCount <= u.f1445s) {
                    u.f1429J.setInputMethodMode(2);
                    C0371U.this.mo1137c();
                }
            }
        }
    }

    static {
        Class<PopupWindow> cls = PopupWindow.class;
        try {
            f1417a = cls.getDeclaredMethod("setClipToScreenEnabled", new Class[]{Boolean.TYPE});
        } catch (NoSuchMethodException unused) {
            Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
        }
        try {
            f1418b = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", new Class[]{View.class, Integer.TYPE, Boolean.TYPE});
        } catch (NoSuchMethodException unused2) {
            Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
        }
        try {
            f1419c = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", new Class[]{Rect.class});
        } catch (NoSuchMethodException unused3) {
            Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
        }
    }

    public C0371U(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public C0371U(Context context, AttributeSet attributeSet, int i, int i2) {
        this.f1433g = -2;
        this.f1434h = -2;
        this.f1437k = GameControllerDelegate.THUMBSTICK_RIGHT_X;
        this.f1439m = true;
        this.f1442p = 0;
        this.f1443q = false;
        this.f1444r = false;
        this.f1445s = Integer.MAX_VALUE;
        this.f1447u = 0;
        this.f1420A = new C0376e();
        this.f1421B = new C0375d();
        this.f1422C = new C0374c();
        this.f1423D = new C0372a();
        this.f1426G = new Rect();
        this.f1430d = context;
        this.f1425F = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0145j.ListPopupWindow, i, i2);
        this.f1435i = obtainStyledAttributes.getDimensionPixelOffset(C0145j.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        this.f1436j = obtainStyledAttributes.getDimensionPixelOffset(C0145j.ListPopupWindow_android_dropDownVerticalOffset, 0);
        if (this.f1436j != 0) {
            this.f1438l = true;
        }
        obtainStyledAttributes.recycle();
        this.f1429J = new C0442v(context, attributeSet, i, i2);
        this.f1429J.setInputMethodMode(1);
    }

    /* renamed from: a */
    private int m1693a(View view, int i, boolean z) {
        Method method = f1418b;
        if (method != null) {
            try {
                return ((Integer) method.invoke(this.f1429J, new Object[]{view, Integer.valueOf(i), Boolean.valueOf(z)})).intValue();
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
            }
        }
        return this.f1429J.getMaxAvailableHeight(view, i);
    }

    /* renamed from: c */
    private void mo2042c(boolean z) {
        Method method = f1417a;
        if (method != null) {
            try {
                method.invoke(this.f1429J, new Object[]{Boolean.valueOf(z)});
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v24, resolved type: android.support.v7.widget.N} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v25, resolved type: android.support.v7.widget.N} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: android.widget.LinearLayout} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v30, resolved type: android.support.v7.widget.N} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0154  */
    /* renamed from: l */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int mo1685l() {
        /*
            r12 = this;
            android.support.v7.widget.N r0 = r12.f1432f
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = -1
            r3 = 1
            r4 = 0
            if (r0 != 0) goto L_0x00c1
            android.content.Context r0 = r12.f1430d
            android.support.v7.widget.S r5 = new android.support.v7.widget.S
            r5.<init>(r12)
            r12.f1424E = r5
            boolean r5 = r12.f1428I
            r5 = r5 ^ r3
            android.support.v7.widget.N r5 = r12.mo1994a(r0, r5)
            r12.f1432f = r5
            android.graphics.drawable.Drawable r5 = r12.f1450x
            if (r5 == 0) goto L_0x0024
            android.support.v7.widget.N r6 = r12.f1432f
            r6.setSelector(r5)
        L_0x0024:
            android.support.v7.widget.N r5 = r12.f1432f
            android.widget.ListAdapter r6 = r12.f1431e
            r5.setAdapter(r6)
            android.support.v7.widget.N r5 = r12.f1432f
            android.widget.AdapterView$OnItemClickListener r6 = r12.f1451y
            r5.setOnItemClickListener(r6)
            android.support.v7.widget.N r5 = r12.f1432f
            r5.setFocusable(r3)
            android.support.v7.widget.N r5 = r12.f1432f
            r5.setFocusableInTouchMode(r3)
            android.support.v7.widget.N r5 = r12.f1432f
            android.support.v7.widget.T r6 = new android.support.v7.widget.T
            r6.<init>(r12)
            r5.setOnItemSelectedListener(r6)
            android.support.v7.widget.N r5 = r12.f1432f
            android.support.v7.widget.U$c r6 = r12.f1422C
            r5.setOnScrollListener(r6)
            android.widget.AdapterView$OnItemSelectedListener r5 = r12.f1452z
            if (r5 == 0) goto L_0x0056
            android.support.v7.widget.N r6 = r12.f1432f
            r6.setOnItemSelectedListener(r5)
        L_0x0056:
            android.support.v7.widget.N r5 = r12.f1432f
            android.view.View r6 = r12.f1446t
            if (r6 == 0) goto L_0x00ba
            android.widget.LinearLayout r7 = new android.widget.LinearLayout
            r7.<init>(r0)
            r7.setOrientation(r3)
            android.widget.LinearLayout$LayoutParams r0 = new android.widget.LinearLayout$LayoutParams
            r8 = 1065353216(0x3f800000, float:1.0)
            r0.<init>(r2, r4, r8)
            int r8 = r12.f1447u
            if (r8 == 0) goto L_0x0091
            if (r8 == r3) goto L_0x008a
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r5 = "Invalid hint position "
            r0.append(r5)
            int r5 = r12.f1447u
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            java.lang.String r5 = "ListPopupWindow"
            android.util.Log.e(r5, r0)
            goto L_0x0097
        L_0x008a:
            r7.addView(r5, r0)
            r7.addView(r6)
            goto L_0x0097
        L_0x0091:
            r7.addView(r6)
            r7.addView(r5, r0)
        L_0x0097:
            int r0 = r12.f1434h
            if (r0 < 0) goto L_0x009e
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x00a0
        L_0x009e:
            r0 = 0
            r5 = 0
        L_0x00a0:
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r5)
            r6.measure(r0, r4)
            android.view.ViewGroup$LayoutParams r0 = r6.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r0 = (android.widget.LinearLayout.LayoutParams) r0
            int r5 = r6.getMeasuredHeight()
            int r6 = r0.topMargin
            int r5 = r5 + r6
            int r0 = r0.bottomMargin
            int r5 = r5 + r0
            r0 = r5
            r5 = r7
            goto L_0x00bb
        L_0x00ba:
            r0 = 0
        L_0x00bb:
            android.widget.PopupWindow r6 = r12.f1429J
            r6.setContentView(r5)
            goto L_0x00df
        L_0x00c1:
            android.widget.PopupWindow r0 = r12.f1429J
            android.view.View r0 = r0.getContentView()
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            android.view.View r0 = r12.f1446t
            if (r0 == 0) goto L_0x00de
            android.view.ViewGroup$LayoutParams r5 = r0.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r5 = (android.widget.LinearLayout.LayoutParams) r5
            int r0 = r0.getMeasuredHeight()
            int r6 = r5.topMargin
            int r0 = r0 + r6
            int r5 = r5.bottomMargin
            int r0 = r0 + r5
            goto L_0x00df
        L_0x00de:
            r0 = 0
        L_0x00df:
            android.widget.PopupWindow r5 = r12.f1429J
            android.graphics.drawable.Drawable r5 = r5.getBackground()
            if (r5 == 0) goto L_0x00fb
            android.graphics.Rect r6 = r12.f1426G
            r5.getPadding(r6)
            android.graphics.Rect r5 = r12.f1426G
            int r6 = r5.top
            int r5 = r5.bottom
            int r5 = r5 + r6
            boolean r7 = r12.f1438l
            if (r7 != 0) goto L_0x0101
            int r6 = -r6
            r12.f1436j = r6
            goto L_0x0101
        L_0x00fb:
            android.graphics.Rect r5 = r12.f1426G
            r5.setEmpty()
            r5 = 0
        L_0x0101:
            android.widget.PopupWindow r6 = r12.f1429J
            int r6 = r6.getInputMethodMode()
            r7 = 2
            if (r6 != r7) goto L_0x010b
            goto L_0x010c
        L_0x010b:
            r3 = 0
        L_0x010c:
            android.view.View r4 = r12.mo2007e()
            int r6 = r12.f1436j
            int r3 = r12.m1693a(r4, r6, r3)
            boolean r4 = r12.f1443q
            if (r4 != 0) goto L_0x0165
            int r4 = r12.f1433g
            if (r4 != r2) goto L_0x011f
            goto L_0x0165
        L_0x011f:
            int r4 = r12.f1434h
            r6 = -2
            if (r4 == r6) goto L_0x012e
            r1 = 1073741824(0x40000000, float:2.0)
            if (r4 == r2) goto L_0x012e
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r4, r1)
        L_0x012c:
            r7 = r1
            goto L_0x0147
        L_0x012e:
            android.content.Context r2 = r12.f1430d
            android.content.res.Resources r2 = r2.getResources()
            android.util.DisplayMetrics r2 = r2.getDisplayMetrics()
            int r2 = r2.widthPixels
            android.graphics.Rect r4 = r12.f1426G
            int r6 = r4.left
            int r4 = r4.right
            int r6 = r6 + r4
            int r2 = r2 - r6
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r1)
            goto L_0x012c
        L_0x0147:
            android.support.v7.widget.N r6 = r12.f1432f
            r8 = 0
            r9 = -1
            int r10 = r3 - r0
            r11 = -1
            int r1 = r6.mo1777a(r7, r8, r9, r10, r11)
            if (r1 <= 0) goto L_0x0163
            android.support.v7.widget.N r2 = r12.f1432f
            int r2 = r2.getPaddingTop()
            android.support.v7.widget.N r3 = r12.f1432f
            int r3 = r3.getPaddingBottom()
            int r2 = r2 + r3
            int r5 = r5 + r2
            int r0 = r0 + r5
        L_0x0163:
            int r1 = r1 + r0
            return r1
        L_0x0165:
            int r3 = r3 + r5
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.widget.C0371U.mo1685l():int");
    }

    /* renamed from: m */
    private void mo1686m() {
        View view = this.f1446t;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.f1446t);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C0349N mo1994a(Context context, boolean z) {
        return new C0349N(context, z);
    }

    /* renamed from: a */
    public void mo1995a() {
        C0349N n = this.f1432f;
        if (n != null) {
            n.setListSelectionHidden(true);
            n.requestLayout();
        }
    }

    /* renamed from: a */
    public void mo1996a(int i) {
        this.f1429J.setAnimationStyle(i);
    }

    /* renamed from: a */
    public void mo1997a(Rect rect) {
        this.f1427H = rect;
    }

    /* renamed from: a */
    public void mo1998a(Drawable drawable) {
        this.f1429J.setBackgroundDrawable(drawable);
    }

    /* renamed from: a */
    public void mo1999a(View view) {
        this.f1449w = view;
    }

    /* renamed from: a */
    public void mo2000a(AdapterView.OnItemClickListener onItemClickListener) {
        this.f1451y = onItemClickListener;
    }

    /* renamed from: a */
    public void mo1682a(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.f1448v;
        if (dataSetObserver == null) {
            this.f1448v = new C0373b();
        } else {
            ListAdapter listAdapter2 = this.f1431e;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f1431e = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.f1448v);
        }
        C0349N n = this.f1432f;
        if (n != null) {
            n.setAdapter(this.f1431e);
        }
    }

    /* renamed from: a */
    public void mo2001a(PopupWindow.OnDismissListener onDismissListener) {
        this.f1429J.setOnDismissListener(onDismissListener);
    }

    /* renamed from: a */
    public void mo2002a(boolean z) {
        this.f1428I = z;
        this.f1429J.setFocusable(z);
    }

    /* renamed from: b */
    public void mo2003b(int i) {
        Drawable background = this.f1429J.getBackground();
        if (background != null) {
            background.getPadding(this.f1426G);
            Rect rect = this.f1426G;
            this.f1434h = rect.left + rect.right + i;
            return;
        }
        mo2016i(i);
    }

    /* renamed from: b */
    public void mo2004b(boolean z) {
        this.f1441o = true;
        this.f1440n = z;
    }

    /* renamed from: b */
    public boolean mo1136b() {
        return this.f1429J.isShowing();
    }

    /* renamed from: c */
    public void mo1137c() {
        int l = mo1685l();
        boolean j = mo2017j();
        C0216k.m800a(this.f1429J, this.f1437k);
        boolean z = true;
        if (!this.f1429J.isShowing()) {
            int i = this.f1434h;
            if (i == -1) {
                i = -1;
            } else if (i == -2) {
                i = mo2007e().getWidth();
            }
            int i2 = this.f1433g;
            if (i2 == -1) {
                l = -1;
            } else if (i2 != -2) {
                l = i2;
            }
            this.f1429J.setWidth(i);
            this.f1429J.setHeight(l);
            mo2042c(true);
            this.f1429J.setOutsideTouchable(!this.f1444r && !this.f1443q);
            this.f1429J.setTouchInterceptor(this.f1421B);
            if (this.f1441o) {
                C0216k.m802a(this.f1429J, this.f1440n);
            }
            Method method = f1419c;
            if (method != null) {
                try {
                    method.invoke(this.f1429J, new Object[]{this.f1427H});
                } catch (Exception e) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e);
                }
            }
            C0216k.m801a(this.f1429J, mo2007e(), this.f1435i, this.f1436j, this.f1442p);
            this.f1432f.setSelection(-1);
            if (!this.f1428I || this.f1432f.isInTouchMode()) {
                mo1995a();
            }
            if (!this.f1428I) {
                this.f1425F.post(this.f1423D);
            }
        } else if (C0127u.m454h(mo2007e())) {
            int i3 = this.f1434h;
            if (i3 == -1) {
                i3 = -1;
            } else if (i3 == -2) {
                i3 = mo2007e().getWidth();
            }
            int i4 = this.f1433g;
            if (i4 == -1) {
                if (!j) {
                    l = -1;
                }
                if (j) {
                    this.f1429J.setWidth(this.f1434h == -1 ? -1 : 0);
                    this.f1429J.setHeight(0);
                } else {
                    this.f1429J.setWidth(this.f1434h == -1 ? -1 : 0);
                    this.f1429J.setHeight(-1);
                }
            } else if (i4 != -2) {
                l = i4;
            }
            PopupWindow popupWindow = this.f1429J;
            if (this.f1444r || this.f1443q) {
                z = false;
            }
            popupWindow.setOutsideTouchable(z);
            this.f1429J.update(mo2007e(), this.f1435i, this.f1436j, i3 < 0 ? -1 : i3, l < 0 ? -1 : l);
        }
    }

    /* renamed from: c */
    public void mo2005c(int i) {
        this.f1442p = i;
    }

    /* renamed from: d */
    public ListView mo1140d() {
        return this.f1432f;
    }

    /* renamed from: d */
    public void mo2006d(int i) {
        this.f1435i = i;
    }

    public void dismiss() {
        this.f1429J.dismiss();
        mo1686m();
        this.f1429J.setContentView((View) null);
        this.f1432f = null;
        this.f1425F.removeCallbacks(this.f1420A);
    }

    /* renamed from: e */
    public View mo2007e() {
        return this.f1449w;
    }

    /* renamed from: e */
    public void mo2008e(int i) {
        this.f1429J.setInputMethodMode(i);
    }

    /* renamed from: f */
    public Drawable mo2009f() {
        return this.f1429J.getBackground();
    }

    /* renamed from: f */
    public void mo2010f(int i) {
        this.f1447u = i;
    }

    /* renamed from: g */
    public int mo2011g() {
        return this.f1435i;
    }

    /* renamed from: g */
    public void mo2012g(int i) {
        C0349N n = this.f1432f;
        if (mo1136b() && n != null) {
            n.setListSelectionHidden(false);
            n.setSelection(i);
            if (n.getChoiceMode() != 0) {
                n.setItemChecked(i, true);
            }
        }
    }

    /* renamed from: h */
    public int mo2013h() {
        if (!this.f1438l) {
            return 0;
        }
        return this.f1436j;
    }

    /* renamed from: h */
    public void mo2014h(int i) {
        this.f1436j = i;
        this.f1438l = true;
    }

    /* renamed from: i */
    public int mo2015i() {
        return this.f1434h;
    }

    /* renamed from: i */
    public void mo2016i(int i) {
        this.f1434h = i;
    }

    /* renamed from: j */
    public boolean mo2017j() {
        return this.f1429J.getInputMethodMode() == 2;
    }

    /* renamed from: k */
    public boolean mo2018k() {
        return this.f1428I;
    }
}

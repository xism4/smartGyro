package android.support.p025v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.p024v4.widget.NestedScrollView;
import android.support.p025v7.widget.C0357Q;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import p000a.p001a.p005c.p014g.C0127u;
import p000a.p001a.p017d.p018a.C0136a;
import p000a.p001a.p017d.p018a.C0141f;
import p000a.p001a.p017d.p018a.C0145j;

/* renamed from: android.support.v7.app.AlertController */
class AlertController {

    /* renamed from: A */
    NestedScrollView f574A;

    /* renamed from: B */
    private int f575B = 0;

    /* renamed from: C */
    private Drawable f576C;

    /* renamed from: D */
    private ImageView f577D;

    /* renamed from: E */
    private TextView f578E;

    /* renamed from: F */
    private TextView f579F;

    /* renamed from: G */
    private View f580G;

    /* renamed from: H */
    ListAdapter f581H;

    /* renamed from: I */
    int f582I = -1;

    /* renamed from: J */
    private int f583J;

    /* renamed from: K */
    private int f584K;

    /* renamed from: L */
    int f585L;

    /* renamed from: M */
    int f586M;

    /* renamed from: N */
    int f587N;

    /* renamed from: O */
    int f588O;

    /* renamed from: P */
    private boolean f589P;

    /* renamed from: Q */
    private int f590Q = 0;

    /* renamed from: R */
    Handler f591R;

    /* renamed from: S */
    private final View.OnClickListener f592S = new C0240b(this);

    /* renamed from: a */
    private final Context f593a;

    /* renamed from: b */
    final C0272z f594b;

    /* renamed from: c */
    private final Window f595c;

    /* renamed from: d */
    private final int f596d;

    /* renamed from: e */
    private CharSequence f597e;

    /* renamed from: f */
    private CharSequence f598f;

    /* renamed from: g */
    ListView f599g;

    /* renamed from: h */
    private View f600h;

    /* renamed from: i */
    private int f601i;

    /* renamed from: j */
    private int f602j;

    /* renamed from: k */
    private int f603k;

    /* renamed from: l */
    private int f604l;

    /* renamed from: m */
    private int f605m;

    /* renamed from: n */
    private boolean f606n = false;

    /* renamed from: o */
    Button f607o;

    /* renamed from: p */
    private CharSequence f608p;

    /* renamed from: q */
    Message f609q;

    /* renamed from: r */
    private Drawable f610r;

    /* renamed from: s */
    Button f611s;

    /* renamed from: t */
    private CharSequence f612t;

    /* renamed from: u */
    Message f613u;

    /* renamed from: v */
    private Drawable f614v;

    /* renamed from: w */
    Button f615w;

    /* renamed from: x */
    private CharSequence f616x;

    /* renamed from: y */
    Message f617y;

    /* renamed from: z */
    private Drawable f618z;

    /* renamed from: android.support.v7.app.AlertController$RecycleListView */
    public static class RecycleListView extends ListView {

        /* renamed from: a */
        private final int f619a;

        /* renamed from: b */
        private final int f620b;

        public RecycleListView(Context context) {
            this(context, (AttributeSet) null);
        }

        public RecycleListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0145j.RecycleListView);
            this.f620b = obtainStyledAttributes.getDimensionPixelOffset(C0145j.RecycleListView_paddingBottomNoButtons, -1);
            this.f619a = obtainStyledAttributes.getDimensionPixelOffset(C0145j.RecycleListView_paddingTopNoTitle, -1);
        }

        /* renamed from: a */
        public void mo938a(boolean z, boolean z2) {
            if (!z2 || !z) {
                setPadding(getPaddingLeft(), z ? getPaddingTop() : this.f619a, getPaddingRight(), z2 ? getPaddingBottom() : this.f620b);
            }
        }
    }

    /* renamed from: android.support.v7.app.AlertController$a */
    public static class C0223a {

        /* renamed from: A */
        public int f621A;

        /* renamed from: B */
        public int f622B;

        /* renamed from: C */
        public int f623C;

        /* renamed from: D */
        public int f624D;

        /* renamed from: E */
        public boolean f625E = false;

        /* renamed from: F */
        public boolean[] f626F;

        /* renamed from: G */
        public boolean f627G;

        /* renamed from: H */
        public boolean f628H;

        /* renamed from: I */
        public int f629I = -1;

        /* renamed from: J */
        public DialogInterface.OnMultiChoiceClickListener f630J;

        /* renamed from: K */
        public Cursor f631K;

        /* renamed from: L */
        public String f632L;

        /* renamed from: M */
        public String f633M;

        /* renamed from: N */
        public AdapterView.OnItemSelectedListener f634N;

        /* renamed from: O */
        public C0224a f635O;

        /* renamed from: P */
        public boolean f636P = true;

        /* renamed from: a */
        public final Context f637a;

        /* renamed from: b */
        public final LayoutInflater f638b;

        /* renamed from: c */
        public int f639c = 0;

        /* renamed from: d */
        public Drawable f640d;

        /* renamed from: e */
        public int f641e = 0;

        /* renamed from: f */
        public CharSequence f642f;

        /* renamed from: g */
        public View f643g;

        /* renamed from: h */
        public CharSequence f644h;

        /* renamed from: i */
        public CharSequence f645i;

        /* renamed from: j */
        public Drawable f646j;

        /* renamed from: k */
        public DialogInterface.OnClickListener f647k;

        /* renamed from: l */
        public CharSequence f648l;

        /* renamed from: m */
        public Drawable f649m;

        /* renamed from: n */
        public DialogInterface.OnClickListener f650n;

        /* renamed from: o */
        public CharSequence f651o;

        /* renamed from: p */
        public Drawable f652p;

        /* renamed from: q */
        public DialogInterface.OnClickListener f653q;

        /* renamed from: r */
        public boolean f654r;

        /* renamed from: s */
        public DialogInterface.OnCancelListener f655s;

        /* renamed from: t */
        public DialogInterface.OnDismissListener f656t;

        /* renamed from: u */
        public DialogInterface.OnKeyListener f657u;

        /* renamed from: v */
        public CharSequence[] f658v;

        /* renamed from: w */
        public ListAdapter f659w;

        /* renamed from: x */
        public DialogInterface.OnClickListener f660x;

        /* renamed from: y */
        public int f661y;

        /* renamed from: z */
        public View f662z;

        /* renamed from: android.support.v7.app.AlertController$a$a */
        public interface C0224a {
            /* renamed from: a */
            void mo940a(ListView listView);
        }

        public C0223a(Context context) {
            this.f637a = context;
            this.f654r = true;
            this.f638b = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        /* JADX WARNING: type inference failed for: r9v0, types: [android.widget.ListAdapter] */
        /* JADX WARNING: type inference failed for: r9v3 */
        /* JADX WARNING: type inference failed for: r9v4 */
        /* JADX WARNING: type inference failed for: r2v6, types: [android.widget.SimpleCursorAdapter] */
        /* JADX WARNING: type inference failed for: r1v20, types: [android.support.v7.app.h] */
        /* JADX WARNING: type inference failed for: r1v21, types: [android.support.v7.app.g] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x008f  */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x0096  */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x009a  */
        /* renamed from: b */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void m853b(android.support.p025v7.app.AlertController r12) {
            /*
                r11 = this;
                android.view.LayoutInflater r0 = r11.f638b
                int r1 = r12.f585L
                r2 = 0
                android.view.View r0 = r0.inflate(r1, r2)
                android.support.v7.app.AlertController$RecycleListView r0 = (android.support.p025v7.app.AlertController.RecycleListView) r0
                boolean r1 = r11.f627G
                r8 = 1
                if (r1 == 0) goto L_0x0033
                android.database.Cursor r4 = r11.f631K
                if (r4 != 0) goto L_0x0026
                android.support.v7.app.g r9 = new android.support.v7.app.g
                android.content.Context r3 = r11.f637a
                int r4 = r12.f586M
                r5 = 16908308(0x1020014, float:2.3877285E-38)
                java.lang.CharSequence[] r6 = r11.f658v
                r1 = r9
                r2 = r11
                r7 = r0
                r1.<init>(r2, r3, r4, r5, r6, r7)
                goto L_0x0067
            L_0x0026:
                android.support.v7.app.h r9 = new android.support.v7.app.h
                android.content.Context r3 = r11.f637a
                r5 = 0
                r1 = r9
                r2 = r11
                r6 = r0
                r7 = r12
                r1.<init>(r2, r3, r4, r5, r6, r7)
                goto L_0x0067
            L_0x0033:
                boolean r1 = r11.f628H
                if (r1 == 0) goto L_0x003a
                int r1 = r12.f587N
                goto L_0x003c
            L_0x003a:
                int r1 = r12.f588O
            L_0x003c:
                r4 = r1
                android.database.Cursor r5 = r11.f631K
                r1 = 16908308(0x1020014, float:2.3877285E-38)
                if (r5 == 0) goto L_0x0059
                android.widget.SimpleCursorAdapter r9 = new android.widget.SimpleCursorAdapter
                android.content.Context r3 = r11.f637a
                java.lang.String[] r6 = new java.lang.String[r8]
                java.lang.String r2 = r11.f632L
                r7 = 0
                r6[r7] = r2
                int[] r10 = new int[r8]
                r10[r7] = r1
                r2 = r9
                r7 = r10
                r2.<init>(r3, r4, r5, r6, r7)
                goto L_0x0067
            L_0x0059:
                android.widget.ListAdapter r9 = r11.f659w
                if (r9 == 0) goto L_0x005e
                goto L_0x0067
            L_0x005e:
                android.support.v7.app.AlertController$c r9 = new android.support.v7.app.AlertController$c
                android.content.Context r2 = r11.f637a
                java.lang.CharSequence[] r3 = r11.f658v
                r9.<init>(r2, r4, r1, r3)
            L_0x0067:
                android.support.v7.app.AlertController$a$a r1 = r11.f635O
                if (r1 == 0) goto L_0x006e
                r1.mo940a(r0)
            L_0x006e:
                r12.f581H = r9
                int r1 = r11.f629I
                r12.f582I = r1
                android.content.DialogInterface$OnClickListener r1 = r11.f660x
                if (r1 == 0) goto L_0x0081
                android.support.v7.app.i r1 = new android.support.v7.app.i
                r1.<init>(r11, r12)
            L_0x007d:
                r0.setOnItemClickListener(r1)
                goto L_0x008b
            L_0x0081:
                android.content.DialogInterface$OnMultiChoiceClickListener r1 = r11.f630J
                if (r1 == 0) goto L_0x008b
                android.support.v7.app.j r1 = new android.support.v7.app.j
                r1.<init>(r11, r0, r12)
                goto L_0x007d
            L_0x008b:
                android.widget.AdapterView$OnItemSelectedListener r1 = r11.f634N
                if (r1 == 0) goto L_0x0092
                r0.setOnItemSelectedListener(r1)
            L_0x0092:
                boolean r1 = r11.f628H
                if (r1 == 0) goto L_0x009a
                r0.setChoiceMode(r8)
                goto L_0x00a2
            L_0x009a:
                boolean r1 = r11.f627G
                if (r1 == 0) goto L_0x00a2
                r1 = 2
                r0.setChoiceMode(r1)
            L_0x00a2:
                r12.f599g = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p025v7.app.AlertController.C0223a.m853b(android.support.v7.app.AlertController):void");
        }

        /* renamed from: a */
        public void mo939a(AlertController alertController) {
            View view = this.f643g;
            if (view != null) {
                alertController.mo933b(view);
            } else {
                CharSequence charSequence = this.f642f;
                if (charSequence != null) {
                    alertController.mo934b(charSequence);
                }
                Drawable drawable = this.f640d;
                if (drawable != null) {
                    alertController.mo928a(drawable);
                }
                int i = this.f639c;
                if (i != 0) {
                    alertController.mo932b(i);
                }
                int i2 = this.f641e;
                if (i2 != 0) {
                    alertController.mo932b(alertController.mo925a(i2));
                }
            }
            CharSequence charSequence2 = this.f644h;
            if (charSequence2 != null) {
                alertController.mo930a(charSequence2);
            }
            if (!(this.f645i == null && this.f646j == null)) {
                alertController.mo927a(-1, this.f645i, this.f647k, (Message) null, this.f646j);
            }
            if (!(this.f648l == null && this.f649m == null)) {
                alertController.mo927a(-2, this.f648l, this.f650n, (Message) null, this.f649m);
            }
            if (!(this.f651o == null && this.f652p == null)) {
                alertController.mo927a(-3, this.f651o, this.f653q, (Message) null, this.f652p);
            }
            if (!(this.f658v == null && this.f631K == null && this.f659w == null)) {
                m853b(alertController);
            }
            View view2 = this.f662z;
            if (view2 == null) {
                int i3 = this.f661y;
                if (i3 != 0) {
                    alertController.mo936c(i3);
                }
            } else if (this.f625E) {
                alertController.mo929a(view2, this.f621A, this.f622B, this.f623C, this.f624D);
            } else {
                alertController.mo937c(view2);
            }
        }
    }

    /* renamed from: android.support.v7.app.AlertController$b */
    private static final class C0225b extends Handler {

        /* renamed from: a */
        private WeakReference<DialogInterface> f663a;

        public C0225b(DialogInterface dialogInterface) {
            this.f663a = new WeakReference<>(dialogInterface);
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == -3 || i == -2 || i == -1) {
                ((DialogInterface.OnClickListener) message.obj).onClick((DialogInterface) this.f663a.get(), message.what);
            } else if (i == 1) {
                ((DialogInterface) message.obj).dismiss();
            }
        }
    }

    /* renamed from: android.support.v7.app.AlertController$c */
    private static class C0226c extends ArrayAdapter<CharSequence> {
        public C0226c(Context context, int i, int i2, CharSequence[] charSequenceArr) {
            super(context, i, i2, charSequenceArr);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public boolean hasStableIds() {
            return true;
        }
    }

    public AlertController(Context context, C0272z zVar, Window window) {
        this.f593a = context;
        this.f594b = zVar;
        this.f595c = window;
        this.f591R = new C0225b(zVar);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes((AttributeSet) null, C0145j.AlertDialog, C0136a.alertDialogStyle, 0);
        this.f583J = obtainStyledAttributes.getResourceId(C0145j.AlertDialog_android_layout, 0);
        this.f584K = obtainStyledAttributes.getResourceId(C0145j.AlertDialog_buttonPanelSideLayout, 0);
        this.f585L = obtainStyledAttributes.getResourceId(C0145j.AlertDialog_listLayout, 0);
        this.f586M = obtainStyledAttributes.getResourceId(C0145j.AlertDialog_multiChoiceItemLayout, 0);
        this.f587N = obtainStyledAttributes.getResourceId(C0145j.AlertDialog_singleChoiceItemLayout, 0);
        this.f588O = obtainStyledAttributes.getResourceId(C0145j.AlertDialog_listItemLayout, 0);
        this.f589P = obtainStyledAttributes.getBoolean(C0145j.AlertDialog_showTitle, true);
        this.f596d = obtainStyledAttributes.getDimensionPixelSize(C0145j.AlertDialog_buttonIconDimen, 0);
        obtainStyledAttributes.recycle();
        zVar.mo1089a(1);
    }

    /* renamed from: a */
    private ViewGroup m827a(View view, View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }

    /* renamed from: a */
    static void m828a(View view, View view2, View view3) {
        int i = 0;
        if (view2 != null) {
            view2.setVisibility(view.canScrollVertically(-1) ? 0 : 4);
        }
        if (view3 != null) {
            if (!view.canScrollVertically(1)) {
                i = 4;
            }
            view3.setVisibility(i);
        }
    }

    /* renamed from: a */
    private void m829a(ViewGroup viewGroup) {
        boolean z;
        Button button;
        this.f607o = (Button) viewGroup.findViewById(16908313);
        this.f607o.setOnClickListener(this.f592S);
        boolean z2 = true;
        if (!TextUtils.isEmpty(this.f608p) || this.f610r != null) {
            this.f607o.setText(this.f608p);
            Drawable drawable = this.f610r;
            if (drawable != null) {
                int i = this.f596d;
                drawable.setBounds(0, 0, i, i);
                this.f607o.setCompoundDrawables(this.f610r, (Drawable) null, (Drawable) null, (Drawable) null);
            }
            this.f607o.setVisibility(0);
            z = true;
        } else {
            this.f607o.setVisibility(8);
            z = false;
        }
        this.f611s = (Button) viewGroup.findViewById(16908314);
        this.f611s.setOnClickListener(this.f592S);
        if (!TextUtils.isEmpty(this.f612t) || this.f614v != null) {
            this.f611s.setText(this.f612t);
            Drawable drawable2 = this.f614v;
            if (drawable2 != null) {
                int i2 = this.f596d;
                drawable2.setBounds(0, 0, i2, i2);
                this.f611s.setCompoundDrawables(this.f614v, (Drawable) null, (Drawable) null, (Drawable) null);
            }
            this.f611s.setVisibility(0);
            z |= true;
        } else {
            this.f611s.setVisibility(8);
        }
        this.f615w = (Button) viewGroup.findViewById(16908315);
        this.f615w.setOnClickListener(this.f592S);
        if (!TextUtils.isEmpty(this.f616x) || this.f618z != null) {
            this.f615w.setText(this.f616x);
            Drawable drawable3 = this.f610r;
            if (drawable3 != null) {
                int i3 = this.f596d;
                drawable3.setBounds(0, 0, i3, i3);
                this.f607o.setCompoundDrawables(this.f610r, (Drawable) null, (Drawable) null, (Drawable) null);
            }
            this.f615w.setVisibility(0);
            z |= true;
        } else {
            this.f615w.setVisibility(8);
        }
        if (m832a(this.f593a)) {
            if (z) {
                button = this.f607o;
            } else if (z) {
                button = this.f611s;
            } else if (z) {
                button = this.f615w;
            }
            m831a(button);
        }
        if (!z) {
            z2 = false;
        }
        if (!z2) {
            viewGroup.setVisibility(8);
        }
    }

    /* renamed from: a */
    private void m830a(ViewGroup viewGroup, View view, int i, int i2) {
        View findViewById = this.f595c.findViewById(C0141f.scrollIndicatorUp);
        View findViewById2 = this.f595c.findViewById(C0141f.scrollIndicatorDown);
        if (Build.VERSION.SDK_INT >= 23) {
            C0127u.m438a(view, i, i2);
            if (findViewById != null) {
                viewGroup.removeView(findViewById);
            }
            if (findViewById2 != null) {
                viewGroup.removeView(findViewById2);
                return;
            }
            return;
        }
        View view2 = null;
        if (findViewById != null && (i & 1) == 0) {
            viewGroup.removeView(findViewById);
            findViewById = null;
        }
        if (findViewById2 == null || (i & 2) != 0) {
            view2 = findViewById2;
        } else {
            viewGroup.removeView(findViewById2);
        }
        if (findViewById != null || view2 != null) {
            if (this.f598f != null) {
                this.f574A.setOnScrollChangeListener(new C0241c(this, findViewById, view2));
                this.f574A.post(new C0242d(this, findViewById, view2));
                return;
            }
            ListView listView = this.f599g;
            if (listView != null) {
                listView.setOnScrollListener(new C0243e(this, findViewById, view2));
                this.f599g.post(new C0244f(this, findViewById, view2));
                return;
            }
            if (findViewById != null) {
                viewGroup.removeView(findViewById);
            }
            if (view2 != null) {
                viewGroup.removeView(view2);
            }
        }
    }

    /* renamed from: a */
    private void m831a(Button button) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
        layoutParams.gravity = 1;
        layoutParams.weight = 0.5f;
        button.setLayoutParams(layoutParams);
    }

    /* renamed from: a */
    private static boolean m832a(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(C0136a.alertDialogCenterButtons, typedValue, true);
        return typedValue.data != 0;
    }

    /* renamed from: a */
    static boolean m833a(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (childCount > 0) {
            childCount--;
            if (m833a(viewGroup.getChildAt(childCount))) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    private int m834b() {
        int i = this.f584K;
        return i == 0 ? this.f583J : this.f590Q == 1 ? i : this.f583J;
    }

    /* renamed from: b */
    private void m835b(ViewGroup viewGroup) {
        this.f574A = (NestedScrollView) this.f595c.findViewById(C0141f.scrollView);
        this.f574A.setFocusable(false);
        this.f574A.setNestedScrollingEnabled(false);
        this.f579F = (TextView) viewGroup.findViewById(16908299);
        TextView textView = this.f579F;
        if (textView != null) {
            CharSequence charSequence = this.f598f;
            if (charSequence != null) {
                textView.setText(charSequence);
                return;
            }
            textView.setVisibility(8);
            this.f574A.removeView(this.f579F);
            if (this.f599g != null) {
                ViewGroup viewGroup2 = (ViewGroup) this.f574A.getParent();
                int indexOfChild = viewGroup2.indexOfChild(this.f574A);
                viewGroup2.removeViewAt(indexOfChild);
                viewGroup2.addView(this.f599g, indexOfChild, new ViewGroup.LayoutParams(-1, -1));
                return;
            }
            viewGroup.setVisibility(8);
        }
    }

    /* renamed from: c */
    private void m836c() {
        ListAdapter listAdapter;
        View findViewById;
        View findViewById2;
        View findViewById3 = this.f595c.findViewById(C0141f.parentPanel);
        View findViewById4 = findViewById3.findViewById(C0141f.topPanel);
        View findViewById5 = findViewById3.findViewById(C0141f.contentPanel);
        View findViewById6 = findViewById3.findViewById(C0141f.buttonPanel);
        ViewGroup viewGroup = (ViewGroup) findViewById3.findViewById(C0141f.customPanel);
        m837c(viewGroup);
        View findViewById7 = viewGroup.findViewById(C0141f.topPanel);
        View findViewById8 = viewGroup.findViewById(C0141f.contentPanel);
        View findViewById9 = viewGroup.findViewById(C0141f.buttonPanel);
        ViewGroup a = m827a(findViewById7, findViewById4);
        ViewGroup a2 = m827a(findViewById8, findViewById5);
        ViewGroup a3 = m827a(findViewById9, findViewById6);
        m835b(a2);
        m829a(a3);
        m838d(a);
        char c = 0;
        boolean z = (viewGroup == null || viewGroup.getVisibility() == 8) ? false : true;
        boolean z2 = (a == null || a.getVisibility() == 8) ? false : true;
        boolean z3 = (a3 == null || a3.getVisibility() == 8) ? false : true;
        if (!(z3 || a2 == null || (findViewById2 = a2.findViewById(C0141f.textSpacerNoButtons)) == null)) {
            findViewById2.setVisibility(0);
        }
        if (z2) {
            NestedScrollView nestedScrollView = this.f574A;
            if (nestedScrollView != null) {
                nestedScrollView.setClipToPadding(true);
            }
            View view = null;
            if (!(this.f598f == null && this.f599g == null)) {
                view = a.findViewById(C0141f.titleDividerNoCustom);
            }
            if (view != null) {
                view.setVisibility(0);
            }
        } else if (!(a2 == null || (findViewById = a2.findViewById(C0141f.textSpacerNoTitle)) == null)) {
            findViewById.setVisibility(0);
        }
        ListView listView = this.f599g;
        if (listView instanceof RecycleListView) {
            ((RecycleListView) listView).mo938a(z2, z3);
        }
        if (!z) {
            View view2 = this.f599g;
            if (view2 == null) {
                view2 = this.f574A;
            }
            if (view2 != null) {
                if (z3) {
                    c = 2;
                }
                m830a(a2, view2, z2 | c ? 1 : 0, 3);
            }
        }
        ListView listView2 = this.f599g;
        if (listView2 != null && (listAdapter = this.f581H) != null) {
            listView2.setAdapter(listAdapter);
            int i = this.f582I;
            if (i > -1) {
                listView2.setItemChecked(i, true);
                listView2.setSelection(i);
            }
        }
    }

    /* renamed from: c */
    private void m837c(ViewGroup viewGroup) {
        View view = this.f600h;
        boolean z = false;
        if (view == null) {
            view = this.f601i != 0 ? LayoutInflater.from(this.f593a).inflate(this.f601i, viewGroup, false) : null;
        }
        if (view != null) {
            z = true;
        }
        if (!z || !m833a(view)) {
            this.f595c.setFlags(131072, 131072);
        }
        if (z) {
            FrameLayout frameLayout = (FrameLayout) this.f595c.findViewById(C0141f.custom);
            frameLayout.addView(view, new ViewGroup.LayoutParams(-1, -1));
            if (this.f606n) {
                frameLayout.setPadding(this.f602j, this.f603k, this.f604l, this.f605m);
            }
            if (this.f599g != null) {
                ((C0357Q.C0358a) viewGroup.getLayoutParams()).f1301a = 0.0f;
                return;
            }
            return;
        }
        viewGroup.setVisibility(8);
    }

    /* renamed from: d */
    private void m838d(ViewGroup viewGroup) {
        if (this.f580G != null) {
            viewGroup.addView(this.f580G, 0, new ViewGroup.LayoutParams(-1, -2));
            this.f595c.findViewById(C0141f.title_template).setVisibility(8);
            return;
        }
        this.f577D = (ImageView) this.f595c.findViewById(16908294);
        if (!(!TextUtils.isEmpty(this.f597e)) || !this.f589P) {
            this.f595c.findViewById(C0141f.title_template).setVisibility(8);
            this.f577D.setVisibility(8);
            viewGroup.setVisibility(8);
            return;
        }
        this.f578E = (TextView) this.f595c.findViewById(C0141f.alertTitle);
        this.f578E.setText(this.f597e);
        int i = this.f575B;
        if (i != 0) {
            this.f577D.setImageResource(i);
            return;
        }
        Drawable drawable = this.f576C;
        if (drawable != null) {
            this.f577D.setImageDrawable(drawable);
            return;
        }
        this.f578E.setPadding(this.f577D.getPaddingLeft(), this.f577D.getPaddingTop(), this.f577D.getPaddingRight(), this.f577D.getPaddingBottom());
        this.f577D.setVisibility(8);
    }

    /* renamed from: a */
    public int mo925a(int i) {
        TypedValue typedValue = new TypedValue();
        this.f593a.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.resourceId;
    }

    /* renamed from: a */
    public void mo926a() {
        this.f594b.setContentView(m834b());
        m836c();
    }

    /* renamed from: a */
    public void mo927a(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message, Drawable drawable) {
        if (message == null && onClickListener != null) {
            message = this.f591R.obtainMessage(i, onClickListener);
        }
        if (i == -3) {
            this.f616x = charSequence;
            this.f617y = message;
            this.f618z = drawable;
        } else if (i == -2) {
            this.f612t = charSequence;
            this.f613u = message;
            this.f614v = drawable;
        } else if (i == -1) {
            this.f608p = charSequence;
            this.f609q = message;
            this.f610r = drawable;
        } else {
            throw new IllegalArgumentException("Button does not exist");
        }
    }

    /* renamed from: a */
    public void mo928a(Drawable drawable) {
        this.f576C = drawable;
        this.f575B = 0;
        ImageView imageView = this.f577D;
        if (imageView == null) {
            return;
        }
        if (drawable != null) {
            imageView.setVisibility(0);
            this.f577D.setImageDrawable(drawable);
            return;
        }
        imageView.setVisibility(8);
    }

    /* renamed from: a */
    public void mo929a(View view, int i, int i2, int i3, int i4) {
        this.f600h = view;
        this.f601i = 0;
        this.f606n = true;
        this.f602j = i;
        this.f603k = i2;
        this.f604l = i3;
        this.f605m = i4;
    }

    /* renamed from: a */
    public void mo930a(CharSequence charSequence) {
        this.f598f = charSequence;
        TextView textView = this.f579F;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    /* renamed from: a */
    public boolean mo931a(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.f574A;
        return nestedScrollView != null && nestedScrollView.mo802a(keyEvent);
    }

    /* renamed from: b */
    public void mo932b(int i) {
        this.f576C = null;
        this.f575B = i;
        ImageView imageView = this.f577D;
        if (imageView == null) {
            return;
        }
        if (i != 0) {
            imageView.setVisibility(0);
            this.f577D.setImageResource(this.f575B);
            return;
        }
        imageView.setVisibility(8);
    }

    /* renamed from: b */
    public void mo933b(View view) {
        this.f580G = view;
    }

    /* renamed from: b */
    public void mo934b(CharSequence charSequence) {
        this.f597e = charSequence;
        TextView textView = this.f578E;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    /* renamed from: b */
    public boolean mo935b(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.f574A;
        return nestedScrollView != null && nestedScrollView.mo802a(keyEvent);
    }

    /* renamed from: c */
    public void mo936c(int i) {
        this.f600h = null;
        this.f601i = i;
        this.f606n = false;
    }

    /* renamed from: c */
    public void mo937c(View view) {
        this.f600h = view;
        this.f601i = 0;
        this.f606n = false;
    }
}

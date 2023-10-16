package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutCompat;
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
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.org.android.view.ViewCompat;
import com.org.v4.util.R$attr;
import com.org.v4.util.R$id;
import com.org.v4.util.R$styleable;
import java.lang.ref.WeakReference;

class AlertController {
    private Drawable icon;
    ListAdapter mAdapter;
    private int mAlertDialogLayout;
    private final View.OnClickListener mButtonHandler = new FilenameDialog$1(this);
    Button mButtonNegative;
    Message mButtonNegativeMessage;
    private CharSequence mButtonNegativeText;
    Button mButtonNeutral;
    Message mButtonNeutralMessage;
    private CharSequence mButtonNeutralText;
    private int mButtonPanelLayoutHint = 0;
    private int mButtonPanelSideLayout;
    Button mButtonPositive;
    Message mButtonPositiveMessage;
    private CharSequence mButtonPositiveText;
    int mCheckedItem = -1;
    private final Context mContext;
    private View mCustomTitleView;
    final AppCompatDialog mDialog;
    private Drawable mDropShadowDrawable;
    Handler mHandler;
    private Drawable mIcon;
    private int mIconId = 0;
    private ImageView mIconView;
    int mListItemLayout;
    int mListLayout;
    ListView mListView;
    private CharSequence mMessage;
    private TextView mMessageView;
    int mMultiChoiceItemLayout;
    NestedScrollView mScrollView;
    private boolean mSelected;
    private Drawable mSelection;
    int mSingleChoiceItemLayout;
    private CharSequence mTitle;
    private TextView mTitleView;
    private View mView;
    private int mViewLayoutResId;
    private int mViewSpacingBottom;
    private int mViewSpacingLeft;
    private int mViewSpacingRight;
    private boolean mViewSpacingSpecified = false;
    private int mViewSpacingTop;
    private final Window mWindow;
    private final int x;

    public static class RecycleListView extends ListView {
        private final int j;
        private final int k;

        public RecycleListView(Context context) {
            this(context, (AttributeSet) null);
        }

        public RecycleListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray $r4 = context.obtainStyledAttributes(attributeSet, R$styleable.RecycleListView);
            this.j = $r4.getDimensionPixelOffset(R$styleable.RecycleListView_paddingBottomNoButtons, -1);
            this.k = $r4.getDimensionPixelOffset(R$styleable.RecycleListView_paddingTopNoTitle, -1);
        }

        public void a(boolean z, boolean z2) {
            if (!z2 || !z) {
                setPadding(getPaddingLeft(), z ? getPaddingTop() : this.k, getPaddingRight(), z2 ? getPaddingBottom() : this.j);
            }
        }
    }

    public static class a {
        public boolean c;
        public CharSequence d;
        public View g;
        public ListAdapter mAdapter;
        public Drawable mBuilder;
        public int mCheckedItem = -1;
        public boolean[] mCheckedItems;
        public Cursor mCursor;
        public Drawable mIcon;
        public int mIconAttrId = 0;
        public int mIconId = 0;
        public final LayoutInflater mInflater;
        public String mIsCheckedColumn;
        public boolean mIsMultiChoice;
        public boolean mIsSingleChoice;
        public CharSequence[] mItems;
        public String mLabelColumn;
        public Drawable mLayoutId;
        public CharSequence mMessage;
        public DialogInterface.OnClickListener mNegativeButtonListener;
        public CharSequence mNegativeButtonText;
        public CharSequence mNeutralButtonListener;
        public Drawable mNeutralButtonText;
        public DialogInterface.OnMultiChoiceClickListener mOnCheckboxClickListener;
        public DialogInterface.OnClickListener mOnClickListener;
        public AdapterView.OnItemSelectedListener mOnItemSelectedListener;
        public DialogInterface.OnClickListener mPositiveButtonListener;
        public CharSequence mPositiveButtonText;
        public boolean mRecycleOnMeasure = true;
        public View mView;
        public int mViewLayoutResId;
        public int mViewSpacingBottom;
        public int mViewSpacingLeft;
        public int mViewSpacingRight;
        public boolean mViewSpacingSpecified = false;
        public int mViewSpacingTop;
        public DialogInterface.OnClickListener okButtonListener;
        public DialogInterface.OnCancelListener p;
        public DialogInterface.OnDismissListener q;
        public DialogInterface.OnKeyListener r;
        public final Context this$0;
        public C0000a w;

        /* renamed from: android.support.v7.app.AlertController$a$a  reason: collision with other inner class name */
        public interface C0000a {
            void setup(ListView listView);
        }

        public a(Context context) {
            this.this$0 = context;
            this.c = true;
            this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v0, resolved type: android.support.v7.app.AlertController$AlertParams$4} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v1, resolved type: android.support.v7.app.AlertController$AlertParams$4} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v2, resolved type: android.support.v7.app.AlertController$AlertParams$4} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v22, resolved type: android.support.v7.app.AlertController$AlertParams$3} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v2, resolved type: android.support.v7.app.AlertController$c} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v3, resolved type: android.widget.SimpleCursorAdapter} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v4, resolved type: android.support.v7.app.AlertController$AlertParams$2} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v5, resolved type: android.support.v7.app.AlertController$AlertParams$1} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x012a  */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x0135  */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x013d  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void create(android.support.v7.app.AlertController r35) {
            /*
                r34 = this;
                r0 = r34
                android.view.LayoutInflater r7 = r0.mInflater
                r0 = r35
                int r8 = r0.mListLayout
                r10 = 0
                android.view.View r9 = r7.inflate(r8, r10)
                r12 = r9
                android.support.v7.app.AlertController$RecycleListView r12 = (android.support.v7.app.AlertController.RecycleListView) r12
                r11 = r12
                r0 = r34
                boolean r13 = r0.mIsMultiChoice
                if (r13 == 0) goto L_0x0061
                r0 = r34
                android.database.Cursor r14 = r0.mCursor
                if (r14 != 0) goto L_0x0045
                android.support.v7.app.AlertController$AlertParams$1 r15 = new android.support.v7.app.AlertController$AlertParams$1
                r16 = r15
                r0 = r34
                android.content.Context r0 = r0.this$0
                r17 = r0
                r0 = r35
                int r8 = r0.mMultiChoiceItemLayout
                r0 = r34
                java.lang.CharSequence[] r0 = r0.mItems
                r18 = r0
                r19 = 16908308(0x1020014, float:2.3877285E-38)
                r0 = r15
                r1 = r34
                r2 = r17
                r3 = r8
                r4 = r19
                r5 = r18
                r6 = r11
                r0.<init>(r1, r2, r3, r4, r5, r6)
                goto L_0x00d6
            L_0x0045:
                android.support.v7.app.AlertController$AlertParams$2 r20 = new android.support.v7.app.AlertController$AlertParams$2
                r16 = r20
                r0 = r34
                android.content.Context r0 = r0.this$0
                r17 = r0
                r19 = 0
                r0 = r20
                r1 = r34
                r2 = r17
                r3 = r14
                r4 = r19
                r5 = r11
                r6 = r35
                r0.<init>(r1, r2, r3, r4, r5, r6)
                goto L_0x00d6
            L_0x0061:
                r0 = r34
                boolean r13 = r0.mIsSingleChoice
                if (r13 == 0) goto L_0x006c
                r0 = r35
                int r8 = r0.mSingleChoiceItemLayout
                goto L_0x0070
            L_0x006c:
                r0 = r35
                int r8 = r0.mListItemLayout
            L_0x0070:
                r0 = r34
                android.database.Cursor r14 = r0.mCursor
                if (r14 == 0) goto L_0x00af
                android.widget.SimpleCursorAdapter r21 = new android.widget.SimpleCursorAdapter
                r16 = r21
                r0 = r34
                android.content.Context r0 = r0.this$0
                r17 = r0
                r19 = 1
                r0 = r19
                java.lang.String[] r0 = new java.lang.String[r0]
                r22 = r0
                r0 = r34
                java.lang.String r0 = r0.mLabelColumn
                r23 = r0
                r19 = 0
                r22[r19] = r23
                r19 = 1
                r0 = r19
                int[] r0 = new int[r0]
                r24 = r0
                r19 = 0
                r25 = 16908308(0x1020014, float:2.3877285E-38)
                r24[r19] = r25
                r0 = r21
                r1 = r17
                r2 = r8
                r3 = r14
                r4 = r22
                r5 = r24
                r0.<init>(r1, r2, r3, r4, r5)
                goto L_0x00d6
            L_0x00af:
                r0 = r34
                android.widget.ListAdapter r0 = r0.mAdapter
                r16 = r0
                if (r16 == 0) goto L_0x00b8
                goto L_0x00d6
            L_0x00b8:
                android.support.v7.app.AlertController$c r26 = new android.support.v7.app.AlertController$c
                r16 = r26
                r0 = r34
                android.content.Context r0 = r0.this$0
                r17 = r0
                r0 = r34
                java.lang.CharSequence[] r0 = r0.mItems
                r18 = r0
                r19 = 16908308(0x1020014, float:2.3877285E-38)
                r0 = r26
                r1 = r17
                r2 = r19
                r3 = r18
                r0.<init>(r1, r8, r2, r3)
            L_0x00d6:
                r0 = r34
                android.support.v7.app.AlertController$a$a r0 = r0.w
                r27 = r0
                if (r27 == 0) goto L_0x00e3
                r0 = r27
                r0.setup(r11)
            L_0x00e3:
                r0 = r16
                r1 = r35
                r1.mAdapter = r0
                r0 = r34
                int r8 = r0.mCheckedItem
                r0 = r35
                r0.mCheckedItem = r8
                r0 = r34
                android.content.DialogInterface$OnClickListener r0 = r0.mOnClickListener
                r28 = r0
                if (r28 == 0) goto L_0x010c
                android.support.v7.app.AlertController$AlertParams$3 r29 = new android.support.v7.app.AlertController$AlertParams$3
                r30 = r29
                r0 = r29
                r1 = r34
                r2 = r35
                r0.<init>(r1, r2)
            L_0x0106:
                r0 = r30
                r11.setOnItemClickListener(r0)
                goto L_0x0122
            L_0x010c:
                r0 = r34
                android.content.DialogInterface$OnMultiChoiceClickListener r0 = r0.mOnCheckboxClickListener
                r31 = r0
                if (r31 == 0) goto L_0x0122
                android.support.v7.app.AlertController$AlertParams$4 r32 = new android.support.v7.app.AlertController$AlertParams$4
                r30 = r32
                r0 = r32
                r1 = r34
                r2 = r35
                r0.<init>(r1, r11, r2)
                goto L_0x0106
            L_0x0122:
                r0 = r34
                android.widget.AdapterView$OnItemSelectedListener r0 = r0.mOnItemSelectedListener
                r33 = r0
                if (r33 == 0) goto L_0x012f
                r0 = r33
                r11.setOnItemSelectedListener(r0)
            L_0x012f:
                r0 = r34
                boolean r13 = r0.mIsSingleChoice
                if (r13 == 0) goto L_0x013d
                r19 = 1
                r0 = r19
                r11.setChoiceMode(r0)
                goto L_0x014a
            L_0x013d:
                r0 = r34
                boolean r13 = r0.mIsMultiChoice
                if (r13 == 0) goto L_0x014a
                r19 = 2
                r0 = r19
                r11.setChoiceMode(r0)
            L_0x014a:
                r0 = r35
                r0.mListView = r11
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AlertController.a.create(android.support.v7.app.AlertController):void");
        }

        public void apply(AlertController alertController) {
            View $r2 = this.g;
            if ($r2 != null) {
                alertController.setCustomTitle($r2);
            } else {
                CharSequence $r3 = this.d;
                if ($r3 != null) {
                    alertController.setTitle($r3);
                }
                Drawable $r4 = this.mIcon;
                if ($r4 != null) {
                    alertController.setIcon($r4);
                }
                int $i0 = this.mIconId;
                if ($i0 != 0) {
                    alertController.setIcon($i0);
                }
                int $i02 = this.mIconAttrId;
                if ($i02 != 0) {
                    alertController.setIcon(alertController.getIconAttributeResId($i02));
                }
            }
            CharSequence $r32 = this.mMessage;
            if ($r32 != null) {
                alertController.setMessage($r32);
            }
            if (!(this.mPositiveButtonText == null && this.mBuilder == null)) {
                alertController.setButton(-1, this.mPositiveButtonText, this.mPositiveButtonListener, (Message) null, this.mBuilder);
            }
            if (!(this.mNegativeButtonText == null && this.mNeutralButtonText == null)) {
                alertController.setButton(-2, this.mNegativeButtonText, this.mNegativeButtonListener, (Message) null, this.mNeutralButtonText);
            }
            if (!(this.mNeutralButtonListener == null && this.mLayoutId == null)) {
                alertController.setButton(-3, this.mNeutralButtonListener, this.okButtonListener, (Message) null, this.mLayoutId);
            }
            if (!(this.mItems == null && this.mCursor == null && this.mAdapter == null)) {
                create(alertController);
            }
            View $r22 = this.mView;
            if ($r22 == null) {
                int $i03 = this.mViewLayoutResId;
                if ($i03 != 0) {
                    alertController.setView($i03);
                }
            } else if (this.mViewSpacingSpecified) {
                alertController.setView($r22, this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
            } else {
                alertController.setView($r22);
            }
        }
    }

    private static final class b extends Handler {
        private WeakReference<DialogInterface> mDialog;

        public b(DialogInterface dialogInterface) {
            this.mDialog = new WeakReference(dialogInterface);
        }

        public void handleMessage(Message message) {
            int $i0 = message.what;
            if ($i0 == -3 || $i0 == -2 || $i0 == -1) {
                ((DialogInterface.OnClickListener) message.obj).onClick((DialogInterface) this.mDialog.get(), message.what);
            } else if ($i0 == 1) {
                ((DialogInterface) message.obj).dismiss();
            }
        }
    }

    private static class c extends ArrayAdapter<CharSequence> {
        public c(Context context, int i, int i2, CharSequence[] charSequenceArr) {
            super(context, i, i2, charSequenceArr);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public boolean hasStableIds() {
            return true;
        }
    }

    public AlertController(Context context, AppCompatDialog appCompatDialog, Window window) {
        this.mContext = context;
        this.mDialog = appCompatDialog;
        this.mWindow = window;
        this.mHandler = new b(appCompatDialog);
        TypedArray $r7 = context.obtainStyledAttributes((AttributeSet) null, R$styleable.AlertDialog, R$attr.alertDialogStyle, 0);
        this.mAlertDialogLayout = $r7.getResourceId(R$styleable.AlertDialog_android_layout, 0);
        this.mButtonPanelSideLayout = $r7.getResourceId(R$styleable.AlertDialog_buttonPanelSideLayout, 0);
        this.mListLayout = $r7.getResourceId(R$styleable.AlertDialog_listLayout, 0);
        this.mMultiChoiceItemLayout = $r7.getResourceId(R$styleable.AlertDialog_multiChoiceItemLayout, 0);
        this.mSingleChoiceItemLayout = $r7.getResourceId(R$styleable.AlertDialog_singleChoiceItemLayout, 0);
        this.mListItemLayout = $r7.getResourceId(R$styleable.AlertDialog_listItemLayout, 0);
        this.mSelected = $r7.getBoolean(R$styleable.AlertDialog_showTitle, true);
        this.x = $r7.getDimensionPixelSize(R$styleable.AlertDialog_buttonIconDimen, 0);
        $r7.recycle();
        appCompatDialog.supportRequestWindowFeature(1);
    }

    static void access$800(View view, View view2, View view3) {
        byte $b0 = 0;
        if (view2 != null) {
            view2.setVisibility(view.canScrollVertically(-1) ? (byte) 0 : 4);
        }
        if (view3 != null) {
            if (!view.canScrollVertically(1)) {
                $b0 = 4;
            }
            view3.setVisibility($b0);
        }
    }

    static boolean canTextInput(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup $r1 = (ViewGroup) view;
        int $i0 = $r1.getChildCount();
        while ($i0 > 0) {
            $i0--;
            if (canTextInput($r1.getChildAt($i0))) {
                return true;
            }
        }
        return false;
    }

    private void centerButton(Button button) {
        LinearLayout.LayoutParams $r3 = (LinearLayout.LayoutParams) button.getLayoutParams();
        $r3.gravity = 1;
        $r3.weight = 0.5f;
        button.setLayoutParams($r3);
    }

    private ViewGroup resolvePanel(View $r1, View $r2) {
        if ($r1 == null) {
            if ($r2 instanceof ViewStub) {
                $r2 = ((ViewStub) $r2).inflate();
            }
            return (ViewGroup) $r2;
        }
        if ($r2 != null) {
            ViewParent $r5 = $r2.getParent();
            if ($r5 instanceof ViewGroup) {
                ((ViewGroup) $r5).removeView($r2);
            }
        }
        if ($r1 instanceof ViewStub) {
            $r1 = ((ViewStub) $r1).inflate();
        }
        return (ViewGroup) $r1;
    }

    private int selectContentView() {
        int $i1 = this.mButtonPanelSideLayout;
        return $i1 == 0 ? this.mAlertDialogLayout : this.mButtonPanelLayoutHint == 1 ? $i1 : this.mAlertDialogLayout;
    }

    private void setScrollIndicators(ViewGroup viewGroup, View view, int i, int i2) {
        View $r4 = this.mWindow.findViewById(R$id.scrollIndicatorUp);
        final View $r5 = $r4;
        View $r6 = this.mWindow.findViewById(R$id.scrollIndicatorDown);
        if (Build.VERSION.SDK_INT >= 23) {
            ViewCompat.setScrollIndicators(view, i, i2);
            if ($r4 != null) {
                viewGroup.removeView($r4);
            }
            if ($r6 != null) {
                viewGroup.removeView($r6);
                return;
            }
            return;
        }
        View view2 = null;
        if ($r4 != null && (i & 1) == 0) {
            viewGroup.removeView($r4);
            $r5 = null;
        }
        if ($r6 == null || (i & 2) != 0) {
            view2 = $r6;
        } else {
            viewGroup.removeView($r6);
        }
        if ($r5 != null || view2 != null) {
            if (this.mMessage != null) {
                final View view3 = view2;
                this.mScrollView.setOnScrollChangeListener(new NestedScrollView.b() {
                    public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                        AlertController.access$800(nestedScrollView, $r5, view3);
                    }
                });
                final View view4 = view2;
                this.mScrollView.post(new Runnable() {
                    public void run() {
                        AlertController.access$800(AlertController.this.mScrollView, $r5, view4);
                    }
                });
                return;
            }
            ListView $r11 = this.mListView;
            if ($r11 != null) {
                final View view5 = view2;
                $r11.setOnScrollListener(new AbsListView.OnScrollListener() {
                    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                        AlertController.access$800(absListView, $r5, view5);
                    }

                    public void onScrollStateChanged(AbsListView absListView, int i) {
                    }
                });
                final View view6 = view2;
                this.mListView.post(new Runnable() {
                    public void run() {
                        AlertController.access$800(AlertController.this.mListView, $r5, view6);
                    }
                });
                return;
            }
            if ($r5 != null) {
                viewGroup.removeView($r5);
            }
            if (view2 != null) {
                viewGroup.removeView(view2);
            }
        }
    }

    private void setupButtons(ViewGroup viewGroup) {
        byte $b0;
        Button $r3;
        this.mButtonPositive = (Button) viewGroup.findViewById(16908313);
        this.mButtonPositive.setOnClickListener(this.mButtonHandler);
        boolean $z1 = true;
        if (!TextUtils.isEmpty(this.mButtonPositiveText) || this.icon != null) {
            this.mButtonPositive.setText(this.mButtonPositiveText);
            Drawable $r6 = this.icon;
            if ($r6 != null) {
                int $i1 = this.x;
                $r6.setBounds(0, 0, $i1, $i1);
                this.mButtonPositive.setCompoundDrawables(this.icon, (Drawable) null, (Drawable) null, (Drawable) null);
            }
            this.mButtonPositive.setVisibility(0);
            $b0 = 1;
        } else {
            this.mButtonPositive.setVisibility(8);
            $b0 = 0;
        }
        this.mButtonNegative = (Button) viewGroup.findViewById(16908314);
        this.mButtonNegative.setOnClickListener(this.mButtonHandler);
        if (!TextUtils.isEmpty(this.mButtonNegativeText) || this.mSelection != null) {
            this.mButtonNegative.setText(this.mButtonNegativeText);
            Drawable $r62 = this.mSelection;
            if ($r62 != null) {
                int $i12 = this.x;
                $r62.setBounds(0, 0, $i12, $i12);
                this.mButtonNegative.setCompoundDrawables(this.mSelection, (Drawable) null, (Drawable) null, (Drawable) null);
            }
            this.mButtonNegative.setVisibility(0);
            $b0 |= 2;
        } else {
            this.mButtonNegative.setVisibility(8);
        }
        this.mButtonNeutral = (Button) viewGroup.findViewById(16908315);
        this.mButtonNeutral.setOnClickListener(this.mButtonHandler);
        if (!TextUtils.isEmpty(this.mButtonNeutralText) || this.mDropShadowDrawable != null) {
            this.mButtonNeutral.setText(this.mButtonNeutralText);
            Drawable $r63 = this.icon;
            if ($r63 != null) {
                int $i13 = this.x;
                $r63.setBounds(0, 0, $i13, $i13);
                this.mButtonPositive.setCompoundDrawables(this.icon, (Drawable) null, (Drawable) null, (Drawable) null);
            }
            this.mButtonNeutral.setVisibility(0);
            $b0 |= 4;
        } else {
            this.mButtonNeutral.setVisibility(8);
        }
        Context $r7 = this.mContext;
        Context context = $r7;
        if (shouldCenterSingleButton($r7)) {
            if ($b0 == 1) {
                $r3 = this.mButtonPositive;
            } else if ($b0 == 2) {
                $r3 = this.mButtonNegative;
            } else if ($b0 == 4) {
                $r3 = this.mButtonNeutral;
            }
            centerButton($r3);
        }
        if ($b0 == 0) {
            $z1 = false;
        }
        if (!$z1) {
            viewGroup.setVisibility(8);
        }
    }

    private void setupContent(ViewGroup $r1) {
        this.mScrollView = (NestedScrollView) this.mWindow.findViewById(R$id.scrollView);
        this.mScrollView.setFocusable(false);
        this.mScrollView.setNestedScrollingEnabled(false);
        this.mMessageView = (TextView) $r1.findViewById(16908299);
        TextView $r5 = this.mMessageView;
        if ($r5 != null) {
            CharSequence $r6 = this.mMessage;
            if ($r6 != null) {
                $r5.setText($r6);
                return;
            }
            $r5.setVisibility(8);
            this.mScrollView.removeView(this.mMessageView);
            if (this.mListView != null) {
                ViewGroup $r12 = (ViewGroup) this.mScrollView.getParent();
                int $i0 = $r12.indexOfChild(this.mScrollView);
                $r12.removeViewAt($i0);
                $r12.addView(this.mListView, $i0, new ViewGroup.LayoutParams(-1, -1));
                return;
            }
            $r1.setVisibility(8);
        }
    }

    private void setupCustomContent(ViewGroup viewGroup) {
        View $r2 = this.mView;
        boolean $z0 = false;
        if ($r2 == null) {
            $r2 = this.mViewLayoutResId != 0 ? LayoutInflater.from(this.mContext).inflate(this.mViewLayoutResId, viewGroup, false) : null;
        }
        if ($r2 != null) {
            $z0 = true;
        }
        if (!$z0 || !canTextInput($r2)) {
            this.mWindow.setFlags(131072, 131072);
        }
        if ($z0) {
            FrameLayout $r7 = (FrameLayout) this.mWindow.findViewById(R$id.custom);
            $r7.addView($r2, new ViewGroup.LayoutParams(-1, -1));
            if (this.mViewSpacingSpecified) {
                $r7.setPadding(this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
            }
            if (this.mListView != null) {
                ((LinearLayoutCompat.LayoutParams) viewGroup.getLayoutParams()).weight = 0.0f;
                return;
            }
            return;
        }
        viewGroup.setVisibility(8);
    }

    private void setupTitle(ViewGroup viewGroup) {
        if (this.mCustomTitleView != null) {
            ViewGroup.LayoutParams $r3 = new ViewGroup.LayoutParams(-1, -2);
            viewGroup.addView(this.mCustomTitleView, 0, $r3);
            this.mWindow.findViewById(R$id.title_template).setVisibility(8);
            return;
        }
        this.mIconView = (ImageView) this.mWindow.findViewById(16908294);
        if (!(!TextUtils.isEmpty(this.mTitle)) || !this.mSelected) {
            this.mWindow.findViewById(R$id.title_template).setVisibility(8);
            this.mIconView.setVisibility(8);
            viewGroup.setVisibility(8);
            return;
        }
        this.mTitleView = (TextView) this.mWindow.findViewById(R$id.alertTitle);
        this.mTitleView.setText(this.mTitle);
        int $i0 = this.mIconId;
        if ($i0 != 0) {
            this.mIconView.setImageResource($i0);
            return;
        }
        Drawable $r8 = this.mIcon;
        if ($r8 != null) {
            this.mIconView.setImageDrawable($r8);
            return;
        }
        this.mTitleView.setPadding(this.mIconView.getPaddingLeft(), this.mIconView.getPaddingTop(), this.mIconView.getPaddingRight(), this.mIconView.getPaddingBottom());
        this.mIconView.setVisibility(8);
    }

    private void setupView() {
        ListAdapter $r16;
        View $r3;
        View $r32;
        View $r2 = this.mWindow.findViewById(R$id.parentPanel);
        View $r33 = $r2.findViewById(R$id.topPanel);
        View $r4 = $r2.findViewById(R$id.contentPanel);
        View $r5 = $r2.findViewById(R$id.buttonPanel);
        ViewGroup $r6 = (ViewGroup) $r2.findViewById(R$id.customPanel);
        setupCustomContent($r6);
        View $r22 = $r6.findViewById(R$id.topPanel);
        View $r7 = $r6.findViewById(R$id.contentPanel);
        View $r8 = $r6.findViewById(R$id.buttonPanel);
        ViewGroup $r9 = resolvePanel($r22, $r33);
        ViewGroup $r10 = resolvePanel($r7, $r4);
        ViewGroup $r11 = resolvePanel($r8, $r5);
        setupContent($r10);
        setupButtons($r11);
        setupTitle($r9);
        int $i0 = 0;
        boolean z = ($r6 == null || $r6.getVisibility() == 8) ? false : true;
        int $i1 = ($r9 == null || $r9.getVisibility() == 8) ? false : true;
        boolean z2 = ($r11 == null || $r11.getVisibility() == 8) ? false : true;
        if (!(z2 || $r10 == null || ($r32 = $r10.findViewById(R$id.textSpacerNoButtons)) == null)) {
            $r32.setVisibility(0);
        }
        if ($i1 != 0) {
            NestedScrollView $r12 = this.mScrollView;
            if ($r12 != null) {
                $r12.setClipToPadding(true);
            }
            View $r34 = null;
            if (!(this.mMessage == null && this.mListView == null)) {
                $r34 = $r9.findViewById(R$id.titleDividerNoCustom);
            }
            if ($r34 != null) {
                $r34.setVisibility(0);
            }
        } else if (!($r10 == null || ($r3 = $r10.findViewById(R$id.textSpacerNoTitle)) == null)) {
            $r3.setVisibility(0);
        }
        ListView $r14 = this.mListView;
        ListView $r142 = $r14;
        if ($r14 instanceof RecycleListView) {
            ((RecycleListView) $r142).a($i1, z2);
        }
        if (!z) {
            ViewGroup $r62 = this.mListView;
            if ($r62 == null) {
                $r62 = this.mScrollView;
            }
            if ($r62 != null) {
                if (z2) {
                    $i0 = 2;
                }
                setScrollIndicators($r10, $r62, (int) ($i1 | $i0), 3);
            }
        }
        ListView $r143 = this.mListView;
        if ($r143 != null && ($r16 = this.mAdapter) != null) {
            $r143.setAdapter($r16);
            int $i02 = this.mCheckedItem;
            if ($i02 > -1) {
                $r143.setItemChecked($i02, true);
                $r143.setSelection($i02);
            }
        }
    }

    private static boolean shouldCenterSingleButton(Context context) {
        TypedValue $r0 = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.alertDialogCenterButtons, $r0, true);
        return $r0.data != 0;
    }

    public int getIconAttributeResId(int i) {
        TypedValue $r1 = new TypedValue();
        this.mContext.getTheme().resolveAttribute(i, $r1, true);
        return $r1.resourceId;
    }

    public void installContent() {
        this.mDialog.setContentView(selectContentView());
        setupView();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        NestedScrollView $r2 = this.mScrollView;
        return $r2 != null && $r2.executeKeyEvent(keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        NestedScrollView $r2 = this.mScrollView;
        return $r2 != null && $r2.executeKeyEvent(keyEvent);
    }

    public void setButton(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message $r2, Drawable drawable) {
        if ($r2 == null && onClickListener != null) {
            $r2 = this.mHandler.obtainMessage(i, onClickListener);
        }
        if (i == -3) {
            this.mButtonNeutralText = charSequence;
            this.mButtonNeutralMessage = $r2;
            this.mDropShadowDrawable = drawable;
        } else if (i == -2) {
            this.mButtonNegativeText = charSequence;
            this.mButtonNegativeMessage = $r2;
            this.mSelection = drawable;
        } else if (i == -1) {
            this.mButtonPositiveText = charSequence;
            this.mButtonPositiveMessage = $r2;
            this.icon = drawable;
        } else {
            throw new IllegalArgumentException("Button does not exist");
        }
    }

    public void setCustomTitle(View view) {
        this.mCustomTitleView = view;
    }

    public void setIcon(int i) {
        this.mIcon = null;
        this.mIconId = i;
        ImageView $r1 = this.mIconView;
        if ($r1 == null) {
            return;
        }
        if (i != 0) {
            $r1.setVisibility(0);
            this.mIconView.setImageResource(this.mIconId);
            return;
        }
        $r1.setVisibility(8);
    }

    public void setIcon(Drawable drawable) {
        this.mIcon = drawable;
        this.mIconId = 0;
        ImageView $r1 = this.mIconView;
        if ($r1 == null) {
            return;
        }
        if (drawable != null) {
            $r1.setVisibility(0);
            this.mIconView.setImageDrawable(drawable);
            return;
        }
        $r1.setVisibility(8);
    }

    public void setMessage(CharSequence charSequence) {
        this.mMessage = charSequence;
        TextView $r2 = this.mMessageView;
        if ($r2 != null) {
            $r2.setText(charSequence);
        }
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        TextView $r2 = this.mTitleView;
        if ($r2 != null) {
            $r2.setText(charSequence);
        }
    }

    public void setView(int i) {
        this.mView = null;
        this.mViewLayoutResId = i;
        this.mViewSpacingSpecified = false;
    }

    public void setView(View view) {
        this.mView = view;
        this.mViewLayoutResId = 0;
        this.mViewSpacingSpecified = false;
    }

    public void setView(View view, int i, int i2, int i3, int i4) {
        this.mView = view;
        this.mViewLayoutResId = 0;
        this.mViewSpacingSpecified = true;
        this.mViewSpacingLeft = i;
        this.mViewSpacingTop = i2;
        this.mViewSpacingRight = i3;
        this.mViewSpacingBottom = i4;
    }
}

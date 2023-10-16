package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.view.menu.ListMenuItemView;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.e;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.org.android.view.ViewCompat;
import com.org.v4.util.R$styleable;
import java.lang.reflect.Method;
import org.cocos2dx.package_1.GameControllerDelegate;

public class ListPopupWindow implements android.support.v7.view.menu.ListPopupWindow {
    private static Method b = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", new Class[]{Rect.class});
    private static Method sClipToWindowEnabledMethod = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", new Class[]{Boolean.TYPE});
    private static Method sGetMaxAvailableHeightMethod = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", new Class[]{View.class, Integer.TYPE, Boolean.TYPE});
    private boolean DEBUG;
    private ListAdapter mAdapter;
    private Context mContext;
    private boolean mDropDownAlwaysVisible;
    private View mDropDownAnchorView;
    private int mDropDownGravity;
    private int mDropDownHeight;
    private int mDropDownHorizontalOffset;
    ListViewCompat mDropDownList;
    private Drawable mDropDownListHighlight;
    private int mDropDownVerticalOffset;
    private boolean mDropDownVerticalOffsetSet;
    private int mDropDownWidth;
    private int mDropDownWindowLayoutType;
    private boolean mForceIgnoreOutsideTouch;
    final Handler mHandler;
    private final ListSelectorHider mHideSelector;
    private AdapterView.OnItemClickListener mItemClickListener;
    private AdapterView.OnItemSelectedListener mItemSelectedListener;
    int mListItemExpandMaximum;
    private boolean mModal;
    private DataSetObserver mObserver;
    PopupWindow mPopup;
    private boolean mPostedShow;
    private int mPromptPosition;
    private View mPromptView;
    private final PopupScrollListener mScrollListener;
    private Runnable mShowDropDownRunnable;
    private Rect mTempAdapter;
    private final Rect mTempRect;
    private final PopupTouchInterceptor mTouchInterceptor;
    final ResizePopupRunnable runnable;
    private boolean x;

    public class DropDownListView extends ListViewCompat {
        private MenuItem b;
        final int command;
        private Object d;
        final int searchAttributes;

        public DropDownListView(Context context, boolean z) {
            super(context, z);
            Configuration $r3 = context.getResources().getConfiguration();
            if (Build.VERSION.SDK_INT < 17 || 1 != $r3.getLayoutDirection()) {
                this.command = 22;
                this.searchAttributes = 21;
                return;
            }
            this.command = 21;
            this.searchAttributes = 22;
        }

        public /* bridge */ /* synthetic */ boolean hasFocus() {
            return super.hasFocus();
        }

        public /* bridge */ /* synthetic */ boolean hasWindowFocus() {
            return super.hasWindowFocus();
        }

        public /* bridge */ /* synthetic */ boolean isFocused() {
            return super.isFocused();
        }

        public /* bridge */ /* synthetic */ boolean isInTouchMode() {
            return super.isInTouchMode();
        }

        public /* bridge */ /* synthetic */ int measureHeightOfChildrenCompat(int i, int i2, int i3, int i4, int i5) {
            return super.measureHeightOfChildrenCompat(i, i2, i3, i4, i5);
        }

        public /* bridge */ /* synthetic */ boolean onForwardedEvent(MotionEvent motionEvent, int i) {
            return super.onForwardedEvent(motionEvent, i);
        }

        public boolean onHoverEvent(MotionEvent motionEvent) {
            int $i0;
            int $i1;
            int $i02;
            if (this.d != null) {
                ListAdapter $r3 = getAdapter();
                ListAdapter $r4 = $r3;
                if ($r3 instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter $r5 = (HeaderViewListAdapter) $r3;
                    $i0 = $r5.getHeadersCount();
                    $r4 = $r5.getWrappedAdapter();
                } else {
                    $i0 = 0;
                }
                e.a $r6 = (e.a) $r4;
                MenuItemImpl $r7 = null;
                if (motionEvent.getAction() != 10 && ($i1 = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) != -1 && ($i02 = $i1 - $i0) >= 0 && $i02 < $r6.getCount()) {
                    $r7 = $r6.getItem($i02);
                }
                MenuItem $r8 = this.b;
                MenuItem $r82 = $r8;
                if ($r8 != $r7) {
                    MenuBuilder $r9 = $r6.b();
                    if ($r82 != null) {
                        this.d.b($r9, $r82);
                    }
                    this.b = $r7;
                    if ($r7 != null) {
                        this.d.a($r9, $r7);
                    }
                }
            }
            return super.onHoverEvent(motionEvent);
        }

        public boolean onKeyDown(int $i0, KeyEvent keyEvent) {
            ListMenuItemView $r3 = (ListMenuItemView) getSelectedView();
            if ($r3 == null || $i0 != this.command) {
                if ($r3 == null || $i0 != this.searchAttributes) {
                    return super.onKeyDown($i0, keyEvent);
                }
                setSelection(-1);
                ((e.a) getAdapter()).b().close(false);
                return true;
            } else if (!$r3.isEnabled() || !$r3.getItemData().hasSubMenu()) {
                return true;
            } else {
                performItemClick($r3, getSelectedItemPosition(), getSelectedItemId());
                return true;
            }
        }

        public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
            return super.onTouchEvent(motionEvent);
        }

        public void setHoverListener(Object object) {
            this.d = object;
        }

        public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
            super.setSelector(drawable);
        }
    }

    public abstract class ForwardingListener implements View.OnTouchListener, View.OnAttachStateChangeListener {
        private int mActivePointerId;
        private Runnable mDisallowIntercept;
        private boolean mForwarding;
        private final int mLongPressTimeout;
        private final float mScaledTouchSlop;
        final View mSrc;
        private final int mTapTimeout;
        private final int[] mTmpLocation = new int[2];
        private Runnable mTriggerLongPress;

        class DisallowIntercept implements Runnable {
            DisallowIntercept() {
            }

            public void run() {
                ViewParent $r1 = ForwardingListener.this.mSrc.getParent();
                if ($r1 != null) {
                    $r1.requestDisallowInterceptTouchEvent(true);
                }
            }
        }

        class TriggerLongPress implements Runnable {
            TriggerLongPress() {
            }

            public void run() {
                ForwardingListener.this.onLongPress();
            }
        }

        public ForwardingListener(View view) {
            this.mSrc = view;
            view.setLongClickable(true);
            view.addOnAttachStateChangeListener(this);
            this.mScaledTouchSlop = (float) ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            this.mTapTimeout = ViewConfiguration.getTapTimeout();
            this.mLongPressTimeout = (this.mTapTimeout + ViewConfiguration.getLongPressTimeout()) / 2;
        }

        private void clearCallbacks() {
            Runnable $r1 = this.mTriggerLongPress;
            if ($r1 != null) {
                this.mSrc.removeCallbacks($r1);
            }
            Runnable $r12 = this.mDisallowIntercept;
            if ($r12 != null) {
                this.mSrc.removeCallbacks($r12);
            }
        }

        private boolean onTouchForwarded(MotionEvent motionEvent) {
            ListViewCompat $r5;
            View $r2 = this.mSrc;
            android.support.v7.view.menu.ListPopupWindow $r3 = getPopup();
            if ($r3 == null || !$r3.isShowing() || ($r5 = (ListViewCompat) $r3.add()) == null || !$r5.isShown()) {
                return false;
            }
            MotionEvent $r6 = MotionEvent.obtainNoHistory(motionEvent);
            toGlobalMotionEvent($r2, $r6);
            toLocalMotionEvent($r5, $r6);
            boolean $z0 = $r5.onForwardedEvent($r6, this.mActivePointerId);
            $r6.recycle();
            int $i0 = motionEvent.getActionMasked();
            return $z0 && ($i0 != 1 && $i0 != 3);
        }

        private boolean onTouchObserved(MotionEvent motionEvent) {
            View $r1 = this.mSrc;
            if (!$r1.isEnabled()) {
                return false;
            }
            int $i0 = motionEvent.getActionMasked();
            if ($i0 != 0) {
                if ($i0 != 1) {
                    if ($i0 == 2) {
                        int $i02 = motionEvent.findPointerIndex(this.mActivePointerId);
                        if ($i02 < 0 || pointInView($r1, motionEvent.getX($i02), motionEvent.getY($i02), this.mScaledTouchSlop)) {
                            return false;
                        }
                        clearCallbacks();
                        $r1.getParent().requestDisallowInterceptTouchEvent(true);
                        return true;
                    } else if ($i0 != 3) {
                        return false;
                    }
                }
                clearCallbacks();
                return false;
            }
            this.mActivePointerId = motionEvent.getPointerId(0);
            if (this.mDisallowIntercept == null) {
                this.mDisallowIntercept = new DisallowIntercept();
            }
            $r1.postDelayed(this.mDisallowIntercept, (long) this.mTapTimeout);
            if (this.mTriggerLongPress == null) {
                this.mTriggerLongPress = new TriggerLongPress();
            }
            $r1.postDelayed(this.mTriggerLongPress, (long) this.mLongPressTimeout);
            return false;
        }

        private static boolean pointInView(View view, float f, float f2, float f3) {
            float $f3 = -f3;
            return f >= $f3 && f2 >= $f3 && f < ((float) (view.getRight() - view.getLeft())) + f3 && f2 < ((float) (view.getBottom() - view.getTop())) + f3;
        }

        private boolean toGlobalMotionEvent(View view, MotionEvent motionEvent) {
            int[] $r3 = this.mTmpLocation;
            view.getLocationOnScreen($r3);
            motionEvent.offsetLocation((float) $r3[0], (float) $r3[1]);
            return true;
        }

        private boolean toLocalMotionEvent(View view, MotionEvent motionEvent) {
            int[] $r3 = this.mTmpLocation;
            view.getLocationOnScreen($r3);
            motionEvent.offsetLocation((float) (-$r3[0]), (float) (-$r3[1]));
            return true;
        }

        public abstract android.support.v7.view.menu.ListPopupWindow getPopup();

        /* access modifiers changed from: protected */
        public abstract boolean onForwardingStarted();

        /* access modifiers changed from: protected */
        public boolean onForwardingStopped() {
            android.support.v7.view.menu.ListPopupWindow $r1 = getPopup();
            if ($r1 == null || !$r1.isShowing()) {
                return true;
            }
            $r1.dismiss();
            return true;
        }

        /* access modifiers changed from: package-private */
        public void onLongPress() {
            clearCallbacks();
            View $r1 = this.mSrc;
            if ($r1.isEnabled() && !$r1.isLongClickable() && onForwardingStarted()) {
                $r1.getParent().requestDisallowInterceptTouchEvent(true);
                long $l0 = SystemClock.uptimeMillis();
                MotionEvent $r3 = MotionEvent.obtain($l0, $l0, 3, 0.0f, 0.0f, 0);
                $r1.onTouchEvent($r3);
                $r3.recycle();
                this.mForwarding = true;
            }
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            boolean $z1;
            boolean $z0 = this.mForwarding;
            if ($z0) {
                $z1 = onTouchForwarded(motionEvent) || !onForwardingStopped();
            } else {
                $z1 = onTouchObserved(motionEvent) && onForwardingStarted();
                if ($z1) {
                    long $l0 = SystemClock.uptimeMillis();
                    MotionEvent $r2 = MotionEvent.obtain($l0, $l0, 3, 0.0f, 0.0f, 0);
                    View $r1 = this.mSrc;
                    View view2 = $r1;
                    $r1.onTouchEvent($r2);
                    $r2.recycle();
                }
            }
            this.mForwarding = $z1;
            return $z1 || $z0;
        }

        public void onViewAttachedToWindow(View view) {
        }

        public void onViewDetachedFromWindow(View view) {
            this.mForwarding = false;
            this.mActivePointerId = -1;
            Runnable $r2 = this.mDisallowIntercept;
            if ($r2 != null) {
                this.mSrc.removeCallbacks($r2);
            }
        }
    }

    class ListSelectorHider implements Runnable {
        ListSelectorHider() {
        }

        public void run() {
            ListPopupWindow.this.clearListSelection();
        }
    }

    class PopupDataSetObserver extends DataSetObserver {
        PopupDataSetObserver() {
        }

        public void onChanged() {
            if (ListPopupWindow.this.isShowing()) {
                ListPopupWindow.this.show();
            }
        }

        public void onInvalidated() {
            ListPopupWindow.this.dismiss();
        }
    }

    class PopupScrollListener implements AbsListView.OnScrollListener {
        PopupScrollListener() {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i == 1 && !ListPopupWindow.this.isInputMethodNotNeeded() && ListPopupWindow.this.mPopup.getContentView() != null) {
                ListPopupWindow $r2 = ListPopupWindow.this;
                $r2.mHandler.removeCallbacks($r2.runnable);
                ListPopupWindow.this.runnable.run();
            }
        }
    }

    class PopupTouchInterceptor implements View.OnTouchListener {
        PopupTouchInterceptor() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            PopupWindow $r4;
            int $i0 = motionEvent.getAction();
            int $i1 = (int) motionEvent.getX();
            int $i2 = (int) motionEvent.getY();
            if ($i0 == 0 && ($r4 = ListPopupWindow.this.mPopup) != null && $r4.isShowing() && $i1 >= 0 && $i1 < ListPopupWindow.this.mPopup.getWidth() && $i2 >= 0 && $i2 < ListPopupWindow.this.mPopup.getHeight()) {
                ListPopupWindow $r3 = ListPopupWindow.this;
                $r3.mHandler.postDelayed($r3.runnable, 250);
                return false;
            } else if ($i0 != 1) {
                return false;
            } else {
                ListPopupWindow $r32 = ListPopupWindow.this;
                $r32.mHandler.removeCallbacks($r32.runnable);
                return false;
            }
        }
    }

    class ResizePopupRunnable implements Runnable {
        ResizePopupRunnable() {
        }

        public void run() {
            ListViewCompat $r2 = ListPopupWindow.this.mDropDownList;
            if ($r2 != null && ViewCompat.isAttachedToWindow($r2) && ListPopupWindow.this.mDropDownList.getCount() > ListPopupWindow.this.mDropDownList.getChildCount()) {
                int $i1 = ListPopupWindow.this.mDropDownList.getChildCount();
                ListPopupWindow $r1 = ListPopupWindow.this;
                if ($i1 <= $r1.mListItemExpandMaximum) {
                    $r1.mPopup.setInputMethodMode(2);
                    ListPopupWindow.this.show();
                }
            }
        }
    }

    static {
        try {
        } catch (NoSuchMethodException e) {
            Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
        }
        try {
        } catch (NoSuchMethodException e2) {
            Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
        }
        try {
        } catch (NoSuchMethodException e3) {
            Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
        }
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mDropDownHeight = -2;
        this.mDropDownWidth = -2;
        this.mDropDownWindowLayoutType = GameControllerDelegate.THUMBSTICK_RIGHT_X;
        this.DEBUG = true;
        this.mDropDownGravity = 0;
        this.mDropDownAlwaysVisible = false;
        this.mForceIgnoreOutsideTouch = false;
        this.mListItemExpandMaximum = Integer.MAX_VALUE;
        this.mPromptPosition = 0;
        this.runnable = new ResizePopupRunnable();
        this.mTouchInterceptor = new PopupTouchInterceptor();
        this.mScrollListener = new PopupScrollListener();
        this.mHideSelector = new ListSelectorHider();
        this.mTempRect = new Rect();
        this.mContext = context;
        this.mHandler = new Handler(context.getMainLooper());
        TypedArray $r11 = context.obtainStyledAttributes(attributeSet, R$styleable.ListPopupWindow, i, i2);
        this.mDropDownHorizontalOffset = $r11.getDimensionPixelOffset(R$styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        this.mDropDownVerticalOffset = $r11.getDimensionPixelOffset(R$styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        if (this.mDropDownVerticalOffset != 0) {
            this.mDropDownVerticalOffsetSet = true;
        }
        $r11.recycle();
        this.mPopup = new AppCompatPopupWindow(context, attributeSet, i, i2);
        this.mPopup.setInputMethodMode(1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x02aa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int buildDropDown() {
        /*
            r43 = this;
            r0 = r43
            android.support.v7.widget.ListViewCompat r6 = r0.mDropDownList
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            r8 = 1
            if (r6 != 0) goto L_0x0170
            r0 = r43
            android.content.Context r9 = r0.mContext
            android.support.v7.widget.ListPopupWindow$2 r10 = new android.support.v7.widget.ListPopupWindow$2
            r0 = r43
            r10.<init>()
            r0 = r43
            r0.mShowDropDownRunnable = r10
            r0 = r43
            boolean r11 = r0.mModal
            r12 = 1
            r11 = r11 ^ r12
            r0 = r43
            android.support.v7.widget.ListViewCompat r6 = r0.show(r9, r11)
            r0 = r43
            r0.mDropDownList = r6
            r0 = r43
            android.graphics.drawable.Drawable r13 = r0.mDropDownListHighlight
            if (r13 == 0) goto L_0x0036
            r0 = r43
            android.support.v7.widget.ListViewCompat r6 = r0.mDropDownList
            r6.setSelector(r13)
        L_0x0036:
            r0 = r43
            android.support.v7.widget.ListViewCompat r6 = r0.mDropDownList
            r0 = r43
            android.widget.ListAdapter r14 = r0.mAdapter
            r6.setAdapter(r14)
            r0 = r43
            android.support.v7.widget.ListViewCompat r6 = r0.mDropDownList
            r0 = r43
            android.widget.AdapterView$OnItemClickListener r15 = r0.mItemClickListener
            r6.setOnItemClickListener(r15)
            r0 = r43
            android.support.v7.widget.ListViewCompat r6 = r0.mDropDownList
            r12 = 1
            r6.setFocusable(r12)
            r0 = r43
            android.support.v7.widget.ListViewCompat r6 = r0.mDropDownList
            r12 = 1
            r6.setFocusableInTouchMode(r12)
            r0 = r43
            android.support.v7.widget.ListViewCompat r6 = r0.mDropDownList
            android.support.v7.widget.IcsListPopupWindow$1 r16 = new android.support.v7.widget.IcsListPopupWindow$1
            r0 = r16
            r1 = r43
            r0.<init>(r1)
            r0 = r16
            r6.setOnItemSelectedListener(r0)
            r0 = r43
            android.support.v7.widget.ListViewCompat r6 = r0.mDropDownList
            r0 = r43
            android.support.v7.widget.ListPopupWindow$PopupScrollListener r0 = r0.mScrollListener
            r17 = r0
            r6.setOnScrollListener(r0)
            r0 = r43
            android.widget.AdapterView$OnItemSelectedListener r0 = r0.mItemSelectedListener
            r18 = r0
            if (r18 == 0) goto L_0x008c
            r0 = r43
            android.support.v7.widget.ListViewCompat r6 = r0.mDropDownList
            r0 = r18
            r6.setOnItemSelectedListener(r0)
        L_0x008c:
            r0 = r43
            android.support.v7.widget.ListViewCompat r0 = r0.mDropDownList
            r19 = r0
            r0 = r43
            android.view.View r0 = r0.mPromptView
            r20 = r0
            if (r20 == 0) goto L_0x0162
            android.widget.LinearLayout r21 = new android.widget.LinearLayout
            r0 = r21
            r0.<init>(r9)
            r12 = 1
            r0 = r21
            r0.setOrientation(r12)
            android.widget.LinearLayout$LayoutParams r22 = new android.widget.LinearLayout$LayoutParams
            r12 = -1
            r23 = 0
            r24 = 1065353216(0x3f800000, float:1.0)
            r0 = r22
            r1 = r23
            r2 = r24
            r0.<init>(r12, r1, r2)
            r0 = r43
            int r0 = r0.mPromptPosition
            r25 = r0
            if (r25 == 0) goto L_0x0103
            r12 = 1
            r0 = r25
            if (r0 == r12) goto L_0x00f2
            java.lang.StringBuilder r26 = new java.lang.StringBuilder
            r0 = r26
            r0.<init>()
            java.lang.String r27 = "Invalid hint position "
            r0 = r26
            r1 = r27
            r0.append(r1)
            r0 = r43
            int r0 = r0.mPromptPosition
            r25 = r0
            r0 = r26
            r1 = r25
            r0.append(r1)
            r0 = r26
            java.lang.String r28 = r0.toString()
            java.lang.String r27 = "ListPopupWindow"
            r0 = r27
            r1 = r28
            android.util.Log.e(r0, r1)
            goto L_0x0113
        L_0x00f2:
            r0 = r21
            r1 = r19
            r2 = r22
            r0.addView(r1, r2)
            r0 = r21
            r1 = r20
            r0.addView(r1)
            goto L_0x0113
        L_0x0103:
            r0 = r21
            r1 = r20
            r0.addView(r1)
            r0 = r21
            r1 = r19
            r2 = r22
            r0.addView(r1, r2)
        L_0x0113:
            r0 = r43
            int r0 = r0.mDropDownWidth
            r25 = r0
            if (r25 < 0) goto L_0x011f
            r29 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x0123
        L_0x011f:
            r25 = 0
            r29 = 0
        L_0x0123:
            r0 = r25
            r1 = r29
            int r25 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r1)
            r12 = 0
            r0 = r20
            r1 = r25
            r0.measure(r1, r12)
            r0 = r20
            android.view.ViewGroup$LayoutParams r30 = r0.getLayoutParams()
            r31 = r30
            android.widget.LinearLayout$LayoutParams r31 = (android.widget.LinearLayout.LayoutParams) r31
            r22 = r31
            r0 = r20
            int r25 = r0.getMeasuredHeight()
            r0 = r22
            int r0 = r0.topMargin
            r29 = r0
            r0 = r25
            r1 = r29
            int r0 = r0 + r1
            r25 = r0
            r0 = r22
            int r0 = r0.bottomMargin
            r29 = r0
            r0 = r25
            r1 = r29
            int r0 = r0 + r1
            r25 = r0
            r19 = r21
            goto L_0x0164
        L_0x0162:
            r25 = 0
        L_0x0164:
            r0 = r43
            android.widget.PopupWindow r0 = r0.mPopup
            r32 = r0
            r1 = r19
            r0.setContentView(r1)
            goto L_0x01b7
        L_0x0170:
            r0 = r43
            android.widget.PopupWindow r0 = r0.mPopup
            r32 = r0
            android.view.View r20 = r0.getContentView()
            r33 = r20
            android.view.ViewGroup r33 = (android.view.ViewGroup) r33
            r19 = r33
            r0 = r43
            android.view.View r0 = r0.mPromptView
            r20 = r0
            if (r20 == 0) goto L_0x01b5
            r0 = r20
            android.view.ViewGroup$LayoutParams r30 = r0.getLayoutParams()
            r34 = r30
            android.widget.LinearLayout$LayoutParams r34 = (android.widget.LinearLayout.LayoutParams) r34
            r22 = r34
            r0 = r20
            int r25 = r0.getMeasuredHeight()
            r0 = r22
            int r0 = r0.topMargin
            r29 = r0
            r0 = r25
            r1 = r29
            int r0 = r0 + r1
            r25 = r0
            r0 = r22
            int r0 = r0.bottomMargin
            r29 = r0
            r0 = r25
            r1 = r29
            int r0 = r0 + r1
            r25 = r0
            goto L_0x01b7
        L_0x01b5:
            r25 = 0
        L_0x01b7:
            r0 = r43
            android.widget.PopupWindow r0 = r0.mPopup
            r32 = r0
            android.graphics.drawable.Drawable r13 = r0.getBackground()
            if (r13 == 0) goto L_0x01f1
            r0 = r43
            android.graphics.Rect r0 = r0.mTempRect
            r35 = r0
            r13.getPadding(r0)
            r0 = r43
            android.graphics.Rect r0 = r0.mTempRect
            r35 = r0
            int r0 = r0.top
            r36 = r0
            r0 = r35
            int r0 = r0.bottom
            r29 = r0
            r1 = r36
            int r0 = r0 + r1
            r29 = r0
            r0 = r43
            boolean r11 = r0.mDropDownVerticalOffsetSet
            if (r11 != 0) goto L_0x01fc
            r0 = r36
            int r0 = -r0
            r36 = r0
            r1 = r43
            r1.mDropDownVerticalOffset = r0
            goto L_0x01fc
        L_0x01f1:
            r0 = r43
            android.graphics.Rect r0 = r0.mTempRect
            r35 = r0
            r0.setEmpty()
            r29 = 0
        L_0x01fc:
            r0 = r43
            android.widget.PopupWindow r0 = r0.mPopup
            r32 = r0
            int r36 = r0.getInputMethodMode()
            r12 = 2
            r0 = r36
            if (r0 != r12) goto L_0x020c
            goto L_0x020d
        L_0x020c:
            r8 = 0
        L_0x020d:
            r0 = r43
            android.view.View r20 = r0.getAnchorView()
            r0 = r43
            int r0 = r0.mDropDownVerticalOffset
            r36 = r0
            r0 = r43
            r1 = r20
            r2 = r36
            int r36 = r0.getMaxAvailableHeight(r1, r2, r8)
            r0 = r43
            boolean r8 = r0.mDropDownAlwaysVisible
            if (r8 != 0) goto L_0x02d3
            r0 = r43
            int r0 = r0.mDropDownHeight
            r37 = r0
            r12 = -1
            r0 = r37
            if (r0 != r12) goto L_0x0237
            goto L_0x02d3
        L_0x0237:
            r0 = r43
            int r0 = r0.mDropDownWidth
            r37 = r0
            r12 = -2
            r0 = r37
            if (r0 == r12) goto L_0x0254
            r7 = 1073741824(0x40000000, float:2.0)
            r12 = -1
            r0 = r37
            if (r0 == r12) goto L_0x0254
            r12 = 1073741824(0x40000000, float:2.0)
            r0 = r37
            int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r12)
        L_0x0253:
            goto L_0x028b
        L_0x0254:
            r0 = r43
            android.content.Context r9 = r0.mContext
            android.content.res.Resources r38 = r9.getResources()
            r0 = r38
            android.util.DisplayMetrics r39 = r0.getDisplayMetrics()
            r0 = r39
            int r0 = r0.widthPixels
            r37 = r0
            r0 = r43
            android.graphics.Rect r0 = r0.mTempRect
            r35 = r0
            int r0 = r0.left
            r40 = r0
            r0 = r35
            int r0 = r0.right
            r41 = r0
            r0 = r40
            r1 = r41
            int r0 = r0 + r1
            r40 = r0
            r0 = r37
            r1 = r40
            int r0 = r0 - r1
            r37 = r0
            int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r7)
            goto L_0x0253
        L_0x028b:
            r0 = r43
            android.support.v7.widget.ListViewCompat r6 = r0.mDropDownList
            r0 = r36
            r1 = r25
            int r0 = r0 - r1
            r36 = r0
            r12 = 0
            r23 = -1
            r42 = -1
            r0 = r6
            r1 = r7
            r2 = r12
            r3 = r23
            r4 = r36
            r5 = r42
            int r7 = r0.measureHeightOfChildrenCompat(r1, r2, r3, r4, r5)
            if (r7 <= 0) goto L_0x02cf
            r0 = r43
            android.support.v7.widget.ListViewCompat r6 = r0.mDropDownList
            int r36 = r6.getPaddingTop()
            r0 = r43
            android.support.v7.widget.ListViewCompat r6 = r0.mDropDownList
            int r37 = r6.getPaddingBottom()
            r0 = r36
            r1 = r37
            int r0 = r0 + r1
            r36 = r0
            r0 = r29
            r1 = r36
            int r0 = r0 + r1
            r29 = r0
            r0 = r25
            r1 = r29
            int r0 = r0 + r1
            r25 = r0
        L_0x02cf:
            r0 = r25
            int r7 = r7 + r0
            return r7
        L_0x02d3:
            int r7 = r36 + r29
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ListPopupWindow.buildDropDown():int");
    }

    private int getMaxAvailableHeight(View view, int $i0, boolean z) {
        Method $r4 = sGetMaxAvailableHeightMethod;
        if ($r4 != null) {
            PopupWindow $r1 = this.mPopup;
            Object[] $r5 = new Object[3];
            $r5[0] = view;
            try {
                $r5[1] = Integer.valueOf($i0);
                $r5[2] = Boolean.valueOf(z);
                return ((Integer) $r4.invoke($r1, $r5)).intValue();
            } catch (Exception e) {
                Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
            }
        }
        return this.mPopup.getMaxAvailableHeight(view, $i0);
    }

    private void removePromptView() {
        View $r2 = this.mPromptView;
        if ($r2 != null) {
            ViewParent $r1 = $r2.getParent();
            if ($r1 instanceof ViewGroup) {
                ((ViewGroup) $r1).removeView(this.mPromptView);
            }
        }
    }

    private void setPopupClipToScreenEnabled(boolean z) {
        Method $r4 = sClipToWindowEnabledMethod;
        if ($r4 != null) {
            PopupWindow $r1 = this.mPopup;
            Object[] $r5 = new Object[1];
            try {
                $r5[0] = Boolean.valueOf(z);
                $r4.invoke($r1, $r5);
            } catch (Exception e) {
                Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    public void a(int i) {
        this.mDropDownGravity = i;
    }

    public void a(View view) {
        this.mDropDownAnchorView = view;
    }

    public ListView add() {
        return this.mDropDownList;
    }

    public void clearListSelection() {
        ListViewCompat $r1 = this.mDropDownList;
        if ($r1 != null) {
            $r1.setListSelectionHidden(true);
            $r1.requestLayout();
        }
    }

    public void dismiss() {
        this.mPopup.dismiss();
        removePromptView();
        this.mPopup.setContentView((View) null);
        this.mDropDownList = null;
        this.mHandler.removeCallbacks(this.runnable);
    }

    public void dismiss(int i) {
        this.mPopup.setAnimationStyle(i);
    }

    public void dismiss(boolean z) {
        this.mModal = z;
        this.mPopup.setFocusable(z);
    }

    public View getAnchorView() {
        return this.mDropDownAnchorView;
    }

    public Drawable getBackground() {
        return this.mPopup.getBackground();
    }

    public int getHorizontalOffset() {
        return this.mDropDownHorizontalOffset;
    }

    public int getVerticalOffset() {
        if (!this.mDropDownVerticalOffsetSet) {
            return 0;
        }
        return this.mDropDownVerticalOffset;
    }

    public int getWidth() {
        return this.mDropDownWidth;
    }

    public boolean isInputMethodNotNeeded() {
        return this.mPopup.getInputMethodMode() == 2;
    }

    public boolean isModal() {
        return this.mModal;
    }

    public boolean isShowing() {
        return this.mPopup.isShowing();
    }

    public void setAdapter(int i) {
        this.mDropDownHorizontalOffset = i;
    }

    public void setAdapter(Rect rect) {
        this.mTempAdapter = rect;
    }

    public void setAdapter(ListAdapter listAdapter) {
        DataSetObserver $r3 = this.mObserver;
        if ($r3 == null) {
            this.mObserver = new PopupDataSetObserver();
        } else {
            ListAdapter $r1 = this.mAdapter;
            if ($r1 != null) {
                $r1.unregisterDataSetObserver($r3);
            }
        }
        this.mAdapter = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.mObserver);
        }
        ListViewCompat $r5 = this.mDropDownList;
        if ($r5 != null) {
            $r5.setAdapter(this.mAdapter);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        this.mPopup.setBackgroundDrawable(drawable);
    }

    public void setContentWidth(int $i0) {
        Drawable $r1 = this.mPopup.getBackground();
        if ($r1 != null) {
            $r1.getPadding(this.mTempRect);
            Rect $r3 = this.mTempRect;
            this.mDropDownWidth = $r3.left + $r3.right + $i0;
            return;
        }
        setWidth($i0);
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.mPopup.setOnDismissListener(onDismissListener);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    public void setPromptPosition(int i) {
        this.mPromptPosition = i;
    }

    public void setSelection(int i) {
        ListViewCompat $r1 = this.mDropDownList;
        if (isShowing() && $r1 != null) {
            $r1.setListSelectionHidden(false);
            $r1.setSelection(i);
            if ($r1.getChoiceMode() != 0) {
                $r1.setItemChecked(i, true);
            }
        }
    }

    public void setVerticalOffset(int i) {
        this.mDropDownVerticalOffset = i;
        this.mDropDownVerticalOffsetSet = true;
    }

    public void setWidth(int i) {
        this.mDropDownWidth = i;
    }

    /* access modifiers changed from: package-private */
    public ListViewCompat show(Context context, boolean z) {
        return new ListViewCompat(context, z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0194, code lost:
        if (r0.isInTouchMode() != false) goto L_0x0196;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void show() {
        /*
            r27 = this;
            r0 = r27
            int r6 = r0.buildDropDown()
            r0 = r27
            boolean r7 = r0.isInputMethodNotNeeded()
            r0 = r27
            android.widget.PopupWindow r8 = r0.mPopup
            r0 = r27
            int r9 = r0.mDropDownWindowLayoutType
            android.support.v4.widget.PopupWindowCompat.setWindowLayoutType(r8, r9)
            r0 = r27
            android.widget.PopupWindow r8 = r0.mPopup
            boolean r10 = r8.isShowing()
            r11 = 1
            if (r10 == 0) goto L_0x00c7
            r0 = r27
            android.view.View r12 = r0.getAnchorView()
            boolean r10 = com.org.android.view.ViewCompat.isAttachedToWindow(r12)
            if (r10 != 0) goto L_0x002f
            return
        L_0x002f:
            r0 = r27
            int r9 = r0.mDropDownWidth
            r13 = -1
            if (r9 != r13) goto L_0x0038
            r9 = -1
            goto L_0x0045
        L_0x0038:
            r13 = -2
            if (r9 != r13) goto L_0x0045
            r0 = r27
            android.view.View r12 = r0.getAnchorView()
            int r9 = r12.getWidth()
        L_0x0045:
            r0 = r27
            int r14 = r0.mDropDownHeight
            r13 = -1
            if (r14 != r13) goto L_0x0086
            if (r7 == 0) goto L_0x004f
            goto L_0x0050
        L_0x004f:
            r6 = -1
        L_0x0050:
            if (r7 == 0) goto L_0x006c
            r0 = r27
            android.widget.PopupWindow r8 = r0.mPopup
            r0 = r27
            int r14 = r0.mDropDownWidth
            r13 = -1
            if (r14 != r13) goto L_0x005f
            r15 = -1
            goto L_0x0060
        L_0x005f:
            r15 = 0
        L_0x0060:
            r8.setWidth(r15)
            r0 = r27
            android.widget.PopupWindow r8 = r0.mPopup
            r13 = 0
            r8.setHeight(r13)
            goto L_0x008b
        L_0x006c:
            r0 = r27
            android.widget.PopupWindow r8 = r0.mPopup
            r0 = r27
            int r14 = r0.mDropDownWidth
            r13 = -1
            if (r14 != r13) goto L_0x0079
            r15 = -1
            goto L_0x007a
        L_0x0079:
            r15 = 0
        L_0x007a:
            r8.setWidth(r15)
            r0 = r27
            android.widget.PopupWindow r8 = r0.mPopup
            r13 = -1
            r8.setHeight(r13)
            goto L_0x008b
        L_0x0086:
            r13 = -2
            if (r14 != r13) goto L_0x008a
            goto L_0x008b
        L_0x008a:
            r6 = r14
        L_0x008b:
            r0 = r27
            android.widget.PopupWindow r8 = r0.mPopup
            r0 = r27
            boolean r7 = r0.mForceIgnoreOutsideTouch
            if (r7 != 0) goto L_0x009c
            r0 = r27
            boolean r7 = r0.mDropDownAlwaysVisible
            if (r7 != 0) goto L_0x009c
            goto L_0x009d
        L_0x009c:
            r11 = 0
        L_0x009d:
            r8.setOutsideTouchable(r11)
            r0 = r27
            android.widget.PopupWindow r8 = r0.mPopup
            r0 = r27
            android.view.View r12 = r0.getAnchorView()
            r0 = r27
            int r14 = r0.mDropDownHorizontalOffset
            r0 = r27
            int r0 = r0.mDropDownVerticalOffset
            r16 = r0
            if (r9 >= 0) goto L_0x00b8
            r9 = -1
            goto L_0x00b8
        L_0x00b8:
            if (r6 >= 0) goto L_0x00bc
            r6 = -1
            goto L_0x00bc
        L_0x00bc:
            r0 = r8
            r1 = r12
            r2 = r14
            r3 = r16
            r4 = r9
            r5 = r6
            r0.update(r1, r2, r3, r4, r5)
            return
        L_0x00c7:
            r0 = r27
            int r9 = r0.mDropDownWidth
            r13 = -1
            if (r9 != r13) goto L_0x00d0
            r9 = -1
            goto L_0x00dd
        L_0x00d0:
            r13 = -2
            if (r9 != r13) goto L_0x00dd
            r0 = r27
            android.view.View r12 = r0.getAnchorView()
            int r9 = r12.getWidth()
        L_0x00dd:
            r0 = r27
            int r14 = r0.mDropDownHeight
            r13 = -1
            if (r14 != r13) goto L_0x00e6
            r6 = -1
            goto L_0x00eb
        L_0x00e6:
            r13 = -2
            if (r14 != r13) goto L_0x00ea
            goto L_0x00eb
        L_0x00ea:
            r6 = r14
        L_0x00eb:
            r0 = r27
            android.widget.PopupWindow r8 = r0.mPopup
            r8.setWidth(r9)
            r0 = r27
            android.widget.PopupWindow r8 = r0.mPopup
            r8.setHeight(r6)
            r13 = 1
            r0 = r27
            r0.setPopupClipToScreenEnabled(r13)
            r0 = r27
            android.widget.PopupWindow r8 = r0.mPopup
            r0 = r27
            boolean r11 = r0.mForceIgnoreOutsideTouch
            if (r11 != 0) goto L_0x0111
            r0 = r27
            boolean r11 = r0.mDropDownAlwaysVisible
            if (r11 != 0) goto L_0x0111
            r11 = 1
            goto L_0x0112
        L_0x0111:
            r11 = 0
        L_0x0112:
            r8.setOutsideTouchable(r11)
            r0 = r27
            android.widget.PopupWindow r8 = r0.mPopup
            r0 = r27
            android.support.v7.widget.ListPopupWindow$PopupTouchInterceptor r0 = r0.mTouchInterceptor
            r17 = r0
            r8.setTouchInterceptor(r0)
            r0 = r27
            boolean r11 = r0.mPostedShow
            if (r11 == 0) goto L_0x0133
            r0 = r27
            android.widget.PopupWindow r8 = r0.mPopup
            r0 = r27
            boolean r11 = r0.x
            android.support.v4.widget.PopupWindowCompat.init(r8, r11)
        L_0x0133:
            java.lang.reflect.Method r18 = b
            if (r18 == 0) goto L_0x015f
            r0 = r27
            android.widget.PopupWindow r8 = r0.mPopup
            r13 = 1
            java.lang.Object[] r0 = new java.lang.Object[r13]
            r19 = r0
            r0 = r27
            android.graphics.Rect r0 = r0.mTempAdapter
            r20 = r0
            r13 = 0
            r19[r13] = r20
            r0 = r18
            r1 = r19
            r0.invoke(r8, r1)     // Catch:{ Exception -> 0x0151 }
            goto L_0x015f
        L_0x0151:
            r21 = move-exception
            java.lang.String r22 = "ListPopupWindow"
            java.lang.String r23 = "Could not invoke setEpicenterBounds on PopupWindow"
            r0 = r22
            r1 = r23
            r2 = r21
            android.util.Log.e(r0, r1, r2)
        L_0x015f:
            r0 = r27
            android.widget.PopupWindow r8 = r0.mPopup
            r0 = r27
            android.view.View r12 = r0.getAnchorView()
            r0 = r27
            int r6 = r0.mDropDownHorizontalOffset
            r0 = r27
            int r9 = r0.mDropDownVerticalOffset
            r0 = r27
            int r14 = r0.mDropDownGravity
            android.support.v4.widget.PopupWindowCompat.update(r8, r12, r6, r9, r14)
            r0 = r27
            android.support.v7.widget.ListViewCompat r0 = r0.mDropDownList
            r24 = r0
            r13 = -1
            r0 = r24
            r0.setSelection(r13)
            r0 = r27
            boolean r11 = r0.mModal
            if (r11 == 0) goto L_0x0196
            r0 = r27
            android.support.v7.widget.ListViewCompat r0 = r0.mDropDownList
            r24 = r0
            boolean r11 = r0.isInTouchMode()
            if (r11 == 0) goto L_0x019b
        L_0x0196:
            r0 = r27
            r0.clearListSelection()
        L_0x019b:
            r0 = r27
            boolean r11 = r0.mModal
            if (r11 != 0) goto L_0x01b4
            r0 = r27
            android.os.Handler r0 = r0.mHandler
            r25 = r0
            r0 = r27
            android.support.v7.widget.ListPopupWindow$ListSelectorHider r0 = r0.mHideSelector
            r26 = r0
            r0 = r25
            r1 = r26
            r0.post(r1)
        L_0x01b4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ListPopupWindow.show():void");
    }

    public void show(int i) {
        this.mPopup.setInputMethodMode(i);
    }

    public void show(boolean z) {
        this.mPostedShow = true;
        this.x = z;
    }
}

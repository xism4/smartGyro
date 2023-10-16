package android.support.v7.app;

import android.app.Activity;
import android.app.UiModeManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.view.menu.ListMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.l$a;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.DecorContentParent;
import android.support.v7.widget.TintManager;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.ViewUtils;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
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
import android.widget.PopupWindow;
import android.widget.TextView;
import com.org.android.view.LayoutInflaterCompatHC;
import com.org.android.view.ViewCompat;
import com.org.android.view.ViewPropertyAnimatorCompat;
import com.org.android.view.ViewPropertyAnimatorListener;
import com.org.android.view.ViewPropertyAnimatorListenerAdapter;
import com.org.android.view.i;
import com.org.android.view.x;
import com.org.v4.text.view.Resources;
import com.org.v4.util.R$attr;
import com.org.v4.util.R$color;
import com.org.v4.util.R$layout;
import com.org.v4.util.R$style;
import com.org.v4.util.R$styleable;
import com.org.v4.view.ActionMode;
import com.org.v4.view.ContextThemeWrapper;
import org.cocos2dx.package_1.GameControllerDelegate;
import org.xmlpull.v1.XmlPullParser;

class AppCompatDelegateImplV7 extends AppCompatDelegate implements MenuBuilder.Callback, LayoutInflater.Factory2 {
    private static final int[] a = {16842836};
    private static boolean o = true;
    private static final boolean s = (Build.VERSION.SDK_INT < 21);
    private int l = -100;
    ActionBar mActionBar;
    private ActionMenuPresenterCallback mActionMenuPresenterCallback;
    ActionMode mActionMode;
    PopupWindow mActionModePopup;
    ActionBarContextView mActionModeView;
    final AppCompatCallback mAppCompatCallback;
    private AppCompatViewInflater mAppCompatViewInflater;
    final Window.Callback mAppCompatWindowCallback;
    private boolean mClosingActionMenu;
    final Context mContext;
    private DecorContentParent mDecorContentParent;
    private boolean mEnableDefaultActionBarUp;
    ViewPropertyAnimatorCompat mFadeAnim = null;
    private boolean mFeatureIndeterminateProgress;
    private boolean mFeatureProgress;
    private boolean mHandleNativeActionModes = true;
    boolean mHasActionBar;
    int mInvalidatePanelMenuFeatures;
    boolean mInvalidatePanelMenuPosted;
    private final Runnable mInvalidatePanelMenuRunnable = new MonthByWeekFragment$2(this);
    boolean mIsDestroyed;
    boolean mIsFloating;
    private boolean mLongPressBackDown;
    final Window.Callback mOriginalWindowCallback;
    boolean mOverlayActionBar;
    boolean mOverlayActionMode;
    private PanelMenuPresenterCallback mPanelMenuPresenterCallback;
    private PanelFeatureState[] mPanels;
    private PanelFeatureState mPreparedPanel;
    Runnable mShowActionModePopup;
    private View mStatusGuard;
    private ViewGroup mSubDecor;
    private boolean mSubDecorInstalled;
    private Rect mTempRect1;
    private Rect mTempRect2;
    private CharSequence mTitle;
    private TextView mTitleView;
    final Window mWindow;
    boolean mWindowNoTitle;
    private boolean r;
    private ClassWriter v;

    final class ActionMenuPresenterCallback implements l$a {
        ActionMenuPresenterCallback() {
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            AppCompatDelegateImplV7.this.checkCloseActionMenu(menuBuilder);
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            Window.Callback $r1 = AppCompatDelegateImplV7.this.getWindowCallback();
            if ($r1 == null) {
                return true;
            }
            $r1.onMenuOpened(108, menuBuilder);
            return true;
        }
    }

    class ActionModeCallbackWrapperV7 implements ActionMode.Callback {
        private ActionMode.Callback mWrapped;

        public ActionModeCallbackWrapperV7(ActionMode.Callback callback) {
            this.mWrapped = callback;
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.mWrapped.onActionItemClicked(actionMode, menuItem);
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.mWrapped.onCreateActionMode(actionMode, menu);
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            this.mWrapped.onDestroyActionMode(actionMode);
            AppCompatDelegateImplV7 $r3 = AppCompatDelegateImplV7.this;
            if ($r3.mActionModePopup != null) {
                $r3.mWindow.getDecorView().removeCallbacks(AppCompatDelegateImplV7.this.mShowActionModePopup);
            }
            AppCompatDelegateImplV7 $r32 = AppCompatDelegateImplV7.this;
            if ($r32.mActionModeView != null) {
                $r32.endOnGoingFadeAnimation();
                AppCompatDelegateImplV7 $r33 = AppCompatDelegateImplV7.this;
                ViewPropertyAnimatorCompat $r9 = ViewCompat.animate($r33.mActionModeView);
                $r9.alpha(0.0f);
                $r33.mFadeAnim = $r9;
                AppCompatDelegateImplV7.this.mFadeAnim.setListener(new ViewPropertyAnimatorListenerAdapter() {
                    public void onAnimationEnd(View view) {
                        AppCompatDelegateImplV7.this.mActionModeView.setVisibility(8);
                        AppCompatDelegateImplV7 $r3 = AppCompatDelegateImplV7.this;
                        PopupWindow $r5 = $r3.mActionModePopup;
                        if ($r5 != null) {
                            $r5.dismiss();
                        } else if ($r3.mActionModeView.getParent() instanceof View) {
                            ViewCompat.requestApplyInsets((View) AppCompatDelegateImplV7.this.mActionModeView.getParent());
                        }
                        AppCompatDelegateImplV7.this.mActionModeView.removeAllViews();
                        AppCompatDelegateImplV7.this.mFadeAnim.setListener((ViewPropertyAnimatorListener) null);
                        AppCompatDelegateImplV7.this.mFadeAnim = null;
                    }
                });
            }
            AppCompatDelegateImplV7 $r34 = AppCompatDelegateImplV7.this;
            AppCompatCallback $r11 = $r34.mAppCompatCallback;
            if ($r11 != null) {
                $r11.onSupportActionModeFinished($r34.mActionMode);
            }
            AppCompatDelegateImplV7.this.mActionMode = null;
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return this.mWrapped.onPrepareActionMode(actionMode, menu);
        }
    }

    class ListMenuDecorView extends ContentFrameLayout {
        public ListMenuDecorView(Context context) {
            super(context);
        }

        private boolean isOutOfBounds(int i, int i2) {
            return i < -5 || i2 < -5 || i > getWidth() + 5 || i2 > getHeight() + 5;
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImplV7.this.dispatchKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0 || !isOutOfBounds((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            AppCompatDelegateImplV7.this.closePanel(0);
            return true;
        }

        public void setBackgroundResource(int i) {
            setBackgroundDrawable(Resources.getDrawable(getContext(), i));
        }
    }

    public final class PanelFeatureState {
        int background;
        View createdPanelView;
        ViewGroup decorView;
        int featureId;
        Bundle frozenActionViewState;
        int gravity;
        boolean isHandled;
        boolean isOpen;
        boolean isPrepared;
        ListMenuPresenter listMenuPresenter;
        Context listPresenterContext;
        MenuBuilder menu;
        public boolean qwertyMode;
        boolean refreshDecorView = false;
        boolean refreshMenuContent;
        View shownPanelView;
        int windowAnimations;
        int x;
        int y;

        PanelFeatureState(int i) {
            this.featureId = i;
        }

        /* access modifiers changed from: package-private */
        public MenuView getListMenuView(l$a l_a) {
            if (this.menu == null) {
                return null;
            }
            if (this.listMenuPresenter == null) {
                this.listMenuPresenter = new ListMenuPresenter(this.listPresenterContext, R$layout.abc_list_menu_item_layout);
                this.listMenuPresenter.a(l_a);
                this.menu.addMenuPresenter(this.listMenuPresenter);
            }
            return this.listMenuPresenter.getMenuView(this.decorView);
        }

        public boolean hasPanelItems() {
            if (this.shownPanelView == null) {
                return false;
            }
            return this.createdPanelView != null || this.listMenuPresenter.getAdapter().getCount() > 0;
        }

        /* access modifiers changed from: package-private */
        public void setMenu(MenuBuilder menuBuilder) {
            ListMenuPresenter $r2;
            MenuBuilder $r3 = this.menu;
            if (menuBuilder != $r3) {
                if ($r3 != null) {
                    $r3.removeMenuPresenter(this.listMenuPresenter);
                }
                this.menu = menuBuilder;
                if (menuBuilder != null && ($r2 = this.listMenuPresenter) != null) {
                    menuBuilder.addMenuPresenter($r2);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void setStyle(Context context) {
            TypedValue $r2 = new TypedValue();
            Resources.Theme $r4 = context.getResources().newTheme();
            $r4.setTo(context.getTheme());
            $r4.resolveAttribute(R$attr.actionBarPopupTheme, $r2, true);
            int $i0 = $r2.resourceId;
            if ($i0 != 0) {
                $r4.applyStyle($i0, true);
            }
            $r4.resolveAttribute(R$attr.panelMenuListTheme, $r2, true);
            int $i02 = $r2.resourceId;
            if ($i02 == 0) {
                $i02 = R$style.Theme_AppCompat_CompactMenu;
            }
            $r4.applyStyle($i02, true);
            ContextThemeWrapper $r6 = new ContextThemeWrapper(context, 0);
            $r6.getTheme().setTo($r4);
            this.listPresenterContext = $r6;
            TypedArray $r8 = $r6.obtainStyledAttributes(R$styleable.AppCompatTheme);
            this.background = $r8.getResourceId(R$styleable.AppCompatTheme_panelBackground, 0);
            this.windowAnimations = $r8.getResourceId(R$styleable.AppCompatTheme_android_windowAnimationStyle, 0);
            $r8.recycle();
        }
    }

    final class PanelMenuPresenterCallback implements l$a {
        PanelMenuPresenterCallback() {
        }

        public void onCloseMenu(MenuBuilder $r2, boolean z) {
            MenuBuilder $r3 = $r2.getRootMenu();
            boolean $z1 = $r3 != $r2;
            AppCompatDelegateImplV7 $r1 = AppCompatDelegateImplV7.this;
            if ($z1) {
                $r2 = $r3;
            }
            PanelFeatureState $r4 = $r1.findMenuPanel($r2);
            if ($r4 == null) {
                return;
            }
            if ($z1) {
                AppCompatDelegateImplV7.this.callOnPanelClosed($r4.featureId, $r4, $r3);
                AppCompatDelegateImplV7.this.closePanel($r4, true);
                return;
            }
            AppCompatDelegateImplV7.this.closePanel($r4, z);
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            Window.Callback $r1;
            if (menuBuilder != null) {
                return true;
            }
            AppCompatDelegateImplV7 $r3 = AppCompatDelegateImplV7.this;
            if (!$r3.mHasActionBar || ($r1 = $r3.getWindowCallback()) == null || AppCompatDelegateImplV7.this.mIsDestroyed) {
                return true;
            }
            $r1.onMenuOpened(108, menuBuilder);
            return true;
        }
    }

    static {
        if (s && !o) {
            Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(Thread.getDefaultUncaughtExceptionHandler()));
        }
    }

    AppCompatDelegateImplV7(Context context, Window window, AppCompatCallback appCompatCallback) {
        this.mContext = context;
        this.mWindow = window;
        this.mAppCompatCallback = appCompatCallback;
        this.mOriginalWindowCallback = this.mWindow.getCallback();
        Window.Callback $r5 = this.mOriginalWindowCallback;
        if (!($r5 instanceof WindowCallbackWrapper)) {
            this.mAppCompatWindowCallback = new WindowCallbackWrapper(this, $r5);
            this.mWindow.setCallback(this.mAppCompatWindowCallback);
            TintTypedArray $r8 = TintTypedArray.obtainStyledAttributes(context, (AttributeSet) null, a);
            Drawable $r9 = $r8.getDrawableIfKnown(0);
            if ($r9 != null) {
                this.mWindow.setBackgroundDrawable($r9);
            }
            $r8.recycle();
            return;
        }
        throw new IllegalStateException("AppCompat has already installed itself into the Window");
    }

    private void applyFixedSizeWindow() {
        ContentFrameLayout $r3 = (ContentFrameLayout) this.mSubDecor.findViewById(16908290);
        View $r2 = this.mWindow.getDecorView();
        $r3.setDecorPadding($r2.getPaddingLeft(), $r2.getPaddingTop(), $r2.getPaddingRight(), $r2.getPaddingBottom());
        TypedArray $r7 = this.mContext.obtainStyledAttributes(R$styleable.AppCompatTheme);
        $r7.getValue(R$styleable.AppCompatTheme_windowMinWidthMajor, $r3.getMinWidthMajor());
        $r7.getValue(R$styleable.AppCompatTheme_windowMinWidthMinor, $r3.getMinWidthMinor());
        if ($r7.hasValue(R$styleable.AppCompatTheme_windowFixedWidthMajor)) {
            $r7.getValue(R$styleable.AppCompatTheme_windowFixedWidthMajor, $r3.getFixedWidthMajor());
        }
        if ($r7.hasValue(R$styleable.AppCompatTheme_windowFixedWidthMinor)) {
            $r7.getValue(R$styleable.AppCompatTheme_windowFixedWidthMinor, $r3.getFixedWidthMinor());
        }
        if ($r7.hasValue(R$styleable.AppCompatTheme_windowFixedHeightMajor)) {
            $r7.getValue(R$styleable.AppCompatTheme_windowFixedHeightMajor, $r3.getFixedHeightMajor());
        }
        if ($r7.hasValue(R$styleable.AppCompatTheme_windowFixedHeightMinor)) {
            $r7.getValue(R$styleable.AppCompatTheme_windowFixedHeightMinor, $r3.getFixedHeightMinor());
        }
        $r7.recycle();
        $r3.requestLayout();
    }

    private void b() {
        if (this.v == null) {
            this.v = new ClassWriter(this, TwilightManager.getLastKnownLocation(this.mContext));
        }
    }

    private boolean connect() {
        if (!this.r) {
            return false;
        }
        Context $r1 = this.mContext;
        if (!($r1 instanceof Activity)) {
            return false;
        }
        try {
            return ($r1.getPackageManager().getActivityInfo(new ComponentName(this.mContext, this.mContext.getClass()), 0).configChanges & 512) == 0;
        } catch (PackageManager.NameNotFoundException $r7) {
            Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", $r7);
            return true;
        }
    }

    /* JADX WARNING: type inference failed for: r10v4, types: [android.view.View] */
    /* JADX WARNING: type inference failed for: r10v5, types: [android.view.View] */
    /* JADX WARNING: type inference failed for: r10v7, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.view.ViewGroup createSubDecor() {
        /*
            r43 = this;
            r0 = r43
            android.content.Context r2 = r0.mContext
            int[] r3 = com.org.v4.util.R$styleable.AppCompatTheme
            android.content.res.TypedArray r4 = r2.obtainStyledAttributes(r3)
            int r5 = com.org.v4.util.R$styleable.AppCompatTheme_windowActionBar
            boolean r6 = r4.hasValue(r5)
            if (r6 == 0) goto L_0x0287
            int r5 = com.org.v4.util.R$styleable.AppCompatTheme_windowNoTitle
            r7 = 0
            boolean r6 = r4.getBoolean(r5, r7)
            if (r6 == 0) goto L_0x0022
            r7 = 1
            r0 = r43
            r0.requestWindowFeature(r7)
            goto L_0x0032
        L_0x0022:
            int r5 = com.org.v4.util.R$styleable.AppCompatTheme_windowActionBar
            r7 = 0
            boolean r6 = r4.getBoolean(r5, r7)
            if (r6 == 0) goto L_0x0032
            r7 = 108(0x6c, float:1.51E-43)
            r0 = r43
            r0.requestWindowFeature(r7)
        L_0x0032:
            int r5 = com.org.v4.util.R$styleable.AppCompatTheme_windowActionBarOverlay
            r7 = 0
            boolean r6 = r4.getBoolean(r5, r7)
            if (r6 == 0) goto L_0x0042
            r7 = 109(0x6d, float:1.53E-43)
            r0 = r43
            r0.requestWindowFeature(r7)
        L_0x0042:
            int r5 = com.org.v4.util.R$styleable.AppCompatTheme_windowActionModeOverlay
            r7 = 0
            boolean r6 = r4.getBoolean(r5, r7)
            if (r6 == 0) goto L_0x0052
            r7 = 10
            r0 = r43
            r0.requestWindowFeature(r7)
        L_0x0052:
            int r5 = com.org.v4.util.R$styleable.AppCompatTheme_android_windowIsFloating
            r7 = 0
            boolean r6 = r4.getBoolean(r5, r7)
            r0 = r43
            r0.mIsFloating = r6
            r4.recycle()
            r0 = r43
            android.view.Window r8 = r0.mWindow
            r8.getDecorView()
            r0 = r43
            android.content.Context r2 = r0.mContext
            android.view.LayoutInflater r9 = android.view.LayoutInflater.from(r2)
            r0 = r43
            boolean r6 = r0.mWindowNoTitle
            if (r6 != 0) goto L_0x0139
            r0 = r43
            boolean r6 = r0.mIsFloating
            if (r6 == 0) goto L_0x0093
            int r5 = com.org.v4.util.R$layout.abc_dialog_title_material
            r11 = 0
            android.view.View r10 = r9.inflate(r5, r11)
            r13 = r10
            android.view.ViewGroup r13 = (android.view.ViewGroup) r13
            r12 = r13
            r7 = 0
            r0 = r43
            r0.mOverlayActionBar = r7
            r7 = 0
            r0 = r43
            r0.mHasActionBar = r7
            goto L_0x017a
        L_0x0093:
            r0 = r43
            boolean r6 = r0.mHasActionBar
            if (r6 == 0) goto L_0x0137
            android.util.TypedValue r14 = new android.util.TypedValue
            r15 = r14
            r14.<init>()
            r0 = r43
            android.content.Context r2 = r0.mContext
            android.content.res.Resources$Theme r16 = r2.getTheme()
            int r5 = com.org.v4.util.R$attr.actionBarTheme
            r7 = 1
            r0 = r16
            r0.resolveAttribute(r5, r15, r7)
            int r5 = r15.resourceId
            if (r5 == 0) goto L_0x00c5
            com.org.v4.view.ContextThemeWrapper r17 = new com.org.v4.view.ContextThemeWrapper
            r2 = r17
            r0 = r43
            android.content.Context r0 = r0.mContext
            r18 = r0
            r0 = r17
            r1 = r18
            r0.<init>((android.content.Context) r1, (int) r5)
            goto L_0x00c9
        L_0x00c5:
            r0 = r43
            android.content.Context r2 = r0.mContext
        L_0x00c9:
            android.view.LayoutInflater r9 = android.view.LayoutInflater.from(r2)
            int r5 = com.org.v4.util.R$layout.abc_screen_toolbar
            r11 = 0
            android.view.View r10 = r9.inflate(r5, r11)
            r19 = r10
            android.view.ViewGroup r19 = (android.view.ViewGroup) r19
            r12 = r19
            int r5 = com.org.v4.util.R$id.decor_content_parent
            android.view.View r10 = r12.findViewById(r5)
            r21 = r10
            android.support.v7.widget.DecorContentParent r21 = (android.support.v7.widget.DecorContentParent) r21
            r20 = r21
            r0 = r20
            r1 = r43
            r1.mDecorContentParent = r0
            r0 = r43
            android.support.v7.widget.DecorContentParent r0 = r0.mDecorContentParent
            r20 = r0
            r0 = r43
            android.view.Window$Callback r22 = r0.getWindowCallback()
            r0 = r20
            r1 = r22
            r0.setWindowCallback(r1)
            r0 = r43
            boolean r6 = r0.mOverlayActionBar
            if (r6 == 0) goto L_0x0112
            r0 = r43
            android.support.v7.widget.DecorContentParent r0 = r0.mDecorContentParent
            r20 = r0
            r7 = 109(0x6d, float:1.53E-43)
            r0 = r20
            r0.initFeature(r7)
        L_0x0112:
            r0 = r43
            boolean r6 = r0.mFeatureIndeterminateProgress
            if (r6 == 0) goto L_0x0124
            r0 = r43
            android.support.v7.widget.DecorContentParent r0 = r0.mDecorContentParent
            r20 = r0
            r7 = 2
            r0 = r20
            r0.initFeature(r7)
        L_0x0124:
            r0 = r43
            boolean r6 = r0.mFeatureProgress
            if (r6 == 0) goto L_0x017a
            r0 = r43
            android.support.v7.widget.DecorContentParent r0 = r0.mDecorContentParent
            r20 = r0
            r7 = 5
            r0 = r20
            r0.initFeature(r7)
            goto L_0x017a
        L_0x0137:
            r12 = 0
            goto L_0x017a
        L_0x0139:
            r0 = r43
            boolean r6 = r0.mOverlayActionMode
            if (r6 == 0) goto L_0x0142
            int r5 = com.org.v4.util.R$layout.abc_screen_simple_overlay_action_mode
            goto L_0x0144
        L_0x0142:
            int r5 = com.org.v4.util.R$layout.abc_screen_simple
        L_0x0144:
            r11 = 0
            android.view.View r10 = r9.inflate(r5, r11)
            r23 = r10
            android.view.ViewGroup r23 = (android.view.ViewGroup) r23
            r12 = r23
            int r5 = android.os.Build.VERSION.SDK_INT
            r7 = 21
            if (r5 < r7) goto L_0x0164
            android.support.v7.app.x r24 = new android.support.v7.app.x
            r0 = r24
            r1 = r43
            r0.<init>(r1)
            r0 = r24
            com.org.android.view.ViewCompat.setOnApplyWindowInsetsListener(r12, r0)
            goto L_0x017a
        L_0x0164:
            r26 = r12
            android.support.v7.widget.FitWindowsViewGroup r26 = (android.support.v7.widget.FitWindowsViewGroup) r26
            r25 = r26
            android.support.v7.app.AppCompatDelegateImplV7$3 r27 = new android.support.v7.app.AppCompatDelegateImplV7$3
            r0 = r27
            r1 = r43
            r0.<init>()
            r0 = r25
            r1 = r27
            r0.setOnFitSystemWindowsListener(r1)
        L_0x017a:
            if (r12 == 0) goto L_0x020b
            r0 = r43
            android.support.v7.widget.DecorContentParent r0 = r0.mDecorContentParent
            r20 = r0
            if (r20 != 0) goto L_0x0196
            int r5 = com.org.v4.util.R$id.title
            android.view.View r10 = r12.findViewById(r5)
            r29 = r10
            android.widget.TextView r29 = (android.widget.TextView) r29
            r28 = r29
            r0 = r28
            r1 = r43
            r1.mTitleView = r0
        L_0x0196:
            android.support.v7.widget.ViewUtils.makeOptionalFitsSystemWindows(r12)
            int r5 = com.org.v4.util.R$id.action_bar_activity_content
            android.view.View r10 = r12.findViewById(r5)
            r31 = r10
            android.support.v7.widget.ContentFrameLayout r31 = (android.support.v7.widget.ContentFrameLayout) r31
            r30 = r31
            r0 = r43
            android.view.Window r8 = r0.mWindow
            r7 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r10 = r8.findViewById(r7)
            r33 = r10
            android.view.ViewGroup r33 = (android.view.ViewGroup) r33
            r32 = r33
            if (r32 == 0) goto L_0x01f3
        L_0x01b8:
            r0 = r32
            int r5 = r0.getChildCount()
            if (r5 <= 0) goto L_0x01d3
            r7 = 0
            r0 = r32
            android.view.View r10 = r0.getChildAt(r7)
            r7 = 0
            r0 = r32
            r0.removeViewAt(r7)
            r0 = r30
            r0.addView(r10)
            goto L_0x01b8
        L_0x01d3:
            r7 = -1
            r0 = r32
            r0.setId(r7)
            r7 = 16908290(0x1020002, float:2.3877235E-38)
            r0 = r30
            r0.setId(r7)
            r0 = r32
            boolean r6 = r0 instanceof android.widget.FrameLayout
            if (r6 == 0) goto L_0x01f3
            r35 = r32
            android.widget.FrameLayout r35 = (android.widget.FrameLayout) r35
            r34 = r35
            r11 = 0
            r0 = r34
            r0.setForeground(r11)
        L_0x01f3:
            r0 = r43
            android.view.Window r8 = r0.mWindow
            r8.setContentView(r12)
            android.support.v7.app.AppCompatDelegateImplV7$4 r36 = new android.support.v7.app.AppCompatDelegateImplV7$4
            r0 = r36
            r1 = r43
            r0.<init>()
            r0 = r30
            r1 = r36
            r0.setAttachListener(r1)
            return r12
        L_0x020b:
            java.lang.IllegalArgumentException r37 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r38 = new java.lang.StringBuilder
            r39 = r38
            r0 = r38
            r0.<init>()
            java.lang.String r40 = "AppCompat does not support the current theme features: { windowActionBar: "
            r0 = r39
            r1 = r40
            r0.append(r1)
            r0 = r43
            boolean r6 = r0.mHasActionBar
            r0 = r39
            r0.append(r6)
            java.lang.String r40 = ", windowActionBarOverlay: "
            r0 = r39
            r1 = r40
            r0.append(r1)
            r0 = r43
            boolean r6 = r0.mOverlayActionBar
            r0 = r39
            r0.append(r6)
            java.lang.String r40 = ", android:windowIsFloating: "
            r0 = r39
            r1 = r40
            r0.append(r1)
            r0 = r43
            boolean r6 = r0.mIsFloating
            r0 = r39
            r0.append(r6)
            java.lang.String r40 = ", windowActionModeOverlay: "
            r0 = r39
            r1 = r40
            r0.append(r1)
            r0 = r43
            boolean r6 = r0.mOverlayActionMode
            r0 = r39
            r0.append(r6)
            java.lang.String r40 = ", windowNoTitle: "
            r0 = r39
            r1 = r40
            r0.append(r1)
            r0 = r43
            boolean r6 = r0.mWindowNoTitle
            r0 = r39
            r0.append(r6)
            java.lang.String r40 = " }"
            r0 = r39
            r1 = r40
            r0.append(r1)
            r0 = r39
            java.lang.String r41 = r0.toString()
            r0 = r37
            r1 = r41
            r0.<init>(r1)
            throw r37
        L_0x0287:
            r4.recycle()
            java.lang.IllegalStateException r42 = new java.lang.IllegalStateException
            java.lang.String r40 = "You need to use a Theme.AppCompat theme (or descendant) with this activity."
            r0 = r42
            r1 = r40
            r0.<init>(r1)
            goto L_0x0296
        L_0x0296:
            throw r42
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatDelegateImplV7.createSubDecor():android.view.ViewGroup");
    }

    private int e() {
        int $i0 = this.l;
        return $i0 != -100 ? $i0 : AppCompatDelegate.getDefaultNightMode();
    }

    private void ensureSubDecor() {
        if (!this.mSubDecorInstalled) {
            this.mSubDecor = createSubDecor();
            CharSequence $r2 = getTitle();
            if (!TextUtils.isEmpty($r2)) {
                DecorContentParent $r3 = this.mDecorContentParent;
                if ($r3 != null) {
                    $r3.setWindowTitle($r2);
                } else if (peekSupportActionBar() != null) {
                    peekSupportActionBar().setWindowTitle($r2);
                } else {
                    TextView $r5 = this.mTitleView;
                    if ($r5 != null) {
                        $r5.setText($r2);
                    }
                }
            }
            applyFixedSizeWindow();
            onSubDecorInstalled(this.mSubDecor);
            this.mSubDecorInstalled = true;
            PanelFeatureState $r6 = getPanelState(0, false);
            if (this.mIsDestroyed) {
                return;
            }
            if ($r6 == null || $r6.menu == null) {
                invalidatePanelMenu(108);
            }
        }
    }

    private boolean init(int i) {
        android.content.res.Resources $r2 = this.mContext.getResources();
        Configuration $r3 = $r2.getConfiguration();
        byte $i1 = $r3.uiMode & 48;
        byte $b2 = i == 2 ? (byte) 32 : 16;
        if ($i1 == $b2) {
            return false;
        }
        if (connect()) {
            ((Activity) this.mContext).recreate();
            return true;
        }
        Configuration $r5 = new Configuration($r3);
        DisplayMetrics $r6 = $r2.getDisplayMetrics();
        $r5.uiMode = $b2 | ($r5.uiMode & -49);
        $r2.updateConfiguration($r5, $r6);
        if (Build.VERSION.SDK_INT >= 26) {
            return true;
        }
        Frame.initialize($r2);
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initWindowDecorActionBar() {
        /*
            r8 = this;
            r8.ensureSubDecor()
            boolean r0 = r8.mHasActionBar
            if (r0 == 0) goto L_0x0037
            android.support.v7.app.ActionBar r1 = r8.mActionBar
            if (r1 == 0) goto L_0x000c
            return
        L_0x000c:
            android.view.Window$Callback r2 = r8.mOriginalWindowCallback
            boolean r0 = r2 instanceof android.app.Activity
            if (r0 == 0) goto L_0x0020
            android.support.v7.app.WindowDecorActionBar r3 = new android.support.v7.app.WindowDecorActionBar
            r5 = r2
            android.app.Activity r5 = (android.app.Activity) r5
            r4 = r5
            boolean r0 = r8.mOverlayActionBar
            r3.<init>(r4, r0)
        L_0x001d:
            r8.mActionBar = r3
            goto L_0x002e
        L_0x0020:
            boolean r0 = r2 instanceof android.app.Dialog
            if (r0 == 0) goto L_0x002e
            android.support.v7.app.WindowDecorActionBar r3 = new android.support.v7.app.WindowDecorActionBar
            r7 = r2
            android.app.Dialog r7 = (android.app.Dialog) r7
            r6 = r7
            r3.<init>(r6)
            goto L_0x001d
        L_0x002e:
            android.support.v7.app.ActionBar r1 = r8.mActionBar
            if (r1 == 0) goto L_0x0037
            boolean r0 = r8.mEnableDefaultActionBarUp
            r1.setDefaultDisplayHomeAsUpEnabled(r0)
        L_0x0037:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatDelegateImplV7.initWindowDecorActionBar():void");
    }

    private boolean initializePanelContent(PanelFeatureState panelFeatureState) {
        View $r3 = panelFeatureState.createdPanelView;
        if ($r3 != null) {
            panelFeatureState.shownPanelView = $r3;
            return true;
        } else if (panelFeatureState.menu == null) {
            return false;
        } else {
            if (this.mPanelMenuPresenterCallback == null) {
                this.mPanelMenuPresenterCallback = new PanelMenuPresenterCallback();
            }
            panelFeatureState.shownPanelView = (View) panelFeatureState.getListMenuView(this.mPanelMenuPresenterCallback);
            return panelFeatureState.shownPanelView != null;
        }
    }

    private boolean initializePanelDecor(PanelFeatureState panelFeatureState) {
        panelFeatureState.setStyle(getActionBarThemedContext());
        panelFeatureState.decorView = new ListMenuDecorView(panelFeatureState.listPresenterContext);
        panelFeatureState.gravity = 81;
        return true;
    }

    private boolean initializePanelMenu(PanelFeatureState panelFeatureState) {
        Context $r2 = this.mContext;
        int $i0 = panelFeatureState.featureId;
        if (($i0 == 0 || $i0 == 108) && this.mDecorContentParent != null) {
            TypedValue $r4 = new TypedValue();
            Resources.Theme $r5 = $r2.getTheme();
            $r5.resolveAttribute(R$attr.actionBarTheme, $r4, true);
            Resources.Theme $r6 = null;
            if ($r4.resourceId != 0) {
                Resources.Theme $r8 = $r2.getResources().newTheme();
                $r6 = $r8;
                $r8.setTo($r5);
                $r8.applyStyle($r4.resourceId, true);
                $r8.resolveAttribute(R$attr.actionBarWidgetTheme, $r4, true);
            } else {
                $r5.resolveAttribute(R$attr.actionBarWidgetTheme, $r4, true);
            }
            if ($r4.resourceId != 0) {
                if ($r6 == null) {
                    Resources.Theme $r82 = $r2.getResources().newTheme();
                    $r6 = $r82;
                    $r82.setTo($r5);
                }
                $r6.applyStyle($r4.resourceId, true);
            }
            if ($r6 != null) {
                ContextThemeWrapper $r9 = new ContextThemeWrapper($r2, 0);
                $r9.getTheme().setTo($r6);
                $r2 = $r9;
            }
        }
        MenuBuilder $r10 = new MenuBuilder($r2);
        $r10.setCallback(this);
        panelFeatureState.setMenu($r10);
        return true;
    }

    private void invalidatePanelMenu(int i) {
        this.mInvalidatePanelMenuFeatures = (1 << i) | this.mInvalidatePanelMenuFeatures;
        if (!this.mInvalidatePanelMenuPosted) {
            ViewCompat.postOnAnimation(this.mWindow.getDecorView(), this.mInvalidatePanelMenuRunnable);
            this.mInvalidatePanelMenuPosted = true;
        }
    }

    private boolean onKeyDownPanel(int i, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() != 0) {
            return false;
        }
        PanelFeatureState $r2 = getPanelState(i, true);
        if (!$r2.isOpen) {
            return preparePanel($r2, keyEvent);
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x006e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean onKeyUpPanel(int r13, android.view.KeyEvent r14) {
        /*
            r12 = this;
            com.org.v4.view.ActionMode r0 = r12.mActionMode
            if (r0 == 0) goto L_0x0006
            r1 = 0
            return r1
        L_0x0006:
            r1 = 1
            android.support.v7.app.AppCompatDelegateImplV7$PanelFeatureState r2 = r12.getPanelState(r13, r1)
            if (r13 != 0) goto L_0x0043
            android.support.v7.widget.DecorContentParent r3 = r12.mDecorContentParent
            if (r3 == 0) goto L_0x0043
            boolean r4 = r3.canShowOverflowMenu()
            if (r4 == 0) goto L_0x0043
            android.content.Context r5 = r12.mContext
            android.view.ViewConfiguration r6 = android.view.ViewConfiguration.get(r5)
            boolean r4 = r6.hasPermanentMenuKey()
            if (r4 != 0) goto L_0x0043
            android.support.v7.widget.DecorContentParent r3 = r12.mDecorContentParent
            boolean r4 = r3.isOverflowMenuShowing()
            if (r4 != 0) goto L_0x003c
            boolean r4 = r12.mIsDestroyed
            if (r4 != 0) goto L_0x0064
            boolean r4 = r12.preparePanel(r2, r14)
            if (r4 == 0) goto L_0x0064
            android.support.v7.widget.DecorContentParent r3 = r12.mDecorContentParent
            boolean r4 = r3.showOverflowMenu()
            goto L_0x006c
        L_0x003c:
            android.support.v7.widget.DecorContentParent r3 = r12.mDecorContentParent
            boolean r4 = r3.hideOverflowMenu()
            goto L_0x006c
        L_0x0043:
            boolean r4 = r2.isOpen
            if (r4 != 0) goto L_0x0066
            boolean r4 = r2.isHandled
            if (r4 == 0) goto L_0x004c
            goto L_0x0066
        L_0x004c:
            boolean r4 = r2.isPrepared
            if (r4 == 0) goto L_0x0064
            boolean r4 = r2.refreshMenuContent
            if (r4 == 0) goto L_0x005c
            r1 = 0
            r2.isPrepared = r1
            boolean r4 = r12.preparePanel(r2, r14)
            goto L_0x005d
        L_0x005c:
            r4 = 1
        L_0x005d:
            if (r4 == 0) goto L_0x0064
            r12.openPanel(r2, r14)
            r4 = 1
            goto L_0x006c
        L_0x0064:
            r4 = 0
            goto L_0x006c
        L_0x0066:
            boolean r4 = r2.isOpen
            r1 = 1
            r12.closePanel(r2, r1)
        L_0x006c:
            if (r4 == 0) goto L_0x0088
            android.content.Context r5 = r12.mContext
            java.lang.String r8 = "audio"
            java.lang.Object r7 = r5.getSystemService(r8)
            r10 = r7
            android.media.AudioManager r10 = (android.media.AudioManager) r10
            r9 = r10
            if (r9 == 0) goto L_0x0081
            r1 = 0
            r9.playSoundEffect(r1)
            return r4
        L_0x0081:
            java.lang.String r8 = "AppCompatDelegate"
            java.lang.String r11 = "Couldn't get audio manager"
            android.util.Log.w(r8, r11)
        L_0x0088:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatDelegateImplV7.onKeyUpPanel(int, android.view.KeyEvent):boolean");
    }

    private void openPanel(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        int i;
        ViewGroup.LayoutParams $r12;
        if (!panelFeatureState.isOpen && !this.mIsDestroyed) {
            if (panelFeatureState.featureId == 0) {
                if ((this.mContext.getResources().getConfiguration().screenLayout & 15) == 4) {
                    return;
                }
            }
            Window.Callback $r6 = getWindowCallback();
            if ($r6 == null || $r6.onMenuOpened(panelFeatureState.featureId, panelFeatureState.menu)) {
                WindowManager windowManager = (WindowManager) this.mContext.getSystemService("window");
                if (windowManager != null && preparePanel(panelFeatureState, keyEvent)) {
                    if (panelFeatureState.decorView == null || panelFeatureState.refreshDecorView) {
                        ViewGroup $r10 = panelFeatureState.decorView;
                        if ($r10 == null) {
                            if (!initializePanelDecor(panelFeatureState) || panelFeatureState.decorView == null) {
                                return;
                            }
                        } else if (panelFeatureState.refreshDecorView && $r10.getChildCount() > 0) {
                            ViewGroup $r102 = panelFeatureState.decorView;
                            ViewGroup viewGroup = $r102;
                            $r102.removeAllViews();
                        }
                        if (initializePanelContent(panelFeatureState) && panelFeatureState.hasPanelItems()) {
                            View $r11 = panelFeatureState.shownPanelView;
                            View view = $r11;
                            ViewGroup.LayoutParams $r122 = $r11.getLayoutParams();
                            ViewGroup.LayoutParams $r13 = $r122;
                            if ($r122 == null) {
                                $r13 = new ViewGroup.LayoutParams(-2, -2);
                            }
                            int $i0 = panelFeatureState.background;
                            ViewGroup $r103 = panelFeatureState.decorView;
                            ViewGroup viewGroup2 = $r103;
                            $r103.setBackgroundResource($i0);
                            View $r112 = panelFeatureState.shownPanelView;
                            View view2 = $r112;
                            ViewParent $r14 = $r112.getParent();
                            if ($r14 != null && ($r14 instanceof ViewGroup)) {
                                ((ViewGroup) $r14).removeView(panelFeatureState.shownPanelView);
                            }
                            panelFeatureState.decorView.addView(panelFeatureState.shownPanelView, $r13);
                            View $r113 = panelFeatureState.shownPanelView;
                            View view3 = $r113;
                            if (!$r113.hasFocus()) {
                                View $r114 = panelFeatureState.shownPanelView;
                                View view4 = $r114;
                                $r114.requestFocus();
                            }
                        } else {
                            return;
                        }
                    } else {
                        View $r115 = panelFeatureState.createdPanelView;
                        if (!($r115 == null || ($r12 = $r115.getLayoutParams()) == null || $r12.width != -1)) {
                            i = -1;
                            panelFeatureState.isHandled = false;
                            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(i, -2, panelFeatureState.x, panelFeatureState.y, GameControllerDelegate.THUMBSTICK_RIGHT_X, 8519680, -3);
                            layoutParams.gravity = panelFeatureState.gravity;
                            layoutParams.windowAnimations = panelFeatureState.windowAnimations;
                            windowManager.addView(panelFeatureState.decorView, layoutParams);
                            panelFeatureState.isOpen = true;
                            return;
                        }
                    }
                    i = -2;
                    panelFeatureState.isHandled = false;
                    WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(i, -2, panelFeatureState.x, panelFeatureState.y, GameControllerDelegate.THUMBSTICK_RIGHT_X, 8519680, -3);
                    layoutParams2.gravity = panelFeatureState.gravity;
                    layoutParams2.windowAnimations = panelFeatureState.windowAnimations;
                    windowManager.addView(panelFeatureState.decorView, layoutParams2);
                    panelFeatureState.isOpen = true;
                    return;
                }
                return;
            }
            closePanel(panelFeatureState, true);
        }
    }

    private boolean performPanelShortcut(PanelFeatureState panelFeatureState, int i, KeyEvent keyEvent, int i2) {
        MenuBuilder $r3;
        boolean $z1 = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((panelFeatureState.isPrepared || preparePanel(panelFeatureState, keyEvent)) && ($r3 = panelFeatureState.menu) != null) {
            $z1 = $r3.performShortcut(i, keyEvent, i2);
        }
        if ($z1 && (i2 & 1) == 0 && this.mDecorContentParent == null) {
            closePanel(panelFeatureState, true);
        }
        return $z1;
    }

    private boolean preparePanel(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        DecorContentParent $r7;
        DecorContentParent $r72;
        DecorContentParent $r73;
        if (this.mIsDestroyed) {
            return false;
        }
        if (panelFeatureState.isPrepared) {
            return true;
        }
        PanelFeatureState $r4 = this.mPreparedPanel;
        if (!($r4 == null || $r4 == panelFeatureState)) {
            closePanel($r4, false);
        }
        Window.Callback $r5 = getWindowCallback();
        if ($r5 != null) {
            panelFeatureState.createdPanelView = $r5.onCreatePanelView(panelFeatureState.featureId);
        }
        int $i0 = panelFeatureState.featureId;
        boolean $z0 = $i0 == 0 || $i0 == 108;
        if ($z0 && ($r73 = this.mDecorContentParent) != null) {
            $r73.setMenuPrepared();
        }
        if (panelFeatureState.createdPanelView == null) {
            if ($z0) {
                peekSupportActionBar();
            }
            if (panelFeatureState.menu == null || panelFeatureState.refreshMenuContent) {
                if (panelFeatureState.menu == null && (!initializePanelMenu(panelFeatureState) || panelFeatureState.menu == null)) {
                    return false;
                }
                if ($z0 && this.mDecorContentParent != null) {
                    if (this.mActionMenuPresenterCallback == null) {
                        this.mActionMenuPresenterCallback = new ActionMenuPresenterCallback();
                    }
                    this.mDecorContentParent.setMenu(panelFeatureState.menu, this.mActionMenuPresenterCallback);
                }
                panelFeatureState.menu.stopDispatchingItemsChanged();
                if (!$r5.onCreatePanelMenu(panelFeatureState.featureId, panelFeatureState.menu)) {
                    panelFeatureState.setMenu((MenuBuilder) null);
                    if (!$z0 || ($r72 = this.mDecorContentParent) == null) {
                        return false;
                    }
                    $r72.setMenu((Menu) null, this.mActionMenuPresenterCallback);
                    return false;
                }
                panelFeatureState.refreshMenuContent = false;
            }
            panelFeatureState.menu.stopDispatchingItemsChanged();
            Bundle $r9 = panelFeatureState.frozenActionViewState;
            if ($r9 != null) {
                panelFeatureState.menu.restoreActionViewStates($r9);
                panelFeatureState.frozenActionViewState = null;
            }
            if (!$r5.onPreparePanel(0, panelFeatureState.createdPanelView, panelFeatureState.menu)) {
                if ($z0 && ($r7 = this.mDecorContentParent) != null) {
                    $r7.setMenu((Menu) null, this.mActionMenuPresenterCallback);
                }
                panelFeatureState.menu.startDispatchingItemsChanged();
                return false;
            }
            panelFeatureState.qwertyMode = KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1;
            panelFeatureState.menu.setQwertyMode(panelFeatureState.qwertyMode);
            panelFeatureState.menu.startDispatchingItemsChanged();
        }
        panelFeatureState.isPrepared = true;
        panelFeatureState.isHandled = false;
        this.mPreparedPanel = panelFeatureState;
        return true;
    }

    private void reopenMenu(MenuBuilder menuBuilder, boolean z) {
        DecorContentParent $r2 = this.mDecorContentParent;
        if ($r2 == null || !$r2.canShowOverflowMenu() || (ViewConfiguration.get(this.mContext).hasPermanentMenuKey() && !this.mDecorContentParent.isOverflowMenuShowPending())) {
            PanelFeatureState $r6 = getPanelState(0, true);
            $r6.refreshDecorView = true;
            closePanel($r6, false);
            openPanel($r6, (KeyEvent) null);
            return;
        }
        Window.Callback $r5 = getWindowCallback();
        if (this.mDecorContentParent.isOverflowMenuShowing() && z) {
            this.mDecorContentParent.hideOverflowMenu();
            if (!this.mIsDestroyed) {
                $r5.onPanelClosed(108, getPanelState(0, true).menu);
            }
        } else if ($r5 != null && !this.mIsDestroyed) {
            if (this.mInvalidatePanelMenuPosted && (this.mInvalidatePanelMenuFeatures & 1) != 0) {
                this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
                this.mInvalidatePanelMenuRunnable.run();
            }
            PanelFeatureState $r62 = getPanelState(0, true);
            MenuBuilder $r1 = $r62.menu;
            if ($r1 != null && !$r62.refreshMenuContent && $r5.onPreparePanel(0, $r62.createdPanelView, $r1)) {
                $r5.onMenuOpened(108, $r62.menu);
                this.mDecorContentParent.showOverflowMenu();
            }
        }
    }

    private int sanitizeWindowFeatureId(int i) {
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

    private boolean shouldInheritContext(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View $r3 = this.mWindow.getDecorView();
        while (viewParent != null) {
            if (viewParent == $r3 || !(viewParent instanceof View) || ViewCompat.isAttachedToWindow((View) viewParent)) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    private void throwFeatureRequestIfSubDecorInstalled() {
        if (this.mSubDecorInstalled) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    /* access modifiers changed from: package-private */
    public int a(int i) {
        if (i == -100) {
            return -1;
        }
        if (i != 0) {
            return i;
        }
        if (Build.VERSION.SDK_INT >= 23 && ((UiModeManager) this.mContext.getSystemService(UiModeManager.class)).getNightMode() == 0) {
            return -1;
        }
        b();
        return this.v.a();
    }

    public boolean a() {
        int $i0 = e();
        int $i1 = a($i0);
        boolean $z0 = $i1 != -1 ? init($i1) : false;
        if ($i0 == 0) {
            b();
            this.v.init();
        }
        this.r = true;
        return $z0;
    }

    /* access modifiers changed from: package-private */
    public int access$300(int i) {
        boolean $z0;
        boolean $z1;
        boolean $z12;
        ActionBarContextView $r1 = this.mActionModeView;
        byte $b1 = 0;
        if ($r1 == null || !($r1.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            $z0 = false;
        } else {
            ViewGroup.MarginLayoutParams $r3 = (ViewGroup.MarginLayoutParams) this.mActionModeView.getLayoutParams();
            $z0 = true;
            if (this.mActionModeView.isShown()) {
                if (this.mTempRect1 == null) {
                    this.mTempRect1 = new Rect();
                    this.mTempRect2 = new Rect();
                }
                Rect $r4 = this.mTempRect1;
                Rect $r5 = this.mTempRect2;
                $r4.set(0, i, 0, 0);
                ViewUtils.computeFitSystemWindows(this.mSubDecor, $r4, $r5);
                int $i2 = $r5.top == 0 ? i : 0;
                int $i3 = $r3.topMargin;
                int i2 = $i3;
                if ($i3 != $i2) {
                    $r3.topMargin = i;
                    View $r7 = this.mStatusGuard;
                    if ($r7 == null) {
                        this.mStatusGuard = new View(this.mContext);
                        View $r72 = this.mStatusGuard;
                        Context $r8 = this.mContext;
                        Context context = $r8;
                        $r72.setBackgroundColor($r8.getResources().getColor(R$color.abc_input_method_navigation_guard));
                        this.mSubDecor.addView(this.mStatusGuard, -1, new ViewGroup.LayoutParams(-1, i));
                    } else {
                        ViewGroup.LayoutParams $r2 = $r7.getLayoutParams();
                        if ($r2.height != i) {
                            $r2.height = i;
                            View $r73 = this.mStatusGuard;
                            View view = $r73;
                            $r73.setLayoutParams($r2);
                        }
                    }
                    $z1 = true;
                } else {
                    $z1 = false;
                }
                if (this.mStatusGuard == null) {
                    $z0 = false;
                }
                if (!this.mOverlayActionMode && $z0) {
                    i = 0;
                }
            } else {
                if ($r3.topMargin != 0) {
                    $r3.topMargin = 0;
                    $z12 = true;
                } else {
                    $z12 = false;
                }
                $z0 = false;
            }
            if ($z1) {
                this.mActionModeView.setLayoutParams($r3);
            }
        }
        View $r74 = this.mStatusGuard;
        if ($r74 != null) {
            if (!$z0) {
                $b1 = 8;
            }
            $r74.setVisibility($b1);
        }
        return i;
    }

    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        ensureSubDecor();
        ((ViewGroup) this.mSubDecor.findViewById(16908290)).addView(view, layoutParams);
        this.mOriginalWindowCallback.onContentChanged();
    }

    public void c() {
        ActionBar $r1 = getSupportActionBar();
        if ($r1 != null) {
            $r1.setShowHideAnimationEnabled(false);
        }
        ClassWriter $r2 = this.v;
        if ($r2 != null) {
            $r2.c();
        }
    }

    /* access modifiers changed from: package-private */
    public void callOnPanelClosed(int i, PanelFeatureState $r2, Menu $r3) {
        if ($r3 == null) {
            if ($r2 == null && i >= 0) {
                PanelFeatureState[] $r1 = this.mPanels;
                if (i < $r1.length) {
                    $r2 = $r1[i];
                }
            }
            if ($r2 != null) {
                $r3 = $r2.menu;
            }
        }
        if (($r2 == null || $r2.isOpen) && !this.mIsDestroyed) {
            this.mOriginalWindowCallback.onPanelClosed(i, $r3);
        }
    }

    /* access modifiers changed from: package-private */
    public void checkCloseActionMenu(MenuBuilder menuBuilder) {
        if (!this.mClosingActionMenu) {
            this.mClosingActionMenu = true;
            this.mDecorContentParent.dismissPopups();
            Window.Callback $r1 = getWindowCallback();
            if ($r1 != null && !this.mIsDestroyed) {
                $r1.onPanelClosed(108, menuBuilder);
            }
            this.mClosingActionMenu = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void closePanel(int i) {
        closePanel(getPanelState(i, true), true);
    }

    /* access modifiers changed from: package-private */
    public void closePanel(PanelFeatureState panelFeatureState, boolean z) {
        ViewGroup $r7;
        DecorContentParent $r2;
        if (!z || panelFeatureState.featureId != 0 || ($r2 = this.mDecorContentParent) == null || !$r2.isOverflowMenuShowing()) {
            WindowManager $r6 = (WindowManager) this.mContext.getSystemService("window");
            if (!($r6 == null || !panelFeatureState.isOpen || ($r7 = panelFeatureState.decorView) == null)) {
                $r6.removeView($r7);
                if (z) {
                    callOnPanelClosed(panelFeatureState.featureId, panelFeatureState, (Menu) null);
                }
            }
            panelFeatureState.isPrepared = false;
            panelFeatureState.isHandled = false;
            panelFeatureState.isOpen = false;
            panelFeatureState.shownPanelView = null;
            panelFeatureState.refreshDecorView = true;
            if (this.mPreparedPanel == panelFeatureState) {
                this.mPreparedPanel = null;
                return;
            }
            return;
        }
        checkCloseActionMenu(panelFeatureState.menu);
    }

    public View createView(View view, String str, Context context, AttributeSet attributeSet) {
        boolean $z1;
        AppCompatViewInflater $r5;
        boolean $z0 = false;
        if (this.mAppCompatViewInflater == null) {
            String $r9 = this.mContext.obtainStyledAttributes(R$styleable.AppCompatTheme).getString(R$styleable.AppCompatTheme_viewInflaterClass);
            if ($r9 == null || AppCompatViewInflater.class.getName().equals($r9)) {
                $r5 = new AppCompatViewInflater();
            } else {
                try {
                    this.mAppCompatViewInflater = (AppCompatViewInflater) Class.forName($r9).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable $r16) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + $r9 + ". Falling back to default.", $r16);
                    $r5 = new AppCompatViewInflater();
                }
            }
            this.mAppCompatViewInflater = $r5;
        }
        if (s) {
            if (!(attributeSet instanceof XmlPullParser)) {
                $z0 = shouldInheritContext((ViewParent) view);
            } else if (((XmlPullParser) attributeSet).getDepth() > 1) {
                $z0 = true;
            }
            $z1 = $z0;
        } else {
            $z1 = false;
        }
        return this.mAppCompatViewInflater.createView(view, str, context, attributeSet, $z1, s, true, TintManager.insert());
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r3.mSubDecor;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean d() {
        /*
            r3 = this;
            boolean r0 = r3.mSubDecorInstalled
            if (r0 == 0) goto L_0x0010
            android.view.ViewGroup r1 = r3.mSubDecor
            if (r1 == 0) goto L_0x0010
            boolean r0 = com.org.android.view.ViewCompat.show(r1)
            if (r0 == 0) goto L_0x0010
            r2 = 1
            return r2
        L_0x0010:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatDelegateImplV7.d():boolean");
    }

    /* access modifiers changed from: package-private */
    public void dismissPopups() {
        MenuBuilder $r8;
        DecorContentParent $r3 = this.mDecorContentParent;
        if ($r3 != null) {
            $r3.dismissPopups();
        }
        if (this.mActionModePopup != null) {
            this.mWindow.getDecorView().removeCallbacks(this.mShowActionModePopup);
            if (this.mActionModePopup.isShowing()) {
                try {
                    this.mActionModePopup.dismiss();
                } catch (IllegalArgumentException e) {
                }
            }
            this.mActionModePopup = null;
        }
        endOnGoingFadeAnimation();
        PanelFeatureState $r7 = getPanelState(0, false);
        if ($r7 != null && ($r8 = $r7.menu) != null) {
            $r8.close();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View $r4;
        Window.Callback $r2 = this.mOriginalWindowCallback;
        boolean $z1 = true;
        if ((($r2 instanceof x) || ($r2 instanceof AppCompatDialog)) && ($r4 = this.mWindow.getDecorView()) != null && i.a($r4, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.mOriginalWindowCallback.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int $i0 = keyEvent.getKeyCode();
        if (keyEvent.getAction() != 0) {
            $z1 = false;
        }
        return $z1 ? onKeyDown($i0, keyEvent) : onKeyUp($i0, keyEvent);
    }

    /* access modifiers changed from: package-private */
    public void doInvalidatePanelMenu(int i) {
        PanelFeatureState $r1;
        PanelFeatureState $r12 = getPanelState(i, true);
        if ($r12.menu != null) {
            Bundle $r3 = new Bundle();
            $r12.menu.saveActionViewStates($r3);
            if ($r3.size() > 0) {
                $r12.frozenActionViewState = $r3;
            }
            $r12.menu.stopDispatchingItemsChanged();
            $r12.menu.clear();
        }
        $r12.refreshMenuContent = true;
        $r12.refreshDecorView = true;
        if ((i == 108 || i == 0) && this.mDecorContentParent != null && ($r1 = getPanelState(0, false)) != null) {
            $r1.isPrepared = false;
            preparePanel($r1, (KeyEvent) null);
        }
    }

    /* access modifiers changed from: package-private */
    public void endOnGoingFadeAnimation() {
        ViewPropertyAnimatorCompat $r1 = this.mFadeAnim;
        if ($r1 != null) {
            $r1.cancel();
        }
    }

    /* access modifiers changed from: package-private */
    public PanelFeatureState findMenuPanel(Menu menu) {
        PanelFeatureState[] $r1 = this.mPanels;
        int $i1 = $r1 != null ? $r1.length : 0;
        for (int $i0 = 0; $i0 < $i1; $i0++) {
            PanelFeatureState $r2 = $r1[$i0];
            if ($r2 != null && $r2.menu == menu) {
                return $r2;
            }
        }
        return null;
    }

    public View findViewById(int i) {
        ensureSubDecor();
        return this.mWindow.findViewById(i);
    }

    /* access modifiers changed from: package-private */
    public final Context getActionBarThemedContext() {
        ActionBar $r1 = getSupportActionBar();
        Context $r2 = $r1 != null ? $r1.getThemedContext() : null;
        return $r2 == null ? this.mContext : $r2;
    }

    /* access modifiers changed from: protected */
    public PanelFeatureState getPanelState(int i, boolean z) {
        PanelFeatureState[] $r1 = this.mPanels;
        if ($r1 == null || $r1.length <= i) {
            PanelFeatureState[] $r2 = new PanelFeatureState[(i + 1)];
            if ($r1 != null) {
                System.arraycopy($r1, 0, $r2, 0, $r1.length);
            }
            this.mPanels = $r2;
            $r1 = $r2;
        }
        PanelFeatureState $r3 = $r1[i];
        if ($r3 != null) {
            return $r3;
        }
        PanelFeatureState $r32 = new PanelFeatureState(i);
        $r1[i] = $r32;
        return $r32;
    }

    public ActionBar getSupportActionBar() {
        initWindowDecorActionBar();
        return this.mActionBar;
    }

    /* access modifiers changed from: package-private */
    public final CharSequence getTitle() {
        Window.Callback $r2 = this.mOriginalWindowCallback;
        return $r2 instanceof Activity ? ((Activity) $r2).getTitle() : this.mTitle;
    }

    /* access modifiers changed from: package-private */
    public final Window.Callback getWindowCallback() {
        return this.mWindow.getCallback();
    }

    public void installViewFactory() {
        LayoutInflater $r2 = LayoutInflater.from(this.mContext);
        if ($r2.getFactory() == null) {
            LayoutInflaterCompatHC.setFactory($r2, this);
        } else if (!($r2.getFactory2() instanceof AppCompatDelegateImplV7)) {
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    public void invalidateOptionsMenu() {
        ActionBar $r1 = getSupportActionBar();
        if ($r1 == null || !$r1.invalidateOptionsMenu()) {
            invalidatePanelMenu(0);
        }
    }

    public boolean isHandleNativeActionModesEnabled() {
        return this.mHandleNativeActionModes;
    }

    /* access modifiers changed from: package-private */
    public boolean onBackPressed() {
        ActionMode $r1 = this.mActionMode;
        if ($r1 != null) {
            $r1.finish();
            return true;
        }
        ActionBar $r2 = getSupportActionBar();
        return $r2 != null && $r2.collapseActionView();
    }

    public void onCreate(Bundle bundle) {
        Window.Callback $r3 = this.mOriginalWindowCallback;
        if ($r3 instanceof Activity) {
            String $r4 = null;
            try {
                $r4 = NavUtils.getParentActivityName((Activity) $r3);
            } catch (IllegalArgumentException e) {
            }
            if ($r4 != null) {
                ActionBar $r6 = peekSupportActionBar();
                if ($r6 == null) {
                    this.mEnableDefaultActionBarUp = true;
                } else {
                    $r6.setDefaultDisplayHomeAsUpEnabled(true);
                }
            }
        }
        if (bundle != null && this.l == -100) {
            this.l = bundle.getInt("appcompat:local_night_mode", -100);
        }
    }

    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return createView(view, str, context, attributeSet);
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView((View) null, str, context, attributeSet);
    }

    /* access modifiers changed from: package-private */
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean $z0 = true;
        if (i == 4) {
            if ((keyEvent.getFlags() & 128) == 0) {
                $z0 = false;
            }
            this.mLongPressBackDown = $z0;
            return false;
        } else if (i != 82) {
            return false;
        } else {
            onKeyDownPanel(0, keyEvent);
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean onKeyShortcut(int i, KeyEvent keyEvent) {
        ActionBar $r2 = getSupportActionBar();
        if ($r2 != null && $r2.onKeyShortcut(i, keyEvent)) {
            return true;
        }
        PanelFeatureState $r3 = this.mPreparedPanel;
        if ($r3 != null && performPanelShortcut($r3, keyEvent.getKeyCode(), keyEvent, 1)) {
            PanelFeatureState $r32 = this.mPreparedPanel;
            if ($r32 == null) {
                return true;
            }
            $r32.isHandled = true;
            return true;
        } else if (this.mPreparedPanel != null) {
            return false;
        } else {
            PanelFeatureState $r33 = getPanelState(0, true);
            preparePanel($r33, keyEvent);
            boolean $z0 = performPanelShortcut($r33, keyEvent.getKeyCode(), keyEvent, 1);
            $r33.isPrepared = false;
            return $z0;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 4) {
            boolean $z0 = this.mLongPressBackDown;
            this.mLongPressBackDown = false;
            PanelFeatureState $r2 = getPanelState(0, false);
            if ($r2 == null || !$r2.isOpen) {
                return onBackPressed();
            }
            if ($z0) {
                return true;
            }
            closePanel($r2, true);
            return true;
        } else if (i != 82) {
            return false;
        } else {
            onKeyUpPanel(0, keyEvent);
            return true;
        }
    }

    public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        PanelFeatureState $r4;
        Window.Callback $r3 = getWindowCallback();
        if ($r3 == null || this.mIsDestroyed || ($r4 = findMenuPanel(menuBuilder.getRootMenu())) == null) {
            return false;
        }
        return $r3.onMenuItemSelected($r4.featureId, menuItem);
    }

    public void onMenuModeChange(MenuBuilder menuBuilder) {
        reopenMenu(menuBuilder, true);
    }

    /* access modifiers changed from: package-private */
    public void onMenuOpened(int i) {
        ActionBar $r1;
        if (i == 108 && ($r1 = getSupportActionBar()) != null) {
            $r1.dispatchMenuVisibilityChanged(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void onPanelClosed(int i) {
        if (i == 108) {
            ActionBar $r1 = getSupportActionBar();
            if ($r1 != null) {
                $r1.dispatchMenuVisibilityChanged(false);
            }
        } else if (i == 0) {
            PanelFeatureState $r2 = getPanelState(i, true);
            if ($r2.isOpen) {
                closePanel($r2, false);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onSubDecorInstalled(ViewGroup viewGroup) {
    }

    public final void onTitleChanged(CharSequence charSequence) {
        this.mTitle = charSequence;
        DecorContentParent $r2 = this.mDecorContentParent;
        if ($r2 != null) {
            $r2.setWindowTitle(charSequence);
        } else if (peekSupportActionBar() != null) {
            peekSupportActionBar().setWindowTitle(charSequence);
        } else {
            TextView $r4 = this.mTitleView;
            if ($r4 != null) {
                $r4.setText(charSequence);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final ActionBar peekSupportActionBar() {
        return this.mActionBar;
    }

    public boolean requestWindowFeature(int i) {
        int $i0 = sanitizeWindowFeatureId(i);
        if (this.mWindowNoTitle && $i0 == 108) {
            return false;
        }
        if (this.mHasActionBar && $i0 == 1) {
            this.mHasActionBar = false;
        }
        if ($i0 == 1) {
            throwFeatureRequestIfSubDecorInstalled();
            this.mWindowNoTitle = true;
            return true;
        } else if ($i0 == 2) {
            throwFeatureRequestIfSubDecorInstalled();
            this.mFeatureIndeterminateProgress = true;
            return true;
        } else if ($i0 == 5) {
            throwFeatureRequestIfSubDecorInstalled();
            this.mFeatureProgress = true;
            return true;
        } else if ($i0 == 10) {
            throwFeatureRequestIfSubDecorInstalled();
            this.mOverlayActionMode = true;
            return true;
        } else if ($i0 == 108) {
            throwFeatureRequestIfSubDecorInstalled();
            this.mHasActionBar = true;
            return true;
        } else if ($i0 != 109) {
            return this.mWindow.requestFeature($i0);
        } else {
            throwFeatureRequestIfSubDecorInstalled();
            this.mOverlayActionBar = true;
            return true;
        }
    }

    public void setContentView(int i) {
        ensureSubDecor();
        ViewGroup $r1 = (ViewGroup) this.mSubDecor.findViewById(16908290);
        $r1.removeAllViews();
        LayoutInflater.from(this.mContext).inflate(i, $r1);
        this.mOriginalWindowCallback.onContentChanged();
    }

    public void setContentView(View view) {
        ensureSubDecor();
        ViewGroup $r3 = (ViewGroup) this.mSubDecor.findViewById(16908290);
        $r3.removeAllViews();
        $r3.addView(view);
        this.mOriginalWindowCallback.onContentChanged();
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        ensureSubDecor();
        ViewGroup $r4 = (ViewGroup) this.mSubDecor.findViewById(16908290);
        $r4.removeAllViews();
        $r4.addView(view, layoutParams);
        this.mOriginalWindowCallback.onContentChanged();
    }

    public ActionMode startSupportActionMode(ActionMode.Callback callback) {
        AppCompatCallback $r1;
        if (callback != null) {
            ActionMode $r3 = this.mActionMode;
            if ($r3 != null) {
                $r3.finish();
            }
            ActionModeCallbackWrapperV7 $r4 = new ActionModeCallbackWrapperV7(callback);
            ActionBar $r5 = getSupportActionBar();
            if ($r5 != null) {
                this.mActionMode = $r5.startActionMode($r4);
                ActionMode $r32 = this.mActionMode;
                if (!($r32 == null || ($r1 = this.mAppCompatCallback) == null)) {
                    $r1.onSupportActionModeStarted($r32);
                }
            }
            if (this.mActionMode == null) {
                this.mActionMode = startSupportActionModeFromWindow($r4);
            }
            return this.mActionMode;
        }
        throw new IllegalArgumentException("ActionMode callback can not be null.");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.org.v4.view.ActionMode startSupportActionModeFromWindow(com.org.v4.view.ActionMode.Callback r44) {
        /*
            r43 = this;
            r0 = r43
            r0.endOnGoingFadeAnimation()
            r0 = r43
            com.org.v4.view.ActionMode r3 = r0.mActionMode
            if (r3 == 0) goto L_0x000e
            r3.finish()
        L_0x000e:
            r0 = r44
            boolean r4 = r0 instanceof android.support.v7.app.AppCompatDelegateImplV7.ActionModeCallbackWrapperV7
            if (r4 != 0) goto L_0x001f
            android.support.v7.app.AppCompatDelegateImplV7$ActionModeCallbackWrapperV7 r5 = new android.support.v7.app.AppCompatDelegateImplV7$ActionModeCallbackWrapperV7
            r0 = r43
            r1 = r44
            r5.<init>(r1)
            r44 = r5
        L_0x001f:
            r0 = r43
            android.support.v7.app.AppCompatCallback r6 = r0.mAppCompatCallback
            if (r6 == 0) goto L_0x0032
            r0 = r43
            boolean r4 = r0.mIsDestroyed
            if (r4 != 0) goto L_0x0032
            r0 = r44
            com.org.v4.view.ActionMode r3 = r6.onWindowStartingSupportActionMode(r0)     // Catch:{ AbstractMethodError -> 0x0274 }
            goto L_0x0033
        L_0x0032:
            r3 = 0
        L_0x0033:
            if (r3 == 0) goto L_0x003c
            r0 = r43
            r0.mActionMode = r3
            goto L_0x0260
        L_0x003c:
            r0 = r43
            android.support.v7.widget.ActionBarContextView r7 = r0.mActionModeView
            r4 = 1
            if (r7 != 0) goto L_0x015f
            r0 = r43
            boolean r8 = r0.mIsFloating
            if (r8 == 0) goto L_0x0128
            android.util.TypedValue r9 = new android.util.TypedValue
            r10 = r9
            r9.<init>()
            r0 = r43
            android.content.Context r11 = r0.mContext
            android.content.res.Resources$Theme r12 = r11.getTheme()
            int r13 = com.org.v4.util.R$attr.actionBarTheme
            r14 = 1
            r12.resolveAttribute(r13, r10, r14)
            int r13 = r10.resourceId
            if (r13 == 0) goto L_0x009e
            r0 = r43
            android.content.Context r11 = r0.mContext
            android.content.res.Resources r15 = r11.getResources()
            android.content.res.Resources$Theme r16 = r15.newTheme()
            r0 = r16
            r0.setTo(r12)
            int r13 = r10.resourceId
            r14 = 1
            r0 = r16
            r0.applyStyle(r13, r14)
            com.org.v4.view.ContextThemeWrapper r17 = new com.org.v4.view.ContextThemeWrapper
            r11 = r17
            r0 = r43
            android.content.Context r0 = r0.mContext
            r18 = r0
            r14 = 0
            r0 = r17
            r1 = r18
            r0.<init>((android.content.Context) r1, (int) r14)
            r19 = r11
            com.org.v4.view.ContextThemeWrapper r19 = (com.org.v4.view.ContextThemeWrapper) r19
            r17 = r19
            r0 = r17
            android.content.res.Resources$Theme r12 = r0.getTheme()
            r0 = r16
            r12.setTo(r0)
            goto L_0x00a2
        L_0x009e:
            r0 = r43
            android.content.Context r11 = r0.mContext
        L_0x00a2:
            android.support.v7.widget.ActionBarContextView r20 = new android.support.v7.widget.ActionBarContextView
            r0 = r20
            r0.<init>(r11)
            r0 = r20
            r1 = r43
            r1.mActionModeView = r0
            android.widget.PopupWindow r21 = new android.widget.PopupWindow
            int r13 = com.org.v4.util.R$attr.actionModePopupWindowStyle
            r22 = 0
            r0 = r21
            r1 = r22
            r0.<init>(r11, r1, r13)
            r0 = r21
            r1 = r43
            r1.mActionModePopup = r0
            r0 = r43
            android.widget.PopupWindow r0 = r0.mActionModePopup
            r23 = r0
            r14 = 2
            r0 = r23
            android.support.v4.widget.PopupWindowCompat.setWindowLayoutType(r0, r14)
            r0 = r43
            android.widget.PopupWindow r0 = r0.mActionModePopup
            r23 = r0
            r0 = r43
            android.support.v7.widget.ActionBarContextView r7 = r0.mActionModeView
            r0 = r23
            r0.setContentView(r7)
            r0 = r43
            android.widget.PopupWindow r0 = r0.mActionModePopup
            r23 = r0
            r14 = -1
            r0 = r23
            r0.setWidth(r14)
            android.content.res.Resources$Theme r16 = r11.getTheme()
            int r13 = com.org.v4.util.R$attr.actionBarSize
            r14 = 1
            r0 = r16
            r0.resolveAttribute(r13, r10, r14)
            int r13 = r10.data
            android.content.res.Resources r15 = r11.getResources()
            android.util.DisplayMetrics r24 = r15.getDisplayMetrics()
            r0 = r24
            int r13 = android.util.TypedValue.complexToDimensionPixelSize(r13, r0)
            r0 = r43
            android.support.v7.widget.ActionBarContextView r7 = r0.mActionModeView
            r7.setContentHeight(r13)
            r0 = r43
            android.widget.PopupWindow r0 = r0.mActionModePopup
            r23 = r0
            r14 = -2
            r0 = r23
            r0.setHeight(r14)
            android.support.v7.app.AppCompatDelegateImplV7$5 r25 = new android.support.v7.app.AppCompatDelegateImplV7$5
            r0 = r25
            r1 = r43
            r0.<init>()
            r0 = r25
            r1 = r43
            r1.mShowActionModePopup = r0
            goto L_0x015f
        L_0x0128:
            r0 = r43
            android.view.ViewGroup r0 = r0.mSubDecor
            r26 = r0
            int r13 = com.org.v4.util.R$id.action_mode_bar_stub
            r0 = r26
            android.view.View r27 = r0.findViewById(r13)
            r29 = r27
            android.support.v7.widget.ViewStubCompat r29 = (android.support.v7.widget.ViewStubCompat) r29
            r28 = r29
            if (r28 == 0) goto L_0x015f
            r0 = r43
            android.content.Context r11 = r0.getActionBarThemedContext()
            android.view.LayoutInflater r30 = android.view.LayoutInflater.from(r11)
            r0 = r28
            r1 = r30
            r0.setLayoutInflater(r1)
            r0 = r28
            android.view.View r27 = r0.inflate()
            r31 = r27
            android.support.v7.widget.ActionBarContextView r31 = (android.support.v7.widget.ActionBarContextView) r31
            r7 = r31
            r0 = r43
            r0.mActionModeView = r7
        L_0x015f:
            r0 = r43
            android.support.v7.widget.ActionBarContextView r7 = r0.mActionModeView
            if (r7 == 0) goto L_0x0260
            r0 = r43
            r0.endOnGoingFadeAnimation()
            r0 = r43
            android.support.v7.widget.ActionBarContextView r7 = r0.mActionModeView
            r7.killMode()
            com.org.v4.view.StandaloneActionMode r32 = new com.org.v4.view.StandaloneActionMode
            r33 = r32
            r0 = r43
            android.support.v7.widget.ActionBarContextView r7 = r0.mActionModeView
            android.content.Context r11 = r7.getContext()
            r0 = r43
            android.support.v7.widget.ActionBarContextView r7 = r0.mActionModeView
            r0 = r43
            android.widget.PopupWindow r0 = r0.mActionModePopup
            r23 = r0
            if (r23 != 0) goto L_0x018a
            goto L_0x018b
        L_0x018a:
            r4 = 0
        L_0x018b:
            r0 = r32
            r1 = r44
            r0.<init>(r11, r7, r1, r4)
            r0 = r33
            android.view.Menu r34 = r0.getMenu()
            r0 = r44
            r1 = r33
            r2 = r34
            boolean r4 = r0.onCreateActionMode(r1, r2)
            if (r4 == 0) goto L_0x0258
            r0 = r33
            r0.invalidate()
            r0 = r43
            android.support.v7.widget.ActionBarContextView r7 = r0.mActionModeView
            r0 = r33
            r7.initForMode(r0)
            r0 = r33
            r1 = r43
            r1.mActionMode = r0
            r0 = r43
            boolean r4 = r0.d()
            if (r4 == 0) goto L_0x01fa
            r0 = r43
            android.support.v7.widget.ActionBarContextView r7 = r0.mActionModeView
            r35 = 0
            r0 = r35
            r7.setAlpha(r0)
            r0 = r43
            android.support.v7.widget.ActionBarContextView r7 = r0.mActionModeView
            com.org.android.view.ViewPropertyAnimatorCompat r36 = com.org.android.view.ViewCompat.animate(r7)
            r35 = 1065353216(0x3f800000, float:1.0)
            r0 = r36
            r1 = r35
            r0.alpha(r1)
            r0 = r36
            r1 = r43
            r1.mFadeAnim = r0
            r0 = r43
            com.org.android.view.ViewPropertyAnimatorCompat r0 = r0.mFadeAnim
            r36 = r0
            android.support.v7.app.AppCompatDelegateImplV7$6 r37 = new android.support.v7.app.AppCompatDelegateImplV7$6
            r0 = r37
            r1 = r43
            r0.<init>()
            r0 = r36
            r1 = r37
            r0.setListener(r1)
            goto L_0x0238
        L_0x01fa:
            r0 = r43
            android.support.v7.widget.ActionBarContextView r7 = r0.mActionModeView
            r35 = 1065353216(0x3f800000, float:1.0)
            r0 = r35
            r7.setAlpha(r0)
            r0 = r43
            android.support.v7.widget.ActionBarContextView r7 = r0.mActionModeView
            r14 = 0
            r7.setVisibility(r14)
            r0 = r43
            android.support.v7.widget.ActionBarContextView r7 = r0.mActionModeView
            r14 = 32
            r7.sendAccessibilityEvent(r14)
            r0 = r43
            android.support.v7.widget.ActionBarContextView r7 = r0.mActionModeView
            android.view.ViewParent r38 = r7.getParent()
            r0 = r38
            boolean r4 = r0 instanceof android.view.View
            if (r4 == 0) goto L_0x0238
            r0 = r43
            android.support.v7.widget.ActionBarContextView r7 = r0.mActionModeView
            android.view.ViewParent r38 = r7.getParent()
            r39 = r38
            android.view.View r39 = (android.view.View) r39
            r27 = r39
            r0 = r27
            com.org.android.view.ViewCompat.requestApplyInsets(r0)
        L_0x0238:
            r0 = r43
            android.widget.PopupWindow r0 = r0.mActionModePopup
            r23 = r0
            if (r23 == 0) goto L_0x0260
            r0 = r43
            android.view.Window r0 = r0.mWindow
            r40 = r0
            android.view.View r27 = r0.getDecorView()
            r0 = r43
            java.lang.Runnable r0 = r0.mShowActionModePopup
            r41 = r0
            r0 = r27
            r1 = r41
            r0.post(r1)
            goto L_0x0260
        L_0x0258:
            r22 = 0
            r0 = r22
            r1 = r43
            r1.mActionMode = r0
        L_0x0260:
            r0 = r43
            com.org.v4.view.ActionMode r3 = r0.mActionMode
            if (r3 == 0) goto L_0x026f
            r0 = r43
            android.support.v7.app.AppCompatCallback r6 = r0.mAppCompatCallback
            if (r6 == 0) goto L_0x026f
            r6.onSupportActionModeStarted(r3)
        L_0x026f:
            r0 = r43
            com.org.v4.view.ActionMode r3 = r0.mActionMode
            return r3
        L_0x0274:
            r42 = move-exception
            goto L_0x0032
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatDelegateImplV7.startSupportActionModeFromWindow(com.org.v4.view.ActionMode$Callback):com.org.v4.view.ActionMode");
    }
}

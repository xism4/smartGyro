package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.app.a;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.DecorToolbar;
import android.support.v7.widget.ScrollingTabContainerView;
import android.support.v7.widget.Toolbar;
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
import com.org.android.view.ViewCompat;
import com.org.android.view.ViewPropertyAnimatorCompat;
import com.org.android.view.ViewPropertyAnimatorListener;
import com.org.android.view.ViewPropertyAnimatorUpdateListener;
import com.org.v4.util.R$attr;
import com.org.v4.util.R$id;
import com.org.v4.util.R$styleable;
import com.org.v4.view.ActionBarPolicy;
import com.org.v4.view.ActionMode;
import com.org.v4.view.SupportMenuInflater;
import com.org.v4.view.ViewPropertyAnimatorCompatSet;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class WindowDecorActionBar extends ActionBar implements ActionBarOverlayLayout.a {
    private static final Interpolator sHideInterpolator = new AccelerateInterpolator();
    private static final Interpolator sShowInterpolator = new DecelerateInterpolator();
    ActionModeImpl mActionMode;
    private Activity mActivity;
    ActionBarContainer mContainerView;
    boolean mContentAnimations = true;
    View mContentView;
    Context mContext;
    ActionBarContextView mContextView;
    private int mCurWindowVisibility = 0;
    ViewPropertyAnimatorCompatSet mCurrentShowAnim;
    DecorToolbar mDecorToolbar;
    ActionMode mDeferredDestroyActionMode;
    ActionMode.Callback mDeferredModeDestroyCallback;
    private Dialog mDialog;
    private boolean mDisplayHomeAsUpSet;
    private boolean mHasEmbeddedTabs;
    boolean mHiddenByApp;
    boolean mHiddenBySystem;
    final ViewPropertyAnimatorListener mHideListener = new ViewPropertyAnimatorCompatSet$1(this);
    boolean mHideOnContentScroll;
    private boolean mLastMenuVisibility;
    private ArrayList<a.b> mMenuVisibilityListeners = new ArrayList();
    private boolean mNowShowing = true;
    ActionBarOverlayLayout mOverlayLayout;
    private int mSavedTabPosition = -1;
    private boolean mShowHideAnimationEnabled;
    final ViewPropertyAnimatorListener mShowListener = new ActionBarOverlayLayout$1(this);
    private boolean mShowingForMode;
    ScrollingTabContainerView mTabScrollView;
    private ArrayList<Object> mTabs = new ArrayList();
    private Context mThemedContext;
    final ViewPropertyAnimatorUpdateListener mUpdateListener = new ViewPropertyAnimatorUpdateListener() {
        public void onAnimationUpdate(View view) {
            ((View) WindowDecorActionBar.this.mContainerView.getParent()).invalidate();
        }
    };

    public class ActionModeImpl extends ActionMode implements MenuBuilder.Callback {
        private final Context mActionModeContext;
        private ActionMode.Callback mCallback;
        private WeakReference<View> mCustomView;
        private final MenuBuilder mMenu;

        public ActionModeImpl(Context context, ActionMode.Callback callback) {
            this.mActionModeContext = context;
            this.mCallback = callback;
            MenuBuilder $r4 = new MenuBuilder(context);
            $r4.setDefaultShowAsAction(1);
            this.mMenu = $r4;
            this.mMenu.setCallback(this);
        }

        public boolean dispatchOnCreate() {
            this.mMenu.stopDispatchingItemsChanged();
            try {
                return this.mCallback.onCreateActionMode(this, this.mMenu);
            } finally {
                this.mMenu.startDispatchingItemsChanged();
            }
        }

        public void finish() {
            WindowDecorActionBar $r1 = WindowDecorActionBar.this;
            if ($r1.mActionMode == this) {
                if (!WindowDecorActionBar.checkShowingFlags($r1.mHiddenByApp, $r1.mHiddenBySystem, false)) {
                    WindowDecorActionBar $r12 = WindowDecorActionBar.this;
                    $r12.mDeferredDestroyActionMode = this;
                    $r12.mDeferredModeDestroyCallback = this.mCallback;
                } else {
                    this.mCallback.onDestroyActionMode(this);
                }
                this.mCallback = null;
                WindowDecorActionBar.this.animateToMode(false);
                WindowDecorActionBar.this.mContextView.closeMode();
                WindowDecorActionBar.this.mDecorToolbar.getViewGroup().sendAccessibilityEvent(32);
                WindowDecorActionBar $r13 = WindowDecorActionBar.this;
                $r13.mOverlayLayout.setHideOnContentScrollEnabled($r13.mHideOnContentScroll);
                WindowDecorActionBar.this.mActionMode = null;
            }
        }

        public View getCustomView() {
            WeakReference $r2 = this.mCustomView;
            if ($r2 != null) {
                return (View) $r2.get();
            }
            return null;
        }

        public Menu getMenu() {
            return this.mMenu;
        }

        public MenuInflater getMenuInflater() {
            return new SupportMenuInflater(this.mActionModeContext);
        }

        public CharSequence getSubtitle() {
            return WindowDecorActionBar.this.mContextView.getSubtitle();
        }

        public CharSequence getTitle() {
            return WindowDecorActionBar.this.mContextView.getTitle();
        }

        public void invalidate() {
            if (WindowDecorActionBar.this.mActionMode == this) {
                this.mMenu.stopDispatchingItemsChanged();
                try {
                    this.mCallback.onPrepareActionMode(this, this.mMenu);
                } finally {
                    this.mMenu.startDispatchingItemsChanged();
                }
            }
        }

        public boolean isTitleOptional() {
            return WindowDecorActionBar.this.mContextView.isTitleOptional();
        }

        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            ActionMode.Callback $r3 = this.mCallback;
            if ($r3 != null) {
                return $r3.onActionItemClicked(this, menuItem);
            }
            return false;
        }

        public void onMenuModeChange(MenuBuilder menuBuilder) {
            if (this.mCallback != null) {
                invalidate();
                WindowDecorActionBar.this.mContextView.showOverflowMenu();
            }
        }

        public void setCustomView(View view) {
            WindowDecorActionBar.this.mContextView.setCustomView(view);
            this.mCustomView = new WeakReference(view);
        }

        public void setSubtitle(int i) {
            setSubtitle((CharSequence) WindowDecorActionBar.this.mContext.getResources().getString(i));
        }

        public void setSubtitle(CharSequence charSequence) {
            WindowDecorActionBar.this.mContextView.setSubtitle(charSequence);
        }

        public void setTitle(int i) {
            setTitle((CharSequence) WindowDecorActionBar.this.mContext.getResources().getString(i));
        }

        public void setTitle(CharSequence charSequence) {
            WindowDecorActionBar.this.mContextView.setTitle(charSequence);
        }

        public void setTitleOptionalHint(boolean z) {
            super.setTitleOptionalHint(z);
            WindowDecorActionBar.this.mContextView.setTitleOptional(z);
        }
    }

    public WindowDecorActionBar(Activity activity, boolean z) {
        this.mActivity = activity;
        View $r7 = activity.getWindow().getDecorView();
        init($r7);
        if (!z) {
            this.mContentView = $r7.findViewById(16908290);
        }
    }

    public WindowDecorActionBar(Dialog dialog) {
        this.mDialog = dialog;
        init(dialog.getWindow().getDecorView());
    }

    static boolean checkShowingFlags(boolean z, boolean z2, boolean z3) {
        if (z3) {
            return true;
        }
        return !z && !z2;
    }

    private DecorToolbar getDecorToolbar(View view) {
        if (view instanceof DecorToolbar) {
            return (DecorToolbar) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        StringBuilder $r1 = new StringBuilder();
        $r1.append("Can't make a decor toolbar out of ");
        $r1.append(view != null ? view.getClass().getSimpleName() : "null");
        throw new IllegalStateException($r1.toString());
    }

    private void hideForActionMode() {
        if (this.mShowingForMode) {
            this.mShowingForMode = false;
            ActionBarOverlayLayout $r1 = this.mOverlayLayout;
            if ($r1 != null) {
                $r1.setShowingForActionMode(false);
            }
            updateVisibility(false);
        }
    }

    private void init(View view) {
        this.mOverlayLayout = (ActionBarOverlayLayout) view.findViewById(R$id.decor_content_parent);
        ActionBarOverlayLayout $r3 = this.mOverlayLayout;
        if ($r3 != null) {
            $r3.setActionBarVisibilityCallback(this);
        }
        this.mDecorToolbar = getDecorToolbar(view.findViewById(R$id.action_bar));
        this.mContextView = (ActionBarContextView) view.findViewById(R$id.action_context_bar);
        this.mContainerView = (ActionBarContainer) view.findViewById(R$id.action_bar_container);
        DecorToolbar $r4 = this.mDecorToolbar;
        if ($r4 == null || this.mContextView == null || this.mContainerView == null) {
            throw new IllegalStateException(G.class.getSimpleName() + " can only be used " + "with a compatible window decor layout");
        }
        this.mContext = $r4.getContext();
        boolean $z0 = (this.mDecorToolbar.getDisplayOptions() & 4) != 0;
        if ($z0) {
            this.mDisplayHomeAsUpSet = true;
        }
        ActionBarPolicy $r8 = ActionBarPolicy.get(this.mContext);
        setHomeButtonEnabled($r8.enableHomeButtonByDefault() || $z0);
        setHasEmbeddedTabs($r8.hasEmbeddedTabs());
        TypedArray $r10 = this.mContext.obtainStyledAttributes((AttributeSet) null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        if ($r10.getBoolean(R$styleable.ActionBar_hideOnContentScroll, false)) {
            setHideOnContentScrollEnabled(true);
        }
        int $i0 = $r10.getDimensionPixelSize(R$styleable.ActionBar_elevation, 0);
        if ($i0 != 0) {
            setElevation((float) $i0);
        }
        $r10.recycle();
    }

    private void setHasEmbeddedTabs(boolean z) {
        this.mHasEmbeddedTabs = z;
        if (!this.mHasEmbeddedTabs) {
            this.mDecorToolbar.setEmbeddedTabView((ScrollingTabContainerView) null);
            this.mContainerView.setTabContainer(this.mTabScrollView);
        } else {
            this.mContainerView.setTabContainer((ScrollingTabContainerView) null);
            this.mDecorToolbar.setEmbeddedTabView(this.mTabScrollView);
        }
        boolean $z0 = true;
        boolean $z1 = getNavigationMode() == 2;
        ScrollingTabContainerView $r3 = this.mTabScrollView;
        if ($r3 != null) {
            if ($z1) {
                $r3.setVisibility(0);
                ActionBarOverlayLayout $r4 = this.mOverlayLayout;
                if ($r4 != null) {
                    ViewCompat.requestApplyInsets($r4);
                }
            } else {
                $r3.setVisibility(8);
            }
        }
        this.mDecorToolbar.setCollapsible(!this.mHasEmbeddedTabs && $z1);
        ActionBarOverlayLayout $r42 = this.mOverlayLayout;
        if (this.mHasEmbeddedTabs || !$z1) {
            $z0 = false;
        }
        $r42.setHasNonEmbeddedTabs($z0);
    }

    private boolean show() {
        return ViewCompat.show(this.mContainerView);
    }

    private void showForActionMode() {
        if (!this.mShowingForMode) {
            this.mShowingForMode = true;
            ActionBarOverlayLayout $r1 = this.mOverlayLayout;
            if ($r1 != null) {
                $r1.setShowingForActionMode(true);
            }
            updateVisibility(false);
        }
    }

    private void updateVisibility(boolean z) {
        if (checkShowingFlags(this.mHiddenByApp, this.mHiddenBySystem, this.mShowingForMode)) {
            if (!this.mNowShowing) {
                this.mNowShowing = true;
                doShow(z);
            }
        } else if (this.mNowShowing) {
            this.mNowShowing = false;
            doHide(z);
        }
    }

    public void animateToMode(boolean z) {
        ViewPropertyAnimatorCompat $r4;
        ViewPropertyAnimatorCompat $r2;
        if (z) {
            showForActionMode();
        } else {
            hideForActionMode();
        }
        if (show()) {
            if (z) {
                $r2 = this.mDecorToolbar.setupAnimatorToVisibility(4, 100);
                $r4 = this.mContextView.setupAnimatorToVisibility(0, 200);
            } else {
                $r4 = this.mDecorToolbar.setupAnimatorToVisibility(0, 200);
                $r2 = this.mContextView.setupAnimatorToVisibility(8, 100);
            }
            ViewPropertyAnimatorCompatSet $r5 = new ViewPropertyAnimatorCompatSet();
            $r5.playSequentially($r2, $r4);
            $r5.start();
        } else if (z) {
            this.mDecorToolbar.setVisibility(4);
            this.mContextView.setVisibility(0);
        } else {
            this.mDecorToolbar.setVisibility(0);
            this.mContextView.setVisibility(8);
        }
    }

    public boolean collapseActionView() {
        DecorToolbar $r1 = this.mDecorToolbar;
        if ($r1 == null || !$r1.hasExpandedActionView()) {
            return false;
        }
        this.mDecorToolbar.collapseActionView();
        return true;
    }

    /* access modifiers changed from: package-private */
    public void completeDeferredDestroyActionMode() {
        ActionMode.Callback $r2 = this.mDeferredModeDestroyCallback;
        if ($r2 != null) {
            $r2.onDestroyActionMode(this.mDeferredDestroyActionMode);
            this.mDeferredDestroyActionMode = null;
            this.mDeferredModeDestroyCallback = null;
        }
    }

    public void dispatchMenuVisibilityChanged(boolean z) {
        if (z != this.mLastMenuVisibility) {
            this.mLastMenuVisibility = z;
            int $i0 = this.mMenuVisibilityListeners.size();
            for (int $i1 = 0; $i1 < $i0; $i1++) {
                this.mMenuVisibilityListeners.get($i1).onMenuVisibilityChanged(z);
            }
        }
    }

    public void doHide(boolean z) {
        View $r6;
        ViewPropertyAnimatorCompatSet $r2 = this.mCurrentShowAnim;
        if ($r2 != null) {
            $r2.cancel();
        }
        if (this.mCurWindowVisibility != 0 || (!this.mShowHideAnimationEnabled && !z)) {
            this.mHideListener.onAnimationEnd((View) null);
            return;
        }
        this.mContainerView.setAlpha(1.0f);
        this.mContainerView.setTransitioning(true);
        ViewPropertyAnimatorCompatSet $r22 = new ViewPropertyAnimatorCompatSet();
        float $f0 = (float) (-this.mContainerView.getHeight());
        if (z) {
            int[] $r3 = {0, 0};
            this.mContainerView.getLocationInWindow($r3);
            $f0 -= (float) $r3[1];
        }
        ViewPropertyAnimatorCompat $r4 = ViewCompat.animate(this.mContainerView);
        $r4.translationY($f0);
        $r4.cancel(this.mUpdateListener);
        $r22.play($r4);
        if (this.mContentAnimations && ($r6 = this.mContentView) != null) {
            ViewPropertyAnimatorCompat $r42 = ViewCompat.animate($r6);
            $r42.translationY($f0);
            $r22.play($r42);
        }
        $r22.setInterpolator(sHideInterpolator);
        $r22.setDuration(250);
        ViewPropertyAnimatorListener $r8 = this.mHideListener;
        ViewPropertyAnimatorListener viewPropertyAnimatorListener = $r8;
        $r22.setListener($r8);
        this.mCurrentShowAnim = $r22;
        $r22.start();
    }

    public void doShow(boolean z) {
        View $r6;
        View $r62;
        ViewPropertyAnimatorCompatSet $r2 = this.mCurrentShowAnim;
        if ($r2 != null) {
            $r2.cancel();
        }
        this.mContainerView.setVisibility(0);
        if (this.mCurWindowVisibility != 0 || (!this.mShowHideAnimationEnabled && !z)) {
            this.mContainerView.setAlpha(1.0f);
            this.mContainerView.setTranslationY(0.0f);
            if (this.mContentAnimations && ($r6 = this.mContentView) != null) {
                $r6.setTranslationY(0.0f);
            }
            this.mShowListener.onAnimationEnd((View) null);
        } else {
            this.mContainerView.setTranslationY(0.0f);
            float $f0 = (float) (-this.mContainerView.getHeight());
            if (z) {
                int[] $r4 = {0, 0};
                this.mContainerView.getLocationInWindow($r4);
                $f0 -= (float) $r4[1];
            }
            this.mContainerView.setTranslationY($f0);
            ViewPropertyAnimatorCompatSet $r22 = new ViewPropertyAnimatorCompatSet();
            ViewPropertyAnimatorCompat $r5 = ViewCompat.animate(this.mContainerView);
            $r5.translationY(0.0f);
            $r5.cancel(this.mUpdateListener);
            $r22.play($r5);
            if (this.mContentAnimations && ($r62 = this.mContentView) != null) {
                $r62.setTranslationY($f0);
                ViewPropertyAnimatorCompat $r52 = ViewCompat.animate(this.mContentView);
                $r52.translationY(0.0f);
                $r22.play($r52);
            }
            $r22.setInterpolator(sShowInterpolator);
            $r22.setDuration(250);
            ViewPropertyAnimatorListener $r8 = this.mShowListener;
            ViewPropertyAnimatorListener viewPropertyAnimatorListener = $r8;
            $r22.setListener($r8);
            this.mCurrentShowAnim = $r22;
            $r22.start();
        }
        ActionBarOverlayLayout $r9 = this.mOverlayLayout;
        if ($r9 != null) {
            ViewCompat.requestApplyInsets($r9);
        }
    }

    public void enableContentAnimations(boolean z) {
        this.mContentAnimations = z;
    }

    public int getNavigationMode() {
        return this.mDecorToolbar.getNavigationMode();
    }

    public Context getThemedContext() {
        if (this.mThemedContext == null) {
            TypedValue $r3 = new TypedValue();
            this.mContext.getTheme().resolveAttribute(R$attr.actionBarWidgetTheme, $r3, true);
            int $i0 = $r3.resourceId;
            if ($i0 != 0) {
                this.mThemedContext = new ContextThemeWrapper(this.mContext, $i0);
            } else {
                this.mThemedContext = this.mContext;
            }
        }
        return this.mThemedContext;
    }

    public void hideForSystem() {
        if (!this.mHiddenBySystem) {
            this.mHiddenBySystem = true;
            updateVisibility(true);
        }
    }

    public void onContentScrollStarted() {
        ViewPropertyAnimatorCompatSet $r1 = this.mCurrentShowAnim;
        if ($r1 != null) {
            $r1.cancel();
            this.mCurrentShowAnim = null;
        }
    }

    public void onContentScrollStopped() {
    }

    public boolean onKeyShortcut(int i, KeyEvent keyEvent) {
        Menu $r3;
        ActionModeImpl $r2 = this.mActionMode;
        if ($r2 == null || ($r3 = $r2.getMenu()) == null) {
            return false;
        }
        boolean $z0 = true;
        if (KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() == 1) {
            $z0 = false;
        }
        $r3.setQwertyMode($z0);
        return $r3.performShortcut(i, keyEvent, 0);
    }

    public void onWindowVisibilityChanged(int i) {
        this.mCurWindowVisibility = i;
    }

    public void setDefaultDisplayHomeAsUpEnabled(boolean z) {
        if (!this.mDisplayHomeAsUpSet) {
            setDisplayHomeAsUpEnabled(z);
        }
    }

    public void setDisplayHomeAsUpEnabled(boolean z) {
        setDisplayOptions(z ? (byte) 4 : 0, 4);
    }

    public void setDisplayOptions(int i, int i2) {
        int $i0 = this.mDecorToolbar.getDisplayOptions();
        if ((i2 & 4) != 0) {
            this.mDisplayHomeAsUpSet = true;
        }
        this.mDecorToolbar.setDisplayOptions((i & i2) | ((~i2) & $i0));
    }

    public void setElevation(float f) {
        ViewCompat.setElevation(this.mContainerView, f);
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        if (!z || this.mOverlayLayout.isInOverlayMode()) {
            this.mHideOnContentScroll = z;
            this.mOverlayLayout.setHideOnContentScrollEnabled(z);
            return;
        }
        throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
    }

    public void setHomeButtonEnabled(boolean z) {
        this.mDecorToolbar.setHomeButtonEnabled(z);
    }

    public void setShowHideAnimationEnabled(boolean z) {
        ViewPropertyAnimatorCompatSet $r1;
        this.mShowHideAnimationEnabled = z;
        if (!z && ($r1 = this.mCurrentShowAnim) != null) {
            $r1.cancel();
        }
    }

    public void setWindowTitle(CharSequence charSequence) {
        this.mDecorToolbar.setWindowTitle(charSequence);
    }

    public void showForSystem() {
        if (this.mHiddenBySystem) {
            this.mHiddenBySystem = false;
            updateVisibility(true);
        }
    }

    public ActionMode startActionMode(ActionMode.Callback callback) {
        ActionModeImpl $r2 = this.mActionMode;
        if ($r2 != null) {
            $r2.finish();
        }
        this.mOverlayLayout.setHideOnContentScrollEnabled(false);
        this.mContextView.killMode();
        ActionModeImpl $r22 = new ActionModeImpl(this.mContextView.getContext(), callback);
        if (!$r22.dispatchOnCreate()) {
            return null;
        }
        this.mActionMode = $r22;
        $r22.invalidate();
        this.mContextView.initForMode($r22);
        animateToMode(true);
        this.mContextView.sendAccessibilityEvent(32);
        return $r22;
    }
}

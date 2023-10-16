package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.ActionMenuItem;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.l$a;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.org.android.view.ViewCompat;
import com.org.android.view.ViewPropertyAnimatorCompat;
import com.org.android.view.ViewPropertyAnimatorListenerAdapter;
import com.org.v4.text.view.Resources;
import com.org.v4.util.R$attr;
import com.org.v4.util.R$drawable;
import com.org.v4.util.R$id;
import com.org.v4.util.R$string;
import com.org.v4.util.R$styleable;

public class ToolbarWidgetWrapper implements DecorToolbar {
    private ActionMenuPresenter mActionMenuPresenter;
    private View mCustomView;
    private int mDefaultNavigationContentDescription;
    private Drawable mDefaultNavigationIcon;
    private int mDisplayOpts;
    private CharSequence mHomeDescription;
    private Drawable mIcon;
    private Drawable mLogo;
    boolean mMenuPrepared;
    private Drawable mNavIcon;
    private int mNavigationMode;
    private CharSequence mSubtitle;
    private View mTabView;
    CharSequence mTitle;
    private boolean mTitleSet;
    Toolbar mToolbar;
    Window.Callback mWindowCallback;

    public ToolbarWidgetWrapper(Toolbar toolbar, boolean z) {
        this(toolbar, z, R$string.abc_action_bar_up_description, R$drawable.abc_ic_ab_back_material);
    }

    public ToolbarWidgetWrapper(Toolbar toolbar, boolean z, int i, int i2) {
        Drawable $r3;
        this.mNavigationMode = 0;
        this.mDefaultNavigationContentDescription = 0;
        this.mToolbar = toolbar;
        this.mTitle = toolbar.getTitle();
        this.mSubtitle = toolbar.getSubtitle();
        this.mTitleSet = this.mTitle != null;
        this.mNavIcon = toolbar.getNavigationIcon();
        TintTypedArray $r6 = TintTypedArray.obtainStyledAttributes(toolbar.getContext(), (AttributeSet) null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        this.mDefaultNavigationIcon = $r6.getDrawable(R$styleable.ActionBar_homeAsUpIndicator);
        if (z) {
            CharSequence $r2 = $r6.getText(R$styleable.ActionBar_title);
            if (!TextUtils.isEmpty($r2)) {
                setTitle($r2);
            }
            CharSequence $r22 = $r6.getText(R$styleable.ActionBar_subtitle);
            if (!TextUtils.isEmpty($r22)) {
                setSubtitle($r22);
            }
            Drawable $r32 = $r6.getDrawable(R$styleable.ActionBar_logo);
            if ($r32 != null) {
                setLogo($r32);
            }
            Drawable $r33 = $r6.getDrawable(R$styleable.ActionBar_icon);
            if ($r33 != null) {
                setIcon($r33);
            }
            if (this.mNavIcon == null && ($r3 = this.mDefaultNavigationIcon) != null) {
                setNavigationIcon($r3);
            }
            setDisplayOptions($r6.getInt(R$styleable.ActionBar_displayOptions, 0));
            int $i1 = $r6.getResourceId(R$styleable.ActionBar_customNavigationLayout, 0);
            if ($i1 != 0) {
                setCustomView(LayoutInflater.from(this.mToolbar.getContext()).inflate($i1, this.mToolbar, false));
                setDisplayOptions(this.mDisplayOpts | 16);
            }
            int $i12 = $r6.getLayoutDimension(R$styleable.ActionBar_height, 0);
            if ($i12 > 0) {
                ViewGroup.LayoutParams $r9 = this.mToolbar.getLayoutParams();
                $r9.height = $i12;
                this.mToolbar.setLayoutParams($r9);
            }
            int $i13 = $r6.getDimensionPixelOffset(R$styleable.ActionBar_contentInsetStart, -1);
            int $i2 = $r6.getDimensionPixelOffset(R$styleable.ActionBar_contentInsetEnd, -1);
            if ($i13 >= 0 || $i2 >= 0) {
                this.mToolbar.setContentInsetsRelative(Math.max($i13, 0), Math.max($i2, 0));
            }
            int $i14 = $r6.getResourceId(R$styleable.ActionBar_titleTextStyle, 0);
            if ($i14 != 0) {
                Toolbar $r1 = this.mToolbar;
                $r1.setTitleTextAppearance($r1.getContext(), $i14);
            }
            int $i15 = $r6.getResourceId(R$styleable.ActionBar_subtitleTextStyle, 0);
            if ($i15 != 0) {
                Toolbar $r12 = this.mToolbar;
                $r12.setSubtitleTextAppearance($r12.getContext(), $i15);
            }
            int $i16 = $r6.getResourceId(R$styleable.ActionBar_popupTheme, 0);
            if ($i16 != 0) {
                this.mToolbar.setPopupTheme($i16);
            }
        } else {
            this.mDisplayOpts = detectDisplayOptions();
        }
        $r6.recycle();
        setDefaultNavigationContentDescription(i);
        this.mHomeDescription = this.mToolbar.getNavigationContentDescription();
        this.mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            final ActionMenuItem mNavItem;

            {
                ToolbarWidgetWrapper $r3 = ToolbarWidgetWrapper.this;
                ToolbarWidgetWrapper toolbarWidgetWrapper = $r3;
                Context $r2 = $r3.mToolbar.getContext();
                ToolbarWidgetWrapper $r32 = ToolbarWidgetWrapper.this;
                this.mNavItem = new ActionMenuItem($r2, 0, 16908332, 0, 0, $r32.mTitle);
            }

            public void onClick(View view) {
                ToolbarWidgetWrapper $r4 = ToolbarWidgetWrapper.this;
                Window.Callback $r1 = $r4.mWindowCallback;
                if ($r1 != null && $r4.mMenuPrepared) {
                    $r1.onMenuItemSelected(0, this.mNavItem);
                }
            }
        });
    }

    private int detectDisplayOptions() {
        if (this.mToolbar.getNavigationIcon() == null) {
            return 11;
        }
        this.mDefaultNavigationIcon = this.mToolbar.getNavigationIcon();
        return 15;
    }

    private void setTitleInt(CharSequence charSequence) {
        this.mTitle = charSequence;
        if ((this.mDisplayOpts & 8) != 0) {
            this.mToolbar.setTitle(charSequence);
        }
    }

    private void updateHomeAccessibility() {
        if ((this.mDisplayOpts & 4) == 0) {
            return;
        }
        if (TextUtils.isEmpty(this.mHomeDescription)) {
            this.mToolbar.setNavigationContentDescription(this.mDefaultNavigationContentDescription);
        } else {
            this.mToolbar.setNavigationContentDescription(this.mHomeDescription);
        }
    }

    private void updateNavigationIcon() {
        Drawable $r2;
        Toolbar $r1;
        if ((this.mDisplayOpts & 4) != 0) {
            $r1 = this.mToolbar;
            $r2 = this.mNavIcon;
            if ($r2 == null) {
                $r2 = this.mDefaultNavigationIcon;
            }
        } else {
            $r1 = this.mToolbar;
            $r2 = null;
        }
        $r1.setNavigationIcon($r2);
    }

    private void updateToolbarLogo() {
        Drawable $r1;
        int $i0 = this.mDisplayOpts;
        if (($i0 & 2) == 0) {
            $r1 = null;
        } else if (($i0 & 1) == 0 || ($r1 = this.mLogo) == null) {
            $r1 = this.mIcon;
        }
        this.mToolbar.setLogo($r1);
    }

    public boolean canShowOverflowMenu() {
        return this.mToolbar.canShowOverflowMenu();
    }

    public void collapseActionView() {
        this.mToolbar.collapseActionView();
    }

    public void dismissPopupMenus() {
        this.mToolbar.dismissPopupMenus();
    }

    public Context getContext() {
        return this.mToolbar.getContext();
    }

    public int getDisplayOptions() {
        return this.mDisplayOpts;
    }

    public int getNavigationMode() {
        return this.mNavigationMode;
    }

    public CharSequence getTitle() {
        return this.mToolbar.getTitle();
    }

    public ViewGroup getViewGroup() {
        return this.mToolbar;
    }

    public boolean hasExpandedActionView() {
        return this.mToolbar.hasExpandedActionView();
    }

    public boolean hideOverflowMenu() {
        return this.mToolbar.hideOverflowMenu();
    }

    public void initIndeterminateProgress() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    public void initProgress() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    public boolean isOverflowMenuShowPending() {
        return this.mToolbar.isOverflowMenuShowPending();
    }

    public boolean isOverflowMenuShowing() {
        return this.mToolbar.isOverflowMenuShowing();
    }

    public void setCollapsible(boolean z) {
        this.mToolbar.setCollapsible(z);
    }

    public void setCustomView(View view) {
        View $r2 = this.mCustomView;
        if (!($r2 == null || (this.mDisplayOpts & 16) == 0)) {
            this.mToolbar.removeView($r2);
        }
        this.mCustomView = view;
        if (view != null && (this.mDisplayOpts & 16) != 0) {
            this.mToolbar.addView(this.mCustomView);
        }
    }

    public void setDefaultNavigationContentDescription(int i) {
        if (i != this.mDefaultNavigationContentDescription) {
            this.mDefaultNavigationContentDescription = i;
            if (TextUtils.isEmpty(this.mToolbar.getNavigationContentDescription())) {
                setNavigationContentDescription(this.mDefaultNavigationContentDescription);
            }
        }
    }

    public void setDisplayOptions(int i) {
        View $r3;
        CharSequence $r2;
        Toolbar $r1;
        int $i1 = this.mDisplayOpts ^ i;
        this.mDisplayOpts = i;
        if ($i1 != 0) {
            if (($i1 & 4) != 0) {
                if ((i & 4) != 0) {
                    updateHomeAccessibility();
                }
                updateNavigationIcon();
            }
            if (($i1 & 3) != 0) {
                updateToolbarLogo();
            }
            if (($i1 & 8) != 0) {
                if ((i & 8) != 0) {
                    this.mToolbar.setTitle(this.mTitle);
                    $r1 = this.mToolbar;
                    $r2 = this.mSubtitle;
                } else {
                    $r2 = null;
                    this.mToolbar.setTitle((CharSequence) null);
                    $r1 = this.mToolbar;
                }
                $r1.setSubtitle($r2);
            }
            if (($i1 & 16) != 0 && ($r3 = this.mCustomView) != null) {
                if ((i & 16) != 0) {
                    this.mToolbar.addView($r3);
                } else {
                    this.mToolbar.removeView($r3);
                }
            }
        }
    }

    public void setEmbeddedTabView(ScrollingTabContainerView scrollingTabContainerView) {
        Toolbar $r4;
        View $r2 = this.mTabView;
        if ($r2 != null && $r2.getParent() == ($r4 = this.mToolbar)) {
            $r4.removeView(this.mTabView);
        }
        this.mTabView = scrollingTabContainerView;
        if (scrollingTabContainerView != null && this.mNavigationMode == 2) {
            this.mToolbar.addView(this.mTabView, 0);
            Toolbar.b $r6 = (Toolbar.b) this.mTabView.getLayoutParams();
            $r6.width = -2;
            $r6.height = -2;
            $r6.gravity = 8388691;
            scrollingTabContainerView.setAllowCollapse(true);
        }
    }

    public void setHomeButtonEnabled(boolean z) {
    }

    public void setIcon(int i) {
        setIcon(i != 0 ? Resources.getDrawable(getContext(), i) : null);
    }

    public void setIcon(Drawable drawable) {
        this.mIcon = drawable;
        updateToolbarLogo();
    }

    public void setLogo(int i) {
        setLogo(i != 0 ? Resources.getDrawable(getContext(), i) : null);
    }

    public void setLogo(Drawable drawable) {
        this.mLogo = drawable;
        updateToolbarLogo();
    }

    public void setMenu(Menu menu, l$a l_a) {
        if (this.mActionMenuPresenter == null) {
            this.mActionMenuPresenter = new ActionMenuPresenter(this.mToolbar.getContext());
            this.mActionMenuPresenter.setId(R$id.action_menu_presenter);
        }
        this.mActionMenuPresenter.a(l_a);
        this.mToolbar.setMenu((MenuBuilder) menu, this.mActionMenuPresenter);
    }

    public void setMenuPrepared() {
        this.mMenuPrepared = true;
    }

    public void setNavigationContentDescription(int i) {
        setNavigationContentDescription((CharSequence) i == 0 ? null : getContext().getString(i));
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        this.mHomeDescription = charSequence;
        updateHomeAccessibility();
    }

    public void setNavigationIcon(Drawable drawable) {
        this.mNavIcon = drawable;
        updateNavigationIcon();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.mSubtitle = charSequence;
        if ((this.mDisplayOpts & 8) != 0) {
            this.mToolbar.setSubtitle(charSequence);
        }
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitleSet = true;
        setTitleInt(charSequence);
    }

    public void setVisibility(int i) {
        this.mToolbar.setVisibility(i);
    }

    public void setWindowCallback(Window.Callback callback) {
        this.mWindowCallback = callback;
    }

    public void setWindowTitle(CharSequence charSequence) {
        if (!this.mTitleSet) {
            setTitleInt(charSequence);
        }
    }

    public ViewPropertyAnimatorCompat setupAnimatorToVisibility(final int i, long j) {
        ViewPropertyAnimatorCompat $r1 = ViewCompat.animate(this.mToolbar);
        $r1.alpha(i == 0 ? 1.0f : 0.0f);
        $r1.setDuration(j);
        $r1.setListener(new ViewPropertyAnimatorListenerAdapter() {
            private boolean mCanceled = false;

            public void onAnimationCancel(View view) {
                this.mCanceled = true;
            }

            public void onAnimationEnd(View view) {
                if (!this.mCanceled) {
                    ToolbarWidgetWrapper.this.mToolbar.setVisibility(i);
                }
            }

            public void onAnimationStart(View view) {
                ToolbarWidgetWrapper.this.mToolbar.setVisibility(0);
            }
        });
        return $r1;
    }

    public boolean showOverflowMenu() {
        return this.mToolbar.showOverflowMenu();
    }
}

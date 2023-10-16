package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.b;
import android.support.v7.view.menu.l.a;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window.Callback;
import com.org.android.view.ViewCompat;
import com.org.android.view.ViewPropertyAnimatorCompat;
import com.org.v4.text.view.Resources;
import com.org.v4.util.R.attr;
import com.org.v4.util.R.drawable;
import com.org.v4.util.R.id;
import com.org.v4.util.R.string;
import com.org.v4.util.R.styleable;

public class ToolbarWidgetWrapper
  implements DecorToolbar
{
  private ActionMenuPresenter mActionMenuPresenter;
  private View mCustomView;
  private int mDefaultNavigationContentDescription = 0;
  private Drawable mDefaultNavigationIcon;
  private int mDisplayOpts;
  private CharSequence mHomeDescription;
  private Drawable mIcon;
  private Drawable mLogo;
  boolean mMenuPrepared;
  private Drawable mNavIcon;
  private int mNavigationMode = 0;
  private CharSequence mSubtitle;
  private View mTabView;
  CharSequence mTitle;
  private boolean mTitleSet;
  Toolbar mToolbar;
  Window.Callback mWindowCallback;
  
  public ToolbarWidgetWrapper(Toolbar paramToolbar, boolean paramBoolean)
  {
    this(paramToolbar, paramBoolean, R.string.abc_action_bar_up_description, R.drawable.abc_ic_ab_back_material);
  }
  
  public ToolbarWidgetWrapper(Toolbar paramToolbar, boolean paramBoolean, int paramInt1, int paramInt2)
  {
    mToolbar = paramToolbar;
    mTitle = paramToolbar.getTitle();
    mSubtitle = paramToolbar.getSubtitle();
    boolean bool;
    if (mTitle != null) {
      bool = true;
    } else {
      bool = false;
    }
    mTitleSet = bool;
    mNavIcon = paramToolbar.getNavigationIcon();
    paramToolbar = TintTypedArray.obtainStyledAttributes(paramToolbar.getContext(), null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
    mDefaultNavigationIcon = paramToolbar.getDrawable(R.styleable.ActionBar_homeAsUpIndicator);
    if (paramBoolean)
    {
      Object localObject = paramToolbar.getText(R.styleable.ActionBar_title);
      if (!TextUtils.isEmpty((CharSequence)localObject)) {
        setTitle((CharSequence)localObject);
      }
      localObject = paramToolbar.getText(R.styleable.ActionBar_subtitle);
      if (!TextUtils.isEmpty((CharSequence)localObject)) {
        setSubtitle((CharSequence)localObject);
      }
      localObject = paramToolbar.getDrawable(R.styleable.ActionBar_logo);
      if (localObject != null) {
        setLogo((Drawable)localObject);
      }
      localObject = paramToolbar.getDrawable(R.styleable.ActionBar_icon);
      if (localObject != null) {
        setIcon((Drawable)localObject);
      }
      if (mNavIcon == null)
      {
        localObject = mDefaultNavigationIcon;
        if (localObject != null) {
          setNavigationIcon((Drawable)localObject);
        }
      }
      setDisplayOptions(paramToolbar.getInt(R.styleable.ActionBar_displayOptions, 0));
      paramInt2 = paramToolbar.getResourceId(R.styleable.ActionBar_customNavigationLayout, 0);
      if (paramInt2 != 0)
      {
        setCustomView(LayoutInflater.from(mToolbar.getContext()).inflate(paramInt2, mToolbar, false));
        setDisplayOptions(mDisplayOpts | 0x10);
      }
      paramInt2 = paramToolbar.getLayoutDimension(R.styleable.ActionBar_height, 0);
      if (paramInt2 > 0)
      {
        localObject = mToolbar.getLayoutParams();
        height = paramInt2;
        mToolbar.setLayoutParams((ViewGroup.LayoutParams)localObject);
      }
      paramInt2 = paramToolbar.getDimensionPixelOffset(R.styleable.ActionBar_contentInsetStart, -1);
      int i = paramToolbar.getDimensionPixelOffset(R.styleable.ActionBar_contentInsetEnd, -1);
      if ((paramInt2 >= 0) || (i >= 0)) {
        mToolbar.setContentInsetsRelative(Math.max(paramInt2, 0), Math.max(i, 0));
      }
      paramInt2 = paramToolbar.getResourceId(R.styleable.ActionBar_titleTextStyle, 0);
      if (paramInt2 != 0)
      {
        localObject = mToolbar;
        ((Toolbar)localObject).setTitleTextAppearance(((View)localObject).getContext(), paramInt2);
      }
      paramInt2 = paramToolbar.getResourceId(R.styleable.ActionBar_subtitleTextStyle, 0);
      if (paramInt2 != 0)
      {
        localObject = mToolbar;
        ((Toolbar)localObject).setSubtitleTextAppearance(((View)localObject).getContext(), paramInt2);
      }
      paramInt2 = paramToolbar.getResourceId(R.styleable.ActionBar_popupTheme, 0);
      if (paramInt2 != 0) {
        mToolbar.setPopupTheme(paramInt2);
      }
    }
    else
    {
      mDisplayOpts = detectDisplayOptions();
    }
    paramToolbar.recycle();
    setDefaultNavigationContentDescription(paramInt1);
    mHomeDescription = mToolbar.getNavigationContentDescription();
    mToolbar.setNavigationOnClickListener(new ToolbarWidgetWrapper.1(this));
  }
  
  private int detectDisplayOptions()
  {
    if (mToolbar.getNavigationIcon() != null)
    {
      mDefaultNavigationIcon = mToolbar.getNavigationIcon();
      return 15;
    }
    return 11;
  }
  
  private void setTitleInt(CharSequence paramCharSequence)
  {
    mTitle = paramCharSequence;
    if ((mDisplayOpts & 0x8) != 0) {
      mToolbar.setTitle(paramCharSequence);
    }
  }
  
  private void updateHomeAccessibility()
  {
    if ((mDisplayOpts & 0x4) != 0)
    {
      if (TextUtils.isEmpty(mHomeDescription))
      {
        mToolbar.setNavigationContentDescription(mDefaultNavigationContentDescription);
        return;
      }
      mToolbar.setNavigationContentDescription(mHomeDescription);
    }
  }
  
  private void updateNavigationIcon()
  {
    Toolbar localToolbar;
    Drawable localDrawable;
    if ((mDisplayOpts & 0x4) != 0)
    {
      localToolbar = mToolbar;
      localDrawable = mNavIcon;
      if (localDrawable == null) {
        localDrawable = mDefaultNavigationIcon;
      }
    }
    else
    {
      localToolbar = mToolbar;
      localDrawable = null;
    }
    localToolbar.setNavigationIcon(localDrawable);
  }
  
  private void updateToolbarLogo()
  {
    int i = mDisplayOpts;
    Drawable localDrawable;
    if ((i & 0x2) != 0)
    {
      if ((i & 0x1) != 0)
      {
        localDrawable = mLogo;
        if (localDrawable != null) {}
      }
      else
      {
        localDrawable = mIcon;
      }
    }
    else {
      localDrawable = null;
    }
    mToolbar.setLogo(localDrawable);
  }
  
  public boolean canShowOverflowMenu()
  {
    return mToolbar.canShowOverflowMenu();
  }
  
  public void collapseActionView()
  {
    mToolbar.collapseActionView();
  }
  
  public void dismissPopupMenus()
  {
    mToolbar.dismissPopupMenus();
  }
  
  public Context getContext()
  {
    return mToolbar.getContext();
  }
  
  public int getDisplayOptions()
  {
    return mDisplayOpts;
  }
  
  public int getNavigationMode()
  {
    return mNavigationMode;
  }
  
  public CharSequence getTitle()
  {
    return mToolbar.getTitle();
  }
  
  public ViewGroup getViewGroup()
  {
    return mToolbar;
  }
  
  public boolean hasExpandedActionView()
  {
    return mToolbar.hasExpandedActionView();
  }
  
  public boolean hideOverflowMenu()
  {
    return mToolbar.hideOverflowMenu();
  }
  
  public void initIndeterminateProgress()
  {
    Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
  }
  
  public void initProgress()
  {
    Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
  }
  
  public boolean isOverflowMenuShowPending()
  {
    return mToolbar.isOverflowMenuShowPending();
  }
  
  public boolean isOverflowMenuShowing()
  {
    return mToolbar.isOverflowMenuShowing();
  }
  
  public void setCollapsible(boolean paramBoolean)
  {
    mToolbar.setCollapsible(paramBoolean);
  }
  
  public void setCustomView(View paramView)
  {
    View localView = mCustomView;
    if ((localView != null) && ((mDisplayOpts & 0x10) != 0)) {
      mToolbar.removeView(localView);
    }
    mCustomView = paramView;
    if ((paramView != null) && ((mDisplayOpts & 0x10) != 0)) {
      mToolbar.addView(mCustomView);
    }
  }
  
  public void setDefaultNavigationContentDescription(int paramInt)
  {
    if (paramInt == mDefaultNavigationContentDescription) {
      return;
    }
    mDefaultNavigationContentDescription = paramInt;
    if (TextUtils.isEmpty(mToolbar.getNavigationContentDescription())) {
      setNavigationContentDescription(mDefaultNavigationContentDescription);
    }
  }
  
  public void setDisplayOptions(int paramInt)
  {
    int i = mDisplayOpts ^ paramInt;
    mDisplayOpts = paramInt;
    if (i != 0)
    {
      if ((i & 0x4) != 0)
      {
        if ((paramInt & 0x4) != 0) {
          updateHomeAccessibility();
        }
        updateNavigationIcon();
      }
      if ((i & 0x3) != 0) {
        updateToolbarLogo();
      }
      Object localObject;
      if ((i & 0x8) != 0)
      {
        CharSequence localCharSequence;
        if ((paramInt & 0x8) != 0)
        {
          mToolbar.setTitle(mTitle);
          localObject = mToolbar;
          localCharSequence = mSubtitle;
        }
        else
        {
          localObject = mToolbar;
          localCharSequence = null;
          ((Toolbar)localObject).setTitle(null);
          localObject = mToolbar;
        }
        ((Toolbar)localObject).setSubtitle(localCharSequence);
      }
      if ((i & 0x10) != 0)
      {
        localObject = mCustomView;
        if (localObject != null)
        {
          if ((paramInt & 0x10) != 0)
          {
            mToolbar.addView((View)localObject);
            return;
          }
          mToolbar.removeView((View)localObject);
        }
      }
    }
  }
  
  public void setEmbeddedTabView(ScrollingTabContainerView paramScrollingTabContainerView)
  {
    Object localObject = mTabView;
    if (localObject != null)
    {
      localObject = ((View)localObject).getParent();
      Toolbar localToolbar = mToolbar;
      if (localObject == localToolbar) {
        localToolbar.removeView(mTabView);
      }
    }
    mTabView = paramScrollingTabContainerView;
    if ((paramScrollingTabContainerView != null) && (mNavigationMode == 2))
    {
      mToolbar.addView(mTabView, 0);
      localObject = (Toolbar.b)mTabView.getLayoutParams();
      width = -2;
      height = -2;
      gravity = 8388691;
      paramScrollingTabContainerView.setAllowCollapse(true);
    }
  }
  
  public void setHomeButtonEnabled(boolean paramBoolean) {}
  
  public void setIcon(int paramInt)
  {
    Drawable localDrawable;
    if (paramInt != 0) {
      localDrawable = Resources.getDrawable(getContext(), paramInt);
    } else {
      localDrawable = null;
    }
    setIcon(localDrawable);
  }
  
  public void setIcon(Drawable paramDrawable)
  {
    mIcon = paramDrawable;
    updateToolbarLogo();
  }
  
  public void setLogo(int paramInt)
  {
    Drawable localDrawable;
    if (paramInt != 0) {
      localDrawable = Resources.getDrawable(getContext(), paramInt);
    } else {
      localDrawable = null;
    }
    setLogo(localDrawable);
  }
  
  public void setLogo(Drawable paramDrawable)
  {
    mLogo = paramDrawable;
    updateToolbarLogo();
  }
  
  public void setMenu(Menu paramMenu, l.a paramA)
  {
    if (mActionMenuPresenter == null)
    {
      mActionMenuPresenter = new ActionMenuPresenter(mToolbar.getContext());
      mActionMenuPresenter.setId(R.id.action_menu_presenter);
    }
    mActionMenuPresenter.a(paramA);
    mToolbar.setMenu((MenuBuilder)paramMenu, mActionMenuPresenter);
  }
  
  public void setMenuPrepared()
  {
    mMenuPrepared = true;
  }
  
  public void setNavigationContentDescription(int paramInt)
  {
    String str;
    if (paramInt == 0) {
      str = null;
    } else {
      str = getContext().getString(paramInt);
    }
    setNavigationContentDescription(str);
  }
  
  public void setNavigationContentDescription(CharSequence paramCharSequence)
  {
    mHomeDescription = paramCharSequence;
    updateHomeAccessibility();
  }
  
  public void setNavigationIcon(Drawable paramDrawable)
  {
    mNavIcon = paramDrawable;
    updateNavigationIcon();
  }
  
  public void setSubtitle(CharSequence paramCharSequence)
  {
    mSubtitle = paramCharSequence;
    if ((mDisplayOpts & 0x8) != 0) {
      mToolbar.setSubtitle(paramCharSequence);
    }
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    mTitleSet = true;
    setTitleInt(paramCharSequence);
  }
  
  public void setVisibility(int paramInt)
  {
    mToolbar.setVisibility(paramInt);
  }
  
  public void setWindowCallback(Window.Callback paramCallback)
  {
    mWindowCallback = paramCallback;
  }
  
  public void setWindowTitle(CharSequence paramCharSequence)
  {
    if (!mTitleSet) {
      setTitleInt(paramCharSequence);
    }
  }
  
  public ViewPropertyAnimatorCompat setupAnimatorToVisibility(int paramInt, long paramLong)
  {
    ViewPropertyAnimatorCompat localViewPropertyAnimatorCompat = ViewCompat.animate(mToolbar);
    float f;
    if (paramInt == 0) {
      f = 1.0F;
    } else {
      f = 0.0F;
    }
    localViewPropertyAnimatorCompat.alpha(f);
    localViewPropertyAnimatorCompat.setDuration(paramLong);
    localViewPropertyAnimatorCompat.setListener(new ToolbarWidgetWrapper.2(this, paramInt));
    return localViewPropertyAnimatorCompat;
  }
  
  public boolean showOverflowMenu()
  {
    return mToolbar.showOverflowMenu();
  }
}

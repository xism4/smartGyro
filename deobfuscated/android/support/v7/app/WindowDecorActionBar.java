package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.ActionBarOverlayLayout.a;
import android.support.v7.widget.DecorToolbar;
import android.support.v7.widget.ScrollingTabContainerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.org.android.view.ViewCompat;
import com.org.android.view.ViewPropertyAnimatorCompat;
import com.org.android.view.ViewPropertyAnimatorListener;
import com.org.android.view.ViewPropertyAnimatorUpdateListener;
import com.org.v4.util.R.attr;
import com.org.v4.util.R.id;
import com.org.v4.util.R.styleable;
import com.org.v4.view.ActionBarPolicy;
import com.org.v4.view.ActionMode;
import com.org.v4.view.ActionMode.Callback;
import com.org.v4.view.SupportMenuInflater;
import com.org.v4.view.ViewPropertyAnimatorCompatSet;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class WindowDecorActionBar
  extends ActionBar
  implements ActionBarOverlayLayout.a
{
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
  final ViewPropertyAnimatorListener mHideListener = new ViewPropertyAnimatorCompatSet.1(this);
  boolean mHideOnContentScroll;
  private boolean mLastMenuVisibility;
  private ArrayList<a.b> mMenuVisibilityListeners = new ArrayList();
  private boolean mNowShowing = true;
  ActionBarOverlayLayout mOverlayLayout;
  private int mSavedTabPosition = -1;
  private boolean mShowHideAnimationEnabled;
  final ViewPropertyAnimatorListener mShowListener = new ActionBarOverlayLayout.1(this);
  private boolean mShowingForMode;
  ScrollingTabContainerView mTabScrollView;
  private ArrayList<Object> mTabs = new ArrayList();
  private Context mThemedContext;
  final ViewPropertyAnimatorUpdateListener mUpdateListener = new ViewPropertyAnimatorUpdateListener()
  {
    public void onAnimationUpdate(View paramAnonymousView)
    {
      ((View)mContainerView.getParent()).invalidate();
    }
  };
  
  public WindowDecorActionBar(Activity paramActivity, boolean paramBoolean)
  {
    mActivity = paramActivity;
    paramActivity = paramActivity.getWindow().getDecorView();
    init(paramActivity);
    if (!paramBoolean) {
      mContentView = paramActivity.findViewById(16908290);
    }
  }
  
  public WindowDecorActionBar(Dialog paramDialog)
  {
    mDialog = paramDialog;
    init(paramDialog.getWindow().getDecorView());
  }
  
  static boolean checkShowingFlags(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if (paramBoolean3) {
      return true;
    }
    return (!paramBoolean1) && (!paramBoolean2);
  }
  
  private DecorToolbar getDecorToolbar(View paramView)
  {
    if ((paramView instanceof DecorToolbar)) {
      return (DecorToolbar)paramView;
    }
    if ((paramView instanceof Toolbar)) {
      return ((Toolbar)paramView).getWrapper();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Can't make a decor toolbar out of ");
    if (paramView != null) {
      paramView = paramView.getClass().getSimpleName();
    } else {
      paramView = "null";
    }
    localStringBuilder.append(paramView);
    throw new IllegalStateException(localStringBuilder.toString());
  }
  
  private void hideForActionMode()
  {
    if (mShowingForMode)
    {
      mShowingForMode = false;
      ActionBarOverlayLayout localActionBarOverlayLayout = mOverlayLayout;
      if (localActionBarOverlayLayout != null) {
        localActionBarOverlayLayout.setShowingForActionMode(false);
      }
      updateVisibility(false);
    }
  }
  
  private void init(View paramView)
  {
    mOverlayLayout = ((ActionBarOverlayLayout)paramView.findViewById(R.id.decor_content_parent));
    ActionBarOverlayLayout localActionBarOverlayLayout = mOverlayLayout;
    if (localActionBarOverlayLayout != null) {
      localActionBarOverlayLayout.setActionBarVisibilityCallback(this);
    }
    mDecorToolbar = getDecorToolbar(paramView.findViewById(R.id.action_bar));
    mContextView = ((ActionBarContextView)paramView.findViewById(R.id.action_context_bar));
    mContainerView = ((ActionBarContainer)paramView.findViewById(R.id.action_bar_container));
    paramView = mDecorToolbar;
    if ((paramView != null) && (mContextView != null) && (mContainerView != null))
    {
      mContext = paramView.getContext();
      if ((mDecorToolbar.getDisplayOptions() & 0x4) != 0) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 0) {
        mDisplayHomeAsUpSet = true;
      }
      paramView = ActionBarPolicy.get(mContext);
      boolean bool;
      if ((!paramView.enableHomeButtonByDefault()) && (i == 0)) {
        bool = false;
      } else {
        bool = true;
      }
      setHomeButtonEnabled(bool);
      setHasEmbeddedTabs(paramView.hasEmbeddedTabs());
      paramView = mContext.obtainStyledAttributes(null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
      if (paramView.getBoolean(R.styleable.ActionBar_hideOnContentScroll, false)) {
        setHideOnContentScrollEnabled(true);
      }
      int i = paramView.getDimensionPixelSize(R.styleable.ActionBar_elevation, 0);
      if (i != 0) {
        setElevation(i);
      }
      paramView.recycle();
      return;
    }
    paramView = new StringBuilder();
    paramView.append(G.class.getSimpleName());
    paramView.append(" can only be used ");
    paramView.append("with a compatible window decor layout");
    throw new IllegalStateException(paramView.toString());
  }
  
  private void setHasEmbeddedTabs(boolean paramBoolean)
  {
    mHasEmbeddedTabs = paramBoolean;
    if (!mHasEmbeddedTabs)
    {
      mDecorToolbar.setEmbeddedTabView(null);
      mContainerView.setTabContainer(mTabScrollView);
    }
    else
    {
      mContainerView.setTabContainer(null);
      mDecorToolbar.setEmbeddedTabView(mTabScrollView);
    }
    int i = getNavigationMode();
    boolean bool = true;
    if (i == 2) {
      i = 1;
    } else {
      i = 0;
    }
    Object localObject = mTabScrollView;
    if (localObject != null) {
      if (i != 0)
      {
        ((View)localObject).setVisibility(0);
        localObject = mOverlayLayout;
        if (localObject != null) {
          ViewCompat.requestApplyInsets((View)localObject);
        }
      }
      else
      {
        ((View)localObject).setVisibility(8);
      }
    }
    localObject = mDecorToolbar;
    if ((!mHasEmbeddedTabs) && (i != 0)) {
      paramBoolean = true;
    } else {
      paramBoolean = false;
    }
    ((DecorToolbar)localObject).setCollapsible(paramBoolean);
    localObject = mOverlayLayout;
    if ((!mHasEmbeddedTabs) && (i != 0)) {
      paramBoolean = bool;
    } else {
      paramBoolean = false;
    }
    ((ActionBarOverlayLayout)localObject).setHasNonEmbeddedTabs(paramBoolean);
  }
  
  private boolean show()
  {
    return ViewCompat.show(mContainerView);
  }
  
  private void showForActionMode()
  {
    if (!mShowingForMode)
    {
      mShowingForMode = true;
      ActionBarOverlayLayout localActionBarOverlayLayout = mOverlayLayout;
      if (localActionBarOverlayLayout != null) {
        localActionBarOverlayLayout.setShowingForActionMode(true);
      }
      updateVisibility(false);
    }
  }
  
  private void updateVisibility(boolean paramBoolean)
  {
    if (checkShowingFlags(mHiddenByApp, mHiddenBySystem, mShowingForMode))
    {
      if (!mNowShowing)
      {
        mNowShowing = true;
        doShow(paramBoolean);
      }
    }
    else if (mNowShowing)
    {
      mNowShowing = false;
      doHide(paramBoolean);
    }
  }
  
  public void animateToMode(boolean paramBoolean)
  {
    if (paramBoolean) {
      showForActionMode();
    } else {
      hideForActionMode();
    }
    if (show())
    {
      ViewPropertyAnimatorCompat localViewPropertyAnimatorCompat1;
      ViewPropertyAnimatorCompat localViewPropertyAnimatorCompat2;
      if (paramBoolean)
      {
        localViewPropertyAnimatorCompat1 = mDecorToolbar.setupAnimatorToVisibility(4, 100L);
        localViewPropertyAnimatorCompat2 = mContextView.setupAnimatorToVisibility(0, 200L);
      }
      else
      {
        localViewPropertyAnimatorCompat2 = mDecorToolbar.setupAnimatorToVisibility(0, 200L);
        localViewPropertyAnimatorCompat1 = mContextView.setupAnimatorToVisibility(8, 100L);
      }
      ViewPropertyAnimatorCompatSet localViewPropertyAnimatorCompatSet = new ViewPropertyAnimatorCompatSet();
      localViewPropertyAnimatorCompatSet.playSequentially(localViewPropertyAnimatorCompat1, localViewPropertyAnimatorCompat2);
      localViewPropertyAnimatorCompatSet.start();
      return;
    }
    if (paramBoolean)
    {
      mDecorToolbar.setVisibility(4);
      mContextView.setVisibility(0);
      return;
    }
    mDecorToolbar.setVisibility(0);
    mContextView.setVisibility(8);
  }
  
  public boolean collapseActionView()
  {
    DecorToolbar localDecorToolbar = mDecorToolbar;
    if ((localDecorToolbar != null) && (localDecorToolbar.hasExpandedActionView()))
    {
      mDecorToolbar.collapseActionView();
      return true;
    }
    return false;
  }
  
  void completeDeferredDestroyActionMode()
  {
    ActionMode.Callback localCallback = mDeferredModeDestroyCallback;
    if (localCallback != null)
    {
      localCallback.onDestroyActionMode(mDeferredDestroyActionMode);
      mDeferredDestroyActionMode = null;
      mDeferredModeDestroyCallback = null;
    }
  }
  
  public void dispatchMenuVisibilityChanged(boolean paramBoolean)
  {
    if (paramBoolean == mLastMenuVisibility) {
      return;
    }
    mLastMenuVisibility = paramBoolean;
    int j = mMenuVisibilityListeners.size();
    int i = 0;
    while (i < j)
    {
      ((ActionBar.OnMenuVisibilityListener)mMenuVisibilityListeners.get(i)).onMenuVisibilityChanged(paramBoolean);
      i += 1;
    }
  }
  
  public void doHide(boolean paramBoolean)
  {
    ViewPropertyAnimatorCompatSet localViewPropertyAnimatorCompatSet = mCurrentShowAnim;
    if (localViewPropertyAnimatorCompatSet != null) {
      localViewPropertyAnimatorCompatSet.cancel();
    }
    if ((mCurWindowVisibility == 0) && ((mShowHideAnimationEnabled) || (paramBoolean)))
    {
      mContainerView.setAlpha(1.0F);
      mContainerView.setTransitioning(true);
      localViewPropertyAnimatorCompatSet = new ViewPropertyAnimatorCompatSet();
      float f2 = -mContainerView.getHeight();
      float f1 = f2;
      if (paramBoolean)
      {
        localObject = new int[2];
        Object tmp82_80 = localObject;
        tmp82_80[0] = 0;
        Object tmp86_82 = tmp82_80;
        tmp86_82[1] = 0;
        tmp86_82;
        mContainerView.getLocationInWindow((int[])localObject);
        f1 = f2 - localObject[1];
      }
      Object localObject = ViewCompat.animate(mContainerView);
      ((ViewPropertyAnimatorCompat)localObject).translationY(f1);
      ((ViewPropertyAnimatorCompat)localObject).cancel(mUpdateListener);
      localViewPropertyAnimatorCompatSet.play((ViewPropertyAnimatorCompat)localObject);
      if (mContentAnimations)
      {
        localObject = mContentView;
        if (localObject != null)
        {
          localObject = ViewCompat.animate((View)localObject);
          ((ViewPropertyAnimatorCompat)localObject).translationY(f1);
          localViewPropertyAnimatorCompatSet.play((ViewPropertyAnimatorCompat)localObject);
        }
      }
      localViewPropertyAnimatorCompatSet.setInterpolator(sHideInterpolator);
      localViewPropertyAnimatorCompatSet.setDuration(250L);
      localViewPropertyAnimatorCompatSet.setListener(mHideListener);
      mCurrentShowAnim = localViewPropertyAnimatorCompatSet;
      localViewPropertyAnimatorCompatSet.start();
      return;
    }
    mHideListener.onAnimationEnd(null);
  }
  
  public void doShow(boolean paramBoolean)
  {
    Object localObject1 = mCurrentShowAnim;
    if (localObject1 != null) {
      ((ViewPropertyAnimatorCompatSet)localObject1).cancel();
    }
    mContainerView.setVisibility(0);
    if ((mCurWindowVisibility == 0) && ((mShowHideAnimationEnabled) || (paramBoolean)))
    {
      mContainerView.setTranslationY(0.0F);
      float f2 = -mContainerView.getHeight();
      float f1 = f2;
      if (paramBoolean)
      {
        localObject1 = new int[2];
        Object tmp73_71 = localObject1;
        tmp73_71[0] = 0;
        Object tmp77_73 = tmp73_71;
        tmp77_73[1] = 0;
        tmp77_73;
        mContainerView.getLocationInWindow((int[])localObject1);
        f1 = f2 - localObject1[1];
      }
      mContainerView.setTranslationY(f1);
      localObject1 = new ViewPropertyAnimatorCompatSet();
      Object localObject2 = ViewCompat.animate(mContainerView);
      ((ViewPropertyAnimatorCompat)localObject2).translationY(0.0F);
      ((ViewPropertyAnimatorCompat)localObject2).cancel(mUpdateListener);
      ((ViewPropertyAnimatorCompatSet)localObject1).play((ViewPropertyAnimatorCompat)localObject2);
      if (mContentAnimations)
      {
        localObject2 = mContentView;
        if (localObject2 != null)
        {
          ((View)localObject2).setTranslationY(f1);
          localObject2 = ViewCompat.animate(mContentView);
          ((ViewPropertyAnimatorCompat)localObject2).translationY(0.0F);
          ((ViewPropertyAnimatorCompatSet)localObject1).play((ViewPropertyAnimatorCompat)localObject2);
        }
      }
      ((ViewPropertyAnimatorCompatSet)localObject1).setInterpolator(sShowInterpolator);
      ((ViewPropertyAnimatorCompatSet)localObject1).setDuration(250L);
      ((ViewPropertyAnimatorCompatSet)localObject1).setListener(mShowListener);
      mCurrentShowAnim = ((ViewPropertyAnimatorCompatSet)localObject1);
      ((ViewPropertyAnimatorCompatSet)localObject1).start();
    }
    else
    {
      mContainerView.setAlpha(1.0F);
      mContainerView.setTranslationY(0.0F);
      if (mContentAnimations)
      {
        localObject1 = mContentView;
        if (localObject1 != null) {
          ((View)localObject1).setTranslationY(0.0F);
        }
      }
      mShowListener.onAnimationEnd(null);
    }
    localObject1 = mOverlayLayout;
    if (localObject1 != null) {
      ViewCompat.requestApplyInsets((View)localObject1);
    }
  }
  
  public void enableContentAnimations(boolean paramBoolean)
  {
    mContentAnimations = paramBoolean;
  }
  
  public int getNavigationMode()
  {
    return mDecorToolbar.getNavigationMode();
  }
  
  public Context getThemedContext()
  {
    if (mThemedContext == null)
    {
      TypedValue localTypedValue = new TypedValue();
      mContext.getTheme().resolveAttribute(R.attr.actionBarWidgetTheme, localTypedValue, true);
      int i = resourceId;
      if (i != 0) {
        mThemedContext = new ContextThemeWrapper(mContext, i);
      } else {
        mThemedContext = mContext;
      }
    }
    return mThemedContext;
  }
  
  public void hideForSystem()
  {
    if (!mHiddenBySystem)
    {
      mHiddenBySystem = true;
      updateVisibility(true);
    }
  }
  
  public void onContentScrollStarted()
  {
    ViewPropertyAnimatorCompatSet localViewPropertyAnimatorCompatSet = mCurrentShowAnim;
    if (localViewPropertyAnimatorCompatSet != null)
    {
      localViewPropertyAnimatorCompatSet.cancel();
      mCurrentShowAnim = null;
    }
  }
  
  public void onContentScrollStopped() {}
  
  public boolean onKeyShortcut(int paramInt, KeyEvent paramKeyEvent)
  {
    Object localObject = mActionMode;
    if (localObject == null) {
      return false;
    }
    localObject = ((ActionModeImpl)localObject).getMenu();
    if (localObject != null)
    {
      if (paramKeyEvent != null) {
        i = paramKeyEvent.getDeviceId();
      } else {
        i = -1;
      }
      int i = KeyCharacterMap.load(i).getKeyboardType();
      boolean bool = true;
      if (i == 1) {
        bool = false;
      }
      ((Menu)localObject).setQwertyMode(bool);
      return ((Menu)localObject).performShortcut(paramInt, paramKeyEvent, 0);
    }
    return false;
  }
  
  public void onWindowVisibilityChanged(int paramInt)
  {
    mCurWindowVisibility = paramInt;
  }
  
  public void setDefaultDisplayHomeAsUpEnabled(boolean paramBoolean)
  {
    if (!mDisplayHomeAsUpSet) {
      setDisplayHomeAsUpEnabled(paramBoolean);
    }
  }
  
  public void setDisplayHomeAsUpEnabled(boolean paramBoolean)
  {
    int i;
    if (paramBoolean) {
      i = 4;
    } else {
      i = 0;
    }
    setDisplayOptions(i, 4);
  }
  
  public void setDisplayOptions(int paramInt1, int paramInt2)
  {
    int i = mDecorToolbar.getDisplayOptions();
    if ((paramInt2 & 0x4) != 0) {
      mDisplayHomeAsUpSet = true;
    }
    mDecorToolbar.setDisplayOptions(paramInt1 & paramInt2 | paramInt2 & i);
  }
  
  public void setElevation(float paramFloat)
  {
    ViewCompat.setElevation(mContainerView, paramFloat);
  }
  
  public void setHideOnContentScrollEnabled(boolean paramBoolean)
  {
    if ((paramBoolean) && (!mOverlayLayout.isInOverlayMode())) {
      throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
    }
    mHideOnContentScroll = paramBoolean;
    mOverlayLayout.setHideOnContentScrollEnabled(paramBoolean);
  }
  
  public void setHomeButtonEnabled(boolean paramBoolean)
  {
    mDecorToolbar.setHomeButtonEnabled(paramBoolean);
  }
  
  public void setShowHideAnimationEnabled(boolean paramBoolean)
  {
    mShowHideAnimationEnabled = paramBoolean;
    if (!paramBoolean)
    {
      ViewPropertyAnimatorCompatSet localViewPropertyAnimatorCompatSet = mCurrentShowAnim;
      if (localViewPropertyAnimatorCompatSet != null) {
        localViewPropertyAnimatorCompatSet.cancel();
      }
    }
  }
  
  public void setWindowTitle(CharSequence paramCharSequence)
  {
    mDecorToolbar.setWindowTitle(paramCharSequence);
  }
  
  public void showForSystem()
  {
    if (mHiddenBySystem)
    {
      mHiddenBySystem = false;
      updateVisibility(true);
    }
  }
  
  public ActionMode startActionMode(ActionMode.Callback paramCallback)
  {
    ActionModeImpl localActionModeImpl = mActionMode;
    if (localActionModeImpl != null) {
      localActionModeImpl.finish();
    }
    mOverlayLayout.setHideOnContentScrollEnabled(false);
    mContextView.killMode();
    paramCallback = new ActionModeImpl(mContextView.getContext(), paramCallback);
    if (paramCallback.dispatchOnCreate())
    {
      mActionMode = paramCallback;
      paramCallback.invalidate();
      mContextView.initForMode(paramCallback);
      animateToMode(true);
      mContextView.sendAccessibilityEvent(32);
      return paramCallback;
    }
    return null;
  }
  
  public class ActionModeImpl
    extends ActionMode
    implements MenuBuilder.Callback
  {
    private final Context mActionModeContext;
    private ActionMode.Callback mCallback;
    private WeakReference<View> mCustomView;
    private final MenuBuilder mMenu;
    
    public ActionModeImpl(Context paramContext, ActionMode.Callback paramCallback)
    {
      mActionModeContext = paramContext;
      mCallback = paramCallback;
      this$1 = new MenuBuilder(paramContext);
      setDefaultShowAsAction(1);
      mMenu = WindowDecorActionBar.this;
      mMenu.setCallback(this);
    }
    
    public boolean dispatchOnCreate()
    {
      mMenu.stopDispatchingItemsChanged();
      try
      {
        boolean bool = mCallback.onCreateActionMode(this, mMenu);
        mMenu.startDispatchingItemsChanged();
        return bool;
      }
      catch (Throwable localThrowable)
      {
        mMenu.startDispatchingItemsChanged();
        throw localThrowable;
      }
    }
    
    public void finish()
    {
      WindowDecorActionBar localWindowDecorActionBar = WindowDecorActionBar.this;
      if (mActionMode != this) {
        return;
      }
      if (!WindowDecorActionBar.checkShowingFlags(mHiddenByApp, mHiddenBySystem, false))
      {
        localWindowDecorActionBar = WindowDecorActionBar.this;
        mDeferredDestroyActionMode = this;
        mDeferredModeDestroyCallback = mCallback;
      }
      else
      {
        mCallback.onDestroyActionMode(this);
      }
      mCallback = null;
      animateToMode(false);
      mContextView.closeMode();
      mDecorToolbar.getViewGroup().sendAccessibilityEvent(32);
      localWindowDecorActionBar = WindowDecorActionBar.this;
      mOverlayLayout.setHideOnContentScrollEnabled(mHideOnContentScroll);
      mActionMode = null;
    }
    
    public View getCustomView()
    {
      WeakReference localWeakReference = mCustomView;
      if (localWeakReference != null) {
        return (View)localWeakReference.get();
      }
      return null;
    }
    
    public Menu getMenu()
    {
      return mMenu;
    }
    
    public MenuInflater getMenuInflater()
    {
      return new SupportMenuInflater(mActionModeContext);
    }
    
    public CharSequence getSubtitle()
    {
      return mContextView.getSubtitle();
    }
    
    public CharSequence getTitle()
    {
      return mContextView.getTitle();
    }
    
    public void invalidate()
    {
      if (mActionMode != this) {
        return;
      }
      mMenu.stopDispatchingItemsChanged();
      try
      {
        mCallback.onPrepareActionMode(this, mMenu);
        mMenu.startDispatchingItemsChanged();
        return;
      }
      catch (Throwable localThrowable)
      {
        mMenu.startDispatchingItemsChanged();
        throw localThrowable;
      }
    }
    
    public boolean isTitleOptional()
    {
      return mContextView.isTitleOptional();
    }
    
    public boolean onMenuItemSelected(MenuBuilder paramMenuBuilder, MenuItem paramMenuItem)
    {
      paramMenuBuilder = mCallback;
      if (paramMenuBuilder != null) {
        return paramMenuBuilder.onActionItemClicked(this, paramMenuItem);
      }
      return false;
    }
    
    public void onMenuModeChange(MenuBuilder paramMenuBuilder)
    {
      if (mCallback == null) {
        return;
      }
      invalidate();
      mContextView.showOverflowMenu();
    }
    
    public void setCustomView(View paramView)
    {
      mContextView.setCustomView(paramView);
      mCustomView = new WeakReference(paramView);
    }
    
    public void setSubtitle(int paramInt)
    {
      setSubtitle(mContext.getResources().getString(paramInt));
    }
    
    public void setSubtitle(CharSequence paramCharSequence)
    {
      mContextView.setSubtitle(paramCharSequence);
    }
    
    public void setTitle(int paramInt)
    {
      setTitle(mContext.getResources().getString(paramInt));
    }
    
    public void setTitle(CharSequence paramCharSequence)
    {
      mContextView.setTitle(paramCharSequence);
    }
    
    public void setTitleOptionalHint(boolean paramBoolean)
    {
      super.setTitleOptionalHint(paramBoolean);
      mContextView.setTitleOptional(paramBoolean);
    }
  }
}

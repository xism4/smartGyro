package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.view.menu.ListMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.l.a;
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
import android.view.LayoutInflater.Factory2;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewManager;
import android.view.ViewParent;
import android.view.Window;
import android.view.Window.Callback;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Adapter;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.org.android.view.LayoutInflaterCompatHC;
import com.org.android.view.ViewCompat;
import com.org.android.view.ViewPropertyAnimatorCompat;
import com.org.android.view.i;
import com.org.android.view.x;
import com.org.v4.util.R.attr;
import com.org.v4.util.R.color;
import com.org.v4.util.R.layout;
import com.org.v4.util.R.style;
import com.org.v4.util.R.styleable;
import com.org.v4.view.ActionMode;
import com.org.v4.view.ActionMode.Callback;
import com.org.v4.view.ContextThemeWrapper;
import java.lang.reflect.Constructor;
import org.xmlpull.v1.XmlPullParser;

class AppCompatDelegateImplV7
  extends AppCompatDelegate
  implements MenuBuilder.Callback, LayoutInflater.Factory2
{
  private static final int[] a;
  private static boolean o;
  private static final boolean s;
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
  private final Runnable mInvalidatePanelMenuRunnable = new MonthByWeekFragment.2(this);
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
  
  static
  {
    boolean bool;
    if (Build.VERSION.SDK_INT < 21) {
      bool = true;
    } else {
      bool = false;
    }
    s = bool;
    a = new int[] { 16842836 };
    if ((s) && (!o))
    {
      Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(Thread.getDefaultUncaughtExceptionHandler()));
      o = true;
    }
  }
  
  AppCompatDelegateImplV7(Context paramContext, Window paramWindow, AppCompatCallback paramAppCompatCallback)
  {
    mContext = paramContext;
    mWindow = paramWindow;
    mAppCompatCallback = paramAppCompatCallback;
    mOriginalWindowCallback = mWindow.getCallback();
    paramWindow = mOriginalWindowCallback;
    if (!(paramWindow instanceof WindowCallbackWrapper))
    {
      mAppCompatWindowCallback = new WindowCallbackWrapper(this, paramWindow);
      mWindow.setCallback(mAppCompatWindowCallback);
      paramContext = TintTypedArray.obtainStyledAttributes(paramContext, null, a);
      paramWindow = paramContext.getDrawableIfKnown(0);
      if (paramWindow != null) {
        mWindow.setBackgroundDrawable(paramWindow);
      }
      paramContext.recycle();
      return;
    }
    throw new IllegalStateException("AppCompat has already installed itself into the Window");
  }
  
  private void applyFixedSizeWindow()
  {
    ContentFrameLayout localContentFrameLayout = (ContentFrameLayout)mSubDecor.findViewById(16908290);
    Object localObject = mWindow.getDecorView();
    localContentFrameLayout.setDecorPadding(((View)localObject).getPaddingLeft(), ((View)localObject).getPaddingTop(), ((View)localObject).getPaddingRight(), ((View)localObject).getPaddingBottom());
    localObject = mContext.obtainStyledAttributes(R.styleable.AppCompatTheme);
    ((TypedArray)localObject).getValue(R.styleable.AppCompatTheme_windowMinWidthMajor, localContentFrameLayout.getMinWidthMajor());
    ((TypedArray)localObject).getValue(R.styleable.AppCompatTheme_windowMinWidthMinor, localContentFrameLayout.getMinWidthMinor());
    if (((TypedArray)localObject).hasValue(R.styleable.AppCompatTheme_windowFixedWidthMajor)) {
      ((TypedArray)localObject).getValue(R.styleable.AppCompatTheme_windowFixedWidthMajor, localContentFrameLayout.getFixedWidthMajor());
    }
    if (((TypedArray)localObject).hasValue(R.styleable.AppCompatTheme_windowFixedWidthMinor)) {
      ((TypedArray)localObject).getValue(R.styleable.AppCompatTheme_windowFixedWidthMinor, localContentFrameLayout.getFixedWidthMinor());
    }
    if (((TypedArray)localObject).hasValue(R.styleable.AppCompatTheme_windowFixedHeightMajor)) {
      ((TypedArray)localObject).getValue(R.styleable.AppCompatTheme_windowFixedHeightMajor, localContentFrameLayout.getFixedHeightMajor());
    }
    if (((TypedArray)localObject).hasValue(R.styleable.AppCompatTheme_windowFixedHeightMinor)) {
      ((TypedArray)localObject).getValue(R.styleable.AppCompatTheme_windowFixedHeightMinor, localContentFrameLayout.getFixedHeightMinor());
    }
    ((TypedArray)localObject).recycle();
    localContentFrameLayout.requestLayout();
  }
  
  private void b()
  {
    if (v == null) {
      v = new ClassWriter(this, TwilightManager.getLastKnownLocation(mContext));
    }
  }
  
  private boolean connect()
  {
    if (r)
    {
      Object localObject = mContext;
      if ((localObject instanceof Activity))
      {
        localObject = ((Context)localObject).getPackageManager();
        Context localContext1 = mContext;
        Context localContext2 = mContext;
        try
        {
          localObject = ((PackageManager)localObject).getActivityInfo(new ComponentName(localContext1, localContext2.getClass()), 0);
          if ((configChanges & 0x200) == 0) {
            return true;
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", localNameNotFoundException);
          return true;
        }
      }
    }
    return false;
  }
  
  private ViewGroup createSubDecor()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a7 = a6\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer$LiveA.onUseLocal(UnSSATransformer.java:552)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer$LiveA.onUseLocal(UnSSATransformer.java:1)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.onUse(BaseAnalyze.java:166)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.onUse(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.travel(Cfg.java:331)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.travel(Cfg.java:387)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:90)\n\t... 17 more\n");
  }
  
  private int e()
  {
    int i = l;
    if (i != -100) {
      return i;
    }
    return AppCompatDelegate.getDefaultNightMode();
  }
  
  private void ensureSubDecor()
  {
    if (!mSubDecorInstalled)
    {
      mSubDecor = createSubDecor();
      Object localObject1 = getTitle();
      if (!TextUtils.isEmpty((CharSequence)localObject1))
      {
        Object localObject2 = mDecorContentParent;
        if (localObject2 != null)
        {
          ((DecorContentParent)localObject2).setWindowTitle((CharSequence)localObject1);
        }
        else if (peekSupportActionBar() != null)
        {
          peekSupportActionBar().setWindowTitle((CharSequence)localObject1);
        }
        else
        {
          localObject2 = mTitleView;
          if (localObject2 != null) {
            ((TextView)localObject2).setText((CharSequence)localObject1);
          }
        }
      }
      applyFixedSizeWindow();
      onSubDecorInstalled(mSubDecor);
      mSubDecorInstalled = true;
      localObject1 = getPanelState(0, false);
      if ((!mIsDestroyed) && ((localObject1 == null) || (menu == null))) {
        invalidatePanelMenu(108);
      }
    }
  }
  
  private boolean init(int paramInt)
  {
    android.content.res.Resources localResources = mContext.getResources();
    Configuration localConfiguration = localResources.getConfiguration();
    int i = uiMode;
    if (paramInt == 2) {
      paramInt = 32;
    } else {
      paramInt = 16;
    }
    if ((i & 0x30) != paramInt)
    {
      if (connect())
      {
        ((Activity)mContext).recreate();
      }
      else
      {
        localConfiguration = new Configuration(localConfiguration);
        DisplayMetrics localDisplayMetrics = localResources.getDisplayMetrics();
        uiMode = (paramInt | uiMode & 0xFFFFFFCF);
        localResources.updateConfiguration(localConfiguration, localDisplayMetrics);
        if (Build.VERSION.SDK_INT < 26) {
          Frame.initialize(localResources);
        }
      }
      return true;
    }
    return false;
  }
  
  private void initWindowDecorActionBar()
  {
    ensureSubDecor();
    if (mHasActionBar)
    {
      if (mActionBar != null) {
        return;
      }
      Object localObject = mOriginalWindowCallback;
      if ((localObject instanceof Activity)) {}
      for (localObject = new WindowDecorActionBar((Activity)localObject, mOverlayActionBar);; localObject = new WindowDecorActionBar((Dialog)localObject))
      {
        mActionBar = ((ActionBar)localObject);
        break;
        if (!(localObject instanceof Dialog)) {
          break;
        }
      }
      localObject = mActionBar;
      if (localObject != null) {
        ((ActionBar)localObject).setDefaultDisplayHomeAsUpEnabled(mEnableDefaultActionBarUp);
      }
    }
  }
  
  private boolean initializePanelContent(PanelFeatureState paramPanelFeatureState)
  {
    View localView = createdPanelView;
    if (localView != null)
    {
      shownPanelView = localView;
      return true;
    }
    if (menu == null) {
      return false;
    }
    if (mPanelMenuPresenterCallback == null) {
      mPanelMenuPresenterCallback = new PanelMenuPresenterCallback();
    }
    shownPanelView = ((View)paramPanelFeatureState.getListMenuView(mPanelMenuPresenterCallback));
    return shownPanelView != null;
  }
  
  private boolean initializePanelDecor(PanelFeatureState paramPanelFeatureState)
  {
    paramPanelFeatureState.setStyle(getActionBarThemedContext());
    decorView = new ListMenuDecorView(listPresenterContext);
    gravity = 81;
    return true;
  }
  
  private boolean initializePanelMenu(PanelFeatureState paramPanelFeatureState)
  {
    Context localContext = mContext;
    int i = featureId;
    if (i != 0)
    {
      localObject1 = localContext;
      if (i != 108) {}
    }
    else
    {
      localObject1 = localContext;
      if (mDecorContentParent != null)
      {
        TypedValue localTypedValue = new TypedValue();
        Resources.Theme localTheme = localContext.getTheme();
        localTheme.resolveAttribute(R.attr.actionBarTheme, localTypedValue, true);
        localObject1 = null;
        if (resourceId != 0)
        {
          localObject2 = localContext.getResources().newTheme();
          localObject1 = localObject2;
          ((Resources.Theme)localObject2).setTo(localTheme);
          ((Resources.Theme)localObject2).applyStyle(resourceId, true);
          ((Resources.Theme)localObject2).resolveAttribute(R.attr.actionBarWidgetTheme, localTypedValue, true);
        }
        else
        {
          localTheme.resolveAttribute(R.attr.actionBarWidgetTheme, localTypedValue, true);
        }
        Object localObject2 = localObject1;
        if (resourceId != 0)
        {
          localObject2 = localObject1;
          if (localObject1 == null)
          {
            localObject1 = localContext.getResources().newTheme();
            localObject2 = localObject1;
            ((Resources.Theme)localObject1).setTo(localTheme);
          }
          ((Resources.Theme)localObject2).applyStyle(resourceId, true);
        }
        localObject1 = localContext;
        if (localObject2 != null)
        {
          localObject1 = new ContextThemeWrapper(localContext, 0);
          ((ContextThemeWrapper)localObject1).getTheme().setTo((Resources.Theme)localObject2);
        }
      }
    }
    Object localObject1 = new MenuBuilder((Context)localObject1);
    ((MenuBuilder)localObject1).setCallback(this);
    paramPanelFeatureState.setMenu((MenuBuilder)localObject1);
    return true;
  }
  
  private void invalidatePanelMenu(int paramInt)
  {
    mInvalidatePanelMenuFeatures = (1 << paramInt | mInvalidatePanelMenuFeatures);
    if (!mInvalidatePanelMenuPosted)
    {
      ViewCompat.postOnAnimation(mWindow.getDecorView(), mInvalidatePanelMenuRunnable);
      mInvalidatePanelMenuPosted = true;
    }
  }
  
  private boolean onKeyDownPanel(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getRepeatCount() == 0)
    {
      PanelFeatureState localPanelFeatureState = getPanelState(paramInt, true);
      if (!isOpen) {
        return preparePanel(localPanelFeatureState, paramKeyEvent);
      }
    }
    return false;
  }
  
  private boolean onKeyUpPanel(int paramInt, KeyEvent paramKeyEvent)
  {
    if (mActionMode != null) {
      return false;
    }
    PanelFeatureState localPanelFeatureState = getPanelState(paramInt, true);
    boolean bool;
    if (paramInt == 0)
    {
      DecorContentParent localDecorContentParent = mDecorContentParent;
      if ((localDecorContentParent != null) && (localDecorContentParent.canShowOverflowMenu()) && (!ViewConfiguration.get(mContext).hasPermanentMenuKey()))
      {
        if (!mDecorContentParent.isOverflowMenuShowing())
        {
          if ((mIsDestroyed) || (!preparePanel(localPanelFeatureState, paramKeyEvent))) {
            break label180;
          }
          bool = mDecorContentParent.showOverflowMenu();
          break label198;
        }
        bool = mDecorContentParent.hideOverflowMenu();
        break label198;
      }
    }
    if ((!isOpen) && (!isHandled))
    {
      if (isPrepared)
      {
        if (refreshMenuContent)
        {
          isPrepared = false;
          bool = preparePanel(localPanelFeatureState, paramKeyEvent);
        }
        else
        {
          bool = true;
        }
        if (bool)
        {
          openPanel(localPanelFeatureState, paramKeyEvent);
          bool = true;
          break label198;
        }
      }
      label180:
      bool = false;
    }
    else
    {
      bool = isOpen;
      closePanel(localPanelFeatureState, true);
    }
    label198:
    if (bool)
    {
      paramKeyEvent = (AudioManager)mContext.getSystemService("audio");
      if (paramKeyEvent != null)
      {
        paramKeyEvent.playSoundEffect(0);
        return bool;
      }
      Log.w("AppCompatDelegate", "Couldn't get audio manager");
    }
    return bool;
  }
  
  private void openPanel(PanelFeatureState paramPanelFeatureState, KeyEvent paramKeyEvent)
  {
    if (!isOpen)
    {
      if (mIsDestroyed) {
        return;
      }
      if (featureId == 0)
      {
        if ((mContext.getResources().getConfiguration().screenLayout & 0xF) == 4) {
          i = 1;
        } else {
          i = 0;
        }
        if (i != 0) {
          return;
        }
      }
      Object localObject = getWindowCallback();
      if ((localObject != null) && (!((Window.Callback)localObject).onMenuOpened(featureId, menu)))
      {
        closePanel(paramPanelFeatureState, true);
        return;
      }
      WindowManager localWindowManager = (WindowManager)mContext.getSystemService("window");
      if (localWindowManager == null) {
        return;
      }
      if (!preparePanel(paramPanelFeatureState, paramKeyEvent)) {
        return;
      }
      if ((decorView != null) && (!refreshDecorView))
      {
        paramKeyEvent = createdPanelView;
        if (paramKeyEvent != null)
        {
          paramKeyEvent = paramKeyEvent.getLayoutParams();
          if ((paramKeyEvent != null) && (width == -1))
          {
            i = -1;
            break label340;
          }
        }
      }
      else
      {
        paramKeyEvent = decorView;
        if (paramKeyEvent == null)
        {
          if (!initializePanelDecor(paramPanelFeatureState)) {
            return;
          }
          if (decorView != null) {}
        }
        else if ((refreshDecorView) && (paramKeyEvent.getChildCount() > 0))
        {
          decorView.removeAllViews();
        }
        if (!initializePanelContent(paramPanelFeatureState)) {
          return;
        }
        if (!paramPanelFeatureState.hasPanelItems()) {
          return;
        }
        localObject = shownPanelView.getLayoutParams();
        paramKeyEvent = (KeyEvent)localObject;
        if (localObject == null) {
          paramKeyEvent = new ViewGroup.LayoutParams(-2, -2);
        }
        i = background;
        decorView.setBackgroundResource(i);
        localObject = shownPanelView.getParent();
        if ((localObject != null) && ((localObject instanceof ViewGroup))) {
          ((ViewGroup)localObject).removeView(shownPanelView);
        }
        decorView.addView(shownPanelView, paramKeyEvent);
        if (!shownPanelView.hasFocus()) {
          shownPanelView.requestFocus();
        }
      }
      int i = -2;
      label340:
      isHandled = false;
      paramKeyEvent = new WindowManager.LayoutParams(i, -2, x, y, 1002, 8519680, -3);
      gravity = gravity;
      windowAnimations = windowAnimations;
      localWindowManager.addView(decorView, paramKeyEvent);
      isOpen = true;
    }
  }
  
  private boolean performPanelShortcut(PanelFeatureState paramPanelFeatureState, int paramInt1, KeyEvent paramKeyEvent, int paramInt2)
  {
    boolean bool1 = paramKeyEvent.isSystem();
    boolean bool2 = false;
    if (bool1) {
      return false;
    }
    if (!isPrepared)
    {
      bool1 = bool2;
      if (!preparePanel(paramPanelFeatureState, paramKeyEvent)) {}
    }
    else
    {
      MenuBuilder localMenuBuilder = menu;
      bool1 = bool2;
      if (localMenuBuilder != null) {
        bool1 = localMenuBuilder.performShortcut(paramInt1, paramKeyEvent, paramInt2);
      }
    }
    if ((bool1) && ((paramInt2 & 0x1) == 0) && (mDecorContentParent == null)) {
      closePanel(paramPanelFeatureState, true);
    }
    return bool1;
  }
  
  private boolean preparePanel(PanelFeatureState paramPanelFeatureState, KeyEvent paramKeyEvent)
  {
    if (mIsDestroyed) {
      return false;
    }
    if (isPrepared) {
      return true;
    }
    Object localObject1 = mPreparedPanel;
    if ((localObject1 != null) && (localObject1 != paramPanelFeatureState)) {
      closePanel((PanelFeatureState)localObject1, false);
    }
    localObject1 = getWindowCallback();
    if (localObject1 != null) {
      createdPanelView = ((Window.Callback)localObject1).onCreatePanelView(featureId);
    }
    int i = featureId;
    if ((i != 0) && (i != 108)) {
      i = 0;
    } else {
      i = 1;
    }
    Object localObject2;
    if (i != 0)
    {
      localObject2 = mDecorContentParent;
      if (localObject2 != null) {
        ((DecorContentParent)localObject2).setMenuPrepared();
      }
    }
    if (createdPanelView == null)
    {
      if (i != 0) {
        peekSupportActionBar();
      }
      if ((menu == null) || (refreshMenuContent))
      {
        if (menu == null)
        {
          if (!initializePanelMenu(paramPanelFeatureState)) {
            break label436;
          }
          if (menu == null) {
            return false;
          }
        }
        if ((i != 0) && (mDecorContentParent != null))
        {
          if (mActionMenuPresenterCallback == null) {
            mActionMenuPresenterCallback = new ActionMenuPresenterCallback();
          }
          mDecorContentParent.setMenu(menu, mActionMenuPresenterCallback);
        }
        menu.stopDispatchingItemsChanged();
        if (!((Window.Callback)localObject1).onCreatePanelMenu(featureId, menu))
        {
          paramPanelFeatureState.setMenu(null);
          if (i != 0)
          {
            paramPanelFeatureState = mDecorContentParent;
            if (paramPanelFeatureState != null)
            {
              paramPanelFeatureState.setMenu(null, mActionMenuPresenterCallback);
              return false;
            }
          }
        }
        else
        {
          refreshMenuContent = false;
        }
      }
      else
      {
        menu.stopDispatchingItemsChanged();
        localObject2 = frozenActionViewState;
        if (localObject2 != null)
        {
          menu.restoreActionViewStates((Bundle)localObject2);
          frozenActionViewState = null;
        }
        if (!((Window.Callback)localObject1).onPreparePanel(0, createdPanelView, menu))
        {
          if (i != 0)
          {
            paramKeyEvent = mDecorContentParent;
            if (paramKeyEvent != null) {
              paramKeyEvent.setMenu(null, mActionMenuPresenterCallback);
            }
          }
          menu.startDispatchingItemsChanged();
          return false;
        }
        if (paramKeyEvent != null) {
          i = paramKeyEvent.getDeviceId();
        } else {
          i = -1;
        }
        boolean bool;
        if (KeyCharacterMap.load(i).getKeyboardType() != 1) {
          bool = true;
        } else {
          bool = false;
        }
        qwertyMode = bool;
        menu.setQwertyMode(qwertyMode);
        menu.startDispatchingItemsChanged();
      }
    }
    else
    {
      isPrepared = true;
      isHandled = false;
      mPreparedPanel = paramPanelFeatureState;
      return true;
    }
    label436:
    return false;
  }
  
  private void reopenMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
  {
    paramMenuBuilder = mDecorContentParent;
    if ((paramMenuBuilder != null) && (paramMenuBuilder.canShowOverflowMenu()) && ((!ViewConfiguration.get(mContext).hasPermanentMenuKey()) || (mDecorContentParent.isOverflowMenuShowPending())))
    {
      paramMenuBuilder = getWindowCallback();
      if ((mDecorContentParent.isOverflowMenuShowing()) && (paramBoolean))
      {
        mDecorContentParent.hideOverflowMenu();
        if (!mIsDestroyed) {
          paramMenuBuilder.onPanelClosed(108, getPanelState0menu);
        }
      }
      else if ((paramMenuBuilder != null) && (!mIsDestroyed))
      {
        if ((mInvalidatePanelMenuPosted) && ((mInvalidatePanelMenuFeatures & 0x1) != 0))
        {
          mWindow.getDecorView().removeCallbacks(mInvalidatePanelMenuRunnable);
          mInvalidatePanelMenuRunnable.run();
        }
        PanelFeatureState localPanelFeatureState = getPanelState(0, true);
        MenuBuilder localMenuBuilder = menu;
        if ((localMenuBuilder != null) && (!refreshMenuContent) && (paramMenuBuilder.onPreparePanel(0, createdPanelView, localMenuBuilder)))
        {
          paramMenuBuilder.onMenuOpened(108, menu);
          mDecorContentParent.showOverflowMenu();
        }
      }
    }
    else
    {
      paramMenuBuilder = getPanelState(0, true);
      refreshDecorView = true;
      closePanel(paramMenuBuilder, false);
      openPanel(paramMenuBuilder, null);
    }
  }
  
  private int sanitizeWindowFeatureId(int paramInt)
  {
    if (paramInt == 8)
    {
      Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
      return 108;
    }
    if (paramInt == 9)
    {
      Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
      return 109;
    }
    return paramInt;
  }
  
  private boolean shouldInheritContext(ViewParent paramViewParent)
  {
    if (paramViewParent == null) {
      return false;
    }
    View localView = mWindow.getDecorView();
    for (;;)
    {
      if (paramViewParent == null) {
        return true;
      }
      if ((paramViewParent == localView) || (!(paramViewParent instanceof View))) {
        break;
      }
      if (ViewCompat.isAttachedToWindow((View)paramViewParent)) {
        return false;
      }
      paramViewParent = paramViewParent.getParent();
    }
    return false;
  }
  
  private void throwFeatureRequestIfSubDecorInstalled()
  {
    if (!mSubDecorInstalled) {
      return;
    }
    throw new AndroidRuntimeException("Window feature must be requested before adding content");
  }
  
  int a(int paramInt)
  {
    if (paramInt != -100)
    {
      if (paramInt != 0) {
        return paramInt;
      }
      if ((Build.VERSION.SDK_INT >= 23) && (((UiModeManager)mContext.getSystemService(UiModeManager.class)).getNightMode() == 0)) {
        return -1;
      }
      b();
      return v.a();
    }
    return -1;
  }
  
  public boolean a()
  {
    int i = e();
    int j = a(i);
    boolean bool;
    if (j != -1) {
      bool = init(j);
    } else {
      bool = false;
    }
    if (i == 0)
    {
      b();
      v.init();
    }
    r = true;
    return bool;
  }
  
  int access$300(int paramInt)
  {
    Object localObject1 = mActionModeView;
    int i1 = 0;
    int i;
    int j;
    if ((localObject1 != null) && ((((View)localObject1).getLayoutParams() instanceof ViewGroup.MarginLayoutParams)))
    {
      localObject1 = (ViewGroup.MarginLayoutParams)mActionModeView.getLayoutParams();
      boolean bool = mActionModeView.isShown();
      int m = 1;
      int k;
      int n;
      if (bool)
      {
        if (mTempRect1 == null)
        {
          mTempRect1 = new Rect();
          mTempRect2 = new Rect();
        }
        Object localObject2 = mTempRect1;
        Rect localRect = mTempRect2;
        ((Rect)localObject2).set(0, paramInt, 0, 0);
        ViewUtils.computeFitSystemWindows(mSubDecor, (Rect)localObject2, localRect);
        if (top == 0) {
          i = paramInt;
        } else {
          i = 0;
        }
        if (topMargin != i)
        {
          topMargin = paramInt;
          localObject2 = mStatusGuard;
          if (localObject2 == null)
          {
            mStatusGuard = new View(mContext);
            mStatusGuard.setBackgroundColor(mContext.getResources().getColor(R.color.abc_input_method_navigation_guard));
            mSubDecor.addView(mStatusGuard, -1, new ViewGroup.LayoutParams(-1, paramInt));
          }
          else
          {
            localObject2 = ((View)localObject2).getLayoutParams();
            if (height != paramInt)
            {
              height = paramInt;
              mStatusGuard.setLayoutParams((ViewGroup.LayoutParams)localObject2);
            }
          }
          j = 1;
        }
        else
        {
          j = 0;
        }
        if (mStatusGuard == null) {
          m = 0;
        }
        k = m;
        n = j;
        i = paramInt;
        if (!mOverlayActionMode)
        {
          k = m;
          n = j;
          i = paramInt;
          if (m != 0)
          {
            i = 0;
            k = m;
            n = j;
          }
        }
      }
      else
      {
        if (topMargin != 0)
        {
          topMargin = 0;
          j = 1;
        }
        else
        {
          j = 0;
        }
        k = 0;
        i = paramInt;
        n = j;
      }
      j = k;
      paramInt = i;
      if (n != 0)
      {
        mActionModeView.setLayoutParams((ViewGroup.LayoutParams)localObject1);
        j = k;
        paramInt = i;
      }
    }
    else
    {
      j = 0;
    }
    localObject1 = mStatusGuard;
    if (localObject1 != null)
    {
      if (j != 0) {
        i = i1;
      } else {
        i = 8;
      }
      ((View)localObject1).setVisibility(i);
    }
    return paramInt;
  }
  
  public void addContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    ensureSubDecor();
    ((ViewGroup)mSubDecor.findViewById(16908290)).addView(paramView, paramLayoutParams);
    mOriginalWindowCallback.onContentChanged();
  }
  
  public void c()
  {
    Object localObject = getSupportActionBar();
    if (localObject != null) {
      ((ActionBar)localObject).setShowHideAnimationEnabled(false);
    }
    localObject = v;
    if (localObject != null) {
      ((ClassWriter)localObject).c();
    }
  }
  
  void callOnPanelClosed(int paramInt, PanelFeatureState paramPanelFeatureState, Menu paramMenu)
  {
    Object localObject1 = paramPanelFeatureState;
    Object localObject2 = paramMenu;
    if (paramMenu == null)
    {
      PanelFeatureState localPanelFeatureState = paramPanelFeatureState;
      if (paramPanelFeatureState == null)
      {
        localPanelFeatureState = paramPanelFeatureState;
        if (paramInt >= 0)
        {
          localObject1 = mPanels;
          localPanelFeatureState = paramPanelFeatureState;
          if (paramInt < localObject1.length) {
            localPanelFeatureState = localObject1[paramInt];
          }
        }
      }
      localObject1 = localPanelFeatureState;
      localObject2 = paramMenu;
      if (localPanelFeatureState != null)
      {
        localObject2 = menu;
        localObject1 = localPanelFeatureState;
      }
    }
    if ((localObject1 != null) && (!isOpen)) {
      return;
    }
    if (!mIsDestroyed) {
      mOriginalWindowCallback.onPanelClosed(paramInt, (Menu)localObject2);
    }
  }
  
  void checkCloseActionMenu(MenuBuilder paramMenuBuilder)
  {
    if (mClosingActionMenu) {
      return;
    }
    mClosingActionMenu = true;
    mDecorContentParent.dismissPopups();
    Window.Callback localCallback = getWindowCallback();
    if ((localCallback != null) && (!mIsDestroyed)) {
      localCallback.onPanelClosed(108, paramMenuBuilder);
    }
    mClosingActionMenu = false;
  }
  
  void closePanel(int paramInt)
  {
    closePanel(getPanelState(paramInt, true), true);
  }
  
  void closePanel(PanelFeatureState paramPanelFeatureState, boolean paramBoolean)
  {
    if ((paramBoolean) && (featureId == 0))
    {
      localObject = mDecorContentParent;
      if ((localObject != null) && (((DecorContentParent)localObject).isOverflowMenuShowing()))
      {
        checkCloseActionMenu(menu);
        return;
      }
    }
    Object localObject = (WindowManager)mContext.getSystemService("window");
    if ((localObject != null) && (isOpen))
    {
      ViewGroup localViewGroup = decorView;
      if (localViewGroup != null)
      {
        ((ViewManager)localObject).removeView(localViewGroup);
        if (paramBoolean) {
          callOnPanelClosed(featureId, paramPanelFeatureState, null);
        }
      }
    }
    isPrepared = false;
    isHandled = false;
    isOpen = false;
    shownPanelView = null;
    refreshDecorView = true;
    if (mPreparedPanel == paramPanelFeatureState) {
      mPreparedPanel = null;
    }
  }
  
  public View createView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    Object localObject = mAppCompatViewInflater;
    boolean bool = false;
    if (localObject == null)
    {
      localObject = mContext.obtainStyledAttributes(R.styleable.AppCompatTheme).getString(R.styleable.AppCompatTheme_viewInflaterClass);
      if ((localObject != null) && (!AppCompatViewInflater.class.getName().equals(localObject))) {
        try
        {
          mAppCompatViewInflater = ((AppCompatViewInflater)Class.forName((String)localObject).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
        }
        catch (Throwable localThrowable)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Failed to instantiate custom view inflater ");
          localStringBuilder.append((String)localObject);
          localStringBuilder.append(". Falling back to default.");
          Log.i("AppCompatDelegate", localStringBuilder.toString(), localThrowable);
          localObject = new AppCompatViewInflater();
          break label155;
        }
      } else {
        localObject = new AppCompatViewInflater();
      }
      label155:
      mAppCompatViewInflater = ((AppCompatViewInflater)localObject);
    }
    if (s)
    {
      if ((paramAttributeSet instanceof XmlPullParser))
      {
        if (((XmlPullParser)paramAttributeSet).getDepth() > 1) {
          bool = true;
        }
      }
      else {
        bool = shouldInheritContext((ViewParent)paramView);
      }
    }
    else {
      bool = false;
    }
    return mAppCompatViewInflater.createView(paramView, paramString, paramContext, paramAttributeSet, bool, s, true, TintManager.insert());
  }
  
  final boolean d()
  {
    if (mSubDecorInstalled)
    {
      ViewGroup localViewGroup = mSubDecor;
      if ((localViewGroup != null) && (ViewCompat.show(localViewGroup))) {
        return true;
      }
    }
    return false;
  }
  
  void dismissPopups()
  {
    Object localObject = mDecorContentParent;
    if (localObject != null) {
      ((DecorContentParent)localObject).dismissPopups();
    }
    if (mActionModePopup != null)
    {
      mWindow.getDecorView().removeCallbacks(mShowActionModePopup);
      if (mActionModePopup.isShowing()) {
        localObject = mActionModePopup;
      }
    }
    try
    {
      ((PopupWindow)localObject).dismiss();
      mActionModePopup = null;
      endOnGoingFadeAnimation();
      localObject = getPanelState(0, false);
      if (localObject != null)
      {
        localObject = menu;
        if (localObject != null)
        {
          ((MenuBuilder)localObject).close();
          return;
        }
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;) {}
    }
  }
  
  boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    Object localObject = mOriginalWindowCallback;
    boolean bool = localObject instanceof x;
    int i = 1;
    if ((bool) || ((localObject instanceof AppCompatDialog)))
    {
      localObject = mWindow.getDecorView();
      if ((localObject != null) && (i.a((View)localObject, paramKeyEvent))) {
        return true;
      }
    }
    if ((paramKeyEvent.getKeyCode() == 82) && (mOriginalWindowCallback.dispatchKeyEvent(paramKeyEvent))) {
      return true;
    }
    int j = paramKeyEvent.getKeyCode();
    if (paramKeyEvent.getAction() != 0) {
      i = 0;
    }
    if (i != 0) {
      return onKeyDown(j, paramKeyEvent);
    }
    return onKeyUp(j, paramKeyEvent);
  }
  
  void doInvalidatePanelMenu(int paramInt)
  {
    PanelFeatureState localPanelFeatureState = getPanelState(paramInt, true);
    if (menu != null)
    {
      Bundle localBundle = new Bundle();
      menu.saveActionViewStates(localBundle);
      if (localBundle.size() > 0) {
        frozenActionViewState = localBundle;
      }
      menu.stopDispatchingItemsChanged();
      menu.clear();
    }
    refreshMenuContent = true;
    refreshDecorView = true;
    if (((paramInt == 108) || (paramInt == 0)) && (mDecorContentParent != null))
    {
      localPanelFeatureState = getPanelState(0, false);
      if (localPanelFeatureState != null)
      {
        isPrepared = false;
        preparePanel(localPanelFeatureState, null);
      }
    }
  }
  
  void endOnGoingFadeAnimation()
  {
    ViewPropertyAnimatorCompat localViewPropertyAnimatorCompat = mFadeAnim;
    if (localViewPropertyAnimatorCompat != null) {
      localViewPropertyAnimatorCompat.cancel();
    }
  }
  
  PanelFeatureState findMenuPanel(Menu paramMenu)
  {
    PanelFeatureState[] arrayOfPanelFeatureState = mPanels;
    int j = 0;
    int i;
    if (arrayOfPanelFeatureState != null) {
      i = arrayOfPanelFeatureState.length;
    } else {
      i = 0;
    }
    while (j < i)
    {
      PanelFeatureState localPanelFeatureState = arrayOfPanelFeatureState[j];
      if ((localPanelFeatureState != null) && (menu == paramMenu)) {
        return localPanelFeatureState;
      }
      j += 1;
    }
    return null;
  }
  
  public View findViewById(int paramInt)
  {
    ensureSubDecor();
    return mWindow.findViewById(paramInt);
  }
  
  final Context getActionBarThemedContext()
  {
    Object localObject1 = getSupportActionBar();
    if (localObject1 != null) {
      localObject1 = ((ActionBar)localObject1).getThemedContext();
    } else {
      localObject1 = null;
    }
    Object localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = mContext;
    }
    return localObject2;
  }
  
  protected PanelFeatureState getPanelState(int paramInt, boolean paramBoolean)
  {
    Object localObject2 = mPanels;
    Object localObject1;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (localObject2.length > paramInt) {}
    }
    else
    {
      localObject1 = new PanelFeatureState[paramInt + 1];
      if (localObject2 != null) {
        System.arraycopy(localObject2, 0, localObject1, 0, localObject2.length);
      }
      mPanels = ((PanelFeatureState[])localObject1);
    }
    Object localObject3 = localObject1[paramInt];
    localObject2 = localObject3;
    if (localObject3 == null)
    {
      localObject2 = new PanelFeatureState(paramInt);
      localObject1[paramInt] = localObject2;
    }
    return localObject2;
  }
  
  public ActionBar getSupportActionBar()
  {
    initWindowDecorActionBar();
    return mActionBar;
  }
  
  final CharSequence getTitle()
  {
    Window.Callback localCallback = mOriginalWindowCallback;
    if ((localCallback instanceof Activity)) {
      return ((Activity)localCallback).getTitle();
    }
    return mTitle;
  }
  
  final Window.Callback getWindowCallback()
  {
    return mWindow.getCallback();
  }
  
  public void installViewFactory()
  {
    LayoutInflater localLayoutInflater = LayoutInflater.from(mContext);
    if (localLayoutInflater.getFactory() == null)
    {
      LayoutInflaterCompatHC.setFactory(localLayoutInflater, this);
      return;
    }
    if (!(localLayoutInflater.getFactory2() instanceof AppCompatDelegateImplV7)) {
      Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
    }
  }
  
  public void invalidateOptionsMenu()
  {
    ActionBar localActionBar = getSupportActionBar();
    if ((localActionBar != null) && (localActionBar.invalidateOptionsMenu())) {
      return;
    }
    invalidatePanelMenu(0);
  }
  
  public boolean isHandleNativeActionModesEnabled()
  {
    return mHandleNativeActionModes;
  }
  
  boolean onBackPressed()
  {
    Object localObject = mActionMode;
    if (localObject != null)
    {
      ((ActionMode)localObject).finish();
      return true;
    }
    localObject = getSupportActionBar();
    return (localObject != null) && (((ActionBar)localObject).collapseActionView());
  }
  
  public void onCreate(Bundle paramBundle)
  {
    Object localObject2 = mOriginalWindowCallback;
    Object localObject1;
    if ((localObject2 instanceof Activity))
    {
      localObject1 = null;
      localObject2 = (Activity)localObject2;
    }
    try
    {
      localObject2 = NavUtils.getParentActivityName((Activity)localObject2);
      localObject1 = localObject2;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;) {}
    }
    if (localObject1 != null)
    {
      localObject1 = peekSupportActionBar();
      if (localObject1 == null) {
        mEnableDefaultActionBarUp = true;
      } else {
        ((ActionBar)localObject1).setDefaultDisplayHomeAsUpEnabled(true);
      }
    }
    if ((paramBundle != null) && (l == -100))
    {
      l = paramBundle.getInt("appcompat:local_night_mode", -100);
      return;
    }
  }
  
  public final View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    return createView(paramView, paramString, paramContext, paramAttributeSet);
  }
  
  public View onCreateView(String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    return onCreateView(null, paramString, paramContext, paramAttributeSet);
  }
  
  boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool = true;
    if (paramInt != 4)
    {
      if (paramInt != 82) {
        return false;
      }
      onKeyDownPanel(0, paramKeyEvent);
      return true;
    }
    if ((paramKeyEvent.getFlags() & 0x80) == 0) {
      bool = false;
    }
    mLongPressBackDown = bool;
    return false;
  }
  
  boolean onKeyShortcut(int paramInt, KeyEvent paramKeyEvent)
  {
    Object localObject = getSupportActionBar();
    if ((localObject != null) && (((ActionBar)localObject).onKeyShortcut(paramInt, paramKeyEvent))) {
      return true;
    }
    localObject = mPreparedPanel;
    if ((localObject != null) && (performPanelShortcut((PanelFeatureState)localObject, paramKeyEvent.getKeyCode(), paramKeyEvent, 1)))
    {
      paramKeyEvent = mPreparedPanel;
      if (paramKeyEvent != null)
      {
        isHandled = true;
        return true;
      }
    }
    else
    {
      if (mPreparedPanel == null)
      {
        localObject = getPanelState(0, true);
        preparePanel((PanelFeatureState)localObject, paramKeyEvent);
        boolean bool = performPanelShortcut((PanelFeatureState)localObject, paramKeyEvent.getKeyCode(), paramKeyEvent, 1);
        isPrepared = false;
        if (!bool) {
          break label117;
        }
        return true;
      }
      return false;
    }
    return true;
    label117:
    return false;
  }
  
  boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt != 4)
    {
      if (paramInt != 82) {
        return false;
      }
      onKeyUpPanel(0, paramKeyEvent);
      return true;
    }
    boolean bool = mLongPressBackDown;
    mLongPressBackDown = false;
    paramKeyEvent = getPanelState(0, false);
    if ((paramKeyEvent != null) && (isOpen))
    {
      if (!bool)
      {
        closePanel(paramKeyEvent, true);
        return true;
      }
    }
    else {
      return onBackPressed();
    }
    return true;
  }
  
  public boolean onMenuItemSelected(MenuBuilder paramMenuBuilder, MenuItem paramMenuItem)
  {
    Window.Callback localCallback = getWindowCallback();
    if ((localCallback != null) && (!mIsDestroyed))
    {
      paramMenuBuilder = findMenuPanel(paramMenuBuilder.getRootMenu());
      if (paramMenuBuilder != null) {
        return localCallback.onMenuItemSelected(featureId, paramMenuItem);
      }
    }
    return false;
  }
  
  public void onMenuModeChange(MenuBuilder paramMenuBuilder)
  {
    reopenMenu(paramMenuBuilder, true);
  }
  
  void onMenuOpened(int paramInt)
  {
    if (paramInt == 108)
    {
      ActionBar localActionBar = getSupportActionBar();
      if (localActionBar != null) {
        localActionBar.dispatchMenuVisibilityChanged(true);
      }
    }
  }
  
  void onPanelClosed(int paramInt)
  {
    Object localObject;
    if (paramInt == 108)
    {
      localObject = getSupportActionBar();
      if (localObject != null) {
        ((ActionBar)localObject).dispatchMenuVisibilityChanged(false);
      }
    }
    else if (paramInt == 0)
    {
      localObject = getPanelState(paramInt, true);
      if (isOpen) {
        closePanel((PanelFeatureState)localObject, false);
      }
    }
  }
  
  void onSubDecorInstalled(ViewGroup paramViewGroup) {}
  
  public final void onTitleChanged(CharSequence paramCharSequence)
  {
    mTitle = paramCharSequence;
    Object localObject = mDecorContentParent;
    if (localObject != null)
    {
      ((DecorContentParent)localObject).setWindowTitle(paramCharSequence);
      return;
    }
    if (peekSupportActionBar() != null)
    {
      peekSupportActionBar().setWindowTitle(paramCharSequence);
      return;
    }
    localObject = mTitleView;
    if (localObject != null) {
      ((TextView)localObject).setText(paramCharSequence);
    }
  }
  
  final ActionBar peekSupportActionBar()
  {
    return mActionBar;
  }
  
  public boolean requestWindowFeature(int paramInt)
  {
    paramInt = sanitizeWindowFeatureId(paramInt);
    if ((mWindowNoTitle) && (paramInt == 108)) {
      return false;
    }
    if ((mHasActionBar) && (paramInt == 1)) {
      mHasActionBar = false;
    }
    if (paramInt != 1)
    {
      if (paramInt != 2)
      {
        if (paramInt != 5)
        {
          if (paramInt != 10)
          {
            if (paramInt != 108)
            {
              if (paramInt != 109) {
                return mWindow.requestFeature(paramInt);
              }
              throwFeatureRequestIfSubDecorInstalled();
              mOverlayActionBar = true;
              return true;
            }
            throwFeatureRequestIfSubDecorInstalled();
            mHasActionBar = true;
            return true;
          }
          throwFeatureRequestIfSubDecorInstalled();
          mOverlayActionMode = true;
          return true;
        }
        throwFeatureRequestIfSubDecorInstalled();
        mFeatureProgress = true;
        return true;
      }
      throwFeatureRequestIfSubDecorInstalled();
      mFeatureIndeterminateProgress = true;
      return true;
    }
    throwFeatureRequestIfSubDecorInstalled();
    mWindowNoTitle = true;
    return true;
  }
  
  public void setContentView(int paramInt)
  {
    ensureSubDecor();
    ViewGroup localViewGroup = (ViewGroup)mSubDecor.findViewById(16908290);
    localViewGroup.removeAllViews();
    LayoutInflater.from(mContext).inflate(paramInt, localViewGroup);
    mOriginalWindowCallback.onContentChanged();
  }
  
  public void setContentView(View paramView)
  {
    ensureSubDecor();
    ViewGroup localViewGroup = (ViewGroup)mSubDecor.findViewById(16908290);
    localViewGroup.removeAllViews();
    localViewGroup.addView(paramView);
    mOriginalWindowCallback.onContentChanged();
  }
  
  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    ensureSubDecor();
    ViewGroup localViewGroup = (ViewGroup)mSubDecor.findViewById(16908290);
    localViewGroup.removeAllViews();
    localViewGroup.addView(paramView, paramLayoutParams);
    mOriginalWindowCallback.onContentChanged();
  }
  
  public ActionMode startSupportActionMode(ActionMode.Callback paramCallback)
  {
    if (paramCallback != null)
    {
      Object localObject = mActionMode;
      if (localObject != null) {
        ((ActionMode)localObject).finish();
      }
      paramCallback = new ActionModeCallbackWrapperV7(paramCallback);
      localObject = getSupportActionBar();
      if (localObject != null)
      {
        mActionMode = ((ActionBar)localObject).startActionMode(paramCallback);
        localObject = mActionMode;
        if (localObject != null)
        {
          AppCompatCallback localAppCompatCallback = mAppCompatCallback;
          if (localAppCompatCallback != null) {
            localAppCompatCallback.onSupportActionModeStarted((ActionMode)localObject);
          }
        }
      }
      if (mActionMode == null) {
        mActionMode = startSupportActionModeFromWindow(paramCallback);
      }
      return mActionMode;
    }
    throw new IllegalArgumentException("ActionMode callback can not be null.");
  }
  
  ActionMode startSupportActionModeFromWindow(ActionMode.Callback paramCallback)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a15 = a14\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer$LiveA.onUseLocal(UnSSATransformer.java:552)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer$LiveA.onUseLocal(UnSSATransformer.java:1)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.onUse(BaseAnalyze.java:166)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.onUse(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.travel(Cfg.java:331)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.travel(Cfg.java:387)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:90)\n\t... 17 more\n");
  }
  
  final class ActionMenuPresenterCallback
    implements l.a
  {
    ActionMenuPresenterCallback() {}
    
    public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
    {
      checkCloseActionMenu(paramMenuBuilder);
    }
    
    public boolean onOpenSubMenu(MenuBuilder paramMenuBuilder)
    {
      Window.Callback localCallback = getWindowCallback();
      if (localCallback != null) {
        localCallback.onMenuOpened(108, paramMenuBuilder);
      }
      return true;
    }
  }
  
  class ActionModeCallbackWrapperV7
    implements ActionMode.Callback
  {
    private ActionMode.Callback mWrapped;
    
    public ActionModeCallbackWrapperV7(ActionMode.Callback paramCallback)
    {
      mWrapped = paramCallback;
    }
    
    public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
    {
      return mWrapped.onActionItemClicked(paramActionMode, paramMenuItem);
    }
    
    public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      return mWrapped.onCreateActionMode(paramActionMode, paramMenu);
    }
    
    public void onDestroyActionMode(ActionMode paramActionMode)
    {
      mWrapped.onDestroyActionMode(paramActionMode);
      paramActionMode = AppCompatDelegateImplV7.this;
      if (mActionModePopup != null) {
        mWindow.getDecorView().removeCallbacks(mShowActionModePopup);
      }
      paramActionMode = AppCompatDelegateImplV7.this;
      if (mActionModeView != null)
      {
        paramActionMode.endOnGoingFadeAnimation();
        paramActionMode = AppCompatDelegateImplV7.this;
        localObject = ViewCompat.animate(mActionModeView);
        ((ViewPropertyAnimatorCompat)localObject).alpha(0.0F);
        mFadeAnim = ((ViewPropertyAnimatorCompat)localObject);
        mFadeAnim.setListener(new AppCompatDelegateImplV7.ActionModeCallbackWrapperV7.1(this));
      }
      paramActionMode = AppCompatDelegateImplV7.this;
      Object localObject = mAppCompatCallback;
      if (localObject != null) {
        ((AppCompatCallback)localObject).onSupportActionModeFinished(mActionMode);
      }
      mActionMode = null;
    }
    
    public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      return mWrapped.onPrepareActionMode(paramActionMode, paramMenu);
    }
  }
  
  class ListMenuDecorView
    extends ContentFrameLayout
  {
    public ListMenuDecorView(Context paramContext)
    {
      super();
    }
    
    private boolean isOutOfBounds(int paramInt1, int paramInt2)
    {
      return (paramInt1 < -5) || (paramInt2 < -5) || (paramInt1 > getWidth() + 5) || (paramInt2 > getHeight() + 5);
    }
    
    public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
    {
      return (AppCompatDelegateImplV7.this.dispatchKeyEvent(paramKeyEvent)) || (super.dispatchKeyEvent(paramKeyEvent));
    }
    
    public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
    {
      if ((paramMotionEvent.getAction() == 0) && (isOutOfBounds((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY())))
      {
        closePanel(0);
        return true;
      }
      return super.onInterceptTouchEvent(paramMotionEvent);
    }
    
    public void setBackgroundResource(int paramInt)
    {
      setBackgroundDrawable(com.org.v4.text.view.Resources.getDrawable(getContext(), paramInt));
    }
  }
  
  public final class PanelFeatureState
  {
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
    boolean refreshDecorView;
    boolean refreshMenuContent;
    View shownPanelView;
    int windowAnimations;
    int x;
    int y;
    
    PanelFeatureState()
    {
      featureId = this$1;
      refreshDecorView = false;
    }
    
    MenuView getListMenuView(l.a paramA)
    {
      if (menu == null) {
        return null;
      }
      if (listMenuPresenter == null)
      {
        listMenuPresenter = new ListMenuPresenter(listPresenterContext, R.layout.abc_list_menu_item_layout);
        listMenuPresenter.a(paramA);
        menu.addMenuPresenter(listMenuPresenter);
      }
      return listMenuPresenter.getMenuView(decorView);
    }
    
    public boolean hasPanelItems()
    {
      if (shownPanelView == null) {
        return false;
      }
      if (createdPanelView != null) {
        return true;
      }
      return listMenuPresenter.getAdapter().getCount() > 0;
    }
    
    void setMenu(MenuBuilder paramMenuBuilder)
    {
      Object localObject = menu;
      if (paramMenuBuilder == localObject) {
        return;
      }
      if (localObject != null) {
        ((MenuBuilder)localObject).removeMenuPresenter(listMenuPresenter);
      }
      menu = paramMenuBuilder;
      if (paramMenuBuilder != null)
      {
        localObject = listMenuPresenter;
        if (localObject != null) {
          paramMenuBuilder.addMenuPresenter((MenuPresenter)localObject);
        }
      }
    }
    
    void setStyle(Context paramContext)
    {
      TypedValue localTypedValue = new TypedValue();
      Resources.Theme localTheme = paramContext.getResources().newTheme();
      localTheme.setTo(paramContext.getTheme());
      localTheme.resolveAttribute(R.attr.actionBarPopupTheme, localTypedValue, true);
      int i = resourceId;
      if (i != 0) {
        localTheme.applyStyle(i, true);
      }
      localTheme.resolveAttribute(R.attr.panelMenuListTheme, localTypedValue, true);
      i = resourceId;
      if (i == 0) {
        i = R.style.Theme_AppCompat_CompactMenu;
      }
      localTheme.applyStyle(i, true);
      paramContext = new ContextThemeWrapper(paramContext, 0);
      paramContext.getTheme().setTo(localTheme);
      listPresenterContext = paramContext;
      paramContext = paramContext.obtainStyledAttributes(R.styleable.AppCompatTheme);
      background = paramContext.getResourceId(R.styleable.AppCompatTheme_panelBackground, 0);
      windowAnimations = paramContext.getResourceId(R.styleable.AppCompatTheme_android_windowAnimationStyle, 0);
      paramContext.recycle();
    }
  }
  
  final class PanelMenuPresenterCallback
    implements l.a
  {
    PanelMenuPresenterCallback() {}
    
    public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
    {
      MenuBuilder localMenuBuilder = paramMenuBuilder.getRootMenu();
      int i;
      if (localMenuBuilder != paramMenuBuilder) {
        i = 1;
      } else {
        i = 0;
      }
      AppCompatDelegateImplV7 localAppCompatDelegateImplV7 = AppCompatDelegateImplV7.this;
      if (i != 0) {
        paramMenuBuilder = localMenuBuilder;
      }
      paramMenuBuilder = localAppCompatDelegateImplV7.findMenuPanel(paramMenuBuilder);
      if (paramMenuBuilder != null)
      {
        if (i != 0)
        {
          callOnPanelClosed(featureId, paramMenuBuilder, localMenuBuilder);
          closePanel(paramMenuBuilder, true);
          return;
        }
        closePanel(paramMenuBuilder, paramBoolean);
      }
    }
    
    public boolean onOpenSubMenu(MenuBuilder paramMenuBuilder)
    {
      if (paramMenuBuilder == null)
      {
        Object localObject = AppCompatDelegateImplV7.this;
        if (mHasActionBar)
        {
          localObject = ((AppCompatDelegateImplV7)localObject).getWindowCallback();
          if ((localObject != null) && (!mIsDestroyed)) {
            ((Window.Callback)localObject).onMenuOpened(108, paramMenuBuilder);
          }
        }
      }
      return true;
    }
  }
}

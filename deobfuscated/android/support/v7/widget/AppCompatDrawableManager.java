package android.support.v7.widget;

import a.a.c.f.b;
import a.a.c.f.d;
import a.a.c.f.j;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import com.org.android.asm.ColorUtils;
import com.org.android.util.ArrayMap;
import com.org.android.util.ByteVector;
import com.org.android.util.LongSparseArray;
import com.org.android.util.SimpleArrayMap;
import com.org.shortcuts.drawable.AnimatedVectorDrawableCompat;
import com.org.v4.util.R.attr;
import com.org.v4.util.R.color;
import com.org.v4.util.R.drawable;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class AppCompatDrawableManager
{
  private static final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY;
  private static final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED;
  private static final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL;
  private static final TintManager.ColorFilterLruCache COLOR_FILTER_CACHE;
  private static final PorterDuff.Mode DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
  private static AppCompatDrawableManager INSTANCE;
  private static final int[] TINT_CHECKABLE_BUTTON_LIST = { R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material };
  private static final int[] TINT_COLOR_CONTROL_NORMAL;
  private static final int[] TINT_COLOR_CONTROL_STATE_LIST;
  private boolean goRight;
  private b<String, o.d> mDelegates;
  private final WeakHashMap<Context, d<WeakReference<Drawable.ConstantState>>> mDrawableCaches = new WeakHashMap(0);
  private j<String> mKnownDrawableIdTags;
  private WeakHashMap<Context, j<ColorStateList>> mTintLists;
  private TypedValue mTypedValue;
  
  static
  {
    COLOR_FILTER_CACHE = new TintManager.ColorFilterLruCache(6);
    COLORFILTER_TINT_COLOR_CONTROL_NORMAL = new int[] { R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha };
    TINT_COLOR_CONTROL_NORMAL = new int[] { R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_seekbar_tick_mark_material, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha };
    COLORFILTER_COLOR_CONTROL_ACTIVATED = new int[] { R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_material, R.drawable.abc_text_select_handle_left_mtrl_dark, R.drawable.abc_text_select_handle_middle_mtrl_dark, R.drawable.abc_text_select_handle_right_mtrl_dark, R.drawable.abc_text_select_handle_left_mtrl_light, R.drawable.abc_text_select_handle_middle_mtrl_light, R.drawable.abc_text_select_handle_right_mtrl_light };
    COLORFILTER_COLOR_BACKGROUND_MULTIPLY = new int[] { R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult };
    TINT_COLOR_CONTROL_STATE_LIST = new int[] { R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material };
  }
  
  public AppCompatDrawableManager() {}
  
  private void addDelegate(String paramString, InflateDelegate paramInflateDelegate)
  {
    if (mDelegates == null) {
      mDelegates = new ArrayMap();
    }
    mDelegates.put(paramString, paramInflateDelegate);
  }
  
  /* Error */
  private boolean addDrawableToCache(Context paramContext, long paramLong, Drawable paramDrawable)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload 4
    //   4: invokevirtual 184	android/graphics/drawable/Drawable:getConstantState	()Landroid/graphics/drawable/Drawable$ConstantState;
    //   7: astore 7
    //   9: aload 7
    //   11: ifnull +68 -> 79
    //   14: aload_0
    //   15: getfield 161	android/support/v7/widget/AppCompatDrawableManager:mDrawableCaches	Ljava/util/WeakHashMap;
    //   18: aload_1
    //   19: invokevirtual 188	java/util/WeakHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   22: checkcast 190	com/org/android/util/LongSparseArray
    //   25: astore 6
    //   27: aload 6
    //   29: astore 4
    //   31: aload 6
    //   33: ifnonnull +23 -> 56
    //   36: new 190	com/org/android/util/LongSparseArray
    //   39: dup
    //   40: invokespecial 191	com/org/android/util/LongSparseArray:<init>	()V
    //   43: astore 4
    //   45: aload_0
    //   46: getfield 161	android/support/v7/widget/AppCompatDrawableManager:mDrawableCaches	Ljava/util/WeakHashMap;
    //   49: aload_1
    //   50: aload 4
    //   52: invokevirtual 192	java/util/WeakHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   55: pop
    //   56: aload 4
    //   58: lload_2
    //   59: new 194	java/lang/ref/WeakReference
    //   62: dup
    //   63: aload 7
    //   65: invokespecial 197	java/lang/ref/WeakReference:<init>	(Ljava/lang/Object;)V
    //   68: invokevirtual 200	com/org/android/util/LongSparseArray:put	(JLjava/lang/Object;)V
    //   71: iconst_1
    //   72: istore 5
    //   74: aload_0
    //   75: monitorexit
    //   76: iload 5
    //   78: ireturn
    //   79: iconst_0
    //   80: istore 5
    //   82: goto -8 -> 74
    //   85: astore_1
    //   86: aload_0
    //   87: monitorexit
    //   88: goto +3 -> 91
    //   91: aload_1
    //   92: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	93	0	this	AppCompatDrawableManager
    //   0	93	1	paramContext	Context
    //   0	93	2	paramLong	long
    //   0	93	4	paramDrawable	Drawable
    //   72	9	5	bool	boolean
    //   25	7	6	localLongSparseArray	LongSparseArray
    //   7	57	7	localConstantState	Drawable.ConstantState
    // Exception table:
    //   from	to	target	type
    //   2	9	85	java/lang/Throwable
    //   14	27	85	java/lang/Throwable
    //   36	56	85	java/lang/Throwable
    //   56	71	85	java/lang/Throwable
  }
  
  private void addTintListToCache(Context paramContext, int paramInt, ColorStateList paramColorStateList)
  {
    if (mTintLists == null) {
      mTintLists = new WeakHashMap();
    }
    ByteVector localByteVector2 = (ByteVector)mTintLists.get(paramContext);
    ByteVector localByteVector1 = localByteVector2;
    if (localByteVector2 == null)
    {
      localByteVector1 = new ByteVector();
      mTintLists.put(paramContext, localByteVector1);
    }
    localByteVector1.d(paramInt, paramColorStateList);
  }
  
  private static boolean arrayContains(int[] paramArrayOfInt, int paramInt)
  {
    int j = paramArrayOfInt.length;
    int i = 0;
    while (i < j)
    {
      if (paramArrayOfInt[i] == paramInt) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private ColorStateList createBorderlessButtonColorStateList(Context paramContext)
  {
    return createButtonColorStateList(paramContext, 0);
  }
  
  private ColorStateList createButtonColorStateList(Context paramContext, int paramInt)
  {
    int k = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlHighlight);
    int i = ThemeUtils.getDisabledThemeAttrColor(paramContext, R.attr.colorButtonNormal);
    paramContext = ThemeUtils.DISABLED_STATE_SET;
    int[] arrayOfInt1 = ThemeUtils.PRESSED_STATE_SET;
    int j = ColorUtils.compositeColors(k, paramInt);
    int[] arrayOfInt2 = ThemeUtils.FOCUSED_STATE_SET;
    k = ColorUtils.compositeColors(k, paramInt);
    return new ColorStateList(new int[][] { paramContext, arrayOfInt1, arrayOfInt2, ThemeUtils.EMPTY_STATE_SET }, new int[] { i, j, k, paramInt });
  }
  
  private static long createCacheKey(TypedValue paramTypedValue)
  {
    return assetCookie << 32 | data;
  }
  
  private ColorStateList createColoredButtonColorStateList(Context paramContext)
  {
    return createButtonColorStateList(paramContext, ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorAccent));
  }
  
  private ColorStateList createDefaultButtonColorStateList(Context paramContext)
  {
    return createButtonColorStateList(paramContext, ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorButtonNormal));
  }
  
  private Drawable createDrawableIfNeeded(Context paramContext, int paramInt)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a8 = a7\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer$LiveA.onUseLocal(UnSSATransformer.java:552)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer$LiveA.onUseLocal(UnSSATransformer.java:1)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.onUse(BaseAnalyze.java:166)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.onUse(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.travel(Cfg.java:331)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.travel(Cfg.java:387)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:90)\n\t... 17 more\n");
  }
  
  private ColorStateList createSwitchThumbColorStateList(Context paramContext)
  {
    int[][] arrayOfInt = new int[3][];
    int[] arrayOfInt1 = new int[3];
    ColorStateList localColorStateList = ThemeUtils.getThemeAttrColorStateList(paramContext, R.attr.colorSwitchThumbNormal);
    if ((localColorStateList != null) && (localColorStateList.isStateful()))
    {
      arrayOfInt[0] = ThemeUtils.DISABLED_STATE_SET;
      arrayOfInt1[0] = localColorStateList.getColorForState(arrayOfInt[0], 0);
      arrayOfInt[1] = ThemeUtils.CHECKED_STATE_SET;
      arrayOfInt1[1] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated);
      arrayOfInt[2] = ThemeUtils.EMPTY_STATE_SET;
      arrayOfInt1[2] = localColorStateList.getDefaultColor();
    }
    else
    {
      arrayOfInt[0] = ThemeUtils.DISABLED_STATE_SET;
      arrayOfInt1[0] = ThemeUtils.getDisabledThemeAttrColor(paramContext, R.attr.colorSwitchThumbNormal);
      arrayOfInt[1] = ThemeUtils.CHECKED_STATE_SET;
      arrayOfInt1[1] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated);
      arrayOfInt[2] = ThemeUtils.EMPTY_STATE_SET;
      arrayOfInt1[2] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorSwitchThumbNormal);
    }
    return new ColorStateList(arrayOfInt, arrayOfInt1);
  }
  
  private static PorterDuffColorFilter createTintFilter(ColorStateList paramColorStateList, PorterDuff.Mode paramMode, int[] paramArrayOfInt)
  {
    if ((paramColorStateList != null) && (paramMode != null)) {
      return getPorterDuffColorFilter(paramColorStateList.getColorForState(paramArrayOfInt, 0), paramMode);
    }
    return null;
  }
  
  public static AppCompatDrawableManager get()
  {
    try
    {
      if (INSTANCE == null)
      {
        INSTANCE = new AppCompatDrawableManager();
        installDefaultInflateDelegates(INSTANCE);
      }
      AppCompatDrawableManager localAppCompatDrawableManager = INSTANCE;
      return localAppCompatDrawableManager;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  private Drawable getCachedDrawable(Context paramContext, long paramLong)
  {
    try
    {
      LongSparseArray localLongSparseArray = (LongSparseArray)mDrawableCaches.get(paramContext);
      if (localLongSparseArray == null) {
        return null;
      }
      Object localObject = (WeakReference)localLongSparseArray.get(paramLong);
      if (localObject != null)
      {
        localObject = (Drawable.ConstantState)((WeakReference)localObject).get();
        if (localObject != null)
        {
          paramContext = ((Drawable.ConstantState)localObject).newDrawable(paramContext.getResources());
          return paramContext;
        }
        localLongSparseArray.delete(paramLong);
      }
      return null;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  public static PorterDuffColorFilter getPorterDuffColorFilter(int paramInt, PorterDuff.Mode paramMode)
  {
    try
    {
      PorterDuffColorFilter localPorterDuffColorFilter2 = COLOR_FILTER_CACHE.get(paramInt, paramMode);
      PorterDuffColorFilter localPorterDuffColorFilter1 = localPorterDuffColorFilter2;
      if (localPorterDuffColorFilter2 == null)
      {
        localPorterDuffColorFilter1 = new PorterDuffColorFilter(paramInt, paramMode);
        COLOR_FILTER_CACHE.put(paramInt, paramMode, localPorterDuffColorFilter1);
      }
      return localPorterDuffColorFilter1;
    }
    catch (Throwable paramMode)
    {
      throw paramMode;
    }
  }
  
  private ColorStateList getTintListFromCache(Context paramContext, int paramInt)
  {
    WeakHashMap localWeakHashMap = mTintLists;
    if (localWeakHashMap != null)
    {
      paramContext = (ByteVector)localWeakHashMap.get(paramContext);
      if (paramContext != null) {
        return (ColorStateList)paramContext.get(paramInt);
      }
    }
    return null;
  }
  
  static PorterDuff.Mode getTintMode(int paramInt)
  {
    if (paramInt == R.drawable.abc_switch_thumb_material) {
      return PorterDuff.Mode.MULTIPLY;
    }
    return null;
  }
  
  private static void installDefaultInflateDelegates(AppCompatDrawableManager paramAppCompatDrawableManager)
  {
    if (Build.VERSION.SDK_INT < 24)
    {
      paramAppCompatDrawableManager.addDelegate("vector", new ServiceController());
      paramAppCompatDrawableManager.addDelegate("animated-vector", new AvdcInflateDelegate());
      paramAppCompatDrawableManager.addDelegate("animated-selector", new VdcInflateDelegate());
    }
  }
  
  private static boolean isVectorDrawable(Drawable paramDrawable)
  {
    return ((paramDrawable instanceof com.org.shortcuts.drawable.VectorDrawableCompat)) || ("android.graphics.drawable.VectorDrawable".equals(paramDrawable.getClass().getName()));
  }
  
  private Drawable loadDrawableFromDelegates(Context paramContext, int paramInt)
  {
    Object localObject1 = mDelegates;
    Object localObject3;
    if ((localObject1 != null) && (!((SimpleArrayMap)localObject1).isEmpty()))
    {
      localObject1 = mKnownDrawableIdTags;
      if (localObject1 != null)
      {
        localObject1 = (String)((ByteVector)localObject1).get(paramInt);
        if ("appcompat_skip_skip".equals(localObject1)) {
          break label420;
        }
        if ((localObject1 != null) && (mDelegates.get(localObject1) == null)) {
          return null;
        }
      }
      else
      {
        mKnownDrawableIdTags = new ByteVector();
      }
      if (mTypedValue == null) {
        mTypedValue = new TypedValue();
      }
      TypedValue localTypedValue = mTypedValue;
      Object localObject4 = paramContext.getResources();
      ((android.content.res.Resources)localObject4).getValue(paramInt, localTypedValue, true);
      long l = createCacheKey(localTypedValue);
      Object localObject2 = getCachedDrawable(paramContext, l);
      localObject1 = localObject2;
      if (localObject2 != null) {
        return localObject2;
      }
      localObject2 = string;
      localObject3 = localObject1;
      if (localObject2 != null)
      {
        localObject3 = localObject1;
        if (((CharSequence)localObject2).toString().endsWith(".xml"))
        {
          localObject3 = localObject1;
          try
          {
            localObject4 = ((android.content.res.Resources)localObject4).getXml(paramInt);
            localObject3 = localObject1;
            AttributeSet localAttributeSet = Xml.asAttributeSet((XmlPullParser)localObject4);
            int i;
            do
            {
              localObject3 = localObject1;
              i = ((XmlPullParser)localObject4).next();
            } while ((i != 2) && (i != 1));
            if (i == 2)
            {
              localObject3 = localObject1;
              localObject2 = ((XmlPullParser)localObject4).getName();
              Object localObject5 = mKnownDrawableIdTags;
              localObject3 = localObject1;
              ((ByteVector)localObject5).d(paramInt, localObject2);
              localObject5 = mDelegates;
              localObject3 = localObject1;
              localObject2 = ((SimpleArrayMap)localObject5).get(localObject2);
              localObject5 = (InflateDelegate)localObject2;
              localObject2 = localObject1;
              if (localObject5 != null)
              {
                localObject3 = localObject1;
                localObject2 = ((InflateDelegate)localObject5).createFromXmlInner(paramContext, (XmlPullParser)localObject4, localAttributeSet, paramContext.getTheme());
              }
              localObject3 = localObject2;
              if (localObject2 != null)
              {
                i = changingConfigurations;
                localObject3 = localObject2;
                ((Drawable)localObject2).setChangingConfigurations(i);
                localObject3 = localObject2;
                addDrawableToCache(paramContext, l, (Drawable)localObject2);
                localObject3 = localObject2;
              }
            }
            else
            {
              localObject3 = localObject1;
              paramContext = new XmlPullParserException("No start tag found");
              throw paramContext;
            }
          }
          catch (Exception paramContext)
          {
            Log.e("AppCompatDrawableManag", "Exception while inflating drawable", paramContext);
          }
        }
      }
      if (localObject3 != null) {
        break label422;
      }
      mKnownDrawableIdTags.d(paramInt, "appcompat_skip_skip");
      return localObject3;
    }
    label420:
    return null;
    label422:
    return localObject3;
  }
  
  private static void setPorterDuffColorFilter(Drawable paramDrawable, int paramInt, PorterDuff.Mode paramMode)
  {
    Drawable localDrawable = paramDrawable;
    if (DrawableUtils.canSafelyMutateDrawable(paramDrawable)) {
      localDrawable = paramDrawable.mutate();
    }
    paramDrawable = paramMode;
    if (paramMode == null) {
      paramDrawable = DEFAULT_MODE;
    }
    localDrawable.setColorFilter(getPorterDuffColorFilter(paramInt, paramDrawable));
  }
  
  private Drawable tintDrawable(Context paramContext, int paramInt, boolean paramBoolean, Drawable paramDrawable)
  {
    Object localObject = getTintList(paramContext, paramInt);
    if (localObject != null)
    {
      paramContext = paramDrawable;
      if (DrawableUtils.canSafelyMutateDrawable(paramDrawable)) {
        paramContext = paramDrawable.mutate();
      }
      paramContext = DrawableCompat.wrap(paramContext);
      DrawableCompat.setTintList(paramContext, (ColorStateList)localObject);
      paramDrawable = getTintMode(paramInt);
      localObject = paramContext;
      if (paramDrawable != null)
      {
        DrawableCompat.setTintMode(paramContext, paramDrawable);
        return paramContext;
      }
    }
    else
    {
      Drawable localDrawable;
      if (paramInt == R.drawable.abc_seekbar_track_material)
      {
        localObject = (LayerDrawable)paramDrawable;
        setPorterDuffColorFilter(((LayerDrawable)localObject).findDrawableByLayerId(16908288), ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlNormal), DEFAULT_MODE);
        localDrawable = ((LayerDrawable)localObject).findDrawableByLayerId(16908303);
      }
      for (paramInt = R.attr.colorControlNormal;; paramInt = R.attr.colorControlActivated)
      {
        setPorterDuffColorFilter(localDrawable, ThemeUtils.getThemeAttrColor(paramContext, paramInt), DEFAULT_MODE);
        setPorterDuffColorFilter(((LayerDrawable)localObject).findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated), DEFAULT_MODE);
        return paramDrawable;
        if ((paramInt != R.drawable.abc_ratingbar_material) && (paramInt != R.drawable.abc_ratingbar_indicator_material) && (paramInt != R.drawable.abc_ratingbar_small_material))
        {
          localObject = paramDrawable;
          if (tintDrawableUsingColorFilter(paramContext, paramInt, paramDrawable)) {
            break;
          }
          localObject = paramDrawable;
          if (!paramBoolean) {
            break;
          }
          return null;
        }
        localObject = (LayerDrawable)paramDrawable;
        setPorterDuffColorFilter(((LayerDrawable)localObject).findDrawableByLayerId(16908288), ThemeUtils.getDisabledThemeAttrColor(paramContext, R.attr.colorControlNormal), DEFAULT_MODE);
        localDrawable = ((LayerDrawable)localObject).findDrawableByLayerId(16908303);
      }
    }
    return localObject;
  }
  
  private void tintDrawable(Context paramContext)
  {
    if (goRight) {
      return;
    }
    goRight = true;
    paramContext = getDrawable(paramContext, R.drawable.abc_vector_test);
    if ((paramContext != null) && (isVectorDrawable(paramContext))) {
      return;
    }
    goRight = false;
    throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
  }
  
  static void tintDrawable(Drawable paramDrawable, TintInfo paramTintInfo, int[] paramArrayOfInt)
  {
    if ((DrawableUtils.canSafelyMutateDrawable(paramDrawable)) && (paramDrawable.mutate() != paramDrawable))
    {
      Log.d("AppCompatDrawableManag", "Mutated drawable is not the same instance as the input.");
      return;
    }
    if ((!mHasTintList) && (!mHasTintMode))
    {
      paramDrawable.clearColorFilter();
    }
    else
    {
      ColorStateList localColorStateList;
      if (mHasTintList) {
        localColorStateList = mTintList;
      } else {
        localColorStateList = null;
      }
      if (mHasTintMode) {
        paramTintInfo = mTintMode;
      } else {
        paramTintInfo = DEFAULT_MODE;
      }
      paramDrawable.setColorFilter(createTintFilter(localColorStateList, paramTintInfo, paramArrayOfInt));
    }
    if (Build.VERSION.SDK_INT <= 23) {
      paramDrawable.invalidateSelf();
    }
  }
  
  static boolean tintDrawableUsingColorFilter(Context paramContext, int paramInt, Drawable paramDrawable)
  {
    PorterDuff.Mode localMode = DEFAULT_MODE;
    boolean bool = arrayContains(COLORFILTER_TINT_COLOR_CONTROL_NORMAL, paramInt);
    int i = 16842801;
    if (bool) {
      paramInt = R.attr.colorControlNormal;
    }
    for (;;)
    {
      i = 1;
      j = -1;
      break label115;
      if (arrayContains(COLORFILTER_COLOR_CONTROL_ACTIVATED, paramInt))
      {
        paramInt = R.attr.colorControlActivated;
      }
      else if (arrayContains(COLORFILTER_COLOR_BACKGROUND_MULTIPLY, paramInt))
      {
        localMode = PorterDuff.Mode.MULTIPLY;
        paramInt = i;
      }
      else
      {
        if (paramInt == R.drawable.abc_list_divider_mtrl_alpha)
        {
          paramInt = 16842800;
          j = Math.round(40.8F);
          i = 1;
          break label115;
        }
        if (paramInt != R.drawable.abc_dialog_material_background) {
          break;
        }
        paramInt = i;
      }
    }
    i = 0;
    int j = -1;
    paramInt = 0;
    label115:
    if (i != 0)
    {
      Drawable localDrawable = paramDrawable;
      if (DrawableUtils.canSafelyMutateDrawable(paramDrawable)) {
        localDrawable = paramDrawable.mutate();
      }
      localDrawable.setColorFilter(getPorterDuffColorFilter(ThemeUtils.getThemeAttrColor(paramContext, paramInt), localMode));
      if (j != -1)
      {
        localDrawable.setAlpha(j);
        return true;
      }
    }
    else
    {
      return false;
    }
    return true;
  }
  
  public Drawable getDrawable(Context paramContext, int paramInt)
  {
    try
    {
      paramContext = getDrawable(paramContext, paramInt, false);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  Drawable getDrawable(Context paramContext, int paramInt, boolean paramBoolean)
  {
    try
    {
      tintDrawable(paramContext);
      Object localObject2 = loadDrawableFromDelegates(paramContext, paramInt);
      Object localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = createDrawableIfNeeded(paramContext, paramInt);
      }
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = com.org.android.ui.Resources.getDrawable(paramContext, paramInt);
      }
      localObject1 = localObject2;
      if (localObject2 != null) {
        localObject1 = tintDrawable(paramContext, paramInt, paramBoolean, (Drawable)localObject2);
      }
      if (localObject1 != null) {
        DrawableUtils.fixDrawable((Drawable)localObject1);
      }
      return localObject1;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  Drawable getDrawable(Context paramContext, TintManager paramTintManager, int paramInt)
  {
    try
    {
      Drawable localDrawable2 = loadDrawableFromDelegates(paramContext, paramInt);
      Drawable localDrawable1 = localDrawable2;
      if (localDrawable2 == null) {
        localDrawable1 = paramTintManager.superGetDrawable(paramInt);
      }
      if (localDrawable1 != null)
      {
        paramContext = tintDrawable(paramContext, paramInt, false, localDrawable1);
        return paramContext;
      }
      return null;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  ColorStateList getTintList(Context paramContext, int paramInt)
  {
    try
    {
      ColorStateList localColorStateList3 = getTintListFromCache(paramContext, paramInt);
      ColorStateList localColorStateList1 = localColorStateList3;
      ColorStateList localColorStateList2 = localColorStateList1;
      if (localColorStateList3 == null)
      {
        int i;
        if (paramInt == R.drawable.abc_edit_text_material) {
          i = R.color.abc_tint_edittext;
        }
        for (;;)
        {
          localColorStateList1 = com.org.v4.text.view.Resources.show(paramContext, i);
          break;
          if (paramInt == R.drawable.abc_switch_track_mtrl_alpha)
          {
            i = R.color.abc_tint_switch_track;
          }
          else
          {
            if (paramInt == R.drawable.abc_switch_thumb_material)
            {
              localColorStateList1 = createSwitchThumbColorStateList(paramContext);
              break;
            }
            if (paramInt == R.drawable.abc_btn_default_mtrl_shape)
            {
              localColorStateList1 = createDefaultButtonColorStateList(paramContext);
              break;
            }
            if (paramInt == R.drawable.abc_btn_borderless_material)
            {
              localColorStateList1 = createBorderlessButtonColorStateList(paramContext);
              break;
            }
            if (paramInt == R.drawable.abc_btn_colored_material)
            {
              localColorStateList1 = createColoredButtonColorStateList(paramContext);
              break;
            }
            if ((paramInt != R.drawable.abc_spinner_mtrl_am_alpha) && (paramInt != R.drawable.abc_spinner_textfield_background_material))
            {
              if (arrayContains(TINT_COLOR_CONTROL_NORMAL, paramInt))
              {
                localColorStateList1 = ThemeUtils.getThemeAttrColorStateList(paramContext, R.attr.colorControlNormal);
                break;
              }
              if (arrayContains(TINT_COLOR_CONTROL_STATE_LIST, paramInt))
              {
                i = R.color.abc_tint_default;
              }
              else if (arrayContains(TINT_CHECKABLE_BUTTON_LIST, paramInt))
              {
                i = R.color.abc_tint_btn_checkable;
              }
              else
              {
                if (paramInt != R.drawable.abc_seekbar_thumb_material) {
                  break;
                }
                i = R.color.abc_tint_seek_thumb;
              }
            }
            else
            {
              i = R.color.abc_tint_spinner;
            }
          }
        }
        localColorStateList2 = localColorStateList1;
        if (localColorStateList1 != null)
        {
          addTintListToCache(paramContext, paramInt, localColorStateList1);
          localColorStateList2 = localColorStateList1;
        }
      }
      return localColorStateList2;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  class AvdcInflateDelegate
    implements AppCompatDrawableManager.InflateDelegate
  {
    AvdcInflateDelegate() {}
    
    public Drawable createFromXmlInner(Context paramContext, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
    {
      try
      {
        paramContext = AnimatedVectorDrawableCompat.createFromXmlInner(paramContext, paramContext.getResources(), paramXmlPullParser, paramAttributeSet, paramTheme);
        return paramContext;
      }
      catch (Exception paramContext)
      {
        Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", paramContext);
      }
      return null;
    }
  }
  
  abstract interface InflateDelegate
  {
    public abstract Drawable createFromXmlInner(Context paramContext, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme);
  }
  
  class VdcInflateDelegate
    implements AppCompatDrawableManager.InflateDelegate
  {
    VdcInflateDelegate() {}
    
    public Drawable createFromXmlInner(Context paramContext, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
    {
      try
      {
        paramContext = com.org.v4.graphics.drawable.VectorDrawableCompat.createFromXmlInner(paramContext, paramContext.getResources(), paramXmlPullParser, paramAttributeSet, paramTheme);
        return paramContext;
      }
      catch (Exception paramContext)
      {
        Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", paramContext);
      }
      return null;
    }
  }
}

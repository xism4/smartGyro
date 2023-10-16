package com.org.shortcuts.drawable;

import a.a.b.a.k.d;
import a.a.c.f.b;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Build.VERSION;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import com.org.android.asm.PathParser;
import com.org.android.asm.PathParser.PathDataNode;
import com.org.android.ui.asm.ClassReader;
import com.org.android.ui.asm.Label;
import com.org.android.ui.asm.TypedArrayUtils;
import com.org.android.util.ArrayMap;
import com.org.android.util.SimpleArrayMap;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class VectorDrawableCompat
  extends VectorDrawableCommon
{
  static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
  private boolean mAllowCaching = true;
  private Drawable.ConstantState mCachedConstantStateDelegate;
  private ColorFilter mColorFilter;
  private boolean mMutated;
  private PorterDuffColorFilter mTintFilter;
  private final Rect mTmpBounds = new Rect();
  private final float[] mTmpFloats = new float[9];
  private final Matrix mTmpMatrix = new Matrix();
  private VectorDrawableCompatState mVectorState;
  
  VectorDrawableCompat()
  {
    mVectorState = new VectorDrawableCompatState();
  }
  
  VectorDrawableCompat(VectorDrawableCompatState paramVectorDrawableCompatState)
  {
    mVectorState = paramVectorDrawableCompatState;
    mTintFilter = updateTintFilter(mTintFilter, mTint, mTintMode);
  }
  
  static int access$getAlpha(int paramInt, float paramFloat)
  {
    return paramInt & 0xFFFFFF | (int)(Color.alpha(paramInt) * paramFloat) << 24;
  }
  
  public static VectorDrawableCompat create(Resources paramResources, int paramInt, Resources.Theme paramTheme)
  {
    Object localObject;
    if (Build.VERSION.SDK_INT >= 24)
    {
      localObject = new VectorDrawableCompat();
      mDelegateDrawable = ClassReader.getDrawable(paramResources, paramInt, paramTheme);
      mCachedConstantStateDelegate = new AnimatedVectorDrawableCompat.AnimatedVectorDrawableDelegateState(mDelegateDrawable.getConstantState());
      return localObject;
    }
    try
    {
      localObject = paramResources.getXml(paramInt);
      AttributeSet localAttributeSet = Xml.asAttributeSet((XmlPullParser)localObject);
      do
      {
        paramInt = ((XmlPullParser)localObject).next();
      } while ((paramInt != 2) && (paramInt != 1));
      if (paramInt == 2)
      {
        paramResources = createFromXmlInner(paramResources, (XmlPullParser)localObject, localAttributeSet, paramTheme);
        return paramResources;
      }
      paramResources = new XmlPullParserException("No start tag found");
      throw paramResources;
    }
    catch (IOException paramResources) {}catch (XmlPullParserException paramResources) {}
    Log.e("VectorDrawableCompat", "parser error", paramResources);
    return null;
  }
  
  public static VectorDrawableCompat createFromXmlInner(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
  {
    VectorDrawableCompat localVectorDrawableCompat = new VectorDrawableCompat();
    localVectorDrawableCompat.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    return localVectorDrawableCompat;
  }
  
  private boolean draw()
  {
    return (Build.VERSION.SDK_INT >= 17) && (isAutoMirrored()) && (DrawableCompat.getLayoutDirection(this) == 1);
  }
  
  private void inflateInternal(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a16 = a15\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  private static PorterDuff.Mode parseTintModeCompat(int paramInt, PorterDuff.Mode paramMode)
  {
    if (paramInt != 3)
    {
      if (paramInt != 5)
      {
        if (paramInt != 9)
        {
          switch (paramInt)
          {
          default: 
            return paramMode;
          case 16: 
            return PorterDuff.Mode.ADD;
          case 15: 
            return PorterDuff.Mode.SCREEN;
          }
          return PorterDuff.Mode.MULTIPLY;
        }
        return PorterDuff.Mode.SRC_ATOP;
      }
      return PorterDuff.Mode.SRC_IN;
    }
    return PorterDuff.Mode.SRC_OVER;
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray, XmlPullParser paramXmlPullParser)
  {
    VectorDrawableCompatState localVectorDrawableCompatState = mVectorState;
    VPathRenderer localVPathRenderer = mVPathRenderer;
    mTintMode = parseTintModeCompat(TypedArrayUtils.getString(paramTypedArray, paramXmlPullParser, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
    ColorStateList localColorStateList = paramTypedArray.getColorStateList(1);
    if (localColorStateList != null) {
      mTint = localColorStateList;
    }
    mAutoMirrored = TypedArrayUtils.getNamedBoolean(paramTypedArray, paramXmlPullParser, "autoMirrored", 5, mAutoMirrored);
    mViewportWidth = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "viewportWidth", 7, mViewportWidth);
    mViewportHeight = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "viewportHeight", 8, mViewportHeight);
    if (mViewportWidth > 0.0F)
    {
      if (mViewportHeight > 0.0F)
      {
        mBaseWidth = paramTypedArray.getDimension(3, mBaseWidth);
        mBaseHeight = paramTypedArray.getDimension(2, mBaseHeight);
        if (mBaseWidth > 0.0F)
        {
          if (mBaseHeight > 0.0F)
          {
            localVPathRenderer.setAlpha(TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "alpha", 4, localVPathRenderer.getAlpha()));
            paramTypedArray = paramTypedArray.getString(0);
            if (paramTypedArray != null)
            {
              mRootName = paramTypedArray;
              mVGTargetsMap.put(paramTypedArray, localVPathRenderer);
            }
          }
          else
          {
            paramXmlPullParser = new StringBuilder();
            paramXmlPullParser.append(paramTypedArray.getPositionDescription());
            paramXmlPullParser.append("<vector> tag requires height > 0");
            throw new XmlPullParserException(paramXmlPullParser.toString());
          }
        }
        else
        {
          paramXmlPullParser = new StringBuilder();
          paramXmlPullParser.append(paramTypedArray.getPositionDescription());
          paramXmlPullParser.append("<vector> tag requires width > 0");
          throw new XmlPullParserException(paramXmlPullParser.toString());
        }
      }
      else
      {
        paramXmlPullParser = new StringBuilder();
        paramXmlPullParser.append(paramTypedArray.getPositionDescription());
        paramXmlPullParser.append("<vector> tag requires viewportHeight > 0");
        throw new XmlPullParserException(paramXmlPullParser.toString());
      }
    }
    else
    {
      paramXmlPullParser = new StringBuilder();
      paramXmlPullParser.append(paramTypedArray.getPositionDescription());
      paramXmlPullParser.append("<vector> tag requires viewportWidth > 0");
      throw new XmlPullParserException(paramXmlPullParser.toString());
    }
  }
  
  public boolean canApplyTheme()
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null) {
      DrawableCompat.canApplyTheme(localDrawable);
    }
    return false;
  }
  
  public void draw(Canvas paramCanvas)
  {
    Object localObject1 = mDelegateDrawable;
    if (localObject1 != null)
    {
      ((Drawable)localObject1).draw(paramCanvas);
      return;
    }
    copyBounds(mTmpBounds);
    if (mTmpBounds.width() > 0)
    {
      if (mTmpBounds.height() <= 0) {
        return;
      }
      Object localObject2 = mColorFilter;
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = mTintFilter;
      }
      paramCanvas.getMatrix(mTmpMatrix);
      mTmpMatrix.getValues(mTmpFloats);
      float f1 = Math.abs(mTmpFloats[0]);
      float f2 = Math.abs(mTmpFloats[4]);
      float f4 = Math.abs(mTmpFloats[1]);
      float f3 = Math.abs(mTmpFloats[3]);
      if ((f4 != 0.0F) || (f3 != 0.0F))
      {
        f1 = 1.0F;
        f2 = 1.0F;
      }
      int i = (int)(mTmpBounds.width() * f1);
      int j = (int)(mTmpBounds.height() * f2);
      i = Math.min(2048, i);
      j = Math.min(2048, j);
      if (i > 0)
      {
        if (j <= 0) {
          return;
        }
        int k = paramCanvas.save();
        localObject2 = mTmpBounds;
        paramCanvas.translate(left, top);
        if (draw())
        {
          paramCanvas.translate(mTmpBounds.width(), 0.0F);
          paramCanvas.scale(-1.0F, 1.0F);
        }
        mTmpBounds.offsetTo(0, 0);
        mVectorState.createCachedBitmapIfNeeded(i, j);
        if (!mAllowCaching)
        {
          mVectorState.updateCachedBitmap(i, j);
        }
        else if (!mVectorState.canReuseCache())
        {
          mVectorState.updateCachedBitmap(i, j);
          mVectorState.updateCacheStates();
        }
        mVectorState.drawCachedBitmapWithRootAlpha(paramCanvas, (ColorFilter)localObject1, mTmpBounds);
        paramCanvas.restoreToCount(k);
      }
    }
  }
  
  public int getAlpha()
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null) {
      return DrawableCompat.getAlpha(localDrawable);
    }
    return mVectorState.mVPathRenderer.getRootAlpha();
  }
  
  public int getChangingConfigurations()
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null) {
      return localDrawable.getChangingConfigurations();
    }
    return super.getChangingConfigurations() | mVectorState.getChangingConfigurations();
  }
  
  public Drawable.ConstantState getConstantState()
  {
    Drawable localDrawable = mDelegateDrawable;
    if ((localDrawable != null) && (Build.VERSION.SDK_INT >= 24)) {
      return new AnimatedVectorDrawableCompat.AnimatedVectorDrawableDelegateState(localDrawable.getConstantState());
    }
    mVectorState.mChangingConfigurations = getChangingConfigurations();
    return mVectorState;
  }
  
  public int getIntrinsicHeight()
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null) {
      return localDrawable.getIntrinsicHeight();
    }
    return (int)mVectorState.mVPathRenderer.mBaseHeight;
  }
  
  public int getIntrinsicWidth()
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null) {
      return localDrawable.getIntrinsicWidth();
    }
    return (int)mVectorState.mVPathRenderer.mBaseWidth;
  }
  
  public int getOpacity()
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null) {
      return localDrawable.getOpacity();
    }
    return -3;
  }
  
  Object getTargetByName(String paramString)
  {
    return mVectorState.mVPathRenderer.mVGTargetsMap.get(paramString);
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet)
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null)
    {
      localDrawable.inflate(paramResources, paramXmlPullParser, paramAttributeSet);
      return;
    }
    inflate(paramResources, paramXmlPullParser, paramAttributeSet, null);
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
  {
    Object localObject = mDelegateDrawable;
    if (localObject != null)
    {
      DrawableCompat.inflate((Drawable)localObject, paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
      return;
    }
    localObject = mVectorState;
    mVPathRenderer = new VPathRenderer();
    TypedArray localTypedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.styleable_VectorDrawableTypeArray);
    updateStateFromTypedArray(localTypedArray, paramXmlPullParser);
    localTypedArray.recycle();
    mChangingConfigurations = getChangingConfigurations();
    mCacheDirty = true;
    inflateInternal(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    mTintFilter = updateTintFilter(mTintFilter, mTint, mTintMode);
  }
  
  public void invalidateSelf()
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null)
    {
      localDrawable.invalidateSelf();
      return;
    }
    super.invalidateSelf();
  }
  
  public boolean isAutoMirrored()
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null) {
      return DrawableCompat.isAutoMirrored(localDrawable);
    }
    return mVectorState.mAutoMirrored;
  }
  
  public boolean isStateful()
  {
    Object localObject = mDelegateDrawable;
    if (localObject != null) {
      return ((Drawable)localObject).isStateful();
    }
    if (!super.isStateful())
    {
      localObject = mVectorState;
      if (localObject != null)
      {
        if (!((VectorDrawableCompatState)localObject).updateCachedBitmap())
        {
          localObject = mVectorState.mTint;
          if ((localObject != null) && (((ColorStateList)localObject).isStateful())) {}
        }
      }
      else {
        return false;
      }
    }
    return true;
  }
  
  public Drawable mutate()
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null)
    {
      localDrawable.mutate();
      return this;
    }
    if ((!mMutated) && (super.mutate() == this))
    {
      mVectorState = new VectorDrawableCompatState(mVectorState);
      mMutated = true;
    }
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null) {
      localDrawable.setBounds(paramRect);
    }
  }
  
  protected boolean onStateChange(int[] paramArrayOfInt)
  {
    Object localObject = mDelegateDrawable;
    if (localObject != null) {
      return ((Drawable)localObject).setState(paramArrayOfInt);
    }
    boolean bool2 = false;
    localObject = mVectorState;
    ColorStateList localColorStateList = mTint;
    boolean bool1 = bool2;
    if (localColorStateList != null)
    {
      PorterDuff.Mode localMode = mTintMode;
      bool1 = bool2;
      if (localMode != null)
      {
        mTintFilter = updateTintFilter(mTintFilter, localColorStateList, localMode);
        invalidateSelf();
        bool1 = true;
      }
    }
    if ((((VectorDrawableCompatState)localObject).updateCachedBitmap()) && (((VectorDrawableCompatState)localObject).draw(paramArrayOfInt)))
    {
      invalidateSelf();
      return true;
    }
    return bool1;
  }
  
  public void scheduleSelf(Runnable paramRunnable, long paramLong)
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null)
    {
      localDrawable.scheduleSelf(paramRunnable, paramLong);
      return;
    }
    super.scheduleSelf(paramRunnable, paramLong);
  }
  
  void setAllowCaching(boolean paramBoolean)
  {
    mAllowCaching = paramBoolean;
  }
  
  public void setAlpha(int paramInt)
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null)
    {
      localDrawable.setAlpha(paramInt);
      return;
    }
    if (mVectorState.mVPathRenderer.getRootAlpha() != paramInt)
    {
      mVectorState.mVPathRenderer.setRootAlpha(paramInt);
      invalidateSelf();
    }
  }
  
  public void setAutoMirrored(boolean paramBoolean)
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null)
    {
      DrawableCompat.setHotspotBounds(localDrawable, paramBoolean);
      return;
    }
    mVectorState.mAutoMirrored = paramBoolean;
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null)
    {
      localDrawable.setColorFilter(paramColorFilter);
      return;
    }
    mColorFilter = paramColorFilter;
    invalidateSelf();
  }
  
  public void setTint(int paramInt)
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null)
    {
      DrawableCompat.setTint(localDrawable, paramInt);
      return;
    }
    setTintList(ColorStateList.valueOf(paramInt));
  }
  
  public void setTintList(ColorStateList paramColorStateList)
  {
    Object localObject = mDelegateDrawable;
    if (localObject != null)
    {
      DrawableCompat.setTintList((Drawable)localObject, paramColorStateList);
      return;
    }
    localObject = mVectorState;
    if (mTint != paramColorStateList)
    {
      mTint = paramColorStateList;
      mTintFilter = updateTintFilter(mTintFilter, paramColorStateList, mTintMode);
      invalidateSelf();
    }
  }
  
  public void setTintMode(PorterDuff.Mode paramMode)
  {
    Object localObject = mDelegateDrawable;
    if (localObject != null)
    {
      DrawableCompat.setTintMode((Drawable)localObject, paramMode);
      return;
    }
    localObject = mVectorState;
    if (mTintMode != paramMode)
    {
      mTintMode = paramMode;
      mTintFilter = updateTintFilter(mTintFilter, mTint, paramMode);
      invalidateSelf();
    }
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2)
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null) {
      return localDrawable.setVisible(paramBoolean1, paramBoolean2);
    }
    return super.setVisible(paramBoolean1, paramBoolean2);
  }
  
  public void unscheduleSelf(Runnable paramRunnable)
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null)
    {
      localDrawable.unscheduleSelf(paramRunnable);
      return;
    }
    super.unscheduleSelf(paramRunnable);
  }
  
  PorterDuffColorFilter updateTintFilter(PorterDuffColorFilter paramPorterDuffColorFilter, ColorStateList paramColorStateList, PorterDuff.Mode paramMode)
  {
    if ((paramColorStateList != null) && (paramMode != null)) {
      return new PorterDuffColorFilter(paramColorStateList.getColorForState(getState(), 0), paramMode);
    }
    return null;
  }
  
  class VClipPath
    extends VectorDrawableCompat.VPath
  {
    public VClipPath() {}
    
    public VClipPath()
    {
      super();
    }
    
    private void updateStateFromTypedArray(TypedArray paramTypedArray)
    {
      String str = paramTypedArray.getString(0);
      if (str != null) {
        mPathName = str;
      }
      paramTypedArray = paramTypedArray.getString(1);
      if (paramTypedArray != null) {
        mNodes = PathParser.createNodesFromPathData(paramTypedArray);
      }
    }
    
    public void inflate(Resources paramResources, AttributeSet paramAttributeSet, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser)
    {
      if (!TypedArrayUtils.hasAttribute(paramXmlPullParser, "pathData")) {
        return;
      }
      paramResources = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.styleable_VectorDrawableClipPath);
      updateStateFromTypedArray(paramResources);
      paramResources.recycle();
    }
    
    public boolean isClipPath()
    {
      return true;
    }
  }
  
  class VFullPath
    extends VectorDrawableCompat.VPath
  {
    Label a;
    float mFillAlpha = 1.0F;
    int mFillColor = 0;
    float mStrokeAlpha = 1.0F;
    Label mStrokeColor;
    Paint.Cap mStrokeLineCap = Paint.Cap.BUTT;
    Paint.Join mStrokeLineJoin = Paint.Join.MITER;
    float mStrokeMiterlimit = 4.0F;
    float mStrokeWidth = 0.0F;
    private int[] mThemeAttrs;
    float mTrimPathEnd = 1.0F;
    float mTrimPathOffset = 0.0F;
    float mTrimPathStart = 0.0F;
    
    public VFullPath() {}
    
    public VFullPath()
    {
      super();
      mThemeAttrs = mThemeAttrs;
      mStrokeColor = mStrokeColor;
      mStrokeWidth = mStrokeWidth;
      mStrokeAlpha = mStrokeAlpha;
      a = a;
      mFillColor = mFillColor;
      mFillAlpha = mFillAlpha;
      mTrimPathStart = mTrimPathStart;
      mTrimPathEnd = mTrimPathEnd;
      mTrimPathOffset = mTrimPathOffset;
      mStrokeLineCap = mStrokeLineCap;
      mStrokeLineJoin = mStrokeLineJoin;
      mStrokeMiterlimit = mStrokeMiterlimit;
    }
    
    private Paint.Cap getStrokeLineCap(int paramInt, Paint.Cap paramCap)
    {
      if (paramInt != 0)
      {
        if (paramInt != 1)
        {
          if (paramInt != 2) {
            return paramCap;
          }
          return Paint.Cap.SQUARE;
        }
        return Paint.Cap.ROUND;
      }
      return Paint.Cap.BUTT;
    }
    
    private Paint.Join getStrokeLineJoin(int paramInt, Paint.Join paramJoin)
    {
      if (paramInt != 0)
      {
        if (paramInt != 1)
        {
          if (paramInt != 2) {
            return paramJoin;
          }
          return Paint.Join.BEVEL;
        }
        return Paint.Join.ROUND;
      }
      return Paint.Join.MITER;
    }
    
    private void updateStateFromTypedArray(TypedArray paramTypedArray, XmlPullParser paramXmlPullParser, Resources.Theme paramTheme)
    {
      mThemeAttrs = null;
      if (!TypedArrayUtils.hasAttribute(paramXmlPullParser, "pathData")) {
        return;
      }
      String str = paramTypedArray.getString(0);
      if (str != null) {
        mPathName = str;
      }
      str = paramTypedArray.getString(2);
      if (str != null) {
        mNodes = PathParser.createNodesFromPathData(str);
      }
      a = TypedArrayUtils.a(paramTypedArray, paramXmlPullParser, paramTheme, "fillColor", 1, 0);
      mFillAlpha = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "fillAlpha", 12, mFillAlpha);
      mStrokeLineCap = getStrokeLineCap(TypedArrayUtils.getString(paramTypedArray, paramXmlPullParser, "strokeLineCap", 8, -1), mStrokeLineCap);
      mStrokeLineJoin = getStrokeLineJoin(TypedArrayUtils.getString(paramTypedArray, paramXmlPullParser, "strokeLineJoin", 9, -1), mStrokeLineJoin);
      mStrokeMiterlimit = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "strokeMiterLimit", 10, mStrokeMiterlimit);
      mStrokeColor = TypedArrayUtils.a(paramTypedArray, paramXmlPullParser, paramTheme, "strokeColor", 3, 0);
      mStrokeAlpha = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "strokeAlpha", 11, mStrokeAlpha);
      mStrokeWidth = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "strokeWidth", 4, mStrokeWidth);
      mTrimPathEnd = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "trimPathEnd", 6, mTrimPathEnd);
      mTrimPathOffset = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "trimPathOffset", 7, mTrimPathOffset);
      mTrimPathStart = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "trimPathStart", 5, mTrimPathStart);
      mFillColor = TypedArrayUtils.getString(paramTypedArray, paramXmlPullParser, "fillType", 13, mFillColor);
    }
    
    public boolean a()
    {
      return (a.b()) || (mStrokeColor.b());
    }
    
    public boolean a(int[] paramArrayOfInt)
    {
      boolean bool = a.a(paramArrayOfInt);
      return mStrokeColor.a(paramArrayOfInt) | bool;
    }
    
    float getFillAlpha()
    {
      return mFillAlpha;
    }
    
    int getFillColor()
    {
      return a.getColor();
    }
    
    float getStrokeAlpha()
    {
      return mStrokeAlpha;
    }
    
    int getStrokeColor()
    {
      return mStrokeColor.getColor();
    }
    
    float getStrokeWidth()
    {
      return mStrokeWidth;
    }
    
    float getTrimPathEnd()
    {
      return mTrimPathEnd;
    }
    
    float getTrimPathOffset()
    {
      return mTrimPathOffset;
    }
    
    float getTrimPathStart()
    {
      return mTrimPathStart;
    }
    
    public void inflate(Resources paramResources, AttributeSet paramAttributeSet, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser)
    {
      paramResources = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.ColorDrawable);
      updateStateFromTypedArray(paramResources, paramXmlPullParser, paramTheme);
      paramResources.recycle();
    }
    
    void setFillAlpha(float paramFloat)
    {
      mFillAlpha = paramFloat;
    }
    
    void setFillColor(int paramInt)
    {
      a.setColor(paramInt);
    }
    
    void setStrokeAlpha(float paramFloat)
    {
      mStrokeAlpha = paramFloat;
    }
    
    void setStrokeColor(int paramInt)
    {
      mStrokeColor.setColor(paramInt);
    }
    
    void setStrokeWidth(float paramFloat)
    {
      mStrokeWidth = paramFloat;
    }
    
    void setTrimPathEnd(float paramFloat)
    {
      mTrimPathEnd = paramFloat;
    }
    
    void setTrimPathOffset(float paramFloat)
    {
      mTrimPathOffset = paramFloat;
    }
    
    void setTrimPathStart(float paramFloat)
    {
      mTrimPathStart = paramFloat;
    }
  }
  
  class VGroup
    extends x
  {
    int mChangingConfigurations;
    final ArrayList<k.d> mChildren = new ArrayList();
    private String mGroupName = null;
    final Matrix mLocalMatrix = new Matrix();
    private float mPivotX = 0.0F;
    private float mPivotY = 0.0F;
    float mRotate = 0.0F;
    private float mScaleX = 1.0F;
    private float mScaleY = 1.0F;
    final Matrix mStackedMatrix = new Matrix();
    private int[] mThemeAttrs;
    private float mTranslateX = 0.0F;
    private float mTranslateY = 0.0F;
    
    public VGroup()
    {
      super();
    }
    
    public VGroup(ArrayMap paramArrayMap) {}
    
    private void updateLocalMatrix()
    {
      mLocalMatrix.reset();
      mLocalMatrix.postTranslate(-mPivotX, -mPivotY);
      mLocalMatrix.postScale(mScaleX, mScaleY);
      mLocalMatrix.postRotate(mRotate, 0.0F, 0.0F);
      mLocalMatrix.postTranslate(mTranslateX + mPivotX, mTranslateY + mPivotY);
    }
    
    private void updateStateFromTypedArray(TypedArray paramTypedArray, XmlPullParser paramXmlPullParser)
    {
      mThemeAttrs = null;
      mRotate = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "rotation", 5, mRotate);
      mPivotX = paramTypedArray.getFloat(1, mPivotX);
      mPivotY = paramTypedArray.getFloat(2, mPivotY);
      mScaleX = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "scaleX", 3, mScaleX);
      mScaleY = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "scaleY", 4, mScaleY);
      mTranslateX = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "translateX", 6, mTranslateX);
      mTranslateY = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "translateY", 7, mTranslateY);
      paramTypedArray = paramTypedArray.getString(0);
      if (paramTypedArray != null) {
        mGroupName = paramTypedArray;
      }
      updateLocalMatrix();
    }
    
    public boolean a()
    {
      int i = 0;
      while (i < mChildren.size())
      {
        if (((x)mChildren.get(i)).a()) {
          return true;
        }
        i += 1;
      }
      return false;
    }
    
    public boolean a(int[] paramArrayOfInt)
    {
      int i = 0;
      boolean bool = false;
      while (i < mChildren.size())
      {
        bool |= ((x)mChildren.get(i)).a(paramArrayOfInt);
        i += 1;
      }
      return bool;
    }
    
    public String getGroupName()
    {
      return mGroupName;
    }
    
    public Matrix getLocalMatrix()
    {
      return mLocalMatrix;
    }
    
    public float getPivotX()
    {
      return mPivotX;
    }
    
    public float getPivotY()
    {
      return mPivotY;
    }
    
    public float getRotation()
    {
      return mRotate;
    }
    
    public float getScaleX()
    {
      return mScaleX;
    }
    
    public float getScaleY()
    {
      return mScaleY;
    }
    
    public float getTranslateX()
    {
      return mTranslateX;
    }
    
    public float getTranslateY()
    {
      return mTranslateY;
    }
    
    public void inflate(Resources paramResources, AttributeSet paramAttributeSet, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser)
    {
      paramResources = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.LayerDrawable);
      updateStateFromTypedArray(paramResources, paramXmlPullParser);
      paramResources.recycle();
    }
    
    public void setPivotX(float paramFloat)
    {
      if (paramFloat != mPivotX)
      {
        mPivotX = paramFloat;
        updateLocalMatrix();
      }
    }
    
    public void setPivotY(float paramFloat)
    {
      if (paramFloat != mPivotY)
      {
        mPivotY = paramFloat;
        updateLocalMatrix();
      }
    }
    
    public void setRotation(float paramFloat)
    {
      if (paramFloat != mRotate)
      {
        mRotate = paramFloat;
        updateLocalMatrix();
      }
    }
    
    public void setScaleX(float paramFloat)
    {
      if (paramFloat != mScaleX)
      {
        mScaleX = paramFloat;
        updateLocalMatrix();
      }
    }
    
    public void setScaleY(float paramFloat)
    {
      if (paramFloat != mScaleY)
      {
        mScaleY = paramFloat;
        updateLocalMatrix();
      }
    }
    
    public void setTranslateX(float paramFloat)
    {
      if (paramFloat != mTranslateX)
      {
        mTranslateX = paramFloat;
        updateLocalMatrix();
      }
    }
    
    public void setTranslateY(float paramFloat)
    {
      if (paramFloat != mTranslateY)
      {
        mTranslateY = paramFloat;
        updateLocalMatrix();
      }
    }
  }
  
  abstract class VPath
    extends x
  {
    int mChangingConfigurations;
    protected PathParser.PathDataNode[] mNodes = null;
    String mPathName;
    
    public VPath()
    {
      super();
    }
    
    public VPath()
    {
      super();
      mPathName = mPathName;
      mChangingConfigurations = mChangingConfigurations;
      mNodes = PathParser.deepCopyNodes(mNodes);
    }
    
    public PathParser.PathDataNode[] getPathData()
    {
      return mNodes;
    }
    
    public String getPathName()
    {
      return mPathName;
    }
    
    public boolean isClipPath()
    {
      return false;
    }
    
    public void setPathData(PathParser.PathDataNode[] paramArrayOfPathDataNode)
    {
      if (!PathParser.canMorph(mNodes, paramArrayOfPathDataNode))
      {
        mNodes = PathParser.deepCopyNodes(paramArrayOfPathDataNode);
        return;
      }
      PathParser.updateNodes(mNodes, paramArrayOfPathDataNode);
    }
    
    public void toPath(Path paramPath)
    {
      paramPath.reset();
      PathParser.PathDataNode[] arrayOfPathDataNode = mNodes;
      if (arrayOfPathDataNode != null) {
        PathParser.PathDataNode.nodesToPath(arrayOfPathDataNode, paramPath);
      }
    }
  }
  
  class VPathRenderer
  {
    private static final Matrix IDENTITY_MATRIX = new Matrix();
    float mBaseHeight = 0.0F;
    float mBaseWidth = 0.0F;
    private int mChangingConfigurations;
    Paint mFillPaint;
    private final Matrix mFinalPathMatrix = new Matrix();
    Boolean mGenerated = null;
    private final Path mPath;
    private PathMeasure mPathMeasure;
    private final Path mRenderPath;
    int mRootAlpha = 255;
    final VectorDrawableCompat.VGroup mRootGroup;
    String mRootName = null;
    Paint mStrokePaint;
    final b<String, Object> mVGTargetsMap = new ArrayMap();
    float mViewportHeight = 0.0F;
    float mViewportWidth = 0.0F;
    
    public VPathRenderer()
    {
      mRootGroup = new VectorDrawableCompat.VGroup();
      mPath = new Path();
      mRenderPath = new Path();
    }
    
    public VPathRenderer()
    {
      mRootGroup = new VectorDrawableCompat.VGroup(mRootGroup, mVGTargetsMap);
      mPath = new Path(mPath);
      mRenderPath = new Path(mRenderPath);
      mBaseWidth = mBaseWidth;
      mBaseHeight = mBaseHeight;
      mViewportWidth = mViewportWidth;
      mViewportHeight = mViewportHeight;
      mChangingConfigurations = mChangingConfigurations;
      mRootAlpha = mRootAlpha;
      mRootName = mRootName;
      String str = mRootName;
      if (str != null) {
        mVGTargetsMap.put(str, this);
      }
      mGenerated = mGenerated;
    }
    
    private static float cross(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
    {
      return paramFloat1 * paramFloat4 - paramFloat2 * paramFloat3;
    }
    
    private void drawGroupTree(VectorDrawableCompat.VGroup paramVGroup, Matrix paramMatrix, Canvas paramCanvas, int paramInt1, int paramInt2, ColorFilter paramColorFilter)
    {
      mStackedMatrix.set(paramMatrix);
      mStackedMatrix.preConcat(mLocalMatrix);
      paramCanvas.save();
      int i = 0;
      while (i < mChildren.size())
      {
        paramMatrix = (x)mChildren.get(i);
        if ((paramMatrix instanceof VectorDrawableCompat.VGroup)) {
          drawGroupTree((VectorDrawableCompat.VGroup)paramMatrix, mStackedMatrix, paramCanvas, paramInt1, paramInt2, paramColorFilter);
        } else if ((paramMatrix instanceof VectorDrawableCompat.VPath)) {
          drawPath(paramVGroup, (VectorDrawableCompat.VPath)paramMatrix, paramCanvas, paramInt1, paramInt2, paramColorFilter);
        }
        i += 1;
      }
      paramCanvas.restore();
    }
    
    private void drawPath(VectorDrawableCompat.VGroup paramVGroup, VectorDrawableCompat.VPath paramVPath, Canvas paramCanvas, int paramInt1, int paramInt2, ColorFilter paramColorFilter)
    {
      float f2 = paramInt1 / mViewportWidth;
      float f3 = paramInt2 / mViewportHeight;
      float f1 = Math.min(f2, f3);
      paramVGroup = mStackedMatrix;
      mFinalPathMatrix.set(paramVGroup);
      mFinalPathMatrix.postScale(f2, f3);
      f2 = getMatrixScale(paramVGroup);
      if (f2 == 0.0F) {
        return;
      }
      paramVPath.toPath(mPath);
      paramVGroup = mPath;
      mRenderPath.reset();
      if (paramVPath.isClipPath())
      {
        mRenderPath.addPath(paramVGroup, mFinalPathMatrix);
        paramCanvas.clipPath(mRenderPath);
        return;
      }
      paramVPath = (VectorDrawableCompat.VFullPath)paramVPath;
      if ((mTrimPathStart != 0.0F) || (mTrimPathEnd != 1.0F))
      {
        float f6 = mTrimPathStart;
        float f4 = mTrimPathOffset;
        float f5 = mTrimPathEnd;
        if (mPathMeasure == null) {
          mPathMeasure = new PathMeasure();
        }
        mPathMeasure.setPath(mPath, false);
        f3 = mPathMeasure.getLength();
        f6 = (f6 + f4) % 1.0F * f3;
        f4 = (f5 + f4) % 1.0F * f3;
        paramVGroup.reset();
        if (f6 > f4)
        {
          mPathMeasure.getSegment(f6, f3, paramVGroup, true);
          mPathMeasure.getSegment(0.0F, f4, paramVGroup, true);
        }
        else
        {
          mPathMeasure.getSegment(f6, f4, paramVGroup, true);
        }
        paramVGroup.rLineTo(0.0F, 0.0F);
      }
      mRenderPath.addPath(paramVGroup, mFinalPathMatrix);
      Object localObject1;
      Object localObject2;
      if (a.a())
      {
        paramVGroup = a;
        if (mFillPaint == null)
        {
          mFillPaint = new Paint(1);
          mFillPaint.setStyle(Paint.Style.FILL);
        }
        localObject1 = mFillPaint;
        if (paramVGroup.setStyle())
        {
          paramVGroup = paramVGroup.e();
          paramVGroup.setLocalMatrix(mFinalPathMatrix);
          ((Paint)localObject1).setShader(paramVGroup);
          ((Paint)localObject1).setAlpha(Math.round(mFillAlpha * 255.0F));
        }
        else
        {
          ((Paint)localObject1).setColor(VectorDrawableCompat.access$getAlpha(paramVGroup.getColor(), mFillAlpha));
        }
        ((Paint)localObject1).setColorFilter(paramColorFilter);
        localObject2 = mRenderPath;
        if (mFillColor == 0) {
          paramVGroup = Path.FillType.WINDING;
        } else {
          paramVGroup = Path.FillType.EVEN_ODD;
        }
        ((Path)localObject2).setFillType(paramVGroup);
        paramCanvas.drawPath(mRenderPath, (Paint)localObject1);
      }
      if (mStrokeColor.a())
      {
        localObject1 = mStrokeColor;
        if (mStrokePaint == null)
        {
          mStrokePaint = new Paint(1);
          mStrokePaint.setStyle(Paint.Style.STROKE);
        }
        paramVGroup = mStrokePaint;
        localObject2 = mStrokeLineJoin;
        if (localObject2 != null) {
          paramVGroup.setStrokeJoin((Paint.Join)localObject2);
        }
        localObject2 = mStrokeLineCap;
        if (localObject2 != null) {
          paramVGroup.setStrokeCap((Paint.Cap)localObject2);
        }
        paramVGroup.setStrokeMiter(mStrokeMiterlimit);
        if (((Label)localObject1).setStyle())
        {
          localObject1 = ((Label)localObject1).e();
          ((Shader)localObject1).setLocalMatrix(mFinalPathMatrix);
          paramVGroup.setShader((Shader)localObject1);
          paramVGroup.setAlpha(Math.round(mStrokeAlpha * 255.0F));
        }
        else
        {
          paramVGroup.setColor(VectorDrawableCompat.access$getAlpha(((Label)localObject1).getColor(), mStrokeAlpha));
        }
        paramVGroup.setColorFilter(paramColorFilter);
        paramVGroup.setStrokeWidth(mStrokeWidth * (f1 * f2));
        paramCanvas.drawPath(mRenderPath, paramVGroup);
      }
    }
    
    private float getMatrixScale(Matrix paramMatrix)
    {
      float[] arrayOfFloat = new float[4];
      arrayOfFloat[0] = 0.0F;
      arrayOfFloat[1] = 1.0F;
      arrayOfFloat[2] = 1.0F;
      arrayOfFloat[3] = 0.0F;
      paramMatrix.mapVectors(arrayOfFloat);
      float f2 = (float)Math.hypot(arrayOfFloat[0], arrayOfFloat[1]);
      float f3 = (float)Math.hypot(arrayOfFloat[2], arrayOfFloat[3]);
      float f1 = cross(arrayOfFloat[0], arrayOfFloat[1], arrayOfFloat[2], arrayOfFloat[3]);
      f2 = Math.max(f2, f3);
      if (f2 > 0.0F) {
        return Math.abs(f1) / f2;
      }
      return 0.0F;
    }
    
    public void draw(Canvas paramCanvas, int paramInt1, int paramInt2, ColorFilter paramColorFilter)
    {
      drawGroupTree(mRootGroup, IDENTITY_MATRIX, paramCanvas, paramInt1, paramInt2, paramColorFilter);
    }
    
    public boolean draw()
    {
      if (mGenerated == null) {
        mGenerated = Boolean.valueOf(mRootGroup.a());
      }
      return mGenerated.booleanValue();
    }
    
    public boolean draw(int[] paramArrayOfInt)
    {
      return mRootGroup.a(paramArrayOfInt);
    }
    
    public float getAlpha()
    {
      return getRootAlpha() / 255.0F;
    }
    
    public int getRootAlpha()
    {
      return mRootAlpha;
    }
    
    public void setAlpha(float paramFloat)
    {
      setRootAlpha((int)(paramFloat * 255.0F));
    }
    
    public void setRootAlpha(int paramInt)
    {
      mRootAlpha = paramInt;
    }
  }
  
  class VectorDrawableCompatState
    extends Drawable.ConstantState
  {
    boolean mAutoMirrored;
    boolean mCacheDirty;
    boolean mCachedAutoMirrored;
    Bitmap mCachedBitmap;
    int mCachedRootAlpha;
    ColorStateList mCachedTint;
    PorterDuff.Mode mCachedTintMode;
    int mChangingConfigurations;
    Paint mTempPaint;
    ColorStateList mTint = null;
    PorterDuff.Mode mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
    VectorDrawableCompat.VPathRenderer mVPathRenderer;
    
    public VectorDrawableCompatState()
    {
      mVPathRenderer = new VectorDrawableCompat.VPathRenderer();
    }
    
    public VectorDrawableCompatState()
    {
      if (this$1 != null)
      {
        mChangingConfigurations = mChangingConfigurations;
        mVPathRenderer = new VectorDrawableCompat.VPathRenderer(mVPathRenderer);
        Paint localPaint = mVPathRenderer.mFillPaint;
        if (localPaint != null) {
          mVPathRenderer.mFillPaint = new Paint(localPaint);
        }
        localPaint = mVPathRenderer.mStrokePaint;
        if (localPaint != null) {
          mVPathRenderer.mStrokePaint = new Paint(localPaint);
        }
        mTint = mTint;
        mTintMode = mTintMode;
        mAutoMirrored = mAutoMirrored;
      }
    }
    
    public boolean canReuseBitmap(int paramInt1, int paramInt2)
    {
      return (paramInt1 == mCachedBitmap.getWidth()) && (paramInt2 == mCachedBitmap.getHeight());
    }
    
    public boolean canReuseCache()
    {
      return (!mCacheDirty) && (mCachedTint == mTint) && (mCachedTintMode == mTintMode) && (mCachedAutoMirrored == mAutoMirrored) && (mCachedRootAlpha == mVPathRenderer.getRootAlpha());
    }
    
    public void createCachedBitmapIfNeeded(int paramInt1, int paramInt2)
    {
      if ((mCachedBitmap == null) || (!canReuseBitmap(paramInt1, paramInt2)))
      {
        mCachedBitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
        mCacheDirty = true;
      }
    }
    
    public boolean draw(int[] paramArrayOfInt)
    {
      boolean bool = mVPathRenderer.draw(paramArrayOfInt);
      mCacheDirty |= bool;
      return bool;
    }
    
    public void drawCachedBitmapWithRootAlpha(Canvas paramCanvas, ColorFilter paramColorFilter, Rect paramRect)
    {
      paramColorFilter = getPaint(paramColorFilter);
      paramCanvas.drawBitmap(mCachedBitmap, null, paramRect, paramColorFilter);
    }
    
    public int getChangingConfigurations()
    {
      return mChangingConfigurations;
    }
    
    public Paint getPaint(ColorFilter paramColorFilter)
    {
      if ((!hasTranslucentRoot()) && (paramColorFilter == null)) {
        return null;
      }
      if (mTempPaint == null)
      {
        mTempPaint = new Paint();
        mTempPaint.setFilterBitmap(true);
      }
      mTempPaint.setAlpha(mVPathRenderer.getRootAlpha());
      mTempPaint.setColorFilter(paramColorFilter);
      return mTempPaint;
    }
    
    public boolean hasTranslucentRoot()
    {
      return mVPathRenderer.getRootAlpha() < 255;
    }
    
    public Drawable newDrawable()
    {
      return new VectorDrawableCompat(this);
    }
    
    public Drawable newDrawable(Resources paramResources)
    {
      return new VectorDrawableCompat(this);
    }
    
    public void updateCacheStates()
    {
      mCachedTint = mTint;
      mCachedTintMode = mTintMode;
      mCachedRootAlpha = mVPathRenderer.getRootAlpha();
      mCachedAutoMirrored = mAutoMirrored;
      mCacheDirty = false;
    }
    
    public void updateCachedBitmap(int paramInt1, int paramInt2)
    {
      mCachedBitmap.eraseColor(0);
      Canvas localCanvas = new Canvas(mCachedBitmap);
      mVPathRenderer.draw(localCanvas, paramInt1, paramInt2, null);
    }
    
    public boolean updateCachedBitmap()
    {
      return mVPathRenderer.draw();
    }
  }
}

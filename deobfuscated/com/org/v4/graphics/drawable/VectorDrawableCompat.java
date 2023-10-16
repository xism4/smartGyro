package com.org.v4.graphics.drawable;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import com.org.android.ui.asm.TypedArrayUtils;
import com.org.shortcuts.drawable.AnimatedVectorDrawableCompat;
import com.org.v4.util.R.styleable;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class VectorDrawableCompat
  extends LayerDrawable
{
  private ClassWriter a;
  private boolean mMutated;
  private RippleDrawable w;
  private int x = -1;
  private int y = -1;
  
  public VectorDrawableCompat()
  {
    this(null, null);
  }
  
  VectorDrawableCompat(ClassWriter paramClassWriter, android.content.res.Resources paramResources)
  {
    super(null);
    mutate(new ClassWriter(paramClassWriter, this, paramResources));
    onStateChange(getState());
    jumpToCurrentState();
  }
  
  private int a(Context paramContext, android.content.res.Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
  {
    Object localObject = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.AnimatedStateListDrawableItem);
    int i = ((TypedArray)localObject).getResourceId(R.styleable.AnimatedStateListDrawableItem_android_id, 0);
    int j = ((TypedArray)localObject).getResourceId(R.styleable.AnimatedStateListDrawableItem_android_drawable, -1);
    Drawable localDrawable;
    if (j > 0) {
      localDrawable = com.org.v4.text.view.Resources.getDrawable(paramContext, j);
    } else {
      localDrawable = null;
    }
    ((TypedArray)localObject).recycle();
    localObject = a(paramAttributeSet);
    paramContext = localDrawable;
    if (localDrawable == null)
    {
      do
      {
        j = paramXmlPullParser.next();
      } while (j == 4);
      if (j == 2)
      {
        if (paramXmlPullParser.getName().equals("vector")) {
          paramContext = com.org.shortcuts.drawable.VectorDrawableCompat.createFromXmlInner(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
        } else if (Build.VERSION.SDK_INT >= 21) {
          paramContext = Drawable.createFromXmlInner(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
        } else {
          paramContext = Drawable.createFromXmlInner(paramResources, paramXmlPullParser, paramAttributeSet);
        }
      }
      else
      {
        paramContext = new StringBuilder();
        paramContext.append(paramXmlPullParser.getPositionDescription());
        paramContext.append(": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
        throw new XmlPullParserException(paramContext.toString());
      }
    }
    if (paramContext != null) {
      return a.a((int[])localObject, paramContext, i);
    }
    paramContext = new StringBuilder();
    paramContext.append(paramXmlPullParser.getPositionDescription());
    paramContext.append(": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
    paramContext = new XmlPullParserException(paramContext.toString());
    throw paramContext;
  }
  
  private int create(Context paramContext, android.content.res.Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
  {
    Object localObject = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.AnimatedStateListDrawableTransition);
    int i = ((TypedArray)localObject).getResourceId(R.styleable.AnimatedStateListDrawableTransition_android_fromId, -1);
    int j = ((TypedArray)localObject).getResourceId(R.styleable.AnimatedStateListDrawableTransition_android_toId, -1);
    int k = ((TypedArray)localObject).getResourceId(R.styleable.AnimatedStateListDrawableTransition_android_drawable, -1);
    Drawable localDrawable;
    if (k > 0) {
      localDrawable = com.org.v4.text.view.Resources.getDrawable(paramContext, k);
    } else {
      localDrawable = null;
    }
    boolean bool = ((TypedArray)localObject).getBoolean(R.styleable.AnimatedStateListDrawableTransition_android_reversible, false);
    ((TypedArray)localObject).recycle();
    localObject = localDrawable;
    if (localDrawable == null)
    {
      do
      {
        k = paramXmlPullParser.next();
      } while (k == 4);
      if (k == 2)
      {
        if (paramXmlPullParser.getName().equals("animated-vector")) {
          localObject = AnimatedVectorDrawableCompat.createFromXmlInner(paramContext, paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
        } else if (Build.VERSION.SDK_INT >= 21) {
          localObject = Drawable.createFromXmlInner(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
        } else {
          localObject = Drawable.createFromXmlInner(paramResources, paramXmlPullParser, paramAttributeSet);
        }
      }
      else
      {
        paramContext = new StringBuilder();
        paramContext.append(paramXmlPullParser.getPositionDescription());
        paramContext.append(": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
        throw new XmlPullParserException(paramContext.toString());
      }
    }
    if (localObject != null)
    {
      if ((i != -1) && (j != -1)) {
        return a.get(i, j, (Drawable)localObject, bool);
      }
      paramContext = new StringBuilder();
      paramContext.append(paramXmlPullParser.getPositionDescription());
      paramContext.append(": <transition> tag requires 'fromId' & 'toId' attributes");
      throw new XmlPullParserException(paramContext.toString());
    }
    paramContext = new StringBuilder();
    paramContext.append(paramXmlPullParser.getPositionDescription());
    paramContext.append(": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
    paramContext = new XmlPullParserException(paramContext.toString());
    throw paramContext;
  }
  
  public static VectorDrawableCompat createFromXmlInner(Context paramContext, android.content.res.Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
  {
    Object localObject = paramXmlPullParser.getName();
    if (((String)localObject).equals("animated-selector"))
    {
      localObject = new VectorDrawableCompat();
      ((VectorDrawableCompat)localObject).inflate(paramContext, paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
      return localObject;
    }
    paramContext = new StringBuilder();
    paramContext.append(paramXmlPullParser.getPositionDescription());
    paramContext.append(": invalid animated-selector tag ");
    paramContext.append((String)localObject);
    throw new XmlPullParserException(paramContext.toString());
  }
  
  private void init(TypedArray paramTypedArray)
  {
    ClassWriter localClassWriter = a;
    if (Build.VERSION.SDK_INT >= 21) {
      type |= paramTypedArray.getChangingConfigurations();
    }
    localClassWriter.read(paramTypedArray.getBoolean(R.styleable.AnimatedStateListDrawableCompat_android_variablePadding, b));
    localClassWriter.addChild(paramTypedArray.getBoolean(R.styleable.AnimatedStateListDrawableCompat_android_constantSize, a));
    localClassWriter.set(paramTypedArray.getInt(R.styleable.AnimatedStateListDrawableCompat_android_enterFadeDuration, g));
    localClassWriter.setColor(paramTypedArray.getInt(R.styleable.AnimatedStateListDrawableCompat_android_exitFadeDuration, mAlpha));
    setDither(paramTypedArray.getBoolean(R.styleable.AnimatedStateListDrawableCompat_android_dither, i));
  }
  
  private void process(Context paramContext, android.content.res.Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
  {
    int i = paramXmlPullParser.getDepth() + 1;
    for (;;)
    {
      int j = paramXmlPullParser.next();
      if (j == 1) {
        break;
      }
      int k = paramXmlPullParser.getDepth();
      if ((k < i) && (j == 3)) {
        break;
      }
      if ((j == 2) && (k <= i)) {
        if (paramXmlPullParser.getName().equals("item")) {
          a(paramContext, paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
        } else if (paramXmlPullParser.getName().equals("transition")) {
          create(paramContext, paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
        }
      }
    }
  }
  
  private boolean run(int paramInt)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a14 = a13\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  private void updateTintFilter()
  {
    onStateChange(getState());
  }
  
  ClassWriter draw()
  {
    return new ClassWriter(a, this, null);
  }
  
  public void inflate(Context paramContext, android.content.res.Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
  {
    TypedArray localTypedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, R.styleable.AnimatedStateListDrawableCompat);
    setVisible(localTypedArray.getBoolean(R.styleable.AnimatedStateListDrawableCompat_android_visible, true), true);
    init(localTypedArray);
    inflate(paramResources);
    localTypedArray.recycle();
    process(paramContext, paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    updateTintFilter();
  }
  
  public boolean isStateful()
  {
    return true;
  }
  
  public void jumpToCurrentState()
  {
    super.jumpToCurrentState();
    RippleDrawable localRippleDrawable = w;
    if (localRippleDrawable != null)
    {
      localRippleDrawable.stopAnimation();
      w = null;
      draw(y);
      y = -1;
      x = -1;
    }
  }
  
  public Drawable mutate()
  {
    if (!mMutated)
    {
      super.mutate();
      a.init();
      mMutated = true;
    }
    return this;
  }
  
  protected void mutate(DrawableContainer.DrawableContainerState paramDrawableContainerState)
  {
    super.mutate(paramDrawableContainerState);
    if ((paramDrawableContainerState instanceof ClassWriter)) {
      a = ((ClassWriter)paramDrawableContainerState);
    }
  }
  
  protected boolean onStateChange(int[] paramArrayOfInt)
  {
    int i = a.get(paramArrayOfInt);
    boolean bool1;
    if ((i != getWidth()) && ((run(i)) || (draw(i)))) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    Drawable localDrawable = getCurrent();
    boolean bool2 = bool1;
    if (localDrawable != null) {
      bool2 = bool1 | localDrawable.setState(paramArrayOfInt);
    }
    return bool2;
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2)
  {
    boolean bool = super.setVisible(paramBoolean1, paramBoolean2);
    if ((w != null) && ((bool) || (paramBoolean2)))
    {
      if (paramBoolean1)
      {
        w.start();
        return bool;
      }
      jumpToCurrentState();
    }
    return bool;
  }
}

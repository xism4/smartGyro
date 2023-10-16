package com.org.shortcuts.drawable;

import a.a.c.f.b;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Build.VERSION;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import com.org.android.ui.asm.TypedArrayUtils;
import com.org.android.util.ArrayMap;
import com.org.android.util.SimpleArrayMap;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

public class AnimatedVectorDrawableCompat
  extends VectorDrawableCommon
  implements Animatable
{
  private AnimatedVectorDrawableCompatState mAnimatedVectorState;
  private ArgbEvaluator mArgbEvaluator = null;
  final Drawable.Callback mCallback = new MaterialProgressDrawable.3(this);
  private android.content.Context mContext;
  ArrayList<Object> mData = null;
  private Animator.AnimatorListener mListener = null;
  
  AnimatedVectorDrawableCompat()
  {
    this(null, null, null);
  }
  
  private AnimatedVectorDrawableCompat(android.content.Context paramContext)
  {
    this(paramContext, null, null);
  }
  
  private AnimatedVectorDrawableCompat(android.content.Context paramContext, AnimatedVectorDrawableCompatState paramAnimatedVectorDrawableCompatState, Resources paramResources)
  {
    mContext = paramContext;
    if (paramAnimatedVectorDrawableCompatState != null)
    {
      mAnimatedVectorState = paramAnimatedVectorDrawableCompatState;
      return;
    }
    mAnimatedVectorState = new AnimatedVectorDrawableCompatState(paramContext, paramAnimatedVectorDrawableCompatState, mCallback, paramResources);
  }
  
  public static AnimatedVectorDrawableCompat createFromXmlInner(android.content.Context paramContext, Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
  {
    paramContext = new AnimatedVectorDrawableCompat(paramContext);
    paramContext.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    return paramContext;
  }
  
  private void setupAnimatorsForTarget(String paramString, Animator paramAnimator)
  {
    paramAnimator.setTarget(mAnimatedVectorState.mVectorDrawable.getTargetByName(paramString));
    if (Build.VERSION.SDK_INT < 21) {
      setupColorAnimator(paramAnimator);
    }
    AnimatedVectorDrawableCompatState localAnimatedVectorDrawableCompatState = mAnimatedVectorState;
    if (mAnimators == null)
    {
      mAnimators = new ArrayList();
      mAnimatedVectorState.mTargetNameMap = new ArrayMap();
    }
    mAnimatedVectorState.mAnimators.add(paramAnimator);
    mAnimatedVectorState.mTargetNameMap.put(paramAnimator, paramString);
  }
  
  private void setupColorAnimator(Animator paramAnimator)
  {
    Object localObject;
    if ((paramAnimator instanceof AnimatorSet))
    {
      localObject = ((AnimatorSet)paramAnimator).getChildAnimations();
      if (localObject != null)
      {
        int i = 0;
        while (i < ((List)localObject).size())
        {
          setupColorAnimator((Animator)((List)localObject).get(i));
          i += 1;
        }
      }
    }
    if ((paramAnimator instanceof ObjectAnimator))
    {
      paramAnimator = (ObjectAnimator)paramAnimator;
      localObject = paramAnimator.getPropertyName();
      if (("fillColor".equals(localObject)) || ("strokeColor".equals(localObject)))
      {
        if (mArgbEvaluator == null) {
          mArgbEvaluator = new ArgbEvaluator();
        }
        paramAnimator.setEvaluator(mArgbEvaluator);
      }
    }
  }
  
  public void applyTheme(Resources.Theme paramTheme)
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null) {
      DrawableCompat.applyTheme(localDrawable, paramTheme);
    }
  }
  
  public boolean canApplyTheme()
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null) {
      return DrawableCompat.canApplyTheme(localDrawable);
    }
    return false;
  }
  
  public void draw(Canvas paramCanvas)
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null)
    {
      localDrawable.draw(paramCanvas);
      return;
    }
    mAnimatedVectorState.mVectorDrawable.draw(paramCanvas);
    if (mAnimatedVectorState.animator.isStarted()) {
      invalidateSelf();
    }
  }
  
  public int getAlpha()
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null) {
      return DrawableCompat.getAlpha(localDrawable);
    }
    return mAnimatedVectorState.mVectorDrawable.getAlpha();
  }
  
  public int getChangingConfigurations()
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null) {
      return localDrawable.getChangingConfigurations();
    }
    return super.getChangingConfigurations() | mAnimatedVectorState.mChangingConfigurations;
  }
  
  public Drawable.ConstantState getConstantState()
  {
    Drawable localDrawable = mDelegateDrawable;
    if ((localDrawable != null) && (Build.VERSION.SDK_INT >= 24)) {
      return new VectorDrawableCompat.VectorDrawableDelegateState(localDrawable.getConstantState());
    }
    return null;
  }
  
  public int getIntrinsicHeight()
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null) {
      return localDrawable.getIntrinsicHeight();
    }
    return mAnimatedVectorState.mVectorDrawable.getIntrinsicHeight();
  }
  
  public int getIntrinsicWidth()
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null) {
      return localDrawable.getIntrinsicWidth();
    }
    return mAnimatedVectorState.mVectorDrawable.getIntrinsicWidth();
  }
  
  public int getOpacity()
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null) {
      return localDrawable.getOpacity();
    }
    return mAnimatedVectorState.mVectorDrawable.getOpacity();
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet)
  {
    inflate(paramResources, paramXmlPullParser, paramAttributeSet, null);
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
  {
    Object localObject1 = mDelegateDrawable;
    if (localObject1 != null)
    {
      DrawableCompat.inflate((Drawable)localObject1, paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
      return;
    }
    int i = paramXmlPullParser.getEventType();
    int j = paramXmlPullParser.getDepth();
    while ((i != 1) && ((paramXmlPullParser.getDepth() >= j + 1) || (i != 3)))
    {
      if (i == 2)
      {
        localObject1 = paramXmlPullParser.getName();
        Object localObject2;
        Object localObject3;
        if ("animated-vector".equals(localObject1))
        {
          localObject2 = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.styleable_AnimatedVectorDrawable);
          localObject1 = localObject2;
          i = ((TypedArray)localObject2).getResourceId(0, 0);
          localObject2 = localObject1;
          if (i != 0)
          {
            localObject2 = VectorDrawableCompat.create(paramResources, i, paramTheme);
            ((VectorDrawableCompat)localObject2).setAllowCaching(false);
            ((Drawable)localObject2).setCallback(mCallback);
            localObject3 = mAnimatedVectorState.mVectorDrawable;
            if (localObject3 != null) {
              ((Drawable)localObject3).setCallback(null);
            }
            mAnimatedVectorState.mVectorDrawable = ((VectorDrawableCompat)localObject2);
            localObject2 = localObject1;
          }
        }
        for (;;)
        {
          ((TypedArray)localObject2).recycle();
          break label286;
          if (!"target".equals(localObject1)) {
            break label286;
          }
          localObject3 = paramResources.obtainAttributes(paramAttributeSet, AndroidResources.styleable_AnimatedVectorDrawableTarget);
          localObject1 = localObject3;
          String str = ((TypedArray)localObject3).getString(0);
          i = ((TypedArray)localObject3).getResourceId(1, 0);
          localObject2 = localObject1;
          if (i != 0)
          {
            localObject2 = mContext;
            if (localObject2 == null) {
              break;
            }
            setupAnimatorsForTarget(str, Context.getDrawable((android.content.Context)localObject2, i));
            localObject2 = localObject1;
          }
        }
        ((TypedArray)localObject3).recycle();
        throw new IllegalStateException("Context can't be null when inflating animators");
      }
      label286:
      i = paramXmlPullParser.next();
    }
    mAnimatedVectorState.start();
  }
  
  public boolean isAutoMirrored()
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null) {
      return DrawableCompat.isAutoMirrored(localDrawable);
    }
    return mAnimatedVectorState.mVectorDrawable.isAutoMirrored();
  }
  
  public boolean isRunning()
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null) {
      return ((AnimatedVectorDrawable)localDrawable).isRunning();
    }
    return mAnimatedVectorState.animator.isRunning();
  }
  
  public boolean isStateful()
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null) {
      return localDrawable.isStateful();
    }
    return mAnimatedVectorState.mVectorDrawable.isStateful();
  }
  
  public Drawable mutate()
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null) {
      localDrawable.mutate();
    }
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null)
    {
      localDrawable.setBounds(paramRect);
      return;
    }
    mAnimatedVectorState.mVectorDrawable.setBounds(paramRect);
  }
  
  protected boolean onLevelChange(int paramInt)
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null) {
      return localDrawable.setLevel(paramInt);
    }
    return mAnimatedVectorState.mVectorDrawable.setLevel(paramInt);
  }
  
  protected boolean onStateChange(int[] paramArrayOfInt)
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null) {
      return localDrawable.setState(paramArrayOfInt);
    }
    return mAnimatedVectorState.mVectorDrawable.setState(paramArrayOfInt);
  }
  
  public void setAlpha(int paramInt)
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null)
    {
      localDrawable.setAlpha(paramInt);
      return;
    }
    mAnimatedVectorState.mVectorDrawable.setAlpha(paramInt);
  }
  
  public void setAutoMirrored(boolean paramBoolean)
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null)
    {
      DrawableCompat.setHotspotBounds(localDrawable, paramBoolean);
      return;
    }
    mAnimatedVectorState.mVectorDrawable.setAutoMirrored(paramBoolean);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null)
    {
      localDrawable.setColorFilter(paramColorFilter);
      return;
    }
    mAnimatedVectorState.mVectorDrawable.setColorFilter(paramColorFilter);
  }
  
  public void setTint(int paramInt)
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null)
    {
      DrawableCompat.setTint(localDrawable, paramInt);
      return;
    }
    mAnimatedVectorState.mVectorDrawable.setTint(paramInt);
  }
  
  public void setTintList(ColorStateList paramColorStateList)
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null)
    {
      DrawableCompat.setTintList(localDrawable, paramColorStateList);
      return;
    }
    mAnimatedVectorState.mVectorDrawable.setTintList(paramColorStateList);
  }
  
  public void setTintMode(PorterDuff.Mode paramMode)
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null)
    {
      DrawableCompat.setTintMode(localDrawable, paramMode);
      return;
    }
    mAnimatedVectorState.mVectorDrawable.setTintMode(paramMode);
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2)
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null) {
      return localDrawable.setVisible(paramBoolean1, paramBoolean2);
    }
    mAnimatedVectorState.mVectorDrawable.setVisible(paramBoolean1, paramBoolean2);
    return super.setVisible(paramBoolean1, paramBoolean2);
  }
  
  public void start()
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null)
    {
      ((AnimatedVectorDrawable)localDrawable).start();
      return;
    }
    if (mAnimatedVectorState.animator.isStarted()) {
      return;
    }
    mAnimatedVectorState.animator.start();
    invalidateSelf();
  }
  
  public void stop()
  {
    Drawable localDrawable = mDelegateDrawable;
    if (localDrawable != null)
    {
      ((AnimatedVectorDrawable)localDrawable).stop();
      return;
    }
    mAnimatedVectorState.animator.end();
  }
  
  class AnimatedVectorDrawableCompatState
    extends Drawable.ConstantState
  {
    AnimatorSet animator;
    ArrayList<Animator> mAnimators;
    int mChangingConfigurations;
    b<Animator, String> mTargetNameMap;
    VectorDrawableCompat mVectorDrawable;
    
    public AnimatedVectorDrawableCompatState(AnimatedVectorDrawableCompatState paramAnimatedVectorDrawableCompatState, Drawable.Callback paramCallback, Resources paramResources)
    {
      if (paramAnimatedVectorDrawableCompatState != null)
      {
        mChangingConfigurations = mChangingConfigurations;
        this$1 = mVectorDrawable;
        int i = 0;
        if (this$1 != null)
        {
          this$1 = this$1.getConstantState();
          if (paramResources != null) {
            this$1 = this$1.newDrawable(paramResources);
          } else {
            this$1 = this$1.newDrawable();
          }
          mVectorDrawable = ((VectorDrawableCompat)this$1);
          this$1 = mVectorDrawable;
          this$1.mutate();
          mVectorDrawable = ((VectorDrawableCompat)this$1);
          mVectorDrawable.setCallback(paramCallback);
          mVectorDrawable.setBounds(mVectorDrawable.getBounds());
          mVectorDrawable.setAllowCaching(false);
        }
        this$1 = mAnimators;
        if (this$1 != null)
        {
          int j = this$1.size();
          mAnimators = new ArrayList(j);
          mTargetNameMap = new ArrayMap(j);
          while (i < j)
          {
            paramCallback = (Animator)mAnimators.get(i);
            this$1 = paramCallback.clone();
            paramCallback = (String)mTargetNameMap.get(paramCallback);
            this$1.setTarget(mVectorDrawable.getTargetByName(paramCallback));
            mAnimators.add(this$1);
            mTargetNameMap.put(this$1, paramCallback);
            i += 1;
          }
          start();
        }
      }
    }
    
    public int getChangingConfigurations()
    {
      return mChangingConfigurations;
    }
    
    public Drawable newDrawable()
    {
      throw new IllegalStateException("No constant state support for SDK < 24.");
    }
    
    public Drawable newDrawable(Resources paramResources)
    {
      throw new IllegalStateException("No constant state support for SDK < 24.");
    }
    
    public void start()
    {
      if (animator == null) {
        animator = new AnimatorSet();
      }
      animator.playTogether(mAnimators);
    }
  }
}

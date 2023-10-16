package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;

class DrawableWrapperDonut
  extends android.graphics.drawable.Drawable
  implements Drawable.Callback, Drawable, DrawableWrapper
{
  static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
  private boolean mColorFilterSet;
  private int mCurrentColor;
  private PorterDuff.Mode mCurrentMode;
  android.graphics.drawable.Drawable mDrawable;
  private boolean mMutated;
  DrawableWrapperState mState;
  
  DrawableWrapperDonut(android.graphics.drawable.Drawable paramDrawable)
  {
    mState = mutateConstantState();
    setWrappedDrawable(paramDrawable);
  }
  
  DrawableWrapperDonut(DrawableWrapperState paramDrawableWrapperState, Resources paramResources)
  {
    mState = paramDrawableWrapperState;
    updateLocalState(paramResources);
  }
  
  private void updateLocalState(Resources paramResources)
  {
    Object localObject = mState;
    if (localObject != null)
    {
      localObject = mDrawableState;
      if (localObject != null) {
        setWrappedDrawable(((Drawable.ConstantState)localObject).newDrawable(paramResources));
      }
    }
  }
  
  private boolean updateTint(int[] paramArrayOfInt)
  {
    if (!isCompatTintEnabled()) {
      return false;
    }
    Object localObject = mState;
    ColorStateList localColorStateList = mTint;
    localObject = mTintMode;
    if ((localColorStateList != null) && (localObject != null))
    {
      int i = localColorStateList.getColorForState(paramArrayOfInt, localColorStateList.getDefaultColor());
      if ((!mColorFilterSet) || (i != mCurrentColor) || (localObject != mCurrentMode))
      {
        setColorFilter(i, (PorterDuff.Mode)localObject);
        mCurrentColor = i;
        mCurrentMode = ((PorterDuff.Mode)localObject);
        mColorFilterSet = true;
        return true;
      }
    }
    else
    {
      mColorFilterSet = false;
      clearColorFilter();
    }
    return false;
  }
  
  public void draw(Canvas paramCanvas)
  {
    mDrawable.draw(paramCanvas);
  }
  
  public int getChangingConfigurations()
  {
    int j = super.getChangingConfigurations();
    DrawableWrapperState localDrawableWrapperState = mState;
    int i;
    if (localDrawableWrapperState != null) {
      i = localDrawableWrapperState.getChangingConfigurations();
    } else {
      i = 0;
    }
    return j | i | mDrawable.getChangingConfigurations();
  }
  
  public Drawable.ConstantState getConstantState()
  {
    DrawableWrapperState localDrawableWrapperState = mState;
    if ((localDrawableWrapperState != null) && (localDrawableWrapperState.canConstantState()))
    {
      mState.mChangingConfigurations = getChangingConfigurations();
      return mState;
    }
    return null;
  }
  
  public android.graphics.drawable.Drawable getCurrent()
  {
    return mDrawable.getCurrent();
  }
  
  public int getIntrinsicHeight()
  {
    return mDrawable.getIntrinsicHeight();
  }
  
  public int getIntrinsicWidth()
  {
    return mDrawable.getIntrinsicWidth();
  }
  
  public int getMinimumHeight()
  {
    return mDrawable.getMinimumHeight();
  }
  
  public int getMinimumWidth()
  {
    return mDrawable.getMinimumWidth();
  }
  
  public int getOpacity()
  {
    return mDrawable.getOpacity();
  }
  
  public boolean getPadding(Rect paramRect)
  {
    return mDrawable.getPadding(paramRect);
  }
  
  public int[] getState()
  {
    return mDrawable.getState();
  }
  
  public Region getTransparentRegion()
  {
    return mDrawable.getTransparentRegion();
  }
  
  public final android.graphics.drawable.Drawable getWrappedDrawable()
  {
    return mDrawable;
  }
  
  public void invalidateDrawable(android.graphics.drawable.Drawable paramDrawable)
  {
    invalidateSelf();
  }
  
  public boolean isAutoMirrored()
  {
    return mDrawable.isAutoMirrored();
  }
  
  protected boolean isCompatTintEnabled()
  {
    return true;
  }
  
  public boolean isStateful()
  {
    if (isCompatTintEnabled())
    {
      localObject = mState;
      if (localObject != null)
      {
        localObject = mTint;
        break label26;
      }
    }
    Object localObject = null;
    label26:
    return ((localObject != null) && (((ColorStateList)localObject).isStateful())) || (mDrawable.isStateful());
  }
  
  public void jumpToCurrentState()
  {
    mDrawable.jumpToCurrentState();
  }
  
  public android.graphics.drawable.Drawable mutate()
  {
    if ((!mMutated) && (super.mutate() == this))
    {
      mState = mutateConstantState();
      Object localObject = mDrawable;
      if (localObject != null) {
        ((android.graphics.drawable.Drawable)localObject).mutate();
      }
      DrawableWrapperState localDrawableWrapperState = mState;
      if (localDrawableWrapperState != null)
      {
        localObject = mDrawable;
        if (localObject != null) {
          localObject = ((android.graphics.drawable.Drawable)localObject).getConstantState();
        } else {
          localObject = null;
        }
        mDrawableState = ((Drawable.ConstantState)localObject);
      }
      mMutated = true;
    }
    return this;
  }
  
  DrawableWrapperState mutateConstantState()
  {
    return new DrawableWrapperEclair.DrawableWrapperStateEclair(mState, null);
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    android.graphics.drawable.Drawable localDrawable = mDrawable;
    if (localDrawable != null) {
      localDrawable.setBounds(paramRect);
    }
  }
  
  protected boolean onLevelChange(int paramInt)
  {
    return mDrawable.setLevel(paramInt);
  }
  
  public void scheduleDrawable(android.graphics.drawable.Drawable paramDrawable, Runnable paramRunnable, long paramLong)
  {
    scheduleSelf(paramRunnable, paramLong);
  }
  
  public void setAlpha(int paramInt)
  {
    mDrawable.setAlpha(paramInt);
  }
  
  public void setAutoMirrored(boolean paramBoolean)
  {
    mDrawable.setAutoMirrored(paramBoolean);
  }
  
  public void setChangingConfigurations(int paramInt)
  {
    mDrawable.setChangingConfigurations(paramInt);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    mDrawable.setColorFilter(paramColorFilter);
  }
  
  public void setDither(boolean paramBoolean)
  {
    mDrawable.setDither(paramBoolean);
  }
  
  public void setFilterBitmap(boolean paramBoolean)
  {
    mDrawable.setFilterBitmap(paramBoolean);
  }
  
  public boolean setState(int[] paramArrayOfInt)
  {
    boolean bool = mDrawable.setState(paramArrayOfInt);
    return (updateTint(paramArrayOfInt)) || (bool);
  }
  
  public void setTint(int paramInt)
  {
    setTintList(ColorStateList.valueOf(paramInt));
  }
  
  public void setTintList(ColorStateList paramColorStateList)
  {
    mState.mTint = paramColorStateList;
    updateTint(getState());
  }
  
  public void setTintMode(PorterDuff.Mode paramMode)
  {
    mState.mTintMode = paramMode;
    updateTint(getState());
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2)
  {
    return (super.setVisible(paramBoolean1, paramBoolean2)) || (mDrawable.setVisible(paramBoolean1, paramBoolean2));
  }
  
  public final void setWrappedDrawable(android.graphics.drawable.Drawable paramDrawable)
  {
    Object localObject = mDrawable;
    if (localObject != null) {
      ((android.graphics.drawable.Drawable)localObject).setCallback(null);
    }
    mDrawable = paramDrawable;
    if (paramDrawable != null)
    {
      paramDrawable.setCallback(this);
      setVisible(paramDrawable.isVisible(), true);
      setState(paramDrawable.getState());
      setLevel(paramDrawable.getLevel());
      setBounds(paramDrawable.getBounds());
      localObject = mState;
      if (localObject != null) {
        mDrawableState = paramDrawable.getConstantState();
      }
    }
    invalidateSelf();
  }
  
  public void unscheduleDrawable(android.graphics.drawable.Drawable paramDrawable, Runnable paramRunnable)
  {
    unscheduleSelf(paramRunnable);
  }
  
  public abstract class DrawableWrapperState
    extends Drawable.ConstantState
  {
    int mChangingConfigurations;
    Drawable.ConstantState mDrawableState;
    ColorStateList mTint = null;
    PorterDuff.Mode mTintMode = DrawableWrapperDonut.DEFAULT_TINT_MODE;
    
    DrawableWrapperState(Resources paramResources)
    {
      if (this$1 != null)
      {
        mChangingConfigurations = mChangingConfigurations;
        mDrawableState = mDrawableState;
        mTint = mTint;
        mTintMode = mTintMode;
      }
    }
    
    boolean canConstantState()
    {
      return mDrawableState != null;
    }
    
    public int getChangingConfigurations()
    {
      int j = mChangingConfigurations;
      Drawable.ConstantState localConstantState = mDrawableState;
      int i;
      if (localConstantState != null) {
        i = localConstantState.getChangingConfigurations();
      } else {
        i = 0;
      }
      return j | i;
    }
    
    public android.graphics.drawable.Drawable newDrawable()
    {
      return newDrawable(null);
    }
    
    public abstract android.graphics.drawable.Drawable newDrawable(Resources paramResources);
  }
}

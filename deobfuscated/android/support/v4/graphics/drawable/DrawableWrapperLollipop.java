package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.util.Log;
import java.lang.reflect.Method;

class DrawableWrapperLollipop
  extends DrawableWrapperDonut
{
  private static Method mSetExclusiveCheckableMethod;
  
  DrawableWrapperLollipop(Drawable paramDrawable)
  {
    super(paramDrawable);
    setItem();
  }
  
  DrawableWrapperLollipop(DrawableWrapperDonut.DrawableWrapperState paramDrawableWrapperState, Resources paramResources)
  {
    super(paramDrawableWrapperState, paramResources);
    setItem();
  }
  
  private void setItem()
  {
    if (mSetExclusiveCheckableMethod == null) {
      try
      {
        Method localMethod = Drawable.class.getDeclaredMethod("isProjected", new Class[0]);
        mSetExclusiveCheckableMethod = localMethod;
        return;
      }
      catch (Exception localException)
      {
        Log.w("WrappedDrawableApi21", "Failed to retrieve Drawable#isProjected() method", localException);
      }
    }
  }
  
  public Rect getDirtyBounds()
  {
    return mDrawable.getDirtyBounds();
  }
  
  public void getOutline(Outline paramOutline)
  {
    mDrawable.getOutline(paramOutline);
  }
  
  protected boolean isCompatTintEnabled()
  {
    if (Build.VERSION.SDK_INT == 21)
    {
      Drawable localDrawable = mDrawable;
      if (((localDrawable instanceof GradientDrawable)) || ((localDrawable instanceof DrawableContainer)) || ((localDrawable instanceof InsetDrawable)) || ((localDrawable instanceof RippleDrawable))) {
        return true;
      }
    }
    return false;
  }
  
  public boolean isProjected()
  {
    Object localObject = mDrawable;
    if (localObject != null)
    {
      Method localMethod = mSetExclusiveCheckableMethod;
      if (localMethod != null) {
        try
        {
          localObject = localMethod.invoke(localObject, new Object[0]);
          localObject = (Boolean)localObject;
          boolean bool = ((Boolean)localObject).booleanValue();
          return bool;
        }
        catch (Exception localException)
        {
          Log.w("WrappedDrawableApi21", "Error calling Drawable#isProjected() method", localException);
        }
      }
    }
    return false;
  }
  
  DrawableWrapperDonut.DrawableWrapperState mutateConstantState()
  {
    return new DrawableWrapperStateLollipop(mState, null);
  }
  
  public void setHotspot(float paramFloat1, float paramFloat2)
  {
    mDrawable.setHotspot(paramFloat1, paramFloat2);
  }
  
  public void setHotspotBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mDrawable.setHotspotBounds(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public boolean setState(int[] paramArrayOfInt)
  {
    if (super.setState(paramArrayOfInt))
    {
      invalidateSelf();
      return true;
    }
    return false;
  }
  
  public void setTint(int paramInt)
  {
    if (isCompatTintEnabled())
    {
      super.setTint(paramInt);
      return;
    }
    mDrawable.setTint(paramInt);
  }
  
  public void setTintList(ColorStateList paramColorStateList)
  {
    if (isCompatTintEnabled())
    {
      super.setTintList(paramColorStateList);
      return;
    }
    mDrawable.setTintList(paramColorStateList);
  }
  
  public void setTintMode(PorterDuff.Mode paramMode)
  {
    if (isCompatTintEnabled())
    {
      super.setTintMode(paramMode);
      return;
    }
    mDrawable.setTintMode(paramMode);
  }
  
  class DrawableWrapperStateLollipop
    extends DrawableWrapperDonut.DrawableWrapperState
  {
    DrawableWrapperStateLollipop(Resources paramResources)
    {
      super(paramResources);
    }
    
    public Drawable newDrawable(Resources paramResources)
    {
      return new DrawableWrapperLollipop(this, paramResources);
    }
  }
}

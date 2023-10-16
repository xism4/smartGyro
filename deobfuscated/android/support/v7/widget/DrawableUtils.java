package android.support.v7.widget;

import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.DrawableContainer.DrawableContainerState;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build.VERSION;
import com.org.v4.graphics.drawable.DrawableWrapper;

public class DrawableUtils
{
  public static final Rect INSETS_NONE = new Rect();
  private static Class<?> sInsetsClazz;
  
  static
  {
    if (Build.VERSION.SDK_INT >= 18) {
      try
      {
        Class localClass = Class.forName("android.graphics.Insets");
        sInsetsClazz = localClass;
        return;
      }
      catch (ClassNotFoundException localClassNotFoundException) {}
    }
  }
  
  public static boolean canSafelyMutateDrawable(android.graphics.drawable.Drawable paramDrawable)
  {
    if ((Build.VERSION.SDK_INT < 15) && ((paramDrawable instanceof InsetDrawable))) {
      return false;
    }
    if ((Build.VERSION.SDK_INT < 15) && ((paramDrawable instanceof GradientDrawable))) {
      return false;
    }
    if ((Build.VERSION.SDK_INT < 17) && ((paramDrawable instanceof LayerDrawable))) {
      return false;
    }
    if ((paramDrawable instanceof DrawableContainer))
    {
      paramDrawable = paramDrawable.getConstantState();
      if ((paramDrawable instanceof DrawableContainer.DrawableContainerState))
      {
        paramDrawable = ((DrawableContainer.DrawableContainerState)paramDrawable).getChildren();
        int j = paramDrawable.length;
        int i = 0;
        while (i < j)
        {
          if (!canSafelyMutateDrawable(paramDrawable[i])) {
            return false;
          }
          i += 1;
        }
      }
    }
    else
    {
      if ((paramDrawable instanceof android.support.v4.graphics.drawable.Drawable)) {
        paramDrawable = ((android.support.v4.graphics.drawable.Drawable)paramDrawable).getWrappedDrawable();
      }
      for (;;)
      {
        return canSafelyMutateDrawable(paramDrawable);
        if ((paramDrawable instanceof DrawableWrapper))
        {
          paramDrawable = ((DrawableWrapper)paramDrawable).getWrappedDrawable();
        }
        else
        {
          if (!(paramDrawable instanceof ScaleDrawable)) {
            break;
          }
          paramDrawable = ((ScaleDrawable)paramDrawable).getDrawable();
        }
      }
    }
    return true;
  }
  
  static void fixDrawable(android.graphics.drawable.Drawable paramDrawable)
  {
    if ((Build.VERSION.SDK_INT == 21) && ("android.graphics.drawable.VectorDrawable".equals(paramDrawable.getClass().getName()))) {
      fixVectorDrawableTinting(paramDrawable);
    }
  }
  
  private static void fixVectorDrawableTinting(android.graphics.drawable.Drawable paramDrawable)
  {
    int[] arrayOfInt = paramDrawable.getState();
    if ((arrayOfInt != null) && (arrayOfInt.length != 0)) {
      paramDrawable.setState(ThemeUtils.EMPTY_STATE_SET);
    } else {
      paramDrawable.setState(ThemeUtils.CHECKED_STATE_SET);
    }
    paramDrawable.setState(arrayOfInt);
  }
  
  public static PorterDuff.Mode parseTintMode(int paramInt, PorterDuff.Mode paramMode)
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
}

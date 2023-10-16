package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;

public final class DrawableCompat
{
  private static boolean b;
  private static Method c;
  private static Method sGetLayoutDirectionMethod;
  private static boolean sGetLayoutDirectionMethodFetched;
  
  public static boolean a(Drawable paramDrawable, int paramInt)
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 23) {
      return paramDrawable.setLayoutDirection(paramInt);
    }
    if (i >= 17)
    {
      if (!b)
      {
        Object localObject = Integer.TYPE;
        try
        {
          localObject = Drawable.class.getDeclaredMethod("setLayoutDirection", new Class[] { localObject });
          c = (Method)localObject;
          localObject = c;
          ((Method)localObject).setAccessible(true);
        }
        catch (NoSuchMethodException localNoSuchMethodException)
        {
          Log.i("DrawableCompat", "Failed to retrieve setLayoutDirection(int) method", localNoSuchMethodException);
        }
        b = true;
      }
      Method localMethod = c;
      if (localMethod != null) {
        try
        {
          localMethod.invoke(paramDrawable, new Object[] { Integer.valueOf(paramInt) });
          return true;
        }
        catch (Exception paramDrawable)
        {
          Log.i("DrawableCompat", "Failed to invoke setLayoutDirection(int) via reflection", paramDrawable);
          c = null;
        }
      }
    }
    return false;
  }
  
  public static void applyTheme(Drawable paramDrawable, Resources.Theme paramTheme)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      paramDrawable.applyTheme(paramTheme);
    }
  }
  
  public static boolean canApplyTheme(Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return paramDrawable.canApplyTheme();
    }
    return false;
  }
  
  public static int getAlpha(Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return paramDrawable.getAlpha();
    }
    return 0;
  }
  
  public static ColorFilter getColorFilter(Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return paramDrawable.getColorFilter();
    }
    return null;
  }
  
  public static int getLayoutDirection(Drawable paramDrawable)
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 23) {
      return paramDrawable.getLayoutDirection();
    }
    if (i >= 17)
    {
      if (!sGetLayoutDirectionMethodFetched)
      {
        try
        {
          Method localMethod1 = Drawable.class.getDeclaredMethod("getLayoutDirection", new Class[0]);
          sGetLayoutDirectionMethod = localMethod1;
          localMethod1 = sGetLayoutDirectionMethod;
          localMethod1.setAccessible(true);
        }
        catch (NoSuchMethodException localNoSuchMethodException)
        {
          Log.i("DrawableCompat", "Failed to retrieve getLayoutDirection() method", localNoSuchMethodException);
        }
        sGetLayoutDirectionMethodFetched = true;
      }
      Method localMethod2 = sGetLayoutDirectionMethod;
      if (localMethod2 != null) {
        try
        {
          paramDrawable = localMethod2.invoke(paramDrawable, new Object[0]);
          paramDrawable = (Integer)paramDrawable;
          i = paramDrawable.intValue();
          return i;
        }
        catch (Exception paramDrawable)
        {
          Log.i("DrawableCompat", "Failed to invoke getLayoutDirection() via reflection", paramDrawable);
          sGetLayoutDirectionMethod = null;
        }
      }
    }
    return 0;
  }
  
  public static void inflate(Drawable paramDrawable, Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramDrawable.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
      return;
    }
    paramDrawable.inflate(paramResources, paramXmlPullParser, paramAttributeSet);
  }
  
  public static boolean isAutoMirrored(Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return paramDrawable.isAutoMirrored();
    }
    return false;
  }
  
  public static void jumpToCurrentState(Drawable paramDrawable)
  {
    paramDrawable.jumpToCurrentState();
  }
  
  public static void setHotspot(Drawable paramDrawable, float paramFloat1, float paramFloat2)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      paramDrawable.setHotspot(paramFloat1, paramFloat2);
    }
  }
  
  public static void setHotspotBounds(Drawable paramDrawable, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      paramDrawable.setHotspotBounds(paramInt1, paramInt2, paramInt3, paramInt4);
    }
  }
  
  public static void setHotspotBounds(Drawable paramDrawable, boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      paramDrawable.setAutoMirrored(paramBoolean);
    }
  }
  
  public static void setTint(Drawable paramDrawable, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramDrawable.setTint(paramInt);
      return;
    }
    if ((paramDrawable instanceof DrawableWrapper)) {
      ((DrawableWrapper)paramDrawable).setTint(paramInt);
    }
  }
  
  public static void setTintList(Drawable paramDrawable, ColorStateList paramColorStateList)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramDrawable.setTintList(paramColorStateList);
      return;
    }
    if ((paramDrawable instanceof DrawableWrapper)) {
      ((DrawableWrapper)paramDrawable).setTintList(paramColorStateList);
    }
  }
  
  public static void setTintMode(Drawable paramDrawable, PorterDuff.Mode paramMode)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramDrawable.setTintMode(paramMode);
      return;
    }
    if ((paramDrawable instanceof DrawableWrapper)) {
      ((DrawableWrapper)paramDrawable).setTintMode(paramMode);
    }
  }
  
  public static Drawable wrap(Drawable paramDrawable)
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 23) {
      return paramDrawable;
    }
    if (i >= 21)
    {
      if (!(paramDrawable instanceof DrawableWrapper)) {
        return new DrawableWrapperLollipop(paramDrawable);
      }
      return paramDrawable;
    }
    if (!(paramDrawable instanceof DrawableWrapper)) {
      return new DrawableWrapperDonut(paramDrawable);
    }
    return paramDrawable;
  }
}

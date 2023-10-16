package com.org.android.ui.asm;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import com.org.android.asm.c;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public final class ClassReader
{
  public static Typeface a(Context paramContext, int paramInt1, TypedValue paramTypedValue, int paramInt2, k paramK)
  {
    if (paramContext.isRestricted()) {
      return null;
    }
    return a(paramContext, paramInt1, paramTypedValue, paramInt2, paramK, null, true);
  }
  
  private static Typeface a(Context paramContext, int paramInt1, TypedValue paramTypedValue, int paramInt2, k paramK, Handler paramHandler, boolean paramBoolean)
  {
    Resources localResources = paramContext.getResources();
    localResources.getValue(paramInt1, paramTypedValue, true);
    paramContext = a(paramContext, localResources, paramTypedValue, paramInt1, paramInt2, paramK, paramHandler, paramBoolean);
    if (paramContext == null)
    {
      if (paramK != null) {
        return paramContext;
      }
      paramContext = new StringBuilder();
      paramContext.append("Font resource ID #0x");
      paramContext.append(Integer.toHexString(paramInt1));
      paramContext.append(" could not be retrieved.");
      throw new Resources.NotFoundException(paramContext.toString());
    }
    return paramContext;
  }
  
  private static Typeface a(Context paramContext, Resources paramResources, TypedValue paramTypedValue, int paramInt1, int paramInt2, k paramK, Handler paramHandler, boolean paramBoolean)
  {
    Object localObject = string;
    if (localObject != null)
    {
      localObject = ((CharSequence)localObject).toString();
      if (!((String)localObject).startsWith("res/"))
      {
        if (paramK != null)
        {
          paramK.a(-3, paramHandler);
          return null;
        }
      }
      else
      {
        paramTypedValue = c.a(paramResources, paramInt1, paramInt2);
        if (paramTypedValue != null)
        {
          if (paramK == null) {
            break label322;
          }
          paramK.a(paramTypedValue, paramHandler);
          return paramTypedValue;
        }
        try
        {
          boolean bool = ((String)localObject).toLowerCase().endsWith(".xml");
          if (bool)
          {
            paramTypedValue = Type.createFromXml(paramResources.getXml(paramInt1), paramResources);
            if (paramTypedValue == null)
            {
              Log.e("ResourcesCompat", "Failed to find font-family tag");
              if (paramK == null) {
                break label324;
              }
              paramK.a(-3, paramHandler);
              return null;
            }
            paramContext = c.a(paramContext, paramTypedValue, paramResources, paramInt1, paramInt2, paramK, paramHandler, paramBoolean);
            return paramContext;
          }
          paramContext = c.a(paramContext, paramResources, paramInt1, (String)localObject, paramInt2);
          if (paramK == null) {
            break label326;
          }
          if (paramContext != null)
          {
            paramK.a(paramContext, paramHandler);
            return paramContext;
          }
          paramK.a(-3, paramHandler);
          return paramContext;
        }
        catch (IOException paramResources)
        {
          paramTypedValue = new StringBuilder();
          paramContext = "Failed to read xml resource ";
        }
        catch (XmlPullParserException paramResources)
        {
          paramTypedValue = new StringBuilder();
          paramContext = "Failed to parse xml resource ";
        }
        paramTypedValue.append(paramContext);
        paramTypedValue.append((String)localObject);
        Log.e("ResourcesCompat", paramTypedValue.toString(), paramResources);
        if (paramK == null) {
          break label328;
        }
        paramK.a(-3, paramHandler);
        return null;
      }
    }
    else
    {
      paramContext = new StringBuilder();
      paramContext.append("Resource \"");
      paramContext.append(paramResources.getResourceName(paramInt1));
      paramContext.append("\" (");
      paramContext.append(Integer.toHexString(paramInt1));
      paramContext.append(") is not a Font: ");
      paramContext.append(paramTypedValue);
      throw new Resources.NotFoundException(paramContext.toString());
    }
    return null;
    label322:
    return paramTypedValue;
    label324:
    return null;
    label326:
    return paramContext;
    label328:
    return null;
  }
  
  public static Drawable getDrawable(Resources paramResources, int paramInt, Resources.Theme paramTheme)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return paramResources.getDrawable(paramInt, paramTheme);
    }
    return paramResources.getDrawable(paramInt);
  }
}

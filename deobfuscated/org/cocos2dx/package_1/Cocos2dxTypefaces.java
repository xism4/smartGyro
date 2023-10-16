package org.cocos2dx.package_1;

import android.content.Context;
import android.graphics.Typeface;
import java.util.HashMap;

public class Cocos2dxTypefaces
{
  private static final HashMap<String, Typeface> sTypefaceCache = new HashMap();
  
  public Cocos2dxTypefaces() {}
  
  public static Typeface get(Context paramContext, String paramString)
  {
    try
    {
      if (!sTypefaceCache.containsKey(paramString))
      {
        if (paramString.startsWith("/")) {
          paramContext = Typeface.createFromFile(paramString);
        } else {
          paramContext = Typeface.createFromAsset(paramContext.getAssets(), paramString);
        }
        sTypefaceCache.put(paramString, paramContext);
      }
      paramContext = (Typeface)sTypefaceCache.get(paramString);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
}

package com.org.android.asm;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.os.Handler;
import com.org.android.manager.Label;
import com.org.android.manager.i;
import com.org.android.ui.asm.b;
import com.org.android.ui.asm.k;
import com.org.android.ui.asm.l;
import com.org.android.util.LruCache;

public class c
{
  private static final f c;
  private static final a.a.c.f.e<String, Typeface> d;
  
  static
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a2 = a1\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer$LiveA.onUseLocal(UnSSATransformer.java:552)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer$LiveA.onUseLocal(UnSSATransformer.java:1)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.onUse(BaseAnalyze.java:166)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.onUse(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.travel(Cfg.java:331)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.travel(Cfg.java:387)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:90)\n\t... 17 more\n");
  }
  
  public static Typeface a(Context paramContext, Resources paramResources, int paramInt1, String paramString, int paramInt2)
  {
    paramContext = c.a(paramContext, paramResources, paramInt1, paramString, paramInt2);
    if (paramContext != null)
    {
      paramResources = valueOf(paramResources, paramInt1, paramInt2);
      d.put(paramResources, paramContext);
    }
    return paramContext;
  }
  
  public static Typeface a(Context paramContext, CancellationSignal paramCancellationSignal, Label[] paramArrayOfLabel, int paramInt)
  {
    return c.a(paramContext, paramCancellationSignal, paramArrayOfLabel, paramInt);
  }
  
  public static Typeface a(Context paramContext, l paramL, Resources paramResources, int paramInt1, int paramInt2, k paramK, Handler paramHandler, boolean paramBoolean)
  {
    if ((paramL instanceof b))
    {
      paramL = (b)paramL;
      boolean bool = false;
      if (paramBoolean ? paramL.d() == 0 : paramK == null) {
        bool = true;
      }
      int i;
      if (paramBoolean) {
        i = paramL.e();
      } else {
        i = -1;
      }
      paramL = i.a(paramContext, paramL.a(), paramK, paramHandler, bool, i, paramInt2);
    }
    else
    {
      Typeface localTypeface = c.a(paramContext, (com.org.android.ui.asm.e)paramL, paramResources, paramInt2);
      paramContext = localTypeface;
      paramL = paramContext;
      if (paramK != null) {
        if (localTypeface != null)
        {
          paramK.a(localTypeface, paramHandler);
          paramL = paramContext;
        }
        else
        {
          paramK.a(-3, paramHandler);
          paramL = paramContext;
        }
      }
    }
    if (paramL != null) {
      d.put(valueOf(paramResources, paramInt1, paramInt2), paramL);
    }
    return paramL;
  }
  
  public static Typeface a(Resources paramResources, int paramInt1, int paramInt2)
  {
    return (Typeface)d.get(valueOf(paramResources, paramInt1, paramInt2));
  }
  
  private static String valueOf(Resources paramResources, int paramInt1, int paramInt2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramResources.getResourcePackageName(paramInt1));
    localStringBuilder.append("-");
    localStringBuilder.append(paramInt1);
    localStringBuilder.append("-");
    localStringBuilder.append(paramInt2);
    return localStringBuilder.toString();
  }
}

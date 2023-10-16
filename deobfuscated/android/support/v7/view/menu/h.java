package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.widget.PopupWindow.OnDismissListener;
import com.org.android.view.ViewCompat;

public class h
  implements MenuItem
{
  private android.view.View a;
  private d b;
  private final Context c;
  private final MenuBuilder d;
  private final int e;
  private final boolean f;
  private final int g;
  private boolean h;
  private l.a k;
  private int l = 8388611;
  private PopupWindow.OnDismissListener v;
  private final PopupWindow.OnDismissListener x = new w(this);
  
  public h(Context paramContext, MenuBuilder paramMenuBuilder, android.view.View paramView, boolean paramBoolean, int paramInt)
  {
    this(paramContext, paramMenuBuilder, paramView, paramBoolean, paramInt, 0);
  }
  
  public h(Context paramContext, MenuBuilder paramMenuBuilder, android.view.View paramView, boolean paramBoolean, int paramInt1, int paramInt2)
  {
    c = paramContext;
    d = paramMenuBuilder;
    a = paramView;
    f = paramBoolean;
    e = paramInt1;
    g = paramInt2;
  }
  
  private void a(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    d localD = a();
    localD.a(paramBoolean2);
    if (paramBoolean1)
    {
      int i = paramInt1;
      if ((com.org.android.view.View.getAbsoluteGravity(l, ViewCompat.getLayoutDirection(a)) & 0x7) == 5) {
        i = paramInt1 - a.getWidth();
      }
      localD.c(i);
      localD.b(paramInt2);
      paramInt1 = (int)(c.getResources().getDisplayMetrics().density * 48.0F / 2.0F);
      localD.b(new Rect(i - paramInt1, paramInt2 - paramInt1, i + paramInt1, paramInt2 + paramInt1));
    }
    localD.show();
  }
  
  private d onCreateView()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a7 = a6\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer$LiveA.onUseLocal(UnSSATransformer.java:552)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer$LiveA.onUseLocal(UnSSATransformer.java:1)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.onUse(BaseAnalyze.java:166)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.onUse(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.travel(Cfg.java:331)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.travel(Cfg.java:387)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:90)\n\t... 17 more\n");
  }
  
  public d a()
  {
    if (b == null) {
      b = onCreateView();
    }
    return b;
  }
  
  public void a(l.a paramA)
  {
    k = paramA;
    d localD = b;
    if (localD != null) {
      localD.a(paramA);
    }
  }
  
  public void a(android.view.View paramView)
  {
    a = paramView;
  }
  
  public void a(boolean paramBoolean)
  {
    h = paramBoolean;
    d localD = b;
    if (localD != null) {
      localD.b(paramBoolean);
    }
  }
  
  protected void b()
  {
    b = null;
    PopupWindow.OnDismissListener localOnDismissListener = v;
    if (localOnDismissListener != null) {
      localOnDismissListener.onDismiss();
    }
  }
  
  public void b(int paramInt)
  {
    l = paramInt;
  }
  
  public void b(PopupWindow.OnDismissListener paramOnDismissListener)
  {
    v = paramOnDismissListener;
  }
  
  public boolean b(int paramInt1, int paramInt2)
  {
    if (isShowing()) {
      return true;
    }
    if (a == null) {
      return false;
    }
    a(paramInt1, paramInt2, true, true);
    return true;
  }
  
  public boolean c()
  {
    if (isShowing()) {
      return true;
    }
    if (a == null) {
      return false;
    }
    a(0, 0, false, false);
    return true;
  }
  
  public void dismiss()
  {
    if (isShowing()) {
      b.dismiss();
    }
  }
  
  public boolean isShowing()
  {
    d localD = b;
    return (localD != null) && (localD.isShowing());
  }
  
  public void setTitle()
  {
    if (c()) {
      return;
    }
    throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
  }
}

package com.org.android.view;

import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

public final class ViewPropertyAnimatorCompat
{
  Runnable mEndAction = null;
  int mOldLayerType = -1;
  Runnable mStartAction = null;
  private WeakReference<View> mView;
  
  ViewPropertyAnimatorCompat(View paramView)
  {
    mView = new WeakReference(paramView);
  }
  
  private void setListener(View paramView, ViewPropertyAnimatorListener paramViewPropertyAnimatorListener)
  {
    if (paramViewPropertyAnimatorListener != null)
    {
      paramView.animate().setListener(new ValueAnimatorCompatImplHoneycombMr1.2(this, paramViewPropertyAnimatorListener, paramView));
      return;
    }
    paramView.animate().setListener(null);
  }
  
  public ViewPropertyAnimatorCompat alpha(float paramFloat)
  {
    View localView = (View)mView.get();
    if (localView != null) {
      localView.animate().alpha(paramFloat);
    }
    return this;
  }
  
  public ViewPropertyAnimatorCompat cancel(ViewPropertyAnimatorUpdateListener paramViewPropertyAnimatorUpdateListener)
  {
    View localView = (View)mView.get();
    if ((localView != null) && (Build.VERSION.SDK_INT >= 19))
    {
      HoneycombMr1AnimatorCompatProvider.HoneycombValueAnimatorCompat.1 local1 = null;
      if (paramViewPropertyAnimatorUpdateListener != null) {
        local1 = new HoneycombMr1AnimatorCompatProvider.HoneycombValueAnimatorCompat.1(this, paramViewPropertyAnimatorUpdateListener, localView);
      }
      localView.animate().setUpdateListener(local1);
    }
    return this;
  }
  
  public void cancel()
  {
    View localView = (View)mView.get();
    if (localView != null) {
      localView.animate().cancel();
    }
  }
  
  public long getDuration()
  {
    View localView = (View)mView.get();
    if (localView != null) {
      return localView.animate().getDuration();
    }
    return 0L;
  }
  
  public ViewPropertyAnimatorCompat setDuration(long paramLong)
  {
    View localView = (View)mView.get();
    if (localView != null) {
      localView.animate().setDuration(paramLong);
    }
    return this;
  }
  
  public ViewPropertyAnimatorCompat setInterpolator(Interpolator paramInterpolator)
  {
    View localView = (View)mView.get();
    if (localView != null) {
      localView.animate().setInterpolator(paramInterpolator);
    }
    return this;
  }
  
  public ViewPropertyAnimatorCompat setListener(ViewPropertyAnimatorListener paramViewPropertyAnimatorListener)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a4 = a3\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  public ViewPropertyAnimatorCompat setStartDelay(long paramLong)
  {
    View localView = (View)mView.get();
    if (localView != null) {
      localView.animate().setStartDelay(paramLong);
    }
    return this;
  }
  
  public void start()
  {
    View localView = (View)mView.get();
    if (localView != null) {
      localView.animate().start();
    }
  }
  
  public ViewPropertyAnimatorCompat translationY(float paramFloat)
  {
    View localView = (View)mView.get();
    if (localView != null) {
      localView.animate().translationY(paramFloat);
    }
    return this;
  }
  
  class ICSViewPropertyAnimatorCompatImpl$MyVpaListener
    implements ViewPropertyAnimatorListener
  {
    boolean mAnimEndCalled;
    
    ICSViewPropertyAnimatorCompatImpl$MyVpaListener() {}
    
    public void onAnimationCancel(View paramView)
    {
      Object localObject = paramView.getTag(2113929216);
      if ((localObject instanceof ViewPropertyAnimatorListener)) {
        localObject = (ViewPropertyAnimatorListener)localObject;
      } else {
        localObject = null;
      }
      if (localObject != null) {
        ((ViewPropertyAnimatorListener)localObject).onAnimationCancel(paramView);
      }
    }
    
    public void onAnimationEnd(View paramView)
    {
      int i = mOldLayerType;
      ViewPropertyAnimatorListener localViewPropertyAnimatorListener = null;
      if (i > -1)
      {
        paramView.setLayerType(i, null);
        mOldLayerType = -1;
      }
      if ((Build.VERSION.SDK_INT >= 16) || (!mAnimEndCalled))
      {
        Object localObject = ViewPropertyAnimatorCompat.this;
        Runnable localRunnable = mEndAction;
        if (localRunnable != null)
        {
          mEndAction = null;
          localRunnable.run();
        }
        localObject = paramView.getTag(2113929216);
        if ((localObject instanceof ViewPropertyAnimatorListener)) {
          localViewPropertyAnimatorListener = (ViewPropertyAnimatorListener)localObject;
        }
        if (localViewPropertyAnimatorListener != null) {
          localViewPropertyAnimatorListener.onAnimationEnd(paramView);
        }
        mAnimEndCalled = true;
      }
    }
    
    public void onAnimationStart(View paramView)
    {
      mAnimEndCalled = false;
      int i = mOldLayerType;
      ViewPropertyAnimatorListener localViewPropertyAnimatorListener = null;
      if (i > -1) {
        paramView.setLayerType(2, null);
      }
      Object localObject = ViewPropertyAnimatorCompat.this;
      Runnable localRunnable = mStartAction;
      if (localRunnable != null)
      {
        mStartAction = null;
        localRunnable.run();
      }
      localObject = paramView.getTag(2113929216);
      if ((localObject instanceof ViewPropertyAnimatorListener)) {
        localViewPropertyAnimatorListener = (ViewPropertyAnimatorListener)localObject;
      }
      if (localViewPropertyAnimatorListener != null) {
        localViewPropertyAnimatorListener.onAnimationStart(paramView);
      }
    }
  }
}

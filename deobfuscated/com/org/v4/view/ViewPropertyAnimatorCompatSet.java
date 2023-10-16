package com.org.v4.view;

import a.a.c.g.z;
import android.view.View;
import android.view.animation.Interpolator;
import com.org.android.view.ViewPropertyAnimatorCompat;
import com.org.android.view.ViewPropertyAnimatorListener;
import com.org.android.view.ViewPropertyAnimatorListenerAdapter;
import java.util.ArrayList;
import java.util.Iterator;

public class ViewPropertyAnimatorCompatSet
{
  final ArrayList<z> mAnimators = new ArrayList();
  private long mDuration = -1L;
  private Interpolator mInterpolator;
  private boolean mIsStarted;
  ViewPropertyAnimatorListener mListener;
  private final ViewPropertyAnimatorListenerAdapter mProxyListener = new ViewPropertyAnimatorListenerAdapter()
  {
    private int mProxyEndCount = 0;
    private boolean mProxyStarted = false;
    
    public void onAnimationEnd(View paramAnonymousView)
    {
      int i = mProxyEndCount + 1;
      mProxyEndCount = i;
      if (i == mAnimators.size())
      {
        paramAnonymousView = mListener;
        if (paramAnonymousView != null) {
          paramAnonymousView.onAnimationEnd(null);
        }
        onEnd();
      }
    }
    
    public void onAnimationStart(View paramAnonymousView)
    {
      if (mProxyStarted) {
        return;
      }
      mProxyStarted = true;
      paramAnonymousView = mListener;
      if (paramAnonymousView != null) {
        paramAnonymousView.onAnimationStart(null);
      }
    }
    
    void onEnd()
    {
      mProxyEndCount = 0;
      mProxyStarted = false;
      onAnimationsEnded();
    }
  };
  
  public ViewPropertyAnimatorCompatSet() {}
  
  public void cancel()
  {
    if (!mIsStarted) {
      return;
    }
    Iterator localIterator = mAnimators.iterator();
    while (localIterator.hasNext()) {
      ((ViewPropertyAnimatorCompat)localIterator.next()).cancel();
    }
    mIsStarted = false;
  }
  
  void onAnimationsEnded()
  {
    mIsStarted = false;
  }
  
  public ViewPropertyAnimatorCompatSet play(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat)
  {
    if (!mIsStarted) {
      mAnimators.add(paramViewPropertyAnimatorCompat);
    }
    return this;
  }
  
  public ViewPropertyAnimatorCompatSet playSequentially(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat1, ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat2)
  {
    mAnimators.add(paramViewPropertyAnimatorCompat1);
    paramViewPropertyAnimatorCompat2.setStartDelay(paramViewPropertyAnimatorCompat1.getDuration());
    mAnimators.add(paramViewPropertyAnimatorCompat2);
    return this;
  }
  
  public ViewPropertyAnimatorCompatSet setDuration(long paramLong)
  {
    if (!mIsStarted) {
      mDuration = paramLong;
    }
    return this;
  }
  
  public ViewPropertyAnimatorCompatSet setInterpolator(Interpolator paramInterpolator)
  {
    if (!mIsStarted) {
      mInterpolator = paramInterpolator;
    }
    return this;
  }
  
  public ViewPropertyAnimatorCompatSet setListener(ViewPropertyAnimatorListener paramViewPropertyAnimatorListener)
  {
    if (!mIsStarted) {
      mListener = paramViewPropertyAnimatorListener;
    }
    return this;
  }
  
  public void start()
  {
    if (mIsStarted) {
      return;
    }
    Iterator localIterator = mAnimators.iterator();
    while (localIterator.hasNext())
    {
      ViewPropertyAnimatorCompat localViewPropertyAnimatorCompat = (ViewPropertyAnimatorCompat)localIterator.next();
      long l = mDuration;
      if (l >= 0L) {
        localViewPropertyAnimatorCompat.setDuration(l);
      }
      Interpolator localInterpolator = mInterpolator;
      if (localInterpolator != null) {
        localViewPropertyAnimatorCompat.setInterpolator(localInterpolator);
      }
      if (mListener != null) {
        localViewPropertyAnimatorCompat.setListener(mProxyListener);
      }
      localViewPropertyAnimatorCompat.start();
    }
    mIsStarted = true;
  }
}

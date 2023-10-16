package com.org.android.view;

import android.view.View;

public abstract interface ViewPropertyAnimatorListener
{
  public abstract void onAnimationCancel(View paramView);
  
  public abstract void onAnimationEnd(View paramView);
  
  public abstract void onAnimationStart(View paramView);
}

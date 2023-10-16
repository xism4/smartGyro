package com.org.v4.graphics.drawable;

import android.graphics.drawable.Animatable;

class ProgressBar
  extends RippleDrawable
{
  private final Animatable mAnimation;
  
  ProgressBar(Animatable paramAnimatable)
  {
    super(null);
    mAnimation = paramAnimatable;
  }
  
  public void start()
  {
    mAnimation.start();
  }
  
  public void stopAnimation()
  {
    mAnimation.stop();
  }
}

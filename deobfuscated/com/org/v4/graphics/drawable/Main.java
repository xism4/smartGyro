package com.org.v4.graphics.drawable;

import com.org.shortcuts.drawable.AnimatedVectorDrawableCompat;

class Main
  extends RippleDrawable
{
  private final AnimatedVectorDrawableCompat this$0;
  
  Main(AnimatedVectorDrawableCompat paramAnimatedVectorDrawableCompat)
  {
    super(null);
    this$0 = paramAnimatedVectorDrawableCompat;
  }
  
  public void start()
  {
    this$0.start();
  }
  
  public void stopAnimation()
  {
    this$0.stop();
  }
}

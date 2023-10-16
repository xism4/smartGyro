package com.org.v4.graphics.drawable;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build.VERSION;

class CircleDrawable
  extends RippleDrawable
{
  private final ObjectAnimator mAnimator;
  private final boolean mVisible;
  
  CircleDrawable(AnimationDrawable paramAnimationDrawable, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(null);
    int j = paramAnimationDrawable.getNumberOfFrames();
    int i;
    if (paramBoolean1) {
      i = j - 1;
    } else {
      i = 0;
    }
    if (paramBoolean1) {
      j = 0;
    } else {
      j -= 1;
    }
    a localA = new a(paramAnimationDrawable, paramBoolean1);
    paramAnimationDrawable = ObjectAnimator.ofInt(paramAnimationDrawable, "currentIndex", new int[] { i, j });
    if (Build.VERSION.SDK_INT >= 18) {
      paramAnimationDrawable.setAutoCancel(true);
    }
    paramAnimationDrawable.setDuration(localA.b());
    paramAnimationDrawable.setInterpolator(localA);
    mVisible = paramBoolean2;
    mAnimator = paramAnimationDrawable;
  }
  
  public boolean draw()
  {
    return mVisible;
  }
  
  public void setColor()
  {
    mAnimator.reverse();
  }
  
  public void start()
  {
    mAnimator.start();
  }
  
  public void stopAnimation()
  {
    mAnimator.cancel();
  }
}

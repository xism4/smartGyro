package com.org.v4.graphics.drawable;

import android.animation.TimeInterpolator;
import android.graphics.drawable.AnimationDrawable;

class a
  implements TimeInterpolator
{
  private int n;
  private int[] o;
  private int p;
  
  a(AnimationDrawable paramAnimationDrawable, boolean paramBoolean)
  {
    a(paramAnimationDrawable, paramBoolean);
  }
  
  int a(AnimationDrawable paramAnimationDrawable, boolean paramBoolean)
  {
    int m = paramAnimationDrawable.getNumberOfFrames();
    n = m;
    int[] arrayOfInt = o;
    if ((arrayOfInt == null) || (arrayOfInt.length < m)) {
      o = new int[m];
    }
    arrayOfInt = o;
    int i = 0;
    int j = 0;
    while (i < m)
    {
      if (paramBoolean) {
        k = m - i - 1;
      } else {
        k = i;
      }
      int k = paramAnimationDrawable.getDuration(k);
      arrayOfInt[i] = k;
      j += k;
      i += 1;
    }
    p = j;
    return j;
  }
  
  int b()
  {
    return p;
  }
  
  public float getInterpolation(float paramFloat)
  {
    int j = (int)(paramFloat * p + 0.5F);
    int k = n;
    int[] arrayOfInt = o;
    int i = 0;
    while ((i < k) && (j >= arrayOfInt[i]))
    {
      j -= arrayOfInt[i];
      i += 1;
    }
    if (i < k) {
      paramFloat = j / p;
    } else {
      paramFloat = 0.0F;
    }
    return i / k + paramFloat;
  }
}

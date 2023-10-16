package com.org.android.ui.asm;

import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;

public abstract class k
{
  public k() {}
  
  public final void a(int paramInt, Handler paramHandler)
  {
    Handler localHandler = paramHandler;
    if (paramHandler == null) {
      localHandler = new Handler(Looper.getMainLooper());
    }
    localHandler.post(new Plot.a(this, paramInt));
  }
  
  public abstract void a(Typeface paramTypeface);
  
  public final void a(Typeface paramTypeface, Handler paramHandler)
  {
    Handler localHandler = paramHandler;
    if (paramHandler == null) {
      localHandler = new Handler(Looper.getMainLooper());
    }
    localHandler.post(new a(this, paramTypeface));
  }
  
  public abstract void setTitle(int paramInt);
}

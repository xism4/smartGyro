package com.org.android.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;

public abstract interface TintableBackgroundView
{
  public abstract ColorStateList getSupportBackgroundTintList();
  
  public abstract PorterDuff.Mode getSupportBackgroundTintMode();
  
  public abstract void setSupportBackgroundTintList(ColorStateList paramColorStateList);
  
  public abstract void setSupportBackgroundTintMode(PorterDuff.Mode paramMode);
}

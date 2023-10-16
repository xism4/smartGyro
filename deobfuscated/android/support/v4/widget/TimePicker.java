package android.support.v4.widget;

import android.os.Build.VERSION;

public abstract interface TimePicker
{
  public static final boolean mIs24HourMode;
  
  static
  {
    boolean bool;
    if (Build.VERSION.SDK_INT >= 27) {
      bool = true;
    } else {
      bool = false;
    }
    mIs24HourMode = bool;
  }
}

package com.org.android.view.accessibility;

import android.os.Build.VERSION;
import android.view.accessibility.AccessibilityRecord;

public class AccessibilityRecordCompat
{
  public static void obtain(AccessibilityRecord paramAccessibilityRecord, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 15) {
      paramAccessibilityRecord.setMaxScrollY(paramInt);
    }
  }
  
  public static void setMaxScrollX(AccessibilityRecord paramAccessibilityRecord, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 15) {
      paramAccessibilityRecord.setMaxScrollX(paramInt);
    }
  }
}

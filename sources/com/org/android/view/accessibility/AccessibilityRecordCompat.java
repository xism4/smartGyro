package com.org.android.view.accessibility;

import android.os.Build;
import android.view.accessibility.AccessibilityRecord;

public class AccessibilityRecordCompat {
    public static void obtain(AccessibilityRecord accessibilityRecord, int i) {
        if (Build.VERSION.SDK_INT >= 15) {
            accessibilityRecord.setMaxScrollY(i);
        }
    }

    public static void setMaxScrollX(AccessibilityRecord accessibilityRecord, int i) {
        if (Build.VERSION.SDK_INT >= 15) {
            accessibilityRecord.setMaxScrollX(i);
        }
    }
}

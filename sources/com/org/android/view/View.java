package com.org.android.view;

import android.os.Build;
import android.view.Gravity;

public final class View {
    public static int getAbsoluteGravity(int $i1, int i) {
        return Build.VERSION.SDK_INT >= 17 ? Gravity.getAbsoluteGravity($i1, i) : $i1 & -8388609;
    }
}

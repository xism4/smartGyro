package com.org.android.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Process;
import android.util.TypedValue;

public class Resources {
    private static final Object cache = new Object();
    private static TypedValue mTypedValue;

    public static int checkSelfPermission(Context context, String str) {
        if (str != null) {
            return context.checkPermission(str, Process.myPid(), Process.myUid());
        }
        throw new IllegalArgumentException("permission is null");
    }

    public static ColorStateList getColorStateList(Context context, int i) {
        return Build.VERSION.SDK_INT >= 23 ? context.getColorStateList(i) : context.getResources().getColorStateList(i);
    }

    public static Drawable getDrawable(Context context, int i) {
        int $i1 = Build.VERSION.SDK_INT;
        if ($i1 >= 21) {
            return context.getDrawable(i);
        }
        if ($i1 < 16) {
            synchronized (cache) {
                if (mTypedValue == null) {
                    mTypedValue = new TypedValue();
                }
                context.getResources().getValue(i, mTypedValue, true);
                i = mTypedValue.resourceId;
            }
        }
        return context.getResources().getDrawable(i);
    }
}

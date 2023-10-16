package com.org.android.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import android.view.ViewConfiguration;
import java.lang.reflect.Method;

public final class Type {
    private static Method b = ViewConfiguration.class.getDeclaredMethod("getScaledScrollFactor", new Class[0]);

    static {
        if (Build.VERSION.SDK_INT == 25) {
            try {
            } catch (Exception e) {
                Log.i("ViewConfigCompat", "Could not find method getScaledScrollFactor() on ViewConfiguration");
            }
        }
    }

    public static boolean a(ViewConfiguration viewConfiguration, Context context) {
        if (Build.VERSION.SDK_INT >= 28) {
            return viewConfiguration.shouldShowMenuShortcutsWhenKeyboardPresent();
        }
        Resources $r2 = context.getResources();
        int $i0 = $r2.getIdentifier("config_showMenuShortcutsWhenKeyboardPresent", "bool", "android");
        return $i0 != 0 && $r2.getBoolean($i0);
    }

    public static int getSize(ViewConfiguration viewConfiguration) {
        return Build.VERSION.SDK_INT >= 28 ? viewConfiguration.getScaledHoverSlop() : viewConfiguration.getScaledTouchSlop() / 2;
    }
}

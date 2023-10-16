package android.support.v4.widget;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import com.org.android.view.ViewCompat;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class PopupWindowCompat {
    private static boolean sCheckedField;
    private static Field sLayoutInflaterFactory2Field;
    private static Method sSetWindowLayoutTypeMethod;
    private static boolean sSetWindowLayoutTypeMethodAttempted;

    public static void init(PopupWindow popupWindow, boolean z) {
        int $i0 = Build.VERSION.SDK_INT;
        if ($i0 >= 23) {
            popupWindow.setOverlapAnchor(z);
        } else if ($i0 >= 21) {
            if (!sCheckedField) {
                try {
                    sLayoutInflaterFactory2Field = PopupWindow.class.getDeclaredField("mOverlapAnchor");
                    sLayoutInflaterFactory2Field.setAccessible(true);
                } catch (NoSuchFieldException $r3) {
                    Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", $r3);
                }
                sCheckedField = true;
            }
            Field $r2 = sLayoutInflaterFactory2Field;
            if ($r2 != null) {
                try {
                    $r2.set(popupWindow, Boolean.valueOf(z));
                } catch (IllegalAccessException $r5) {
                    Log.i("PopupWindowCompatApi21", "Could not set overlap anchor field in PopupWindow", $r5);
                }
            }
        }
    }

    public static void setWindowLayoutType(PopupWindow popupWindow, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            popupWindow.setWindowLayoutType(i);
            return;
        }
        if (!sSetWindowLayoutTypeMethodAttempted) {
            try {
                sSetWindowLayoutTypeMethod = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", new Class[]{Integer.TYPE});
                sSetWindowLayoutTypeMethod.setAccessible(true);
            } catch (Exception e) {
            }
            sSetWindowLayoutTypeMethodAttempted = true;
        }
        Method $r6 = sSetWindowLayoutTypeMethod;
        if ($r6 != null) {
            Object[] $r7 = new Object[1];
            try {
                $r7[0] = Integer.valueOf(i);
                $r6.invoke(popupWindow, $r7);
            } catch (Exception e2) {
            }
        }
    }

    public static void update(PopupWindow popupWindow, View view, int $i1, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 19) {
            popupWindow.showAsDropDown(view, $i1, i, i2);
            return;
        }
        if ((com.org.android.view.View.getAbsoluteGravity(i2, ViewCompat.getLayoutDirection(view)) & 7) == 5) {
            $i1 -= popupWindow.getWidth() - view.getWidth();
        }
        popupWindow.showAsDropDown(view, $i1, i);
    }
}

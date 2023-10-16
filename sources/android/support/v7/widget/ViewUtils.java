package android.support.v7.widget;

import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import com.org.android.view.ViewCompat;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ViewUtils {
    private static Method sComputeFitSystemWindowsMethod = View.class.getDeclaredMethod("computeFitSystemWindows", new Class[]{Rect.class, Rect.class});

    static {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                if (!sComputeFitSystemWindowsMethod.isAccessible()) {
                    sComputeFitSystemWindowsMethod.setAccessible(true);
                }
            } catch (NoSuchMethodException e) {
                Log.d("ViewUtils", "Could not find method computeFitSystemWindows. Oh well.");
            }
        }
    }

    public static void computeFitSystemWindows(View view, Rect rect, Rect rect2) {
        Method $r0 = sComputeFitSystemWindowsMethod;
        if ($r0 != null) {
            try {
                $r0.invoke(view, new Object[]{rect, rect2});
            } catch (Exception $r5) {
                Log.d("ViewUtils", "Could not invoke computeFitSystemWindows", $r5);
            }
        }
    }

    public static boolean isLayoutRtl(View view) {
        return ViewCompat.getLayoutDirection(view) == 1;
    }

    public static void makeOptionalFitsSystemWindows(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            try {
                Method $r4 = view.getClass().getMethod("makeOptionalFitsSystemWindows", new Class[0]);
                if (!$r4.isAccessible()) {
                    $r4.setAccessible(true);
                }
                $r4.invoke(view, new Object[0]);
            } catch (NoSuchMethodException e) {
                Log.d("ViewUtils", "Could not find method makeOptionalFitsSystemWindows. Oh well...");
            } catch (IllegalAccessException | InvocationTargetException $r6) {
                Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", $r6);
            }
        }
    }
}

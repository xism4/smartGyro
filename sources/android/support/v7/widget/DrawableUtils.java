package android.support.v7.widget;

import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build;
import com.org.v4.graphics.drawable.DrawableWrapper;

public class DrawableUtils {
    public static final Rect INSETS_NONE = new Rect();
    private static Class<?> sInsetsClazz = Class.forName("android.graphics.Insets");

    static {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
            } catch (ClassNotFoundException e) {
            }
        }
    }

    public static boolean canSafelyMutateDrawable(Drawable $r0) {
        Drawable $r02;
        if (Build.VERSION.SDK_INT < 15 && ($r0 instanceof InsetDrawable)) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 15 && ($r0 instanceof GradientDrawable)) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 17 && ($r0 instanceof LayerDrawable)) {
            return false;
        }
        if ($r0 instanceof DrawableContainer) {
            Drawable.ConstantState $r1 = $r0.getConstantState();
            if (!($r1 instanceof DrawableContainer.DrawableContainerState)) {
                return true;
            }
            for (Drawable $r03 : ((DrawableContainer.DrawableContainerState) $r1).getChildren()) {
                if (!canSafelyMutateDrawable($r03)) {
                    return false;
                }
            }
            return true;
        }
        if ($r0 instanceof android.support.v4.graphics.drawable.Drawable) {
            $r02 = ((android.support.v4.graphics.drawable.Drawable) $r0).getWrappedDrawable();
        } else if ($r0 instanceof DrawableWrapper) {
            $r02 = ((DrawableWrapper) $r0).getWrappedDrawable();
        } else if (!($r0 instanceof ScaleDrawable)) {
            return true;
        } else {
            $r02 = ((ScaleDrawable) $r0).getDrawable();
        }
        return canSafelyMutateDrawable($r02);
    }

    static void fixDrawable(Drawable drawable) {
        if (Build.VERSION.SDK_INT == 21 && "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName())) {
            fixVectorDrawableTinting(drawable);
        }
    }

    private static void fixVectorDrawableTinting(Drawable drawable) {
        int[] $r1 = drawable.getState();
        if ($r1 == null || $r1.length == 0) {
            drawable.setState(ThemeUtils.CHECKED_STATE_SET);
        } else {
            drawable.setState(ThemeUtils.EMPTY_STATE_SET);
        }
        drawable.setState($r1);
    }

    public static PorterDuff.Mode parseTintMode(int i, PorterDuff.Mode mode) {
        if (i == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }
}

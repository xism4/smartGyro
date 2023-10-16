package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.widget.CompoundButton;
import java.lang.reflect.Field;

public final class CompoundButtonCompat {
    private static Field sButtonDrawableField;
    private static boolean sButtonDrawableFieldFetched;

    public static Drawable getButtonDrawable(CompoundButton compoundButton) {
        if (Build.VERSION.SDK_INT >= 23) {
            return compoundButton.getButtonDrawable();
        }
        if (!sButtonDrawableFieldFetched) {
            try {
                sButtonDrawableField = CompoundButton.class.getDeclaredField("mButtonDrawable");
                sButtonDrawableField.setAccessible(true);
            } catch (NoSuchFieldException $r4) {
                Log.i("CompoundButtonCompat", "Failed to retrieve mButtonDrawable field", $r4);
            }
            sButtonDrawableFieldFetched = true;
        }
        Field $r3 = sButtonDrawableField;
        if ($r3 == null) {
            return null;
        }
        try {
            return (Drawable) $r3.get(compoundButton);
        } catch (IllegalAccessException $r6) {
            Log.i("CompoundButtonCompat", "Failed to get button drawable via reflection", $r6);
            sButtonDrawableField = null;
            return null;
        }
    }

    public static void setButtonTintList(CompoundButton compoundButton, ColorStateList colorStateList) {
        if (Build.VERSION.SDK_INT >= 21) {
            compoundButton.setButtonTintList(colorStateList);
        } else if (compoundButton instanceof TintableCompoundButton) {
            ((TintableCompoundButton) compoundButton).setSupportButtonTintList(colorStateList);
        }
    }

    public static void setButtonTintMode(CompoundButton compoundButton, PorterDuff.Mode mode) {
        if (Build.VERSION.SDK_INT >= 21) {
            compoundButton.setButtonTintMode(mode);
        } else if (compoundButton instanceof TintableCompoundButton) {
            ((TintableCompoundButton) compoundButton).setSupportButtonTintMode(mode);
        }
    }
}

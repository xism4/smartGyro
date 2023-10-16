package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.org.android.asm.ColorUtils;

class ThemeUtils {
    static final int[] ACTIVATED_STATE_SET = {16843518};
    static final int[] CHECKED_STATE_SET = {16842912};
    static final int[] DISABLED_STATE_SET = {-16842910};
    static final int[] EMPTY_STATE_SET = new int[0];
    static final int[] FOCUSED_STATE_SET = {16842908};
    static final int[] NOT_PRESSED_OR_FOCUSED_STATE_SET = {-16842919, -16842908};
    static final int[] PRESSED_STATE_SET = {16842919};
    static final int[] SELECTED_STATE_SET = {16842913};
    private static final int[] TEMP_ARRAY = new int[1];
    private static final ThreadLocal<TypedValue> TL_TYPED_VALUE = new ThreadLocal();

    public static int getDisabledThemeAttrColor(Context context, int $i0) {
        ColorStateList $r1 = getThemeAttrColorStateList(context, $i0);
        if ($r1 != null && $r1.isStateful()) {
            return $r1.getColorForState(DISABLED_STATE_SET, $r1.getDefaultColor());
        }
        TypedValue $r3 = getTypedValue();
        context.getTheme().resolveAttribute(16842803, $r3, true);
        return getThemeAttrColor(context, $i0, $r3.getFloat());
    }

    public static int getThemeAttrColor(Context context, int i) {
        int[] $r0 = TEMP_ARRAY;
        $r0[0] = i;
        TintTypedArray $r2 = TintTypedArray.obtainStyledAttributes(context, (AttributeSet) null, $r0);
        try {
            return $r2.getColor(0, 0);
        } finally {
            $r2.recycle();
        }
    }

    static int getThemeAttrColor(Context context, int i, float f) {
        int $i0 = getThemeAttrColor(context, i);
        return ColorUtils.setAlphaComponent($i0, Math.round(((float) Color.alpha($i0)) * f));
    }

    public static ColorStateList getThemeAttrColorStateList(Context context, int i) {
        int[] $r0 = TEMP_ARRAY;
        $r0[0] = i;
        TintTypedArray $r2 = TintTypedArray.obtainStyledAttributes(context, (AttributeSet) null, $r0);
        try {
            return $r2.getColorStateList(0);
        } finally {
            $r2.recycle();
        }
    }

    private static TypedValue getTypedValue() {
        TypedValue $r2 = TL_TYPED_VALUE.get();
        if ($r2 != null) {
            return $r2;
        }
        TypedValue $r22 = new TypedValue();
        TL_TYPED_VALUE.set($r22);
        return $r22;
    }
}

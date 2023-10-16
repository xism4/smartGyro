package com.org.android.asm;

import android.graphics.Color;

public final class ColorUtils {
    private static final ThreadLocal<double[]> TEMP_ARRAY = new ThreadLocal();

    private static int compositeAlpha(int i, int i2) {
        return 255 - (((255 - i2) * (255 - i)) / 255);
    }

    public static int compositeColors(int i, int i2) {
        int $i2 = Color.alpha(i2);
        int $i3 = Color.alpha(i);
        int $i4 = compositeAlpha($i3, $i2);
        return Color.argb($i4, compositeComponent(Color.red(i), $i3, Color.red(i2), $i2, $i4), compositeComponent(Color.green(i), $i3, Color.green(i2), $i2, $i4), compositeComponent(Color.blue(i), $i3, Color.blue(i2), $i2, $i4));
    }

    private static int compositeComponent(int i, int i2, int i3, int i4, int i5) {
        if (i5 == 0) {
            return 0;
        }
        return (((i * 255) * i2) + ((i3 * i4) * (255 - i2))) / (i5 * 255);
    }

    public static int setAlphaComponent(int i, int i2) {
        if (i2 >= 0 && i2 <= 255) {
            return (i & 16777215) | (i2 << 24);
        }
        throw new IllegalArgumentException("alpha must be between 0 and 255.");
    }
}

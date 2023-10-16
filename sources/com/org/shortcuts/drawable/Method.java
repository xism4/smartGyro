package com.org.shortcuts.drawable;

import android.animation.TypeEvaluator;

public class Method implements TypeEvaluator {
    private static final Method method = new Method();

    public static Method getMethod() {
        return method;
    }

    public Object evaluate(float $f0, Object obj, Object obj2) {
        int $i0 = ((Integer) obj).intValue();
        float $f1 = ((float) (($i0 >> 24) & 255)) / 255.0f;
        float $f5 = ((float) ($i0 & 255)) / 255.0f;
        int $i02 = ((Integer) obj2).intValue();
        float $f4 = (float) Math.pow((double) (((float) (($i0 >> 16) & 255)) / 255.0f), 2.2d);
        float $f3 = (float) Math.pow((double) (((float) (($i0 >> 8) & 255)) / 255.0f), 2.2d);
        float $f2 = (float) Math.pow((double) $f5, 2.2d);
        float $f10 = (float) Math.pow((double) (((float) (($i02 >> 16) & 255)) / 255.0f), 2.2d);
        float $f7 = (float) Math.pow((double) (((float) (($i02 >> 8) & 255)) / 255.0f), 2.2d);
        float $f6 = (((float) (($i02 >> 24) & 255)) / 255.0f) - $f1;
        float f = $f6;
        float $f62 = $f6 * $f0;
        float f2 = $f62;
        float $f12 = $f1 + $f62;
        float $f63 = ($f10 - $f4) * $f0;
        float f3 = $f63;
        float $f42 = $f4 + $f63;
        float $f64 = ($f7 - $f3) * $f0;
        float f4 = $f64;
        float $f32 = $f3 + $f64;
        float $f02 = $f0 * (((float) Math.pow((double) (((float) ($i02 & 255)) / 255.0f), 2.2d)) - $f2);
        float f5 = $f02;
        float $f22 = $f2 + $f02;
        float $f03 = $f12 * 255.0f;
        return Integer.valueOf((Math.round(((float) Math.pow((double) $f42, 0.45454545454545453d)) * 255.0f) << 16) | (Math.round($f03) << 24) | (Math.round(((float) Math.pow((double) $f32, 0.45454545454545453d)) * 255.0f) << 8) | Math.round(((float) Math.pow((double) $f22, 0.45454545454545453d)) * 255.0f));
    }
}

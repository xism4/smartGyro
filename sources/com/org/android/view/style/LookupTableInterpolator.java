package com.org.android.view.style;

import android.view.animation.Interpolator;

abstract class LookupTableInterpolator implements Interpolator {
    private final float mStepSize = (1.0f / ((float) (this.mValues.length - 1)));
    private final float[] mValues;

    protected LookupTableInterpolator(float[] fArr) {
        this.mValues = fArr;
    }

    public float getInterpolation(float f) {
        if (f >= 1.0f) {
            return 1.0f;
        }
        if (f <= 0.0f) {
            return 0.0f;
        }
        float[] $r1 = this.mValues;
        int $i0 = Math.min((int) (((float) ($r1.length - 1)) * f), $r1.length - 2);
        float $f1 = this.mStepSize;
        float $f12 = (f - (((float) $i0) * $f1)) / $f1;
        float[] $r12 = this.mValues;
        return $r12[$i0] + ($f12 * ($r12[$i0 + 1] - $r12[$i0]));
    }
}

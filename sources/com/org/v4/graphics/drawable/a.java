package com.org.v4.graphics.drawable;

import android.animation.TimeInterpolator;
import android.graphics.drawable.AnimationDrawable;

class a implements TimeInterpolator {
    private int n;
    private int[] o;
    private int p;

    a(AnimationDrawable animationDrawable, boolean z) {
        a(animationDrawable, z);
    }

    /* access modifiers changed from: package-private */
    public int a(AnimationDrawable animationDrawable, boolean z) {
        int $i0 = animationDrawable.getNumberOfFrames();
        this.n = $i0;
        int[] $r2 = this.o;
        if ($r2 == null || $r2.length < $i0) {
            this.o = new int[$i0];
        }
        int[] $r22 = this.o;
        int $i2 = 0;
        for (int $i1 = 0; $i1 < $i0; $i1++) {
            int $i3 = animationDrawable.getDuration(z ? ($i0 - $i1) - 1 : $i1);
            $r22[$i1] = $i3;
            $i2 += $i3;
        }
        this.p = $i2;
        return $i2;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.p;
    }

    public float getInterpolation(float f) {
        int $i1 = (int) ((f * ((float) this.p)) + 0.5f);
        int $i0 = this.n;
        int[] $r1 = this.o;
        int $i2 = 0;
        while ($i2 < $i0 && $i1 >= $r1[$i2]) {
            $i1 -= $r1[$i2];
            $i2++;
        }
        return (((float) $i2) / ((float) $i0)) + ($i2 < $i0 ? ((float) $i1) / ((float) this.p) : 0.0f);
    }
}

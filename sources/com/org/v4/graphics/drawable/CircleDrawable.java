package com.org.v4.graphics.drawable;

import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;

class CircleDrawable extends RippleDrawable {
    private final ObjectAnimator mAnimator;
    private final boolean mVisible;

    CircleDrawable(AnimationDrawable animationDrawable, boolean z, boolean z2) {
        super();
        int $i0 = animationDrawable.getNumberOfFrames();
        int $i1 = z ? $i0 - 1 : 0;
        int $i02 = z ? 0 : $i0 - 1;
        a $r1 = new a(animationDrawable, z);
        ObjectAnimator $r4 = ObjectAnimator.ofInt(animationDrawable, "currentIndex", new int[]{$i1, $i02});
        if (Build.VERSION.SDK_INT >= 18) {
            $r4.setAutoCancel(true);
        }
        $r4.setDuration((long) $r1.b());
        $r4.setInterpolator($r1);
        this.mVisible = z2;
        this.mAnimator = $r4;
    }

    public boolean draw() {
        return this.mVisible;
    }

    public void setColor() {
        this.mAnimator.reverse();
    }

    public void start() {
        this.mAnimator.start();
    }

    public void stopAnimation() {
        this.mAnimator.cancel();
    }
}

package com.org.v4.graphics.drawable;

import android.graphics.drawable.Animatable;

class ProgressBar extends RippleDrawable {
    private final Animatable mAnimation;

    ProgressBar(Animatable animatable) {
        super();
        this.mAnimation = animatable;
    }

    public void start() {
        this.mAnimation.start();
    }

    public void stopAnimation() {
        this.mAnimation.stop();
    }
}

package com.org.v4.graphics.drawable;

import com.org.shortcuts.drawable.AnimatedVectorDrawableCompat;

class Main extends RippleDrawable {
    private final AnimatedVectorDrawableCompat this$0;

    Main(AnimatedVectorDrawableCompat animatedVectorDrawableCompat) {
        super();
        this.this$0 = animatedVectorDrawableCompat;
    }

    public void start() {
        this.this$0.start();
    }

    public void stopAnimation() {
        this.this$0.stop();
    }
}

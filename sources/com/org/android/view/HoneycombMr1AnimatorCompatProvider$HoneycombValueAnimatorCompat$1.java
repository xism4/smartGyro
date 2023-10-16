package com.org.android.view;

import android.animation.ValueAnimator;
import android.view.View;

class HoneycombMr1AnimatorCompatProvider$HoneycombValueAnimatorCompat$1 implements ValueAnimator.AnimatorUpdateListener {
    final /* synthetic */ View this$0;
    final /* synthetic */ ViewPropertyAnimatorCompat val$animation;
    final /* synthetic */ ViewPropertyAnimatorUpdateListener val$animatorUpdateListener;

    HoneycombMr1AnimatorCompatProvider$HoneycombValueAnimatorCompat$1(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener, View view) {
        this.val$animation = viewPropertyAnimatorCompat;
        this.val$animatorUpdateListener = viewPropertyAnimatorUpdateListener;
        this.this$0 = view;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.val$animatorUpdateListener.onAnimationUpdate(this.this$0);
    }
}

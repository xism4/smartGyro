package com.org.android.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

class ValueAnimatorCompatImplHoneycombMr1$2 extends AnimatorListenerAdapter {
    final /* synthetic */ ViewPropertyAnimatorCompat this$0;
    final /* synthetic */ ViewPropertyAnimatorListener val$listener;
    final /* synthetic */ View val$view;

    ValueAnimatorCompatImplHoneycombMr1$2(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, ViewPropertyAnimatorListener viewPropertyAnimatorListener, View view) {
        this.this$0 = viewPropertyAnimatorCompat;
        this.val$listener = viewPropertyAnimatorListener;
        this.val$view = view;
    }

    public void onAnimationCancel(Animator animator) {
        this.val$listener.onAnimationCancel(this.val$view);
    }

    public void onAnimationEnd(Animator animator) {
        this.val$listener.onAnimationEnd(this.val$view);
    }

    public void onAnimationStart(Animator animator) {
        this.val$listener.onAnimationStart(this.val$view);
    }
}

package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class ValueAnimatorCompatImplHoneycombMr1$2 extends AnimatorListenerAdapter {
    final /* synthetic */ ActionBarOverlayLayout this$0;

    ValueAnimatorCompatImplHoneycombMr1$2(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.this$0 = actionBarOverlayLayout;
    }

    public void onAnimationCancel(Animator animator) {
        ActionBarOverlayLayout $r2 = this.this$0;
        $r2.mCurrentActionBarTopAnimator = null;
        $r2.mAnimatingForFling = false;
    }

    public void onAnimationEnd(Animator animator) {
        ActionBarOverlayLayout $r2 = this.this$0;
        $r2.mCurrentActionBarTopAnimator = null;
        $r2.mAnimatingForFling = false;
    }
}

package android.support.p025v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: android.support.v7.widget.d */
class C0394d extends AnimatorListenerAdapter {

    /* renamed from: a */
    final /* synthetic */ ActionBarOverlayLayout f1514a;

    C0394d(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.f1514a = actionBarOverlayLayout;
    }

    public void onAnimationCancel(Animator animator) {
        ActionBarOverlayLayout actionBarOverlayLayout = this.f1514a;
        actionBarOverlayLayout.f1148x = null;
        actionBarOverlayLayout.f1136l = false;
    }

    public void onAnimationEnd(Animator animator) {
        ActionBarOverlayLayout actionBarOverlayLayout = this.f1514a;
        actionBarOverlayLayout.f1148x = null;
        actionBarOverlayLayout.f1136l = false;
    }
}

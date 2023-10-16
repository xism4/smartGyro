package p000a.p001a.p005c.p014g;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* renamed from: a.a.c.g.x */
class C0132x extends AnimatorListenerAdapter {

    /* renamed from: a */
    final /* synthetic */ C0092A f288a;

    /* renamed from: b */
    final /* synthetic */ View f289b;

    /* renamed from: c */
    final /* synthetic */ C0134z f290c;

    C0132x(C0134z zVar, C0092A a, View view) {
        this.f290c = zVar;
        this.f288a = a;
        this.f289b = view;
    }

    public void onAnimationCancel(Animator animator) {
        this.f288a.mo389a(this.f289b);
    }

    public void onAnimationEnd(Animator animator) {
        this.f288a.mo390b(this.f289b);
    }

    public void onAnimationStart(Animator animator) {
        this.f288a.mo391c(this.f289b);
    }
}

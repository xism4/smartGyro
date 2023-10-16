package p000a.p001a.p005c.p014g;

import android.animation.ValueAnimator;
import android.view.View;

/* renamed from: a.a.c.g.y */
class C0133y implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a */
    final /* synthetic */ C0094C f291a;

    /* renamed from: b */
    final /* synthetic */ View f292b;

    /* renamed from: c */
    final /* synthetic */ C0134z f293c;

    C0133y(C0134z zVar, C0094C c, View view) {
        this.f293c = zVar;
        this.f291a = c;
        this.f292b = view;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f291a.mo392a(this.f292b);
    }
}

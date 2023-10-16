package android.support.p025v7.view.menu;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: android.support.v7.view.menu.f */
class C0285f implements View.OnAttachStateChangeListener {

    /* renamed from: a */
    final /* synthetic */ C0288i f941a;

    C0285f(C0288i iVar) {
        this.f941a = iVar;
    }

    public void onViewAttachedToWindow(View view) {
    }

    public void onViewDetachedFromWindow(View view) {
        ViewTreeObserver viewTreeObserver = this.f941a.f973z;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.f941a.f973z = view.getViewTreeObserver();
            }
            C0288i iVar = this.f941a;
            iVar.f973z.removeGlobalOnLayoutListener(iVar.f958k);
        }
        view.removeOnAttachStateChangeListener(this);
    }
}

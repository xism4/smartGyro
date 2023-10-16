package p000a.p001a.p003b.p004a;

import android.graphics.drawable.Drawable;

/* renamed from: a.a.b.a.c */
class C0005c implements Drawable.Callback {

    /* renamed from: a */
    final /* synthetic */ C0006d f12a;

    C0005c(C0006d dVar) {
        this.f12a = dVar;
    }

    public void invalidateDrawable(Drawable drawable) {
        this.f12a.invalidateSelf();
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        this.f12a.scheduleSelf(runnable, j);
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        this.f12a.unscheduleSelf(runnable);
    }
}

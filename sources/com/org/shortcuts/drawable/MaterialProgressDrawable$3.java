package com.org.shortcuts.drawable;

import android.graphics.drawable.Drawable;

class MaterialProgressDrawable$3 implements Drawable.Callback {
    final /* synthetic */ AnimatedVectorDrawableCompat this$0;

    MaterialProgressDrawable$3(AnimatedVectorDrawableCompat animatedVectorDrawableCompat) {
        this.this$0 = animatedVectorDrawableCompat;
    }

    public void invalidateDrawable(Drawable drawable) {
        this.this$0.invalidateSelf();
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        this.this$0.scheduleSelf(runnable, j);
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        this.this$0.unscheduleSelf(runnable);
    }
}

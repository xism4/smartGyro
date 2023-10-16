package com.org.v4.graphics.drawable;

import android.graphics.drawable.Drawable;

class MaterialProgressDrawable$3 implements Drawable.Callback {
    private Drawable.Callback value;

    MaterialProgressDrawable$3() {
    }

    public Drawable.Callback apply() {
        Drawable.Callback r1 = this.value;
        this.value = null;
        return r1;
    }

    public void invalidateDrawable(Drawable drawable) {
    }

    public MaterialProgressDrawable$3 put(Drawable.Callback callback) {
        this.value = callback;
        return this;
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback $r3 = this.value;
        if ($r3 != null) {
            $r3.scheduleDrawable(drawable, runnable, j);
        }
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback $r3 = this.value;
        if ($r3 != null) {
            $r3.unscheduleDrawable(drawable, runnable);
        }
    }
}

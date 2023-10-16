package com.org.android.ui.asm;

import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;

public abstract class k {
    public final void a(int i, Handler $r2) {
        if ($r2 == null) {
            $r2 = new Handler(Looper.getMainLooper());
        }
        $r2.post(new Plot$a(this, i));
    }

    public abstract void a(Typeface typeface);

    public final void a(Typeface typeface, Handler $r3) {
        if ($r3 == null) {
            $r3 = new Handler(Looper.getMainLooper());
        }
        $r3.post(new a(this, typeface));
    }

    public abstract void setTitle(int i);
}

package com.org.android.ui.asm;

import android.graphics.Typeface;

class a implements Runnable {
    final /* synthetic */ Typeface c;
    final /* synthetic */ k d;

    a(k kVar, Typeface typeface) {
        this.d = kVar;
        this.c = typeface;
    }

    public void run() {
        this.d.a(this.c);
    }
}

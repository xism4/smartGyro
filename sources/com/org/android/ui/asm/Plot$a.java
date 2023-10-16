package com.org.android.ui.asm;

class Plot$a implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ k d;

    Plot$a(k kVar, int i) {
        this.d = kVar;
        this.a = i;
    }

    public void run() {
        this.d.setTitle(this.a);
    }
}

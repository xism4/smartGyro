package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.Ya */
class C0963Ya implements Runnable {

    /* renamed from: a */
    final /* synthetic */ ResizeLayout f2621a;

    C0963Ya(ResizeLayout resizeLayout) {
        this.f2621a = resizeLayout;
    }

    public void run() {
        this.f2621a.requestLayout();
        this.f2621a.invalidate();
    }
}

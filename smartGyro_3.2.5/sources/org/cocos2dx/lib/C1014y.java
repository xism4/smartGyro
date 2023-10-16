package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.y */
class C1014y implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2720a;

    C1014y(int i) {
        this.f2720a = i;
    }

    public void run() {
        Cocos2dxEditBoxHelper.openKeyboardOnUiThread(this.f2720a);
    }
}

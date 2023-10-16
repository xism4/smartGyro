package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.z */
class C1016z implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2723a;

    C1016z(int i) {
        this.f2723a = i;
    }

    public void run() {
        Cocos2dxEditBoxHelper.closeKeyboardOnUiThread(this.f2723a);
    }
}

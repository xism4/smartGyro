package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.Ta */
class C0953Ta implements Runnable {

    /* renamed from: a */
    final /* synthetic */ String f2591a;

    /* renamed from: b */
    final /* synthetic */ int f2592b;

    C0953Ta(String str, int i) {
        this.f2591a = str;
        this.f2592b = i;
    }

    public void run() {
        GameControllerAdapter.nativeControllerConnected(this.f2591a, this.f2592b);
    }
}

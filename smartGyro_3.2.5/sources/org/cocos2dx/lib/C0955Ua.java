package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.Ua */
class C0955Ua implements Runnable {

    /* renamed from: a */
    final /* synthetic */ String f2595a;

    /* renamed from: b */
    final /* synthetic */ int f2596b;

    C0955Ua(String str, int i) {
        this.f2595a = str;
        this.f2596b = i;
    }

    public void run() {
        GameControllerAdapter.nativeControllerDisconnected(this.f2595a, this.f2596b);
    }
}

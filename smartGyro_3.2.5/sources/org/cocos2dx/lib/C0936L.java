package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.L */
class C0936L implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2541a;

    /* renamed from: b */
    final /* synthetic */ C0942O f2542b;

    C0936L(C0942O o, int i) {
        this.f2542b = o;
        this.f2541a = i;
    }

    public void run() {
        Cocos2dxEngineDataManager.nativeOnChangeExpectedFps(this.f2541a);
    }
}

package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.M */
class C0938M implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2545a;

    /* renamed from: b */
    final /* synthetic */ C0942O f2546b;

    C0938M(C0942O o, int i) {
        this.f2546b = o;
        this.f2545a = i;
    }

    public void run() {
        Cocos2dxEngineDataManager.nativeOnChangeSpecialEffectLevel(this.f2545a);
    }
}

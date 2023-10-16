package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.J */
class C0932J implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2532a;

    /* renamed from: b */
    final /* synthetic */ int f2533b;

    /* renamed from: c */
    final /* synthetic */ C0942O f2534c;

    C0932J(C0942O o, int i, int i2) {
        this.f2534c = o;
        this.f2532a = i;
        this.f2533b = i2;
    }

    public void run() {
        Cocos2dxEngineDataManager.nativeOnChangeContinuousFrameLostConfig(this.f2532a, this.f2533b);
    }
}

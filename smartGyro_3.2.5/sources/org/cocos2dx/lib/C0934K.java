package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.K */
class C0934K implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2537a;

    /* renamed from: b */
    final /* synthetic */ float f2538b;

    /* renamed from: c */
    final /* synthetic */ C0942O f2539c;

    C0934K(C0942O o, int i, float f) {
        this.f2539c = o;
        this.f2537a = i;
        this.f2538b = f;
    }

    public void run() {
        Cocos2dxEngineDataManager.nativeOnChangeLowFpsConfig(this.f2537a, this.f2538b);
    }
}

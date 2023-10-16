package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.N */
class C0940N implements Runnable {

    /* renamed from: a */
    final /* synthetic */ boolean f2548a;

    /* renamed from: b */
    final /* synthetic */ C0942O f2549b;

    C0940N(C0942O o, boolean z) {
        this.f2549b = o;
        this.f2548a = z;
    }

    public void run() {
        Cocos2dxEngineDataManager.nativeOnChangeMuteEnabled(this.f2548a);
    }
}

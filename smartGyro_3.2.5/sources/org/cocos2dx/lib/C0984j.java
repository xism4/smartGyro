package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.j */
class C0984j implements Runnable {

    /* renamed from: a */
    final /* synthetic */ String f2665a;

    /* renamed from: b */
    final /* synthetic */ C0986k f2666b;

    C0984j(C0986k kVar, String str) {
        this.f2666b = kVar;
        this.f2665a = str;
    }

    public void run() {
        Cocos2dxDownloader cocos2dxDownloader = this.f2666b.f2669b;
        cocos2dxDownloader.nativeOnFinish(cocos2dxDownloader._id, this.f2666b.f2670c, 0, this.f2665a, (byte[]) null);
    }
}

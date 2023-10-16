package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.h */
class C0980h implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2655a;

    /* renamed from: b */
    final /* synthetic */ long f2656b;

    /* renamed from: c */
    final /* synthetic */ long f2657c;

    /* renamed from: d */
    final /* synthetic */ long f2658d;

    /* renamed from: e */
    final /* synthetic */ Cocos2dxDownloader f2659e;

    C0980h(Cocos2dxDownloader cocos2dxDownloader, int i, long j, long j2, long j3) {
        this.f2659e = cocos2dxDownloader;
        this.f2655a = i;
        this.f2656b = j;
        this.f2657c = j2;
        this.f2658d = j3;
    }

    public void run() {
        Cocos2dxDownloader cocos2dxDownloader = this.f2659e;
        cocos2dxDownloader.nativeOnProgress(cocos2dxDownloader._id, this.f2655a, this.f2656b, this.f2657c, this.f2658d);
    }
}

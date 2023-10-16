package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.i */
class C0982i implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2660a;

    /* renamed from: b */
    final /* synthetic */ int f2661b;

    /* renamed from: c */
    final /* synthetic */ String f2662c;

    /* renamed from: d */
    final /* synthetic */ byte[] f2663d;

    /* renamed from: e */
    final /* synthetic */ Cocos2dxDownloader f2664e;

    C0982i(Cocos2dxDownloader cocos2dxDownloader, int i, int i2, String str, byte[] bArr) {
        this.f2664e = cocos2dxDownloader;
        this.f2660a = i;
        this.f2661b = i2;
        this.f2662c = str;
        this.f2663d = bArr;
    }

    public void run() {
        Cocos2dxDownloader cocos2dxDownloader = this.f2664e;
        cocos2dxDownloader.nativeOnFinish(cocos2dxDownloader._id, this.f2660a, this.f2661b, this.f2662c, this.f2663d);
    }
}

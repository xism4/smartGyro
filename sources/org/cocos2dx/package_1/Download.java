package org.cocos2dx.package_1;

class Download implements Runnable {
    final /* synthetic */ byte[] name;
    final /* synthetic */ Cocos2dxDownloader path;
    final /* synthetic */ int progress;
    final /* synthetic */ int size;
    final /* synthetic */ String url;

    Download(Cocos2dxDownloader cocos2dxDownloader, int i, int i2, String str, byte[] bArr) {
        this.path = cocos2dxDownloader;
        this.size = i;
        this.progress = i2;
        this.url = str;
        this.name = bArr;
    }

    public void run() {
        Cocos2dxDownloader $r1 = this.path;
        $r1.nativeOnFinish($r1.events, this.size, this.progress, this.url, this.name);
    }
}

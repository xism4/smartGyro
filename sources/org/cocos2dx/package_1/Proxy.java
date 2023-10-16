package org.cocos2dx.package_1;

class Proxy implements Runnable {
    final /* synthetic */ boolean h;
    final /* synthetic */ DownloadsFragment$1 time;

    Proxy(DownloadsFragment$1 downloadsFragment$1, boolean z) {
        this.time = downloadsFragment$1;
        this.h = z;
    }

    public void run() {
        Cocos2dxEngineDataManager.nativeOnChangeMuteEnabled(this.h);
    }
}

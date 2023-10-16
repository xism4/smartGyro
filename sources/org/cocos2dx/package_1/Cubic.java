package org.cocos2dx.package_1;

class Cubic implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ DownloadsFragment$1 c;

    Cubic(DownloadsFragment$1 downloadsFragment$1, int i, int i2) {
        this.c = downloadsFragment$1;
        this.a = i;
        this.b = i2;
    }

    public void run() {
        Cocos2dxEngineDataManager.nativeOnChangeContinuousFrameLostConfig(this.a, this.b);
    }
}

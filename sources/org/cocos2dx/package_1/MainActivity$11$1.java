package org.cocos2dx.package_1;

class MainActivity$11$1 implements Runnable {
    final /* synthetic */ float mColor;
    final /* synthetic */ int mId;
    final /* synthetic */ DownloadsFragment$1 this$1;

    MainActivity$11$1(DownloadsFragment$1 downloadsFragment$1, int i, float f) {
        this.this$1 = downloadsFragment$1;
        this.mId = i;
        this.mColor = f;
    }

    public void run() {
        Cocos2dxEngineDataManager.nativeOnChangeLowFpsConfig(this.mId, this.mColor);
    }
}

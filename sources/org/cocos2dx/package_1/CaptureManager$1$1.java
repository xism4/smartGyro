package org.cocos2dx.package_1;

class CaptureManager$1$1 implements Runnable {
    final /* synthetic */ DownloadsFragment$1 this$1;
    final /* synthetic */ int val$result;

    CaptureManager$1$1(DownloadsFragment$1 downloadsFragment$1, int i) {
        this.this$1 = downloadsFragment$1;
        this.val$result = i;
    }

    public void run() {
        Cocos2dxEngineDataManager.nativeOnChangeExpectedFps(this.val$result);
    }
}

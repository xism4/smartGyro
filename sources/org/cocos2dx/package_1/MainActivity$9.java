package org.cocos2dx.package_1;

class MainActivity$9 implements Runnable {
    final /* synthetic */ DownloadsFragment$1 this$0;
    final /* synthetic */ int val$type;

    MainActivity$9(DownloadsFragment$1 downloadsFragment$1, int i) {
        this.this$0 = downloadsFragment$1;
        this.val$type = i;
    }

    public void run() {
        Cocos2dxEngineDataManager.nativeOnChangeSpecialEffectLevel(this.val$type);
    }
}

package org.cocos2dx.package_1;

class IonRequestBuilder$2$1 implements Runnable {
    final /* synthetic */ long t;
    final /* synthetic */ Cocos2dxDownloader this$1;
    final /* synthetic */ long time;
    final /* synthetic */ int val$progress;
    final /* synthetic */ long val$total;

    IonRequestBuilder$2$1(Cocos2dxDownloader cocos2dxDownloader, int i, long j, long j2, long j3) {
        this.this$1 = cocos2dxDownloader;
        this.val$progress = i;
        this.val$total = j;
        this.time = j2;
        this.t = j3;
    }

    public void run() {
        Cocos2dxDownloader $r1 = this.this$1;
        $r1.nativeOnProgress($r1.events, this.val$progress, this.val$total, this.time, this.t);
    }
}

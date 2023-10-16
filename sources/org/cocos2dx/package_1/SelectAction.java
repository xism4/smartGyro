package org.cocos2dx.package_1;

class SelectAction implements Runnable {
    final /* synthetic */ Channel c;
    final /* synthetic */ String t;

    SelectAction(Channel channel, String str) {
        this.c = channel;
        this.t = str;
    }

    public void run() {
        Cocos2dxDownloader $r1 = this.c.this$0;
        $r1.nativeOnFinish($r1.events, this.c.d, 0, this.t, (byte[]) null);
    }
}

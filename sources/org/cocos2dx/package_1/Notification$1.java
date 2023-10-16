package org.cocos2dx.package_1;

final class Notification$1 implements Runnable {
    final /* synthetic */ boolean service;
    final /* synthetic */ float this$0;
    final /* synthetic */ int val$callbackContext;
    final /* synthetic */ String val$message;
    final /* synthetic */ int val$result;

    Notification$1(String str, int i, int i2, float f, boolean z) {
        this.val$message = str;
        this.val$callbackContext = i;
        this.val$result = i2;
        this.this$0 = f;
        this.service = z;
    }

    public void run() {
        GameControllerAdapter.nativeControllerAxisEvent(this.val$message, this.val$callbackContext, this.val$result, this.this$0, this.service);
    }
}

package org.cocos2dx.package_1;

final class Client implements Runnable {
    final /* synthetic */ int endTime;
    final /* synthetic */ String key;

    Client(String str, int i) {
        this.key = str;
        this.endTime = i;
    }

    public void run() {
        GameControllerAdapter.nativeControllerDisconnected(this.key, this.endTime);
    }
}

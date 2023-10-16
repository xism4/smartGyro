package org.cocos2dx.package_1;

final class Relay implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ int b;

    Relay(String str, int i) {
        this.a = str;
        this.b = i;
    }

    public void run() {
        GameControllerAdapter.nativeControllerConnected(this.a, this.b);
    }
}

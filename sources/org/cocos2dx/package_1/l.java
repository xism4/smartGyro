package org.cocos2dx.package_1;

import java.util.concurrent.CountDownLatch;

class l implements Runnable {
    private CountDownLatch a;
    private final String b;
    private boolean[] c;
    private final int l;

    l(CountDownLatch countDownLatch, boolean[] zArr, int i, String str) {
        this.a = countDownLatch;
        this.c = zArr;
        this.l = i;
        this.b = str;
    }

    public void run() {
        this.c[0] = Cocos2dxWebViewHelper._shouldStartLoading(this.l, this.b);
        this.a.countDown();
    }
}

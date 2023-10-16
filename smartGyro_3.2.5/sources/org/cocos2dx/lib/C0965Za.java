package org.cocos2dx.lib;

import java.util.concurrent.CountDownLatch;

/* renamed from: org.cocos2dx.lib.Za */
class C0965Za implements Runnable {

    /* renamed from: a */
    private CountDownLatch f2623a;

    /* renamed from: b */
    private boolean[] f2624b;

    /* renamed from: c */
    private final int f2625c;

    /* renamed from: d */
    private final String f2626d;

    C0965Za(CountDownLatch countDownLatch, boolean[] zArr, int i, String str) {
        this.f2623a = countDownLatch;
        this.f2624b = zArr;
        this.f2625c = i;
        this.f2626d = str;
    }

    public void run() {
        this.f2624b[0] = Cocos2dxWebViewHelper._shouldStartLoading(this.f2625c, this.f2626d);
        this.f2623a.countDown();
    }
}

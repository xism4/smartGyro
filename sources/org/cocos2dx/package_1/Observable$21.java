package org.cocos2dx.package_1;

import java.util.concurrent.Callable;

final class Observable$21 implements Callable<Boolean> {
    final /* synthetic */ int val$time;

    Observable$21(int i) {
        this.val$time = i;
    }

    public Boolean call() {
        Cocos2dxWebView $r3 = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.val$time);
        return Boolean.valueOf($r3 != null && $r3.canGoBack());
    }
}

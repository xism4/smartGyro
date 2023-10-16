package org.cocos2dx.package_1;

import java.util.concurrent.Callable;

final class StreamClientImpl$4 implements Callable<Boolean> {
    final /* synthetic */ int val$request;

    StreamClientImpl$4(int i) {
        this.val$request = i;
    }

    public Boolean call() {
        Cocos2dxWebView $r3 = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.val$request);
        return Boolean.valueOf($r3 != null && $r3.canGoForward());
    }
}

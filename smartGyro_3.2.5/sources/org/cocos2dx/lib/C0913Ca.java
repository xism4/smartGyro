package org.cocos2dx.lib;

import java.util.concurrent.Callable;

/* renamed from: org.cocos2dx.lib.Ca */
class C0913Ca implements Callable<Boolean> {

    /* renamed from: a */
    final /* synthetic */ int f2488a;

    C0913Ca(int i) {
        this.f2488a = i;
    }

    public Boolean call() {
        Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f2488a);
        return Boolean.valueOf(cocos2dxWebView != null && cocos2dxWebView.canGoForward());
    }
}

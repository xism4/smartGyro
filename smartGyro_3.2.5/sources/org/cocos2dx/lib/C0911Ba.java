package org.cocos2dx.lib;

import java.util.concurrent.Callable;

/* renamed from: org.cocos2dx.lib.Ba */
class C0911Ba implements Callable<Boolean> {

    /* renamed from: a */
    final /* synthetic */ int f2484a;

    C0911Ba(int i) {
        this.f2484a = i;
    }

    public Boolean call() {
        Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f2484a);
        return Boolean.valueOf(cocos2dxWebView != null && cocos2dxWebView.canGoBack());
    }
}

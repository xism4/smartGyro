package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.Ea */
class C0923Ea implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2514a;

    C0923Ea(int i) {
        this.f2514a = i;
    }

    public void run() {
        Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f2514a);
        if (cocos2dxWebView != null) {
            cocos2dxWebView.goForward();
        }
    }
}

package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.Da */
class C0921Da implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2511a;

    C0921Da(int i) {
        this.f2511a = i;
    }

    public void run() {
        Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f2511a);
        if (cocos2dxWebView != null) {
            cocos2dxWebView.goBack();
        }
    }
}

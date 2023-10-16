package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.Fa */
class C0925Fa implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2520a;

    /* renamed from: b */
    final /* synthetic */ String f2521b;

    C0925Fa(int i, String str) {
        this.f2520a = i;
        this.f2521b = str;
    }

    public void run() {
        Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f2520a);
        if (cocos2dxWebView != null) {
            cocos2dxWebView.loadUrl("javascript:" + this.f2521b);
        }
    }
}

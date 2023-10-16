package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.Aa */
class C0909Aa implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2482a;

    C0909Aa(int i) {
        this.f2482a = i;
    }

    public void run() {
        Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f2482a);
        if (cocos2dxWebView != null) {
            cocos2dxWebView.reload();
        }
    }
}

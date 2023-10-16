package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.ya */
class C1015ya implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2721a;

    /* renamed from: b */
    final /* synthetic */ String f2722b;

    C1015ya(int i, String str) {
        this.f2721a = i;
        this.f2722b = str;
    }

    public void run() {
        Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f2721a);
        if (cocos2dxWebView != null) {
            cocos2dxWebView.loadUrl(this.f2722b);
        }
    }
}

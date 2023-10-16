package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.Ha */
class C0929Ha implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2527a;

    /* renamed from: b */
    final /* synthetic */ boolean f2528b;

    C0929Ha(int i, boolean z) {
        this.f2527a = i;
        this.f2528b = z;
    }

    public void run() {
        Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f2527a);
        if (cocos2dxWebView != null) {
            cocos2dxWebView.setScalesPageToFit(this.f2528b);
        }
    }
}

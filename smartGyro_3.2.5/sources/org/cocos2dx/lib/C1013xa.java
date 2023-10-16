package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.xa */
class C1013xa implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2717a;

    /* renamed from: b */
    final /* synthetic */ boolean f2718b;

    /* renamed from: c */
    final /* synthetic */ String f2719c;

    C1013xa(int i, boolean z, String str) {
        this.f2717a = i;
        this.f2718b = z;
        this.f2719c = str;
    }

    public void run() {
        Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f2717a);
        if (cocos2dxWebView != null) {
            cocos2dxWebView.getSettings().setCacheMode(this.f2718b ? 2 : -1);
            cocos2dxWebView.loadUrl(this.f2719c);
        }
    }
}

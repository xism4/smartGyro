package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.Ja */
class C0933Ja implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2535a;

    /* renamed from: b */
    final /* synthetic */ boolean f2536b;

    C0933Ja(int i, boolean z) {
        this.f2535a = i;
        this.f2536b = z;
    }

    public void run() {
        Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f2535a);
        if (cocos2dxWebView != null) {
            cocos2dxWebView.setVisibility(this.f2536b ? 0 : 8);
        }
    }
}

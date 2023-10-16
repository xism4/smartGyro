package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.Oa */
class C0943Oa implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2556a;

    /* renamed from: b */
    final /* synthetic */ String f2557b;

    C0943Oa(int i, String str) {
        this.f2556a = i;
        this.f2557b = str;
    }

    public void run() {
        Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f2556a);
        if (cocos2dxWebView != null) {
            cocos2dxWebView.setJavascriptInterfaceScheme(this.f2557b);
        }
    }
}

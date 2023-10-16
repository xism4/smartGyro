package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.za */
class C1017za implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2724a;

    C1017za(int i) {
        this.f2724a = i;
    }

    public void run() {
        Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f2724a);
        if (cocos2dxWebView != null) {
            cocos2dxWebView.stopLoading();
        }
    }
}

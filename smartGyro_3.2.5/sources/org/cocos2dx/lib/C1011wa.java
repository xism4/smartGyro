package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.wa */
class C1011wa implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2709a;

    /* renamed from: b */
    final /* synthetic */ String f2710b;

    /* renamed from: c */
    final /* synthetic */ String f2711c;

    C1011wa(int i, String str, String str2) {
        this.f2709a = i;
        this.f2710b = str;
        this.f2711c = str2;
    }

    public void run() {
        Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f2709a);
        if (cocos2dxWebView != null) {
            cocos2dxWebView.loadDataWithBaseURL(this.f2710b, this.f2711c, (String) null, (String) null, (String) null);
        }
    }
}

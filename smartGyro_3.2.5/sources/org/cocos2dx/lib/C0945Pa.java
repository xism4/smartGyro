package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.Pa */
class C0945Pa implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2562a;

    /* renamed from: b */
    final /* synthetic */ String f2563b;

    /* renamed from: c */
    final /* synthetic */ String f2564c;

    /* renamed from: d */
    final /* synthetic */ String f2565d;

    /* renamed from: e */
    final /* synthetic */ String f2566e;

    C0945Pa(int i, String str, String str2, String str3, String str4) {
        this.f2562a = i;
        this.f2563b = str;
        this.f2564c = str2;
        this.f2565d = str3;
        this.f2566e = str4;
    }

    public void run() {
        Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f2562a);
        if (cocos2dxWebView != null) {
            cocos2dxWebView.loadDataWithBaseURL(this.f2563b, this.f2564c, this.f2565d, this.f2566e, (String) null);
        }
    }
}

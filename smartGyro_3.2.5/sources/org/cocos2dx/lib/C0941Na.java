package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.Na */
class C0941Na implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2550a;

    /* renamed from: b */
    final /* synthetic */ int f2551b;

    /* renamed from: c */
    final /* synthetic */ int f2552c;

    /* renamed from: d */
    final /* synthetic */ int f2553d;

    /* renamed from: e */
    final /* synthetic */ int f2554e;

    C0941Na(int i, int i2, int i3, int i4, int i5) {
        this.f2550a = i;
        this.f2551b = i2;
        this.f2552c = i3;
        this.f2553d = i4;
        this.f2554e = i5;
    }

    public void run() {
        Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f2550a);
        if (cocos2dxWebView != null) {
            cocos2dxWebView.setWebViewRect(this.f2551b, this.f2552c, this.f2553d, this.f2554e);
        }
    }
}

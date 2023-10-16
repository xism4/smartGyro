package org.cocos2dx.lib;

import android.widget.FrameLayout;

/* renamed from: org.cocos2dx.lib.Ga */
class C0927Ga implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2524a;

    C0927Ga(int i) {
        this.f2524a = i;
    }

    public void run() {
        Cocos2dxWebView cocos2dxWebView = new Cocos2dxWebView(Cocos2dxWebViewHelper.sCocos2dxActivity, this.f2524a);
        Cocos2dxWebViewHelper.sLayout.addView(cocos2dxWebView, new FrameLayout.LayoutParams(-2, -2));
        Cocos2dxWebViewHelper.webViews.put(this.f2524a, cocos2dxWebView);
    }
}

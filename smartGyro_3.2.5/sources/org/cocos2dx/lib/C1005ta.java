package org.cocos2dx.lib;

import org.cocos2dx.lib.Cocos2dxWebView;

/* renamed from: org.cocos2dx.lib.ta */
class C1005ta implements Runnable {

    /* renamed from: a */
    final /* synthetic */ String f2697a;

    /* renamed from: b */
    final /* synthetic */ Cocos2dxWebView.C0919a f2698b;

    C1005ta(Cocos2dxWebView.C0919a aVar, String str) {
        this.f2698b = aVar;
        this.f2697a = str;
    }

    public void run() {
        Cocos2dxWebViewHelper._onJsCallback(Cocos2dxWebView.this.mViewTag, this.f2697a);
    }
}

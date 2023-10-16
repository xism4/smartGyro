package org.cocos2dx.lib;

import org.cocos2dx.lib.Cocos2dxWebView;

/* renamed from: org.cocos2dx.lib.ua */
class C1007ua implements Runnable {

    /* renamed from: a */
    final /* synthetic */ String f2701a;

    /* renamed from: b */
    final /* synthetic */ Cocos2dxWebView.C0919a f2702b;

    C1007ua(Cocos2dxWebView.C0919a aVar, String str) {
        this.f2702b = aVar;
        this.f2701a = str;
    }

    public void run() {
        Cocos2dxWebViewHelper._didFinishLoading(Cocos2dxWebView.this.mViewTag, this.f2701a);
    }
}

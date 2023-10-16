package org.cocos2dx.package_1;

import org.cocos2dx.package_1.Cocos2dxWebView;

class CordovaWebViewClient$1 implements Runnable {
    final /* synthetic */ Cocos2dxWebView.a this$0;
    final /* synthetic */ String val$u;

    CordovaWebViewClient$1(Cocos2dxWebView.a aVar, String str) {
        this.this$0 = aVar;
        this.val$u = str;
    }

    public void run() {
        Cocos2dxWebViewHelper._didFinishLoading(Cocos2dxWebView.this.mViewTag, this.val$u);
    }
}

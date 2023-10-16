package org.cocos2dx.package_1;

import org.cocos2dx.package_1.Cocos2dxWebView;

class Progress implements Runnable {
    final /* synthetic */ String x;
    final /* synthetic */ Cocos2dxWebView.a y;

    Progress(Cocos2dxWebView.a aVar, String str) {
        this.y = aVar;
        this.x = str;
    }

    public void run() {
        Cocos2dxWebViewHelper._onJsCallback(Cocos2dxWebView.this.mViewTag, this.x);
    }
}

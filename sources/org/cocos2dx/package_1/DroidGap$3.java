package org.cocos2dx.package_1;

import org.cocos2dx.package_1.Cocos2dxWebView;

class DroidGap$3 implements Runnable {
    final /* synthetic */ String val$description;
    final /* synthetic */ Cocos2dxWebView.a val$me;

    DroidGap$3(Cocos2dxWebView.a aVar, String str) {
        this.val$me = aVar;
        this.val$description = str;
    }

    public void run() {
        Cocos2dxWebViewHelper._didFailLoading(Cocos2dxWebView.this.mViewTag, this.val$description);
    }
}

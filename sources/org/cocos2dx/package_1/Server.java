package org.cocos2dx.package_1;

final class Server implements Runnable {
    final /* synthetic */ String title;
    final /* synthetic */ int version;

    Server(int i, String str) {
        this.version = i;
        this.title = str;
    }

    public void run() {
        Cocos2dxWebView $r3 = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.version);
        if ($r3 != null) {
            $r3.loadUrl("javascript:" + this.title);
        }
    }
}

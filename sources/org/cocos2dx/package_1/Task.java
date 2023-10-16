package org.cocos2dx.package_1;

final class Task implements Runnable {
    final /* synthetic */ int source;
    final /* synthetic */ String url;

    Task(int i, String str) {
        this.source = i;
        this.url = str;
    }

    public void run() {
        Cocos2dxWebView $r3 = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.source);
        if ($r3 != null) {
            $r3.loadUrl(this.url);
        }
    }
}

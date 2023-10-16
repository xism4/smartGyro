package org.cocos2dx.package_1;

final class ThreadHelper implements Runnable {
    final /* synthetic */ int time;

    ThreadHelper(int i) {
        this.time = i;
    }

    public void run() {
        Cocos2dxWebView $r3 = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.time);
        if ($r3 != null) {
            $r3.reload();
        }
    }
}

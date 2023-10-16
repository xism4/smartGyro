package org.cocos2dx.package_1;

final class PlayActivity$7 implements Runnable {
    final /* synthetic */ String t;
    final /* synthetic */ int time;

    PlayActivity$7(int i, String str) {
        this.time = i;
        this.t = str;
    }

    public void run() {
        Cocos2dxWebView $r3 = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.time);
        if ($r3 != null) {
            $r3.setJavascriptInterfaceScheme(this.t);
        }
    }
}

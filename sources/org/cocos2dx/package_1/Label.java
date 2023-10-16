package org.cocos2dx.package_1;

final class Label implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ boolean c;
    final /* synthetic */ int text;

    Label(int i, boolean z, String str) {
        this.text = i;
        this.c = z;
        this.a = str;
    }

    public void run() {
        Cocos2dxWebView $r3 = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.text);
        if ($r3 != null) {
            $r3.getSettings().setCacheMode(this.c ? (byte) 2 : -1);
            $r3.loadUrl(this.a);
        }
    }
}

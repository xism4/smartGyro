package org.cocos2dx.package_1;

final class Message implements Runnable {
    final /* synthetic */ boolean c;
    final /* synthetic */ int id;

    Message(int i, boolean z) {
        this.id = i;
        this.c = z;
    }

    public void run() {
        Cocos2dxWebView $r3 = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.id);
        if ($r3 != null) {
            $r3.setVisibility(this.c ? (byte) 0 : 8);
        }
    }
}

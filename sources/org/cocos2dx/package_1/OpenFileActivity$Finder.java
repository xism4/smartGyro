package org.cocos2dx.package_1;

final class OpenFileActivity$Finder implements Runnable {
    final /* synthetic */ boolean dialog;
    final /* synthetic */ int parent;

    OpenFileActivity$Finder(int i, boolean z) {
        this.parent = i;
        this.dialog = z;
    }

    public void run() {
        Cocos2dxWebView $r3 = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.parent);
        if ($r3 != null) {
            $r3.setScalesPageToFit(this.dialog);
        }
    }
}

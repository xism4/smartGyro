package org.cocos2dx.package_1;

final class Model implements Runnable {
    final /* synthetic */ int mId;

    Model(int i) {
        this.mId = i;
    }

    public void run() {
        Cocos2dxWebView $r3 = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.mId);
        if ($r3 != null) {
            $r3.stopLoading();
        }
    }
}

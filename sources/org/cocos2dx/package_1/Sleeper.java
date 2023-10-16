package org.cocos2dx.package_1;

final class Sleeper implements Runnable {
    final /* synthetic */ int mSleepTime;

    Sleeper(int i) {
        this.mSleepTime = i;
    }

    public void run() {
        Cocos2dxWebView $r3 = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.mSleepTime);
        if ($r3 != null) {
            $r3.goBack();
        }
    }
}

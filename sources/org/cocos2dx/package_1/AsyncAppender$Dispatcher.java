package org.cocos2dx.package_1;

import android.widget.FrameLayout;

final class AsyncAppender$Dispatcher implements Runnable {
    final /* synthetic */ int parent;

    AsyncAppender$Dispatcher(int i) {
        this.parent = i;
    }

    public void run() {
        Cocos2dxWebView $r1 = new Cocos2dxWebView(Cocos2dxWebViewHelper.sCocos2dxActivity, this.parent);
        Cocos2dxWebViewHelper.sLayout.addView($r1, new FrameLayout.LayoutParams(-2, -2));
        Cocos2dxWebViewHelper.webViews.put(this.parent, $r1);
    }
}

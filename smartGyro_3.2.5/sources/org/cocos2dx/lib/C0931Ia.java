package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.Ia */
class C0931Ia implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2531a;

    C0931Ia(int i) {
        this.f2531a = i;
    }

    public void run() {
        Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f2531a);
        if (cocos2dxWebView != null) {
            Cocos2dxWebViewHelper.webViews.remove(this.f2531a);
            Cocos2dxWebViewHelper.sLayout.removeView(cocos2dxWebView);
        }
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= Cocos2dxWebViewHelper.webViews.size()) {
                break;
            } else if (Cocos2dxWebViewHelper.webViews.get(i) != null) {
                if (((Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(i)).getVisibility() == 0) {
                    z = true;
                    break;
                }
                i++;
            } else {
                return;
            }
        }
        if (!z) {
            Cocos2dxGLSurfaceView.getInstance().requestFocus();
        }
    }
}

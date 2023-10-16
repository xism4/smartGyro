package org.cocos2dx.package_1;

final class MainActivity$4 implements Runnable {
    final /* synthetic */ int a;

    MainActivity$4(int i) {
        this.a = i;
    }

    public void run() {
        Cocos2dxWebView $r3 = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.a);
        if ($r3 != null) {
            Cocos2dxWebViewHelper.webViews.remove(this.a);
            Cocos2dxWebViewHelper.sLayout.removeView($r3);
        }
        boolean $z0 = false;
        int $i0 = 0;
        while (true) {
            if ($i0 >= Cocos2dxWebViewHelper.webViews.size()) {
                break;
            } else if (Cocos2dxWebViewHelper.webViews.get($i0) != null) {
                if (((Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get($i0)).getVisibility() == 0) {
                    $z0 = true;
                    break;
                }
                $i0++;
            } else {
                return;
            }
        }
        if (!$z0) {
            Cocos2dxGLSurfaceView.getInstance().requestFocus();
        }
    }
}

package org.cocos2dx.lib;

import android.graphics.Paint;

/* renamed from: org.cocos2dx.lib.Ka */
class C0935Ka implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2540a;

    C0935Ka(int i) {
        this.f2540a = i;
    }

    public void run() {
        Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f2540a);
        if (cocos2dxWebView != null) {
            cocos2dxWebView.setBackgroundColor(0);
            try {
                cocos2dxWebView.getClass().getMethod("setLayerType", new Class[]{Integer.TYPE, Paint.class}).invoke(cocos2dxWebView, new Object[]{1, null});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

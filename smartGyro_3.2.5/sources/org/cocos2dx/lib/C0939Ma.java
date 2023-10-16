package org.cocos2dx.lib;

import java.util.concurrent.Callable;

/* renamed from: org.cocos2dx.lib.Ma */
class C0939Ma implements Callable<Float> {

    /* renamed from: a */
    final /* synthetic */ int f2547a;

    C0939Ma(int i) {
        this.f2547a = i;
    }

    public Float call() {
        Object obj;
        Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f2547a);
        if (cocos2dxWebView != null) {
            try {
                obj = cocos2dxWebView.getClass().getMethod("getAlpha", new Class[0]).invoke(cocos2dxWebView, new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return (Float) obj;
        }
        obj = null;
        return (Float) obj;
    }
}

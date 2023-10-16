package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.La */
class C0937La implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2543a;

    /* renamed from: b */
    final /* synthetic */ float f2544b;

    C0937La(int i, float f) {
        this.f2543a = i;
        this.f2544b = f;
    }

    public void run() {
        Cocos2dxWebView cocos2dxWebView = (Cocos2dxWebView) Cocos2dxWebViewHelper.webViews.get(this.f2543a);
        if (cocos2dxWebView != null) {
            try {
                cocos2dxWebView.getClass().getMethod("setAlpha", new Class[]{Float.TYPE}).invoke(cocos2dxWebView, new Object[]{Float.valueOf(this.f2544b)});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

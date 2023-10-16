package org.cocos2dx.lib;

import org.cocos2dx.lib.Cocos2dxWebView;

/* renamed from: org.cocos2dx.lib.va */
class C1009va implements Runnable {

    /* renamed from: a */
    final /* synthetic */ String f2705a;

    /* renamed from: b */
    final /* synthetic */ Cocos2dxWebView.C0919a f2706b;

    C1009va(Cocos2dxWebView.C0919a aVar, String str) {
        this.f2706b = aVar;
        this.f2705a = str;
    }

    public void run() {
        Cocos2dxWebViewHelper._didFailLoading(Cocos2dxWebView.this.mViewTag, this.f2705a);
    }
}

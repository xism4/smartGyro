package org.cocos2dx.lib;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

/* renamed from: org.cocos2dx.lib.sa */
class C1003sa implements View.OnKeyListener {

    /* renamed from: a */
    final /* synthetic */ Cocos2dxWebView f2694a;

    C1003sa(Cocos2dxWebView cocos2dxWebView) {
        this.f2694a = cocos2dxWebView;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i == 4) {
            Cocos2dxWebView.KeyDownBack();
            Log.d("setOnKeyListener", "按下了返回键");
            return true;
        }
        Log.d("setOnKeyListener", "按键");
        return false;
    }
}

package org.cocos2dx.package_1;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

class Settings$1 implements View.OnKeyListener {
    final /* synthetic */ Cocos2dxWebView val$p;

    Settings$1(Cocos2dxWebView cocos2dxWebView) {
        this.val$p = cocos2dxWebView;
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

package org.cocos2dx.lib;

import android.view.KeyEvent;
import android.view.View;

/* renamed from: org.cocos2dx.lib.r */
class C1000r implements View.OnKeyListener {

    /* renamed from: a */
    final /* synthetic */ Cocos2dxEditBox f2689a;

    /* renamed from: b */
    final /* synthetic */ C0908A f2690b;

    C1000r(C0908A a, Cocos2dxEditBox cocos2dxEditBox) {
        this.f2690b = a;
        this.f2689a = cocos2dxEditBox;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0 || i != 66 || (this.f2689a.getInputType() & 131072) == 131072) {
            return false;
        }
        Cocos2dxEditBoxHelper.closeKeyboardOnUiThread(this.f2690b.f2481f);
        return true;
    }
}

package org.cocos2dx.lib;

import android.view.KeyEvent;
import android.widget.TextView;

/* renamed from: org.cocos2dx.lib.s */
class C1002s implements TextView.OnEditorActionListener {

    /* renamed from: a */
    final /* synthetic */ Cocos2dxEditBox f2692a;

    /* renamed from: b */
    final /* synthetic */ C0908A f2693b;

    C1002s(C0908A a, Cocos2dxEditBox cocos2dxEditBox) {
        this.f2693b = a;
        this.f2692a = cocos2dxEditBox;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == 5) {
            this.f2692a.endAction = 1;
            Cocos2dxEditBoxHelper.closeKeyboardOnUiThread(this.f2693b.f2481f);
            return true;
        } else if (i != 6 && i != 4 && i != 3 && i != 2) {
            return false;
        } else {
            this.f2692a.endAction = 3;
            Cocos2dxEditBoxHelper.closeKeyboardOnUiThread(this.f2693b.f2481f);
            return false;
        }
    }
}

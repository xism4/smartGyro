package org.cocos2dx.lib;

import android.util.Log;

/* renamed from: org.cocos2dx.lib.B */
class C0910B implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2483a;

    C0910B(int i) {
        this.f2483a = i;
    }

    public void run() {
        Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f2483a);
        if (cocos2dxEditBox != null) {
            Cocos2dxEditBoxHelper.mEditBoxArray.remove(this.f2483a);
            Cocos2dxEditBoxHelper.mFrameLayout.removeView(cocos2dxEditBox);
            Log.e(Cocos2dxEditBoxHelper.TAG, "remove EditBox");
        }
    }
}

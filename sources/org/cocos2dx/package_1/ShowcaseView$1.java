package org.cocos2dx.package_1;

import android.util.Log;

final class ShowcaseView$1 implements Runnable {
    final /* synthetic */ int a;

    ShowcaseView$1(int i) {
        this.a = i;
    }

    public void run() {
        Cocos2dxEditBox $r3 = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.a);
        if ($r3 != null) {
            Cocos2dxEditBoxHelper.mEditBoxArray.remove(this.a);
            Cocos2dxEditBoxHelper.mFrameLayout.removeView($r3);
            Log.e(Cocos2dxEditBoxHelper.CLASS_NAME, "remove EditBox");
        }
    }
}

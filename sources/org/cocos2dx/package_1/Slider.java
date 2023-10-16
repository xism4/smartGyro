package org.cocos2dx.package_1;

final class Slider implements Runnable {
    final /* synthetic */ String h;
    final /* synthetic */ int y;

    Slider(int i, String str) {
        this.y = i;
        this.h = str;
    }

    public void run() {
        Cocos2dxEditBox $r3 = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.y);
        if ($r3 != null) {
            $r3.setHint(this.h);
        }
    }
}

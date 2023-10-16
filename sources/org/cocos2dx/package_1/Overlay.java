package org.cocos2dx.package_1;

final class Overlay implements Runnable {
    final /* synthetic */ boolean b;
    final /* synthetic */ int c;

    Overlay(int i, boolean z) {
        this.c = i;
        this.b = z;
    }

    public void run() {
        Cocos2dxEditBox $r3 = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.c);
        if ($r3 != null) {
            $r3.setVisibility(this.b ? (byte) 0 : 8);
        }
    }
}

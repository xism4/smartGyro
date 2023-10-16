package org.cocos2dx.package_1;

final class SweeperPool$Sweeper implements Runnable {
    final /* synthetic */ int service;
    final /* synthetic */ int t;

    SweeperPool$Sweeper(int i, int i2) {
        this.service = i;
        this.t = i2;
    }

    public void run() {
        Cocos2dxEditBox $r3 = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.service);
        if ($r3 != null) {
            $r3.setInputFlag(this.t);
        }
    }
}

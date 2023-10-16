package org.cocos2dx.package_1;

final class FileBrowser$11 implements Runnable {
    final /* synthetic */ int i;
    final /* synthetic */ int k;

    FileBrowser$11(int i2, int i3) {
        this.i = i2;
        this.k = i3;
    }

    public void run() {
        Cocos2dxEditBox $r3 = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.i);
        if ($r3 != null) {
            $r3.setTextHorizontalAlignment(this.k);
        }
    }
}

package org.cocos2dx.package_1;

final class RemoteAppenderStreamClient implements Runnable {
    final /* synthetic */ int c;
    final /* synthetic */ int id;

    RemoteAppenderStreamClient(int i, int i2) {
        this.c = i;
        this.id = i2;
    }

    public void run() {
        Cocos2dxEditBox $r3 = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.c);
        if ($r3 != null) {
            $r3.setReturnType(this.id);
        }
    }
}

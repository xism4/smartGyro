package org.cocos2dx.package_1;

final class AgendaListView$2 implements Runnable {
    final /* synthetic */ int b;
    final /* synthetic */ int c;

    AgendaListView$2(int i, int i2) {
        this.c = i;
        this.b = i2;
    }

    public void run() {
        Cocos2dxEditBox $r3 = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.c);
        if ($r3 != null) {
            $r3.setInputMode(this.b);
        }
    }
}

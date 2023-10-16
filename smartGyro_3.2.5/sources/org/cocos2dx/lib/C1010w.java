package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.w */
class C1010w implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2707a;

    /* renamed from: b */
    final /* synthetic */ int f2708b;

    C1010w(int i, int i2) {
        this.f2707a = i;
        this.f2708b = i2;
    }

    public void run() {
        Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f2707a);
        if (cocos2dxEditBox != null) {
            cocos2dxEditBox.setInputFlag(this.f2708b);
        }
    }
}

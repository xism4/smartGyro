package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.v */
class C1008v implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2703a;

    /* renamed from: b */
    final /* synthetic */ int f2704b;

    C1008v(int i, int i2) {
        this.f2703a = i;
        this.f2704b = i2;
    }

    public void run() {
        Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f2703a);
        if (cocos2dxEditBox != null) {
            cocos2dxEditBox.setInputMode(this.f2704b);
        }
    }
}

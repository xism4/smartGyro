package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.G */
class C0926G implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2522a;

    /* renamed from: b */
    final /* synthetic */ int f2523b;

    C0926G(int i, int i2) {
        this.f2522a = i;
        this.f2523b = i2;
    }

    public void run() {
        Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f2522a);
        if (cocos2dxEditBox != null) {
            cocos2dxEditBox.setMaxLength(this.f2523b);
        }
    }
}

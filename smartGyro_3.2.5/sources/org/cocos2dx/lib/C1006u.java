package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.u */
class C1006u implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2699a;

    /* renamed from: b */
    final /* synthetic */ int f2700b;

    C1006u(int i, int i2) {
        this.f2699a = i;
        this.f2700b = i2;
    }

    public void run() {
        Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f2699a);
        if (cocos2dxEditBox != null) {
            cocos2dxEditBox.setTextHorizontalAlignment(this.f2700b);
        }
    }
}

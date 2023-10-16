package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.t */
class C1004t implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2695a;

    /* renamed from: b */
    final /* synthetic */ int f2696b;

    C1004t(int i, int i2) {
        this.f2695a = i;
        this.f2696b = i2;
    }

    public void run() {
        Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f2695a);
        if (cocos2dxEditBox != null) {
            cocos2dxEditBox.setReturnType(this.f2696b);
        }
    }
}

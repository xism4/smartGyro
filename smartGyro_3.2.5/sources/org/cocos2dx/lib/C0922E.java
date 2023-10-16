package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.E */
class C0922E implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2512a;

    /* renamed from: b */
    final /* synthetic */ String f2513b;

    C0922E(int i, String str) {
        this.f2512a = i;
        this.f2513b = str;
    }

    public void run() {
        Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f2512a);
        if (cocos2dxEditBox != null) {
            cocos2dxEditBox.setHint(this.f2513b);
        }
    }
}

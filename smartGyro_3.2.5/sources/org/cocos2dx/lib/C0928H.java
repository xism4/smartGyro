package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.H */
class C0928H implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2525a;

    /* renamed from: b */
    final /* synthetic */ boolean f2526b;

    C0928H(int i, boolean z) {
        this.f2525a = i;
        this.f2526b = z;
    }

    public void run() {
        Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f2525a);
        if (cocos2dxEditBox != null) {
            cocos2dxEditBox.setVisibility(this.f2526b ? 0 : 8);
        }
    }
}

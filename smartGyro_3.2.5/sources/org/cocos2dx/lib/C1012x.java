package org.cocos2dx.lib;

/* renamed from: org.cocos2dx.lib.x */
class C1012x implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2712a;

    /* renamed from: b */
    final /* synthetic */ int f2713b;

    /* renamed from: c */
    final /* synthetic */ int f2714c;

    /* renamed from: d */
    final /* synthetic */ int f2715d;

    /* renamed from: e */
    final /* synthetic */ int f2716e;

    C1012x(int i, int i2, int i3, int i4, int i5) {
        this.f2712a = i;
        this.f2713b = i2;
        this.f2714c = i3;
        this.f2715d = i4;
        this.f2716e = i5;
    }

    public void run() {
        Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f2712a);
        if (cocos2dxEditBox != null) {
            cocos2dxEditBox.setEditBoxViewRect(this.f2713b, this.f2714c, this.f2715d, this.f2716e);
        }
    }
}

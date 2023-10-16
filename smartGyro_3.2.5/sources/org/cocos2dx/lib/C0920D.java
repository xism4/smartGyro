package org.cocos2dx.lib;

import android.graphics.Color;

/* renamed from: org.cocos2dx.lib.D */
class C0920D implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2506a;

    /* renamed from: b */
    final /* synthetic */ int f2507b;

    /* renamed from: c */
    final /* synthetic */ int f2508c;

    /* renamed from: d */
    final /* synthetic */ int f2509d;

    /* renamed from: e */
    final /* synthetic */ int f2510e;

    C0920D(int i, int i2, int i3, int i4, int i5) {
        this.f2506a = i;
        this.f2507b = i2;
        this.f2508c = i3;
        this.f2509d = i4;
        this.f2510e = i5;
    }

    public void run() {
        Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f2506a);
        if (cocos2dxEditBox != null) {
            cocos2dxEditBox.setTextColor(Color.argb(this.f2507b, this.f2508c, this.f2509d, this.f2510e));
        }
    }
}

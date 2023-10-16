package org.cocos2dx.lib;

import android.graphics.Color;

/* renamed from: org.cocos2dx.lib.F */
class C0924F implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2515a;

    /* renamed from: b */
    final /* synthetic */ int f2516b;

    /* renamed from: c */
    final /* synthetic */ int f2517c;

    /* renamed from: d */
    final /* synthetic */ int f2518d;

    /* renamed from: e */
    final /* synthetic */ int f2519e;

    C0924F(int i, int i2, int i3, int i4, int i5) {
        this.f2515a = i;
        this.f2516b = i2;
        this.f2517c = i3;
        this.f2518d = i4;
        this.f2519e = i5;
    }

    public void run() {
        Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f2515a);
        if (cocos2dxEditBox != null) {
            cocos2dxEditBox.setHintTextColor(Color.argb(this.f2516b, this.f2517c, this.f2518d, this.f2519e));
        }
    }
}

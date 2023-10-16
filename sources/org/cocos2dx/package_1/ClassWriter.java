package org.cocos2dx.package_1;

import android.graphics.Color;

final class ClassWriter implements Runnable {
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ int d;
    final /* synthetic */ int g;
    final /* synthetic */ int r;

    ClassWriter(int i, int i2, int i3, int i4, int i5) {
        this.c = i;
        this.r = i2;
        this.g = i3;
        this.b = i4;
        this.d = i5;
    }

    public void run() {
        Cocos2dxEditBox $r3 = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.c);
        if ($r3 != null) {
            $r3.setTextColor(Color.argb(this.r, this.g, this.b, this.d));
        }
    }
}

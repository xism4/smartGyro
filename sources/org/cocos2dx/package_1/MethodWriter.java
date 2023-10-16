package org.cocos2dx.package_1;

import android.graphics.Color;

final class MethodWriter implements Runnable {
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ int e;
    final /* synthetic */ int g;
    final /* synthetic */ int r;

    MethodWriter(int i, int i2, int i3, int i4, int i5) {
        this.c = i;
        this.r = i2;
        this.g = i3;
        this.e = i4;
        this.b = i5;
    }

    public void run() {
        Cocos2dxEditBox $r3 = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.c);
        if ($r3 != null) {
            $r3.setHintTextColor(Color.argb(this.r, this.g, this.e, this.b));
        }
    }
}

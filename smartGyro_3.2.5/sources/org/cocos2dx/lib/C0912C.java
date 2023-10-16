package org.cocos2dx.lib;

import android.graphics.Typeface;
import android.util.Log;

/* renamed from: org.cocos2dx.lib.C */
class C0912C implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f2485a;

    /* renamed from: b */
    final /* synthetic */ String f2486b;

    /* renamed from: c */
    final /* synthetic */ float f2487c;

    C0912C(int i, String str, float f) {
        this.f2485a = i;
        this.f2486b = str;
        this.f2487c = f;
    }

    public void run() {
        Typeface typeface;
        Cocos2dxEditBox cocos2dxEditBox = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.f2485a);
        if (cocos2dxEditBox != null) {
            if (!this.f2486b.isEmpty()) {
                if (this.f2486b.endsWith(".ttf")) {
                    try {
                        Cocos2dxActivity unused = Cocos2dxEditBoxHelper.mCocos2dxActivity;
                        typeface = Cocos2dxTypefaces.get(Cocos2dxActivity.getContext(), this.f2486b);
                    } catch (Exception unused2) {
                        Log.e("Cocos2dxEditBoxHelper", "error to create ttf type face: " + this.f2486b);
                    }
                }
                typeface = Typeface.create(this.f2486b, 0);
            } else {
                typeface = Typeface.DEFAULT;
            }
            float f = this.f2487c;
            if (f >= 0.0f) {
                cocos2dxEditBox.setTextSize(0, f);
            }
            cocos2dxEditBox.setTypeface(typeface);
        }
    }
}

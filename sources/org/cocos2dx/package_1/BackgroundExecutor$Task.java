package org.cocos2dx.package_1;

import android.graphics.Typeface;
import android.util.Log;

final class BackgroundExecutor$Task implements Runnable {
    final /* synthetic */ float context;
    final /* synthetic */ int id;
    final /* synthetic */ String text;

    BackgroundExecutor$Task(int i, String str, float f) {
        this.id = i;
        this.text = str;
        this.context = f;
    }

    public void run() {
        Typeface $r7;
        Cocos2dxEditBox $r4 = (Cocos2dxEditBox) Cocos2dxEditBoxHelper.mEditBoxArray.get(this.id);
        if ($r4 != null) {
            if (!this.text.isEmpty()) {
                if (this.text.endsWith(".ttf")) {
                    try {
                        Cocos2dxActivity unused = Cocos2dxEditBoxHelper.mCocos2dxActivity;
                        $r7 = Cocos2dxTypefaces.get(Cocos2dxActivity.getContext(), this.text);
                    } catch (Exception e) {
                        Log.e("Cocos2dxEditBoxHelper", "error to create ttf type face: " + this.text);
                    }
                }
                $r7 = Typeface.create(this.text, 0);
            } else {
                $r7 = Typeface.DEFAULT;
            }
            float $f0 = this.context;
            if ($f0 >= 0.0f) {
                $r4.setTextSize(0, $f0);
            }
            $r4.setTypeface($r7);
        }
    }
}

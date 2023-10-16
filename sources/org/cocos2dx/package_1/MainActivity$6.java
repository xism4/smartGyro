package org.cocos2dx.package_1;

import android.view.KeyEvent;
import android.view.View;

class MainActivity$6 implements View.OnKeyListener {
    final /* synthetic */ Cocos2dxEditBox mText;
    final /* synthetic */ Stream this$0;

    MainActivity$6(Stream stream, Cocos2dxEditBox cocos2dxEditBox) {
        this.this$0 = stream;
        this.mText = cocos2dxEditBox;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0 || i != 66 || (this.mText.getInputType() & 131072) == 131072) {
            return false;
        }
        Cocos2dxEditBoxHelper.closeKeyboardOnUiThread(this.this$0.this$0);
        return true;
    }
}

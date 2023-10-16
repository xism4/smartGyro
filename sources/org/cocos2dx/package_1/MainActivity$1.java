package org.cocos2dx.package_1;

import android.view.KeyEvent;
import android.widget.TextView;

class MainActivity$1 implements TextView.OnEditorActionListener {
    final /* synthetic */ Stream this$0;
    final /* synthetic */ Cocos2dxEditBox val$dialog;

    MainActivity$1(Stream stream, Cocos2dxEditBox cocos2dxEditBox) {
        this.this$0 = stream;
        this.val$dialog = cocos2dxEditBox;
    }

    public boolean onEditorAction(TextView textView, int $i0, KeyEvent keyEvent) {
        if ($i0 == 5) {
            this.val$dialog.endAction = 1;
            Cocos2dxEditBoxHelper.closeKeyboardOnUiThread(this.this$0.this$0);
            return true;
        } else if ($i0 != 6 && $i0 != 4 && $i0 != 3 && $i0 != 2) {
            return false;
        } else {
            this.val$dialog.endAction = 3;
            Cocos2dxEditBoxHelper.closeKeyboardOnUiThread(this.this$0.this$0);
            return false;
        }
    }
}

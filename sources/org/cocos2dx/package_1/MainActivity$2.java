package org.cocos2dx.package_1;

import android.text.Editable;
import android.text.TextWatcher;

class MainActivity$2 implements TextWatcher {
    final /* synthetic */ Stream this$0;
    final /* synthetic */ Cocos2dxEditBox val$editText;

    MainActivity$2(Stream stream, Cocos2dxEditBox cocos2dxEditBox) {
        this.this$0 = stream;
        this.val$editText = cocos2dxEditBox;
    }

    public void afterTextChanged(Editable editable) {
        if (!this.val$editText.getChangedTextProgrammatically().booleanValue() && ((Boolean) this.val$editText.getTag()).booleanValue()) {
            Cocos2dxEditBoxHelper.mCocos2dxActivity.runOnGLThread(new SearchResultDrawingAsyncTask$2(this, editable));
        }
        this.val$editText.setChangedTextProgrammatically(false);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}

package org.cocos2dx.package_1;

import android.text.Editable;

class SearchResultDrawingAsyncTask$2 implements Runnable {
    final /* synthetic */ MainActivity$2 this$0;
    final /* synthetic */ Editable val$result;

    SearchResultDrawingAsyncTask$2(MainActivity$2 mainActivity$2, Editable editable) {
        this.this$0 = mainActivity$2;
        this.val$result = editable;
    }

    public void run() {
        Cocos2dxEditBoxHelper.__editBoxEditingChanged(this.this$0.this$0.this$0, this.val$result.toString());
    }
}

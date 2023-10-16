package org.cocos2dx.package_1;

class MainActivity$3 implements Runnable {
    final /* synthetic */ Folder this$0;
    final /* synthetic */ String val$title;

    MainActivity$3(Folder folder, String str) {
        this.this$0 = folder;
        this.val$title = str;
    }

    public void run() {
        Folder $r2 = this.this$0;
        Cocos2dxEditBoxHelper.__editBoxEditingDidEnd($r2.title.this$0, this.val$title, $r2.text.endAction);
    }
}

package org.cocos2dx.package_1;

class MonthByWeekFragment$2 implements Runnable {
    final /* synthetic */ Folder this$0;

    MonthByWeekFragment$2(Folder folder) {
        this.this$0 = folder;
    }

    public void run() {
        Folder $r2 = this.this$0;
        $r2.text.endAction = 0;
        Cocos2dxEditBoxHelper.__editBoxEditingDidBegin($r2.title.this$0);
    }
}

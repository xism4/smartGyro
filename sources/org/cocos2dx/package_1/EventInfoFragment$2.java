package org.cocos2dx.package_1;

class EventInfoFragment$2 implements Runnable {
    final /* synthetic */ ResizeLayout this$0;

    EventInfoFragment$2(ResizeLayout resizeLayout) {
        this.this$0 = resizeLayout;
    }

    public void run() {
        this.this$0.requestLayout();
        this.this$0.invalidate();
    }
}

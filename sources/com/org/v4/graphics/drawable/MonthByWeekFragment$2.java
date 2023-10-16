package com.org.v4.graphics.drawable;

class MonthByWeekFragment$2 implements Runnable {
    final /* synthetic */ DrawableContainer this$0;

    MonthByWeekFragment$2(DrawableContainer drawableContainer) {
        this.this$0 = drawableContainer;
    }

    public void run() {
        this.this$0.animate(true);
        this.this$0.invalidateSelf();
    }
}

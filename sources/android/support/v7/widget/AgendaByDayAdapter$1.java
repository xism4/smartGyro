package android.support.v7.widget;

class AgendaByDayAdapter$1 implements Runnable {
    final /* synthetic */ ActionBarOverlayLayout this$0;

    AgendaByDayAdapter$1(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.this$0 = actionBarOverlayLayout;
    }

    public void run() {
        this.this$0.haltActionBarHideOffsetAnimations();
        ActionBarOverlayLayout $r1 = this.this$0;
        $r1.mCurrentActionBarTopAnimator = $r1.mActionBarTop.animate().translationY((float) (-this.this$0.mActionBarTop.getHeight())).setListener(this.this$0.listener);
    }
}

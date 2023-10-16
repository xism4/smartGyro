package android.support.v7.widget;

class AllInOneActivity$3 implements Runnable {
    final /* synthetic */ ActionBarOverlayLayout this$0;

    AllInOneActivity$3(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.this$0 = actionBarOverlayLayout;
    }

    public void run() {
        this.this$0.haltActionBarHideOffsetAnimations();
        ActionBarOverlayLayout $r1 = this.this$0;
        $r1.mCurrentActionBarTopAnimator = $r1.mActionBarTop.animate().translationY(0.0f).setListener(this.this$0.listener);
    }
}

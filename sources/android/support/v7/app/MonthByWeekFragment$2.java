package android.support.v7.app;

class MonthByWeekFragment$2 implements Runnable {
    final /* synthetic */ AppCompatDelegateImplV7 this$0;

    MonthByWeekFragment$2(AppCompatDelegateImplV7 appCompatDelegateImplV7) {
        this.this$0 = appCompatDelegateImplV7;
    }

    public void run() {
        AppCompatDelegateImplV7 $r1 = this.this$0;
        if (($r1.mInvalidatePanelMenuFeatures & 1) != 0) {
            $r1.doInvalidatePanelMenu(0);
        }
        AppCompatDelegateImplV7 $r12 = this.this$0;
        if (($r12.mInvalidatePanelMenuFeatures & 4096) != 0) {
            $r12.doInvalidatePanelMenu(108);
        }
        AppCompatDelegateImplV7 $r13 = this.this$0;
        $r13.mInvalidatePanelMenuPosted = false;
        $r13.mInvalidatePanelMenuFeatures = 0;
    }
}

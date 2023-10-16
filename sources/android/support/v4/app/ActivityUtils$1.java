package android.support.v4.app;

import android.app.Activity;
import android.content.pm.PackageManager;

final class ActivityUtils$1 implements Runnable {
    final /* synthetic */ String[] this$0;
    final /* synthetic */ Activity val$activity;
    final /* synthetic */ int val$titleId;

    ActivityUtils$1(String[] strArr, Activity activity, int i) {
        this.this$0 = strArr;
        this.val$activity = activity;
        this.val$titleId = i;
    }

    public void run() {
        int[] $r2 = new int[this.this$0.length];
        PackageManager $r4 = this.val$activity.getPackageManager();
        String $r5 = this.val$activity.getPackageName();
        int $i0 = this.this$0.length;
        for (int $i1 = 0; $i1 < $i0; $i1++) {
            $r2[$i1] = $r4.checkPermission(this.this$0[$i1], $r5);
        }
        ((Item) this.val$activity).a(this.val$titleId, this.this$0, $r2);
    }
}

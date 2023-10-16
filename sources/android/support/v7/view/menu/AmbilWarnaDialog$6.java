package android.support.v7.view.menu;

import android.view.View;
import android.view.ViewTreeObserver;

class AmbilWarnaDialog$6 implements ViewTreeObserver.OnGlobalLayoutListener {
    final /* synthetic */ f this$0;

    AmbilWarnaDialog$6(f fVar) {
        this.this$0 = fVar;
    }

    public void onGlobalLayout() {
        if (this.this$0.isShowing() && this.this$0.c.size() > 0 && !this.this$0.c.get(0).this$0.isModal()) {
            View $r6 = this.this$0.this$0;
            if ($r6 == null || !$r6.isShown()) {
                this.this$0.dismiss();
                return;
            }
            for (e $r4 : this.this$0.c) {
                $r4.this$0.show();
            }
        }
    }
}

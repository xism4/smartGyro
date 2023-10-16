package android.support.v7.app;

import android.view.View;
import com.org.android.view.ViewPropertyAnimatorListenerAdapter;

class ActionBarOverlayLayout$1 extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ WindowDecorActionBar this$0;

    ActionBarOverlayLayout$1(WindowDecorActionBar windowDecorActionBar) {
        this.this$0 = windowDecorActionBar;
    }

    public void onAnimationEnd(View view) {
        WindowDecorActionBar $r2 = this.this$0;
        $r2.mCurrentShowAnim = null;
        $r2.mContainerView.requestLayout();
    }
}

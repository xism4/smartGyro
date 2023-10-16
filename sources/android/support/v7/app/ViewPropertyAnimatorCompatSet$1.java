package android.support.v7.app;

import android.support.v7.widget.ActionBarOverlayLayout;
import android.view.View;
import com.org.android.view.ViewCompat;
import com.org.android.view.ViewPropertyAnimatorListenerAdapter;

class ViewPropertyAnimatorCompatSet$1 extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ WindowDecorActionBar this$0;

    ViewPropertyAnimatorCompatSet$1(WindowDecorActionBar windowDecorActionBar) {
        this.this$0 = windowDecorActionBar;
    }

    public void onAnimationEnd(View view) {
        View $r1;
        WindowDecorActionBar $r2 = this.this$0;
        if ($r2.mContentAnimations && ($r1 = $r2.mContentView) != null) {
            $r1.setTranslationY(0.0f);
            this.this$0.mContainerView.setTranslationY(0.0f);
        }
        this.this$0.mContainerView.setVisibility(8);
        this.this$0.mContainerView.setTransitioning(false);
        WindowDecorActionBar $r22 = this.this$0;
        $r22.mCurrentShowAnim = null;
        $r22.completeDeferredDestroyActionMode();
        ActionBarOverlayLayout $r4 = this.this$0.mOverlayLayout;
        if ($r4 != null) {
            ViewCompat.requestApplyInsets($r4);
        }
    }
}

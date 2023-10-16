package com.org.android.view;

import android.animation.Animator;
import android.os.Build;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

public final class ViewPropertyAnimatorCompat {
    Runnable mEndAction = null;
    int mOldLayerType = -1;
    Runnable mStartAction = null;
    private WeakReference<View> mView;

    ViewPropertyAnimatorCompat(View view) {
        this.mView = new WeakReference(view);
    }

    private void setListener(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        if (viewPropertyAnimatorListener != null) {
            view.animate().setListener(new ValueAnimatorCompatImplHoneycombMr1$2(this, viewPropertyAnimatorListener, view));
        } else {
            view.animate().setListener((Animator.AnimatorListener) null);
        }
    }

    public ViewPropertyAnimatorCompat alpha(float f) {
        View $r3 = (View) this.mView.get();
        if ($r3 != null) {
            $r3.animate().alpha(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat cancel(ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener) {
        View $r4 = (View) this.mView.get();
        if ($r4 != null && Build.VERSION.SDK_INT >= 19) {
            HoneycombMr1AnimatorCompatProvider$HoneycombValueAnimatorCompat$1 $r5 = null;
            if (viewPropertyAnimatorUpdateListener != null) {
                $r5 = new HoneycombMr1AnimatorCompatProvider$HoneycombValueAnimatorCompat$1(this, viewPropertyAnimatorUpdateListener, $r4);
            }
            $r4.animate().setUpdateListener($r5);
        }
        return this;
    }

    public void cancel() {
        View $r3 = (View) this.mView.get();
        if ($r3 != null) {
            $r3.animate().cancel();
        }
    }

    public long getDuration() {
        View $r3 = (View) this.mView.get();
        if ($r3 != null) {
            return $r3.animate().getDuration();
        }
        return 0;
    }

    public ViewPropertyAnimatorCompat setDuration(long j) {
        View $r3 = (View) this.mView.get();
        if ($r3 != null) {
            $r3.animate().setDuration(j);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setInterpolator(Interpolator interpolator) {
        View $r4 = (View) this.mView.get();
        if ($r4 != null) {
            $r4.animate().setInterpolator(interpolator);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setListener(ViewPropertyAnimatorListener $r2) {
        View $r4 = (View) this.mView.get();
        if ($r4 != null) {
            if (Build.VERSION.SDK_INT < 16) {
                $r4.setTag(2113929216, $r2);
                $r2 = r5;
                ViewPropertyAnimatorCompat$ICSViewPropertyAnimatorCompatImpl$MyVpaListener r5 = new ViewPropertyAnimatorCompat$ICSViewPropertyAnimatorCompatImpl$MyVpaListener(this);
            }
            setListener($r4, $r2);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setStartDelay(long j) {
        View $r3 = (View) this.mView.get();
        if ($r3 != null) {
            $r3.animate().setStartDelay(j);
        }
        return this;
    }

    public void start() {
        View $r3 = (View) this.mView.get();
        if ($r3 != null) {
            $r3.animate().start();
        }
    }

    public ViewPropertyAnimatorCompat translationY(float f) {
        View $r3 = (View) this.mView.get();
        if ($r3 != null) {
            $r3.animate().translationY(f);
        }
        return this;
    }
}

package com.org.v4.view;

import a.a.c.g.z;
import android.view.View;
import android.view.animation.Interpolator;
import com.org.android.view.ViewPropertyAnimatorCompat;
import com.org.android.view.ViewPropertyAnimatorListener;
import com.org.android.view.ViewPropertyAnimatorListenerAdapter;
import java.util.ArrayList;
import java.util.Iterator;

public class ViewPropertyAnimatorCompatSet {
    final ArrayList<z> mAnimators = new ArrayList();
    private long mDuration = -1;
    private Interpolator mInterpolator;
    private boolean mIsStarted;
    ViewPropertyAnimatorListener mListener;
    private final ViewPropertyAnimatorListenerAdapter mProxyListener = new ViewPropertyAnimatorListenerAdapter() {
        private int mProxyEndCount = 0;
        private boolean mProxyStarted = false;

        public void onAnimationEnd(View view) {
            int $i1 = this.mProxyEndCount + 1;
            this.mProxyEndCount = $i1;
            if ($i1 == ViewPropertyAnimatorCompatSet.this.mAnimators.size()) {
                ViewPropertyAnimatorListener $r4 = ViewPropertyAnimatorCompatSet.this.mListener;
                if ($r4 != null) {
                    $r4.onAnimationEnd((View) null);
                }
                onEnd();
            }
        }

        public void onAnimationStart(View view) {
            if (!this.mProxyStarted) {
                this.mProxyStarted = true;
                ViewPropertyAnimatorListener $r3 = ViewPropertyAnimatorCompatSet.this.mListener;
                if ($r3 != null) {
                    $r3.onAnimationStart((View) null);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void onEnd() {
            this.mProxyEndCount = 0;
            this.mProxyStarted = false;
            ViewPropertyAnimatorCompatSet.this.onAnimationsEnded();
        }
    };

    public void cancel() {
        if (this.mIsStarted) {
            Iterator $r2 = this.mAnimators.iterator();
            while ($r2.hasNext()) {
                $r2.next().cancel();
            }
            this.mIsStarted = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void onAnimationsEnded() {
        this.mIsStarted = false;
    }

    public ViewPropertyAnimatorCompatSet play(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
        if (!this.mIsStarted) {
            this.mAnimators.add(viewPropertyAnimatorCompat);
        }
        return this;
    }

    public ViewPropertyAnimatorCompatSet playSequentially(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, ViewPropertyAnimatorCompat viewPropertyAnimatorCompat2) {
        this.mAnimators.add(viewPropertyAnimatorCompat);
        viewPropertyAnimatorCompat2.setStartDelay(viewPropertyAnimatorCompat.getDuration());
        this.mAnimators.add(viewPropertyAnimatorCompat2);
        return this;
    }

    public ViewPropertyAnimatorCompatSet setDuration(long j) {
        if (!this.mIsStarted) {
            this.mDuration = j;
        }
        return this;
    }

    public ViewPropertyAnimatorCompatSet setInterpolator(Interpolator interpolator) {
        if (!this.mIsStarted) {
            this.mInterpolator = interpolator;
        }
        return this;
    }

    public ViewPropertyAnimatorCompatSet setListener(ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        if (!this.mIsStarted) {
            this.mListener = viewPropertyAnimatorListener;
        }
        return this;
    }

    public void start() {
        if (!this.mIsStarted) {
            Iterator $r2 = this.mAnimators.iterator();
            while ($r2.hasNext()) {
                ViewPropertyAnimatorCompat $r4 = $r2.next();
                long $l1 = this.mDuration;
                if ($l1 >= 0) {
                    $r4.setDuration($l1);
                }
                Interpolator $r5 = this.mInterpolator;
                if ($r5 != null) {
                    $r4.setInterpolator($r5);
                }
                if (this.mListener != null) {
                    $r4.setListener(this.mProxyListener);
                }
                $r4.start();
            }
            this.mIsStarted = true;
        }
    }
}

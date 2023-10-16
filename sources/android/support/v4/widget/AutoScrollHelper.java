package android.support.v4.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import com.org.android.view.ViewCompat;

public abstract class AutoScrollHelper implements View.OnTouchListener {
    private static final int DEFAULT_ACTIVATION_DELAY = ViewConfiguration.getTapTimeout();
    boolean h;
    private int mActivationDelay;
    private boolean mAlreadyDelayed;
    boolean mAnimating;
    private final Interpolator mEdgeInterpolator = new AccelerateInterpolator();
    private int mEdgeType;
    private boolean mEnabled;
    private boolean mExclusive;
    private float[] mMaximumEdges = {Float.MAX_VALUE, Float.MAX_VALUE};
    private float[] mMaximumVelocity = {Float.MAX_VALUE, Float.MAX_VALUE};
    private float[] mMinimumVelocity = {0.0f, 0.0f};
    boolean mNeedsReset;
    private float[] mRelativeEdges = {0.0f, 0.0f};
    private float[] mRelativeVelocity = {0.0f, 0.0f};
    private Runnable mRunnable;
    final ClampedScroller mScroller = new ClampedScroller();
    final View mTarget;

    class ClampedScroller {
        private long mDeltaTime = 0;
        private int mDeltaX = 0;
        private int mDeltaY = 0;
        private int mEffectiveRampDown;
        private int mRampDownDuration;
        private int mRampUpDuration;
        private long mStartTime = Long.MIN_VALUE;
        private long mStopTime = -1;
        private float mStopValue;
        private float mTargetVelocityX;
        private float mTargetVelocityY;

        ClampedScroller() {
        }

        private float getValueAt(long $l1) {
            if ($l1 < this.mStartTime) {
                return 0.0f;
            }
            long $l2 = this.mStopTime;
            if ($l2 < 0 || $l1 < $l2) {
                return AutoScrollHelper.constrain(((float) ($l1 - this.mStartTime)) / ((float) this.mRampUpDuration), 0.0f, 1.0f) * 0.5f;
            }
            float $f0 = this.mStopValue;
            return (1.0f - $f0) + ($f0 * AutoScrollHelper.constrain(((float) ($l1 - $l2)) / ((float) this.mEffectiveRampDown), 0.0f, 1.0f));
        }

        private float interpolateValue(float f) {
            return (-4.0f * f * f) + (f * 4.0f);
        }

        public void computeScrollDelta() {
            if (this.mDeltaTime != 0) {
                long $l1 = AnimationUtils.currentAnimationTimeMillis();
                float $f0 = interpolateValue(getValueAt($l1));
                this.mDeltaTime = $l1;
                float $f02 = ((float) ($l1 - this.mDeltaTime)) * $f0;
                this.mDeltaX = (int) (this.mTargetVelocityX * $f02);
                this.mDeltaY = (int) ($f02 * this.mTargetVelocityY);
                return;
            }
            throw new RuntimeException("Cannot compute scroll delta before calling start()");
        }

        public int getDeltaX() {
            return this.mDeltaX;
        }

        public int getDeltaY() {
            return this.mDeltaY;
        }

        public int getHorizontalDirection() {
            float $f1 = this.mTargetVelocityX;
            return (int) ($f1 / Math.abs($f1));
        }

        public int getVerticalDirection() {
            float $f1 = this.mTargetVelocityY;
            return (int) ($f1 / Math.abs($f1));
        }

        public boolean isFinished() {
            return this.mStopTime > 0 && AnimationUtils.currentAnimationTimeMillis() > this.mStopTime + ((long) this.mEffectiveRampDown);
        }

        public void requestStop() {
            long $l1 = AnimationUtils.currentAnimationTimeMillis();
            this.mEffectiveRampDown = AutoScrollHelper.access$getConstrain((int) ($l1 - this.mStartTime), 0, this.mRampDownDuration);
            this.mStopValue = getValueAt($l1);
            this.mStopTime = $l1;
        }

        public void setRampDownDuration(int i) {
            this.mRampUpDuration = i;
        }

        public void setRampUpDuration(int i) {
            this.mRampDownDuration = i;
        }

        public void setTargetVelocity(float f, float f2) {
            this.mTargetVelocityX = f;
            this.mTargetVelocityY = f2;
        }

        public void start() {
            this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
            this.mStopTime = -1;
            this.mDeltaTime = this.mStartTime;
            this.mStopValue = 0.5f;
            this.mDeltaX = 0;
            this.mDeltaY = 0;
        }
    }

    class ScrollAnimationRunnable implements Runnable {
        ScrollAnimationRunnable() {
        }

        public void run() {
            AutoScrollHelper $r1 = AutoScrollHelper.this;
            if ($r1.mAnimating) {
                if ($r1.mNeedsReset) {
                    $r1.mNeedsReset = false;
                    $r1.mScroller.start();
                }
                ClampedScroller $r2 = AutoScrollHelper.this.mScroller;
                if ($r2.isFinished() || !AutoScrollHelper.this.shouldAnimate()) {
                    AutoScrollHelper.this.mAnimating = false;
                    return;
                }
                AutoScrollHelper $r12 = AutoScrollHelper.this;
                if ($r12.h) {
                    $r12.h = false;
                    $r12.cancelTargetTouch();
                }
                $r2.computeScrollDelta();
                AutoScrollHelper.this.scrollTargetBy($r2.getDeltaX(), $r2.getDeltaY());
                ViewCompat.postOnAnimation(AutoScrollHelper.this.mTarget, this);
            }
        }
    }

    public AutoScrollHelper(View view) {
        this.mTarget = view;
        float $f0 = Resources.getSystem().getDisplayMetrics().density;
        int $i1 = (int) (($f0 * 315.0f) + 0.5f);
        float $f02 = (float) ((int) ((1575.0f * $f0) + 0.5f));
        setMaximumVelocity($f02, $f02);
        float $f03 = (float) $i1;
        setMinimumVelocity($f03, $f03);
        setEdgeType(1);
        setMaximumEdges(Float.MAX_VALUE, Float.MAX_VALUE);
        setRelativeEdges(0.2f, 0.2f);
        setRelativeVelocity(1.0f, 1.0f);
        setActivationDelay(DEFAULT_ACTIVATION_DELAY);
        setRampDownDuration(500);
        setRampUpDuration(500);
    }

    static int access$getConstrain(int i, int i2, int i3) {
        return i > i3 ? i3 : i < i2 ? i2 : i;
    }

    private float computeTargetVelocity(int i, float f, float f2, float f3) {
        float $f3 = getEdgeValue(this.mRelativeEdges[i], f2, this.mMaximumEdges[i], f);
        if ($f3 == 0.0f) {
            return 0.0f;
        }
        float $f4 = this.mRelativeVelocity[i];
        float $f1 = this.mMinimumVelocity[i];
        float $f2 = this.mMaximumVelocity[i];
        float $f0 = $f4 * f3;
        return $f3 > 0.0f ? constrain($f3 * $f0, $f1, $f2) : -constrain((-$f3) * $f0, $f1, $f2);
    }

    static float constrain(float f, float f2, float f3) {
        return f > f3 ? f3 : f < f2 ? f2 : f;
    }

    private float constrainEdgeValue(float $f0, float $f1) {
        if ($f1 == 0.0f) {
            return 0.0f;
        }
        int $i1 = this.mEdgeType;
        if ($i1 == 0 || $i1 == 1) {
            if ($f0 < $f1) {
                return $f0 >= 0.0f ? 1.0f - ($f0 / $f1) : (!this.mAnimating || this.mEdgeType != 1) ? 0.0f : 1.0f;
            }
            return 0.0f;
        } else if ($i1 == 2 && $f0 < 0.0f) {
            return $f0 / (-$f1);
        } else {
            return 0.0f;
        }
    }

    private float getEdgeValue(float f, float f2, float f3, float f4) {
        float $f1;
        float $f3 = constrain(f * f2, 0.0f, f3);
        float $f12 = constrainEdgeValue(f2 - f4, $f3) - constrainEdgeValue(f4, $f3);
        if ($f12 < 0.0f) {
            $f1 = -this.mEdgeInterpolator.getInterpolation(-$f12);
        } else if ($f12 <= 0.0f) {
            return 0.0f;
        } else {
            $f1 = this.mEdgeInterpolator.getInterpolation($f12);
        }
        return constrain($f1, -1.0f, 1.0f);
    }

    private void requestStop() {
        if (this.mNeedsReset) {
            this.mAnimating = false;
        } else {
            this.mScroller.requestStop();
        }
    }

    private void startAnimating() {
        int $i1;
        if (this.mRunnable == null) {
            this.mRunnable = new ScrollAnimationRunnable();
        }
        this.mAnimating = true;
        this.mNeedsReset = true;
        if (this.mAlreadyDelayed || ($i1 = this.mActivationDelay) <= 0) {
            this.mRunnable.run();
        } else {
            ViewCompat.start(this.mTarget, this.mRunnable, (long) $i1);
        }
        this.mAlreadyDelayed = true;
    }

    public abstract boolean canTargetScrollHorizontally(int i);

    public abstract boolean canTargetScrollVertically(int i);

    /* access modifiers changed from: package-private */
    public void cancelTargetTouch() {
        long $l0 = SystemClock.uptimeMillis();
        MotionEvent $r2 = MotionEvent.obtain($l0, $l0, 3, 0.0f, 0.0f, 0);
        this.mTarget.onTouchEvent($r2);
        $r2.recycle();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
        if (r2 != 3) goto L_0x005c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0064 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouch(android.view.View r10, android.view.MotionEvent r11) {
        /*
            r9 = this;
            boolean r0 = r9.mEnabled
            if (r0 != 0) goto L_0x0006
            r1 = 0
            return r1
        L_0x0006:
            int r2 = r11.getActionMasked()
            if (r2 == 0) goto L_0x001a
            r1 = 1
            if (r2 == r1) goto L_0x0016
            r1 = 2
            if (r2 == r1) goto L_0x0020
            r1 = 3
            if (r2 == r1) goto L_0x0016
            goto L_0x005c
        L_0x0016:
            r9.requestStop()
            goto L_0x005c
        L_0x001a:
            r1 = 1
            r9.h = r1
            r1 = 0
            r9.mAlreadyDelayed = r1
        L_0x0020:
            float r3 = r11.getX()
            int r2 = r10.getWidth()
            float r4 = (float) r2
            android.view.View r5 = r9.mTarget
            int r2 = r5.getWidth()
            float r6 = (float) r2
            r1 = 0
            float r3 = r9.computeTargetVelocity(r1, r3, r4, r6)
            float r4 = r11.getY()
            int r2 = r10.getHeight()
            float r6 = (float) r2
            android.view.View r10 = r9.mTarget
            int r2 = r10.getHeight()
            float r7 = (float) r2
            r1 = 1
            float r4 = r9.computeTargetVelocity(r1, r4, r6, r7)
            android.support.v4.widget.AutoScrollHelper$ClampedScroller r8 = r9.mScroller
            r8.setTargetVelocity(r3, r4)
            boolean r0 = r9.mAnimating
            if (r0 != 0) goto L_0x005c
            boolean r0 = r9.shouldAnimate()
            if (r0 == 0) goto L_0x005c
            r9.startAnimating()
        L_0x005c:
            boolean r0 = r9.mExclusive
            if (r0 == 0) goto L_0x0066
            boolean r0 = r9.mAnimating
            if (r0 == 0) goto L_0x0066
            r1 = 1
            return r1
        L_0x0066:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.AutoScrollHelper.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public abstract void scrollTargetBy(int i, int i2);

    public AutoScrollHelper setActivationDelay(int i) {
        this.mActivationDelay = i;
        return this;
    }

    public AutoScrollHelper setEdgeType(int i) {
        this.mEdgeType = i;
        return this;
    }

    public AutoScrollHelper setEnabled(boolean z) {
        if (this.mEnabled && !z) {
            requestStop();
        }
        this.mEnabled = z;
        return this;
    }

    public AutoScrollHelper setMaximumEdges(float f, float f2) {
        float[] $r1 = this.mMaximumEdges;
        $r1[0] = f;
        $r1[1] = f2;
        return this;
    }

    public AutoScrollHelper setMaximumVelocity(float f, float f2) {
        float[] $r1 = this.mMaximumVelocity;
        $r1[0] = f / 1000.0f;
        $r1[1] = f2 / 1000.0f;
        return this;
    }

    public AutoScrollHelper setMinimumVelocity(float f, float f2) {
        float[] $r1 = this.mMinimumVelocity;
        $r1[0] = f / 1000.0f;
        $r1[1] = f2 / 1000.0f;
        return this;
    }

    public AutoScrollHelper setRampDownDuration(int i) {
        this.mScroller.setRampDownDuration(i);
        return this;
    }

    public AutoScrollHelper setRampUpDuration(int i) {
        this.mScroller.setRampUpDuration(i);
        return this;
    }

    public AutoScrollHelper setRelativeEdges(float f, float f2) {
        float[] $r1 = this.mRelativeEdges;
        $r1[0] = f;
        $r1[1] = f2;
        return this;
    }

    public AutoScrollHelper setRelativeVelocity(float f, float f2) {
        float[] $r1 = this.mRelativeVelocity;
        $r1[0] = f / 1000.0f;
        $r1[1] = f2 / 1000.0f;
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldAnimate() {
        ClampedScroller $r1 = this.mScroller;
        int $i0 = $r1.getVerticalDirection();
        int $i1 = $r1.getHorizontalDirection();
        if ($i0 == 0 || !canTargetScrollVertically($i0)) {
            return $i1 != 0 && canTargetScrollHorizontally($i1);
        }
        return true;
    }
}

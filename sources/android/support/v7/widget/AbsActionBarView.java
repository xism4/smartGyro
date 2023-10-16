package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.org.android.view.ViewCompat;
import com.org.android.view.ViewPropertyAnimatorCompat;
import com.org.android.view.ViewPropertyAnimatorListener;
import com.org.v4.util.R$attr;
import com.org.v4.util.R$styleable;

abstract class AbsActionBarView extends ViewGroup {
    protected ActionMenuPresenter mActionMenuPresenter;
    protected int mContentHeight;
    private boolean mEatingHover;
    private boolean mEatingTouch;
    protected ActionMenuView mMenuView;
    protected final Context mPopupContext;
    protected final VisibilityAnimListener mVisAnimListener;
    protected ViewPropertyAnimatorCompat mVisibilityAnim;

    public class VisibilityAnimListener implements ViewPropertyAnimatorListener {
        private boolean mCanceled = false;
        int mFinalVisibility;

        protected VisibilityAnimListener() {
        }

        public void onAnimationCancel(View view) {
            this.mCanceled = true;
        }

        public void onAnimationEnd(View view) {
            if (!this.mCanceled) {
                AbsActionBarView $r2 = AbsActionBarView.this;
                $r2.mVisibilityAnim = null;
                AbsActionBarView.super.setVisibility(this.mFinalVisibility);
            }
        }

        public void onAnimationStart(View view) {
            AbsActionBarView.super.setVisibility(0);
            this.mCanceled = false;
        }

        public VisibilityAnimListener withFinalVisibility(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, int i) {
            AbsActionBarView.this.mVisibilityAnim = viewPropertyAnimatorCompat;
            this.mFinalVisibility = i;
            return this;
        }
    }

    AbsActionBarView(Context context) {
        this(context, (AttributeSet) null);
    }

    AbsActionBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    AbsActionBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        int $i0;
        this.mVisAnimListener = new VisibilityAnimListener();
        TypedValue $r4 = new TypedValue();
        if (!context.getTheme().resolveAttribute(R$attr.actionBarPopupTheme, $r4, true) || ($i0 = $r4.resourceId) == 0) {
            this.mPopupContext = context;
        } else {
            this.mPopupContext = new ContextThemeWrapper(context, $i0);
        }
    }

    protected static int next(int $i1, int i, boolean z) {
        return z ? $i1 - i : $i1 + i;
    }

    public int getAnimatedVisibility() {
        return this.mVisibilityAnim != null ? this.mVisAnimListener.mFinalVisibility : getVisibility();
    }

    public int getContentHeight() {
        return this.mContentHeight;
    }

    /* access modifiers changed from: protected */
    public int measureChildView(View view, int i, int i2, int i3) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE), i2);
        return Math.max(0, (i - view.getMeasuredWidth()) - i3);
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray $r4 = getContext().obtainStyledAttributes((AttributeSet) null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        setContentHeight($r4.getLayoutDimension(R$styleable.ActionBar_height, 0));
        $r4.recycle();
        ActionMenuPresenter $r5 = this.mActionMenuPresenter;
        if ($r5 != null) {
            $r5.onConfigurationChanged(configuration);
        }
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        int $i0 = motionEvent.getActionMasked();
        if ($i0 == 9) {
            this.mEatingHover = false;
        }
        if (!this.mEatingHover) {
            boolean $z0 = super.onHoverEvent(motionEvent);
            if ($i0 == 9 && !$z0) {
                this.mEatingHover = true;
            }
        }
        if ($i0 != 10 && $i0 != 3) {
            return true;
        }
        this.mEatingHover = false;
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int $i0 = motionEvent.getActionMasked();
        if ($i0 == 0) {
            this.mEatingTouch = false;
        }
        if (!this.mEatingTouch) {
            boolean $z0 = super.onTouchEvent(motionEvent);
            if ($i0 == 0 && !$z0) {
                this.mEatingTouch = true;
            }
        }
        if ($i0 != 1 && $i0 != 3) {
            return true;
        }
        this.mEatingTouch = false;
        return true;
    }

    /* access modifiers changed from: protected */
    public int positionChild(View view, int i, int i2, int i3, boolean z) {
        int $i3 = view.getMeasuredWidth();
        int $i4 = view.getMeasuredHeight();
        int $i1 = i2 + ((i3 - $i4) / 2);
        if (z) {
            view.layout(i - $i3, $i1, i, $i4 + $i1);
        } else {
            view.layout(i, $i1, i + $i3, $i4 + $i1);
        }
        return z ? -$i3 : $i3;
    }

    public abstract void setContentHeight(int i);

    public void setVisibility(int i) {
        if (i != getVisibility()) {
            ViewPropertyAnimatorCompat $r1 = this.mVisibilityAnim;
            if ($r1 != null) {
                $r1.cancel();
            }
            super.setVisibility(i);
        }
    }

    public ViewPropertyAnimatorCompat setupAnimatorToVisibility(int i, long j) {
        ViewPropertyAnimatorCompat $r1 = this.mVisibilityAnim;
        if ($r1 != null) {
            $r1.cancel();
        }
        if (i == 0) {
            if (getVisibility() != 0) {
                setAlpha(0.0f);
            }
            ViewPropertyAnimatorCompat $r12 = ViewCompat.animate(this);
            $r12.alpha(1.0f);
            $r12.setDuration(j);
            VisibilityAnimListener $r2 = this.mVisAnimListener;
            $r2.withFinalVisibility($r12, i);
            $r12.setListener($r2);
            return $r12;
        }
        ViewPropertyAnimatorCompat $r13 = ViewCompat.animate(this);
        $r13.alpha(0.0f);
        $r13.setDuration(j);
        VisibilityAnimListener $r22 = this.mVisAnimListener;
        $r22.withFinalVisibility($r13, i);
        $r13.setListener($r22);
        return $r13;
    }
}

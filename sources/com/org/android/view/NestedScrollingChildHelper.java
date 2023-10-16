package com.org.android.view;

import android.view.View;
import android.view.ViewParent;

public class NestedScrollingChildHelper {
    private ViewParent mCallback;
    private boolean mIsNestedScrollingEnabled;
    private ViewParent mMenu;
    private int[] mTempNestedScrollConsumed;
    private final View mView;

    public NestedScrollingChildHelper(View view) {
        this.mView = view;
    }

    private ViewParent getChildAt(int i) {
        if (i == 0) {
            return this.mMenu;
        }
        if (i != 1) {
            return null;
        }
        return this.mCallback;
    }

    private void initialize(int i, ViewParent viewParent) {
        if (i == 0) {
            this.mMenu = viewParent;
        } else if (i == 1) {
            this.mCallback = viewParent;
        }
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        ViewParent $r1;
        if (!isNestedScrollingEnabled() || ($r1 = getChildAt(0)) == null) {
            return false;
        }
        return ViewParentCompat.onNestedFling($r1, this.mView, f, f2, z);
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        ViewParent $r1;
        if (!isNestedScrollingEnabled() || ($r1 = getChildAt(0)) == null) {
            return false;
        }
        return ViewParentCompat.onNestedPreFling($r1, this.mView, f, f2);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] $r2, int[] iArr, int i3) {
        ViewParent $r3;
        int $i4;
        int $i3;
        if (!isNestedScrollingEnabled() || ($r3 = getChildAt(i3)) == null) {
            return false;
        }
        if (i != 0 || i2 != 0) {
            if (iArr != null) {
                this.mView.getLocationInWindow(iArr);
                $i3 = iArr[0];
                $i4 = iArr[1];
            } else {
                $i3 = 0;
                $i4 = 0;
            }
            if ($r2 == null) {
                if (this.mTempNestedScrollConsumed == null) {
                    int[] $r22 = new int[2];
                    this.mTempNestedScrollConsumed = $r22;
                }
                $r2 = this.mTempNestedScrollConsumed;
            }
            $r2[0] = 0;
            $r2[1] = 0;
            ViewParentCompat.onNestedPreScroll($r3, this.mView, i, i2, $r2, i3);
            if (iArr != null) {
                this.mView.getLocationInWindow(iArr);
                iArr[0] = iArr[0] - $i3;
                iArr[1] = iArr[1] - $i4;
            }
            return ($r2[0] == 0 && $r2[1] == 0) ? false : true;
        } else if (iArr == null) {
            return false;
        } else {
            iArr[0] = 0;
            iArr[1] = 0;
            return false;
        }
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr, int i5) {
        ViewParent $r2;
        int $i6;
        int $i5;
        if (!isNestedScrollingEnabled() || ($r2 = getChildAt(i5)) == null) {
            return false;
        }
        if (i != 0 || i2 != 0 || i3 != 0 || i4 != 0) {
            if (iArr != null) {
                this.mView.getLocationInWindow(iArr);
                $i5 = iArr[0];
                $i6 = iArr[1];
            } else {
                $i5 = 0;
                $i6 = 0;
            }
            ViewParentCompat.onNestedScroll($r2, this.mView, i, i2, i3, i4, i5);
            if (iArr == null) {
                return true;
            }
            this.mView.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - $i5;
            iArr[1] = iArr[1] - $i6;
            return true;
        } else if (iArr == null) {
            return false;
        } else {
            iArr[0] = 0;
            iArr[1] = 0;
            return false;
        }
    }

    public boolean hasNestedScrollingParent(int i) {
        return getChildAt(i) != null;
    }

    public boolean isNestedScrollingEnabled() {
        return this.mIsNestedScrollingEnabled;
    }

    public void setNestedScrollingEnabled(boolean z) {
        if (this.mIsNestedScrollingEnabled) {
            ViewCompat.draw(this.mView);
        }
        this.mIsNestedScrollingEnabled = z;
    }

    public boolean startNestedScroll(int i, int i2) {
        if (hasNestedScrollingParent(i2)) {
            return true;
        }
        if (!isNestedScrollingEnabled()) {
            return false;
        }
        View $r1 = this.mView;
        for (ViewParent $r2 = this.mView.getParent(); $r2 != null; $r2 = $r2.getParent()) {
            if (ViewParentCompat.onStartNestedScroll($r2, $r1, this.mView, i, i2)) {
                initialize(i2, $r2);
                ViewParentCompat.onNestedScrollAccepted($r2, $r1, this.mView, i, i2);
                return true;
            }
            if ($r2 instanceof View) {
                $r1 = (View) $r2;
            }
        }
        return false;
    }

    public void stopNestedScroll(int i) {
        ViewParent $r2 = getChildAt(i);
        if ($r2 != null) {
            ViewParentCompat.onStopNestedScroll($r2, this.mView, i);
            initialize(i, (ViewParent) null);
        }
    }
}

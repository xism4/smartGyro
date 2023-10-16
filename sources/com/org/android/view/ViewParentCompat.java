package com.org.android.view;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;

public final class ViewParentCompat {

    public interface ViewParentCompatImpl extends NestedScrollingParent {
        void onNestedPreScroll(View view, int i, int i2, int[] iArr, int i3);

        void onNestedScrollAccepted(View view, View view2, int i, int i2);

        boolean onStartNestedScroll(View view, View view2, int i, int i2);

        void onStopNestedScroll(View view, int i);

        void scroll(View view, int i, int i2, int i3, int i4, int i5);
    }

    public static boolean onNestedFling(ViewParent viewParent, View view, float f, float f2, boolean $z0) {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                return viewParent.onNestedFling(view, f, f2, $z0);
            } catch (AbstractMethodError $r2) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface " + "method onNestedFling", $r2);
                return false;
            }
        } else if (viewParent instanceof NestedScrollingParent) {
            return ((NestedScrollingParent) viewParent).onNestedFling(view, f, f2, $z0);
        } else {
            return false;
        }
    }

    public static boolean onNestedPreFling(ViewParent viewParent, View view, float f, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                return viewParent.onNestedPreFling(view, f, f2);
            } catch (AbstractMethodError $r2) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface " + "method onNestedPreFling", $r2);
                return false;
            }
        } else if (viewParent instanceof NestedScrollingParent) {
            return ((NestedScrollingParent) viewParent).onNestedPreFling(view, f, f2);
        } else {
            return false;
        }
    }

    public static void onNestedPreScroll(ViewParent viewParent, View view, int i, int i2, int[] iArr, int i3) {
        if (viewParent instanceof ViewParentCompatImpl) {
            ((ViewParentCompatImpl) viewParent).onNestedPreScroll(view, i, i2, iArr, i3);
        } else if (i3 != 0) {
        } else {
            if (Build.VERSION.SDK_INT >= 21) {
                try {
                    viewParent.onNestedPreScroll(view, i, i2, iArr);
                } catch (AbstractMethodError $r5) {
                    Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface " + "method onNestedPreScroll", $r5);
                }
            } else if (viewParent instanceof NestedScrollingParent) {
                ((NestedScrollingParent) viewParent).onNestedPreScroll(view, i, i2, iArr);
            }
        }
    }

    public static void onNestedScroll(ViewParent viewParent, View view, int i, int i2, int i3, int i4, int i5) {
        if (viewParent instanceof ViewParentCompatImpl) {
            ((ViewParentCompatImpl) viewParent).scroll(view, i, i2, i3, i4, i5);
        } else if (i5 != 0) {
        } else {
            if (Build.VERSION.SDK_INT >= 21) {
                try {
                    viewParent.onNestedScroll(view, i, i2, i3, i4);
                } catch (AbstractMethodError $r4) {
                    Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface " + "method onNestedScroll", $r4);
                }
            } else if (viewParent instanceof NestedScrollingParent) {
                ((NestedScrollingParent) viewParent).onNestedScroll(view, i, i2, i3, i4);
            }
        }
    }

    public static void onNestedScrollAccepted(ViewParent viewParent, View view, View view2, int i, int i2) {
        if (viewParent instanceof ViewParentCompatImpl) {
            ((ViewParentCompatImpl) viewParent).onNestedScrollAccepted(view, view2, i, i2);
        } else if (i2 != 0) {
        } else {
            if (Build.VERSION.SDK_INT >= 21) {
                try {
                    viewParent.onNestedScrollAccepted(view, view2, i);
                } catch (AbstractMethodError $r5) {
                    Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface " + "method onNestedScrollAccepted", $r5);
                }
            } else if (viewParent instanceof NestedScrollingParent) {
                ((NestedScrollingParent) viewParent).onNestedScrollAccepted(view, view2, i);
            }
        }
    }

    public static boolean onStartNestedScroll(ViewParent viewParent, View view, View view2, int i, int i2) {
        if (viewParent instanceof ViewParentCompatImpl) {
            return ((ViewParentCompatImpl) viewParent).onStartNestedScroll(view, view2, i, i2);
        }
        if (i2 != 0) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                return viewParent.onStartNestedScroll(view, view2, i);
            } catch (AbstractMethodError $r4) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface " + "method onStartNestedScroll", $r4);
                return false;
            }
        } else if (viewParent instanceof NestedScrollingParent) {
            return ((NestedScrollingParent) viewParent).onStartNestedScroll(view, view2, i);
        } else {
            return false;
        }
    }

    public static void onStopNestedScroll(ViewParent viewParent, View view, int i) {
        if (viewParent instanceof ViewParentCompatImpl) {
            ((ViewParentCompatImpl) viewParent).onStopNestedScroll(view, i);
        } else if (i != 0) {
        } else {
            if (Build.VERSION.SDK_INT >= 21) {
                try {
                    viewParent.onStopNestedScroll(view);
                } catch (AbstractMethodError $r4) {
                    Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface " + "method onStopNestedScroll", $r4);
                }
            } else if (viewParent instanceof NestedScrollingParent) {
                ((NestedScrollingParent) viewParent).onStopNestedScroll(view);
            }
        }
    }
}

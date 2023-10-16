package com.org.android.view;

import a.a.c.g.z;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowInsets;
import java.lang.reflect.Field;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ViewCompat {
    private static final AtomicInteger BITMAP_INSTANCES = new AtomicInteger(1);
    private static boolean DBG_HL = false;
    private static WeakHashMap<View, z> mViewPropertyAnimatorCompatMap = null;
    private static Field sMinHeightField;
    private static boolean sMinHeightFieldFetched;

    public static int a(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getWindowSystemUiVisibility();
        }
        return 0;
    }

    public static Label a(View view, Label $r1) {
        if (Build.VERSION.SDK_INT < 21) {
            return $r1;
        }
        WindowInsets $r3 = (WindowInsets) Label.b($r1);
        WindowInsets $r4 = view.onApplyWindowInsets($r3);
        if ($r4 != $r3) {
            $r3 = new WindowInsets($r4);
        }
        return Label.a($r3);
    }

    static boolean a(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return f.a(view).a(keyEvent);
    }

    public static ViewPropertyAnimatorCompat animate(View view) {
        if (mViewPropertyAnimatorCompatMap == null) {
            mViewPropertyAnimatorCompatMap = new WeakHashMap();
        }
        ViewPropertyAnimatorCompat $r3 = mViewPropertyAnimatorCompatMap.get(view);
        if ($r3 != null) {
            return $r3;
        }
        ViewPropertyAnimatorCompat $r32 = new ViewPropertyAnimatorCompat(view);
        mViewPropertyAnimatorCompatMap.put(view, $r32);
        return $r32;
    }

    static boolean b(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        return f.a(view).a(view, keyEvent);
    }

    public static void draw(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.stopNestedScroll();
        } else if (view instanceof Point) {
            ((Point) view).stopNestedScroll();
        }
    }

    public static ColorStateList getBackgroundTintList(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getBackgroundTintList();
        }
        if (view instanceof TintableBackgroundView) {
            return ((TintableBackgroundView) view).getSupportBackgroundTintList();
        }
        return null;
    }

    public static PorterDuff.Mode getBackgroundTintMode(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getBackgroundTintMode();
        }
        if (view instanceof TintableBackgroundView) {
            return ((TintableBackgroundView) view).getSupportBackgroundTintMode();
        }
        return null;
    }

    public static int getLayoutDirection(View view) {
        if (Build.VERSION.SDK_INT >= 17) {
            return view.getLayoutDirection();
        }
        return 0;
    }

    public static int getMinimumHeight(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getMinimumHeight();
        }
        if (!sMinHeightFieldFetched) {
            try {
                sMinHeightField = View.class.getDeclaredField("mMinHeight");
                sMinHeightField.setAccessible(true);
            } catch (NoSuchFieldException e) {
            }
            sMinHeightFieldFetched = true;
        }
        Field $r4 = sMinHeightField;
        if ($r4 == null) {
            return 0;
        }
        try {
            return ((Integer) $r4.get(view)).intValue();
        } catch (Exception e2) {
            return 0;
        }
    }

    public static boolean hasOnClickListeners(View view) {
        if (Build.VERSION.SDK_INT >= 15) {
            return view.hasOnClickListeners();
        }
        return false;
    }

    public static boolean isAttachedToWindow(View view) {
        return Build.VERSION.SDK_INT >= 19 ? view.isAttachedToWindow() : view.getWindowToken() != null;
    }

    public static void postInvalidateOnAnimation(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postInvalidateOnAnimation();
        } else {
            view.postInvalidate();
        }
    }

    public static void postOnAnimation(View view, Runnable runnable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postOnAnimation(runnable);
        } else {
            view.postDelayed(runnable, ValueAnimator.getFrameDelay());
        }
    }

    public static void requestApplyInsets(View view) {
        int $i0 = Build.VERSION.SDK_INT;
        if ($i0 >= 20) {
            view.requestApplyInsets();
        } else if ($i0 >= 16) {
            view.requestFitSystemWindows();
        }
    }

    public static void setAccessibilityDelegate(View view, AccessibilityDelegateCompat accessibilityDelegateCompat) {
        view.setAccessibilityDelegate(accessibilityDelegateCompat == null ? null : accessibilityDelegateCompat.getBridge());
    }

    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void setBackgroundTintList(View view, ColorStateList $r1) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setBackgroundTintList($r1);
            if (Build.VERSION.SDK_INT == 21) {
                Drawable $r2 = view.getBackground();
                boolean $z0 = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
                if ($r2 != null && $z0) {
                    if ($r2.isStateful()) {
                        $r2.setState(view.getDrawableState());
                    }
                    view.setBackground($r2);
                }
            }
        } else if (view instanceof TintableBackgroundView) {
            ((TintableBackgroundView) view).setSupportBackgroundTintList($r1);
        }
    }

    public static void setBackgroundTintMode(View view, PorterDuff.Mode $r1) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setBackgroundTintMode($r1);
            if (Build.VERSION.SDK_INT == 21) {
                Drawable $r2 = view.getBackground();
                boolean $z0 = (view.getBackgroundTintList() == null && view.getBackgroundTintMode() == null) ? false : true;
                if ($r2 != null && $z0) {
                    if ($r2.isStateful()) {
                        $r2.setState(view.getDrawableState());
                    }
                    view.setBackground($r2);
                }
            }
        } else if (view instanceof TintableBackgroundView) {
            ((TintableBackgroundView) view).setSupportBackgroundTintMode($r1);
        }
    }

    public static void setElevation(View view, float f) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setElevation(f);
        }
    }

    public static void setOnApplyWindowInsetsListener(View view, OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        if (onApplyWindowInsetsListener == null) {
            view.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) null);
        } else {
            view.setOnApplyWindowInsetsListener(new ViewCompatLollipop$1(onApplyWindowInsetsListener));
        }
    }

    public static void setScrollIndicators(View view, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            view.setScrollIndicators(i, i2);
        }
    }

    public static boolean show(View view) {
        return Build.VERSION.SDK_INT >= 19 ? view.isLaidOut() : view.getWidth() > 0 && view.getHeight() > 0;
    }

    public static void start(View view, Runnable runnable, long j) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.postOnAnimationDelayed(runnable, j);
        } else {
            view.postDelayed(runnable, ValueAnimator.getFrameDelay() + j);
        }
    }
}

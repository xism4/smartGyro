package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;

public final class DrawableCompat {
    private static boolean b;
    private static Method c;
    private static Method sGetLayoutDirectionMethod;
    private static boolean sGetLayoutDirectionMethodFetched;

    public static boolean a(Drawable drawable, int i) {
        int $i1 = Build.VERSION.SDK_INT;
        if ($i1 >= 23) {
            return drawable.setLayoutDirection(i);
        }
        if ($i1 < 17) {
            return false;
        }
        if (!b) {
            try {
                c = Drawable.class.getDeclaredMethod("setLayoutDirection", new Class[]{Integer.TYPE});
                c.setAccessible(true);
            } catch (NoSuchMethodException $r5) {
                Log.i("DrawableCompat", "Failed to retrieve setLayoutDirection(int) method", $r5);
            }
            b = true;
        }
        Method $r4 = c;
        if ($r4 == null) {
            return false;
        }
        Object[] $r6 = new Object[1];
        try {
            $r6[0] = Integer.valueOf(i);
            $r4.invoke(drawable, $r6);
            return true;
        } catch (Exception $r8) {
            Log.i("DrawableCompat", "Failed to invoke setLayoutDirection(int) via reflection", $r8);
            c = null;
            return false;
        }
    }

    public static void applyTheme(Drawable drawable, Resources.Theme theme) {
        if (Build.VERSION.SDK_INT >= 21) {
            drawable.applyTheme(theme);
        }
    }

    public static boolean canApplyTheme(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 21) {
            return drawable.canApplyTheme();
        }
        return false;
    }

    public static int getAlpha(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 19) {
            return drawable.getAlpha();
        }
        return 0;
    }

    public static ColorFilter getColorFilter(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 21) {
            return drawable.getColorFilter();
        }
        return null;
    }

    public static int getLayoutDirection(Drawable drawable) {
        int $i0 = Build.VERSION.SDK_INT;
        if ($i0 >= 23) {
            return drawable.getLayoutDirection();
        }
        if ($i0 < 17) {
            return 0;
        }
        if (!sGetLayoutDirectionMethodFetched) {
            try {
                sGetLayoutDirectionMethod = Drawable.class.getDeclaredMethod("getLayoutDirection", new Class[0]);
                sGetLayoutDirectionMethod.setAccessible(true);
            } catch (NoSuchMethodException $r4) {
                Log.i("DrawableCompat", "Failed to retrieve getLayoutDirection() method", $r4);
            }
            sGetLayoutDirectionMethodFetched = true;
        }
        Method $r3 = sGetLayoutDirectionMethod;
        if ($r3 == null) {
            return 0;
        }
        try {
            return ((Integer) $r3.invoke(drawable, new Object[0])).intValue();
        } catch (Exception $r8) {
            Log.i("DrawableCompat", "Failed to invoke getLayoutDirection() via reflection", $r8);
            sGetLayoutDirectionMethod = null;
            return 0;
        }
    }

    public static void inflate(Drawable drawable, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        if (Build.VERSION.SDK_INT >= 21) {
            drawable.inflate(resources, xmlPullParser, attributeSet, theme);
        } else {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        }
    }

    public static boolean isAutoMirrored(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 19) {
            return drawable.isAutoMirrored();
        }
        return false;
    }

    public static void jumpToCurrentState(Drawable drawable) {
        drawable.jumpToCurrentState();
    }

    public static void setHotspot(Drawable drawable, float f, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            drawable.setHotspot(f, f2);
        }
    }

    public static void setHotspotBounds(Drawable drawable, int i, int i2, int i3, int i4) {
        if (Build.VERSION.SDK_INT >= 21) {
            drawable.setHotspotBounds(i, i2, i3, i4);
        }
    }

    public static void setHotspotBounds(Drawable drawable, boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            drawable.setAutoMirrored(z);
        }
    }

    public static void setTint(Drawable drawable, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            drawable.setTint(i);
        } else if (drawable instanceof DrawableWrapper) {
            ((DrawableWrapper) drawable).setTint(i);
        }
    }

    public static void setTintList(Drawable drawable, ColorStateList colorStateList) {
        if (Build.VERSION.SDK_INT >= 21) {
            drawable.setTintList(colorStateList);
        } else if (drawable instanceof DrawableWrapper) {
            ((DrawableWrapper) drawable).setTintList(colorStateList);
        }
    }

    public static void setTintMode(Drawable drawable, PorterDuff.Mode mode) {
        if (Build.VERSION.SDK_INT >= 21) {
            drawable.setTintMode(mode);
        } else if (drawable instanceof DrawableWrapper) {
            ((DrawableWrapper) drawable).setTintMode(mode);
        }
    }

    public static Drawable wrap(Drawable drawable) {
        int $i0 = Build.VERSION.SDK_INT;
        return $i0 >= 23 ? drawable : $i0 >= 21 ? !(drawable instanceof DrawableWrapper) ? new DrawableWrapperLollipop(drawable) : drawable : !(drawable instanceof DrawableWrapper) ? new DrawableWrapperDonut(drawable) : drawable;
    }
}

package p000a.p001a.p005c.p014g;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.Log;
import android.view.MenuItem;
import p000a.p001a.p005c.p009c.p010a.C0057b;

/* renamed from: a.a.c.g.j */
public final class C0116j {
    /* renamed from: a */
    public static MenuItem m407a(MenuItem menuItem, C0108e eVar) {
        if (menuItem instanceof C0057b) {
            return ((C0057b) menuItem).mo194a(eVar);
        }
        Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
        return menuItem;
    }

    /* renamed from: a */
    public static void m408a(MenuItem menuItem, char c, int i) {
        if (menuItem instanceof C0057b) {
            ((C0057b) menuItem).setAlphabeticShortcut(c, i);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setAlphabeticShortcut(c, i);
        }
    }

    /* renamed from: a */
    public static void m409a(MenuItem menuItem, ColorStateList colorStateList) {
        if (menuItem instanceof C0057b) {
            ((C0057b) menuItem).setIconTintList(colorStateList);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setIconTintList(colorStateList);
        }
    }

    /* renamed from: a */
    public static void m410a(MenuItem menuItem, PorterDuff.Mode mode) {
        if (menuItem instanceof C0057b) {
            ((C0057b) menuItem).setIconTintMode(mode);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setIconTintMode(mode);
        }
    }

    /* renamed from: a */
    public static void m411a(MenuItem menuItem, CharSequence charSequence) {
        if (menuItem instanceof C0057b) {
            ((C0057b) menuItem).setContentDescription(charSequence);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setContentDescription(charSequence);
        }
    }

    /* renamed from: b */
    public static void m412b(MenuItem menuItem, char c, int i) {
        if (menuItem instanceof C0057b) {
            ((C0057b) menuItem).setNumericShortcut(c, i);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setNumericShortcut(c, i);
        }
    }

    /* renamed from: b */
    public static void m413b(MenuItem menuItem, CharSequence charSequence) {
        if (menuItem instanceof C0057b) {
            ((C0057b) menuItem).setTooltipText(charSequence);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setTooltipText(charSequence);
        }
    }
}

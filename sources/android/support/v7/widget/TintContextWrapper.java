package android.support.v7.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class TintContextWrapper extends ContextWrapper {
    private static final Object LINE_SEPARATOR = new Object();
    private static ArrayList<WeakReference<qa>> sCache;
    private final Resources mResources;
    private final Resources.Theme mTheme;

    private TintContextWrapper(Context context) {
        super(context);
        if (TintManager.insert()) {
            this.mResources = new TintManager(this, context.getResources());
            this.mTheme = this.mResources.newTheme();
            this.mTheme.setTo(context.getTheme());
            return;
        }
        this.mResources = new TintResources(this, context.getResources());
        this.mTheme = null;
    }

    private static boolean get(Context context) {
        if ((context instanceof TintContextWrapper) || (context.getResources() instanceof TintResources) || (context.getResources() instanceof TintManager)) {
            return false;
        }
        return Build.VERSION.SDK_INT < 21 || TintManager.insert();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v0, resolved type: android.support.v7.widget.TintContextWrapper} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.Context wrap(android.content.Context r12) {
        /*
            boolean r0 = get(r12)
            if (r0 == 0) goto L_0x007a
            java.lang.Object r1 = LINE_SEPARATOR
            monitor-enter(r1)
            java.util.ArrayList<java.lang.ref.WeakReference<android.support.v7.widget.qa>> r2 = sCache     // Catch:{ Throwable -> 0x0077 }
            if (r2 != 0) goto L_0x0015
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0077 }
            r2.<init>()     // Catch:{ Throwable -> 0x0077 }
            sCache = r2     // Catch:{ Throwable -> 0x0077 }
            goto L_0x0066
        L_0x0015:
            java.util.ArrayList<java.lang.ref.WeakReference<android.support.v7.widget.qa>> r2 = sCache     // Catch:{ Throwable -> 0x0077 }
            int r3 = r2.size()     // Catch:{ Throwable -> 0x0077 }
            int r3 = r3 + -1
        L_0x001d:
            if (r3 < 0) goto L_0x0039
            java.util.ArrayList<java.lang.ref.WeakReference<android.support.v7.widget.qa>> r2 = sCache     // Catch:{ Throwable -> 0x0077 }
            java.lang.Object r4 = r2.get(r3)     // Catch:{ Throwable -> 0x0077 }
            r6 = r4
            java.lang.ref.WeakReference r6 = (java.lang.ref.WeakReference) r6     // Catch:{ Throwable -> 0x0077 }
            r5 = r6
            if (r5 == 0) goto L_0x0031
            java.lang.Object r4 = r5.get()     // Catch:{ Throwable -> 0x0077 }
            if (r4 != 0) goto L_0x0036
        L_0x0031:
            java.util.ArrayList<java.lang.ref.WeakReference<android.support.v7.widget.qa>> r2 = sCache     // Catch:{ Throwable -> 0x0077 }
            r2.remove(r3)     // Catch:{ Throwable -> 0x0077 }
        L_0x0036:
            int r3 = r3 + -1
            goto L_0x001d
        L_0x0039:
            java.util.ArrayList<java.lang.ref.WeakReference<android.support.v7.widget.qa>> r2 = sCache     // Catch:{ Throwable -> 0x0077 }
            int r3 = r2.size()     // Catch:{ Throwable -> 0x0077 }
            int r3 = r3 + -1
        L_0x0041:
            if (r3 < 0) goto L_0x0066
            java.util.ArrayList<java.lang.ref.WeakReference<android.support.v7.widget.qa>> r2 = sCache     // Catch:{ Throwable -> 0x0077 }
            java.lang.Object r4 = r2.get(r3)     // Catch:{ Throwable -> 0x0077 }
            r7 = r4
            java.lang.ref.WeakReference r7 = (java.lang.ref.WeakReference) r7     // Catch:{ Throwable -> 0x0077 }
            r5 = r7
            if (r5 == 0) goto L_0x0058
            java.lang.Object r4 = r5.get()     // Catch:{ Throwable -> 0x0077 }
            r9 = r4
            android.support.v7.widget.TintContextWrapper r9 = (android.support.v7.widget.TintContextWrapper) r9     // Catch:{ Throwable -> 0x0077 }
            r8 = r9
            goto L_0x0059
        L_0x0058:
            r8 = 0
        L_0x0059:
            if (r8 == 0) goto L_0x0063
            android.content.Context r10 = r8.getBaseContext()     // Catch:{ Throwable -> 0x0077 }
            if (r10 != r12) goto L_0x0063
            monitor-exit(r1)     // Catch:{ Throwable -> 0x0077 }
            return r8
        L_0x0063:
            int r3 = r3 + -1
            goto L_0x0041
        L_0x0066:
            android.support.v7.widget.TintContextWrapper r8 = new android.support.v7.widget.TintContextWrapper     // Catch:{ Throwable -> 0x0077 }
            r8.<init>(r12)     // Catch:{ Throwable -> 0x0077 }
            java.util.ArrayList<java.lang.ref.WeakReference<android.support.v7.widget.qa>> r2 = sCache     // Catch:{ Throwable -> 0x0077 }
            java.lang.ref.WeakReference r5 = new java.lang.ref.WeakReference     // Catch:{ Throwable -> 0x0077 }
            r5.<init>(r8)     // Catch:{ Throwable -> 0x0077 }
            r2.add(r5)     // Catch:{ Throwable -> 0x0077 }
            monitor-exit(r1)     // Catch:{ Throwable -> 0x0077 }
            return r8
        L_0x0077:
            r11 = move-exception
            monitor-exit(r1)     // Catch:{ Throwable -> 0x0077 }
            throw r11
        L_0x007a:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.TintContextWrapper.wrap(android.content.Context):android.content.Context");
    }

    public AssetManager getAssets() {
        return this.mResources.getAssets();
    }

    public Resources getResources() {
        return this.mResources;
    }

    public Resources.Theme getTheme() {
        Resources.Theme $r1 = this.mTheme;
        return $r1 == null ? super.getTheme() : $r1;
    }

    public void setTheme(int i) {
        Resources.Theme $r1 = this.mTheme;
        if ($r1 == null) {
            super.setTheme(i);
        } else {
            $r1.applyStyle(i, true);
        }
    }
}

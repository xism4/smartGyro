package android.support.p025v7.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* renamed from: android.support.v7.widget.qa */
public class C0433qa extends ContextWrapper {

    /* renamed from: a */
    private static final Object f1624a = new Object();

    /* renamed from: b */
    private static ArrayList<WeakReference<C0433qa>> f1625b;

    /* renamed from: c */
    private final Resources f1626c;

    /* renamed from: d */
    private final Resources.Theme f1627d;

    private C0433qa(Context context) {
        super(context);
        if (C0340Ga.m1474b()) {
            this.f1626c = new C0340Ga(this, context.getResources());
            this.f1627d = this.f1626c.newTheme();
            this.f1627d.setTo(context.getTheme());
            return;
        }
        this.f1626c = new C0437sa(this, context.getResources());
        this.f1627d = null;
    }

    /* renamed from: a */
    public static Context m1887a(Context context) {
        if (!m1888b(context)) {
            return context;
        }
        synchronized (f1624a) {
            if (f1625b == null) {
                f1625b = new ArrayList<>();
            } else {
                for (int size = f1625b.size() - 1; size >= 0; size--) {
                    WeakReference weakReference = f1625b.get(size);
                    if (weakReference == null || weakReference.get() == null) {
                        f1625b.remove(size);
                    }
                }
                for (int size2 = f1625b.size() - 1; size2 >= 0; size2--) {
                    WeakReference weakReference2 = f1625b.get(size2);
                    C0433qa qaVar = weakReference2 != null ? (C0433qa) weakReference2.get() : null;
                    if (qaVar != null && qaVar.getBaseContext() == context) {
                        return qaVar;
                    }
                }
            }
            C0433qa qaVar2 = new C0433qa(context);
            f1625b.add(new WeakReference(qaVar2));
            return qaVar2;
        }
    }

    /* renamed from: b */
    private static boolean m1888b(Context context) {
        if ((context instanceof C0433qa) || (context.getResources() instanceof C0437sa) || (context.getResources() instanceof C0340Ga)) {
            return false;
        }
        return Build.VERSION.SDK_INT < 21 || C0340Ga.m1474b();
    }

    public AssetManager getAssets() {
        return this.f1626c.getAssets();
    }

    public Resources getResources() {
        return this.f1626c;
    }

    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f1627d;
        return theme == null ? super.getTheme() : theme;
    }

    public void setTheme(int i) {
        Resources.Theme theme = this.f1627d;
        if (theme == null) {
            super.setTheme(i);
        } else {
            theme.applyStyle(i, true);
        }
    }
}

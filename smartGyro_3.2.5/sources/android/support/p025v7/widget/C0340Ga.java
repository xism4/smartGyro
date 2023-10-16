package android.support.p025v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import java.lang.ref.WeakReference;

/* renamed from: android.support.v7.widget.Ga */
public class C0340Ga extends Resources {

    /* renamed from: a */
    private static boolean f1228a = false;

    /* renamed from: b */
    private final WeakReference<Context> f1229b;

    public C0340Ga(Context context, Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.f1229b = new WeakReference<>(context);
    }

    /* renamed from: a */
    public static boolean m1473a() {
        return f1228a;
    }

    /* renamed from: b */
    public static boolean m1474b() {
        return m1473a() && Build.VERSION.SDK_INT <= 20;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final Drawable mo1718a(int i) {
        return super.getDrawable(i);
    }

    public Drawable getDrawable(int i) {
        Context context = (Context) this.f1229b.get();
        return context != null ? C0423o.m1851a().mo2227a(context, this, i) : super.getDrawable(i);
    }
}

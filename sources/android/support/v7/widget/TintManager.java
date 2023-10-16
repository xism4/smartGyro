package android.support.v7.widget;

import a.a.c.f.e;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import java.lang.ref.WeakReference;

public class TintManager extends Resources {
    private static boolean JAVA_1_2;
    private final WeakReference<Context> mContextRef;

    class ColorFilterLruCache extends e<Integer, PorterDuffColorFilter> {
        public ColorFilterLruCache(int i) {
            super(i);
        }

        private static int generateCacheKey(int i, PorterDuff.Mode mode) {
            return ((i + 31) * 31) + mode.hashCode();
        }

        /* JADX WARNING: type inference failed for: r4v0, types: [com.org.android.util.LruCache, android.support.v7.widget.TintManager$ColorFilterLruCache] */
        /* access modifiers changed from: package-private */
        public PorterDuffColorFilter get(int i, PorterDuff.Mode mode) {
            return (PorterDuffColorFilter) get(Integer.valueOf(generateCacheKey(i, mode)));
        }

        /* JADX WARNING: type inference failed for: r3v0, types: [com.org.android.util.LruCache, android.support.v7.widget.TintManager$ColorFilterLruCache] */
        /* access modifiers changed from: package-private */
        public PorterDuffColorFilter put(int i, PorterDuff.Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return (PorterDuffColorFilter) put(Integer.valueOf(generateCacheKey(i, mode)), porterDuffColorFilter);
        }
    }

    public TintManager(Context context, Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.mContextRef = new WeakReference(context);
    }

    public static boolean get() {
        return JAVA_1_2;
    }

    public static boolean insert() {
        return get() && Build.VERSION.SDK_INT <= 20;
    }

    public Drawable getDrawable(int i) {
        Context $r3 = (Context) this.mContextRef.get();
        return $r3 != null ? AppCompatDrawableManager.get().getDrawable($r3, this, i) : super.getDrawable(i);
    }

    /* access modifiers changed from: package-private */
    public final Drawable superGetDrawable(int i) {
        return super.getDrawable(i);
    }
}

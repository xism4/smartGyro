package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

class TintResources extends ResourcesWrapper {
    private final WeakReference<Context> mContextRef;

    public TintResources(Context context, Resources resources) {
        super(resources);
        this.mContextRef = new WeakReference(context);
    }

    public Drawable getDrawable(int i) {
        Drawable $r1 = super.getDrawable(i);
        Context $r4 = (Context) this.mContextRef.get();
        if (!($r1 == null || $r4 == null)) {
            AppCompatDrawableManager.get();
            AppCompatDrawableManager.tintDrawableUsingColorFilter($r4, i, $r1);
        }
        return $r1;
    }
}

package android.support.p025v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

/* renamed from: android.support.v7.widget.sa */
class C0437sa extends C0381X {

    /* renamed from: b */
    private final WeakReference<Context> f1638b;

    public C0437sa(Context context, Resources resources) {
        super(resources);
        this.f1638b = new WeakReference<>(context);
    }

    public Drawable getDrawable(int i) {
        Drawable drawable = super.getDrawable(i);
        Context context = (Context) this.f1638b.get();
        if (!(drawable == null || context == null)) {
            C0423o.m1851a();
            C0423o.m1858a(context, i, drawable);
        }
        return drawable;
    }
}

package android.support.p025v7.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;
import p000a.p001a.p017d.p018a.C0136a;

/* renamed from: android.support.v7.widget.y */
public class C0448y extends RatingBar {

    /* renamed from: a */
    private final C0444w f1657a;

    public C0448y(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0136a.ratingBarStyle);
    }

    public C0448y(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1657a = new C0444w(this);
        this.f1657a.mo1492a(attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        Bitmap a = this.f1657a.mo2298a();
        if (a != null) {
            setMeasuredDimension(View.resolveSizeAndState(a.getWidth() * getNumStars(), i, 0), getMeasuredHeight());
        }
    }
}

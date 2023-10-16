package p000a.p001a.p005c.p014g.p016b;

import android.view.animation.Interpolator;

/* renamed from: a.a.c.g.b.d */
abstract class C0104d implements Interpolator {

    /* renamed from: a */
    private final float[] f254a;

    /* renamed from: b */
    private final float f255b = (1.0f / ((float) (this.f254a.length - 1)));

    protected C0104d(float[] fArr) {
        this.f254a = fArr;
    }

    public float getInterpolation(float f) {
        if (f >= 1.0f) {
            return 1.0f;
        }
        if (f <= 0.0f) {
            return 0.0f;
        }
        float[] fArr = this.f254a;
        int min = Math.min((int) (((float) (fArr.length - 1)) * f), fArr.length - 2);
        float f2 = this.f255b;
        float f3 = (f - (((float) min) * f2)) / f2;
        float[] fArr2 = this.f254a;
        return fArr2[min] + (f3 * (fArr2[min + 1] - fArr2[min]));
    }
}

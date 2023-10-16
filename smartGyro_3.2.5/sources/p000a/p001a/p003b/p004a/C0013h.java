package p000a.p001a.p003b.p004a;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.animation.Interpolator;
import org.xmlpull.v1.XmlPullParser;
import p000a.p001a.p005c.p006a.p007a.C0040i;
import p000a.p001a.p005c.p008b.C0043b;

/* renamed from: a.a.b.a.h */
public class C0013h implements Interpolator {

    /* renamed from: a */
    private float[] f27a;

    /* renamed from: b */
    private float[] f28b;

    public C0013h(Context context, AttributeSet attributeSet, XmlPullParser xmlPullParser) {
        this(context.getResources(), context.getTheme(), attributeSet, xmlPullParser);
    }

    public C0013h(Resources resources, Resources.Theme theme, AttributeSet attributeSet, XmlPullParser xmlPullParser) {
        TypedArray a = C0040i.m131a(resources, theme, attributeSet, C0003a.f11l);
        m29a(a, xmlPullParser);
        a.recycle();
    }

    /* renamed from: a */
    private void m27a(float f, float f2) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.quadTo(f, f2, 1.0f, 1.0f);
        m30a(path);
    }

    /* renamed from: a */
    private void m28a(float f, float f2, float f3, float f4) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.cubicTo(f, f2, f3, f4, 1.0f, 1.0f);
        m30a(path);
    }

    /* renamed from: a */
    private void m29a(TypedArray typedArray, XmlPullParser xmlPullParser) {
        if (C0040i.m134a(xmlPullParser, "pathData")) {
            String a = C0040i.m132a(typedArray, xmlPullParser, "pathData", 4);
            Path b = C0043b.m151b(a);
            if (b != null) {
                m30a(b);
                return;
            }
            throw new InflateException("The path is null, which is created from " + a);
        } else if (!C0040i.m134a(xmlPullParser, "controlX1")) {
            throw new InflateException("pathInterpolator requires the controlX1 attribute");
        } else if (C0040i.m134a(xmlPullParser, "controlY1")) {
            float a2 = C0040i.m128a(typedArray, xmlPullParser, "controlX1", 0, 0.0f);
            float a3 = C0040i.m128a(typedArray, xmlPullParser, "controlY1", 1, 0.0f);
            boolean a4 = C0040i.m134a(xmlPullParser, "controlX2");
            if (a4 != C0040i.m134a(xmlPullParser, "controlY2")) {
                throw new InflateException("pathInterpolator requires both controlX2 and controlY2 for cubic Beziers.");
            } else if (!a4) {
                m27a(a2, a3);
            } else {
                m28a(a2, a3, C0040i.m128a(typedArray, xmlPullParser, "controlX2", 2, 0.0f), C0040i.m128a(typedArray, xmlPullParser, "controlY2", 3, 0.0f));
            }
        } else {
            throw new InflateException("pathInterpolator requires the controlY1 attribute");
        }
    }

    /* renamed from: a */
    private void m30a(Path path) {
        int i = 0;
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float length = pathMeasure.getLength();
        int min = Math.min(3000, ((int) (length / 0.002f)) + 1);
        if (min > 0) {
            this.f27a = new float[min];
            this.f28b = new float[min];
            float[] fArr = new float[2];
            for (int i2 = 0; i2 < min; i2++) {
                pathMeasure.getPosTan((((float) i2) * length) / ((float) (min - 1)), fArr, (float[]) null);
                this.f27a[i2] = fArr[0];
                this.f28b[i2] = fArr[1];
            }
            if (((double) Math.abs(this.f27a[0])) <= 1.0E-5d && ((double) Math.abs(this.f28b[0])) <= 1.0E-5d) {
                int i3 = min - 1;
                if (((double) Math.abs(this.f27a[i3] - 1.0f)) <= 1.0E-5d && ((double) Math.abs(this.f28b[i3] - 1.0f)) <= 1.0E-5d) {
                    int i4 = 0;
                    float f = 0.0f;
                    while (i < min) {
                        float[] fArr2 = this.f27a;
                        int i5 = i4 + 1;
                        float f2 = fArr2[i4];
                        if (f2 >= f) {
                            fArr2[i] = f2;
                            i++;
                            f = f2;
                            i4 = i5;
                        } else {
                            throw new IllegalArgumentException("The Path cannot loop back on itself, x :" + f2);
                        }
                    }
                    if (pathMeasure.nextContour()) {
                        throw new IllegalArgumentException("The Path should be continuous, can't have 2+ contours");
                    }
                    return;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("The Path must start at (0,0) and end at (1,1) start: ");
            sb.append(this.f27a[0]);
            sb.append(",");
            sb.append(this.f28b[0]);
            sb.append(" end:");
            int i6 = min - 1;
            sb.append(this.f27a[i6]);
            sb.append(",");
            sb.append(this.f28b[i6]);
            throw new IllegalArgumentException(sb.toString());
        }
        throw new IllegalArgumentException("The Path has a invalid length " + length);
    }

    public float getInterpolation(float f) {
        if (f <= 0.0f) {
            return 0.0f;
        }
        if (f >= 1.0f) {
            return 1.0f;
        }
        int i = 0;
        int length = this.f27a.length - 1;
        while (length - i > 1) {
            int i2 = (i + length) / 2;
            if (f < this.f27a[i2]) {
                length = i2;
            } else {
                i = i2;
            }
        }
        float[] fArr = this.f27a;
        float f2 = fArr[length] - fArr[i];
        if (f2 == 0.0f) {
            return this.f28b[i];
        }
        float[] fArr2 = this.f28b;
        float f3 = fArr2[i];
        return f3 + (((f - fArr[i]) / f2) * (fArr2[length] - f3));
    }
}

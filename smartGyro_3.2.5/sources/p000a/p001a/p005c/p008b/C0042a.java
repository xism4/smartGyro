package p000a.p001a.p005c.p008b;

import android.graphics.Color;

/* renamed from: a.a.c.b.a */
public final class C0042a {

    /* renamed from: a */
    private static final ThreadLocal<double[]> f120a = new ThreadLocal<>();

    /* renamed from: a */
    public static int m140a(int i, int i2) {
        int alpha = Color.alpha(i2);
        int alpha2 = Color.alpha(i);
        int c = m143c(alpha2, alpha);
        return Color.argb(c, m141a(Color.red(i), alpha2, Color.red(i2), alpha, c), m141a(Color.green(i), alpha2, Color.green(i2), alpha, c), m141a(Color.blue(i), alpha2, Color.blue(i2), alpha, c));
    }

    /* renamed from: a */
    private static int m141a(int i, int i2, int i3, int i4, int i5) {
        if (i5 == 0) {
            return 0;
        }
        return (((i * 255) * i2) + ((i3 * i4) * (255 - i2))) / (i5 * 255);
    }

    /* renamed from: b */
    public static int m142b(int i, int i2) {
        if (i2 >= 0 && i2 <= 255) {
            return (i & 16777215) | (i2 << 24);
        }
        throw new IllegalArgumentException("alpha must be between 0 and 255.");
    }

    /* renamed from: c */
    private static int m143c(int i, int i2) {
        return 255 - (((255 - i2) * (255 - i)) / 255);
    }
}

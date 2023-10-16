package p000a.p001a.p005c.p008b;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import p000a.p001a.p005c.p006a.p007a.C0028c;
import p000a.p001a.p005c.p006a.p007a.C0038h;
import p000a.p001a.p005c.p011d.C0064f;
import p000a.p001a.p005c.p013f.C0081e;

/* renamed from: a.a.c.b.c */
public class C0046c {

    /* renamed from: a */
    private static final C0053j f125a;

    /* renamed from: b */
    private static final C0081e<String, Typeface> f126b = new C0081e<>(16);

    static {
        int i = Build.VERSION.SDK_INT;
        f125a = i >= 28 ? new C0050g() : i >= 26 ? new C0049f() : (i < 24 || !C0048e.m167a()) ? Build.VERSION.SDK_INT >= 21 ? new C0047d() : new C0053j() : new C0048e();
    }

    /* renamed from: a */
    public static Typeface m159a(Context context, C0028c.C0029a aVar, Resources resources, int i, int i2, C0038h.C0039a aVar2, Handler handler, boolean z) {
        Typeface typeface;
        if (aVar instanceof C0028c.C0032d) {
            C0028c.C0032d dVar = (C0028c.C0032d) aVar;
            boolean z2 = false;
            if (!z ? aVar2 == null : dVar.mo165a() == 0) {
                z2 = true;
            }
            typeface = C0064f.m231a(context, dVar.mo166b(), aVar2, handler, z2, z ? dVar.mo167c() : -1, i2);
        } else {
            typeface = f125a.mo176a(context, (C0028c.C0030b) aVar, resources, i2);
            if (aVar2 != null) {
                if (typeface != null) {
                    aVar2.mo173a(typeface, handler);
                } else {
                    aVar2.mo171a(-3, handler);
                }
            }
        }
        if (typeface != null) {
            f126b.mo293a(m163b(resources, i, i2), typeface);
        }
        return typeface;
    }

    /* renamed from: a */
    public static Typeface m160a(Context context, Resources resources, int i, String str, int i2) {
        Typeface a = f125a.mo177a(context, resources, i, str, i2);
        if (a != null) {
            f126b.mo293a(m163b(resources, i, i2), a);
        }
        return a;
    }

    /* renamed from: a */
    public static Typeface m161a(Context context, CancellationSignal cancellationSignal, C0064f.C0066b[] bVarArr, int i) {
        return f125a.mo175a(context, cancellationSignal, bVarArr, i);
    }

    /* renamed from: a */
    public static Typeface m162a(Resources resources, int i, int i2) {
        return f126b.mo297b(m163b(resources, i, i2));
    }

    /* renamed from: b */
    private static String m163b(Resources resources, int i, int i2) {
        return resources.getResourcePackageName(i) + "-" + i + "-" + i2;
    }
}

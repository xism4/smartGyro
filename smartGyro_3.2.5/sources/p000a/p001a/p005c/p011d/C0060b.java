package p000a.p001a.p005c.p011d;

import android.content.Context;
import android.graphics.Typeface;
import java.util.concurrent.Callable;
import p000a.p001a.p005c.p011d.C0064f;

/* renamed from: a.a.c.d.b */
class C0060b implements Callable<C0064f.C0067c> {

    /* renamed from: a */
    final /* synthetic */ Context f146a;

    /* renamed from: b */
    final /* synthetic */ C0059a f147b;

    /* renamed from: c */
    final /* synthetic */ int f148c;

    /* renamed from: d */
    final /* synthetic */ String f149d;

    C0060b(Context context, C0059a aVar, int i, String str) {
        this.f146a = context;
        this.f147b = aVar;
        this.f148c = i;
        this.f149d = str;
    }

    public C0064f.C0067c call() {
        C0064f.C0067c a = C0064f.m229a(this.f146a, this.f147b, this.f148c);
        Typeface typeface = a.f165a;
        if (typeface != null) {
            C0064f.f153a.mo293a(this.f149d, typeface);
        }
        return a;
    }
}

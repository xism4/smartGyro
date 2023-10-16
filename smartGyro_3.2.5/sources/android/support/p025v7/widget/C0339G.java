package android.support.p025v7.widget;

import android.graphics.Typeface;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import p000a.p001a.p005c.p006a.p007a.C0038h;

/* renamed from: android.support.v7.widget.G */
class C0339G extends C0038h.C0039a {

    /* renamed from: a */
    final /* synthetic */ WeakReference f1226a;

    /* renamed from: b */
    final /* synthetic */ C0341H f1227b;

    C0339G(C0341H h, WeakReference weakReference) {
        this.f1227b = h;
        this.f1226a = weakReference;
    }

    /* renamed from: a */
    public void mo170a(int i) {
    }

    /* renamed from: a */
    public void mo172a(Typeface typeface) {
        this.f1227b.mo1726a((WeakReference<TextView>) this.f1226a, typeface);
    }
}

package p000a.p001a.p005c.p011d;

import android.os.Handler;
import java.util.concurrent.Callable;
import p000a.p001a.p005c.p011d.C0072k;

/* renamed from: a.a.c.d.i */
class C0070i implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Callable f170a;

    /* renamed from: b */
    final /* synthetic */ Handler f171b;

    /* renamed from: c */
    final /* synthetic */ C0072k.C0073a f172c;

    /* renamed from: d */
    final /* synthetic */ C0072k f173d;

    C0070i(C0072k kVar, Callable callable, Handler handler, C0072k.C0073a aVar) {
        this.f173d = kVar;
        this.f170a = callable;
        this.f171b = handler;
        this.f172c = aVar;
    }

    public void run() {
        Object obj;
        try {
            obj = this.f170a.call();
        } catch (Exception unused) {
            obj = null;
        }
        this.f171b.post(new C0069h(this, obj));
    }
}

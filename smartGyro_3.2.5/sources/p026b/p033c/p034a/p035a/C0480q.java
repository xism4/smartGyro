package p026b.p033c.p034a.p035a;

import android.os.Looper;
import java.lang.ref.WeakReference;

/* renamed from: b.c.a.a.q */
public class C0480q {

    /* renamed from: a */
    private final WeakReference<C0467e> f1736a;

    public C0480q(C0467e eVar) {
        this.f1736a = new WeakReference<>(eVar);
    }

    /* renamed from: a */
    public boolean mo2432a() {
        C0467e eVar = (C0467e) this.f1736a.get();
        return eVar == null || eVar.mo2371a();
    }

    /* renamed from: a */
    public boolean mo2433a(boolean z) {
        C0467e eVar = (C0467e) this.f1736a.get();
        if (eVar == null) {
            return false;
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return eVar.mo2372a(z);
        }
        new Thread(new C0479p(this, eVar, z)).start();
        return true;
    }

    /* renamed from: b */
    public boolean mo2434b() {
        C0467e eVar = (C0467e) this.f1736a.get();
        return eVar == null || eVar.mo2374b();
    }

    /* renamed from: c */
    public boolean mo2435c() {
        boolean z = mo2432a() || mo2434b();
        if (z) {
            this.f1736a.clear();
        }
        return z;
    }
}

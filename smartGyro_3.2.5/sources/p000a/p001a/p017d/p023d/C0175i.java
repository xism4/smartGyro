package p000a.p001a.p017d.p023d;

import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;
import p000a.p001a.p005c.p014g.C0092A;
import p000a.p001a.p005c.p014g.C0093B;
import p000a.p001a.p005c.p014g.C0134z;

/* renamed from: a.a.d.d.i */
public class C0175i {

    /* renamed from: a */
    final ArrayList<C0134z> f439a = new ArrayList<>();

    /* renamed from: b */
    private long f440b = -1;

    /* renamed from: c */
    private Interpolator f441c;

    /* renamed from: d */
    C0092A f442d;

    /* renamed from: e */
    private boolean f443e;

    /* renamed from: f */
    private final C0093B f444f = new C0174h(this);

    /* renamed from: a */
    public C0175i mo707a(long j) {
        if (!this.f443e) {
            this.f440b = j;
        }
        return this;
    }

    /* renamed from: a */
    public C0175i mo708a(C0092A a) {
        if (!this.f443e) {
            this.f442d = a;
        }
        return this;
    }

    /* renamed from: a */
    public C0175i mo709a(C0134z zVar) {
        if (!this.f443e) {
            this.f439a.add(zVar);
        }
        return this;
    }

    /* renamed from: a */
    public C0175i mo710a(C0134z zVar, C0134z zVar2) {
        this.f439a.add(zVar);
        zVar2.mo511b(zVar.mo509b());
        this.f439a.add(zVar2);
        return this;
    }

    /* renamed from: a */
    public C0175i mo711a(Interpolator interpolator) {
        if (!this.f443e) {
            this.f441c = interpolator;
        }
        return this;
    }

    /* renamed from: a */
    public void mo712a() {
        if (this.f443e) {
            Iterator<C0134z> it = this.f439a.iterator();
            while (it.hasNext()) {
                it.next().mo508a();
            }
            this.f443e = false;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo713b() {
        this.f443e = false;
    }

    /* renamed from: c */
    public void mo714c() {
        if (!this.f443e) {
            Iterator<C0134z> it = this.f439a.iterator();
            while (it.hasNext()) {
                C0134z next = it.next();
                long j = this.f440b;
                if (j >= 0) {
                    next.mo504a(j);
                }
                Interpolator interpolator = this.f441c;
                if (interpolator != null) {
                    next.mo507a(interpolator);
                }
                if (this.f442d != null) {
                    next.mo505a((C0092A) this.f444f);
                }
                next.mo512c();
            }
            this.f443e = true;
        }
    }
}

package p036c.p037a.p038a.p039a.p041b.p044c;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.p041b.p047f.C0551a;
import p036c.p037a.p038a.p039a.p048c.C0571a;
import p036c.p037a.p038a.p039a.p050e.C0610e;
import p036c.p037a.p038a.p039a.p050e.C0628i;
import p036c.p037a.p038a.p039a.p069k.C0813a;
import p036c.p037a.p038a.p039a.p069k.C0830r;
import p036c.p037a.p038a.p039a.p070l.C0844g;

/* renamed from: c.a.a.a.b.c.d */
public abstract class C0524d extends C0813a implements C0528h, C0521a, Cloneable, C0881r {

    /* renamed from: c */
    private final AtomicBoolean f1815c = new AtomicBoolean(false);

    /* renamed from: d */
    private final AtomicReference<C0571a> f1816d = new AtomicReference<>((Object) null);

    protected C0524d() {
    }

    /* renamed from: a */
    public void mo2543a(C0571a aVar) {
        if (!this.f1815c.get()) {
            this.f1816d.set(aVar);
        }
    }

    @Deprecated
    /* renamed from: a */
    public void mo2540a(C0610e eVar) {
        mo2543a((C0571a) new C0522b(this, eVar));
    }

    @Deprecated
    /* renamed from: a */
    public void mo2541a(C0628i iVar) {
        mo2543a((C0571a) new C0523c(this, iVar));
    }

    public void abort() {
        C0571a andSet;
        if (this.f1815c.compareAndSet(false, true) && (andSet = this.f1816d.getAndSet((Object) null)) != null) {
            andSet.cancel();
        }
    }

    public Object clone() {
        C0524d dVar = (C0524d) super.clone();
        dVar.f2344a = (C0830r) C0551a.m2226a(this.f2344a);
        dVar.f2345b = (C0844g) C0551a.m2226a(this.f2345b);
        return dVar;
    }

    public boolean isAborted() {
        return this.f1815c.get();
    }
}

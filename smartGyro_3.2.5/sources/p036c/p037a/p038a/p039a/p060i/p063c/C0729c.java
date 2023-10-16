package p036c.p037a.p038a.p039a.p060i.p063c;

import p036c.p037a.p038a.p039a.p050e.C0585b;
import p036c.p037a.p038a.p039a.p050e.C0636q;
import p036c.p037a.p038a.p039a.p050e.p052b.C0587b;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p072n.C0855e;

@Deprecated
/* renamed from: c.a.a.a.i.c.c */
public abstract class C0729c extends C0716a {

    /* renamed from: f */
    protected volatile C0728b f2187f;

    protected C0729c(C0585b bVar, C0728b bVar2) {
        super(bVar, bVar2.f2183b);
        this.f2187f = bVar2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public synchronized void mo2933a() {
        this.f2187f = null;
        super.mo2933a();
    }

    /* renamed from: a */
    public void mo2719a(C0587b bVar, C0855e eVar, C0844g gVar) {
        C0728b f = mo2950f();
        mo2994a(f);
        f.mo2990a(bVar, eVar, gVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo2994a(C0728b bVar) {
        if (mo2938e() || bVar == null) {
            throw new C0732f();
        }
    }

    /* renamed from: a */
    public void mo2720a(C0855e eVar, C0844g gVar) {
        C0728b f = mo2950f();
        mo2994a(f);
        f.mo2991a(eVar, gVar);
    }

    /* renamed from: a */
    public void mo2721a(boolean z, C0844g gVar) {
        C0728b f = mo2950f();
        mo2994a(f);
        f.mo2993a(z, gVar);
    }

    public void close() {
        C0728b f = mo2950f();
        if (f != null) {
            f.mo2947b();
        }
        C0636q c = mo2936c();
        if (c != null) {
            c.close();
        }
    }

    /* access modifiers changed from: protected */
    @Deprecated
    /* renamed from: f */
    public C0728b mo2950f() {
        return this.f2187f;
    }

    public C0587b getRoute() {
        C0728b f = mo2950f();
        mo2994a(f);
        if (f.f2186e == null) {
            return null;
        }
        return f.f2186e.mo2651c();
    }

    public void setState(Object obj) {
        C0728b f = mo2950f();
        mo2994a(f);
        f.mo2992a(obj);
    }

    public void shutdown() {
        C0728b f = mo2950f();
        if (f != null) {
            f.mo2947b();
        }
        C0636q c = mo2936c();
        if (c != null) {
            c.shutdown();
        }
    }
}

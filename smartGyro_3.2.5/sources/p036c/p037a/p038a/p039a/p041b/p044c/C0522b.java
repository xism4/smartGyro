package p036c.p037a.p038a.p039a.p041b.p044c;

import p036c.p037a.p038a.p039a.p048c.C0571a;
import p036c.p037a.p038a.p039a.p050e.C0610e;

/* renamed from: c.a.a.a.b.c.b */
class C0522b implements C0571a {

    /* renamed from: a */
    final /* synthetic */ C0610e f1811a;

    /* renamed from: b */
    final /* synthetic */ C0524d f1812b;

    C0522b(C0524d dVar, C0610e eVar) {
        this.f1812b = dVar;
        this.f1811a = eVar;
    }

    public boolean cancel() {
        this.f1811a.abortRequest();
        return true;
    }
}

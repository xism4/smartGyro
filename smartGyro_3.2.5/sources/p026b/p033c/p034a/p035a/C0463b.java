package p026b.p033c.p034a.p035a;

import p026b.p033c.p034a.p035a.C0465d;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0639f;
import p036c.p037a.p038a.p039a.C0837l;
import p036c.p037a.p038a.p039a.C0883t;
import p036c.p037a.p038a.p039a.C0885v;
import p036c.p037a.p038a.p039a.p072n.C0855e;

/* renamed from: b.c.a.a.b */
class C0463b implements C0885v {

    /* renamed from: a */
    final /* synthetic */ C0465d f1688a;

    C0463b(C0465d dVar) {
        this.f1688a = dVar;
    }

    /* renamed from: a */
    public void mo2353a(C0883t tVar, C0855e eVar) {
        C0576e contentEncoding;
        C0837l entity = tVar.getEntity();
        if (entity != null && (contentEncoding = entity.getContentEncoding()) != null) {
            for (C0639f name : contentEncoding.getElements()) {
                if (name.getName().equalsIgnoreCase("gzip")) {
                    tVar.mo3158a(new C0465d.C0466a(entity));
                    return;
                }
            }
        }
    }
}

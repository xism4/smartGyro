package p026b.p033c.p034a.p035a;

import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.C0882s;
import p036c.p037a.p038a.p039a.p072n.C0855e;

/* renamed from: b.c.a.a.a */
class C0462a implements C0882s {

    /* renamed from: a */
    final /* synthetic */ C0465d f1687a;

    C0462a(C0465d dVar) {
        this.f1687a = dVar;
    }

    /* renamed from: a */
    public void mo2352a(C0881r rVar, C0855e eVar) {
        if (!rVar.containsHeader("Accept-Encoding")) {
            rVar.addHeader("Accept-Encoding", "gzip");
        }
        for (String str : this.f1687a.f1694e.keySet()) {
            if (rVar.containsHeader(str)) {
                C0576e firstHeader = rVar.getFirstHeader(str);
                C0465d.f1690a.mo2422d("AsyncHttpClient", String.format("Headers were overwritten! (%s | %s) overwrites (%s | %s)", new Object[]{str, this.f1687a.f1694e.get(str), firstHeader.getName(), firstHeader.getValue()}));
                rVar.mo3119b(firstHeader);
            }
            rVar.addHeader(str, (String) this.f1687a.f1694e.get(str));
        }
    }
}

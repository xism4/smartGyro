package p036c.p037a.p038a.p039a.p072n;

import p036c.p037a.p038a.p039a.C0488D;
import p036c.p037a.p038a.p039a.C0837l;
import p036c.p037a.p038a.p039a.C0848m;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.C0882s;
import p036c.p037a.p038a.p039a.C0886w;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.n.m */
public class C0863m implements C0882s {

    /* renamed from: a */
    private final boolean f2428a;

    @Deprecated
    public C0863m() {
        this(false);
    }

    public C0863m(boolean z) {
        this.f2428a = z;
    }

    /* renamed from: a */
    public void mo2352a(C0881r rVar, C0855e eVar) {
        C0870a.m3042a(rVar, "HTTP request");
        if (!rVar.containsHeader("Expect") && (rVar instanceof C0848m)) {
            C0488D protocolVersion = rVar.getRequestLine().getProtocolVersion();
            C0837l entity = ((C0848m) rVar).getEntity();
            if (entity != null && entity.getContentLength() != 0 && !protocolVersion.mo2445c(C0886w.f2445e) && rVar.getParams().getBooleanParameter("http.protocol.expect-continue", this.f2428a)) {
                rVar.addHeader("Expect", "100-continue");
            }
        }
    }
}

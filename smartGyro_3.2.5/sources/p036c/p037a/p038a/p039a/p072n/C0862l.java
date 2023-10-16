package p036c.p037a.p038a.p039a.p072n;

import p036c.p037a.p038a.p039a.C0487C;
import p036c.p037a.p038a.p039a.C0488D;
import p036c.p037a.p038a.p039a.C0837l;
import p036c.p037a.p038a.p039a.C0848m;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.C0882s;
import p036c.p037a.p038a.p039a.C0886w;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.n.l */
public class C0862l implements C0882s {

    /* renamed from: a */
    private final boolean f2427a;

    public C0862l() {
        this(false);
    }

    public C0862l(boolean z) {
        this.f2427a = z;
    }

    /* renamed from: a */
    public void mo2352a(C0881r rVar, C0855e eVar) {
        C0870a.m3042a(rVar, "HTTP request");
        if (rVar instanceof C0848m) {
            if (this.f2427a) {
                rVar.removeHeaders("Transfer-Encoding");
                rVar.removeHeaders("Content-Length");
            } else if (rVar.containsHeader("Transfer-Encoding")) {
                throw new C0487C("Transfer-encoding header already present");
            } else if (rVar.containsHeader("Content-Length")) {
                throw new C0487C("Content-Length header already present");
            }
            C0488D protocolVersion = rVar.getRequestLine().getProtocolVersion();
            C0837l entity = ((C0848m) rVar).getEntity();
            if (entity == null) {
                rVar.addHeader("Content-Length", "0");
                return;
            }
            if (!entity.isChunked() && entity.getContentLength() >= 0) {
                rVar.addHeader("Content-Length", Long.toString(entity.getContentLength()));
            } else if (!protocolVersion.mo2445c(C0886w.f2445e)) {
                rVar.addHeader("Transfer-Encoding", "chunked");
            } else {
                throw new C0487C("Chunked transfer encoding not allowed for " + protocolVersion);
            }
            if (entity.getContentType() != null && !rVar.containsHeader("Content-Type")) {
                rVar.mo3115a(entity.getContentType());
            }
            if (entity.getContentEncoding() != null && !rVar.containsHeader("Content-Encoding")) {
                rVar.mo3115a(entity.getContentEncoding());
            }
        }
    }
}

package p036c.p037a.p038a.p039a.p041b.p046e;

import java.util.Collection;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.C0882s;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.b.e.g */
public class C0546g implements C0882s {

    /* renamed from: a */
    private final Collection<? extends C0576e> f1836a;

    public C0546g() {
        this((Collection<? extends C0576e>) null);
    }

    public C0546g(Collection<? extends C0576e> collection) {
        this.f1836a = collection;
    }

    /* renamed from: a */
    public void mo2352a(C0881r rVar, C0855e eVar) {
        C0870a.m3042a(rVar, "HTTP request");
        if (!rVar.getRequestLine().getMethod().equalsIgnoreCase("CONNECT")) {
            Collection<? extends C0576e> collection = (Collection) rVar.getParams().getParameter("http.default-headers");
            if (collection == null) {
                collection = this.f1836a;
            }
            if (collection != null) {
                for (C0576e a : collection) {
                    rVar.mo3115a(a);
                }
            }
        }
    }
}

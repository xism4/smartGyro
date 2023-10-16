package p036c.p037a.p038a.p039a.p060i.p062b;

import java.io.Closeable;
import java.net.URI;
import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.p041b.C0550f;
import p036c.p037a.p038a.p039a.p041b.C0561j;
import p036c.p037a.p038a.p039a.p041b.p044c.C0525e;
import p036c.p037a.p038a.p039a.p041b.p044c.C0532l;
import p036c.p037a.p038a.p039a.p041b.p047f.C0556e;
import p036c.p037a.p038a.p039a.p059h.C0668b;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.i.b.h */
public abstract class C0696h implements C0561j, Closeable {

    /* renamed from: a */
    public C0668b f2075a = new C0668b(getClass());

    /* renamed from: a */
    private static C0867o m2553a(C0532l lVar) {
        URI uri = lVar.getURI();
        if (!uri.isAbsolute()) {
            return null;
        }
        C0867o a = C0556e.m2252a(uri);
        if (a != null) {
            return a;
        }
        throw new C0550f("URI does not specify a valid host name: " + uri);
    }

    /* renamed from: a */
    public C0525e mo2895a(C0532l lVar, C0855e eVar) {
        C0870a.m3042a(lVar, "HTTP request");
        return mo2852a(m2553a(lVar), lVar, eVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract C0525e mo2852a(C0867o oVar, C0881r rVar, C0855e eVar);
}

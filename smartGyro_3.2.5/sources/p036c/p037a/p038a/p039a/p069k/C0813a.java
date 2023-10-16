package p036c.p037a.p038a.p039a.p069k;

import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0664h;
import p036c.p037a.p038a.p039a.C0880q;
import p036c.p037a.p038a.p039a.p070l.C0839b;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p074p.C0870a;

/* renamed from: c.a.a.a.k.a */
public abstract class C0813a implements C0880q {

    /* renamed from: a */
    protected C0830r f2344a;
    @Deprecated

    /* renamed from: b */
    protected C0844g f2345b;

    protected C0813a() {
        this((C0844g) null);
    }

    @Deprecated
    protected C0813a(C0844g gVar) {
        this.f2344a = new C0830r();
        this.f2345b = gVar;
    }

    /* renamed from: a */
    public void mo3115a(C0576e eVar) {
        this.f2344a.mo3206a(eVar);
    }

    @Deprecated
    /* renamed from: a */
    public void mo3116a(C0844g gVar) {
        C0870a.m3042a(gVar, "HTTP parameters");
        this.f2345b = gVar;
    }

    /* renamed from: a */
    public void mo3117a(C0576e[] eVarArr) {
        this.f2344a.mo3207a(eVarArr);
    }

    public void addHeader(String str, String str2) {
        C0870a.m3042a(str, "Header name");
        this.f2344a.mo3206a((C0576e) new C0814b(str, str2));
    }

    /* renamed from: b */
    public void mo3119b(C0576e eVar) {
        this.f2344a.mo3210b(eVar);
    }

    public boolean containsHeader(String str) {
        return this.f2344a.mo3208a(str);
    }

    public C0576e[] getAllHeaders() {
        return this.f2344a.mo3211b();
    }

    public C0576e getFirstHeader(String str) {
        return this.f2344a.mo3209b(str);
    }

    public C0576e[] getHeaders(String str) {
        return this.f2344a.mo3214c(str);
    }

    @Deprecated
    public C0844g getParams() {
        if (this.f2345b == null) {
            this.f2345b = new C0839b();
        }
        return this.f2345b;
    }

    public C0664h headerIterator() {
        return this.f2344a.mo3212c();
    }

    public C0664h headerIterator(String str) {
        return this.f2344a.mo3216d(str);
    }

    public void removeHeaders(String str) {
        if (str != null) {
            C0664h c = this.f2344a.mo3212c();
            while (c.hasNext()) {
                if (str.equalsIgnoreCase(c.nextHeader().getName())) {
                    c.remove();
                }
            }
        }
    }

    public void setHeader(String str, String str2) {
        C0870a.m3042a(str, "Header name");
        this.f2344a.mo3213c((C0576e) new C0814b(str, str2));
    }
}

package p036c.p037a.p038a.p039a;

import p036c.p037a.p038a.p039a.p070l.C0844g;

/* renamed from: c.a.a.a.q */
public interface C0880q {
    /* renamed from: a */
    void mo3115a(C0576e eVar);

    @Deprecated
    /* renamed from: a */
    void mo3116a(C0844g gVar);

    /* renamed from: a */
    void mo3117a(C0576e[] eVarArr);

    void addHeader(String str, String str2);

    /* renamed from: b */
    void mo3119b(C0576e eVar);

    boolean containsHeader(String str);

    C0576e[] getAllHeaders();

    C0576e getFirstHeader(String str);

    C0576e[] getHeaders(String str);

    @Deprecated
    C0844g getParams();

    C0488D getProtocolVersion();

    C0664h headerIterator();

    C0664h headerIterator(String str);

    void removeHeaders(String str);

    void setHeader(String str, String str2);
}

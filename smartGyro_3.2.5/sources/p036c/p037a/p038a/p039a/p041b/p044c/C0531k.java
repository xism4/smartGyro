package p036c.p037a.p038a.p039a.p041b.p044c;

import java.net.URI;
import p036c.p037a.p038a.p039a.C0488D;
import p036c.p037a.p038a.p039a.C0490F;
import p036c.p037a.p038a.p039a.p041b.p042a.C0516a;
import p036c.p037a.p038a.p039a.p069k.C0826n;
import p036c.p037a.p038a.p039a.p070l.C0846i;

/* renamed from: c.a.a.a.b.c.k */
public abstract class C0531k extends C0524d implements C0532l, C0526f {

    /* renamed from: e */
    private C0488D f1818e;

    /* renamed from: f */
    private URI f1819f;

    /* renamed from: g */
    private C0516a f1820g;

    /* renamed from: a */
    public C0516a mo2547a() {
        return this.f1820g;
    }

    /* renamed from: a */
    public void mo2551a(C0488D d) {
        this.f1818e = d;
    }

    /* renamed from: a */
    public void mo2552a(C0516a aVar) {
        this.f1820g = aVar;
    }

    /* renamed from: a */
    public void mo2553a(URI uri) {
        this.f1819f = uri;
    }

    public abstract String getMethod();

    public C0488D getProtocolVersion() {
        C0488D d = this.f1818e;
        return d != null ? d : C0846i.m2991b(getParams());
    }

    public C0490F getRequestLine() {
        String method = getMethod();
        C0488D protocolVersion = getProtocolVersion();
        URI uri = getURI();
        String aSCIIString = uri != null ? uri.toASCIIString() : null;
        if (aSCIIString == null || aSCIIString.isEmpty()) {
            aSCIIString = "/";
        }
        return new C0826n(method, aSCIIString, protocolVersion);
    }

    public URI getURI() {
        return this.f1819f;
    }

    public String toString() {
        return getMethod() + " " + getURI() + " " + getProtocolVersion();
    }
}

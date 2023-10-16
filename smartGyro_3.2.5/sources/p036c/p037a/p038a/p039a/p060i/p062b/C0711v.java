package p036c.p037a.p038a.p039a.p060i.p062b;

import java.net.URI;
import java.net.URISyntaxException;
import p036c.p037a.p038a.p039a.C0487C;
import p036c.p037a.p038a.p039a.C0488D;
import p036c.p037a.p038a.p039a.C0490F;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.p041b.p044c.C0532l;
import p036c.p037a.p038a.p039a.p069k.C0813a;
import p036c.p037a.p038a.p039a.p069k.C0826n;
import p036c.p037a.p038a.p039a.p070l.C0846i;
import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.i.b.v */
public class C0711v extends C0813a implements C0532l {

    /* renamed from: c */
    private final C0881r f2119c;

    /* renamed from: d */
    private URI f2120d;

    /* renamed from: e */
    private String f2121e;

    /* renamed from: f */
    private C0488D f2122f;

    /* renamed from: g */
    private int f2123g;

    public C0711v(C0881r rVar) {
        C0488D protocolVersion;
        C0870a.m3042a(rVar, "HTTP request");
        this.f2119c = rVar;
        mo3116a(rVar.getParams());
        mo3117a(rVar.getAllHeaders());
        if (rVar instanceof C0532l) {
            C0532l lVar = (C0532l) rVar;
            this.f2120d = lVar.getURI();
            this.f2121e = lVar.getMethod();
            protocolVersion = null;
        } else {
            C0490F requestLine = rVar.getRequestLine();
            try {
                this.f2120d = new URI(requestLine.getUri());
                this.f2121e = requestLine.getMethod();
                protocolVersion = rVar.getProtocolVersion();
            } catch (URISyntaxException e) {
                throw new C0487C("Invalid request URI: " + requestLine.getUri(), e);
            }
        }
        this.f2122f = protocolVersion;
        this.f2123g = 0;
    }

    /* renamed from: a */
    public void mo2923a(URI uri) {
        this.f2120d = uri;
    }

    public void abort() {
        throw new UnsupportedOperationException();
    }

    /* renamed from: b */
    public int mo2924b() {
        return this.f2123g;
    }

    /* renamed from: c */
    public C0881r mo2925c() {
        return this.f2119c;
    }

    /* renamed from: d */
    public void mo2926d() {
        this.f2123g++;
    }

    /* renamed from: e */
    public boolean mo2913e() {
        return true;
    }

    /* renamed from: f */
    public void mo2927f() {
        this.f2344a.mo3205a();
        mo3117a(this.f2119c.getAllHeaders());
    }

    public String getMethod() {
        return this.f2121e;
    }

    public C0488D getProtocolVersion() {
        if (this.f2122f == null) {
            this.f2122f = C0846i.m2991b(getParams());
        }
        return this.f2122f;
    }

    public C0490F getRequestLine() {
        C0488D protocolVersion = getProtocolVersion();
        URI uri = this.f2120d;
        String aSCIIString = uri != null ? uri.toASCIIString() : null;
        if (aSCIIString == null || aSCIIString.isEmpty()) {
            aSCIIString = "/";
        }
        return new C0826n(getMethod(), aSCIIString, protocolVersion);
    }

    public URI getURI() {
        return this.f2120d;
    }

    public boolean isAborted() {
        return false;
    }
}

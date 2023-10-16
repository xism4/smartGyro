package p036c.p037a.p038a.p039a.p060i.p063c;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import p036c.p037a.p038a.p039a.C0576e;
import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.C0881r;
import p036c.p037a.p038a.p039a.C0883t;
import p036c.p037a.p038a.p039a.C0884u;
import p036c.p037a.p038a.p039a.p050e.C0635p;
import p036c.p037a.p038a.p039a.p050e.C0636q;
import p036c.p037a.p038a.p039a.p059h.C0668b;
import p036c.p037a.p038a.p039a.p060i.C0788f;
import p036c.p037a.p038a.p039a.p068j.C0807c;
import p036c.p037a.p038a.p039a.p068j.C0810f;
import p036c.p037a.p038a.p039a.p068j.C0811g;
import p036c.p037a.p038a.p039a.p069k.C0834v;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p070l.C0846i;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p074p.C0870a;

@Deprecated
/* renamed from: c.a.a.a.i.c.g */
public class C0733g extends C0788f implements C0636q, C0635p, C0855e {

    /* renamed from: k */
    public C0668b f2198k = new C0668b(C0733g.class);

    /* renamed from: l */
    public C0668b f2199l = new C0668b("cz.msebera.android.httpclient.headers");

    /* renamed from: m */
    public C0668b f2200m = new C0668b("cz.msebera.android.httpclient.wire");

    /* renamed from: n */
    private volatile Socket f2201n;

    /* renamed from: o */
    private C0867o f2202o;

    /* renamed from: p */
    private boolean f2203p;

    /* renamed from: q */
    private volatile boolean f2204q;

    /* renamed from: r */
    private final Map<String, Object> f2205r = new HashMap();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0807c<C0883t> mo2820a(C0810f fVar, C0884u uVar, C0844g gVar) {
        return new C0735i(fVar, (C0834v) null, uVar, gVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0810f mo3000a(Socket socket, int i, C0844g gVar) {
        if (i <= 0) {
            i = 8192;
        }
        C0810f a = super.mo3000a(socket, i, gVar);
        return this.f2200m.mo2805a() ? new C0740n(a, new C0745s(this.f2200m), C0846i.m2988a(gVar)) : a;
    }

    /* renamed from: a */
    public void mo2814a(C0881r rVar) {
        if (this.f2198k.mo2805a()) {
            this.f2198k.mo2803a("Sending request: " + rVar.getRequestLine());
        }
        super.mo2814a(rVar);
        if (this.f2199l.mo2805a()) {
            this.f2199l.mo2803a(">> " + rVar.getRequestLine().toString());
            for (C0576e eVar : rVar.getAllHeaders()) {
                this.f2199l.mo2803a(">> " + eVar.toString());
            }
        }
    }

    /* renamed from: a */
    public void mo2727a(Socket socket, C0867o oVar) {
        mo3056g();
        this.f2201n = socket;
        this.f2202o = oVar;
        if (this.f2204q) {
            socket.close();
            throw new InterruptedIOException("Connection already shutdown");
        }
    }

    /* renamed from: a */
    public void mo2728a(Socket socket, C0867o oVar, boolean z, C0844g gVar) {
        mo2822a();
        C0870a.m3042a(oVar, "Target host");
        C0870a.m3042a(gVar, "Parameters");
        if (socket != null) {
            this.f2201n = socket;
            mo3055a(socket, gVar);
        }
        this.f2202o = oVar;
        this.f2203p = z;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public C0811g mo3001b(Socket socket, int i, C0844g gVar) {
        if (i <= 0) {
            i = 8192;
        }
        C0811g b = super.mo3001b(socket, i, gVar);
        return this.f2200m.mo2805a() ? new C0741o(b, new C0745s(this.f2200m), C0846i.m2988a(gVar)) : b;
    }

    /* renamed from: b */
    public void mo2729b(boolean z, C0844g gVar) {
        C0870a.m3042a(gVar, "Parameters");
        mo3056g();
        this.f2203p = z;
        mo3055a(this.f2201n, gVar);
    }

    public void close() {
        try {
            super.close();
            if (this.f2198k.mo2805a()) {
                C0668b bVar = this.f2198k;
                bVar.mo2803a("Connection " + this + " closed");
            }
        } catch (IOException e) {
            this.f2198k.mo2804a("I/O error closing connection", e);
        }
    }

    public Object getAttribute(String str) {
        return this.f2205r.get(str);
    }

    public SSLSession getSSLSession() {
        if (this.f2201n instanceof SSLSocket) {
            return ((SSLSocket) this.f2201n).getSession();
        }
        return null;
    }

    public final Socket getSocket() {
        return this.f2201n;
    }

    public final boolean isSecure() {
        return this.f2203p;
    }

    public C0883t receiveResponseHeader() {
        C0883t receiveResponseHeader = super.receiveResponseHeader();
        if (this.f2198k.mo2805a()) {
            this.f2198k.mo2803a("Receiving response: " + receiveResponseHeader.getStatusLine());
        }
        if (this.f2199l.mo2805a()) {
            this.f2199l.mo2803a("<< " + receiveResponseHeader.getStatusLine().toString());
            for (C0576e eVar : receiveResponseHeader.getAllHeaders()) {
                this.f2199l.mo2803a("<< " + eVar.toString());
            }
        }
        return receiveResponseHeader;
    }

    public void setAttribute(String str, Object obj) {
        this.f2205r.put(str, obj);
    }

    public void shutdown() {
        this.f2204q = true;
        try {
            super.shutdown();
            if (this.f2198k.mo2805a()) {
                C0668b bVar = this.f2198k;
                bVar.mo2803a("Connection " + this + " shut down");
            }
            Socket socket = this.f2201n;
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            this.f2198k.mo2804a("I/O error shutting down connection", e);
        }
    }
}

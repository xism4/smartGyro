package p036c.p037a.p038a.p039a.p050e.p055e;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import p036c.p037a.p038a.p039a.C0867o;
import p036c.p037a.p038a.p039a.p050e.C0632m;
import p036c.p037a.p038a.p039a.p050e.p053c.C0595a;
import p036c.p037a.p038a.p039a.p050e.p053c.C0596b;
import p036c.p037a.p038a.p039a.p050e.p053c.C0597c;
import p036c.p037a.p038a.p039a.p050e.p053c.C0600f;
import p036c.p037a.p038a.p039a.p050e.p054d.C0609b;
import p036c.p037a.p038a.p039a.p070l.C0842e;
import p036c.p037a.p038a.p039a.p070l.C0844g;
import p036c.p037a.p038a.p039a.p072n.C0855e;
import p036c.p037a.p038a.p039a.p074p.C0870a;
import p036c.p037a.p038a.p039a.p074p.C0871b;

@Deprecated
/* renamed from: c.a.a.a.e.e.i */
public class C0620i implements C0609b, C0600f, C0596b, C0597c {

    /* renamed from: a */
    public static final C0623l f1926a = new C0612b();

    /* renamed from: b */
    public static final C0623l f1927b = new C0613c();

    /* renamed from: c */
    public static final C0623l f1928c = new C0621j();

    /* renamed from: d */
    private final SSLSocketFactory f1929d;

    /* renamed from: e */
    private final C0595a f1930e;

    /* renamed from: f */
    private volatile C0623l f1931f;

    /* renamed from: g */
    private final String[] f1932g;

    /* renamed from: h */
    private final String[] f1933h;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C0620i(java.security.KeyStore r2) {
        /*
            r1 = this;
            c.a.a.a.e.e.f r0 = p036c.p037a.p038a.p039a.p050e.p055e.C0618g.m2364b()
            r0.mo2692a(r2)
            javax.net.ssl.SSLContext r2 = r0.mo2694a()
            c.a.a.a.e.e.l r0 = f1927b
            r1.<init>(r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p036c.p037a.p038a.p039a.p050e.p055e.C0620i.<init>(java.security.KeyStore):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public C0620i(SSLContext sSLContext, C0623l lVar) {
        this(sSLContext.getSocketFactory(), (String[]) null, (String[]) null, lVar);
        C0870a.m3042a(sSLContext, "SSL context");
    }

    public C0620i(SSLSocketFactory sSLSocketFactory, String[] strArr, String[] strArr2, C0623l lVar) {
        C0870a.m3042a(sSLSocketFactory, "SSL socket factory");
        this.f1929d = sSLSocketFactory;
        this.f1932g = strArr;
        this.f1933h = strArr2;
        this.f1931f = lVar == null ? f1927b : lVar;
        this.f1930e = null;
    }

    /* renamed from: a */
    public static C0620i m2365a() {
        return new C0620i(C0618g.m2363a(), f1927b);
    }

    /* renamed from: a */
    private void m2366a(SSLSocket sSLSocket, String str) {
        try {
            this.f1931f.verify(str, sSLSocket);
        } catch (IOException e) {
            try {
                sSLSocket.close();
            } catch (Exception unused) {
            }
            throw e;
        }
    }

    /* renamed from: b */
    private void m2367b(SSLSocket sSLSocket) {
        String[] strArr = this.f1932g;
        if (strArr != null) {
            sSLSocket.setEnabledProtocols(strArr);
        }
        String[] strArr2 = this.f1933h;
        if (strArr2 != null) {
            sSLSocket.setEnabledCipherSuites(strArr2);
        }
        mo2702a(sSLSocket);
    }

    /* renamed from: a */
    public Socket mo2698a(int i, Socket socket, C0867o oVar, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, C0855e eVar) {
        C0870a.m3042a(oVar, "HTTP host");
        C0870a.m3042a(inetSocketAddress, "Remote address");
        if (socket == null) {
            socket = mo2699a(eVar);
        }
        if (inetSocketAddress2 != null) {
            socket.bind(inetSocketAddress2);
        }
        try {
            socket.connect(inetSocketAddress, i);
            if (!(socket instanceof SSLSocket)) {
                return mo2700a(socket, oVar.mo3271b(), inetSocketAddress.getPort(), eVar);
            }
            SSLSocket sSLSocket = (SSLSocket) socket;
            sSLSocket.startHandshake();
            m2366a(sSLSocket, oVar.mo3271b());
            return socket;
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException unused) {
            }
            throw e;
        }
    }

    /* renamed from: a */
    public Socket mo2660a(C0844g gVar) {
        return mo2699a((C0855e) null);
    }

    /* renamed from: a */
    public Socket mo2699a(C0855e eVar) {
        SSLSocket sSLSocket = (SSLSocket) this.f1929d.createSocket();
        m2367b(sSLSocket);
        return sSLSocket;
    }

    /* renamed from: a */
    public Socket mo2672a(Socket socket, String str, int i, C0844g gVar) {
        return mo2700a(socket, str, i, (C0855e) null);
    }

    /* renamed from: a */
    public Socket mo2700a(Socket socket, String str, int i, C0855e eVar) {
        SSLSocket sSLSocket = (SSLSocket) this.f1929d.createSocket(socket, str, i, true);
        m2367b(sSLSocket);
        sSLSocket.startHandshake();
        m2366a(sSLSocket, str);
        return sSLSocket;
    }

    /* renamed from: a */
    public Socket mo2661a(Socket socket, String str, int i, InetAddress inetAddress, int i2, C0844g gVar) {
        C0595a aVar = this.f1930e;
        InetAddress resolve = aVar != null ? aVar.resolve(str) : InetAddress.getByName(str);
        InetSocketAddress inetSocketAddress = null;
        if (inetAddress != null || i2 > 0) {
            if (i2 <= 0) {
                i2 = 0;
            }
            inetSocketAddress = new InetSocketAddress(inetAddress, i2);
        }
        return mo2662a(socket, (InetSocketAddress) new C0632m(new C0867o(str, i), resolve, i), inetSocketAddress, gVar);
    }

    /* renamed from: a */
    public Socket mo2659a(Socket socket, String str, int i, boolean z) {
        return mo2700a(socket, str, i, (C0855e) null);
    }

    /* renamed from: a */
    public Socket mo2662a(Socket socket, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, C0844g gVar) {
        C0870a.m3042a(inetSocketAddress, "Remote address");
        C0870a.m3042a(gVar, "HTTP parameters");
        C0867o a = inetSocketAddress instanceof C0632m ? ((C0632m) inetSocketAddress).mo2716a() : new C0867o(inetSocketAddress.getHostName(), inetSocketAddress.getPort(), "https");
        int d = C0842e.m2984d(gVar);
        int a2 = C0842e.m2977a(gVar);
        socket.setSoTimeout(d);
        return mo2698a(a2, socket, a, inetSocketAddress, inetSocketAddress2, (C0855e) null);
    }

    /* renamed from: a */
    public void mo2701a(C0623l lVar) {
        C0870a.m3042a(lVar, "Hostname verifier");
        this.f1931f = lVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo2702a(SSLSocket sSLSocket) {
    }

    public Socket createSocket() {
        return mo2699a((C0855e) null);
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) {
        return mo2659a(socket, str, i, z);
    }

    public boolean isSecure(Socket socket) {
        C0870a.m3042a(socket, "Socket");
        C0871b.m3050a(socket instanceof SSLSocket, "Socket not created by this factory");
        C0871b.m3050a(!socket.isClosed(), "Socket is closed");
        return true;
    }
}

package cz.msebera.android.http.conn.ssl;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.conn.HttpInetSocketAddress;
import cz.msebera.android.http.conn.scheme.HostNameResolver;
import cz.msebera.android.http.conn.scheme.LayeredSocketFactory;
import cz.msebera.android.http.conn.scheme.SchemeLayeredSocketFactory;
import cz.msebera.android.http.conn.util.LayeredSchemeSocketFactory;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import cz.msebera.android.http.util.HttpConnectionParams;
import cz.msebera.android.http.util.HttpParams;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;

@Deprecated
public class SSLSocketFactory implements LayeredSchemeSocketFactory, SchemeLayeredSocketFactory, cz.msebera.android.http.conn.scheme.LayeredSchemeSocketFactory, LayeredSocketFactory {
    public static final X509HostnameVerifier ALLOW_ALL_HOSTNAME_VERIFIER = new StrictHostnameVerifier();
    public static final X509HostnameVerifier BROWSER_COMPATIBLE_HOSTNAME_VERIFIER = new BrowserCompatHostnameVerifier();
    public static final X509HostnameVerifier STRICT_HOSTNAME_VERIFIER = new AllowAllHostnameVerifier();
    private volatile X509HostnameVerifier hostnameVerifier;
    private final HostNameResolver nameResolver;
    private final javax.net.ssl.SSLSocketFactory socketfactory;
    private final String[] supportedCipherSuites;
    private final String[] supportedProtocols;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SSLSocketFactory(java.security.KeyStore r4) {
        /*
            r3 = this;
            cz.msebera.android.http.conn.ssl.SSLContextBuilder r0 = cz.msebera.android.http.conn.ssl.SSLContexts.custom()
            r0.loadTrustMaterial(r4)
            javax.net.ssl.SSLContext r1 = r0.build()
            cz.msebera.android.http.conn.ssl.X509HostnameVerifier r2 = BROWSER_COMPATIBLE_HOSTNAME_VERIFIER
            r3.<init>(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.conn.ssl.SSLSocketFactory.<init>(java.security.KeyStore):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SSLSocketFactory(SSLContext $r3, X509HostnameVerifier x509HostnameVerifier) {
        this($r3.getSocketFactory(), (String[]) null, (String[]) null, x509HostnameVerifier);
        Args.notNull($r3, "SSL context");
    }

    public SSLSocketFactory(javax.net.ssl.SSLSocketFactory $r3, String[] strArr, String[] strArr2, X509HostnameVerifier $r4) {
        Args.notNull($r3, "SSL socket factory");
        this.socketfactory = $r3;
        this.supportedProtocols = strArr;
        this.supportedCipherSuites = strArr2;
        this.hostnameVerifier = $r4 == null ? BROWSER_COMPATIBLE_HOSTNAME_VERIFIER : $r4;
        this.nameResolver = null;
    }

    public static SSLSocketFactory getSocketFactory() {
        return new SSLSocketFactory(SSLContexts.createDefault(), BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    private void internalPrepareSocket(SSLSocket sSLSocket) {
        String[] $r2 = this.supportedProtocols;
        if ($r2 != null) {
            sSLSocket.setEnabledProtocols($r2);
        }
        String[] $r22 = this.supportedCipherSuites;
        if ($r22 != null) {
            sSLSocket.setEnabledCipherSuites($r22);
        }
        prepareSocket(sSLSocket);
    }

    private void verifyHostname(SSLSocket sSLSocket, String str) {
        try {
            this.hostnameVerifier.verify(str, sSLSocket);
        } catch (IOException $r5) {
            try {
                sSLSocket.close();
            } catch (Exception e) {
            }
            throw $r5;
        }
    }

    public Socket connectSocket(int i, Socket $r5, HttpHost httpHost, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, HttpContext httpContext) {
        Args.notNull(httpHost, "HTTP host");
        Args.notNull(inetSocketAddress, "Remote address");
        if ($r5 == null) {
            $r5 = createSocket(httpContext);
        }
        if (inetSocketAddress2 != null) {
            $r5.bind(inetSocketAddress2);
        }
        try {
            $r5.connect(inetSocketAddress, i);
            if (!($r5 instanceof SSLSocket)) {
                return createLayeredSocket($r5, httpHost.getHostName(), inetSocketAddress.getPort(), httpContext);
            }
            SSLSocket $r7 = (SSLSocket) $r5;
            $r7.startHandshake();
            verifyHostname($r7, httpHost.getHostName());
            return $r5;
        } catch (IOException $r9) {
            try {
                $r5.close();
            } catch (IOException e) {
            }
            throw $r9;
        }
    }

    public Socket connectSocket(Socket socket, String str, int i, InetAddress inetAddress, int $i1, HttpParams httpParams) {
        HostNameResolver $r5 = this.nameResolver;
        InetAddress $r6 = $r5 != null ? $r5.resolve(str) : InetAddress.getByName(str);
        InetSocketAddress $r7 = null;
        if (inetAddress != null || $i1 > 0) {
            if ($i1 <= 0) {
                $i1 = 0;
            }
            $r7 = new InetSocketAddress(inetAddress, $i1);
        }
        return connectSocket(socket, new HttpInetSocketAddress(new HttpHost(str, i), $r6, i), $r7, httpParams);
    }

    public Socket connectSocket(Socket socket, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, HttpParams httpParams) {
        Args.notNull(inetSocketAddress, "Remote address");
        Args.notNull(httpParams, "HTTP parameters");
        HttpHost $r6 = inetSocketAddress instanceof HttpInetSocketAddress ? ((HttpInetSocketAddress) inetSocketAddress).getHttpHost() : new HttpHost(inetSocketAddress.getHostName(), inetSocketAddress.getPort(), "https");
        int $i0 = HttpConnectionParams.getSoTimeout(httpParams);
        int $i1 = HttpConnectionParams.getConnectionTimeout(httpParams);
        socket.setSoTimeout($i0);
        return connectSocket($i1, socket, $r6, inetSocketAddress, inetSocketAddress2, (HttpContext) null);
    }

    public Socket createLayeredSocket(Socket socket, String str, int i, HttpContext httpContext) {
        SSLSocket $r5 = (SSLSocket) this.socketfactory.createSocket(socket, str, i, true);
        internalPrepareSocket($r5);
        $r5.startHandshake();
        verifyHostname($r5, str);
        return $r5;
    }

    public Socket createLayeredSocket(Socket socket, String str, int i, HttpParams httpParams) {
        return createLayeredSocket(socket, str, i, (HttpContext) null);
    }

    public Socket createLayeredSocket(Socket socket, String str, int i, boolean z) {
        return createLayeredSocket(socket, str, i, (HttpContext) null);
    }

    public Socket createSocket() {
        return createSocket((HttpContext) null);
    }

    public Socket createSocket(HttpContext httpContext) {
        SSLSocket $r4 = (SSLSocket) this.socketfactory.createSocket();
        internalPrepareSocket($r4);
        return $r4;
    }

    public Socket createSocket(HttpParams httpParams) {
        return createSocket((HttpContext) null);
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) {
        return createLayeredSocket(socket, str, i, z);
    }

    public boolean isSecure(Socket socket) {
        Args.notNull(socket, "Socket");
        Asserts.check(socket instanceof SSLSocket, "Socket not created by this factory");
        Asserts.check(!socket.isClosed(), "Socket is closed");
        return true;
    }

    /* access modifiers changed from: protected */
    public void prepareSocket(SSLSocket sSLSocket) {
    }

    public void setHostnameVerifier(X509HostnameVerifier x509HostnameVerifier) {
        Args.notNull(x509HostnameVerifier, "Hostname verifier");
        this.hostnameVerifier = x509HostnameVerifier;
    }
}

package cz.msebera.android.http.impl.conn;

import c.a.a.a.i.c.h;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.conn.ClientConnectionOperator;
import cz.msebera.android.http.conn.DnsResolver;
import cz.msebera.android.http.conn.OperatedClientConnection;
import cz.msebera.android.http.conn.scheme.Scheme;
import cz.msebera.android.http.conn.scheme.SchemeLayeredSocketFactory;
import cz.msebera.android.http.conn.scheme.SchemeRegistry;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import cz.msebera.android.http.util.HttpConnectionParams;
import cz.msebera.android.http.util.HttpParams;
import java.net.InetAddress;
import java.net.Socket;

@Deprecated
public class DefaultClientConnectionOperator implements ClientConnectionOperator {
    protected final DnsResolver dnsResolver;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(h.class);
    protected final SchemeRegistry schemeRegistry;

    public DefaultClientConnectionOperator(SchemeRegistry schemeRegistry2) {
        Args.notNull(schemeRegistry2, "Scheme registry");
        this.schemeRegistry = schemeRegistry2;
        this.dnsResolver = new SystemDefaultDnsResolver();
    }

    private SchemeRegistry getSchemeRegistry(HttpContext httpContext) {
        SchemeRegistry $r3 = (SchemeRegistry) httpContext.getAttribute("http.scheme-registry");
        return $r3 == null ? this.schemeRegistry : $r3;
    }

    public OperatedClientConnection createConnection() {
        return new DefaultClientConnection();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ff, code lost:
        r0 = r27.log;
        r21 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0109, code lost:
        if (r0.isDebugEnabled() == false) goto L_0x013f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x010b, code lost:
        r21 = r27.log;
        r21.debug("Connect to " + r0 + " timed out. " + "Connection will be retried using another IP address");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x013f, code lost:
        r13 = r13 + 1;
        r4 = r30;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void openConnection(cz.msebera.android.http.conn.OperatedClientConnection r28, cz.msebera.android.http.HttpHost r29, java.net.InetAddress r30, cz.msebera.android.http.execchain.HttpContext r31, cz.msebera.android.http.util.HttpParams r32) {
        /*
            r27 = this;
            r4 = r30
            java.lang.String r5 = "Connection"
            r0 = r28
            cz.msebera.android.http.mime.Args.notNull(r0, r5)
            java.lang.String r5 = "Target host"
            r0 = r29
            cz.msebera.android.http.mime.Args.notNull(r0, r5)
            java.lang.String r5 = "HTTP parameters"
            r0 = r32
            cz.msebera.android.http.mime.Args.notNull(r0, r5)
            r0 = r28
            boolean r6 = r0.isOpen()
            if (r6 != 0) goto L_0x0021
            r6 = 1
            goto L_0x0022
        L_0x0021:
            r6 = 0
        L_0x0022:
            java.lang.String r5 = "Connection must not be open"
            cz.msebera.android.http.mime.Asserts.check(r6, r5)
            r0 = r27
            r1 = r31
            cz.msebera.android.http.conn.scheme.SchemeRegistry r7 = r0.getSchemeRegistry(r1)
            r0 = r29
            java.lang.String r8 = r0.getSchemeName()
            cz.msebera.android.http.conn.scheme.Scheme r9 = r7.getScheme((java.lang.String) r8)
            cz.msebera.android.http.conn.scheme.SchemeSocketFactory r10 = r9.getSchemeSocketFactory()
            r0 = r29
            java.lang.String r8 = r0.getHostName()
            r0 = r27
            java.net.InetAddress[] r11 = r0.resolveHostname(r8)
            r0 = r29
            int r12 = r0.getPort()
            int r12 = r9.resolvePort(r12)
            r13 = 0
        L_0x0054:
            int r14 = r11.length
            if (r13 >= r14) goto L_0x0147
            r15 = r11[r13]
            int r14 = r11.length
            r16 = 1
            r0 = r16
            int r14 = r14 - r0
            if (r13 != r14) goto L_0x0063
            r6 = 1
            goto L_0x0064
        L_0x0063:
            r6 = 0
        L_0x0064:
            r0 = r32
            java.net.Socket r17 = r10.createSocket(r0)
            r18 = r17
            r0 = r28
            r1 = r17
            r2 = r29
            r0.opening(r1, r2)
            cz.msebera.android.http.conn.HttpInetSocketAddress r19 = new cz.msebera.android.http.conn.HttpInetSocketAddress
            r0 = r19
            r1 = r29
            r0.<init>(r1, r15, r12)
            r20 = 0
            if (r4 == 0) goto L_0x008d
            java.net.InetSocketAddress r20 = new java.net.InetSocketAddress
            r16 = 0
            r0 = r20
            r1 = r16
            r0.<init>(r4, r1)
        L_0x008d:
            r0 = r27
            cz.msebera.android.http.cache.HttpClientAndroidLog r0 = r0.log
            r21 = r0
            boolean r22 = r0.isDebugEnabled()
            if (r22 == 0) goto L_0x00bf
            r0 = r27
            cz.msebera.android.http.cache.HttpClientAndroidLog r0 = r0.log
            r21 = r0
            java.lang.StringBuilder r23 = new java.lang.StringBuilder
            r0 = r23
            r0.<init>()
            java.lang.String r5 = "Connecting to "
            r0 = r23
            r0.append(r5)
            r0 = r23
            r1 = r19
            r0.append(r1)
            r0 = r23
            java.lang.String r8 = r0.toString()
            r0 = r21
            r0.debug(r8)
        L_0x00bf:
            r0 = r17
            r1 = r19
            r2 = r20
            r3 = r32
            java.net.Socket r24 = r10.connectSocket(r0, r1, r2, r3)     // Catch:{ ConnectException -> 0x00fc, ConnectTimeoutException -> 0x00f7 }
            r0 = r17
            r1 = r24
            if (r0 == r1) goto L_0x00dc
            r0 = r28
            r1 = r24
            r2 = r29
            r0.opening(r1, r2)     // Catch:{ ConnectException -> 0x00fc, ConnectTimeoutException -> 0x00f7 }
            r18 = r24
        L_0x00dc:
            r0 = r27
            r1 = r18
            r2 = r31
            r3 = r32
            r0.prepareSocket(r1, r2, r3)     // Catch:{ ConnectException -> 0x00fc, ConnectTimeoutException -> 0x00f7 }
            r0 = r18
            boolean r22 = r10.isSecure(r0)     // Catch:{ ConnectException -> 0x00fc, ConnectTimeoutException -> 0x00f7 }
            r0 = r28
            r1 = r22
            r2 = r32
            r0.openCompleted(r1, r2)     // Catch:{ ConnectException -> 0x00fc, ConnectTimeoutException -> 0x00f7 }
            return
        L_0x00f7:
            r25 = move-exception
            if (r6 != 0) goto L_0x00fb
            goto L_0x00ff
        L_0x00fb:
            throw r25
        L_0x00fc:
            r26 = move-exception
            if (r6 != 0) goto L_0x0146
        L_0x00ff:
            r0 = r27
            cz.msebera.android.http.cache.HttpClientAndroidLog r0 = r0.log
            r21 = r0
            boolean r6 = r0.isDebugEnabled()
            if (r6 == 0) goto L_0x013f
            r0 = r27
            cz.msebera.android.http.cache.HttpClientAndroidLog r0 = r0.log
            r21 = r0
            java.lang.StringBuilder r23 = new java.lang.StringBuilder
            r0 = r23
            r0.<init>()
            java.lang.String r5 = "Connect to "
            r0 = r23
            r0.append(r5)
            r0 = r23
            r1 = r19
            r0.append(r1)
            java.lang.String r5 = " timed out. "
            r0 = r23
            r0.append(r5)
            java.lang.String r5 = "Connection will be retried using another IP address"
            r0 = r23
            r0.append(r5)
            r0 = r23
            java.lang.String r8 = r0.toString()
            r0 = r21
            r0.debug(r8)
        L_0x013f:
            int r13 = r13 + 1
            r4 = r30
            goto L_0x0054
        L_0x0146:
            throw r26
        L_0x0147:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.impl.conn.DefaultClientConnectionOperator.openConnection(cz.msebera.android.http.conn.OperatedClientConnection, cz.msebera.android.http.HttpHost, java.net.InetAddress, cz.msebera.android.http.execchain.HttpContext, cz.msebera.android.http.util.HttpParams):void");
    }

    /* access modifiers changed from: protected */
    public void prepareSocket(Socket socket, HttpContext httpContext, HttpParams httpParams) {
        socket.setTcpNoDelay(HttpConnectionParams.getTcpNoDelay(httpParams));
        socket.setSoTimeout(HttpConnectionParams.getSoTimeout(httpParams));
        int $i0 = HttpConnectionParams.getLinger(httpParams);
        if ($i0 >= 0) {
            socket.setSoLinger($i0 > 0, $i0);
        }
    }

    /* access modifiers changed from: protected */
    public InetAddress[] resolveHostname(String str) {
        return this.dnsResolver.resolve(str);
    }

    public void updateSecureConnection(OperatedClientConnection operatedClientConnection, HttpHost httpHost, HttpContext httpContext, HttpParams httpParams) {
        Args.notNull(operatedClientConnection, "Connection");
        Args.notNull(httpHost, "Target host");
        Args.notNull(httpParams, "Parameters");
        Asserts.check(operatedClientConnection.isOpen(), "Connection must be open");
        Scheme $r7 = getSchemeRegistry(httpContext).getScheme(httpHost.getSchemeName());
        Asserts.check($r7.getSchemeSocketFactory() instanceof SchemeLayeredSocketFactory, "Socket factory must implement SchemeLayeredSocketFactory");
        SchemeLayeredSocketFactory $r9 = (SchemeLayeredSocketFactory) $r7.getSchemeSocketFactory();
        Socket $r10 = $r9.createLayeredSocket(operatedClientConnection.getSocket(), httpHost.getHostName(), $r7.resolvePort(httpHost.getPort()), httpParams);
        prepareSocket($r10, httpContext, httpParams);
        operatedClientConnection.update($r10, httpHost, $r9.isSecure($r10), httpParams);
    }
}

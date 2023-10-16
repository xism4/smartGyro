package cz.msebera.android.http.impl.conn;

import c.a.a.a.i.c.e;
import cz.msebera.android.http.HttpClientConnection;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.conn.ClientConnectionManager;
import cz.msebera.android.http.conn.ClientConnectionOperator;
import cz.msebera.android.http.conn.ClientConnectionRequest;
import cz.msebera.android.http.conn.ManagedClientConnection;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.conn.scheme.SchemeRegistry;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Deprecated
public class BasicClientConnectionManager implements ClientConnectionManager {
    private static final AtomicLong COUNTER = new AtomicLong();
    private ManagedClientConnectionImpl conn;
    private final ClientConnectionOperator connOperator;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(e.class);
    private HttpPoolEntry poolEntry;
    private final SchemeRegistry schemeRegistry;
    private volatile boolean shutdown;

    public BasicClientConnectionManager(SchemeRegistry schemeRegistry2) {
        Args.notNull(schemeRegistry2, "Scheme registry");
        this.schemeRegistry = schemeRegistry2;
        this.connOperator = createConnectionOperator(schemeRegistry2);
    }

    private void assertNotShutdown() {
        Asserts.check(!this.shutdown, "Connection manager has been shut down");
    }

    private void shutdownConnection(HttpClientConnection httpClientConnection) {
        try {
            httpClientConnection.shutdown();
        } catch (IOException $r2) {
            if (this.log.isDebugEnabled()) {
                this.log.debug("I/O exception shutting down connection", $r2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public ClientConnectionOperator createConnectionOperator(SchemeRegistry schemeRegistry2) {
        return new DefaultClientConnectionOperator(schemeRegistry2);
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        try {
            shutdown();
        } finally {
            super.finalize();
        }
    }

    /* access modifiers changed from: package-private */
    public ManagedClientConnection getConnection(HttpRoute httpRoute, Object obj) {
        ManagedClientConnectionImpl $r7;
        Args.notNull(httpRoute, "Route");
        synchronized (this) {
            assertNotShutdown();
            if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog $r1 = this.log;
                $r1.debug("Get connection for route " + httpRoute);
            }
            Asserts.check(this.conn == null, "Invalid use of BasicClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.");
            if (this.poolEntry != null && !this.poolEntry.getPlannedRoute().equals(httpRoute)) {
                this.poolEntry.close();
                this.poolEntry = null;
            }
            if (this.poolEntry == null) {
                String $r6 = Long.toString(COUNTER.getAndIncrement());
                ClientConnectionOperator $r11 = this.connOperator;
                ClientConnectionOperator clientConnectionOperator = $r11;
                this.poolEntry = new HttpPoolEntry(this.log, $r6, httpRoute, $r11.createConnection(), 0, TimeUnit.MILLISECONDS);
            }
            if (this.poolEntry.isExpired(System.currentTimeMillis())) {
                this.poolEntry.close();
                this.poolEntry.getTracker().reset();
            }
            this.conn = new ManagedClientConnectionImpl(this, this.connOperator, this.poolEntry);
            $r7 = this.conn;
        }
        return $r7;
    }

    public SchemeRegistry getSchemeRegistry() {
        return this.schemeRegistry;
    }

    /* JADX WARNING: type inference failed for: r10v3, types: [cz.msebera.android.http.pool.PoolEntry, cz.msebera.android.http.impl.conn.HttpPoolEntry] */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00e8, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void releaseConnection(cz.msebera.android.http.conn.ManagedClientConnection r21, long r22, java.util.concurrent.TimeUnit r24) {
        /*
            r20 = this;
            r0 = r21
            boolean r3 = r0 instanceof cz.msebera.android.http.impl.conn.ManagedClientConnectionImpl
            java.lang.String r4 = "Connection class mismatch, connection not obtained from this manager"
            cz.msebera.android.http.mime.Args.check(r3, r4)
            r6 = r21
            cz.msebera.android.http.impl.conn.ManagedClientConnectionImpl r6 = (cz.msebera.android.http.impl.conn.ManagedClientConnectionImpl) r6
            r5 = r6
            monitor-enter(r5)
            r0 = r20
            cz.msebera.android.http.cache.HttpClientAndroidLog r7 = r0.log     // Catch:{ Throwable -> 0x010b }
            boolean r3 = r7.isDebugEnabled()     // Catch:{ Throwable -> 0x010b }
            if (r3 == 0) goto L_0x0033
            r0 = r20
            cz.msebera.android.http.cache.HttpClientAndroidLog r7 = r0.log     // Catch:{ Throwable -> 0x010b }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x010b }
            r8.<init>()     // Catch:{ Throwable -> 0x010b }
            java.lang.String r4 = "Releasing connection "
            r8.append(r4)     // Catch:{ Throwable -> 0x010b }
            r0 = r21
            r8.append(r0)     // Catch:{ Throwable -> 0x010b }
            java.lang.String r9 = r8.toString()     // Catch:{ Throwable -> 0x010b }
            r7.debug(r9)     // Catch:{ Throwable -> 0x010b }
        L_0x0033:
            cz.msebera.android.http.impl.conn.HttpPoolEntry r10 = r5.getPoolEntry()     // Catch:{ Throwable -> 0x010b }
            if (r10 != 0) goto L_0x003b
            monitor-exit(r5)     // Catch:{ Throwable -> 0x010b }
            return
        L_0x003b:
            cz.msebera.android.http.conn.ClientConnectionManager r11 = r5.getManager()     // Catch:{ Throwable -> 0x010b }
            r0 = r20
            if (r11 != r0) goto L_0x0045
            r3 = 1
            goto L_0x0046
        L_0x0045:
            r3 = 0
        L_0x0046:
            java.lang.String r4 = "Connection not obtained from this manager"
            cz.msebera.android.http.mime.Asserts.check(r3, r4)     // Catch:{ Throwable -> 0x010b }
            monitor-enter(r20)     // Catch:{ Throwable -> 0x010b }
            r0 = r20
            boolean r3 = r0.shutdown     // Catch:{ Throwable -> 0x0108 }
            if (r3 == 0) goto L_0x005a
            r0 = r20
            r0.shutdownConnection(r5)     // Catch:{ Throwable -> 0x0108 }
            monitor-exit(r20)     // Catch:{ Throwable -> 0x0108 }
            monitor-exit(r5)     // Catch:{ Throwable -> 0x010b }
            return
        L_0x005a:
            boolean r3 = r5.isOpen()     // Catch:{ Throwable -> 0x00e9 }
            if (r3 == 0) goto L_0x006b
            boolean r3 = r5.isMarkedReusable()     // Catch:{ Throwable -> 0x00e9 }
            if (r3 != 0) goto L_0x006b
            r0 = r20
            r0.shutdownConnection(r5)     // Catch:{ Throwable -> 0x00e9 }
        L_0x006b:
            boolean r3 = r5.isMarkedReusable()     // Catch:{ Throwable -> 0x00e9 }
            if (r3 == 0) goto L_0x00c9
            r0 = r20
            cz.msebera.android.http.impl.conn.HttpPoolEntry r10 = r0.poolEntry     // Catch:{ Throwable -> 0x00e9 }
            if (r24 == 0) goto L_0x007a
            r12 = r24
            goto L_0x007c
        L_0x007a:
            java.util.concurrent.TimeUnit r12 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ Throwable -> 0x00e9 }
        L_0x007c:
            r0 = r22
            r10.updateExpiry(r0, r12)     // Catch:{ Throwable -> 0x00e9 }
            r0 = r20
            cz.msebera.android.http.cache.HttpClientAndroidLog r7 = r0.log     // Catch:{ Throwable -> 0x00e9 }
            boolean r3 = r7.isDebugEnabled()     // Catch:{ Throwable -> 0x00e9 }
            if (r3 == 0) goto L_0x00c9
            r14 = 0
            int r13 = (r22 > r14 ? 1 : (r22 == r14 ? 0 : -1))
            if (r13 <= 0) goto L_0x00af
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00e9 }
            r8.<init>()     // Catch:{ Throwable -> 0x00e9 }
            java.lang.String r4 = "for "
            r8.append(r4)     // Catch:{ Throwable -> 0x00e9 }
            r0 = r22
            r8.append(r0)     // Catch:{ Throwable -> 0x00e9 }
            java.lang.String r4 = " "
            r8.append(r4)     // Catch:{ Throwable -> 0x00e9 }
            r0 = r24
            r8.append(r0)     // Catch:{ Throwable -> 0x00e9 }
            java.lang.String r9 = r8.toString()     // Catch:{ Throwable -> 0x00e9 }
            goto L_0x00b1
        L_0x00af:
            java.lang.String r9 = "indefinitely"
        L_0x00b1:
            r0 = r20
            cz.msebera.android.http.cache.HttpClientAndroidLog r7 = r0.log     // Catch:{ Throwable -> 0x00e9 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00e9 }
            r8.<init>()     // Catch:{ Throwable -> 0x00e9 }
            java.lang.String r4 = "Connection can be kept alive "
            r8.append(r4)     // Catch:{ Throwable -> 0x00e9 }
            r8.append(r9)     // Catch:{ Throwable -> 0x00e9 }
            java.lang.String r9 = r8.toString()     // Catch:{ Throwable -> 0x00e9 }
            r7.debug(r9)     // Catch:{ Throwable -> 0x00e9 }
        L_0x00c9:
            r5.detach()     // Catch:{ Throwable -> 0x0108 }
            r16 = 0
            r0 = r16
            r1 = r20
            r1.conn = r0     // Catch:{ Throwable -> 0x0108 }
            r0 = r20
            cz.msebera.android.http.impl.conn.HttpPoolEntry r10 = r0.poolEntry     // Catch:{ Throwable -> 0x0108 }
            boolean r3 = r10.isClosed()     // Catch:{ Throwable -> 0x0108 }
            if (r3 == 0) goto L_0x00e6
            r16 = 0
            r0 = r16
            r1 = r20
            r1.poolEntry = r0     // Catch:{ Throwable -> 0x0108 }
        L_0x00e6:
            monitor-exit(r20)     // Catch:{ Throwable -> 0x0108 }
            monitor-exit(r5)     // Catch:{ Throwable -> 0x010b }
            return
        L_0x00e9:
            r17 = move-exception
            r5.detach()     // Catch:{ Throwable -> 0x0108 }
            r16 = 0
            r0 = r16
            r1 = r20
            r1.conn = r0     // Catch:{ Throwable -> 0x0108 }
            r0 = r20
            cz.msebera.android.http.impl.conn.HttpPoolEntry r10 = r0.poolEntry     // Catch:{ Throwable -> 0x0108 }
            boolean r3 = r10.isClosed()     // Catch:{ Throwable -> 0x0108 }
            if (r3 == 0) goto L_0x0107
            r16 = 0
            r0 = r16
            r1 = r20
            r1.poolEntry = r0     // Catch:{ Throwable -> 0x0108 }
        L_0x0107:
            throw r17     // Catch:{ Throwable -> 0x0108 }
        L_0x0108:
            r18 = move-exception
            monitor-exit(r20)     // Catch:{ Throwable -> 0x0108 }
            throw r18     // Catch:{ Throwable -> 0x010b }
        L_0x010b:
            r19 = move-exception
            monitor-exit(r5)     // Catch:{ Throwable -> 0x010b }
            throw r19
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.impl.conn.BasicClientConnectionManager.releaseConnection(cz.msebera.android.http.conn.ManagedClientConnection, long, java.util.concurrent.TimeUnit):void");
    }

    public final ClientConnectionRequest requestConnection(HttpRoute httpRoute, Object obj) {
        return new PoolingClientConnectionManager$1(this, httpRoute, obj);
    }

    public void shutdown() {
        synchronized (this) {
            this.shutdown = true;
            try {
                if (this.poolEntry != null) {
                    this.poolEntry.close();
                }
            } finally {
                this.poolEntry = null;
                this.conn = null;
            }
        }
    }
}

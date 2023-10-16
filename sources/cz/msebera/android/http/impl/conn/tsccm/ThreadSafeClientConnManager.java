package cz.msebera.android.http.impl.conn.tsccm;

import c.a.a.a.i.c.a.i;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.conn.ClientConnectionManager;
import cz.msebera.android.http.conn.ClientConnectionOperator;
import cz.msebera.android.http.conn.ClientConnectionRequest;
import cz.msebera.android.http.conn.ManagedClientConnection;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.conn.scheme.SchemeRegistry;
import cz.msebera.android.http.conn.tsccm.ConnPerRouteBean;
import cz.msebera.android.http.impl.conn.DefaultClientConnectionOperator;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpParams;
import java.util.concurrent.TimeUnit;

@Deprecated
public class ThreadSafeClientConnManager implements ClientConnectionManager {
    protected final ClientConnectionOperator connOperator;
    protected final ConnPerRouteBean connPerRoute;
    protected final AbstractConnPool connectionPool;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(i.class);
    protected final ConnPoolByRoute pool;
    protected final SchemeRegistry schemeRegistry;

    public ThreadSafeClientConnManager(HttpParams httpParams, SchemeRegistry schemeRegistry2) {
        Args.notNull(schemeRegistry2, "Scheme registry");
        this.schemeRegistry = schemeRegistry2;
        this.connPerRoute = new ConnPerRouteBean();
        this.connOperator = createConnectionOperator(schemeRegistry2);
        this.pool = (ConnPoolByRoute) createConnectionPool(httpParams);
        this.connectionPool = this.pool;
    }

    /* access modifiers changed from: protected */
    public ClientConnectionOperator createConnectionOperator(SchemeRegistry schemeRegistry2) {
        return new DefaultClientConnectionOperator(schemeRegistry2);
    }

    /* access modifiers changed from: protected */
    public AbstractConnPool createConnectionPool(HttpParams httpParams) {
        return new ConnPoolByRoute(this.connOperator, httpParams);
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        try {
            shutdown();
        } finally {
            super.finalize();
        }
    }

    public SchemeRegistry getSchemeRegistry() {
        return this.schemeRegistry;
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:21:0x0040=Splitter:B:21:0x0040, B:37:0x0091=Splitter:B:37:0x0091} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void releaseConnection(cz.msebera.android.http.conn.ManagedClientConnection r23, long r24, java.util.concurrent.TimeUnit r26) {
        /*
            r22 = this;
            r0 = r23
            boolean r6 = r0 instanceof cz.msebera.android.http.impl.conn.tsccm.BasicPooledConnAdapter
            java.lang.String r7 = "Connection class mismatch, connection not obtained from this manager"
            cz.msebera.android.http.mime.Args.check(r6, r7)
            r9 = r23
            cz.msebera.android.http.impl.conn.tsccm.BasicPooledConnAdapter r9 = (cz.msebera.android.http.impl.conn.tsccm.BasicPooledConnAdapter) r9
            r8 = r9
            cz.msebera.android.http.impl.conn.AbstractPoolEntry r10 = r8.getPoolEntry()
            if (r10 == 0) goto L_0x0024
            cz.msebera.android.http.conn.ClientConnectionManager r11 = r8.getManager()
            r0 = r22
            if (r11 != r0) goto L_0x001e
            r6 = 1
            goto L_0x001f
        L_0x001e:
            r6 = 0
        L_0x001f:
            java.lang.String r7 = "Connection not obtained from this manager"
            cz.msebera.android.http.mime.Asserts.check(r6, r7)
        L_0x0024:
            monitor-enter(r8)
            cz.msebera.android.http.impl.conn.AbstractPoolEntry r10 = r8.getPoolEntry()     // Catch:{ Throwable -> 0x00f7 }
            r13 = r10
            cz.msebera.android.http.impl.conn.tsccm.BasicPoolEntry r13 = (cz.msebera.android.http.impl.conn.tsccm.BasicPoolEntry) r13     // Catch:{ Throwable -> 0x00f7 }
            r12 = r13
            if (r12 != 0) goto L_0x0031
            monitor-exit(r8)     // Catch:{ Throwable -> 0x00f7 }
            return
        L_0x0031:
            boolean r6 = r8.isOpen()     // Catch:{ IOException -> 0x007b }
            if (r6 == 0) goto L_0x0040
            boolean r6 = r8.isMarkedReusable()     // Catch:{ IOException -> 0x007b }
            if (r6 != 0) goto L_0x0040
            r8.shutdown()     // Catch:{ IOException -> 0x007b }
        L_0x0040:
            boolean r14 = r8.isMarkedReusable()     // Catch:{ Throwable -> 0x00f7 }
            r6 = r14
            r0 = r22
            cz.msebera.android.http.cache.HttpClientAndroidLog r15 = r0.log     // Catch:{ Throwable -> 0x00f7 }
            boolean r16 = r15.isDebugEnabled()     // Catch:{ Throwable -> 0x00f7 }
            if (r16 == 0) goto L_0x0064
            if (r14 == 0) goto L_0x005d
            r0 = r22
            cz.msebera.android.http.cache.HttpClientAndroidLog r15 = r0.log     // Catch:{ Throwable -> 0x00f7 }
            java.lang.String r17 = "Released connection is reusable."
        L_0x0057:
            r0 = r17
            r15.debug(r0)     // Catch:{ Throwable -> 0x00f7 }
            goto L_0x0064
        L_0x005d:
            r0 = r22
            cz.msebera.android.http.cache.HttpClientAndroidLog r15 = r0.log     // Catch:{ Throwable -> 0x00f7 }
            java.lang.String r17 = "Released connection is not reusable."
            goto L_0x0057
        L_0x0064:
            r8.detach()     // Catch:{ Throwable -> 0x00f7 }
            r0 = r22
            cz.msebera.android.http.impl.conn.tsccm.ConnPoolByRoute r0 = r0.pool     // Catch:{ Throwable -> 0x00f7 }
            r18 = r0
        L_0x006d:
            r0 = r18
            r1 = r12
            r2 = r6
            r3 = r24
            r5 = r26
            r0.freeEntry(r1, r2, r3, r5)     // Catch:{ Throwable -> 0x00f7 }
            goto L_0x00bf
        L_0x0079:
            r19 = move-exception
            goto L_0x00c1
        L_0x007b:
            r20 = move-exception
            r0 = r22
            cz.msebera.android.http.cache.HttpClientAndroidLog r15 = r0.log     // Catch:{ Throwable -> 0x0079 }
            boolean r6 = r15.isDebugEnabled()     // Catch:{ Throwable -> 0x0079 }
            if (r6 == 0) goto L_0x0091
            r0 = r22
            cz.msebera.android.http.cache.HttpClientAndroidLog r15 = r0.log     // Catch:{ Throwable -> 0x0079 }
            java.lang.String r7 = "Exception shutting down released connection."
            r0 = r20
            r15.debug(r7, r0)     // Catch:{ Throwable -> 0x0079 }
        L_0x0091:
            boolean r14 = r8.isMarkedReusable()     // Catch:{ Throwable -> 0x00f7 }
            r6 = r14
            r0 = r22
            cz.msebera.android.http.cache.HttpClientAndroidLog r15 = r0.log     // Catch:{ Throwable -> 0x00f7 }
            boolean r16 = r15.isDebugEnabled()     // Catch:{ Throwable -> 0x00f7 }
            if (r16 == 0) goto L_0x00b5
            if (r14 == 0) goto L_0x00ae
            r0 = r22
            cz.msebera.android.http.cache.HttpClientAndroidLog r15 = r0.log     // Catch:{ Throwable -> 0x00f7 }
            java.lang.String r17 = "Released connection is reusable."
        L_0x00a8:
            r0 = r17
            r15.debug(r0)     // Catch:{ Throwable -> 0x00f7 }
            goto L_0x00b5
        L_0x00ae:
            r0 = r22
            cz.msebera.android.http.cache.HttpClientAndroidLog r15 = r0.log     // Catch:{ Throwable -> 0x00f7 }
            java.lang.String r17 = "Released connection is not reusable."
            goto L_0x00a8
        L_0x00b5:
            r8.detach()     // Catch:{ Throwable -> 0x00f7 }
            r0 = r22
            cz.msebera.android.http.impl.conn.tsccm.ConnPoolByRoute r0 = r0.pool     // Catch:{ Throwable -> 0x00f7 }
            r18 = r0
            goto L_0x006d
        L_0x00bf:
            monitor-exit(r8)     // Catch:{ Throwable -> 0x00f7 }
            return
        L_0x00c1:
            boolean r6 = r8.isMarkedReusable()     // Catch:{ Throwable -> 0x00f7 }
            r0 = r22
            cz.msebera.android.http.cache.HttpClientAndroidLog r15 = r0.log     // Catch:{ Throwable -> 0x00f7 }
            boolean r14 = r15.isDebugEnabled()     // Catch:{ Throwable -> 0x00f7 }
            if (r14 == 0) goto L_0x00e4
            if (r6 == 0) goto L_0x00dd
            r0 = r22
            cz.msebera.android.http.cache.HttpClientAndroidLog r15 = r0.log     // Catch:{ Throwable -> 0x00f7 }
            java.lang.String r17 = "Released connection is reusable."
        L_0x00d7:
            r0 = r17
            r15.debug(r0)     // Catch:{ Throwable -> 0x00f7 }
            goto L_0x00e4
        L_0x00dd:
            r0 = r22
            cz.msebera.android.http.cache.HttpClientAndroidLog r15 = r0.log     // Catch:{ Throwable -> 0x00f7 }
            java.lang.String r17 = "Released connection is not reusable."
            goto L_0x00d7
        L_0x00e4:
            r8.detach()     // Catch:{ Throwable -> 0x00f7 }
            r0 = r22
            cz.msebera.android.http.impl.conn.tsccm.ConnPoolByRoute r0 = r0.pool     // Catch:{ Throwable -> 0x00f7 }
            r18 = r0
            r1 = r12
            r2 = r6
            r3 = r24
            r5 = r26
            r0.freeEntry(r1, r2, r3, r5)     // Catch:{ Throwable -> 0x00f7 }
            throw r19     // Catch:{ Throwable -> 0x00f7 }
        L_0x00f7:
            r21 = move-exception
            monitor-exit(r8)     // Catch:{ Throwable -> 0x00f7 }
            goto L_0x00fa
        L_0x00fa:
            throw r21
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.impl.conn.tsccm.ThreadSafeClientConnManager.releaseConnection(cz.msebera.android.http.conn.ManagedClientConnection, long, java.util.concurrent.TimeUnit):void");
    }

    public ClientConnectionRequest requestConnection(final HttpRoute httpRoute, Object obj) {
        final PoolEntryRequest $r2 = this.pool.requestPoolEntry(httpRoute, obj);
        return new ClientConnectionRequest() {
            public void abortRequest() {
                $r2.abortRequest();
            }

            public ManagedClientConnection getConnection(long j, TimeUnit timeUnit) {
                Args.notNull(httpRoute, "Route");
                if (ThreadSafeClientConnManager.this.log.isDebugEnabled()) {
                    HttpClientAndroidLog $r5 = ThreadSafeClientConnManager.this.log;
                    $r5.debug("Get connection: " + httpRoute + ", timeout = " + j);
                }
                return new BasicPooledConnAdapter(ThreadSafeClientConnManager.this, $r2.getPoolEntry(j, timeUnit));
            }
        };
    }

    public void shutdown() {
        this.log.debug("Shutting down");
        this.pool.shutdown();
    }
}

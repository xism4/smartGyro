package cz.msebera.android.http.impl.conn.tsccm;

import c.a.a.a.i.c.a.b;
import c.a.a.a.i.c.a.e;
import c.a.a.a.i.c.a.g;
import c.a.a.a.i.c.a.j;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.conn.ClientConnectionOperator;
import cz.msebera.android.http.conn.ConnectionPoolTimeoutException;
import cz.msebera.android.http.conn.OperatedClientConnection;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.conn.tsccm.ConnManagerParams;
import cz.msebera.android.http.conn.tsccm.ConnPerRoute;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import cz.msebera.android.http.util.HttpParams;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Deprecated
public class ConnPoolByRoute extends AbstractConnPool {
    protected final ConnPerRoute connPerRoute;
    private final long connTTL;
    private final TimeUnit connTTLTimeUnit;
    protected final Queue<b> freeConnections;
    protected final Set<b> leasedConnections;
    public HttpClientAndroidLog log;
    protected volatile int maxTotalConnections;
    protected volatile int numConnections;
    protected final ClientConnectionOperator operator;
    /* access modifiers changed from: private */
    public final Lock poolLock;
    protected final Map<c.a.a.a.e.b.b, g> routeToPool;
    protected volatile boolean shutdown;
    protected final Queue<j> waitingThreads;

    public ConnPoolByRoute(ClientConnectionOperator clientConnectionOperator, ConnPerRoute connPerRoute2, int i) {
        this(clientConnectionOperator, connPerRoute2, i, -1, TimeUnit.MILLISECONDS);
    }

    public ConnPoolByRoute(ClientConnectionOperator clientConnectionOperator, ConnPerRoute connPerRoute2, int i, long j, TimeUnit timeUnit) {
        this.log = new HttpClientAndroidLog(e.class);
        Args.notNull(clientConnectionOperator, "Connection operator");
        Args.notNull(connPerRoute2, "Connections per route");
        this.poolLock = this.poolLock;
        this.leasedConnections = this.leasedConnections;
        this.operator = clientConnectionOperator;
        this.connPerRoute = connPerRoute2;
        this.maxTotalConnections = i;
        this.freeConnections = createFreeConnQueue();
        this.waitingThreads = createWaitingThreadQueue();
        this.routeToPool = createRouteToPoolMap();
        this.connTTL = j;
        this.connTTLTimeUnit = timeUnit;
    }

    public ConnPoolByRoute(ClientConnectionOperator clientConnectionOperator, HttpParams httpParams) {
        this(clientConnectionOperator, ConnManagerParams.getMaxConnectionsPerRoute(httpParams), ConnManagerParams.getMaxTotalConnections(httpParams));
    }

    private void closeConnection(BasicPoolEntry basicPoolEntry) {
        OperatedClientConnection $r2 = basicPoolEntry.getConnection();
        if ($r2 != null) {
            try {
                $r2.close();
            } catch (IOException $r4) {
                this.log.debug("I/O error closing connection", $r4);
            }
        }
    }

    /* access modifiers changed from: protected */
    public BasicPoolEntry createEntry(RouteSpecificPool routeSpecificPool, ClientConnectionOperator clientConnectionOperator) {
        if (this.log.isDebugEnabled()) {
            this.log.debug("Creating new connection [" + routeSpecificPool.getRoute() + "]");
        }
        BasicPoolEntry basicPoolEntry = new BasicPoolEntry(clientConnectionOperator, routeSpecificPool.getRoute(), this.connTTL, this.connTTLTimeUnit);
        Lock $r9 = this.poolLock;
        Lock lock = $r9;
        $r9.lock();
        try {
            routeSpecificPool.createdEntry(basicPoolEntry);
            this.numConnections++;
            Set<b> set = this.leasedConnections;
            Set<b> set2 = set;
            set.add(basicPoolEntry);
            return basicPoolEntry;
        } finally {
            Lock $r92 = this.poolLock;
            Lock lock2 = $r92;
            $r92.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public Queue createFreeConnQueue() {
        return new LinkedList();
    }

    /* access modifiers changed from: protected */
    public Map createRouteToPoolMap() {
        return new HashMap();
    }

    /* access modifiers changed from: protected */
    public Queue createWaitingThreadQueue() {
        return new LinkedList();
    }

    /* access modifiers changed from: protected */
    public void deleteEntry(BasicPoolEntry basicPoolEntry) {
        HttpRoute $r2 = basicPoolEntry.getPlannedRoute();
        if (this.log.isDebugEnabled()) {
            this.log.debug("Deleting connection [" + $r2 + "][" + basicPoolEntry.getState() + "]");
        }
        this.poolLock.lock();
        try {
            closeConnection(basicPoolEntry);
            RouteSpecificPool $r8 = getRoutePool($r2, true);
            $r8.deleteEntry(basicPoolEntry);
            this.numConnections--;
            if ($r8.isUnused()) {
                this.routeToPool.remove($r2);
            }
        } finally {
            this.poolLock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public void deleteLeastUsedEntry() {
        this.poolLock.lock();
        try {
            BasicPoolEntry $r4 = this.freeConnections.remove();
            if ($r4 != null) {
                deleteEntry($r4);
            } else if (this.log.isDebugEnabled()) {
                this.log.debug("No free connection to delete");
            }
        } finally {
            this.poolLock.unlock();
        }
    }

    public void freeEntry(BasicPoolEntry basicPoolEntry, boolean z, long j, TimeUnit timeUnit) {
        String $r7;
        HttpRoute $r3 = basicPoolEntry.getPlannedRoute();
        if (this.log.isDebugEnabled()) {
            this.log.debug("Releasing connection [" + $r3 + "][" + basicPoolEntry.getState() + "]");
        }
        this.poolLock.lock();
        try {
            if (this.shutdown) {
                closeConnection(basicPoolEntry);
                return;
            }
            this.leasedConnections.remove(basicPoolEntry);
            RouteSpecificPool $r10 = getRoutePool($r3, true);
            if (z) {
                if ($r10.getCapacity() >= 0) {
                    if (this.log.isDebugEnabled()) {
                        if (j > 0) {
                            $r7 = "for " + j + " " + timeUnit;
                        } else {
                            $r7 = "indefinitely";
                        }
                        this.log.debug("Pooling connection [" + $r3 + "][" + basicPoolEntry.getState() + "]; keep alive " + $r7);
                    }
                    $r10.freeEntry(basicPoolEntry);
                    basicPoolEntry.updateExpiry(j, timeUnit);
                    Queue<b> queue = this.freeConnections;
                    Queue<b> queue2 = queue;
                    queue.add(basicPoolEntry);
                    notifyWaitingThread($r10);
                    this.poolLock.unlock();
                }
            }
            closeConnection(basicPoolEntry);
            $r10.dropEntry();
            int $i1 = this.numConnections - 1;
            int i = $i1;
            this.numConnections = $i1;
            notifyWaitingThread($r10);
            this.poolLock.unlock();
        } finally {
            this.poolLock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public BasicPoolEntry getEntryBlocking(HttpRoute httpRoute, Object obj, long j, TimeUnit timeUnit, WaitingThreadAborter waitingThreadAborter) {
        RouteSpecificPool $r9;
        WaitingThread $r10;
        ClientConnectionOperator $r16;
        BasicPoolEntry $r6 = null;
        Date $r7 = j > 0 ? new Date(System.currentTimeMillis() + timeUnit.toMillis(j)) : null;
        this.poolLock.lock();
        try {
            $r9 = getRoutePool(httpRoute, true);
            $r10 = null;
            while ($r6 == null) {
                boolean z = false;
                Asserts.check(!this.shutdown, "Connection pool shut down");
                HttpClientAndroidLog $r11 = this.log;
                HttpClientAndroidLog httpClientAndroidLog = $r11;
                if ($r11.isDebugEnabled()) {
                    HttpClientAndroidLog $r112 = this.log;
                    StringBuilder sb = new StringBuilder();
                    sb.append("[");
                    sb.append(httpRoute);
                    sb.append("] total kept alive: ");
                    Queue<b> queue = this.freeConnections;
                    Queue<b> queue2 = queue;
                    sb.append(queue.size());
                    sb.append(", total issued: ");
                    Set<b> set = this.leasedConnections;
                    Set<b> set2 = set;
                    sb.append(set.size());
                    sb.append(", total allocated: ");
                    sb.append(this.numConnections);
                    sb.append(" out of ");
                    sb.append(this.maxTotalConnections);
                    $r112.debug(sb.toString());
                }
                BasicPoolEntry $r15 = getFreeEntry($r9, obj);
                $r6 = $r15;
                if ($r15 != null) {
                    break;
                }
                if ($r9.getCapacity() > 0) {
                    z = true;
                }
                HttpClientAndroidLog $r113 = this.log;
                HttpClientAndroidLog httpClientAndroidLog2 = $r113;
                if ($r113.isDebugEnabled()) {
                    HttpClientAndroidLog $r114 = this.log;
                    $r114.debug("Available capacity: " + $r9.getCapacity() + " out of " + $r9.getMaxEntries() + " [" + httpRoute + "][" + obj + "]");
                }
                if (z) {
                    if (this.numConnections < this.maxTotalConnections) {
                        $r16 = this.operator;
                        $r6 = createEntry($r9, $r16);
                    }
                }
                if (z) {
                    Queue<b> queue3 = this.freeConnections;
                    Queue<b> queue4 = queue3;
                    if (!queue3.isEmpty()) {
                        deleteLeastUsedEntry();
                        $r9 = getRoutePool(httpRoute, true);
                        $r16 = this.operator;
                        $r6 = createEntry($r9, $r16);
                    }
                }
                HttpClientAndroidLog $r115 = this.log;
                HttpClientAndroidLog httpClientAndroidLog3 = $r115;
                if ($r115.isDebugEnabled()) {
                    HttpClientAndroidLog $r116 = this.log;
                    $r116.debug("Need to wait for connection [" + httpRoute + "][" + obj + "]");
                }
                if ($r10 == null) {
                    WaitingThread $r18 = newWaitingThread(this.poolLock.newCondition(), $r9);
                    $r10 = $r18;
                    waitingThreadAborter.setWaitingThread($r18);
                }
                $r9.queueThread($r10);
                Queue<j> queue5 = this.waitingThreads;
                Queue<j> queue6 = queue5;
                queue5.add($r10);
                boolean $z1 = $r10.await($r7);
                $r9.removeThread($r10);
                Queue<j> queue7 = this.waitingThreads;
                Queue<j> queue8 = queue7;
                queue7.remove($r10);
                if (!$z1 && $r7 != null) {
                    if ($r7.getTime() <= System.currentTimeMillis()) {
                        throw new ConnectionPoolTimeoutException("Timeout waiting for connection from pool");
                    }
                }
            }
            this.poolLock.unlock();
            return $r6;
        } catch (Throwable $r21) {
            this.poolLock.unlock();
            throw $r21;
        }
    }

    /* access modifiers changed from: protected */
    public BasicPoolEntry getFreeEntry(RouteSpecificPool routeSpecificPool, Object obj) {
        this.poolLock.lock();
        boolean $z0 = false;
        BasicPoolEntry $r4 = null;
        while (!$z0) {
            try {
                BasicPoolEntry $r5 = routeSpecificPool.allocEntry(obj);
                $r4 = $r5;
                if ($r5 != null) {
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("Getting free connection [" + routeSpecificPool.getRoute() + "][" + obj + "]");
                    }
                    this.freeConnections.remove($r5);
                    if ($r5.isExpired(System.currentTimeMillis())) {
                        if (this.log.isDebugEnabled()) {
                            this.log.debug("Closing expired free connection [" + routeSpecificPool.getRoute() + "][" + obj + "]");
                        }
                        closeConnection($r5);
                        routeSpecificPool.dropEntry();
                        this.numConnections--;
                    } else {
                        Set<b> set = this.leasedConnections;
                        Set<b> set2 = set;
                        set.add($r5);
                    }
                } else if (this.log.isDebugEnabled()) {
                    this.log.debug("No free connections [" + routeSpecificPool.getRoute() + "][" + obj + "]");
                }
                $z0 = true;
            } catch (Throwable $r12) {
                this.poolLock.unlock();
                throw $r12;
            }
        }
        this.poolLock.unlock();
        return $r4;
    }

    /* access modifiers changed from: protected */
    public RouteSpecificPool getRoutePool(HttpRoute httpRoute, boolean z) {
        this.poolLock.lock();
        try {
            RouteSpecificPool $r5 = this.routeToPool.get(httpRoute);
            if ($r5 == null && z) {
                RouteSpecificPool $r6 = newRouteSpecificPool(httpRoute);
                $r5 = $r6;
                this.routeToPool.put(httpRoute, $r6);
            }
            return $r5;
        } finally {
            this.poolLock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public RouteSpecificPool newRouteSpecificPool(HttpRoute httpRoute) {
        return new RouteSpecificPool(httpRoute, this.connPerRoute);
    }

    /* access modifiers changed from: protected */
    public WaitingThread newWaitingThread(Condition condition, RouteSpecificPool routeSpecificPool) {
        return new WaitingThread(condition, routeSpecificPool);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v0, resolved type: cz.msebera.android.http.impl.conn.tsccm.WaitingThread} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006d A[SYNTHETIC, Splitter:B:27:0x006d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void notifyWaitingThread(cz.msebera.android.http.impl.conn.tsccm.RouteSpecificPool r13) {
        /*
            r12 = this;
            java.util.concurrent.locks.Lock r0 = r12.poolLock
            r0.lock()
            if (r13 == 0) goto L_0x0039
            boolean r1 = r13.hasThread()     // Catch:{ Throwable -> 0x0076 }
            if (r1 == 0) goto L_0x0039
            cz.msebera.android.http.cache.HttpClientAndroidLog r2 = r12.log     // Catch:{ Throwable -> 0x0076 }
            boolean r1 = r2.isDebugEnabled()     // Catch:{ Throwable -> 0x0076 }
            if (r1 == 0) goto L_0x0034
            cz.msebera.android.http.cache.HttpClientAndroidLog r2 = r12.log     // Catch:{ Throwable -> 0x0076 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0076 }
            r3.<init>()     // Catch:{ Throwable -> 0x0076 }
            java.lang.String r4 = "Notifying thread waiting on pool ["
            r3.append(r4)     // Catch:{ Throwable -> 0x0076 }
            cz.msebera.android.http.conn.routing.HttpRoute r5 = r13.getRoute()     // Catch:{ Throwable -> 0x0076 }
            r3.append(r5)     // Catch:{ Throwable -> 0x0076 }
            java.lang.String r4 = "]"
            r3.append(r4)     // Catch:{ Throwable -> 0x0076 }
            java.lang.String r6 = r3.toString()     // Catch:{ Throwable -> 0x0076 }
            r2.debug(r6)     // Catch:{ Throwable -> 0x0076 }
        L_0x0034:
            cz.msebera.android.http.impl.conn.tsccm.WaitingThread r7 = r13.nextThread()     // Catch:{ Throwable -> 0x0076 }
            goto L_0x006b
        L_0x0039:
            java.util.Queue<c.a.a.a.i.c.a.j> r8 = r12.waitingThreads     // Catch:{ Throwable -> 0x0076 }
            boolean r1 = r8.isEmpty()     // Catch:{ Throwable -> 0x0076 }
            if (r1 != 0) goto L_0x005b
            cz.msebera.android.http.cache.HttpClientAndroidLog r2 = r12.log     // Catch:{ Throwable -> 0x0076 }
            boolean r1 = r2.isDebugEnabled()     // Catch:{ Throwable -> 0x0076 }
            if (r1 == 0) goto L_0x0050
            cz.msebera.android.http.cache.HttpClientAndroidLog r2 = r12.log     // Catch:{ Throwable -> 0x0076 }
            java.lang.String r4 = "Notifying thread waiting on any pool"
            r2.debug(r4)     // Catch:{ Throwable -> 0x0076 }
        L_0x0050:
            java.util.Queue<c.a.a.a.i.c.a.j> r8 = r12.waitingThreads     // Catch:{ Throwable -> 0x0076 }
            java.lang.Object r9 = r8.remove()     // Catch:{ Throwable -> 0x0076 }
            r10 = r9
            cz.msebera.android.http.impl.conn.tsccm.WaitingThread r10 = (cz.msebera.android.http.impl.conn.tsccm.WaitingThread) r10     // Catch:{ Throwable -> 0x0076 }
            r7 = r10
            goto L_0x006b
        L_0x005b:
            cz.msebera.android.http.cache.HttpClientAndroidLog r2 = r12.log     // Catch:{ Throwable -> 0x0076 }
            boolean r1 = r2.isDebugEnabled()     // Catch:{ Throwable -> 0x0076 }
            if (r1 == 0) goto L_0x006a
            cz.msebera.android.http.cache.HttpClientAndroidLog r2 = r12.log     // Catch:{ Throwable -> 0x0076 }
            java.lang.String r4 = "Notifying no-one, there are no waiting threads"
            r2.debug(r4)     // Catch:{ Throwable -> 0x0076 }
        L_0x006a:
            r7 = 0
        L_0x006b:
            if (r7 == 0) goto L_0x0070
            r7.wakeup()     // Catch:{ Throwable -> 0x0076 }
        L_0x0070:
            java.util.concurrent.locks.Lock r0 = r12.poolLock
            r0.unlock()
            return
        L_0x0076:
            r11 = move-exception
            java.util.concurrent.locks.Lock r0 = r12.poolLock
            r0.unlock()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.http.impl.conn.tsccm.ConnPoolByRoute.notifyWaitingThread(cz.msebera.android.http.impl.conn.tsccm.RouteSpecificPool):void");
    }

    public PoolEntryRequest requestPoolEntry(final HttpRoute httpRoute, final Object obj) {
        final WaitingThreadAborter $r3 = new WaitingThreadAborter();
        return new PoolEntryRequest() {
            public void abortRequest() {
                ConnPoolByRoute.this.poolLock.lock();
                try {
                    $r3.abort();
                } finally {
                    ConnPoolByRoute.this.poolLock.unlock();
                }
            }

            public BasicPoolEntry getPoolEntry(long j, TimeUnit timeUnit) {
                return ConnPoolByRoute.this.getEntryBlocking(httpRoute, obj, j, timeUnit, $r3);
            }
        };
    }

    public void shutdown() {
        this.poolLock.lock();
        try {
            if (!this.shutdown) {
                this.shutdown = true;
                Iterator $r3 = this.leasedConnections.iterator();
                while ($r3.hasNext()) {
                    $r3.remove();
                    closeConnection($r3.next());
                }
                Iterator $r32 = this.freeConnections.iterator();
                while ($r32.hasNext()) {
                    BasicPoolEntry $r5 = (BasicPoolEntry) $r32.next();
                    $r32.remove();
                    if (this.log.isDebugEnabled()) {
                        HttpClientAndroidLog $r7 = this.log;
                        $r7.debug("Closing connection [" + $r5.getPlannedRoute() + "][" + $r5.getState() + "]");
                    }
                    closeConnection($r5);
                }
                Iterator $r33 = this.waitingThreads.iterator();
                while ($r33.hasNext()) {
                    $r33.remove();
                    ((WaitingThread) $r33.next()).wakeup();
                }
                Map<c.a.a.a.e.b.b, g> map = this.routeToPool;
                Map<c.a.a.a.e.b.b, g> map2 = map;
                map.clear();
                this.poolLock.unlock();
            }
        } finally {
            this.poolLock.unlock();
        }
    }
}

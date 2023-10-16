package cz.msebera.android.http.impl.conn;

import c.a.a.a.e.b.b;
import c.a.a.a.e.q;
import c.a.a.a.m.a;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.conn.OperatedClientConnection;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.conn.routing.RouteTracker;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Deprecated
class HttpPoolEntry extends a<b, q> {
    public HttpClientAndroidLog log;
    private final RouteTracker tracker;

    public HttpPoolEntry(HttpClientAndroidLog httpClientAndroidLog, String str, HttpRoute httpRoute, OperatedClientConnection operatedClientConnection, long j, TimeUnit timeUnit) {
        super(str, httpRoute, operatedClientConnection, j, timeUnit);
        this.log = httpClientAndroidLog;
        this.tracker = new RouteTracker(httpRoute);
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [cz.msebera.android.http.pool.PoolEntry, cz.msebera.android.http.impl.conn.HttpPoolEntry] */
    public void close() {
        try {
            ((OperatedClientConnection) getConnection()).close();
        } catch (IOException $r4) {
            this.log.debug("I/O error closing connection", $r4);
        }
    }

    /* access modifiers changed from: package-private */
    public HttpRoute getEffectiveRoute() {
        return this.tracker.toRoute();
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [cz.msebera.android.http.pool.PoolEntry, cz.msebera.android.http.impl.conn.HttpPoolEntry] */
    /* access modifiers changed from: package-private */
    public HttpRoute getPlannedRoute() {
        return (HttpRoute) getRoute();
    }

    /* access modifiers changed from: package-private */
    public RouteTracker getTracker() {
        return this.tracker;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [cz.msebera.android.http.pool.PoolEntry, cz.msebera.android.http.impl.conn.HttpPoolEntry] */
    public boolean isClosed() {
        return !((OperatedClientConnection) getConnection()).isOpen();
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [cz.msebera.android.http.pool.PoolEntry, cz.msebera.android.http.impl.conn.HttpPoolEntry, java.lang.Object] */
    public boolean isExpired(long j) {
        boolean $z0 = HttpPoolEntry.super.isExpired(j);
        if ($z0 && this.log.isDebugEnabled()) {
            HttpClientAndroidLog $r1 = this.log;
            $r1.debug("Connection " + this + " expired @ " + new Date(getExpiry()));
        }
        return $z0;
    }
}

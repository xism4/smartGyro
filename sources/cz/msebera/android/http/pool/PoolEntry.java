package cz.msebera.android.http.pool;

import cz.msebera.android.http.mime.Args;
import java.util.concurrent.TimeUnit;

public abstract class PoolEntry<T, C> {
    private final C conn;
    private final long created = System.currentTimeMillis();
    private long expiry;
    private final String id;
    private final T route;
    private volatile Object state;
    private long updated;
    private final long validUnit;

    public PoolEntry(String str, Object obj, Object obj2, long j, TimeUnit timeUnit) {
        Args.notNull(obj, "Route");
        Args.notNull(obj2, "Connection");
        Args.notNull(timeUnit, "Time unit");
        this.id = str;
        this.route = obj;
        this.conn = obj2;
        this.validUnit = j > 0 ? this.created + timeUnit.toMillis(j) : Long.MAX_VALUE;
        this.expiry = this.validUnit;
    }

    public Object getConnection() {
        return this.conn;
    }

    public synchronized long getExpiry() {
        return this.expiry;
    }

    public Object getRoute() {
        return this.route;
    }

    public synchronized boolean isExpired(long j) {
        return j >= this.expiry;
    }

    public void setState(Object obj) {
        this.state = obj;
    }

    public String toString() {
        return "[id:" + this.id + "][route:" + this.route + "][state:" + this.state + "]";
    }

    public synchronized void updateExpiry(long j, TimeUnit timeUnit) {
        Args.notNull(timeUnit, "Time unit");
        this.updated = System.currentTimeMillis();
        this.expiry = Math.min(j > 0 ? this.updated + timeUnit.toMillis(j) : Long.MAX_VALUE, this.validUnit);
    }
}

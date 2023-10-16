package cz.msebera.android.http.impl.conn.tsccm;

import cz.msebera.android.http.conn.ClientConnectionOperator;
import cz.msebera.android.http.conn.OperatedClientConnection;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.impl.conn.AbstractPoolEntry;
import cz.msebera.android.http.mime.Args;
import java.util.concurrent.TimeUnit;

@Deprecated
public class BasicPoolEntry extends AbstractPoolEntry {
    private final long created = System.currentTimeMillis();
    private long expiry;
    private long updated;
    private final long validUntil;

    public BasicPoolEntry(ClientConnectionOperator clientConnectionOperator, HttpRoute httpRoute, long j, TimeUnit timeUnit) {
        super(clientConnectionOperator, httpRoute);
        Args.notNull(httpRoute, "HTTP route");
        this.validUntil = j > 0 ? this.created + timeUnit.toMillis(j) : Long.MAX_VALUE;
        this.expiry = this.validUntil;
    }

    /* access modifiers changed from: protected */
    public final OperatedClientConnection getConnection() {
        return this.connection;
    }

    /* access modifiers changed from: protected */
    public final HttpRoute getPlannedRoute() {
        return this.route;
    }

    public boolean isExpired(long j) {
        return j >= this.expiry;
    }

    /* access modifiers changed from: protected */
    public void shutdownEntry() {
        super.shutdownEntry();
    }

    public void updateExpiry(long j, TimeUnit timeUnit) {
        this.updated = System.currentTimeMillis();
        this.expiry = Math.min(this.validUntil, j > 0 ? this.updated + timeUnit.toMillis(j) : Long.MAX_VALUE);
    }
}

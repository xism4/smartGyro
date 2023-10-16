package cz.msebera.android.http.impl.conn;

import cz.msebera.android.http.conn.ClientConnectionManager;
import cz.msebera.android.http.conn.OperatedClientConnection;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.util.HttpParams;

@Deprecated
public abstract class AbstractPooledConnAdapter extends AbstractClientConnAdapter {
    protected volatile AbstractPoolEntry poolEntry;

    protected AbstractPooledConnAdapter(ClientConnectionManager clientConnectionManager, AbstractPoolEntry abstractPoolEntry) {
        super(clientConnectionManager, abstractPoolEntry.connection);
        this.poolEntry = abstractPoolEntry;
    }

    /* access modifiers changed from: protected */
    public void assertValid(AbstractPoolEntry abstractPoolEntry) {
        if (isReleased() || abstractPoolEntry == null) {
            throw new ConnectionShutdownException();
        }
    }

    public void close() {
        AbstractPoolEntry $r1 = getPoolEntry();
        if ($r1 != null) {
            $r1.shutdownEntry();
        }
        OperatedClientConnection $r2 = getWrappedConnection();
        if ($r2 != null) {
            $r2.close();
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void detach() {
        this.poolEntry = null;
        super.detach();
    }

    /* access modifiers changed from: protected */
    public AbstractPoolEntry getPoolEntry() {
        return this.poolEntry;
    }

    public HttpRoute getRoute() {
        AbstractPoolEntry $r2 = getPoolEntry();
        assertValid($r2);
        if ($r2.tracker == null) {
            return null;
        }
        return $r2.tracker.toRoute();
    }

    public void layerProtocol(HttpContext httpContext, HttpParams httpParams) {
        AbstractPoolEntry $r3 = getPoolEntry();
        assertValid($r3);
        $r3.layerProtocol(httpContext, httpParams);
    }

    public void open(HttpRoute httpRoute, HttpContext httpContext, HttpParams httpParams) {
        AbstractPoolEntry $r4 = getPoolEntry();
        assertValid($r4);
        $r4.open(httpRoute, httpContext, httpParams);
    }

    public void setState(Object obj) {
        AbstractPoolEntry $r2 = getPoolEntry();
        assertValid($r2);
        $r2.setState(obj);
    }

    public void shutdown() {
        AbstractPoolEntry $r1 = getPoolEntry();
        if ($r1 != null) {
            $r1.shutdownEntry();
        }
        OperatedClientConnection $r2 = getWrappedConnection();
        if ($r2 != null) {
            $r2.shutdown();
        }
    }

    public void tunnelTarget(boolean z, HttpParams httpParams) {
        AbstractPoolEntry $r2 = getPoolEntry();
        assertValid($r2);
        $r2.tunnelTarget(z, httpParams);
    }
}

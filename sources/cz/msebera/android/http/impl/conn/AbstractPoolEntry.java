package cz.msebera.android.http.impl.conn;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.conn.ClientConnectionOperator;
import cz.msebera.android.http.conn.OperatedClientConnection;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.conn.routing.RouteTracker;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import cz.msebera.android.http.util.HttpParams;
import java.io.InterruptedIOException;
import java.net.Socket;

@Deprecated
public abstract class AbstractPoolEntry {
    protected final ClientConnectionOperator connOperator;
    protected final OperatedClientConnection connection;
    protected volatile HttpRoute route;
    protected volatile Object state;
    protected volatile RouteTracker tracker = null;

    protected AbstractPoolEntry(ClientConnectionOperator clientConnectionOperator, HttpRoute httpRoute) {
        Args.notNull(clientConnectionOperator, "Connection operator");
        this.connOperator = clientConnectionOperator;
        this.connection = clientConnectionOperator.createConnection();
        this.route = httpRoute;
    }

    public Object getState() {
        return this.state;
    }

    public void layerProtocol(HttpContext httpContext, HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        Asserts.notNull(this.tracker, "Route tracker");
        Asserts.check(this.tracker.isConnected(), "Connection not open");
        Asserts.check(this.tracker.isTunnelled(), "Protocol layering without a tunnel not supported");
        Asserts.check(!this.tracker.isLayered(), "Multiple protocol layering not supported");
        this.connOperator.updateSecureConnection(this.connection, this.tracker.getTargetHost(), httpContext, httpParams);
        this.tracker.layerProtocol(this.connection.isSecure());
    }

    public void open(HttpRoute httpRoute, HttpContext httpContext, HttpParams httpParams) {
        Args.notNull(httpRoute, "Route");
        Args.notNull(httpParams, "HTTP parameters");
        if (this.tracker != null) {
            Asserts.check(!this.tracker.isConnected(), "Connection already open");
        }
        this.tracker = new RouteTracker(httpRoute);
        HttpHost $r6 = httpRoute.getProxyHost();
        this.connOperator.openConnection(this.connection, $r6 != null ? $r6 : httpRoute.getTargetHost(), httpRoute.getLocalAddress(), httpContext, httpParams);
        RouteTracker $r5 = this.tracker;
        if ($r5 == null) {
            throw new InterruptedIOException("Request aborted");
        } else if ($r6 == null) {
            $r5.connectProxy(this.connection.isSecure());
        } else {
            $r5.connectProxy($r6, this.connection.isSecure());
        }
    }

    public void setState(Object obj) {
        this.state = obj;
    }

    /* access modifiers changed from: protected */
    public void shutdownEntry() {
        this.tracker = null;
        this.state = null;
    }

    public void tunnelTarget(boolean z, HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        Asserts.notNull(this.tracker, "Route tracker");
        Asserts.check(this.tracker.isConnected(), "Connection not open");
        Asserts.check(!this.tracker.isTunnelled(), "Connection is already tunnelled");
        this.connection.update((Socket) null, this.tracker.getTargetHost(), z, httpParams);
        this.tracker.tunnelTarget(z);
    }
}

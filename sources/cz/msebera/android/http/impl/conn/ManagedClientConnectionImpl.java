package cz.msebera.android.http.impl.conn;

import cz.msebera.android.http.HttpEntityEnclosingRequest;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.conn.ClientConnectionManager;
import cz.msebera.android.http.conn.ClientConnectionOperator;
import cz.msebera.android.http.conn.ManagedClientConnection;
import cz.msebera.android.http.conn.OperatedClientConnection;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.conn.routing.RouteTracker;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import cz.msebera.android.http.util.HttpParams;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

@Deprecated
class ManagedClientConnectionImpl implements ManagedClientConnection {
    private volatile long duration = Long.MAX_VALUE;
    private final ClientConnectionManager manager;
    private final ClientConnectionOperator operator;
    private volatile HttpPoolEntry poolEntry;
    private volatile boolean reusable = false;

    ManagedClientConnectionImpl(ClientConnectionManager clientConnectionManager, ClientConnectionOperator clientConnectionOperator, HttpPoolEntry httpPoolEntry) {
        Args.notNull(clientConnectionManager, "Connection manager");
        Args.notNull(clientConnectionOperator, "Connection operator");
        Args.notNull(httpPoolEntry, "HTTP pool entry");
        this.manager = clientConnectionManager;
        this.operator = clientConnectionOperator;
        this.poolEntry = httpPoolEntry;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [cz.msebera.android.http.pool.PoolEntry, cz.msebera.android.http.impl.conn.HttpPoolEntry] */
    private OperatedClientConnection ensureConnection() {
        ? r0 = this.poolEntry;
        if (r0 != 0) {
            return (OperatedClientConnection) r0.getConnection();
        }
        throw new ConnectionShutdownException();
    }

    private HttpPoolEntry ensurePoolEntry() {
        HttpPoolEntry $r1 = this.poolEntry;
        if ($r1 != null) {
            return $r1;
        }
        throw new ConnectionShutdownException();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [cz.msebera.android.http.pool.PoolEntry, cz.msebera.android.http.impl.conn.HttpPoolEntry] */
    private OperatedClientConnection getConnection() {
        ? r0 = this.poolEntry;
        if (r0 == 0) {
            return null;
        }
        return (OperatedClientConnection) r0.getConnection();
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [cz.msebera.android.http.pool.PoolEntry, cz.msebera.android.http.impl.conn.HttpPoolEntry] */
    public void abortConnection() {
        synchronized (this) {
            if (this.poolEntry != null) {
                this.reusable = false;
                try {
                    ((OperatedClientConnection) this.poolEntry.getConnection()).shutdown();
                } catch (IOException e) {
                }
                this.manager.releaseConnection(this, this.duration, TimeUnit.MILLISECONDS);
                this.poolEntry = null;
            }
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [cz.msebera.android.http.pool.PoolEntry, cz.msebera.android.http.impl.conn.HttpPoolEntry] */
    public void close() {
        ? r0 = this.poolEntry;
        if (r0 != 0) {
            r0.getTracker().reset();
            ((OperatedClientConnection) r0.getConnection()).close();
        }
    }

    /* access modifiers changed from: package-private */
    public HttpPoolEntry detach() {
        HttpPoolEntry r1 = this.poolEntry;
        this.poolEntry = null;
        return r1;
    }

    public void flush() {
        ensureConnection().flush();
    }

    public ClientConnectionManager getManager() {
        return this.manager;
    }

    /* access modifiers changed from: package-private */
    public HttpPoolEntry getPoolEntry() {
        return this.poolEntry;
    }

    public InetAddress getRemoteAddress() {
        return ensureConnection().getRemoteAddress();
    }

    public int getRemotePort() {
        return ensureConnection().getRemotePort();
    }

    public HttpRoute getRoute() {
        return ensurePoolEntry().getEffectiveRoute();
    }

    public SSLSession getSSLSession() {
        Socket $r2 = ensureConnection().getSocket();
        if ($r2 instanceof SSLSocket) {
            return ((SSLSocket) $r2).getSession();
        }
        return null;
    }

    public boolean isMarkedReusable() {
        return this.reusable;
    }

    public boolean isOpen() {
        OperatedClientConnection $r1 = getConnection();
        if ($r1 != null) {
            return $r1.isOpen();
        }
        return false;
    }

    public boolean isResponseAvailable(int i) {
        return ensureConnection().isResponseAvailable(i);
    }

    public boolean isStale() {
        OperatedClientConnection $r1 = getConnection();
        if ($r1 != null) {
            return $r1.isStale();
        }
        return true;
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [cz.msebera.android.http.pool.PoolEntry, cz.msebera.android.http.impl.conn.HttpPoolEntry] */
    public void layerProtocol(HttpContext httpContext, HttpParams httpParams) {
        HttpHost $r5;
        OperatedClientConnection $r7;
        Args.notNull(httpParams, "HTTP parameters");
        synchronized (this) {
            if (this.poolEntry != null) {
                RouteTracker $r4 = this.poolEntry.getTracker();
                Asserts.notNull($r4, "Route tracker");
                Asserts.check($r4.isConnected(), "Connection not open");
                Asserts.check($r4.isTunnelled(), "Protocol layering without a tunnel not supported");
                Asserts.check(!$r4.isLayered(), "Multiple protocol layering not supported");
                $r5 = $r4.getTargetHost();
                $r7 = (OperatedClientConnection) this.poolEntry.getConnection();
            } else {
                throw new ConnectionShutdownException();
            }
        }
        this.operator.updateSecureConnection($r7, $r5, httpContext, httpParams);
        synchronized (this) {
            if (this.poolEntry != null) {
                this.poolEntry.getTracker().layerProtocol($r7.isSecure());
            } else {
                throw new InterruptedIOException();
            }
        }
    }

    public void markReusable() {
        this.reusable = true;
    }

    /* JADX WARNING: type inference failed for: r7v2, types: [cz.msebera.android.http.pool.PoolEntry, cz.msebera.android.http.impl.conn.HttpPoolEntry] */
    public void open(HttpRoute httpRoute, HttpContext httpContext, HttpParams httpParams) {
        OperatedClientConnection $r7;
        Args.notNull(httpRoute, "Route");
        Args.notNull(httpParams, "HTTP parameters");
        synchronized (this) {
            if (this.poolEntry != null) {
                RouteTracker $r5 = this.poolEntry.getTracker();
                Asserts.notNull($r5, "Route tracker");
                Asserts.check(!$r5.isConnected(), "Connection already open");
                $r7 = (OperatedClientConnection) this.poolEntry.getConnection();
            } else {
                throw new ConnectionShutdownException();
            }
        }
        HttpHost $r8 = httpRoute.getProxyHost();
        this.operator.openConnection($r7, $r8 != null ? $r8 : httpRoute.getTargetHost(), httpRoute.getLocalAddress(), httpContext, httpParams);
        synchronized (this) {
            if (this.poolEntry != null) {
                RouteTracker $r52 = this.poolEntry.getTracker();
                if ($r8 == null) {
                    $r52.connectProxy($r7.isSecure());
                } else {
                    $r52.connectProxy($r8, $r7.isSecure());
                }
            } else {
                throw new InterruptedIOException();
            }
        }
    }

    public void receiveResponseEntity(HttpResponse httpResponse) {
        ensureConnection().receiveResponseEntity(httpResponse);
    }

    public HttpResponse receiveResponseHeader() {
        return ensureConnection().receiveResponseHeader();
    }

    public void releaseConnection() {
        synchronized (this) {
            if (this.poolEntry != null) {
                this.manager.releaseConnection(this, this.duration, TimeUnit.MILLISECONDS);
                this.poolEntry = null;
            }
        }
    }

    public void sendRequestEntity(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        ensureConnection().sendRequestEntity(httpEntityEnclosingRequest);
    }

    public void sendRequestHeader(HttpRequest httpRequest) {
        ensureConnection().sendRequestHeader(httpRequest);
    }

    public void setIdleDuration(long j, TimeUnit timeUnit) {
        this.duration = j > 0 ? timeUnit.toMillis(j) : -1;
    }

    public void setSocketTimeout(int i) {
        ensureConnection().setSocketTimeout(i);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [cz.msebera.android.http.pool.PoolEntry, cz.msebera.android.http.impl.conn.HttpPoolEntry] */
    public void setState(Object obj) {
        ensurePoolEntry().setState(obj);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [cz.msebera.android.http.pool.PoolEntry, cz.msebera.android.http.impl.conn.HttpPoolEntry] */
    public void shutdown() {
        ? r0 = this.poolEntry;
        if (r0 != 0) {
            r0.getTracker().reset();
            ((OperatedClientConnection) r0.getConnection()).shutdown();
        }
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [cz.msebera.android.http.pool.PoolEntry, cz.msebera.android.http.impl.conn.HttpPoolEntry] */
    public void tunnelTarget(boolean z, HttpParams httpParams) {
        HttpHost $r4;
        OperatedClientConnection $r6;
        Args.notNull(httpParams, "HTTP parameters");
        synchronized (this) {
            if (this.poolEntry != null) {
                RouteTracker $r3 = this.poolEntry.getTracker();
                Asserts.notNull($r3, "Route tracker");
                Asserts.check($r3.isConnected(), "Connection not open");
                Asserts.check(!$r3.isTunnelled(), "Connection is already tunnelled");
                $r4 = $r3.getTargetHost();
                $r6 = (OperatedClientConnection) this.poolEntry.getConnection();
            } else {
                throw new ConnectionShutdownException();
            }
        }
        $r6.update((Socket) null, $r4, z, httpParams);
        synchronized (this) {
            if (this.poolEntry != null) {
                this.poolEntry.getTracker().tunnelTarget(z);
            } else {
                throw new InterruptedIOException();
            }
        }
    }

    public void unmarkReusable() {
        this.reusable = false;
    }
}

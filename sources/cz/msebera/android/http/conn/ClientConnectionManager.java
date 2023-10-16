package cz.msebera.android.http.conn;

import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.conn.scheme.SchemeRegistry;
import java.util.concurrent.TimeUnit;

@Deprecated
public interface ClientConnectionManager {
    SchemeRegistry getSchemeRegistry();

    void releaseConnection(ManagedClientConnection managedClientConnection, long j, TimeUnit timeUnit);

    ClientConnectionRequest requestConnection(HttpRoute httpRoute, Object obj);

    void shutdown();
}

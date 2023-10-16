package cz.msebera.android.http.conn;

import cz.msebera.android.http.HttpClientConnection;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.util.HttpParams;
import java.util.concurrent.TimeUnit;

@Deprecated
public interface ManagedClientConnection extends HttpClientConnection, HttpRoutedConnection, ManagedHttpClientConnection, ConnectionReleaseTrigger {
    HttpRoute getRoute();

    void layerProtocol(HttpContext httpContext, HttpParams httpParams);

    void markReusable();

    void open(HttpRoute httpRoute, HttpContext httpContext, HttpParams httpParams);

    void setIdleDuration(long j, TimeUnit timeUnit);

    void setState(Object obj);

    void tunnelTarget(boolean z, HttpParams httpParams);

    void unmarkReusable();
}

package cz.msebera.android.http.conn.tsccm;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpParams;
import java.net.InetAddress;

@Deprecated
public class ConnRouteParams implements ConnRoutePNames {
    public static final HttpHost NO_HOST = new HttpHost("127.0.0.255", 0, "no-host");
    public static final HttpRoute NO_ROUTE = new HttpRoute(NO_HOST);

    public static HttpHost getDefaultProxy(HttpParams httpParams) {
        Args.notNull(httpParams, "Parameters");
        HttpHost $r2 = (HttpHost) httpParams.getParameter("http.route.default-proxy");
        if ($r2 == null || !NO_HOST.equals($r2)) {
            return $r2;
        }
        return null;
    }

    public static HttpRoute getForcedRoute(HttpParams httpParams) {
        Args.notNull(httpParams, "Parameters");
        HttpRoute $r2 = (HttpRoute) httpParams.getParameter("http.route.forced-route");
        if ($r2 == null || !NO_ROUTE.equals($r2)) {
            return $r2;
        }
        return null;
    }

    public static InetAddress getLocalAddress(HttpParams httpParams) {
        Args.notNull(httpParams, "Parameters");
        return (InetAddress) httpParams.getParameter("http.route.local-address");
    }
}

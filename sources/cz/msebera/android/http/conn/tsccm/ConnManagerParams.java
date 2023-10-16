package cz.msebera.android.http.conn.tsccm;

import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.util.HttpParams;

@Deprecated
public final class ConnManagerParams implements ConnManagerPNames {
    private static final ConnPerRoute DEFAULT_CONN_PER_ROUTE = new ConnPerRoute() {
        public int getMaxForRoute(HttpRoute httpRoute) {
            return 2;
        }
    };

    public static ConnPerRoute getMaxConnectionsPerRoute(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        ConnPerRoute $r2 = (ConnPerRoute) httpParams.getParameter("http.conn-manager.max-per-route");
        return $r2 == null ? DEFAULT_CONN_PER_ROUTE : $r2;
    }

    public static int getMaxTotalConnections(HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP parameters");
        return httpParams.getIntParameter("http.conn-manager.max-total", 20);
    }

    public static void setMaxConnectionsPerRoute(HttpParams httpParams, ConnPerRoute connPerRoute) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setParameter("http.conn-manager.max-per-route", connPerRoute);
    }

    public static void setMaxTotalConnections(HttpParams httpParams, int i) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setIntParameter("http.conn-manager.max-total", i);
    }

    public static void setTimeout(HttpParams httpParams, long j) {
        Args.notNull(httpParams, "HTTP parameters");
        httpParams.setLongParameter("http.conn-manager.timeout", j);
    }
}

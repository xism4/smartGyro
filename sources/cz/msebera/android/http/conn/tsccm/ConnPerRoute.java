package cz.msebera.android.http.conn.tsccm;

import cz.msebera.android.http.conn.routing.HttpRoute;

@Deprecated
public interface ConnPerRoute {
    int getMaxForRoute(HttpRoute httpRoute);
}

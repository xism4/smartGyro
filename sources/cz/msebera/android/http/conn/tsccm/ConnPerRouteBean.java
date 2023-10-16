package cz.msebera.android.http.conn.tsccm;

import c.a.a.a.e.b.b;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.mime.Args;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
public final class ConnPerRouteBean implements ConnPerRoute {
    private volatile int defaultMax;
    private final ConcurrentHashMap<b, Integer> maxPerHostMap;

    public ConnPerRouteBean() {
        this(2);
    }

    public ConnPerRouteBean(int i) {
        this.maxPerHostMap = new ConcurrentHashMap();
        setDefaultMaxPerRoute(i);
    }

    public int getMaxForRoute(HttpRoute httpRoute) {
        Args.notNull(httpRoute, "HTTP route");
        Integer $r4 = this.maxPerHostMap.get(httpRoute);
        return $r4 != null ? $r4.intValue() : this.defaultMax;
    }

    public void setDefaultMaxPerRoute(int i) {
        Args.positive(i, "Default max per route");
        this.defaultMax = i;
    }

    public String toString() {
        return this.maxPerHostMap.toString();
    }
}

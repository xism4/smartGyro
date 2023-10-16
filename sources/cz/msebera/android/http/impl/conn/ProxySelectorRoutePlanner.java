package cz.msebera.android.http.impl.conn;

import cz.msebera.android.http.HttpException;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.conn.routing.HttpRoute;
import cz.msebera.android.http.conn.routing.HttpRoutePlanner;
import cz.msebera.android.http.conn.scheme.SchemeRegistry;
import cz.msebera.android.http.conn.tsccm.ConnRouteParams;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import java.net.InetAddress;

@Deprecated
public class ProxySelectorRoutePlanner implements HttpRoutePlanner {
    protected final SchemeRegistry schemeRegistry;

    public ProxySelectorRoutePlanner(SchemeRegistry schemeRegistry2) {
        Args.notNull(schemeRegistry2, "Scheme registry");
        this.schemeRegistry = schemeRegistry2;
    }

    public HttpRoute determineRoute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
        Args.notNull(httpRequest, "HTTP request");
        HttpRoute $r5 = ConnRouteParams.getForcedRoute(httpRequest.getParams());
        if ($r5 != null) {
            return $r5;
        }
        Asserts.notNull(httpHost, "Target host");
        InetAddress $r6 = ConnRouteParams.getLocalAddress(httpRequest.getParams());
        HttpHost $r7 = ConnRouteParams.getDefaultProxy(httpRequest.getParams());
        try {
            boolean $z0 = this.schemeRegistry.getScheme(httpHost.getSchemeName()).isLayered();
            return $r7 == null ? new HttpRoute(httpHost, $r6, $z0) : new HttpRoute(httpHost, $r6, $r7, $z0);
        } catch (IllegalStateException $r11) {
            throw new HttpException($r11.getMessage());
        }
    }
}

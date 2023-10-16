package cz.msebera.android.http.client.cache;

import c.a.a.a.b.e.f;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.conn.routing.RouteInfo;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;

public class RequestAddCookies implements HttpRequestInterceptor {
    public HttpClientAndroidLog log = new HttpClientAndroidLog(f.class);

    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        Args.notNull(httpRequest, "HTTP request");
        if (httpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT")) {
            httpRequest.setHeader("Proxy-Connection", "Keep-Alive");
            return;
        }
        RouteInfo $r6 = HttpClientContext.adapt(httpContext).getHttpRoute();
        if ($r6 == null) {
            this.log.debug("Connection route not set in the context");
            return;
        }
        if (($r6.getHopCount() == 1 || $r6.isTunnelled()) && !httpRequest.containsHeader("Connection")) {
            httpRequest.addHeader("Connection", "Keep-Alive");
        }
        if ($r6.getHopCount() == 2 && !$r6.isTunnelled() && !httpRequest.containsHeader("Proxy-Connection")) {
            httpRequest.addHeader("Proxy-Connection", "Keep-Alive");
        }
    }
}

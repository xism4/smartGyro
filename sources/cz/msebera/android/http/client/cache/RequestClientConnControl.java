package cz.msebera.android.http.client.cache;

import c.a.a.a.b.e.b;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.client.CookieStore;
import cz.msebera.android.http.client.auth.HttpUriRequest;
import cz.msebera.android.http.conn.routing.RouteInfo;
import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.CookieSpec;
import cz.msebera.android.http.cookie.CookieSpecProvider;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.TextUtils;
import cz.msebera.android.http.protocol.Lookup;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RequestClientConnControl implements HttpRequestInterceptor {
    public HttpClientAndroidLog log = new HttpClientAndroidLog(b.class);

    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        URI $r16;
        Header $r27;
        Args.notNull(httpRequest, "HTTP request");
        Args.notNull(httpContext, "HTTP context");
        if (!httpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT")) {
            HttpClientContext $r6 = HttpClientContext.adapt(httpContext);
            CookieStore $r7 = $r6.getCookieStore();
            if ($r7 == null) {
                this.log.debug("Cookie store not specified in HTTP context");
                return;
            }
            Lookup $r9 = $r6.getCookieSpecRegistry();
            if ($r9 == null) {
                this.log.debug("CookieSpec registry not specified in HTTP context");
                return;
            }
            HttpHost $r10 = $r6.getTargetHost();
            if ($r10 == null) {
                this.log.debug("Target host not set in the context");
                return;
            }
            RouteInfo $r11 = $r6.getHttpRoute();
            if ($r11 == null) {
                this.log.debug("Connection route not set in the context");
                return;
            }
            String $r13 = $r6.getRequestConfig().getMaxRedirects();
            String $r5 = $r13;
            if ($r13 == null) {
                $r5 = "default";
            }
            if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog $r8 = this.log;
                $r8.debug("CookieSpec selected: " + $r5);
            }
            String $r132 = null;
            if (httpRequest instanceof HttpUriRequest) {
                $r16 = ((HttpUriRequest) httpRequest).getURI();
            } else {
                try {
                    $r16 = new URI(httpRequest.getRequestLine().getUri());
                } catch (URISyntaxException e) {
                    $r16 = null;
                }
            }
            if ($r16 != null) {
                $r132 = $r16.getPath();
            }
            String $r17 = $r10.getHostName();
            int $i0 = $r10.getPort();
            int $i1 = $i0;
            if ($i0 < 0) {
                $i1 = $r11.getTargetHost().getPort();
            }
            boolean $z0 = false;
            if ($i1 < 0) {
                $i1 = 0;
            }
            if (TextUtils.isEmpty($r132)) {
                $r132 = "/";
            }
            CookieOrigin cookieOrigin = new CookieOrigin($r17, $i1, $r132, $r11.isSecure());
            CookieSpecProvider $r20 = (CookieSpecProvider) $r9.lookup($r5);
            if ($r20 != null) {
                CookieSpec $r21 = $r20.create($r6);
                List<Cookie> $r22 = $r7.getCookies();
                ArrayList arrayList = new ArrayList();
                Date date = new Date();
                for (Cookie $r26 : $r22) {
                    if ($r26.isExpired(date)) {
                        if (this.log.isDebugEnabled()) {
                            HttpClientAndroidLog $r82 = this.log;
                            $r82.debug("Cookie " + $r26 + " expired");
                        }
                        $z0 = true;
                    } else if ($r21.match($r26, cookieOrigin)) {
                        if (this.log.isDebugEnabled()) {
                            HttpClientAndroidLog $r83 = this.log;
                            $r83.debug("Cookie " + $r26 + " match " + cookieOrigin);
                        }
                        arrayList.add($r26);
                    }
                }
                if ($z0) {
                    $r7.clearExpired(date);
                }
                if (!arrayList.isEmpty()) {
                    for (Header $r272 : $r21.formatCookies(arrayList)) {
                        httpRequest.addHeader($r272);
                    }
                }
                if ($r21.getVersion() > 0 && ($r27 = $r21.getVersionHeader()) != null) {
                    httpRequest.addHeader($r27);
                }
                httpContext.setAttribute("http.cookie-spec", $r21);
                httpContext.setAttribute("http.cookie-origin", cookieOrigin);
            } else if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog $r84 = this.log;
                $r84.debug("Unsupported cookie policy: " + $r5);
            }
        }
    }
}

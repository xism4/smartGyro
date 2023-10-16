package cz.msebera.android.http.client.cache;

import c.a.a.a.b.e.j;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HeaderIterator;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.HttpResponseInterceptor;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.client.CookieStore;
import cz.msebera.android.http.cookie.Cookie;
import cz.msebera.android.http.cookie.CookieOrigin;
import cz.msebera.android.http.cookie.CookieSpec;
import cz.msebera.android.http.cookie.MalformedCookieException;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;

public class ResponseProcessCookies implements HttpResponseInterceptor {
    public HttpClientAndroidLog log = new HttpClientAndroidLog(j.class);

    private static String formatCooke(Cookie cookie) {
        StringBuilder $r0 = new StringBuilder();
        $r0.append(cookie.getName());
        $r0.append("=\"");
        String $r2 = cookie.getValue();
        String $r3 = $r2;
        if ($r2 != null) {
            if ($r2.length() > 100) {
                $r3 = $r2.substring(0, 100) + "...";
            }
            $r0.append($r3);
        }
        $r0.append("\"");
        $r0.append(", version:");
        $r0.append(Integer.toString(cookie.getVersion()));
        $r0.append(", domain:");
        $r0.append(cookie.getDomain());
        $r0.append(", path:");
        $r0.append(cookie.getPath());
        $r0.append(", expiry:");
        $r0.append(cookie.getExpiryDate());
        return $r0.toString();
    }

    private void processCookies(HeaderIterator headerIterator, CookieSpec cookieSpec, CookieOrigin cookieOrigin, CookieStore cookieStore) {
        while (headerIterator.hasNext()) {
            Header $r5 = headerIterator.nextHeader();
            try {
                for (Cookie $r9 : cookieSpec.parse($r5, cookieOrigin)) {
                    try {
                        cookieSpec.validate($r9, cookieOrigin);
                        cookieStore.addCookie($r9);
                        if (this.log.isDebugEnabled()) {
                            HttpClientAndroidLog $r10 = this.log;
                            $r10.debug("Cookie accepted [" + formatCooke($r9) + "]");
                        }
                    } catch (MalformedCookieException $r13) {
                        if (this.log.isWarnEnabled()) {
                            HttpClientAndroidLog $r102 = this.log;
                            $r102.warn("Cookie rejected [" + formatCooke($r9) + "] " + $r13.getMessage());
                        }
                    }
                }
            } catch (MalformedCookieException $r14) {
                if (this.log.isWarnEnabled()) {
                    HttpClientAndroidLog $r103 = this.log;
                    $r103.warn("Invalid cookie header: \"" + $r5 + "\". " + $r14.getMessage());
                }
            }
        }
    }

    public void process(HttpResponse httpResponse, HttpContext httpContext) {
        HttpClientAndroidLog $r5;
        String $r6;
        Args.notNull(httpResponse, "HTTP request");
        Args.notNull(httpContext, "HTTP context");
        HttpClientContext $r3 = HttpClientContext.adapt(httpContext);
        CookieSpec $r4 = $r3.getCookieSpec();
        if ($r4 == null) {
            $r5 = this.log;
            $r6 = "Cookie spec not specified in HTTP context";
        } else {
            CookieStore $r7 = $r3.getCookieStore();
            if ($r7 == null) {
                $r5 = this.log;
                $r6 = "Cookie store not specified in HTTP context";
            } else {
                CookieOrigin $r8 = $r3.getCookieOrigin();
                if ($r8 == null) {
                    $r5 = this.log;
                    $r6 = "Cookie origin not specified in HTTP context";
                } else {
                    processCookies(httpResponse.headerIterator("Set-Cookie"), $r4, $r8, $r7);
                    if ($r4.getVersion() > 0) {
                        processCookies(httpResponse.headerIterator("Set-Cookie2"), $r4, $r8, $r7);
                        return;
                    }
                    return;
                }
            }
        }
        $r5.debug($r6);
    }
}

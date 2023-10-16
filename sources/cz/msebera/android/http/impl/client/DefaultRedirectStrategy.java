package cz.msebera.android.http.impl.client;

import c.a.a.a.i.b.n;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.ProtocolException;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.client.CircularRedirectException;
import cz.msebera.android.http.client.RedirectStrategy;
import cz.msebera.android.http.client.auth.HttpDelete;
import cz.msebera.android.http.client.auth.HttpHead;
import cz.msebera.android.http.client.auth.HttpUriRequest;
import cz.msebera.android.http.client.auth.RequestBuilder;
import cz.msebera.android.http.client.cache.HttpClientContext;
import cz.msebera.android.http.client.methods.RequestConfig;
import cz.msebera.android.http.client.ssl.URIBuilder;
import cz.msebera.android.http.client.ssl.URIUtils;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import cz.msebera.android.http.mime.Asserts;
import cz.msebera.android.http.mime.TextUtils;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

public class DefaultRedirectStrategy implements RedirectStrategy {
    public static final DefaultRedirectStrategy INSTANCE = new DefaultRedirectStrategy();
    private static final String[] REDIRECT_METHODS = {"GET", "HEAD"};
    public HttpClientAndroidLog log = new HttpClientAndroidLog(n.class);

    /* access modifiers changed from: protected */
    public URI createLocationURI(String str) {
        try {
            URIBuilder $r2 = new URIBuilder(new URI(str).normalize());
            String $r4 = $r2.getHost();
            if ($r4 != null) {
                $r2.setHost($r4.toLowerCase(Locale.ROOT));
            }
            if (TextUtils.isEmpty($r2.getPath())) {
                $r2.setPath("/");
            }
            return $r2.build();
        } catch (URISyntaxException $r6) {
            throw new ProtocolException("Invalid redirect URI: " + str, $r6);
        }
    }

    public URI getLocationURI(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) {
        Args.notNull(httpRequest, "HTTP request");
        Args.notNull(httpResponse, "HTTP response");
        Args.notNull(httpContext, "HTTP context");
        HttpClientContext $r4 = HttpClientContext.adapt(httpContext);
        Header $r5 = httpResponse.getFirstHeader("location");
        if ($r5 != null) {
            String $r6 = $r5.getValue();
            if (this.log.isDebugEnabled()) {
                HttpClientAndroidLog $r7 = this.log;
                $r7.debug("Redirect requested to location '" + $r6 + "'");
            }
            RequestConfig $r10 = $r4.getRequestConfig();
            URI $r11 = createLocationURI($r6);
            URI $r12 = $r11;
            try {
                if (!$r11.isAbsolute()) {
                    if ($r10.isCircularRedirectsAllowed()) {
                        HttpHost $r13 = $r4.getTargetHost();
                        Asserts.notNull($r13, "Target host");
                        $r12 = URIUtils.resolve(URIUtils.rewriteURI(new URI(httpRequest.getRequestLine().getUri()), $r13, false), $r11);
                    } else {
                        throw new ProtocolException("Relative redirect location '" + $r11 + "' not allowed");
                    }
                }
                RedirectLocations $r17 = (RedirectLocations) $r4.getAttribute("http.protocol.redirect-locations");
                if ($r17 == null) {
                    $r17 = new RedirectLocations();
                    httpContext.setAttribute("http.protocol.redirect-locations", $r17);
                }
                if ($r10.getConnectionRequestTimeout() || !$r17.contains($r12)) {
                    $r17.add($r12);
                    return $r12;
                }
                throw new CircularRedirectException("Circular redirect to '" + $r12 + "'");
            } catch (URISyntaxException $r19) {
                throw new ProtocolException($r19.getMessage(), $r19);
            }
        } else {
            throw new ProtocolException("Received redirect response " + httpResponse.getStatusLine() + " but no location header");
        }
    }

    public HttpUriRequest getRedirect(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) {
        URI $r4 = getLocationURI(httpRequest, httpResponse, httpContext);
        String $r6 = httpRequest.getRequestLine().getMethod();
        if ($r6.equalsIgnoreCase("HEAD")) {
            return new HttpDelete($r4);
        }
        if ($r6.equalsIgnoreCase("GET")) {
            return new HttpHead($r4);
        }
        if (httpResponse.getStatusLine().getStatusCode() != 307) {
            return new HttpHead($r4);
        }
        RequestBuilder $r10 = RequestBuilder.copy(httpRequest);
        $r10.setUri($r4);
        return $r10.build();
    }

    /* access modifiers changed from: protected */
    public boolean isRedirectable(String str) {
        for (String $r3 : REDIRECT_METHODS) {
            if ($r3.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean isRedirected(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) {
        Args.notNull(httpRequest, "HTTP request");
        Args.notNull(httpResponse, "HTTP response");
        int $i0 = httpResponse.getStatusLine().getStatusCode();
        String $r6 = httpRequest.getRequestLine().getMethod();
        Header $r7 = httpResponse.getFirstHeader("location");
        if ($i0 != 307) {
            switch ($i0) {
                case 301:
                    break;
                case 302:
                    return isRedirectable($r6) && $r7 != null;
                case 303:
                    return true;
                default:
                    return false;
            }
        }
        return isRedirectable($r6);
    }
}

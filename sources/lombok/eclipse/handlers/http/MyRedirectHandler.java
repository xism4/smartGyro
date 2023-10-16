package lombok.eclipse.handlers.http;

import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.ProtocolException;
import cz.msebera.android.http.client.CircularRedirectException;
import cz.msebera.android.http.client.ssl.URIUtils;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.impl.client.DefaultRedirectHandler;
import cz.msebera.android.http.impl.client.RedirectLocations;
import cz.msebera.android.http.util.HttpParams;
import java.net.URI;
import java.net.URISyntaxException;

class MyRedirectHandler extends DefaultRedirectHandler {
    private final boolean enableRedirects;

    public MyRedirectHandler(boolean z) {
        this.enableRedirects = z;
    }

    public URI getLocationURI(HttpResponse httpResponse, HttpContext httpContext) {
        URI $r10;
        if (httpResponse != null) {
            Header $r3 = httpResponse.getFirstHeader("location");
            if ($r3 != null) {
                String $r4 = $r3.getValue().replaceAll(" ", "%20");
                try {
                    URI $r5 = new URI($r4);
                    HttpParams $r6 = httpResponse.getParams();
                    if (!$r5.isAbsolute()) {
                        if (!$r6.isParameterTrue("http.protocol.reject-relative-redirect")) {
                            HttpHost $r8 = (HttpHost) httpContext.getAttribute("http.target_host");
                            if ($r8 != null) {
                                try {
                                    $r5 = URIUtils.resolve(URIUtils.rewriteURI(new URI(((HttpRequest) httpContext.getAttribute("http.request")).getRequestLine().getUri()), $r8, true), $r5);
                                } catch (URISyntaxException $r12) {
                                    throw new ProtocolException($r12.getMessage(), $r12);
                                }
                            } else {
                                throw new IllegalStateException("Target host not available in the HTTP context");
                            }
                        } else {
                            throw new ProtocolException("Relative redirect location '" + $r5 + "' not allowed");
                        }
                    }
                    if (!$r6.isParameterFalse("http.protocol.allow-circular-redirects")) {
                        return $r5;
                    }
                    RedirectLocations $r16 = (RedirectLocations) httpContext.getAttribute("http.protocol.redirect-locations");
                    if ($r16 == null) {
                        $r16 = new RedirectLocations();
                        httpContext.setAttribute("http.protocol.redirect-locations", $r16);
                    }
                    if ($r5.getFragment() != null) {
                        try {
                            $r10 = URIUtils.rewriteURI($r5, new HttpHost($r5.getHost(), $r5.getPort(), $r5.getScheme()), true);
                        } catch (URISyntaxException $r18) {
                            throw new ProtocolException($r18.getMessage(), $r18);
                        }
                    } else {
                        $r10 = $r5;
                    }
                    if (!$r16.contains($r10)) {
                        $r16.add($r10);
                        return $r5;
                    }
                    throw new CircularRedirectException("Circular redirect to '" + $r10 + "'");
                } catch (URISyntaxException $r20) {
                    throw new ProtocolException("Invalid redirect URI: " + $r4, $r20);
                }
            } else {
                throw new ProtocolException("Received redirect response " + httpResponse.getStatusLine() + " but no location header");
            }
        } else {
            throw new IllegalArgumentException("HTTP response may not be null");
        }
    }

    public boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext) {
        if (!this.enableRedirects) {
            return false;
        }
        if (httpResponse != null) {
            int $i0 = httpResponse.getStatusLine().getStatusCode();
            if ($i0 == 307) {
                return true;
            }
            switch ($i0) {
                case 301:
                case 302:
                case 303:
                    return true;
                default:
                    return false;
            }
        } else {
            throw new IllegalArgumentException("HTTP response may not be null");
        }
    }
}

package cz.msebera.android.http.client.cache;

import c.a.a.a.e;
import cz.msebera.android.http.Header;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import java.util.Collection;

public class RequestDefaultHeaders implements HttpRequestInterceptor {
    private final Collection<? extends e> defaultHeaders;

    public RequestDefaultHeaders() {
        this((Collection) null);
    }

    public RequestDefaultHeaders(Collection collection) {
        this.defaultHeaders = collection;
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        Args.notNull(httpRequest, "HTTP request");
        if (!httpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT")) {
            Collection<Header> $r7 = (Collection) httpRequest.getParams().getParameter("http.default-headers");
            if ($r7 == null) {
                $r7 = this.defaultHeaders;
            }
            if ($r7 != null) {
                for (Header $r9 : $r7) {
                    httpRequest.addHeader($r9);
                }
            }
        }
    }
}

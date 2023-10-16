package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.client.ClientProtocolException;
import cz.msebera.android.http.client.HttpClient;
import cz.msebera.android.http.client.auth.CloseableHttpResponse;
import cz.msebera.android.http.client.auth.HttpUriRequest;
import cz.msebera.android.http.client.ssl.URIUtils;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.mime.Args;
import java.io.Closeable;
import java.net.URI;

public abstract class CloseableHttpClient implements HttpClient, Closeable {
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());

    private static HttpHost determineTarget(HttpUriRequest httpUriRequest) {
        URI $r2 = httpUriRequest.getURI();
        if (!$r2.isAbsolute()) {
            return null;
        }
        HttpHost $r3 = URIUtils.extractHost($r2);
        if ($r3 != null) {
            return $r3;
        }
        throw new ClientProtocolException("URI does not specify a valid host name: " + $r2);
    }

    /* access modifiers changed from: protected */
    public abstract CloseableHttpResponse doExecute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext);

    public CloseableHttpResponse execute(HttpUriRequest httpUriRequest, HttpContext httpContext) {
        Args.notNull(httpUriRequest, "HTTP request");
        return doExecute(determineTarget(httpUriRequest), httpUriRequest, httpContext);
    }
}

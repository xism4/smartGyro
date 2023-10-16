package lombok.eclipse.handlers.http;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpRequestInterceptor;
import cz.msebera.android.http.auth.AuthScope;
import cz.msebera.android.http.auth.AuthState;
import cz.msebera.android.http.auth.Credentials;
import cz.msebera.android.http.client.CredentialsProvider;
import cz.msebera.android.http.execchain.HttpContext;
import cz.msebera.android.http.impl.auth.BasicScheme;

class RequestDefaultHeaders implements HttpRequestInterceptor {
    final /* synthetic */ AsyncHttpClient defaultHeaders;

    RequestDefaultHeaders(AsyncHttpClient asyncHttpClient) {
        this.defaultHeaders = asyncHttpClient;
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        Credentials $r10;
        AuthState $r4 = (AuthState) httpContext.getAttribute("http.auth.target-scope");
        CredentialsProvider $r5 = (CredentialsProvider) httpContext.getAttribute("http.auth.credentials-provider");
        HttpHost $r6 = (HttpHost) httpContext.getAttribute("http.target_host");
        if ($r4.getAuthScheme() == null && ($r10 = $r5.getCredentials(new AuthScope($r6.getHostName(), $r6.getPort()))) != null) {
            $r4.setAuthScheme(new BasicScheme());
            $r4.update($r10);
        }
    }
}

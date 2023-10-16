package cz.msebera.android.http.impl.client;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.auth.AuthState;
import cz.msebera.android.http.cache.HttpClientAndroidLog;
import cz.msebera.android.http.client.AuthenticationStrategy;
import cz.msebera.android.http.execchain.HttpContext;

@Deprecated
public class HttpAuthenticator extends cz.msebera.android.http.impl.auth.HttpAuthenticator {
    public HttpAuthenticator(HttpClientAndroidLog httpClientAndroidLog) {
        super(httpClientAndroidLog);
    }

    public boolean authenticate(HttpHost httpHost, HttpResponse httpResponse, AuthenticationStrategy authenticationStrategy, AuthState authState, HttpContext httpContext) {
        return handleAuthChallenge(httpHost, httpResponse, authenticationStrategy, authState, httpContext);
    }
}

package cz.msebera.android.http.client;

import cz.msebera.android.http.HttpHost;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.auth.AuthScheme;
import cz.msebera.android.http.execchain.HttpContext;
import java.util.Map;
import java.util.Queue;

public interface AuthenticationStrategy {
    void authFailed(HttpHost httpHost, AuthScheme authScheme, HttpContext httpContext);

    void authSucceeded(HttpHost httpHost, AuthScheme authScheme, HttpContext httpContext);

    Map getChallenges(HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext);

    boolean isAuthenticationRequested(HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext);

    Queue select(Map map, HttpHost httpHost, HttpResponse httpResponse, HttpContext httpContext);
}

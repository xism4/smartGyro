package cz.msebera.android.http.client;

import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.auth.AuthScheme;
import cz.msebera.android.http.execchain.HttpContext;
import java.util.Map;

@Deprecated
public interface AuthenticationHandler {
    Map getChallenges(HttpResponse httpResponse, HttpContext httpContext);

    boolean isAuthenticationRequested(HttpResponse httpResponse, HttpContext httpContext);

    AuthScheme selectScheme(Map map, HttpResponse httpResponse, HttpContext httpContext);
}

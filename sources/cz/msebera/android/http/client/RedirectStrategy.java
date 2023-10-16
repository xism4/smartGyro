package cz.msebera.android.http.client;

import cz.msebera.android.http.HttpRequest;
import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.client.auth.HttpUriRequest;
import cz.msebera.android.http.execchain.HttpContext;

public interface RedirectStrategy {
    HttpUriRequest getRedirect(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext);

    boolean isRedirected(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext);
}

package cz.msebera.android.http.client;

import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.execchain.HttpContext;
import java.net.URI;

@Deprecated
public interface RedirectHandler {
    URI getLocationURI(HttpResponse httpResponse, HttpContext httpContext);

    boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext);
}

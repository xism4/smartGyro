package cz.msebera.android.http;

import cz.msebera.android.http.execchain.HttpContext;

public interface ConnectionReuseStrategy {
    boolean keepAlive(HttpResponse httpResponse, HttpContext httpContext);
}

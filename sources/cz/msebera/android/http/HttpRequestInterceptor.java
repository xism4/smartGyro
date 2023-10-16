package cz.msebera.android.http;

import cz.msebera.android.http.execchain.HttpContext;

public interface HttpRequestInterceptor {
    void process(HttpRequest httpRequest, HttpContext httpContext);
}

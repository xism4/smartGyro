package cz.msebera.android.http;

import cz.msebera.android.http.execchain.HttpContext;

public interface HttpResponseInterceptor {
    void process(HttpResponse httpResponse, HttpContext httpContext);
}

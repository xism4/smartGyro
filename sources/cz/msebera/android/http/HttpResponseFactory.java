package cz.msebera.android.http;

import cz.msebera.android.http.execchain.HttpContext;

public interface HttpResponseFactory {
    HttpResponse newHttpResponse(StatusLine statusLine, HttpContext httpContext);
}

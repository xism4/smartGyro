package cz.msebera.android.http.conn;

import cz.msebera.android.http.HttpResponse;
import cz.msebera.android.http.execchain.HttpContext;

public interface ConnectionKeepAliveStrategy {
    long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext);
}

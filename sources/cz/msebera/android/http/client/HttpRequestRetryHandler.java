package cz.msebera.android.http.client;

import cz.msebera.android.http.execchain.HttpContext;
import java.io.IOException;

public interface HttpRequestRetryHandler {
    boolean retryRequest(IOException iOException, int i, HttpContext httpContext);
}
